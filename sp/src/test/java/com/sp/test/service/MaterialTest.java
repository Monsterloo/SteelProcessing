package com.sp.test.service;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sp.net.dao.MaterialDao;
import com.sp.net.entity.Material;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.MaterialService;
import com.sp.net.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/sp/config/spring-common.xml")
public class MaterialTest {
	
	@Autowired
	MaterialDao materialDao;
	
	@Autowired
	MaterialService materialSerivce;

	@Test
	public void insert(){
		Material m = new Material();
		m.setMname("测试材料3");
		m.setMcategory(0);
		m.setMminCount(88);
		m.setMexistCount(8889);
		m.setMdiameter((double) 888);
		m.setMlength((double) 88);
		m.setMweight((double) 8);
		m.setState("1");
		materialSerivce.insert(m);
	}
	
	@Test
	public void listPage(){
		materialSerivce.listPage(new PageParam(0, 10), null);
	}
	
	@Test
	public void getById(){
		Material m = (Material) materialSerivce.getById("f3cad0698d6311e7b5cec85b76aeb963");
		System.out.println(m);
	}
	
	@Test
	public void update(){
		Material m = (Material) materialSerivce.getById("f3cad0698d6311e7b5cec85b76aeb963");
		m.setMminCount(111);
		m.setState("1");
		materialSerivce.update(m);
	}
	
	@Test
	public void delete(){
		materialSerivce.deleteById("f3cad0698d6311e7b5cec85b76aeb963");
	}
	
	@Test
	public void getTimeStampStr(){
		String time = "2017-09-14 14:47:22";
		try {
			Date parse = DateUtil.LONG_DATE_FORMAT.parse(time);
			System.out.println(parse);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
