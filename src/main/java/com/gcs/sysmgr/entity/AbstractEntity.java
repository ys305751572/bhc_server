package com.gcs.sysmgr.entity;

import java.sql.Timestamp;
import java.util.TimeZone;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {

	private static final long serialVersionUID = 0x8596c8ca0bc6b234L;
	private Integer dr;
	private Timestamp ts;

	public AbstractEntity(){
		TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
		TimeZone.setDefault(tz);
		dr = 0;
		ts = new Timestamp(System.currentTimeMillis());
	}

	public Integer getDr(){
		return dr;
	}

	public void setDr(Integer dr){
		this.dr = dr;
	}

	public Timestamp getTs(){
		return ts;
	}

	public void setTs(Timestamp ts){
		this.ts = ts;
	}
}
