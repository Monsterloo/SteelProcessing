/*
 * Powered By [sp]
 */

package com.sp.net.dao;


import java.util.Map;

import com.sp.net.dao.BaseDao;
import com.sp.net.entity.Client;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;


public interface ClientDao extends BaseDao<Client>{

	public PageBean fuzzyListPage(PageParam pageParam, Map<String, Object> paramMap);
}
