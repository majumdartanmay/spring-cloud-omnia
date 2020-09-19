package applications;

import java.util.List;

public interface IOmniaApplication {
    String getApplicationName();

    BaseOmniaApplication.OmniaCloudProvider getApplicationPlatform();

    List<String> getHosts();
}
