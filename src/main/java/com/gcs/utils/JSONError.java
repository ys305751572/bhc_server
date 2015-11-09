package com.gcs.utils;

/**
 * 错误信息
 *
 */
public class JSONError {
	//元素名，与页面元素名一致
	private String element;
	//错误信息
	private String message;
	
	public JSONError(String e, String m) {
		this.setElement(e);
		this.setMessage(m);
	}
	
	public void setElement(String element) {
		this.element = element;
	}
	public String getElement() {
		return element;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
}
