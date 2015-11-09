/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, gcsdylan.com
 * Filename:		com.gcs.sysmgr.service.impl.RoleServiceImpl.java
 * Class:			RoleServiceImpl
 * Date:			2012-8-7
 * Author:			<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.gcs.sysmgr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gcs.sysmgr.dao.RoleDAO;
import com.gcs.sysmgr.entity.main.Role;
import com.gcs.sysmgr.service.RoleService;
//import com.gcs.sysmgr.shiro.ShiroDbRealm;
import com.gcs.sysmgr.util.dwz.Page;
import com.gcs.sysmgr.util.dwz.PageUtils;

/** 
 * 	
 * @author 	<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version  1.1.0
 * @since   2012-8-7 下午5:04:52 
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDAO roleDAO;
	
//	@Autowired(required = false)
//	private ShiroDbRealm shiroRealm;
	
	public void save(Role role) {
		roleDAO.save(role);
	}

	public Role get(Long id) {
		return roleDAO.findOne(id);
	}
	
	public List<Role> findAll(Page page) {
		org.springframework.data.domain.Page<Role> springDataPage = roleDAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	/**   
	 * @param role  
	 * @see com.gcs.sysmgr.service.RoleService#update(com.gcs.sysmgr.entity.main.Role)  
	 */
	public void update(Role role) {
		roleDAO.save(role);
		//shiroRealm.clearAllCachedAuthorizationInfo();
	}

	/**   
	 * @param id  
	 * @see com.gcs.sysmgr.service.RoleService#delete(java.lang.Long)  
	 */
	public void delete(Long id) {
		roleDAO.delete(id);
		//shiroRealm.clearAllCachedAuthorizationInfo();
	}

	/**   
	 * @param page
	 * @param name
	 * @return  
	 * @see com.gcs.sysmgr.service.RoleService#find(com.gcs.sysmgr.util.dwz.Page, java.lang.String)  
	 */
	public List<Role> find(Page page, String name) {
		org.springframework.data.domain.Page<Role> springDataPage = 
				(org.springframework.data.domain.Page<Role>)roleDAO.findByNameContaining(name, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	public List<Role> findAll() {
		return roleDAO.findAll();
	}

}
