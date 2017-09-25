/*
 * Powered By [sp]
 */

package com.sp.net.entity;

import java.io.Serializable;
import java.util.Date;

import com.sp.net.common.enums.RoleEnum;
import com.sp.net.common.enums.WarehouseStateEnum;
import com.sp.net.utils.BlankUtil;
/**
 * 
 */
public class WarehouseRecord implements Serializable{

	private static final long serialVersionUID = 1L;

	//columns START
	/**
	 * 入库主键
	 */
	private String wid;

	/**
	 * 材料主键
	 */
	private String mid;

	/**
	 * 采购数量
	 */
	private Integer purchaseCount;

	/**
	 * 材料类型
	 */
	private Integer materialCategory;
	
	/**
	 * 入库单价
	 */
	private Double price;

	/**
	 * 采购状态
	 */
	private String purchaseState;
	
	/**
	 * 创建时间
	 */
	private Date createtime;
	
	/**
	 * 修改时间
	 */
	private Date modifytime;

	/**
	 * 记录状态
	 */
	private String state;
	
	//记录对应的材料
	private Material material;

	//columns END
	
	//WebModal
	//采购状态显示
	private String purchaseStateIndex;

	/**
	 * 入库主键
	 */
	public void setWid(String wid) {
		this.wid = wid;
	}
	/**
	 * 入库主键
	 */
	public String getWid() {
		return this.wid;
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
	 * 采购数量
	 */
	public void setPurchaseCount(Integer purchaseCount) {
		this.purchaseCount = purchaseCount;
	}
	/**
	 * 采购数量
	 */
	public Integer getPurchaseCount() {
		return this.purchaseCount;
	}

	/**
	 * 材料类型
	 */
	public void setMaterialCategory(Integer materialCategory) {
		this.materialCategory = materialCategory;
	}
	/**
	 * 材料类型
	 */
	public Integer getMaterialCategory() {
		return this.materialCategory;
	}
	
	/**
	 * 入库单价
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * 入库单价
	 */
	public Double getPrice() {
		return this.price;
	}

	/**
	 * 采购状态
	 */
	public void setPurchaseState(String purchaseState) {
		this.purchaseState = purchaseState;
	}
	/**
	 * 采购状态
	 */
	public String getPurchaseState() {
		return this.purchaseState;
	}
	
	/**
	 * 创建时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	
	/**
	 * 创建时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	/**
	 * 修改时间
	 */
	public Date getModifytime() {
		return modifytime;
	}

	/**
	 * 修改时间
	 */
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
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
	
	public Material getMaterial() {
		return material;
	}
	
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	public String getPurchaseStateIndex() {
		if(BlankUtil.isNotBlank(this.purchaseState)){
			WarehouseStateEnum stateEnum = WarehouseStateEnum.getEnum(Integer.parseInt(this.purchaseState));
			return stateEnum.getDesc();
		}else{
			return "";
		}
	}
	
	public void setPurchaseStateIndex(String purchaseStateIndex) {
		this.purchaseStateIndex = purchaseStateIndex;
	}
	
	@Override
	public String toString() {
		return "WarehouseRecord [wid=" + wid + ", mid=" + mid + ", purchaseCount=" + purchaseCount
				+ ", materialCategory=" + materialCategory + ", purchaseState=" + purchaseState + ", createtime="
				+ createtime + ", modifytime=" + modifytime + ", state=" + state + ", material=" + material
				+ ", purchaseStateIndex=" + purchaseStateIndex + "]";
	}
	
	
	
}
