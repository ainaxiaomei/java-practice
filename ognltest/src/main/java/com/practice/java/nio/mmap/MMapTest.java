package com.practice.java.nio.mmap;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MMapTest {
	
	public static void main(String[] args) throws IOException {
		
		
		File file = new File("E:\\workspace\\minerva-server\\ip-service\\ip.txt");
		
		RandomAccessFile raf = new RandomAccessFile(file,"r");
		MappedByteBuffer bufer = raf.getChannel().map(MapMode.READ_ONLY, 0, raf.length());
		System.out.println("文件大小 : " + bufer.capacity());
		
		List<Byte> line = new ArrayList<>();
		
		
		for ( int i=0 ; i<bufer.capacity(); i++) {
			byte b = bufer.get();
			line.add(b);
			if (b == '\n') {
				break;
			}
		}
		
		byte[] barray = new byte[line.size()];
		
		for (int i = 0; i < line.size(); i++) {
			barray[i] = line.get(i);
		}
		
		String str = new String(barray);
		System.out.println(str);
		String [] strArray = str.split("\t");
		for (int i=0 ; i< strArray.length; i++) {
			System.out.print(strArray[i]);
		}
		
		System.out.println();
		raf.close();
		
	}
}
