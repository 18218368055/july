package com.qylyx.july.salog.entity;

import java.io.Serializable;

/**
 * 记录salog配置的参数解析后的数据
 * @author Qiaoxin.Hong
 *
 */
public class SalogHdAo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 是否打印方法开始的日志
	 */
	protected boolean in;
	
	/**
	 * 是否打印方法结束的日志
	 */
	protected boolean out;
	
	/**
	 * 是否打印入参的日志
	 */
	protected boolean inArgs;
	
	/**
	 * 是否打印出参的日志
	 */
	protected boolean ourResult;
	
	/**
	 * 日志前缀
	 */
	protected String prefix = "";
	
	/**
	 * 日志后缀
	 */
	protected String suffix = "";
	
	/**
	 * 分割符
	 */
	protected String segm = "";

	public boolean isIn() {
		return in;
	}

	public void setIn(boolean in) {
		this.in = in;
	}

	public boolean isOut() {
		return out;
	}

	public void setOut(boolean out) {
		this.out = out;
	}

	public boolean isInArgs() {
		return inArgs;
	}

	public void setInArgs(boolean inArgs) {
		this.inArgs = inArgs;
	}

	public boolean isOurResult() {
		return ourResult;
	}

	public void setOurResult(boolean ourResult) {
		this.ourResult = ourResult;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getSegm() {
		return segm;
	}

	public void setSegm(String segm) {
		this.segm = segm;
	}

	@Override
	public String toString() {
		return "SalogHdAo [in=" + in + ", out=" + out + ", inArgs=" + inArgs + ", ourResult=" + ourResult + ", prefix="
				+ prefix + ", suffix=" + suffix + ", segm=" + segm + "]";
	}
	
	
	
}
