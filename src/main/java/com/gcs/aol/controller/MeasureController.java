package com.gcs.aol.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.gcs.aol.entity.AolUser;
import com.gcs.aol.entity.HighCharVo;
import com.gcs.aol.entity.Measure;
import com.gcs.aol.service.IAolUserManager;
import com.gcs.aol.service.impl.DeviceManagerImpl;
import com.gcs.aol.service.impl.MeasureManagerImpl;
import com.gcs.aol.utils.DateUtil;
import com.gcs.aol.utils.ListToTreeUtils;
import com.gcs.aol.utils.ReportUtils;
import com.gcs.aol.vo.DeciveListVO;
import com.gcs.aol.vo.DeciveSearchVO;
import com.gcs.aol.vo.LoginUserVO;
import com.gcs.aol.vo.MeasureListVO;
import com.gcs.aol.vo.MeasureSearchVO;
import com.gcs.aol.vo.PageVO;
import com.gcs.aol.vo.TreeNodeVO;
import com.gcs.sysmgr.SecurityConstants;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

@Controller
@RequestMapping("/management/measure")
public class MeasureController extends GenericEntityController<Measure,Measure,MeasureManagerImpl>{

	private static final String XTLIST = "management/aol/measureMgr/xtList";//血糖预警
	private static final String XYLIST = "management/aol/measureMgr/xyList";//血压预警
	private static final String XYREPORT = "management/aol/measureMgr/xyReportlist";//血压信息
	private static final String XTREPORT = "management/aol/measureMgr/xtReportlist";//血糖信息
	private static final String TWREPORT = "management/aol/measureMgr/twReportlist";//体温信息
	private static final String REPORT = "management/aol/measureMgr/report";//体温信息
	private static final String GRREPORT = "management/aol/measureMgr/grreport";//体温信息
//	private static final String GRXYREPORT = "management/aol/measureMgr/grxyReportlist";//血压信息
//	private static final String GRXTREPORT = "management/aol/measureMgr/grxtReportlist";//血糖信息
//	private static final String GRTWREPORT = "management/aol/measureMgr/grtwReportlist";//体温信息
	
	
	@Autowired
	private DeviceManagerImpl deviceManagerImpl;
	
	@Autowired
	private IAolUserManager aolUserManager;
	
