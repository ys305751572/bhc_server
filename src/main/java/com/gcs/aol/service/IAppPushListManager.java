package com.gcs.aol.service;


import com.gcs.aol.entity.AppPushList;
import com.gcs.aol.vo.PageVO;
import com.gcs.sysmgr.service.GenericManager;
import com.gcs.sysmgr.vo.PageParameters;

public interface IAppPushListManager extends GenericManager<AppPushList> {

	PageVO queryAppInboxDataList(PageParameters pp, String sendeeId);

}
