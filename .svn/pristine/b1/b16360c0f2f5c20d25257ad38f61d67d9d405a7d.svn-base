package com.lanen.util;


import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;



public class POIExcelUtil extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HSSFWorkbook wb;
	HSSFSheet sheet;
	List<String> structure = new ArrayList<String>();
	
	public HSSFSheet newSheet(String sheetName)
	{
		 wb = new HSSFWorkbook();//创建Excel工作簿对象   
		 sheet = wb.createSheet(sheetName);//创建Excel工作表对象    
		 /*HSSFCellStyle cellStyle = wb.createCellStyle();//创建单元格样式   
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_DOTTED);//下边框        
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_DOTTED);//左边框        
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框        
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  */
		// wb.setSheetName(1, "第一张工作表");  //后面修改名字
		return sheet;
	}

		//sheet.setColumnWidth((short)column,(short)width);      
		//row.setHeight((short)height); 
		
		//设置打印区域
		//wb.setPrintArea(0, "$A$1:$C$2"); 
	
	public boolean save(String name)
	{
		boolean result=true;
		this.setExtendedState(JFrame.NORMAL);
		this.toFront();
		this.setAlwaysOnTop(true);
		this.setVisible(true);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		double frameWidth = this.getWidth();
		double frameHeight =this.getHeight();
		this.setBounds((int)(width-frameWidth)/2, (int)(height-frameWidth)/2, (int)frameWidth, (int)frameHeight);
		
	
			JFileChooser fileChooser = new JFileChooser();
			//fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//不能加
			FileNameExtensionFilter filter = new FileNameExtensionFilter("*.xls","*.xlsx");//建立过滤器    
			fileChooser.setFileFilter(filter);//开始过滤    
			System.out.println("+++++++++++addFileChooser");
			this.add(fileChooser);
			
			fileChooser.setSelectedFile(new File(name));
			int returnVal = fileChooser.showSaveDialog(this);
			
			if(returnVal == JFileChooser.APPROVE_OPTION) {    
			 File file = fileChooser.getSelectedFile();
			/* if (file.exists()) {
			 int copy = JOptionPane.showConfirmDialog(null,"是否要覆盖当前文件？", "保存", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			     if (copy == JOptionPane.YES_OPTION){
			    	 fileChooser.approveSelection();
			     }
			 }
			 else
				 fileChooser.approveSelection();
			}*/
			//File file = new File("C:/Users/Administrator/Desktop/"+name);
			 try {
				 String path = file.getPath();
				if(!path.contains(".xls")&&!path.contains(".xlsx"))
					path+=".xls";
				
				FileOutputStream fileOut = new FileOutputStream(path);
				wb.write(fileOut);  
				
				fileOut.close();
			//}
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}finally
		{			
			this.setVisible(false);
		}
		}else {
			result = false;
		}
		return result;
		
	}

	public HSSFWorkbook getWb() {
		return wb;
	}

	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;
	}
	



}
