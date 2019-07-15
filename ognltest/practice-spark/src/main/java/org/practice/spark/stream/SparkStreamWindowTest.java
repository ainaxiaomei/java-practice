package org.practice.spark.stream;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import scala.Tuple2;

public class SparkStreamWindowTest {
	 
	public static void main(String[] args) throws InterruptedException {
		
		SparkConf conf = new SparkConf();
		conf.setMaster("local[2]");
		conf.setAppName("SparkStreamWindowTest");
		JavaStreamingContext jsc = new JavaStreamingContext(conf, Durations.seconds(1));
		
		jsc.socketTextStream("192.168.2.3", 9999).flatMap((line) -> {
			
			return Arrays.asList(line.split(" ")).iterator();
			
		}).window(Durations.seconds(3), Durations.seconds(2))
		.reduce((a,b)->{
			return a + " - " + b;
		})
		.print();
		
		jsc.start();
		jsc.awaitTermination();
		
//		jsc.socketTextStream("192.168.2.3", 9999).flatMap((line) -> {
//			
//			return Arrays.asList(line.split(" ")).iterator();
//			
//		}).mapToPair((String word)->{
//			return new Tuple2<String, Integer>(word, 1);
//		}).reduceByKeyAndWindow((a , b) -> {
//			
//			return a + b ;
//			
//		}, Durations.seconds(15), Durations.seconds(6))
//		.print();
//		
//		jsc.start();
//		jsc.awaitTermination();
		
	}

}
