package util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtils {
    private static ConnectionFactory connectionFactory;

    static {
        //重量级资源 类加载执行，只执行一次
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.163.132");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("sdxt7841176");
    }

    //定义提供连接对象的方法
    public static Connection getConnection(){
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //关闭通道和关闭连接工具方法
    public static void closeConnection(Channel channel,Connection connection){
        try {
            if (channel!=null) channel.close();
            if (connection!=null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
