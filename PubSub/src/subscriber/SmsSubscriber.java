package subscriber;

import core.Message;

public class SmsSubscriber implements Subscriber{

    private final String id;

    public SmsSubscriber(String id) {
        this.id = id;
    }

    @Override public String getId() {
        return id;
    }
    @Override public void onMessage(String topic, Message message) {
        System.out.println("[SMS " + id + "] Received on '" + topic + "': " + message.getPayload());
    }

}
