package com.atguigu.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTX {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.163.128", 6379);

        Transaction transaction = jedis.multi();

        transaction.set("k4", "v44");
        transaction.set("k5", "v55");

//        transaction.exec();
        transaction.discard();

    }
}
