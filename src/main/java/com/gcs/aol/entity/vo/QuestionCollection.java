package com.gcs.aol.entity.vo;

import java.util.List;

public class QuestionCollection {

	private String tid;
	private List<Question> questions;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
