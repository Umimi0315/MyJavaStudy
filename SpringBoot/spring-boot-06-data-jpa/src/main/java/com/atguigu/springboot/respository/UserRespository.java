package com.atguigu.springboot.respository;

import com.atguigu.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//继承JpaRepository来完成对数据库的操作
public interface UserRespository extends JpaRepository<User,Integer> {
}
