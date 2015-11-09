package com.gcs.aol.service;


import java.util.Map;

import com.gcs.aol.entity.LogInfo;
import com.gcs.aol.vo.PageVO;
import com.gcs.sysmgr.service.GenericManager;
import com.gcs.sysmgr.vo.PageParameters;

public interface ILogInfoManager extends GenericManager<LogInfo> {

	/**
	 * 获取日志信息列表数据
	 * @param pp
	 * @param pMap
	 * @return
	 */
	public PageVO queryLogInfoDataList(PageParameters pp, Map<String, String> pMap);
}
