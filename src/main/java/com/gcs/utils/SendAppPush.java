package com.gcs.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class SendAppPush {
	public static final String PUSHURL = "http://msg.umeng.com/api/send";

	public static String sendIosMsg(String userid, String msg) {
		String remsg = "";
		String timestamp = String.valueOf(DateUtil.getNowTimestamp());
		String json = StringUtils.getIOSSendJson(userid, msg, timestamp);
		String str = "POST" + PUSHURL + json + StringUtils.IOSMASTERSECRET;
		String key = MD5Tools.MD5(str).toLowerCase();
		String path = PUSHURL + "?sign=" + key;
		try {
			System.out.println(HttpConnectionUtil.postRequestStr(path, json));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return remsg;
	}

	public static String sendAndroidMsg(String userid, String text) {
		String remsg = "";
		String ticker = text;
		String title = "健康护照云平台通知";
		String timestamp = String.valueOf(DateUtil.getNowTimestamp());
		String json = StringUtils.getAndroidSendJson(userid, ticker, title, text, timestamp);
		String str = "POST" + PUSHURL + json + StringUtils.ANDROIDMASTERSECRET;
		String key = MD5Tools.MD5(str).toLowerCase();
		String path = PUSHURL + "?sign=" + key;
		try {
			System.out.println(HttpConnectionUtil.postRequestStr(path, json));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return remsg;
	}

	public static void main(String[] args) {
		System.out.println(sendAndroidMsg("f9aa885b483bbe7201483bbe72690000",
				"测试消息"));
	}
}
