package org.cloud.omnia.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

//@SpringBootApplication
public class OmniaServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(OmniaServerApplication.class).run(args);
    }
}
