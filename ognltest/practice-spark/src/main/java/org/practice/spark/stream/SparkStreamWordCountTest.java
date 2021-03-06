package org.practice.spark.stream;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import scala.Tuple2;


public class SparkStreamWordCountTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		SparkConf conf = new SparkConf();
		JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(3));
		
		jssc.socketTextStream("192.168.2.3", 9999).flatMap((String line)-> {
			return Arrays.asList(line.split(" ")).iterator();
		}).mapToPair((String str)->{
			
			return new Tuple2<>(str,1);
			
		}).reduceByKey((a,b)->{
			
			return a + b;
			
		}).print();
		
		jssc.start();              // Start the computation
		jssc.awaitTermination();
		
	}

}
