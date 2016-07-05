package com.lanen.util;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RandomUtil {

	/**
	 * 产生n位随机数
	 * 
	 * @param n
	 * @param radix
	 *            10-只有数字 36-包含数字和字母(小写)
	 * @return
	 * @author zhur
	 */
	public static String randomNum(int n, int radix) {
		SecureRandom a = new SecureRandom();
		long l = a.nextLong();
		long l1 = l < 0L ? -l : l;
		StringBuffer stringbuffer;
		for (stringbuffer = new StringBuffer(Long.toString(l1, radix)); stringbuffer
				.length() < n; stringbuffer.insert(0, '0'))
			;
		if (stringbuffer.length() > n)
			return stringbuffer.substring(stringbuffer.length() - n);
		else
			return stringbuffer.toString();
	}
	
	/**
	 * 判断无符号整数
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str){
		return str.matches("[1-9]([0-9]+)?");
	}
}
