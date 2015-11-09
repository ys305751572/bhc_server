package com.gcs.aol.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.gcs.sysmgr.entity.AbstractEntity;

@Entity
@Table(name = "messages")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Messages extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = -1728139270121258276L;
	
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String messagesId = null;
	//标题
	private String messagesTitle = null;
	//内容
	private String messagesContent = null;
	//发送时间
	private java.sql.Timestamp messagesTime  = null;
	//消息状态 : send、receive
	private String messagesOwn = null;
	//状态
	//消息状态为send时         0：草稿   1：已发送
	//消息状态为receive时 0：未读   1：已读
	private Integer messagesState = null;
	//发送人ID
	private String messagesSender = null;
	//发送人名称
	private String messagesSenderName = null;
	//接收人
	private String messagesSendee = null;
	//接收人名称
	private String messagesSendeeName = null;
	//接收方身份
	//消息状态为send时         1:用户,2：代理商,3：代理商和用户
	//消息状态为receive时 1:用户,2：代理商
	private Integer sendeeType = null;
	//备用字段1
	private String bak1 = null;
	//备用字段2
	private String bak2 = null;
	//备用字段3
	private String bak3 = null;
	//备用字段4
	private String bak4 = null;
	//备用字段5
	private java.sql.Timestamp bak5 = null;
	//时间戳
	private java.sql.Timestamp bak6 = null;
	//备用字段7
	private Double bak7 = null;
	//备用字段8
	private Double bak8 = null;
	
	public Messages(){
	}
	
	public String getMessagesId() {
		return messagesId;
	}
	public void setMessagesId(String messagesId) {
		this.messagesId = messagesId;
	}
	public String getMessagesTitle() {
		return messagesTitle;
	}
	public void setMessagesTitle(String messagesTitle) {
		this.messagesTitle = messagesTitle;
	}
	public String getMessagesContent() {
		return messagesContent;
	}
	public void setMessagesContent(String messagesContent) {
		this.messagesContent = messagesContent;
	}
	public java.sql.Timestamp getMessagesTime() {
		return messagesTime;
	}
	public void setMessagesTime(java.sql.Timestamp messagesTime) {
		this.messagesTime = messagesTime;
	}
	public Integer getMessagesState() {
		return messagesState;
	}
	public void setMessagesState(Integer messagesState) {
		this.messagesState = messagesState;
	}
	public String getMessagesOwn() {
		return messagesOwn;
	}
	public void setMessagesOwn(String messagesOwn) {
		this.messagesOwn = messagesOwn;
	}
	public String getMessagesSender() {
		return messagesSender;
	}
	public void setMessagesSender(String messagesSender) {
		this.messagesSender = messagesSender;
	}
	public String getMessagesSendee() {
		return messagesSendee;
	}
	public void setMessagesSendee(String messagesSendee) {
		this.messagesSendee = messagesSendee;
	}
	
	public Integer getSendeeType() {
		return sendeeType;
	}

	public void setSendeeType(Integer sendeeType) {
		this.sendeeType = sendeeType;
	}

	public String getBak1() {
		return bak1;
	}
	public void setBak1(String bak1) {
		this.bak1 = bak1;
	}
	public String getBak2() {
		return bak2;
	}
	public void setBak2(String bak2) {
		this.bak2 = bak2;
	}
	public String getBak3() {
		return bak3;
	}
	public void setBak3(String bak3) {
		this.bak3 = bak3;
	}
	public String getBak4() {
		return bak4;
	}
	public void setBak4(String bak4) {
		this.bak4 = bak4;
	}
	public java.sql.Timestamp getBak5() {
		return bak5;
	}
	public void setBak5(java.sql.Timestamp bak5) {
		this.bak5 = bak5;
	}
	public java.sql.Timestamp getBak6() {
		return bak6;
	}
	public void setBak6(java.sql.Timestamp bak6) {
		this.bak6 = bak6;
	}
	public Double getBak7() {
		return bak7;
	}
	public void setBak7(Double bak7) {
		this.bak7 = bak7;
	}
	public Double getBak8() {
		return bak8;
	}
	public void setBak8(Double bak8) {
		this.bak8 = bak8;
	}

	public String getMessagesSenderName() {
		return messagesSenderName;
	}

	public void setMessagesSenderName(String messagesSenderName) {
		this.messagesSenderName = messagesSenderName;
	}

	public String getMessagesSendeeName() {
		return messagesSendeeName;
	}

	public void setMessagesSendeeName(String messagesSendeeName) {
		this.messagesSendeeName = messagesSendeeName;
	}
	
}
