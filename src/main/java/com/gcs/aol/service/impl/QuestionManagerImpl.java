package com.gcs.aol.service.impl;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gcs.aol.dao.QuestionDAO;
import com.gcs.aol.entity.vo.Question;
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
	public void saveQuestions(List<Question> questionList) {
		for (Question question : questionList) {
			dao.save(question);
		}
	}
}
