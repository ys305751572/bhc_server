package com.gcs.aol.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.MultipartFile;

import com.gcs.aol.entity.Attach;
import com.gcs.aol.service.impl.AttachManagerImpl;
import com.gcs.sysmgr.util.ServiceLocator;

public class CommonUtils {
	/**
	 * 上传附件
	 * @param file
	 * @param attachPath
	 * @return
	 */
	public static Attach uploadAttach(MultipartFile file, String webRoot, String attachPath ,String userId){
		String path =  webRoot + attachPath;
		File f = new File(path);
		if(!f.exists()){
			f.mkdirs();
		}
		
		Attach newattach = new Attach();
		
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		String oldFileName = file.getOriginalFilename();
		//文件扩展名
		String kzm = oldFileName.substring(oldFileName.lastIndexOf("."));
		//服务器存储文件名
		String newFileName = oldFileName.substring(0, oldFileName.lastIndexOf("."))+"_" + getDateOf16() + kzm;
		String newFilePath = webRoot + attachPath + newFileName;
		
		try {
			
			fos = new FileOutputStream(newFilePath);
			bis = new BufferedInputStream(file.getInputStream());
			int c;
	        byte buffer[]=new byte[1024];
	        while((c= bis.read(buffer))!=-1){
	        	for(int i=0;i<c;i++)
	        		fos.write(buffer[i]);        
	        }
	        
	        AttachManagerImpl attachMgr = (AttachManagerImpl)ServiceLocator.lookup(AttachManagerImpl.class);
			Attach attach = new Attach();
			attach.setAttachName(newFileName);
			attach.setAttachTruename(oldFileName);
			attach.setAttachPath(attachPath);
			attach.setAttachSize(file.getSize() / 1024.00);
			attach.setAttachUser("");
			newattach = attachMgr.save(attach);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(bis != null){
					bis.close();
				}
				if(fos != null){
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return newattach;
	}
	
	/**
	 * 获取16为日期(yyyyMMddHHmmssSS)
	 */
	public static String getDateOf16(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
		String id = sdf.format(new Date());
		return id;
	}
	
	/**
	 * 获取文件的扩展名
	 * @param file
	 * @return
	 */
	public static String getExcelKzm(String fileName){
		if(fileName == null || "".equals(fileName)){
			return "";
		}
		return fileName.substring(fileName.lastIndexOf(".")).toUpperCase();
	}
	
	/**
	 * 验证是否是空行
	 * @param cells
	 * @return
	 */
	public static boolean checkIsNullCell(Row row){
		String result = "";
		Iterator<Cell> cellIt = row.cellIterator();
	    for (; cellIt.hasNext();){
	    	Cell cell = cellIt.next();
	    	result = result + cell.toString();
	    	
	    }

	    return "".equals(result.trim());
	}
	
	/**
	 * 保留几位小数返回字符串
	 * 
	 * @param value  数字
	 * @param digits 小数点后保留位数
	 * @return
	 */
	public static String keepNDigits(Double value, int digits) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		
		nf.setGroupingUsed(false);
		
		nf.setMaximumFractionDigits(digits);
		nf.setMinimumFractionDigits(digits);
		return nf.format(value);
	}

	/**
	 * 字符串转成double
	 * 
	 * @param numStr 数字字符串
	 * @param digits 小数点后保留位数
	 * @return
	 */
	public static double parseString(String numStr, int digits) {

		if ("".equals(numStr) || numStr == null) {
			numStr = "0.00";
		}

		double result = 0.00;

		NumberFormat nf = NumberFormat.getNumberInstance();
		BigDecimal bd;
		try {
			bd = new BigDecimal(nf.parse(numStr).doubleValue());

			result = bd.setScale(digits, BigDecimal.ROUND_HALF_UP)
					.doubleValue();

		} catch (ParseException e) {
			result = -0.00001;
			e.printStackTrace();
		}

		return result;
	}
}
