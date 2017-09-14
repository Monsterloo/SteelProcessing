package com.sp.net.dao;


import java.util.Map;

import com.sp.net.dao.BaseDao;
import com.sp.net.entity.Admin;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;


public interface AdminDao extends BaseDao<Admin>{

	public PageBean fuzzyListPage(PageParam pageParam, Map<String, Object> paramMap);
}
