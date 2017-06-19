package com.qylyx.july.salog.config.etype;

/**
 * 是否使用的配置类型
 * @author Qiaoxin.Hong
 *
 */
public enum SalogUseType {
	
	/**
	 * 不配置，即不会进行相关处理
	 */
	EMPTY("0"),
	
	/**
	 * 使用 
	 */
	YES("1"),
	
	/**
	 * 不使用
	 */
	NO("2");
	
	/**
	 * 配置代码
	 */
	private String code;
	
	private SalogUseType(String code) {
		this.code = code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
}
