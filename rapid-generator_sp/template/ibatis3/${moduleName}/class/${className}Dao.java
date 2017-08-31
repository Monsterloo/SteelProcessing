<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package com.sp.net.dao;

<#include "/java_imports.include">

import com.sp.net.dao.BaseDao;
import com.sp.net.entity.${className};


public interface ${className}Dao extends BaseDao<${className}>{

}
