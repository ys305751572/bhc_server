package com.gcs.aol.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.gcs.sysmgr.entity.AbstractEntity;

@Entity
@Table(name = "user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AolUser extends AbstractEntity implements Serializable {
	
	private static final long serialVersionUID = -2571308010092743039L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String user_id = null;
	
	/** **第一阶段验证*** */
	// 注册用户手机号
	private String mobile = null;
	// 邮箱
	private String email = null;
	
	/** *注册第二阶段填写** */
	// 注册序列码
	private String regcode = null;
	// 账户
	private String account = null;
	// 密码
	private String password = null;
	
	/** *注册第三阶段填写** */
	// 头像地址
	private String headPic = null;
	// 姓名
	private String name = null;
	// 性别
	private String sex = null;
	// 生日
	private String birthday = null;
	// 身高
	private String height = null;
	// 体重
	private String weight = null;
	
	// 机器码
	private String deviceCode = null;
	// 用户唯一令牌
	private String userToken = null;
	// 苹果token
	private String iosToken = null;
	
	// 备用字段1
	private String bak1 = null;
	// 备用字段2
	private String bak2 = null;
	// 备用字段3
	private String bak3 = null;
	// 用户类型(0:普通用户, 1:代理商管理员(取消), 2:特殊用户(取消), 99:系统管理员)
	private String bak4 = null;
    // 注册时间
	private java.sql.Timestamp bak5 = null;
	// 备用字段6
	private java.sql.Timestamp bak6 = null;
	// 备用字段7
	private Double bak7 = null;
	// 备用字段8
	private Double bak8 = null;
	
	//新增字段：备注姓名
	private String remark = null;
	
	@OneToMany
	@JoinColumn(name="user_id")
	private List<Device> list;
	
	public List<Device> getList() {
		return list;
	}

	public void setList(List<Device> list) {
		this.list = list;
	}

	public AolUser(){
	}

	public String getUserId() {
		return user_id;
	}

	public void setUserId(String userId) {
		user_id = userId;
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

	public String getRegcode() {
		return regcode;
	}

	public void setRegcode(String regcode) {
		this.regcode = regcode;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHeadpic() {
		return headPic;
	}

	public void setHeadpic(String headpic) {
		this.headPic = headpic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getDevicecode() {
		return deviceCode;
	}

	public void setDevicecode(String devicecode) {
		this.deviceCode = devicecode;
	}

	public String getUsertoken() {
		return userToken;
	}

	public void setUsertoken(String usertoken) {
		this.userToken = usertoken;
	}

	public String getIostoken() {
		return iosToken;
	}

	public void setIostoken(String iostoken) {
		this.iosToken = iostoken;
	}

	public String getBak1() {
		return bak1;
	}

	public void setBak1(String bak1) {
		this.bak1 = bak1;
	}

	public String getBak2() {
		return bak2;
	}

	public void setBak2(String bak2) {
		this.bak2 = bak2;
	}

	public String getBak3() {
		return bak3;
	}

	public void setBak3(String bak3) {
		this.bak3 = bak3;
	}

	public String getBak4() {
		return bak4;
	}

	public void setBak4(String bak4) {
		this.bak4 = bak4;
	}

	public java.sql.Timestamp getBak5() {
		return bak5;
	}

	public void setBak5(java.sql.Timestamp bak5) {
		this.bak5 = bak5;
	}

	public java.sql.Timestamp getBak6() {
		return bak6;
	}

	public void setBak6(java.sql.Timestamp bak6) {
		this.bak6 = bak6;
	}

	public Double getBak7() {
		return bak7;
	}

	public void setBak7(Double bak7) {
		this.bak7 = bak7;
	}

	public Double getBak8() {
		return bak8;
	}

	public void setBak8(Double bak8) {
		this.bak8 = bak8;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
