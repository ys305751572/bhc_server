package com.gcs.utils;

/**
 * 读取配置文件
 * 
 * @author xubing
 * 
 */
public class ReadConfigProperties {

	/**
	 * 获取redis 地址
	 * 
	 * @return
	 */
	public static String getRedisHost() {
		return PropertiesLoad.getPValue("redis.host", "redis.properties");
	}

	/**
	 * 获取redis 端口号
	 * 
	 * @return
	 */
	public static int getRedisPort() {
		return Integer.parseInt(PropertiesLoad.getPValue("redis.port",
				"redis.properties"));
	}
	
	public static void main(String[] args) {
		System.out.println(getRedisPort());
	}

}
