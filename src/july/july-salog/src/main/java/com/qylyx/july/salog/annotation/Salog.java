package com.qylyx.july.salog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.qylyx.july.salog.config.etype.SalogUseType;

/**
 * 配置则使用salog日志打印功能
 * @author Qiaoxin.Hong
 *
 */
@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Salog {
	/**
	 * 打印的日志前缀，如方法的作用
	 * @return
	 */
	String prefix() default "";
	
	/**
	 * 等价prefix
	 * @return
	 */
	String value() default "";
	
	/**
	 * 是否打印方法开始的日志，默认不配置
	 * @return
	 */
	SalogUseType in() default SalogUseType.EMPTY;
	
	/**
	 * 是否打印方法结束的日志，默认不配置
	 * @return
	 */
	SalogUseType out() default SalogUseType.EMPTY;
	
	/**
	 * 是否打印入参的日志，默认不配置
	 * @return
	 */
	SalogUseType inArgs() default SalogUseType.EMPTY;
	
	/**
	 * 是否打印出参的日志，默认不配置
	 * @return
	 */
	SalogUseType outResult() default SalogUseType.EMPTY;
	
	
}
