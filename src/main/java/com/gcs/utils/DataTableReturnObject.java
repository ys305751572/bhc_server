package com.gcs.utils;

import java.util.List;

public class DataTableReturnObject {

 private long iTotalRecords;
 private long iTotalDisplayRecords;
 private String sEcho;
 private String[][] aaData;
 private List<?> bbData;
 public DataTableReturnObject() {
}
 public DataTableReturnObject(long totalRecords, long totalDisplayRecords,
   String echo, String[][] d,List<?> bbData) {
  this.iTotalRecords = totalRecords;
  this.iTotalDisplayRecords = totalDisplayRecords;
  this.sEcho = echo;
  this.aaData = d;
  this.bbData = bbData;
 }

 public DataTableReturnObject(long totalRecords, long totalDisplayRecords,
		   String echo, String[][] d) {
		  this.iTotalRecords = totalRecords;
		  this.iTotalDisplayRecords = totalDisplayRecords;
		  this.sEcho = echo;
		  this.aaData = d;
		 }
 
 public DataTableReturnObject(long totalRecords, long totalDisplayRecords,
		   String echo,List<?> bbData) {
		  this.iTotalRecords = totalRecords;
		  this.iTotalDisplayRecords = totalDisplayRecords;
		  this.sEcho = echo;
		  this.bbData = bbData;
		 }
 
 public long getiTotalRecords() {
  return iTotalRecords;
 }

 public void setiTotalRecords(long iTotalRecords) {
  this.iTotalRecords = iTotalRecords;
 }

 public long getiTotalDisplayRecords() {
  return iTotalDisplayRecords;
 }

 public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
  this.iTotalDisplayRecords = iTotalDisplayRecords;
 }

 public String getsEcho() {
  return sEcho;
 }

 public void setsEcho(String sEcho) {
  this.sEcho = sEcho;
 }

 public String[][] getAaData() {
  return aaData;
 }

 public void setAaData(String[][] aaData) {
  this.aaData = aaData;
 }
public List<?> getBbData() {
	return bbData;
}
public void setBbData(List<?> bbData) {
	this.bbData = bbData;
}

}