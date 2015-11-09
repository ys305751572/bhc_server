package com.gcs.aol.vo;

/**
 * 登录用户信息
 */
public class LoginUserVO {
	//用户ID
	private String userId = null;
	// 注册用户手机号
	private String mobile = null;
	// 邮箱
	private String email = null;
	// 账户
	private String account = null;
	// 姓名
	private String name = null;
	//用户类型(0:普通用户, 1:代理商管理员, 2:特殊用户, 99:总公司)
	private String userType = null;
	//代理商ID
	private String organiseId = null;
	//代理商名称
	private String organiseName  = null;
	//代理商简称
	private String organiseShortname = null;
	//登录IP地址
	private String ipAddress;
	//登录时间
	private java.sql.Timestamp loginTime = null;
	
	public LoginUserVO(){
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getOrganiseId() {
		return organiseId;
	}

	public void setOrganiseId(String organiseId) {
		this.organiseId = organiseId;
	}

	public String getOrganiseName() {
		return organiseName;
	}

	public void setOrganiseName(String organiseName) {
		this.organiseName = organiseName;
	}

	public String getOrganiseShortname() {
		return organiseShortname;
	}

	public void setOrganiseShortname(String organiseShortname) {
		this.organiseShortname = organiseShortname;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public java.sql.Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(java.sql.Timestamp loginTime) {
		this.loginTime = loginTime;
	}
}
