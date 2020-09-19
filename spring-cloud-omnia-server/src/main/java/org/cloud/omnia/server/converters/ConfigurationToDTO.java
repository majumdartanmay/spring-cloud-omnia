package org.cloud.omnia.server.converters;

import Networking.DTO.OmniaConfigurationDTO;
import configurations.IOmniaConfiguration;

public class ConfigurationToDTO implements IBaseConverterInterface<IOmniaConfiguration, OmniaConfigurationDTO> {
    @Override
    public OmniaConfigurationDTO convert(IOmniaConfiguration input) {
        return new OmniaConfigurationDTO(
                input.getEnvironment(),
                input.getDatacenter(),
                input.getApplications());
    }
}
