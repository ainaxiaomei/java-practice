package com.practice.zookeeper;

import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;


/**
 * 1.瞬时节点不能根节点
 * 2.瞬时序列节点不能没有父节点
 * 3.监视器只会触发一次触发过第二次不会再触发
 * @author vergil
 *
 */
public class ZookeeperTest {
	public static void main(String[] args) {
		try {
			ZooKeeper zk =new ZooKeeper("127.0.0.1:2181",10000,new MyWatch());
			MyWatch watch = new MyWatch();
			if(null == zk.exists("/root", watch)) {
				zk.create("/root", "123".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

			}
			
		
			zk.create("/root/leader", "a".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
			zk.create("/root/leader", "a".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
			System.out.println(zk.getSessionId());
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
