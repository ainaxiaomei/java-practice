package org.practice.spark.RDD;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class SparkRDDWordCount {
	
	
	public static void main(String[] args) {
		
		
		JavaSparkContext sc = new JavaSparkContext();
		JavaRDD<String> words = sc.textFile("hdfs:///text.data").flatMap(new FlatMapFunction<String, String>() {

			  
			    /**  
			    * @Fields field:field:{todo}(用一句话描述这个变量表示什么)  
			    */  
			    
			private static final long serialVersionUID = 1L;

			@Override
			public Iterator<String> call(String t) throws Exception {
				
				String[] words = t.split(" ");
				
				List<String> res = new ArrayList<>();
				
				for (String s : words) {
					
					res.add(s);
				}
				return res.iterator();
			}
		});
		
		JavaPairRDD<String, Integer> pair = words.mapToPair(new PairFunction<String, String, Integer>() {

			  
			    /**  
			    * @Fields field:field:{todo}(用一句话描述这个变量表示什么)  
			    */  
			    
			private static final long serialVersionUID = 1L;

			@Override
			public Tuple2<String, Integer> call(String t) throws Exception {
				
				
				return new Tuple2<String, Integer>(t,1);
			}
		});
		
		JavaPairRDD<String, Integer> res = pair.reduceByKey(new Function2<Integer,Integer,Integer>(){

			@Override
			public Integer call(Integer v1, Integer v2) throws Exception {
				
				
				return v1 + v2 ;
			}
			
		});
		
		List<Tuple2<String, Integer>>  ls = res.collect();
		for (Tuple2<?, ?> tuple : ls) {
            System.out.println(tuple._1() + ": " + tuple._2());
        }

        sc.stop();
		
		
		
		
	}
	
	public void pairRDD() {
		
		JavaSparkContext sc = new JavaSparkContext();
		JavaPairRDD<String, Integer> pairs = sc.textFile("hdfs:///text.data").mapToPair(new PairFunction<String, String, Integer>() {

			@Override
			public Tuple2<String, Integer> call(String t) throws Exception {
				return new Tuple2<String, Integer>(t,1) ;
			}
		});
		
		JavaPairRDD<String, Integer> counts = pairs.reduceByKey((a, b) -> a + b);
		
	}

}
