package com.baizhi;

import com.baizhi.elasticsearch.dao.EmpRepository;
import com.baizhi.entity.Emp;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class TestEmpRespository {
    @Autowired
    private EmpRepository empRepository;
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    //保存|更新一条文档 id存在 更新 id不存在 添加
    @Test
    public void testSave(){
        Emp s=new Emp();
        //s.setId("68fd0a17-0b60-4a5d-aa2e-94fa3b5c74b9");
        s.setName("张天宝");
        s.setBir(new Date());
        s.setAge(23);
        s.setAddress("武当山学院毕业");
        s.setContent("武侠败类!");
        empRepository.save(s);
    }

    //根据id删除一条文档
    @Test
    public void testDelete(){
        empRepository.deleteById("68fd0a17-0b60-4a5d-aa2e-94fa3b5c74b9");
    }

    //删除所有
    @Test
    public void testDeleteAll(){
        empRepository.deleteAll();
    }

    //检索一条记录
    @Test
    public void testFindOne(){
        Optional<Emp> optional = empRepository.findById("68fd0a17-0b60-4a5d-aa2e-94fa3b5c74b9");
        System.out.println(optional.get());
    }

    //查询所有 排序
    @Test
    public void testFindAll(){
        Iterable<Emp> all = empRepository.findAll(Sort.by(Sort.Order.asc("age")));
        for (Emp emp : all) {
            System.out.println(emp);
        }
    }

    //分页
    @Test
    public void testFindPage(){
        //PageRequest.of 参数1：当前页-1
        Page<Emp> search = empRepository.search(QueryBuilders.matchAllQuery(), PageRequest.of(0, 1));
        for (Emp emp : search) {
            System.out.println(emp);
        }
    }

    //基础查询 根据姓名 姓名和年龄 地址等
    @Test
    public void testFindByName(){
        List<Emp> emps = empRepository.findByName("张君宝");
        List<Emp> emps1 = empRepository.findByAge(23);
        List<Emp> emps2 = empRepository.findByNameAndAddress("张君宝", "武当山");
        List<Emp> emps3 = empRepository.findByNameOrAge("张君宝", 23);
        List<Emp> emps4 = empRepository.findByAgeGreaterThanEqual(23);
        for (Emp emp : emps4) {
            System.out.println(emp);
        }
    }

    //复杂查询RestHighLevelClient   高亮
    @Test
    public void testSearchQuery() throws IOException {

        //创建搜索请求
        SearchRequest searchRequest = new SearchRequest("ems");

        //创建搜索对象
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("content", "武侠"))//设置条件
                .sort("age", SortOrder.DESC)
                .from(0)//起始条数（当前页-1）*size的值
                .size(20)//每页展示条数
                .highlighter(new HighlightBuilder().field("*").requireFieldMatch(false).preTags("<span style='color:red;'>").postTags("</span>"));

        searchRequest.types("emp").source(sourceBuilder);

        //执行搜索
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            //原始文档
            System.out.println(hit.getSourceAsString());
        }
    }

}
