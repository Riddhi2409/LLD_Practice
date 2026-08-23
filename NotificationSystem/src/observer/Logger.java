package observer;

import notification.Notification;

public class Logger implements NotificationObserver{

    @Override
    public void update(Notification notification) {
        System.out.println(
                "Logging New Notification:\n" +
                        notification.getContent()
        );
    }


}
