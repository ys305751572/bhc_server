package com.gcs.aol.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcs.aol.entity.LogInfo;
import com.gcs.aol.service.impl.LogInfoManagerImpl;
import com.gcs.aol.vo.PageVO;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

@Controller
@RequestMapping("/management/loginfo")
public class LogInfoController extends GenericEntityController<LogInfo, LogInfo, LogInfoManagerImpl> {

	private static final String LOGSLIST = "management/aol/logMgr/logsList";
	
	/**
	 * 跳转到日志信息列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/logslist", method=RequestMethod.GET)
	public String logslist(HttpServletRequest request) {	
		return LOGSLIST;
	}
	
	/**
	 * 获取日志列表数据
	 * @param params
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getLogsList", method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse getLogsList(@RequestBody JSONParam[] params, HttpServletRequest request) {
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		PageVO pv = this.getEntityManager().queryLogInfoDataList(pp, paramMap);
		Long count = (long)pv.getCount();
		List tmpList = pv.getList();
		return successed(new DataTableReturnObject(count, count, pp.getSEcho(),tmpList));
	}
	
}
