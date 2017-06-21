package com.qylyx.july.reports.poi.entity.setting;

import java.io.Serializable;

import com.qylyx.july.reports.poi.epis.main.HssfSupport;
import com.qylyx.july.reports.poi.epis.main.IJulyPoiSupport;
import com.qylyx.july.reports.poi.epis.main.XssfSupport;
import com.qylyx.july.reports.poi.epis.style.IPoiWriteStyle;
import com.qylyx.july.reports.poi.epis.style.PoiWriteDefaultStyle;
import com.qylyx.july.reports.poi.etype.PoiSupport;
import com.qylyx.july.reports.poi.exception.JulyPoiException;

/**
 * POI导出的整体参数配置容器
 *
 * @author Qiaoxin.Hong
 *
 */
public class PoiWriteSetting implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * POI的支持操作类型
	 */
	protected IJulyPoiSupport julyPoiSupport = new HssfSupport();
	
	/**
	 * POI导出样式
	 */
	protected IPoiWriteStyle poiWriteStyle = new PoiWriteDefaultStyle();
	
	public PoiWriteSetting setJulyPoiSupport(IJulyPoiSupport julyPoiSupport) {
		if (julyPoiSupport == null)
			throw new JulyPoiException("POI的支持操作类型IJulyPoiSupport不能为空！");
		this.julyPoiSupport = julyPoiSupport;
		return this;
	}
	
	public PoiWriteSetting setJulyPoiSupport2(PoiSupport poiSupport) {
		if (poiSupport == null)
			throw new JulyPoiException("POI的支持操作类型枚举PoiSupport不能为空！");
		switch (poiSupport) {
		case HSSF:
			setJulyPoiSupport(new HssfSupport());
			break;
		case XSSF:
			setJulyPoiSupport(new XssfSupport());
			break;
		}
		return this;
	}
	
	public IJulyPoiSupport getJulyPoiSupport() {
		return julyPoiSupport;
	}
	
	public PoiWriteSetting setPoiWriteStyle(IPoiWriteStyle poiWriteStyle) {
		this.poiWriteStyle = poiWriteStyle;
		return this;
	}
	
	public IPoiWriteStyle getPoiWriteStyle() {
		return poiWriteStyle;
	}
}
