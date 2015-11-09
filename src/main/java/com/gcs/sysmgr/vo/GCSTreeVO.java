package com.gcs.sysmgr.vo;

import java.util.List;

public class GCSTreeVO {
	private String id;
	private String text;
	private String state;
	private List<GCSTreeVO> children;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<GCSTreeVO> getChildren() {
		return children;
	}
	public void setChildren(List<GCSTreeVO> children) {
		this.children = children;
	}
}
