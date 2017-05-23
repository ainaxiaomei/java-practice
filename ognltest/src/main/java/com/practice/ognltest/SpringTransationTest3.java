package com.practice.ognltest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class SpringTransationTest3 {
	public static void main(String[] args) throws SQLException {
		MysqlDataSource ds = new MysqlDataSource();
		ds.setURL("jdbc:mysql://10.220.10.10:3306/test?user=root");
		ds.setPassword("123456");
		PlatformTransactionManager ptm = new DataSourceTransactionManager(ds);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("SomeTxName");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		DefaultTransactionStatus transStatus = (DefaultTransactionStatus) ptm.getTransaction(def);
		//Connection con = ds.getConnection();
		Connection con = DataSourceUtils.getConnection(ds);
		transStatus.getTransaction();
		
	}
}
