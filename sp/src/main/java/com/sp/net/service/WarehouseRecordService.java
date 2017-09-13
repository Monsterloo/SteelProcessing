/*
 * Powered By [sp]
 */


package com.sp.net.service;

import java.util.List;
import java.util.Map;

import com.sp.net.entity.WarehouseRecord;

public interface WarehouseRecordService extends BaseService<WarehouseRecord>{

	public long purchaseMaterial(Map<String, Object> paramMap);
	
	public long confirmInventory(String wId);
	
	public List<WarehouseRecord> listBy(Map<String, Object> paramMap);
}
