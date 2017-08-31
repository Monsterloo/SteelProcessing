/*
 * Powered By [sp]
 */


package com.sp.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sp.entity.Material;
import com.sp.dao.MaterialDao;

@Component("materialBiz")
public class MaterialBiz{
	@Autowired
	private MaterialDao materialDao;

	public Material getById(long id){
		return materialDao.getById(id);
	}

	public boolean deleteById(long id){
		return materialDao.deleteById(id) > 0;
	}
}
