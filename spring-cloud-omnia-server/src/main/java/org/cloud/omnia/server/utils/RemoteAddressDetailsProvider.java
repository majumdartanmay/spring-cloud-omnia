package org.cloud.omnia.server.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public final class RemoteAddressDetailsProvider {
    private static Logger logger =
            Logger.getLogger(RemoteAddressDetailsProvider.class.getName());
    public static void getDetails(String ipAddr){
        String hostName = "Unknown";
        try {
            InetAddress ip = InetAddress.getByName(ipAddr);
            hostName = ip.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        logger.info(String.format("Host name is %s ", hostName));
    }
}
