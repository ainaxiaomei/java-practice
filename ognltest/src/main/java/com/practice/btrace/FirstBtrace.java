package com.practice.btrace;

import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.Return;
import static com.sun.btrace.BTraceUtils.*;

import javax.ws.rs.core.Response;

@BTrace
public class FirstBtrace {
	
	@OnMethod(clazz="com.netflix.eureka.resources.ApplicationResource",
			method="getApplication"
	)
	public static void getApplication( String version,
             final String acceptHeader,
             String eurekaAccept,@Return Response res) {
		 println("version : " + version);
		 println("acceptHeader : " + acceptHeader);
		 println("eurekaAccept : " + eurekaAccept);
		 printFields(res);
		 println("trace: =======================");
		 jstack();
	}

}
