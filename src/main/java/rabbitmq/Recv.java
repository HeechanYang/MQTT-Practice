package rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import rabbitmq.util.Config;
import rabbitmq.util.CustomConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv {

    public static void main(String[] argv) throws IOException, TimeoutException {
        Connection connection = CustomConnection.getConnectionFactory().newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(Config.MQTT_TEST_QUEUE, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(Config.MQTT_TEST_QUEUE, true, deliverCallback, consumerTag -> { });
    }
}
