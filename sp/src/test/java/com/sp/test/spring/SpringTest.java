package com.sp.test.spring;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	public static void main(String[] args) {
		/*ApplicationContext ctx = new ClassPathXmlApplicationContext("com/sp/config/spring-common.xml");
		 Object adminDao = ctx.getBean("adminDao");
		 System.out.println(adminDao);*/
		Date date = new Date();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String s = "Fri Sep 08 10:29:36 CST 2017";
		System.out.println(sdf.format(date));
		long time = 1505836800000l;
		date = new Date(time);
		System.out.println(sdf.format(date));
	}
}
