package com.atguigu.test;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.Test;

import javax.sound.midi.Soundbank;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDao userDao=new UserDaoImpl();

    @Test
    public void queryUsername() {

        if (userDao.queryUsername("admin")==null){
            System.out.println("用户名可用！");
        }else {
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void queryUsernameAndPassword() {
        if (userDao.queryUsernameAndPassword("admin", "admin")!=null){
            System.out.println("登录成功!");
        }else{
            System.out.println("查询失败!");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"wzg168","123456","wzg168@126.com")));
    }
}