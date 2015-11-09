package com.gcs.aol.vo;


import com.gcs.aol.entity.Device;

public class DeciveListVO  extends Device {
	private static final long serialVersionUID = 1L;
	private String xhid;//序号ID 用于构建树ID
	private String userName;//用户名字
	private String userId;//用户ID
	private String userNumber;//用户号
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getXhid() {
		return xhid;
	}

	public void setXhid(String xhid) {
		this.xhid = xhid;
	}

	
	
}
