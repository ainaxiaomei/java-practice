package com.practice.hadoop.mapreduce;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * 
    * @ClassName: LogByteSumTest  
    * @Description: 按照年 月 日 时 5min为粒度统计流量
    * 日志格式 
    * 175.176.21.8 AS PH  OTHERS cycdnml.yuanzhanapp.com 2019-10-10T07:12:37.000Z 2019 10 10 7 10 GET http /res_version5/445.1/zzp2_huicheng_load_jpliyu_add.zip HTTP/1.1 200 0.017 25594 383 - - TCP_HIT
    * @author win  
    * @date 2019年10月17日  
    *
 */
public class LogByteSumTest {
	
//	private static Logger logger = Logger.getLogger(LogByteSumTest.class);
//	
//	/**
//	 * 
//	    * @ClassName: GrouyKey  
//	    * @Description: 实现自定义的key,按照  年 月 日 时 5min分组
//	    * @author win  
//	    * @date 2019年10月17日  
//	    *
//	 */
//	public static class GrouyKey implements  WritableComparable<GrouyKey>{
//		
//		
//		
//		private String year;
//		
//		private String month;
//		
//		private String day;
//		
//		private String hour;
//		
//		private String min5;
//		
//		private String src;
//		
//		public GrouyKey() {
//			
//		}
//
//		public GrouyKey(String year, String month, String day, String hour, String min5,String src) {
//			this.year = year;
//			this.month = month;
//			this.day = day;
//			this.hour = hour;
//			this.min5 = min5;
//			this.src = src;
//		}
//		
//
//		public String getSrc() {
//			return src;
//		}
//
//		public void setSrc(String src) {
//			this.src = src;
//		}
//
//		public String getYear() {
//			return year;
//		}
//
//		public void setYear(String year) {
//			this.year = year;
//		}
//
//		public String getMonth() {
//			return month;
//		}
//
//		public void setMonth(String month) {
//			this.month = month;
//		}
//
//		public String getDay() {
//			return day;
//		}
//
//		public void setDay(String day) {
//			this.day = day;
//		}
//
//		public String getHour() {
//			return hour;
//		}
//
//		public void setHour(String hour) {
//			this.hour = hour;
//		}
//
//		public String getMin5() {
//			return min5;
//		}
//
//		public void setMin5(String min5) {
//			this.min5 = min5;
//		}
//
//		@Override
//		public void write(DataOutput out) throws IOException {
//			
//			out.writeUTF(year);
//			out.writeUTF(month);
//			out.writeUTF(day);
//			out.writeUTF(hour);
//			out.writeUTF(min5);
//			out.writeUTF(src);
//			
//			
//		}
//
//		@Override
//		public void readFields(DataInput in) throws IOException {
//			year = in.readUTF();
//			month = in.readUTF();
//			day = in.readUTF();
//			hour = in.readUTF();
//			min5 = in.readUTF();
//			src = in.readUTF();
//			
//		}
//
//		@Override
//		public String toString() {
//			return "GrouyKey [year=" + year + ", month=" + month + ", day=" + day + ", hour=" + hour + ", min5=" + min5
//					+  "]";
//		}
//
//		@Override
//		public int hashCode() {
//			return 1;
//		}
//
//		@Override
//		public boolean equals(Object obj) {
//			
//			logger.info("----" + this.equals(obj) + " " + this + " " + obj);
//			GrouyKey other = (GrouyKey) obj;
//			if (this.year.equals(other.getYear())&&
//				this.month.equals(other.getMonth()) &&	
//				this.day.equals(other.getDay()) &&
//				this.hour.equals(other.getHour()) &&
//				this.min5.equals(other.getMin5())) {
//				
//				return true;
//				
//			}
//			return false;
//		}
//
//		@Override
//		public int compareTo(GrouyKey o) {
//			
//			if (this.year.equals(o.year) && 
//					this.month.equals(o.month) && 
//					this.day.equals(o.day) &&
//					this.hour.equals(o.hour) &&
//							this.min5.equals(o.min5)) {
//				
//				return 0;
//			}
//			
//			if (Long.valueOf(this.year) > Long.valueOf(o.year)) {
//				
//				return 1 ;
//			}
//			
//			if (Long.valueOf(this.year) == Long.valueOf(o.year) && 
//					Long.valueOf(this.month) > Long.valueOf(o.month)) {
//				
//				return 1 ;
//			}
//			if (Long.valueOf(this.year) == Long.valueOf(o.year) && 
//					Long.valueOf(this.month) == Long.valueOf(o.month) && 
//					Long.valueOf(this.day) > Long.valueOf(o.day)) {
//				
//				return 1 ;
//			}
//			if (Long.valueOf(this.year) == Long.valueOf(o.year) && 
//					Long.valueOf(this.month) == Long.valueOf(o.month) && 
//					Long.valueOf(this.day) == Long.valueOf(o.day) &&
//					Long.valueOf(this.hour) > Long.valueOf(o.hour)) {
//				
//				return 1 ;
//			}
//			if (Long.valueOf(this.year) == Long.valueOf(o.year) && 
//					Long.valueOf(this.month) == Long.valueOf(o.month) && 
//					Long.valueOf(this.day) == Long.valueOf(o.day) &&
//					Long.valueOf(this.hour) == Long.valueOf(o.hour) &&
//					Long.valueOf(this.min5) > Long.valueOf(o.min5)) {
//				
//				return 1 ;
//			}
//			return -1;
//		}
//		
//	}
//	
//	
////	public static class GropPatitioner extends Partitioner<GrouyKey, LongWritable>{
////
////		@Override
////		public int getPartition(GrouyKey key, LongWritable value, int numPartitions) {
////			String str = key.getYear() + key.getMonth() + key.getDay()
////			+ key.getHour() + key.getMin5();
////			return Integer.valueOf(str) % numPartitions;
////		}
////		
////	}
////	
//	public static class GroupMapper extends Mapper<Object, Text, GrouyKey, LongWritable>{
//
//		@Override
//		protected void map(Object key, Text value, Mapper<Object, Text, GrouyKey, LongWritable>.Context context)
//				throws IOException, InterruptedException {
//			
//			String[] wordArray = value.toString().split(" ");
//			
//			String year = wordArray[7].trim();
//			
//			String month = wordArray[8].trim();
//			
//			String day = wordArray[9].trim();
//			
//			String hour = wordArray[10].trim();
//			
//			String min5 = wordArray[11].trim();
//			
//			GrouyKey group = new GrouyKey(year,month,day,hour,min5,value.toString());
//			
//			context.write(group, new LongWritable(Long.valueOf(wordArray[18])));
//		}
//		
//	}
//	
//	public static class ByteSumReducer extends Reducer< GrouyKey, LongWritable, GrouyKey, LongWritable>{
//
//		@Override
//		protected void reduce(GrouyKey group, Iterable<LongWritable> list,
//				Reducer<GrouyKey, LongWritable, GrouyKey, LongWritable>.Context context) throws IOException, InterruptedException {
//			
//			Iterator<LongWritable> itr = list.iterator();
//			
//			long sum = 0;
//			while (itr.hasNext()) {
//				sum = sum + itr.next().get();
//			}
//			
//			context.write(group, new LongWritable(sum));
//			
//		}
//		
//		
//	}
//	
//	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
//		
//		Configuration conf = new Configuration();
//		Job job = Job.getInstance(conf);
//		BasicConfigurator.configure();
//		job.setMapOutputKeyClass(GrouyKey.class);
//		job.setMapOutputValueClass(LongWritable.class);
//		job.setMapperClass(GroupMapper.class);
//		job.setCombinerClass(ByteSumReducer.class);
//		job.setReducerClass(ByteSumReducer.class);
//		job.setJarByClass(LogByteSumTest.class);
////		job.setPartitionerClass(GropPatitioner.class);
//		
//		
//		FileSystem fs = FileSystem.newInstance(conf);
//		
//		if (fs.exists(new Path("/sunqi/mapreduce/out"))) {
//			fs.delete(new Path("/sunqi/mapreduce/out"),true);
//		}
//		
//		FileInputFormat.setInputPaths(job, new Path("/user/hive/warehouse/asia_cdn_log"));
//		FileOutputFormat.setOutputPath(job, new Path("/sunqi/mapreduce/out"));
//		
//		System.setProperty("HADOOP_USER_NAME", "root");
//		System.exit(job.waitForCompletion(true) ? 0 : 1);
//	}

}
