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

import com.sp.net.common.AppConstants;
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
			String result = toJsonString(pageBean);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}

	@RequestMapping(value="getInfo/{aId}",method=RequestMethod.GET,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void getAdminInfo(HttpServletRequest request, HttpServletResponse response, @PathVariable("aId")String aId) throws IOException{
		Admin admin = (Admin) getHttpSession().getAttribute(AppConstants.SESSION_ADMIN);
		if(admin !=null){
			String result = toJsonString(admin);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
	
	@RequestMapping(value="update",method=RequestMethod.PUT,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void update(HttpServletRequest request, HttpServletResponse response) throws IOException{
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
	
	@RequestMapping(value="delete/{aid}",method=RequestMethod.GET,produces="application/json; charset=UTF-8")
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
}
