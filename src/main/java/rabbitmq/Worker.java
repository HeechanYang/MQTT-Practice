package rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import rabbitmq.util.CustomConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Worker {
    public static void main(String[] args) throws IOException, TimeoutException {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            System.out.println(" [x] Received '" + message + "'");
            try {
                doWork(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(" [x] Done");
            }
        };
        Connection connection = CustomConnection.getConnectionFactory().newConnection();
        Channel channel = connection.createChannel();
        boolean autoAck = true; // acknowledgment is covered below
        channel.basicConsume("test_queue", autoAck, deliverCallback, consumerTag -> { });
    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch: task.toCharArray()) {
            if (ch == '.') Thread.sleep(1000);
        }
    }
}
