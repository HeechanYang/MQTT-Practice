package rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import rabbitmq.util.Config;
import rabbitmq.util.CustomConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class NewTask {
    public static void main(String[] args) throws IOException, TimeoutException {
        String message = String.join(" ", "test1....");

        // The queue won't be lost even if RabbitMQ restart.
        boolean durable = true;
        int prefetchCount = 1;

        Connection connection = CustomConnection.getConnectionFactory().newConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(prefetchCount);
        channel.queueDeclare(Config.MQTT_TEST_QUEUE, durable, false, false, null);
        // We need to mark our messages as persistent
        // MessageProperties.PERSISTENT_TEXT_PLAIN
        channel.basicPublish("", Config.MQTT_TEST_QUEUE, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
    }
}
