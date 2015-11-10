package com.gcs.aol.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.gcs.aol.entity.AolUser;
import com.gcs.aol.entity.Device;
import com.gcs.aol.entity.Organise;
import com.gcs.aol.entity.OrganiseDevice;
import com.gcs.aol.entity.UserDevice;
import com.gcs.aol.service.IDeviceManager;
import com.gcs.aol.service.IOrganiseDeviceManager;
import com.gcs.aol.service.IOrganiseManager;
import com.gcs.aol.service.IUserDeviceManager;
import com.gcs.aol.service.impl.AolUserManagerImpl;
import com.gcs.aol.service.impl.DeviceManagerImpl;
import com.gcs.aol.service.impl.UserDeviceManagerImpl;
import com.gcs.aol.utils.CommonUtils;
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
@RequestMapping("/management/aoluser")
public class AolUserController extends GenericEntityController<AolUser, AolUser, AolUserManagerImpl> {
	@Autowired
	IOrganiseManager iOrganiseManager;
	@Autowired
	IUserDeviceManager userDeviceManager;
	@Autowired
	IDeviceManager deviceManager;
	@Autowired
	IOrganiseDeviceManager iOrganiseDeviceManager;
	
	private static final String USERSLIST = "management/aol/usersMgr/usersList";
	private static final String VIEWUSERINFO = "management/aol/usersMgr/viewUserInfo";
	private static final String HANDADDDEVICE = "management/aol/usersMgr/handAddDevice";
	private static final String USERTRANSFER = "management/aol/usersMgr/userTransfer";
	private static final String VIEWOWNINFO = "management/aol/usersMgr/viewOwnInfo";
	
	@RequestMapping(value="/userslist", method=RequestMethod.GET)
	public String userslist(HttpServletRequest request) {	
		request.setAttribute("regTimeQ", request.getParameter("starttime"));
		return USERSLIST;
	}
	
	/**
	 * 获取用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getUserList", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse getUserList(@RequestBody JSONParam[] params,HttpServletRequest request) {
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String usersname = paramMap.get("usersname");
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		//获取登录用户
		PageVO pv = new PageVO();
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		if("00000000000000000000000000000000".equals(loginuser.getUserId())){
			//管理员
			pv = this.getEntityManager().queryUsersDataList(pp, "",usersname,"0");
		}else{
			//普通用户
			pv = this.getEntityManager().queryUsersDataList(pp, loginuser.getUserId(),usersname,"0");
		}
		 
		Long count = (long)pv.getCount();
		List tmpList = pv.getList();
		return successed(new DataTableReturnObject(count, count, pp.getSEcho(),tmpList));
	}
	
	/**
	 * 用户信息列表
	 * @param params
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getUsersDataList", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse getUsersDataList(@RequestBody JSONParam[] params,HttpServletRequest request){
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String sexType = paramMap.get("sexType");
		String mobile =  paramMap.get("mobile");
		String usersname = paramMap.get("usersname");
		String birthday = paramMap.get("birthday");
		String regTimeQ = paramMap.get("regTimeQ");
		String regTimeZ = paramMap.get("regTimeZ");
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		PageVO pv = this.getEntityManager().queryUsersDataList(pp, usersname, sexType, mobile, birthday, regTimeQ, regTimeZ, loginuser.getOrganiseId());
		Long count = (long)pv.getCount();
		List tmpList = pv.getList();
		return successed(new DataTableReturnObject(count, count, pp.getSEcho(),tmpList));
	}
	
	@RequestMapping(value="/sfUserInfo", method=RequestMethod.POST)
	@ResponseBody
	public void sfUserInfo(HttpServletRequest request, HttpServletResponse response, String userId){
		List<UserDevice> udlist = null;
		udlist  = userDeviceManager.queryByProperty("user_id", userId);
		String msg = "";
		if(udlist == null||udlist.size() == 0){
			msg = "无法查询";
		}
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 查看用户信息
	 */
	@RequestMapping(value="/viewUserInfo", method=RequestMethod.GET)
	public String viewUserInfo(HttpServletRequest request){
		String userId = request.getParameter("userId");
		String deviceId = request.getParameter("deviceId");
		String urltype  = request.getParameter("urltype");
		AolUser user = this.getEntityManager().queryByPK(userId);
		MeasureController mc = new MeasureController();//调用MeasureController的report方法
		//查找用户所使用的设备ID
		List<UserDevice> udlist = null;
		List<Device> devicelist = new ArrayList<Device>();
		String xml = "";
		if((!StringUtils.isNotBlank(deviceId))||StringUtils.isNotBlank(urltype)){
			udlist  = userDeviceManager.queryByProperty("user_id", userId);
			//根据查询出来的设备ID找到设备
			for (UserDevice userDevice : udlist) {
				List<Device> dlist = new ArrayList<Device>();
				dlist = deviceManager.queryByProperty("device_id", userDevice.getDevice_id());
				if(dlist.size()>0){
					devicelist.add(dlist.get(0));
				}
			}
			if(!StringUtils.isNotBlank(deviceId)){
				deviceId = devicelist.get(0).getDevice_id();
			}
			xml = mc.makeXmlReport(deviceId, userId, deviceManager.queryByProperty("device_id", deviceId).get(0).getDeviceType(),"","");
		}else{
			List<Device> dlist = new ArrayList<Device>();
			dlist = deviceManager.queryByProperty("device_id", deviceId);
			devicelist.add(dlist.get(0));
			xml = mc.makeXmlReport(deviceId, userId, dlist.get(0).getDeviceType(),"","");
		}
		request.setAttribute("devicelist", devicelist);
		System.out.println(deviceId);
		request.setAttribute("selectdeviceid", deviceId);
		request.setAttribute("xml", xml);
		request.setAttribute("aoluser", user);
		return VIEWUSERINFO;
	}
	
