package org.cloud.omnia.client.network.web;

import Networking.DTO.LogRequestsDTO;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.cloud.omnia.client.config.OmniaClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@Lazy
public class OmniaServerService {

    private static Logger logger = Logger.getLogger(OmniaServerService.class.getName());

    @Autowired
    private OmniaClientProperties omniaClientProperties;

    public boolean checkConnection() throws UnirestException {
        String url = getOmniaServerUrl() + "/ping";

        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get(url)
                .asString();

        return response.getStatus() == 200;
    }

    private String getOmniaServerUrl(){
        return String.format("%s/%s",
                omniaClientProperties.getOmniaUrl(),
                omniaClientProperties.getOmniaServerName());
    }

    public void logRequest(LogRequestsDTO request){
        Unirest.setTimeouts(0, 0);
        String baseUrl = String.format("%s/%s", getOmniaServerUrl(), "createLog");

        try {
            HttpResponse<String> response = Unirest.post(baseUrl)
                    .header("Content-Type", "application/json")
                    .body(new Gson().toJson(request))
                    .asString();

            if(response.getStatus() != 200){
                logger.warning("Unable to log request to omnia server. Ensure omnia server connectivity"); }
            else
                logger.info("Request logged");

        } catch (UnirestException e) {
            e.printStackTrace();
            logger.warning("Unable to log request to omnia server. Ensure omnia server connectivity");
        }
    }

}
