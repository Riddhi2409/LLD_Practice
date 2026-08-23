import notification.Notification;
import notification.SignatureDecorator;
import notification.SimpleNotification;
import notification.TimestampDecorator;
import observer.Logger;
import observer.NotificationEngine;
import service.NotificationService;
import strategy.EmailNotificationStrategy;
import strategy.PopUpNotificationStrategy;
import strategy.SMSNotificationStrategy;

public class Main {
    public static void main(String[] args) {

        NotificationService notificationService = NotificationService.getInstance();

        Logger logger = new Logger();

        notificationService.addObserver(logger);
        
        NotificationEngine notificationEngine = new NotificationEngine();

        notificationService.addObserver(notificationEngine);

        notificationEngine.addStrategy(new EmailNotificationStrategy("random.person@gmail.com"));

        notificationEngine.addStrategy(new SMSNotificationStrategy("+91 9876543210"));

        notificationEngine.addStrategy(new PopUpNotificationStrategy());

        Notification notification = new SimpleNotification("Your order has been shipped!");

        notification = new TimestampDecorator(notification);

        notification = new SignatureDecorator(notification, "Customer Care");

        notificationService.sendNotification(notification);

    }
}