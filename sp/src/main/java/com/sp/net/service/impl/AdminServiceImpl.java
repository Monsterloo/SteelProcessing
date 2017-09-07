package com.sp.net.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sp.net.dao.AdminDao;
import com.sp.net.entity.Admin;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.AdminService;

@Component("adminService")
@Transactional
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminDao adminDao;

	@Override
	public Admin getById(String aId) {
		// TODO Auto-generated method stub
		Admin admin = adminDao.getById(aId);
		return admin;
	}
	
	@Override
	public Admin findByAccount(String aAcount) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("aaccount", aAcount);
		Admin admin = adminDao.getBy(paramMap);
		return admin;
	}

	@Override
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		PageBean listPage = adminDao.listPage(pageParam, paramMap);
		return listPage;
	}

	@Override
	public long insert(Admin admin) {
		// TODO Auto-generated method stub
		admin.setState("1");
		return adminDao.insert(admin);
	}

	@Override
	public long update(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.updateIfNotNull(admin);
	}

	@Override
	public long deleteById(String aId) {
		// TODO Auto-generated method stub
		return adminDao.deleteById(aId);
	}
	
	@Override
	public Admin login(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		Admin result = adminDao.getBy(paramMap);
		if(result != null){
			return result;
		}
		return null;
	}
	
	@Override
	public long modifyPwd(String aId, String newPwd) {
		// TODO Auto-generated method stub
		Admin admin = adminDao.getById(aId);
		admin.setApwd(newPwd);
		return adminDao.update(admin);
	}

	@Override
	public long batchDelete(List<String> aIds) {
		// TODO Auto-generated method stub
		long count = 0;
		for(String aId : aIds){
			count += adminDao.deleteById(aId);
		}
		return count;
	}

}
