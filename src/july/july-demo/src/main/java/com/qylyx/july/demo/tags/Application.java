package com.qylyx.july.demo.tags;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		Object[] objects = new Object[10];
		System.out.println(objects.getClass().getName());
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}
}
