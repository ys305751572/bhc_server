package com.gcs.aol.service;

import java.util.List;

import com.gcs.aol.entity.AolUser;
import com.gcs.aol.vo.PageVO;
import com.gcs.sysmgr.service.GenericManager;
import com.gcs.sysmgr.vo.PageParameters;

public interface IAolUserManager extends GenericManager<AolUser> {
	
	/**
	 * 根据帐号查找用户
	 * @param account 帐号
	 * @return
	 */
	public AolUser findByAccount(String account);
	
	/**
	 * 根据帐号查找用户
	 * @param account
	 * @return
	 */
	public List<AolUser> queryAccountList(String account);
	
	/**
	 * 根据手机号查找用户
	 * @param mobile 手机号
	 * @return
	 */
	public AolUser findByMobile(String mobile);
	
	/**
	 * 根据手机号查找用户
	 * @param mobile 手机号
	 * @return
	 */
	public List<AolUser> queryMobileList(String mobile);
	
	/**
	 * 根据邮箱查找用户
	 * @param email 邮箱
	 * @return
	 */
	public AolUser findByEmail(String email);
	
	/**
	 * 根据邮箱查找用户
	 * @param email 邮箱
	 * @return
	 */
	public List<AolUser> queryEmailList(String email);
	
	/**
	 * 获取用户列表数据
	 * @param pp
	 * @param usersname
	 * @param regTimeQ
	 * @param regTimeZ
	 * @return
	 */
	public PageVO queryUsersDataList(PageParameters pp, String usersname, String sexType, String mobile, String birthday, String regTimeQ, String regTimeZ, String userType,String userId);
	
	/**
	 * 根据代理商ID获取其所属用户
	 * @param orgid
	 * @return
	 */
	public List<AolUser> queryUsersByOrgid(String orgid);
	
	/**
	 * 获取所有没有代理商的用户集合
	 * @return
	 */
	public List<AolUser> queryNoOrgUsers();
	
	/**
	 * 根据代理商ID获取其所属用户
	 * @param orgid
	 * @return
	 */
	public List<AolUser> queryUsersByOrgid_new();
	
	/**
	 * 根据用户ID，将该用户转移至目标代理商
	 * @param userids 用户ID
	 * @param orgid 目标代理商ID
	 */
	public int doChangeUsers(String userids, String orgid);
	
	/**
	 * 根据用户ID集合获取用户设备号集合
	 * @param userids
	 * @return
	 */
	public List<String> getDeviceSerialByUserids(String userids);
	
	/**
	 * 查找所有用户(此方法只限总公司使用)
	 * 根据传递过来的时间
	 */
	public String findAllUser(String newAddTime);
	
	/**
	 * 根据代理商ID获取其所属用户总数
	 * @param orgid
	 * @return
	 */
	public String queryCountUsersByOrgid(String orgid,String newAddTime);
	
	/**
	 * 检查用户名是否唯一
	 * @param userId
	 * @param account
	 * @return
	 */
	public Boolean checkUsers(String userId, String account);
	
	public List<AolUser> findByName(String name);
	
}
