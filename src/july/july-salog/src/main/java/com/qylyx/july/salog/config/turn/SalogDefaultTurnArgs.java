package com.qylyx.july.salog.config.turn;

import com.alibaba.fastjson.JSONObject;

/**
 * 以json格式转换入参或出参为字符串，用于打印日志
 * @author Qiaoxin.Hong
 *
 */
public class SalogDefaultTurnArgs implements ISalogTurnArgs {
	
	/**
	 * 以json格式转换入参或出参为字符串
	 */
	@Override
	public String turn(Object args) {
		return JSONObject.toJSONString(args);
	}

}
