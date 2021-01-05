package com.baizhi.test;

import com.baizhi.RabbitmqSpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqSpringbootApplication.class)
public class TestRabbitMQ {

    //注入rabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //topic 动态路由
    @Test
    public void testTopic(){
        rabbitTemplate.convertAndSend("topics", "order", "发送user.save的key的路由信息");
    }

    //route 路由模式
    @Test
    public void testRoute(){
        rabbitTemplate.convertAndSend("directs", "error", "发送info的key的路由信息");
    }

    //fanout 广播
    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("logs", "", "fanout模型");
    }

    //work
    @Test
    public void testWork(){
        for (int i=0;i<10;i++){
            rabbitTemplate.convertAndSend("work", "work模型"+i);
        }
    }

    //hello world
    @Test
    public void testHello(){
        rabbitTemplate.convertAndSend("hello", "hello world");
    }


}
