package com.qylyx.july.utils.check;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

/**
 * 验证工具类
 * @author Qiaoxin.Hong
 *
 */
public class ValidateUtils {
	/**
	 * 是否为空
	 * <pre>
	 * null    =>  true
	 * coll.size() == 0    =>  true
	 * </pre>
	 * @param coll 集合
	 * @return true：为空   false：不为空
	 */
	public static boolean isEmpty(Collection<?> coll) {
		return coll == null || coll.size() == 0;
	}
	
	/**
	 * 是否不为空
	 * <pre>
	 * null    =>  false
	 * coll.size() == 0    =>  false
	 * </pre>
	 * @param coll 集合
	 * @return true：不为空   false：为空
	 */
	public static boolean isNotEmpty(Collection<?> coll) {
		return !isEmpty(coll);
	}
	
	/**
	 * 是否为空
	 * <pre>
	 * null    =>  true
	 * arr.length == 0    =>  true
	 * </pre>
	 * @param coll 数组
	 * @return true：为空   false：不为空
	 */
	public static boolean isEmpty(Object[] arr) {
		return arr == null || arr.length ==0;
	}
	
	/**
	 * 是否不为空
	 * <pre>
	 * null    =>  false
	 * arr.length == 0    =>  false
	 * </pre>
	 * @param coll 数组
	 * @return true：不为空   false：为空
	 */
	public static boolean isNotEmpty(Object[] arr) {
		return isEmpty(arr);
	}
	
	/**
	 * 是否为空，只要有一元素为空
	 * <pre>
	 * null    =>    true
	 * ""      =>    true
	 * </pre>
	 * @param arr
	 * @return
	 */
	public static boolean isEmpty(String...arr) {
		for (String str : arr) {
			if (StringUtils.isEmpty(str))
				return true;
		}
		return false;
	}
	
	/**
	 * 是否不为空，只要有一元素为空
	 * <pre>
	 * null    =>    false
	 * ""      =>    false
	 * </pre>
	 * @param arr
	 * @return
	 */
	public static boolean isNotEmpty(String...arr) {
		return isEmpty(arr);
	}
	
	/**
	 * 是否为空
	 * <pre>
	 * null    =>  true
	 * arr.length == 0    =>  true
	 * </pre>
	 * @param coll 数组
	 * @return true：为空   false：不为空
	 */
	public static boolean isEmpty(char[] arr) {
		return arr == null || arr.length ==0;
	}
	
	/**
	 * 是否不为空
	 * <pre>
	 * null    =>  false
	 * arr.length == 0    =>  false
	 * </pre>
	 * @param coll 数组
	 * @return true：不为空   false：为空
	 */
	public static boolean isNotEmpty(char[] arr) {
		return isEmpty(arr);
	}
}
