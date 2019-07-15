package org.practice.spark.RDD;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public class SparkTest {
	
	public static void main(String[] args) {
		
		if (args.length < 1) {
			
			System.out.println(" --- paramter error !");
			System.exit(0);
			
		}
		
		
	    SparkSession spark = SparkSession.builder().appName("Simple Application").getOrCreate();
	    Dataset<String> logData = spark.read().textFile(args[0]).cache();

	    long numAs = logData.filter(s -> s.contains("a")).count();
	    long numBs = logData.filter(s -> s.contains("b")).count();

	    System.out.println(" --- Lines with a: " + numAs + ", lines with b: " + numBs);
	    spark.stop();
		
	}

}
