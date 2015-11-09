/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, gcsdylan.com
 * Filename:		com.gcs.sysmgr.service.impl.RolePermissionImpl.java
 * Class:			RolePermissionImpl
 * Date:			2013-4-16
 * Author:			<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.gcs.sysmgr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gcs.sysmgr.dao.PermissionDAO;
import com.gcs.sysmgr.entity.main.Permission;
import com.gcs.sysmgr.service.PermissionService;

/** 
 * 	
 * @author 	<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version  2.0.0
 * @since   2013-4-16 下午2:12:41 
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService{
	
	@Autowired
	private PermissionDAO permissionDAO;

	/**   
	 * @param permission  
	 * @see com.gcs.sysmgr.service.PermissionService#save(com.gcs.sysmgr.entity.main.Permission)  
	 */
	public void save(Permission permission) {
		permissionDAO.save(permission);
	}

	/**   
	 * @param id
	 * @return  
	 * @see com.gcs.sysmgr.service.PermissionService#get(java.lang.Long)  
	 */
	public Permission get(Long id) {
		return permissionDAO.findOne(id);
	}

	/**   
	 * @param permission  
	 * @see com.gcs.sysmgr.service.PermissionService#update(com.gcs.sysmgr.entity.main.Permission)  
	 */
	public void update(Permission permission) {
		permissionDAO.save(permission);
	}

	/**   
	 * @param id  
	 * @see com.gcs.sysmgr.service.PermissionService#delete(java.lang.Long)  
	 */
	public void delete(Long id) {
		permissionDAO.delete(id);
	}
}
