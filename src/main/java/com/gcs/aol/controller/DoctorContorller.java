package com.gcs.aol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcs.aol.entity.Doctor;
import com.gcs.aol.service.IDoctorManager;
import com.gcs.aol.service.impl.DoctorManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.utils.DataTableReturnObject;
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

	private static final String DOCTORLIST = "management/aol/doctorMgr/doctorList"; // 医师列表页面
	private static final String DOCTORADD = "management/aol/doctorMgr/doctorAdd"; // 医师新增页面
	private static final String DOCTOREDIT = "management/aol/doctorMgr/doctorEdit"; // 医师编辑页面
	
	@Autowired
	private IDoctorManager iDoctorManager;
	
	@RequestMapping(value = "pageAdd", method = RequestMethod.POST)
	@ResponseBody
	public String pageAdd() {
		return DOCTORADD;
	}
	
	public JSONResponse createDoctor(Doctor doctor) {
		this.getEntityManager().save(doctor);
		return successed(doctor);
	}
	
	public String pageEdit() {
		return DOCTOREDIT;
	} 
	
	public JSONResponse modifyDoctor(Doctor doctor) {
		this.getEntityManager().update(doctor);
		return successed(doctor);
	}
	
	public String pageDetail(Doctor doctor) {
		return DOCTORLIST;
	}
	
	/**
	 * 
	 * @param doctor
	 * @param iDisplayLength 
	 * @param iDisplayStart
	 * @param sEcho
	 * @return
	 */
	public JSONResponse pageList(Doctor doctor,Integer iDisplayLength, Integer iDisplayStart,String sEcho) {
		int currentPage = PageUtil.getCurrentPage(iDisplayStart, iDisplayStart);
		Page<Doctor> doctorPage = null;
		try {
			doctorPage = iDoctorManager.findAll(doctor, currentPage, iDisplayLength);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return successed(new DataTableReturnObject(doctorPage.getTotalElements(), doctorPage.getTotalElements(), sEcho, doctorPage.getContent()));
	}
}
