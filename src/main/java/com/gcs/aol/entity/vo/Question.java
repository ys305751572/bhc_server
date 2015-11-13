package com.gcs.aol.entity.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.google.common.annotations.Beta;

/**
 * 题目
 * @author yesong
 *
 */
@Entity
@Table(name = "t_detection_q")
public class Question {

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;
	
	@Column(name = "QNO")
	private Integer qno;  // 题号
	@Column(name = "TITLE")
	private String question; // 题目
	@Column(name = "A")
	private String optiona; // 选项，为空则无此选项
	
	@Column(name = "B")
	private String optionb;
	@Column(name = "C")
	private String optionc;
	
	@Column(name = "D")
	private String optiond;
	
	@Column(name = "ANSWER")
	private String answer; // 答案
	
	@Column(name = "T_ID")
	private String tid; // 所属题库ID
	
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getQno() {
		return qno.intValue();
	}
	public void setQno(Integer qno) {
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
