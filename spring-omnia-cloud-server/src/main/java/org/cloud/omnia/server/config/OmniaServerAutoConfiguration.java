package org.cloud.omnia.server.config;

import org.cloud.omnia.server.database.OmniaDBLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.logging.Logger;

@ConditionalOnBean(OmniaClassloaderConfig.Marker.class)
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(OmniaServerProperties.class)
@Import({OmniaDBLoader.class, OmniaServerMVCConfiguration.class, OmniaScheduledConfiguration.class})
public class OmniaServerAutoConfiguration {

    private static final Logger logger = Logger.getLogger(OmniaServerAutoConfiguration.class.getName());

    @Autowired
    private OmniaServerProperties omniaServerProperties;

    public OmniaServerAutoConfiguration(){
        logger.info(OmniaServerAutoConfiguration.class.getName() + " has been initialized " );
    }
}
