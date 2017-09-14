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
import com.sp.net.common.AppConstants;
import com.sp.net.common.enums.RoleEnum;
import com.sp.net.entity.Admin;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="listPage/{pageIndex}/{pageSize}",method=RequestMethod.GET,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void listPage(HttpServletRequest request, HttpServletResponse response, @PathVariable("pageIndex")Integer pageIndex, @PathVariable("pageSize")Integer pageSize) throws IOException{
		Map<String, Object> paramMap = getParamMap_NullStr();
		PageBean pageBean = adminService.listPage(new PageParam(pageIndex, pageSize), paramMap);
		if(pageBean != null){
			JSONObject jObj = (JSONObject) JSON.toJSON(pageBean);
			jObj.put("RoleList", RoleEnum.toList());
			String result = toJsonString(jObj);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
	
	@RequestMapping(value="fuzzyListPage/{pageIndex}/{pageSize}/{aname}",method=RequestMethod.GET,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void fuzzyListPage(HttpServletRequest request, HttpServletResponse response, @PathVariable("pageIndex")Integer pageIndex, @PathVariable("pageSize")Integer pageSize, @PathVariable("aname")String aname) throws IOException{
		Map<String, Object> paramMap = getParamMap_NullStr();
		paramMap.put("aname", aname);
		PageBean pageBean = adminService.fuzzyListPage(new PageParam(pageIndex, pageSize), paramMap);
		if(pageBean != null){
			JSONObject jObj = (JSONObject) JSON.toJSON(pageBean);
			jObj.put("RoleList", RoleEnum.toList());
			String result = toJsonString(jObj);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}

	@RequestMapping(value="getInfo/{aId}",method=RequestMethod.GET,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void getInfo(HttpServletRequest request, HttpServletResponse response, @PathVariable("aId")String aId) throws IOException{
		Admin admin = adminService.getById(aId);
		if(admin !=null){
			String result = toJsonString(admin);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void create(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> paramMap = getParamMap_NullStr();
		Admin admin = new Admin();
		admin.setAaccount((String)paramMap.get("aaccount"));
		admin.setApwd((String)paramMap.get("apwd"));
		admin.setArole((String)paramMap.get("arole"));
		admin.setAcontact((String)paramMap.get("acontact"));
		admin.setAname((String)paramMap.get("aname"));
		long insert = adminService.insert(admin);
		if(insert > 0){
			String result = toJsonString(insert);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
	
	@RequestMapping(value="updateInfo",method=RequestMethod.PUT,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void updateInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> paramMap = getParamMap_NullStr();
		Admin admin = (Admin) getHttpSession().getAttribute(AppConstants.SESSION_ADMIN);
		admin.setAname((String)paramMap.get("aname"));
		admin.setAcontact((String)paramMap.get("acontact"));
		admin.setArole((String)paramMap.get("arole"));
		long update = adminService.update(admin);
		if(update > 0){
			String result = toJsonString(update);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
	
	@RequestMapping(value="update",method=RequestMethod.PUT,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void update(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> paramMap = getParamMap_NullStr();
		Admin admin = new Admin();
		admin.setAid((String) paramMap.get("aid"));
		admin.setAname((String)paramMap.get("aname"));
		admin.setAcontact((String)paramMap.get("acontact"));
		admin.setArole((String)paramMap.get("arole"));
		long update = adminService.update(admin);
		if(update > 0){
			String result = toJsonString(update);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
	
	@RequestMapping(value="delete/{aId}",method=RequestMethod.DELETE,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("aId")String aId) throws IOException{
		long delete = adminService.deleteById(aId);
		if(delete > 0){
			String result = toJsonString(delete);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="modifyPwd",method=RequestMethod.PUT,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void modifyPwd(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> paramMap = getParamMap_NullStr();
		Admin admin = new Admin();
		admin.setAid(((Admin) getHttpSession().getAttribute(AppConstants.SESSION_ADMIN)).getAid());
		admin.setApwd((String) paramMap.get("apwd"));
		long update = adminService.update(admin);
		if(update > 0){
			String result = toJsonString(update);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
}
