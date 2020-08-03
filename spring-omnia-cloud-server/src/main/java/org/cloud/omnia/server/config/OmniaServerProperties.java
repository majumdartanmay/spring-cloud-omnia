package org.cloud.omnia.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.logging.Logger;


@ConfigurationProperties("spring.cloud.omnia.server")
public class OmniaServerProperties {

    private static final Logger logger = Logger.getLogger(OmniaServerProperties.class.getName());

    public OmniaServerProperties(){
    }

    private DataStore dataStore;

    public DataStore getDataStore() {
        return dataStore;
    }

    public void setDataStore(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public static class DataStore{
        private String username;
        private String password;
        private String url;
        private String dbType;

        public DataStore() {
            logger.info("data store properties created");
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDbType() {
            return dbType;
        }

        public void setDbType(String dbType) {
            this.dbType = dbType;
        }
    }

}
