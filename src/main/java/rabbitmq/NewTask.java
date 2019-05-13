package rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.util.CustomConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class NewTask {
    public static void main(String[] args) throws IOException, TimeoutException {
        String message = String.join(" ", "test1");
        Connection connection = CustomConnection.getConnectionFactory().newConnection();
        Channel channel = connection.createChannel();

        channel.basicPublish("test_queue", "routingKey", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
    }
}
