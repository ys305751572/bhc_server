package com.gcs.aol.vo;

import com.gcs.aol.entity.Device;

public class DeviceListVO extends Device {
	private static final long serialVersionUID = 949164765390654308L;

	//代理商名称
	private String organiseName;
	
	//使用状态
	private String usedState;

	public String getOrganiseName() {
		return organiseName;
	}

	public void setOrganiseName(String organiseName) {
		this.organiseName = organiseName;
	}

	public String getUsedState() {
		return usedState;
	}

	public void setUsedState(String usedState) {
		this.usedState = usedState;
	}
}
