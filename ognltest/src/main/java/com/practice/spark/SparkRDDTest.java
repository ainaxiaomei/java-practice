package com.practice.spark;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

public class SparkRDDTest {
	
	
	public static void main(String[] args) {
		
		
		JavaSparkContext sc = new JavaSparkContext();
		JavaRDD<Integer> lineLengthRDD = sc.textFile("hdfs:///text.data").map(new Function<String,Integer>() {

			@Override
			public Integer call(String v1) throws Exception {
				return v1.length();
			}

			
		});
		
		int totalLength = lineLengthRDD.reduce(new Function2<Integer, Integer, Integer>() {
			
			@Override
			public Integer call(Integer v1, Integer v2) throws Exception {
				return v1 + v2;
			}
		});
		
		System.out.println("--- total length " + totalLength);
		
	}

}
