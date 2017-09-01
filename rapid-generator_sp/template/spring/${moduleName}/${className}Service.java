<#include "/java_copyright.include">
<#include "/java_imports.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>

package com.sp.net.service;

import com.sp.net.entity.${className};

public interface ${className}Service<${className}> extends BaseService<${className}>{

}
