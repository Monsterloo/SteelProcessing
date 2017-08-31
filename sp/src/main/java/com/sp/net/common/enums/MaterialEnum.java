package com.sp.net.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum MaterialEnum {
	
	RAW_MATERIAL("原材料", 0),
	
	SEMI_FINISHED_MATERIAL("半成品", 1),
	
	FINISHED_MATERIAL("成品", 2);

	/**
	 * 枚举值
	 */
	private int value;
	
	/**
	 * 描述
	 */
	private String desc;
	
	private MaterialEnum(String desc, int value){
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
	public static MaterialEnum getEnum(int value) {
		MaterialEnum resultEnum = null;
		MaterialEnum[] enumAry = MaterialEnum.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].getValue() == value) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static List toList(){
		MaterialEnum[] ary = MaterialEnum.values();
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
