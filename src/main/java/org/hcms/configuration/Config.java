package org.hcms.configuration;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {

    public static final String DB_URL = "db.url";
    public static final String DB_USER = "db.user";
    public static final String DB_PASSWORD = "db.password";
    public static final String ADMIN_NAME = "admin.name";
    public static final String ADMIN_PASSWORD = "admin.password";


    private final String DEFAULT_CONFIG_FILE = "/config.properties";
    private static final Config config = new Config();

    private Properties prop = new Properties();
    private boolean loaded = false;

    private Config() {}
    public static Config getInstance() {
        return config;
    }

    public String getProperty(final String key) {
        return prop.getProperty(key);
    }

    public void loadConfig(final String path) throws  DefaultConfigurationLoadError {
        if (loaded) {
            return;
        }
        try {
            loadUtil(new FileInputStream(path));
        } catch (Exception ex) {
            try {
                loadUtil(getClass().getResourceAsStream(config.DEFAULT_CONFIG_FILE));
            } catch (Exception exception) {
                ex.printStackTrace();
                throw new DefaultConfigurationLoadError();
            }
        }
        loaded = true;
    }

    private void loadUtil(InputStream input) throws IOException {
        prop = new Properties();
        prop.load(input);
    }

    class DefaultConfigurationLoadError extends Error {

        public DefaultConfigurationLoadError() {
            super("Load of Default configuration failed");
        }
    }
}
