package com.common.date.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public final String DATE_PATTERN_LONG = "yyyy-MM-dd HH:mm:ss";
	public final String DATE_PATTERN_SHORT = "yyyy-MM-dd";
	public final String[] MONTH = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public String dateToString(Date date, String pattern) {
		SimpleDateFormat dateFormet = new SimpleDateFormat(pattern);
		if (date != null) {
			return dateFormet.format(date);
		}
		return null;
	}

	/**
	 * String 转 Date
	 * 
	 * @param strDate
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public Date stringToDate(String strDate, String pattern) throws ParseException {
		if (strDate != null) {

			SimpleDateFormat dateFormet = new SimpleDateFormat(pattern);
			return dateFormet.parse(strDate);
		}
		return null;
	}

	/**
	 * 通过spike获取指定日期,此方法按天计算
	 * 
	 * @param date
	 * @param spike >0 往后取 <0 往前取
	 * @return Date
	 */
	public Date getDateBySpike(Date date, int spike) {
		// 初始化一个日历
		Calendar calendar = Calendar.getInstance();
		// 指定日期时间
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, spike);
		date = calendar.getTime();
		return date;
	}
}
