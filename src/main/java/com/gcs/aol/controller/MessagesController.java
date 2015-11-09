package com.gcs.aol.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.gcs.aol.entity.AolUser;
import com.gcs.aol.entity.AppPushList;
import com.gcs.aol.entity.Messages;
import com.gcs.aol.entity.Organise;
import com.gcs.aol.service.IAolUserManager;
import com.gcs.aol.service.IAppPushListManager;
import com.gcs.aol.service.IOrganiseManager;
import com.gcs.aol.service.impl.AppPushListManagerImpl;
import com.gcs.aol.service.impl.MessagesManagerImpl;
import com.gcs.aol.vo.LoginUserVO;
import com.gcs.aol.vo.MsgJsonReturn;
import com.gcs.aol.vo.PageVO;
import com.gcs.aol.vo.TreeChildNodeVO;
import com.gcs.aol.vo.TreeNodeVO;
import com.gcs.sysmgr.SecurityConstants;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.util.ServiceLocator;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

@Controller
@RequestMapping("/management/messages")
public class MessagesController extends GenericEntityController<Messages, Messages, MessagesManagerImpl> {
	@Autowired
	IOrganiseManager iOrganiseManager;
	@Autowired
	IAolUserManager iAolUserManager;
	@Autowired
	IAppPushListManager appPushListManager;
	
	/** 消息查看 */
	private static final String INBOXLIST = "management/aol/messagesMgr/inboxList";
	/** APP消息查看 */
	private static final String APPINBOXLIST = "management/aol/messagesMgr/appInboxList";
	/** 草稿箱 */
	private static final String DRAFTBOXLIST = "management/aol/messagesMgr/draftboxList";
	/** 已发送 */
	private static final String SENDEDLIST = "management/aol/messagesMgr/sendedList";
	/** 写消息（总公司） */
	private static final String WRITEMESSAGE = "management/aol/messagesMgr/writeMessage";
	/** 写消息（代理商） */
	private static final String ORGWRITEMESSAGE = "management/aol/messagesMgr/orgWriteMessage";
	/** app消息推送（代理商） */
	private static final String ORGAPPMESSAGESET = "management/aol/messagesMgr/orgAppMessageSet";
	/** 消息查看 */
	private static final String VIEWMSG = "management/aol/messagesMgr/viewMsg";
	/** 推送消息查看 */
	private static final String APPVIEWMSG = "management/aol/messagesMgr/appViewMsg";
	
	/**
	 * 跳转到消息查看页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/inboxlist", method=RequestMethod.GET)
	public String inboxlist(HttpServletRequest request) {	
		return INBOXLIST;
	}
	/**
	 * 跳转到app消息查看页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/appInboxlist", method=RequestMethod.GET)
	public String appInboxlist(HttpServletRequest request) {	
		return APPINBOXLIST;
	}
	/**
	 * 跳转到草稿箱页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/draftboxlist", method=RequestMethod.GET)
	public String draftboxlist(HttpServletRequest request) {	
		return DRAFTBOXLIST;
	}
	
	/**
	 * 跳转到已发送页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/sendedlist", method=RequestMethod.GET)
	public String sendedlist(HttpServletRequest request) {	
		return SENDEDLIST;
	}
	
	/**
	 * 跳转到发送消息页面(总公司)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/writemessage", method=RequestMethod.GET)
	public String writemessage(HttpServletRequest request) {	
		return WRITEMESSAGE;
	}
	
	/**
	 * 跳转到发送消息页面（代理商）
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/orgwritemessage", method=RequestMethod.GET)
	public String orgwritemessage(HttpServletRequest request) {	
		return ORGWRITEMESSAGE;
	}
	
	/**
	 * 跳转到代理商消息推送页面
	 * 
	 */
	@RequestMapping(value="/orgappmessageset", method=RequestMethod.GET)
	public String orgappmessageset(HttpServletRequest request) {	
		return ORGAPPMESSAGESET;
	}
	
