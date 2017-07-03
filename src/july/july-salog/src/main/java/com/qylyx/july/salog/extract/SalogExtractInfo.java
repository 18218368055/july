package com.qylyx.july.salog.extract;

import java.lang.reflect.Method;

import com.qylyx.july.salog.annotation.Salog;

/**
 * 提取Salog注解中的信息
 * @see com.qylyx.july.salog.annotation.Salog
 * 
 * @author Qiaoxin.Hong
 *
 */
public class SalogExtractInfo {
	/**
	 * 从类和方法中提取salog的前缀日志信息，自动跳过没加Salog注解的
	 * @see com.qylyx.july.salog.annotation.Salog
	 * 
	 * @param entityClass 类Class
	 * @param method 方法
	 * @return
	 */
	public static String getLogPrefix(Class<?> entityClass, Method method) {
		String logPrefix = "";
		boolean initLogPrefix = false;
		
		//类上是否有Salog注解
		if (entityClass != null && entityClass.isAnnotationPresent(Salog.class)) {
			Salog salog = entityClass.getAnnotation(Salog.class);
			logPrefix += salog.value();
			initLogPrefix = true;
		}
		
		//方法上是否有Salog注解
		if(method != null && method.isAnnotationPresent(Salog.class)) {
			Salog salog = method.getAnnotation(Salog.class);
			if (initLogPrefix)
				logPrefix += " - ";
			//解析封装方法上Salog注解的相关配置
			logPrefix += salog.value();
		}
		return logPrefix;
	}
}
