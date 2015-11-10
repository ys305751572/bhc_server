package com.gcs.aol.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcs.aol.entity.Doctor;
import com.gcs.aol.service.IDoctorManager;
import com.gcs.aol.service.impl.DoctorManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

/**
 * 医师管理
 * @author yesong
 *
 */
@Controller
@RequestMapping("/management/doctor/")
public class DoctorContorller extends GenericEntityController<Doctor, Doctor, DoctorManagerImpl>{

	private static final String DOCTOR_LIST = "management/aol/doctorMgr/doctorList"; // 医师列表页面
	private static final String DOCTOR_ADD = "management/aol/doctorMgr/doctorAdd"; // 医师新增页面
	private static final String DOCTOR_EDIT = "management/aol/doctorMgr/doctorAdd"; // 医师编辑页面
	private static final String DOCTORD_DETAIL = "management/aol/doctorMgr/doctorDetail"; // 医师详情页面
	
	@Autowired
	private IDoctorManager iDoctorManager;
	
	@RequestMapping(value = "pageAdd", method = RequestMethod.GET)
	public String pageAdd() {
		return DOCTOR_ADD;
	}
	
	/**
	 * 跳转列表页面
	 * @return
	 */
	@RequestMapping(value = "paegList", method = RequestMethod.GET)
	public String paegList() {
		return DOCTOR_LIST;
	}
	
	@RequestMapping(value = "editDoctor", method = RequestMethod.GET)
	public String pageEdit(String id,Model model) {
		
		try {
			Doctor doctor = iDoctorManager.findById(id);
			model.addAttribute("doctor", doctor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DOCTOR_EDIT;
	} 
	
	/**
	 * 保存医师信息
	 * @param doctor
	 * @return
	 */
	@RequestMapping(value = "saveDoctor", method = RequestMethod.POST)
	public String modifyDoctor(Doctor doctor) {
		iDoctorManager.save(doctor);
		return DOCTOR_LIST;
	}
	
	@RequestMapping(value = "sfDoctorInfo",method = RequestMethod.POST)
	@ResponseBody
	public void sfDoctorInfo(String id, Model model,HttpServletResponse response) {
		try {
			String msg = "";
			Doctor doctor = iDoctorManager.findById(id);
			if(doctor == null) {
				msg = "无法显示";
			}
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			response.getWriter().write(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "viewDoctorInfo",method = RequestMethod.GET)
	public String viewDoctorInfo(String id, Model model) {
		try {
			Doctor doctor = iDoctorManager.findById(id);
			model.addAttribute("doctor", doctor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DOCTORD_DETAIL;
	}
	
	/**
	 * 
	 * @param doctor
	 * @param iDisplayLength 
	 * @param iDisplayStart
	 * @param sEcho
	 * @return
	 */
	@RequestMapping(value = "page",method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse pageList(@RequestBody JSONParam[] params) {
		
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		PageParameters pp = PageUtil.getParameter(paramMap, "");
		Page<Doctor> doctorPage = null;
		try {
			doctorPage = iDoctorManager.findAll(null, pp.getStart(), pp.getLength());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return successed(new DataTableReturnObject(doctorPage.getTotalElements(), doctorPage.getTotalElements(),pp.getSEcho(), doctorPage.getContent()));
	}
}
