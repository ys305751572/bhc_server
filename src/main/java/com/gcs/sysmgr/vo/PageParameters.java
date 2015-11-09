package com.gcs.sysmgr.vo;

public class PageParameters{
	private Integer length;
	private String sEcho;
	private String iSortCol_0;
	private Integer sortCol;
	private String sSortDir_0;
	private Integer start;
	private String sort;
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public String getSEcho() {
		return sEcho;
	}
	public void setSEcho(String echo) {
		sEcho = echo;
	}
	public String getISortCol_0() {
		return iSortCol_0;
	}
	public void setISortCol_0(String sortCol_0) {
		iSortCol_0 = sortCol_0;
	}
	public Integer getSortCol() {
		return sortCol;
	}
	public void setSortCol(Integer sortCol) {
		this.sortCol = sortCol;
	}
	public String getSSortDir_0() {
		return sSortDir_0;
	}
	public void setSSortDir_0(String sortDir_0) {
		sSortDir_0 = sortDir_0;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
}