/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, gcsdylan.com
 * Filename:		com.gcs.sysmgr.service.impl.UserServiceImpl.java
 * Class:			UserServiceImpl
 * Date:			2012-8-7
 * Author:			<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.gcs.sysmgr.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gcs.sysmgr.dao.UserDAO;
import com.gcs.sysmgr.entity.main.User;
import com.gcs.sysmgr.exception.ServiceException;
import com.gcs.sysmgr.service.UserService;
import com.gcs.sysmgr.util.dwz.Page;
import com.gcs.sysmgr.util.dwz.PageUtils;

/** 
 * 	
 * @author 	<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version  1.1.0
 * @since   2012-8-7 下午3:14:29 
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserDAO userDAO;
	
//	@Autowired
//	private ShiroDbRealm shiroRealm;
	
	/**  
	 * 构造函数
	 * @param jpaRepository  
	 */ 
	@Autowired
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public User get(Long id) {
		return userDAO.findOne(id);
	}

	public List<User> findAll(Page page) {
		org.springframework.data.domain.Page<User> springDataPage = userDAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
	
//	/**
//	 * 
//	 * @param user
//	 * @throws ExistedException  
//	 * @see com.gcs.sysmgr.service.UserService#save(com.gcs.sysmgr.entity.main.User)
//	 */
//	public void save(User user) throws ExistedException {		
//		if (userDAO.findByUsername(user.getUsername()) != null) {
//			throw new ExistedException("用户添加失败，登录名：" + user.getUsername() + "已存在。");
//		}
//		
//		if (userDAO.findByRealname(user.getRealname()) != null) {
//			throw new ExistedException("用户添加失败，真实名：" + user.getRealname() + "已存在。");
//		}
//		
//		//设定安全的密码，使用passwordService提供的salt并经过1024次 sha-1 hash
//		if (StringUtils.isNotBlank(user.getPlainPassword()) && shiroRealm != null) {
//			HashPassword hashPassword = shiroRealm.encrypt(user.getPlainPassword());
//			user.setSalt(hashPassword.salt);
//			user.setPassword(hashPassword.password);
//		}
//		
//		userDAO.save(user);
//		shiroRealm.clearCachedAuthorizationInfo(user.getUsername());
//	}

//	/**   
//	 * @param user  
//	 * @see com.gcs.sysmgr.service.UserService#update(com.gcs.sysmgr.entity.main.User)  
//	 */
//	public void update(User user) {
//		//if (isSupervisor(user.getId())) {
//		//	logger.warn("操作员{},尝试修改超级管理员用户", SecurityUtils.getSubject().getPrincipal());
//		//	throw new ServiceException("不能修改超级管理员用户");
//		//}
//		//设定安全的密码，使用passwordService提供的salt并经过1024次 sha-1 hash
//		
//		if (StringUtils.isNotBlank(user.getPlainPassword()) && shiroRealm != null) {
//			HashPassword hashPassword = shiroRealm.encrypt(user.getPlainPassword());
//			user.setSalt(hashPassword.salt);
//			user.setPassword(hashPassword.password);
//		}
//		
//		userDAO.save(user);
//		shiroRealm.clearCachedAuthorizationInfo(user.getUsername());
//	}

	/**   
	 * @param id  
	 * @see com.gcs.sysmgr.service.UserService#delete(java.lang.Long)  
	 */
	public void delete(Long id) throws ServiceException {
		if (isSupervisor(id)) {
			logger.warn("操作员{}，尝试删除超级管理员用户", SecurityUtils.getSubject()
					.getPrincipal() + "。");
			throw new ServiceException("不能删除超级管理员用户。");
		}
		userDAO.delete(id);
	}

	/**   
	 * @param username
	 * @return  
	 * @see com.gcs.sysmgr.service.UserService#get(java.lang.String)  
	 */
	public User get(String username) {
		return userDAO.findByUsername(username);
	}

	/**   
	 * @param page
	 * @param name
	 * @return  
	 * @see com.gcs.sysmgr.service.UserService#find(com.gcs.sysmgr.util.dwz.Page, java.lang.String)  
	 */
	public List<User> find(Page page, String name) {
		org.springframework.data.domain.Page<User> springDataPage = 
				userDAO.findByUsernameContaining(name, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Long id) {
		return id == 1;
	}

	public String findUserJson(Long orgId) {
		List<User> userlist = userDAO.findByOrganizationId(orgId);
		StringBuffer resJson = new StringBuffer(); 
		resJson.append("{items:[");
		if(null != userlist && userlist.size() > 0){
			for(int i = 0 ; i < userlist.size() ; i++){
				User user = userlist.get(i);
				resJson.append("'"+user.getRealname()+"',");
			}
			int j = resJson.length();
			resJson.deleteCharAt(j-1);			
		}
		resJson.append("]}");
		return resJson.toString();
	}

	public String findAllUser() {
		List<User> userlist = userDAO.findAll();
		StringBuffer resJson = new StringBuffer(); 
		resJson.append("{items:[");
		if(null != userlist && userlist.size() > 0){
			for(int i = 0 ; i < userlist.size() ; i++){
				User user = userlist.get(i);
				resJson.append("'"+user.getRealname()+"("+user.getOrganization().getName()+")',");
			}
			int j = resJson.length();
			resJson.deleteCharAt(j-1);			
		}
		resJson.append("]}");
		return resJson.toString();
	}
	
	public List<User> getAllUser(){
		return userDAO.findAll();
	}
	
}
