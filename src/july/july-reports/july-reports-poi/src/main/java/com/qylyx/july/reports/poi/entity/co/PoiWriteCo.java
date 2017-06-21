package com.qylyx.july.reports.poi.entity.co;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import com.qylyx.july.reports.poi.epis.extra.IPoiExtra;
import com.qylyx.july.reports.poi.epis.val.IPoiDiyCellVal;

/**
 * POI导出时的数据方面的入参对象
 *
 * @author Qiaoxin.Hong
 *
 * @param <T> 数据类型
 */
public class PoiWriteCo<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 导出的文件路径，必传
	 */
	protected File file;
	
	/**
	 * 导出的数据
	 */
	protected List<T> data;
	
	/**
	 * excel文件标题，可选
	 */
	protected String title;
	
	/**
	 * 某些单元格数据进行一些特殊的自定义处理，可选
	 */
	protected IPoiDiyCellVal<T> poiDiyCellVal;
	
	/**
	 * POI导出过程中的一些额外操作，选设
	 */
	protected IPoiExtra<T> poiExtra = new IPoiExtra<T>(){};

	public PoiWriteCo() {
		super();
	}

	public PoiWriteCo(File file, List<T> data) {
		super();
		this.file = file;
		this.data = data;
	}
	
	public PoiWriteCo(String fileUrl, List<T> data) {
		super();
		setFile(new File(fileUrl));
		this.data = data;
	}

	public PoiWriteCo(File file, List<T> data, String title) {
		super();
		this.file = file;
		this.data = data;
		this.title = title;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public PoiWriteCo<T> setPoiDiyCellVal(IPoiDiyCellVal<T> poiDiyCellVal) {
		this.poiDiyCellVal = poiDiyCellVal;
		return this;
	}
	
	public IPoiDiyCellVal<T> getPoiDiyCellVal() {
		return poiDiyCellVal;
	}
	
	public PoiWriteCo<T> setPoiExtra(IPoiExtra<T> poiExtra) {
		this.poiExtra = poiExtra;
		return this;
	}
	
	public IPoiExtra<T> getPoiExtra() {
		return poiExtra;
	}
}
