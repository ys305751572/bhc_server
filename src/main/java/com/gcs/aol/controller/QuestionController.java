package com.gcs.aol.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcs.aol.entity.vo.Question;
import com.gcs.aol.entity.vo.QuestionContainer;

@RequestMapping("/management/question/")
@Controller
public class QuestionController {

	@RequestMapping(value = "testList", method = RequestMethod.POST)
	@ResponseBody
	public void testList(QuestionContainer questions) {
		if(questions != null && questions.getQuestions() != null && questions.getQuestions().size() > 0) {
			List<Question> list = questions.getQuestions();
			for (Question question : list) {
				System.out.println("id:" + question.getId() + "==answer:" + question.getAnswer());
			}
		}
		else {
			System.out.println("参数没有穿进来 ");
		}
	}
	
	@RequestMapping(value = "testPage", method = RequestMethod.GET)
	public String testPage() {
		return "/management/aol/test/doctorAdd";
	}
}
