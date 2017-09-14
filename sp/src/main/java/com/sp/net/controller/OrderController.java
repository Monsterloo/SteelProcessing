package com.sp.net.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
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
import com.sp.net.entity.Order;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;
import com.sp.net.service.OrderService;
import com.sp.net.utils.DateUtil;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="listPage/{pageIndex}/{pageSize}",method=RequestMethod.GET,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void listPage(HttpServletRequest request, HttpServletResponse response, @PathVariable("pageIndex")Integer pageIndex, @PathVariable("pageSize")Integer pageSize) throws IOException{
		Map<String, Object> paramMap = getParamMap_NullStr();
		PageBean pageBean = orderService.listPage(new PageParam(pageIndex, pageSize), paramMap);
		if(pageBean != null){
			JSONObject jObj = (JSONObject) JSON.toJSON(pageBean);
			String result = toJsonString(jObj);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
	
	@RequestMapping(value="getInfo/{oId}",method=RequestMethod.GET,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void getInfo(HttpServletRequest request, HttpServletResponse response, @PathVariable("oId")String oId) throws IOException{
		Order order = orderService.getById(oId);
		if(order !=null){
			String result = toJsonString(order);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		Map<String, Object> paramMap = getParamMap_NullStr();
		Order order = new Order();
		order.setProjectName((String) paramMap.get("projectName"));
		order.setCid((String) paramMap.get("cid"));
		order.setSid((String) paramMap.get("sid"));
		order.setAid((String) paramMap.get("aid"));
		order.setPrice(Double.valueOf((String) paramMap.get("price")));
		order.setCount(Integer.valueOf((String) paramMap.get("count")));
		order.setDueDate(DateUtil.LONG_DATE_FORMAT.parse((String) paramMap.get("dueDate")));
		order.setTotalLength(Double.valueOf((String) paramMap.get("totalLength")));
		order.setTotalWeight(Double.valueOf((String) paramMap.get("totalWeight")));
		order.setTotalPrice(Double.valueOf((String) paramMap.get("totalPrice")));
		long insert = orderService.insert(order);
		if(insert > 0){
			String result = toJsonString(insert);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
	
	/**
	 * 生产完成
	 * @param request
	 * @param response
	 * @param oId
	 * @throws IOException
	 */
	@RequestMapping(value="completeProducted/{oId}",method=RequestMethod.PUT,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void completeProducted(HttpServletRequest request, HttpServletResponse response, @PathVariable("oId")String oId) throws IOException{
		long completeProducted = orderService.completeProducted(oId);
		if(completeProducted > 0){
			String result = toJsonString(completeProducted);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
	
	/**
	 * 订单出库
	 * @param request
	 * @param response
	 * @param oId
	 * @throws IOException
	 */
	@RequestMapping(value="stockOut/{oId}",method=RequestMethod.PUT,produces="application/json; charset=UTF-8")
	@ResponseBody
	public void stockOut(HttpServletRequest request, HttpServletResponse response, @PathVariable("oId")String oId) throws IOException{
		Map<String, Object> paramMap = getParamMap_NullStr();
		long orderStockOut = orderService.orderStockOut(oId);
		if(orderStockOut > 0){
			String result = toJsonString(orderStockOut);
			outWrite(response, result);
		}else{
			outWrite(response, null);
		}
	}
}
