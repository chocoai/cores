import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.lanen.util.DateUtil;


public class Test {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		System.err.println(93%31+"as");
		Long l0=10l;
		String str="10";
		Integer it=10;
		Integer.parseInt(str);
		System.err.println(Integer.valueOf(l0.toString()));
		System.err.println(Integer.parseInt(l0.toString()));
		System.err.println(Byte.parseByte(str));
		System.err.println(it.toString());
		
		 UUID uuid = UUID.randomUUID();
	        System.out.println(uuid);
	        System.out.println(uuid.toString());
	        
	        
	        String result="";  
	        for(int i=0;i<6;i++){  
	            int intVal=(int)(Math.random()*26+97);  
	            result=result+(char)intVal;  	            
	        }  
	        System.out.print(result); 
	       System.err.println( Test.randomPassword(8, 10));
	       
	       System.err.println(isNum2("02"));
	       
	       
	       String s1=",7,8,9,99,10,11,12,97,";
	       String[]s=s1.split(",");
	       System.err.println(s[0]+"sssss");
	       
	       Date d=DateUtil.AddDate(new Date(), -30);
	       String sss=DateUtil.dateToString(d, "yyyy-MM-dd");
	       System.err.println(sss+"============");
	       System.err.println(new Date().getYear()+1900+"----====--");
	       System.err.println(new Date().getMonth()+1+"----====--");
	       
	       System.out.println(getLastDayOfMonth(new Date()).getDate());
	       
	       System.err.println(",".split(",").length);
	       
