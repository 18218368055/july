package com.qylyx.july.reports.poi.epis.extra;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 在整个POI的操作，workbook -> sheet -> row -> cell过程中，可额外进行的一些操作
 *
 * @author Qiaoxin.Hong
 *
 */
public interface IPoiExtra<T> {
	/**
	 * workbook的额外处理
	 * @param workbook Workbook
	 */
	public default void extraWorkbook(Workbook workbook) {

	}

	/**
	 * sheet的额外处理
	 * @param sheet Sheet
	 */
	public default void extraSheet(Sheet sheet) {

	}
	
	/**
	 * row的额外处理
	 * @param row Row
	 * @param rowIndex 行下标，针对excel的
	 * @param dataRowIndex 数据行下标
	 * @param obj 行数据
	 */
	public default void extraRow(Row row, int rowIndex, int dataRowIndex, T obj) {
		
	}
	
	/**
	 * cell的额外处理
	 * @param cell
	 * @param rowIndex 行下标，针对excel的
	 * @param dataRowIndex 数据行下标
	 * @param obj 行数据
	 * @param cellIndex 列下标，excel和数据的
	 * @param hdStandard 调度器的一个标准
	 */
	public default void extraCell(Cell cell, int rowIndex, int dataRowIndex, T obj, int cellIndex, Object hdStandard) {
		
	}
}
