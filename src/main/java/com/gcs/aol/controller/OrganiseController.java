package com.gcs.aol.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.gcs.aol.entity.AolUser;
import com.gcs.aol.entity.Attach;
import com.gcs.aol.entity.Organise;
import com.gcs.aol.service.IAolUserManager;
import com.gcs.aol.service.impl.AolUserManagerImpl;
import com.gcs.aol.service.impl.AttachManagerImpl;
import com.gcs.aol.service.impl.DeviceManagerImpl;
import com.gcs.aol.service.impl.OrganiseManagerImpl;
import com.gcs.aol.utils.CommonUtils;
import com.gcs.aol.utils.LogUtils;
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
@RequestMapping("/management/organise")
public class OrganiseController extends GenericEntityController<Organise, Organise, OrganiseManagerImpl> {
	@Autowired
	IAolUserManager iAolUserManager;
	
	private static final String Z_ORG_ID = "00000000000000000000000000000000";
	private static final String ORGANISESLIST = "management/aol/organiseMgr/organisesList";
	private static final String EDITORG = "management/aol/organiseMgr/editOrg";
	private static final String VIEWORG = "management/aol/organiseMgr/viewOrg";
	private static final String ACCOUNTMGT = "management/aol/organiseMgr/accountMgt";
	private static final String VIEWOWNINFO = "management/aol/organiseMgr/viewOwnInfo";
	private static final String EDITOWNINFO = "management/aol/organiseMgr/editOwnInfo";
	
	@RequestMapping(value="/organiseslist", method=RequestMethod.GET)
	public String organiseslist(HttpServletRequest request) {	
		return ORGANISESLIST;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getOrganiseDataList", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse getOrganiseDataList(@RequestBody JSONParam[] params,HttpServletRequest request){
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		
		String parenttid = paramMap.get("parentId");
		if(Z_ORG_ID.equals(parenttid)){
			parenttid = "";
		}
		
		String organiseName = paramMap.get("organiseName");
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		PageVO pv = this.getEntityManager().queryOrganiseDataList(pp, parenttid, organiseName);
		Long count = (long)pv.getCount();
		List tmpList = pv.getList();
		return successed(new DataTableReturnObject(count, count, pp.getSEcho(),tmpList));
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
		
		Organise orgRoot = this.getEntityManager().queryByPK(loginuser.getOrganiseId());
		
		treeNode = new TreeNodeVO();
		treeNode.setId(orgRoot.getOrganiseId());
		treeNode.setText(orgRoot.getOrganiseName());
		treeNode.setState("open");
		treeNode.setIconCls("icon-briefcase");
		
		List<Object> childrenNodeList = null;
		List<Organise> orgChildren = this.getEntityManager().queryByProperty("parentId", orgRoot.getOrganiseId(),"createTime",false);
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
	 * 新增代理商
	 * @param request
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value="/addOrg", method=RequestMethod.GET)
	public String addOrg(HttpServletRequest request, String parentId) {
		request.setAttribute("parentId", parentId);
		
		return EDITORG;
	}
	
	/**
	 * 修改代理商
	 * @param request
	 * @param orgId
	 * @return
	 */
	@RequestMapping(value="/editOrg", method=RequestMethod.GET)
	public String editOrg(HttpServletRequest request, String orgId) {
		Organise org = this.getEntityManager().queryByPK(orgId);
		
		if(StringUtils.isNotBlank(org.getAttachId())){
			AttachManagerImpl attachMgr = (AttachManagerImpl)ServiceLocator.lookup(AttachManagerImpl.class);
			Attach attach = attachMgr.queryByPK(org.getAttachId());
			request.setAttribute("attach", attach);
		}
		
		request.setAttribute("org", org);
		request.setAttribute("parentId", org.getParentId());
		
		return EDITORG;
	}
	
