package com.qylyx.july.reports.poi.mop;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.qylyx.july.reports.poi.entity.co.PoiWriteCo;
import com.qylyx.july.reports.poi.entity.setting.PoiWriteSetting;
import com.qylyx.july.reports.poi.entity.so.PoiWriteSo;
import com.qylyx.july.reports.poi.epis.extra.IPoiExtra;
import com.qylyx.july.reports.poi.epis.style.IPoiWriteStyle;
import com.qylyx.july.reports.poi.epis.val.ICellValHd;
import com.qylyx.july.reports.poi.epis.val.IPoiDiyCellVal;
import com.qylyx.july.reports.poi.exception.JulyPoiException;
import com.qylyx.july.utils.check.ValidateUtils;

/**
 * POI导出excel
 *
 * @author Qiaoxin.Hong
 *
 */
public class JulyPoiWrite<T> {
	
	//************************ 外部设置 *****************************
	
	/**
	 * POI导出的整体参数配置容器
	 */
	protected PoiWriteSetting poiWriteSetting = new PoiWriteSetting();
	
	/**
	 * POI导出相关操作的参数配置
	 */
	protected PoiWriteSo<T> poiWriteSo;
	
	
	//************************ 内部使用 *****************************
	
	/**
	 * 缓存第一次解析数据时，提取出来的列样式
	 */
	protected Map<Integer, CellStyle> cellStyleMap;
	
	/**
	 * 导出excel
	 * @param file
	 * @param data
	 */
	public void write(File file, List<T> data) {
		PoiWriteCo<T> co = new PoiWriteCo<>(file, data);
		write(co);
	}
	
	/**
	 * 导出excel
	 * @param fileUrl
	 * @param data
	 */
	public void write(String fileUrl, List<T> data) {
		PoiWriteCo<T> co = new PoiWriteCo<>(fileUrl, data);
		write(co);
	}
	
