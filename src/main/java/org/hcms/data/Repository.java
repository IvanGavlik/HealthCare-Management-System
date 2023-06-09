package org.hcms.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class Repository {

    private Connection connection;

    private Repository() {
        this.connection = ConnectionProviderDefault.getCon()
                .orElseThrow(RepositoryException::new);
    }
    private Repository(Connection connection) {
        this.connection = connection;
    }

    private static Repository repository = new Repository();
    public static synchronized Repository getInstance() {
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }

    @Deprecated
    public Connection getConnection() {
        this.checkConnection();
        return connection;
    }

    public boolean executeUpdate(String sql) {
        this.checkConnection();
        try {
            Statement st= this.connection.createStatement();
            st.executeUpdate(sql);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                this.connection.close();
            } catch (SQLException e) {
                throw new RepositoryConnectionCloseException();
            }
        }
    }
    // TODO CHECK QUERY RETURN TYPES IN ALL PLACES WHERE IS USED
    public <RESULT_ITEM> List<RESULT_ITEM> executeQuery(String query, Function<ResultSet, RESULT_ITEM> function) {
        this.checkConnection();
        List<RESULT_ITEM> result = new ArrayList<>();
        try {
            Statement st= this.connection.createStatement();
            ResultSet resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                result.add(function.apply(resultSet));
            }
        } catch (Exception ex) {
            throw new RepositoryException();
        } finally {
            try {
                this.connection.close();
            } catch (SQLException e) {
                throw new RepositoryConnectionCloseException();
            }
        }
        return result;
    }

    private void checkConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                this.connection = ConnectionProviderDefault.getCon().orElseThrow(RepositoryException::new);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

class RepositoryException extends RuntimeException { }

class RepositoryConnectionCloseException extends RepositoryException {}
