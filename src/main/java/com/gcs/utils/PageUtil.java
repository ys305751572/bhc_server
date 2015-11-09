package com.gcs.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import com.gcs.sysmgr.vo.PageParameters;

public class PageUtil{
	/**
	 * 将vo转换为字符串数组,不支持父类属性
	 * 
	 * @param strs
	 *            需要转换的属性名称
	 * @param o
	 *            需要获取的对象
	 * @param lastStr
	 *            最后一列追加的列，如果为""则代表最后一列无需追加
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@SuppressWarnings("unchecked")
	public static String[] convertVOToArrayAddLastCol(String[] strs, Object o,
			String lastStr) {
		String[] str = null;
		Class clazz = o.getClass();
		Object reObject = null;
		Method m = null;
		if (strs != null && strs.length > 0) {
			if (StringUtils.isNotBlank(lastStr)) {
				str = new String[strs.length + 1];
				str[strs.length] = lastStr;
			} else {
				str = new String[strs.length];
			}
			for (int count = 0; count < strs.length; count++) {
				String properties = strs[count];
				if (StringUtils.isNotBlank(properties)) {
					char c = properties.charAt(0);
					char uc = (char) (c - 32);
					String rfname = uc + properties.substring(1);
					String method = "get" + rfname;
					try {
						m = clazz.getDeclaredMethod(method);
						reObject = m.invoke(o);
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					if (reObject instanceof java.util.Date) {
						str[count] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(reObject);
					} else {
						str[count] = reObject == null ? "" : reObject
								.toString();
					}
				} else {
					str[count] = "";
				}
			}
		}
		return str;
	}
	
	public static PageParameters getParameter(HashMap<String, String> paramMap,String sortStrs[]){
		int length = Integer.parseInt(paramMap.get("iDisplayLength"));
		String sEcho = paramMap.get("sEcho");
		String iSortCol_0 = paramMap.get("iSortCol_0");
		Integer sortCol = Integer.parseInt(iSortCol_0);
		String sSortDir_0 = paramMap.get("sSortDir_0");
		int start = Integer.parseInt(paramMap.get("iDisplayStart")) / length;
		String sort = "createTime";
		if (sortStrs!=null&&sortStrs.length > sortCol) {
			sort = sortStrs[sortCol];
		}
		PageParameters p = new PageParameters();
		p.setSEcho(sEcho);
		p.setISortCol_0(iSortCol_0);
		p.setSortCol(sortCol);
		p.setSSortDir_0(sSortDir_0);
		p.setStart(start);
		p.setSort(sort);
		p.setLength(length);
		return p;
		
	}
	
	public static PageParameters getParameter(HashMap<String, String> paramMap,String sortStr){
		int length = Integer.parseInt(paramMap.get("iDisplayLength"));
		String sEcho = paramMap.get("sEcho");
		String iSortCol_0 = paramMap.get("iSortCol_0");
		Integer sortCol = -1;
		if(iSortCol_0!=null){
			sortCol = Integer.valueOf(iSortCol_0);
		}
		String sSortDir_0 = paramMap.get("sSortDir_0")==null?"desc":paramMap.get("sSortDir_0");
		int start = Integer.parseInt(paramMap.get("iDisplayStart")) / length;
		String sort = "createTime";
		if (sortStr!=null&&sortStr.trim().length()>0) {
			sort = sortStr;
		}
		PageParameters p = new PageParameters();
		p.setSEcho(sEcho);
		p.setISortCol_0(iSortCol_0);
		p.setSortCol(sortCol);
		p.setSSortDir_0(sSortDir_0);
		p.setStart(start);
		p.setSort(sort);
		p.setLength(length);
		return p;
	}
	
	public static int getCurrentPage(Integer startPage, Integer length) {
		int start = startPage.intValue() / length.intValue();
		return start;
	}
}
