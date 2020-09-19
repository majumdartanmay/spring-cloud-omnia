package configurations;

import applications.IOmniaApplication;

import java.util.List;

public interface IOmniaConfiguration {
    String getEnvironment();

    String getDatacenter();

    List<IOmniaApplication> getApplications();
}
