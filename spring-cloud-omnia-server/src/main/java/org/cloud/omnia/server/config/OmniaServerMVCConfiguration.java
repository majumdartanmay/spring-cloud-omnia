package org.cloud.omnia.server.config;

import org.cloud.omnia.server.database.repository.NetworkRequestRepository;
import org.cloud.omnia.server.processor.BasicOmniaQueue;
import org.cloud.omnia.server.web.OmniaController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
public class OmniaServerMVCConfiguration {

    private static final Logger log = Logger.getLogger(OmniaServerMVCConfiguration.class.getName());

    public OmniaServerMVCConfiguration(){
        log.info("Omnia server mvc configured");
    }

    @Bean
    public OmniaController getOmniaController(BasicOmniaQueue basicOmniaQueue){
        return new OmniaController(basicOmniaQueue);
    }
}
