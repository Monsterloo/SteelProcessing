/*
 * Powered By [sp]
 */

package com.sp.net.entity;

import java.io.Serializable;
/**
 * 客户
 */
public class Client implements Serializable{

	private static final long serialVersionUID = 1L;

	//columns START
	/**
	 * cid
	 */
	private String cid;

	/**
	 * 客户公司名字
	 */
	private String cname;

	/**
	 * 客户联系方式
	 */
	private String ccontact;

	/**
	 * 客户公司地址
	 */
	private String caddress;

	/**
	 * 账户余额
	 */
	private String clegalperson;

	/**
	 * 记录状态
	 */
	private String state;

	//columns END

	/**
	 * cid
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}
	/**
	 * cid
	 */
	public String getCid() {
		return this.cid;
	}

	/**
	 * 客户公司名字
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}
	/**
	 * 客户公司名字
	 */
	public String getCname() {
		return this.cname;
	}

	/**
	 * 客户联系方式
	 */
	public void setCcontact(String ccontact) {
		this.ccontact = ccontact;
	}
	/**
	 * 客户联系方式
	 */
	public String getCcontact() {
		return this.ccontact;
	}

	/**
	 * 客户公司地址
	 */
	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}
	/**
	 * 客户公司地址
	 */
	public String getCaddress() {
		return this.caddress;
	}

	/**
	 * 账户余额
	 */
	public void setClegalperson(String clegalperson) {
		this.clegalperson = clegalperson;
	}
	/**
	 * 账户余额
	 */
	public String getClegalperson() {
		return this.clegalperson;
	}

	/**
	 * 记录状态
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 记录状态
	 */
	public String getState() {
		return this.state;
	}




}
