package com.gcs.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sun.jmx.snmp.Timestamp;

import freemarker.core.ParseException;

public class DateUtil {

	/**
	 * @param args
	 * @throws java.text.ParseException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException,
			java.text.ParseException {
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		System.out.println(d.getDateTime());
	}
	
	public static long getNowTimestamp(){
		return System.currentTimeMillis() / 1000;
	}

	/**
	 * 返回日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static String dateFormat(String date) {
		String regex = "^[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}$";// 格式为：2014/6/3
		if (match(regex, date))
			return "yyyy/MM/dd";
		regex = "^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}\\s[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}$";
		if (match(regex, date))
			return "yyyy-MM-dd HH:mm:ss";
		return null;
	}

	/**
	 * 匹配正则表达式
	 * 
	 * @param regex
	 * @param str
	 * @return
	 */
	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * 根据传入的月份，返回上一个或后一个月份
	 * 
	 * @param operateDate
	 *            月份，格式YYYY-MM
	 * @param flag
	 *            -1为获得前一个月份，1为获得后一个月份
	 * @return String
	 * @throws ParseException
	 * @throws java.text.ParseException
	 */
	public static String getLastMonth(String operateDate, int flag)
			throws ParseException, java.text.ParseException {
		operateDate = operateDate + "-01";
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		c1.setTime(sdf.parse(operateDate));
		c1.add(Calendar.MONTH, flag);
		return sdf.format(c1.getTime()).substring(5, 7);
	}

	/**
	 * 按照指定方式格式化时间
	 * 
	 * @param date
	 *            时间
	 * @param type
	 *            格式
	 * @return
	 */
	public static String dateFormat(Date date, String type) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(type);
			return sdf.format(date);
		} else {
			return "";
		}
	}

	/**
	 * 将Sting类型转为date类型
	 * 
	 * @param type
	 * @param date
	 * @return
	 * @throws ParseException
	 * @throws java.text.ParseException
	 */
	public static Date getString(String date, String type) {
		if (StringUtils.isNotBlank(type)) {
			SimpleDateFormat sdf = new SimpleDateFormat(type);
			try {
				if (StringUtils.isNotBlank(date)) {
					return sdf.parse(date);
				} else {
					return null;
				}
			} catch (java.text.ParseException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 计算时间差 (时间单位,开始时间,结束时间)<br>
	 * s - 秒,m - 分,h - 时,d - 天 ,M - 月 y - 年
	 */
	public static long howLong(String unit, Date begin, Date end) {
		long ltime = begin.getTime() - end.getTime() < 0 ? end.getTime()
				- begin.getTime() : begin.getTime() - end.getTime();
		if (unit.equals("s")) {
			return ltime / 1000;// 返回秒
		} else if (unit.equals("m")) {
			return ltime / 60000;// 返回分钟
		} else if (unit.equals("h")) {
			return ltime / 3600000;// 返回小时
		} else if (unit.equals("d")) {
			return ltime / 86400000;// 返回天数
		} else if (unit.equals("y")) {
			long res = ltime / 86400000;
			return res / 365;
		} else if (unit.equals("M")) {
			long res = ltime / 86400000;
			return res / 30;
		} else {
			return 0;
		}
	}

	public static String getLastMonthYear(String operateDate, int flag)
			throws ParseException, java.text.ParseException {
		operateDate = operateDate + "-01";
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		c1.setTime(sdf.parse(operateDate));
		c1.add(Calendar.MONTH, flag);
		return sdf.format(c1.getTime()).substring(0, 4);
	}

	// 获取当前时间格式（YYYY-MM）
	public static String getYearMonth() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				"yyyy-MM");
		java.util.Date currTime = new java.util.Date();
		return formatter.format(currTime);
	}

	// 获得当前时间格式（yyyy-MM-dd）
	public static String getYearMonthDay() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		java.util.Date currTime = new java.util.Date();
		return formatter.format(currTime);
	}

	// 得到当前月的第一天日期 格式（yyyy-MM-dd）
	public static String getThisMonthFirstDay() {
		String day = getYearMonth();
		return day + "-01";
	}

	// 转换时间为yyyy-MM-dd HH:mm:ss格式的字符串
	public static String covDateToStr(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(date);
		}
		return "";
	}

	// 转换时间为yyyy-MM-dd HH:mm:ss格式的字符串
	public static String covDateToStr2(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		}
		return "";
	}

	// 获得指定开始时间到今年的所有年份信息
	public static List<String> getAllYearByStartYear(String startYear) {
		List<String> yearList = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String nowYear = sdf.format(new Date());
		Integer startYearInt = Integer.valueOf(startYear);
		Integer nowYearInt = Integer.valueOf(nowYear);
		while (startYearInt <= nowYearInt) {
			yearList.add(startYearInt.toString());
			startYearInt++;
		}
		return yearList;
	}

	// 获得当前日期天
	public static String getDayStr() {
		String dayStr = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		dayStr = sdf.format(new Date());
		return dayStr;
	}
	
	// 获得当前日期月
	public static String getYearAndMonthStr() {
		String dayStr = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		dayStr = sdf.format(new Date());
		return dayStr;
	}
	
	/**
	 * 将时间转化成日期（2014-11-11转成11月11日）
	 * @param datetime
	 * @return
	 */
	public static String getMonthAndDay(String datetime){
		String timeStr = "";
		try {
			Date time = stringParseDate(datetime);
			String newtimestr = dateParseString(time);
			timeStr = newtimestr.substring(5, 7) + "月" + newtimestr.substring(8, 10) + "日";
		} catch (Exception e) {
			timeStr = datetime;
		}
		return timeStr;
	}
	
	/**
	 * 字符串转化时间
	 * @param daytime 时间字符串
	 */
	public static Date stringParseDate(String daytime) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy-MM-dd");
		return sdf.parse(daytime);
	}
	
	/**
	 * 时间转化字符串
	 * @param daytime 时间
	 */
	public static String dateParseString(Date daytime) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy-MM-dd");
		return sdf.format(daytime);
	}
	
	/**
	 * 天数加一
	 * @param daytime 时间字符串
	 */
	public static String addOneDay(String daytime) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(stringParseDate(daytime));
		cal.add(Calendar.DAY_OF_MONTH, 1);
		daytime = dateParseString(cal.getTime());
		return daytime;
	}
	
	/**
	 * 天数减一
	 * @param daytime 时间字符串
	 */
	public static String reduceOneDay(String daytime) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(stringParseDate(daytime));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		daytime = dateParseString(cal.getTime());
		return daytime;
	}
	
	/**
	 * 获取当前时间状态(0：上午，1：下午：2：晚上)
	 * @return
	 */
	public static int getDataState(){
		String dayStr = "";
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		dayStr = sdf.format(new Date());
		int dataInt = Integer.parseInt(dayStr);
		if(dataInt >= 4 && dataInt < 12){
			//上午
			return 0;
		} else if(dataInt >= 12 && dataInt < 19){
			//下午
			return 1;
		} else {
			//晚上
			return 2;
		}
	}
	
}
