package com.baizhi.elasticsearch.dao;

import com.baizhi.entity.Emp;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

//自定义EmpRepository
public interface EmpRepository extends ElasticsearchRepository<Emp,String> {
    //根据姓名查询
    List<Emp> findByName(String name);
    //根据年龄查询
    List<Emp> findByAge(Integer age);
    //根据姓名和地址查询
    List<Emp> findByNameAndAddress(String name,String address);
    //根据姓名或年龄
    List<Emp> findByNameOrAge(String name,Integer age);
    //查询年龄大于等于8
    List<Emp> findByAgeGreaterThanEqual(Integer age);
}
