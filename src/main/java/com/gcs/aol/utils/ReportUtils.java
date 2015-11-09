package com.gcs.aol.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.gcs.aol.entity.HighCharVo;
import com.gcs.aol.vo.MeasureListVO;

public class ReportUtils {

	
	public static List<HighCharVo> twReportListVo(List<MeasureListVO> measurelistvolist){
		List<HighCharVo> charVos = new ArrayList<HighCharVo>();
		//高压分为3条线
		HighCharVo tw = new HighCharVo("血糖",null);//血糖
		List<Map<String, String>> twlist = new ArrayList<Map<String,String>>();
		for (MeasureListVO measureListVO : measurelistvolist) {
			//装入血糖
			twlist.add(setMap(DateUtil.DeleteYear(measureListVO.getSendTime()), measureListVO.getResult4()));
		}
		tw.setHighvalue(twlist);
		charVos.add(tw);
		return charVos;
	}
	
	/**
	 * 根据传递过来的血糖数据转换成Report需要用到vo
	 * @param measurelistvolist
	 * @return
	 */
	public static String twReportXml(List<MeasureListVO> measurelistvolist){
		//体重1条线
		String strxml = "";
		if(measurelistvolist.size()==0){
			strxml = "<graph caption='体温统计'  canvasBorderColor = '#FFFFFF' bgColor='#FFFFFF'  showBorder='0' baseFontSize='12' rotateNames='0' > ";
			strxml = strxml +"<categories > ";
			strxml = strxml +"<category name='0' />";
			strxml = strxml +"<category name='0' />";
			strxml = strxml +"</categories>  ";
			strxml = strxml +"<dataset seriesName='体温' color='1D8BD1' anchorBorderColor='1D8BD1' anchorBgColor='1D8BD1'>";
			strxml = strxml +"<set value='0' />";
			strxml = strxml +"<set value='0' />";
			strxml = strxml +"</dataset>";
			strxml = strxml +"</graph>";
			return strxml;
		}
		String title = "";
		if(StringUtils.isNotBlank(measurelistvolist.get(0).getUserNumber())){
			title = "用户"+measurelistvolist.get(0).getMeasureName()+"("+measurelistvolist.get(0).getUserNumber()+")体温统计";
		}else{
			title = "用户"+measurelistvolist.get(0).getMeasureName()+"体温统计";
		}
		strxml = "<graph caption='"+title+"'    canvasBorderColor = '#FFFFFF' bgColor='#FFFFFF' showBorder='0'  baseFontSize='12' canvasRightMargin='30'  showLabels ='0' > ";
		strxml = strxml +"<categories > ";
		for (MeasureListVO measureListVO : measurelistvolist) {
				strxml =  strxml +"<category name='"+measureListVO.getSendTime()+"' />";
		}
		strxml = strxml +"</categories>  ";
		//体重
		strxml = strxml +"<dataset seriesName='体温' color='1D8BD1'  anchorBorderColor='1D8BD1' anchorBgColor='1D8BD1'>";
		for (MeasureListVO measureListVO : measurelistvolist) {
				strxml =  strxml +"<set value='"+measureListVO.getResult4()+"' />";
		}
		strxml = strxml +"</dataset>";
		strxml = strxml +"</graph>";
		return strxml;
	}
	
	public static List<HighCharVo> xtReportListVo(List<MeasureListVO> measurelistvolist){
		List<HighCharVo> charVos = new ArrayList<HighCharVo>();
		//高压分为3条线
		HighCharVo xt = new HighCharVo("血糖",null);//血糖
		List<Map<String, String>> xtlist = new ArrayList<Map<String,String>>();
		for (MeasureListVO measureListVO : measurelistvolist) {
			//装入血糖
			xtlist.add(setMap(DateUtil.DeleteYear(measureListVO.getSendTime()), measureListVO.getResult4()));
		}
		xt.setHighvalue(xtlist);
		charVos.add(xt);
		return charVos;
	}
	
