package com.gcs.aol.entity;

import java.util.List;
import java.util.Map;

public class HighCharVo {
	
	String typename="";
	
	List<Map<String, String>> highvalue ;
	
	public String getTypename() {
		return typename;
	}
	
	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	public List<Map<String, String>> getHighvalue() {
		return highvalue;
	}
	
	public void setHighvalue(List<Map<String, String>> highvalue) {
		this.highvalue = highvalue;
	}
	
	public HighCharVo(String typename,List<Map<String, String>> highvalue ){
		this.typename = typename;
		this.highvalue = highvalue;
	}
	
}
