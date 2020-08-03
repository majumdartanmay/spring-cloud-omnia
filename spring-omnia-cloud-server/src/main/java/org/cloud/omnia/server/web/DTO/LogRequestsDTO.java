package org.cloud.omnia.server.web.DTO;

import org.cloud.omnia.server.database.entity.NetworkRequestEntity;

import javax.validation.constraints.NotEmpty;

public class LogRequestsDTO {

    private String requestBody;

    private String responseBody;

    @NotEmpty
    private String endpoint;

    private int statusCode;

    private String params;

    private String requestHeaders;

    private String responseHeaders;

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(String requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public String getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(String responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public NetworkRequestEntity getNetworkRequestEntity(){

        NetworkRequestEntity networkRequestEntity = new NetworkRequestEntity();
        networkRequestEntity.setRequestBody(requestBody);
        networkRequestEntity.setResponseBody(responseBody);
        networkRequestEntity.setEndpoint(endpoint);
        networkRequestEntity.setStatusCode(statusCode);
        networkRequestEntity.setRequestHeaders(requestHeaders);
        networkRequestEntity.setResponseHeaders(responseHeaders);
        networkRequestEntity.setParameters(params);

        return networkRequestEntity;
    }
}
