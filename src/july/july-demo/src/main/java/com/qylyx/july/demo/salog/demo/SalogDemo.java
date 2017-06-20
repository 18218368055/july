package com.qylyx.july.demo.salog.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qylyx.july.demo.salog.Application;
import com.qylyx.july.demo.salog.service.ISalogDemoService;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SalogDemo {
	
	@Autowired
	private ISalogDemoService salogDemoService;
	
	@Test
	public void test() {
		salogDemoService.demo1();
	}
}
