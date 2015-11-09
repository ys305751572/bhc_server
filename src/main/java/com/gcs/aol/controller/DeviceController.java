package com.gcs.aol.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.gcs.aol.entity.AolUser;
import com.gcs.aol.entity.Device;
import com.gcs.aol.entity.Organise;
import com.gcs.aol.entity.OrganiseDevice;
import com.gcs.aol.entity.UserDevice;
import com.gcs.aol.service.IAolUserManager;
import com.gcs.aol.service.IMeasureManager;
import com.gcs.aol.service.IOrganiseDeviceManager;
import com.gcs.aol.service.IOrganiseManager;
import com.gcs.aol.service.IUserDeviceManager;
import com.gcs.aol.service.impl.DeviceManagerImpl;
import com.gcs.aol.utils.CommonUtils;
import com.gcs.aol.utils.ExcelImportField;
import com.gcs.aol.utils.UUIDGenerate;
import com.gcs.aol.vo.DeviceUploadVO;
import com.gcs.aol.vo.LoginUserVO;
import com.gcs.aol.vo.MeasureListVO;
import com.gcs.aol.vo.MeasureSearchVO;
import com.gcs.aol.vo.MsgJsonReturn;
import com.gcs.aol.vo.PageVO;
import com.gcs.aol.vo.TreeChildNodeVO;
import com.gcs.aol.vo.TreeNodeVO;
import com.gcs.sysmgr.SecurityConstants;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

@Controller
@RequestMapping("/management/devices")
public class DeviceController extends GenericEntityController<Device, Device, DeviceManagerImpl> {
	
	@Autowired
	IUserDeviceManager iuserDeviceManager;
	@Autowired
	IAolUserManager iuserManager;
	@Autowired
	IOrganiseManager iOrganiseManager;
	@Autowired
	IMeasureManager iMeasureManager;
	@Autowired
	IOrganiseDeviceManager iOrganiseDeviceManager;
	
	private static final String QUERYDEVICE = "management/aol/usersMgr/queryDevice";
	private static final String DEVICEINFO = "management/aol/usersMgr/deviceInfo";
	
	private static final String DEVICESLIST = "management/aol/deviceMgr/devicesList";
	private static final String UPLOADDEVICE = "management/aol/deviceMgr/uploadDevice";
	
	private static final String DEVTRANSFER = "management/aol/deviceMgr/devTransfer";
	/** 机器码测量数据页面 */
	private static final String QUERYMEASURELIST = "management/aol/deviceMgr/queryMeasureList";
	
	@RequestMapping(value="/gotoqueryDevice", method=RequestMethod.GET)
	public String gotoqueryDevice(HttpServletRequest request) {	
		return QUERYDEVICE;
	}
	
