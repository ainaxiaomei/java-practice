package com.practice.netflix.eureka;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInstanceConfig;
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider;
import com.netflix.discovery.BackupRegistry;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClientConfig;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;

public class BackupRegistryInstanceTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		EurekaInstanceConfig insConfig = new MyDataCenterInstanceConfig();
		InstanceInfo info = new EurekaConfigBasedInstanceInfoProvider(insConfig).get();
		
		ApplicationInfoManager manager = new ApplicationInfoManager(insConfig,info);
		
		EurekaClientConfig clientConfig = new DefaultEurekaClientConfig();
		DiscoveryClient client = new DiscoveryClient(manager,clientConfig);
		Application app = client.getApplication("sunqi");
		
		System.out.println(app);
		
		//TimeUnit.MINUTES.sleep(5);
		
	}
	
	public static class MyBackupRegister implements BackupRegistry {

		@Override
		public Applications fetchRegistry() {
			
			System.out.println(" --- Using BackupRegistry ");
			Applications apps = new Applications();
			Application app = new Application("sunqi");
			EurekaInstanceConfig insConfig = new MyDataCenterInstanceConfig();
			InstanceInfo info = new EurekaConfigBasedInstanceInfoProvider(insConfig).get();
			app.addInstance(info);
			apps.addApplication(app);
			return apps;
		}

		@Override
		public Applications fetchRegistry(String[] includeRemoteRegions) {
			Applications apps = new Applications();
			Application app = new Application("sunqi");
			EurekaInstanceConfig insConfig = new MyDataCenterInstanceConfig();
			InstanceInfo info = new EurekaConfigBasedInstanceInfoProvider(insConfig).get();
			app.addInstance(info);
			apps.addApplication(app);
			return apps;
		}
		
	}

}
