package com.practice.zookeeper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;


/**
 * 即使主线程阻塞也不影响watch
 * @author win
 *
 */
public class ZookeeperWatchTest implements Watcher{
	
	private static Integer mutex = 0;
	
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZookeeperWatchTest test = new ZookeeperWatchTest();
		
		ZooKeeper zoo = new ZooKeeper("192.168.2.3:2181",10000,test);
		
		if(zoo.exists("/watch-root", false) == null) {
			zoo.create("/watch-root", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		}
		
		zoo.getChildren("/watch-root", true);
		
//		synchronized (mutex) {
//			while(true) {
//				mutex.wait();
//			}
//		}
		
		TimeUnit.SECONDS.sleep(100);
		
		
	}

	@Override
	public void process(WatchedEvent event) {
		
		System.out.println("-- Watch Triggerd !");
		System.out.println("-- Event Type : " + event.getType());
		System.out.println("-- Path : " + event.getPath());
		System.out.println("-- State : " + event.getState());
		
	}
	
}
