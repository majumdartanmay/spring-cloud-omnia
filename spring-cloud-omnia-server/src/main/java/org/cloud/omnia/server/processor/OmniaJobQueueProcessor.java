package org.cloud.omnia.server.processor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * Class to sync data with database.
 *
 * @author Tanmay Majumdar
 */
@Service
@ConditionalOnBean(BasicOmniaQueue.class)
public class OmniaJobQueueProcessor {

    /**
     * Queue used to sync the API logs with database.
     */
    private final IOmniaJobQueueInterface omniaQueue;

    /**
     * Logger to record events.
     */
    private static final Logger log =
            Logger.getLogger(OmniaJobQueueProcessor.class.getName());

    /**
     * Constructor to get Omnia queue.
     * @param queue used to sync data.
     */
    public OmniaJobQueueProcessor(final IOmniaJobQueueInterface queue) {
        this.omniaQueue = queue;
    }

    @Scheduled(fixedDelayString = "${spring.cloud.omnia.server.syncTime:10000}")
    private void triggerEvent() {
        log.info(String.format("Queue size is %d. Send events to data store ",
                omniaQueue.getSize()));
        omniaQueue.forceSend();
    }

}
