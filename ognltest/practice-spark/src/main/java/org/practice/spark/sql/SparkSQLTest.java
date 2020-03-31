package org.practice.spark.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;

public class SparkSQLTest {
	
	public static void main(String[] args) {
		
		
		SparkConf config = new SparkConf();
		config.set("spark.executor.instances", "3");
		config.set("spark.executor.memory", "4g");
		config.set("spark.executor.cores", "4");
		config.set("spark.default.parallelism", "10");
		config.set("spark.sql.warehouse.dir", "/user/hive/warehouse");
		SparkSession spark = SparkSession
				  .builder()
				  .appName("spark sql test")
				  .config(config)
				  .enableHiveSupport()
				  .getOrCreate();
		
		spark.sql("select count(1) from asia_cdn_log").show();
		
	}

}
