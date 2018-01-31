package com.practice.hadoop.common;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试FileInputFormat的getInputSplit如何切分文件
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
	public void fileSplitTest() throws IOException {
		
		TextInputFormat in = new TextInputFormat();
		JobConf job = new JobConf();
		FileInputFormat.setInputPaths(job, "/text");
		in.configure(job);
		InputSplit[] splits = in.getSplits(job, 10);
		
		System.out.println("一个分成 : " + splits.length + "份");
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
