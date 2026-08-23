package subscriber;

import core.Message;

public interface Subscriber {
    String getId();
    void onMessage(String topic, Message message);
}
