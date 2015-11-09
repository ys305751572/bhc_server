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
@Table(name = "device_coordinate")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DeviceCoordinate extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = -1728139270121258276L;
	
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String device_coordinate_id = null;
	//设备ID
	private String device_id = null;
	//设备记录X坐标
	private String x_coordinate = null;
	//设备记录Y坐标
	private String y_coordinate = null;
	//设备记录时间
	private String coordinate_time = null;
	//备用字段
	private String bak1 = null;
	//备用字段
	private String bak2 = null;
	//备用字段
	private String bak3 = null;
	//信息类别：0为一般信息，1为急救信息
	private String bak4 = null;
	//备用字段
	private java.sql.Timestamp  bak5 = null;
	//备用字段
	private java.sql.Timestamp  bak6 = null;
	//备用字段
	private Double bak7 = null;
	//备用字段
	private Double bak8 = null;

	public DeviceCoordinate(){
	}

	public String getDevice_coordinate_id() {
		return device_coordinate_id;
	}

	public void setDevice_coordinate_id(String device_coordinate_id) {
		this.device_coordinate_id = device_coordinate_id;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getX_coordinate() {
		return x_coordinate;
	}

	public void setX_coordinate(String x_coordinate) {
		this.x_coordinate = x_coordinate;
	}

	public String getY_coordinate() {
		return y_coordinate;
	}

	public void setY_coordinate(String y_coordinate) {
		this.y_coordinate = y_coordinate;
	}

	public String getCoordinate_time() {
		return coordinate_time;
	}

	public void setCoordinate_time(String coordinate_time) {
		this.coordinate_time = coordinate_time;
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
	
	
}
