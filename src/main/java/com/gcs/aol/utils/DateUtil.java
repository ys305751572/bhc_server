package com.gcs.aol.utils;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class DateUtil {
	static String foramtDate = "yyyy-MM-dd HH:mm";
	static Integer timeDiffer = 100;//设置时间差，用来判断新增时间 单位为天
	
	public static Date StringToDate(String stringDate){	
		
		Format format = new SimpleDateFormat(foramtDate);
		
		Date dPromise = null;
		
		try {
			dPromise = (Date) format.parseObject(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	return dPromise;
	}
	
	public static String DeleteYear(String date){
		return date.substring(5).substring(0, date.substring(5).length()-3);
	}
	public static void main(String[] args) {
		System.out.println(DeleteYear("aaaa"));
	}
	public static String DateToStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		   String str = format.format(date);
		   return str;
	}
	
	public static String GetCurrentTime() {
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currenttime = dateFormat.format(now);
		return currenttime;
	}
	public static long GetDiffTime(String day) throws ParseException {
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currenttime = dateFormat.format(now);
		Date starttime = dateFormat.parse(currenttime);
		long endtime = starttime.getTime()-Integer.valueOf(day)*(24*60*60*1000);
		return endtime;
	}
	
	public static String getDateBefore(int day) {  
        Calendar now = Calendar.getInstance();  
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day); 
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    String str = format.format(now.getTime());
        return str;  
    }  
	
	public static String getNewStartTime(){
		/*Date date = new Date();
		String newStartTime = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -timeDiffer);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		date = calendar.getTime();
		newStartTime = format.format(date);
		return newStartTime;*/
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        String first = format.format(c.getTime());
        return first;
	}
	
	/**
	 * 格式化字符串型日期
	 * @param strDate
	 * @return 标准格式：yyyy-MM-dd
	 */
	public static String formatStrDate(String strDate){
		Format format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		String newStrDate = null;
		
		try {
			newStrDate = sdformat.format((Date) format.parseObject(strDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return newStrDate;
	}
	
}
