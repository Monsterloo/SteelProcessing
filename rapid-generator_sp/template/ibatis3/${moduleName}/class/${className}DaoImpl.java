<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package com.sp.net.dao.impl;

<#include "/java_imports.include">

import org.springframework.stereotype.Repository;

import com.sp.net.dao.impl.BaseDaoImpl;
import com.sp.net.entity.${className};
import com.sp.net.dao.${className}Dao;

@Repository("${classNameLower}Dao")
public class ${className}DaoImpl extends BaseDaoImpl<${className}> implements ${className}Dao {

}
