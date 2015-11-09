package com.gcs.aol.service;

import com.gcs.aol.entity.Adspublish;
import com.gcs.sysmgr.service.GenericManager;

public interface IAdspublishManager extends GenericManager<Adspublish> {

	/**
	 * 根据广告ID删除
	 * @param ads_id
	 */
	public void deleteAdsById(String ads_id);
	
}
