package com.sp.net.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sp.net.service.AdminService;

@Controller
@RequestMapping("/index")
public class LoginController extends BaseController{
	
	@Autowired
	private AdminService adminService;
	
	/*@RequestMapping("/login")
	public*/
}
