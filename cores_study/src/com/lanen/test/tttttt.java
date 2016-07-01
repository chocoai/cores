package com.lanen.test;

import java.io.IOException;

import com.lanen.util.CryptUtils;

public class tttttt {

    //将字符串转换成二进制字符串，以2相隔
    private static String StrToBinstr(String str) {
        char[] strChar=str.toCharArray();
        String result="";
        for(int i=0;i<strChar.length;i++){
            result +=Integer.toBinaryString(strChar[i])+ "2";
        }
        return result;
    }
    //将二进制字符串转换成Unicode字符串
    private static String BinstrToStr(String binStr) {
        String[] tempStr=StrToStrArray(binStr);
        char[] tempChar=new char[tempStr.length];
        for(int i=0;i<tempStr.length;i++) {
            tempChar[i]=BinstrToChar(tempStr[i]);
        }
        return String.valueOf(tempChar);
    }
    //TODO 将二进制字符串转换为char
    private static char BinstrToChar(String binStr){
        int[] temp=BinstrToIntArray(binStr);
        int sum=0;   
        for(int i=0; i<temp.length;i++){
            sum +=temp[temp.length-1-i]<<i;
        }   
        return (char)sum;
    }
    //TODO 将初始二进制字符串转换成字符串数组，以2相隔
    private static String[] StrToStrArray(String str) {
        return str.split("2");
    }
    //TODO 将二进制字符串转换成int数组
    private static int[] BinstrToIntArray(String binStr) {       
        char[] temp=binStr.toCharArray();
        int[] result=new int[temp.length];   
        for(int i=0;i<temp.length;i++) {
            result[i]=temp[i]-48;
        }
        return result;
    }


	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		System.out.println(CryptUtils.encryptString("src0051"));
		String binstr =StrToBinstr("6Cm+5EeRYjg=");
		System.out.println(binstr);
		String str = BinstrToStr(binstr);
		System.out.println(str);
		System.out.println(CryptUtils.decryptString("6Cm+5EeRYjg="));

	}

}
