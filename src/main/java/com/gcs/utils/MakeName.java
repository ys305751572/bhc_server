package com.gcs.utils;

import java.util.Date;

public class MakeName {
	private MakeName() {
	}
	
	private static MakeName makeName;

	public static  MakeName getMakeName() {
		if (makeName == null) {
			makeName = new MakeName();
		}
		return makeName;
	}
	public synchronized String fjName(){
		return new Date().toString();
	}
}
