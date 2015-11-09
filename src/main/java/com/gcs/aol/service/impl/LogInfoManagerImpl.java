package com.gcs.aol.service.impl;


import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.LogInfoDAO;
import com.gcs.aol.entity.LogInfo;
import com.gcs.aol.service.ILogInfoManager;
import com.gcs.aol.vo.PageVO;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;
import com.gcs.sysmgr.vo.PageParameters;

@Service
public class LogInfoManagerImpl extends GenericManagerImpl<LogInfo, LogInfoDAO> implements ILogInfoManager {
	@Autowired
	LogInfoDAO logInfoDAO;
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	/**
	 * 获取日志信息列表数据
	 * @param pp
	 * @param pMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageVO queryLogInfoDataList(PageParameters pp, Map<String, String> pMap){
		PageVO pv = new PageVO();
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			String selectSql = "select l from ";
			String sql = "LogInfo l where l.dr=0 ";
			if(StringUtils.isNotBlank(pMap.get("usersname"))){
				sql = sql + "and l.username like '%" + pMap.get("usersname").trim() + "%' ";
			}
			if(StringUtils.isNotBlank(pMap.get("logTimeQ"))){
				sql = sql + "and l.logtime >= '" + pMap.get("logTimeQ") + " 00:00:00' ";
			}
			if(StringUtils.isNotBlank(pMap.get("logTimeZ"))){
				sql = sql + "and l.logtime <= '" + pMap.get("logTimeZ") + " 23:59:59' ";
			}
			
			List<LogInfo> list = null;
			
			Long count = queryCount(sql);
			Query query = em.createQuery(selectSql + sql + " order by l.logtime DESC ");
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
		String countSql = "select count(*) as count from " + sql + "";
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery(countSql);
		count = (Long) query.getSingleResult();
		em.close();
		return count;
	}
}
