package org.cloud.omnia.server.processor;

import org.cloud.omnia.server.config.OmniaServerProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class OmniaJobQueueProcessor {

    private BasicOmniaQueue omniaQueue;
    private static Logger log = Logger.getLogger(OmniaJobQueueProcessor.class.getName());

    public OmniaJobQueueProcessor(BasicOmniaQueue queue){
        this.omniaQueue = queue;
    }

    @Scheduled(fixedDelayString = "${spring.cloud.omnia.server.syncTime:10000}")
    private void triggerEvent(){
        log.info(String.format("Queue size is %d. Send events to data store ", omniaQueue.getSize()));
        omniaQueue.forceSend();
    }

}
