package com.practice.java;

import java.util.Date;

/**
 * 测试java的克隆
 * 1.java 原生的clone是浅克隆，成员对象不会克隆,只是指向同一对象
 * 2.java克隆需要实现Cloneable方法
 * 3.克隆的两个对象是不相等(== false)是新的对象
 * 4.Object的clone是保护方法不能在外部直接使用
 * 
 */
public class CloneTest {

	static class Element implements Cloneable {
		private int index;

		private String text;
	    
		private Date date;
		
		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public Element(int index, String text) {
			super();
			this.index = index;
			this.text = text;
			this.date = new Date();
		}
		
		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public Element clone() throws CloneNotSupportedException {
			return (Element) super.clone();
		}

		@Override
		public String toString() {
			return "Element [index=" + index + ", text=" + text + "]";
		}
		

	}
    
	public static void main(String[] args) {
		Element ela = new Element(1, "abc");
		try {
			Element elb = ela.clone();
			System.out.println("elb is " + elb);
			//clone的对象不相等
			System.out.println("elb is == ela : " + (elb==ela));//true
			//浅克隆对象成员不复制对象是相等
			System.out.println(elb.getIndex()==ela.getIndex());//true
			System.out.println(elb.getText()==ela.getText());//true
			System.out.println(elb.getDate()==ela.getDate());//true
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}
