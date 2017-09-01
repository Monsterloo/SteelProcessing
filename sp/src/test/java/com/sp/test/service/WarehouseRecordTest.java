package com.sp.test.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sp.net.dao.WarehouseRecordDao;
import com.sp.net.entity.WarehouseRecord;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.MaterialService;
import com.sp.net.service.WarehouseRecordService;
import com.sp.net.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/sp/config/spring-common.xml")
public class WarehouseRecordTest {
	
	@Autowired
	WarehouseRecordDao warehouseRecordDao;

	@Autowired
	WarehouseRecordService<WarehouseRecord> warehouseRecordService;
	
	@Autowired
	MaterialService materialService;
	
	@Test
	public void getDate(){
		Date date = new Date();
		String timeStampStr = DateUtil.getTimeStampStr(date);
		System.out.println(timeStampStr);
	}
	
	@Test 
	public void purchaseMaterial(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mid", "f3cad0698d6311e7b5cec85b76aeb963");
		paramMap.put("purchaseCount", 88888);
		long purchaseMaterial = warehouseRecordService.purchaseMaterial(paramMap);
		System.out.println(purchaseMaterial);
		/*Material material = (Material) materialService.getById("f3cad0698d6311e7b5cec85b76aeb963");
		WarehouseRecord record = new WarehouseRecord();
		record.setMid("f3cad0698d6311e7b5cec85b76aeb963");
		record.setMaterialCategory(material.getMcategory());
		record.setPurchaseCount(888);
		record.setPurchaseState("0");
		//record.setMaterial(material);
		record.setState("1");
		record.setCreatetime(new Date());
		long purchaseMaterial = warehouseRecordDao.insert(record);*/
	}
	
	@Test
	public void listPage(){
		PageBean listPage = warehouseRecordService.listPage(new PageParam(0, 10), null);
		System.out.println(listPage.getRecordList());
		List<Object> recordList = listPage.getRecordList();
		WarehouseRecord record = null;
		if(recordList.size() > 0){
			record = (WarehouseRecord) recordList.get(0);
		}
		System.out.println(record.getPurchaseStateIndex());
	}	
	
	@Test
	public void confirmInventory(){
		String wId = "fa659bea8ec111e7b5cec85b76aeb963";
		long confirmInventory = warehouseRecordService.confirmInventory(wId);
		System.out.println(confirmInventory);
	}
}
