package org.cloud.omnia.server.processor;

import org.cloud.omnia.server.config.OmniaServerProperties;
import org.cloud.omnia.server.database.entity.NetworkRequestEntity;
import org.cloud.omnia.server.database.repository.NetworkRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.Queue;

@Configuration(proxyBeanMethods = false)
public class BasicOmniaQueue implements OmniaJobQueueInterface<NetworkRequestEntity> {

    private Queue<NetworkRequestEntity> networkRequestEntityQueue = new LinkedList<>();

    @Autowired
    private OmniaServerProperties omniaServerProperties;

    @Autowired
    private NetworkRequestRepository networkRequestRepository;

    @Override
    public synchronized void checkAndSend() {
        if(networkRequestEntityQueue.size() >= omniaServerProperties.getMaxQueueSize()){
            forceSend();
        }
    }

    @Override
    public void clearQueue() {
        networkRequestEntityQueue.clear();
    }

    @Override
    public void addToQueue(NetworkRequestEntity obj) {
        networkRequestEntityQueue.add(obj);
        checkAndSend();
    }

    @Override
    public void forceSend() {
        if(!networkRequestEntityQueue.isEmpty()){
            networkRequestRepository.saveAll(networkRequestEntityQueue);
            clearQueue();
        }
    }

    @Override
    public int getSize() {
        return networkRequestEntityQueue.size();
    }
}
