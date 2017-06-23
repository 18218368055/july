package com.qylyx.july.demo.common.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.qylyx.july.demo.common.entity.User;

/**
 * 示例的数据工厂
 * 
 * @author Qiaoxin.Hong
 *
 */
public class DataFactory {
	private static Random random = new Random();
	
	public static User getUser() {
		User user = new User();
		user.setId(1001L);
		user.setName("路人甲");
		user.setAge(18);
		user.setBirthday(new Date());
		return user;
	}
	
	public static List<User> getUserList() {
		List<User> list = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setId(1000L + i);
			user.setName("路人甲" + random.nextInt(10));
			user.setAge(random.nextInt(100) + i);
			user.setBirthday(new Date());
			list.add(user);
		}
		return list;
	}
}