	/**
	 * 草稿箱发送消息
	 * @param request
	 * @param messagesId
	 * @return
	 */
	@RequestMapping(value="/sendmessage", method=RequestMethod.GET)
	public String sendmessage(HttpServletRequest request, String messagesId){
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		
		Messages msg = this.getEntityManager().queryByPK(messagesId);
		request.setAttribute("messages", msg);
		
		if("1".equals(loginuser.getUserType())){
			return ORGWRITEMESSAGE;
		} else if("99".equals(loginuser.getUserType())){
			return WRITEMESSAGE;
		} else {
			return DRAFTBOXLIST;
		}
	}
	/**
	 * app发送消息
	 * @param mes
	 * @param request
	 * @param session
	 * @param saveORsend
	 * @return
	 */
	@RequestMapping(value = "/saveAppMessage", method = RequestMethod.POST)
	public String saveAppMessage(Messages mes,HttpServletRequest request, HttpSession session, String saveORsend){
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		AppPushListManagerImpl appPushListManagerImpl = (AppPushListManagerImpl)ServiceLocator.lookup(AppPushListManagerImpl.class);
		//获取接收对象ID
		String messagesSendee = mes.getMessagesSendee();
		//接收方处理
		if("send".equals(saveORsend)){
			//获取选择对象ID
			String[] sendees = messagesSendee.split(",");
			//根据收件方的不同，筛选出有效的收件人ID
			for(int i=0; i<sendees.length; i++){
				String _id = sendees[i];
				//用户
				if(mes.getSendeeType() == 1){
					//如果接收方是用户这把消息传递到APP推送表也发一份
					AppPushList appPushList =  new AppPushList();
					appPushList.setUserid(_id.replaceAll("U_", ""));
					appPushList.setTitle(mes.getMessagesTitle());
					appPushList.setText(mes.getMessagesContent());
					AolUser u = iAolUserManager.queryByPK(_id.replaceAll("U_", ""));
					appPushList.setUsername(u.getName());
					appPushList.setState("0");
					appPushList.setTs(new Date());
					appPushList.setSenduserid(loginuser.getUserId());
					appPushListManagerImpl.save(appPushList);
				}
			}
		}
		if("send".equals(saveORsend)){
			request.setAttribute("msg", "1_发送成功！");
		} 
		return APPINBOXLIST;

	}
	/**
	 * 发送消息
	 * @param mes
	 * @param request
	 * @param session
	 * @param saveORsend
	 * @return
	 */
	@RequestMapping(value = "/saveMessage", method = RequestMethod.POST)
	public String saveMessage(Messages mes,HttpServletRequest request, HttpSession session, String saveORsend){
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		//获取接收对象ID
		String messagesSendee = mes.getMessagesSendee();
		//发送时间
		Timestamp sfsj = new Timestamp(System.currentTimeMillis());
		mes.setMessagesTime(sfsj);
		//发送人ID
		mes.setMessagesSender(loginuser.getUserId());
		if("0".equals(loginuser.getUserType())){
			mes.setMessagesSenderName(loginuser.getName());
		} else {
			mes.setMessagesSenderName(loginuser.getOrganiseName());
		}
		//消息为发送方查看
		mes.setMessagesOwn("send");
		if("send".equals(saveORsend)){
			mes.setMessagesState(1);//已发送
		} else {
			mes.setMessagesState(0);//草稿
		}
		
		//接收方处理
		if("send".equals(saveORsend)){
			//有效ID集合
			Set<String>  sendeesList = new HashSet<String>();
			//获取选择对象ID
			String[] sendees = messagesSendee.split(",");
			//根据收件方的不同，筛选出有效的收件人ID
			for(int i=0; i<sendees.length; i++){
				String _id = sendees[i];
				//用户
				if(mes.getSendeeType() == 1){
					if("U_".equals(_id.substring(0, 2))){
						sendeesList.add(_id.replaceAll("U_", "") + "@@1");
					}
				//代理商
				} else if(mes.getSendeeType() == 2){
					if("O_".equals(_id.substring(0, 2))){
						sendeesList.add(_id.replaceAll("O_", "" )+ "@@2");
					}
				//所有，即代理商和用户
				} else if(mes.getSendeeType() == 3){
					if("U_".equals(_id.substring(0, 2))){
						sendeesList.add(_id.replaceAll("U_", "")+ "@@1");
					} else if("O_".equals(_id.substring(0, 2))){
						sendeesList.add(_id.replaceAll("O_", "" )+ "@@2");
					}
				}
			}
			//接收方姓名集合
			String sendeeNames = "";
			
			for(String sendmsg : sendeesList){
				String[] ss = sendmsg.split("@@");
				
				Messages jsf = new Messages();
				jsf.setMessagesTitle(mes.getMessagesTitle());//标题
				jsf.setMessagesContent(mes.getMessagesContent());//内容
				jsf.setMessagesTime(sfsj);//发送时间
				jsf.setMessagesOwn("receive");//收件人查看
				jsf.setMessagesState(0);//未读
				jsf.setMessagesSender(mes.getMessagesSender());//发送人
				jsf.setMessagesSenderName(mes.getMessagesSenderName());//发送人名称
				jsf.setMessagesSendee(ss[0]);//接收人
				jsf.setSendeeType(Integer.parseInt(ss[1]));//接收人类型(1:用户,2：代理商)
				if("1".equals(ss[1])){
					AolUser u = iAolUserManager.queryByPK(ss[0]);
					jsf.setMessagesSendeeName(u.getName());//接收人名称
				} else if("2".equals(ss[1])){
					Organise o = iOrganiseManager.queryByPK(ss[0]);
					jsf.setMessagesSendeeName(o.getOrganiseName());//接收人名称
				}
				//保存接收方消息记录
				this.getEntityManager().save(jsf);
				//拼接接收方名称
				sendeeNames = sendeeNames + jsf.getMessagesSendeeName() + ",";
			}
			//插入接收方姓名
			mes.setMessagesSendeeName(sendeeNames);
		}
		//保存发送方消息记录
		this.getEntityManager().save(mes);
		
		if("send".equals(saveORsend)){
			request.setAttribute("msg", "1_发送成功！");
		} else {
			request.setAttribute("msg", "0_存草稿成功！");
		}
		
		if("99".equals(loginuser.getUserType())){
			return WRITEMESSAGE;
		} else {
			return ORGWRITEMESSAGE;
		}
	}
	
