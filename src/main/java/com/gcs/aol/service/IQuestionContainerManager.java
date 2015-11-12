package com.gcs.aol.service;

import org.springframework.data.domain.Page;
import com.gcs.aol.entity.vo.QuestionContainer;
import com.gcs.sysmgr.service.GenericManager;

public interface IQuestionContainerManager extends GenericManager<QuestionContainer>{

	public Page<QuestionContainer> findAll(QuestionContainer qc,Integer currentPage, Integer pageSize) throws Exception ;
	
	public void create(QuestionContainer qc);
}
