package com.sp.test.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sp.net.dao.ClientDao;
import com.sp.net.entity.Client;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.ClientService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/sp/config/spring-common.xml")
public class ClientTest {

	@Autowired
	ClientDao clientDao;
	
	@Autowired
	ClientService clientService;
	
	@Test
	public void insert(){
		Client client = new Client();
		client.setCname("test");
		client.setCcontact("020-123321123");
		client.setCaddress("where");
		client.setClegalperson("ttttt");
		client.setState("1");
		long insert = clientService.insert(client);
		System.out.println(insert);
		//clientService.insertClient(client);
	}
	
	@Test
	public void getBy(){
		Client clientById = (Client) clientService.getById("3ea284418c6e11e7b5cec85b76aeb963");
		System.out.println(clientById);
	}
	
	@Test
	public void listBy(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		PageParam pageParam = new PageParam(0, 10);
		PageBean listPage = clientService.listPage(pageParam, paramMap);
		System.out.println(listPage);
	}
	
	@Test
	public void bathDelete(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		PageParam pageParam = new PageParam(0, 10);
		PageBean listPage = clientService.listPage(pageParam, paramMap);
		List<Object> recordList = listPage.getRecordList();
		List<String> cIds = new ArrayList<String>();
		for(Object c : recordList){
			System.out.println(((Client)c).toString());
			cIds.add(((Client)c).getCid());
		}
		System.out.println(clientService.batchDelete(cIds));
	}
	
	@Test
	public void update(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		PageParam pageParam = new PageParam(0, 1);
		PageBean listPage = clientService.listPage(pageParam, paramMap);
		List<Object> recordList = listPage.getRecordList();
		Client c = null;
		if(recordList.size() > 0){
			c = (Client) recordList.get(0);
		}
		c.setCaddress("where is it");
		clientService.update(c);
	}
}
