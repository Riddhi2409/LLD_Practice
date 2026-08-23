import core.Message;
import publisher.Publisher;
import publisher.TopicPublisher;
import service.PubSubBroker;
import subscriber.EmailSubscriber;
import subscriber.SmsSubscriber;
import subscriber.Subscriber;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        PubSubBroker broker = PubSubBroker.getInstance();

        broker.createTopic("orders");
        broker.createTopic("payments");

        // 2. Instantiate 4 Distinct Subscribers
        Subscriber sub1 = new EmailSubscriber("sub1");
        Subscriber sub2 = new SmsSubscriber("sub2");

        broker.subscribe("orders", sub1);
        broker.subscribe("orders", sub2);

        broker.subscribe("payments",sub1);

        Publisher orderPublisher = new TopicPublisher("order-service", "orders", broker);
        Publisher paymentPublisher = new TopicPublisher("payment-service", "payments", broker);

        // 5. Trigger Messaging
        orderPublisher.publish(new Message("Order #1001 Created"));
        paymentPublisher.publish(new Message("Payment #5501 Approved"));

        Thread.sleep(1000); // Allow thread pool execution
        broker.shutdown();
    }
}