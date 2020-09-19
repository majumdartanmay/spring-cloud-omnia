package applications;


public interface IOmniaApplication {
    String getApplicationName();

    BaseOmniaApplication.OmniaCloudProvider getApplicationPlatform();

    String getHost();

    void setHost(String host);

    void setApplicationName(String name);
}
