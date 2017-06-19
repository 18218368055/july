package com.qylyx.july.utils.universal.intf.turn.val;

/**
 * 当循环中，某些数据不能满足当前需要时使用，用于将数据转换为可使用的数据
 * @author Qiaoxin.Hong
 *
 * @param <V> 转换前的数据
 * @param <R> 转换后的数据
 */
public interface ITurnEachVal<V, R> {

	/**
	 * 转换数据
	 * @param val 转换前的数据
	 * @param index 当前下标
	 * @return 转换后的数据
	 */
	public R turn(V val, int index);
}
