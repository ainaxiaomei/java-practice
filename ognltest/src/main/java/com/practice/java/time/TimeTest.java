package com.practice.java.time;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import org.junit.Test;

public class TimeTest {
	
	@Test
	public void localDateTest() {
		
		LocalDate localDate = LocalDate.now();
		System.out.println("当前日期 : " + localDate );
		
		LocalTime localTime = LocalTime.now();
		System.out.println("当前日期 时间: " + localTime );
		
		LocalDateTime localDateTime = LocalDateTime.now(); 
		System.out.println("当前日期 时间: " + localDateTime );

		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		System.out.println("当前时区日期 时间: " + zonedDateTime );
		
		ZonedDateTime utcTime = ZonedDateTime.now(Clock.systemUTC());
		System.out.println("当前UTC日期 时间: " + utcTime );
		
	}
	
	@Test
	public void clockTest() {
		Clock clock = Clock.systemUTC();
		System.out.println("clock : " + clock);
		System.out.println("clock millis: " + clock.millis());
		System.out.println("clock currentTimeMillis: " + System.currentTimeMillis());
	}
	

}
