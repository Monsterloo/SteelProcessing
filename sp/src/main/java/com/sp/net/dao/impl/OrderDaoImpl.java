/*
 * Powered By [sp]
 */

package com.sp.net.dao.impl;


import org.springframework.stereotype.Repository;

import com.sp.net.entity.Order;
import com.sp.net.dao.OrderDao;

@Repository("orderDao")
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {

}
