package com.gcs.sysmgr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gcs.sysmgr.dao.ModuleDesktopDAO;
import com.gcs.sysmgr.entity.main.ModuleDesktop;
import com.gcs.sysmgr.service.ModuleDesktopService;

@Service
@Transactional
public class ModuleDesktopServiceImpl implements ModuleDesktopService {

	@Autowired
	ModuleDesktopDAO moduleDesktopDAO;

	public List<ModuleDesktop> findByUserId(Long userId) {
		return moduleDesktopDAO.findByUserId(userId);
	}

	public void desktopSetting(List<ModuleDesktop> mds, Long userId) {
		List<ModuleDesktop> list = moduleDesktopDAO.findByUserId(userId);
		moduleDesktopDAO.delete(list);
		moduleDesktopDAO.save(mds);
	}

}
