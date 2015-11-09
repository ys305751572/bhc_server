package com.gcs.sysmgr.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.gcs.utils.JSONError;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;



public class JSONController {
	protected Validator validator;

	public JSONController() {
	}
	
	public JSONController(Validator validator) {
		this.validator = validator;
	}
	
	protected JSONResponse successed(Object obj) {
		JSONResponse ret = new JSONResponse();
		ret.setSuccessed(true);
		ret.setReturnObject(obj);
		
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	protected JSONResponse failed(Set failures) {
		Set<ConstraintViolation<?>> failureSet = (Set<ConstraintViolation<?>>)failures;
		ArrayList<JSONError> errors = new ArrayList<JSONError>();
		for (ConstraintViolation<?> failure : failureSet) {
			errors.add(new JSONError(failure.getPropertyPath().toString(), failure.getMessage()));
		}
		JSONResponse ret = new JSONResponse();
		ret.setSuccessed(false);
		ret.setErrors(errors);
		
		return ret;
	}
	
	protected JSONResponse failed(String msg) {
		ArrayList<JSONError> errors = new ArrayList<JSONError>();
		errors.add(new JSONError("", msg));
		
		JSONResponse ret = new JSONResponse();
		ret.setSuccessed(false);
		ret.setErrors(errors);
		
		return ret;
	}
	
	protected HashMap<String, String> convertToMap(JSONParam[] params) {
		HashMap<String, String> map = new HashMap<String, String>();
		for (JSONParam param : params) {
			if(param != null)
				map.put(param.getName(), param.getValue());
		}
		return map;
	}
}
