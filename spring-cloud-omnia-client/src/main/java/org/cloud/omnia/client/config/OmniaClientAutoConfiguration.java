package org.cloud.omnia.client.config;


import org.cloud.omnia.client.network.OmniaNetworkInterceptor;
import org.cloud.omnia.client.network.web.OmniaServerService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.logging.Logger;

@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(OmniaClientClassloaderConfig.Marker.class)
@EnableConfigurationProperties(OmniaClientProperties.class)
@Import({OmniaServerService.class, OmniaNetworkInterceptor.class, OmniaClientMVCConfiguration.class})
public class OmniaClientAutoConfiguration {
    private static Logger logger = Logger.getLogger(OmniaClientAutoConfiguration.class.getName());
    public OmniaClientAutoConfiguration(){
        logger.info("OmniaClientAutoConfiguration added");
    }
}
