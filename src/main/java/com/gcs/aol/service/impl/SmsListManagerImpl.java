package com.gcs.aol.service.impl;


import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.SmsListDAO;
import com.gcs.aol.entity.SmsList;
import com.gcs.aol.service.ISmsListManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class SmsListManagerImpl extends GenericManagerImpl<SmsList, SmsListDAO> implements ISmsListManager {
	@Autowired
	SmsListDAO smsListDAO;
	@Autowired
	EntityManagerFactory entityManagerFactory;
}
