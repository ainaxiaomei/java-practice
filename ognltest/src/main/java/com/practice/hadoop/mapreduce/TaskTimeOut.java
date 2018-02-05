package com.practice.hadoop.mapreduce;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 设置任务超时时间，并在taks任务中睡眠一段时间观察task的情况
 * 
 * @author Administrator
 *
 */
public class TaskTimeOut {

	static class TimeOutMapper extends Mapper<Object, Text, Text, IntWritable> {
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		@Override
		protected void map(Object key, Text value, Mapper<Object, Text, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {
			StringTokenizer itr = new StringTokenizer(value.toString());
			while (itr.hasMoreTokens()) {
				word.set(itr.nextToken());
				context.write(word, one);
			}
			// 睡眠30分钟

			TimeUnit.MINUTES.sleep(30);
		}

	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

		while (args.length < 2) {
			System.out.println("参数错误");
			System.exit(0);
		}

		Configuration conf = new Configuration();
		conf.set(Job.NUM_REDUCES, "0");
		Job job = Job.getInstance(conf);
		job.setMapperClass(TimeOutMapper.class);
		job.setJarByClass(TaskTimeOut.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));

		Path outPath = new Path(args[1]);
		System.setProperty("HADOOP_USER_NAME", "hdfs");
		FileSystem fs = outPath.getFileSystem(conf);
		if (fs.exists(outPath)) {
			fs.delete(outPath, true);
		}

		FileOutputFormat.setOutputPath(job, outPath);
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
