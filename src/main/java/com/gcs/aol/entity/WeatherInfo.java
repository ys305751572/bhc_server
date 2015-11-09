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
@Table(name = "weatherinfo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WeatherInfo extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 2836018291812906986L;
	//主键
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String weatherinfoId = null;
	//城市编码
	private String cityCode = null;
	//天气时间（格式：yyyy-mm-dd）
	private String dateTime = null;
	//天气报文
	private String weatherInfo = null;
	//时间状态（0：上午，1：下午，2：晚上）
	private Integer dataState  = null;
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
	//备用字段8
	private Double bak8 = null;
	public String getWeatherinfoId() {
		return weatherinfoId;
	}
	public void setWeatherinfoId(String weatherinfoId) {
		this.weatherinfoId = weatherinfoId;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getWeatherInfo() {
		return weatherInfo;
	}
	public void setWeatherInfo(String weatherInfo) {
		this.weatherInfo = weatherInfo;
	}
	public Integer getDataState() {
		return dataState;
	}
	public void setDataState(Integer dataState) {
		this.dataState = dataState;
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
}
