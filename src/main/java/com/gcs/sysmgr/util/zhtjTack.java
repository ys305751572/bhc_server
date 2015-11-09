package com.gcs.sysmgr.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class zhtjTack {

	public void singing(){  
        Date date=new Date();  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
        System.out.println(sdf.format(date));  
    }  
}
