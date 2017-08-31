package com.sp.test.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/sp/config/spring-common.xml");
		 Object adminDao = ctx.getBean("adminDao");
		 System.out.println(adminDao);
	}
}
