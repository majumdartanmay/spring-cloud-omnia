package com.tanmay.application;

import org.cloud.omnia.server.EnableOmniaServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableOmniaServer
public class TestApplication {

    public static void main(String... args){
       SpringApplication.run(TestApplication.class, args);
    }
}
