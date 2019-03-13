package com.practice.hadoop.mapreduce;

import java.io.IOException;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class WordCountTest {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		while (args.length < 2) {
			System.out.println("参数错误");
			System.exit(0);
		}

		Configuration conf = new Configuration();
		conf.set(Job.NUM_REDUCES, "1");
		Job job = Job.getInstance(conf);
		job.setMapperClass(WordMapper.class);
		job.setReducerClass(WordReducer.class);
		job.setPartitionerClass(SunqiPartitioner.class);
		job.setJarByClass(WordCountTest.class);
		job.setNumReduceTasks(3);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.setCombinerClass(WordReducer.class);
		
		

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

class WordMapper extends Mapper<Object, Text, Text, IntWritable> {

	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		System.out.println("----------" + key);
		context.write(new Text("line"), new IntWritable(1));
	}
	
}

class SunqiPartitioner extends Partitioner<Text, IntWritable> {
	
	
	private static final Logger log = LoggerFactory.getLogger(SunqiPartitioner.class);

	
	private int count = 0 ;

	@Override
	public int getPartition(Text key, IntWritable value, int numPartitions) {
		count = count + 1;
		
		log.error("count is {},numPartitions is {}",count,numPartitions);
		System.out.println("---------------");
		System.out.println(String.format("count is %s,numPartitions is %s",count,numPartitions));
		//return count % 2;
		
		return 0;
	}
	
}

class WordReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	
	private IntWritable result = new IntWritable();

	@Override
	protected void reduce(Text key, Iterable<IntWritable> list,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		
		int sum = 0;
		for (IntWritable val : list) {
			sum += val.get();
		}
		
		result.set(sum);
		context.write(key, result);
	}
	
	
	
	
	
}
