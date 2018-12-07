package com.practice.guava;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class CacheTest {
	
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		
		HashMap<String,String> map = new HashMap<>();
		map.put("a", "A");
		map.put("b", "B");
		map.put("c", "C");
		map.put("d", "D");
		map.put("e", "E");
		map.put("f", "F");
		
		LoadingCache<String,String> cache = CacheBuilder.newBuilder()
		.refreshAfterWrite(2, TimeUnit.SECONDS)
		.removalListener(new RemovalListener<String, String>() {

			@Override
			public void onRemoval(RemovalNotification<String, String> notification) {
				System.out.println(" --- 移除缓存 : key [ " + notification.getKey() + " ]" + "," + "value [ "
						+ notification.getValue() + " ]");
			}
		})
		
		.build(new CacheLoader<String, String>() {

			@Override
			public String load(String key) throws Exception {
				
				System.out.println(String.format(" --- 缓存 %s 未命中，回源取数据", key));
				return map.get(key);
			}
		});
		
		
		System.out.println("--- 获取缓存 : " + cache.get("a"));
		System.out.println("--- 获取缓存 : " + cache.get("a"));
		TimeUnit.SECONDS.sleep(3);
		System.out.println("--- 获取缓存 : " + cache.get("a"));
		System.out.println("--- 获取缓存 : " + cache.get("b"));
		System.out.println("--- 获取缓存 : " + cache.get("b"));
		
	}

}
