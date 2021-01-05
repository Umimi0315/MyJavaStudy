package com.baizhi;

import com.baizhi.entity.Emp;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.fetch.subphase.highlight.Highlighter;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestDocumentHighlightQuery {

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

    /**
     * 各种查询
     */
    @Test
    public void testQuery(){

        List<Emp> emps=new ArrayList<>();

        //创建highlightBuilder
        HighlightBuilder highlightBuilder = new HighlightBuilder().field("*")
                .requireFieldMatch(false)
                .preTags("<span style='color:red;'>")
                .postTags("</span>");

        SearchResponse searchResponse = transportClient.prepareSearch("ems")
                .setTypes("emp")
                .setQuery(QueryBuilders.multiMatchQuery("我喜欢框架","name", "content"))
                .highlighter(highlightBuilder)
                .get();

        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            Emp emp = new Emp();
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            emp.setId(hit.getId());
            emp.setName(sourceAsMap.get("name").toString());
            emp.setAge(Integer.valueOf(sourceAsMap.get("age").toString()));
            emp.setContent(sourceAsMap.get("content").toString());
            emp.setAddress(sourceAsMap.get("address").toString());

            //高亮处理
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (highlightFields.containsKey("name")){
                String nameHigh = highlightFields.get("name").fragments()[0].toString();
                emp.setName(nameHigh);
            }
            if (highlightFields.containsKey("content")){
                String contentHigh = highlightFields.get("content").fragments()[0].toString();
                emp.setContent(contentHigh);
            }

            emps.add(emp);
        }

        //遍历集合
        for (Emp emp : emps) {
            System.out.println(emp);
        }

    }


}
