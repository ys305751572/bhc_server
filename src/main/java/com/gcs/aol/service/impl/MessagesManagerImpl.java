package com.gcs.aol.service.impl;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.MessagesDAO;
import com.gcs.aol.entity.Messages;
import com.gcs.aol.service.IMessagesManager;
import com.gcs.aol.vo.PageVO;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;
import com.gcs.sysmgr.vo.PageParameters;

@Service
public class MessagesManagerImpl extends GenericManagerImpl<Messages, MessagesDAO> implements IMessagesManager {

	@Autowired
	MessagesDAO messagesDAO;
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	/**
	 * 消息数据列表
	 * @param pp
	 * @param sendeeId 接收人ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageVO queryInboxDataList(PageParameters pp, String sendeeId){
		PageVO pv = new PageVO();
		EntityManager em = entityManagerFactory.createEntityManager();
		
		try {
			String innerSql = "select m from ";
			String sql = "Messages m where m.dr=0 and m.messagesOwn = 'receive' and m.messagesSendee = '" + sendeeId + "' ";
			
			List<Messages> list = null;
			
			Long count = queryCount(sql);
			
			Query query = em.createQuery(innerSql + sql + "order by m.messagesTime DESC ");
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
	 * 获取消息列表数据
	 * @param pp
	 * @param _type 消息类型（send：发送方，receive：接收方）
	 * @param _state 消息状态（send时0：草稿   1：已发送，receive时0：未读   1：已读）
	 * @param userID 登录人ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageVO queryMessageDataList(PageParameters pp, String _type, String _state, String userID){
		PageVO pv = new PageVO();
		EntityManager em = entityManagerFactory.createEntityManager();
		
		try {
			String innerSql = "select m from ";
			String sql = "Messages m where m.dr=0 and m.messagesOwn = '" + _type + "' and m.messagesState = '" + _state + "' and m.messagesSender = '" + userID + "' ";
			
			List<Messages> list = null;
			
			Long count = queryCount(sql);
			
			Query query = em.createQuery(innerSql + sql + "order by m.messagesTime DESC ");
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
	 * 删除消息（只是逻辑删除）
	 * @param msgIds
	 * @return
	 */
	public int deleteMsg(String[] msgIds){
		if(msgIds.length <= 0) {
			return -1;
		}
		EntityManager em = null;
		Connection con = null;
		Statement st = null;
		//更新影响条数
		int resultNum = 0;
		try {
			String _ids = "";
			for(int i=0; i<msgIds.length; i++){
				_ids = _ids + "'" + msgIds[i] + "',";
			}
			_ids = _ids.substring(0, _ids.length()-1);
			
			String deleteSql = "update messages set dr = 1 where messagesId in (" + _ids + ")";
			
			em = entityManagerFactory.createEntityManager();
			SessionImplementor session =em.unwrap(SessionImplementor.class);
			con = session.connection();
			st = con.createStatement();
			//可以返回影响的记录数 
			resultNum = st.executeUpdate(deleteSql);
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
	 * 标记消息为已读状态
	 * @param msgIds
	 * @return
	 */
	public int markRead(String[] msgIds){
		if(msgIds.length <= 0) {
			return -1;
		}
		EntityManager em = null;
		Connection con = null;
		Statement st = null;
		//更新影响条数
		int resultNum = 0;
		try {
			String _ids = "";
			for(int i=0; i<msgIds.length; i++){
				_ids = _ids + "'" + msgIds[i] + "',";
			}
			_ids = _ids.substring(0, _ids.length()-1);
			
			String deleteSql = "update messages set messagesState = 1 where dr=0 and messagesOwn='receive' and messagesId in (" + _ids + ")";
			
			em = entityManagerFactory.createEntityManager();
			SessionImplementor session =em.unwrap(SessionImplementor.class);
			con = session.connection();
			st = con.createStatement();
			//可以返回影响的记录数 
			resultNum = st.executeUpdate(deleteSql);
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
	 * 根据SQL查询消息结果集
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Messages> queryTopMsgList(String sql){
		List<Messages> list = new ArrayList<Messages>();
		String selectSql = "select m from " + sql + " ";
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery(selectSql);
		query.setFirstResult(0);
		query.setMaxResults(3);
		list = (List<Messages>) query.getResultList();
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
		String countSql = "select count(*) as count from " + sql + " ";
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery(countSql);
		count = (Long) query.getSingleResult();
		em.close();
		return count;
	}
}
