package com.practice.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		String j = "{\r\n" + 
				"	\"颜色\": [\"白色\",\"红色\"],\r\n" + 
				"	 \"尺寸\":[\"1\",\"2\"]\r\n" + 
				"}";
		
		JSONObject jobject = JSON.parseObject(j);
		Set<String> keys = jobject.keySet();
		System.out.println(keys);
		JSONArray colorArray = jobject.getJSONArray((String)keys.toArray()[0]);
		JSONArray sizeArray = jobject.getJSONArray((String)keys.toArray()[1]);
		
		
		JSONArray result = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put((String)keys.toArray()[0],colorArray.getString(0));
		obj.put((String)keys.toArray()[1],sizeArray.getInteger(0));
		
		JSONObject obj1 = new JSONObject();
		obj1.put((String)keys.toArray()[0],colorArray.getString(0));
		obj1.put((String)keys.toArray()[1], sizeArray.getInteger(1));
		
		JSONObject obj2 = new JSONObject();
		obj2.put((String)keys.toArray()[0],colorArray.getString(1));
		obj2.put((String)keys.toArray()[1], sizeArray.getInteger(0));
		
		JSONObject obj3 = new JSONObject();
		obj3.put((String)keys.toArray()[0],colorArray.getString(1));
		obj3.put((String)keys.toArray()[1], sizeArray.getInteger(1));
		
		result.add(obj);
		result.add(obj1);
		result.add(obj2);
		result.add(obj3);
		
		System.out.println(result.toJSONString());
	
		
	}
		

}
