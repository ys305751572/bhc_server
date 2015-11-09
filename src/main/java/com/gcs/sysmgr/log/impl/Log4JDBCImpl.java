/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, gcsdylan.com
 * Filename:		com.gcs.sysmgr.log.Log4JDBC.java
 * Class:			Log4JDBC
 * Date:			2013-5-3
 * Author:			<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version          2.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.gcs.sysmgr.log.impl;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.gcs.aol.shiro.LoginRealm;
import com.gcs.sysmgr.entity.main.LogEntity;
import com.gcs.sysmgr.log.LogLevel;
import com.gcs.sysmgr.service.LogEntityService;
import com.google.common.collect.Maps;

/** 
 * 全局日志等级<包日志等级<类和方法日志等级
 * @author 	<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version  2.1.0
 * @since   2013-5-3 下午4:41:55 
 */
public class Log4JDBCImpl extends LogAdapter {
	
	private LogLevel rootLogLevel = LogLevel.ERROR;
	
	private LogEntityService logEntityService;
	
	private Map<String, LogLevel> customLogLevel = Maps.newHashMap();

	/**
	 * 
	 * @param message
	 * @param objects
	 * @param logLevel  
	 * @see com.gcs.sysmgr.log.impl.LogAdapter#log(java.lang.String, java.lang.Object[], com.gcs.sysmgr.log.LogLevel)
	 */
	@Override
	public void log(String message, Object[] objects, LogLevel logLevel) {	
		
		MessageFormat mFormat = new MessageFormat(message);
		String result = mFormat.format(objects);
		
		if (!StringUtils.isNotBlank(result)) {
			return;
		}
		
		Subject subject = SecurityUtils.getSubject();
		LoginRealm.ShiroUser shiroUser = (LoginRealm.ShiroUser)subject.getPrincipal();
		
		LogEntity logEntity = new LogEntity();
		logEntity.setCreateTime(new Date());
		
		logEntity.setUsername(shiroUser.getLoginName());
		logEntity.setMessage(result);
		logEntity.setIpAddress(shiroUser.getIpAddress());
		logEntity.setLogLevel(logLevel);
		
		logEntityService.save(logEntity);
	}

	public void setRootLogLevel(LogLevel rootLogLevel) {
		this.rootLogLevel = rootLogLevel;
	}

	/**   
	 * 
	 * @return  
	 * @see com.gcs.sysmgr.log.LogTemplate#getRootLogLevel()  
	 */
	@Override
	public LogLevel getRootLogLevel() {
		return rootLogLevel;
	}
	
	public void setCustomLogLevel(Map<String, LogLevel> customLogLevel) {
		this.customLogLevel = customLogLevel;
	}
	
	@Override
	public Map<String, LogLevel> getCustomLogLevel() {
		return customLogLevel;
	}

	public void setLogEntityService(LogEntityService logEntityService) {
		this.logEntityService = logEntityService;
	}
	
}
