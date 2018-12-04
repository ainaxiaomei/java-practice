package com.practice.elasticsearch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;


public class Test {
	
	public static void main(String[] args) throws IOException, ParseException {
		
		//RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.2.3", 9200, "http")));
		
//		IndexRequest request = new IndexRequest("dns","ip","1").source("name","sunqi");
//		request.create(true);
//		IndexResponse response = client.index(request, RequestOptions.DEFAULT);
//		System.out.println(response);
		
//		GetRequest getRequest = new GetRequest("shakespeare");
//		GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
//		System.out.println(getResponse);
		
//		GetIndexRequest getIndexRequest = new GetIndexRequest().indices("*");
//		GetIndexResponse getIndexResponse = client.indices().get(getIndexRequest, RequestOptions.DEFAULT);
//		ImmutableOpenMap<String, ImmutableOpenMap<String, MappingMetaData>> mappings  = getIndexResponse.mappings();
//		mappings.forEach((ObjectObjectCursor<String, ImmutableOpenMap<String, MappingMetaData>> e)->{
//			
//			System.out.println(" --- index : " + e.key);
//			ImmutableOpenMap<String, MappingMetaData> mapping = e.value;
//			mapping.forEach((ObjectObjectCursor<String, MappingMetaData> mapEntry) -> {
//				System.out.println(" \t|");
//				System.out.println(" \t--- mappings : " + mapEntry.key);
//				System.out.println(" \t\t|");
//				System.out.println(" \t\t--- sources : " + mapEntry.value.sourceAsMap());
//				
//			});
//			System.out.println();
//			
//		});
		
//		SearchRequest sr = new SearchRequest("logstash-2015.05.18");
//		SearchSourceBuilder ssb = new SearchSourceBuilder();
//		ssb.fetchSource(false);
//		ssb.aggregation(AggregationBuilders.dateHistogram("time_interval").field("@timestamp").interval(3600000));
//		sr.source(ssb);
//		SearchResponse sp = client.search(sr, RequestOptions.DEFAULT);
//		System.out.println(sp);
//		
//		
//		client.close();
		
		Random ran = new Random();
		File file = new File("ipdata");
		FileOutputStream fos = new FileOutputStream(file);
		PrintWriter write = new PrintWriter(new OutputStreamWriter(fos));
		for (int i= 0 ; i< 100000; i++) {
			
			int day = ran.nextInt(30) + 1;
			String dayStr = "";
			if ( day < 10) {
				dayStr = "0" + day;
			} else {
				dayStr = String.valueOf(day);
			}
			
			int hour = ran.nextInt(24) + 1;
			String hourStr = "";
			if ( hour < 10) {
				hourStr = "0" + hour;
			} else {
				hourStr = String.valueOf(hour);
			}
			
			int min = ran.nextInt(60) + 1;
			String minStr = "";
			if ( min < 10) {
				minStr = "0" + min;
			} else {
				minStr = String.valueOf(min);
			}
			
			int second = ran.nextInt(59) + 1;
			String secondStr = "";
			if ( second < 10) {
				secondStr = "0" + second;
			} else {
				secondStr = String.valueOf(second);
			}
			
			
			char[] chars = new char[]{'a','b','c','a','a','f','f','f','g','a','x','y'};
			String ip = ran.nextInt(255) + "." + ran.nextInt(255) + "." + ran.nextInt(255) + "." + ran.nextInt(255);
			String domain = String.valueOf(chars[ran.nextInt(12)]) + chars[ran.nextInt(12)] + chars[ran.nextInt(12)]+".asia-data.com.";
			String data = "[2018-" + 10 + "-" + dayStr + " " + hourStr + ":" + minStr + ":" + secondStr + ".573822] nsd[30821]: info: mode=[udp] pid=[30821] "
					+ "domain=[" + domain + "]"+ " qtype=[A] "
					+ "ip=["+ip+"] subnet=[] "
					+ ""
					+ "view=[GB,others|default] an=[0] rcode=[NO ERROR] asnwer=[]";
			
			
			//System.out.println(data);
			write.println(data);
			write.flush();
		}
		write.close();
		fos.close();
		
	}

}
