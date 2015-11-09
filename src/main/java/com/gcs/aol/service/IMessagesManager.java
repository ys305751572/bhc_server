package com.gcs.aol.service;

import java.util.List;

import com.gcs.aol.entity.Messages;
import com.gcs.aol.vo.PageVO;
import com.gcs.sysmgr.service.GenericManager;
import com.gcs.sysmgr.vo.PageParameters;

public interface IMessagesManager extends GenericManager<Messages> {

	/**
	 * 消息数据列表
	 * @param pp
	 * @return
	 */
	public PageVO queryInboxDataList(PageParameters pp, String sendeeId);
	
	/**
	 * 获取消息列表数据
	 * @param pp
	 * @param _type 消息类型（send：发送方，receive：接收方）
	 * @param _state 消息状态（send时0：草稿   1：已发送，receive时0：未读   1：已读）
	 * @param userID 登录人ID
	 * @return
	 */
	public PageVO queryMessageDataList(PageParameters pp, String _type, String _state, String userID);
	
	/**
	 * 删除消息（只是逻辑删除）
	 * @param msgIds
	 * @return
	 */
	public int deleteMsg(String[] msgIds);
	
	/**
	 * 标记消息为已读状态
	 * @param msgIds
	 * @return
	 */
	public int markRead(String[] msgIds);
	
	/**
	 * 根据SQL查询消息结果集
	 * @param sql
	 * @return
	 */
	public List<Messages> queryTopMsgList(String sql);
	
	/**
	 * 查询总条数
	 * @param sql
	 */
	public Long queryCount(String sql);
	
}
