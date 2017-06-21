package com.qylyx.july.reports.poi.epis.val;

/**
 * 取得当前单元格数据的调度器
 *
 * @author Qiaoxin.Hong
 *
 */
public interface ICellValHd<T> {
	/**
	 * 取得当前单元格数据
	 * @param obj 当前对象
	 * @param dataIndex 当前对象对应数据集下标，不代表当前excel所在行数
	 * @param colIndex 当前excel所对应的列下标
	 * @return
	 */
	public Object get(T obj, int dataIndex, int colIndex);
	
	/**
	 * 提供当前列对应调度器的一个标准，在调度器使用过程中，可能有场景需要获取到当前列所对应的那个标准（标识），
	 * 利用这个标准来确定并做某些相关的动作，如属性自动解析时的属性
	 * @param colIndex 列下标
	 * @return
	 */
	public default Object hdStandard(int colIndex) {
		return null;
	}
}
