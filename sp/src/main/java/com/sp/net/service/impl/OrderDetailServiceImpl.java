/*
 * Powered By [sp]
 */


package com.sp.net.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sp.net.dao.OrderDetailDao;
import com.sp.net.entity.Order;
import com.sp.net.entity.OrderDetail;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.OrderDetailService;

@Component("orderDetailService")
@Transactional(rollbackFor=Exception.class)
public class OrderDetailServiceImpl implements OrderDetailService{

	@Autowired
	private OrderDetailDao orderDetailDao;
	
	@Override
	public OrderDetail getById(String id) {
		// TODO Auto-generated method stub
		OrderDetail detail = orderDetailDao.getById(id);
		return detail;
	}

	@Override
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		PageBean listPage = orderDetailDao.listPage(pageParam, paramMap);
		return listPage;
	}

	@Override
	public long insert(OrderDetail t) {
		// TODO Auto-generated method stub
		t.setState("1");
		long insert = orderDetailDao.insert(t);
		return insert;
	}

	@Override
	public long update(OrderDetail t) {
		// TODO Auto-generated method stub
		long updateIfNotNull = orderDetailDao.updateIfNotNull(t);
		return updateIfNotNull;
	}

	@Override
	public long deleteById(String id) {
		// TODO Auto-generated method stub
		long deleteById = orderDetailDao.deleteById(id);
		return deleteById;
	}

	@Override
	public List<OrderDetail> listBy(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		List<OrderDetail> listBy = orderDetailDao.listBy(paramMap);
		return listBy;
	}
}
