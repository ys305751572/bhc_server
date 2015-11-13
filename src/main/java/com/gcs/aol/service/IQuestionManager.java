package com.gcs.aol.service;

import com.gcs.aol.entity.vo.Question;
import com.gcs.aol.entity.vo.QuestionCollection;
import com.gcs.sysmgr.service.GenericManager;

public interface IQuestionManager extends GenericManager<Question>{

	public void saveQuestions(QuestionCollection questions);
}
