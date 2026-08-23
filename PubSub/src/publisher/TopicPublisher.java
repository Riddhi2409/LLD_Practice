package publisher;

import core.Message;
import service.PubSubBroker;

public class TopicPublisher implements Publisher{
    private final String publisherId;
    private final String topicName;
    private final PubSubBroker broker;

    public TopicPublisher(String publisherId, String topicName, PubSubBroker broker) {
        this.publisherId = publisherId;
        this.topicName = topicName;
        this.broker = broker;
    }

    @Override
    public void publish(Message message) {
        System.out.println("-> [" + publisherId + "] Publishing to topic '" + topicName + "': " + message.getPayload());
        broker.publish(topicName, message);
    }
}
