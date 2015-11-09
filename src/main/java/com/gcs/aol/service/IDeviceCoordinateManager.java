package com.gcs.aol.service;

import com.gcs.aol.entity.DeviceCoordinate;
import com.gcs.sysmgr.service.GenericManager;

public interface IDeviceCoordinateManager extends GenericManager<DeviceCoordinate>{
	
	public DeviceCoordinate findNewByID(String deviceid);
}
