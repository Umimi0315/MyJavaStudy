package com.atguigu.dao.impl;

import com.atguigu.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {

    //使用DBUtils操作数据库
    private QueryRunner queryRunner=new QueryRunner();

    /**
     * update()方法用来执行insert/delete/update语句
     * @return
     */
    public int update(String sql,Object... args){
        Connection conn= JDBCUtils.getConnection();
        try {
            return queryRunner.update(conn, sql, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(conn);
        }
        return -1;
    }

    /**
     * 查询返回一个JavaBean的sql语句
     * @param type  返回的对象类型
     * @param sql   执行的sql语句
     * @param args  sql对应的参数值
     * @param <T>   返回类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type,String sql,Object... args){
        Connection conn=JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(conn);
        }
        return null;
    }

    public <T>List<T> queryForList(Class<T> type,String sql,Object... args){
        Connection conn=JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanListHandler<>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(conn);
        }
        return null;
    }

    /**
     * 执行返回一行一列的sql语句
     * @param sql   执行的sql语句
     * @param args  sql对应的参数值
     * @return
     */
    public Object queryForSingleValue(String sql,Object... args){
        Connection conn=JDBCUtils.getConnection();

        try {
            queryRunner.query(conn, sql, new ScalarHandler(),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(conn);
        }
        return null;
    }

}
