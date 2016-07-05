/**
 * 系统数据导出Excel 生成器
 * @version 1.0
 */
package com.lanen.util;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import com.lanen.model.Bacteria;
import com.lanen.model.Individual_Json;
import com.lanen.model.Parasite;
import com.lanen.model.Vaccine;
import com.lanen.model.Virus;
import com.lanen.model.Weight;
import com.lowagie.text.Cell;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfParagraphStyle;


public class WordFileGenerator1 {
	
	


	private  WordUtils wordUtils  = null;
	
	private Individual_Json individual = null; //word猴子个体数据内容	

	private List<Weight> w;
	
	private Virus virus;
	
	private List<Vaccine> v;
	
	private List<Bacteria> bacteria;
	
	private List<Parasite> parasite;
	/**
	 * 构造器
	 * @param fieldName 结果集的字段名
	 * @param data
	 */
	public WordFileGenerator1(Individual_Json individual,List<Weight> w,Virus virus,List<Vaccine> v,List<Bacteria> bacteria,List<Parasite> parasite) {
		this.individual = individual;
		this.w=w;
		this.virus=virus;
		this.v=v;
		this.bacteria=bacteria;
		this.parasite=parasite;
	}

	
	

	public InputStream createWorkbook() throws Exception{
		  wordUtils  = new WordUtils();
	        ByteArrayOutputStream ba = new ByteArrayOutputStream();
	        try{
	          RtfWriter2 writer = RtfWriter2.getInstance(wordUtils.getDocument(), ba);
	          wordUtils.openDocument();
	          //document.open();

	          //第一级标题样式
	          RtfParagraphStyle rtfGsBt1 = RtfParagraphStyle.STYLE_HEADING_1;
	          rtfGsBt1.setAlignment(Element.ALIGN_LEFT);
	          rtfGsBt1.setStyle(Font.BOLD);
	          rtfGsBt1.setSize(15);

	          //第二级标题样式
	          RtfParagraphStyle rtfGsBt2 = RtfParagraphStyle.STYLE_HEADING_2;
	          rtfGsBt2.setAlignment(Element.ALIGN_LEFT);
	          rtfGsBt2.setStyle(Font.BOLD);
	          rtfGsBt2.setSize(13);
	          String animaltypeName = individual.getAnimaltypeName();
	          String type = animaltypeName;
	          String en = "";
	          if( "恒河猴".equals(animaltypeName)){
	        	  //type = animaltypeName;
	        	  en = "RHESUS";
	          }else if("食蟹猴".equals(animaltypeName)){
	        	 // type = animaltypeName;
	        	  en = "CYNOMOLGUS";
	          }
	          wordUtils.insertTitle(type +"个体报告", 17, Font.BOLD, Element.ALIGN_CENTER);
	          wordUtils.insertTitle("THE FILES OF "+ en +"MONKEY", 12, Font.BOLD, Element.ALIGN_CENTER);
	          
	          Table table = new Table(6);
	          int width[] = {20, 20, 20, 20, 20, 20};
	          table.setWidths(width);//设置系列所占比例
	          table.setWidth(100);
	          table.setAutoFillEmptyCells(true);
	          table.setAlignment(Element.ALIGN_CENTER);//居中显示
	          table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中显示
	          table.setBorder(1000);
	          table.setBorderWidth(1);//边框宽度
	          //table.setSpacing(2);
	          //table.setPadding(3);
	          table.setBorderColor(new Color(205,205,205));//边框颜色
              /**第一行 开始**/
	          Cell cell01 = new Cell(new Phrase("动物编号/NO"));
	          cell01.setColspan(1);//设置当前单元格占据的列数
	          cell01.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell01.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell01.setBorderColor(new Color(205,205,205));
	         // cell.setBackgroundColor(new Color(58, 137, 20));
	          table.addCell(cell01);
	          
	          Cell cell02 = new Cell(new Phrase(individual.getMonkeyid()));
	          cell02.setColspan(1);//设置当前单元格占据的列数
	          cell02.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell02.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell02.setBorderColor(new Color(205,205,205));
	         // cell.setBackgroundColor(new Color(58, 137, 20));
	          table.addCell(cell02);
	          
	         
	          Cell cell03 = new Cell(new Phrase("性别/Sex"));
	          cell03.setColspan(1);//设置当前单元格占据的列数
	          cell03.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell03.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell03.setBorderColor(new Color(205,205,205));
	         // cell.setBackgroundColor(new Color(58, 137, 20));
	          table.addCell(cell03);
	          String sex = "";
	          if(individual.getSex() == 0){
	        	  sex = "公";
	          }else{
	        	  sex = "母";
	          }
             
	          Cell cell04 = new Cell(new Phrase(sex));
	          cell04.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell04.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell04.setBorderColor(new Color(205,205,205));
	          // cell2.setBackgroundColor(new Color(137, 34, 44));
	          table.addCell(cell04);

	          Cell cell05 = new Cell(new Phrase("体重/BW(kg)"));
	          cell05.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell05.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell05.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell05);
	          
	          Cell cell06 = new Cell(new Phrase(individual.getCurrentweight()+""));
	          cell06.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell06.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell06.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell06);
	          /**第一行 结束**/
	          /**第二行开始*/
	          Cell cell21 = new Cell(new Phrase("出生日期/Date Of Birth"));
	          cell21.setColspan(2);//设置当前单元格占据的列数
	          cell21.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell21.setBorderColor(new Color(205,205,205));
	         // cell.setBackgroundColor(new Color(58, 137, 20));
	          table.addCell(cell21);
	          
