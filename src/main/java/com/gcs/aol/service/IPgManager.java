package com.gcs.aol.service;

import org.springframework.data.domain.Page;

import com.gcs.aol.entity.Pg;
import com.gcs.sysmgr.service.GenericManager;

/**
 * 病理 / 讲座
 * @author yesong
 *
 */
public interface IPgManager extends GenericManager<Pg>{

	public Page<Pg> findAll(Pg pg,Integer start,Integer length);
}
