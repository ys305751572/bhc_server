package com.gcs.aol.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcs.aol.entity.DeviceCoordinate;
import com.gcs.aol.service.impl.DeviceCoordinateManagerImpl;
import com.gcs.aol.service.impl.DeviceManagerImpl;
import com.gcs.aol.vo.DeciveListVO;
import com.gcs.aol.vo.DeciveSearchVO;
import com.gcs.aol.vo.LoginUserVO;
import com.gcs.aol.vo.PageVO;
import com.gcs.sysmgr.SecurityConstants;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

@Controller
@RequestMapping("/management/position")
public class PositionController extends GenericEntityController<DeviceCoordinate,DeviceCoordinate,DeviceCoordinateManagerImpl>{

	@Autowired
	private DeviceManagerImpl deviceManagerImpl;
	@Autowired
	private DeviceCoordinateManagerImpl coordinateManagerImpl;
	
	private static final String WZXXLIST = "management/aol/PositionMgr/wzxxlist";//定点查询List
	private static final String WZXXGJLIST = "management/aol/PositionMgr/wzgjxxlist";//轨迹查询List
	private static final String HJJKLIST = "management/aol/PositionMgr/hjjklist";//呼救监控信息查询
	private static final String GRWZXXLIST = "management/aol/PositionMgr/grwzxxlist";//定点查询List
	private static final String GRWZXXGJLIST = "management/aol/PositionMgr/grwzgjxxlist";//轨迹查询List
	private static final String GRHJJKLIST = "management/aol/PositionMgr/grhjjklist";//呼救监控信息查询
	private static final String GDDHPAGEDD = "management/aol/gddhpage/gddhpagedd";//定点查询导航
	private static final String GDDHGJPAGEDD = "management/aol/gddhpage/gddhgjpagedd";//轨迹查询导航
//	private static final String GRGDDHPAGEDD = "management/aol/gddhpage/grgddhpagedd";//定点查询导航
//	private static final String GRGDDHGJPAGEDD = "management/aol/gddhpage/grgddhgjpagedd";//轨迹查询导航
	
	
	@RequestMapping(value="/wzxxlist", method=RequestMethod.GET)
	public String wzlist(String state,String type,HttpServletRequest request) {	
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		if("0".equals(loginuser.getUserType())){
			return GRWZXXLIST;
		}
		return WZXXLIST;
		
	}
	
	@RequestMapping(value="/hjjklist", method=RequestMethod.GET)
	public String hjjklist(String state,String type,HttpServletRequest request) {	
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		if("0".equals(loginuser.getUserType())){
			return GRHJJKLIST;
		}
		return HJJKLIST;
	}
	
	@RequestMapping(value="/wzgjxxlist", method=RequestMethod.GET)
	public String wzgjlist(String state,String type,HttpServletRequest request) {	
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		if("0".equals(loginuser.getUserType())){
			return GRWZXXGJLIST;
		}
		return WZXXGJLIST;
		
	}
	
	@RequestMapping(value="/gddhpagedd", method=RequestMethod.GET)
	public String gddhpagedd(String state,String type,HttpServletRequest request) {	
		/**
		 * 定点查询高德导航数据
		 */
		return GDDHPAGEDD;
	}
	
	@RequestMapping(value="/gddhgjpagedd", method=RequestMethod.GET)
	public String gddhgjpagedd(String state,String type,HttpServletRequest request) {	
		/**
		 * 定点查询高德导航数据
		 */
		return GDDHGJPAGEDD;
	}
	
	/*@RequestMapping(value="/grgddhpagedd", method=RequestMethod.GET)
	public String grgddhpagedd(String state,String type,HttpServletRequest request) {	
		*//**
		 * 定点查询高德导航数据
		 *//*
		return GRGDDHPAGEDD;
	}
	
	@RequestMapping(value="/grgddhgjpagedd", method=RequestMethod.GET)
	public String grgddhgjpagedd(String state,String type,HttpServletRequest request) {	
		*//**
		 * 定点查询高德导航数据
		 *//*
		return GRGDDHGJPAGEDD;
	}*/
	
