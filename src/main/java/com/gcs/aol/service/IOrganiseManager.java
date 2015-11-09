package com.gcs.aol.service;

import com.gcs.aol.entity.Organise;
import com.gcs.aol.vo.PageVO;
import com.gcs.sysmgr.service.GenericManager;
import com.gcs.sysmgr.vo.PageParameters;

public interface IOrganiseManager extends GenericManager<Organise> {
	
	/**
	 * 获取公司列表数据
	 * @param pp
	 * @param parentid
	 * @param orgname
	 * @return
	 */
	public PageVO queryOrganiseDataList(PageParameters pp, String parentid, String orgname);
	
	/**
	 * 根据用户ID查找代理商
	 * @param userId
	 * @return
	 */
	public Organise getOrgByUserId(String userId);
	
	/**
	 * 根据设备序列号查找代理商
	 * @param devcode
	 * @return
	 */
	public Organise getOrgByDevCode(String devcode);

	public String findCountOrg(String id,String newAddTime);
}
