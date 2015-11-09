package com.gcs.aol.service.impl;

import java.sql.Connection;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.AdspublishDAO;
import com.gcs.aol.entity.Adspublish;
import com.gcs.aol.service.IAdspublishManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class AdspublishManagerImpl extends GenericManagerImpl<Adspublish, AdspublishDAO> implements IAdspublishManager {
	@Autowired
	AdspublishDAO adspublishDAO;
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	/**
	 * 根据广告ID删除
	 * @param ads_id
	 */
	public void deleteAdsById(String ads_id){
		EntityManager em = null;
		Connection con = null;
		Statement st = null;
		
		try {
			em = entityManagerFactory.createEntityManager();
			SessionImplementor session =em.unwrap(SessionImplementor.class);
			con = session.connection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			st = con.createStatement();
			st.executeUpdate("delete from adspublish where imageadsId = '" + ads_id.replaceAll(";", "") + "'");
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(st != null){
					st.close();
				}
				if(con != null){
					con.close();
				}
				if(em != null){
					em.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
