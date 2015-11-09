package com.gcs.aol.service.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.AppPushListDAO;
import com.gcs.aol.entity.AppPushList;
import com.gcs.aol.entity.Messages;
import com.gcs.aol.service.IAppPushListManager;
import com.gcs.aol.vo.PageVO;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;
import com.gcs.sysmgr.vo.PageParameters;

@Service
public class AppPushListManagerImpl extends GenericManagerImpl<AppPushList, AppPushListDAO> implements IAppPushListManager {
	@Autowired
	AppPushListDAO smsListDAO;
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	/**
	 * 消息数据列表
	 * @param pp
	 * @param sendeeId 接收人ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageVO queryAppInboxDataList(PageParameters pp, String sendeeId){
		PageVO pv = new PageVO();
		EntityManager em = entityManagerFactory.createEntityManager();
		
		try {
			String innerSql = "select a from ";
			String sql = "AppPushList a where  a.senduserid  = '" + sendeeId + "' ";
			
			List<Messages> list = null;
			
			Long count = queryCount(sql);
			
			Query query = em.createQuery(innerSql + sql + "order by a.sendtime DESC ");
			int firstResult = pp.getStart() * pp.getLength();
			query.setFirstResult(firstResult);
			query.setMaxResults(pp.getLength());
			list = query.getResultList();
			
			pv.setCount(count);
			pv.setStart(pp.getStart());
			pv.setLength(pp.getLength());
			pv.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return pv;
	}
	/**
	 * 查询总条数
	 * @param sql
	 * @return
	 */
	public Long queryCount(String sql) {
		Long count = Long.valueOf(0);
		String countSql = "select count(*) as count from " + sql + " ";
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery(countSql);
		count = (Long) query.getSingleResult();
		em.close();
		return count;
	}
}
