/*
 * Powered By [sp]
 */

package com.sp.net.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 */
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;

	//columns START
	/**
	 * 订单ID
	 */
	private String oid;

	/**
	 * 项目名字
	 */
	private String projectName;

	/**
	 * 公司主键
	 */
	private String cid;

	/**
	 * 钢筋图主键
	 */
	private String sid;

	/**
	 * 管理员主键
	 */
	private String aid;

	/**
	 * 单价
	 */
	private Double price;

	/**
	 * 数量
	 */
	private Integer count;

	/**
	 * 钢筋直径
	 */
	private Double diameter;

	/**
	 * 到期日期
	 */
	private Date dueDate;

	/**
	 * 总长度
	 */
	private Double totalLength;

	/**
	 * totalWeight
	 */
	private Double totalWeight;

	/**
	 * totalPrice
	 */
	private Double totalPrice;

	/**
	 * 创建时间
	 */
	private Date createtime;

	/**
	 * 修改时间
	 */
	private Date modifytime;

	/**
	 * 订单状态
	 */
	private String orderState;

	/**
	 * 状态
	 */
	private String state;

	//columns END
	
	//操作人
	private Admin admin;
	
	//公司
	private Client client;
	
	//钢筋简图
	private SteelShape steelShape;

	/**
	 * 订单ID
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}
	/**
	 * 订单ID
	 */
	public String getOid() {
		return this.oid;
	}

	/**
	 * 项目名字
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * 项目名字
	 */
	public String getProjectName() {
		return this.projectName;
	}

	/**
	 * 公司主键
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}
	/**
	 * 公司主键
	 */
	public String getCid() {
		return this.cid;
	}

	/**
	 * 钢筋图主键
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}
	/**
	 * 钢筋图主键
	 */
	public String getSid() {
		return this.sid;
	}

	/**
	 * 管理员主键
	 */
	public void setAid(String aid) {
		this.aid = aid;
	}
	/**
	 * 管理员主键
	 */
	public String getAid() {
		return this.aid;
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
	 * 钢筋直径
	 */
	public void setDiameter(Double diameter) {
		this.diameter = diameter;
	}
	/**
	 * 钢筋直径
	 */
	public Double getDiameter() {
		return this.diameter;
	}

	/**
	 * 到期日期
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * 到期日期
	 */
	public Date getDueDate() {
		return this.dueDate;
	}

	/**
	 * 总长度
	 */
	public void setTotalLength(Double totalLength) {
		this.totalLength = totalLength;
	}
	/**
	 * 总长度
	 */
	public Double getTotalLength() {
		return this.totalLength;
	}

	/**
	 * totalWeight
	 */
	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}
	/**
	 * totalWeight
	 */
	public Double getTotalWeight() {
		return this.totalWeight;
	}

	/**
	 * totalPrice
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * totalPrice
	 */
	public Double getTotalPrice() {
		return this.totalPrice;
	}

	/**
	 * 创建时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 创建时间
	 */
	public Date getCreatetime() {
		return this.createtime;
	}

	/**
	 * 修改时间
	 */
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	/**
	 * 修改时间
	 */
	public Date getModifytime() {
		return this.modifytime;
	}

	/**
	 * 订单状态
	 */
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	/**
	 * 订单状态
	 */
	public String getOrderState() {
		return this.orderState;
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
	
	//操作人
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	//公司
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	//钢筋简图
	public SteelShape getSteelShape() {
		return steelShape;
	}
	public void setSteelShape(SteelShape steelShape) {
		this.steelShape = steelShape;
	}

	


}
