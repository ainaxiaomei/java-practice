package com.practice.redisson;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.config.TransportMode;

public class RedissionTest {

	public static void main(String[] args) {
		Config config = new Config();
		SingleServerConfig singleConfig = config.useSingleServer();

		singleConfig.setAddress("redis://192.168.2.3:6379");
		RedissonClient  redisson = Redisson.create(config);
//		RAtomicLong atomicLong = redisson.getAtomicLong("genId_");
//		atomicLong.set(1);
		
		RLock lock = redisson.getLock("mylock");
		lock.lock();
		
		

	}

}
