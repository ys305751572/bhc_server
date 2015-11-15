package com.gcs.aol.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.DoctorDAO;
import com.gcs.aol.entity.Doctor;
import com.gcs.aol.service.IDoctorManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class DoctorManagerImpl extends GenericManagerImpl<Doctor, DoctorDAO> implements IDoctorManager {
	
	@Autowired
	private DoctorDAO doctorDAO;
	@Autowired
	private EntityManagerFactory em;

	@Override
	public Doctor findById(String id) throws Exception {
		return doctorDAO.findById(id);
	}

	/**
	 * 查询医师列表
	 */
	@Override
	public Page<Doctor> findAll(final Doctor doctor,Integer currentPage, Integer pageSize) throws Exception {
		Specification<Doctor> spec = buildSpecification(doctor);
		return doctorDAO.findAll(spec,new PageRequest(currentPage, pageSize, Sort.Direction.DESC, "id"));
	}
	
	@Override
	public Specification<Doctor> buildSpecification(final Doctor d) {
		return new Specification<Doctor>() {
			@Override
			public Predicate toPredicate(Root<Doctor> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
				
				if(d.getName() != null) {
					list.add(cb.like(root.get("name").as(String.class), "%" + d.getName() + "%"));
				}
			    Predicate[] p = new Predicate[list.size()];  
			    return cb.and(list.toArray(p));  
			}
		};
	}
}
