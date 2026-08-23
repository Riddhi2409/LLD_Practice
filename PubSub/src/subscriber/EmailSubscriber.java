package subscriber;

import core.Message;

public class EmailSubscriber implements Subscriber{

    private final String id;
    public EmailSubscriber(String id) { this.id = id; }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void onMessage(String topic, Message message) {
        System.out.println("[EMAIL " + id + "] Received on '" + topic + "': " + message.getPayload());
    }
}
