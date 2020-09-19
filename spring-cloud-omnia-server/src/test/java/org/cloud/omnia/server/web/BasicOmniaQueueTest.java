package org.cloud.omnia.server.web;

import org.cloud.omnia.server.database.entity.NetworkRequestEntity;
import org.cloud.omnia.server.processor.BasicOmniaQueue;
import org.cloud.omnia.server.processor.IOmniaJobQueueInterface;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Profile("test")
public class BasicOmniaQueueTest extends BaseOmniaApplicationTest {

    @Autowired
    IOmniaJobQueueInterface<NetworkRequestEntity> queue;

    private static ConfigurableApplicationContext server;

    @BeforeClass
    public static void startConfigServer() throws IOException {
        server = SpringApplication.run(OmniaTestApplication.class);
        IOmniaJobQueueInterface dummyQueue =
                server.getBeanFactory().getBean(IOmniaJobQueueInterface.class);
        assertThat(dummyQueue).isNotNull();
    }

    @Test
    public void insertDummyDataTest() {
        NetworkRequestEntity dummyData = new NetworkRequestEntity();
        queue.addToQueue(dummyData);
        queue.forceSend();
    }

    @Test
    public void checkQueueType(){
        assertThat(queue.getClass().getName())
                .isEqualTo(BasicOmniaQueue.class.getName());
    }
}
