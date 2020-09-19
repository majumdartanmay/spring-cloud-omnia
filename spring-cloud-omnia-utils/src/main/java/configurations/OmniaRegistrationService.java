package configurations;

import applications.IOmniaApplication;

import java.util.ArrayList;
import java.util.List;

public class OmniaRegistrationService implements IOmniaRegistrationService, IOmniaConfiguration {

    private List<IOmniaApplication> applicationFactories = new ArrayList<IOmniaApplication>();
    private String environment;
    private String dataCenter;

    public OmniaRegistrationService(String environment, String dataCenter){
        this.environment = environment;
        this.dataCenter = dataCenter;
    }

    public void registerApplication(IOmniaApplication application) {
        applicationFactories.add(application);
    }

    public String getEnvironment() {
        return environment;
    }

    public String getDatacenter() {
        return dataCenter;
    }

    public List<IOmniaApplication> getApplications() {
        return applicationFactories;
    }
}
