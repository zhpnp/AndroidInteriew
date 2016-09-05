package com.zhp.utils;

import com.zhp.bean.SelectDate;

/**
 * �Զ�������ڲ��������࣬��ָ�������ڸ�ʽ�ַ���ת��������Ҫ������
 * @author zhp
 *
 */
public class DateTransformUtil {
	
	/**
	 * ��ָ����ʽ�������ַ���ת����SelectDate����
	 * @param date ָ����ʽ�������ַ���
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
	 * ȥ��ָ�����ڸ�ʽ�ַ����е����
	 * @param date ָ����ʽ�������ַ���
	 * @return
	 */
	public static String cutYear(String date){
		if(date == null){
			return null;
		}
		int index = date.indexOf("��");
		return date.substring(index+1).trim();
	}
	
	/**
	 * ȥ��ָ�����ڸ�ʽ�ַ����е������գ�ֻ����Сʱ�ͷ���
	 * @param date ָ����ʽ�������ַ���
	 * @return
	 */
	public static String timeOlny(String date){
		if(date==null){
			return null;
		}
		int index = date.indexOf("��");
		return date.substring(index+1).trim();
	}
	
	
}
