/*
 * Powered By [sp]
 */


package com.sp.net.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sp.net.dao.SteelShapeDao;
import com.sp.net.entity.SteelShape;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.SteelShapeService;

@Component("steelShapeService")
@Transactional
public class SteelShapeServiceImpl implements SteelShapeService{

	@Autowired
	SteelShapeDao steelShapeDao;
	
	@Override
	public SteelShape getById(String id) {
		// TODO Auto-generated method stub
		SteelShape ss = steelShapeDao.getById(id);
		return ss;
	}

	@Override
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		PageBean listPage = steelShapeDao.listPage(pageParam, paramMap);
		return listPage;
	}

	@Override
	public long insert(SteelShape t) {
		// TODO Auto-generated method stub
		t.setState("1");
		return steelShapeDao.insert(t);
	}

	@Override
	public long update(SteelShape t) {
		// TODO Auto-generated method stub
		return steelShapeDao.updateIfNotNull(t);
	}

	@Override
	public long deleteById(String id) {
		// TODO Auto-generated method stub
		return steelShapeDao.deleteById(id);
	}
	
}
