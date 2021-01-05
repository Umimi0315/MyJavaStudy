package com.atguigu.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * id默认就是类名首字母小写
 */
@Repository
//@Scope(value = "prototype")
public class BookDao {

    public void saveBook(){
        System.out.println("图书已经保存...");
    }
}
