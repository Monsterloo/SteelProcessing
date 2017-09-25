package com.sp.net.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSONObject;
import com.sp.net.entity.page.PageParam;
import com.sp.net.utils.string.StringUtil;

public class BaseController extends HttpServlet{
	
	protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected HttpSession session;

	private static final String UTF_8 = "utf-8";

	private static final String GBK = "GBK";
	
	private static ThreadLocal<Map<String, Object>> outPutMsg = new ThreadLocal<Map<String, Object>>();
	
	 private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	private Integer numPerPage;
	
	public Integer pageNum;
	
	public void setNumPerPage(Integer numPerPage) {
		this.numPerPage = numPerPage;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
	
	   this.request = request;
	   
       this.response = response;

       this.session = request.getSession();
	
	}
	
	/**
	 * 增加获取字符串参数方法
	 * @param request
	 * @param para
	 * @return
	 */
	protected String getParamStr(HttpServletRequest request, String para){
		return StringUtils.isBlank(request.getParameter(para)) ? request
				.getParameter(para) : "";
	}
	
	protected void renderJson(HttpServletResponse response,String jsonResponse){
	    response.setContentType("text/html");
		PrintWriter printWriter = null;
		try {
		    response.setContentType("text/html");
			printWriter = response.getWriter();
			printWriter.write(jsonResponse);
			printWriter.flush();
		} catch (IOException e) {
			logger.error("[CONT] /BaseController/rederJson", e);
		} finally {
			if (printWriter != null) {
				IOUtils.closeQuietly(printWriter);
			}
		}
	}
	
	protected Map<String, String> getRequestParams(HttpServletRequest request,String escape) {  
        Map<String, String> map = new HashMap<String, String>();  
        Enumeration paramNames = request.getParameterNames();  
        while (paramNames.hasMoreElements()) {  
            String paramName = (String) paramNames.nextElement();  
            if(escape!=null){
            	  if(!paramName.equals(escape)){
      	            String[] paramValues = request.getParameterValues(paramName);  
      	            if (paramValues.length == 1) {  
      	                String paramValue = paramValues[0];  
      	                if (paramValue.length() != 0) {  
      	                    map.put(paramName, paramValue);  
      	                }  
      	            }  
                  }
            }
	            String[] paramValues = request.getParameterValues(paramName);  
	            if (paramValues.length == 1) {  
	                String paramValue = paramValues[0];  
	                if (paramValue.length() != 0) {  
	                    map.put(paramName, paramValue);  
	                }  
            }
        }  
        return map;
    }

	/**
	 * 线程绑定，其内容会在outPrint方法调用后清空
	 * 
	 * @return the outputMsg
	 */
	public Map<String, Object> getOutputMsg() {
		Map<String, Object> output = outPutMsg.get();
		if (output == null) {
			output = new HashMap<String, Object>();
			outPutMsg.set(output);
		}
		return output;
	}
	
