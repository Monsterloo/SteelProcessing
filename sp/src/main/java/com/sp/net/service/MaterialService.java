/*
 * Powered By [sp]
 */


package com.sp.net.service;

import java.util.List;

import com.sp.net.entity.Material;

public interface MaterialService extends BaseService<Material>{
	
	public long purchase(Material material);
	
	public long batchDelete(List<String> mIds);
}
