/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, gcsdylan.com
 * Filename:		com.gcs.sysmgr.service.impl.OrganizationServiceImpl.java
 * Class:			OrganizationServiceImpl
 * Date:			2012-8-27
 * Author:			<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.gcs.sysmgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gcs.sysmgr.dao.OrganizationDAO;
import com.gcs.sysmgr.dao.UserDAO;
import com.gcs.sysmgr.entity.main.Organization;
import com.gcs.sysmgr.entity.main.User;
import com.gcs.sysmgr.exception.ServiceException;
import com.gcs.sysmgr.service.OrganizationService;
import com.gcs.sysmgr.util.dwz.Page;
import com.gcs.sysmgr.util.dwz.PageUtils;

/** 
 * 	
 * @author 	<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version  1.1.0
 * @since   2012-8-27 下午3:56:46 
 */
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private OrganizationDAO organizationDAO;
	
	public void save(Organization organization) {
		organizationDAO.save(organization);
	}

	public Organization get(Long id) {
		return organizationDAO.findOne(id);
	}

	public void update(Organization organization) {
		organizationDAO.save(organization);
	}

	/**   
	 * @param id
	 * @throws ServiceException  
	 * @see com.gcs.sysmgr.service.OrganizationService#delete(java.lang.Long)  
	 */
	public void delete(Long id) throws ServiceException {
		if (isRoot(id)) {
			throw new ServiceException("不允许删除根组织。");
		}
		
		Organization organization = this.get(id);
		
		//先判断是否存在子模块，如果存在子模块，则不允许删除
		if(organization.getChildren().size() > 0){
			throw new ServiceException(organization.getName() + "组织下存在子组织，不允许删除。");
		}
		
		if (userDAO.findByOrganizationId(id).size() > 0) {
			throw new ServiceException(organization.getName() + "组织下存在用户，不允许删除。");
		}
		
		organizationDAO.delete(organization);
	}

	/**   
	 * @param parentId
	 * @param page
	 * @return  
	 * @see com.gcs.sysmgr.service.OrganizationService#find(java.lang.Long, com.gcs.sysmgr.util.dwz.Page)  
	 */
	public List<Organization> find(Long parentId, Page page) {
		org.springframework.data.domain.Page<Organization> springDataPage = 
				organizationDAO.findByParentId(parentId, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	/**   
	 * @param parentId
	 * @param name
	 * @param page
	 * @return  
	 * @see com.gcs.sysmgr.service.OrganizationService#find(java.lang.Long, java.lang.String, com.gcs.sysmgr.util.dwz.Page)  
	 */
	public List<Organization> find(Long parentId, String name, Page page) {
		org.springframework.data.domain.Page<Organization> springDataPage = 
				organizationDAO.findByParentIdAndNameContaining(parentId, name, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
	
	/**
	 * 判断是否是根组织.
	 */
	private boolean isRoot(Long id) {
		return id == 1;
	}

	/**
	 * 
	 * @return  
	 * @see com.gcs.sysmgr.service.OrganizationService#getTree()
	 */
	public Organization getTree() {
		List<Organization> list = organizationDAO.findAllWithCache();
		
		List<Organization> rootList = makeTree(list);
				
		return rootList.get(0);
	}

	private List<Organization> makeTree(List<Organization> list) {
		List<Organization> parent = new ArrayList<Organization>();
		// get parentId = null;
		for (Organization e : list) {
			if (e.getParent() == null) {
				e.setChildren(new ArrayList<Organization>(0));
				parent.add(e);
			}
		}
		// 删除parentId = null;
		list.removeAll(parent);
		
		makeChildren(parent, list);
		
		return parent;
	}
	
	private void makeChildren(List<Organization> parent, List<Organization> children) {
		if (children.isEmpty()) {
			return ;
		}
		
		List<Organization> tmp = new ArrayList<Organization>();
		for (Organization c1 : parent) {
			for (Organization c2 : children) {
				c2.setChildren(new ArrayList<Organization>(0));
				if (c1.getId().equals(c2.getParent().getId())) {
					c1.getChildren().add(c2);
					tmp.add(c2);
				}
			}
		}
		
		children.removeAll(tmp);
		
		makeChildren(tmp, children);
	}


	public String findOrgJson(Long parentId) {
		List<Organization> orgList = organizationDAO.findByParentId(parentId);
		StringBuffer resJson = new StringBuffer(); 
		resJson.append("{items:[");
		if(null != orgList && orgList.size() > 0){
			for(int i = 0 ; i < orgList.size() ; i++){
				Organization org = orgList.get(i);
				resJson.append("'"+org.getName()+"',");
			}
			int j = resJson.length();
			resJson.deleteCharAt(j-1);			
		}
		resJson.append("]}");
		return resJson.toString();
	}

	public String findByName(String names) {
		String namesArr[] = names.split(",");
		StringBuffer resJson = new StringBuffer(); 
		resJson.append("{items:[");
		for(int i = 0 ; i < namesArr.length ; i++){
			Organization obj = organizationDAO.findByName(namesArr[i]);
			List<User> orgUserList = obj.getUsers();
			for(int j = 0 ; j < orgUserList.size() ; j++){
				User orgUser = orgUserList.get(j);
				resJson.append("'"+orgUser.getRealname()+"("+obj.getName()+")',");
			}
		}
		int k = resJson.length();
		resJson.deleteCharAt(k-1);	
		resJson.append("]}");
		return resJson.toString();
	}
	
}
