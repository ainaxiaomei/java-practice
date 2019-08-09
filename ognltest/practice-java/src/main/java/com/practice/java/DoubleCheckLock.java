package com.practice.java;

public class DoubleCheckLock {

	private String target;

	public String getTarget1() {

		if (target == null) {
			synchronized (this) {
				if (target == null) {
					target = new String("123");
				} else {
					return null;
				}
			}
		}

		return target;
	}

	public String getTarget2() {

			synchronized (this) {
				if (target == null) {
					target = new String("123");
				} else {
					return null;
				}
				return target;
			}

		
	}

}