	/**
	 * 获取草稿箱列表数据
	 * @param params
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getDraftboxDataList", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse getDraftboxDataList(@RequestBody JSONParam[] params,HttpServletRequest request){
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		PageVO pv = this.getEntityManager().queryMessageDataList(pp, "send", "0", loginuser.getUserId());
		Long count = (long)pv.getCount();
		List tmpList = pv.getList();
		return successed(new DataTableReturnObject(count, count, pp.getSEcho(),tmpList));
	}
	
	/**
	 * 获取草稿箱列表数据
	 * @param params
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getSendedDataList", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse getSendedDataList(@RequestBody JSONParam[] params,HttpServletRequest request){
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		PageVO pv = this.getEntityManager().queryMessageDataList(pp, "send", "1", loginuser.getUserId());
		Long count = (long)pv.getCount();
		List tmpList = pv.getList();
		return successed(new DataTableReturnObject(count, count, pp.getSEcho(),tmpList));
	}
	/**
	 * 获取APP消息数据
	 * @param params
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getAppInboxDataList", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse getAppInboxDataList(@RequestBody JSONParam[] params,HttpServletRequest request){
		//获取登录用户
		String sendeeId = "";
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		sendeeId = loginuser.getUserId();
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		PageVO pv = appPushListManager.queryAppInboxDataList(pp, sendeeId);
		Long count = (long)pv.getCount();
		List tmpList = pv.getList();
		return successed(new DataTableReturnObject(count, count, pp.getSEcho(),tmpList));
	}
	/**
	 * 获取消息数据
	 * @param params
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getinboxDataList", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse getinboxDataList(@RequestBody JSONParam[] params,HttpServletRequest request){
		//获取登录用户
		String sendeeId = "";
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		String usertype = loginuser.getUserType();
		if("0".equals(usertype)){
			sendeeId = loginuser.getUserId();
		} else {
			sendeeId = loginuser.getOrganiseId();
		}
		
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		PageVO pv = this.getEntityManager().queryInboxDataList(pp, sendeeId);
		Long count = (long)pv.getCount();
		List tmpList = pv.getList();
		return successed(new DataTableReturnObject(count, count, pp.getSEcho(),tmpList));
	}
	
	
	/**
	 * 查看推送消息
	 * @param request
	 * @param messagesId
	 * @return
	 */
	@RequestMapping(value="/appViewMsg", method=RequestMethod.GET)
	public String appViewMsg(HttpServletRequest request, String id){
		AppPushList appPushList = appPushListManager.queryByPK(id);
		
		/*if("send".equals(message.getMessagesOwn())){
			request.setAttribute("backUrl", "sendedlist");
		} else if("receive".equals(message.getMessagesOwn())) {
			if(message.getMessagesState() == 0){
				message.setMessagesState(1);
				this.getEntityManager().save(message);
			}
			request.setAttribute("backUrl", "inboxlist");
		}*/
		request.setAttribute("appPushList", appPushList);
		return APPVIEWMSG;
	}
	
