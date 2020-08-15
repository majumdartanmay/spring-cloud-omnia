package org.cloud.omnia.server.config;

import org.cloud.omnia.server.database.OmniaDBLoader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * Class to load necessary configuration beans.
 *
 * @author Tanmay Majumdar
 */
@ConditionalOnBean(OmniaServerClassloaderConfig.Marker.class)
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(OmniaServerProperties.class)
@Import({ OmniaDBLoader.class,
        OmniaServerScheduledConfiguration.class,
        OmniaServerMVCConfiguration.class})
public class OmniaServerAutoConfiguration {

}
