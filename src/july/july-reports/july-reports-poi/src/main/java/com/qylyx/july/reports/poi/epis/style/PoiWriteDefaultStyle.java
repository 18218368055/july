package com.qylyx.july.reports.poi.epis.style;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * POI导出默认样式
 *
 * @author Qiaoxin.Hong
 *
 */
public class PoiWriteDefaultStyle implements IPoiWriteStyle {

	@Override
	public CellStyle getTitleCellStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();
		style.setFont(getTitleFont(workbook));  //设置标题字体样式
		style.setAlignment(HorizontalAlignment.CENTER);  //居中
//		style.setBorderTop(BorderStyle.THIN);
//		style.setBorderBottom(BorderStyle.THIN);
//		style.setBorderLeft(BorderStyle.THIN);
//		style.setBorderRight(BorderStyle.THIN);
		return style;
	}

	@Override
	public Font getTitleFont(Workbook workbook) {
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 14);  //设置字体大小
		font.setBold(true);  //粗体显示
		return font;
	}

	@Override
	public CellStyle getColTitleCellStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();
		style.setFont(getColTitleFont(workbook));  //设置标题字体样式
		style.setAlignment(HorizontalAlignment.CENTER);  //居中
//		style.setBorderTop(BorderStyle.THIN);
//		style.setBorderBottom(BorderStyle.THIN);
//		style.setBorderLeft(BorderStyle.THIN);
//		style.setBorderRight(BorderStyle.THIN);
		return style;
	}

	@Override
	public Font getColTitleFont(Workbook workbook) {
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 10);  //设置字体大小
		font.setBold(true);  //粗体显示
		return font;
	}

	@Override
	public CellStyle getCellStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();
		style.setFont(getFont(workbook));  //设置内容字体样式
		return style;
	}

	@Override
	public Font getFont(Workbook workbook) {
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 10);  //设置字体大小
		return font;
	}

}
