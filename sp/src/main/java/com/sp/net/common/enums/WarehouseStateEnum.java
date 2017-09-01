package com.sp.net.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum WarehouseStateEnum {
	
	IS_WAREHOUSED("已入库",0),
	
	IS_NOT_WAREHOUSED("未入库",1);

	/**
	 * 枚举值
	 */
	private int value;
	
	/**
	 * 描述
	 */
	private String desc;
	
	private WarehouseStateEnum(String desc, int value){
		this.desc = desc;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	public static WarehouseStateEnum getEnum(int value) {
		WarehouseStateEnum resultEnum = null;
		WarehouseStateEnum[] enumAry = WarehouseStateEnum.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].getValue() == value) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static List toList(){
		WarehouseStateEnum[] ary = WarehouseStateEnum.values();
		List list = new ArrayList();
		for (int i = 0; i < ary.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("value", String.valueOf(ary[i].getValue()));
			map.put("desc", ary[i].getDesc());
			list.add(map);
		}
		return list;
	}
}
