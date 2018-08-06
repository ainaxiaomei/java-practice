package com.practice.jersey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class HTTPUrlConnectionTest {
	public static void main(String[] args) throws MalformedURLException, IOException {

		Properties props = System.getProperties();
		props.setProperty("http.proxyHost", "localhost");
		props.setProperty("http.proxyPort", "8888");
		System.setProperties(props);
		
		HttpURLConnection http = (HttpURLConnection) new URL("http://www.baidu.com").openConnection();
		System.out.println(http.getClass());
		InputStream in = http.getInputStream();
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(in));
		String str = null;
		while ( (str = bf.readLine()) != null) {
			System.out.println(str);
		}
	}
}
