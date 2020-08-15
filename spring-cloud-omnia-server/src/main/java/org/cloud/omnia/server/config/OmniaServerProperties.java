package org.cloud.omnia.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("spring.cloud.omnia.server")
public class OmniaServerProperties {

    /**
     * Stored server name.
     */
    private String prefix;

    /**
     * Stores queue and DB sync time.
     */
    private Long syncTime;

    /**
     * Max queue size. The data in queue is synced,
     * when the queue reaches this size.
     */
    private Integer maxQueueSize;

    /**
     * Default DB sync time b/w DB and omnia server.
     */
    private static final long DEFAULT_SYNC_TIME = 10000L;

    /**
     * Default max queue size.
     */
    private static final int DEFAULT_MAX_QUEUE_SIZE = 10;

    /**
     * Getter of prefix.
     * @return Omnia server perfix.
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Stores the omnia server prefix.
     * @param value Omnia server prefix
     */
    public void setPrefix(final String value) {
        this.prefix = value;
    }

    /**
     *
     * @return sync time with DB.
     */
    public Long getSyncTime() {
        if (syncTime != null) {
            return syncTime;
        }
        return DEFAULT_SYNC_TIME;
    }

    /**
     * @param value store DB sync time
     */
    public void setSyncTime(final Long value) {
        this.syncTime = value;
    }

    /**
     * @return get max queue size
     */
    public Integer getMaxQueueSize() {
        if (maxQueueSize != null) {
            return maxQueueSize;
        }
        return DEFAULT_MAX_QUEUE_SIZE;
    }

    /**
     *
     * @param value set max queue size
     */
    public void setMaxQueueSize(final Integer value) {
        this.maxQueueSize = value;
    }
}
