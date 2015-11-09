package com.gcs.aol.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.gcs.sysmgr.entity.AbstractEntity;

@Entity
@Table(name = "loginfo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LogInfo extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = -6629335435802704292L;
	
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String loginfo_id = null;
	//日志内容
	private String logcontent  = null;
	//日志操作用户ID
	private String username = null;
	//登录IP
	private String ip_address = null;
	//日志操作时间
	private java.sql.Timestamp logtime = null;
	//备用字段1
	private String bak1 = null;
	//备用字段2
	private String bak2 = null;
	//备用字段3
	private String bak3 = null;
	//备用字段4
	private String bak4 = null;
	//备用字段5
	private java.sql.Timestamp bak5 = null;
	//时间戳
	private java.sql.Timestamp bak6 = null;
	//备用字段7
	private Double bak7 = null;
	//备用字段8
	private Double bak8 = null;
	
	public LogInfo(){
	}

	public String getLoginfo_id() {
		return loginfo_id;
	}

	public void setLoginfo_id(String loginfoId) {
		loginfo_id = loginfoId;
	}

	public String getLogcontent() {
		return logcontent;
	}

	public void setLogcontent(String logcontent) {
		this.logcontent = logcontent;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ipAddress) {
		ip_address = ipAddress;
	}

	public java.sql.Timestamp getLogtime() {
		return logtime;
	}

	public void setLogtime(java.sql.Timestamp logtime) {
		this.logtime = logtime;
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
}
