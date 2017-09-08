/*
 * Powered By [sp]
 */


package com.sp.net.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sp.net.dao.OrderDao;
import com.sp.net.entity.Order;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.AdminService;
import com.sp.net.service.ClientService;
import com.sp.net.service.OrderService;
import com.sp.net.service.SteelShapeService;

@Component("orderService")
@Transactional
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private SteelShapeService steelShapeService;
	
	@Autowired
	private AdminService adminService;

	@Override
	public Order getById(String id) {
		// TODO Auto-generated method stub
		Order order = orderDao.getById(id);
		String cId = order.getCid();
		String sId = order.getSid();
		String aId = order.getAid();
		order.setClient(clientService.getById(cId));
		order.setSteelShape(steelShapeService.getById(sId));
		order.setAdmin(adminService.getById(aId));
		return order;
	}

	@Override
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		PageBean listPage = orderDao.listPage(pageParam, paramMap);
		return listPage;
	}

	@Override
	public long insert(Order order) {
		// TODO Auto-generated method stub
		order.setState("1");
		order.setOrderState("1");	//1.订单下达		2.生产完成		3.订单出库
		return orderDao.insert(order);
	}

	@Override
	public long update(Order order) {
		// TODO Auto-generated method stub
		return orderDao.updateIfNotNull(order);
	}

	@Override
	public long deleteById(String id) {
		// TODO Auto-generated method stub
		return orderDao.deleteById(id);
	}

	@Override
	public long completeProducted(String oId) {
		// TODO Auto-generated method stub
		Order order = new Order();
		order.setOid(oId);
		order.setOrderState("2");
		return update(order);
	}

	@Override
	public long orderStockOut(String oId) {
		// TODO Auto-generated method stub
		Order order = new Order();
		order.setOid(oId);
		order.setOrderState("3");
		return 0;
	}
	
}
