package com.qylyx.july.utils.universal.intf.turn.val;

/**
 * 当某些数据不能满足当前需要时使用，用于将数据转换为可使用的数据
 * @author Qiaoxin.Hong
 *
 * @param <V> 转换前的数据
 * @param <R> 转换后的数据
 */
public interface ITurnVal<V, R> {

	/**
	 * 转换数据
	 * @param val 转换前的数据
	 * @return 转换后的数据
	 */
	public R turn(V val);
}
