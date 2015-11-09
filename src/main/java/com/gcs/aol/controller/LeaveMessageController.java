package com.gcs.aol.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gcs.aol.entity.Messages;
import com.gcs.aol.entity.Organise;
import com.gcs.aol.service.IAolUserManager;
import com.gcs.aol.service.IOrganiseManager;
import com.gcs.aol.service.impl.MessagesManagerImpl;
import com.gcs.aol.vo.LoginUserVO;
import com.gcs.sysmgr.SecurityConstants;
import com.gcs.sysmgr.controller.GenericEntityController;

@Controller
@RequestMapping("/management/leavemsg")
public class LeaveMessageController extends GenericEntityController<Messages, Messages, MessagesManagerImpl> {
	@Autowired
	IOrganiseManager iOrganiseManager;
	@Autowired
	IAolUserManager iAolUserManager;
	
	private static final String LEAVEMESSAGE = "management/aol/messagesMgr/leaveMessage";
	
	/**
	 * 跳转到留言页面
	 * @param state
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/leavemessage", method=RequestMethod.GET)
	public String leavemessage(String state,String type,HttpServletRequest request) {	
		return LEAVEMESSAGE;
	}
	
	/**
	 * 用户留言保存
	 * @param mes
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveLeaveMessage", method = RequestMethod.POST)
	public String saveLeaveMessage(Messages mes,HttpServletRequest request){
		mes.setMessagesOwn("send");
		mes.setMessagesState(1);
		
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		//发送时间
		Timestamp sfsj = new Timestamp(System.currentTimeMillis());
		mes.setMessagesTime(sfsj);
		//发送人ID
		mes.setMessagesSender(loginuser.getUserId());
		mes.setMessagesSenderName(loginuser.getName());
		mes.setMessagesSendee("00000000000000000000000000000000");
		Organise o = iOrganiseManager.queryByPK("00000000000000000000000000000000");
		mes.setMessagesSendeeName(o.getOrganiseName());//接收人名称
		
		Messages jsf = new Messages();
		jsf.setMessagesTitle(mes.getMessagesTitle());//标题
		jsf.setMessagesContent(mes.getMessagesContent());//内容
		jsf.setMessagesTime(sfsj);//发送时间
		jsf.setMessagesOwn("receive");//收件人查看
		jsf.setMessagesState(0);//未读
		jsf.setMessagesSender(mes.getMessagesSender());//发送人
		jsf.setMessagesSenderName(mes.getMessagesSenderName());//发送人名称
		jsf.setMessagesSendee(mes.getMessagesSendee());//接收人
		jsf.setMessagesSendeeName(mes.getMessagesSendeeName());//接收人名称
		//保存接收方消息记录
		this.getEntityManager().save(jsf);
		
		//保存发送方消息记录
		this.getEntityManager().save(mes);
		
		request.setAttribute("msg", "在线留言成功！");
		return LEAVEMESSAGE;
	}
	
}
