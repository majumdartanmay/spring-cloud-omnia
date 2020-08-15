package org.cloud.omnia.server.config;

import org.cloud.omnia.server.database.OmniaDBLoader;
import org.cloud.omnia.server.processor.BasicOmniaQueue;
import org.cloud.omnia.server.processor.OmniaJobQueueProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Class to load data exchange processor classes.
 */
@EnableScheduling
@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(OmniaDBLoader.class)
@Import({BasicOmniaQueue.class, OmniaJobQueueProcessor.class })
public class OmniaServerScheduledConfiguration {
 }