	/**
	 * 根据传递过来的血糖数据转换成Report需要用到Xml
	 * @param measurelistvolist
	 * @return
	 */
	public static String xtReportXml(List<MeasureListVO> measurelistvolist){
		
		//血糖1条线
		String strxml = "";
		if(measurelistvolist.size()==0){
			strxml = "<graph caption='血糖统计'   canvasBorderColor = '#FFFFFF' bgColor='#FFFFFF' showBorder='0'  baseFontSize='12' canvasRightMargin='30' rotateNames='0' showLabels ='0'> ";
			strxml = strxml +"<categories > ";
			strxml = strxml +"<category name='0' />";
			strxml = strxml +"<category name='0' />";
			strxml = strxml +"</categories>  ";
			strxml = strxml +"<dataset seriesName='血糖' color='1D8BD1' anchorBorderColor='1D8BD1' anchorBgColor='1D8BD1'>";
			strxml = strxml +"<set value='0' />";
			strxml = strxml +"<set value='0' />";
			strxml = strxml +"</dataset>";
			strxml = strxml +"</graph>";
			return strxml;
		}
		String title = "";
		if(StringUtils.isNotBlank(measurelistvolist.get(0).getUserNumber())){
			title = "用户"+measurelistvolist.get(0).getMeasureName()+"("+measurelistvolist.get(0).getUserNumber()+")血糖统计";
		}else{
			title = "用户"+measurelistvolist.get(0).getMeasureName()+"血糖统计";
		}
		strxml = "<graph caption='"+title+"'   canvasBorderColor = '#FFFFFF' bgColor='#FFFFFF' showBorder='0'  baseFontSize='12' canvasRightMargin='30' rotateNames='0' showLabels ='0' > ";
		strxml = strxml +"<categories > ";
		
		for (MeasureListVO measureListVO : measurelistvolist) {
				strxml =  strxml +"<category name='"+measureListVO.getSendTime()+"' />";
		}
		strxml = strxml +"</categories>  ";
		//高血糖
		strxml = strxml +"<dataset seriesName='血糖' canvasBorderThickness='0' color='1D8BD1' anchorBorderColor='1D8BD1' anchorBgColor='1D8BD1'>";
		for (MeasureListVO measureListVO : measurelistvolist) {
				if("2".equals(measureListVO.getMeasureState())){
					strxml =  strxml +"<set value='"+measureListVO.getResult4()+"' />";
				}else{
					strxml =  strxml +"<set value='"+changeValue(measureListVO.getResult4())+"' />";
				}
		}
		strxml = strxml +"</dataset>";
		strxml = strxml +"</graph>";
		return strxml;
	}
	
	/**
	 * 根据传递过来的血压数据转换成	需要用到HighCharVo
	 * @param measurelistvolist
	 * @return
	 */
	public static List<HighCharVo> xyReportListVo(List<MeasureListVO> measurelistvolist){
		List<HighCharVo> charVos = new ArrayList<HighCharVo>();
		//高压分为3条线
		HighCharVo gy = new HighCharVo("高血压",null);//高压
		HighCharVo dy = new HighCharVo("低血压",null);//低压
		HighCharVo xl = new HighCharVo("心率",null);//心率
		List<Map<String, String>> gylist = new ArrayList<Map<String,String>>();
		List<Map<String, String>> dylist = new ArrayList<Map<String,String>>();
		List<Map<String, String>> xllist = new ArrayList<Map<String,String>>();
		for (MeasureListVO measureListVO : measurelistvolist) {
			//装入GY
			gylist.add(setMap(DateUtil.DeleteYear(measureListVO.getSendTime()), measureListVO.getResult2()));
			//装入低压
			dylist.add(setMap(DateUtil.DeleteYear(measureListVO.getSendTime()), measureListVO.getResult3()));
			//装入心率
			xllist.add(setMap(DateUtil.DeleteYear(measureListVO.getSendTime()), measureListVO.getResult4()));
		}
		//第一条高压
		gy.setHighvalue(gylist);
		//第二条低压
		dy.setHighvalue(dylist);
		//第三条心率
		xl.setHighvalue(xllist);
		charVos.add(gy);
		charVos.add(dy);
		charVos.add(xl);
		return charVos;
	}
	
	public static Map<String, String> setMap(String x,String y){
		Map<String,String> temp = new HashMap<String, String>();
		temp.put("x", x);
		temp.put("y", y);
		return temp;
	}
	
