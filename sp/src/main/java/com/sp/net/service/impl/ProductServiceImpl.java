/*
 * Powered By [sp]
 */


package com.sp.net.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sp.net.dao.MaterialDao;
import com.sp.net.dao.ProductDao;
import com.sp.net.entity.Material;
import com.sp.net.entity.Product;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.ProductService;

@Component("productService")
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDao productDao;
	
	@Autowired
	MaterialDao materialDao;
	
	@Override
	public Product getById(String id) {
		// TODO Auto-generated method stub
		Product product = productDao.getById(id);
		String mId = product.getMid();
		Material material = materialDao.getById(mId);
		product.setMaterial(material);
		return product;
	}

	@Override
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		PageBean listPage = productDao.listPage(pageParam, paramMap);
		return listPage;
	}

	@Override
	public long insert(Product t) {
		// TODO Auto-generated method stub
		t.setState("1");
		return productDao.insert(t);
	}

	@Override
	public long update(Product t) {
		// TODO Auto-generated method stub
		return productDao.updateIfNotNull(t);
	}

	@Override
	public long deleteById(String id) {
		// TODO Auto-generated method stub
		return productDao.deleteById(id);
	}
	
}
