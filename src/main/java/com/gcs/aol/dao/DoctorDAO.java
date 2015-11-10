package com.gcs.aol.dao;


import com.gcs.aol.entity.Doctor;
import com.gcs.sysmgr.service.IBaseJpaRepository;

public interface DoctorDAO extends IBaseJpaRepository<Doctor>{

	public Doctor findById(String id);
}
