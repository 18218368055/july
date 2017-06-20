package com.qylyx.july.demo.salog.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qylyx.july.salog.pop.SalogHandle;

/**
 * 配置日志自动打印的拦截器
 *
 * @author Qiaoxin.Hong
 *
 */
@Aspect
@Component
public class SalogInterceptor {
	
	@Autowired
	SalogHandle salogHandle;
	
	@Pointcut("@annotation(com.qylyx.july.salog.annotation.Salog)")
	private void salogMethod() {}
	
	/**
	 * aop环绕通知
	 * @param point
	 * @return
	 */
	@Around("salogMethod()")
	public Object around(ProceedingJoinPoint point) {
		return salogHandle.handle(point);
	}
}
