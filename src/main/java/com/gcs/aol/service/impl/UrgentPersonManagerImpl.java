package com.gcs.aol.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.UrgentPersonDAO;
import com.gcs.aol.entity.UrgentPerson;
import com.gcs.aol.service.UrgentPersonManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class UrgentPersonManagerImpl extends GenericManagerImpl<UrgentPerson, UrgentPersonDAO> implements UrgentPersonManager {

	@Autowired
	UrgentPersonDAO urgentPersonDAO;
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@SuppressWarnings("unchecked")
	public UrgentPerson findById(String id){
		EntityManager em = entityManagerFactory.createEntityManager();
		String sql = "select u from UrgentPerson u where  u.urgentperson_id ='"+id+"' ";
		Query query = em.createQuery(sql);
		List list = null;
		list = query.getResultList();
		em.close();
		if(list.size()>0){
			UrgentPerson up = (UrgentPerson) list.get(0);
			return up;
		}
		return null;
		
	}
	
	//根据用户定义查找紧急联系人或监护人
	@SuppressWarnings("unchecked")
	public List<UrgentPerson> findByUserId(String userid,String type) {
		List<UrgentPerson> uplist = new ArrayList<UrgentPerson>();
		EntityManager em = entityManagerFactory.createEntityManager();
		String sql = "select u from UrgentPerson u where  u.user_id ='"+userid+"' and bak4 = '"+type+"'";
		Query query = em.createQuery(sql);
		List list = null;
		list = query.getResultList();
		if(list.size()>0){
			for(int i=0; i<list.size(); i++){
				UrgentPerson up = (UrgentPerson) list.get(i);
				uplist.add(up);
			}
		}
		em.close();
		return uplist;
	}
	
}
