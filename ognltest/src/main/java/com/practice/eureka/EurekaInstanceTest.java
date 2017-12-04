package com.practice.eureka;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;

import javax.swing.plaf.synth.SynthSpinnerUI;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.MyDataCenterInstanceConfig;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.EurekaClientConfig;

/**
 * Sample Eureka service that registers with Eureka to receive and process requests.
 * This example just receives one request and exits once it receives the request after processing it.
 *
 */
public class EurekaInstanceTest {

    public static void main(String[] args) {
    	EurekaInstanceConfig instanceConfig = new MyDataCenterInstanceConfig();
    	InstanceInfo  instanceInfo = new EurekaConfigBasedInstanceInfoProvider(instanceConfig).get();
    	ApplicationInfoManager applicationInfoManager = new ApplicationInfoManager(instanceConfig,instanceInfo);
    	EurekaClientConfig clientConfig = new DefaultEurekaClientConfig();
    	System.out.println("Eureka Server Port : " + clientConfig.getEurekaServerPort());
    	System.out.println("Eureka Server ServiceUrls : " + clientConfig.getEurekaServerServiceUrls("default"));
		EurekaClient client = new DiscoveryClient(applicationInfoManager, clientConfig);
		applicationInfoManager.setInstanceStatus(InstanceStatus.UP);
		InstanceInfo info = client.getNextServerFromEureka("eureka.mydomain.net", false);
		System.out.println(info.getHostName());
		System.out.println(info.getIPAddr());
		for(;;);
    }
	
}
