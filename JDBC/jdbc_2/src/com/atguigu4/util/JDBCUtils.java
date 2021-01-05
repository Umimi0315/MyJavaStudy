package com.atguigu4.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;

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
	 * 使用dbutils.jar包中提供的DbUtils工具类，实现资源的关闭
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public static void closeResource1(Connection conn,Statement ps,ResultSet rs) {
//		try {
//			DbUtils.close(conn);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			DbUtils.close(ps);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			DbUtils.close(rs);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		DbUtils.closeQuietly(conn);
		DbUtils.closeQuietly(ps);
		DbUtils.closeQuietly(rs);

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
	/**
	 * 使用C3P0的数据库连接池技术
	 * @return
	 * @throws Exception
	 */
	//数据库连接池只需提供一个即可
	private static ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");  
	public static Connection getConnection1() throws Exception {
		Connection conn = cpds.getConnection();
		return conn;
	}
	
	/**
	 * 使用DBCP数据库连接池技术获取数据库连接
	 * @return
	 * @throws Exception
	 */
	
	//创建一个DBCP数据库连接池
	private static DataSource source;
	static {
		try {
			Properties pros = new Properties();
			//方式1：
//	InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
			FileInputStream is=new FileInputStream(new File("src/dbcp.properties"));
			pros.load(is);
			source = BasicDataSourceFactory.createDataSource(pros);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public static Connection getConnection2() throws Exception {
		Properties pros = new Properties();
		//方式1：
//		InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
		FileInputStream is=new FileInputStream(new File("src/dbcp.properties"));
		pros.load(is);
		//创建一个DBCP数据库连接池
		DataSource source = BasicDataSourceFactory.createDataSource(pros);
		
		Connection conn = source.getConnection();
		return conn;
	}
	
	/**
	 * 使用Druid数据库连接池技术
	 */
	private static DataSource source1;
	static {
		try {
			Properties pros=new Properties();
			InputStream is = ClassLoader.getSystemClassLoader().getSystemResourceAsStream("druid.properties");
			pros.load(is);
			source1 = DruidDataSourceFactory.createDataSource(pros);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection3() throws Exception {
		
		Connection conn = source1.getConnection();
		return conn;
	}
}
