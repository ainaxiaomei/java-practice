package com.practice.tomcat;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

/**
 * jersey写拦截器用来修改实体，于filter不同
 * 过滤器应当专注于实体的操作，如果没有实体拦截器不会执行
 * 拦截器是在MessageBodyWriter执行
 * @author Administrator
 *
 */

@Provider
public class JerseyWriteInterceptor implements WriterInterceptor {

	@Override
	public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
		//context.setEntity("被拦截 !原内容是 : " + context.getEntity());
		context.setOutputStream(new GZIPOutputStream(context.getOutputStream()));
		context.getHeaders().add("Content-Encoding", "gzip");
		context.proceed();
	}

}
