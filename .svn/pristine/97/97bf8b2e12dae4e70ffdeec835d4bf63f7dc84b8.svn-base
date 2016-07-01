package com.lanen.util;

public class StringUtil {
	/**
	 * int转String指定位数前补零
	 * @param source
	 * @param length
	 * @return
	 */
	public static String intToString(int source , int length){
		String str="";
		int value=0;
		switch (length) {
		case 2:value= 100+source;
			   str=value+"";
			   str= str.substring(1);
			break;
		case 3:value= 1000+source;
		str=value+"";
		str= str.substring(1);
		break;
		case 4:value= 10000+source;
		str=value+"";
		str= str.substring(1);
		break;
		case 5:value= 100000+source;
		str=value+"";
		str= str.substring(1);
		break;
		default:str="";
			break;
		}
		return str;
	}

}
