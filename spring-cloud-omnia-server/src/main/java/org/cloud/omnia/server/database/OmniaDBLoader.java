package org.cloud.omnia.server.database;

import org.cloud.omnia.server.database.repository.NetworkRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration(proxyBeanMethods = false)
@EntityScan(basePackages = "org.cloud.omnia.server.database.entity")
@EnableJpaRepositories(basePackages = "org.cloud.omnia.server.database.repository")
public class OmniaDBLoader {
//    @Autowired
//    private NetworkRequestRepository networkRequestRepository;
//    public OmniaDBLoader(NetworkRequestRepository networkRequestRepository){
//        this.networkRequestRepository = networkRequestRepository;
//    }
}
