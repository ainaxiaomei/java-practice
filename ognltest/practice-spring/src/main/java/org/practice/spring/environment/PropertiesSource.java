package org.practice.spring.environment;

import java.io.IOException;
import java.util.Properties;

import org.junit.Test;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySourcesPropertyResolver;

public class PropertiesSource {

	@Test
	public void test() throws IOException {
		
		
		Properties pro = new Properties();
		pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("envTest.properties"));
		PropertiesPropertySource  source = new PropertiesPropertySource("test.env", pro);
		System.out.println("--- 从PropertiesPropertySource获取  : " + source.getProperty("a"));
		
		
		MutablePropertySources sources = new MutablePropertySources();
		sources.addLast(source);
		PropertySourcesPropertyResolver resolver = new PropertySourcesPropertyResolver(sources);
		System.out.println("--- 从PropertySourcesPropertyResolver获取  : " + resolver.getProperty("a"));
		
	}

}
