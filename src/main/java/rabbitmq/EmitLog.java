package rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.util.Config;
import rabbitmq.util.CustomConnection;

public class EmitLog {
    public static void main(String[] argv) throws Exception {
        try (Connection connection = CustomConnection.getConnectionFactory().newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(Config.EXCHANGE_NAME, "fanout");

            String message = argv.length < 1 ? "info: Hello World!" :
                    String.join(" ", argv);

            channel.basicPublish(Config.EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
