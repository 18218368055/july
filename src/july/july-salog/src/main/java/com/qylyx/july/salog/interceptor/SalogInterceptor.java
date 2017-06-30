package com.qylyx.july.salog.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.qylyx.july.salog.mop.SalogHandle;

/**
 * 日志自动打印的父拦截器，可以继承来快速配置拦截器，默认拦截实现Salog注解的方法，以aop环绕通知来进行日志打印处理
 * @see com.qylyx.july.salog.annotation.Salog
 * 
 * @author Qiaoxin.Hong
 *
 */
@Aspect
public class SalogInterceptor {
	
	/**
	 * Salog的注解字符串，主要用于配置aop的
	 * @see com.qylyx.july.salog.annotation.Salog
	 */
	protected final static String SALOG_CLASS = "com.qylyx.july.salog.annotation.Salog";
	
	/**
	 * salog日志打印核心处理器，子类可以对此处理器进行设置
	 */
	protected SalogHandle salogHandle = new SalogHandle();
	
	/**
	 * 切点，默认拦截实现Salog注解的方法
	 * @see com.qylyx.july.salog.annotation.Salog
	 */
	@Pointcut("@annotation(" + SALOG_CLASS + ")" )
	protected void salogMethod() {}
	
	/**
	 * aop环绕通知
	 * @param point
	 * @return
	 */
	@Around("salogMethod()")
	public Object around(ProceedingJoinPoint point) {
		return salogHandle.handle(point);
	}
	
	public SalogHandle getSalogHandle() {
		return salogHandle;
	}
	
	public void setSalogHandle(SalogHandle salogHandle) {
		this.salogHandle = salogHandle;
	}
}
