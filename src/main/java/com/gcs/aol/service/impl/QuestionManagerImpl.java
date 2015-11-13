package com.gcs.aol.service.impl;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gcs.aol.dao.QuestionDAO;
import com.gcs.aol.entity.vo.Question;
import com.gcs.aol.entity.vo.QuestionCollection;
import com.gcs.aol.service.IQuestionManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

/**
 * 
 * @author yesong
 *
 */
@Service
public class QuestionManagerImpl extends GenericManagerImpl<Question, QuestionDAO> implements IQuestionManager{

	@Autowired
	private QuestionDAO dao;
	
	@Autowired
	private EntityManagerFactory em;
	/**
	 * 保存题目
	 */
	@Override
	@Transactional
	public void saveQuestions(QuestionCollection questions) {
		List<Question> qList = questions.getQuestions();
		for (Question question : qList) {
			if(question.getQuestion() != null) {
				question.setTid(questions.getTid());
				dao.save(question);
			} 
		}
	}
}
