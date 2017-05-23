package snail.ognltest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAspectJTest {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext con = new AnnotationConfigApplicationContext(SpringAspectJConfig.class,SpringAspectJAspect.class);
		SpringAspectJDO DO = con.getBean(SpringAspectJDO.class);
		System.out.println(DO.getClass());
		DO.printTag("args ...");
		((SpringAOPDeclareParent)DO).print();
	}
}
