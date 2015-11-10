package com.gcs.aol.service;

import org.springframework.data.domain.Page;
import com.gcs.aol.entity.Doctor;
import com.gcs.sysmgr.service.GenericManager;


public interface IDoctorManager extends GenericManager<Doctor>{
	public Doctor findById(String id) throws Exception ;
	
	public Page<Doctor> findAll(Doctor doctor,Integer currentPage, Integer pageSize) throws Exception ;
}
