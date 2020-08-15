package org.cloud.omnia.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public final class OmniaServerClassloaderConfig {
    /**
     *
     * @return Marker bean.
     */
    @Bean
    public Marker enableOmniaServerMarker() {
        return new Marker();
    }
    public class Marker { }
}
