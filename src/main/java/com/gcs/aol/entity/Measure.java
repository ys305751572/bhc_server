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
@Table(name = "measure")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Measure extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = -5540056395815610771L;
	
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String measure_id = null;
	//上传用户id
	private String user_id = null;
	//设备id
	private String device_id = null;
	//测量状态(1.设备记录  2.手动记录)
	private String measureState = null;
	//测量类型(测量类型只有：1、血压 2、血糖
    //手动记录有4种：1、血压 2、血糖 3、心率 4、血脂)
	private String measureType = null;
	
	//测量结果1
	private String result1 = null;
	//测量结果2  测量类型不同，此数值含义不同。类型为1时，表示是血压收缩压（高压）
	private String result2 = null;
	//测量结果3 测量类型不同，此数值含义不同。类型为1时，表示是血压舒张压（低压）
	private String result3 = null;
	//测量结果4 测量类型不同，此数值含义不同。类型为1时，表示是心率;类型为2时，表示是血糖数值
	private String result4 = null;
	//测量结果5 测量数值（如果统计类型是日平均，它返回的是每天的平均数值；如果统计类型是周平均，它返回的是每周的平均数值）
	private String result5 = null;
	//发送报文信息
	private String sendMsg = null;
	//发送时间
	private String sendTime = null;

	//测量结果是否显示 0、不显示，1、显示
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
	
	public Measure(){
	}
	
	public String getMeasureId() {
		return measure_id;
	}
	public void setMeasureId(String measureId) {
		this.measure_id = measureId;
	}
	public String getUserId() {
		return user_id;
	}
	public void setUserId(String userId) {
		this.user_id = userId;
	}
	public String getDeviceId() {
		return device_id;
	}
	public void setDeviceId(String deviceId) {
		this.device_id = deviceId;
	}
	public String getMeasureState() {
		return measureState;
	}
	public void setMeasureState(String measureState) {
		this.measureState = measureState;
	}
	public String getMeasureType() {
		return measureType;
	}
	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}
	public String getResult1() {
		return result1;
	}
	public void setResult1(String result1) {
		this.result1 = result1;
	}
	public String getResult2() {
		return result2;
	}
	public void setResult2(String result2) {
		this.result2 = result2;
	}
	public String getResult3() {
		return result3;
	}
	public void setResult3(String result3) {
		this.result3 = result3;
	}
	public String getResult4() {
		return result4;
	}
	public void setResult4(String result4) {
		this.result4 = result4;
	}
	public String getResult5() {
		return result5;
	}
	public void setResult5(String result5) {
		this.result5 = result5;
	}
	public String getSendMsg() {
		return sendMsg;
	}
	public void setSendMsg(String sendMsg) {
		this.sendMsg = sendMsg;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
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
