package snail.ognltest;

public class SpringAspectJDO {
	
	private String tag;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	 
	
	@Override
	public String toString() {
		return "SpeingAspectJDO [tag=" + tag + "]";
	}
	
	public String printTag(String arg){
		try {
			
			System.out.println(String.format("tag is %s", tag));
			return this.tag;
		} finally {
			System.out.println("finally is invoked ...");
		}
		
		
	}
	
	
}
