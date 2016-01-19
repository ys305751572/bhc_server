package com.gcs.aol.service.impl;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.AolUserDAO;
import com.gcs.aol.entity.AolChildRelation;
import com.gcs.aol.entity.AolUser;
import com.gcs.aol.service.IAolUserManager;
import com.gcs.aol.vo.PageVO;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;
import com.gcs.sysmgr.vo.PageParameters;

@Service
public class AolUserManagerImpl extends GenericManagerImpl<AolUser, AolUserDAO> implements IAolUserManager {
	@Autowired
	AolUserDAO aolUserDAO;
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	public AolUser findByUserId(String userId){
		return aolUserDAO.findOne(userId);
	}
	/**
	 * 根据帐号查找用户
	 * @param account 帐号
	 * @return
	 */
	public AolUser findByAccount(String account){
		return aolUserDAO.findByAccount(account);
	}
	
	/**
	 * 根据帐号查找用户
	 * @param account
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AolUser> queryAccountList(String account){
		String sql = "select u from AolUser u where u.account='" + account.replaceAll(";", "") + "' ";
		EntityManager em = entityManagerFactory.createEntityManager();
		List<AolUser> list = null;
		
		try {
			Query query = em.createQuery(sql);
			list = (List<AolUser>) query.getResultList();
		} catch (Exception e) {
			list = null;
		}
		em.close();
		return list;
	}
	
	/**
	 * 根据手机号查找用户
	 * @param mobile 手机号
	 * @return
	 */
	public AolUser findByMobile(String mobile){
		return aolUserDAO.findByMobile(mobile);
	}
	
	/**
	 * 根据手机号查找用户
	 * @param mobile 手机号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AolUser> queryMobileList(String mobile){
		String sql = "select u from AolUser u where u.mobile='" + mobile.replaceAll(";", "") + "' ";
		EntityManager em = entityManagerFactory.createEntityManager();
		List<AolUser> list = null;
		
		try {
			Query query = em.createQuery(sql);
			list = (List<AolUser>) query.getResultList();
		} catch (Exception e) {
			list = null;
		}
		em.close();
		return list;
	}
	
	/**
	 * 根据邮箱查找用户
	 * @param email 邮箱
	 * @return
	 */
	public AolUser findByEmail(String email){
		return aolUserDAO.findByEmail(email);
	}
	
