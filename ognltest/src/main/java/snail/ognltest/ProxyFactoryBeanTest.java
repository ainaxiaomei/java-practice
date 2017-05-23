package snail.ognltest;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactoryBean;

public class ProxyFactoryBeanTest {

	public static void main(String[] args) {
		Probe p = new ProbeImpl();
		ProxyFactoryBean factory = new ProxyFactoryBean();
		factory.setTarget(p);
		factory.addAdvice(new MethodBeforeAdvice() {

			@Override
			public void before(Method method, Object[] args, Object target) throws Throwable {
				System.out.println("before invoke");
			}
			
		});
		System.out.println(Arrays.toString(factory.getAdvisors()));
		System.out.println(factory.getTargetClass());
		System.out.println(factory.getProxiedInterfaces());
		Probe p2 = (Probe) factory.getObject();
		//p2.getProbeData();
		
		System.out.println("========");
		
		Advised a = (Advised)p2;
		a.addAdvice(new MethodBeforeAdvice() {
			
			@Override
			public void before(Method method, Object[] args, Object target) throws Throwable {
				System.out.println("after cast ...");
			}
		});
		
		
		p2.getProbeData();
	}

}
