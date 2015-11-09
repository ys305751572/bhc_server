package com.gcs.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IDUtil {
	private static int i = 0;
	public static String getID(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
		if(i<10000){
			i=i++;
		}else{
			i=0;
		}
		String id = sdf.format(new Date())+i;
		return id;
	}
}
