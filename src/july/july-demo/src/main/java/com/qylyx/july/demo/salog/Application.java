package com.qylyx.july.demo.salog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.qylyx.july.salog.mop.SalogHandle;

/**
 * salog日志自动打印例子
 *
 * @author Qiaoxin.Hong
 *
 */
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	/**
	 * salog日志自动打印核心处理器
	 * @return
	 */
	@Bean
	public SalogHandle getSalogHandle() {
		return new SalogHandle();
	}
}
