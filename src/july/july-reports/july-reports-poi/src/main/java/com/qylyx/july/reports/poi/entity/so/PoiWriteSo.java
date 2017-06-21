package com.qylyx.july.reports.poi.entity.so;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.qylyx.july.reports.poi.epis.val.CellValPropertyHd;
import com.qylyx.july.reports.poi.epis.val.ICellValHd;
import com.qylyx.july.reports.poi.exception.JulyPoiException;
import com.qylyx.july.utils.check.ValidateUtils;

/**
 * POI导出相关操作的参数配置容器
 *
 * @author Qiaoxin.Hong
 *
 */
public class PoiWriteSo<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 默认列宽度
	 */
	protected int colDefaultWidth = 8;
	
	/**
	 * excel列宽度集，默认乘256
	 */
	protected int[] colWidths;
	
	/**
	 * excel列标题集
	 */
	protected String[] colNames;
	
	/**
	 * 取得当前单元格数据的调度器，选设，默认直接取当前对象
	 */
	protected ICellValHd<T> cellValHd;
	
	/**
	 * 日期格式，选设，默认yyyy-MM-dd HH:mm:ss
	 */
	protected String dateFormatPattern = "yyyy-MM-dd HH:mm:ss";
	
	
	
	public PoiWriteSo() {
		super();
	}

	public PoiWriteSo(String[] colNames) {
		super();
		this.colNames = colNames;
	}
	
	public PoiWriteSo(String[] colNames, int[] colWidths) {
		super();
		this.colNames = colNames;
		this.colWidths = colWidths;
	}
	
	public PoiWriteSo(String[] colNames, int[] colWidths, ICellValHd<T> cellValHd) {
		super();
		this.colWidths = colWidths;
		this.colNames = colNames;
		this.cellValHd = cellValHd;
	}
	
	public PoiWriteSo(String[] colNames, ICellValHd<T> cellValHd) {
		super();
		this.colNames = colNames;
		this.cellValHd = cellValHd;
	}
	
	public PoiWriteSo(String[] colNames, int[] colWidths, String[] colProperties) {
		super();
		this.colWidths = colWidths;
		this.colNames = colNames;
		setCellValHd(colProperties);
	}
	
	public PoiWriteSo(String[] colNames, String[] colProperties) {
		super();
		this.colNames = colNames;
		setCellValHd(colProperties);
	}

	public int getColDefaultWidth() {
		return colDefaultWidth;
	}
	

	public PoiWriteSo<T> setColDefaultWidth(int colDefaultWidth) {
		if (colDefaultWidth < 0)
			throw new JulyPoiException("默认列宽度不能低于0！");
		this.colDefaultWidth = colDefaultWidth;
		return this;
	}
	
	public int[] getColWidths() {
		return colWidths;
	}

	public PoiWriteSo<T> setColWidths(int[] colWidths) {
		this.colWidths = colWidths;
		return this;
	}
	
	public PoiWriteSo<T> setColNames(String[] colNames) {
		this.colNames = colNames;
		return this;
	}
	
	public String[] getColNames() {
		return colNames;
	}
	
	public PoiWriteSo<T> setCellValHd(ICellValHd<T> cellValHd) {
		this.cellValHd = cellValHd;
		return this;
	}
	
	/**
	 * 对象属性集，会往cellValHd注入一个以属性解析获取当前单元格数据的调度器
	 * @param colProperties 对象属性集
	 * @return
	 * 
	 * @see com.qylyx.july.reports.poi.epis.val.CellValPropertyHd<T>
	 */
	public PoiWriteSo<T> setCellValHd(String[] colProperties) {
		if (ValidateUtils.isEmpty(colProperties))
			throw new JulyPoiException("对象属性集不能为空！");
		this.cellValHd = new CellValPropertyHd<>(colProperties);
		return this;
	}
	
	public ICellValHd<T> getCellValHd() {
		return cellValHd;
	}
	
	public PoiWriteSo<T> setDateFormatPattern(String dateFormatPattern) {
		if (StringUtils.isBlank(dateFormatPattern)) 
			throw new JulyPoiException("日期格式不能为空！");
		this.dateFormatPattern = dateFormatPattern;
		return this;
	}
	
	public String getDateFormatPattern() {
		return dateFormatPattern;
	}
}