	/**
	 * 删除代理商
	 * @param orgId
	 * @return
	 */
	@RequestMapping(value = "/deleteOrg", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn deleteOrg(HttpServletRequest request, String orgId){
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		String orgName = "";
		try {
			if(StringUtils.isNotBlank(orgId)){
				Organise org = this.getEntityManager().queryByPK(orgId);
				orgName = org.getOrganiseName();
				DeviceManagerImpl devManager = (DeviceManagerImpl) ServiceLocator.lookup(DeviceManagerImpl.class);
				//删除前先转移设备到总公司下
				devManager.doChangeByDeleteDev(orgId);
				//删除代理商
				this.getEntityManager().deleteByPK(orgId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MsgJsonReturn(false, "删除失败");
		}
		LogUtils.writeLog(loginuser.getName(), "删除代理商【"+orgName+"】", LogUtils.getIpAddr(request));
		return new MsgJsonReturn(true, "删除成功");
	}
	
	/**
	 * 查看代理商基本信息
	 * @param request
	 * @param orgId
	 * @return
	 */
	@RequestMapping(value="/viewOrg", method=RequestMethod.GET)
	public String viewOrg(HttpServletRequest request, String orgId) {
		Organise org = this.getEntityManager().queryByPK(orgId);
		if(org != null){
			if(StringUtils.isNotBlank(org.getAttachId())){
				AttachManagerImpl attachMgr = (AttachManagerImpl)ServiceLocator.lookup(AttachManagerImpl.class);
				Attach attach = attachMgr.queryByPK(org.getAttachId());
				request.setAttribute("attach", attach);
			}
		}
		
		request.setAttribute("org", org);
		
		return VIEWORG;
	}
	
	/**
	 * 保存代理商信息
	 * @param org
	 * @param request
	 * @param session
	 * @param uploadAttach
	 * @return
	 */
	@RequestMapping(value = "/saveOrg", method = RequestMethod.POST)
	public String saveOrg(Organise org, HttpServletRequest request, HttpSession session, MultipartFile uploadAttach){
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		//代理商ID，判断是新增还是修改
		String orgId = "";
		Organise paerentOrg = this.getEntityManager().queryByPK(org.getParentId());
		org.setLevelType(paerentOrg.getLevelType() + 1);
		org.setCreateTime(new Timestamp(System.currentTimeMillis()));
		
		if(uploadAttach!=null&&uploadAttach.getSize()>0){
			String webRoot = request.getSession().getServletContext().getRealPath("");
			Attach attach  = CommonUtils.uploadAttach(uploadAttach, webRoot, "//upload//org//", loginuser.getUserId());
			org.setAttachId(attach.getAttachId());
		}
		orgId = org.getOrganiseId();
		Organise newOrg = this.getEntityManager().save(org);
		
		request.setAttribute("parentId", org.getParentId());
		request.setAttribute("result", "{success:'true', msg:'保存成功'}");
		if(StringUtils.isNotBlank(orgId)){
			LogUtils.writeLog(loginuser.getName(), "修改代理商【" + newOrg.getOrganiseName() + "】的基本信息", LogUtils.getIpAddr(request));
		} else {
			LogUtils.writeLog(loginuser.getName(), "新增代理商【" + newOrg.getOrganiseName() + "】", LogUtils.getIpAddr(request));
		}
		return EDITORG;
	}
	
	/**
	 * 账号管理
	 * @param request
	 * @param orgId
	 * @return
	 */
	@RequestMapping(value="/accountMgt", method=RequestMethod.GET)
	public String accountMgt(HttpServletRequest request, String orgId) {
		Organise org = this.getEntityManager().queryByPK(orgId);
		if(StringUtils.isNotBlank(org.getUserId())){
			AolUserManagerImpl userMgr = (AolUserManagerImpl)ServiceLocator.lookup(AolUserManagerImpl.class);
			AolUser user = userMgr.queryByPK(org.getUserId());
			request.setAttribute("aoluser", user);
		}
		request.setAttribute("orgId", org.getOrganiseId());
		return ACCOUNTMGT;
	}
	
	/**
	 * 检查账号是否唯一
	 * @param request
	 * @param userId
	 */
	@RequestMapping(value = "/checkAccount", method = RequestMethod.POST)
	public void checkAccount(HttpServletRequest request, HttpServletResponse response, String userId, String account){
		boolean isOnly = iAolUserManager.checkUsers(userId, account);
		
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			response.getWriter().write(isOnly + "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 账号保存
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
	public String saveAccount(HttpServletRequest request, HttpSession session){
		String organiseId = request.getParameter("organiseId");
		String userId = request.getParameter("userId");
		String _name = request.getParameter("_name");
		String _account = request.getParameter("_account");
		String _password = request.getParameter("_password");
		String accountstate = request.getParameter("accountstate");
		
		AolUser user = null;
		AolUserManagerImpl userMgr = (AolUserManagerImpl)ServiceLocator.lookup(AolUserManagerImpl.class);
		
		if(StringUtils.isNotBlank(userId)){
			user = userMgr.queryByPK(userId);
		} else {
			user = new AolUser();
			user.setBak5(new Timestamp(System.currentTimeMillis()));
		}
		
		user.setName(_name);
		user.setAccount(_account);
		user.setPassword(_password);
		user.setBak4(accountstate);
		AolUser newuser = userMgr.save(user);
		
		Organise org = this.getEntityManager().queryByPK(organiseId);
		org.setUserId(newuser.getUserId());
		this.getEntityManager().save(org);
		request.setAttribute("result", "{success:'true', msg:'保存成功'}");
		return ACCOUNTMGT;
	}
	
	/**
	 * 查看基本信息
	 * @param request
	 * @param orgId
	 * @return
	 */
	@RequestMapping(value="/viewowninfo", method=RequestMethod.GET)
	public String viewowninfo(HttpServletRequest request) {
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		
		Organise org = this.getEntityManager().queryByPK(loginuser.getOrganiseId());
		if(org != null){
			if(StringUtils.isNotBlank(org.getAttachId())){
				AttachManagerImpl attachMgr = (AttachManagerImpl)ServiceLocator.lookup(AttachManagerImpl.class);
				Attach attach = attachMgr.queryByPK(org.getAttachId());
				request.setAttribute("attach", attach);
			}
		}
		
		request.setAttribute("org", org);
		
		return VIEWOWNINFO;
	}
	
	/**
	 * 跳转到编辑基本信息
	 * @param request
	 * @param orgId
	 * @return
	 */
	@RequestMapping(value="/editowninfo", method=RequestMethod.GET)
	public String editowninfo(HttpServletRequest request) {
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		
		Organise org = this.getEntityManager().queryByPK(loginuser.getOrganiseId());
		if(org != null){
			if(StringUtils.isNotBlank(org.getAttachId())){
				AttachManagerImpl attachMgr = (AttachManagerImpl)ServiceLocator.lookup(AttachManagerImpl.class);
				Attach attach = attachMgr.queryByPK(org.getAttachId());
				request.setAttribute("attach", attach);
			}
		}
		
		request.setAttribute("org", org);
		
		return EDITOWNINFO;
	}
	
	/**
	 * 保存代理商信息
	 * @param org
	 * @param request
	 * @param session
	 * @param uploadAttach
	 * @return
	 */
	@RequestMapping(value = "/saveOwnInfo", method = RequestMethod.POST)
	public String saveOwnInfo(Organise org, HttpServletRequest request, HttpSession session, MultipartFile uploadAttach){
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		
		Organise paerentOrg = this.getEntityManager().queryByPK(org.getParentId());
		if(paerentOrg == null){
			org.setLevelType(0);
		} else {
			org.setLevelType(paerentOrg.getLevelType() + 1);
		}
		org.setCreateTime(new Timestamp(System.currentTimeMillis()));
		
		if(uploadAttach!=null&&uploadAttach.getSize()>0){
			String webRoot = request.getSession().getServletContext().getRealPath("");
			Attach attach  = CommonUtils.uploadAttach(uploadAttach, webRoot, "//upload//org//", loginuser.getUserId());
			org.setAttachId(attach.getAttachId());
		}
		
		Organise newOrg = this.getEntityManager().save(org);
		if(StringUtils.isNotBlank(newOrg.getAttachId())){
			AttachManagerImpl attachMgr = (AttachManagerImpl)ServiceLocator.lookup(AttachManagerImpl.class);
			Attach attach = attachMgr.queryByPK(newOrg.getAttachId());
			request.setAttribute("attach", attach);
		}
		request.setAttribute("org", newOrg);
		request.setAttribute("result", "{success:'true', msg:'保存成功'}");
		LogUtils.writeLog(loginuser.getName(), "修改了自己单位的基本登记信息", LogUtils.getIpAddr(request));
		return VIEWOWNINFO;
	}
	
	/**
	 * 附件下载
	 * @param attachId
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/download", method = { RequestMethod.GET,RequestMethod.POST })
	public void download(String attachId,HttpServletRequest request ,HttpServletResponse response) throws IOException{
		AttachManagerImpl attachMgr = (AttachManagerImpl)ServiceLocator.lookup(AttachManagerImpl.class);
		Attach attach = attachMgr.queryByPK(attachId);
		
		String path = attach.getAttachPath();
		String fileName = attach.getAttachTruename();
		response.setContentType("text/html;charset=utf-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		try {
			String webRoot = request.getSession().getServletContext().getRealPath("");
			request.setCharacterEncoding("utf-8");
			String ctxPath = webRoot + path ;
			String downLoadPath = ctxPath + attach.getAttachName();
			long fileLength = new File(downLoadPath).length(); 
			response.setContentType("application/x-msdownload;charset=utf-8"); 
			response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName,"UTF-8"));
			response.setHeader("Content-Length", String.valueOf(fileLength)); 
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];  
			int bytesRead; 
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) { 
				bos.write(buff, 0, bytesRead);  
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { 
			if(bis != null) 
			  bis.close(); 
			if(bos != null) 
			  bos.close();  
		}  
	}
	
}
