package org.cloud.omnia.server.processor;

import org.cloud.omnia.server.config.OmniaServerProperties;
import org.cloud.omnia.server.database.entity.NetworkRequestEntity;
import org.cloud.omnia.server.database.repository.NetworkRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.Queue;

/**
 * class to implement queue used for data-exchange.
 *
 * @author Tanmay Majumdar
 */
@Configuration(proxyBeanMethods = false)
public class BasicOmniaQueue implements
        OmniaJobQueueInterface<NetworkRequestEntity> {
    /**
     * Queue to perform enqueue and dequeue of data.
     */
    private Queue<NetworkRequestEntity>
            networkRequestEntityQueue = new LinkedList<>();

    /**
     * Queue related properties defined in configuration files.
     */
    @Autowired
    private OmniaServerProperties omniaServerProperties;

    /**
     * repository used for data-exchange with DB.
     */
    @Autowired
    private NetworkRequestRepository networkRequestRepository;

    /*
     * (non-javadoc)
     * @see org.cloud.omnia.server.processor#checkAndSend()
     */
    @Override
    public final synchronized void checkAndSend() {
        if (networkRequestEntityQueue.size()
                >= omniaServerProperties.getMaxQueueSize()) {
            forceSend();
        }
    }

    /*
     * (non-javadoc)
     * @see org.cloud.omnia.server.processor#clearQueue()
     */
    @Override
    public final void clearQueue() {
        networkRequestEntityQueue.clear();
    }

    /*
     * (non-javadoc)
     * @see org.cloud.omnia.server.processor#addToQueue(T data)
     */
    @Override
    public final void addToQueue(final NetworkRequestEntity obj) {
        networkRequestEntityQueue.add(obj);
        checkAndSend();
    }

    /*
     * (non-javadoc)
     * @see org.cloud.omnia.server.processor#forceSend()
     */
    @Override
    public final void forceSend() {
        if (!networkRequestEntityQueue.isEmpty()) {
            networkRequestRepository.saveAll(networkRequestEntityQueue);
            clearQueue();
        }
    }

    /*
     * (non-javadoc)
     * @see org.cloud.omnia.server.processor#getSize()
     */
    @Override
    public final int getSize() {
        return networkRequestEntityQueue.size();
    }
}
