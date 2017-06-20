package com.qylyx.july.demo.salog.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.qylyx.july.demo.salog.entity.UserVo;

@Service
public class UserService implements IUserService{
	
	@Override
	public UserVo findUser() {
		UserVo user = new UserVo();
		user.setId(101L);
		user.setName("路人甲");
		user.setAge(11);
		user.setBirthday(new Date());
		return user;
	}
}
