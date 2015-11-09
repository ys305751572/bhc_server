package com.gcs.aol.service;

import org.springframework.data.domain.Page;
import com.gcs.aol.entity.Doctor;


public interface IDoctorManager {

	public int add(Doctor doctor) throws Exception;
	
	public int detele(String id) throws Exception;
	
	public int modify(Doctor doctor) throws Exception;
	
	public Doctor findById(String id) throws Exception ;
	
	public Page<Doctor> findAll(Doctor doctor,Integer currentPage, Integer pageSize) throws Exception ;
}
