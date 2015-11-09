package com.gcs.aol.vo;

public class MeasureSearchVO {
	
	private String userName;
	private String sendTimeQ;
	private String sendTimeZ;
	private String xtstate;
	//机器码
	private String deviceSerial;
	//代理商
	private String organiseId;
	//设备类型
	private String measureType;
	
	public MeasureSearchVO(){
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSendTimeQ() {
		return sendTimeQ;
	}

	public void setSendTimeQ(String sendTimeQ) {
		this.sendTimeQ = sendTimeQ;
	}

	public String getSendTimeZ() {
		return sendTimeZ;
	}

	public void setSendTimeZ(String sendTimeZ) {
		this.sendTimeZ = sendTimeZ;
	}

	public String getXtstate() {
		return xtstate;
	}

	public void setXtstate(String xtstate) {
		this.xtstate = xtstate;
	}

	public String getDeviceSerial() {
		return deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public String getOrganiseId() {
		return organiseId;
	}

	public void setOrganiseId(String organiseId) {
		this.organiseId = organiseId;
	}

	public String getMeasureType() {
		return measureType;
	}

	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}
	
}
