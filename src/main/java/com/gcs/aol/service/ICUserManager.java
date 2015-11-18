package com.gcs.aol.service;

import org.springframework.data.domain.Page;

import com.gcs.aol.entity.CUser;
import com.gcs.sysmgr.service.GenericManager;

public interface ICUserManager extends GenericManager<CUser>{

	public Page<CUser> finaAll(CUser cuser,Integer currentPage, Integer pageSize);
}
