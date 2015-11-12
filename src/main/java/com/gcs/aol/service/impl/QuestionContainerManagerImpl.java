package com.gcs.aol.service.impl;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.gcs.aol.dao.QuestionContainerDAO;
import com.gcs.aol.entity.vo.QuestionContainer;
import com.gcs.aol.service.IQuestionContainerManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

/**
 * 
 * @author yesong
 *
 */
@Service
public class QuestionContainerManagerImpl extends GenericManagerImpl<QuestionContainer, QuestionContainerDAO> implements IQuestionContainerManager{

	@Autowired
	private QuestionContainerDAO qcDao;
	
	@Autowired
	private EntityManagerFactory em;
	/**
	 * 保存题目
	 */

	@Override
	public Page<QuestionContainer> findAll(QuestionContainer qc, Integer currentPage, Integer pageSize) throws Exception {
		return qcDao.findAll(new PageRequest(currentPage, pageSize, Sort.Direction.DESC, "id"));
	}
	@Override
	public void create(QuestionContainer qc) {
		qcDao.save(qc);
	}
}
