package com.practice.ognltest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;


public class SavePointTest {
	static class StringE extends ArrayList{
		
	}
	public static void main(String[] args) throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://10.220.10.10:3306/test","root","123456");
		Savepoint sp = null;
		
		
		try {
			PreparedStatement ps =con.prepareStatement("INSERT INTO test.t_isp  VALUES (?,?,?)");
			ps.setInt(1, 10086);
			ps.setString(2, "电信");
			ps.setString(3, "dx");
			ps.execute();
			sp = con.setSavepoint("sp1");
			
			ps =con.prepareStatement("INSERT INTO t_domain VALUES  (?,?)");
			ps.setInt(1, 10086);
			ps.setString(2, "www.baidu.com");
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			if(sp != null){
				System.out.println("rool back for savePoint");
				con.rollback(sp);
			}else{
				System.out.println("rool back for all");
				con.rollback();
			}
		}finally{
			con.close();
		}
		
	}
}
