package com.gcs.aol.service;

import java.util.List;
import com.gcs.aol.entity.vo.Question;
import com.gcs.sysmgr.service.GenericManager;

public interface IQuestionManager extends GenericManager<Question>{

	public void saveQuestions(List<Question> questionList);
}
