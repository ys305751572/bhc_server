/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, gcsdylan.com
 * Filename:		com.gcs.sysmgr.service.UserRoleService.java
 * Class:			UserRoleService
 * Date:			2012-8-7
 * Author:			<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.gcs.sysmgr.service;

import java.util.List;

import com.gcs.sysmgr.entity.main.UserRole;

/** 
 * 	
 * @author 	<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version  1.1.0
 * @since   2012-8-7 下午5:08:51 
 */

public interface UserRoleService {
	UserRole get(Long id);
	
	/**
	 * 根据userId，找到已分配的角色。
	 * 描述
	 * @param userId
	 * @return
	 */
	List<UserRole> find(Long userId);

	void save(UserRole userRole);

	void delete(Long userRoleId);

}
