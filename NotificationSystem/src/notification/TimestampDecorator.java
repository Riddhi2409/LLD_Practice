package notification;

import java.time.LocalDateTime;

public class TimestampDecorator extends NotificationDecorator {

    public TimestampDecorator(Notification notification) {
        super(notification);
    }

    public String getContent(){
        String timeStampStr= LocalDateTime.now().toString();
        return timeStampStr + "  " + notification.getContent();

    }
}
