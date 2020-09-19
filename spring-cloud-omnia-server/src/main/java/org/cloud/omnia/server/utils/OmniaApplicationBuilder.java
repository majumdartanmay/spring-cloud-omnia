package org.cloud.omnia.server.utils;

import applications.BaseOmniaApplication;
import applications.IOmniaApplication;
import applications.LocalOmniaApplication;

public class OmniaApplicationBuilder {
    private String ipAddr;
    private String applicationName;

    public OmniaApplicationBuilder setIpAddr(String ipAddr){
        this.ipAddr = ipAddr;
        return this;
    }

    public OmniaApplicationBuilder setApplicationName(String name) {
        this.applicationName = name;
        return this;
    }

    public IOmniaApplication build(){
        IOmniaApplication app = new LocalOmniaApplication();
        app.setApplicationName(applicationName);
        app.setHost(ipAddr);
        return app;
    }
}
