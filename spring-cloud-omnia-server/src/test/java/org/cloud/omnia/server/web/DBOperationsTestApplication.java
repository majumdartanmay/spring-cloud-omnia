package org.cloud.omnia.server.web;

import database.OmniaSearchFilters;
import org.cloud.omnia.server.database.entity.NetworkRequestEntity;
import org.cloud.omnia.server.database.repository.NetworkRequestRepository;
import org.cloud.omnia.server.database.specifications.NetworkRequestSpecification;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Profile("test")
public class DBOperationsTestApplication extends BasicOmniaTestApplication {

    @Autowired
    private NetworkRequestRepository networkRequestRepository;

    private static ConfigurableApplicationContext context;

    private static ConfigurableApplicationContext server;

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @BeforeClass
    public static void startConfigServer() throws IOException {
        //server = SpringApplication.run(OmniaTestApplication.class);
    }

    @Test
    public void basicTest() {
        int a = 1;
        int b = 2;
        assertThat(a + b).isEqualTo(3);
    }

    @AfterClass
    public static void close() {
        if (server != null) {
            server.close();
        }
    }

    @Test
    public void testEqualToOperation() {
        OmniaSearchFilters[] queries =
                buildSingleQuery("statusCode", ":", "404");

        Specification<NetworkRequestEntity> networkRequestSpecification = new NetworkRequestSpecification(queries);
        List<NetworkRequestEntity> result = networkRequestRepository.findAll(networkRequestSpecification);

        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    public void testGreaterThanOperation() {
        OmniaSearchFilters[] queries =
                buildSingleQuery("statusCode", ">", "200");

        NetworkRequestSpecification networkRequestSpecification = new NetworkRequestSpecification(queries);
        List<NetworkRequestEntity> result = networkRequestRepository.findAll(networkRequestSpecification);

        assertThat(result.size()).isGreaterThan(0);
    }

    @Test
    public void testLessThanOperation() {
        OmniaSearchFilters[] queries =
                buildSingleQuery("statusCode", "<", "200");

        NetworkRequestSpecification networkRequestSpecification = new NetworkRequestSpecification(queries);
        List<NetworkRequestEntity> result = networkRequestRepository.findAll(networkRequestSpecification);

        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    public void testNotEqualToOperation() {
        OmniaSearchFilters[] queries =
                buildSingleQuery("statusCode", "<>", "200");

        NetworkRequestSpecification networkRequestSpecification = new NetworkRequestSpecification(queries);
        List<NetworkRequestEntity> result = networkRequestRepository.findAll(networkRequestSpecification);

        assertThat(result.size()).isGreaterThan(0);
    }

    private OmniaSearchFilters[] buildSingleQuery(String key, String operation, String value) {

        OmniaSearchFilters.SubSearchFilters filter = new OmniaSearchFilters.SubSearchFilters();
        filter.setKey(key);
        filter.setOperation(operation);
        filter.setValue(value);

        OmniaSearchFilters query = new OmniaSearchFilters(new OmniaSearchFilters.SubSearchFilters[]{filter});

        OmniaSearchFilters[] queries = {
                query
        };

        return queries;
    }

}
