package com.gcs.sysmgr.controller;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.gcs.sysmgr.util.ServiceLocator;

public class GenericEntityController<V, T, M>  extends JSONController {

	/**
	 * 获取Service实例
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public M getEntityManager() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		return (M) ServiceLocator.lookup((Class) params[2]);
	}
}
