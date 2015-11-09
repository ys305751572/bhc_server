//package com.gcs.sysmgr.util;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.lang3.StringUtils;
//
//import com.gcs.cw.szy.common.entity.Sjzd;
//import com.gcs.sysmgr.staticValue.StaticVales;
//
//public class SjzdUtils {
//
//	/**
//	 * 根据大类和小类代码获取小类的名称
//	 * 
//	 * @param lb
//	 * @param zbdm
//	 * @return
//	 */
//	public static String getZbmcByZbDmAndLb(String lb, String zbdm) {
//		String zbmc = "";
//		for (Sjzd s : StaticVales.sjzdList) {
//			if (StringUtils.isNotBlank(zbdm)) {
//				if (s.getLb().toString().equals(lb.trim())
//						&& s.getDm().toString().equals(zbdm.trim())) {
//					zbmc = s.getMc();
//					break;
//				}
//			}
//		}
//		return zbmc;
//	}
//
//	/**
//	 * 根据大类代码获取所有字典信息
//	 * 
//	 * @param lb
//	 * @return
//	 */
//	public static List<Sjzd> getSjzdByLb(String lb) {
//		List<Sjzd> list = new ArrayList<Sjzd>();
//		for (Sjzd s : StaticVales.sjzdList) {
//			if (s.getLb().toString().equals(lb)) {
//				list.add(s);
//			}
//		}
//		return list;
//	}
//
//	/**
//	 * 根据大类和小类代码获取字典所有子行业的查询字符串
//	 */
//
//	public static String getCxByZbDmAndLb(String lb, String zbdm) {
//		String queryStr = "'" + zbdm + "'";
//		Sjzd sjzd = null;
//		for (Sjzd s : StaticVales.sjzdList) {
//			if (StringUtils.isNotBlank(zbdm)) {
//				if (s.getLb().toString().equals(lb.trim())
//						&& s.getDm().toString().equals(zbdm.trim())) {
//					sjzd = s;
//					break;
//				}
//			}
//		}
//
//		if (sjzd != null) {
//			for (Sjzd s : StaticVales.sjzdList) {
//				if (s.getParentid().toString().equals(sjzd.getDm().toString())) {
//					queryStr = queryStr + ",'"+s.getDm()+"'";
//				}
//			}
//		}
//
//		return queryStr;
//	}
//}
