package com.zhp.bean;

import java.io.Serializable;

public class SelectDate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8080L;
	private int year;
	private int monthOfYear;
	private int dayOfMonth;
	private int hourOfDay;
	private int minuteOfHour;

	public SelectDate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SelectDate(int year, int monthOfYear, int dayOfMonth, int hourOfDay,
			int minuteOfHour) {
		super();
		this.year = year;
		this.monthOfYear = monthOfYear;
		this.dayOfMonth = dayOfMonth;
		this.hourOfDay = hourOfDay;
		this.minuteOfHour = minuteOfHour;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonthOfYear() {
		return monthOfYear;
	}

	public void setMonthOfYear(int monthOfYear) {
		this.monthOfYear = monthOfYear;
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public int getHourOfDay() {
		return hourOfDay;
	}

	public void setHourOfDay(int hourOfDay) {
		this.hourOfDay = hourOfDay;
	}

	public int getMinuteOfHour() {
		return minuteOfHour;
	}

	public void setMinuteOfHour(int minuteOfHour) {
		this.minuteOfHour = minuteOfHour;
	}

	@Override
	public String toString() {
		return year + " 年 " + monthOfYear + " 月 " + dayOfMonth + " 日 " + " "
				+ hourOfDay + " 时 " + minuteOfHour + " 分";
	}

}
