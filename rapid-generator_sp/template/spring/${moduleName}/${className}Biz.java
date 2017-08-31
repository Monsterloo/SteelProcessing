<#include "/java_copyright.include">
<#include "/java_imports.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign daoName = classNameLower+'Dao'>

package com.sp.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sp.entity.${className};
import com.sp.dao.${className}Dao;

@Component("${classNameLower}Biz")
public class ${className}Biz{
	@Autowired
	private ${className}Dao ${daoName};

	public ${className} getById(long id){
		return ${daoName}.getById(id);
	}

	public boolean deleteById(long id){
		return ${daoName}.deleteById(id) > 0;
	}
}
