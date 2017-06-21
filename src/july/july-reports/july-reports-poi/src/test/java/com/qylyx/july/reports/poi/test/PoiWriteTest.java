package com.qylyx.july.reports.poi.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.qylyx.july.reports.poi.entity.co.PoiWriteCo;
import com.qylyx.july.reports.poi.entity.setting.PoiWriteSetting;
import com.qylyx.july.reports.poi.entity.so.PoiWriteSo;
import com.qylyx.july.reports.poi.epis.main.XssfSupport;
import com.qylyx.july.reports.poi.epis.val.IPoiDiyCellVal;
import com.qylyx.july.reports.poi.mop.JulyPoiWrite;
import com.qylyx.july.reports.poi.test.entity.User;
import com.qylyx.july.utils.cold.stool.ColDesGroup;

public class PoiWriteTest {
	public static void main(String[] args) {
		List<User> list = getUserList(10);
		
		ColDesGroup colDesGroup = new ColDesGroup("ID, id, 8, 姓名, name, 14, 年龄, age, 5, 生日, birthday, 18"
				, 3);
		PoiWriteSo<User> poiWriteSo = new PoiWriteSo<User>(colDesGroup.getArr(0), colDesGroup.getArrInt(2)
				, colDesGroup.getArr(1));
		JulyPoiWrite<User> poiWrite = new JulyPoiWrite<User>()
				.setPoiWriteSo(poiWriteSo);
		PoiWriteCo<User> co = new PoiWriteCo<>("E://aa.xls", list);
		co.setPoiDiyCellVal(new IPoiDiyCellVal<User>() {
			@Override
			public Object diy(User obj, Object colVal, int dataIndex, int colIndex, Object hdStandard) {
				if (colIndex == 1) {
					return colVal + hdStandard.toString();
				} 
				return colVal;
			}
		});
		poiWrite.write(co);
		
		PoiWriteSo<User> poiWriteSo2 = new PoiWriteSo<User>(colDesGroup.getArr(0), colDesGroup.getArrInt(2)
				, colDesGroup.getArr(1));
		JulyPoiWrite<User> poiWrite2 = new JulyPoiWrite<User>()
				.setPoiWriteSo(poiWriteSo2);
		PoiWriteSetting setting2 = poiWrite2.getPoiWriteSetting();
		setting2.setJulyPoiSupport(new XssfSupport());
		poiWrite2.write("E://bb.xlsx", list);
		
		System.out.println("========== end ============");
	}
	
	public static List<User> getUserList(int count) {
		Random random = new Random();
		List<User> list = new ArrayList<User>();
		for (int i = 0; i < count; i++) {
			User user = new User();
			user.setId(i + 0L);
			user.setAge(random.nextInt(100));
			user.setName("张三" + random.nextInt(100));
			user.setBirthday(new Date());
			list.add(user);
		}
		return list;
	}
}
