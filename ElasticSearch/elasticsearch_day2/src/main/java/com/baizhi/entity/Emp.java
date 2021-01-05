package com.baizhi.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * 用在类上 作用：将Emp的对象映射成ES中一条json格式文档
 *      indexName：用来指定这个对象的转为json文档存入哪个索引中 要求:ES服务器中之前不能存在此索引名
 *      type：用来指定在当前这个索引下创建的类型名称
 */
@Document(indexName = "ems",type = "emp")
public class Emp {

    @Id //用来将对象中id属性与文档中_id一一对应
    private String id;
    //用在属性上 代表mapping中一个属性 一个字段 type:属性 用来指定字段类型 analyzer：用来指定分词器
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String name;
    @Field(type = FieldType.Integer)
    private Integer age;
    @Field(type = FieldType.Date)
    private Date bir;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String content;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String address;

    public Emp(String id, String name, Integer age, Date bir, String content, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bir = bir;
        this.content = content;
        this.address = address;
    }

    public Emp() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBir() {
        return bir;
    }

    public void setBir(Date bir) {
        this.bir = bir;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", bir=" + bir +
                ", content='" + content + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
