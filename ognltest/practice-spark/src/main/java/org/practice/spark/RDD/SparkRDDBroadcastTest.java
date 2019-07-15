package org.practice.spark.RDD;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;

/**
 * 
    * @ClassName: SparkRDDBroadcastTest  
    * @Description: 使用广播变量统计包含单词"slave"的行  
    * @author win  
    * @date 2019年3月18日  
    *
 */
public class SparkRDDBroadcastTest {
	
	
	public static void main(String[] args) {
		
		
		JavaSparkContext sc = new JavaSparkContext();
		
		String val = "slave.";
		
		Broadcast<String> brWord = sc.broadcast(val);
		
		sc.textFile("hdfs:///text.data")
		.filter((line) -> {
			return line.contains(brWord.value());
		}).collect().forEach((line)->{
			System.out.println(line);
		});;
		
		sc.stop();
		
		
	}

}
