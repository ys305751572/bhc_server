package com.gcs.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class SendSmsUtil {
	public static String ENDSTRING = "【爱奥乐】";
	public static String APIKEY = "e914f56b61d7184ad08289807c642864";
	public static String USERNAME = "aiaole";
	public static String USERPASS = "aiaole0828";
	
	/**
	 * 
	 * apiKeye :e914f56b61d7184ad08289807c642864
		返回值											说明
		success:msgid								提交成功，发送状态请见4.1
		error:msgid									提交失败
		error:Missing username						用户名为空
		error:Missing password						密码为空
		error:Missing apikey						APIKEY为空
		error:Missing recipient						手机号码为空
		error:Missing message content				短信内容为空
		error:Account is blocked					帐号被禁用
		error:Unrecognized encoding					编码未能识别
		error:APIKEY or password error				APIKEY 或密码错误
		error:Unauthorized IP address				未授权 IP 地址
		error:Account balance is insufficient		余额不足
		error:Black keywords is:党中央				屏蔽词
	 * @throws IOException 
	 */
	
	public static String sendSms(String apiKey,String userName,String userPass,List<String> mobile,String content) throws IOException{
		String code = "";
		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer("http://m.5c.com.cn/api/send/?");
		// APIKEY
		sb.append("apikey=");
		sb.append(apiKey);
		//用户名
		sb.append("&username=");
		sb.append(userName);
		// 向StringBuffer追加密码
		sb.append("&password=");
		sb.append(userPass);
		// 向StringBuffer追加手机号码
		sb.append("&mobile="+StringUtils.changeListToStr(mobile));
		// 向StringBuffer追加消息内容转URL标准码
		sb.append("&content="+URLEncoder.encode(content,"GBK"));
		// 创建url对象
		URL url = new URL(sb.toString());
		// 打开url连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		// 设置url请求方式 ‘get’ 或者 ‘post’
		connection.setRequestMethod("POST");
		// 发送
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		// 返回发送结果
		code = in.readLine();
		in.close();
		connection.disconnect();
		// 输出结果
		System.out.println(code);
		return code;
	}
	
	public static String sendSms(String apiKey,String userName,String userPass,String mobile,String content) throws IOException{
		String code = "";
		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer("http://m.5c.com.cn/api/send/?");
		// APIKEY
		sb.append("apikey=");
		sb.append(apiKey);
		//用户名
		sb.append("&username=");
		sb.append(userName);
		// 向StringBuffer追加密码
		sb.append("&password=");
		sb.append(userPass);
		// 向StringBuffer追加手机号码
		sb.append("&mobile="+mobile);
		// 向StringBuffer追加消息内容转URL标准码
		sb.append("&content="+URLEncoder.encode(content,"GBK"));
		// 创建url对象
		URL url = new URL(sb.toString());
		// 打开url连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		// 设置url请求方式 ‘get’ 或者 ‘post’
		connection.setRequestMethod("POST");
		// 发送
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		// 返回发送结果
		code = in.readLine();
		in.close();
		connection.disconnect();
		// 输出结果
		System.out.println(code);
		return code;
	}
	
	public static void main(String[] args) {
		List<String> mobile = new ArrayList<String>();
		mobile.add("18502713158");
		try {
			sendSms("e914f56b61d7184ad08289807c642864", "aiaole", "aiaole0828", mobile, "你的注册验证码是1234");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
