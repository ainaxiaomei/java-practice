package org.practice.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class SpringTransationTest2 {
	public static void main(String[] args) throws SQLException {
		MysqlDataSource ds = new MysqlDataSource();
		ds.setURL("jdbc:mysql://10.220.10.10:3306/test?user=root");
		ds.setPassword("123456");
		PlatformTransactionManager ptm = new DataSourceTransactionManager(ds);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("SomeTxName");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus transStatus = ptm.getTransaction(def);
		//Connection con = ds.getConnection();
		Connection con = DataSourceUtils.getConnection(ds);
		try {
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("INSERT INTO test.t_isp  VALUES (?,?,?)");
			ps.setInt(1, 10086);
			ps.setString(2, "电信");
			ps.setString(3, "dx");
			ps.execute();
			ptm.commit(transStatus);
			
			ps = con.prepareStatement("INSERT INTO t_domain VALUES  (?,?)");
			ps.setInt(1, 10087);
			ps.setString(2, "www.baidu.com");
			ps.execute();

			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("roll back");
			//con.rollback();
			ptm.rollback(transStatus);
			
		} 
		
			//ptm.commit(transStatus);
			//con.commit();
		
	}
}
