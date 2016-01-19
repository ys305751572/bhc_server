package com.gcs.aol.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.MeasureDAO;
import com.gcs.aol.entity.Device;
import com.gcs.aol.entity.Measure;
import com.gcs.aol.service.IMeasureManager;
import com.gcs.aol.utils.DateUtil;
import com.gcs.aol.utils.ReportUtils;
import com.gcs.aol.vo.MeasureListVO;
import com.gcs.aol.vo.MeasureSearchVO;
import com.gcs.aol.vo.PageVO;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;
import com.gcs.sysmgr.util.ServiceLocator;
import com.gcs.sysmgr.vo.PageParameters;

@Service
public class MeasureManagerImpl extends GenericManagerImpl<Measure, MeasureDAO> implements IMeasureManager {

	@Autowired
	MeasureDAO measureDAO;
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	/**
	 * 血糖预警列表
	 */
	@SuppressWarnings("unchecked")
	public PageVO queryXtDataList(MeasureSearchVO measureSearchVO, PageParameters pp){
		List<MeasureListVO> msvoLost = null;
		PageVO pv = new PageVO();
		EntityManager em = entityManagerFactory.createEntityManager();
		
		try {
			String innerSql = "select m, u.name as measureName,u.user_id from ";
			String sql = "Measure m, AolUser u where m.user_id = u.user_id and m.measureType='2' ";
			if(StringUtils.isNotBlank(measureSearchVO.getUserName())){
				sql = sql + " and u.name like '%" + measureSearchVO.getUserName().trim() + "%' ";
			}
			if(StringUtils.isNotBlank(measureSearchVO.getSendTimeQ())){
				sql = sql + " and m.sendTime >= '" + measureSearchVO.getSendTimeQ().trim() + " 00:00:00' ";
			}
			if(StringUtils.isNotBlank(measureSearchVO.getSendTimeZ())){
				sql = sql + " and m.sendTime <= '" + measureSearchVO.getSendTimeZ().trim() + " 23:59:59' ";
			}
			if(StringUtils.isNotBlank(measureSearchVO.getXtstate())){
				if("0".equals(measureSearchVO.getXtstate())){
					sql = sql + " and m.result4 >= 3.9 and m.result4 <= 6.1 ";
				} else if("1".equals(measureSearchVO.getXtstate())){
					sql = sql + " and m.result4 < 3.9 ";
				} else if("2".equals(measureSearchVO.getXtstate())){
					sql = sql + " and m.result4 > 6.1 ";
				} else {
					
				}
			}
			if(!"00000000000000000000000000000000".equals(measureSearchVO.getOrganiseId())){
				String devIdStr = "";
				List<String> devIds = getDeviceIdsByOrg(measureSearchVO.getOrganiseId());
				if(devIds != null){
					for(int i=0; i<devIds.size(); i++){
						devIdStr = devIdStr + "'" + devIds.get(i) + "',";
					}
				}
				if(StringUtils.isNotBlank(devIdStr)){
					devIdStr = devIdStr.substring(0, devIdStr.length()-1);
				} else {
					devIdStr = "'          '";
				}
				
				sql = sql + " and m.device_id in (" + devIdStr + ")";
			}
			
			msvoLost = new ArrayList<MeasureListVO>();
			List list = null;
			
			Long count = queryCount(sql);
			
			Query query = em.createQuery(innerSql + sql);
			int firstResult = pp.getStart() * pp.getLength();
			query.setFirstResult(firstResult);
			query.setMaxResults(pp.getLength());
			list = query.getResultList();
			
			for(int i=0; i<list.size(); i++){
				Object[] objM = (Object[]) list.get(i);
				Measure ms = (Measure)objM[0];
				
				MeasureListVO msvo = new MeasureListVO();
				PropertyUtils.copyProperties(msvo, ms);
				if("2".equals(msvo.getMeasureType())&&"1".equals(msvo.getMeasureState())){
					msvo.setResult4(ReportUtils.changeValue(msvo.getResult4()));
				}
				msvo.setMeasureName(objM[1] == null ? "" : objM[1].toString());
				msvo.setUserId(objM[2] == null ? "" : objM[2].toString());
				msvoLost.add(msvo);
			}
			
			pv.setCount(count);
			pv.setStart(pp.getStart());
			pv.setLength(pp.getLength());
			pv.setList(msvoLost);
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return pv;
	}
	
	/**
	 * 血压预警列表
	 */
	@SuppressWarnings("unchecked")
	public PageVO queryXyDataList(MeasureSearchVO measureSearchVO, PageParameters pp){
		List<MeasureListVO> msvoLost = null;
		PageVO pv = new PageVO();
		EntityManager em = entityManagerFactory.createEntityManager();
		
		try {
			String innerSql = "select m, u.name as measureName,u.user_id  from ";
			String sql = "Measure m, AolUser u where m.user_id = u.user_id and m.measureType='1' ";
			if(StringUtils.isNotBlank(measureSearchVO.getUserName())){
				sql = sql + " and u.name like '%" + measureSearchVO.getUserName().trim() + "%' ";
			}
			if(StringUtils.isNotBlank(measureSearchVO.getSendTimeQ())){
				sql = sql + " and m.sendTime >= '" + measureSearchVO.getSendTimeQ().trim() + " 00:00:00' ";
			}
			if(StringUtils.isNotBlank(measureSearchVO.getSendTimeZ())){
				sql = sql + " and m.sendTime <= '" + measureSearchVO.getSendTimeZ().trim() + " 23:59:59' ";
			}
			if(StringUtils.isNotBlank(measureSearchVO.getXtstate())){
				if("0".equals(measureSearchVO.getXtstate())){//低雪压
					sql = sql + " and (m.result2 < 90 or m.result3 < 60) ";
				} else if("1".equals(measureSearchVO.getXtstate())){//正常
					sql = sql + " and ((m.result2 >= 90 and m.result2 <= 129) or (m.result3 >= 60 and m.result3 <= 84)) ";
				} else if("2".equals(measureSearchVO.getXtstate())){//正常高压
					sql = sql + " and ((m.result2 >= 130 and m.result2 <= 139) or (m.result3 >= 85 and m.result3 <= 89)) ";
				} else if("3".equals(measureSearchVO.getXtstate())) {//高血压
					sql = sql + " and (m.result2 >= 140 or m.result3 >= 90) ";
				} else if("4".equals(measureSearchVO.getXtstate())){//高压偏高
					sql = sql + " and m.result2 > 140 ";
				} else if("5".equals(measureSearchVO.getXtstate())){//高压偏低
					sql = sql + " and m.result2 < 90 ";
				} else if("6".equals(measureSearchVO.getXtstate())){//低压偏高
					sql = sql + " and m.result3 > 90 ";
				} else if("7".equals(measureSearchVO.getXtstate())){//低压偏低
					sql = sql + " and m.result3 < 60 ";
				} 
			}
			if(!"00000000000000000000000000000000".equals(measureSearchVO.getOrganiseId())){
				String devIdStr = "";
				List<String> devIds = getDeviceIdsByOrg(measureSearchVO.getOrganiseId());
				if(devIds != null){
					for(int i=0; i<devIds.size(); i++){
						devIdStr = devIdStr + "'" + devIds.get(i) + "',";
					}
				}
				if(StringUtils.isNotBlank(devIdStr)){
					devIdStr = devIdStr.substring(0, devIdStr.length()-1);
				} else {
					devIdStr = "'          '";
				}
				
				sql = sql + " and m.device_id in (" + devIdStr + ")";
			}
			
			msvoLost = new ArrayList<MeasureListVO>();
			List list = null;
			
			Long count = queryCount(sql);
			
			Query query = em.createQuery(innerSql + sql);
			int firstResult = pp.getStart() * pp.getLength();
			query.setFirstResult(firstResult);
			query.setMaxResults(pp.getLength());
			list = query.getResultList();
			for(int i=0; i<list.size(); i++){
				Object[] objM = (Object[]) list.get(i);
				Measure ms = (Measure)objM[0];
				
				MeasureListVO msvo = new MeasureListVO();
				PropertyUtils.copyProperties(msvo, ms);
				msvo.setMeasureName(objM[1] == null ? "" : objM[1].toString());
				msvo.setUserId(objM[2] == null ? "" : objM[2].toString());
				msvoLost.add(msvo);
			}
			
			pv.setCount(count);
			pv.setStart(pp.getStart());
			pv.setLength(pp.getLength());
			pv.setList(msvoLost);
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return pv;
	}
	
	/**
	 * 根据机器码查找用户数据
	 * @param measureSearchVO
	 * @param pp
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageVO queryMeasureDataList(MeasureSearchVO measureSearchVO, PageParameters pp){
		List<MeasureListVO> msvoLost = null;
		PageVO pv = new PageVO();
		EntityManager em = entityManagerFactory.createEntityManager();
		
		try {
			String innerSql = "select m, u.name as measureName,u.user_id,d.deviceSerial from ";
			String sql = "Measure m, AolUser u,Device d where m.user_id = u.user_id and m.device_id = d.device_id ";
			if(!"00000000000000000000000000000000".equals(measureSearchVO.getOrganiseId())){
				DeviceManagerImpl deviceManager = (DeviceManagerImpl) ServiceLocator.lookup(DeviceManagerImpl.class);
				List<Device> devList = deviceManager.queryDevicesByOrgId(measureSearchVO.getOrganiseId());
				String devIds ="";
				if(devList != null&&devList.size()>0){
					for(int i=0; i<devList.size(); i++){
						devIds = devIds + "'" + devList.get(i).getDevice_id() + "',";
					}
					devIds = "".equals(devIds) ? "" : devIds.substring(0, devIds.length()-1);
					sql = sql + " and d.device_id in (" + devIds + ") ";
				}
			}
			if(StringUtils.isNotBlank(measureSearchVO.getDeviceSerial())){
				sql = sql + " and d.deviceSerial like '%" + measureSearchVO.getDeviceSerial().trim().replaceAll(";", "") + "%' ";
			}
			if(StringUtils.isNotBlank(measureSearchVO.getSendTimeQ())){
				sql = sql + " and m.sendTime >= '" + measureSearchVO.getSendTimeQ().trim() + " 00:00:00' ";
			}
			if(StringUtils.isNotBlank(measureSearchVO.getSendTimeZ())){
				sql = sql + " and m.sendTime <= '" + measureSearchVO.getSendTimeZ().trim() + " 23:59:59' ";
			}
			if(StringUtils.isNotBlank(measureSearchVO.getMeasureType())){
				sql = sql + " and m.measureType = '" + measureSearchVO.getMeasureType() + "' ";
			}
			if(StringUtils.isNotBlank(measureSearchVO.getUserName())){
				sql = sql + " and u.name like '%" + measureSearchVO.getUserName() + "%' ";
			}
			if(StringUtils.isNotBlank(measureSearchVO.getXtstate())){
				if("0".equals(measureSearchVO.getXtstate())){//低雪压
				} else if("1".equals(measureSearchVO.getXtstate())){//低血糖
					sql = sql + " and m.result4 < 3.9  and m.measureType = '2'";
				} else if("2".equals(measureSearchVO.getXtstate())){//高血糖
					sql = sql + " and m.result4 > 6.2 and m.measureType = '2'" ;
				} else if("3".equals(measureSearchVO.getXtstate())){//高压偏高
					sql = sql + " and m.result2 > 140 and m.measureType = '1'";
				} else if("4".equals(measureSearchVO.getXtstate())){//高压偏低
					sql = sql + " and m.result2 < 90 and m.measureType = '1'";
				} else if("5".equals(measureSearchVO.getXtstate())){//低压偏高
					sql = sql + " and m.result3 > 90 and m.measureType = '1'";
				}else if("6".equals(measureSearchVO.getXtstate())){//低压偏低
					sql = sql + " and m.result3 < 60 and m.measureType = '1'";
				}
			}
			msvoLost = new ArrayList<MeasureListVO>();
			List list = null;
			
			Long count = queryCount(sql);
			
			Query query = em.createQuery(innerSql + sql + " order by m.sendTime desc");
			int firstResult = pp.getStart() * pp.getLength();
			query.setFirstResult(firstResult);
			query.setMaxResults(pp.getLength());
			list = query.getResultList();
			for(int i=0; i<list.size(); i++){
				Object[] objM = (Object[]) list.get(i);
				Measure ms = (Measure)objM[0];
				
				MeasureListVO msvo = new MeasureListVO();
				PropertyUtils.copyProperties(msvo, ms);
				if("2".equals(msvo.getMeasureType())&&"1".equals(msvo.getMeasureState())){
					msvo.setResult4(ReportUtils.changeValue(msvo.getResult4()));
				}
				msvo.setMeasureName(objM[1] == null ? "" : objM[1].toString());
				msvo.setUserId(objM[2] == null ? "" : objM[2].toString());
				msvo.setDeviceSerial(objM[3] == null ? "" : objM[3].toString());
				msvoLost.add(msvo);
			}
			
			pv.setCount(count);
			pv.setStart(pp.getStart());
			pv.setLength(pp.getLength());
			pv.setList(msvoLost);
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

	//根据用户名，设备ID，测量类型来找出数据
	@SuppressWarnings("unchecked")
	@Override
	public List<MeasureListVO> queryReportDate(String username,
			String diviceID, String measureType) {
		List<MeasureListVO> msvoLost = null;
		EntityManager em = entityManagerFactory.createEntityManager();
		
		try {
			String innerSql = "select m, u.name as measureName,u.user_id from ";
			String sql = "Measure m, AolUser u where m.user_id = u.user_id " +
					"and u.name = '"+username+"' and m.device_id ='"+diviceID+"' and m.measureType='"+measureType+"' order by m.sendTime";
				
			msvoLost = new ArrayList<MeasureListVO>();
			List list = null;
			Query query = em.createQuery(innerSql + sql);
			list = query.getResultList();
			for(int i=0; i<list.size(); i++){
				Object[] objM = (Object[]) list.get(i);
				Measure ms = (Measure)objM[0];
				
				MeasureListVO msvo = new MeasureListVO();
				PropertyUtils.copyProperties(msvo, ms);
				msvo.setMeasureName(objM[1] == null ? "" : objM[1].toString());
				msvo.setUserId(objM[2] == null ? "" : objM[2].toString());
				msvoLost.add(msvo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return msvoLost;
	}
	
	
	//根据用户ID，设备ID，测量类型来找出数据
	@SuppressWarnings("unchecked")
	@Override
	public List<MeasureListVO> queryReportDateId(String userid,
			String diviceID, String measureType,String sendTimeQ,String sendTimeZ) {
		List<MeasureListVO> msvoLost = null;
		EntityManager em = entityManagerFactory.createEntityManager();
		Boolean falg = false;
		try {
			String innerSql = "select m, u.name as measureName,d.deviceSerial from ";
			String sql = "Measure m, AolUser u ,Device d where  m.user_id = u.user_id  and m.device_id=d.deviceSerial " +
					"and u.user_id = '"+userid+"' and (m.user_id='"+userid+"' or (m.user_id is null or m.user_id ='')) and d.device_id ='"+diviceID+"' and m.measureType='"+measureType+"' " ;
			if(StringUtils.isNotBlank(sendTimeQ)||StringUtils.isNotBlank(sendTimeZ)){
				if(StringUtils.isNotBlank(sendTimeQ)){
					sql = sql + " and m.bak6 >= '" + DateUtil.formatStrDate(sendTimeQ) + " 00:00:00' ";
				}
				if(StringUtils.isNotBlank(sendTimeZ)){
					sql = sql + " and m.bak6 <= '" + DateUtil.formatStrDate(sendTimeZ) + " 23:59:59' ";
				}
			}else{
				falg = true;
			}
			
			sql =sql +	" order by m.sendTime desc ";
			msvoLost = new ArrayList<MeasureListVO>();
			List list = null;
			Query query = em.createQuery(innerSql + sql);
			list = query.getResultList();
			for(int i=0; i<list.size(); i++){
				Object[] objM = (Object[]) list.get(i);
				Measure ms = (Measure)objM[0];
				
				MeasureListVO msvo = new MeasureListVO();
				PropertyUtils.copyProperties(msvo, ms);
				msvo.setMeasureName(objM[1] == null ? "" : objM[1].toString());
				msvo.setUserNumber(objM[2] == null ? "" : objM[2].toString());
				if(falg == true && i>=10){
					break;
				}
				msvoLost.add(msvo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return msvoLost;
	}

	/**
	 * 血压正常值为140~90/90~60mmHg 
	 */
	@Override
	public String findHighBloodToDown(String orgId,String newTime) {
		Long count = Long.valueOf(0);
		EntityManager em = entityManagerFactory.createEntityManager();
		String innerSql = "select count(*) as count from ";
		String sql = "Measure m, AolUser u,Device d where m.user_id = u.user_id and m.device_id = d.device_id and m.measureType = '1' ";
		if(!"00000000000000000000000000000000".equals(orgId)){
			DeviceManagerImpl deviceManager = (DeviceManagerImpl) ServiceLocator.lookup(DeviceManagerImpl.class);
			List<Device> devList = deviceManager.queryDevicesByOrgId(orgId);
			String devIds ="";
			if(devList != null&&devList.size()>0){
				for(int i=0; i<devList.size(); i++){
					devIds = devIds + "'" + devList.get(i).getDevice_id() + "',";
				}
				devIds = "".equals(devIds) ? "" : devIds.substring(0, devIds.length()-1);
				sql = sql + " and d.device_id in (" + devIds + ") ";
			}
			
		}
		sql +=  " and m.sendTime >= '" + newTime + " 00:00:00' ";
		sql +=  "and m.result2 < 90";
		Query query = em.createQuery(innerSql+sql);
		count = (Long) query.getSingleResult();
		em.close();
		return count.toString();
	}

	@Override
	public String findHighBloodToUp(String orgId,String newTime) {
		Long count = Long.valueOf(0);
		EntityManager em = entityManagerFactory.createEntityManager();
		String innerSql = "select count(*) as count from ";
		String sql = "Measure m, AolUser u,Device d where m.user_id = u.user_id and m.device_id = d.device_id and m.measureType = '1' ";
		if(!"00000000000000000000000000000000".equals(orgId)){
			DeviceManagerImpl deviceManager = (DeviceManagerImpl) ServiceLocator.lookup(DeviceManagerImpl.class);
			List<Device> devList = deviceManager.queryDevicesByOrgId(orgId);
			String devIds ="";
			if(devList != null&&devList.size()>0){
				for(int i=0; i<devList.size(); i++){
					devIds = devIds + "'" + devList.get(i).getDevice_id() + "',";
				}
				devIds = "".equals(devIds) ? "" : devIds.substring(0, devIds.length()-1);
				sql = sql + " and d.device_id in (" + devIds + ") ";
			}
			
		}
		sql +=  " and m.sendTime >= '" + newTime + " 00:00:00' ";
		sql +=   "and m.result2 > 140";
		Query query = em.createQuery(innerSql+sql);
		count = (Long) query.getSingleResult();
		em.close();
		return count.toString();
	}

	@Override
	public String findLowBloodToDown(String orgId,String newTime) {
		Long count = Long.valueOf(0);
		EntityManager em = entityManagerFactory.createEntityManager();
		String innerSql = "select count(*) as count from ";
		String sql = "Measure m, AolUser u,Device d where m.user_id = u.user_id and m.device_id = d.device_id and m.measureType = '1' ";
		if(!"00000000000000000000000000000000".equals(orgId)){
			DeviceManagerImpl deviceManager = (DeviceManagerImpl) ServiceLocator.lookup(DeviceManagerImpl.class);
			List<Device> devList = deviceManager.queryDevicesByOrgId(orgId);
			String devIds ="";
			if(devList != null&&devList.size()>0){
				for(int i=0; i<devList.size(); i++){
					devIds = devIds + "'" + devList.get(i).getDevice_id() + "',";
				}
				devIds = "".equals(devIds) ? "" : devIds.substring(0, devIds.length()-1);
				sql = sql + " and d.device_id in (" + devIds + ") ";
			}
			
		}
		sql +=  " and m.sendTime >= '" + newTime + " 00:00:00' ";
		sql += "and m.result3 < 60";
		Query query = em.createQuery(innerSql+sql);
		count = (Long) query.getSingleResult();
		em.close();
		return count.toString();
	}

	@Override
	public String findLowBloodToUp(String orgId,String newTime) {
		Long count = Long.valueOf(0);
		EntityManager em = entityManagerFactory.createEntityManager();
		String innerSql = "select count(*) as count from ";
		String sql = "Measure m, AolUser u,Device d where m.user_id = u.user_id and m.device_id = d.device_id and m.measureType = '1' ";
		if(!"00000000000000000000000000000000".equals(orgId)){
			DeviceManagerImpl deviceManager = (DeviceManagerImpl) ServiceLocator.lookup(DeviceManagerImpl.class);
			List<Device> devList = deviceManager.queryDevicesByOrgId(orgId);
			String devIds ="";
			if(devList != null&&devList.size()>0){
				for(int i=0; i<devList.size(); i++){
					devIds = devIds + "'" + devList.get(i).getDevice_id() + "',";
				}
				devIds = "".equals(devIds) ? "" : devIds.substring(0, devIds.length()-1);
				sql = sql + " and d.device_id in (" + devIds + ") ";
			}
			
		}
		sql +=  " and m.sendTime >= '" + newTime + " 00:00:00' ";
		sql +=  "and m.result3 > 90";
		Query query = em.createQuery(innerSql+sql);
		count = (Long) query.getSingleResult();
		em.close();
		return count.toString();
	}

	/**
	 *  空腹血糖：3.9--6.2mmoL/L
	 */
	@Override
	public String findBloodSugarToDown(String orgId,String newTime) {
		Long count = Long.valueOf(0);
		EntityManager em = entityManagerFactory.createEntityManager();
		String innerSql = "select count(*) as count from ";
		String sql = "Measure m, AolUser u,Device d where m.user_id = u.user_id and m.device_id = d.device_id and m.measureType = '2' ";
		if(!"00000000000000000000000000000000".equals(orgId)){
			DeviceManagerImpl deviceManager = (DeviceManagerImpl) ServiceLocator.lookup(DeviceManagerImpl.class);
			List<Device> devList = deviceManager.queryDevicesByOrgId(orgId);
			String devIds ="";
			if(devList != null&&devList.size()>0){
				for(int i=0; i<devList.size(); i++){
					devIds = devIds + "'" + devList.get(i).getDevice_id() + "',";
				}
				devIds = "".equals(devIds) ? "" : devIds.substring(0, devIds.length()-1);
				sql = sql + " and d.device_id in (" + devIds + ") ";
			}
			
		}
		sql +=  " and m.sendTime >= '" + newTime + " 00:00:00' ";
		sql += 	" and m.result4 < 3.9";
		Query query = em.createQuery(innerSql+sql);
		count = (Long) query.getSingleResult();
		em.close();
		return count.toString();
	}

	@Override
	public String findBloodSugarToUp(String orgId,String newTime) {
		Long count = Long.valueOf(0);
		EntityManager em = entityManagerFactory.createEntityManager();
		String innerSql = "select count(*) as count from ";
		String sql = "Measure m, AolUser u,Device d where m.user_id = u.user_id and m.device_id = d.device_id and m.measureType = '2' ";
		if(!"00000000000000000000000000000000".equals(orgId)){
			DeviceManagerImpl deviceManager = (DeviceManagerImpl) ServiceLocator.lookup(DeviceManagerImpl.class);
			List<Device> devList = deviceManager.queryDevicesByOrgId(orgId);
			String devIds ="";
			if(devList != null&&devList.size()>0){
				for(int i=0; i<devList.size(); i++){
					devIds = devIds + "'" + devList.get(i).getDevice_id() + "',";
				}
				devIds = "".equals(devIds) ? "" : devIds.substring(0, devIds.length()-1);
				sql = sql + " and d.device_id in (" + devIds + ") ";
			}
			
		}
		sql +=  " and m.sendTime >= '" + newTime + " 00:00:00' ";
		sql +=   "and m.result4 > 6.2";
		Query query = em.createQuery(innerSql+sql);
		count = (Long) query.getSingleResult();
		em.close();
		return count.toString();
	}
	
	/**
	 * 根据部门ID获取该部门所属设备ID
	 * @param orgId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getDeviceIdsByOrg(String orgId){
		String deviceSql = "select d.device_id from device d right join organisedevice od on od.organiseId = '" + orgId + "' and od.deviceSerial = d.deviceSerial where d.device_id is not null";
		
		EntityManager em = entityManagerFactory.createEntityManager();
		List<String> list = null;
		try {
			Query query = em.createNativeQuery(deviceSql);
			list = (List<String>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return list;
	}
	
}
