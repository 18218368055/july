package com.qylyx.july.demo.salog.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Test {
	@Pointcut("execution(* com.qylyx.july.demo.salog..*.*(..))")
	protected void method() {}
	
	@Around("method()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		System.out.println("======================point======================");
		return point.proceed();
	}
}
