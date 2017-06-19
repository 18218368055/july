package com.qylyx.july.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期工具类
 * @author Qiaoxin.Hong
 *
 */
public class DateUtils {
	
	/**
	 * 日期格式转换器
	 */
	private static Map<String, SimpleDateFormat> formatterMap = new HashMap<String, SimpleDateFormat>();
	
	/** 日期格式化格式 - yyyy-MM-dd */
	public final static String PATTERN_DATE = "yyyy-MM-dd";
	/** 日期格式化格式 - yyyy-MM-dd HH:mm:ss */
	public final static String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
	/** 日期格式化格式 - HH:mm:ss */
	public final static String PATTERN_TIME = "HH:mm:ss";
	/** 日期格式化格式 - yyyy-MM-dd HH:mm */
	public final static String PATTERN_DATETIME_MM = "yyyy-MM-dd HH:mm";
	/** 日期格式化格式 - yyyy年MM月dd日 */
	public final static String PATTERN_CN_DATE = "yyyy年MM月dd日";
	/** 日期格式化格式 - yyyy年MM月dd日 HH时mm分ss秒 */
	public final static String PATTERN_CN_DATETIME = "yyyy年MM月dd日 HH时mm分ss秒";
	/** 日期格式化格式 - HH时mm分ss秒 */
	public final static String PATTERN_CN_TIME = "HH时mm分ss秒";
	
	
	/**
	 * 将日期转换为字符串
	 * @param date 日期
	 * @param pattern 格式字符串
	 * @return
	 */
	public static String format(Date date, String pattern) {
		return getFormatter(pattern).format(date);
	}
	
	/**
	 * 将字符串转换为日期
	 * @param dateStr 日期字符串
	 * @param pattern 格式字符串
	 * @return
	 */
	public static Date parse(String dateStr, String pattern) {
		try {
			return getFormatter(pattern).parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 将日期转换为字符串，格式：yyyy-MM-dd
	 * @param date 日期
	 * @return
	 */
	public static String formatDate(Date date) {
		return format(date, PATTERN_DATE);
	}
	
	/**
	 * 将日期转换为字符串，格式：yyyy-MM-dd HH:mm:ss
	 * @param date 日期
	 * @return
	 */
	public static String formatDateTime(Date date) {
		return format(date, PATTERN_DATETIME);
	}
	
	/**
	 * 将日期转换为字符串，格式：HH:mm:ss
	 * @param date 日期
	 * @return
	 */
	public static String formatTime(Date date) {
		return format(date, PATTERN_TIME);
	}
	
	/**
	 * 将字符串转换为日期，格式：yyyy-MM-dd
	 * @param dateStr 日期字符串
	 * @return
	 */
	public static Date parseDate(String dateStr) {
		return parse(dateStr, PATTERN_DATE);
	}
	
	/**
	 * 将字符串转换为日期，格式：yyyy-MM-dd HH:mm:ss
	 * @param dateStr 日期字符串
	 * @return
	 */
	public static Date parseDateTime(String dateStr) {
		return parse(dateStr, PATTERN_DATE);
	}
	
	/**
	 * 将字符串转换为日期，格式：HH:mm:ss
	 * @param dateStr 日期字符串
	 * @return
	 */
	public static Date parseTime(String dateStr) {
		return parse(dateStr, PATTERN_DATE);
	}
	
	
	/**
	 * 取得日期格式转换器
	 * @return
	 */
	public static SimpleDateFormat getFormatter(String pattern) {
		SimpleDateFormat formatter = formatterMap.get(pattern);
		if (formatter == null) {
			synchronized (DateUtils.class) {
				if (formatterMap.get(pattern) == null) {
					formatter = new SimpleDateFormat(pattern);
					formatterMap.put(pattern, formatter);
				}
			}
		}
		return formatter;
	}
}
