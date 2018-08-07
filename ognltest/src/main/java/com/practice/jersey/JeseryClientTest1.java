package com.practice.jersey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.junit.Test;

public class JeseryClientTest1 {
    
	/**
	 * 
	    * @Title: test  
	    * @Description: 只有ApacheConnectorProvider 和 JettyConnectorProvider支持ClientConfig设置代理
	    * @param     参数  
	    * @return void    返回类型  
	    * @throws
	 */
	@Test
	public void JersyProxyTest() {
		
		ClientConfig config = new ClientConfig();
		config.property(ClientProperties.PROXY_URI, "http://localhost:8888");
		Client client = ClientBuilder.newClient(config);
		config = (ClientConfig) client.getConfiguration();
		System.out.println("ConnectorProvider : " + config.getConnectorProvider());
		System.out.println("jersey.config.client.proxy.uri : " + config.getProperty(ClientProperties.PROXY_URI));
		WebTarget webTarget = client.target("http://www.baidu.com");
		String res = webTarget.request().get(String.class);
		//System.out.println(res);

	}

	/**
	 * 
	 * @Title: HttpUrlConnectionTest @Description: HTTP代理即使代理不存在也能使用 @param @throws
	 * MalformedURLException @param @throws IOException 参数 @return void 返回类型 @throws
	 */
	@Test
	public void HttpUrlConnectionTest() throws MalformedURLException, IOException {
		Properties props = System.getProperties();
		props.setProperty("http.proxyHost", "localhost");
		props.setProperty("http.proxyPort", "8888");
		System.setProperties(props);

		HttpURLConnection http = (HttpURLConnection) new URL("http://www.baidu.com").openConnection();
		InputStream in = http.getInputStream();

		BufferedReader bf = new BufferedReader(new InputStreamReader(in));
		String str = null;
		while ((str = bf.readLine()) != null) {
			System.out.println(str);
		}

	}

	/**
	 * 
	 * @Title: HttpUrlConnectionTest  
	 * @Description: HTTPS代理即使代理不存在也能使用,jdk密钥库默认的密码是changeit
	 * @param @throws MalformedURLException
	 * @param @throws IOException    参数  
	 * @return void    返回类型  
	 * @throws
	 */
	@Test
	public void HttpsUrlConnectionTest() throws MalformedURLException, IOException {
		Properties props = System.getProperties();
		props.setProperty("https.proxyHost", "localhost");
		props.setProperty("https.proxyPort", "8888");
		System.setProperties(props);
		
		//System.setProperty("java.protocol.handler.pkgs",  "com.sun.net.ssl.internal.www.protocol");

	    //System.setProperty("java.protocol.handler.pkgs",  "com.ibm.net.ssl.internal.www.protocol");

		String trustStorePath = "D:\\Program Files\\Java\\jdk1.8.0_162\\jre\\lib\\security\\mytruststore";
	
		System.setProperty("javax.net.ssl.trustStore",  trustStorePath);
	
		System.setProperty("javax.net.ssl.trustStorePassword",  "changeit");
		
		HttpURLConnection http = (HttpURLConnection) new URL("https://www.baidu.com?name=123").openConnection();
		InputStream in = http.getInputStream();
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(in));
		String str = null;
		while ( (str = bf.readLine()) != null) {
			System.out.println(str);
		}
		
	}
	

}
