package com.gcs.utils;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class StringUtils {
	public static final String ANDROIDMASTERSECRET = "lzp0gvkfzpqy9qxgx0udbrbzhiac2p2r";
	public static final String ANDROIDAPPKEY = "54543265fd98c5bcd2003c87";
	public static final String ANDROIDALIASTYPE = "健康护照";
	public static final String IOSMASTERSECRET = "bjovetekgm3rleagc9idbtupda3c841d";
	public static final String IOSAPPKEY = "54339e42fd98c5bbf70189e7";
	public static final String IOSALIASTYPE = "健康护照";

	public static String changeNull(Object o) {
		if (o != null) {
			return o.toString();
		} else {
			return "";
		}
	}

	/**
	 * 根据字符串获得查询的sql
	 */
	public static String getQuerySql(String str) {
		String reStr = "";
		if (org.apache.commons.lang3.StringUtils.isNotBlank(str)) {
			String[] strs = str.split("-");
			for (String s : strs) {
				if (org.apache.commons.lang3.StringUtils.isNotBlank(s)) {
					reStr = reStr + "'" + s + "',";
				}
			}
		}
		if (reStr.length() > 0) {
			reStr = reStr.substring(0, reStr.length() - 1);
		}
		return reStr;
	}

	/**
	 * 根据字符串
	 * 
	 * @param args
	 */
	public static String getIDsByList(List<String> list) {
		String ids = "";
		for (String str : list) {
			ids = ids + "'" + str + "',";
		}
		if (ids.length() > 0) {
			ids = ids.substring(0, ids.length() - 1);
		}
		return ids;
	}

	/**
	 * 转换字符串为指定编码格式
	 * 
	 * @param str
	 *            字符串
	 * @param encoding
	 *            原字符串编码格式
	 * @param encoding
	 *            转后编码格式
	 * @return
	 */
	public static String changeCharSet(String str, String encod, String encoding) {
		try {
			return new String(str.getBytes(encod), encoding);
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}

	/**
	 * 将list转换为格式为 字符串,字符串
	 * 
	 * @param list
	 * @return
	 */
	public static String changeListToStr(List<String> list) {
		String reStr = "";
		if (list != null) {
			for (String str : list) {
				reStr = str + ",";
			}
		}
		if (!"".equals(reStr)) {
			reStr = reStr.substring(0, reStr.length() - 1);
		}
		return reStr;
	}

	/**
	 * 安卓设备发送推送
	 * 
	 * @param args
	 */

	public static String getAndroidSendJson(String userid, String ticker,
			String title, String text, String timestamp) {
		String json = "{" + " \"appkey\":\""
				+ StringUtils.ANDROIDAPPKEY
				+ "\","
				+ " \"timestamp\":\""
				+ timestamp
				+ "\","
				+ "\"type\":\"customizedcast\", "
				+ "\"alias\": \"f9aa885b483e3a4001483f6f2fce0003\"," // 不能超过50个，多个alias以英文逗号风格"
				+ " \"alias_type\":\"健康护照\","
				+ "\"payload\":"
				+ " {"
				+ "  \"display_type\": \"notification\", "// 通知，notification
				+ " \"body\":" + "{" + "   \"ticker\":\"" + ticker + "\","
				+ "  \"title\":\"" + title + "\"," + " \"text\":\"" + text
				+ "\"," + " \"after_open\": \"go_app\"" + " }" + " },"
				+ "\"production_mode\":\"false\"," // 可选
				+ "\"description\":\"测试alias通知-Android\","
				+ "\"thirdparty_id\": \"webpush-Android\"" + "}";
		return json;
	}

	// IOS消息推送
	public static String getIOSSendJson(String userid, String text,
			String timetamp) {
		String json = "{" + "\"appkey\":\"" + IOSAPPKEY + "\"," // 必填 应用唯一标识
				+ "\"timestamp\":\"" + timetamp + "\"," // 必填
				// 时间戳，10位或者13位均可，时间戳有效期为10分钟
				+ "\"type\":\"customizedcast\"," // 必填 消息发送类型,其值可以为:
				// unicast-单播
				// listcast-列播(要求不超过50个device_token)
				// filecast-文件播(多个device_token可以通过文件形式批量发送）
				// broadcast-广播
				// groupcast-组播(按照filter条件筛选特定用户群,
				// 具体请参照filter参数)
				// customizedcast(通过开发者自有的alias进行推送),
				// 包括以下两种case:
				// - alias:
				// 对单个或者多个alias进行推送
				// - file_id:
				// 将alias存放到文件后，根据file_id来推送
				// + "\"device_tokens\":\"xx\"," // 可选 设备唯一表示
				// 当type=unicast时,必填,
				// 表示指定的单个设备
				// 当type=listcast时,必填,要求不超过50个,多个device_token以英文逗号间隔
				+ " \"alias_type\": \"" + IOSALIASTYPE + "\"," // 可选
				// 当type=customizedcast时，必填，alias的类型,
				// alias_type可由开发者自定义
				// 开发者在SDK中调用setAlias(alias,
				// alias_type)时所设置的alias_type
				+ "\"alias\":\"" + userid + "\"," // 可选
				// 当type=customizedcast时,
				// 开发者填写自己的alias。
				// 要求不超过50个alias,多个alias以英文逗号间隔
				// 开发者在SDK中调用setAlias(alias,
				// alias_type)时所设置的alias
				+ "\"payload\":" // 必填
				// 消息内容(Android最大为824B),
				// 包含参数说明如下(JSON格式):
				+ "{\"aps\": " + "{" + " \"alert\": \"" + text + "\"" // 必填
				+ "}" + "}," + "\"production_mode\":\"false\"," // 可选
				// 正式/测试模式。测试模式下，只会将消息发给测试设备。
				// 测试设备需要到web上添加。
				// Android:
				// 测试设备属于正式设备的一个子集。
				+ "\"description\": \"云平台推送-IOS\"," // 可选
				// 发送消息描述，建议填写。
				+ "\"thirdparty_id\": \"webpush-IOS\"" // 可选
				// 开发者自定义消息标识ID,
				// 开发者可以为同一批发送的多条消息提供同一个
				// thirdparty_id,
				// 便于友盟后台后期合并统计数据。
				+ "}";
		return json;
	}

	public static void main(String[] args) {
	}
}
