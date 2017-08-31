/*
 * Powered By [sp]
 */

package com.sp.net.dao.impl;


import org.springframework.stereotype.Repository;

import com.sp.net.dao.impl.BaseDaoImpl;
import com.sp.net.entity.Client;
import com.sp.net.dao.ClientDao;

@Repository("clientDao")
public class ClientDaoImpl extends BaseDaoImpl<Client> implements ClientDao {

}
