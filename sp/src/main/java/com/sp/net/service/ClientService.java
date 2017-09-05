/*
 * Powered By [sp]
 */

package com.sp.net.service;

import java.util.List;
import java.util.Map;

import com.sp.net.entity.Client;
import com.sp.net.entity.page.PageBean;
import com.sp.net.entity.page.PageParam;

public interface ClientService extends BaseService<Client>{
	
	public long batchDelete(List<String> cIds);

	public long insert(Client client);
	
}
