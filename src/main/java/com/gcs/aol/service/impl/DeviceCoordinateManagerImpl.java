package com.gcs.aol.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.DeviceCoordinateDAO;
import com.gcs.aol.dao.MeasureDAO;
import com.gcs.aol.entity.DeviceCoordinate;
import com.gcs.aol.service.IDeviceCoordinateManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class DeviceCoordinateManagerImpl extends GenericManagerImpl<DeviceCoordinate, DeviceCoordinateDAO> implements IDeviceCoordinateManager {

	@Autowired
	MeasureDAO measureDAO;
	@Autowired
	EntityManagerFactory entityManagerFactory;

	@SuppressWarnings("unchecked")
	//根据ID最新的一条数据
	public DeviceCoordinate findNewByID(String deviceid) {
		String sql = "from DeviceCoordinate where device_id = '"+deviceid+"' order  by coordinate_time desc ";
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery(sql);
		List<DeviceCoordinate> dclist = new ArrayList<DeviceCoordinate>();
		dclist = query.getResultList();
		em.close();
		if(dclist.size()!=0){
			return dclist.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	//根据ID查询设备所有记录的坐标点
	public List<DeviceCoordinate> findByID(String deviceid) {
		String sql = "from DeviceCoordinate where device_id = '"+deviceid+"' order  by coordinate_time desc ";
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery(sql);
		List<DeviceCoordinate> dclist = new ArrayList<DeviceCoordinate>();
		dclist = query.getResultList();
		em.close();
		return dclist;
	}
}
