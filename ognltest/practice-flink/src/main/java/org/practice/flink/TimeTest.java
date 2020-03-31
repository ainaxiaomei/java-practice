package org.practice.flink;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class TimeTest {
	
	public static void main(String[] args) {
		
		/**
		 * 10/Mar/2020:04:26:37
		 * 
		 * 12/March/2020:18:00:40
		 */
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss",Locale.UK);
		System.out.println(ZonedDateTime.now().format(f));
		
		LocalDateTime t = LocalDateTime.parse("12/March/2020:18:00:40", f);
		System.out.println(t.toString());
		t = LocalDateTime.parse("10/Mar/2020:04:26:37", f);
		
		
	}

}
