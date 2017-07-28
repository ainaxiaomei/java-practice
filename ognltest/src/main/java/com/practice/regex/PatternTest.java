package com.practice.regex;

import java.util.regex.Pattern;

public class PatternTest {
	public static void main(String[] args) {
		String str = "123";
		str.matches("$.");
		
		Pattern p = Pattern.compile("^1");
		boolean b = p.matcher("123").matches();
		System.out.println(b);
	}
}
