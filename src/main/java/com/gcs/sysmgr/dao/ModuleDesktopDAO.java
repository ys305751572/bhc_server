package com.gcs.sysmgr.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcs.sysmgr.entity.main.ModuleDesktop;

/**
 * 
 * <code>ModuleDesktopDAO.java</code>
 * <p>
 * 功能:
 * 
 * 
 * @author dylan hwzhanga@sina.com 时间 2013-7-7 下午08:03:00
 * @version 1.0 </br>
 */
public interface ModuleDesktopDAO extends JpaRepository<ModuleDesktop, String> {

	List<ModuleDesktop> findByUserId(Long userId);

}
