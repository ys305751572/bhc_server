package com.gcs.aol.utils;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import com.gcs.aol.entity.LogInfo;
import com.gcs.aol.service.impl.LogInfoManagerImpl;
import com.gcs.sysmgr.util.ServiceLocator;

public class LogUtils {
	
	public static void main(String[] args) {
		System.out.println(DateUtil.GetCurrentTime());
	}
	
	/**
	 * 保存登录日志
	 * @param username 用户名
	 * @param content 日志内容
	 * @param ipAddress IP地址
	 */
	public static void writeLog(String username,String content,String ipAddress){
		LogInfoManagerImpl logManagerImpl = (LogInfoManagerImpl)ServiceLocator.lookup(LogInfoManagerImpl.class);
		LogInfo log = new LogInfo();
		log.setUsername(username);
		log.setLogcontent(content);
		log.setIp_address(ipAddress);
		log.setLogtime(new Timestamp(System.currentTimeMillis()));
		logManagerImpl.save(log);
	}
	
	/**
	 * 获取登录IP
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getIpAddr(HttpServletRequest request) {  
		String ip = "";
		try {
			ip = request.getHeader("x-forwarded-for");  
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			    ip = request.getHeader("Proxy-Client-IP");  
			}  
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			    ip = request.getHeader("WL-Proxy-Client-IP");  
			}  
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			    ip = request.getHeader("HTTP_CLIENT_IP");  
			}  
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			    ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
			}  
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			    ip = request.getRemoteAddr();  
			} 
		} catch (Exception e) {
			ip = "";
		}
		return ip;
	}
	
}
