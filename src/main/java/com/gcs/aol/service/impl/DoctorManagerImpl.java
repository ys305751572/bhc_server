package com.gcs.aol.service.impl;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.DoctorDAO;
import com.gcs.aol.entity.Doctor;
import com.gcs.aol.service.IDoctorManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class DoctorManagerImpl extends GenericManagerImpl<Doctor, DoctorDAO> implements IDoctorManager {

	@Autowired
	private EntityManagerFactory em;
	
	@Autowired
	private DoctorDAO doctorDAO;
	
	@Override
	public int add(Doctor doctor) throws Exception {
		
		return 0;
	}

	@Override
	public int detele(String id) throws Exception {
		return 0;
	}

	@Override
	public int modify(Doctor doctor) throws Exception {
		return 0;
	}

	@Override
	public Doctor findById(String id) throws Exception {
		return null;
	}

	/**
	 * 查询医师列表
	 */
	@Override
	public Page<Doctor> findAll(final Doctor doctor,Integer currentPage, Integer pageSize) throws Exception {
		return doctorDAO.findAll(new PageRequest(currentPage, pageSize, Sort.Direction.DESC, "id"));
	}
}
