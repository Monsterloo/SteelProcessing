package com.sp.net.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sp.net.common.enums.WarehouseStateEnum;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.WarehouseRecordService;

@Controller
@RequestMapping("/warehouseRecord")
public class WarehouseRecordController extends BaseController {

	@Autowired
	WarehouseRecordService warehouseRecordService;
	
	@RequestMapping(value="listPage/{pageIndex}/{pageSize}",method=RequestMethod.GET,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void listPage(HttpServletRequest request, HttpServletResponse response, @PathVariable("pageIndex")Integer pageIndex, @PathVariable("pageSize")Integer pageSize) throws IOException{
		Map<String, Object> paramMap = getParamMap_NullStr();
		PageBean pageBean = warehouseRecordService.listPage(new PageParam(pageIndex, pageSize), paramMap);
		if(pageBean != null){
			JSONObject jObj = (JSONObject) JSON.toJSON(pageBean);
			jObj.put("WarehouseStateList", WarehouseStateEnum.toList());
			String result = toJsonString(jObj);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
	
	@RequestMapping(value="confirm/{wId}",method=RequestMethod.POST,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void confirm(HttpServletRequest request, HttpServletResponse response, @PathVariable("wId")String wId) throws IOException{
		long confirmInventory = warehouseRecordService.confirmInventory(wId);
		if(confirmInventory > 0){
			String result = toJsonString(confirmInventory);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
}
