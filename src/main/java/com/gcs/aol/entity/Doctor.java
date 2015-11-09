package com.gcs.aol.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

// 医师专家PO
@Entity
@Table(name = "t_doctor")
public class Doctor {

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DETAIL")
	private String detail;

	@Column(name = "DEPART")
	private String depart;

	@Column(name = "DOMAIN")
	private String domain;

	@Column(name = "MOBILE")
	private String mobile;

	@Column(name = "TEL")
	private String tel;

	@Column(name = "HEAD")
	private String head;

	@Column(name = "GENDER")
	private Byte gender; // 性别 1:男 2:女

	@Column(name = "LEVEL")
	private Integer level;

	@Column(name = "BIRTHDAY")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;

	@Column(name = "ONLINE")
	private String online; // 在诊时间

	@Column(name = "TYPE")
	private Byte type; // 类型 0:专家 1:家庭医生

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public Byte getGender() {
		return gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}
}
