package com.gcs.aol.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.ImageadsDAO;
import com.gcs.aol.entity.Imageads;
import com.gcs.aol.service.IImageadsManager;
import com.gcs.aol.vo.PageVO;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;
import com.gcs.sysmgr.vo.PageParameters;

@Service
public class ImageadsManagerImpl extends GenericManagerImpl<Imageads, ImageadsDAO> implements IImageadsManager {
	@Autowired
	ImageadsDAO imageadsDAO;
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	/**
	 * 获取广告列表数据
	 * @param pp
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageVO queryAdsDataList(PageParameters pp, String _title, String adsType, String adsState){
		PageVO pv = new PageVO();
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			String innerSql = "select a from ";
			String sql = "Imageads a where a.dr=0 ";
			
			if(StringUtils.isNotBlank(_title)){
				sql = sql + " and a.title like '%" + _title + "%' ";
			}
			
			if(StringUtils.isNotBlank(adsType)){
				sql = sql + " and a.adsType = '" + adsType + "' ";
			}
			
			if(StringUtils.isNotBlank(adsState)){
				sql = sql + " and a.adsState = '" + adsState + "' ";
			}
			
			List<Imageads> list = null;
			
			Long count = queryCount(sql);
			
			Query query = em.createQuery(innerSql + sql + "order by a.createTime ASC ");
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
	 * 根据登录人获取广告
	 * @param loginuser_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Imageads> adsListByLoginuser(String loginuser_id){
		EntityManager em = entityManagerFactory.createEntityManager();
		List<Imageads> list = null;
		try {
			String sql = "select a from Imageads a, Adspublish p where a.dr=0 and a.imageadsId=p.imageadsId and p.receiveId = '" + loginuser_id + "' order by p.publishTime DESC  ";
			Query query = em.createQuery(sql);
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return list;
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
