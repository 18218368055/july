package com.qylyx.july.reports.poi.epis.main;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 使用HSSF支持进行相关操作
 *
 * @author Qiaoxin.Hong
 *
 */
public class HssfSupport implements IJulyPoiSupport {

	@Override
	public Workbook getWorkbook() {
		return new HSSFWorkbook();
	}

}
