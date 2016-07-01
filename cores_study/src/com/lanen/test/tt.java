package com.lanen.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.lanen.util.NumberValidationUtils;
import com.lanen.util.StringUtil;

public class tt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String  tempLeaderName ="<a style='color:red;'>戚家柱</a>,<a style='color:red;'>程海燕</a>,<a style='color:red;'>[未指定]</a>";
//		System.out.println(null != tempLeaderName && ( tempLeaderName.contains(",<a style='color:red;'>[未指定]</a>")
//								|| tempLeaderName.contains("<a style='color:red;'>[未指定]</a>,") ));
//		
//		tempLeaderName = tempLeaderName.replaceAll(",<a style='color:red;'>\\[未指定\\]</a>","");
//		tempLeaderName = tempLeaderName.replaceAll("<a style='color:red;'>\\[未指定\\]</a>,","");
//		
//		System.out.println(tempLeaderName);
//		
//		
//		
//		String   aa ="acbb";
//		aa = aa.replaceAll("bb", "");
//		System.out.println(aa);
//		System.out.println(NumberValidationUtils.isPositiveDecimal("01.000"));
//		Calendar calendar =Calendar.getInstance();
//        String format = "yyyyMMddHHmmss";
//        
//        SimpleDateFormat sdf = new SimpleDateFormat(format);
//        System.out.println(sdf.format(new Date()));
//        System.out.println(System.currentTimeMillis());
		System.out.println(StringUtil.studyNoRemoveFN("2015(f)"));
	}

}
