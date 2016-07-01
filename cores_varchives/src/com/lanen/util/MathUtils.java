package com.lanen.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathUtils {

	/**
	 * 取商,如果有余数则进位
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public static Integer DivideBigger(Integer dividend , Integer divisor){
		int result = 0;
		if(divisor != 0){
			result = dividend/divisor;
			if(dividend%divisor >0){
				result = result + 1;
			}
		}
		return result;
	}
	
	/*public static String add1ToStringInt(String str){
		 str = str.trim();
		 Pattern pattern = Pattern.compile("[0-9]");
		 Pattern pattern2 = Pattern.compile("[0-9]*");
		 Matcher matcher = pattern.matcher(str);
		 boolean flag = false;
		 String str2 = str;
		 while (matcher.find()) {
			str2 = str2.substring(str2.indexOf(matcher.group()));
			if(pattern2.matcher(str2).matches())
			{
				Integer aI = Integer.valueOf(str2)+1;
				int index = str.length() - str2.length();
				str = str.substring(0,index).concat(""+aI);
				flag = true;
				break;
			}
			str2 = str2.substring(1);
		 }
		 if(!flag){
			 str+="1";
		 }
		 
		 return str;
	}*/
	public static String add1ToStringInt(String str){ 
		if(str.length()>1&&Character.isDigit(str.charAt(str.length()-1)))
		{
			String[] array2 = str.split("[\\D]+");
			Integer aInteger = Integer.parseInt(array2[array2.length-1]);
			
			str = str.substring(0,str.length()-array2[array2.length-1].length() )+(aInteger+1);
			
		}else {
			str += 1;
		}
		 return str;
	}
	
	public static void main(String[] args) {
		 String str = "";
		 String string = add1ToStringInt(str);
		System.out.println("string="+string);
	}
}
