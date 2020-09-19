package org.cloud.omnia.server.converters;

import applications.IOmniaApplication;
import javafx.util.Pair;
import org.cloud.omnia.server.database.entity.OmniaApplicationEntity;
import org.cloud.omnia.server.utils.RemoteAddressDetailsProvider;

public class RequestToApplicationEntity
        implements IBaseConverterInterface<IOmniaApplication, OmniaApplicationEntity> {
    @Override
    public OmniaApplicationEntity convert(IOmniaApplication input) {
        String remoteAddr = input.getHost();
        String name = input.getApplicationName();

        RemoteAddressDetailsProvider.getDetails(remoteAddr);

        OmniaApplicationEntity entity = new OmniaApplicationEntity();
        entity.setAppName(name);
        entity.setHost(remoteAddr);
        return entity;
    }


}
