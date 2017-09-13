/*
 * Powered By [sp]
 */


package com.sp.net.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sp.net.dao.WarehouseRecordDao;
import com.sp.net.entity.Material;
import com.sp.net.entity.WarehouseRecord;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.MaterialService;
import com.sp.net.service.WarehouseRecordService;

@Component("warehouseRecordService")
@Transactional
public class WarehouseRecordServiceImpl implements WarehouseRecordService{
	
	@Autowired
	WarehouseRecordDao warehouseRecordDao;
	
	@Autowired
	MaterialService materialService;

	@Override
	public WarehouseRecord getById(String id) {
		// TODO Auto-generated method stub
		WarehouseRecord record = warehouseRecordDao.getById(id);
		Material m = materialService.getById(record.getMid());
		record.setMaterial(m);
		return record;
	}

	@Override
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		PageBean listPage = warehouseRecordDao.listPage(pageParam, paramMap);
		return listPage;
	}

	@Override
	public long insert(WarehouseRecord t) {
		// TODO Auto-generated method stub
		return warehouseRecordDao.insert(t);
	}

	@Override
	public long update(WarehouseRecord t) {
		// TODO Auto-generated method stub
		t.setModifytime(new Date());
		return warehouseRecordDao.updateIfNotNull(t);
	}

	@Override
	public long deleteById(String id) {
		// TODO Auto-generated method stub
		return warehouseRecordDao.deleteById(id);
	}

	/**
	 * 采购材料
	 */
	@Override
	public long purchaseMaterial(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		String mId = (String) paramMap.get("mid");
		int purchaseCount = (int) paramMap.get("purchaseCount");
		Material material = materialService.getById(mId);
		WarehouseRecord record = new WarehouseRecord();
		record.setMid(mId);
		record.setMaterialCategory(material.getMcategory());
		record.setPurchaseCount(purchaseCount);
		record.setPurchaseState("0");
		//record.setMaterial(material);
		record.setState("1");
		record.setCreatetime(new Date());
		record.setModifytime(new Date()); 
		return insert(record);
	}
	
	/**
	 * 确认入库
	 */
	@Override
	public long confirmInventory(String wId) {
		// TODO Auto-generated method stub
		WarehouseRecord record = getById(wId);
		record.setPurchaseState("1");
		Material material = record.getMaterial();
		material.setMexistCount(material.getMexistCount() + record.getPurchaseCount());
		long update = materialService.update(material);
		if(update > 0){
			return this.update(record);
		}else{
			return 0;
		}
	}

	@Override
	public List<WarehouseRecord> listBy(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		List<WarehouseRecord> listBy = warehouseRecordDao.listBy(paramMap);
		return listBy;
	}
	
}
