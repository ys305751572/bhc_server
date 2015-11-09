package com.gcs.aol.vo;

import java.util.Map;

public class TreeChildNodeVO {

	public String id;
	
	public String text;
	
	public String state;
	
	public String iconCls;
	
	public Boolean checked;
	
	public Map<String, String> attributes;

	public TreeChildNodeVO(){
	}
	
	public TreeChildNodeVO(String _id, String _text, String iconCls){
		this.id = _id;
		this.text = _text;
		this.iconCls = iconCls;
	}
	
	public TreeChildNodeVO(String _id, String _text, String _state, String iconCls){
		this.id = _id;
		this.text = _text;
		this.state = _state;
		this.iconCls = iconCls;
	}
	
	public TreeChildNodeVO(String _id, String _text, String _state, String iconCls, Boolean _checked, Map<String, String> _attributes){
		this.id = _id;
		this.text = _text;
		this.state = _state;
		this.iconCls = iconCls;
		this.checked = _checked;
		this.attributes = _attributes;
	}
	
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

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	
}
