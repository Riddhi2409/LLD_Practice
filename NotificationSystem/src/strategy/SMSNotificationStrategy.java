package strategy;

import notification.Notification;

public class SMSNotificationStrategy implements NotificationStrategy{

    private String mobileNumber;

    public SMSNotificationStrategy(String smsNumber) {
        this.mobileNumber = smsNumber;
    }

    @Override
    public void sendNotification(Notification notification) {
        // Simulate the process of sending an SMS notification,
        // representing the dispatch of messages to users via SMS.​
        System.out.println("Sending SMS Notification to: " + mobileNumber + "\n" + notification.getContent());
    }
}
