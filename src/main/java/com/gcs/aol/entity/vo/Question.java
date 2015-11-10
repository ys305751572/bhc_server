package com.gcs.aol.entity.vo;

/**
 * 题目
 * @author yesong
 *
 */
public class Question {

	private String id;
	private String qno;  // 题号
	private String optiona; // 选项，为空则无此选项
	private String optionb;
	private String optionc;
	private String optiond;
	
	private String answer; // 答案
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQno() {
		return qno;
	}
	public void setQno(String qno) {
		this.qno = qno;
	}
	public String getOptiona() {
		return optiona;
	}
	public void setOptiona(String optiona) {
		this.optiona = optiona;
	}
	public String getOptionb() {
		return optionb;
	}
	public void setOptionb(String optionb) {
		this.optionb = optionb;
	}
	public String getOptionc() {
		return optionc;
	}
	public void setOptionc(String optionc) {
		this.optionc = optionc;
	}
	public String getOptiond() {
		return optiond;
	}
	public void setOptiond(String optiond) {
		this.optiond = optiond;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
