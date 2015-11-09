/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, gcsdylan.com
 * Filename:		com.gcs.sysmgr.service.impl.OrganizationRoleServiceImpl.java
 * Class:			OrganizationRoleServiceImpl
 * Date:			2013-4-15
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

import com.gcs.sysmgr.dao.OrganizationRoleDAO;
import com.gcs.sysmgr.entity.main.OrganizationRole;
import com.gcs.sysmgr.service.OrganizationRoleService;

/** 
 * 	
 * @author 	<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version  2.0.0
 * @since   2013-4-15 下午4:16:04 
 */
@Service
@Transactional
public class OrganizationRoleServiceImpl implements OrganizationRoleService {
	
	private OrganizationRoleDAO organizationRoleDAO;
	
	/**
	 * 
	 * 构造函数
	 * @param organizationRoleDAO
	 */
	@Autowired
	public OrganizationRoleServiceImpl(OrganizationRoleDAO organizationRoleDAO) {
		this.organizationRoleDAO = organizationRoleDAO;
	}
	
	/**   
	 * @param id
	 * @return  
	 * @see com.gcs.sysmgr.service.OrganizationRoleService#get(java.lang.Long)  
	 */
	public OrganizationRole get(Long id) {
		return organizationRoleDAO.findOne(id);
	}

	/**   
	 * @param organizationId
	 * @return  
	 * @see com.gcs.sysmgr.service.OrganizationRoleService#find(java.lang.Long)  
	 */
	public List<OrganizationRole> find(Long organizationId) {
		return organizationRoleDAO.findByOrganizationId(organizationId);
	}

	/**   
	 * @param organizationRole  
	 * @see com.gcs.sysmgr.service.OrganizationRoleService#save(com.gcs.sysmgr.entity.main.OrganizationRole)  
	 */
	public void save(OrganizationRole organizationRole) {
		organizationRoleDAO.save(organizationRole);
	}

	/**   
	 * @param organizationRoleId  
	 * @see com.gcs.sysmgr.service.OrganizationRoleService#delete(java.lang.Long)  
	 */
	public void delete(Long organizationRoleId) {
		organizationRoleDAO.delete(organizationRoleId);
	}

}
