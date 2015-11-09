package com.gcs.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnectionUtil {

	public static String getRequestStr(String url)
			throws UnsupportedEncodingException, IOException {
		String resultStr = "";
		HttpURLConnection httpConn = null;
		URL urls = new URL(url);
		httpConn = (HttpURLConnection) urls.openConnection();
		httpConn.setConnectTimeout(20000);
		httpConn.setReadTimeout(20000);
		httpConn.setDoOutput(true);
		BufferedReader br = new BufferedReader(new InputStreamReader(httpConn
				.getInputStream(), "utf-8"));
		httpConn.connect();
		String line = br.readLine();
		while (line != null) {
			resultStr = resultStr + line;
			line = br.readLine();
		}

		if (httpConn != null)
			httpConn.disconnect();
		return resultStr;
	}

	public static String postRequestStr(String path, String data)
			throws UnsupportedEncodingException, IOException {
		String resultStr = "";
		HttpURLConnection httpConn = null;
		URL urls = new URL(path);
		httpConn = (HttpURLConnection) urls.openConnection();
		httpConn.setRequestMethod("POST");
		httpConn.setConnectTimeout(20000);
		httpConn.setReadTimeout(20000);
		httpConn.setDoOutput(true);
		DataOutputStream out = new DataOutputStream(httpConn.getOutputStream());
		if (data != null)
			out.write(data.getBytes("utf-8"));
		out.flush();
		out.close();
		BufferedReader br = null;
		httpConn.connect();
		try {
			System.out.println(httpConn.getResponseCode());
			br = new BufferedReader(new InputStreamReader(httpConn
					.getInputStream()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (br != null) {
			String line = br.readLine();
			while (line != null) {
				try {
					resultStr = resultStr + line;
					line = br.readLine();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			br.close();
		}
		if (httpConn != null)
			httpConn.disconnect();
		return resultStr;
	}

	public static void main(String[] args) {
		try {
			System.out.println(postRequestStr("http://121.42.8.219:8080",
					"5A180010002F2031561400000016614091022130065"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
