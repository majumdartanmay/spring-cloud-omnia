package org.cloud.omnia.server.config;

import org.cloud.omnia.server.database.OmniaDBLoader;
import org.cloud.omnia.server.processor.BasicOmniaQueue;
import org.cloud.omnia.server.processor.OmniaJobQueueProcessor;
import org.slf4j.event.Level;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.logging.Logger;

@EnableScheduling
@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(OmniaDBLoader.class)
@Import({BasicOmniaQueue.class,OmniaJobQueueProcessor.class })
public class OmniaServerScheduledConfiguration {
    private static final Logger logger = Logger.getLogger(OmniaServerMVCConfiguration.class.getName());
    public OmniaServerScheduledConfiguration(){
        logger.info("Omnia Server scheduled configuration created");
    }

}
