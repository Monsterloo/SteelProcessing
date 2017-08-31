package com.sp.net.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum RoleEnum {
	
	NORMAL_ADMIN("普通管理员",0),
	
	SPUER_ADMIN("超级管理员",1);
	
	/**
	 * 枚举值
	 */
	private int value;
	
	/**
	 * 描述
	 */
	private String desc;
	
	private RoleEnum(String desc, int value){
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
	public static RoleEnum getEnum(int value) {
		RoleEnum resultEnum = null;
		RoleEnum[] enumAry = RoleEnum.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].getValue() == value) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static List toList(){
		RoleEnum[] ary = RoleEnum.values();
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
