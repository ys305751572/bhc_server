package com.gcs.aol.service.impl;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.PgDAO;
import com.gcs.aol.entity.Pg;
import com.gcs.aol.service.IPgManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class PgManagerImpl extends GenericManagerImpl<Pg, PgDAO> implements IPgManager{

	@Autowired
	private PgDAO dao;
	
	@Autowired
	private EntityManagerFactory em;

	@Override
	public Page<Pg> findAll(Pg pg, Integer start, Integer length) {
		return dao.findAll(new PageRequest(start, length, Sort.Direction.DESC, "id"));
	}
}
