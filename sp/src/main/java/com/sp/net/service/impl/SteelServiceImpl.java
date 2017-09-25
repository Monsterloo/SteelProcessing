/*
 * Powered By [sp]
 */


package com.sp.net.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sp.net.dao.SteelDao;
import com.sp.net.entity.Steel;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.SteelService;

@Component("steelShapeService")
@Transactional(rollbackFor=Exception.class)
public class SteelServiceImpl implements SteelService{

	@Autowired
	SteelDao steelShapeDao;
	
	@Override
	public Steel getById(String id) {
		// TODO Auto-generated method stub
		Steel ss = steelShapeDao.getById(id);
		return ss;
	}

	@Override
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		PageBean listPage = steelShapeDao.listPage(pageParam, paramMap);
		return listPage;
	}

	@Override
	public long insert(Steel t) {
		// TODO Auto-generated method stub
		t.setState("1");
		return steelShapeDao.insert(t);
	}

	@Override
	public long update(Steel t) {
		// TODO Auto-generated method stub
		return steelShapeDao.updateIfNotNull(t);
	}

	@Override
	public long deleteById(String id) {
		// TODO Auto-generated method stub
		return steelShapeDao.deleteById(id);
	}
	
}
