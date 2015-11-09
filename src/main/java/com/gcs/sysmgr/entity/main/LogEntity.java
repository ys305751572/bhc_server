/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, gcsdylan.com
 * Filename:		com.gcs.sysmgr.entity.main.LogEntity.java
 * Class:			LogEntity
 * Date:			2013-5-3
 * Author:			<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version          2.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.gcs.sysmgr.entity.main;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.gcs.sysmgr.entity.IdEntity;
import com.gcs.sysmgr.log.LogLevel;

/** 
 * 	
 * @author 	<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version  2.1.0
 * @since   2013-5-3 下午4:43:44 
 */
@Entity
@Table(name="security_log_entity")
public class LogEntity extends IdEntity {

	/** 描述  */
	private static final long serialVersionUID = 6057051455824317181L;

	@Column(length=255)
	private String message;
	
	@Column(length=32)
	private String username;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Column(length=16)
	private String ipAddress;
	
	@Column(length=16)
	@Enumerated(EnumType.STRING)
	private LogLevel logLevel;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