	/**
	 * 设备查询接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryDevice", method=RequestMethod.GET)
	public String queryDevice(HttpServletRequest request) {	
		String deviceCode = null;
		Boolean isOk = true;
		try {
			deviceCode = java.net.URLDecoder.decode(request.getParameter("deviceCode"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//查找到登陆用户的用户ID
		LoginUserVO loginuser = (LoginUserVO)request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		
		if(StringUtils.isNotBlank(deviceCode)){
			List<Device> devlist = this.getEntityManager().getDevByCode(deviceCode, loginuser.getOrganiseId());
			if(devlist != null){
				if(devlist.size()>1){
					request.setAttribute("modelSize", "2");
				}else{
					request.setAttribute("modelSize", "1");
				}
				request.setAttribute("device", devlist.get(0));
				
				List<UserDevice> userDeviceList = iuserDeviceManager.queryByProperty("device_id", devlist.get(0).getDevice_id());
				if(userDeviceList != null){
					List<AolUser> userList = new ArrayList<AolUser>();
					for(int i=0; i<userDeviceList.size(); i++){
						AolUser user = iuserManager.queryByPK(userDeviceList.get(i).getUser_id());
						userList.add(user);
					}
					request.setAttribute("aoluserList", userList);
				} else {
					request.setAttribute("aoluserList", null);
				}
				Organise org = iOrganiseManager.getOrgByDevCode(devlist.get(0).getDeviceSerial());
				if(org != null){
					request.setAttribute("org", org);
				} else {
					request.setAttribute("org", null);
				}
				
			} else {
				isOk = false;
			}
		} else {
			isOk = false;
		}
		
		request.setAttribute("isOk", isOk);
		return DEVICEINFO;
	}
	
	/**
	 * 跳转到设备信息页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/deviceslist", method=RequestMethod.GET)
	public String deviceslist(HttpServletRequest request) {
		request.setAttribute("deviceType", request.getParameter("deviceType"));
		request.setAttribute("starttime", request.getParameter("starttime"));
		return DEVICESLIST;
	}
	
	/**
	 * 获取设备列表数据
	 * @param params
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getDevicesDataList", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse getDevicesDataList(@RequestBody JSONParam[] params,HttpServletRequest request){
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		String organiseId = "";
		if("1".equals(loginuser.getUserType())){
			organiseId = loginuser.getOrganiseId();
		}
		
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String starttime = paramMap.get("starttime");
		String deviceSerial = paramMap.get("deviceSerial");
		String organiseName = paramMap.get("organiseName");
		String usedState = paramMap.get("usedState");
		String sortStr = paramMap.get("bbSortName");
		String deviceType = paramMap.get("deviceType");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		PageVO pv = this.getEntityManager().queryDevicesDataList(pp, deviceSerial, organiseName, usedState,deviceType,starttime, organiseId);
		Long count = (long)pv.getCount();
		List tmpList = pv.getList();
		return successed(new DataTableReturnObject(count, count, pp.getSEcho(),tmpList));
	}
	
	/**
	 * 跳转到设备批量导入页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/uploaddevice", method=RequestMethod.GET)
	public String uploaddevice(HttpServletRequest request) {
		return UPLOADDEVICE;
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
	 * 解析文件，并将数据导入数据库中
	 * @param request
	 * @param session
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping(value = "/uploadDeviceFile", method = RequestMethod.POST)
	public String uploadDeviceFile(HttpServletRequest request, HttpSession session, MultipartFile uploadFile) {
		String organise_id = request.getParameter("orgChoose");
		String msg = "";
		String batch_number = CommonUtils.getDateOf16();
		if(uploadFile!=null && uploadFile.getSize()>0){
			InputStream is = null;
			Workbook rwb = null;
			Sheet sheet = null;
			
			//文件名
			String filename = uploadFile.getOriginalFilename();
			//文件扩展名
			String kzm = CommonUtils.getExcelKzm(filename);
			
			Connection con = null;
			PreparedStatement pstm = null;
			
			//项目导入规则对象
			DeviceUploadVO deviceupload = new DeviceUploadVO();
			//获取导入有效字段Map
			Map<String, ExcelImportField> fieldMap = deviceupload.getFieldsMap();
			List<ExcelImportField> fieldList = new ArrayList<ExcelImportField>();
			
			try {
				//获取文件刘
				is = uploadFile.getInputStream();
				//根据扩展名不同，将文件流转换成Excel对象
				if(".XLS".equals(kzm)){
					rwb = new HSSFWorkbook(is);
				} else if (".XLSX".equals(kzm)){
					//rwb = new XSSFWorkbook(is);
					msg = "文件《" + filename + "》类型不支持，请转化成Excel 2003版，谢谢配合！";
					request.setAttribute("msg", msg);
					return UPLOADDEVICE;
				}
				//获取Excel对象第一个工作簿
				sheet = rwb.getSheetAt(0);
				//总行数
				int rowNum = sheet.getLastRowNum() - sheet.getFirstRowNum() + 1;
				if(rowNum < 2){
					msg = "文件《" + filename + "》没有数据！";
					request.setAttribute("msg", msg);
					return UPLOADDEVICE;
				}
				//有效列数
				int columnNum = 0;
				String insertSql = "INSERT INTO " + deviceupload.getTableName() + "(";
				String field_str = "";
				String value_str = "";
				//默认第一行为表头字段项
				Iterator<Cell> cellList_top = sheet.getRow(0).cellIterator();
				//遍历表头，与有效字段进行比对，比对成功的获取字段信息，比对不上的设为null
				for(int i=0; cellList_top.hasNext(); i++){
					String columnName = cellList_top.next().toString().trim();
					ExcelImportField field = fieldMap.get(columnName);
					if(field != null){
						fieldList.add(field);
						//拼接有效字段
						field_str = field_str + field.getFieldCode() + ",";
						value_str = value_str + "?,";
						columnNum++;
					} else {
						fieldList.add(null);
					}
				}
				if(columnNum == 0){
					msg = "文件《" + filename + "》内容不符合规范！";
					request.setAttribute("msg", msg);
					return UPLOADDEVICE;
				}
				insertSql = insertSql + field_str + deviceupload.getTablePk() + ",organiseId,batchNumber,orderIndex,bindTime,dr,ts) VALUES(" + value_str + "?,?,?,?,?,?,?)";
				
				//获取数据库连接
				con = this.getEntityManager().getConnection();
				con.setAutoCommit(false);
				pstm = con.prepareStatement(insertSql);
				//导入成功数
				int sucessNum = 0;
				//默认第一行为表头，第二行开始才是数据行
				for(int i=1; i<rowNum; i++){
					Row row = sheet.getRow(i);
					//空行验证
					if(CommonUtils.checkIsNullCell(row)){
						continue;
					}
					
					int ps_num = 1;
					for(int j=0; j<fieldList.size(); j++){
						ExcelImportField newfield = fieldList.get(j);
						if(newfield == null){
							continue;
						}
						Cell fieldCell = row.getCell(j);
						if(fieldCell == null){
							pstm.setString(ps_num, "");
						} else {
							pstm.setString(ps_num, fieldCell.toString());
						}
						ps_num++;
					}
					//生成主键
					pstm.setString(ps_num, UUIDGenerate.generate32UUID());
					pstm.setString(ps_num+1, organise_id);
					pstm.setString(ps_num+2, batch_number);
					pstm.setInt(ps_num+3, i);
					pstm.setTimestamp(ps_num+4, new Timestamp(System.currentTimeMillis()));
					pstm.setInt(ps_num+5, 0);
					pstm.setTimestamp(ps_num+6, new Timestamp(System.currentTimeMillis()));
					pstm.addBatch();
					
					sucessNum++;
					if((sucessNum % 100) == 0){
						pstm.executeBatch();
						con.commit();
						pstm.clearBatch();
					}
				}
				
				pstm.executeBatch();
				con.commit();
				pstm.clearBatch();
				
				msg = "数据导入完成,成功导入【" + sucessNum + "】条数据!";
			} catch (Exception e) {
				msg = "数据导入失败!";
				e.printStackTrace();
			} finally {
				try {
					if(pstm != null){
						pstm.close();
					}
					if(con != null){
						con.close();
					}
					if(is != null){
						is.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			msg = "文件丢失，请重新上传！";
		}
		
		request.setAttribute("msg", msg);
		return UPLOADDEVICE;
	}
	
	/**
	 * 下载设备批量导入模板
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/download", method = { RequestMethod.GET,RequestMethod.POST })
	public void download(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		try {
			String webRoot = request.getSession().getServletContext().getRealPath("");
			request.setCharacterEncoding("utf-8");
			String ctxPath = webRoot + "//upload//dev//" ;
			String downLoadPath = ctxPath + "设备批量导入模板.xls";
			long fileLength = new File(downLoadPath).length(); 
			response.setContentType("application/x-msdownload;charset=utf-8"); 
			response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("设备批量导入模板.xls","UTF-8"));
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
	
	/**
	 * 跳转到设备转移页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/gotoDevTransfer", method=RequestMethod.GET)
	public String gotoDevTransfer(HttpServletRequest request) {	
		return DEVTRANSFER;
	}
	
	/**
	 * 获取设备与代理商树
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/getOrgAndDevTreeData", method={RequestMethod.POST})
	public void getOrgAndDevTreeData(HttpServletRequest request, HttpServletResponse response){
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
					
					List<Object> childrenDevicesNodeList = null;
					//生成当前代理商节点
					TreeNodeVO orgTreeNode = new TreeNodeVO("O_" + org.getOrganiseId(), org.getOrganiseName(), "closed", "icon-briefcase");
					//查找当前代理商的所属用户节点
					List<Device> deviceOfOrgList = this.getEntityManager().queryDevicesByOrgId(org.getOrganiseId());
					//遍历，生成用户节点
					if(deviceOfOrgList != null && deviceOfOrgList.size() > 0){
						if(childrenDevicesNodeList == null){
							childrenDevicesNodeList = new ArrayList<Object>();
						}
						for(int j=0; j<deviceOfOrgList.size(); j++){
							Device deviceOfOrg = deviceOfOrgList.get(j);
							//生成用户节点
							childrenDevicesNodeList.add(new TreeChildNodeVO("D_" + deviceOfOrg.getDevice_id(), getDevTypeName(deviceOfOrg.getDeviceType())+"【"+deviceOfOrg.getDeviceSerial()+"】", "icon-hdd"));
						}
					}
					//将用户节点添加到代理商子节点
					orgTreeNode.setChildren(childrenDevicesNodeList);
					//添加当前代理商节点
					childrenNodeList.add(orgTreeNode);
				}
			}
			
			//设置无代理商节点
			TreeNodeVO noOrgTreeNode = new TreeNodeVO("W_WDLS00000000000000000000000000", "无代理商", "closed", "icon-briefcase");
			//设置无代理商节点的子节点集合
			List<Object> childrenNoOrgDevicesNodeList = new ArrayList<Object>();
			//获取所有无代理商的设备
			List<Device> deviceList = this.getEntityManager().queryNoOrgDevices();
			if(deviceList != null && deviceList.size() > 0){
				for(int j=0; j<deviceList.size(); j++){
					Device dev = deviceList.get(j);
					childrenNoOrgDevicesNodeList.add(new TreeChildNodeVO("W_" + dev.getDevice_id(), getDevTypeName(dev.getDeviceType())+"【"+dev.getDeviceSerial()+"】", "icon-hdd"));
				}
				noOrgTreeNode.setChildren(childrenNoOrgDevicesNodeList);
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
			
			List<Device> deviceList = this.getEntityManager().queryDevicesByOrgId(parenttid);
			if(deviceList != null && deviceList.size() > 0){
				if(childrenNodeList == null){
					childrenNodeList = new ArrayList<Object>();
				}
				for(int j=0; j<deviceList.size(); j++){
					Device dev = deviceList.get(j);
					childrenNodeList.add(new TreeChildNodeVO("D_" + dev.getDevice_id(), getDevTypeName(dev.getDeviceType())+"【"+dev.getDeviceSerial()+"】", "icon-hdd"));
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
	 * 根据设备类型编码获取设备类型
	 * @param devType （1.血压 2、血糖、3.体温 4.运动）
	 * @return
	 */
	private String getDevTypeName(String devType){
		String typeName = "";
		if("1".equals(devType)){
			typeName = "血压";
		} else if("2".equals(devType)){
			typeName = "血糖";
		} else if("3".equals(devType)){
			typeName = "体温";
		} else if("4".equals(devType)){
			typeName = "运动";
		} else {
			typeName = "其他";
		}
		
		return typeName;
	}
	
