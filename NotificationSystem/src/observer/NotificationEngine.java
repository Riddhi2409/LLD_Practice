package observer;

import notification.Notification;
import strategy.NotificationStrategy;

import java.util.ArrayList;
import java.util.List;

public class NotificationEngine implements NotificationObserver {

    private final List<NotificationStrategy> strategies=new ArrayList<>();

    public void addStrategy(NotificationStrategy strategy) {
        strategies.add(strategy);
    }

    public void removeStrategy(NotificationStrategy strategy) {
        strategies.remove(strategy);
    }

    @Override
    public void update(Notification notification) {
        for (NotificationStrategy strategy : strategies) {
            try {
                strategy.sendNotification(notification);
            } catch (Exception e) {
                System.out.println(
                        "Failed to send notification: "
                                + e.getMessage()
                );
            }
        }

    }
}
