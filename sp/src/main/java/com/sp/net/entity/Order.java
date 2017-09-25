/*
 * Powered By [sp]
 */

package com.sp.net.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
	 * 管理员主键
	 */
	private String aid;

	/**
	 * 到期日期
	 */
	private Date dueDate;

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
	
	//订金
	private Double deposit;

	//操作人
	private Admin admin;
	
	//公司
	private Client client;
	
	//订单明细
	private List<OrderDetail> orderDetailList;

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
	
	//订金
	public Double getDeposit() {
		if(totalPrice != null){
			return totalPrice == 0.0? 0.0 : new BigDecimal(totalPrice*0.2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		return 0.0;
	}
	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}
	
	//Vo
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}
	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

}
