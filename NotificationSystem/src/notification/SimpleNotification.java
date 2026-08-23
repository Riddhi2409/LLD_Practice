package notification;

public class SimpleNotification implements Notification{
    private String msg;

    public SimpleNotification(String msg) {
        this.msg = msg;
    }

    @Override
    public String getContent() {
        return msg;
    }
}
