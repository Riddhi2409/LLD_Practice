package notification;

public class SignatureDecorator extends NotificationDecorator {

    private String signature;

    public SignatureDecorator(Notification notification, String sig) {
        super(notification);
        signature=sig;
    }

    public String getContent() {
        return notification.getContent() + "\n-- " + signature + "\n\n";
    }

}
