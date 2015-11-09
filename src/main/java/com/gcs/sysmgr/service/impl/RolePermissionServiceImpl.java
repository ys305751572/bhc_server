/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, gcsdylan.com
 * Filename:		com.gcs.sysmgr.service.impl.RolePermissionServiceImpl.java
 * Class:			RolePermissionServiceImpl
 * Date:			2013-4-16
 * Author:			<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.gcs.sysmgr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gcs.sysmgr.dao.RolePermissionDAO;
import com.gcs.sysmgr.entity.main.RolePermission;
import com.gcs.sysmgr.service.RolePermissionService;

/** 
 * 	
 * @author 	<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version  2.0.0
 * @since   2013-4-16 下午2:14:10 
 */
@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {
	
	@Autowired
	private RolePermissionDAO rolePermissionDAO;

	/**   
	 * @param rolePermission  
	 * @see com.gcs.sysmgr.service.RolePermissionService#save(com.gcs.sysmgr.entity.main.RolePermission)  
	 */
	public void save(RolePermission rolePermission) {
		rolePermissionDAO.save(rolePermission);
	}

	/**   
	 * @param id
	 * @return  
	 * @see com.gcs.sysmgr.service.RolePermissionService#get(java.lang.Long)  
	 */
	public RolePermission get(Long id) {
		return rolePermissionDAO.findOne(id);
	}

	/**   
	 * @param rolePermission  
	 * @see com.gcs.sysmgr.service.RolePermissionService#update(com.gcs.sysmgr.entity.main.RolePermission)  
	 */
	public void update(RolePermission rolePermission) {
		rolePermissionDAO.save(rolePermission);
	}

	/**   
	 * @param id  
	 * @see com.gcs.sysmgr.service.RolePermissionService#delete(java.lang.Long)  
	 */
	public void delete(Long id) {
		rolePermissionDAO.delete(id);
	}

	/**   
	 * @param roleId
	 * @return  
	 * @see com.gcs.sysmgr.service.RolePermissionService#findByRoleId(java.lang.Long)  
	 */
	public List<RolePermission> findByRoleId(Long roleId) {
		return rolePermissionDAO.findByRoleId(roleId);
	}

}
