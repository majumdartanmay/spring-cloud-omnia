package Networking.DTO;

import applications.IOmniaApplication;

import java.util.List;

public class OmniaConfigurationDTO {
    private String environment;
    private String dataCenter;
    private List<IOmniaApplication> applications;

    public OmniaConfigurationDTO(String environment, String dataCenter, List<IOmniaApplication> applications) {
        this.environment = environment;
        this.dataCenter = dataCenter;
        this.applications = applications;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getDataCenter() {
        return dataCenter;
    }

    public void setDataCenter(String dataCenter) {
        this.dataCenter = dataCenter;
    }

    public List<IOmniaApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<IOmniaApplication> applications) {
        this.applications = applications;
    }
}
