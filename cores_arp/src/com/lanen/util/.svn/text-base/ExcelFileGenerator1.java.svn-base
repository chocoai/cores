/**
 * 系统数据导出Excel 生成器
 * @version 1.0
 */
package com.lanen.util;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileGenerator1 {

	private final int SPLIT_COUNT = 99999999; //Excel每个工作簿的行数

	private ArrayList<Object> fieldName = null; //excel标题数据集

	private ArrayList<Object> fieldData = null; //excel数据内容	

	private XSSFWorkbook workBook = null;

	/**
	 * 构造器
	 * @param fieldName 结果集的字段名
	 * @param data
	 */
	public ExcelFileGenerator1(ArrayList<Object> fieldName, ArrayList<Object> fieldData) {

		this.fieldName = fieldName;
		this.fieldData = fieldData;
	}

	/**
	 * 创建HSSFWorkbook对象
	 * @return HSSFWorkbook
	 */
	public XSSFWorkbook createWorkbook() {

		workBook = new XSSFWorkbook();
		int rows = fieldData.size();
		
		for (int i = 0; i < fieldName.size(); i++) {
			ArrayList<Object> a=(ArrayList<Object>) fieldName.get(i);
			//String sheetName=a.get(0)+"";
			//HSSFSheet sheet = workBook.createSheet("Page " + i);跟据检疫项目名为sheetname;
			String sheetName=null;
			if(i==0){
				sheetName="疫苗检疫";
			}else if(i==1){
				sheetName="驱虫检疫";
			}else if(i==2){
				sheetName="细菌检疫";
			}else if(i==3){
				sheetName="病毒检疫";
			}else if(i==4){
				sheetName="血常规检疫";
			}else if(i==5){
				sheetName="血液生化检疫";
			}else if(i==6){
				sheetName="体内寄生虫检疫";
			}else if(i==7){
				sheetName="体外寄生虫检疫";
			}else if(i==8){
				sheetName="TB检疫";
			}else if(i==9){
				sheetName="传染病检疫";
			}else if(i==10){
				sheetName="X光检疫";
			}else{
				sheetName="体表检疫";
			}
			XSSFSheet sheet = workBook.createSheet(sheetName);
			//sheet.setColumnHidden(0, true);//隐藏检疫项目标识列.
			XSSFRow headRow = sheet.createRow((short) 0);
			ArrayList fieldNameList = (ArrayList) fieldName.get(i);
			for (int j = 0; j < fieldNameList.size(); j++) {
				XSSFCell cell = headRow.createCell((short) j);
				//添加样式
				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
				//cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				//添加样式
				//设置所有单元格的宽度
				sheet.setColumnWidth((short)j, (short)2800);
				//创建样式(使用工作本的对象创建)
				XSSFCellStyle cellStyle = workBook.createCellStyle();
				//创建字体的对象
				XSSFFont font = workBook.createFont();
				//将字体加粗
				font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
				//设置字体的颜色
				//short color = HSSFColor.RED.index;
				//font.setColor(color);
				font.setColor(new XSSFColor(Color.blue));
				//将新设置的字体属性放置到样式中
				cellStyle.setFont(font);
				if(fieldNameList.get(j) != null){
					cell.setCellStyle(cellStyle);
					cell.setCellValue((String) fieldNameList.get(j));
				}else{
					cell.setCellStyle(cellStyle);
					cell.setCellValue("-");
				}
			}

			for (int k = 0; k < (rows < SPLIT_COUNT ? rows : SPLIT_COUNT); k++) {
				XSSFRow row = sheet.createRow((short) (k + 1));
				//将数据内容放入excel单元格
				ArrayList rowList = (ArrayList) fieldData.get(k);
				for (int n = 0; n < rowList.size(); n++) {
					XSSFCell cell = row.createCell((short) n);
					//cell.setEncoding(HSSFCell.ENCODING_UTF_16); 2007后编码自动转换.
					if(rowList.get(n) != null){
						cell.setCellValue((String) rowList.get(n).toString());
					}else{
						cell.setCellValue("");
					}
				}
			}
		}
		return workBook;
	}

	public void expordExcel(OutputStream os) throws Exception {
		workBook = createWorkbook();
		workBook.write(os);
		os.close();
	}

	 public  InputStream getInputStream() throws Exception{
		 
		 InputStream  excelStream ;
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        workBook = createWorkbook();
	        workBook.write(baos); 
	        baos.flush(); 
	        byte[] aa = baos.toByteArray();
	        excelStream = new ByteArrayInputStream(aa, 0, aa.length);
	        baos.close();
	        return excelStream;
	 }
}
