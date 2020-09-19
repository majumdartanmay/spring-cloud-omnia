package org.cloud.omnia.server.processor;

public interface IOmniaJobQueueInterface<T> {
    /**
     * To sync the data with DB is all conditions are satisfied.
     */
    void checkAndSend();

    /**
     * Clear the data syncing queue.
     */
    void clearQueue();

    /**
     * To add data in DB.
     * @param obj data to store in DB
     */
    void addToQueue(T obj);

    /**
     * Store data without verifying parameters.
     */
    void forceSend();

    /**
     * returns size of the queue.
     * @return size of queue.
     */
    int getSize();
}
