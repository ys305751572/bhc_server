package com.gcs.aol.service.impl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.DeviceDAO;
import com.gcs.aol.entity.AolUser;
import com.gcs.aol.entity.Device;
import com.gcs.aol.service.IAolUserManager;
import com.gcs.aol.service.IDeviceManager;
import com.gcs.aol.service.IUserDeviceManager;
import com.gcs.aol.vo.DeciveListVO;
import com.gcs.aol.vo.DeciveSearchVO;
import com.gcs.aol.vo.DeviceListVO;
import com.gcs.aol.vo.PageVO;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;
import com.gcs.sysmgr.vo.PageParameters;

@Service
public class DeviceManagerImpl extends GenericManagerImpl<Device, DeviceDAO> implements IDeviceManager {
	@Autowired
	DeviceDAO deviceDAO;
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Autowired
	private IAolUserManager userManager;
	
	/**
	 * 根据设备序列号和代理商ID查询设备信息
	 * @param deviceCode
	 * @param orgId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Device> getDevByCode(String deviceCode, String orgId){
		List<Device> list = null;
		String sql = "select d from Device d, OrganiseDevice od where d.deviceSerial  like '%" + deviceCode.replaceAll(";", "") + "%' and d.deviceSerial=od.deviceSerial  ";
		//总公司可以查所有设备
		if(!"00000000000000000000000000000000".equals(orgId)){
			sql = sql + "and od.organiseId='" + orgId + "' ";
		}else{
			sql = "select d from Device d where d.deviceSerial  like '%" + deviceCode.replaceAll(";", "") + "%'";
		}
		sql += "order by d.bak6";
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery(sql);
		list = query.getResultList();
		em.close();
		if(list != null & list.size() > 0){
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * 根据序列号获取代理商
	 * @param deviceCode
	 * @return
	 */
	public String getOrgNameByDeviceCode(String deviceCode){
		String sql = "select o.organiseName from organise o, organisedevice od where o.organiseId = od.organiseId and od.deviceSerial = '" + deviceCode + "'";
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createNativeQuery(sql);
		Object obj = query.getSingleResult();
		em.close();
		return obj==null ? "" : obj.toString();
	}
	
