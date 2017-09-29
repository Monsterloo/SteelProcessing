/*
 * Powered By [sp]
 */

package com.sp.net.entity;

import java.io.Serializable;
/**
 * 
 */
public class OrderDetail implements Serializable{

	private static final long serialVersionUID = 1L;

	//columns START
	/**
	 * 订单明细主键
	 */
	private String did;

	/**
	 * 订单主键
	 */
	private String oid;

	/**
	 * 钢筋主键
	 */
	private String sid;

	/**
	 * 钢筋半径
	 */
	private Double diametre;

	/**
	 * 钢筋单价
	 */
	private Double price;

	/**
	 * 钢筋种类
	 */
	private String steelCategory;
	
	/**
	 * 部件长度
	 */
	private String partsLength;

	/**
	 * 长度
	 */
	private Double length;

	/**
	 * 数量
	 */
	private Integer count;

	/**
	 * 重量
	 */
	private Double weight;

	/**
	 * 总价
	 */
	private Double totalPrice;

	/**
	 * 状态
	 */
	private String state;

	//columns END
	
	//订单
	private Order order;
	
	//钢筋
	private Steel steel;

	/**
	 * 订单明细主键
	 */
	public void setDid(String did) {
		this.did = did;
	}
	/**
	 * 订单明细主键
	 */
	public String getDid() {
		return this.did;
	}

	/**
	 * 订单主键
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}
	/**
	 * 订单主键
	 */
	public String getOid() {
		return this.oid;
	}

	/**
	 * 钢筋主键
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}
	/**
	 * 钢筋主键
	 */
	public String getSid() {
		return this.sid;
	}

	/**
	 * 钢筋半径
	 */
	public void setDiametre(Double diametre) {
		this.diametre = diametre;
	}
	/**
	 * 钢筋半径
	 */
	public Double getDiametre() {
		return this.diametre;
	}

	/**
	 * 钢筋单价
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * 钢筋单价
	 */
	public Double getPrice() {
		return this.price;
	}

	/**
	 * 钢筋种类
	 */
	public void setSteelCategory(String steelCategory) {
		this.steelCategory = steelCategory;
	}
	/**
	 * 钢筋种类
	 */
	public String getSteelCategory() {
		return this.steelCategory;
	}
	
	/**
	 * 部件长度
	 */
	public void setPartsLength(String partsLength) {
		this.partsLength = partsLength;
	}
	/**
	 * 部件长度
	 */
	public String getPartsLength() {
		return this.partsLength;
	}

	/**
	 * 长度
	 */
	public void setLength(Double length) {
		this.length = length;
	}
	/**
	 * 长度
	 */
	public Double getLength() {
		return this.length;
	}

	/**
	 * 数量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 数量
	 */
	public Integer getCount() {
		return this.count;
	}

	/**
	 * 重量
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	/**
	 * 重量
	 */
	public Double getWeight() {
		return this.weight;
	}

	/**
	 * 总价
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * 总价
	 */
	public Double getTotalPrice() {
		return this.totalPrice;
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
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Steel getSteel() {
		return steel;
	}
	public void setSteel(Steel steel) {
		this.steel = steel;
	}


}
