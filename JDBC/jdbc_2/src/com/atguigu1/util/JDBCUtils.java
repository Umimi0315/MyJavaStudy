package com.atguigu1.util;
/**
 * @Description 操作数据库的工具类
 * @author niexiaohan
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


import com.mysql.jdbc.PreparedStatement;

public class JDBCUtils {
	
	/**
	 * 获取数据库的连接
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		//1.读取配置文件中的4个基本信息
		InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
		
		Properties pros = new Properties();
		pros.load(is);
		
		String user = pros.getProperty("user");
		String password = pros.getProperty("password");
		String url = pros.getProperty("url");
		String driverClass = pros.getProperty("driverClass");
		
		//2.加载驱动
		Class.forName(driverClass);
		
		//3.获取连接
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	/**
	 * 关闭连接和statement的操作
	 * @param conn
	 * @param ps
	 */
	public static void closeResource(Connection conn,Statement ps) {
		try {
			if (ps!=null)
				ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 关闭资源的操作
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public static void closeResource(Connection conn,Statement ps,ResultSet rs) {
		try {
			if (ps!=null)
				ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs!=null)
				rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
