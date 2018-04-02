package com.practice.kafka;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;
import org.junit.Test;

public class KafkaTest {

	/**
	 * 使用默认的partitioner发送消息，有key值使用hash值与partition的个数取模
	 */
	@Test
	public void kafkaProducerTest1() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.2.3:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<>(props);
		for (int i = 0; i < 100; i++)
			producer.send(new ProducerRecord<String, String>("probe", "sunqi" + Integer.toString(i),
					"sunqi" + Integer.toString(i)));

		producer.close();

	}

	/**
	 * 使用自定义partition
	 */
	@Test
	public void kafkaProducerTest2() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.5.23:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<>(props);
		for (int i = 0; i < 100; i++)
			producer.send(new ProducerRecord<String, String>("sunqi", 9, "sunqi" + Integer.toString(i),
					"" + Integer.toString(i)));

		producer.close();

	}

	/**
	 * 自定义partitioner。
	 */
	@Test
	public void kafkaProducerTest3() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.5.23:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.practice.kafka.MyPationtioner");

		Producer<String, String> producer = new KafkaProducer<>(props);
		for (int i = 0; i < 100; i++)
			if (i % 2 == 0) {
				producer.send(new ProducerRecord<String, String>("sunqi",  "a" + Integer.toString(i),
						"A" + Integer.toString(i)));

			} else {
				producer.send(new ProducerRecord<String, String>("sunqi",  "b" + Integer.toString(i),
						"B" + Integer.toString(i)));

			}

		producer.close();

	}

	
	/**
	 * 使用自动提交的consumer,并使用subscribe自动进行组管理
	 * 默认会从partition的最后开始消费
	 */
	@Test
	public void kafkaConsumerTest() {
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.5.23:9092");
		props.put("group.id", "test");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
		
		/**
		 * 获取topic信息
		 */
		
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		
		Map<String,List<PartitionInfo>> map = consumer.listTopics();
		map.forEach((topic,list) ->{
			System.out.println("topic : " + topic + "; " + "partition : "+ list);
		});
		
		
		consumer.subscribe(Arrays.asList("sunqi"));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records)
				System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
		}

	}

}
