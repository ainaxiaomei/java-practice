package org.practice.spring.aop;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

@EnableAspectJAutoProxy
@EnableTransactionManagement
@Configuration
public class Config {
	
	@Bean
	public DataSource newDataSource() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUrl("jdbc://192.168.2.3:3306/dns");
		dataSource.setPassword("00715701");
		dataSource.setUser("root");
		return dataSource;
		
	}
	
	
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		DataSourceTransactionManager  transactionManager = new DataSourceTransactionManager(dataSource);
		return transactionManager;
		
	}

}
