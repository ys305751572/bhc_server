package com.gcs.sysmgr.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gcs.aol.entity.AolUser;
import com.gcs.aol.entity.Messages;
import com.gcs.aol.entity.Organise;
import com.gcs.aol.service.IMessagesManager;
import com.gcs.aol.service.impl.OrganiseManagerImpl;
import com.gcs.aol.shiro.LoginRealm;
import com.gcs.aol.utils.LogUtils;
import com.gcs.aol.vo.LoginUserVO;
import com.gcs.sysmgr.SecurityConstants;
import com.gcs.sysmgr.log.Log;
import com.gcs.sysmgr.log.LogMessageObject;
import com.gcs.sysmgr.log.impl.LogUitl;
import com.gcs.sysmgr.util.ServiceLocator;

@Controller
@RequestMapping("/management/index")
public class IndexController {
	
	private static final String INDEX = "management/index/index";
	private static final String UPDATE_PASSWORD = "management/index/updatePwd";
	private static final String UPDATE_BASE = "management/index/updateBase";
	private static final String HOME = "management/index/home";

	@Log(message = "{0}登录了系统。")
	@RequiresUser
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		LoginRealm.ShiroUser shiroUser = (LoginRealm.ShiroUser) subject.getPrincipal();
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
//			OrganiseManagerImpl orgMgl = (OrganiseManagerImpl) ServiceLocator.lookup(OrganiseManagerImpl.class);
//			Organise org = orgMgl.getOrgByUserId(loginUser.getUserId());
//			if(org == null){
//				return "redirect:/logout";
//			}
//			loginUser.setOrganiseId(org.getOrganiseId());//登录代理商ID
//			loginUser.setOrganiseName(org.getOrganiseName());//登录代理商名称
//			loginUser.setOrganiseShortname(org.getOrganiseShortname());//登录代理商简称
		}
		
		//将用户信息放入session
		request.getSession().setAttribute(SecurityConstants.LOGIN_USER, loginUser);
		//因为在前期不一致先在session中存入user对象，方便操作
		request.getSession().setAttribute(SecurityConstants.USER_OBJ, user);
		LogUitl.putArgs(LogMessageObject.newWrite().setObjects( new Object[] { shiroUser.getLoginName() }));		
		//记录登录日志
		LogUtils.writeLog(loginUser.getName(), loginUser.getName() + "登录了系统。", LogUtils.getIpAddr(request));
		request.setAttribute("loginUser", loginUser);
		
		IMessagesManager mesMgr = (IMessagesManager) ServiceLocator.lookup(IMessagesManager.class);
		String sql = "";
		if("0".equals(loginUser.getUserType())){
			sql = " Messages m where m.dr=0 and m.messagesOwn = 'receive' and m.messagesState=0 and m.messagesSendee = '" + loginUser.getUserId() + "' ";
		} else {
			sql = " Messages m where m.dr=0 and m.messagesOwn = 'receive' and m.messagesState=0 and m.messagesSendee = '" + loginUser.getOrganiseId() + "' ";
		}
		//获取登录用户未读消息条数
		Long count = mesMgr.queryCount(sql);
		request.setAttribute("msgCount", count);
		//获取最新未读的3条消息
		List<Messages> msgTop3List = mesMgr.queryTopMsgList(sql + " order by m.messagesTime");
		if(msgTop3List != null && msgTop3List.size() > 0){
			request.setAttribute("msgsList", msgTop3List);
		} else {
			request.setAttribute("msgsList", null);
		}
		
		return INDEX;
	}
	
	@RequestMapping(value = "/updatePwd", method = RequestMethod.GET)
	public String preUpdatePassword() {
		return UPDATE_PASSWORD;
	}
	/**
	 * 主页跳转
	 * @return
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(HttpServletRequest request,HttpSession session) {
		return HOME;
	}
	
	@RequestMapping(value = "/ievirtual", method = RequestMethod.GET)
	public String openVirtualDialog() {
		return "management/index/ievirtual";
	}

	@RequestMapping(value = "/updateBase", method = RequestMethod.GET)
	public String preUpdateBase() {
		return UPDATE_BASE;
	}
}
