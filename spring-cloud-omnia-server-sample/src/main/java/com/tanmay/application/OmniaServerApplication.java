package com.tanmay.application;

import org.cloud.omnia.server.EnableOmniaServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableOmniaServer
public class OmniaServerApplication {

    public static void main(String... args){
       SpringApplication.run(OmniaServerApplication.class, args);
    }
}
