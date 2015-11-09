package com.gcs.aol.service.impl;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.OrganiseDeviceDAO;
import com.gcs.aol.entity.OrganiseDevice;
import com.gcs.aol.service.IOrganiseDeviceManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class OrganiseDeviceManagerImpl extends GenericManagerImpl<OrganiseDevice, OrganiseDeviceDAO> implements IOrganiseDeviceManager {
	@Autowired
	OrganiseDeviceDAO organiseDeviceDAO;
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	
}