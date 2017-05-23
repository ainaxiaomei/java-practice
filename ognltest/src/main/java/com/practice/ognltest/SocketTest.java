package com.practice.ognltest;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.SocketFactory;

public class SocketTest {
	public static void main(String[] args) {
		try {
			Socket soc = SocketFactory.getDefault().createSocket("192.168.5.23", 5672);
			System.out.println(soc.getPort());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
