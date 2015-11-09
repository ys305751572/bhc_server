package com.gcs.aol.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcs.aol.entity.SmsList;
import com.gcs.aol.entity.UrgentPerson;
import com.gcs.aol.service.UrgentPersonManager;
import com.gcs.aol.service.impl.SmsListManagerImpl;
import com.gcs.aol.service.impl.UrgentPersonManagerImpl;
import com.gcs.aol.utils.KeyWord;
import com.gcs.aol.vo.LoginUserVO;
import com.gcs.aol.vo.MsgJsonReturn;
import com.gcs.sysmgr.SecurityConstants;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.util.ServiceLocator;

@Controller
@RequestMapping("/management/urgentperson")
public class UrgentPersonController extends GenericEntityController<UrgentPerson,UrgentPerson,UrgentPersonManagerImpl>{
	@Autowired
	UrgentPersonManager urgentPersonManagerImpl;
	
	private static final String USERURGENTLIST = "management/aol/agentMgr/userurgentlist";
	private static final String ORGUSERURGENTLIST = "management/aol/agentMgr/orguserurgentlist";
	
	private static final String GUARDIANLIST = "management/aol/agentMgr/guardianlist";
	private static final String ORGGUARDIANLIST = "management/aol/agentMgr/orgguardianlist";
	
	//跳转到紧急联系人页面
	@RequestMapping(value="/userurgentlist", method=RequestMethod.GET)
	public String userurgentlist(HttpServletRequest request, String userId) {	
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		if(StringUtils.isBlank(userId)){
			userId = loginuser.getUserId();
		}
		
		List<UrgentPerson> list = urgentPersonManagerImpl.findByUserId(userId,"0");
		UrgentPerson urgentPerson = new UrgentPerson();
		if(list==null || list.size()==0){
			//如果没有设置紧急联系人
			urgentPerson.setUser_id(userId);
		}else{
			//设置紧急联系人
			urgentPerson = list.get(0);
		}
		request.setAttribute("urgentPersontemp", urgentPerson);
		request.setAttribute("userType", loginuser.getUserType());
		return USERURGENTLIST;
	}
	
	//代理商、总公司跳转到紧急联系人页面
	@RequestMapping(value="/orguserurgentlist", method=RequestMethod.GET)
	public String orguserurgentlist(HttpServletRequest request) {	
		return ORGUSERURGENTLIST;
	}
	
	//跳转到监护人页面
	@RequestMapping(value="/guardianlist", method=RequestMethod.GET)
	public String guardianlist(HttpServletRequest request, String userId) {	
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		if(StringUtils.isBlank(userId)){
			userId = loginuser.getUserId();
		}
		
		List<UrgentPerson> list = urgentPersonManagerImpl.findByUserId(userId,"1");
		UrgentPerson urgentPerson1 = null;
		UrgentPerson urgentPerson2 = null;
		UrgentPerson urgentPerson3 = null;
		
		if(list==null || list.size()==0){
			//如果没有设置监护人
			urgentPerson1 = new UrgentPerson();
			urgentPerson2 = new UrgentPerson();
			urgentPerson3 = new UrgentPerson();
		}else{
			//因为监护人可以设置三个，所以如果不到3个，直接加空对象补足
			for (int i = 0; i < 3; i++) {
				list.add(new UrgentPerson());
			}
			
			urgentPerson1 = list.get(0);
			urgentPerson2 = list.get(1);
			urgentPerson3 = list.get(2);
		}
		
		request.setAttribute("userid", userId);
		request.setAttribute("urgentPerson1", urgentPerson1);
		request.setAttribute("urgentPerson2", urgentPerson2);
		request.setAttribute("urgentPerson3", urgentPerson3);
		
		request.setAttribute("userType", loginuser.getUserType());
		
		return GUARDIANLIST;
	}
	
	//代理商、总公司跳转到监护人页面
	@RequestMapping(value="/orgguardianlist", method=RequestMethod.GET)
	public String orgguardianlist(HttpServletRequest request) {	
		return ORGGUARDIANLIST;
	}
	
