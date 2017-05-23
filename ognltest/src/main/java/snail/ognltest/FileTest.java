package snail.ognltest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileTest {
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		File file = new File("D:\\oix-full-snapshot-latest.dat\\oix-full-snapshot-latest.dat");
		FileInputStream fis = new FileInputStream(file);
//		byte b[] = new byte[4096];
//		while(fis.read(b)!=-1){
//			
//		}
//		
//		long end = System.currentTimeMillis();
//		
//		System.out.println(end-start);
		
	    start = System.currentTimeMillis();
		BufferedReader be = new BufferedReader(new InputStreamReader(fis));
		char cbuf[] = new char[100];
		while( be.read(cbuf)!=-1){
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
}
