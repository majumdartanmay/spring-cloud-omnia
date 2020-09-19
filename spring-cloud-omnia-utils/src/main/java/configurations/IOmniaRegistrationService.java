package configurations;

import applications.IOmniaApplication;

import java.util.List;

public interface IOmniaRegistrationService {

    void registerApplication(IOmniaApplication application);
    List<IOmniaApplication> getApplications();
}
