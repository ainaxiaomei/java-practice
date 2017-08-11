package com.practice.zookeeper;

import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZookeeperTest {
	public static void main(String[] args) {
		try {
			ZooKeeper zk =new ZooKeeper("10.220.10.10:2181,192.168.5.12:2181,192.168.5.151:2181",3000,new MyWatch());
			zk.create("/root", "123".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
			
			Stat stat = new Stat();
			byte[] data = zk.getData("/root", true, stat);
			System.out.println("data is : " + new String(data));
			System.out.println("stat is : " + stat);
			while(true){
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		
	}
	
	static class MyWatch implements Watcher{

		@Override
		public void process(WatchedEvent event) {
			System.out.println("触发了事件！" + event);
		}
		
	}
}
