package com.tanmay.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ApplicationBean {

//    @Autowired
//    private LibraryBean2 bean;

    public ApplicationBean() {
        System.out.println("Application bean created");
    }


}
