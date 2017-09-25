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
import com.sp.net.common.enums.MaterialEnum;
import com.sp.net.entity.Material;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.MaterialService;

/**
 * 材料
 * @author junlonlu
 *
 */
@Controller
@RequestMapping("/material")
public class MaterialController extends BaseController{

	@Autowired
	private MaterialService materialService;
	
	@RequestMapping(value="listPage/{pageIndex}/{pageSize}",method=RequestMethod.GET,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void listPage(HttpServletRequest request, HttpServletResponse response, @PathVariable("pageIndex")Integer pageIndex, @PathVariable("pageSize")Integer pageSize) throws IOException{
		Map<String, Object> paramMap = getParamMap_NullStr();
		PageBean pageBean = materialService.listPage(new PageParam(pageIndex, pageSize), paramMap);
		if(pageBean != null){
			JSONObject jObj = (JSONObject) JSON.toJSON(pageBean);
			jObj.put("Materialist", MaterialEnum.toList());
			String result = toJsonString(jObj);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
	
	@RequestMapping(value="getInfo/{mId}",method=RequestMethod.GET,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void getInfo(HttpServletRequest request, HttpServletResponse response, @PathVariable("mId")String mId) throws IOException{
		Material material = materialService.getById(mId);
		if(material !=null){
			String result = toJsonString(material);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void create(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> paramMap = getParamMap_NullStr();
		Material material = new Material();
		material.setMname((String) paramMap.get("mname"));
		material.setMsource((String) paramMap.get("msource"));
		material.setMprice((Double) paramMap.get("mprice"));
		material.setMlength(Double.valueOf((String) paramMap.get("mlength")));
		material.setMdiameter(Double.valueOf((String) paramMap.get("mdiameter")));
		material.setMweight(Double.valueOf((String) paramMap.get("mweight")));
		material.setMexistCount(Integer.valueOf((String) paramMap.get("mexistCount")));
		material.setMminCount(Integer.valueOf((String) paramMap.get("mminCount")));
		material.setMcategory(Integer.valueOf((String) paramMap.get("mcategory")));
		long insert = materialService.insert(material);
		if(insert > 0){
			String result = toJsonString(insert);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void update(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> paramMap = getParamMap_NullStr();
		Material material = new Material();
		material.setMid((String) paramMap.get("mid"));
		material.setMminCount(Integer.valueOf((String) paramMap.get("mminCount")));
		long update = materialService.update(material);
		if(update > 0){
			String result = toJsonString(update);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
	
	@RequestMapping(value="purchase",method=RequestMethod.POST,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void purchase(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> paramMap = getParamMap_NullStr();
		Material material = new Material();
		material.setMid((String) paramMap.get("mid"));
		material.setMexistCount(Integer.valueOf((String) paramMap.get("mexistCount")));
		long update = materialService.purchase(material);
		if(update > 0){
			String result = toJsonString(update);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
	
	@RequestMapping(value="delete/{mId}",method=RequestMethod.DELETE,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("mId")String mId) throws IOException{
		long delete = materialService.deleteById(mId);
		if(delete > 0){
			String result = toJsonString(delete);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
}
