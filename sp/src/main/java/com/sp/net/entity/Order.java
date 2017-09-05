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
	 * 产品主键
	 */
	private String pid;
	
	/**
	 * 管理员主键
	 */
	private String aid;

	/**
	 * 数量
	 */
	private Integer count;

	/**
	 * 单价
	 */
	private Double price;

	/**
	 * 到期日期
	 */
	private Date dueDate;

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

	/**
	 * 钢筋直径
	 */
	private Double diameter;

	//columns END
	
	//公司
	private Client client;
	
	//材料
	private Product product;
	
	//管理员
	private Admin admin;

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
	 * 产品主键
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * 产品主键
	 */
	public String getPid() {
		return this.pid;
	}
	
	/**
	 * 管理员主键
	 */
	public void setAid(java.lang.String aid) {
		this.aid = aid;
	}
	/**
	 * 管理员主键
	 */
	public String getAid() {
		return this.aid;
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
	
	//公司
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	//钢筋
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	//管理员
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	

}
