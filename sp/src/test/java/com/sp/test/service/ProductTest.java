package com.sp.test.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sp.net.dao.ProductDao;
import com.sp.net.entity.Product;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/sp/config/spring-common.xml")
public class ProductTest {

	@Autowired
	ProductDao productDao;
	
	@Autowired
	ProductService productService;
	
	@Test
	public void getById(){
		Product product = (Product) productService.getById("da44a3ce912411e7a3ecc85b76aeb963");
		System.out.println(product);
	}
	
	@Test
	public void insert(){
		Product product = new Product();
		product.setMid("05840891914211e7a3ecc85b76aeb963");
		product.setMaterialCount(3);
		product.setPrice(233.0);
		product.setProductName("成品测试3");
		product.setImageUrl("www.jq22.com");
		productService.insert(product);
	}
	
	@Test
	public void listPage(){
		productService.listPage(new PageParam(0, 10), null);
	}
	
	@Test
	public void update(){
		PageBean listPage = productService.listPage(new PageParam(0, 1), null);
		List<Object> recordList = listPage.getRecordList();
		Product product = (Product) recordList.get(0);
		product.setPrice(88.0);
		long update = productService.update(product);
	}
}
