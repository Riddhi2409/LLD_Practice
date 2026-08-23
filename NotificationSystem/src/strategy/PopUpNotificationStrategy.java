package strategy;

import notification.Notification;

public class PopUpNotificationStrategy implements NotificationStrategy{
    @Override
    public void sendNotification(Notification notification) {
        // Simulate the process of sending popup notification.
        System.out.println("Sending Popup Notification: \n" + notification.getContent());
    }
}
