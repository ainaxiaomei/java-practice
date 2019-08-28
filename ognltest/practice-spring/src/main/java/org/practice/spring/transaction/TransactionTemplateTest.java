package org.practice.spring.transaction;

import java.util.List;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class TransactionTemplateTest {
	
	public static void main(String[] args) {
		
		
		PlatformTransactionManager tm = new DataSourceTransactionManager();
		TransactionTemplate tt =new TransactionTemplate(tm);
		tt.execute(new TransactionCallback<List<String>>() {

			@Override
			public List<String> doInTransaction(TransactionStatus status) {
				
				DefaultTransactionStatus ts = (DefaultTransactionStatus)status;
				return null;
			}
		});
		
	}

}
