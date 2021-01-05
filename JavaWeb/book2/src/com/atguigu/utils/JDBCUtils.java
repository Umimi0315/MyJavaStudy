package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {

    private static DruidDataSource dataSource;
    static {

        try {
            Properties properties = new Properties();
            //读取jdbc.properties属性配置文件
            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从源中加载数据
            properties.load(inputStream);
            //创建数据库连接池
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            System.out.println(dataSource.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库的连接
     * @return 如果返回null，说明获取连接失败<br/>有值就是获取连接成功
     */
    public static Connection getConnection(){
        Connection conn=null;
        try {
            conn=dataSource.getConnection();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭连接，放回数据库连接池
     * @param conn
     */
    public static void close(Connection conn){
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }



}
