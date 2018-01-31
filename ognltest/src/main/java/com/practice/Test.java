package com.practice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\Administrator\\Desktop\\text");
		if(!file.exists()){
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		int count = 128*1024*1024*10;
		while(count > 0) {
			byte[] data = "Browse Directory 123 FileOutputStream \n".getBytes();
			fos.write(data);
			fos.flush();
			count = count - data.length;
		}
		
		fos.close();
	}
}
