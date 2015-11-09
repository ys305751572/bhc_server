package com.gcs.aol.vo;

import java.util.HashMap;
import java.util.Map;

import com.gcs.aol.utils.ExcelImportField;

public class DeviceUploadVO {

	private String tableName = "organisedevice";
	
	private String tablePk = "organisedeviceId";
	
	private Map<String, ExcelImportField> fieldsMap;
	
	public DeviceUploadVO(){
		setfieldsMap();
	}
	
	private void setfieldsMap(){
		fieldsMap = new HashMap<String, ExcelImportField>();
		fieldsMap.put("设备序列号", new ExcelImportField("设备序列号","deviceSerial","String",null,false));
	}
	
	/**
	 * 获取表名
	 */
	public String getTableName(){
		return this.tableName;
	}
	
	/**
	 * 获取表主键字段名
	 */
	public String getTablePk(){
		return this.tablePk;
	}
	
	/**
	 * 获取导入字段Map
	 */
	public Map<String, ExcelImportField> getFieldsMap(){
		return this.fieldsMap;
	}
}
