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
import com.sp.net.entity.Client;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController extends BaseController{

	@Autowired
	private ClientService clientService;
	
	@RequestMapping(value="listPage/{pageIndex}/{pageSize}",method=RequestMethod.GET,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void listPage(HttpServletRequest request, HttpServletResponse response, @PathVariable("pageIndex")Integer pageIndex, @PathVariable("pageSize")Integer pageSize) throws IOException{
		Map<String, Object> paramMap = getParamMap_NullStr();
		PageBean pageBean = clientService.listPage(new PageParam(pageIndex, pageSize), paramMap);
		if(pageBean != null){
			JSONObject jObj = (JSONObject) JSON.toJSON(pageBean);
			String result = toJsonString(jObj);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
	
	@RequestMapping(value="getInfo/{cId}",method=RequestMethod.GET,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void getInfo(HttpServletRequest request, HttpServletResponse response, @PathVariable("cId")String cId) throws IOException{
		Client client = clientService.getById(cId);
		if(client !=null){
			String result = toJsonString(client);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void create(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> paramMap = getParamMap_NullStr();
		Client client = new Client();
		client.setCname((String)paramMap.get("cname"));
		client.setCcontact((String)paramMap.get("ccontact"));
		client.setCaddress((String)paramMap.get("caddress"));
		client.setClegalperson((String) paramMap.get("clegalperson"));
		long insert = clientService.insert(client);
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
		Client client = new Client();
		client.setCid((String)paramMap.get("cid"));
		client.setCname((String)paramMap.get("cname"));
		client.setCcontact((String)paramMap.get("ccontact"));
		client.setCaddress((String)paramMap.get("caddress"));
		client.setClegalperson((String) paramMap.get("clegalperson"));
		long update = clientService.update(client);
		if(update > 0){
			String result = toJsonString(update);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
	
	@RequestMapping(value="delete/{cId}",method=RequestMethod.DELETE,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("cId")String cId) throws IOException{
		long delete = clientService.deleteById(cId);
		if(delete > 0){
			String result = toJsonString(delete);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
}
