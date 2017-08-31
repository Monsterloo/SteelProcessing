/*
 * Powered By [sp]
 */

package com.sp.net.dao.impl;


import org.springframework.stereotype.Repository;

import com.sp.net.dao.impl.BaseDaoImpl;
import com.sp.net.entity.Material;
import com.sp.net.dao.MaterialDao;

@Repository("materialDao")
public class MaterialDaoImpl extends BaseDaoImpl<Material> implements MaterialDao {

}
