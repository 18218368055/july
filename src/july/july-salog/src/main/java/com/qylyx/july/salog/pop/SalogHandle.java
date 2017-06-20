package com.qylyx.july.salog.pop;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qylyx.july.salog.annotation.Salog;
import com.qylyx.july.salog.config.SalogSetting;
import com.qylyx.july.salog.config.etype.SalogUseType;
import com.qylyx.july.salog.entity.SalogHdAo;
import com.qylyx.july.salog.exception.JulySalogException;

/**
 * salog日志打印核心处理器
 *
 * @author Qiaoxin.Hong
 *
 */
public class SalogHandle {
	
	/**
	 * salog配置参数
	 */
	protected SalogSetting setting;
	
	/**
	 * 由自定义AOP转入此处理方法，进行日志打印的相关处理
	 * @param point
	 * @return
	 */
	public Object handle(ProceedingJoinPoint point) {
		try {
			SalogHdAo hdAo = new SalogHdAo();
			hdAo.setIn(SalogUseType.YES.equals(getSetting().getIn()));
			hdAo.setOut(SalogUseType.YES.equals(getSetting().getOut()));
			hdAo.setInArgs(SalogUseType.YES.equals(getSetting().getInArgs()));
			hdAo.setOurResult(SalogUseType.YES.equals(getSetting().getOutResult()));
			hdAo.setSegm(getSetting().getSegm());
			hdAo.setSuffix(getSetting().getSuffix());
			
			Class<?> entityClass = point.getTarget().getClass();
			if (entityClass.isAnnotationPresent(Salog.class)) {
				Salog salog = entityClass.getAnnotation(Salog.class);
				//解析封装实体上Salog注解的相关配置
				packSalogConfig(hdAo, salog);
			}
			Method method = ((MethodSignature) point.getSignature()).getMethod();
			//如果是接口的实现类，则取实现类的method
			if (!method.getClass().equals(entityClass))
				method = entityClass.getMethod(method.getName(), method.getParameterTypes());
			
			if (method.isAnnotationPresent(Salog.class)) {
				Salog salog = method.getAnnotation(Salog.class);
				//解析封装方法上Salog注解的相关配置
				packSalogConfig(hdAo, salog);
			}
			Logger logger = LoggerFactory.getLogger(point.getTarget().getClass());
			//打印方法开始
			if (hdAo.isIn()) {
				String show = hdAo.getPrefix() + hdAo.getSegm() + "开始";
				Object[] args = point.getArgs();
				//拼接入参日志
				if (hdAo.isInArgs() && args != null && args.length != 0) {
					show += hdAo.getSegm() + "入参：" + turn(args);
				}
				show += hdAo.getSuffix();
				logger.info(show);
			}
			
			Object result = point.proceed();
			
			//打印方法结束
			if (hdAo.isOut()) {
				String show = hdAo.getPrefix() + hdAo.getSegm() + "结束";
				//拼接出参日志
				if (hdAo.isOurResult() && !Void.TYPE.equals(method.getReturnType())) {
					show += hdAo.getSegm() + "出参：" + turn(result);
				}
				show += hdAo.getSuffix();
				logger.info(show);
			}
			return result;
		} catch (Throwable e) {
			throw new JulySalogException("日志自动打印操作异常！", e);
		}
		
	}
	
	/**
	 * 解析封装Salog注解中配置
	 * @param hdAo
	 * @param salog
	 */
	protected void packSalogConfig(SalogHdAo hdAo, Salog salog) {
		if (!SalogUseType.EMPTY.equals(salog.in()))
			hdAo.setIn(SalogUseType.YES.equals(salog.in()));
		if (!SalogUseType.EMPTY.equals(salog.out()))
			hdAo.setOut(SalogUseType.YES.equals(salog.out()));
		if (!SalogUseType.EMPTY.equals(salog.inArgs()))
			hdAo.setInArgs(SalogUseType.YES.equals(salog.inArgs()));
		if (!SalogUseType.EMPTY.equals(salog.outResult()))
			hdAo.setOurResult(SalogUseType.YES.equals(salog.outResult()));
		
		//salog对象先取prefix，没有设置再取value
		String salogPrefix = StringUtils.isNotBlank(salog.prefix()) ? salog.prefix() : salog.value();
		
		String segmItem = StringUtils.isNotBlank(hdAo.getPrefix()) 
				&& StringUtils.isNotBlank(salogPrefix) ? hdAo.getSegm() : "";
		hdAo.setPrefix(hdAo.getPrefix() + segmItem + salog.prefix() + salog.value());
	}
	
	/**
	 * 转换值为字符串
	 * @param args
	 * @return
	 */
	protected String turn(Object args) {
		return getSetting().getSalogTurnArgs().turn(args);
	}
	
	public SalogSetting getSetting() {
		if (setting == null) {
			setting = new SalogSetting();
		}
		return setting;
	}
	
	public void setSetting(SalogSetting setting) {
		this.setting = setting;
	}
}
