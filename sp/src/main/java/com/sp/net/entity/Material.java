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
	private String mid;

	/**
	 * 材料名字
	 */
	private String mname;

	/**
	 * 材料来源
	 */
	private String msource;

	/**
	 * 材料单价
	 */
	private Double mprice;

	/**
	 * 现存数量
	 */
	private Integer mexistCount;

	/**
	 * 最低数量
	 */
	private Integer mminCount;

	/**
	 * 材料类别
	 */
	private Integer mcategory;

	/**
	 * 材料直径
	 */
	private Double mdiameter;

	/**
	 * 材料长度
	 */
	private Double mlength;

	/**
	 * 材料重量
	 */
	private Double mweight;

	/**
	 * 状态
	 */
	private String state;

	//columns END

	//WebModal
	//入库的中的材料数量
	private long purchaseCount;

	/**
	 * 材料主键
	 */
	public void setMid(String mid) {
		this.mid = mid;
	}
	/**
	 * 材料主键
	 */
	public String getMid() {
		return this.mid;
	}

	/**
	 * 材料名字
	 */
	public void setMname(String mname) {
		this.mname = mname;
	}
	/**
	 * 材料名字
	 */
	public String getMname() {
		return this.mname;
	}

	/**
	 * 材料来源
	 */
	public void setMsource(String msource) {
		this.msource = msource;
	}
	/**
	 * 材料来源
	 */
	public String getMsource() {
		return this.msource;
	}

	/**
	 * 材料单价
	 */
	public void setMprice(Double mprice) {
		this.mprice = mprice;
	}
	/**
	 * 材料单价
	 */
	public Double getMprice() {
		return this.mprice;
	}

	/**
	 * 现存数量
	 */
	public void setMexistCount(Integer mexistCount) {
		this.mexistCount = mexistCount;
	}
	/**
	 * 现存数量
	 */
	public Integer getMexistCount() {
		return this.mexistCount;
	}

	/**
	 * 最低数量
	 */
	public void setMminCount(Integer mminCount) {
		this.mminCount = mminCount;
	}
	/**
	 * 最低数量
	 */
	public Integer getMminCount() {
		return this.mminCount;
	}

	/**
	 * 材料类别
	 */
	public void setMcategory(Integer mcategory) {
		this.mcategory = mcategory;
	}
	/**
	 * 材料类别
	 */
	public Integer getMcategory() {
		return this.mcategory;
	}

	/**
	 * 材料直径
	 */
	public void setMdiameter(Double mdiameter) {
		this.mdiameter = mdiameter;
	}
	/**
	 * 材料直径
	 */
	public Double getMdiameter() {
		return this.mdiameter;
	}

	/**
	 * 材料长度
	 */
	public void setMlength(Double mlength) {
		this.mlength = mlength;
	}
	/**
	 * 材料长度
	 */
	public Double getMlength() {
		return this.mlength;
	}

	/**
	 * 材料重量
	 */
	public void setMweight(Double mweight) {
		this.mweight = mweight;
	}
	/**
	 * 材料重量
	 */
	public Double getMweight() {
		return this.mweight;
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

	/**
	 * 入库中的材料数量
	 */
	public long getPurchaseCount() {
		return purchaseCount;
	}
	/**
	 * 入库中的材料数量
	 */
	public void setPurchaseCount(long purchaseCount) {
		this.purchaseCount = purchaseCount;
	}


}
