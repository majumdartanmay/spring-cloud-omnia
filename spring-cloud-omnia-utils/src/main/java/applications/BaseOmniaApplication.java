package applications;

public class BaseOmniaApplication implements IOmniaApplication {

    private String name;
    private OmniaCloudProvider platform;
    private String host;

    public BaseOmniaApplication(OmniaCloudProvider provider) {
        this.platform = provider;
    }

    public BaseOmniaApplication(String name, String host, String platform){
        this.name = name;
        this.host = host;
        this.platform = platform != null ? OmniaCloudProvider.valueOf(platform) : null;
    }

    public void setApplicationName(String name) {
        this.name = name;
    }

    public String getApplicationName() {
        return name;
    }

    public OmniaCloudProvider getApplicationPlatform() {
        return platform;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public enum OmniaCloudProvider {

        AWS("AWS"),
        AZURE("AZURE"),
        LOCAL("LOCAL");

        OmniaCloudProvider(String aws) {
            this.applicationPlatform = aws;
        }

        private String applicationPlatform;

    }
}