	/**
	 * 获取设备列表
	 * @param measureSearchVO
	 * @param pp
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageVO queryDeviceDataList(DeciveSearchVO decivesearchvO, PageParameters pp){
		List<DeciveListVO> decivelist = null;
		PageVO pv = new PageVO();
		EntityManager em = entityManagerFactory.createEntityManager();
		
		String innerSql = "select d, u.name as measureName,u.user_id,d.deviceSerial from ";
		String sql = "AolUser u ,Device d WHERE 1 =1 and u.user_id = d.user_id";
		if(StringUtils.isNotBlank(decivesearchvO.getUserName())){
			sql = sql + " and u.name like '%" + decivesearchvO.getUserName().trim() + "%' ";
		}if(StringUtils.isNotBlank(decivesearchvO.getDeviceType())){
			sql = sql + " and d.deviceType = '" + decivesearchvO.getDeviceType().trim() + "' ";
		}if(StringUtils.isNotBlank(decivesearchvO.getUserId())){
			sql = sql + " and u.user_id = '" + decivesearchvO.getUserId().trim() + "' ";
		}
		sql += " order by u.name desc";//按照用户名排序
		try {
			decivelist = new ArrayList<DeciveListVO>();
			List list = null;
			
			Long count = queryCount(sql);
			Query query = em.createQuery(innerSql + sql);
			//int firstResult = pp.getStart() * pp.getLength();
			//query.setFirstResult(firstResult);
			//query.setMaxResults(pp.getLength());
			list = query.getResultList();
			
			for(int i=0; i<list.size(); i++){
				Object[] objM = (Object[]) list.get(i);
				Device dc = (Device)objM[0];
				
				DeciveListVO dcvo = new DeciveListVO();
				PropertyUtils.copyProperties(dcvo, dc);
				dcvo.setUserName(objM[1] == null ? "" : objM[1].toString());
				dcvo.setUserId(objM[2] == null ? "" : objM[2].toString());
				dcvo.setUserNumber(objM[3] == null ? "" : objM[3].toString());
				decivelist.add(dcvo);
			}
			pv.setCount(count);
			if(pp != null){
				pv.setStart(pp.getStart());
				pv.setLength(pp.getLength());
			}
			
			pv.setList(decivelist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return pv;
	}
	
	/**
	 * 获取设备列表(代理商)
	 * @param measureSearchVO
	 * @param pp
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageVO queryDeviceDataList(DeciveSearchVO decivesearchvO,String organiseId, PageParameters pp){
		List<DeciveListVO> decivelist = null;
		PageVO pv = new PageVO();
		EntityManager em = entityManagerFactory.createEntityManager();
		
		String innerSql = "select d, u.name as measureName,u.user_id,ud.bak1 from ";
		String sql = "AolUser u, Device d, UserDevice ud,OrganiseDevice o where u.user_id = ud.user_id and ud.device_id = d.device_id and o.deviceSerial = d.deviceSerial";
		if(StringUtils.isNotBlank(organiseId)){
			sql = sql + " and o.organiseId  = '" + organiseId + "' ";
		}
		if(StringUtils.isNotBlank(decivesearchvO.getUserName())){
			sql = sql + " and u.name like '%" + decivesearchvO.getUserName().trim() + "%' ";
		}if(StringUtils.isNotBlank(decivesearchvO.getDeviceType())){
			sql = sql + " and d.deviceType = '" + decivesearchvO.getDeviceType().trim() + "' ";
		}if(StringUtils.isNotBlank(decivesearchvO.getUserId())){
			sql = sql + " and u.user_id = '" + decivesearchvO.getUserId().trim() + "' ";
		}
		sql += " order by u.name desc";//按照用户名排序
		try {
			decivelist = new ArrayList<DeciveListVO>();
			List list = null;
			
			Long count = queryCount(sql);
			Query query = em.createQuery(innerSql + sql);
			//int firstResult = pp.getStart() * pp.getLength();
			//query.setFirstResult(firstResult);
			//query.setMaxResults(pp.getLength());
			list = query.getResultList();
			
			for(int i=0; i<list.size(); i++){
				Object[] objM = (Object[]) list.get(i);
				Device dc = (Device)objM[0];
				
				DeciveListVO dcvo = new DeciveListVO();
				PropertyUtils.copyProperties(dcvo, dc);
				dcvo.setUserName(objM[1] == null ? "" : objM[1].toString());
				dcvo.setUserId(objM[2] == null ? "" : objM[2].toString());
				dcvo.setUserNumber(objM[3] == null ? "" : objM[3].toString());
				decivelist.add(dcvo);
			}
			pv.setCount(count);
			if(pp!= null){
				pv.setStart(pp.getStart());
				pv.setLength(pp.getLength());
			}
			
//			List<AolUser> aolUserList = userManager.queryAll("user_id", false);
			pv.setList(decivelist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return pv;
	}
	
	/**
	 * 获取设备列表(代理商)
	 * @param measureSearchVO
	 * @param pp
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageVO queryUserDeviceDataList(DeciveSearchVO decivesearchvO,String userId, PageParameters pp){
		List<DeciveListVO> decivelist = null;
		PageVO pv = new PageVO();
		EntityManager em = entityManagerFactory.createEntityManager();
		
		String innerSql = "select d, u.name as measureName,u.user_id from ";
		String sql = "AolUser u, Device d, UserDevice ud where u.user_id = ud.user_id and ud.device_id = d.device_id ";
		if(StringUtils.isNotBlank(decivesearchvO.getUserName())){
			sql = sql + " and u.name like '%" + decivesearchvO.getUserName().trim() + "%' ";
		}if(StringUtils.isNotBlank(decivesearchvO.getDeviceType())){
			sql = sql + " and d.deviceType = '" + decivesearchvO.getDeviceType().trim() + "' ";
		}if(StringUtils.isNotBlank(decivesearchvO.getUserId())){
			sql = sql + " and u.user_id = '" + decivesearchvO.getUserId().trim() + "' ";
		}
		try {
			decivelist = new ArrayList<DeciveListVO>();
			List list = null;
			
			Long count = queryCount(sql);
			Query query = em.createQuery(innerSql + sql);
			//int firstResult = pp.getStart() * pp.getLength();
			//query.setFirstResult(firstResult);
			//query.setMaxResults(pp.getLength());
			list = query.getResultList();
			
			for(int i=0; i<list.size(); i++){
				Object[] objM = (Object[]) list.get(i);
				Device dc = (Device)objM[0];
				
				DeciveListVO dcvo = new DeciveListVO();
				PropertyUtils.copyProperties(dcvo, dc);
				dcvo.setUserName(objM[1] == null ? "" : objM[1].toString());
				dcvo.setUserId(objM[2] == null ? "" : objM[2].toString());
				decivelist.add(dcvo);
			}
			pv.setCount(count);
			pv.setStart(pp.getStart());
			pv.setLength(pp.getLength());
			pv.setList(decivelist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return pv;
	}
	
	/**
	 * 根据用户ID以及设备类型获取设备ID
	 * @param userid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryDeciveID(String userid,String deciveType){

		String innerSql = "select d.device_id from ";
		String sql = "AolUser u, Device d, UserDevice ud where u.user_id = ud.user_id and ud.device_id = d.device_id ";
		sql = sql + " and u.user_id = '" + userid + "' ";
		sql = sql + " and d.deviceType = '" + deciveType + "' ";
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery(innerSql + sql);
		List list = new ArrayList();
		list = query.getResultList();
		em.close();
		if(list.size()>0){
			Object objM =  list.get(0);
			return objM.toString();
		}
		return null;
		
	}
	
	/**
	 * 获取呼救信息列表
	 * @param measureSearchVO
	 * @param pp
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageVO queryHjDataList(DeciveSearchVO decivesearchvO, PageParameters pp){
		List<DeciveListVO> decivelist = null;
		PageVO pv = new PageVO();
		EntityManager em = entityManagerFactory.createEntityManager();
		
		String innerSql = "select d, u.name as measureName from ";
		String sql = "Device d, AolUser u,DeviceCoordinate dc where d.user_id = u.user_id and dc.device_id = d.device_id and dc.bak4 = '1' ";
		if(StringUtils.isNotBlank(decivesearchvO.getUserName())){
			sql = sql + " and u.name like '%" + decivesearchvO.getUserName().trim() + "%' ";
		}if(StringUtils.isNotBlank(decivesearchvO.getDeviceType())){
			sql = sql + " and d.deviceType = '" + decivesearchvO.getDeviceType().trim() + "' ";
		}if(StringUtils.isNotBlank(decivesearchvO.getUserId())){
			sql = sql + " and u.user_id = '" + decivesearchvO.getUserId().trim() + "' ";
		}
		try {
			decivelist = new ArrayList<DeciveListVO>();
			List list = null;
			
			Long count = queryCount(sql);
			Query query = em.createQuery(innerSql + sql);
			//int firstResult = pp.getStart() * pp.getLength();
			//query.setFirstResult(firstResult);
			//query.setMaxResults(pp.getLength());
			list = query.getResultList();
			
			for(int i=0; i<list.size(); i++){
				Object[] objM = (Object[]) list.get(i);
				Device dc = (Device)objM[0];
				
				DeciveListVO dcvo = new DeciveListVO();
				PropertyUtils.copyProperties(dcvo, dc);
				dcvo.setUserName(objM[1] == null ? "" : objM[1].toString());
				decivelist.add(dcvo);
			}
			pv.setCount(count);
			pv.setStart(pp.getStart());
			pv.setLength(pp.getLength());
			pv.setList(decivelist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return pv;
	}
	
	/**
	 * 获取设备数据列表数据
	 * @param pp
	 * @param deviceSerial
	 * @param organiseName
	 * @param usedState
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageVO queryDevicesDataList(PageParameters pp,String deviceSerial,String organiseName,String usedState,String deviceType,String startTime, String organiseId){
		List<DeviceListVO> deviceListVO = null;
		
		PageVO pv = new PageVO();
		EntityManager em = entityManagerFactory.createEntityManager();
		
		try {
			//获取设备与代理商的关系SQL
			String selectOrgSql = "select od.deviceSerial, o.organiseName, o.organiseId from organisedevice od, organise o where od.organiseId = o.organiseId";
			//添加查询条件：代理商
			if(StringUtils.isNotBlank(organiseName)){
				selectOrgSql = selectOrgSql + " and o.organiseName like '%" + organiseName + "%' ";
			}
			
			//获取设备信息和代理商名称
			String selectDevOrgSQL = "select d.*, dl.organiseName from device d left join (" + selectOrgSql + ") dl on d.deviceSerial = dl.deviceSerial where d.deviceSerial is not null ";
			//添加查询条件：设备序列号
			if(StringUtils.isNotBlank(deviceSerial)){
				selectDevOrgSQL = selectDevOrgSQL + " and d.deviceSerial like '%" + deviceSerial + "%' ";
			}
			if(StringUtils.isNotBlank(deviceType)){
				selectDevOrgSQL = selectDevOrgSQL + " and d.deviceType = '" + deviceType + "' ";
			}
			if(StringUtils.isNotBlank(startTime)){
				selectDevOrgSQL = selectDevOrgSQL + " and d.bak6 > '"+startTime+"' ";
			}
			if(StringUtils.isNotBlank(organiseId)){
				selectDevOrgSQL = selectDevOrgSQL + " and dl.organiseId = '" + organiseId + "' ";
			}
			
			//获取总数SQL
			String countSQL = "select count(*) from (" + selectDevOrgSQL + ") ddl";
			//获取设备信息、代理商名称、使用状态的SQL
			String selectSQL = "select ddl.*, ud.device_id as devid from (" + selectDevOrgSQL + ") ddl left join (select distinct(device_id) from measure where user_id is not null and device_id is not null) ud on ddl.device_id = ud.device_id";
			//添加查询条件：使用状态
			if(StringUtils.isNotBlank(usedState)){
				if("0".equals(usedState)){
					//未被使用
					selectSQL = selectSQL + " where ud.device_id is null ";
				} else if("1".equals(usedState)) {
					//已被使用
					selectSQL = selectSQL + " where ud.device_id is not null ";
				}
			}
			
			deviceListVO = new ArrayList<DeviceListVO>();
			List list = null;
			
			//获取数据总数
			Long count = queryCountBySql(countSQL);
			//获取数据
			Query query = em.createNativeQuery(selectSQL);
			int firstResult = pp.getStart() * pp.getLength();
			query.setFirstResult(firstResult);
			query.setMaxResults(pp.getLength());
			list = query.getResultList();
			for(int i=0; i<list.size(); i++){
				Object[] ob = (Object[])list.get(i);
				
				DeviceListVO devVo = new DeviceListVO();
				devVo.setDevice_id(ob[0].toString());
				devVo.setUser_id(ob[1] == null ? "" : ob[1].toString());
				devVo.setDeviceProYear(ob[2] == null ? "" : ob[2].toString());
				devVo.setDeviceProMonth(ob[3] == null ? "" : ob[3].toString());
				devVo.setDeviceType(ob[4] == null ? "" : ob[4].toString());
				devVo.setDeviceSerial(ob[5] == null ? "" : ob[5].toString());
				devVo.setVersion(ob[6] == null ? "" : ob[6].toString());
				devVo.setBak1(ob[7] == null ? "" : ob[7].toString());
				devVo.setBak2(ob[8] == null ? "" : ob[8].toString());
				devVo.setBak3(ob[9] == null ? "" : ob[9].toString());
				devVo.setBak4(ob[10] == null ? "" : ob[10].toString());
				//devVo.setBak5(ob[11] == null ? "" : ob[11].toString());
				//devVo.setBak6(ob[12] == null ? "" : ob[12].toString());
				//devVo.setBak7(ob[13] == null ? "" : ob[13].toString());
				//devVo.setBak8(ob[14] == null ? "" : ob[14].toString());
				/**15是dr,16是ts*/
				devVo.setOrganiseName(ob[17] == null ? "" : ob[17].toString());
				if(ob[18] == null){
					devVo.setUsedState("0");
				} else {
					devVo.setUsedState("1");
				}
				