	       Date d7=DateUtil.AddDate(new Date(), 7);
	       String s7=DateUtil.dateToString(d7, "yyyy-MM-dd");
	       System.err.println(s7);
	       Date d1=DateUtil.stringToDate("2015-10-12 20:20:20", "yyyy-MM-dd HH:mm:ss");
	       System.err.println(d1.before(new Date()));
	       
	       
	       TestInteger();
	       yymmdd();
	       
	       
	       String str12="ascdfdffs";
	       System.err.println(str12.substring(1, 3));
	       set02();
	       str();
	       getTime();
	       split2();
	}
	
	/**
	 * 产生n位随机数
	 * 
	 * @param n
	 * @param radix
	 *            10-只有数字 36-包含数字和字母(小写)
	 * @return
	 */
	public static String randomPassword(int n, int radix) {
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
	 * 判断整数或小数
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	
	/**
	 * 判断有符号整数
	 * @param str
	 * @return
	 */
	public static boolean isNum1(String str){
		return str.matches("^[-+]?(([0-9]+)?)");
	}

	/**
	 * 判断无符号整数
	 * @param str
	 * @return
	 */
	public static boolean isNum2(String str){
		return str.matches("[1-9]([0-9]+)?");
	}

	/**
	 * 月最后一天
	 * 
	 * @param sDate1
	 * @return
	 */
	public static Date getLastDayOfMonth(Date sDate1) {
		Calendar cDay1 = Calendar.getInstance();
		cDay1.setTime(sDate1);
		final int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date lastDate = cDay1.getTime();
		lastDate.setDate(lastDay);
		return lastDate;
	}
	
	public static void TestInteger() throws ParseException{
		Integer i=Integer.valueOf("1987456321");
		i=Integer.parseInt("1987456321");
		Long i1=Long.valueOf("121345678911");
		System.err.println(i);
		//System.err.println(DateUtil.stringToDate("Fri Oct 23 00:00:00 CST 2015", "EEE MMM dd HH:mm:ss zz yyyy"));
		
		String time="Fri Oct 23 00:00:00 CST 2015";
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zz yyyy", Locale.ENGLISH);
		Date d = sdf.parse(time);
		sdf.applyPattern("yyyy-MM-dd");
		System.out.println(sdf.format(d));
	}
	public static void yymmdd() throws ParseException{
		String time = "Thu Sep 04 2014 00:00:00 GMT 0800";
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss zzz", Locale.ENGLISH);
		Date d = sdf.parse(time);
		sdf.applyPattern("yyyy:MM:dd");
		System.out.println(sdf.format(d));
	}
	public static void set01(){
		//可以换种思路，把数组放到set里面（set的值不会重复）就可以去重了
		Integer[] arr = {85,4,2,6,11,4,5,8,9};
		Set<Integer> set = new HashSet<Integer>();
		 for(Integer i : arr)
		 set.add(i);
		 for(Object j: set.toArray())
		 System.out.print(j + " ");
		 }
	public static void set02(){
	        Set  set=new HashSet();  
	        set.add("abc");  
	        set.add("cde");  
	        set.add("efg");  
	        set.add("fgh");      
	        set.add("abc"); //重复的abc,set会自动将其去掉     
	        System.out.println("size="+ set.size() );  
	          
	        List list = new ArrayList();  
	        list.add("abc");  
	        list.add("aaa");  
	        list.add("fff");  
	        set.addAll(list); //将list中的值加入set,并去掉重复的abc  
	        System.out.println("size="+ set.size() );  
	          
	        for( Iterator   it = set.iterator();  it.hasNext(); )  
	        {               
	            System.out.println("value="+it.next().toString());              
	        }   
	          
	}
	public static void str(){
		String s="as-s";
		
		System.err.println(s.contains("s-as")+"--------------");
	}
	/**
	 * map取值
	 */
	public static void map(){
		Map map = new HashMap();
		map.put("one", 4);
		  map.put("one", 1);
		  map.put("two", 2);
		  map.put("three", 3);
		  //得到value的方法
		  System.out.println("========得到value的方法========");
		  Collection c = map.values();
		  System.out.println(c);
		  Iterator iter1 = (Iterator)map.values().iterator();
		  while(iter1.hasNext()){
		   System.out.println(iter1.next());
		  }
		  //得到key的方法
		  System.out.println("========得到key的方法========");
		  Collection s = map.keySet();
		  System.out.println(s);
		  Iterator iter2 = (Iterator)map.keySet().iterator();
		  while(iter2.hasNext()){
		   System.out.println(iter2.next());
		  }
		  //Map的按键必须是唯一的，比如说不能有两个按键都为null
		  Set set=map.keySet();
		  Iterator it=set.iterator();
		  while(it.hasNext()){
			  Object key=it.next();
			  Object value=map.get(key);
			  System.err.println(key.toString()+"=="+value.toString());
		  }
	}
	//Map的按键必须是唯一的，比如说不能有两个按键都为null
	public static void map2(){
		HashMap   hashmap =new  HashMap();  

        hashmap.put("Item0", "Value0");  

        hashmap.put("Item1", "Value1");  

        hashmap.put("Item2", "Value2");  

        hashmap.put("Item3", "Value3");  

        Set  set=hashmap.entrySet();  

        Iterator   iterator=set.iterator();  

        while (iterator.hasNext()){  

          Map.Entry  mapentry = (Map.Entry) iterator.next();  

          System.out.println(mapentry.getKey()+"//"+ mapentry.getValue());  

        }   
	}
	
	public static void map3(){
		HashMap   hashmap =new  HashMap();
		HashMap   hashmap1 =new  HashMap();
		HashMap   hashmap2 =new  HashMap();
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
        hashmap.put("Item0", "Value0");  

        hashmap1.put("Item1", "Value1");  

        hashmap2.put("Item2", "Value2");  

        list.add(hashmap);list.add(hashmap1);list.add(hashmap2);
        for(Iterator<Map<String,String>> it=list.iterator();it.hasNext();){
			Map<String,String> map=it.next();
			
			Iterator keys = map.keySet().iterator();
			while(keys.hasNext()){
				if(keys.next().equals("Item0")){
					//转换异常
					Map<String,String> mm=(Map<String,String>)it;
					System.err.println(mm.get(keys.next()));
				}
			}
			
		}    
	}
	
	public static void getTime(){
		long l=System.currentTimeMillis();
		Date date=new Date(l);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str=dateFormat.format(date);
		System.err.println(str.substring(0, 10));
		System.err.println(str.substring(11, 19));
	}
	public static void split2(){
		String str="UFDXB    900  250000090615D193 ";
		String [] str1=str.split(" ");
		str.replaceAll(" +","");
	}
}
