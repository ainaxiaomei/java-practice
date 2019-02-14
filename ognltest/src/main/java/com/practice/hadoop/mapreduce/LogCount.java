package com.practice.hadoop.mapreduce;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.mortbay.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 根据日志统计客户端的访问ip数
 * 日志格式
114.124.137.92 - - [12/Jan/2019:01:27:37 +0800] "GET http://cycdnml.yuanzhanapp.com/res_version/349.2/53_sniper_attack_zidan_diaoluo_skin02_add.zip?29772065b4183d26c5598d296c7fa2bd" 200 0.797 - 3267 "-" "-" "-" "[0779e641]" "HIT" "180.250.81.4" 0.000796 4099.12
114.124.175.52 - - [12/Jan/2019:01:27:37 +0800] "GET http://cycdnml.yuanzhanapp.com/res_version/349.2/hero_shikongjianhao_skin01_high_add.zip?f65dd90aab9afe8b21fefd2d1eb21152" 200 10.903 - 574310 "-" "-" "-" "[0779feb4]" "HIT" "180.250.81.4" 0.001073 52674.5
115.178.206.34 - - [12/Jan/2019:01:27:37 +0800] "GET http://cycdnml.yuanzhanapp.com/res_version/349.2/54_mechwarrior_damage_skin02_add.zip?3b25ec4f30d0bb45815eb1faf117905a" 200 0.882 - 9253 "-" "-" "-" "[0779f27e]" "HIT" "180.250.81.4" 0.000881 10490.9

 * @author vergil
 *
 */
public class LogCount {

	private static Logger logger = LoggerFactory.getLogger(LogCount.class);
	
	public static void main(String[] args) {
		
	}
}

class LogMapper extends Mapper<Text, Text, Text, Text>{

	@Override
	protected void map(Text key, Text value, Mapper<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		
		
	}
	
}
