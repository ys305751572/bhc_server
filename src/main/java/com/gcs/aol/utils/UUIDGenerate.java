package com.gcs.aol.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class UUIDGenerate {

	public static void main(String args[]) {
		System.out.println(getPrimaryKey());
		System.out.println(getPrimaryKey().length());
	}

	private UUIDGenerate() {

	}

	/**
	 * 
	 * 获得14位当前年月日时秒分随机码18位做位主键(32位主键)
	 */
	public synchronized static String getPrimaryKey() {
		// 生成主键线程安全化
		Date now = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateformat.format(now) + getRandomChar(18);
	}

	/**
	 * 获得0-9,a-z,A-Z范围的随机数
	 * 
	 * @param length
	 *            随机数长度
	 * @return String
	 */

	public static String getRandomChar(int length) {
		char[] chr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
				'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
				'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z' };

		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			buffer.append(chr[random.nextInt(62)]);
		}
		return buffer.toString();
	}

	/**
	 * 生成32UUID数据库主键
	 * 
	 * @return
	 */
	public synchronized static String generate32UUID() {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		String uuid = s.replaceAll("-", "");
		return uuid;
	}
}
