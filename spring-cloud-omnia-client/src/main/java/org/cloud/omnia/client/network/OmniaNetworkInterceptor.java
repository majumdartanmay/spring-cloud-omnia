package org.cloud.omnia.client.network;

import Networking.DTO.interfaces.ILogRequestsDTO;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.cloud.omnia.client.config.OmniaClientProperties;
import org.cloud.omnia.client.converters.LogRequestBuilder;
import org.cloud.omnia.client.network.web.OmniaServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@Configuration
public class OmniaNetworkInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = Logger.getLogger(OmniaNetworkInterceptor.class.getName());

    private boolean isConnected = false;

    @Autowired
    private OmniaServerService omniaServerService;

    @Autowired
    private OmniaClientProperties omniaClientProperties;

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            @Nullable ModelAndView modelAndView) {

        ILogRequestsDTO logRequestsDTO = new LogRequestBuilder(request, response).build();
        omniaServerService.logRequest(logRequestsDTO);
    }

    @PostConstruct
    public void checkConnectivity(){
        try {
            isConnected = omniaServerService.checkConnection();
        } catch (UnirestException e) {
            logger.severe(e.getMessage());
        }

        if(!isConnected && omniaClientProperties.isStrict()){
            throw new RuntimeException("Unable to connect to omnia server");
        }else if(!isConnected){
            logger.warning("Unable to connect to Omnia server");
        }else{
            logger.info("Connected with omnia server");
        }
    }
}
