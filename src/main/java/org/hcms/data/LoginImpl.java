package org.hcms.data;

public class LoginImpl implements Login {

    Repository repository;

    public LoginImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public boolean loginUser(int id, String password, String type) {
        return true;
    }

    /**

     Function<ResultSet, Integer> mapper = rs -> {
     try {
     if(rs.getInt(1)==id
     && rs.getString(2).compareTo("Patient")==0
     && (rs.getString(3).compareTo(pd)==0 ))  {
     return 1;
     }
     return -1;
     } catch (SQLException e) {
     throw new RuntimeException(e);
     }
     };
     flag = Repository.getInstance().executeQuery("Select * from Users", mapper)
     .stream()
     .filter( el -> el == 1)
     .collect(Collectors.toList())
     .get(0);

     */
}
