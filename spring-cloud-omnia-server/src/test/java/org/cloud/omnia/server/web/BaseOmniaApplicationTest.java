package org.cloud.omnia.server.web;

import org.cloud.omnia.server.config.OmniaServerProperties;
import org.cloud.omnia.server.database.OmniaDBLoader;
import org.cloud.omnia.server.processor.BasicOmniaQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;

public class BaseOmniaApplicationTest {
    @SpringBootApplication
    @Import(value = {BasicOmniaQueue.class, OmniaServerProperties.class, OmniaDBLoader.class})
    static class OmniaTestApplication {
        public static void main(String... args) {
            SpringApplication.run(OmniaTestApplication.class, args);
        }
    }
}
