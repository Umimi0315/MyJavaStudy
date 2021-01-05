package com.baizhi;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

public class TestIndexAndTypeMapping {

    private TransportClient transportClient;

    @BeforeEach
    public void before() throws UnknownHostException {
        //创建客户端
        this.transportClient=new PreBuiltTransportClient(Settings.EMPTY);
        //设置es服务地址
        transportClient.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.163.132"), 9300));

    }

    @AfterEach
    public void after(){
        transportClient.close();
    }

    //创建索引
    @Test
    public void testCreateIndex() throws UnknownHostException {

        //创建一个索引    保证创建索引不存在
        CreateIndexResponse createIndexResponse = transportClient.admin().indices().prepareCreate("dangdang").get();
        //获取信息
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println(acknowledged);
    }

    //删除索引
    @Test
    public void testDeleteIndex() throws UnknownHostException {

        //删除索引
        AcknowledgedResponse acknowledgedResponse = transportClient.admin().indices().prepareDelete("dangdang").get();
        System.out.println(acknowledgedResponse.isAcknowledged());

    }

    //【了解】 创建索引 创建类型 创建mapping
    @Test
    public void testCreateIndexAndTypeMapping() throws ExecutionException, InterruptedException {
        //创建一个索引请求对象
        CreateIndexRequest dangdang = new CreateIndexRequest("dangdang");

        //索引设置类型
        //参数1：类型名 参数2：映射的json格式 参数3：映射格式类型
        dangdang.mapping("book", "{\"properties\":{\"id\":{\"type\":\"keyword\"},\"name\":{\"type\":\"keyword\"},\"price\":{\"type\":\"double\"},\"author\":{\"type\":\"keyword\"},\"des\":{\"type\":\"text\"},\"pubdate\":{\"type\":\"date\"}}}", XContentType.JSON);

        //创建索引
        CreateIndexResponse createIndexResponse = transportClient.admin().indices().create(dangdang).get();
        System.out.println(createIndexResponse.isAcknowledged());
    }



}
