package snail.ognltest;

public class InteceptorB implements Inteceptor{

	public String doIntecpt(Controller c) {
		
		System.out.println("before InteceptorB...");
		System.out.println("do InteceptorB... ");
		c.process();
		System.out.println("after InteceptorB...");
		return null;
	}

}