	/**
	 * 查看消息
	 * @param request
	 * @param messagesId
	 * @return
	 */
	@RequestMapping(value="/viewMsg", method=RequestMethod.GET)
	public String viewMsg(HttpServletRequest request, String messagesId){
		Messages message = this.getEntityManager().queryByPK(messagesId);
		
		if("send".equals(message.getMessagesOwn())){
			request.setAttribute("backUrl", "sendedlist");
		} else if("receive".equals(message.getMessagesOwn())) {
			if(message.getMessagesState() == 0){
				message.setMessagesState(1);
				this.getEntityManager().save(message);
			}
			request.setAttribute("backUrl", "inboxlist");
		}
		request.setAttribute("message", message);
		return VIEWMSG;
	}
	
	/**
	 * 删除消息
	 * @param msgId
	 * @return
	 */
	@RequestMapping(value = "/deleteMsg", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn deleteMsg(String msgId){
		try {
			String[] msgIds = msgId.split(",");
			
			int resultSum = this.getEntityManager().deleteMsg(msgIds);
			if(resultSum == -1){
				return new MsgJsonReturn(false, "删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MsgJsonReturn(false, "删除失败");
		}
		return new MsgJsonReturn(true, "删除成功");
	}
	
	/**
	 * 标记为已读
	 * @param msgId
	 * @return
	 */
	@RequestMapping(value = "/markReadMsg", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn markReadMsg(String msgId){
		try {
			String[] msgIds = msgId.split(",");
			
			int resultSum = this.getEntityManager().markRead(msgIds);
			if(resultSum == -1){
				return new MsgJsonReturn(false, "标记失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MsgJsonReturn(false, "标记失败");
		}
		return new MsgJsonReturn(true, "标记成功");
	}
	
	/**
	 * 获取公司树
	 * @param params
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getOrgTreeData", method={RequestMethod.POST})
	public void getOrgTreeData(HttpServletRequest request, HttpServletResponse response){
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		
		List<TreeNodeVO> treeList = new ArrayList<TreeNodeVO>();
		TreeNodeVO treeNode = new TreeNodeVO();
		
		Organise orgRoot = iOrganiseManager.queryByPK(loginuser.getOrganiseId());
		
		treeNode = new TreeNodeVO();
		treeNode.setId(orgRoot.getOrganiseId());
		treeNode.setText(orgRoot.getOrganiseName());
		treeNode.setState("open");
		treeNode.setIconCls("icon-briefcase");
		
		List<Object> childrenNodeList = null;
		List<Organise> orgChildren = iOrganiseManager.queryByProperty("parentId", orgRoot.getOrganiseId(),"createTime",false);
		if(orgChildren != null && orgChildren.size() > 0){
			childrenNodeList = new ArrayList<Object>();
			for(int i=0; i<orgChildren.size(); i++){
				Organise org = orgChildren.get(i);
				childrenNodeList.add(new TreeChildNodeVO(org.getOrganiseId(), org.getOrganiseName(), "icon-briefcase"));
			}
			treeNode.setChildren(childrenNodeList);
		}
		treeList.add(treeNode);
		try {
			response.setContentType("applicatin/json;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(JSON.toJSON(treeList));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取公司、用户树
	 * @param params
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getOrgAndUserTreeData", method={RequestMethod.POST})
	public void getOrgAndUserTreeData(HttpServletRequest request, HttpServletResponse response){
		String parenttid = request.getParameter("id");
		if(StringUtils.isBlank(parenttid)){
			//获取登录用户
			LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
			
			List<TreeNodeVO> treeList = new ArrayList<TreeNodeVO>();
			TreeNodeVO treeNode = new TreeNodeVO();
			
			Organise orgRoot = iOrganiseManager.queryByPK(loginuser.getOrganiseId());
			
			treeNode = new TreeNodeVO();
			treeNode.setId(orgRoot.getOrganiseId());
			treeNode.setText(orgRoot.getOrganiseName());
			treeNode.setState("open");
			treeNode.setIconCls("icon-briefcase");
			
			List<Object> childrenNodeList = null;
			List<Organise> orgChildren = iOrganiseManager.queryByProperty("parentId", orgRoot.getOrganiseId(),"createTime",false);
			if(orgChildren != null && orgChildren.size() > 0){
				childrenNodeList = new ArrayList<Object>();
				for(int i=0; i<orgChildren.size(); i++){
					Organise org = orgChildren.get(i);
					
					List<Object> childrenUserNodeList = null;
					//生成当前代理商节点
					TreeNodeVO orgTreeNode = new TreeNodeVO("O_" + org.getOrganiseId(), org.getOrganiseName(), "closed", "icon-briefcase");
					//查找当前代理商的所属用户节点
					List<AolUser> userOfOrgList = iAolUserManager.queryUsersByOrgid(org.getOrganiseId());
					//遍历，生成用户节点
					if(userOfOrgList != null && userOfOrgList.size() > 0){
						if(childrenUserNodeList == null){
							childrenUserNodeList = new ArrayList<Object>();
						}
						for(int j=0; j<userOfOrgList.size(); j++){
							AolUser userOfOrg = userOfOrgList.get(j);
							//生成用户节点
							childrenUserNodeList.add(new TreeChildNodeVO("U_" + userOfOrg.getUserId(), userOfOrg.getName(), "icon-user"));
						}
					}
					//将用户节点添加到代理商子节点
					orgTreeNode.setChildren(childrenUserNodeList);
					//添加当前代理商节点
					childrenNodeList.add(orgTreeNode);
				}
			}
			//生成虚拟未分组的节点
			List<AolUser> userList1 = iAolUserManager.queryUsersByOrgid_new();
			if(userList1.size()>0&&childrenNodeList!=null){
				TreeNodeVO orgTreeNode = new TreeNodeVO("O_9999","未分组" , "closed", "icon-briefcase");
					List<Object> childrenNodeList1 = new ArrayList<Object>();
				for(int j=0; j<userList1.size(); j++){
					AolUser user = userList1.get(j);
					childrenNodeList1.add(new TreeChildNodeVO("U_" + user.getUserId(), user.getName(), "icon-user"));
				}
				orgTreeNode.setChildren(childrenNodeList1);
				childrenNodeList.add(orgTreeNode);
			}
			List<AolUser> userList = iAolUserManager.queryUsersByOrgid(orgRoot.getOrganiseId());
			if(userList != null && userList.size() > 0){
				if(childrenNodeList == null){
					childrenNodeList = new ArrayList<Object>();
				}
				for(int j=0; j<userList.size(); j++){
					AolUser user = userList.get(j);
					childrenNodeList.add(new TreeChildNodeVO("U_" + user.getUserId(), user.getName(), "icon-user"));
				}
			}
			
			treeNode.setChildren(childrenNodeList);
			treeList.add(treeNode);
			try {
				response.setContentType("applicatin/json;charset=UTF-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(JSON.toJSON(treeList));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			if("O_".equals(parenttid.subSequence(0, 2))){
				parenttid = parenttid.substring(2, parenttid.length());
			}
			List<Object> childrenNodeList = null;
			
			List<AolUser> userList = iAolUserManager.queryUsersByOrgid(parenttid);
			if(userList != null && userList.size() > 0){
				if(childrenNodeList == null){
					childrenNodeList = new ArrayList<Object>();
				}
				for(int j=0; j<userList.size(); j++){
					AolUser user = userList.get(j);
					childrenNodeList.add(new TreeChildNodeVO("U_" + user.getUserId(), user.getName(), "icon-user"));
				}
			}
			
			try {
				response.setContentType("applicatin/json;charset=UTF-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(JSON.toJSON(childrenNodeList));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
