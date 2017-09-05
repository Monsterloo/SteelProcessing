package com.sp.test.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sp.net.entity.Order;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.OrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/sp/config/spring-common.xml")
public class OrderTest {

	@Autowired
	private OrderService orderService;
	
	@Test
	public void insert(){
		Order order = new Order();
		order.setProjectName("测试项目111111");
		order.setCid("3ea284418c6e11e7b5cec85b76aeb963");
		order.setPid("da44a3ce912411e7a3ecc85b76aeb963");
		order.setAid("64c7f5eb87e311e7b5cec85b76aeb963");
		order.setCount(3);
		order.setDiameter(10.0);
		order.setPrice(2.33);
		Date date = new Date();
		order.setCreatetime(date);
		order.setModifytime(date);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 5);
		order.setDueDate(c.getTime());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(format.format(c.getTime()));
		long insert = orderService.insert(order);
		System.out.println(insert);
	}
	
	@Test
	public void getById(){
		Order byId = orderService.getById("a04ad630921311e7a3ecc85b76aeb963");
		System.out.println(byId);
	}
	
	@Test
	public void listPage(){
		PageBean listPage = orderService.listPage(new PageParam(0, 10), null);
		System.out.println(listPage);
	}
	
	@Test
	public void update(){
		orderService.completeProducted("a04ad630921311e7a3ecc85b76aeb963");
	}
	
	@Test
	public void stockOut(){
		
	}
}
