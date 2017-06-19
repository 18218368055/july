package com.qylyx.july.salog.config.turn;

/**
 * 转换入参或出参为字符串，用于打印日志
 * @author Qiaoxin.Hong
 *
 */
public interface ISalogTurnArgs {
	
	/**
	 * 转换值为字符串
	 * @param args
	 * @return
	 */
	public String turn(Object args);
}
