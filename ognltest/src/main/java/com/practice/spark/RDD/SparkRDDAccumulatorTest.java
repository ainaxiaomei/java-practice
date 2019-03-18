package com.practice.spark.RDD;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.util.LongAccumulator;

public class SparkRDDAccumulatorTest {
	
	public static void main(String[] args) {
		
		JavaSparkContext jsc = new JavaSparkContext();
		
		LongAccumulator acc = jsc.sc().longAccumulator();
		
		jsc.textFile("hdfs:///text.data").flatMap((String line)-> {
			
			String[] words = line.split(" ");
			List<String> ls = new ArrayList<String>();
			
			for (String s : words) {
				ls.add(s);
			}
			
			
			return ls.iterator();
			
		}).foreach((word)->{
			acc.add(1L);
		});
		
		System.out.println("all word is  " + acc.value());
		
		jsc.close();
		
		
		
	}

}
