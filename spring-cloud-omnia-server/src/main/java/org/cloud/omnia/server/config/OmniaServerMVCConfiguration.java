package org.cloud.omnia.server.config;

import org.cloud.omnia.server.processor.BasicOmniaQueue;
import org.cloud.omnia.server.web.OmniaController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class to create web configuration beans.
 *
 * @author Tanmay Majumdar
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
public class OmniaServerMVCConfiguration {

    /**
     * Function to return omnia controller bean.
     * @param basicOmniaQueue queue for data-transaction purposes.
     * @return OmniaController.
     */
    @Bean
    @ConditionalOnBean(BasicOmniaQueue.class)
    public OmniaController getOmniaController(
            final BasicOmniaQueue basicOmniaQueue) {
        return new OmniaController(basicOmniaQueue);
    }
}
