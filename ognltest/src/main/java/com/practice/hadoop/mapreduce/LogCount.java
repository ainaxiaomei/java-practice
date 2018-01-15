package com.practice.hadoop.mapreduce;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.mortbay.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogCount {

	private static Logger logger = LoggerFactory.getLogger(LogCount.class);

	static class LoggerMapper extends Mapper<Object, BytesWritable, LongWritable, IntWritable> {

		private static Logger logger = LoggerFactory.getLogger(LoggerMapper.class);

		@Override
		protected void map(Object key, BytesWritable value,
				Mapper<Object, BytesWritable, LongWritable, IntWritable>.Context context)
				throws IOException, InterruptedException {
			Log.info("-------------------");
			logger.info("key is : {}", key);
			logger.info("value is : {}", value);
			Log.info("-------------------");

			StringTokenizer token = new StringTokenizer(new String(value.getBytes()));
			while (token.hasMoreTokens()) {
				String val = token.nextToken();
				if ("INFO".equals(val)) {
					context.write(new LongWritable(1), new IntWritable(1));
				}
			}
		}

	}

	static class LoggerReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

		@Override
		protected void reduce(Text key, Iterable<IntWritable> value,
				Reducer<Text, IntWritable, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {

			int sum = 0;
			Iterator<IntWritable> itr = value.iterator();
			while (itr.hasNext()) {
				IntWritable val = itr.next();
				sum = sum + val.get();
			}

			context.write(key, new IntWritable(sum));

		}

	}

	public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
		Job job = Job.getInstance();
		job.setJarByClass(LoggerMapper.class);

		job.setJobName("logCount");

		job.setInputFormatClass(SequenceFileInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		job.setMapperClass(LoggerMapper.class);
		job.setReducerClass(LoggerReducer.class);

		SequenceFileInputFormat.setInputPaths(job, new Path("/flume/lmsLogs"));
		FileOutputFormat.setOutputPath(job, new Path("/flume/lmsLogs/log"));
		
		System.out.println(job.getMapOutputKeyClass());
		System.out.println(job.getMapOutputValueClass());
		System.out.println(job.getOutputKeyClass());
		System.out.println(job.getOutputValueClass());
		logger.info("mapper key类型{}", job.getMapOutputKeyClass());
		logger.info("mapper value类型{}", job.getMapOutputValueClass());

		logger.info("输出key类型{}", job.getOutputKeyClass());
		logger.info("输出value类型{}", job.getOutputValueClass());
		
		
		job.waitForCompletion(true);

	}

}
