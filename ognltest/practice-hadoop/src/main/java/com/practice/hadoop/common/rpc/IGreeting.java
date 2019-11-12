package com.practice.hadoop.common.rpc;

import org.apache.hadoop.ipc.VersionedProtocol;

public interface IGreeting extends VersionedProtocol{
	
	public static final long versionID = 1L ;
	String greet();

}
