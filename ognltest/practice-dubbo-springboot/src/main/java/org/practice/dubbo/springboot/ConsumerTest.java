package org.practice.dubbo.springboot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerTest {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"org/practice/dubbo/springboot/consumner-app.xml");
		
		IFpingService pingService = (IFpingService) context.getBean("FpingService");
		pingService.fping0(10, new String[] {"192.168.2.3","-p", "60" ,"-i",
				"60", "-b" ,"24" ,"-l", "-Q", "1"});
		
	//	String arg[] =  new String[] {"192.168.2.3","192.168.2.4"};
	//	pingService.fping0(2, arg);
//		IGreetingService greetingService = (IGreetingService) context.getBean("greetingService");
//		String str = greetingService.greet();
		
	//	System.out.println(str);
		
		
		
	}

}
