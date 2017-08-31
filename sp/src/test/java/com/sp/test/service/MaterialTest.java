package com.sp.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sp.net.dao.MaterialDao;
import com.sp.net.entity.Material;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.MaterialService;

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
		m.setMname("tset1");
		m.setMcategory(1);
		m.setMminCount(123);
		m.setMexistCount(321);
		m.setMdiameter((double) 100);
		m.setMlength((double) 10);
		m.setMweight((double) 101);
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
}
