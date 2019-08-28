package com.practice.java.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * PECS只适用于通配符,provider extends consumer super
 * <? extends T> 只能获取不能增加 
 * <? super T> 只能增加不能获取(事实上可以取不糊报错),只是不知到这个元素是什么类型而已也就无法赋值
 * <? extends T> 和 <? super T> 一般用在方法参数中很少使用new方式 
 * 
 * @author Administrator
 *
 */
public class PECS {

	public static class Fruit {
		@Override
		public String toString() {
			return "Fruit";
		}
	}

	public static class Apple extends Fruit {

		@Override 
		public String toString() {
			return "Apple";
		}
		
	}

	public static class Orange extends Fruit {
		@Override
		public String toString() {
			return "Orange";
		}
	}
	
	/**
	 * List<? extends T>作为方法参数，只能get使用add编译报错
	 * @param list
	 * @param elem
	 */
	public <T> void  m1(List<? extends T> list,T elem){
		
		/**
		 *  <? extends T>可以get但是不能add
		 */
		T a = list.get(0);
		//list.add(elem);//编译报错
	    // list.add(list.get(0));//编译报错
		 
		 
		 List<? extends Fruit> ls = new ArrayList<Apple>();
		 //ls.add(new Apple());//编译报错
		 
		 List<Fruit> ls1= new ArrayList<>();
		 ls1.add(new Apple());
		 ls1.add(new Orange());
	}
	
	/**
	 * <? super T>作为方法参数,可以add也可get但是get出来的值无法赋值使用
	 * @param list
	 * @param elem
	 */
	public <T> void  m2(List<? super T> list,T elem){
		/**
		 * <? super T>可以add
		 */
		list.add(elem);
		
		/**
		 * super上界get不会报错只是无法使用，除非强转为T类型
		 */
		System.out.println(list.get(0));
		
		List<? super Apple> ls = new ArrayList<Fruit>();
		ls.add(new Apple());
		//ls.add(new Fruit());//编译报错
		ls.get(0);
	}
	
	public <T> void  m3(List<?> list,T elem){
		//list.add(elem);//编译报错
		list.get(0);
	}
	
	public static void main(String[] args) {
		PECS p = new PECS();
		p.m2(new ArrayList<Apple>(), new Apple());
	}

}
