package com.gcs.aol.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.UserDeviceDAO;
import com.gcs.aol.entity.UserDevice;
import com.gcs.aol.service.IUserDeviceManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class UserDeviceManagerImpl extends GenericManagerImpl<UserDevice, UserDeviceDAO> implements IUserDeviceManager {
	@Autowired
	UserDeviceDAO userDeviceDAO;
	@Autowired
	EntityManagerFactory entityManagerFactory;

	/**
	 * 根据用户ID和设备ID，检测是否已绑定
	 * @param userid
	 * @param deviceid
	 * @return -1:已绑定，0或大于0为当前最大序号
	 */
	@SuppressWarnings("unchecked")
	public int checkIsExist(String userid, String deviceid){
		String sql = "select ud from UserDevice ud where ud.user_id='" + userid + "' and ud.device_id='" + deviceid + "' ";
		
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery(sql);
		List list = query.getResultList();
		if(list != null && list.size() > 0){
			em.close();
			return -1;
		} else {
			int max = 0;
			String sqlMax = "select max(_order) from userdevice where user_id = '" + userid + "'";
			Query queryMax = em.createNativeQuery(sqlMax);
			List listMax = queryMax.getResultList();
			if(listMax != null && listMax.size() > 0){
				max = Integer.parseInt(listMax.get(0)==null ? "0" : listMax.get(0).toString());
			} else {
				max = 0;
			}
			
			em.close();
			return max;
		}
	}
	
}
