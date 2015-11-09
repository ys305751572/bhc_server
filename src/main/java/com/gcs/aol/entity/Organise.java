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
@Table(name = "organise")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Organise extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = -6431202962742778447L;
	//主键
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String organiseId = null;
	//编码
	private String organiseCode = null;
	//名称
	private String organiseName  = null;
	//简称
	private String organiseShortname = null;
	//联系电话
	private String telephone = null;
	//传真
	private String fax = null;
	//公司地址
	private String organiseAddress = null;
	//门店地址
	private String storeAddress = null;
	//公司网站
	private String website = null;
	//公司邮箱
	private String mailbox = null;
	//公司邮编
	private String zipcode = null;
	//父节点公司ID
	private String parentId = null;
	//创建时间
	private java.sql.Timestamp createTime = null;
	//级别
	private Integer levelType = null;
	//附件ID
	private String attachId = null;
	//登录帐号ID
	private String userId = null;
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
	//备用字段6
	private java.sql.Timestamp bak6 = null;
	//备用字段7
	private Double bak7 = null;         	
	//备用字段9
	private Double bak8 = null;         

	public Organise(){
	}

	public String getOrganiseId() {
		return organiseId;
	}

	public void setOrganiseId(String organiseId) {
		this.organiseId = organiseId;
	}

	public String getOrganiseCode() {
		return organiseCode;
	}

	public void setOrganiseCode(String organiseCode) {
		this.organiseCode = organiseCode;
	}

	public String getOrganiseName() {
		return organiseName;
	}

	public void setOrganiseName(String organiseName) {
		this.organiseName = organiseName;
	}

	public String getOrganiseShortname() {
		return organiseShortname;
	}

	public void setOrganiseShortname(String organiseShortname) {
		this.organiseShortname = organiseShortname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getOrganiseAddress() {
		return organiseAddress;
	}

	public void setOrganiseAddress(String organiseAddress) {
		this.organiseAddress = organiseAddress;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getMailbox() {
		return mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getLevelType() {
		return levelType;
	}

	public void setLevelType(Integer levelType) {
		this.levelType = levelType;
	}

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public java.sql.Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
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

}
