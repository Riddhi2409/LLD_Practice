package service;

import notification.Notification;
import observer.NotificationObservable;
import observer.NotificationObserver;

public class NotificationService {

    private static volatile NotificationService INSTANCE;
    private NotificationObservable observable;

    private NotificationService(){
        observable=new NotificationObservable();
    }

    public static NotificationService getInstance(){
        if(INSTANCE==null){
            synchronized (NotificationService.class){
                if(INSTANCE==null){
                    INSTANCE=new NotificationService();
                }
            }
        }
        return INSTANCE;
    }

    public void addObserver(NotificationObserver observer) {
        observable.addObserver(observer);
    }


    public void removeObserver(NotificationObserver observer) {
        observable.removeObserver(observer);
    }


    public void sendNotification(Notification notification) {

        observable.notifyObservers(notification);
    }


}
