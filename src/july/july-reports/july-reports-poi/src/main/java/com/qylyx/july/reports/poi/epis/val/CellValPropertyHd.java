package com.qylyx.july.reports.poi.epis.val;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.qylyx.july.reports.poi.exception.JulyPoiException;

/**
 * 以数据对象属性通过反射取得当前单元格数据的调度器
 *
 * @author Qiaoxin.Hong
 *
 */
public class CellValPropertyHd<T> implements ICellValHd<T> {
	
	/**
	 * 属性集
	 */
	protected String[] properties;
	
	/**
	 * 缓存属性解析后的method
	 */
	protected Map<String, Method> propertyMethodMap = new HashMap<String, Method>();
	
	/**
	 * 以数据对象属性通过反射取得当前单元格数据的调度器
	 * @param properties
	 */
	public CellValPropertyHd(String[] properties) {
		this.properties = properties;
	}
	
	/**
	 * 取得当前单元格数据
	 * @param obj 当前对象
	 * @param dataIndex 当前对象对应数据集下标，不代表当前excel所在行数
	 * @param colIndex 当前excel所对应的列下标
	 * @return
	 */
	@Override
	public Object get(T obj, int dataIndex, int colIndex) {
		String property = properties[colIndex];
		try {
			Method method = propertyMethodMap.get(property);
			//method不存在，生成method
			if (method == null) {
				PropertyDescriptor pdes = new PropertyDescriptor(property, obj.getClass());
				method = pdes.getReadMethod();
				propertyMethodMap.put(property, method);
			}
			return method.invoke(obj);
		} catch (Exception e) {
			throw new JulyPoiException("以属性通过反射取得数据异常，异常属性：" + property);
		}
	}
	
	/**
	 * 提供当前列的属性
	 */
	@Override
	public Object hdStandard(int colIndex) {
		return properties[colIndex];
	}
	
	public String[] getProperties() {
		return properties;
	}
	
	public void setProperties(String[] properties) {
		this.properties = properties;
	}
	
}
