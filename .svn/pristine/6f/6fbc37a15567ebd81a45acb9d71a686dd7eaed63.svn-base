package com.lanen.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FileUtil {   
  
    private static final int BUFFER_SIZE = 16 * 1024;   
  
    /**
     * 把 src read后  write到dst里
     * 
     * @param src
     * @param dst
     * @throws IOException
     */
    public static void uploadFile(File src, File dst) throws IOException {   
  
        InputStream in = null;   
        OutputStream out = null;   
        try {   
            in = new java.io.BufferedInputStream (new FileInputStream(src), BUFFER_SIZE); 
            out = new java.io.BufferedOutputStream(new FileOutputStream(dst),BUFFER_SIZE);
            
            byte[] buffer = new byte[BUFFER_SIZE];   
            int len = in.read(buffer);
            while (len > 0) {   
                out.write(buffer,0,len);
                len = in.read(buffer);
            }   
        } finally {   
            if (null != in) {   
                in.close();   
            }   
            if (null != out) {   
                out.close();   
            }   
        }   
  
    }   
  
    /**
     * 获得文件名后缀
     * @param fileName
     * @return
     */
    public static String getExtention(String fileName) {   
        int pos = fileName.lastIndexOf(".");   
        return fileName.substring(pos);   
    }   
  
    /**
     * 文件目录，若不存在则创建
     * @param directory
     */
    public static void makeDir(String directory) {   
        File dir = new File(directory);   
  
        if (!dir.isDirectory()) {   
            dir.mkdirs();   
        }   
  
    }   
  
    /**
     * 给文件名后加个 时间及随机数
     * @param fileName
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String generateFileName(String fileName)   
             {   
        DateFormat format = new SimpleDateFormat("yyMMddHHmmss");   
        String formatDate = format.format(new Date());   
        String extension = fileName.substring(fileName.lastIndexOf("."));   
//        fileName = new String(fileName.getBytes("iso8859-1"), "gb2312"); 
        fileName = fileName.substring(0,fileName.lastIndexOf("."));
        return fileName + "_" + formatDate + new Random().nextInt(10000)   
                + extension;   
    }   
  
}  

