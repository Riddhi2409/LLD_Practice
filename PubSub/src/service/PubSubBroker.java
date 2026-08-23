package service;

import core.Message;
import core.Topic;
import subscriber.Subscriber;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PubSubBroker {
    private static PubSubBroker INSTANCE;
    private final Map<String, Topic> topics=new ConcurrentHashMap<>();
    private final ExecutorService executorService= Executors.newFixedThreadPool(10);

    private PubSubBroker() {
    }

    public static PubSubBroker getInstance(){
        if(INSTANCE==null){
            synchronized (PubSubBroker.class){
                if(INSTANCE==null){
                    INSTANCE=new PubSubBroker();
                }
            }
        }
        return  INSTANCE;
    }

    public void createTopic(String topicName) {
        topics.putIfAbsent(topicName, new Topic(topicName));
    }

    public void subscribe(String topicName, Subscriber subscriber) {
        Topic topic = topics.get(topicName);
        if (topic != null) {
            topic.addSubscriber(subscriber);
        } else {
            throw new IllegalArgumentException("Topic does not exist: " + topicName);
        }
    }

    public void unsubscribe(String topicName, Subscriber subscriber) {
        Topic topic = topics.get(topicName);
        if (topic != null) {
            topic.removeSubscriber(subscriber);
        }
    }

    public void publish(String topicName, Message message){
        Topic topic = topics.get(topicName);
        if (topic == null) {
            throw new IllegalArgumentException("Topic does not exist: " + topicName);
        }
        for (Subscriber subscriber:topic.getSubscribers()){
            executorService.submit(()->{
                try {
                    subscriber.onMessage(topicName, message);
                } catch (Exception e) {
                    System.err.println("Error dispatching to " + subscriber.getId() + ": " + e.getMessage());
                }
            });
        }
    }

    public void shutdown() {
        System.out.println("PubSubService shutting down...");
        executorService.shutdown();
        try {
            // Wait a reasonable time for existing tasks to complete
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ie) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("PubSubService shutdown complete.");
    }
}
