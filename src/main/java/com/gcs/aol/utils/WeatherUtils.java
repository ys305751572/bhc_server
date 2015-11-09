package com.gcs.aol.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;


import com.alibaba.fastjson.JSONObject;

public class WeatherUtils {

	/**
	 * 从中国天气网获取近6天的天气报文（包含今天）
	 * @param cityName
	 * @return
	 * @throws Exception
	 */
	public static String getWeaterMessage(String cityName) throws Exception {
		StringBuilder sb = null;
	    BufferedReader br = null;
	    URLConnection connectionData = null;
		
	    String url_path = "http://api.map.baidu.com/telematics/v3/weather?location=" +URLEncoder.encode(cityName, "UTF-8")+ "&output=xml&ak=OSrjGGEtBFeSVHW8GzpthtRQ&sn=" + snCal(cityName);
	    
		URL url = new URL(url_path);
		connectionData = url.openConnection();
		// 设置通用的请求属性
		connectionData.setRequestProperty("accept", "*/*");
		connectionData.setRequestProperty("connection", "Keep-Alive");
		connectionData.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		// 建立实际的连接
		connectionData.connect();
		
        br = new BufferedReader(new InputStreamReader(connectionData.getInputStream(), "UTF-8"));
        sb = new StringBuilder();
        String line = null;
        while((line = br.readLine()) != null){
        	sb.append(line);
        }
        
        return sb.toString();
	}
	
	/**
	 * 解析
	 * @param weatherStrXml
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static  Map<String, String> getWeaterOfXml(String weatherStrXml) {
		Map<String, String> weatherMap = null;
		
		try {
			//String weatherStrXml = getWeaterMessage(cityName);
			StringReader read = new StringReader(weatherStrXml);
			InputSource source = new InputSource(read);
			SAXBuilder saxBuilde = new SAXBuilder(false);  
			Document doc = saxBuilde.build(source);
			Element root = doc.getRootElement();
			
			weatherMap = new HashMap<String, String>();
			weatherMap.put("date_time", root.getChildTextTrim("date"));
			if(!"success".equals(root.getChildTextTrim("status"))){
				weatherMap.put("isOk", "false");
			} else {
				weatherMap.put("isOk", "true");
				//获取天气结果
				Element results = root.getChild("results");
				weatherMap.put("city_name", results.getChildTextTrim("currentCity"));//城市名称
				weatherMap.put("pm25", results.getChildTextTrim("pm25"));//pm2.5
				//获取具体天气情况
				Element weather_data = results.getChild("weather_data");
				List dataList = weather_data.getChildren("date");
				Element dataToday = (Element) dataList.get(0);
				weatherMap.put("week_today", dataToday.getTextTrim());//今天星期
				Element dataTomorrow = (Element) dataList.get(1);
				weatherMap.put("week_tomorrow", dataTomorrow.getTextTrim());//明天星期
				
				List weatherList = weather_data.getChildren("weather");
				Element weatherToday = (Element) weatherList.get(0);
				weatherMap.put("weather_today", weatherToday.getTextTrim());//今天天气
				Element weatherTomorrow = (Element) weatherList.get(1);
				weatherMap.put("weather_tomorrow", weatherTomorrow.getTextTrim());//明天天气
				
				List windList = weather_data.getChildren("wind");
				Element windToday = (Element) windList.get(0);
				weatherMap.put("wind_today", windToday.getTextTrim());//今天风况
				Element windTomorrow = (Element) windList.get(1);
				weatherMap.put("wind_tomorrow", windTomorrow.getTextTrim());//明天风况
				
				List temperatureList = weather_data.getChildren("temperature");
				Element temperatureToday = (Element) temperatureList.get(0);
				weatherMap.put("temperature_today", temperatureToday.getTextTrim());//今天温度
				Element temperatureTomorrow = (Element) temperatureList.get(1);
				weatherMap.put("temperature_tomorrow", temperatureTomorrow.getTextTrim());//明天温度
			}
		} catch (Exception e) {
			weatherMap.put("isOk", "false");
		}
		
		return weatherMap;
	}
	
	/**
	 * 获取天气情况
	 * @param cityName
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getWeaterOfJson(String cityName) throws Exception {
		JSONObject jsonData = null;
	    JSONObject info = null;
	    Map<String, String> weatherMap = new HashMap<String, String>();
	    
		String weatherMsg = getWeaterMessage(cityName);
		
		jsonData = JSONObject.parseObject(weatherMsg);
		info = jsonData.getJSONObject("weatherinfo");
		
		if(info != null){
			weatherMap.put("city_name", info.getString("city"));//城市名称
			weatherMap.put("city_id", info.getString("cityid"));//城市ID
			weatherMap.put("date_solar", info.getString("date_y"));//今天阳历
			weatherMap.put("date_lunar", info.getString("date"));//今天阴历
			weatherMap.put("week", info.getString("week"));//今天星期
			weatherMap.put("temp_today", info.getString("temp1"));//今天温度
			weatherMap.put("temp_tomorrow", info.getString("temp2"));//明天温度
			weatherMap.put("weather_today", info.getString("weather1"));//今天天气
			weatherMap.put("weather_tomorrow", info.getString("weather2"));//明天天气
			weatherMap.put("wind_today", info.getString("wind1"));//今天风况
			weatherMap.put("wind_tomorrow", info.getString("wind2"));//明天风况
		} else {
			throw new Exception("获取天气异常！");
		}
		
		return weatherMap;
	} 
	
	/**
	 * 重新加密组装URL
	 */
	private static String snCal(String cityName) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		//假设ak=yourak
		//假设sk=yoursk
		//计算sn跟参数对出现顺序有关，所以用LinkedHashMap保存<key,value>，此方法适用于get请求，
		//如果是为发送post请求的url生成签名，请保证参数对按照key的字母顺序依次放入Map。
		//以get请求为例：http://api.map.baidu.com/telematics/v3/weather?location=北京&output=json&ak=yourak，paramsMap中先放入location，再放output，然后放ak，放入顺序必须跟get请求中对应参数的出现顺序保持一致。
        Map<String, String> paramsMap = new LinkedHashMap<String, String>();
        paramsMap.put("location", cityName);
        paramsMap.put("output", "xml");
        paramsMap.put("ak", "OSrjGGEtBFeSVHW8GzpthtRQ");
        
        //调用下面的toQueryString方法，对LinkedHashMap内所有value作utf8编码，
        //拼接返回结果location=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourak
        String paramsStr = toQueryString(paramsMap);
        
        //对paramsStr前面拼接上/telematics/v3/weather?，后面直接拼接yoursk得到/telematics/v3/weather?location=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourakyoursk
        String wholeStr = new String("/telematics/v3/weather?" + paramsStr + "Sni9SvXl5QdRqra1IaCsOzs8ugtrsQWq");
        
        // 对上面wholeStr再作utf8编码
        String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
        
        return MD5(tempStr);
	}
	
	/**
	 * 对Map内所有value作utf8编码，拼接返回结果
	 */
	private static String toQueryString(Map<?, ?> data) throws UnsupportedEncodingException {
        StringBuffer queryString = new StringBuffer();
        for (Entry<?, ?> pair : data.entrySet()) {
            queryString.append(pair.getKey() + "=");
            queryString.append(URLEncoder.encode((String) pair.getValue(), "UTF-8") + "&");
        }
        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }
        return queryString.toString();
	}
	
	/**
	 * 来自stackoverflow的MD5计算方法，调用了MessageDigest库函数，并把byte数组结果转换成16进制
	 */
    private static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                    sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
	
}
