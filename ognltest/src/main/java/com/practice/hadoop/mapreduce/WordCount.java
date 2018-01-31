package com.practice.hadoop.mapreduce;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {

	static class WordMapper extends Mapper<Object, Text, Text, IntWritable> {
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

		}

	}

	static class WordReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();

		@Override
		protected void reduce(Text key, Iterable<IntWritable> values,
				Reducer<Text, IntWritable, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			result.set(sum);
			context.write(key, result);
		}

	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		while (args.length < 2) {
			System.out.println("参数错误");
			System.exit(0);
		}

		Configuration conf = new Configuration();
		conf.set(Job.NUM_REDUCES, "1");
		Job job = Job.getInstance(conf);
		job.setMapperClass(WordMapper.class);
		job.setReducerClass(Reducer.class);
		job.setJarByClass(WordCount.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.setCombinerClass(Reducer.class);

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
