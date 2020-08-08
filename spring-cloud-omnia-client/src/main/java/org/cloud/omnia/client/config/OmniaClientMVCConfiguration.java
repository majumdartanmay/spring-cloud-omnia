package org.cloud.omnia.client.config;

import org.cloud.omnia.client.network.OmniaNetworkInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
public class OmniaClientMVCConfiguration implements WebMvcConfigurer {

    private OmniaNetworkInterceptor omniaNetworkInterceptor;

    @Autowired
    public OmniaClientMVCConfiguration(OmniaNetworkInterceptor omniaNetworkInterceptor){
        this.omniaNetworkInterceptor = omniaNetworkInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(omniaNetworkInterceptor);
    }
}
