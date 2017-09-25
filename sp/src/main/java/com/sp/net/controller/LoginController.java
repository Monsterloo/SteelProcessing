package com.sp.net.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sp.net.common.AppConstants;
import com.sp.net.entity.Admin;
import com.sp.net.service.AdminService;

/**
 * 登录
 * @author junlonlu
 *
 */
@Controller
@RequestMapping("/login/*")
public class LoginController extends BaseController{
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="login",method=RequestMethod.POST,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> paramMap = getParamMap_NullStr();
		Admin admin = adminService.login(paramMap);
		if(admin != null){
			getHttpSession().setAttribute(AppConstants.SESSION_ADMIN, admin);
			System.out.println(getHttpSession());
			String result = toJsonString(admin);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
	
	@RequestMapping(value="loginout",method=RequestMethod.POST,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void loginout(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.getSession().removeAttribute(AppConstants.SESSION_ADMIN);
		request.getSession().invalidate();
		outWrite(response, "loginout");
	}
	
}
