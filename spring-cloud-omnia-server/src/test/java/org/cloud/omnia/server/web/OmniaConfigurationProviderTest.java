package org.cloud.omnia.server.web;

import applications.IOmniaApplication;
import configurations.IOmniaConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Profile("test")
public class OmniaConfigurationProviderTest extends BasicOmniaTestApplication{

    private static final String TEST_ENVIRONMENT = "TEST";
    private static final String TEST_DATACENTER = "com.mysql.cj.jdbc.Driver";

    @Autowired
    private IOmniaConfiguration configuration;

    @Test
    public void omniaConfigurationNullTest(){
        assertThat(configuration).isNotNull();
    }

    @Test
    public void omniaConfigurationEnvironmentTest(){
        assertThat(configuration.getEnvironment()).isEqualTo(TEST_ENVIRONMENT);
    }

    @Test
    public void omniaConfigurationDatacenterTest(){
        assertThat(configuration.getDatacenter()).isEqualTo(TEST_DATACENTER);
    }

    @Test
    public void connectedApplicationTest(){
        List<IOmniaApplication> applications = configuration.getApplications();
        assertThat(applications).isNotNull();
    }

    @Test
    public void connectedApplicationTest1(){
        List<IOmniaApplication> applications = configuration.getApplications();
        assertThat(applications.size()).isGreaterThanOrEqualTo(1);
    }
}