	//查询设备集合
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/jqDateList", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse jqDateList(@RequestBody JSONParam[] params,HttpServletRequest request) {	
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		
		String userName = paramMap.get("usersname");
		String deviceType = paramMap.get("deviceType");
		//如果是普通用户就进行此处理--start
		DeciveSearchVO deciveSearchVO = new DeciveSearchVO();
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		//loginuser.setUserType("0");
		//loginuser.setUserId("00000000000000000000000000000021");
		System.out.println(loginuser.getUserId());
		if("0".equals(loginuser.getUserType())){
			//如果是普通用户，则查询设备时加入用户条件，只查找当前用户的设备ID
			deciveSearchVO.setUserId(loginuser.getUserId());
		}
		//--end
		//加入机器坐标
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		deciveSearchVO.setUserName(userName);
		deciveSearchVO.setDeviceType(deviceType);
		PageVO pv = deviceManagerImpl.queryDeviceDataList(deciveSearchVO, pp);
		Long count = (long)pv.getCount();
		List<DeciveListVO> tmpList = pv.getList();
		return successed(new DataTableReturnObject(count, count, pp.getSEcho(),tmpList));
	}
	
	
	//查询呼救设备集合
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/hjDateList", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse hjDateList(@RequestBody JSONParam[] params,HttpServletRequest request) {	
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		
		String userName = paramMap.get("usersname");
		String deviceType = paramMap.get("deviceType");
		//如果是普通用户就进行此处理--start
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		//loginuser.setUserType("0");
		//loginuser.setUserId("00000000000000000000000000000014");
		//System.out.println(loginuser.getUserId());
		DeciveSearchVO deciveSearchVO = new DeciveSearchVO();
		if("0".equals(loginuser.getUserType())){
			//如果是普通用户，则查询设备时加入用户条件，只查找当前用户的设备ID
			deciveSearchVO.setUserId(loginuser.getUserId());
		}
		//--end
		//加入机器坐标
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		deciveSearchVO.setUserName(userName);
		deciveSearchVO.setDeviceType(deviceType);
		PageVO pv = deviceManagerImpl.queryHjDataList(deciveSearchVO, pp);
		Long count = (long)pv.getCount();
		List<DeciveListVO> tmpList = pv.getList();
		return successed(new DataTableReturnObject(count, count, pp.getSEcho(),tmpList));
	}
	
	
	/**
	 * 查询出设备坐标位置的信息
	 * 
	 * @param request
	 * @param response
	 * @param deviceid
	 */
	@RequestMapping(value="/getDdCoordinate", method=RequestMethod.POST)
	public void getDdCoordinate(HttpServletRequest request, HttpServletResponse response, String deviceid){
		//根据设备ID查找坐标
		DeviceCoordinate devicecoordinate= coordinateManagerImpl.findNewByID(deviceid);
		String msg = "";
		if(devicecoordinate == null){
			msg = "未作记录";
		}else{
			msg = devicecoordinate.getX_coordinate()+"%"+devicecoordinate.getY_coordinate();
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
	 * 查询出设备轨迹坐标位置的信息
	 * 
	 * @param request
	 * @param response
	 * @param deviceid
	 */
	@RequestMapping(value="/getGjCoordinate", method=RequestMethod.POST)
	public void getGjCoordinate(HttpServletRequest request, HttpServletResponse response, String deviceid){
		//根据设备ID查找坐标
		List<DeviceCoordinate> dclist= coordinateManagerImpl.findByID(deviceid);
		String msg = "";
		if(dclist == null||dclist.size() == 0){
			msg = "未作记录";
		}else{
			for (DeviceCoordinate deviceCoordinate : dclist) {
				msg = msg+"%"+deviceCoordinate.getX_coordinate()+"*"+deviceCoordinate.getY_coordinate();
			}
		}
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
