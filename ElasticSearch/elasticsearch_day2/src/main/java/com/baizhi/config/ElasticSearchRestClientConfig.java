package com.baizhi.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class ElasticSearchRestClientConfig extends AbstractElasticsearchConfiguration {

    //这个client用来替换transportClient对象
    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        //定义客户端配置对象 RestClient  9200
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                //书写集群中所有节点
                .connectedTo("192.168.163.132:9201","192.168.163.132:9202","192.168.163.132:9203")
                .build();

        //通过RestClient对象创建
        return RestClients.create(clientConfiguration).rest();

    }
}
