/*
 * Powered By [sp]
 */

package com.sp.net.entity;

import java.io.Serializable;
/**
 * 
 */
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;

	//columns START
	/**
	 * 成品主键
	 */
	private String pid;

	/**
	 * 成品名称
	 */
	private String productName;

	/**
	 * 图片地址
	 */
	private String imageUrl;

	/**
	 * 单价
	 */
	private Double price;

	/**
	 * 材料主键
	 */
	private String mid;

	/**
	 * 材料数量
	 */
	private java.lang.Integer materialCount;

	/**
	 * 状态
	 */
	private String state;
	
	/**
	 * 材料对象
	 */
	private Material material;

	//columns END

	/**
	 * 成品主键
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * 成品主键
	 */
	public String getPid() {
		return this.pid;
	}

	/**
	 * 成品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 成品名称
	 */
	public String getProductName() {
		return this.productName;
	}

	/**
	 * 图片地址
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * 图片地址
	 */
	public String getImageUrl() {
		return this.imageUrl;
	}

	/**
	 * 单价
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * 单价
	 */
	public Double getPrice() {
		return this.price;
	}

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
	 * 材料数量
	 */
	public void setMaterialCount(java.lang.Integer materialCount) {
		this.materialCount = materialCount;
	}
	/**
	 * 材料数量
	 */
	public java.lang.Integer getMaterialCount() {
		return this.materialCount;
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
	
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}


}
