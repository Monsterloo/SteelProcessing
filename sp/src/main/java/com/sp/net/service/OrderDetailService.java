/*
 * Powered By [sp]
 */


package com.sp.net.service;

import java.util.List;
import java.util.Map;

import com.sp.net.entity.OrderDetail;

public interface OrderDetailService extends BaseService<OrderDetail>{

	public List<OrderDetail> listBy(Map<String, Object> paramMap);
}
