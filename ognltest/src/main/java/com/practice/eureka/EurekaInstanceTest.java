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
    	InstanceInfo instanceInfo = new EurekaConfigBasedInstanceInfoProvider(instanceConfig).get();
    	ApplicationInfoManager mamger = new ApplicationInfoManager(instanceConfig, instanceInfo);
    	System.out.println("------------------Instance Config ------------------");
    	System.out.println("AppGroupName : " + instanceConfig.getAppGroupName());
    	System.out.println("Appname : " + instanceConfig.getAppname());
    	System.out.println("ASGName name : " + instanceConfig.getASGName());
    	System.out.println("HealthCheckUrl : " + instanceConfig.getHealthCheckUrl());
    	System.out.println("HealthCheckUrlPath : " + instanceConfig.getHealthCheckUrlPath());
    	System.out.println("HomePageUrl : " + instanceConfig.getHomePageUrl());
    	System.out.println("HomePageUrlPath : " + instanceConfig.getHomePageUrlPath());
    	System.out.println("HostName : " + instanceConfig.getHostName(true));
    	System.out.println("InstanceId : " + instanceConfig.getInstanceId());
    	System.out.println("IpAddress : " + instanceConfig.getIpAddress());
    	System.out.println("LeaseExpirationDurationInSeconds : " + instanceConfig.getLeaseExpirationDurationInSeconds());
    	System.out.println("LeaseRenewalIntervalInSeconds : " + instanceConfig.getLeaseRenewalIntervalInSeconds());
    	System.out.println("Namespace : " + instanceConfig.getNamespace());
    	System.out.println("NonSecurePort : " + instanceConfig.getNonSecurePort());
    	System.out.println("SecureHealthCheckUrl : " + instanceConfig.getSecureHealthCheckUrl());
    	System.out.println("SecurePort : " + instanceConfig.getSecurePort());
    	System.out.println("SecureVirtualHostName : " + instanceConfig.getSecureVirtualHostName());
    	System.out.println("StatusPageUrl : " + instanceConfig.getStatusPageUrl());
    	System.out.println("StatusPageUrlPath : " + instanceConfig.getStatusPageUrlPath());
    	System.out.println("VirtualHostName : " + instanceConfig.getVirtualHostName());
    	System.out.println("DataCenterInfo : " + instanceConfig.getDataCenterInfo());
    	System.out.println("DefaultAddressResolutionOrder : " + instanceConfig.getDefaultAddressResolutionOrder());
    	System.out.println("SecurePortEnabled : " + instanceConfig.getSecurePortEnabled());
    	System.out.println("MetadataMap : " + instanceConfig.getMetadataMap());
    	
    	EurekaClientConfig clientConfig = new DefaultEurekaClientConfig();
    	DiscoveryClient client = new DiscoveryClient(mamger, clientConfig);
    	
    	System.out.println("------------------Client Config ------------------");
    	System.out.println("BackupRegistryImpl : " + clientConfig.getBackupRegistryImpl());
    	System.out.println("CacheRefreshExecutorExponentialBackOffBound : " + clientConfig.getCacheRefreshExecutorExponentialBackOffBound());
    	System.out.println("CacheRefreshExecutorThreadPoolSize : " + clientConfig.getCacheRefreshExecutorThreadPoolSize());
    	System.out.println("ClientDataAccept : " + clientConfig.getClientDataAccept());
    	System.out.println("DecoderName : " + clientConfig.getDecoderName());
    	System.out.println("DollarReplacement : " + clientConfig.getDollarReplacement());
    	System.out.println("EncoderName : " + clientConfig.getEncoderName());
    	System.out.println("EscapeCharReplacement : " + clientConfig.getEscapeCharReplacement());
    	System.out.println("EurekaConnectionIdleTimeoutSeconds : " + clientConfig.getEurekaConnectionIdleTimeoutSeconds());
    	System.out.println("EurekaServerConnectTimeoutSecond : " + clientConfig.getEurekaServerConnectTimeoutSeconds());
    	System.out.println("EurekaServerDNSName : " + clientConfig.getEurekaServerDNSName());
    	System.out.println("EurekaServerPort : " + clientConfig.getEurekaServerPort());
    	System.out.println("EurekaServerReadTimeoutSeconds : " + clientConfig.getEurekaServerReadTimeoutSeconds());
    	System.out.println("EurekaServerTotalConnections : " + clientConfig.getEurekaServerTotalConnections());
    	System.out.println("EurekaServerTotalConnectionsPerHost : " + clientConfig.getEurekaServerTotalConnectionsPerHost());
    	System.out.println("EurekaServerURLContext : " + clientConfig.getEurekaServerURLContext());
    	System.out.println("EurekaServiceUrlPollIntervalSeconds : " + clientConfig.getEurekaServiceUrlPollIntervalSeconds());
    	System.out.println("HeartbeatExecutorExponentialBackOffBound : " + clientConfig.getHeartbeatExecutorExponentialBackOffBound());
    	System.out.println("HeartbeatExecutorThreadPoolSize : " + clientConfig.getHeartbeatExecutorThreadPoolSize());
    	System.out.println("InitialInstanceInfoReplicationIntervalSeconds : " + clientConfig.getInitialInstanceInfoReplicationIntervalSeconds());
    	System.out.println("getInstanceInfoReplicationIntervalSeconds : " + clientConfig.getInstanceInfoReplicationIntervalSeconds());
    	System.out.println("ProxyHost : " + clientConfig.getProxyHost());
    	System.out.println("ProxyPassword : " + clientConfig.getProxyPassword());
    	System.out.println("ProxyUserName : " + clientConfig.getProxyUserName());
    	System.out.println("Region : " + clientConfig.getRegion());
    	System.out.println("RegistryFetchIntervalSeconds : " + clientConfig.getRegistryFetchIntervalSeconds());
    	System.out.println("RegistryRefreshSingleVipAddress : " + clientConfig.getRegistryRefreshSingleVipAddress());
    	System.out.println("allowRedirects : " + clientConfig.allowRedirects());
    	System.out.println("TransportConfig : " + clientConfig.getTransportConfig());
    	System.out.println("shouldDisableDelta : " + clientConfig.shouldDisableDelta());
    	System.out.println("shouldEnforceRegistrationAtInit : " + clientConfig.shouldEnforceRegistrationAtInit());
    	System.out.println("FetchRegistry : " + clientConfig.shouldFetchRegistry());
    	System.out.println("FilterOnlyUpInstances : " + clientConfig.shouldFilterOnlyUpInstances());
    	System.out.println("GZipContent : " + clientConfig.shouldGZipContent());
    	System.out.println("LogDeltaDiff : " + clientConfig.shouldLogDeltaDiff());
    	System.out.println("shouldOnDemandUpdateStatusChange : " + clientConfig.shouldOnDemandUpdateStatusChange());
    	System.out.println("PreferSameZoneEureka : " + clientConfig.shouldPreferSameZoneEureka());
    	System.out.println("shouldRegisterWithEureka : " + clientConfig.shouldRegisterWithEureka());
    	System.out.println("shouldUnregisterOnShutdown : " + clientConfig.shouldUnregisterOnShutdown());
    	System.out.println("UseDnsForFetchingServiceUrls : " + clientConfig.shouldUseDnsForFetchingServiceUrls());
    	
    	
    	
    	
    	System.out.println("------------------Runtime Info ------------------");
    	System.out.println("AllKnownRegions : " + client.getAllKnownRegions());
    	System.out.println("applications : ");
    	client.getApplications().getRegisteredApplications().forEach((app) ->{
    		System.out.println("appname : " + app.getName());
    		System.out.println("instances size :" + app.size());
    		System.out.println(app.getInstances());
    	});
    	
    	for(;;){
    		
    	}
    	
    }
	
}
