package org.cloud.omnia.server.web;

import applications.BaseOmniaApplication;
import applications.IOmniaApplication;
import configurations.IOmniaConfiguration;
import org.cloud.omnia.server.config.OmniaServerProperties;
import org.cloud.omnia.server.converters.IBaseConverterInterface;
import org.cloud.omnia.server.database.entity.OmniaApplicationEntity;
import org.cloud.omnia.server.database.repository.NetworkRequestRepository;
import org.cloud.omnia.server.database.repository.OmniaApplicationRepository;
import org.cloud.omnia.server.processor.BasicOmniaQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@Lazy
public class OmniaConfigurationProvider implements IOmniaConfiguration {

    @Value("${spring.cloud.omnia.server.environment}")
    private String environment;

    @Value("${spring.datasource.driver-class-name}")
    private String dataCenter;

    @Autowired
    private OmniaApplicationRepository omniaApplicationRepository;

    @Override
    public String getEnvironment() {
        return environment;
    }

    @Override
    public String getDatacenter() {
        return dataCenter;
    }

    @Override
    public List<IOmniaApplication> getApplications() {

        Date offsetTime = Date.from(LocalDateTime
                .now(ZoneOffset.UTC)
                .minusHours(100)
                .toInstant(ZoneOffset.UTC));

        List<OmniaApplicationEntity> applicationEntities =
                omniaApplicationRepository
                .findByCreatedOnGreaterThanEqual(offsetTime);

        IBaseConverterInterface<OmniaApplicationEntity, IOmniaApplication> converter
                = input -> new BaseOmniaApplication(
                        input.getAppName(),
                        input.getHost(),
                        input.getCloudProvider()
                    );

        return applicationEntities
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
