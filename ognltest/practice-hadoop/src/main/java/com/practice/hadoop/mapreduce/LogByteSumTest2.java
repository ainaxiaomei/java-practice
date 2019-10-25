package com.practice.hadoop.mapreduce;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
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
public class LogByteSumTest2 {
	
	private static Logger logger = Logger.getLogger(LogByteSumTest2.class);
	
	
	public static class GroupMapper extends Mapper<Object, Text, Text, LongWritable> {

		@Override
		protected void map(Object key, Text value, Mapper<Object, Text, Text, LongWritable>.Context context)
				throws IOException, InterruptedException {
			
            String[] wordArray = value.toString().split(" ");
			
			String year = wordArray[7].trim();
			
			String month = wordArray[8].trim();
			
			String day = wordArray[9].trim();
			
			String hour = wordArray[10].trim();
			
			String min5 = wordArray[11].trim();
			
			String group = year + month + day + hour + min5;
			context.write(new Text(group), new LongWritable(Long.valueOf(wordArray[18])));
		}
		
		
		
	}
	
	public static class ByteSumReducer extends Reducer< Text, LongWritable, Text, LongWritable>{

		@Override
		protected void reduce(Text group, Iterable<LongWritable> list,
				Reducer<Text, LongWritable, Text, LongWritable>.Context context) throws IOException, InterruptedException {
			
			Iterator<LongWritable> itr = list.iterator();
			
			long sum = 0;
			while (itr.hasNext()) {
				sum = sum + itr.next().get();
			}
			
			context.write(group, new LongWritable(sum));
			
		}
		
		
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
//		BasicConfigurator.configure();
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		job.setMapperClass(GroupMapper.class);
		job.setCombinerClass(ByteSumReducer.class);
		job.setReducerClass(ByteSumReducer.class);
		job.setJarByClass(LogByteSumTest2.class);
//		job.setPartitionerClass(GropPatitioner.class);
		
		
		FileSystem fs = FileSystem.newInstance(conf);
		
		if (fs.exists(new Path("/sunqi/mapreduce/out"))) {
			fs.delete(new Path("/sunqi/mapreduce/out"),true);
		}
		
		FileInputFormat.setInputPaths(job, new Path("/user/hive/warehouse/asia_cdn_log"));
		FileOutputFormat.setOutputPath(job, new Path("/sunqi/mapreduce/out"));
		
		System.setProperty("HADOOP_USER_NAME", "root");
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
	}
	

}
