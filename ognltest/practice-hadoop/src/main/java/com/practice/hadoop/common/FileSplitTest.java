package com.practice.hadoop.common;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试FileInputFormat的getInputSplit如何切分文件
 * 当一个blk剩余的大小不足以作为一个split的时候，这个split不会跨blk
 * @author Administrator
 *
 */
public class FileSplitTest {

	private FileSystem fs;

	@Before
	public void init() throws IOException {
		System.setProperty("HADOOP_USER_NAME", "hdfs");

		Configuration conf = new Configuration();
		fs = FileSystem.get(conf);
	}

	
	
	@Test
	public void fileSplitTest() throws IOException, InterruptedException {
		
		TextInputFormat in = new TextInputFormat();
		Job job = Job.getInstance();
		job.getConfiguration().set(FileInputFormat.SPLIT_MAXSIZE, String.valueOf(50 * 1024 * 1024));
		FileInputFormat.setInputPaths(job, "/text");
		List<InputSplit> splits = in.getSplits(job);
		
		System.out.println("默认记录分割符 : " + job.getConfiguration().get("textinputformat.record.delimiter"));
		System.out.println("一个分成 : " + splits.size() + "份");
		for(InputSplit split : splits){
			System.out.println("[");
			    FileSplit s = (FileSplit)split;
				System.out.println("\t长度 : " + split.getLength());
				System.out.println("\t主机位置 : " + split.getLocations());
				System.out.println("\t相对文件的偏移值 : " + s.getStart());
				System.out.println("\tLocationInfo : " + s.getLocationInfo());
			System.out.println("]");
		}
	}



}
