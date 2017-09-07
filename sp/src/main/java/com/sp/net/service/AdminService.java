package com.sp.net.service;

import java.util.List;
import java.util.Map;

import com.sp.net.entity.Admin;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;

public interface AdminService extends BaseService<Admin>{

	public Admin findByAccount(String aAcount);
	
	public long modifyPwd(String aId, String newPwd);
	
	public Admin login(Map<String, Object> paramMap);
	
	//public long batchInsert(List<Admin> remitRequest);
	
	public long batchDelete(List<String> aIds);

}
