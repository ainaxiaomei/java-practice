package org.practice.flink;

import java.util.Calendar;
import java.util.Date;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;

public class FlinkHDFSTest {
	
	
	public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

	
	public static void main(String[] args) throws Exception {
		
		String t0 = "";

		ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
		env.readTextFile("hdfs://192.168.2.3:9000/logs/txwd/6DFDCF646BEC146C38E21CD051A0B747_20190923_00_caa_001.log")
				.map(new MapFunction<String, Tuple2<String, String>>() {

					@Override
					public Tuple2<String, String> map(String value) throws Exception {
						
						String time = value.split(" ")[3];
						return new Tuple2<String, String>(time,value.split(" ")[0]);				
					}

				}).print();
	}

}
