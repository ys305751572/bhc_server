package com.gcs.aol.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.OrganiseDAO;
import com.gcs.aol.entity.Organise;
import com.gcs.aol.service.IOrganiseManager;
import com.gcs.aol.vo.PageVO;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;
import com.gcs.sysmgr.vo.PageParameters;

@Service
public class OrganiseManagerImpl extends GenericManagerImpl<Organise, OrganiseDAO> implements IOrganiseManager {
	@Autowired
	OrganiseDAO organiseDAO;
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	/**
	 * 获取公司列表数据
	 * @param pp
	 * @param parentid
	 * @param orgname
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageVO queryOrganiseDataList(PageParameters pp, String parentid, String orgname){
		PageVO pv = new PageVO();
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			String innerSql = "select o from ";
			String sql = "Organise o where o.dr=0 and o.levelType != 0 ";
			
			if(StringUtils.isNotBlank(parentid)){
				sql = sql + "and (o.parentId = '" + parentid + "' or o.organiseId = '" + parentid + "') ";
			}
			if(StringUtils.isNotBlank(orgname)){
				sql = sql + "and o.organiseName like '%" + orgname + "%' ";
			}
			
			List<Organise> list = null;
			
			Long count = queryCount(sql);
			Query query = em.createQuery(innerSql + sql + "order by o.levelType ASC, o.createTime DESC ");
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
	 * 根据用户ID查找代理商
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Organise getOrgByUserId(String userId){
		EntityManager em = entityManagerFactory.createEntityManager();
		List<Organise> list = null;
		Query query = em.createQuery("select o from Organise o where o.dr=0 and o.userId like '%" + userId.trim() + "%' ");
		list = query.getResultList();
		em.close();
		if(list != null && list.size() > 0){
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 根据设备序列号查找代理商
	 * @param devcode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Organise getOrgByDevCode(String devcode){
		EntityManager em = entityManagerFactory.createEntityManager();
		List<Organise> list = null;
		Query query = em.createQuery("select o from Organise o,OrganiseDevice od where o.organiseId = od.organiseId and od.deviceSerial = '" + devcode + "' ");
		list = query.getResultList();
		em.close();
		if(list.size()==0){
			return null;
		}
		return list.get(0);
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
	
	@Override
	public String findCountOrg(String id,String newAddTime) {
		Long count = Long.valueOf(0);
		String countSql = "select count(*) as count from Organise where parentId = '"+id+"' ";
		if(StringUtils.isNotBlank(newAddTime)){
			countSql += "and createTime > '"+newAddTime+"'";
		}
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery(countSql);
		count = (Long) query.getSingleResult();
		em.close();
		return count.toString();
	}
}
