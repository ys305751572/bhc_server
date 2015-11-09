package com.gcs.utils;

import java.net.InetAddress;

public class PingUtils {
	public static boolean pingIP(String ip){
		boolean b = false;
		InetAddress address;
		  try {
		   address = InetAddress.getByName(ip.trim());
		   b = address.isReachable(1000);
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return b;
	}
	
	public static void main(String[] args) {
	}
}
