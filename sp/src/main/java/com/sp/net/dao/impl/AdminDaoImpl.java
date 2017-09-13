package com.sp.net.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sp.net.dao.impl.BaseDaoImpl;
import com.sp.net.entity.Admin;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.utils.string.StringUtil;
import com.sp.net.dao.AdminDao;

@Repository("adminDao")
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao {
	
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap, String sqlId) {
		// TODO Auto-generated method stub
		if (paramMap == null)
			paramMap = new HashMap<String, Object>();
		
		if (StringUtil.isBlank(sqlId)) {
			sqlId = SQL_LIST_PAGE;
		}
		
		String state = (String) paramMap.get("state");
		if(StringUtil.isBlank(state)){
			paramMap.put("state", "1");
		}
		
		// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
		/*List<Object> list = getSqlSession().selectList(getStatement(sqlId), paramMap,
				new RowBounds((pageParam.getPageNum() - 1) * pageParam.getNumPerPage(), pageParam.getNumPerPage()));*/
		paramMap.put("pageIndex", pageParam.getPageIndex()*pageParam.getPageSize());
		paramMap.put("pageSize", pageParam.getPageSize());
		
		List<Object> list = getSqlSession().selectList(getStatement(sqlId), paramMap);
		
		// 统计总记录数
		//Object countObject = (Object) getSqlSession().selectOne(getStatement(sqlId), new ExecutorInterceptor.CountParameter(paramMap));
		Object countObject = getSqlSession().selectOne(getStatement(SQL_LIST_PAGE_COUNT), paramMap);
		Long count = Long.valueOf(countObject.toString());
		
		// 是否统计当前分页条件下的数据：1:是，其他为否
		Object isCount = paramMap.get("isCount");
		if (isCount != null && "1".equals(isCount.toString())) {
			Map<String, Object> countResultMap = getSessionTemplate().selectOne(getStatement(SQL_COUNT_BY_PAGE_PARAM), paramMap);
			return new PageBean(pageParam.getPageIndex(), pageParam.getPageSize(), count.intValue(), list, countResultMap);
		} else {
			return new PageBean(pageParam.getPageIndex(), pageParam.getPageSize(), count.intValue(), list, null);
		}
				
	}
}
