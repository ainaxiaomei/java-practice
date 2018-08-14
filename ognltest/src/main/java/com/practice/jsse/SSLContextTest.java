package com.practice.jsse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Properties;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.netty.connector.NettyConnectorProvider;
import org.junit.Test;

public class SSLContextTest {
    
	@Test
	public void SSLContextTest() throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
		
		SSLContext defaultContext = SSLContext.getDefault();
		SSLContext tslContext = SSLContext.getInstance("SSL");
		
		System.out.println(defaultContext.getProtocol());
		System.out.println(tslContext.getProtocol());
		
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		ks.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("mytruststore"), "changeit".toCharArray());
		tmf.init(ks);
	
		
	}
	

}
