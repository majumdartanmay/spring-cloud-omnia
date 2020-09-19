package applications;

import java.util.ArrayList;
import java.util.List;

public class BaseOmniaApplication implements IOmniaApplication {

    private String name;
    private OmniaCloudProvider platform;
    private List<String> hosts = new ArrayList<String>();

    public BaseOmniaApplication(OmniaCloudProvider provider){
        this.platform = provider;
    }

    public void setApplicationName(String name){
        this.name = name;
    }
    public void setApplicationHosts(String host){
        hosts.add(host);
    }

    public void removeHost(String host){
        hosts.remove(host);
    }

    public String getApplicationName() {
        return name;
    }

    public OmniaCloudProvider getApplicationPlatform() {
        return platform;
    }

    public List<String> getHosts() {
        return hosts;
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
