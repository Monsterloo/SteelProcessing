/*
 * Powered By [sp]
 */

package com.sp.net.entity;

import java.io.Serializable;
/**
 * 
 */
public class Steel implements Serializable{

	private static final long serialVersionUID = 1L;

	//columns START
	/**
	 * 钢筋形状主键
	 */
	private String sid;

	/**
	 * 图片路径
	 */
	private String imageUrl;

	/**
	 * 公式
	 */
	private String formula;

	/**
	 * 状态
	 */
	private String state;

	//columns END

	/**
	 * 钢筋形状主键
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}
	/**
	 * 钢筋形状主键
	 */
	public String getSid() {
		return this.sid;
	}

	/**
	 * 图片路径
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * 图片路径
	 */
	public String getImageUrl() {
		return this.imageUrl;
	}

	/**
	 * 公式
	 */
	public void setFormula(String formula) {
		this.formula = formula;
	}
	/**
	 * 公式
	 */
	public String getFormula() {
		return this.formula;
	}

	/**
	 * 状态
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 状态
	 */
	public String getState() {
		return this.state;
	}




}
