package com.practice.jersey;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.rx.rxjava2.RxFlowableInvoker;
import org.glassfish.jersey.client.rx.rxjava2.RxFlowableInvokerProvider;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class JerseyClientTest {
	public static void main(String[] args) throws InterruptedException {

		// //同步请求
		// String frontUrl = "http://127.0.0.1:8081/";
		// WebTarget clientTarget = ClientBuilder.newClient().target(frontUrl);
		// String str = clientTarget.path("location/record/getAll")
		// .request()
		// .get(String.class);
		//
		// System.out.println(str);
		//
		// //异步请求包含异常处理
		// ClientBuilder.newClient().target("http://127.0.0.1:8081/location/record/getAll1")
		// .request()
		// .async().get(new InvocationCallback<Response>() {
		//
		// @Override
		// public void completed(Response response) {
		// System.out.println(response.getStatus());
		// System.out.println(response.readEntity(String.class));
		// }
		//
		// @Override
		// public void failed(Throwable throwable) {
		// throwable.printStackTrace();
		// }
		// });
		// System.out.println(12);
		//
		// //原生react形式
		// ClientBuilder.newClient()
		// .target("http://127.0.0.1:8081/location/record/getAll")
		// .request()
		// .rx()
		// .get(String.class)
		// .exceptionally((e)->{
		// e.printStackTrace();
		// return "error";})
		// .thenAccept((res)->{System.out.println(res);});
		
		// rxjava2形式
		
		WebTarget target = ClientBuilder.newClient()
					 .target("http://127.0.0.1:8081/location/record/getAll")
					 .register(RxFlowableInvokerProvider.class);
		target.request()
					 .header("Rx-User", "RxJava2")
			         .rx(RxFlowableInvoker.class)
			         .get(String.class)
			         .onErrorReturn(throwable -> {
			        	throwable.printStackTrace();
			            return "error";
			           }).subscribe(System.out::println);
		
		Thread.sleep(500);
		
		WebTarget target2 = ClientBuilder.newClient()
				.target("http://127.0.0.1:8081/location/record/getAll")
				.register(RxFlowableInvokerProvider.class);
		target2.request()
		.header("Rx-User", "RxJava2")
		.rx(RxFlowableInvoker.class)
		.get(String.class)
		.onErrorReturn(throwable -> {
			throwable.printStackTrace();
			return "error";
		}).blockingSubscribe(System.out::println);
		
		
	}
}
