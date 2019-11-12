package com.practice.hadoop.hdfs;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

public class HdfsTest {
	
	public static void main(String[] args) throws IOException {
		
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://sh-tl-65:8020");
		FileSystem fs = FileSystem.get(conf);
		RemoteIterator<LocatedFileStatus> itr = fs.listFiles(new Path("/"), false);
		
		while (itr.hasNext()) {
			LocatedFileStatus file = itr.next();
			System.out.println(file.getPath().toString());
		}
	}

}