	/**
	 * 输出，同时清空outPutMsg
	 * 
	 * @param response
	 * @param result
	 * @throws IOException 
	 */
	public void outPrint(HttpServletResponse response, Object result) throws IOException {
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			out.print(result.toString());
			getOutputMsg().clear();
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
		    if (out != null) {
		        out.close();
		    }
		}
	}
	
	/**
	 * 输出，直接输入结果数据
	 * 
	 * @param response
	 * @param result
	 * @throws IOException 
	 */
	public void outWrite(HttpServletResponse response, Object result) throws IOException {
		PrintWriter out = null;
		try {
			response.setContentType("application/json;charset=UTF-8");//防止数据传递乱码
			response.setHeader("Access-Control-Allow-Origin", "*");
			out = response.getWriter();
			if(result == null){
				out.write("");
			}else{
				out.write(result.toString());
			}
			out.close();
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
		    if (out != null) {
		        out.close();
		    }
		}
	}
	
	public void outPrintResult(HttpServletResponse response,String returnStr) {
		try {
			response.setContentType("text/plain; charset=utf-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.getWriter().print(returnStr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 取得当前request
	 * 
	 * @return
	 */
	public HttpServletRequest getHttpRequest() {
		return request;
	}

	/**
	 * 取得当前的HttpSession
	 * 
	 * @return HttpSession
	 */
	public HttpSession getHttpSession() {
		return session;
	}
	
	/**
	 * 取得当前response
	 * 
	 * @return
	 */
	public HttpServletResponse getHttpResponse() {
		return response;
	}

	/**
	 * 获取session里面的属性
	 * 
	 * @return
	 */
	public Map<String, Object> getSessionMap() {
		return (Map<String, Object>) session.getAttributeNames();
	}
	
	// ///////////////////////////////////////////////////////////////
	// /////////////////分页参数获取方法 ////////////////////////////
	/**
	 * 获取当前页（分页查询参数）.<br/>
	 * 如果没有值则默认返回1.
	 * 
	 * @author WuShuicheng.
	 */
	private int getPageNum() {
		// 当前页数
		String pageNumStr = getHttpRequest().getParameter("pageNum");
		int pageNum = 1;
		if (StringUtils.isNotBlank(pageNumStr)) {
			pageNum = Integer.valueOf(pageNumStr);
		}
		return pageNum;
	}

	/**
	 * 获取每页记录数（分页查询参数）.<br/>
	 * 如果没有值则默认返回10.
	 * 
	 * @author WuShuicheng.
	 */
	private int getNumPerPage() {
		String numPerPageStr = getHttpRequest().getParameter("numPerPage");
		int numPerPage = 10;
		if (StringUtils.isNotBlank(numPerPageStr)) {
			numPerPage = Integer.parseInt(numPerPageStr);
		}
		return numPerPage;
	}

	/**
	 * 获取分页参数，包含当前页、每页记录数.
	 * 
	 * @return PageParam .
	 */
	public PageParam getPageParam() {
		return new PageParam(getPageNum(), getNumPerPage());
	}
	
	// //////////////////////// 存值方法 /////////////////////////////////
	/**
	 * 将数据放入上下文的值栈中.<br/>
	 * ActionContext.getContext().getValueStack().push(obj);
	 */
	public void pushData(Object obj) {
	}
	
	/**
	 * 将数据放入session中.<br/>
	 */
	public void putData(String key, Object value) {
		getHttpSession().setAttribute(key, value);
	}
	
	// ///////////////////////getHttpRequest()方法扩展//////////////////////////
	/**
	 * 根据参数名从HttpRequest中获取Double类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return DoubleValue or null .
	 */
	public Double getDouble(String key) {
		String value = getHttpRequest().getParameter(key);
		if (StringUtils.isNotBlank(value)) {
			return Double.parseDouble(value.trim());
		}
		return null;
	}

	/**
	 * 根据参数名从HttpRequest中获取Integer类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return IntegerValue or null .
	 */
	public Integer getInteger(String key) {
		String value = getHttpRequest().getParameter(key);
		if (StringUtils.isNotBlank(value)) {
			return Integer.parseInt(value.trim());
		}
		return null;
	}

	/**
	 * 根据参数名从HttpRequest中获取Long类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return LongValue or null .
	 */
	public Long getLong(String key) {
		String value = getHttpRequest().getParameter(key);
		if (StringUtils.isNotBlank(value)) {
			return Long.parseLong(value.trim());
		}
		return null;
	}

	/**
	 * 根据参数名从HttpRequest中获取String类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return String or null .
	 */
	public String getString(String key) {
		String param = getHttpRequest().getParameter(key);
		if (StringUtil.isEmpty(param)) {
			return param;
		} else {
			return param.trim();
		}
	}
	
	/**
	 * 根据参数名从HttpRequest中获取String[] 类型的参数值 有 返回字符串数组 无 返回null;
	 * 
	 * @param key
	 *            .
	 * @return String[] or null .
	 */
	public String[] getStringArr(String key) {
		return getHttpRequest().getParameterValues(key);
	}
	
	/**
	 * 获取请求中的参数值
	 * 
	 * @return
	 */
	public Map<String, Object> getParamMap() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Map map = getHttpRequest().getParameterMap();
		Set keys = map.keySet();
		for (Object key : keys) {
			parameters.put(key.toString(), this.getString(key.toString()));
		}
		return parameters;
	}
	
	/**
	 * 获取请求中的参数值，如果参数值为null刚转为空字符串""
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public Map<String, Object> getParamMap_NullStr() throws UnsupportedEncodingException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Map map = getHttpRequest().getParameterMap();
		Set keys = map.keySet();
		for (Object key : keys) {
			/*byte [] b= this.getString(key.toString()).getBytes("ISO-8859-1");
			String value = new String(b,"utf-8");*/
			String value = URLDecoder.decode(this.getString(key.toString()),"utf-8");
			//String value = this.getString(key.toString());
			if (value == null) {
				value = "";
			}
			parameters.put(key.toString(), value);
		}
		return parameters;
	}
	
	
	// ///////////////////////JSON方法扩展//////////////////////////
	/**
	 * 将一个对象转换成json字符串
	 * 
	 * @param obj
	 * @return
	 */
	protected String toJsonString(Object obj) {
		String result = JSONObject.toJSONString(obj);
		logger.debug("convert Java Object=" + obj + " to json string=" + result);
		return result;
	}
	
	/**
	 * 将一个json字符串装换成一个java对象
	 * 
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	protected <T> T parseJsonString(String jsonString, Class<T> clazz) {
		T t = JSONObject.parseObject(jsonString, clazz);
		logger.debug("convert jsonString=" + jsonString + " to Java Object=" + t);
		return t;
	}

    /**
     * 封装好的数据map转成Json
     * @param response
     * @param data
     */
    protected void renderMapToJson(HttpServletResponse response, Map<String, Object> data){
         String   jsonResponse = JSONObject.toJSONString(data);
        renderJson(response, jsonResponse);
    }
    
}
