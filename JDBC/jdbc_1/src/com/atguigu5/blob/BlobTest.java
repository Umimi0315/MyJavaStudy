package com.atguigu5.blob;
/**
 * 测试使用PreparedStatement操作blob类型的数据
 * @author niexiaohan
 *
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.atguigu3.bean.Customer;
import com.atguigu3.util.JDBCUtils;

public class BlobTest {
	
	//向数据表customers中插入blob类型的字段
	@Test
	public void testInsert() throws Exception {
		
		Connection conn = JDBCUtils.getConnection();
		
		String sql="insert into customers(name,email,birth,photo)values(?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1, "袁浩");
		ps.setObject(2, "yuan@qq.com");
		ps.setObject(3, "1992-09-08");
		FileInputStream is=new FileInputStream(new File("0.jpg"));
		ps.setBlob(4, is);
		
		ps.execute();
		
		JDBCUtils.closeResource(conn, ps);	
	}
	
	//查询数据表customers中的Blob类型的字段
	@Test
	public void testQuery() {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		InputStream is=null;
		FileOutputStream fos=null;
		try {
			conn = JDBCUtils.getConnection();
			
			String sql="select id,name,email,birth,photo from customers where id=?";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, 21);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				//方式一：
//			int id = rs.getInt(1);
//			String name = rs.getString(2);
//			String email = rs.getString(3);
//			Date birth = rs.getDate(4);
				//方式二：
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date birth = rs.getDate("birth");
				Customer cust = new Customer(id, name, email, birth);
				System.out.println(cust);
				
				//将Blob类型的字段下载下来，以文件的方式保存到本地
				Blob photo = rs.getBlob("photo");
				is = photo.getBinaryStream();
				fos=new FileOutputStream("zhangyuhao.jpg");
				byte[] buffer=new byte[1024];
				int len;
				while ((len=is.read(buffer))!=-1) {
					fos.write(buffer, 0, len);		
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(is!=null)
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(fos!=null)
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JDBCUtils.closeResource(conn, ps, rs);
		}
		
		
		
		
	}
	
}