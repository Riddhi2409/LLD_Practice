package core;

import java.time.Instant;
import java.util.UUID;

public class Message {
    private final String id;
    private final String payload;
    private final Instant timestamp;

    public Message(String payload) {
        id= UUID.randomUUID().toString();
        this.payload = payload;
        this.timestamp = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getPayload() {
        return payload;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
