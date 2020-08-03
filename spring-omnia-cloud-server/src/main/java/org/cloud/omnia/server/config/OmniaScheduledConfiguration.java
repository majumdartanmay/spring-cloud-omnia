package org.cloud.omnia.server.config;

import org.cloud.omnia.server.processor.BasicOmniaQueue;
import org.cloud.omnia.server.processor.OmniaJobQueueProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration(proxyBeanMethods = false)
@Import({BasicOmniaQueue.class, OmniaJobQueueProcessor.class })
public class OmniaScheduledConfiguration {
}
