package com.gcs.aol.controller;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gcs.aol.entity.Attach;
import com.gcs.aol.entity.Pg;
import com.gcs.aol.service.IPgManager;
import com.gcs.aol.service.impl.PgManagerImpl;
import com.gcs.aol.utils.CommonUtils;
import com.gcs.aol.vo.MsgJsonReturn;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

/**
 * 病理controller
 * @author yesong
 *
 */
@RequestMapping("/management/pg/")
@Controller
public class PgController extends GenericEntityController<Pg, Pg, PgManagerImpl>{

	public static final String PG_LIST = "/management/aol/pg/pgList";
	public static final String PG_EDIT = "/management/aol/pg/pgEdit";
	
	public static final String PG_DETAIL = "/management/aol/pg/pgDetail";
	
	@Autowired
	private IPgManager manager;
	
	/**
	 * 跳转列表页面
	 * @return
	 */
	@RequestMapping(value = "pageList", method = RequestMethod.GET)
	public String pageList() {
		return PG_LIST;
	}
	
	/**
	 * 查询列表信息
	 * @return
	 */
	@RequestMapping(value = "findAll", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse findAll(@RequestBody JSONParam[] params) {
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		PageParameters pp = PageUtil.getParameter(paramMap, "");
		Page<Pg> pgPage = null;
		try {
			Pg pg = new Pg();
			pg.setTitle(paramMap.get("title"));
			pgPage = manager.findAll(pg, pp.getStart(), pp.getLength());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return successed(new DataTableReturnObject(pgPage.getTotalElements(), pgPage.getTotalElements(),pp.getSEcho(), pgPage.getContent()));
	}
	
	/**
	 * 跳转到新增页面
	 * @return
	 */
	@RequestMapping(value = "pageAdd", method = RequestMethod.GET)
	public String pageAdd() {
		return PG_EDIT;
	}
	
	/**
	 * 跳转到编辑页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "pageEdit",method = RequestMethod.GET)
	public String pageEdit(String id,Model model) {
		Pg pg = manager.queryByPK(id);
		model.addAttribute("pg", pg);
		return PG_EDIT;
	} 
	
	/**
	 * 新增 或 编辑
	 * @return
	 */
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String saveOrEdit(Pg pg, MultipartFile imageFile,HttpServletRequest request) {
		Pg _pg = null;
		if(StringUtils.isNotBlank(pg.getId())) {
			_pg = manager.queryByPK(pg.getId());
		}
		
		if(imageFile!=null&&imageFile.getSize()>0){
			String webRoot = request.getSession().getServletContext().getRealPath("");
			Attach attach  = CommonUtils.uploadAttach(imageFile, webRoot, "/upload/qc/",null);
			if(StringUtils.isNotBlank(attach.getAttachId()))
				pg.setImage("/upload/qc/"+attach.getAttachName());
		}
		if(_pg != null && StringUtils.isBlank(pg.getImage())) {
			pg.setImage(_pg.getImage());
		}
		pg.setCreateDate(new Date());
		manager.save(pg);
		return PG_LIST;
	}
	
	/**
	 * 删除记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn delete(String id) {
		manager.deleteByPK(id);
		return new MsgJsonReturn(true, "删除成功");
	}
	
	/**
	 * 删除记录
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail", method = RequestMethod.POST)
	public String detail(String id, Model model) {
		Pg pg = manager.queryByPK(id);
		model.addAttribute("pg", pg);
		return PG_DETAIL;
	}
}
