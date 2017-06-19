//package com.qyly.july.salog.interceptor;
//
//import java.lang.reflect.Method;
//
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.qyly.july.salog.annotation.Salog;
//import com.qyly.july.salog.config.SalogSetting;
//import com.qyly.july.salog.config.etype.SalogUseType;
//import com.qyly.july.salog.entity.SalogHdAo;
//
///**
// * salog核心拦截器，以spring aop实现，拦截并根据配置打印相关日志
// * @author Qiaoxin.Hong
// *
// */
//@Aspect
//public abstract class SalogInterceptor {
//	
//	/**
//	 * salog配置参数
//	 */
//	protected SalogSetting setting;
//	
//	/**
//	 * 默认切点，以Salog注解进行拦截
//	 */
//	@Pointcut("@annotation(com.qyly.july.salog.annotation.Salog)")
////	@Pointcut("execution(* com.qyly.july.dict.service.*.*(..))")
//	private void salogMethod() {}
//	
//	/**
//	 * 环绕通知，根据配置打印方法开始、结束的日志
//	 * @param point
//	 * @throws Throwable
//	 */
//	@Around("salogMethod()")
//	public Object around(ProceedingJoinPoint point) throws Throwable {
//		SalogHdAo hdAo = new SalogHdAo();
//		hdAo.setIn(SalogUseType.YES.equals(getSetting().getIn()));
//		hdAo.setOut(SalogUseType.YES.equals(getSetting().getOut()));
//		hdAo.setInArgs(SalogUseType.YES.equals(getSetting().getInArgs()));
//		hdAo.setOurResult(SalogUseType.YES.equals(getSetting().getOutResult()));
//		hdAo.setSegm(getSetting().getSegm());
//		hdAo.setSuffix(getSetting().getSuffix());
//		
//		Class<?> entityClass = point.getTarget().getClass();
//		if (entityClass.isAnnotationPresent(Salog.class)) {
//			Salog salog = entityClass.getAnnotation(Salog.class);
//			//解析封装实体上Salog注解的相关配置
//			packSalogConfig(hdAo, salog);
//		}
//		Method method = ((MethodSignature) point.getSignature()).getMethod();
//		if (method.isAnnotationPresent(Salog.class)) {
//			Salog salog = method.getAnnotation(Salog.class);
//			//解析封装方法上Salog注解的相关配置
//			packSalogConfig(hdAo, salog);
//		}
//		Logger logger = LoggerFactory.getLogger(point.getTarget().getClass());
//		//打印方法开始
//		if (hdAo.isIn()) {
//			String show = hdAo.getPrefix() + hdAo.getSegm() + "开始";
//			Object[] args = point.getArgs();
//			//拼接入参日志
//			if (hdAo.isInArgs() && args != null && args.length != 0) {
//				show += hdAo.getSegm() + "入参：" + turn(args);
//			}
//			show += hdAo.getSuffix();
//			logger.info(show);
//		}
//		
//		Object result = point.proceed();
//		
//		//打印方法结束
//		if (hdAo.isOut()) {
//			String show = hdAo.getPrefix() + hdAo.getSegm() + "结束";
//			//拼接出参日志
//			if (hdAo.isOurResult() && !Void.TYPE.equals(method.getReturnType())) {
//				show += hdAo.getSegm() + "出参：" + turn(result);
//			}
//			show += hdAo.getSuffix();
//			logger.info(show);
//		}
//		
//		return result;
//	}
//	
//	/**
//	 * 解析封装Salog注解中配置
//	 * @param hdAo
//	 * @param salog
//	 */
//	private void packSalogConfig(SalogHdAo hdAo, Salog salog) {
//		if (!SalogUseType.EMPTY.equals(salog.in()))
//			hdAo.setIn(SalogUseType.YES.equals(salog.in()));
//		if (!SalogUseType.EMPTY.equals(salog.out()))
//			hdAo.setOut(SalogUseType.YES.equals(salog.out()));
//		if (!SalogUseType.EMPTY.equals(salog.inArgs()))
//			hdAo.setInArgs(SalogUseType.YES.equals(salog.inArgs()));
//		if (!SalogUseType.EMPTY.equals(salog.outResult()))
//			hdAo.setOurResult(SalogUseType.YES.equals(salog.outResult()));
//		
//		String segmItem = StringUtils.isNotBlank(hdAo.getPrefix()) 
//				&& StringUtils.isNotBlank(salog.prefix()) ? hdAo.getSegm() : "";
//		hdAo.setPrefix(hdAo.getPrefix() + segmItem + salog.prefix());
//	}
//	
//	/**
//	 * 转换值为字符串
//	 * @param args
//	 * @return
//	 */
//	private String turn(Object args) {
//		return getSetting().getSalogTurnArgs().turn(args);
//	}
//	
//	public SalogSetting getSetting() {
//		if (setting == null) {
//			setting = new SalogSetting();
//		}
//		return setting;
//	}
//	
//	public void setSetting(SalogSetting setting) {
//		this.setting = setting;
//	}
//	
//	
//	
//	
//}
