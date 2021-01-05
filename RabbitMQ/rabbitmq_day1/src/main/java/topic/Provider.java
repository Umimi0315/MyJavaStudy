package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.RabbitMQUtils;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        //声明交换机以及交换机类型 topic
        channel.exchangeDeclare("topics", "topic");

        //发布消息
        String routeKey="user.save.delete";
        channel.basicPublish("topics",routeKey,null,("这里是topic动态路由模型，routekey：["+routeKey+"]").getBytes());

        //关闭资源
        RabbitMQUtils.closeConnection(channel, connection);
    }
}
