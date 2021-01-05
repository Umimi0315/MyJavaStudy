package com.atguigu.springboot.controller;

import com.atguigu.springboot.entity.User;
import com.atguigu.springboot.respository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserRespository userRespository;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id){
        User user = userRespository.findOne(id);
        return user;
    }

    @GetMapping("/user")
    public User insertUser(User user){
        User save = userRespository.save(user);
        return save;
    }

}
