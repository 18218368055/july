package com.qylyx.july.reports.poi.epis.main;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * 控制使用POI的哪种支持进行相关操作，如HSSF，XSSF等
 *
 * @author Qiaoxin.Hong
 *
 */
public interface IJulyPoiSupport {
	
	/**
	 * 取得workbook
	 * @return
	 */
	public Workbook getWorkbook();
}
