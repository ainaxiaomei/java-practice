package org.practice.flink;

import java.util.Properties;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

public class FlinkKafkaTest {
	
	
	public static void main(String[] args) throws Exception {
		
		
		StreamExecutionEnvironment see = StreamExecutionEnvironment.getExecutionEnvironment();
		
		
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "192.168.2.3:9092");
		properties.setProperty("group.id", "test");
		
		FlinkKafkaConsumer<String> kafakConsumer = new FlinkKafkaConsumer<String>("kafka-probe",
				new SimpleStringSchema(), properties);
		see.addSource(kafakConsumer).print();
		
		see.execute();
		
	}

}
