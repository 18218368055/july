package com.qylyx.july.reports.poi.epis.style;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * POI导出样式设置
 *
 * @author Qiaoxin.Hong
 *
 */
public interface IPoiWriteStyle {
	
	/**
	 * 取得标题样式
	 * @param workbook
	 * @return
	 */
	public CellStyle getTitleCellStyle(Workbook workbook);
	
	/**
	 * 取得标题字体样式
	 * @param workbook
	 * @return
	 */
	public Font getTitleFont(Workbook workbook);
	
	/**
	 * 取得列标题样式
	 * @param workbook
	 * @return
	 */
	public CellStyle getColTitleCellStyle(Workbook workbook);
	
	/**
	 * 取得列标题字体样式
	 * @param workbook
	 * @return
	 */
	public Font getColTitleFont(Workbook workbook);
	
	/**
	 * 取得内容样式
	 * @param workbook
	 * @return
	 */
	public CellStyle getCellStyle(Workbook workbook);
	
	/**
	 * 取得内容字体样式
	 * @param workbook
	 * @return
	 */
	public Font getFont(Workbook workbook);
}