	/**
	 * 导出excel
	 * @param co
	 */
	public void write(PoiWriteCo<T> co) {
		// 导出前的验证
		if (!validate(co))
			return;
		
		try {
			//根据不同支持操作不同的workbook
			Workbook workbook = poiWriteSetting.getJulyPoiSupport().getWorkbook();
			
			Sheet sheet = workbook.createSheet();
			//默认列宽度
			sheet.setDefaultColumnWidth(poiWriteSo.getColDefaultWidth());
			//自定义列宽度
			int[] colWidths = poiWriteSo.getColWidths();
			if (ValidateUtils.isNotEmpty(colWidths)) {
				for (int i = 0; i < colWidths.length; i++) {
					sheet.setColumnWidth(i, colWidths[i] * 256);
				}
			}
			
			//列标题集
			String[] colNames = poiWriteSo.getColNames();
			//导出样式
			IPoiWriteStyle poiWriteStyle = poiWriteSetting.getPoiWriteStyle();
			//取得当前单元格数据的调度器
			ICellValHd<T> cellValHd = poiWriteSo.getCellValHd();
			//某些单元格数据进行一些特殊的自定义处理
			IPoiDiyCellVal<T> poiDiyCellVal = co.getPoiDiyCellVal();
			//POI导出过程中的一些额外操作
			IPoiExtra<T> poiExtra = co.getPoiExtra();
			
			// 当前行下标的滚动标识
			int rowRollIndex = 0;
			
			// 标题的相关操作，如有设
			if (StringUtils.isNotBlank(co.getTitle())) {
				// 以列数量进行标题的合并单元格
				sheet.addMergedRegion(new CellRangeAddress(rowRollIndex, rowRollIndex, 0, colNames.length - 1));

				// 标题列的相关处理
				Row titleRow = sheet.createRow(rowRollIndex);
				Cell titleCell = titleRow.createCell(0);
				titleCell.setCellValue(co.getTitle());
				titleCell.setCellStyle(poiWriteStyle.getTitleCellStyle(workbook));

				// 行下标的滚动标识向前推一行
				rowRollIndex++;
			}
			
			// 列标题的相关操作
			Row colTitleRow = sheet.createRow(rowRollIndex);
			CellStyle colTitleCellStyle = poiWriteStyle.getColTitleCellStyle(workbook);
			for (int i = 0; i < colNames.length; i++) {
				Cell colTitleCell = colTitleRow.createCell(i);
				colTitleCell.setCellValue(colNames[i]);
				colTitleCell.setCellStyle(colTitleCellStyle);
			}
			rowRollIndex++; // 行下标的滚动标识向前推一行
			
			// 数据操作
			List<T> data = co.getData();
			if (ValidateUtils.isNotEmpty(data)) {
				//行循环
				for (int i = 0; i < data.size(); i++) {
					Row row = sheet.createRow(i + rowRollIndex);  //创建行
					T curItem = data.get(i);  //当前行数据
					
					//是否生成列样式，仅生成一次
					boolean initCellStyleMap = cellStyleMap == null;
					if (initCellStyleMap)
						cellStyleMap = new HashMap<Integer, CellStyle>();
					
					//列循环
					for (int j = 0; j < colNames.length; j++) {
						Cell cell = row.createCell(j);// 创建单元格
						
						//生成列样式，仅生成一次
						if (initCellStyleMap) {
							CellStyle cellStyle = poiWriteStyle.getCellStyle(workbook);
							cellStyleMap.put(j, cellStyle);
						}
						
						// 获取到单元格数据
						Object val = cellValHd == null ? curItem : cellValHd.get(curItem, i, j);;
						//某些单元格数据进行一些特殊的自定义处理
						if (poiDiyCellVal != null)
							val = poiDiyCellVal.diy(data.get(i), val, i, j, cellValHd.hdStandard(j));
						
						//根据数据类型绑入单元格中
						if (val == null) {
							cell.setCellValue(StringUtils.EMPTY);
						} else if (val instanceof Date) {
							cell.setCellValue((Date) val);
							
							//日期格式
							if (initCellStyleMap)
								cellStyleMap.get(j).setDataFormat(workbook.createDataFormat()
										.getFormat(poiWriteSo.getDateFormatPattern()));
						} else if (val instanceof Integer) {
							cell.setCellValue((Integer) val);
						} else if (val instanceof Double) {
							cell.setCellValue((Double) val);
						} else if (val instanceof Float) {
							cell.setCellValue((Float) val);
						} else if (val instanceof Long) {
							cell.setCellValue((Long) val);
						} else {
							cell.setCellValue(val.toString());
						}
						//列样式
						cell.setCellStyle(cellStyleMap.get(j));
						
						//额外处理cell，如有设
						poiExtra.extraCell(cell, i + rowRollIndex, i, data.get(i), j, cellValHd.hdStandard(j));
					}
					//额外处理row，如有设
					poiExtra.extraRow(row, i + rowRollIndex, i, data.get(i));
				}
			}
			
			//额外处理sheet，如有设
			poiExtra.extraSheet(sheet);
			//额外处理workbook，如有设
			poiExtra.extraWorkbook(workbook);
			
			//导出
			workbook.write(new FileOutputStream(co.getFile()));
		} catch (Exception e) {
			throw new JulyPoiException("POI导出excel异常！", e);
		}
	}
	
	/**
	 * 导出操作前的验证
	 * @return
	 */
	protected boolean validate(PoiWriteCo<T> co) {
		//数据方面的相关验证
		if (co == null)
			throw new JulyPoiException("PoiWriteCo不能为空！");
		if (co.getFile() == null)
			throw new JulyPoiException("导出的文件路径不能为空！");
		//整体参数配置
		if (poiWriteSetting == null)
			throw new JulyPoiException("整体参数配置PoiWriteSetting不能为空！");
		if (poiWriteSetting.getJulyPoiSupport() == null)
			throw new JulyPoiException("操作支持类型IJulyPoiSupport不能为空");
		if (poiWriteSetting.getPoiWriteStyle() == null)
			throw new JulyPoiException("POI导出样式IPoiWriteStyle不能为空");
		//相关操作的参数配置
		if (poiWriteSo == null)
			throw new JulyPoiException("整体参数配置PoiWriteSo不能为空！");
		if (ValidateUtils.isEmpty(poiWriteSo.getColNames()))
			throw new JulyPoiException("列标题集不能为空！");
		return true;
	}
	
	public JulyPoiWrite<T> setPoiWriteSetting(PoiWriteSetting poiWriteSetting) {
		this.poiWriteSetting = poiWriteSetting;
		return this;
	}
	
	public PoiWriteSetting getPoiWriteSetting() {
		return poiWriteSetting;
	}
	
	public JulyPoiWrite<T> setPoiWriteSo(PoiWriteSo<T> poiWriteSo) {
		this.poiWriteSo = poiWriteSo;
		return this;
	}
	
	public PoiWriteSo<T> getPoiWriteSo() {
		return poiWriteSo;
	}
	
}
