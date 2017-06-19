package com.qylyx.july.salog.config;

import java.io.Serializable;

import com.qylyx.july.salog.config.etype.SalogUseType;
import com.qylyx.july.salog.config.turn.ISalogTurnArgs;
import com.qylyx.july.salog.config.turn.SalogDefaultTurnArgs;

/**
 * salog配置参数容器
 * @author Qiaoxin.Hong
 *
 */
public class SalogSetting implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 是否打印方法开始的日志，默认打印
	 */
	protected SalogUseType in = SalogUseType.YES;
	
	/**
	 * 是否打印方法结束的日志，默认打印
	 */
	protected SalogUseType out = SalogUseType.YES;
	
	/**
	 * 是否打印入参的日志，默认打印
	 */
	protected SalogUseType inArgs = SalogUseType.YES;
	
	/**
	 * 是否打印出参的日志，默认打印
	 */
	protected SalogUseType outResult = SalogUseType.YES;
	
	/**
	 * 转换入参或出参为字符串的方式，默认转换为json格式
	 */
	protected ISalogTurnArgs salogTurnArgs;
	
	/**
	 * 分割符，默认" - "
	 */
	protected String segm = " - ";
	
	/**
	 * 日志后缀，默认"！"
	 */
	protected String suffix = "！";
	

	public ISalogTurnArgs getSalogTurnArgs() {
		if (salogTurnArgs == null) {
			salogTurnArgs = new SalogDefaultTurnArgs();
		}
		return salogTurnArgs;
	}
	
	
	public String getSegm() {
		return segm;
	}

	public void setSegm(String segm) {
		this.segm = segm;
	}

	public void setSalogTurnArgs(ISalogTurnArgs salogTurnArgs) {
		this.salogTurnArgs = salogTurnArgs;
	}
	
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public String getSuffix() {
		return suffix;
	}

	public SalogUseType getIn() {
		return in;
	}

	public void setIn(SalogUseType in) {
		this.in = in;
	}

	public SalogUseType getOut() {
		return out;
	}

	public void setOut(SalogUseType out) {
		this.out = out;
	}

	public SalogUseType getInArgs() {
		return inArgs;
	}

	public void setInArgs(SalogUseType inArgs) {
		this.inArgs = inArgs;
	}

	public SalogUseType getOutResult() {
		return outResult;
	}

	public void setOutResult(SalogUseType outResult) {
		this.outResult = outResult;
	}
}
