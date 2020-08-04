package org.cloud.omnia.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration(proxyBeanMethods = false)
public class OmniaServerClassloaderConfig {

    private static final Logger logger = Logger.getLogger(OmniaServerClassloaderConfig.class.getName());


    @Bean
    public Marker enableOmniaServerMarker(){
        return new Marker();
    }

    public OmniaServerClassloaderConfig(){
        logger.info(OmniaServerClassloaderConfig.class.getName() + " has been loaded");
    }

    public class Marker{}
}
