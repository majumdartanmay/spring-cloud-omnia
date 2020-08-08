package org.cloud.omnia.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.cloud.omnia.client")
public class OmniaClientProperties {

    private String omniaUrl;

    private String omniaServerName;

    private boolean strict;

    public String getOmniaUrl() {
        return omniaUrl;
    }

    public void setOmniaUrl(String omniaUrl) {
        this.omniaUrl = omniaUrl;
    }

    public String getOmniaServerName() {
        return omniaServerName;
    }

    public void setOmniaServerName(String omniaServerName) {
        this.omniaServerName = omniaServerName;
    }

    public boolean isStrict() {
        return strict;
    }

    public void setStrict(boolean strict) {
        this.strict = strict;
    }
}
