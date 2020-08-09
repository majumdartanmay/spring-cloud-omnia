package org.cloud.omnia.server.converters;

import Networking.DTO.LogRequestsDTO;
import org.cloud.omnia.server.database.entity.NetworkRequestEntity;

public class LogRequestDTOToNetworkEntity implements BaseConverterInterface<LogRequestsDTO, NetworkRequestEntity> {

    private NetworkRequestEntity getNetworkRequestEntity(LogRequestsDTO logRequestsDTO) {

        NetworkRequestEntity networkRequestEntity = new NetworkRequestEntity();
        networkRequestEntity.setRequestBody(logRequestsDTO.getRequestBody());
        networkRequestEntity.setResponseBody(logRequestsDTO.getResponseBody());
        networkRequestEntity.setEndpoint(logRequestsDTO.getEndpoint());
        networkRequestEntity.setStatusCode(logRequestsDTO.getStatusCode());
        networkRequestEntity.setRequestHeaders(logRequestsDTO.getRequestHeaders());
        networkRequestEntity.setResponseHeaders(logRequestsDTO.getResponseHeaders());
        networkRequestEntity.setParameters(logRequestsDTO.getParams());
        networkRequestEntity.setMethod(logRequestsDTO.getMethod());
        networkRequestEntity.setAppName(logRequestsDTO.getAppName());

        return networkRequestEntity;
    }

    @Override
    public NetworkRequestEntity convert(LogRequestsDTO input) {
        return getNetworkRequestEntity(input);
    }
}