	/**
	 * 根据邮箱查找用户
	 * @param email 邮箱
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AolUser> queryEmailList(String email){
		String sql = "select u from AolUser u where u.email='" + email.replaceAll(";", "") + "' ";
		EntityManager em = entityManagerFactory.createEntityManager();
		List<AolUser> list = null;
		
		try {
			Query query = em.createQuery(sql);
			list = (List<AolUser>) query.getResultList();
		} catch (Exception e) {
			list = null;
		}
		em.close();
		return list;
	}
	
	/**
	 * 查找用户列表，做成通用方法能够查抄到普通用户，代理商用户，特殊用户
	 * @param pp
	 * @param usersname
	 * @param bak4 参数0,普通用户  1,代理商用户  2,特殊用户
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageVO queryUsersDataList(PageParameters pp, String user_id,String username,String bak4){
		PageVO pv = new PageVO();
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			String innerSql = "select u from ";
			String sql = "AolUser u where (dr = 0 or dr is null) ";
			if(StringUtils.isNotBlank(user_id)){
				sql = sql + "and u.user_id = '" + user_id + "' ";
			}
			if(StringUtils.isNotBlank(username)){
				sql = sql + " and (u.name like '%" + username + "%' or u.remark like '%"+username+"%') ";
			}
			if("0".equals(bak4)){
				sql += " and (bak4 = '"+bak4+"' or bak4 is null ) ";
				
			}
			List<AolUser> list = null;
			Long count = queryCount(sql);
			Query query = em.createQuery(innerSql + sql + "order by u.bak5 DESC ");
			int firstResult = pp.getStart() * pp.getLength();
			if(pp.getLength()>=0){
				query.setFirstResult(firstResult);
				query.setMaxResults(pp.getLength());
			}
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
	 * 查找用户列表，做成通用方法能够查抄到普通用户，代理商用户，特殊用户
	 * @param pp
	 * @param usersname
	 * @param bak4 参数0,普通用户  1,代理商用户  2,特殊用户
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageVO queryFoucsUsersDataList(PageParameters pp, String user_id,String username,String bak4){
		PageVO pv = new PageVO();
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			String innserSql = "select _ar from ";
			String sql = "AolChildRelation _ar WHERE _ar.childId = '" + user_id + "'";
			List<AolChildRelation> list = null;
			Long count = queryCount2(sql);
			Query query = em.createQuery(innserSql + sql);
			int firstResult = pp.getStart() * pp.getLength();
			if(pp.getLength()>=0){
				query.setFirstResult(firstResult);
				query.setMaxResults(pp.getLength());
			}
			list = query.getResultList();
			List<AolUser> aolUserList = new ArrayList<AolUser>();
			for (AolChildRelation ar : list) {
				aolUserList.add(ar.getAolUser());
			}
			pv.setCount(count);
			pv.setStart(pp.getStart());
			pv.setLength(pp.getLength());
			pv.setList(aolUserList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return pv;
	}
	
	/**
	 * 获取用户列表数据
	 * @param pp
	 * @param usersname
	 * @param regTimeQ
	 * @param regTimeZ
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageVO queryUsersDataList(PageParameters pp, String usersname, String sexType, String mobile, String birthday, String regTimeQ, String regTimeZ, String orgId){
		PageVO pv = new PageVO();
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			String innerSql = "select u from ";
			String sql = "AolUser u where  (u.bak4 is null or u.bak4='0') ";
			
			if(StringUtils.isNotBlank(usersname)){
				sql = sql + "and (u.name like '%" + usersname + "%' or u.remark like '%" + usersname + "%') ";
			}
			if(StringUtils.isNotBlank(sexType)){
				sql = sql + "and u.sex = '"+ sexType +"'";
			}
			if(StringUtils.isNotBlank(mobile)){
				sql = sql + "and u.mobile like '%"+ mobile +"%'";
			}
			if(StringUtils.isNotBlank(birthday)){
				sql = sql + "and u.birthday = '"+ birthday +"'";
			}
			if(StringUtils.isNotBlank(sexType)){
				sql = sql + "and u.sex = '"+ sexType +"'";
			}
			if(StringUtils.isNotBlank(regTimeQ)){
				sql = sql + "and u.bak5 >= '" + regTimeQ + "' ";
			}
			if(StringUtils.isNotBlank(regTimeZ)){
				sql = sql + "and u.bak5 <= '" + regTimeZ + "' ";
			}
			
			List<AolUser> list = null;
			if(!"00000000000000000000000000000000".equals(orgId)){
				String userIdStr = "";
				List<String> userIds = getUserIdsByOrg(orgId);
				if(userIds != null){
					for(int i=0; i<userIds.size(); i++){
						userIdStr = userIdStr + "'" + userIds.get(i) + "',";
					}
				}
				if(StringUtils.isNotBlank(userIdStr)){
					userIdStr = userIdStr.substring(0, userIdStr.length()-1);
				} else {
					userIdStr = "'          '";
				}
				
				sql = sql + "and u.user_id in (" + userIdStr + ")";
			}
			
			Long count = queryCount(sql);
			
			Query query = em.createQuery(innerSql + sql + "order by u.bak5 DESC ");
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
	 * 查找所有用户(此方法只限总公司使用)
	 * 根据传递过来的时间
	 */
	public String findAllUser(String newAddTime){
		Long count = Long.valueOf(0);
		String countSql = "select count(*) as count from AolUser where (bak4 is null or bak4='0')";
		if(StringUtils.isNotBlank(newAddTime)){
			countSql += " and bak5 > '"+newAddTime+"' ";
		}
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery(countSql);
		count = (Long) query.getSingleResult();
		em.close();
		return count.toString();
	}
	/**
	 * 根据代理商ID获取其所属用户总数
	 * @param orgid
	 * @return
	 */
	public String queryCountUsersByOrgid(String orgid,String newAddTime){
		//sql语句不能够这样写 以后要修改
		Long count = Long.valueOf(0);
		EntityManager em = entityManagerFactory.createEntityManager();
		String deviceSql = "select d.device_id from device d right join organisedevice od on od.organiseId = '" + orgid.replaceAll(";", "") + "' and od.deviceSerial = d.deviceSerial where d.device_id is not null";
		String useridSql = "select distinct(user_id) from userdevice ud where ud.device_id in (" + deviceSql + ")";
		String sql = "select count(*) as count from user u where (u.bak4 is null or u.bak4 = '0') ";
		if(StringUtils.isNotBlank(newAddTime)){
			sql += "and  u.bak5 > '"+newAddTime+"' ";
		}
		sql += " and u.user_id in (" + useridSql + ") ";
		Query query = em.createNativeQuery(sql);
		count = Long.valueOf(query.getSingleResult().toString()) ;
		em.close();
		return count.toString();
	}
	/**
	 * 根据代理商ID获取其所属用户
	 * @param orgid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AolUser> queryUsersByOrgid(String orgid){
		EntityManager em = entityManagerFactory.createEntityManager();
		String deviceSql = "select d.device_id from device d right join organisedevice od on od.organiseId = '" + orgid.replaceAll(";", "") + "' and od.deviceSerial = d.deviceSerial where d.device_id is not null";
		String useridSql = "select distinct(user_id) from userdevice ud where ud.device_id in (" + deviceSql + ")";
		String sql = "select u.user_id, u.name, u.mobile from user u where (u.bak4 is null or u.bak4 = '0') and u.user_id in (" + useridSql + ")";
		
		List<AolUser> userList = new ArrayList<AolUser>();
		List list = null;
		
		try {
			Query query = em.createNativeQuery(sql);
			list = query.getResultList();
			for(int i=0; i<list.size(); i++){
				Object[] ob = (Object[])list.get(i);
				
				AolUser user =  new AolUser();
				user.setUserId(ob[0].toString());
				if(ob[1] == null){
					user.setName("【未知】");
				} else if("".equals(ob[1].toString().replaceAll(" ", ""))){
					user.setName("【未知】");
				} else {
					user.setName(ob[1].toString());
				}
				if(ob[2] == null){
					user.setMobile("");
				} else {
					user.setMobile(ob[2].toString());
				}
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return userList;
	}
	
	/**
	 * 获取所有没有代理商的用户集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AolUser> queryNoOrgUsers(){
		EntityManager em = entityManagerFactory.createEntityManager();
		
		//没有代理商的设备所关联的用户
		String w_useridSql = "select distinct(user_id) from userdevice ud where ud.device_id in (select d.device_id from device d left join organisedevice od on od.deviceSerial = d.deviceSerial where od.organisedeviceId is null)";
		//有代理商的设备所关联的用户
		String y_useridSql = "select distinct(user_id) from userdevice ud where ud.device_id in (select d.device_id from device d left join organisedevice od on od.deviceSerial = d.deviceSerial where od.organisedeviceId is not null)";
		
		String sql = "select u.user_id, u.name, u.mobile from user u where (u.bak4 is null or u.bak4 = '0') and u.user_id in (" + w_useridSql + ") and u.user_id not in (" + y_useridSql + ")";
		
		List<AolUser> userList = new ArrayList<AolUser>();
		List list = null;
		
		try {
			Query query = em.createNativeQuery(sql);
			list = query.getResultList();
			for(int i=0; i<list.size(); i++){
				Object[] ob = (Object[])list.get(i);
				
				AolUser user =  new AolUser();
				user.setUserId(ob[0].toString());
				if(ob[1] == null){
					user.setName("【未知】");
				} else if("".equals(ob[1].toString().replaceAll(" ", ""))){
					user.setName("【未知】");
				} else {
					user.setName(ob[1].toString());
				}
				if(ob[2] == null){
					user.setMobile("");
				} else {
					user.setMobile(ob[2].toString());
				}
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return userList;
	}
	
	
	/**
	 * 根据用户ID，将该用户转移至目标代理商
	 * @param userids 用户ID
	 * @param orgid 目标代理商ID
	 */
	public int doChangeUsers(String userids, String orgid){
		EntityManager em = null;
		Connection con = null;
		Statement st = null;
		//更新影响条数
		int resultNum = 0;
		
		try {
			em = entityManagerFactory.createEntityManager();
			SessionImplementor session =em.unwrap(SessionImplementor.class);
			con = session.connection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			//根据用户ID获取用户的设备ID
			String deviceidSQL = "select distinct(device_id) from userdevice where user_id in (" + userids + ")";
			//根据设备ID获取设备序列号
			String deviceserialSql = "select deviceSerial from device where device_id in (" + deviceidSQL +  ")";
			//根据设备序列号更新设备所属代理商
			String updataSql = "update organisedevice set organiseId = '" + orgid + "' where deviceSerial in (" + deviceserialSql + ")";
			System.out.println(updataSql);
			st = con.createStatement();
			//可以返回影响的记录数 
			resultNum = st.executeUpdate(updataSql);
			con.commit();
		} catch (Exception e) {
			resultNum = -1;
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
		
		return resultNum;
	}
	
	/**
	 * 根据用户ID集合获取用户设备号集合
	 * @param userids
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getDeviceSerialByUserids(String userids){
		EntityManager em = entityManagerFactory.createEntityManager();
		//根据用户ID获取用户的设备ID
		String deviceidSQL = "select distinct(device_id) from userdevice where user_id in (" + userids + ")";
		//根据设备ID获取设备序列号
		String deviceserialSql = "select deviceSerial from device where device_id in (" + deviceidSQL +  ")";
		List<String> deviceSerialList = new ArrayList<String>();
		try {
			Query query = em.createNativeQuery(deviceserialSql);
			List list = query.getResultList();
			for(int i=0; i<list.size(); i++){
				if(list.get(i) != null){
					deviceSerialList.add(list.get(i).toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return deviceSerialList;
		
	}
	
	/**
	 * 查询总条数
	 * @param sql
	 * @return
	 */
	public Long queryCount(String sql) {
		Long count = Long.valueOf(0);
		String countSql = "select count(u.user_id) as count from " + sql + "";
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery(countSql);
		count = (Long) query.getSingleResult();
		em.close();
		return count;
	}
	
	/**
	 * 查询总条数
	 * @param sql
	 * @return
	 */
	public Long queryCount2(String sql) {
		Long count = Long.valueOf(0);
		String countSql = "select count(_ar.id) as count from " + sql + "";
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery(countSql);
		count = (Long) query.getSingleResult();
		em.close();
		return count;
	}
	
	/**
	 * 根据部门ID获取该部门所属用户ID
	 * @param orgId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getUserIdsByOrg(String orgId){
		String deviceSql = "select d.device_id from device d right join organisedevice od on od.organiseId = '" + orgId + "' and od.deviceSerial = d.deviceSerial where d.device_id is not null";
		String sql = "select distinct(user_id) from userdevice ud where ud.device_id in (" + deviceSql + ")";
		
		EntityManager em = entityManagerFactory.createEntityManager();
		List<String> list = null;
		try {
			Query query = em.createNativeQuery(sql);
			list = (List<String>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return list;
	}

	/**
	 * 查询未分组的用户
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AolUser> queryUsersByOrgid_new() {
		EntityManager em = entityManagerFactory.createEntityManager();
		String deviceSql = "select d.device_id from device d right join organisedevice od on od.deviceSerial = d.deviceSerial where d.device_id is not null";
		String useridSql = "select distinct(user_id) from userdevice ud where ud.device_id in (" + deviceSql + ")";
		String sql = "select u.user_id, u.name from user u where (u.bak4 is null or u.bak4 = '0') and u.user_id not in (" + useridSql + ")";
		
		List<AolUser> userList = new ArrayList<AolUser>();
		List list = null;
		
		try {
			Query query = em.createNativeQuery(sql);
			list = query.getResultList();
			for(int i=0; i<list.size(); i++){
				Object[] ob = (Object[])list.get(i);
				
				AolUser user =  new AolUser();
				user.setUserId(ob[0].toString());
				user.setName(ob[1] == null ? "【未知】" : ob[1].toString());
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return userList;
	}
	
	/**
	 * 检查用户名是否唯一
	 * @param userId
	 * @param account
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Boolean checkUsers(String userId, String account){
		String sql = "";
		if(StringUtils.isNotBlank(userId)){
			sql = "select a from AolUser a where a.account='" + account + "' and a.user_id!='" + userId + "' ";
		} else {
			sql = "select a from AolUser a where a.account='" + account + "' ";
		}
		
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery(sql);
		List list = query.getResultList();
		em.close();
		if(list.size() > 0){
			return false;
		} else {
			return true;
		}
	}
	@Override
	public List<AolUser> findByName(String name) {
		
		String sql = "select u from AolUser u where u.name like '%" + name + "%'";
		EntityManager em = entityManagerFactory.createEntityManager();
		List<AolUser> list = null;
		try{
			list = (List<AolUser>) em.createQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		em.close();
		return list;
	}
	
}
