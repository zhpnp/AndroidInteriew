package com.zhp.utils;

import com.zhp.bean.SelectDate;

/**
 * 自定义的日期操作工具类，将指定的日期格式字符串转换成所需要的类型
 * @author zhp
 *
 */
public class DateTransformUtil {
	
	/**
	 * 将指定格式的日期字符串转换成SelectDate对象
	 * @param date 指定格式的日期字符串
	 * @return 
	 */
	public static SelectDate transform(String date){
		if(date == null){
			return new SelectDate();
		}
		String[] dates = date.trim().split(" +");
		SelectDate sd = new SelectDate();
		sd.setYear(Integer.parseInt(dates[0].trim()));
		sd.setMonthOfYear(Integer.parseInt(dates[2].trim()));
		sd.setDayOfMonth(Integer.parseInt(dates[4].trim()));
		sd.setHourOfDay(Integer.parseInt(dates[6].trim()));
		sd.setMinuteOfHour(Integer.parseInt(dates[8].trim()));
		return sd;
	}
	
	/**
	 * 去除指定日期格式字符串中的年份
	 * @param date 指定格式的日期字符串
	 * @return
	 */
	public static String cutYear(String date){
		if(date == null){
			return null;
		}
		int index = date.indexOf("年");
		return date.substring(index+1).trim();
	}
	
	/**
	 * 去除指定日期格式字符串中的年月日，只留下小时和分钟
	 * @param date 指定格式的日期字符串
	 * @return
	 */
	public static String timeOlny(String date){
		if(date==null){
			return null;
		}
		int index = date.indexOf("日");
		return date.substring(index+1).trim();
	}
	
	
}
