package com.gcs.aol.utils;

public class ExcelImportField {
	/** 字段中文名 */
	private String fieldName;
	/** 字段代码 */
	private String fieldCode;
	/** 字段类型 */
	private String fieldType;
	/** 字段初始值 */
	private Object fieldIniValue;
	/** 是否需要转码 */
	private boolean isTranscode = false;
	
	/**
	 * 构造对象
	 * @param fieldName 字段中文名
	 * @param fieldCode 字段代码
	 * @param fieldType 字段类型
	 * @param fieldIniValue 字段初始值
	 */
	public ExcelImportField(String fieldName, String fieldCode, String fieldType, Object fieldIniValue, boolean isTranscode){
		this.fieldName = fieldName;
		this.fieldCode = fieldCode;
		this.fieldType = fieldType;
		this.fieldIniValue = fieldIniValue;
		this.isTranscode = isTranscode;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldCode() {
		return fieldCode;
	}
	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public Object getFieldIniValue() {
		return fieldIniValue;
	}
	public void setFieldIniValue(Object fieldIniValue) {
		this.fieldIniValue = fieldIniValue;
	}
	public boolean isTranscode() {
		return isTranscode;
	}
	public void setTranscode(boolean isTranscode) {
		this.isTranscode = isTranscode;
	}
}