	/**
	 * 查看用户个人信息
	 */
	@RequestMapping(value="/viewOwnInfo", method=RequestMethod.GET)
	public String viewOwnInfo(HttpServletRequest request){
		//查找到登陆用户的用户ID
		LoginUserVO loginuser = (LoginUserVO)request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		String userid = loginuser.getUserId();
		AolUser user = this.getEntityManager().queryByPK(userid);
		request.setAttribute("aoluser", user);
		return VIEWOWNINFO;
	}
	
	/**
	 * 用户自己编辑个人信息保存
	 */
	@RequestMapping(value="/saveOwnUser", method=RequestMethod.POST)
	public String saveOwnUser(HttpServletRequest request){
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String height = request.getParameter("height");
		String weight = request.getParameter("weight");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		
		//查找到登陆用户的用户ID
		AolUser loginuser = (AolUser)request.getSession().getAttribute(SecurityConstants.USER_OBJ);
		loginuser.setName(name);
		loginuser.setSex(sex);
		loginuser.setBirthday(birthday);
		loginuser.setHeight(height);
		loginuser.setWeight(weight);
		loginuser.setMobile(mobile);
		loginuser.setEmail(email);
		this.getEntityManager().save(loginuser);
		String userid = loginuser.getUserId();
		AolUser user = this.getEntityManager().queryByPK(userid);
		request.setAttribute("aoluser", user);
		return VIEWOWNINFO;
	}
	
