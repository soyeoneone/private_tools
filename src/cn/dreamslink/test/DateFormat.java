package cn.dreamslink.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd" + "0000");
		Date date = new Date();
		System.out.println(sdf.format(date));
		
	}
}
