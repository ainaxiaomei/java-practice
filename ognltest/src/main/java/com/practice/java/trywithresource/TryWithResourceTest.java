package com.practice.java.trywithresource;

import java.io.FileInputStream;
import java.util.Arrays;

public class TryWithResourceTest {
	
	public static class ExceptionStream implements AutoCloseable{
		
		public ExceptionStream() {
			
		}

		public int read()  {
			System.out.println(" --- call read");
			throw new RuntimeException(" read exception !");
		}

		@Override
		public void close(){
			System.out.println(" --- call close");
			throw new RuntimeException(" close exception !");
		}
		
		
		
		
	}
	
	public static void main(String[] args)  {
		
		try(ExceptionStream es = new ExceptionStream()){
			
			es.read();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(Arrays.toString(e.getSuppressed()));;
			
		}
		
		
		FileInputStream fis = null;
		
		try (FileInputStream fisNUll = fis){
			
			fis.read();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		
		ExceptionStream es = null;
		try {
			
			es = new ExceptionStream();
			es.read();
			
		} finally {
			es.close();
		}
		
		
	}

}
