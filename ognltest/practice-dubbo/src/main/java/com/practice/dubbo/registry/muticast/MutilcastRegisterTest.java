package com.practice.dubbo.registry.muticast;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.practice.dubbo.registry.muticast.provider.GreetingService;

public class MutilcastRegisterTest {
	@Before
    public void initRemote() {
        ClassPathXmlApplicationContext remoteContext
          = new ClassPathXmlApplicationContext("com/practice/dubbo/muticast/provider/provider-app.xml");
        remoteContext.start();
    }
 
    @Test
    public void givenProvider_whenConsumerSaysHi_thenGotResponse(){
        ClassPathXmlApplicationContext localContext 
          = new ClassPathXmlApplicationContext("com/practice/dubbo/muticast/consumer/consumner-app.xml");
        localContext.start();
        GreetingService greetingsService
          = (GreetingService) localContext.getBean("greetingsService");
        String hiMessage = greetingsService.greet();
 
        assertNotNull(hiMessage);
        System.out.println(hiMessage);
    }
    
    /**
     * 测试调用结果缓存，连续调用一个延时五秒的接口
     */
    @Test
    public void cacheTest(){
        ClassPathXmlApplicationContext localContext 
          = new ClassPathXmlApplicationContext("com/practice/dubbo/muticast/consumer/consumner-app.xml");
        localContext.start();
        GreetingService greetingsService
          = (GreetingService) localContext.getBean("greetingsService");
        
        long start = System.currentTimeMillis();
        String hiMessage = greetingsService.greetWithSleep();
        System.out.println(hiMessage);
        long end = System.currentTimeMillis();
        assertTrue(end - start >= 5000);
 
        start = System.currentTimeMillis();
        hiMessage = greetingsService.greetWithSleep();
        end = System.currentTimeMillis();
        assertTrue(end - start <= 1000);
        System.out.println(hiMessage);
    }
}
