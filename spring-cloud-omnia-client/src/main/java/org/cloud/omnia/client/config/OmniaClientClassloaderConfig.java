package org.cloud.omnia.client.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class OmniaClientClassloaderConfig {

    @Bean
    public Marker enableOmniaClientMarker(){
        return new Marker();
    }

    public class Marker{}

}
