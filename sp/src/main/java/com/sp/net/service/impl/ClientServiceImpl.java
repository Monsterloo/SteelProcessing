/*
 * Powered By [sp]
 */

package com.sp.net.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sp.net.dao.ClientDao;
import com.sp.net.entity.Client;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.ClientService;

@Component("clientService")
@Transactional
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	ClientDao clientDao;

	@Override
	public Client getById(String cId) {
		// TODO Auto-generated method stub
		Client client = clientDao.getById(cId);
		return client;
	}

	@Override
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		PageBean listPage = clientDao.listPage(pageParam, paramMap);
		return listPage;
	}

	@Override
	public long insert(Client client) {
		// TODO Auto-generated method stub
		long insert = clientDao.insert(client);
		return insert;
	}

	@Override
	public long update(Client client) {
		// TODO Auto-generated method stub
		long update = clientDao.updateIfNotNull(client);
		return update;
	}

	@Override
	public long deleteById(String cId) {
		// TODO Auto-generated method stub
		long deleteById = clientDao.deleteById(cId);
		return deleteById;
	}

	@Override
	public long batchDelete(List<String> cIds) {
		// TODO Auto-generated method stub
		long count = 0;
		for(String cId : cIds){
			count += clientDao.deleteById(cId);
		}
		return count;
	}
	
}
