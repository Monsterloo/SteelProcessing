package com.sp.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sp.net.entity.SteelShape;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.SteelShapeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/sp/config/spring-common.xml")
public class SteelShapeTest {

	@Autowired
	SteelShapeService steelShapeService;
	
	@Test
	public void insert(){
		String [] sIds = {"000010","000011","000012","000013","000020","000021","000030","000031","000032","000035","000040","000041"};
		for(int i=0;i<sIds.length;++i){
			SteelShape ss = new SteelShape();
			ss.setSid(sIds[i]);
			ss.setImageUrl("../assets/addimg/"+sIds[i]+".jpg");
			ss.setState("1");
			steelShapeService.insert(ss);
		}
	}
	
	@Test
	public void listPage(){
		steelShapeService.listPage(new PageParam(0, 100), null);
	}
}
