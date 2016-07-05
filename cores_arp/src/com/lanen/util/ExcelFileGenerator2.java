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

public class ExcelFileGenerator2 {

	private final int SPLIT_COUNT = 99999999; //Excel每个工作簿的行数

	private ArrayList<Object> fieldName = null; //excel标题数据集

	private ArrayList<Object> fieldData = null; //excel数据内容	

	private XSSFWorkbook workBook = null;
	
	private String kind="";//检疫类别标识.

	/**
	 * 构造器
	 * @param fieldName 结果集的字段名
	 * @param data
	 */
	public ExcelFileGenerator2(ArrayList<Object> fieldName, ArrayList<Object> fieldData,String kind) {

		this.fieldName = fieldName;
		this.fieldData = fieldData;
		this.kind=kind;
	}

	/**
	 * 创建HSSFWorkbook对象
	 * @return HSSFWorkbook
	 */
	public XSSFWorkbook createWorkbook() {

		workBook = new XSSFWorkbook();
		int rows = fieldData.size();
		String sheetName="";
		if("virus".equals(kind)){
			sheetName="病毒检疫";
		}else if("vaccine".equals(kind)){
			sheetName="疫苗检疫";
		}else if("bacteria".equals(kind)){
			sheetName="细菌检疫";
		}else if("parasite".equals(kind)){
			sheetName="寄生虫检疫";
		}else if("qc".equals(kind)){
			sheetName="驱虫检疫";
		}else if("tb".equals(kind)){
			sheetName="TB检疫";
		}else if("xcg".equals(kind)){
			sheetName="血常规检疫";
		}else if("xysh".equals(kind)){
			sheetName="血生化检疫";
		}else if("infectious".equals(kind)){
			sheetName="传染病检疫";
		}else if("x".equals(kind)){
			sheetName="X光检疫";
		}else{
			sheetName="体表检疫";
		}
		XSSFSheet sheet = workBook.createSheet(sheetName);
		XSSFRow headRow = sheet.createRow((short) 0);
		ArrayList fieldNameList = (ArrayList) fieldName.get(0);
		for (int j = 0; j < fieldNameList.size(); j++) {
			XSSFCell cell = headRow.createCell((short) j);
			// 添加样式
			cell.setCellType(XSSFCell.CELL_TYPE_STRING);
			// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			// 添加样式
			// 设置所有单元格的宽度
			sheet.setColumnWidth((short) j, (short) 3600);
			// 创建样式(使用工作本的对象创建)
			XSSFCellStyle cellStyle = workBook.createCellStyle();
			// 创建字体的对象
			XSSFFont font = workBook.createFont();
			// 将字体加粗
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			// 设置字体的颜色
			// short color = HSSFColor.RED.index;
			// font.setColor(color);
			font.setColor(new XSSFColor(Color.blue));
			// 将新设置的字体属性放置到样式中
			cellStyle.setFont(font);
			if (fieldNameList.get(j) != null) {
				cell.setCellStyle(cellStyle);
				cell.setCellValue((String) fieldNameList.get(j));
			} else {
				cell.setCellStyle(cellStyle);
				cell.setCellValue("-");
			}
		}

		for (int k = 0; k < (rows < SPLIT_COUNT ? rows : SPLIT_COUNT); k++) {
			XSSFRow row = sheet.createRow((short) (k + 1));
			// 将数据内容放入excel单元格
			ArrayList rowList = (ArrayList) fieldData.get(k);
			for (int n = 0; n < rowList.size(); n++) {
				XSSFCell cell = row.createCell((short) n);
				// cell.setEncoding(HSSFCell.ENCODING_UTF_16); 2007后编码自动转换.
				if (rowList.get(n) != null) {
					cell.setCellValue((String) rowList.get(n).toString());
				} else {
					cell.setCellValue("");
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
