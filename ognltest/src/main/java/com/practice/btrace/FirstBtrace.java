package com.practice.btrace;

import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;
import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class FirstBtrace {
	
	@OnMethod(clazz="com.netflix.eureka.resources.ApplicationResource",
			method="getApplication"
	)
	public static void getApplication() {
		 println("trace: =======================");
		 jstack();
	}

}
