/*
 * Powered By [sp]
 */


package com.sp.net.service;

import java.util.Map;

import com.sp.net.entity.Order;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;

public interface OrderService extends BaseService<Order>{
	
	public long createOrder(Order order) throws Exception;

	public long completeProducted(String oId);
	
	public long orderStockOut(String oId);
	
	public PageBean listPageBySaleReports(PageParam pageParam, Map<String, Object> paramMap);
}
