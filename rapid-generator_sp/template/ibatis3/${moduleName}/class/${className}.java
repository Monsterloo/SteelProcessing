<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package com.sp.net.entity;

import java.io.Serializable;
<#include "/java_imports.include">
/**
 * ${table.remarks}
 */
public class ${className} implements Serializable{

	private static final long serialVersionUID = 1L;

	//columns START
	<#list table.columns as column>
		<#if column.columnNameLower != 'id' && column.columnNameLower != 'version' && column.columnNameLower != 'createTime'>
	/**
	 * ${column.columnAlias}
	 */
	private ${column.javaType} ${column.columnNameLower};

		</#if>
	</#list>
	//columns END

<@generateJavaColumns/>


<#macro generateJavaColumns>
	<#list table.columns as column>
		<#if column.columnNameLower != 'id' && column.columnNameLower != 'version' && column.columnNameLower != 'createTime'>
	/**
	 * ${column.columnAlias}
	 */
	public void set${column.columnName}(${column.javaType} ${column.columnNameLower}) {
		this.${column.columnNameLower} = ${column.columnNameLower};
	}
	/**
	 * ${column.columnAlias}
	 */
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}

		</#if>
	</#list>
</#macro>

}