	/**
	 * 执行设备转移操作
	 * @param doState
	 * @param userIds
	 * @param orgId
	 * @return
	 */
	@RequestMapping(value = "/doChangeDev", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn doChangeDev(String devIds, String orgId){
		boolean isOk = false;
		if(StringUtils.isNotBlank(devIds) && StringUtils.isNotBlank(orgId)){
			String devidsStr = "";
			List<String> onOrgDevids = new ArrayList<String>();
			
			String[] devidList = devIds.split("@@");
			for(int i=0; i<devidList.length; i++){
				String dStr = devidList[i];
				if("D_".equals(dStr.substring(0, 2))){
					devidsStr = devidsStr + "'" + dStr.substring(2, dStr.length()) + "',";
				} else if("W_".equals(dStr.substring(0, 2))){
					onOrgDevids.add(dStr.substring(2, dStr.length()));
				}
			}
			if(StringUtils.isNotBlank(devidsStr)){
				devidsStr = devidsStr.substring(0, devidsStr.length()-1);
			}
			
			int resultNum = 0;
			if(StringUtils.isNotBlank(devidsStr)){
				resultNum = this.getEntityManager().doChangeDevices(devidsStr, orgId);
			}
			if(onOrgDevids.size() > 0){
				for(int o=0; o<onOrgDevids.size(); o++){
					Device ddd = this.getEntityManager().queryByPK(onOrgDevids.get(o));
					
					OrganiseDevice od = new OrganiseDevice();
					od.setDeviceSerial(ddd.getDeviceSerial());
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
	 * 跳转到机器码查询测量数据页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/querymeasurelist", method=RequestMethod.GET)
	public String querymeasurelist(HttpServletRequest request) {	
		request.setAttribute("xtstate", request.getParameter("xtstate"));
		request.setAttribute("sendTimeQ", request.getParameter("starttime"));
		request.setAttribute("measureType", request.getParameter("measureType"));
		return QUERYMEASURELIST;
	}
	
	/**
	 * 根据机器码获取测量数据列表
	 * @param params
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getMeasureDataList", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse getMeasureDataList(@RequestBody JSONParam[] params,HttpServletRequest request){
		//获取登录用户
		LoginUserVO loginuser = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		
		String deviceSerial = paramMap.get("deviceSerial");
		String sendTimeQ = paramMap.get("sendTimeQ");
		String sendTimeZ = paramMap.get("sendTimeZ");
		String measureType = paramMap.get("measureType");
		String username = paramMap.get("username");
		String xtstate = paramMap.get("xtstate");
		//System.out.println(username);
		
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		MeasureSearchVO measureSearchVO = new MeasureSearchVO();
		measureSearchVO.setOrganiseId(loginuser.getOrganiseId());
		measureSearchVO.setDeviceSerial(deviceSerial);
		measureSearchVO.setSendTimeQ(sendTimeQ);
		measureSearchVO.setSendTimeZ(sendTimeZ);
		measureSearchVO.setMeasureType(measureType);
		measureSearchVO.setUserName(username);
		measureSearchVO.setXtstate(xtstate);
		
		PageVO pv = iMeasureManager.queryMeasureDataList(measureSearchVO, pp);
		Long count = (long)pv.getCount();
		List<MeasureListVO> tmpList = pv.getList();
		return successed(new DataTableReturnObject(count, count, pp.getSEcho(),tmpList));
	}
	
}
