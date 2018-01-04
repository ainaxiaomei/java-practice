package com.practice.hadoop.common;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Hdfs;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.junit.Test;

public class HadoopTest1 {

	@Test
	public void test() throws IOException {
		
		Configuration config = new Configuration();
		FileSystem fs = FileSystem.get(config);
		System.out.println("FileSystem Type : " + fs.getClass());
		Path path = new Path(URI.create("/"));
		RemoteIterator<LocatedFileStatus> itr = fs.listFiles(path, true);
	}

}
