package com.practice.kafka;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
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
	 * 使用ProducerRecord指定partion
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
	 * kafka异步发送，发送结束后调用回调函数
	 */
	@Test
	public void kafkaAsyncSendTest() {
		
		Properties prop = new Properties();
		prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.9.200:9092");
		prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
		prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
		
		
		KafkaProducer<String, String> producer = new KafkaProducer<>(prop);
		ProducerRecord<String, String> record = new ProducerRecord<>("sunqi","test","sunqi123");
		producer.send(record,new Callback() {
			
			@Override
			public void onCompletion(RecordMetadata metadata, Exception exception) {
				if(exception != null) {
					exception.printStackTrace();
				}else {
					System.out.println("hasOffset :" + metadata.hasOffset());
					System.out.println("partition :" + metadata.partition());
					System.out.println("timestamp :" + metadata.timestamp());
					System.out.println("topic :" + metadata.topic());
				}
			}
		});
		
	}
	
	/**
	 * 同步发送测试，发送完返回一个future,并在future上等待
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	@Test
	public void kafkaSyncSendTest() throws InterruptedException, ExecutionException {
		Properties props = new Properties();
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.9.200:9092");
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		props.setProperty(ProducerConfig.ACKS_CONFIG, "1");
		props.setProperty(ProducerConfig.RETRIES_CONFIG, "4");
		
		
		KafkaProducer<String, String> kafka = new KafkaProducer<>(props);
		Future<RecordMetadata> fu =kafka.send(new ProducerRecord<String, String>("sunqi", "sync-sunqi"));
		RecordMetadata metadata = fu.get();
		
		System.out.println("hasOffset :" + metadata.hasOffset());
		System.out.println("partition :" + metadata.partition());
		System.out.println("timestamp :" + metadata.timestamp());
		System.out.println("topic :" + metadata.topic());
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
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.2.3:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
		
		/**
		 * 获取topic信息
		 */
		
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		
//		Map<String,List<PartitionInfo>> map = consumer.listTopics();
//		map.forEach((topic,list) ->{
//			System.out.println("topic : " + topic + "; " + "partition : "+ list);
//		});
//		
//		
//		System.out.printf("assignment : %s\n",consumer.assignment());
//		consumer.poll(10);
//		ConsumerRecords<String, String> records = consumer.poll(100);
//		for (ConsumerRecord<String, String> record : records)
//			System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
		TopicPartition p = new TopicPartition("kafka-probe",3061275);
		consumer.assign(Arrays.asList(p));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records)
				System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
		}

	}

}