				deviceListVO.add(devVo);
			}
			
			pv.setCount(count);
			pv.setStart(pp.getStart());
			pv.setLength(pp.getLength());
			pv.setList(deviceListVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return pv;
	}
	
	/**
	 * 根据代理商ID获取设备
	 * @param orgId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Device> queryDevicesByOrgId(String orgId){
		EntityManager em = entityManagerFactory.createEntityManager();
		String sql = "SELECT d FROM Organise o, OrganiseDevice od, Device d "
			       + "where o.organiseId='" + orgId + "' and od.organiseId=o.organiseId and d.deviceSerial=od.deviceSerial "
			       + "order by d.deviceType ";
		
		List<Device> devList = new ArrayList<Device>();
		try {
			Query query = em.createQuery(sql);
			devList = (List<Device>)query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return devList;
	}
	
	/**
	 * 查询没有代理商的设备
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Device> queryNoOrgDevices(){
		EntityManager em = entityManagerFactory.createEntityManager();
		String sql = "select d from Device d where d.deviceSerial not in (select od.deviceSerial from OrganiseDevice od) ";
		
		List<Device> devList = new ArrayList<Device>();
		try {
			Query query = em.createQuery(sql);
			devList = (List<Device>)query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return devList;
		
	}
	
	/**
	 * 根据代理商ID获取设备总数
	 */
	public String queryDevicesByOrgId(String orgId,String newAddTime){
		Long count = Long.valueOf(0);
		EntityManager em = entityManagerFactory.createEntityManager();
		String sql = "SELECT count(*) as count FROM Organise o, OrganiseDevice od, Device d "
			       + "where o.organiseId='" + orgId + "' and od.organiseId=o.organiseId and d.deviceSerial=od.deviceSerial and od.bindTime > '"+newAddTime+"'"
			       + "order by d.deviceType ";
		
		Query query = em.createQuery(sql);
		count = (Long) query.getSingleResult();
		em.close();
		return count.toString();
	}
	
	/**
	 * 获取所有设备数
	 * deviceType 为必填项，否则SQL不能正确拼接
	 */
	public String fingAllDevice(String newAddTime,String deviceType){
		Long count = Long.valueOf(0);
		EntityManager em = entityManagerFactory.createEntityManager();
		String sql = "SELECT count(*) as count FROM Device where deviceType = '"+deviceType+"' ";
		if(StringUtils.isNotBlank(newAddTime)){
			sql += " and  bak6> '"+newAddTime+"' ";
		}
		Query query = em.createQuery(sql);
		count = (Long) query.getSingleResult();
		em.close();
		return count.toString();
	}
	
	/**
	 * 根据传入参数 查找设备总数
	 * @param orgId
	 * @param deviceType  1.血压 2、血糖、3.体温 4.运动
	 * @param newAddTime
	 * @return
	 */
	@Override
	public String queryDevicesByOrgId(String orgId, String deviceType,
			String newAddTime) {
		Long count = Long.valueOf(0);
		EntityManager em = entityManagerFactory.createEntityManager();
		String sql = "select count(*) as count from device d left join (select od.deviceSerial, o.organiseName, o.organiseId from organisedevice od, organise o where od.organiseId = o.organiseId) dl on d.deviceSerial = dl.deviceSerial "
			       + "where d.deviceSerial is not null ";
	    if(StringUtils.isNotBlank(newAddTime)){
	    	sql += " and d.bak6 > '"+newAddTime+"' ";
	    }
	    if(StringUtils.isNotBlank(deviceType)){
	    	sql += " and d.deviceType = '"+deviceType+"' ";
	    }
		if(StringUtils.isNotBlank(orgId)){
	    	sql += "and dl.organiseId = '" + orgId + "'";
	    }
		Query query = em.createNativeQuery(sql);
		BigInteger bi = new BigInteger("0");
		bi = (BigInteger) query.getSingleResult();
		count = bi.longValue();
		em.close();
		return count.toString();
	}
	
	/**
	 * 根据设备ID批量更新设备所属代理商
	 * @param devids 设备ID
	 * @param orgid 代理商ID
	 * @return
	 */
	public int doChangeDevices(String devids, String orgid){
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
			//根据设备ID获取设备序列号
			String deviceserialSql = "select deviceSerial from device where device_id in (" + devids +  ")";
			//根据设备序列号更新设备所属代理商
			String updataSql = "update organisedevice set organiseId = '" + orgid + "' where deviceSerial in (" + deviceserialSql + ")";
			
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
	 * 由于删除代理商，将代理商的所属设备转移到总公司下
	 * @param orgid 删除的代理商ID
	 * @return
	 */
	public int doChangeByDeleteDev(String orgid){
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
			//根据设备序列号更新设备所属代理商
			String updataSql = "delete from organisedevice where organiseId = '" + orgid + "'";
			
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
	 * 查询总条数
	 * @param sql
	 * @return
	 */
	public Long queryCountBySql(String sql) {
		Long count = Long.valueOf(0);
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createNativeQuery(sql);
		
		BigInteger bi = new BigInteger("0");
		bi = (BigInteger) query.getSingleResult();
		count = bi.longValue();
		
		em.close();
		return count;
	}
	
	/**
	 * 获取数据库连接
	 */
	public Connection getConnection(){
		Connection con = null;
		try {
			EntityManager em = entityManagerFactory.createEntityManager();
			SessionImplementor session =em.unwrap(SessionImplementor.class);
			con = session.connection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
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
