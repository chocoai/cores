package com.lanen.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil{
	
	public static String getWeekDay(){
		String[] weekDayName={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(new Date());
		int intWeek =calendar.get(Calendar.DAY_OF_WEEK)-1;
		return weekDayName[intWeek];
	}
	public static String getWeekDay(String dateStr,String format){
		String[] weekDayName={"日","一","二","三","四","五","六"};
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(stringToDate(dateStr,format));
		int intWeek =calendar.get(Calendar.DAY_OF_WEEK)-1;
		return weekDayName[intWeek];
	}
	public static String getWeekDay(Date date){
		String[] weekDayName={"日","一","二","三","四","五","六"};
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(date);
		int intWeek =calendar.get(Calendar.DAY_OF_WEEK)-1;
		return weekDayName[intWeek];
	}
	public static Date AddDate(Date date,int i){
		if(null==date){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, i);
		return calendar.getTime();
	}
	/**
	 * 取当前系统日期，并按指定格式或者是默认格式返回
	 * @param format
	 * @return
	 */
	public static String getNow(String format){
        if(null == format || "".equals(format))
        {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String date = sdf.format(new Date());
        return date;
    }
	/**
	 * 将字符型转换为指定格式日期型
	 * @param _date 需要转换成日期的字符串
	 * @param format 与需要转换成日期的字符串相匹配的格式
	 * @return
	 */
	public static Date stringToDate(String _date,String format){
		if(null == format || "".equals(format))
        {
            format = "yyyy-MM-dd";
        }
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date=null;
		try {
			date=sdf.parse(_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 将英文格式转化为中文年月日.
	 * @param date
	 * @param format
	 * @return
	 */
	public static String yymmdd(String date,String format) {
		//String time = "Fri Oct 23 00:00:00 CST 2015";
		if(null==format || "".equals(format)){
			format="EEE MMM dd HH:mm:ss zz yyyy";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		sdf.applyPattern("yyyy-MM-dd");
		return sdf.format(d);
	}
	/**
	 * 将日期型转换为指定格式的字符串
	 * @param date 日期
	 * @param format 格式
	 * @return
	 */
	public static String dateToString(Date date,String format){
		if(null == format || "".equals(format))
        {
            format = "yyyy年MM月dd日 hh点:mm分:ss秒";
        }
		if(null==date){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 取得两个时间段的时间间隔 return t2 与t1的间隔天数 throws ParseException
	 * 如果输入的日期格式不是0000-00-00 格式抛出异常
	 */
	public static int getBetweenDays(Date d1, Date d2) throws ParseException {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		// 保证第二个时间一定大于第一个时间
		if (c1.after(c2)) {
			c1.setTime(d2);
			c2.setTime(d1);
		}
		int days=0;
		int years=c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
		if(years>0){
			days+=c1.getActualMaximum(Calendar.DAY_OF_YEAR)-c1.get(Calendar.DAY_OF_YEAR);
			for(int i=0;i<years-1;i++){
				c1.set(Calendar.YEAR, c1.get(Calendar.YEAR)+1);
				days+=c1.getActualMaximum(Calendar.DAY_OF_YEAR);
			}
			days+=c2.get(Calendar.DAY_OF_YEAR);
		}else{
			days=c2.get(Calendar.DAY_OF_YEAR)-c1.get(Calendar.DAY_OF_YEAR);
		}
		return days;
	}
	/**
	 * 获取n天日期（'yyyy-MM-dd'）
	 * @param days
	 * @return
	 */
	public static String getDateAgo(int days){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -days);
		return dateToString(calendar.getTime(), "yyyy-MM-dd");
	}
	/**
	 * 获取昨晚前后一秒日期
	 * @return
	 */
	public static Date getYesterday(){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.MILLISECOND, 998);
		return calendar.getTime();
	}

//	public static String dateToString(Date date,String format){
//		if(null == format || "".equals(format))
//        {
//            format = "yyyy年MM月dd日 hh点:mm分:ss秒";
//        }
//		if(null==date){
//			return "";
//		}
//		SimpleDateFormat sdf = new SimpleDateFormat(format);
//		return sdf.format(date);
//	}
}