	/**
	 * 查找紧急联系人
	 * @param request
	 */
	@RequestMapping(value="/getuserurgentperson", method=RequestMethod.POST)
	public void getUserUrgentperson(HttpServletRequest request, HttpServletResponse response) {	
		String userid = request.getParameter("userid");
		List<UrgentPerson> list = urgentPersonManagerImpl.findByUserId(userid,"0");
		UrgentPerson urgentPerson = new UrgentPerson();
		String msg = "";
		if(list.size()==0){
			//如果没有设置紧急联系人
		}else{
			//设置紧急联系人
			urgentPerson = list.get(0);
			
			msg = "{'urgentxm':'" + urgentPerson.getUrgentxm() + "','telephone':'"+urgentPerson.getTelephone()+"','address':'"+urgentPerson.getAddress()+"','urgentperson_id':'"+urgentPerson.getUrgentperson_id()+"','sffs':'"+urgentPerson.getSffs()+"'}";
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
	 * 查找监护人
	 * @param request
	 */
	@RequestMapping(value="/getguardianperson", method=RequestMethod.POST)
	public void getguardianperson(HttpServletRequest request, HttpServletResponse response) {	
		String userid = request.getParameter("userid");
		List<UrgentPerson> list = urgentPersonManagerImpl.findByUserId(userid,"1");
		@SuppressWarnings("unused")
		UrgentPerson urgentPerson = new UrgentPerson();
		String msg = "";
		if(list.size()==0){
			//如果没有设置监护人
		}else{
			//设置监护人
			urgentPerson = list.get(0);
			//因为监护人可以设置三个，所以如果没有3个直接加个对象
			for (int i = 0; i < 3; i++) {
				UrgentPerson temp = new UrgentPerson();
				list.add(temp);
			}
			msg = "{" +
					"'urgentxm1':'" + list.get(0).getUrgentxm() + "','telephone1':'"+list.get(0).getTelephone()+"','address1':'"+list.get(0).getAddress()+"','gx1':'"+list.get(0).getGx()+"','urgentperson_id1':'"+list.get(0).getUrgentperson_id()+"'," +
					"'urgentxm2':'" + list.get(1).getUrgentxm() + "','telephone2':'"+list.get(1).getTelephone()+"','address2':'"+list.get(1).getAddress()+"','gx2':'"+list.get(1).getGx()+"','urgentperson_id2':'"+list.get(1).getUrgentperson_id()+"'," +
					"'urgentxm3':'" + list.get(2).getUrgentxm() + "','telephone3':'"+list.get(2).getTelephone()+"','address3':'"+list.get(2).getAddress()+"','gx3':'"+list.get(2).getGx()+"','urgentperson_id3':'"+list.get(2).getUrgentperson_id()+"'" +
					"}";
		}
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//保存紧急联系人
	@RequestMapping(value="/saveUrgentPerson", method=RequestMethod.POST)
	public String saveUrgentPerson(HttpServletRequest request){
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		
		String urgentxm = request.getParameter("urgentxm");
		String telephone = request.getParameter("telephone");
		String address = request.getParameter("address");
		String userid  = request.getParameter("userid");
		String urgentperson_id  = request.getParameter("urgentperson_id");
		String sffs  = request.getParameter("sffs");
		
		UrgentPerson newUrgentPerson = new UrgentPerson();
		if(StringUtils.isNotBlank(urgentperson_id)){
			//查找到登陆用户的用户ID查找紧急联系人
			//有ID 做修改操作
			UrgentPerson urgentPerson = urgentPersonManagerImpl.queryByPK(urgentperson_id);
			urgentPerson.setUrgentxm(urgentxm);
			urgentPerson.setTelephone(telephone);
			urgentPerson.setAddress(address);
			if("on".equals(sffs)){
				urgentPerson.setSffs("1");
			}else{
				urgentPerson.setSffs("0");
			}
			newUrgentPerson = urgentPersonManagerImpl.save(urgentPerson);
		}else{
			//没有ID 新增操作
			UrgentPerson urgentPerson = new UrgentPerson();
			if("on".equals(sffs)){
				urgentPerson.setSffs("1");
			}else{
				urgentPerson.setSffs("0");
			}
			urgentPerson.setUrgentxm(urgentxm);
			urgentPerson.setTelephone(telephone);
			urgentPerson.setAddress(address);
			urgentPerson.setUser_id(userid);
			urgentPerson.setBak4("0");//设置为紧急联系人
			newUrgentPerson = urgentPersonManagerImpl.save(urgentPerson);
		}
		request.setAttribute("msg", "ok");
		request.setAttribute("urgentPersontemp", newUrgentPerson);
		request.setAttribute("userType", loginuser.getUserType());
		return USERURGENTLIST;
	}
	
	//对发送监护人发送信息
	@RequestMapping(value="/setMessage", method=RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn setMassage(HttpServletRequest request,String message,String telephone){
		try {
			SmsListManagerImpl smsListManagerImpl = (SmsListManagerImpl) ServiceLocator.lookup(SmsListManagerImpl.class);
			String [] ky =  new KeyWord(request).keyword;
			for (String string : ky) {
				if(StringUtils.isNotBlank(string)){
					if(!(message.indexOf(string)==-1)){
						System.out.println(message.indexOf(string));
						return new MsgJsonReturn(false,"出现敏感词！");
					}
				}
			}
			SmsList smsList = new SmsList();
			smsList.setTelephone(telephone);
			smsList.setContent(message);
			smsList.setState("0");
			smsList.setTs(new Date());
			smsList.setSend_time(new Date());
			smsListManagerImpl.save(smsList);
			return new MsgJsonReturn(true,"发送成功！");
		} catch (Exception e) {
			return new MsgJsonReturn(false,"发送失败！");
		}
	}
	
	//保存监护人
	@RequestMapping(value="/saveGuardian", method=RequestMethod.POST)
	public String saveGuardian(HttpServletRequest request){
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		String userid = request.getParameter("userid");
		
		String urgentxm1 = request.getParameter("urgentxm1");
		String telephone1 = request.getParameter("telephone1");
		String address1 = request.getParameter("address1");
		String gx1 = request.getParameter("gx1");
		String urgentperson_id1  = request.getParameter("urgentperson_id1");
		
		String urgentxm2 = request.getParameter("urgentxm2");
		String telephone2 = request.getParameter("telephone2");
		String address2 = request.getParameter("address2");
		String gx2 = request.getParameter("gx2");
		String urgentperson_id2  = request.getParameter("urgentperson_id2");
		
		String urgentxm3 = request.getParameter("urgentxm3");
		String telephone3 = request.getParameter("telephone3");
		String address3 = request.getParameter("address3");
		String gx3 = request.getParameter("gx3");
		String urgentperson_id3  = request.getParameter("urgentperson_id3");
		
		List<UrgentPerson> list = new ArrayList<UrgentPerson>();
		//保存第一个监护人
		if(StringUtils.isNotBlank(urgentperson_id1)){
			//查找到登陆用户的用户ID查找紧急联系人
			//有ID 做修改操作
			UrgentPerson urgentPerson = urgentPersonManagerImpl.queryByPK(urgentperson_id1);
			urgentPerson.setUrgentxm(urgentxm1);
			urgentPerson.setTelephone(telephone1);
			urgentPerson.setAddress(address1);
			urgentPerson.setGx(gx1);
			urgentPerson.setUser_id(userid);
			urgentPerson.setBak4("1");//设置为监护人
			urgentPersonManagerImpl.save(urgentPerson);
			list.add(urgentPerson);
		}else{
			//没有ID 新增操作
			UrgentPerson urgentPerson = new UrgentPerson();
			urgentPerson.setUrgentxm(urgentxm1);
			urgentPerson.setTelephone(telephone1);
			urgentPerson.setAddress(address1);
			urgentPerson.setGx(gx1);
			urgentPerson.setUser_id(userid);
			urgentPerson.setBak4("1");//设置为监护人
			urgentPersonManagerImpl.save(urgentPerson);
			list.add(urgentPerson);
		}
		
		//保存第二个监护人
		if(StringUtils.isNotBlank(urgentperson_id2)){
			//查找到登陆用户的用户ID查找紧急联系人
			//有ID 做修改操作
			UrgentPerson urgentPerson = urgentPersonManagerImpl.queryByPK(urgentperson_id2);
			urgentPerson.setUrgentxm(urgentxm2);
			urgentPerson.setTelephone(telephone2);
			urgentPerson.setAddress(address2);
			urgentPerson.setGx(gx2);
			urgentPerson.setUser_id(userid);
			urgentPerson.setBak4("1");//设置为监护人
			urgentPersonManagerImpl.save(urgentPerson);
			list.add(urgentPerson);
		}else{
			//没有ID 新增操作
			UrgentPerson urgentPerson = new UrgentPerson();
			urgentPerson.setUrgentxm(urgentxm2);
			urgentPerson.setTelephone(telephone2);
			urgentPerson.setAddress(address2);
			urgentPerson.setGx(gx2);
			urgentPerson.setUser_id(userid);
			urgentPerson.setBak4("1");//设置为监护人
			urgentPersonManagerImpl.save(urgentPerson);
			list.add(urgentPerson);
		}
		
		//保存第三个监护人
		if(StringUtils.isNotBlank(urgentperson_id3)){
			//查找到登陆用户的用户ID查找紧急联系人
			//有ID 做修改操作
			UrgentPerson urgentPerson = urgentPersonManagerImpl.queryByPK(urgentperson_id3);
			urgentPerson.setUrgentxm(urgentxm3);
			urgentPerson.setTelephone(telephone3);
			urgentPerson.setAddress(address3);
			urgentPerson.setGx(gx3);
			urgentPerson.setUser_id(userid);
			urgentPerson.setBak4("1");//设置为监护人
			urgentPersonManagerImpl.save(urgentPerson);
			list.add(urgentPerson);
		}else{
			//没有ID 新增操作
			UrgentPerson urgentPerson = new UrgentPerson();
			urgentPerson.setUrgentxm(urgentxm3);
			urgentPerson.setTelephone(telephone3);
			urgentPerson.setAddress(address3);
			urgentPerson.setGx(gx3);
			urgentPerson.setUser_id(userid);
			urgentPerson.setBak4("1");//设置为监护人
			urgentPersonManagerImpl.save(urgentPerson);
			list.add(urgentPerson);
		}
		
		request.setAttribute("userid", userid);
		request.setAttribute("urgentPerson1", list.get(0));
		request.setAttribute("urgentPerson2", list.get(1));
		request.setAttribute("urgentPerson3", list.get(2));
		request.setAttribute("userType", loginuser.getUserType());
		request.setAttribute("msg", "ok");
		
		return GUARDIANLIST;
	}
	
}
