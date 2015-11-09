package com.gcs.aol.shiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.utils.Collections3;

import com.gcs.aol.entity.AolUser;
import com.gcs.aol.service.IAolUserManager;
import com.gcs.sysmgr.entity.main.Module;
import com.gcs.sysmgr.entity.main.Permission;
import com.gcs.sysmgr.service.ModuleService;
import com.gcs.sysmgr.shiro.IncorrectCaptchaException;
import com.google.common.collect.Sets;
import com.octo.captcha.service.image.ImageCaptchaService;

public class LoginRealm extends AuthorizingRealm {
	private static final Logger log = LoggerFactory.getLogger(LoginRealm.class);
	
	// 是否使用验证码
	protected boolean useCaptcha = true;
	
	protected IAolUserManager iAolUserManager;
	
	protected ModuleService moduleService;
	
	protected ImageCaptchaService imageCaptchaService;
	
	public LoginRealm(){
		super();
	}
	
	/**
	 * 自定义密码校验模式
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		//该句作用是重写shiro的密码验证，让shiro用我自己的验证  
		setCredentialsMatcher(new CustomCredentialsMatcher());
	}
	
	/**
	 * 登录验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		if (useCaptcha) {
			UsernamePasswordCaptchaToken captchaToken = (UsernamePasswordCaptchaToken)authcToken;
			String parm = captchaToken.getCaptcha();
			try {
				if (!imageCaptchaService.validateResponseForID(SecurityUtils.getSubject().getSession().getId().toString(), parm)) {
					throw new IncorrectCaptchaException("验证码错误！");
				}
			} catch (Exception e) {
				throw new IncorrectCaptchaException("验证码错误！");
			}
		}
		
		UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
		String username = token.getUsername();
		if(StringUtils.isBlank(username)){
			throw new UnknownAccountException("该用户不存在！");
		}
		Boolean isOK = false;
		//首先查找帐号
		AolUser user = null;
		List<AolUser> userList = iAolUserManager.queryAccountList(username);
		if(userList == null){
			isOK = false;
		} else {
			if(userList.size() == 1){
				isOK = true;
				user = userList.get(0);
			} else {
				isOK = false;
			}
		}
		//帐号不存在，则查找手机
		if(!isOK){
			userList = new ArrayList<AolUser>();
			userList = iAolUserManager.queryMobileList(username);
			if(userList == null){
				isOK = false;
			} else {
				if(userList.size() == 1){
					isOK = true;
					user = userList.get(0);
				} else {
					isOK = false;
				}
			}
		}
		//手机也不存在，则查找邮箱
		if(!isOK){
			userList = new ArrayList<AolUser>();
			userList = iAolUserManager.queryEmailList(username);
			if(userList == null){
				isOK = false;
			} else {
				if(userList.size() == 1){
					isOK = true;
					user = userList.get(0);
				} else {
					isOK = false;
				}
			}
		}
		
		if(isOK){
			ShiroUser shiroUser = new ShiroUser(user.getUserId(), user.getAccount(), user);
			// 这里可以缓存认证
			return new SimpleAuthenticationInfo(shiroUser, user.getPassword(), getName());
		} else {
			throw new UnknownAccountException("该用户不存在！");
		}
	}
	
	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Collection<?> collection = principals.fromRealm(getName());
		if (Collections3.isEmpty(collection)) {
			return null;
		}
		ShiroUser shiroUser = (ShiroUser) collection.iterator().next();
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(makePermissions(shiroUser));
		
		return info;
	}

	private Collection<String> makePermissions(ShiroUser shiroUser) {
		Collection<String> stringPermissions = Sets.newHashSet();
		
		List<Module> modules = moduleService.findAll();
		for (Module module : modules) {
			List<Permission> permissions = module.getPermissions();
			// 默认构造CRUD权限
			stringPermissions.add(module.getSn() + ":" + Permission.PERMISSION_CREATE);
			stringPermissions.add(module.getSn() + ":" + Permission.PERMISSION_READ);
			stringPermissions.add(module.getSn() + ":" + Permission.PERMISSION_UPDATE);
			stringPermissions.add(module.getSn() + ":" + Permission.PERMISSION_DELETE);
			
			for (Permission permission : permissions) {
				stringPermissions.add(module.getSn() + ":" + permission.getShortName());
			}
		}
		
		log.info("使用了超级管理员:" + shiroUser.getLoginName() + "登录了系统。At " + new Date());
		log.info(shiroUser.getLoginName() + "拥有的权限:" + stringPermissions);
		return stringPermissions;
	}
	
	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}
	
	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}
	
	public void setUseCaptcha(boolean useCaptcha) {
		this.useCaptcha = useCaptcha;
	}

	public void setiAolUserManager(IAolUserManager iAolUserManager) {
		this.iAolUserManager = iAolUserManager;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public void setImageCaptchaService(ImageCaptchaService imageCaptchaService) {
		this.imageCaptchaService = imageCaptchaService;
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1746537882963711884L;
		
		private String id;
		private String loginName;
		private String ipAddress;
		private AolUser aolUser;

		public ShiroUser(){
		}
		
		public ShiroUser(String id, String loginName, AolUser aolUser){
			this.id = id;
			this.loginName = loginName;
			this.aolUser = aolUser;
		}
		
		/**  
		 * 返回 id 的值   
		 */
		public String getId() {
			return id;
		}

		/**  
		 * 返回 loginName 的值   
		 */
		public String getLoginName() {
			return loginName;
		}
		
		/**
		 * 返回ipAddress 的值
		 * @return
		 */
		public String getIpAddress() {
			return ipAddress;
		}
		
		/**
		 * 设置ipAddress 的值
		 */
		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}
		
		/**
		 * 返回AolUser 对象
		 * @return
		 */
		public AolUser getAolUser(){
			return this.aolUser;
		}
		
		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return loginName;
		}
	}
}
