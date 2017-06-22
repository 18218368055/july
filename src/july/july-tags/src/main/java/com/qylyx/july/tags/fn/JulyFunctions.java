package com.qylyx.july.tags.fn;

import java.util.Collection;

import com.qylyx.july.utils.check.ValidateUtils;

/**
 * july标签库Functions
 * 
 * @author Qiaoxin.Hong
 *
 */
public class JulyFunctions {
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
		return ValidateUtils.isEmpty(coll);
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
		return ValidateUtils.isNotEmpty(coll);
	}
}
