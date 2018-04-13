package com.practice.dubbo.muticast.start;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.practice.dubbo.muticast.provider.GreetingService;

public class MutilcastRegisterTest {
	@Before
    public void initRemote() {
        ClassPathXmlApplicationContext remoteContext
          = new ClassPathXmlApplicationContext("com/practice/dubbo/provider/provider-app.xml");
        remoteContext.start();
    }
 
    @Test
    public void givenProvider_whenConsumerSaysHi_thenGotResponse(){
        ClassPathXmlApplicationContext localContext 
          = new ClassPathXmlApplicationContext("com/practice/dubbo/consumer/consumner-app.xml");
        localContext.start();
        GreetingService greetingsService
          = (GreetingService) localContext.getBean("greetingsService");
        String hiMessage = greetingsService.greet();
 
        assertNotNull(hiMessage);
        System.out.println(hiMessage);
    }
}
