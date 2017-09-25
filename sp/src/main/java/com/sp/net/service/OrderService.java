/*
 * Powered By [sp]
 */


package com.sp.net.service;

import com.sp.net.entity.Order;

public interface OrderService extends BaseService<Order>{
	
	public long createOrder(Order order) throws Exception;

	public long completeProducted(String oId);
	
	public long orderStockOut(String oId);
}
