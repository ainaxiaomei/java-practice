package com.practice.java.exception;

import java.io.IOException;

/**
 * 检查异常必须被显示捕获或者throw
 * 检查异常是所有Exception的子类同时不是RuntimeException的子类
 * @author Administrator
 *
 */
public class ExceptionTest {
	public static void main(String[] args) {
		//throw new RuntimeException();//编译通过
		//throw new IOException();//编译报错
	}
}
