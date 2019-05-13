package rabbitmq.util;

import com.rabbitmq.client.ConnectionFactory;

public class CustomConnection {
    private static String MQTT_HOST = "52.79.158.171";
    private static String MQTT_USERNAME = "user1";
    private static String MQTT_PASSWORD = "test";
    private static String MQTT_VIRTUAL_HOST = "dev1";

    public static ConnectionFactory getConnectionFactory(){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(MQTT_HOST);
        factory.setUsername(MQTT_USERNAME);
        factory.setPassword(MQTT_PASSWORD);
        factory.setVirtualHost(MQTT_VIRTUAL_HOST);
        return factory;
    }
}
