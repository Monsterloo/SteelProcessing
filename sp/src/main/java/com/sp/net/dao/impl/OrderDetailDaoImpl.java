/*
 * Powered By [sp]
 */

package com.sp.net.dao.impl;


import org.springframework.stereotype.Repository;

import com.sp.net.dao.impl.BaseDaoImpl;
import com.sp.net.entity.OrderDetail;
import com.sp.net.dao.OrderDetailDao;

@Repository("orderDetailDao")
public class OrderDetailDaoImpl extends BaseDaoImpl<OrderDetail> implements OrderDetailDao {

}
