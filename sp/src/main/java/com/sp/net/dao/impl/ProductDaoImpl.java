/*
 * Powered By [sp]
 */

package com.sp.net.dao.impl;


import org.springframework.stereotype.Repository;

import com.sp.net.dao.impl.BaseDaoImpl;
import com.sp.net.entity.Product;
import com.sp.net.dao.ProductDao;

@Repository("productDao")
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {

}
