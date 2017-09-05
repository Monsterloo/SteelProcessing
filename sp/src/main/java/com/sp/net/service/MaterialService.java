/*
 * Powered By [sp]
 */


package com.sp.net.service;

import java.util.List;
import java.util.Map;

import com.sp.net.entity.Material;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;

public interface MaterialService extends BaseService<Material>{
	
	public long batchDelete(List<String> mIds);
}
