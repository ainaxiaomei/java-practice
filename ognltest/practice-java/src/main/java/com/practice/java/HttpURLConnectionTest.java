package com.practice.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.junit.Test;

public class HttpURLConnectionTest {

	@Test
	public void test() throws IOException{
	    URL url = new URL("http://www.baidu.com");
		HttpURLConnection  con = (HttpURLConnection) url.openConnection();
		con.connect();
		System.out.println(con.getHeaderFields());
		System.out.println("Response Code : " + con.getResponseCode());
		System.out.println("Response Message : " + con.getResponseMessage());
		System.out.println("Response Conent : " + con.getContent(new Class[]{String.class}));
		BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String a = null;
		StringBuilder sb = new StringBuilder();
		while((a = reader.readLine())!=null){
			System.out.println(a);
			sb.append(a);
			a = null;
		}
		
		Jsoup.parse(sb.toString());
		
		// IOUtils.toString(input, encoding)
		
		
		
	}

}
