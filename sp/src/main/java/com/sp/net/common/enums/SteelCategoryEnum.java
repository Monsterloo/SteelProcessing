package com.sp.net.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum SteelCategoryEnum {

	GY_STEEL("光圆钢筋",0),
	
	DL_STEEL("带肋钢筋",1),
	
	NZ_STEEL("扭转钢筋",2),
	
	BX_Z_STEEL("变形钢筋(直)",3),
	
	BX_Y_STEEL("变形钢筋(圆)",4);
	
	/**
	 * 枚举值
	 */
	private int value;
	
	/**
	 * 描述
	 */
	private String desc;
	
	private SteelCategoryEnum(String desc, int value){
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
	public static SteelCategoryEnum getEnum(int value) {
		SteelCategoryEnum resultEnum = null;
		SteelCategoryEnum[] enumAry = SteelCategoryEnum.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].getValue() == value) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static List toList(){
		SteelCategoryEnum[] ary = SteelCategoryEnum.values();
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
