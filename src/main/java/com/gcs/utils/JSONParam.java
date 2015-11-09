package com.gcs.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class JSONParam {
	private String name;
	private String value;
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	
	public static Map<String,String> convertToMap(JSONParam[] jp){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for(JSONParam j:jp){
			map.put(j.getName(), j.getValue());
		}
		return map;
	}
}
