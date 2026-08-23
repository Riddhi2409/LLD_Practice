package strategy;

import notification.Notification;

public class EmailNotificationStrategy implements NotificationStrategy{
    private String emailId;

    public EmailNotificationStrategy(String emailId) {
        this.emailId = emailId;
    }

    public void sendNotification(Notification notification) {
        // Simulate the process of sending an email notification,
        // representing the dispatch of messages to users via email.​
        System.out.println("Sending email Notification to: " + emailId + "\n" + notification.getContent());
    }


}
