package org.hcms.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class LoginImpl implements Login {

    private Repository repository;
    public LoginImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public boolean loginUser(int id, String password, String type) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT userID FROM Users where userID = ").append(id)
                .append(" ")
                .append("and Password = '").append(password.trim())
                .append("' ")
                .append("and userType = '").append(type).append("'")
                .append(";");

        Function<ResultSet, Integer> idMapper = (rs) -> {
            try {
                return rs.getInt(0);
            } catch (Exception ex) {
                return -1;
            }
        };

        return repository.executeQuery(sql.toString(), idMapper).size() == 1;
    }
}
