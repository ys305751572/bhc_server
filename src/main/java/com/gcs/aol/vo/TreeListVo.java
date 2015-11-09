package com.gcs.aol.vo;

import java.util.List;


public class TreeListVo extends DeciveListVO {
	
	private static final long serialVersionUID = -7635915547140606761L;
	List<DeciveListVO> children;
	public List<DeciveListVO> getChildren() {
		return children;
	}
	public void setChildren(List<DeciveListVO> children) {
		this.children = children;
	}

	
}
