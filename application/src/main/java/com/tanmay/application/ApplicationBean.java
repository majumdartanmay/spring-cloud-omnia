package com.tanmay.application;

import com.tanmay.service.LibraryAnn;
import com.tanmay.service.LibraryBean2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ApplicationBean {

//    @Autowired
//    private LibraryBean2 bean;

    public ApplicationBean() {
        System.out.println("Application bean created");
        test();
    }

    @LibraryAnn
    public void test(){}

}
