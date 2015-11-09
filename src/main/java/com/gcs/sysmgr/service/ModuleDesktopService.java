package com.gcs.sysmgr.service;

import java.util.List;

import com.gcs.sysmgr.entity.main.ModuleDesktop;

/**
 * 
 * <code>ModuleDesktopService.java</code>
 * <p>
 * 功能:
 * 
 * 
 * @author dylan hwzhanga@sina.com 时间 2013-7-7 下午07:58:00
 * @version 1.0 </br>
 */
public interface ModuleDesktopService {

	List<ModuleDesktop> findByUserId(Long userId);
	
	void desktopSetting(List<ModuleDesktop> mds,Long userId);
}
