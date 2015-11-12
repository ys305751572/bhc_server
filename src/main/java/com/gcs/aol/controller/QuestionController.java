package com.gcs.aol.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gcs.aol.entity.Attach;
import com.gcs.aol.entity.vo.Question;
import com.gcs.aol.entity.vo.QuestionCollection;
import com.gcs.aol.entity.vo.QuestionContainer;
import com.gcs.aol.service.IQuestionContainerManager;
import com.gcs.aol.service.IQuestionManager;
import com.gcs.aol.service.impl.QuestionManagerImpl;
import com.gcs.aol.utils.CommonUtils;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

@RequestMapping("/management/question/")
@Controller
public class QuestionController extends GenericEntityController<QuestionContainer, QuestionContainer, QuestionManagerImpl>{

	// TODO
	public static final String QUESTION_LIST = "/management/aol/question/qcList";
	public static final String QUESTION_EDIT = "/management/aol/question/qcEdit";
	
	public static final String QUESTION_ADD = "/management/aol/test/questionAdd";
	
	@Autowired
	private IQuestionManager manager;
	
	@Autowired
	private IQuestionContainerManager qcManager;
	
	@RequestMapping(value = "testList", method = RequestMethod.POST)
	@ResponseBody
	public void testList(QuestionCollection questions) {
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
		return "/management/aol/test/questionAdd";
	}
	
	
	// ==========================================QuestionContainer===================================
	/**
	 * 题库列表
	 * @return
	 */
	@RequestMapping(value = "pageList", method = RequestMethod.GET)
	public String pageList() {
		return QUESTION_LIST;
	}
	
	@RequestMapping(value = "findAll", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse findAll(@RequestBody JSONParam[] params) {
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		PageParameters pp = PageUtil.getParameter(paramMap, "");
		Page<QuestionContainer> questionContainerPage = null;
		try {
			questionContainerPage = qcManager.findAll(null, pp.getStart(), pp.getLength());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return successed(new DataTableReturnObject(questionContainerPage.getTotalElements(), questionContainerPage.getTotalElements(),pp.getSEcho(), questionContainerPage.getContent()));
	}
	
	/**
	 * 新增题库
	 * @param qc
	 * @return
	 */
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String createQuestionContainer(QuestionContainer qc,MultipartFile imageFile,HttpServletRequest request) {
		
		if(imageFile!=null&&imageFile.getSize()>0){
			String webRoot = request.getSession().getServletContext().getRealPath("");
			Attach attach  = CommonUtils.uploadAttach(imageFile, webRoot, "//upload//qc//",null);
			if(StringUtils.isNotBlank(attach.getAttachId()))
				qc.setImage("//upload//qc//"+attach.getAttachName());
		}
		qcManager.create(qc);
		return QUESTION_LIST;
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String deleteQuestionContainer(String id) {
		qcManager.deleteByPK(id);
		return QUESTION_LIST;
	}
	
	@RequestMapping(value = "pageEdit", method = RequestMethod.POST)
	public String pageEdit(String id,Model model) {
		QuestionContainer qc = qcManager.queryByPK(id);
		model.addAttribute("qc", qc);
		return QUESTION_EDIT;
	}
	
	// ========================================question===============================================
	
	@RequestMapping(value = "pageQuestionAdd", method = RequestMethod.POST)
	public String pageQuestionAdd(String tid, Model model) {
		
		return QUESTION_ADD;
	} 
	
	/**
	 * 保存题目
	 * @return
	 */
	@RequestMapping(value = "saveQuestions", method = RequestMethod.POST)
	public String saveQuestions(QuestionCollection questions) {
		
		if(questions != null && questions.getQuestions() != null && questions.getQuestions().size() >0) {
			manager.saveQuestions(questions.getQuestions());
		}
		else {
			System.out.println("无效参数");
		}
		return null;
	}
}
