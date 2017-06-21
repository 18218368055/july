package com.qylyx.july.reports.poi.epis.val;

/**
 * 对某些单元格数据进行一些特殊的自定义处理，执行于内部列数据自动解析之后
 *
 * @author Qiaoxin.Hong
 *
 */
public interface IPoiDiyCellVal<T> {
	/**
	 * 取得当前单元格数据，对不需要处理的列，返回colVal
	 * @param obj 当前对象
	 * @param colVal 当前解析过的列数据，在不需要其它处理时，直接返回此值就行
	 * @param dataIndex 当前对象对应数据集体下标，不代表当前excel所在行数
	 * @param colIndex 当前excel所对应的列下标
	 * @param hdStandard 调度器的一个标准
	 * @return 最后的单元格数据
	 */
	public Object diy(T obj, Object colVal, int dataIndex, int colIndex, Object hdStandard);
}
