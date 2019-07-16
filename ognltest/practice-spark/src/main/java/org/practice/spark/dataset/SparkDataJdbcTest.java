package org.practice.spark.dataset;

import java.util.Arrays;
import java.util.Properties;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkDataJdbcTest {
	
	public static void main(String[] args) {
		SparkSession ss = SparkSession.builder().getOrCreate();
		
		Properties properties = new Properties();
		properties.setProperty("user", "root");
		properties.setProperty("password", "00715701");
		Dataset<Row> df = ss.read().jdbc("jdbc:mysql://192.168.2.3:3306/dns?"
				+ "useCompression=true&useUnicode=true&characterEncoding=utf-8",
				"t_location", properties);
		df.show();
		df.select("ip_begin");
		
		
		Dataset<Loction> loction= df.as(Encoders.bean(Loction.class));
		loction.show();
		
		
	}
	
	
	public static class Loction {
		
		private String ip_begin;
		
		private String ip_end;
		
		private String continent;
		
		private String country;
		
		private String province;
		
		private String isp;

		public String getIp_begin() {
			return ip_begin;
		}

		public void setIp_begin(String ip_begin) {
			this.ip_begin = ip_begin;
		}

		public String getIp_end() {
			return ip_end;
		}

		public void setIp_end(String ip_end) {
			this.ip_end = ip_end;
		}

		public String getContinent() {
			return continent;
		}

		public void setContinent(String continent) {
			this.continent = continent;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getIsp() {
			return isp;
		}

		public void setIsp(String isp) {
			this.isp = isp;
		}
		
		
		
		
		
	}
	
	

}
