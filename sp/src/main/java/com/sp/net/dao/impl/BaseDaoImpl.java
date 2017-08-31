package com.sp.net.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.pool.DruidDataSource;
import com.sp.net.dao.BaseDao;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.utils.string.StringUtil;

@Repository("baseDao")
public abstract class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {
	
	public static final String SQL_INSERT = "insert";
	public static final String SQL_BATCH_INSERT = "batchInsert";
	public static final String SQL_UPDATE = "update";
	public static final String SQL_GET_BY_ID = "getById";
	public static final String SQL_DELETE_BY_ID = "deleteById";
	public static final String SQL_LIST_PAGE = "listPage";
	public static final String SQL_LIST_PAGE_COUNT = "listPageCount";
	public static final String SQL_LIST_BY = "listBy";
	public static final String SQL_COUNT_BY_PAGE_PARAM = "countByPageParam"; // 根据当前分页参数进行统计
	public static final String SQL_MAP_BY_ID = "mapById";
	public static final String SQL_MAP_BY = "mapBy";
	private final static String DEFAULT_ID_COLUMN_NAME = "id";
	
	/**
	 * 注入SqlSessionTemplate实例(要求Spring中进行SqlSessionTemplate的配置).<br/>
	 * 可以调用sessionTemplate完成数据库操作.
	 */
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	@Autowired
	protected SqlSessionFactory sqlSessionFactory;

	@Autowired
	private DruidDataSource druidDataSource;

	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}
	
	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
	    super.setSqlSessionFactory(sqlSessionFactory);
	}

	public SqlSession getSqlSession() {
		return super.getSqlSession();
	}
	
	public String getStatement(String sqlId) {

		String name = this.getClass().getName();

		StringBuffer sb = new StringBuffer().append(name).append(".").append(sqlId);

		return sb.toString();
	}
	
	@Override
	public long insert(T t) {
		// TODO Auto-generated method stub
		if (t == null)
			throw new RuntimeException("T is null");
		
		int result = sessionTemplate.insert(getStatement(SQL_INSERT), t);
		return result;
	}

	@Override
	public long insert(List<T> list) {
		// TODO Auto-generated method stub
		if (list == null || list.size() <= 0)
			return 0;
		
		int result = sessionTemplate.insert(getStatement(SQL_BATCH_INSERT), list);
		return result;
	}

	@Override
	public long update(T t) {
		// TODO Auto-generated method stub
		if (t == null)
			throw new RuntimeException("t is null");

		int result = sessionTemplate.update(getStatement(SQL_UPDATE), t);

		return result;
	}

	@Override
	public long update(List<T> list) {
		// TODO Auto-generated method stub
		if (list == null || list.size() <= 0)
			return 0;

		int result = 0;

		for (int i = 0; i < list.size(); i++) {
			this.update(list.get(i));
			result += 1;
		}

		return result;
	}

	@Override
	public T getById(String id) {
		// TODO Auto-generated method stub
		return sessionTemplate.selectOne(getStatement(SQL_GET_BY_ID), id);
	}

	@Override
	public long deleteById(String id) {
		// TODO Auto-generated method stub
		return sessionTemplate.delete(getStatement(SQL_DELETE_BY_ID), id);
	}

	@Override
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return this.listPage(pageParam, paramMap, SQL_LIST_PAGE);
	}

	@Override
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
		paramMap.put("pageIndex", pageParam.getPageIndex());
		paramMap.put("pageSize", pageParam.getPageSize());
		
		List<Object> list = getSqlSession().selectList(getStatement(sqlId), paramMap);
		
		// 统计总记录数
		//Object countObject = (Object) getSqlSession().selectOne(getStatement(sqlId), new ExecutorInterceptor.CountParameter(paramMap));
		Object countObject = getSqlSession().selectOne(getStatement(SQL_LIST_PAGE_COUNT), paramMap);
		Long count = Long.valueOf(countObject.toString());
		
		// 是否统计当前分页条件下的数据：1:是，其他为否
		Object isCount = paramMap.get("isCount");
		if (isCount != null && "1".equals(isCount.toString())) {
			Map<String, Object> countResultMap = sessionTemplate.selectOne(getStatement(SQL_COUNT_BY_PAGE_PARAM), paramMap);
			return new PageBean(pageParam.getPageIndex(), pageParam.getPageSize(), count.intValue(), list, countResultMap);
		} else {
			return new PageBean(pageParam.getPageIndex(), pageParam.getPageSize(), count.intValue(), list, null);
		}
				
	}
	
	public List<T> listBy(Map<String, Object> paramMap) {
		return (List) this.listBy(paramMap, SQL_LIST_BY);
	}

	@Override
	public List<Object> listBy(Map<String, Object> paramMap, String sqlId) {
		// TODO Auto-generated method stub
		if (paramMap == null)
			paramMap = new HashMap<String, Object>();

		return sessionTemplate.selectList(getStatement(sqlId), paramMap);
	}

	@Override
	public T getBy(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return (T) this.getBy(paramMap, SQL_LIST_BY);
	}

	@Override
	public Object getBy(Map<String, Object> paramMap, String sqlId) {
		// TODO Auto-generated method stub
		if (paramMap == null || paramMap.isEmpty())
			return null;

		return this.getSqlSession().selectOne(getStatement(sqlId), paramMap);
	}

	@Override
	public long countTotalRecord(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sessionTemplate.selectOne(getStatement(SQL_LIST_PAGE_COUNT), paramMap);
	}

	@Override
	public Map<String, Object> sumData(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sessionTemplate.selectOne(getStatement(SQL_COUNT_BY_PAGE_PARAM), paramMap);
	}
}
