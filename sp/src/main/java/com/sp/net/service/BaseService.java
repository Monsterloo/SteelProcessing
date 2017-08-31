package com.sp.net.service;

import java.util.Map;

import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;

public interface BaseService<T> {

	T getById(String id);
	
	PageBean listPage(PageParam pageParam, Map<String, Object> paramMap);
	
	long insert(T t);
	
	long update(T t);
	
	long deleteById(String id);
	
}
