package org.cloud.omnia.server.web;

import database.OmniaSearchFilters;
import org.cloud.omnia.server.EnableOmniaServer;
import org.cloud.omnia.server.OmniaServerApplication;
import org.cloud.omnia.server.config.OmniaServerProperties;
import org.cloud.omnia.server.database.OmniaDBLoader;
import org.cloud.omnia.server.database.entity.NetworkRequestEntity;
import org.cloud.omnia.server.database.repository.NetworkRequestRepository;
import org.cloud.omnia.server.database.specifications.NetworkRequestSpecification;
import org.cloud.omnia.server.processor.BasicOmniaQueue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Profile("test")
public class OmniaControllerTests {

    @Autowired
    private NetworkRequestRepository networkRequestRepository;

    private static ConfigurableApplicationContext context;

    private static ConfigurableApplicationContext server;

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @BeforeClass
    public static void startConfigServer() throws IOException {
        server = SpringApplication.run(OmniaTestApplication.class);
    }

    @AfterClass
    public static void close() {
        if (server != null) {
            server.close();
        }
    }

    @Test
    public void contextLoads() {

        OmniaSearchFilters.SubSearchFilters filter = new OmniaSearchFilters.SubSearchFilters();
        filter.setKey("statusCode");
        filter.setOperation(":");
        filter.setValue("2002");

        OmniaSearchFilters.SubSearchFilters filter2 = new OmniaSearchFilters.SubSearchFilters();
        filter2.setKey("appName");
        filter2.setOperation(":");
        filter2.setValue("test");


        OmniaSearchFilters query = new OmniaSearchFilters(new OmniaSearchFilters.SubSearchFilters[]{filter, filter2});

        query.setBaseOperator("OR");

        OmniaSearchFilters[] queries = {
                query
        };

        NetworkRequestSpecification networkRequestSpecification = new NetworkRequestSpecification(queries);
        List<NetworkRequestEntity> value =  networkRequestRepository.findAll(networkRequestSpecification);
        assertThat(value.size()).isGreaterThan(0);
    }


    @SpringBootApplication
    @Import(value = {BasicOmniaQueue.class, OmniaServerProperties.class, OmniaDBLoader.class})
    static class OmniaTestApplication {
        public static void main(String... args) {
            SpringApplication.run(OmniaTestApplication.class, args);
        }
    }

}
