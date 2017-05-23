package snail.ognltest;

import java.util.HashMap;
import java.util.Map;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import ognl.OgnlRuntime;
import ognl.PropertyAccessor;

/**
 * Hello world!
 *
 */
public class App 

{
	static class Person {
		
		public Person(String name, Integer age) {
			super();
			this.name = name;
			this.age = age;
		}

		String name;
		
		Integer age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "Person [name=" + name + ", age=" + age + "]";
		}
		
		
		
	}
    public static void main( String[] args ) throws OgnlException
    
    {   
    	Person p = new Person("ss",10);
    	Ognl.setValue("age", p, 11);
    	System.out.println(p);
    	
    	Object o = Ognl.getValue("name.charAt(0)", p);
    	System.out.println(o);
    	
    	Map<String,Person> map = new HashMap<String,Person>();
    	map.put("pp", p);
    	
    	System.out.println(Ognl.getValue("age", map, p));
    	System.out.println(Ognl.getValue("#pp.name", map, p));
    	
    	
    	System.out.println(Ognl.getValue("pp.name", map));
    	
    	System.out.println(Ognl.getValue("#pp",Ognl.createDefaultContext(map), map));
    }
}
