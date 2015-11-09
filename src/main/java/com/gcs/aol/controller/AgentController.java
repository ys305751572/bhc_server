package com.gcs.aol.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gcs.aol.entity.Measure;
import com.gcs.aol.service.impl.MeasureManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;

@Controller
@RequestMapping("/management/agent")
public class AgentController extends GenericEntityController<Measure,Measure,MeasureManagerImpl>{
	
	private static final String AGENTLIST = "management/aol/agentMgr/agentlist";//血糖预警
	
	/**
	 * 跳转代理商管理页面
	 * @param state
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/agentlist", method=RequestMethod.GET)
	public String agentlist(String state,String type,HttpServletRequest request) {	
		return AGENTLIST;
	}
}
