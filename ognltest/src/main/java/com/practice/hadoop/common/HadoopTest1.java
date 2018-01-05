package com.practice.hadoop.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Arrays;
import java.util.Date;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FSInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Hdfs;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.junit.Before;
import org.junit.Test;

public class HadoopTest1 {

	private FileSystem fs;
    
	@Before
	public void init() throws IOException {
		System.setProperty("HADOOP_USER_NAME", "hdfs");

		Configuration conf = new Configuration();
		fs = FileSystem.get(conf);
	}

	@Test
	public void addTest() throws IOException {

		Path path = new Path("/sunqi-123");
		FSDataOutputStream fsOut = fs.create(path);
		fsOut.writeBytes("123123");
		fsOut.hflush();

	}

	@Test
	public void deleteTest() throws IOException {
		Path path = new Path("/sunqi-123");
		fs.delete(path, true);
	}
	
	@Test
	public void importTest() throws IOException {
		Path src = new Path("D:\\workspace\\javapractice\\java-practice\\ognltest\\src\\main\\resources\\word");
		Path dst = new Path("/sunqi-123");
	
		fs.copyFromLocalFile(src, dst);
	}
	
	@Test
	public void readTest() throws IOException {
		Path dst = new Path("/sunqi-123");
		FSDataInputStream fsIn = fs.open(dst);
		BufferedReader reader = new BufferedReader(new InputStreamReader(fsIn));
		String str = null;
		
		while ((str = reader.readLine()) != null) {
			System.out.println(str);
		}
	}
	
	
	/**
	 * listFiles如果没有指定地位，且当前文件是个目录只会返回该目录下的文件信息
	 * listLocatedStatus不仅会列出文件还会列出目录
	 * @throws IOException
	 */
	@Test
	public void listTest() throws IOException {
		Path dst = new Path("/");
		//RemoteIterator<LocatedFileStatus> itr = fs.listFiles(dst, false);
		RemoteIterator<LocatedFileStatus> itr = fs.listLocatedStatus(dst);
		while(itr.hasNext()){
			System.out.println("---------");
			LocatedFileStatus fileStatus = itr.next();
			System.out.println(fileStatus.getPath() + "信息 :");
			System.out.println("访问时间 : " + new Date(fileStatus.getAccessTime()) );
			System.out.println("修改时间: " + new Date(fileStatus.getModificationTime()));
			System.out.println("是目录 : " + fileStatus.isDirectory());
			System.out.println("块大小 : " + fileStatus.getBlockSize());
			System.out.println("长度 : " + fileStatus.getLen());
			System.out.println("拥有者: " + fileStatus.getOwner());
			System.out.println("用户组 : " + fileStatus.getGroup());
			System.out.println("副本数: " + fileStatus.getReplication());
			System.out.println("是否加密: " + fileStatus.isEncrypted());
			System.out.println("是否是符号链接: " + fileStatus.isSymlink());
			System.out.println("权限信息: " + fileStatus.getPermission());
			System.out.println("块位置: " + Arrays.toString(fileStatus.getBlockLocations()));
		}
	}
	
	

}
