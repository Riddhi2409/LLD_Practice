package observer;

import notification.Notification;

public interface Observable {
    void addObserver(NotificationObserver observer);
    void removeObserver(NotificationObserver observer);
    void notifyObservers(Notification notification);

}
