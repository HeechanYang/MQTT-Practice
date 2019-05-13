package rabbitmq.util;

import com.rabbitmq.client.ConnectionFactory;

public class CustomConnection {

    public static ConnectionFactory getConnectionFactory(){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Config.MQTT_HOST);
        factory.setUsername(Config.MQTT_USERNAME);
        factory.setPassword(Config.MQTT_PASSWORD);
        factory.setVirtualHost(Config.MQTT_VIRTUAL_HOST);
        return factory;
    }
}