	          Cell cell22 = new Cell(new Phrase(individual.getBirthday()));
	          cell22.setColspan(1);//设置当前单元格占据的列数
	          cell22.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell22.setBorderColor(new Color(205,205,205));
	         // cell.setBackgroundColor(new Color(58, 137, 20));
	          table.addCell(cell22);
	          
	         
	          Cell cell23 = new Cell(new Phrase("断奶日期/Date of weaning"));
	          cell23.setColspan(2);//设置当前单元格占据的列数
	          cell23.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell23.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell23.setBorderColor(new Color(205,205,205));
	         // cell.setBackgroundColor(new Color(58, 137, 20));
	          table.addCell(cell23);
	         
	          Cell cell24 = new Cell(new Phrase(individual.getLeavebreastdate()));
	          cell24.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell24.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell24.setBorderColor(new Color(205,205,205));
	          // cell2.setBackgroundColor(new Color(137, 34, 44));
	          table.addCell(cell24);
	          /**第二行结束*/
	          /**第三行开始*/
	          Cell cell30 = new Cell(new Phrase("父号/Sire"));
	          cell30.setColspan(2);//设置当前单元格占据的列数
	          cell30.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell30.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell30);
	          
	          Cell cell31 = new Cell(new Phrase(individual.getFatherid()));
	          cell31.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell31.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell31.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell31);
	          
	          Cell cell32 = new Cell(new Phrase("母号/Dam"));
	          cell32.setColspan(2);//设置当前单元格占据的列数
	          cell32.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell32.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell32);
	          
	          Cell cell33 = new Cell(new Phrase(individual.getMotherid()));
	          cell33.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell33.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell33);
	          /**第三行结束*/
	          /**第四行开始*/
	          Cell cell40 = new Cell(new Phrase("称重日期/Weighting Date(y/m/d)"));
	          cell40.setColspan(2);//设置当前单元格占据的列数
	          cell40.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell40.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell40.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell40);
	          for(Weight weight:w){
	        	  Cell cell4=new Cell(new Phrase(weight.getWeightdate()));
	        	  cell4.setColspan(1);
	        	  cell4.setVerticalAlignment(Element.ALIGN_CENTER);
	        	  cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	  cell4.setBorderColor(new Color(205,205,205));
	        	  table.addCell(cell4);
	          }
	          /*Cell cell41 = new Cell(new Phrase("日期1(后续)"));
	          cell41.setColspan(1);//设置当前单元格占据的列数
	          cell41.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell41.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell41.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell41);
	          
	          Cell cell42 = new Cell(new Phrase("日期2(后续)"));
	          cell42.setColspan(1);//设置当前单元格占据的列数
	          cell42.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell42.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell42.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell42);
	          
	          Cell cell43 = new Cell(new Phrase("日期3(后续)"));
	          cell43.setColspan(1);//设置当前单元格占据的列数
	          cell43.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell43.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell43.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell43);
	          
	          Cell cell44 = new Cell(new Phrase("日期4(后续)"));
	          cell44.setColspan(1);//设置当前单元格占据的列数
	          cell44.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell44.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell44.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell44);*/
	          
	          
	          /**第四行结束*/
	          
	          /**第五行开始*/
	          Cell cell50 = new Cell(new Phrase("体重/Growth Weight(kg)"));
	          cell50.setColspan(2);//设置当前单元格占据的列数
	          cell50.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell50.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell50.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell50);
	          for(Weight weight:w){
	        	  Cell cell5=new Cell(new Phrase(weight.getWeight()+""));
	        	  cell5.setColspan(1);
	        	  cell5.setVerticalAlignment(Element.ALIGN_CENTER);
	        	  cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	  cell5.setBorderColor(new Color(205,205,205));
	        	  table.addCell(cell5);
	          }
	          
	          /**第四行结束*/
	          
	          /**第六行开始*/
	          Cell cell60 = new Cell(new Phrase("日期/Date y/m/d"));
	          cell60.setColspan(1);//设置当前单元格占据的列数
	          cell60.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell60.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell60.setBorderColor(new Color(205,205,205));
	         // cell60.setBackgroundColor(new Color(227, 227, 227));
	          table.addCell(cell60);
	          Cell cell61 = new Cell(new Phrase("yy-mm-dd"));
	          cell61.setColspan(1);//设置当前单元格占据的列数
	          cell61.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell61.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell61.setBorderColor(new Color(205,205,205));
	          //cell61.setBackgroundColor(new Color(227, 227, 227));
	          table.addCell(cell61);
	          
	          Cell cell62 = new Cell(new Phrase("病毒检查/The Results of Virus Examinations"));
	          cell62.setColspan(4);//设置当前单元格占据的列数
	          cell62.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell62.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell62.setBorderColor(new Color(205,205,205));
	          cell62.setBackgroundColor(new Color(227, 227, 227));
	          table.addCell(cell62);
	          
	          
	          /**第六行结束*/
	          
	          /**第七行开始*/
	          Cell cell70 = new Cell(new Phrase("项目/Items"));
	          cell70.setColspan(1);//设置当前单元格占据的列数
	          cell70.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell70.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell70.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell70);
	          
	          Cell cell71 = new Cell(new Phrase("BV"));
	          cell71.setColspan(1);//设置当前单元格占据的列数
	          cell71.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell71.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell71.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell71);
	          
	          Cell cell72 = new Cell(new Phrase("STLV-1"));
	          cell72.setColspan(1);//设置当前单元格占据的列数
	          cell72.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell72.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell72.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell72);
	          
	          Cell cell73 = new Cell(new Phrase("SIV"));
	          cell73.setColspan(1);//设置当前单元格占据的列数
	          cell73.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell73.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell73.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell73);
	          
	          Cell cell74 = new Cell(new Phrase("SRV"));
	          cell74.setColspan(1);//设置当前单元格占据的列数
	          cell74.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell74.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell74.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell74);
	          
	          Cell cell75 = new Cell(new Phrase("FiLoverus"));
	          cell75.setColspan(1);//设置当前单元格占据的列数
	          cell75.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell75.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell75.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell75);
	          
	          /**第七行结束*/
	          
	          
	          /**第八行开始*/
	          Cell cell80 = new Cell(new Phrase("方法/Method"));
	          cell80.setColspan(1);//设置当前单元格占据的列数
	          cell80.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell80.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell80.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell80);
	          
	          Cell cell81 = new Cell(new Phrase("酶联免疫/DIA"));
	          cell81.setColspan(1);//设置当前单元格占据的列数
	          cell81.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell81.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell81.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell81);
	          
	          Cell cell82 = new Cell(new Phrase("酶联免疫/DIA"));
	          cell82.setColspan(1);//设置当前单元格占据的列数
	          cell82.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell82.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell82.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell82);
	          
	          Cell cell83 = new Cell(new Phrase("酶联免疫/DIA"));
	          cell83.setColspan(1);//设置当前单元格占据的列数
	          cell83.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell83.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell83.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell83);
	          
	          Cell cell84 = new Cell(new Phrase("酶联免疫/DIA"));
	          cell84.setColspan(1);//设置当前单元格占据的列数
	          cell84.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell84.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell84.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell84);
	          
	          Cell cell85 = new Cell(new Phrase("酶联免疫/DIA"));
	          cell85.setColspan(1);//设置当前单元格占据的列数
	          cell85.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell85.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell85.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell85);
	          /**第九行开始*/
	          Cell cell90 = new Cell(new Phrase(""));
	          cell90.setColspan(1);//设置当前单元格占据的列数
	          cell90.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell90.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell90.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell90);
	        
	          Cell cell91 = new Cell(new Phrase(virus.getBv()));
	          cell91.setColspan(1);//设置当前单元格占据的列数
	          cell91.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell91.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell91.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell91);
	          
	          Cell cell92 = new Cell(new Phrase(virus.getStlv()));
	          cell92.setColspan(1);//设置当前单元格占据的列数
	          cell92.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell92.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell92.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell92);
	          
	          Cell cell93 = new Cell(new Phrase(virus.getSiv()));
	          cell93.setColspan(1);//设置当前单元格占据的列数
	          cell93.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell93.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell93.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell93);
	          
	          Cell cell94 = new Cell(new Phrase(virus.getSrv()));
	          cell94.setColspan(1);//设置当前单元格占据的列数
	          cell94.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell94.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell94.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell94);
	          
	          Cell cell95 = new Cell(new Phrase(virus.getFilo()));
	          cell95.setColspan(1);//设置当前单元格占据的列数
	          cell95.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell95.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell95.setBorderColor(new Color(205,205,205));
	          // cell3.setBackgroundColor(new Color(232, 219, 48));
	          table.addCell(cell95);
	          
	          /**第九行结束*/
	          /**第十行开始*/
	          
	          Cell cell10 = new Cell(new Phrase("日期/Date y/m/d"));
	          cell10.setBorderColor(new Color(205,205,205));
	         // cell10.setBackgroundColor(new Color(227, 227, 227));
	          cell10.setRowspan(1);
	          table.addCell(cell10);
	          
	          Cell cell101 = new Cell(new Phrase("yyyy-mm-dd"));
	          cell101.setColspan(1);//设置当前单元格占据的列数
	          cell101.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell101.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell101.setBorderColor(new Color(205,205,205));
	          //cell101.setBackgroundColor(new Color(227, 227, 227));
	          table.addCell(cell101);
	          
	          Cell cell11 = new Cell(new Phrase("疫苗接种/Vaccinations"));
	          cell11.setColspan(4);//设置当前单元格占据的列数
	          cell11.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell11.setBorderColor(new Color(205,205,205));
	          cell11.setBackgroundColor(new Color(227, 227, 227));
	          table.addCell(cell11);
	          /**第十行结束*/
	          /**第十一行开始*/
	          Cell cell111 = new Cell(new Phrase("项目/Items"));
	          cell111.setColspan(1);//设置当前单元格占据的列数
	          cell111.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell111.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell111.setBorderColor(new Color(205,205,205));
	          table.addCell(cell111);
	          
	          Cell cell112 = new Cell(new Phrase("甲肝/Hepatitis"));
	          cell112.setColspan(1);//设置当前单元格占据的列数
	          cell112.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell112.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell112.setBorderColor(new Color(205,205,205));
	          table.addCell(cell112);
	          
	          Cell cell113 = new Cell(new Phrase("麻疹/Measles"));
	          cell113.setColspan(1);//设置当前单元格占据的列数
	          cell113.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell113.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell113.setBorderColor(new Color(205,205,205));
	          table.addCell(cell113);
	          
	          Cell cell114 = new Cell(new Phrase("狂犬/Rabies"));
	          cell114.setColspan(1);//设置当前单元格占据的列数
	          cell114.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell114.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell114.setBorderColor(new Color(205,205,205));
	          table.addCell(cell114);
	          
	          Cell cell115= new Cell(new Phrase(""));
	          cell115.setColspan(2);//设置当前单元格占据的列数
	          cell115.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell115.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell115.setBorderColor(new Color(205,205,205));
	          table.addCell(cell115);
	          
	          /**第十一行结束*/
	          /**第十二行开始*/
	          
	          Cell cell121 = new Cell(new Phrase(""));
	          cell121.setColspan(1);//设置当前单元格占据的列数
	          cell121.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell121.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell121.setBorderColor(new Color(205,205,205));
	          table.addCell(cell121);
	          for(Vaccine vc:v){
	        	  Cell cell12=null;
	        	  if("Measles".equals(vc.getQ_id())){
	        		  cell12=new Cell(new Phrase(vc.getYpin()));
	        		  cell12.setVerticalAlignment(Element.ALIGN_CENTER);
	        		  cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		  cell12.setColspan(1);
	        		  cell12.setBorderColor(new Color(205,205,205));
	        		  table.addCell(cell12);
	        	  }
	        	  if("HepatitisA".equals(vc.getQ_id())){
	        		  cell12=new Cell(new Phrase(vc.getYpin()));
	        		  cell12.setVerticalAlignment(Element.ALIGN_CENTER);
	        		  cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		  cell12.setColspan(1);
	        		  cell12.setBorderColor(new Color(205,205,205));
	        		  table.addCell(cell12);
	        	  }
	        	  if("HepatitisB".equals(vc.getQ_id())){
	        		  cell12=new Cell(new Phrase(vc.getYpin()));
	        		  cell12.setVerticalAlignment(Element.ALIGN_CENTER);
	        		  cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		  cell12.setColspan(1);
	        		  cell12.setBorderColor(new Color(205,205,205));
	        		  table.addCell(cell12);
	        	  }
	          }
	          /*Cell cell122 = new Cell(new Phrase("(ND)"));
	          cell122.setColspan(1);//设置当前单元格占据的列数
	          cell122.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell122.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell122.setBorderColor(new Color(205,205,205));
	          table.addCell(cell122);
	          
	          Cell cell123 = new Cell(new Phrase("(D)"));
	          cell123.setColspan(1);//设置当前单元格占据的列数
	          cell123.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell123.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell123.setBorderColor(new Color(205,205,205));
	          table.addCell(cell123);
	          
	          Cell cell124 = new Cell(new Phrase("(ND)"));
	          cell124.setColspan(1);//设置当前单元格占据的列数
	          cell124.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell124.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell124.setBorderColor(new Color(205,205,205));
	          table.addCell(cell124);*/
	          
	          
	          Cell cell125= new Cell(new Phrase(""));
	          cell125.setColspan(2);//设置当前单元格占据的列数
	          cell125.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell125.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell125.setBorderColor(new Color(205,205,205));
	          table.addCell(cell125);
	          /**第十二行结束*/
	          
	          /**第十三行开始*/
	          Cell cell131 = new Cell(new Phrase("日期/Date y/m/d"));
	          cell131.setBorderColor(new Color(205,205,205));
	          cell131.setRowspan(3);
	          table.addCell(cell131);
	          
	          Cell cell132 = new Cell(new Phrase("细菌检测与治疗/Bacteria Examination"));
	          cell132.setColspan(5);//设置当前单元格占据的列数
	          cell132.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell132.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell132.setBorderColor(new Color(205,205,205));
	          cell132.setBackgroundColor(new Color(227, 227, 227));
	          table.addCell(cell132);
	          /**第十三行结束*/
	          
              /**第十四行开始*/
	          
	          Cell cell141 = new Cell(new Phrase("项目/Items"));
	          cell141.setColspan(1);//设置当前单元格占据的列数
	          cell141.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell141.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell141.setBorderColor(new Color(205,205,205));
	          table.addCell(cell141);
	          
	          Cell cell142 = new Cell(new Phrase("耶尔森氏菌/Yers"));
	          cell142.setColspan(1);//设置当前单元格占据的列数
	          cell142.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell142.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell142.setBorderColor(new Color(205,205,205));
	          table.addCell(cell142);
	          
	          Cell cell143 = new Cell(new Phrase("志贺/Shigella"));
	          cell143.setColspan(1);//设置当前单元格占据的列数
	          cell143.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell143.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell143.setBorderColor(new Color(205,205,205));
	          table.addCell(cell143);
	          
	          Cell cell144 = new Cell(new Phrase("沙门/Salmonella"));
	          cell144.setColspan(1);//设置当前单元格占据的列数
	          cell144.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell144.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell144.setBorderColor(new Color(205,205,205));
	          table.addCell(cell144);
	          
	          
	          Cell cell145= new Cell(new Phrase("治疗/Treatments"));
	          cell145.setRowspan(2);
	          cell145.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell145.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell145.setBorderColor(new Color(205,205,205));
	          table.addCell(cell145);
	          /**第十四行结束*/
	          
              /**第十五行开始*/
	          Cell cell151 = new Cell(new Phrase("方法/Method"));
	          cell151.setColspan(1);//设置当前单元格占据的列数
	          cell151.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell151.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell151.setBorderColor(new Color(205,205,205));
	          table.addCell(cell151);
	          
	          Cell cell152 = new Cell(new Phrase("OT 0.1ml/2500IU"));
	          cell152.setColspan(1);//设置当前单元格占据的列数
	          cell152.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell152.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell152.setBorderColor(new Color(205,205,205));
	          table.addCell(cell152);
	          
	          Cell cell153 = new Cell(new Phrase("S.S+DHL"));
	          cell153.setColspan(1);//设置当前单元格占据的列数
	          cell153.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell153.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell153.setBorderColor(new Color(205,205,205));
	          table.addCell(cell153);
	          
	          Cell cell154 = new Cell(new Phrase("S.S+DHL"));
	          cell154.setColspan(1);//设置当前单元格占据的列数
	          cell154.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell154.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell154.setBorderColor(new Color(205,205,205));
	          table.addCell(cell154);
	        
	          /**第十五行结束*/
	          
	          
	          /**第十六行开始*/
	          for(Bacteria b:bacteria){
	        		  
				Cell cell16 = new Cell(new Phrase(b.getCdate() + ""));
				cell16.setColspan(2);// 设置当前单元格占据的列数
				cell16.setVerticalAlignment(Element.ALIGN_CENTER);
				cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell16.setBorderColor(new Color(205, 205, 205));
				table.addCell(cell16);
					
	        		  Cell cell162 = new Cell(new Phrase(b.getYers()));
			          cell162.setColspan(1);//设置当前单元格占据的列数
			          cell162.setVerticalAlignment(Element.ALIGN_CENTER);
			          cell162.setHorizontalAlignment(Element.ALIGN_CENTER);
			          cell162.setBorderColor(new Color(205,205,205));
			          table.addCell(cell162);
			          
			          Cell cell163 = new Cell(new Phrase(b.getShig()));
			          cell163.setColspan(1);//设置当前单元格占据的列数
			          cell163.setVerticalAlignment(Element.ALIGN_CENTER);
			          cell163.setHorizontalAlignment(Element.ALIGN_CENTER);
			          cell163.setBorderColor(new Color(205,205,205));
			          table.addCell(cell163);
			          
			          Cell cell164 = new Cell(new Phrase(b.getSalm()));
			          cell164.setColspan(1);//设置当前单元格占据的列数
			          cell164.setVerticalAlignment(Element.ALIGN_CENTER);
			          cell164.setHorizontalAlignment(Element.ALIGN_CENTER);
			          cell164.setBorderColor(new Color(205,205,205));
			          table.addCell(cell164);
			          
			          Cell cell165 = new Cell(new Phrase("(ND)"));
			          cell165.setColspan(1);//设置当前单元格占据的列数
			          cell165.setVerticalAlignment(Element.ALIGN_CENTER);
			          cell165.setHorizontalAlignment(Element.ALIGN_CENTER);
			          cell165.setBorderColor(new Color(205,205,205));
			          table.addCell(cell165);
	        	  
	          }
	        
	          /**第二十一行结束*/
	          
	          
	          /**第二十二行开始*/
	          Cell cell221 = new Cell(new Phrase("日期/Date y/m/d"));
	          cell221.setBorderColor(new Color(205,205,205));
	          cell221.setRowspan(5);
	          table.addCell(cell221);
	          
	          Cell cell222 = new Cell(new Phrase("寄生虫检测与治疗/Parasites Examinations"));
	          cell222.setColspan(5);//设置当前单元格占据的列数
	          cell222.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell222.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell222.setBorderColor(new Color(205,205,205));
	          cell222.setBackgroundColor(new Color(227, 227, 227));
	          table.addCell(cell222);
	          /**第二十二行结束*/
	          
              /**第二十三行开始*/
	          
	          Cell cell230 = new Cell(new Phrase("项目/Items"));
	          cell230.setColspan(1);//设置当前单元格占据的列数
	          cell230.setRowspan(2);
	          cell230.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell230.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell230.setBorderColor(new Color(205,205,205));
	          table.addCell(cell230);
	          
	          Cell cell231 = new Cell(new Phrase("体外/In Vito - Parasites"));
	          cell231.setColspan(1);//设置当前单元格占据的列数
	          cell231.setRowspan(2);
	          cell231.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell231.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell231.setBorderColor(new Color(205,205,205));
	          table.addCell(cell231);
	          
	          Cell cell232 = new Cell(new Phrase("体内/In vivo - Parasites"));
	          cell232.setColspan(3);//设置当前单元格占据的列数
	          cell232.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell232.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell232.setBorderColor(new Color(205,205,205));
	          table.addCell(cell232);

	          /**第二十三行结束*/
	          Cell cell=new Cell(new Phrase("溶组织内阿米"));
	          cell.setColspan(1);
	          cell.setBorderColor(new Color(205,205,205));
	          cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell.setVerticalAlignment(Element.ALIGN_CENTER);
	          table.addCell(cell);
	          Cell cell1=new Cell(new Phrase("蠕虫"));
	          cell1.setColspan(1);
	          cell1.setBorderColor(new Color(205,205,205));
	          cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell1.setVerticalAlignment(Element.ALIGN_CENTER);
	          table.addCell(cell1);
	          Cell cell2=new Cell(new Phrase("鞭毛虫"));
	          cell2.setColspan(1);
	          cell2.setBorderColor(new Color(205,205,205));
	          cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell2.setVerticalAlignment(Element.ALIGN_CENTER);
	          table.addCell(cell2);
	       
              /**第二十四行开始*/
	          Cell cell240 = new Cell(new Phrase("方法/Method"));
	          cell240.setColspan(1);//设置当前单元格占据的列数
	          cell240.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell240.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell240.setBorderColor(new Color(205,205,205));
	          table.addCell(cell240);
	          
	          Cell cell241 = new Cell(new Phrase("药浴 Medicine Bath"));
	          cell241.setColspan(1);//设置当前单元格占据的列数
	          cell241.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell241.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell241.setBorderColor(new Color(205,205,205));
	          table.addCell(cell241);
	          
	          Cell cell242 = new Cell(new Phrase("驱虫药  Anthrlmintic"));
	          cell242.setColspan(1);//设置当前单元格占据的列数
	          cell242.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell242.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell242.setBorderColor(new Color(205,205,205));
	          table.addCell(cell242);
	          
	          Cell cell243 = new Cell(new Phrase("驱虫药  Anthrlmintic"));
	          cell243.setColspan(1);//设置当前单元格占据的列数
	          cell243.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell243.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell243.setBorderColor(new Color(205,205,205));
	          table.addCell(cell243);
	          
	          Cell cell244 = new Cell(new Phrase("驱虫药  Anthrlmintic"));
	          cell244.setColspan(1);//设置当前单元格占据的列数
	          cell244.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell244.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell244.setBorderColor(new Color(205,205,205));
	          table.addCell(cell244);
	          
	          
	        
	          /**第二十四行结束*/
	          
	          /**第二十五行开始*/
	          Cell cell250 = new Cell(new Phrase("Treatment"));
	          cell250.setColspan(1);//设置当前单元格占据的列数
	          cell250.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell250.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell250.setBorderColor(new Color(205,205,205));
	          table.addCell(cell250);
	          
	          Cell cell251 = new Cell(new Phrase("双甲眯 Amitraz 0.05%"));
	          cell251.setColspan(1);//设置当前单元格占据的列数
	          cell251.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell251.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell251.setBorderColor(new Color(205,205,205));
	          table.addCell(cell251);
	          
	          Cell cell252 = new Cell(new Phrase("依维菌素 Ivermectin "));
	          cell252.setColspan(1);//设置当前单元格占据的列数
	          cell252.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell252.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell252.setBorderColor(new Color(205,205,205));
	          table.addCell(cell252);
	          
	          Cell cell253 = new Cell(new Phrase("阿苯达唑 Albendazole 0.4g/head,PO"));
	          cell253.setColspan(1);//设置当前单元格占据的列数
	          cell253.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell253.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell253.setBorderColor(new Color(205,205,205));
	          table.addCell(cell253);
	          
	          Cell cell254 = new Cell(new Phrase("阿苯达唑 Albendazole 0.4g/head,PO"));
	          cell254.setColspan(1);//设置当前单元格占据的列数
	          cell254.setVerticalAlignment(Element.ALIGN_CENTER);
	          cell254.setHorizontalAlignment(Element.ALIGN_CENTER);
	          cell254.setBorderColor(new Color(205,205,205));
	          table.addCell(cell254);
	        
	          /**第二十五行结束*/
	          
	          
	          
	          /**第二十六行开始*/
	          for(Parasite p:parasite){
	        	  
	        			  Cell cell261 = new Cell(new Phrase(p.getCdate()+""));
	        	          cell261.setColspan(2);//设置当前单元格占据的列数
	        	          cell261.setVerticalAlignment(Element.ALIGN_CENTER);
	        	          cell261.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	          cell261.setBorderColor(new Color(205,205,205));
	        	          table.addCell(cell261);
	        		  
	        		  Cell cell262 = new Cell(new Phrase(p.getTwjsc()));
	    	          cell262.setColspan(1);//设置当前单元格占据的列数
	    	          cell262.setVerticalAlignment(Element.ALIGN_CENTER);
	    	          cell262.setHorizontalAlignment(Element.ALIGN_CENTER);
	    	          cell262.setBorderColor(new Color(205,205,205));
	    	          table.addCell(cell262);
	    	          
	    	          Cell cell263 = new Cell(new Phrase(p.getAmb()));
	    	          cell263.setColspan(1);//设置当前单元格占据的列数
	    	          cell263.setVerticalAlignment(Element.ALIGN_CENTER);
	    	          cell263.setHorizontalAlignment(Element.ALIGN_CENTER);
	    	          cell263.setBorderColor(new Color(205,205,205));
	    	          table.addCell(cell263);
	    	          
	    	          Cell cell264 = new Cell(new Phrase(p.getGxc()));
	    	          cell264.setColspan(1);//设置当前单元格占据的列数
	    	          cell264.setVerticalAlignment(Element.ALIGN_CENTER);
	    	          cell264.setHorizontalAlignment(Element.ALIGN_CENTER);
	    	          cell264.setBorderColor(new Color(205,205,205));
	    	          table.addCell(cell264);
	    	          
	    	          Cell cell265 = new Cell(new Phrase(p.getBmc()));
	    	          cell265.setColspan(1);//设置当前单元格占据的列数
	    	          cell265.setVerticalAlignment(Element.ALIGN_CENTER);
	    	          cell265.setHorizontalAlignment(Element.ALIGN_CENTER);
	    	          cell265.setBorderColor(new Color(205,205,205));
	    	          table.addCell(cell265);
	        	  
	          }          
	        
	          /**第三十一行结束*/
	          
	          
	          wordUtils.getDocument().add(table);
	          wordUtils.insertContext("注/Remark:\"(-)\"：阴性/Negative \"(+)\":阳性/positive \"(ND)\":未做/NO Done \"(D)\":做Done 未检出/Normal",  8, Font.NORMAL, Element.ALIGN_CENTER);

	        }catch(DocumentException de){
	            de.printStackTrace();
	            System.err.println("A Document error:" +de.getMessage());
	          }

	        wordUtils.getDocument().close();

	           byte[] aa = ba.toByteArray();
	           
	  		  InputStream  excelStream ;
		      excelStream = new ByteArrayInputStream(aa, 0, aa.length);
		      ba.close();
		return excelStream;

    }

    /**
     * @return 带有背景颜色的table
     * @throws DocumentException
     */
    public Table insertBGColor() throws DocumentException {
        Table table = new Table(4);//生成一个四列的表格
        int width[] = {25, 25, 25, 25};
        table.setWidths(width);//设置系列所占比例
        table.setWidth(100);
        table.setAutoFillEmptyCells(true);
        table.setAlignment(Element.ALIGN_CENTER);//居中显示
        table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中显示
        table.setBorder(30);
        table.setBorderWidth(230);//边框宽度
        table.setSpacing(2);
        table.setPadding(3);
        table.setBorderColor(new Color(58, 255, 132));//边框颜色

        Cell cell = new Cell(new Phrase("列一"));
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(new Color(189, 22, 33));
        cell.setBackgroundColor(new Color(58, 137, 20));
        table.addCell(cell);

        Cell cell2 = new Cell(new Phrase("列二"));
        cell2.setVerticalAlignment(Element.ALIGN_CENTER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setBorderColor(new Color(189, 22, 33));
        cell2.setBackgroundColor(new Color(137, 34, 44));
        table.addCell(cell2);

        Cell cell3 = new Cell(new Phrase("列三"));
        cell3.setVerticalAlignment(Element.ALIGN_CENTER);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setBorderColor(new Color(189, 22, 33));
        cell3.setBackgroundColor(new Color(232, 219, 48));
        table.addCell(cell3);

        Cell cell4 = new Cell(new Phrase("列四"));
        cell4.setVerticalAlignment(Element.ALIGN_CENTER);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setBorderColor(new Color(189, 22, 33));
        cell4.setBackgroundColor(new Color(54, 246, 231));
        table.addCell(cell);

        for (int i = 0; i < 8; i++) {
            table.addCell(new Cell("自定义内容"));
        }
        return table;
    }

    /**
     * @return 复合表格的简单例子
     * @throws DocumentException
     */
    public Table insertComplexTable() throws DocumentException {
        Table table = new Table(5);
        int width[] = {20, 20, 20, 20, 20};
        table.setWidths(width);//设置系列所占比例
        table.setWidth(100);
        table.setAutoFillEmptyCells(true);
        table.setAlignment(Element.ALIGN_CENTER);//居中显示
        table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中显示
        table.setBorder(1000);
        table.setBorderWidth(1);//边框宽度
        //table.setSpacing(2);
        //table.setPadding(3);
        table.setBorderColor(new Color(58, 255, 132));//边框颜色

        Cell cell = new Cell(new Phrase("占据三列的单元格"));
        cell.setColspan(3);//设置当前单元格占据的列数
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(new Color(189, 22, 33));
        cell.setBackgroundColor(new Color(58, 137, 20));
        table.addCell(cell);

        Cell cell2 = new Cell(new Phrase("第四列"));
        cell2.setVerticalAlignment(Element.ALIGN_CENTER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setBorderColor(new Color(189, 22, 33));
        // cell2.setBackgroundColor(new Color(137, 34, 44));
        table.addCell(cell2);

        Cell cell3 = new Cell(new Phrase("第五列"));
        cell3.setVerticalAlignment(Element.ALIGN_CENTER);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setBorderColor(new Color(189, 22, 33));
        // cell3.setBackgroundColor(new Color(232, 219, 48));
        table.addCell(cell3);

        Cell cell4 = new Cell(new Phrase("占据两行的单元格"));
        cell4.setBackgroundColor(new Color(232, 219, 48));
        cell4.setRowspan(2);
        table.addCell(cell4);

        for (int i = 0; i < 8; i++) {
            table.addCell(new Cell("自定义内容"));
        }

        Cell cell5 = new Cell(new Phrase("占据两行两列的单元格"));
        cell5.setBackgroundColor(new Color(137, 34, 44));
        cell5.setRowspan(2);
        cell5.setColspan(2);
        table.addCell(cell5);

        for (int i = 0; i < 6; i++) {
            table.addCell(new Cell("自定义内容"));
        }
        return table;
    }
    
    
}