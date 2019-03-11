package com.practice.hadoop.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountTest {
	
	
	

}

class WordMapper extends Mapper<Object, Text, Text, IntWritable> {

	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		context.write(new Text("line"), new IntWritable(1));
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