	/**
	 * 根据传递过来的血压数据转换成	需要用到Xml
	 * @param measurelistvolist
	 * @return
	 */
	public static String xyReportXml(List<MeasureListVO> measurelistvolist){
		//血压分高血压以及底血压2条线
		String strxml = "";
		if(measurelistvolist.size()==0){
			strxml = "<graph caption='血压统计'   canvasBorderColor = '#FFFFFF' bgColor='#FFFFFF' showBorder='0'  baseFontSize='10' canvasRightMargin='30' rotateNames='0' showLabels ='0'> ";
			strxml = strxml +"<categories > ";
			strxml = strxml +"<category name='0' />";
			strxml = strxml +"<category name='0' />";
			strxml = strxml +"</categories>  ";
			strxml = strxml +"<dataset seriesName='高血压' color='EE2C2C' anchorBorderColor='EE2C2C' anchorBgColor='EE2C2C'>";
			strxml = strxml +"<set value='0' />";
			strxml = strxml +"<set value='0' />";
			strxml = strxml +"</dataset>";
			strxml = strxml +"<dataset seriesName='低血压' color='1D8BD1' anchorBorderColor='1D8BD1' anchorBgColor='1D8BD1'>";
			strxml = strxml +"<set value='0' />";
			strxml = strxml +"<set value='0' />";
			strxml = strxml +"</dataset>";
			strxml = strxml +"<dataset seriesName='心率' color='990066' anchorBorderColor='990066' anchorBgColor='990066'> ";
			strxml = strxml +"<set value='0' />";
			strxml = strxml +"<set value='0' />";
			strxml = strxml +"</dataset>";
			strxml = strxml +"</graph>";
			return strxml;
		}
		String title = "";
		if(StringUtils.isNotBlank(measurelistvolist.get(0).getUserNumber())){
			title = "用户"+measurelistvolist.get(0).getMeasureName()+"("+measurelistvolist.get(0).getUserNumber()+")血压统计";
		}else{
			title = "用户"+measurelistvolist.get(0).getMeasureName()+"血压统计";
		}
		strxml = "<graph caption='"+title+"'  labelStep='2' canvasBorderColor = '#FFFFFF' bgColor='#FFFFFF' showBorder='0'  baseFontSize='12' canvasRightMargin='30' rotateNames='0' showLabels ='0'> ";
		strxml = strxml +"<categories fontSize='9'> ";
		for (MeasureListVO measureListVO : measurelistvolist) {
				strxml =  strxml +"<category   name='"+DateUtil.DeleteYear(measureListVO.getSendTime())+"' /> ";
		}
		strxml = strxml +"</categories>  ";
		//高血压
		strxml = strxml +"<dataset seriesName='高血压' color='EE2C2C' anchorBorderColor='EE2C2C' anchorBgColor='EE2C2C'> ";
		for (MeasureListVO measureListVO : measurelistvolist) {
				strxml =  strxml +"<set value='"+measureListVO.getResult2()+"' />";
		}
		strxml = strxml +"</dataset>";
		//底血压
		strxml = strxml +"<dataset seriesName='低血压' color='1D8BD1' anchorBorderColor='1D8BD1' anchorBgColor='1D8BD1'> ";
		for (MeasureListVO measureListVO : measurelistvolist) {
				strxml =  strxml +"<set value='"+measureListVO.getResult3()+"' />";
		}
		strxml = strxml +"</dataset>";
		//心率
		strxml = strxml +"<dataset seriesName='心率' color='990066' anchorBorderColor='990066' anchorBgColor='990066'> ";
		for (MeasureListVO measureListVO : measurelistvolist) {
				strxml =  strxml +"<set value='"+measureListVO.getResult4()+"' />";
		}
		strxml = strxml +"</dataset>";
		strxml = strxml +"</graph>";
		return strxml;
	}
	
	public static String changeValue(String value){
		if(StringUtils.isNotBlank(value)){
			Double dval = Double.valueOf(value);
			dval = dval/18;
			DecimalFormat df = new DecimalFormat("#.0");
			if(df.format(dval).indexOf(".")==0){
				return "0"+df.format(dval);
			}else{
				return df.format(dval);
			}
		}else{
			return "0";
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(changeValue("72"));
	}

}
