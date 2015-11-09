package com.gcs.aol.service;

import java.sql.Connection;
import java.util.List;

import com.gcs.aol.entity.Device;
import com.gcs.aol.vo.DeciveSearchVO;
import com.gcs.aol.vo.PageVO;
import com.gcs.sysmgr.service.GenericManager;
import com.gcs.sysmgr.vo.PageParameters;

public interface IDeviceManager extends GenericManager<Device> {

	/**
	 * 根据设备序列号和代理商ID查询设备信息
	 * @param deviceCode 设备序列号
	 * @param orgId 代理商ID
	 * @return
	 */
	public List<Device> getDevByCode(String deviceCode, String orgId);
	
	/**
	 * 根据序列号获取代理商
	 * @param deviceCode
	 * @return
	 */
	public String getOrgNameByDeviceCode(String deviceCode);
	
	//获取设备列表
	public PageVO queryDeviceDataList(DeciveSearchVO decivesearchvO, PageParameters pp);
	
	/**
	 * 获取设备数据列表数据
	 * @param pp
	 * @param organiseName
	 * @return
	 */
	public PageVO queryDevicesDataList(PageParameters pp,String deviceSerial,String organiseName,String usedState,String deviceType,String startTime, String organiseId);
	
	/**
	 * 根据代理商ID获取设备
	 * @param orgId
	 * @return
	 */
	public List<Device> queryDevicesByOrgId(String orgId);
	
	/**
	 * 查询没有代理商的设备
	 * @return
	 */
	public List<Device> queryNoOrgDevices();
	
	/**
	 * 根据代理商ID获取设备总数
	 */
	public String queryDevicesByOrgId(String orgId,String newAddTime);
	
	/**
	 * 根据传入参数 查找设备总数
	 * @param orgId
	 * @param deviceType  1.血压 2、血糖、3.体温 4.运动
	 * @param newAddTime
	 * @return
	 */
	public String queryDevicesByOrgId(String orgId,String deviceType,String newAddTime);
	/**
	 * 获取所有设备数
	 */
	public String fingAllDevice(String newAddTime,String deviceType);
	
	/**
	 * 根据设备ID批量更新设备所属代理商
	 * @param devids 设备ID
	 * @param orgid 代理商ID
	 * @return
	 */
	public int doChangeDevices(String devids, String orgid);
	
	/**
	 * 由于删除代理商，将代理商的所属设备转移到总公司下
	 * @param orgid 删除的代理商ID
	 * @return
	 */
	public int doChangeByDeleteDev(String orgid);
	
	/**
	 * 获取数据库连接
	 */
	public Connection getConnection();
	
}
