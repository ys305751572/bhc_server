package com.gcs.utils;

import java.util.ArrayList;

/**
 * 服务器返回对象
 * 所有服务器处理返回的统一对象
 */
public class JSONResponse {
	//成功、失败标志
	private boolean successed = false;
	//错误信息
	private ArrayList<JSONError> errors = null;
	//成功时返回的对象
	private Object returnObject = null;
	
	public void setSuccessed(boolean successed) {
		this.successed = successed;
	}
	public boolean isSuccessed() {
		return successed;
	}
	public void setErrors(ArrayList<JSONError> errors) {
		this.errors = errors;
	}
	public ArrayList<JSONError> getErrors() {
		return errors;
	}
	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}
	public Object getReturnObject() {
		return returnObject;
	}
}
