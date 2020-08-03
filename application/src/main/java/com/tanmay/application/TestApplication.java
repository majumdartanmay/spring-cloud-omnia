package com.tanmay.application;

import com.tanmay.service.LibraryAnn;
import org.cloud.omnia.server.EnableOmniaServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableOmniaServer
public class TestApplication {


    @LibraryAnn
    public static void main(String... args){

        //test();
        ConfigurableApplicationContext ss = SpringApplication.run(TestApplication.class, args);

    }




}
