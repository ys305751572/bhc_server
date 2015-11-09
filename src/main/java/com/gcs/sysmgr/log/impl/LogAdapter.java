/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, gcsdylan.com
 * Filename:		com.gcs.sysmgr.log.LogAdapter.java
 * Class:			LogTemplateAdapter
 * Date:			2013-5-3
 * Author:			<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version          2.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.gcs.sysmgr.log.impl;

import java.util.Map;

import com.gcs.sysmgr.log.LogAPI;
import com.gcs.sysmgr.log.LogLevel;
import com.google.common.collect.Maps;

/** 
 * 	
 * @author 	<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version  2.1.0
 * @since   2013-5-3 下午5:21:07 
 */

public class LogAdapter implements LogAPI {

	/**   
	 * @param message
	 * @param logLevel  
	 * @see com.gcs.sysmgr.log.LogAPI#log(java.lang.String, com.gcs.sysmgr.log.LogLevel)  
	 */
	public void log(String message, LogLevel logLevel) {
		log(message, null, logLevel);
	}

	/**   
	 * @param message
	 * @param objects
	 * @param logLevel  
	 * @see com.gcs.sysmgr.log.LogAPI#log(java.lang.String, java.lang.Object[], com.gcs.sysmgr.log.LogLevel)  
	 */
	public void log(String message, Object[] objects, LogLevel logLevel) {
		
	}
	
	/**   
	 * @return  
	 * @see com.gcs.sysmgr.log.LogAPI#getRootLogLevel()  
	 */
	public LogLevel getRootLogLevel() {
		return LogLevel.ERROR;
	}

	/**   
	 * @return  
	 * @see com.gcs.sysmgr.log.LogAPI#getCustomLogLevel()  
	 */
	public Map<String, LogLevel> getCustomLogLevel() {
		return Maps.newHashMap();
	}
}
