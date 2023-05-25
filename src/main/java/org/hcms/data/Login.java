package org.hcms.data;

public interface Login {
    Login DEFAULT_INSTANCE = new LoginImpl(Repository.getInstance());
    boolean loginUser(int id, String password, String type);
}
