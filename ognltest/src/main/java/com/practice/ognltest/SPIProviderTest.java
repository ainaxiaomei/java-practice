package com.practice.ognltest;

import java.util.Iterator;
import java.util.Optional;
import java.util.ServiceLoader;


public class SPIProviderTest {

	public static void main(String[] args) {
		
		 SPIProvider provider = null ;
		 ServiceLoader<SPIProvider> loader = ServiceLoader.load(SPIProvider.class);
		 Iterator<SPIProvider > itr = loader.iterator();
		 while(itr.hasNext()){
			 provider = itr.next();
		 }
		 
		 if(provider != null){
			 System.out.println(provider.provide());
		 }
	}

}
