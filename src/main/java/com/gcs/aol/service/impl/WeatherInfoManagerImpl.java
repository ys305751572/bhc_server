package com.gcs.aol.service.impl;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.WeatherInfoDAO;
import com.gcs.aol.entity.WeatherInfo;
import com.gcs.aol.service.IWeatherInfoManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class WeatherInfoManagerImpl extends GenericManagerImpl<WeatherInfo, WeatherInfoDAO> implements IWeatherInfoManager {
	@Autowired
	WeatherInfoDAO weatherInfoDAO;
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	

}
