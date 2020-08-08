package org.omnia.client.application;

import org.cloud.omnia.client.EnableOmniaClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableOmniaClient
public class OmniaClientApplication {
    public static void main(String... args){
        SpringApplication.run(OmniaClientApplication.class, args);
    }
}
