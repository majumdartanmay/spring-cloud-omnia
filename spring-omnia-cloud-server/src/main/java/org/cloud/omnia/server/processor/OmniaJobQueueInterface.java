package org.cloud.omnia.server.processor;

public interface OmniaJobQueueInterface<T> {
    void checkAndSend();
    void clearQueue();
    void addToQueue(T obj);
    void forceSend();
    int getSize();
}
