/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, gcsdylan.com
 * Filename:		com.gcs.sysmgr.dao.RolePermissionDao.java
 * Class:			RolePermissionDao
 * Date:			2013-4-16
 * Author:			<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.gcs.sysmgr.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcs.sysmgr.entity.main.RolePermission;

/** 
 * 	
 * @author 	<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version  2.0.0
 * @since   2013-4-16 下午2:10:57 
 */

public interface RolePermissionDAO extends JpaRepository<RolePermission, Long> {
	List<RolePermission> findByRoleId(Long roleId);
}
