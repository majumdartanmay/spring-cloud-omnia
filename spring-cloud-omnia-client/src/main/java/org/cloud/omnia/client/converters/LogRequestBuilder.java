package org.cloud.omnia.client.converters;

import Networking.DTO.LogRequestsDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.stream.Collectors;

public class LogRequestBuilder {

    private LogRequestsDTO logRequestsDTO = new LogRequestsDTO();
    private HttpServletRequest request;
    private HttpServletResponse response;

    public LogRequestBuilder(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
        this.logRequestsDTO.setEndpoint(request.getRequestURI());
        setRequestParams();
        setRequestHeaders();
        setRequestBody();
        setResponseHeaders();
        setStatusCode();
        setMethod();
    }

    private void setMethod(){
        logRequestsDTO.setMethod(request.getMethod());
    }

    private void setRequestParams(){
        Enumeration<String> parameterNames = request.getParameterNames();
        logRequestsDTO.setParams(getUrlEncodedValue(parameterNames, "&", false));
    }

    private void setStatusCode(){
        logRequestsDTO.setStatusCode(response.getStatus());
    }

    private void setResponseHeaders(){
        Collection<String> paramNames = response.getHeaderNames();
        String urlEncoded = getUrlEncodedValue(paramNames);
        logRequestsDTO.setResponseHeaders(urlEncoded);
    }

    private void setRequestHeaders(){
        Enumeration<String> parameterNames = request.getHeaderNames();
        logRequestsDTO.setRequestHeaders(getUrlEncodedValue(parameterNames, "\n", true));
    }

    private void setRequestBody(){

        if(!"get".equalsIgnoreCase(request.getMethod())){
            try {
                String json = request
                        .getReader()
                        .lines()
                        .collect(Collectors.joining(System.lineSeparator()));

                logRequestsDTO.setRequestBody(json);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getUrlEncodedValue(Collection<String> parameterNames){
        return getUrlEncodedValue(Collections.enumeration(parameterNames), "\n", true);
    }

    private String getUrlEncodedValue(Enumeration<String> parameterNames, String splitIdentifier, boolean isHeader){
        if(parameterNames != null && parameterNames.hasMoreElements()) {

            StringBuilder params = new StringBuilder();

            while (parameterNames.hasMoreElements()) {
                String paramKey = parameterNames.nextElement();
                String paramValue = isHeader ? request.getHeader(paramKey) : request.getParameter(paramKey);
                String paramIdentifier = paramKey + "=" + paramValue + (parameterNames.hasMoreElements() ? splitIdentifier : "");
                params.append(paramIdentifier);
            }

            return params.toString();
        }

        return null;
    }

    public LogRequestsDTO build(){
        return logRequestsDTO;
    }

}
