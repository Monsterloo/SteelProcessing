package com.sp.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sp.net.entity.OrderDetail;
import com.sp.net.service.OrderDetailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/sp/config/spring-common.xml")
public class OrderDetailTest {

	@Autowired
	private OrderDetailService orderDetailService;
	
	@Test
	public void insert(){
		OrderDetail od = new OrderDetail();
		od.setOid("12312321");
		long insert = orderDetailService.insert(od);
		System.out.println(insert);
	}
	
	@Test
	public void dIdSub(){
		String dId = "000040-3";
		dId = dId.substring(0, dId.indexOf("-"));
		System.out.println(dId);
	}
}
