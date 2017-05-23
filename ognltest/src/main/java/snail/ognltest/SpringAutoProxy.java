package snail.ognltest;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

public class SpringAutoProxy {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		Probe p = context.getBean(Probe.class);
		System.out.println(p.getClass());
		p.getProbeData();
	}
}

@Configurable
class Config{
	
	@Bean(name="probe")
	public Probe newProbe(){
		return new ProbeImpl();
		
	}
	
	@Bean(name="in")
	public MethodInterceptor  newAdvice(){
		return new MethodInterceptor () {

			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				System.out.println("before ...");
				Object ret =  invocation.proceed();
				System.out.println("after ...");
				return ret;
			}
			
		} ;
		
	}
	
	@Bean
	public BeanNameAutoProxyCreator newBeanNameAutoProxyCreator(){
		BeanNameAutoProxyCreator pc = new BeanNameAutoProxyCreator();
		pc.setBeanNames("probe");
		pc.setInterceptorNames("in");
		return pc;
	}
	
	@Bean
	public DefaultAdvisorAutoProxyCreator newDefaultAdvisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator pc = new DefaultAdvisorAutoProxyCreator();
		pc.setInterceptorNames("in");
		return pc;
	}
	
}
