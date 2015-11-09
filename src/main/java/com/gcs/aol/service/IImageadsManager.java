package com.gcs.aol.service;

import java.util.List;

import com.gcs.aol.entity.Imageads;
import com.gcs.aol.vo.PageVO;
import com.gcs.sysmgr.service.GenericManager;
import com.gcs.sysmgr.vo.PageParameters;

public interface IImageadsManager extends GenericManager<Imageads> {

	/**
	 * 获取广告列表数据
	 * @param pp
	 * @return
	 */
	public PageVO queryAdsDataList(PageParameters pp, String _title, String adsType, String adsState);
	
	/**
	 * 根据登录人获取广告
	 * @param loginuser_id
	 * @return
	 */
	public List<Imageads> adsListByLoginuser(String loginuser_id);
}
