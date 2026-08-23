package observer;

import notification.Notification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NotificationObservable implements Observable{
    private Set<NotificationObserver> observers=new HashSet<>();
    private Notification currentNotification = null;

    public void addObserver(NotificationObserver obs) {
        observers.add(obs);
    }

    public void removeObserver(NotificationObserver obs) {
        observers.remove(obs);
    }

    public void notifyObservers(Notification notification) {
        for (NotificationObserver obs : observers) {
            obs.update(notification);
        }
    }

}
