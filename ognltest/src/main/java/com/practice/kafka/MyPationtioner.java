package com.practice.kafka;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

/**
 * 自定义的partitioner，如果key是a开头进入partition 1,否则进入partition 2
 * @author Administrator
 *
 */
public class MyPationtioner implements Partitioner{

	@Override
	public void configure(Map<String, ?> configs) {
		
	}

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		
		if(String.valueOf(key).substring(0, 1).equals("a")){
			return 3;
		}else{
			return 4;
		}
		
	}

	@Override
	public void close() {
		
	}

}
