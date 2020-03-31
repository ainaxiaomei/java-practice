package org.practice.flink;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.IngestionTimeExtractor;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import com.alibaba.fastjson.JSON;

public class FlinkKafkaTest {
	
	
	public static void main(String[] args) throws Exception {
		
		
		StreamExecutionEnvironment see = StreamExecutionEnvironment.getExecutionEnvironment();
		
		
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "192.168.2.55:9092");
		properties.setProperty("group.id", "test");
		
		FlinkKafkaConsumer<String> kafakConsumer = new FlinkKafkaConsumer<String>("beta_asia_cdn_log_txwd_20200310",
				new SimpleStringSchema(), properties);
		kafakConsumer.setStartFromEarliest();
		see
		.addSource(kafakConsumer)
		.map(new MapFunction<String, String>() {

			@Override
			public String map(String value) throws Exception {
				
				return JSON.parseObject(value).get("message").toString();
			}
		})
		.flatMap(new FlatMapFunction<String, Tuple4<String,String,String,Integer>>() {

			@Override
			public void flatMap(String value, Collector<Tuple4<String,String,String,Integer>> out) throws Exception {
				String[] tokens = value.split(" ");
				
				out.collect(new Tuple4<>("ip",tokens[0], tokens[3]  ,1));
				out.collect(new Tuple4<>("domain",tokens[1],tokens[3] , 1));
				
			}
		})
		.assignTimestampsAndWatermarks(new IngestionTimeExtractor<Tuple4<String,String,String,Integer>>(){

			@Override
			public long extractTimestamp(Tuple4<String, String, String, Integer> element,
					long previousElementTimestamp) {
				String timeStr = element.getField(2);
				//10/Mar/2020:04:26:37 +0800
				DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss",Locale.UK);
				LocalDateTime t = LocalDateTime.parse(timeStr, f);
				
				return  Timestamp.valueOf(t).getTime();
			}
			
		})
		.keyBy(1)
		.window(TumblingEventTimeWindows.of(Time.seconds(10)))
		.sum(3)
		.print();
		
		see.execute();
		
	}

}
