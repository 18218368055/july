package com.qylyx.july.utils.tolerant;

import java.util.ArrayList;
import java.util.List;

/**
 * 提高代码容错率的一个工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class TolerantUtils {
	
	/**
	 * 在list为null时，返回一个空数量的集合
	 * @param list
	 * @return
	 */
	public static <T> List<T> defaultList(List<T> list) {
		return list == null ? new ArrayList<T>() : list;
	}
}
