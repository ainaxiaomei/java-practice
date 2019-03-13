package com.practice.hadoop.yarn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.yarn.client.api.YarnClient;

public class YarnAppTest {
	
	
	public static void main(String[] args) {
		YarnClient yarnClient = YarnClient.createYarnClient();
		Configuration conf = new Configuration();
		yarnClient.init(conf);
		yarnClient.start();
	}

}
