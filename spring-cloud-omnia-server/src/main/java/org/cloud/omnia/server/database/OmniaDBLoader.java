package org.cloud.omnia.server.database;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.cloud.omnia.server.database.entity")
@EnableJpaRepositories(basePackages = "org.cloud.omnia.server.database.repository")
public class OmniaDBLoader {
}
