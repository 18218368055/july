package com.qylyx.july.utils.array.regroup;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import com.qylyx.july.base.exception.JulyException;
import com.qylyx.july.utils.universal.intf.turn.val.ITurnVal;

/**
 * 排序用，用于取对象的某属性的值
 * @author Qiaoxin.Hong
 *
 * @param <V> 要取其属性值的方法
 */
public class SortDefaultTurnValField<V> implements ITurnVal<V, Object> {
	/**
	 * 对象的某字段名
	 */
	private String field;
	
	/**
	 * 读取字段值的方法
	 */
	private Method readMethod = null;
	
	public SortDefaultTurnValField() {
		super();
	}
	
	public SortDefaultTurnValField(String field) {
		super();
		this.field = field;
	}

	@Override
	public Object turn(V val) {
		Object r = null;
		if (val != null) {
			try {
				if (readMethod == null) {
					synchronized (this) {
						if (readMethod == null) {
							PropertyDescriptor pd = new PropertyDescriptor(field, val.getClass());
							readMethod = pd.getReadMethod();
						}
					}
				}
				r = readMethod.invoke(val);
			} catch (Exception e) {
				throw new JulyException("排序根据对象属性转换值时异常！", e);
			}

		}
		return r;
	}

	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}



}
