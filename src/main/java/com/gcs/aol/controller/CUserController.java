package com.gcs.aol.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcs.aol.entity.CUser;
import com.gcs.aol.service.ICUserManager;
import com.gcs.aol.service.impl.CUserManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

/**
 * 用户--子女端
 * @author yesong
 *
 */
@Controller
@RequestMapping("/cuser/")
public class CUserController extends GenericEntityController<CUser, CUser, CUserManagerImpl>{

	@Autowired
	private ICUserManager manager;
	
	/**
	 * 子女用户列表
	 * @param params
	 * @return
	 */
	public JSONResponse listPage(@RequestBody JSONParam[] params) {
		
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		PageParameters pp = PageUtil.getParameter(paramMap, "");
		Page<CUser> cuserPage = null;
		try {
			CUser d = new CUser();
			d.setNickname(paramMap.get("nickname"));
			cuserPage = manager.finaAll(d, pp.getStart(), pp.getLength());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return successed(new DataTableReturnObject(cuserPage.getTotalElements(), cuserPage.getTotalElements(),pp.getSEcho(), cuserPage.getContent()));
	}
	
	
}