	/**
	 * 跳转到血糖预警页面
	 * @param state
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/xtlist", method=RequestMethod.GET)
	public String xtlist(String state,String type,HttpServletRequest request) {
		//System.out.println(request.getParameter("starttime"));
		//request.setAttribute("sendTimeQ", request.getParameter("xtstate"));
		request.setAttribute("sendTimeZ", request.getParameter("xtstate"));
		request.setAttribute("xtstate", request.getParameter("xtstate"));
		request.setAttribute("sendTimeQ", request.getParameter("starttime"));
		return XTLIST;
	}
	
	/**
	 * 跳转到血压预警页面
	 * @param state
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/xylist", method=RequestMethod.GET)
	public String xylist(String state,String type,HttpServletRequest request) {	
		System.out.println(request.getParameter("starttime"));
		request.setAttribute("xtstate", request.getParameter("xtstate"));
		request.setAttribute("sendTimeQ", request.getParameter("starttime"));
		return XYLIST;
	}
	
	
	/**
	 * 跳转到血压信息页面
	 * @param state
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/xyReport", method=RequestMethod.GET)
	public String xyReport(String state,String type,HttpServletRequest request, HttpServletResponse response) {	
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		String strxml = "";
		//获取减10天数据
		request.setAttribute("sendTimeQ", DateUtil.getDateBefore(10));
		//获取当前时间
		request.setAttribute("sendTimeZ",  DateUtil.DateToStr(new Date()));
		if("0".equals(loginuser.getUserType())){
			//如果个人用户直接查找到数据,需要3个数据。
			/**
			 * 1.用户名
			 * 2.设备ID
			 * 3.检测类型
			 */
			//更具用户查找设备Id
			DeciveSearchVO temp = new DeciveSearchVO();
			temp.setUserName(loginuser.getUserId());
			String deciveid = deviceManagerImpl.queryDeciveID(loginuser.getUserId(), "1");
			strxml = makeXmlReport(deciveid, loginuser.getUserId(), "1",DateUtil.getDateBefore(10),DateUtil.DateToStr(new Date()));
			request.setAttribute("strxml", strxml);
		}else{
			List<MeasureListVO> list = new ArrayList<MeasureListVO>();
			strxml = ReportUtils.xyReportXml(list);
			request.setAttribute("strxml", strxml);
		}
		return XYREPORT;
	}
	
	/**
	 * 跳转到体温信息页面
	 * @param state
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/twReport", method=RequestMethod.GET)
	public String twReport(String state,String type,HttpServletRequest request, HttpServletResponse response) {	
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		String strxml = "";
		//获取减10天数据
		request.setAttribute("sendTimeQ", DateUtil.getDateBefore(10));
		//获取当前时间
		request.setAttribute("sendTimeZ",  DateUtil.DateToStr(new Date()));
		if("0".equals(loginuser.getUserType())){
			//如果个人用户直接查找到数据,需要3个数据。
			/**
			 * 1.用户名
			 * 2.设备ID
			 * 3.检测类型
			 */
			//更具用户查找设备Id
			DeciveSearchVO temp = new DeciveSearchVO();
			temp.setUserName(loginuser.getUserId());
			String deciveid = deviceManagerImpl.queryDeciveID(loginuser.getUserId(), "3");
			strxml = makeXmlReport( deciveid, loginuser.getUserId(),"3",DateUtil.getDateBefore(10),DateUtil.DateToStr(new Date()));
			request.setAttribute("strxml", strxml);
		}else{
			List<MeasureListVO> list = new ArrayList<MeasureListVO>();
			strxml = ReportUtils.twReportXml(list);
			request.setAttribute("strxml", strxml);
			
		}
		return TWREPORT;
	}
	
	
	/**
	 * 跳转到血糖信息页面
	 * @param state
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/xtReport", method=RequestMethod.GET)
	public String xtReport(String state,String type,HttpServletRequest request, HttpServletResponse response) {	
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		String strxml = "";
		//获取减10天数据
		request.setAttribute("sendTimeQ", DateUtil.getDateBefore(10));
		//获取当前时间
		request.setAttribute("sendTimeZ",  DateUtil.DateToStr(new Date()));
		if("0".equals(loginuser.getUserType())){
			//如果个人用户直接查找到数据,需要3个数据。
			/**
			 * 1.用户名
			 * 2.设备ID
			 * 3.检测类型
			 */
			//更具用户查找设备Id
			DeciveSearchVO temp = new DeciveSearchVO();
			temp.setUserName(loginuser.getUserId());
			String deciveid = deviceManagerImpl.queryDeciveID(loginuser.getUserId(), "2");
			strxml = makeXmlReport( deciveid, loginuser.getUserId(),"2",DateUtil.getDateBefore(10),DateUtil.DateToStr(new Date()));
			request.setAttribute("strxml", strxml);
		}else{
			List<MeasureListVO> list = new ArrayList<MeasureListVO>();
			strxml = ReportUtils.xtReportXml(list);
			request.setAttribute("strxml", strxml);
		}
		return XTREPORT;
	}
	
	
	/**
	 * 跳转到报表页面
	 * @param state
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/report", method=RequestMethod.GET)
	public String report(String state,String type,HttpServletRequest request) {	
		return REPORT;
	}
	
	/**
	 * 跳转到个人报表页面
	 * @param state
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/grreport", method=RequestMethod.GET)
	public String grreport(String state,String type,HttpServletRequest request) {	
		return GRREPORT;
	}
	
	/**
	 * 获取血糖预警数据
	 * @param params
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getXtDataList", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse getXtDataList(@RequestBody JSONParam[] params,HttpServletRequest request){
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		
		String userName = paramMap.get("usersname");
		String sendTimeQ = paramMap.get("sendTimeQ");
		String sendTimeZ = paramMap.get("sendTimeZ");
		String xtstate = paramMap.get("xtstate");
		
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		MeasureSearchVO measureSearchVO = new MeasureSearchVO();
		measureSearchVO.setUserName(userName);
		measureSearchVO.setSendTimeQ(sendTimeQ);
		measureSearchVO.setSendTimeZ(sendTimeZ);
		measureSearchVO.setXtstate(xtstate);
		measureSearchVO.setOrganiseId(loginuser.getOrganiseId());
		
		PageVO pv = this.getEntityManager().queryXtDataList(measureSearchVO, pp);
		Long count = (long)pv.getCount();
		List<MeasureListVO> tmpList = pv.getList();
		return successed(new DataTableReturnObject(count, count, pp.getSEcho(),tmpList));
	}
	
	
	/**
	 * 获取血压预警数据
	 * @param params
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getXyDataList", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse getXyDataList(@RequestBody JSONParam[] params,HttpServletRequest request){
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		
		String userName = paramMap.get("usersname");
		String sendTimeQ = paramMap.get("sendTimeQ");
		String sendTimeZ = paramMap.get("sendTimeZ");
		String xtstate = paramMap.get("xtstate");
		
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		MeasureSearchVO measureSearchVO = new MeasureSearchVO();
		measureSearchVO.setUserName(userName);
		measureSearchVO.setSendTimeQ(sendTimeQ);
		measureSearchVO.setSendTimeZ(sendTimeZ);
		measureSearchVO.setXtstate(xtstate);
		measureSearchVO.setOrganiseId(loginuser.getOrganiseId());
		
		PageVO pv = this.getEntityManager().queryXyDataList(measureSearchVO, pp);
		Long count = (long)pv.getCount();
		List<MeasureListVO> tmpList = pv.getList();
		return successed(new DataTableReturnObject(count, count, pp.getSEcho(),tmpList));
	}
	
	/**
	 * 获取体温列表
	 * @param params
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/twDateList", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse twDateList(@RequestBody JSONParam[] params,HttpServletRequest request) {	
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String userName = paramMap.get("usersname");
		String deviceType = "3";
		//加入机器坐标
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		DeciveSearchVO deciveSearchVO = new DeciveSearchVO();
		deciveSearchVO.setUserName(userName);
		deciveSearchVO.setDeviceType(deviceType);
		PageVO pv = new PageVO();
		//添加代理商页面，如果是代理商登陆页面则只查询代理商下的所有设备信息
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		if("1".equals(loginuser.getUserType())){
			 pv = deviceManagerImpl.queryDeviceDataList(deciveSearchVO,loginuser.getOrganiseId(), pp);
		}else{
			//如果是普通用户则查找到用户下的设备
			if("0".equals(loginuser.getUserType())){
				deciveSearchVO.setUserId(loginuser.getUserId());
			}
			pv = deviceManagerImpl.queryDeviceDataList(deciveSearchVO, pp);
		}
		Long count = (long)pv.getCount();
		List<DeciveListVO> tmpList = pv.getList();
		return successed(new DataTableReturnObject(count, count, pp.getSEcho(),tmpList));
	}
	
	
	/**
	 * 获取血糖列表
	 * @param params
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/xtDateList", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse xtDateList(@RequestBody JSONParam[] params,HttpServletRequest request) {	
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		
		String userName = paramMap.get("usersname");
		String deviceType = "2";
		//加入机器坐标
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		DeciveSearchVO deciveSearchVO = new DeciveSearchVO();
		deciveSearchVO.setUserName(userName);
		deciveSearchVO.setDeviceType(deviceType);
		PageVO pv = new PageVO();
		//添加代理商页面，如果是代理商登陆页面则只查询代理商下的所有设备信息
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		if("1".equals(loginuser.getUserType())){
			 pv = deviceManagerImpl.queryDeviceDataList(deciveSearchVO,loginuser.getOrganiseId(), pp);
		}else{
			//如果是普通用户则查找到用户下的设备
			if("0".equals(loginuser.getUserType())){
				deciveSearchVO.setUserId(loginuser.getUserId());
			}
			pv = deviceManagerImpl.queryDeviceDataList(deciveSearchVO, pp);
		}
		
		Long count = (long)pv.getCount();
		List<DeciveListVO> tmpList = pv.getList();
		return successed(new DataTableReturnObject(count, count, pp.getSEcho(),tmpList));
	}
	
	/**
	 *  获取血糖树列表
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/reportxtTreeList", method=RequestMethod.POST)
	public void reportxtTreeList(HttpServletRequest request, HttpServletResponse response){
		
		String userName = request.getParameter("usersname");
		String deviceType = "2";
		//加入机器坐标
		@SuppressWarnings("unused")
		String sortStr = request.getParameter("bbSortName");
		DeciveSearchVO deciveSearchVO = new DeciveSearchVO();
		deciveSearchVO.setUserName(userName);
		deciveSearchVO.setDeviceType(deviceType);
		PageVO pv = new PageVO();
		//添加代理商页面，如果是代理商登陆页面则只查询代理商下的所有设备信息
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		if("1".equals(loginuser.getUserType())){
			 pv = deviceManagerImpl.queryDeviceDataList(deciveSearchVO,loginuser.getOrganiseId(), null);
		}else{
			//如果是普通用户则查找到用户下的设备
			if("0".equals(loginuser.getUserType())){
				deciveSearchVO.setUserId(loginuser.getUserId());
			}
			pv = deviceManagerImpl.queryDeviceDataList(deciveSearchVO, null);
		}
		
		@SuppressWarnings("unused")
		Long count = (long)pv.getCount();
		List<DeciveListVO> tmpList = pv.getList();
		List<TreeNodeVO> list= ListToTreeUtils.getTreeNode(tmpList);
		try {
			response.setContentType("applicatin/json;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(JSON.toJSON(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *  获取血压树列表
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/reportxyTreeList", method=RequestMethod.POST)
	public void reportxyTreeList(@RequestParam(value="username",required=false) String username, HttpServletRequest request, HttpServletResponse response){
		
		String userName = request.getParameter("usersname");
		String deviceType = "1";
		//加入机器坐标
		@SuppressWarnings("unused")
		String sortStr = request.getParameter("bbSortName");
		DeciveSearchVO deciveSearchVO = new DeciveSearchVO();
		deciveSearchVO.setUserName(userName);
		deciveSearchVO.setDeviceType(deviceType);
		PageVO pv = new PageVO();
		//添加代理商页面，如果是代理商登陆页面则只查询代理商下的所有设备信息
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		if("1".equals(loginuser.getUserType())){
			 pv = deviceManagerImpl.queryDeviceDataList(deciveSearchVO,loginuser.getOrganiseId(), null);
		}else{
			//如果是普通用户则查找到用户下的设备
			if("0".equals(loginuser.getUserType())){
				deciveSearchVO.setUserId(loginuser.getUserId());
			}
			pv = deviceManagerImpl.queryDeviceDataList(deciveSearchVO, null);
		}
		
		@SuppressWarnings("unused")
		Long count = (long)pv.getCount();
		List<DeciveListVO> tmpList = pv.getList();
		List<TreeNodeVO> list= ListToTreeUtils.getTreeNode(tmpList);
		
//		List<AolUser> tmpList = aolUserManager.findByName(username);
//		List<TreeNodeVO> list= ListToTreeUtils.getTreeNode(tmpList);
		try {
			response.setContentType("applicatin/json;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(JSON.toJSON(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *  获取体温树列表
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/reporttwTreeList", method=RequestMethod.POST)
	public void reporttwTreeList(HttpServletRequest request, HttpServletResponse response){
		
		String userName = request.getParameter("usersname");
		String deviceType = "3";
		//加入机器坐标
		@SuppressWarnings("unused")
		String sortStr = request.getParameter("bbSortName");
		DeciveSearchVO deciveSearchVO = new DeciveSearchVO();
		deciveSearchVO.setUserName(userName);
		deciveSearchVO.setDeviceType(deviceType);
		PageVO pv = new PageVO();
		//添加代理商页面，如果是代理商登陆页面则只查询代理商下的所有设备信息
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		if("1".equals(loginuser.getUserType())){
			 pv = deviceManagerImpl.queryDeviceDataList(deciveSearchVO,loginuser.getOrganiseId(), null);
		}else{
			//如果是普通用户则查找到用户下的设备
			if("0".equals(loginuser.getUserType())){
				deciveSearchVO.setUserId(loginuser.getUserId());
			}
			pv = deviceManagerImpl.queryDeviceDataList(deciveSearchVO, null);
		}
		
		List<DeciveListVO> tmpList = pv.getList();
		List<TreeNodeVO> list= ListToTreeUtils.getTreeNode(tmpList);
		try {
			response.setContentType("applicatin/json;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(JSON.toJSON(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取血压列表
	 * @param params
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/xyDateList", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse xyDateList(@RequestBody JSONParam[] params,HttpServletRequest request) {	
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String userName = paramMap.get("usersname");
		String deviceType = "1";
		//加入机器坐标
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		DeciveSearchVO deciveSearchVO = new DeciveSearchVO();
		deciveSearchVO.setUserName(userName);
		deciveSearchVO.setDeviceType(deviceType);
		PageVO pv = new PageVO();
		//添加代理商页面，如果是代理商登陆页面则只查询代理商下的所有设备信息
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		if("1".equals(loginuser.getUserType())){
			 pv = deviceManagerImpl.queryDeviceDataList(deciveSearchVO,loginuser.getOrganiseId(), pp);
		}else{
			//如果是普通用户则查找到用户下的设备
			if("0".equals(loginuser.getUserType())){
				deciveSearchVO.setUserId(loginuser.getUserId());
			}
			pv = deviceManagerImpl.queryDeviceDataList(deciveSearchVO, pp);
		}
		Long count = (long)pv.getCount();
		List<DeciveListVO> tmpList = pv.getList();
		return successed(new DataTableReturnObject(count, count, pp.getSEcho(),tmpList));
	}
	
	/**
	 * 获取设备ID 查找到设备中记录的信息
	 * @param request
	 * @param response
	 * @param deviceid
	 */
	@RequestMapping(value="/getReport", method=RequestMethod.POST)
	public void getReport(HttpServletRequest request, HttpServletResponse response, String deviceid,String userid,String measureType,String sendTimeQ,String sendTimeZ){
		List<HighCharVo> charVos = makeVoReport(deviceid,userid,measureType,sendTimeQ,sendTimeZ);
		try {
			response.setContentType("applicatin/json;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(JSON.toJSON(charVos));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<HighCharVo> makeVoReport(String deviceid,String user_id,String measureType,String sendTimeQ,String sendTimeZ){
		List<MeasureListVO> list = new ArrayList<MeasureListVO>();
		list = this.getEntityManager().queryReportDateId(user_id, deviceid, measureType,sendTimeQ,sendTimeZ);
		/*if(!(list.size()>0)){
			return "未找到记录";
		}*/
		Collections.reverse(list);
		List<HighCharVo> charVos = new ArrayList<HighCharVo>();
		//根据查询出来的数据进行处理生成报表
		switch (Integer.valueOf(measureType)) {
		case 1://血压
			charVos = ReportUtils.xyReportListVo(list);
			break;
		case 2://血糖
			charVos = ReportUtils.xtReportListVo(list);
			break;
		case 3://体温
			charVos = ReportUtils.twReportListVo(list);
			break;
		default:
			break;
		}
		return charVos;
	}
	
	public String  makeXmlReport(String deviceid,String user_id,String measureType,String sendTimeQ,String sendTimeZ){
		List<MeasureListVO> list = new ArrayList<MeasureListVO>();
		list = this.getEntityManager().queryReportDateId(user_id, deviceid, measureType,sendTimeQ,sendTimeZ);
		/*if(!(list.size()>0)){
			return "未找到记录";
		}*/
		String strxml = "";
		Collections.reverse(list);
		//根据查询出来的数据进行处理生成报表
		switch (Integer.valueOf(measureType)) {
		case 1://血压
			strxml = ReportUtils.xyReportXml(list);
			break;
		case 2://血糖
			strxml = ReportUtils.xtReportXml(list);
			break;
		case 3://体重
			strxml = ReportUtils.twReportXml(list);
			break;
		default:
			break;
		}
		return strxml;
	}
}
