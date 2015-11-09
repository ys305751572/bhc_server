package com.gcs.aol.service;

import com.gcs.aol.entity.UserDevice;
import com.gcs.sysmgr.service.GenericManager;

public interface IUserDeviceManager extends GenericManager<UserDevice> {

	/**
	 * 根据用户ID和设备ID，检测是否已绑定
	 * @param userid
	 * @param deviceid
	 * @return -1:已绑定，0或大于0为当前最大序号
	 */
	public int checkIsExist(String userid, String deviceid);
	
}
