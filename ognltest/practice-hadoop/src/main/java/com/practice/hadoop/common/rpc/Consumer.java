package com.practice.hadoop.common.rpc;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.protocolPB.ClientNamenodeProtocolPB;
import org.apache.hadoop.ipc.RPC;

import com.google.protobuf.RpcController;

public class Consumer {
	
	public static void main(String[] args) throws IOException {
		
		Configuration conf = new Configuration() ;
		ClientNamenodeProtocolPB service = RPC.getProxy(ClientNamenodeProtocolPB.class, IGreeting.versionID,
				new InetSocketAddress("192.168.2.3", 9000), conf);
		
		System.out.println(11);
		//System.out.println(service.getBlockLocations(controller, request)); 
		
	}

}