	/**
	 * 修改备注姓名
	 * @param userId
	 * @param remark
	 * @return
	 */
	@RequestMapping(value="/modifyRemark", method=RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn modifyRemark(String userId, String remark){
		try {
			AolUser user = this.getEntityManager().queryByPK(userId);
			user.setName(remark);
			this.getEntityManager().save(user);
			
			return new MsgJsonReturn(true,"修改成功！");
		} catch (Exception e) {
			return new MsgJsonReturn(false,"修改失败！");
		}
	}
	
	/**
	 * 跳转到手动添加设备页面
	 * @param request
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/handAddDevice", method=RequestMethod.GET)
	public String handAddDevice(HttpServletRequest request, String userId){
		AolUser user = this.getEntityManager().queryByPK(userId);
		request.setAttribute("aoluser", user);
		return HANDADDDEVICE;
	}
	
	/**
	 * 根据序列号检索设备
	 * @param deviceSerial
	 * @return
	 */
	@RequestMapping(value="/checkDevice", method=RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn checkDevice(String deviceSerial){
		DeviceManagerImpl deviceMgr = (DeviceManagerImpl) ServiceLocator.lookup(DeviceManagerImpl.class);
		Device dev = deviceMgr.queryUniqueBy("deviceSerial", deviceSerial);
		if(dev != null){
			String orgName = deviceMgr.getOrgNameByDeviceCode(dev.getDeviceSerial());
			
			return new MsgJsonReturn(true, "{'deviceId':'" + dev.getDevice_id() + "', 'deviceSerial':'" + dev.getDeviceSerial() + "', 'deviceType':'" + getDeviceType(dev.getDeviceType()) + "', 'bak1':'" + getBak1Type(dev.getBak1()) + "', 'deviceProYear':'" + dev.getDeviceProYear() + "', 'deviceProMonth':'" + dev.getDeviceProMonth() + "', 'organiseName':'" + orgName + "'}");
		} else {
			return new MsgJsonReturn(false,"没有检索到序列号对应的设备！");
		}
	}
	
	/**
	 * 手动添加设备
	 * @param deviceSerial
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/addDevice", method=RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn addDevice(String deviceId, String userId){
		UserDeviceManagerImpl userDevMgr = (UserDeviceManagerImpl) ServiceLocator.lookup(UserDeviceManagerImpl.class);
		int xh = userDevMgr.checkIsExist(userId, deviceId);
		if(xh<0){
			return new MsgJsonReturn(false,"设备已绑定，无需再次添加！");
		} else {
			UserDevice ud = new UserDevice();
			ud.setUser_id(userId);
			ud.setDevice_id(deviceId);
			ud.setIsAdmin("1");
			ud.set_order(xh + 1);
			ud.setBak6(new Timestamp(System.currentTimeMillis()));
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			ud.setBindTime(sdf.format(new Timestamp(System.currentTimeMillis())).toString());
			
			userDevMgr.save(ud);
			return new MsgJsonReturn(true,"设备添加成功！");
		}
		
	}
	
	@RequestMapping(value="/usertransfer", method=RequestMethod.GET)
	public String usertransfer(HttpServletRequest request) {	
		return USERTRANSFER;
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
			treeNode.setId("O_" + orgRoot.getOrganiseId());
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
					List<AolUser> userOfOrgList = this.getEntityManager().queryUsersByOrgid(org.getOrganiseId());
					//遍历，生成用户节点
					if(userOfOrgList != null && userOfOrgList.size() > 0){
						if(childrenUserNodeList == null){
							childrenUserNodeList = new ArrayList<Object>();
						}
						for(int j=0; j<userOfOrgList.size(); j++){
							AolUser userOfOrg = userOfOrgList.get(j);
							//生成用户节点
							childrenUserNodeList.add(new TreeChildNodeVO("U_" + userOfOrg.getUserId(), userOfOrg.getName()+(StringUtils.isNotBlank(userOfOrg.getMobile())?("  ("+userOfOrg.getMobile()+")"):""), "icon-user"));
						}
					}
					//将用户节点添加到代理商子节点
					orgTreeNode.setChildren(childrenUserNodeList);
					//添加当前代理商节点
					childrenNodeList.add(orgTreeNode);
				}
			}
			
			//设置无代理商节点
			TreeNodeVO noOrgTreeNode = new TreeNodeVO("W_WDLS00000000000000000000000000", "无代理商", "closed", "icon-briefcase");
			//设置无代理商节点的子节点集合
			List<Object> childrenNoOrgUsersNodeList = new ArrayList<Object>();
			//获取所有无代理商的用户
			List<AolUser> userList = this.getEntityManager().queryNoOrgUsers();
			if(userList != null && userList.size() > 0){
				for(int j=0; j<userList.size(); j++){
					AolUser user = userList.get(j);
					childrenNoOrgUsersNodeList.add(new TreeChildNodeVO("W_" + user.getUserId(), user.getName()+(StringUtils.isNotBlank(user.getMobile())?("  ("+user.getMobile()+")"):""), "icon-user"));
				}
				noOrgTreeNode.setChildren(childrenNoOrgUsersNodeList);
			}
			//添加无代理商节点
			childrenNodeList.add(noOrgTreeNode);
			
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
			
			List<AolUser> userList = this.getEntityManager().queryUsersByOrgid(parenttid);
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
	 * 执行用户复制或转移操作
	 * @param doState
	 * @param userIds
	 * @param orgId
	 * @return
	 */
	@RequestMapping(value = "/doChange", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn doChange(String doState, String userIds, String orgId){
		boolean isOk = false;
		if(StringUtils.isNotBlank(userIds) && StringUtils.isNotBlank(orgId)){
			String useridsStr = "";
			String onOrgUsersStr = "";
			String[] useridList = userIds.split("@@");
			for(int i=0; i<useridList.length; i++){
				String uStr = useridList[i];
				if("U_".equals(uStr.substring(0, 2))){
					useridsStr = useridsStr + "'" + uStr.substring(2, uStr.length()) + "',";
				} else if("W_".equals(uStr.substring(0, 2))){
					onOrgUsersStr = onOrgUsersStr + "'" + uStr.substring(2, uStr.length()) + "',";
				}
			}
			if(StringUtils.isNotBlank(useridsStr)){
				useridsStr = useridsStr.substring(0, useridsStr.length()-1);
			}
			if(StringUtils.isNotBlank(onOrgUsersStr)){
				onOrgUsersStr = onOrgUsersStr.substring(0, onOrgUsersStr.length()-1);
			}
			
			int resultNum = 0;
			if("change".equals(doState)){
				if(StringUtils.isNotBlank(useridsStr)){
					resultNum = this.getEntityManager().doChangeUsers(useridsStr, orgId);
				}
				if(StringUtils.isNotBlank(onOrgUsersStr)){
					List<String> deviceSerialList = this.getEntityManager().getDeviceSerialByUserids(onOrgUsersStr);
					for(int o=0; o<deviceSerialList.size(); o++){
						OrganiseDevice od = new OrganiseDevice();
						od.setDeviceSerial(deviceSerialList.get(o));
						od.setOrganiseId(orgId);
						od.setBatchNumber(CommonUtils.getDateOf16());
						od.setOrderIndex(o+1);
						od.setBindTime(new Timestamp(System.currentTimeMillis()));
						od.setDr(0);
						iOrganiseDeviceManager.save(od);
						resultNum++;
					}
				}
				if(resultNum > 0){
					isOk = true;
				} else {
					isOk = false;
				}
			}
		} else {
			isOk = false;
		}
		if(isOk){
			return new MsgJsonReturn(true, "转移成功！");
		} else {
			return new MsgJsonReturn(false, "转移失败！");
		}
	}
	
	/**
	 * 转化设备功能类型
	 * @param type
	 * @return
	 */
	private String getDeviceType(String type){
		if("1".equals(type)){
			return "血压";
		} else if("2".equals(type)){
			return "血糖";
		} else if("3".equals(type)){
			return "体重";
		} else if("4".equals(type)){
			return "运动";
		} else {
			return "其他";
		}
	}
	
	/**
	 * 转化设备设备类型
	 * @param type
	 * @return
	 */
	private String getBak1Type(String type){
		if("0".equals(type)){
			return "普通设备";
		} else if("1".equals(type)){
			return "GSM设备";
		} else {
			return "其他";
		}
	}
	
}
