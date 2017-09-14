package com.sp.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sp.net.dao.AdminDao;
import com.sp.net.entity.Admin;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.AdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/sp/config/spring-common.xml")
public class AdminTest {

	@Autowired
	AdminDao adminDao;
	
	@Autowired
	AdminService adminService;	
	
	@Test
	public void getBy(){
		Admin admin = adminService.getById("1");
		System.out.println(admin);
		/*List<Admin> listBy = adminDao.listBy(null);
		for(Admin admin : listBy){
			System.out.println(admin.getaRoleName());
		}*/
	}
	
	@Test
	public void delete(){
		long deleteById = adminService.deleteById("1");
		System.out.println(deleteById);
	}
	
	@Test
	public void update(){
		Admin admin = adminService.getById("1");
		admin.setAname("winna");
		admin.setAcontact("13600000000");
		long updateAdmin = adminService.update(admin);
		System.out.println(updateAdmin);
	}
	
	@Test
	public void insert(){
		Admin admin = new Admin();
		admin.setAaccount("test1");
		admin.setApwd("111111");
		admin.setArole("1");
		admin.setAcontact("18100000123");
		admin.setAname("testtt");
		admin.setAdept("testtt");
		long insertAdmin = adminService.insert(admin);
		System.out.println(insertAdmin);
	}
	
	@Test
	public void listBy(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("state", "1");
		PageParam pageParam = new PageParam(0, 10);
		PageBean listPage = adminService.listPage(pageParam, paramMap);
		System.out.println(listPage);
	}
	
	@Test
	public void fuzzyListPage(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("aname", "lu");
		PageBean fuzzyListPage = adminService.fuzzyListPage(new PageParam(0, 10), paramMap);
		System.out.println(fuzzyListPage);
	}
}
