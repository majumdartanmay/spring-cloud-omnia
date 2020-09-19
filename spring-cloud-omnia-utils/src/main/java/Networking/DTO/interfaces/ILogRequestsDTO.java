package Networking.DTO.interfaces;

public interface ILogRequestsDTO {
    String getRequestBody();
    String getResponseBody();
    String getEndpoint();
    int getStatusCode();
    String getParams();
    String getRequestHeaders();
    String getMethod();
    String getAppName();
}
