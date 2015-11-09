package com.gcs.aol.service;

import java.util.List;

import com.gcs.aol.entity.UrgentPerson;
import com.gcs.sysmgr.service.GenericManager;

public interface UrgentPersonManager extends GenericManager<UrgentPerson> {

	public List<UrgentPerson> findByUserId(String userid,String type);
	
	public UrgentPerson findById(String id);
}
