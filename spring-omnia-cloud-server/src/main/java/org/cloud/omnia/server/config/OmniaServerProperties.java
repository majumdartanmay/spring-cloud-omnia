package org.cloud.omnia.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.logging.Logger;


@ConfigurationProperties("spring.cloud.omnia.server")
public class OmniaServerProperties {

    private static final Logger logger = Logger.getLogger(OmniaServerProperties.class.getName());

    private String prefix;
    private Long syncTime;
    private Integer maxQueueSize;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Long getSyncTime() {
        if(syncTime != null)
            return syncTime;
        return 10000L;
    }

    public void setSyncTime(Long syncTime) {
        this.syncTime = syncTime;
    }

    public Integer getMaxQueueSize() {
        if(maxQueueSize != null)
            return maxQueueSize;
        return 10;
    }

    public void setMaxQueueSize(Integer maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
    }
}
