package com.gcs.sysmgr.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtils {
	public static long getNeedTime(String startTime,String planDay) throws ParseException{
		long count = 0;
	    Date nowTime=new Date();   
	    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
	    Date start = df.parse(startTime);
	    GregorianCalendar gc=new GregorianCalendar(); 
	    gc.setTime(start);
	    gc.add(5,Integer.parseInt(planDay));
	    count = (gc.getTime().getTime()-nowTime.getTime())/(24 * 60 * 60 * 1000);
		return count;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(getNeedTime("2013-12-01","10"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
