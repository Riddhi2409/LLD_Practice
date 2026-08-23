package core;

import subscriber.Subscriber;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Topic {
    private final String name;
    private final Set<Subscriber> subscribers;

    public Topic(String name) {
        this.name = name;
        this.subscribers = ConcurrentHashMap.newKeySet();
    }

    public String getName() { return name; }
    public void addSubscriber(Subscriber subscriber) { subscribers.add(subscriber); }
    public void removeSubscriber(Subscriber subscriber) { subscribers.remove(subscriber); }
    public Set<Subscriber> getSubscribers() { return subscribers; }
}
