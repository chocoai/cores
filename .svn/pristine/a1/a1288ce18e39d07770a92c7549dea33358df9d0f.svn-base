package com.lanen.util;


import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lanen.jsonAndModel.Json;



public class POIExcelUtil extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HSSFWorkbook wb;
	HSSFSheet sheet;
	List<String> structure = new ArrayList<String>();
	Json json ;
	
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

	public List<List<String>> excel2007Export(File excelCodeFile,List<String> titleList,List<Integer> sheetIndexList) throws IOException 
	{
		json = new Json();
		
		List<List<String>> mapList = new ArrayList<List<String>>();
		//2007版读取方法
		Workbook workbook =null;
		String path=excelCodeFile.getAbsolutePath();//获取文件的路径
		
		long d = System.currentTimeMillis();
		workbook = new XSSFWorkbook(path);
		long d1 = System.currentTimeMillis();
		System.out.println(d+"--"+d1+"--"+(d1-d)/1000);
		String sheetName = "";
		for(int sheetI=0;sheetI<sheetIndexList.size();sheetI++)
		{
			Integer sheetIndex = sheetIndexList.get(sheetI);
			
			if(workbook.getNumberOfSheets()>sheetIndex){//sheet
				if(null!=workbook.getSheetAt(sheetIndex)){
					//获取第一个sheet
					XSSFSheet aSheet = (XSSFSheet) workbook.getSheetAt(sheetIndex);
					sheetName = aSheet.getSheetName();
					if(aSheet.getLastRowNum()>1){
						int lastCellNum = 0;
						for(int rowNumOfSheet =0;rowNumOfSheet <=aSheet.getLastRowNum();rowNumOfSheet++){
							//进入aSheet的行(row)的循环
							if(null!= aSheet.getRow(rowNumOfSheet)){
								XSSFRow aRow =aSheet.getRow(rowNumOfSheet);
								if(rowNumOfSheet ==0){//表头
									lastCellNum =aRow.getLastCellNum();
									if(lastCellNum>=titleList.size()){
										boolean flag = true;
										for(int i=0;i<lastCellNum;i++)
										{
											XSSFCell aCell = aRow.getCell(i);
											if(null!=aCell){
												aCell.setCellType(XSSFCell.CELL_TYPE_STRING);
												if(!aCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals(titleList.get(i).trim())){
													json.setMsg(json.getMsg()+" 请检查 "+sheetName+" 的表头："+titleList.get(i).trim());
													flag=false;
													break;
												}
											}else{
												/*json.setMsg("表头不能为空");
												flag=false;
												break;	*/
											}
										}
										if(!flag)
										{
											break;
										}
										
									}else{
										//表头不一样也没关系，只要前面的符合就行了
										json.setMsg(json.getMsg()+" "+sheetName+" 表头数量小于规定，请按照要求上传文件！");
										break;
									}
								}else{
									//数据行
									List<String> rowCols = new ArrayList<String>();
									
									//按表头的列数来读取数据行
								//	int lastCellNum =aRow.getLastCellNum();
									for(int i=0;i<lastCellNum;i++)
									{
										XSSFCell aCell = aRow.getCell(i);
										if(null!=aCell){
											aCell.setCellType(XSSFCell.CELL_TYPE_STRING);
											String col = "";
											if(aCell.getStringCellValue()!=null)
											{
												col =aCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
											}
											rowCols.add(col);
										}else{
											//json.setMsg("动物Id号不能为空");
											//break;
											rowCols.add("");
										}
										
									}
									mapList.add(rowCols);
								}
							}else{//end 当前行不为空
								if(rowNumOfSheet ==0){
									json.setMsg(json.getMsg()+" "+sheetName+" 请检查表头,表头行不能为空！");
									break;
								}
							}
						}//end aSheet的行的循环
					}else{
						json.setMsg(json.getMsg()+" "+sheetName+" excel数据为空");
					}
				}else{//end   获取第一个sheet是否为空
					json.setMsg(json.getMsg()+" "+ "第 "+sheetIndex+" 个excel为空");
				}
			}else{ //end sheets  >0
				json.setMsg("excel文件为空");
			}
			
		}//for end
		return mapList;
	}
	
	public List<List<String>> excel2003Export(File excelCodeFile,List<String> titleList,List<Integer> sheetIndexList)
	{
		json = new Json();
		
		List<List<String>> mapList = new ArrayList<List<String>>();
		//2003版读取方法
		Workbook workbook =null;
		String path=excelCodeFile.getAbsolutePath();//获取文件的路径
		
		//下面使用的是2003（workbook的赋值不同，其他与2007基本相同）
		InputStream is = null;
		try{
			is=	new FileInputStream(path);
			workbook = new HSSFWorkbook(is);
			for(int sheetI=0;sheetI<sheetIndexList.size();sheetI++)
			{
				Integer sheetIndex = sheetIndexList.get(sheetI);
				if(workbook.getNumberOfSheets()>sheetIndex){//sheet
					if(null!=workbook.getSheetAt(sheetIndex)){
						//获取第一个sheet
						HSSFSheet aSheet = (HSSFSheet) workbook.getSheetAt(sheetIndex);
						if(aSheet.getLastRowNum()>1){
							for(int rowNumOfSheet =0;rowNumOfSheet <=aSheet.getLastRowNum();rowNumOfSheet++){
								//进入aSheet的行(row)的循环
								if(null!= aSheet.getRow(rowNumOfSheet)){
									HSSFRow aRow =aSheet.getRow(rowNumOfSheet);
									if(rowNumOfSheet ==0){//表头
										int lastCellNum =aRow.getLastCellNum();
										if(lastCellNum>=titleList.size()){
											boolean flag = true;
											for(int i=0;i<=lastCellNum;i++)
											{
												HSSFCell aCell = aRow.getCell(i);
												if(null!=aCell){
													aCell.setCellType(HSSFCell.CELL_TYPE_STRING);
													if(!aCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals(titleList.get(i).trim())){
														json.setMsg("请检查表头");
														flag = false;
														break;
													}
												}else{
													/*json.setMsg("表头不能为空");
													flag = false;
													break;*/
												}
											}
											if(!flag)
											{
												break;
											}
										}else{
											json.setMsg("表头数量和样本上的不一致！");
											break;
										}
									}else{
										//数据行
										List<String> rowCols = new ArrayList<String>();
										int lastCellNum =aRow.getLastCellNum();
										for(int i=0;i<=lastCellNum;i++)
										{
											HSSFCell aCell = aRow.getCell(i);
										
											if(null!=aCell){//id号为空，则code就不检查了
												aCell.setCellType(HSSFCell.CELL_TYPE_STRING);
												String rowCol =aCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
												rowCols.add(rowCol);
											
											}else{
												//rowCols.add(null);
												//break;
											}
											
										}
										mapList.add(rowCols);
										
									}
								}else{//end 当前行不为空
									if(rowNumOfSheet ==0){
										json.setMsg("请检查表头，表头行为空");
										break;
									}
								}
							}//end aSheet的行的循环
						}else{
							json.setMsg("excel数据为空");
						}
					}else{//end   获取第一个sheet是否为空
						json.setMsg("excel为空");
					}
				}else{ //end sheets  >0
					json.setMsg("excel为空");
				}
				
			}//for结束
		}catch(Exception ex){
			json.setMsg("文件读取失败");
		}finally{
			if(is!=null)
				try {
					is.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}
		
		return mapList;
	}
		
	public HSSFWorkbook getWb() {
		return wb;
	}

	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;
	}

	public Json getJson() {
		return json;
	}

	public void setJson(Json json) {
		this.json = json;
	}
	



}
