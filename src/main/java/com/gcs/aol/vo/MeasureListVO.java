package com.gcs.aol.vo;

import com.gcs.aol.entity.Measure;

public class MeasureListVO extends Measure {
	private static final long serialVersionUID = 1591544716206998888L;
	
	private String measureName;
	
	private String userId;
	
	private String deviceSerial;
	
	private String userNumber;//用户号

	public String getMeasureName() {
		return measureName;
	}

	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeviceSerial() {
		return deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	
	
}
