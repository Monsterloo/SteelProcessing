/*
 * Powered By [sp]
 */


package com.sp.net.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sp.net.dao.MaterialDao;
import com.sp.net.entity.Material;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.MaterialService;
import com.sp.net.service.WarehouseRecordService;

@Component("materialService")
@Transactional
public class MaterialServiceImpl implements MaterialService{
	
	@Autowired
	MaterialDao materialDao;
	
	@Autowired
	WarehouseRecordService warehouseRecordService;

	@Override
	public Material getById(String mId) {
		// TODO Auto-generated method stub
		Material material = materialDao.getById(mId);
		return material;
	}

	@Override
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		PageBean listPage = materialDao.listPage(pageParam, paramMap);
		return listPage;
	}

	@Override
	public long insert(Material material) {
		// TODO Auto-generated method stub
		return materialDao.insert(material);
	}

	@Override
	public long update(Material material) {
		// TODO Auto-generated method stub
		return materialDao.updateIfNotNull(material);
	}

	@Override
	public long deleteById(String mId) {
		// TODO Auto-generated method stub
		return materialDao.deleteById(mId);
	}
	
	@Override
	public long purchase(Material material) {
		// TODO Auto-generated method stub
		Material m = getById(material.getMid());
		m.setMexistCount(m.getMexistCount() + material.getMexistCount());
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mid", m.getMid());
		paramMap.put("purchaseCount", material.getMexistCount());
		long purchaseMaterial = warehouseRecordService.purchaseMaterial(paramMap);
		if(purchaseMaterial > 0){
			return update(m);
		}else {
			return 0;
		}
		
	}

	@Override
	public long batchDelete(List<String> mIds) {
		// TODO Auto-generated method stub
		long count = 0;
		for(String mId : mIds){
			count += materialDao.deleteById(mId);
		}
		return count;
	}

}
