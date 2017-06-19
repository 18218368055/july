package com.qylyx.july.utils.array.regroup;

import java.lang.reflect.Method;
import java.util.List;

import com.qylyx.july.base.exception.JulyException;
import com.qylyx.july.utils.universal.etype.array.SortOrder;
import com.qylyx.july.utils.universal.intf.turn.val.ITurnVal;

/**
 * 排序工具类，值比较规则：null作为最小值，如果实现了Comparable接口，则以其compareTo()方法进行比较，没实现则比较toString()
 * @author Qiaoxin.Hong
 *
 */
public class SortUtils {
	
	/**
	 * 排序，升序，以当前对象直接排序，适用于如Integer,Long,String,Date等基础数据的排序
	 * @param list 需排序的集合
	 */
	public static void sort(List<?> list) {
		sort(list, SortOrder.ASC);
	}
	
	/**
	 * 排序，以当前对象直接排序，适用于如Integer,Long,String,Date等基础数据的排序
	 * @param list 需排序的集合
	 * @param order 排序的顺序
	 */
	public static <T> void sort(List<T> list, SortOrder order) {
		sort(list, o -> {
			return o;
		}, order);
	}
	
	/**
	 * 排序，升序，以对象的某属性的值进行排序
	 * @param list 需排序的集合
	 * @param field 要排序的字段
	 */
	public static <T> void sort(List<T> list, String field) {
		sort(list, field, SortOrder.ASC);
	}
	
	/**
	 * 排序，以对象的某属性的值进行排序
	 * @param list 需排序的集合
	 * @param field 要排序的字段
	 * @param order 排序的顺序
	 */
	public static <T> void sort(List<T> list, String field, SortOrder order) {
		sort(list, new SortDefaultTurnValField<T>(field), order);
	}
	
	/**
	 * 排序，升序，将集合的对象转换为要比较的值来进行排序
	 * @param list 需排序的集合
	 * @param turnVal 值转换器
	 */
	public static <T,R> void sort(List<T> list, ITurnVal<T, R> turnVal) {
		sort(list, turnVal, SortOrder.ASC);
	}
	
	/**
	 * 排序，将集合的对象转换为要比较的值来进行排序
	 * @param list 需排序的集合
	 * @param turnVal 值转换器
	 * @param order 排序的顺序
	 */
	public static <T,R> void sort(List<T> list, ITurnVal<T, R> turnVal, SortOrder order) {
		if (list != null && list.size() > 0) {
			//是否是降序
			final boolean isDesc = SortOrder.DESC.equals(order);
			list.sort((o1, o2) -> {
				R v1 = turnVal.turn(o1);
				R v2 = turnVal.turn(o2);
				int cr = compVal(v1, v2);
				//在降序时，将比较值颠倒
				if (isDesc && cr != 0)
					cr = -cr;
				return cr;
			});
		}
	}
	
	/**
	 * 比较两者值的大小，null作为最小值，如果实现了Comparable接口，则以其compareTo()方法进行比较，没实现则比较toString()
	 * @see java.lang.Comparable<T>
	 * @param v1 值1
	 * @param v2 值2
	 * @return 0：v1 = v2；  1：v1 < v2；  -1：v1 > v2
	 */
	private static int compVal(Object v1, Object v2) {
		if (v1 == null)
			return v2 == null ? 0 : 1;
		if (v2 == null)
			return -1;
		Class<?> clazz = v1.getClass();
		try {
			if (v1 instanceof Comparable) {
				Method method = clazz.getMethod("compareTo", clazz);
				return (int)method.invoke(v1, v2);
			} else {
				return v1.toString().compareTo(v2.toString());
			}
		} catch (Exception e) {
			throw new JulyException("排序比较值时异常！", e);
		}
	}
	
	
	
	
	
	
	
	
}
