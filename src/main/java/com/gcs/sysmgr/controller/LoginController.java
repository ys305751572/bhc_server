
package com.gcs.sysmgr.controller;

import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.utils.Exceptions;

import com.gcs.aol.entity.AolUser;
import com.gcs.aol.entity.Organise;
import com.gcs.aol.service.impl.OrganiseManagerImpl;
import com.gcs.aol.shiro.LoginRealm;
import com.gcs.aol.utils.LogUtils;
import com.gcs.aol.vo.LoginUserVO;
import com.gcs.sysmgr.SecurityConstants;
import com.gcs.sysmgr.log.Log;
import com.gcs.sysmgr.shiro.IncorrectCaptchaException;
import com.gcs.sysmgr.util.ServiceLocator;
import com.gcs.sysmgr.util.dwz.AjaxObject;


@Controller
@RequestMapping("/login")
public class LoginController {
	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class); 
	
	private static final String LOGIN_HOME_PAGE = "login_home";
	private static final String LOGIN_PAGE = "login";
	private static final String LOGIN_DIALOG = "management/index/loginDialog";

	@RequestMapping(method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		return LOGIN_PAGE;
	}
	
	@RequestMapping(value = "/loginHome", method = RequestMethod.GET)
	public String loginHome(HttpServletRequest request) {
		return LOGIN_HOME_PAGE;
	}

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.HEAD }, headers = "x-requested-with=XMLHttpRequest")
	public @ResponseBody
	String loginDialog(HttpServletRequest request) {
		return AjaxObject.newTimeout("会话超时，请重新登录。").toString();
	}

	@RequestMapping(value = "/timeout", method = { RequestMethod.GET })
	public String timeout() {
		return LOGIN_DIALOG;
	}

	@Log(message="会话超时， 该用户重新登录系统。")
	@RequestMapping(value = "/timeout/success", method = {RequestMethod.GET})
	public @ResponseBody
	String timeoutSuccess(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		LoginRealm.ShiroUser shiroUser = (LoginRealm.ShiroUser)subject.getPrincipal();
		//获取登录用户
		AolUser user = shiroUser.getAolUser();
		//定义登录用户数据存储类
		LoginUserVO loginUser = new LoginUserVO();
		loginUser.setUserId(user.getUserId());//用户ID
		loginUser.setMobile(user.getMobile());//用户手机
		loginUser.setEmail(user.getEmail());//用户邮箱
		loginUser.setAccount(user.getAccount());//用户名
		loginUser.setName(user.getName());//用户姓名
		if(StringUtils.isNotBlank(user.getBak4())){
			loginUser.setUserType(user.getBak4());//用户类型(0:普通用户, 1:代理商管理员, 2:特殊用户, 99:总公司)
 		} else {
 			loginUser.setUserType("0");//如果用户类型为空，则该用户为普通用户
 		}
		loginUser.setIpAddress(request.getRemoteAddr());//用户登录IP
		loginUser.setLoginTime(new Timestamp(System.currentTimeMillis()));//用户登录时间
		//基于现在的规则，普通用户很难判断其所属代理商，故暂时不考虑普通用户的代理商
		if(!"0".equals(loginUser.getUserType())){
			OrganiseManagerImpl orgMgl = (OrganiseManagerImpl) ServiceLocator.lookup(OrganiseManagerImpl.class);
			Organise org = orgMgl.getOrgByUserId(loginUser.getUserId());
			loginUser.setOrganiseId(org.getOrganiseId());//登录代理商ID
			loginUser.setOrganiseName(org.getOrganiseName());//登录代理商名称
			loginUser.setOrganiseShortname(org.getOrganiseShortname());//登录代理商简称
		}
		//将用户信息放入session
		request.getSession().setAttribute(SecurityConstants.LOGIN_USER, loginUser);
		//修改BY XXX 因为在前期不一致先在session中存入user对象，方便操作
		request.getSession().setAttribute(SecurityConstants.USER_OBJ, user);
		//记录登录日志
		LogUtils.writeLog(loginUser.getName(), loginUser.getName() + "登录了系统。", LogUtils.getIpAddr(request));
		return AjaxObject.newOk("登录成功。").toString();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String fail(
			@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username,
			Map<String, Object> map, HttpServletRequest request) {

		String msg = parseException(request);
		
		map.put("msg", msg);
		map.put("username", username);
		
		return LOGIN_PAGE;
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.HEAD }, headers = "x-requested-with=XMLHttpRequest")
	public @ResponseBody
	String failDialog(HttpServletRequest request) {
		String msg = parseException(request);
		
		AjaxObject ajaxObject = new AjaxObject(msg);
		ajaxObject.setStatusCode(AjaxObject.STATUS_CODE_FAILURE);
		ajaxObject.setCallbackType("");

		return ajaxObject.toString();
	}

	private String parseException(HttpServletRequest request) {
		String errorString = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		Class<?> error = null;
		try {
			if (errorString != null) {
				error = Class.forName(errorString);
			}
		} catch (ClassNotFoundException e) {
			LOG.error(Exceptions.getStackTraceAsString(e));
		} 
		
		String msg = "其他错误！";
		if (error != null) {
			if (error.equals(UnknownAccountException.class))
				msg = "未知帐号错误！";
			else if (error.equals(IncorrectCredentialsException.class))
				msg = "密码错误！";
			else if (error.equals(IncorrectCaptchaException.class))
				msg = "验证码错误！";
			else if (error.equals(AuthenticationException.class))
				msg = "认证失败！";
			else if (error.equals(DisabledAccountException.class))
				msg = "账号被冻结！";
		}
		return "登录失败，" + msg;
	}
}
