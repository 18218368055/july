package com.qylyx.july.reports.poi.epis.main;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 使用XSSF支持进行相关操作
 *
 * @author Qiaoxin.Hong
 *
 */
public class XssfSupport implements IJulyPoiSupport {

	@Override
	public Workbook getWorkbook() {
		return new XSSFWorkbook();
	}

}
