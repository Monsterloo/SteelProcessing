/*
 * Powered By [sp]
 */

package com.sp.net.entity;

import java.io.Serializable;
/**
 * 
 */
public class Material implements Serializable{

	private static final long serialVersionUID = 1L;

	//columns START
	/**
	 * 材料主键
	 */
	private java.lang.String mid;

	/**
	 * 材料名字
	 */
	private java.lang.String mname;

	/**
	 * 现存数量
	 */
	private java.lang.Integer mexistCount;

	/**
	 * 最低数量
	 */
	private java.lang.Integer mminCount;

	/**
	 * 材料类别
	 */
	private java.lang.Integer mcategory;

	/**
	 * 材料直径
	 */
	private java.lang.Double mdiameter;

	/**
	 * 材料长度
	 */
	private java.lang.Double mlength;

	/**
	 * 材料重量
	 */
	private java.lang.Double mweight;

	/**
	 * 状态
	 */
	private java.lang.String state;

	//columns END

	/**
	 * 材料主键
	 */
	public void setMid(java.lang.String mid) {
		this.mid = mid;
	}
	/**
	 * 材料主键
	 */
	public java.lang.String getMid() {
		return this.mid;
	}

	/**
	 * 材料名字
	 */
	public void setMname(java.lang.String mname) {
		this.mname = mname;
	}
	/**
	 * 材料名字
	 */
	public java.lang.String getMname() {
		return this.mname;
	}

	/**
	 * 现存数量
	 */
	public void setMexistCount(java.lang.Integer mexistCount) {
		this.mexistCount = mexistCount;
	}
	/**
	 * 现存数量
	 */
	public java.lang.Integer getMexistCount() {
		return this.mexistCount;
	}

	/**
	 * 最低数量
	 */
	public void setMminCount(java.lang.Integer mminCount) {
		this.mminCount = mminCount;
	}
	/**
	 * 最低数量
	 */
	public java.lang.Integer getMminCount() {
		return this.mminCount;
	}

	/**
	 * 材料类别
	 */
	public void setMcategory(java.lang.Integer mcategory) {
		this.mcategory = mcategory;
	}
	/**
	 * 材料类别
	 */
	public java.lang.Integer getMcategory() {
		return this.mcategory;
	}

	/**
	 * 材料直径
	 */
	public void setMdiameter(java.lang.Double mdiameter) {
		this.mdiameter = mdiameter;
	}
	/**
	 * 材料直径
	 */
	public java.lang.Double getMdiameter() {
		return this.mdiameter;
	}

	/**
	 * 材料长度
	 */
	public void setMlength(java.lang.Double mlength) {
		this.mlength = mlength;
	}
	/**
	 * 材料长度
	 */
	public java.lang.Double getMlength() {
		return this.mlength;
	}

	/**
	 * 材料重量
	 */
	public void setMweight(java.lang.Double mweight) {
		this.mweight = mweight;
	}
	/**
	 * 材料重量
	 */
	public java.lang.Double getMweight() {
		return this.mweight;
	}

	/**
	 * 状态
	 */
	public void setState(java.lang.String state) {
		this.state = state;
	}
	/**
	 * 状态
	 */
	public java.lang.String getState() {
		return this.state;
	}




}
