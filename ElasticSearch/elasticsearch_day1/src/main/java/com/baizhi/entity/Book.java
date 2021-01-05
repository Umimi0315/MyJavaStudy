package com.baizhi.entity;


import java.util.Date;

public class Book {
    private String id;
    private String name;
    private Integer age;
    private Date bir;
    private String content;
    private String address;

    public Book() {
    }

    public Book(String id, String name, Integer age, Date bir, String content, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bir = bir;
        this.content = content;
        this.address = address;
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
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", bir=" + bir +
                ", content='" + content + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
