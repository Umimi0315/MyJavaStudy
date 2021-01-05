package com.baizhi;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.Book;
import com.baizhi.entity.Emp;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class TestDocumentSearch {

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
        //查询所有
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();

        //termQuery
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("content", "太极");

        //rangeQuery
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age").gte(0).lte(24);

        //wildcardQuery 通配符 ? 一个 * 0到多个
        WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("content", "框?");

        //prefixQuery 前缀查询
        PrefixQueryBuilder prefixQueryBuilder = QueryBuilders.prefixQuery("content", "框");

        //ids查询
        IdsQueryBuilder idsQueryBuilder = QueryBuilders.idsQuery().addIds("12").addIds("fes2uHYBc3z1GIACxBSM");

        //fuzzy 模糊查询 0-2 不允许模糊 3-5 可以出现一个模糊 >5 最多出现两个模糊
        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("content", "sproog");

        //bool query
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().mustNot(QueryBuilders.termQuery("content", "spring"));

        //调用方法
        testResult(boolQueryBuilder);

    }

    //用来输出搜索结果
    public void testResult(QueryBuilder queryBuilder){
        SearchResponse searchResponse = transportClient.prepareSearch("ems")
                .setTypes("emp")
                .setQuery(queryBuilder)
                .setFrom(0)//起始条数 默认从0开始 （当前页-1）*size
                .setSize(20)//设置每页展示记录数
                .addSort("age", SortOrder.DESC)//设置排序 desc降序 asc升序
                .setSource(SearchSourceBuilder.searchSource().fetchSource("*", "age"))//在执行结果中返回哪些字段
                .get();

        //获取searchResponse中hits
        System.out.println("查询符合条件的总条数:"+searchResponse.getHits().totalHits);
        System.out.println("查询符合条件文档的最大得分:"+searchResponse.getHits().getMaxScore());

        //获取每一个文档详细信息
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println("======>"+hit.getSourceAsString());
        }
    }

}
