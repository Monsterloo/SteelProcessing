/*
 * Powered By [sp]
 */


package com.sp.net.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sp.net.dao.OrderDao;
import com.sp.net.entity.Client;
import com.sp.net.entity.Order;
import com.sp.net.entity.OrderDetail;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.AdminService;
import com.sp.net.service.ClientService;
import com.sp.net.service.OrderDetailService;
import com.sp.net.service.OrderService;
import com.sp.net.service.SteelService;

@Component("orderService")
@Transactional(rollbackFor=Exception.class)
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private SteelService steelService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Override
	public Order getById(String id) {
		// TODO Auto-generated method stub
		Order order = orderDao.getById(id);
		String cId = order.getCid();
		String aId = order.getAid();
		order.setClient(clientService.getById(cId));
		order.setAdmin(adminService.getById(aId));
		return order;
	}

	@Override
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		PageBean listPage = orderDao.listPage(pageParam, paramMap);
		List<Object> orderList = listPage.getRecordList();
		List<Object> newOrderList = new ArrayList<Object>();
		for(Object obj : orderList){
			Order order = (Order)obj;
			order.setClient(clientService.getById(order.getCid()));
			order.setAdmin(adminService.getById(order.getAid()));
			//根据订单id查处订单明细
			String oid = order.getOid();
			Map<String, Object> detailsParam = new HashMap<String, Object>();
			detailsParam.put("oid", oid);
			List<OrderDetail> detailList = orderDetailService.listBy(detailsParam);
			order.setOrderDetailList(detailList);
			
			newOrderList.add(order);
		}
		listPage.setRecordList(newOrderList);
		return listPage;
	}

	@Override
	public long insert(Order order) {
		// TODO Auto-generated method stub
		Date date = new Date();
		order.setCreatetime(date);
		order.setModifytime(date);
		order.setState("1");
		order.setOrderState("1");	//1.订单下达		2.生产完成		3.订单出库
		return orderDao.insert(order);
	}

	@Override
	public long update(Order order) {
		// TODO Auto-generated method stub
		order.setModifytime(new Date());
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
		return update(order);
	}

	@Override
	public long createOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		long insert = insert(order);
		if(insert > 0){
			String oid = order.getOid();
			List<OrderDetail> orderDetailList = order.getOrderDetailList();
			if(orderDetailList.size() > 0){
				for(OrderDetail od : orderDetailList){
					//钢筋id处理
					String sId = od.getSid();
					sId = sId.substring(0, sId.indexOf("-"));
					od.setSid(sId);
					//存入订单id
					od.setOid(oid);
					long createDetail = orderDetailService.insert(od);
					if(createDetail <= 0){
						throw new Exception("订单创建产品明细失败!");
					}
				}
			}else{
				throw new Exception("订单中添加的产品不能少于1件!");
			}
		}else{
			throw new Exception("创建订单失败!");
		}
		return insert;
	}

	@Override
	public PageBean listPageBySaleReports(PageParam pageParam, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		Client client = clientService.getBy(paramMap);
		String cid = client.getCid();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("cid", cid);
		PageBean listPage = this.listPage(pageParam, parameters);
		return listPage;
	}
	
}
