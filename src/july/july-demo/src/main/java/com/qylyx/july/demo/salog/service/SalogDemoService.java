package com.qylyx.july.demo.salog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qylyx.july.demo.salog.entity.UserVo;
import com.qylyx.july.salog.annotation.Salog;

@Salog("用户管理")
@Service
public class SalogDemoService implements ISalogDemoService {
	
	@Autowired
	IUserService userService;

	@Salog("查询用户")
	@Override
	public UserVo demo1() {
		UserVo userVo = userService.findUser();
		return userVo;
	}
}
