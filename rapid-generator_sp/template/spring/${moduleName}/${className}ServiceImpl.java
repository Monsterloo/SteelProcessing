<#include "/java_copyright.include">
<#include "/java_imports.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>

package com.sp.net.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sp.net.service.${className}Service;

@Component("${classNameLower}Service")
@Transactional
public class ${className}ServiceImpl implements ${className}Service<${className}>{
	
}
