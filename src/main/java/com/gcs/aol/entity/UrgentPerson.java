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
@Table(name = "urgentperson")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UrgentPerson extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 2139084254061156894L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String urgentperson_id  = null;
	
	private String urgentxm = null;
	//用户唯一id信息
	private String user_id = null;
	//紧急联系人电话号码
	private String telephone  = null;
	//紧急联系人地址
	private String address  = null;
	//是否自动发送信息0.未设置自动发送 1.已设置自动发送
	private String sffs  = null;
	//用户与联系人关系
	private String gx  = null;
	//备用字段1
	private String bak1 = null;
	//备用字段2
	private String bak2 = null;
	//备用字段3
	private String bak3 = null;
	//设置联系是人紧急联系人还是监护人
	//0.紧急联系人 1.监护人
	private String bak4 = null;
	//备用字段5
	private java.sql.Timestamp bak5 = null;
	//时间戳
	private java.sql.Timestamp bak6 = null;
	//备用字段7
	private Double bak7 = null;
	//备用字段8
	private Double bak8 = null;
	
	public UrgentPerson(){
	}

	public String getUrgentperson_id() {
		return urgentperson_id;
	}

	public void setUrgentperson_id(String urgentperson_id) {
		this.urgentperson_id = urgentperson_id;
	}

	public String getUrgentxm() {
		return urgentxm;
	}

	public void setUrgentxm(String urgentxm) {
		this.urgentxm = urgentxm;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSffs() {
		return sffs;
	}

	public void setSffs(String sffs) {
		this.sffs = sffs;
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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getGx() {
		return gx;
	}

	public void setGx(String gx) {
		this.gx = gx;
	}


}
