package com.lanen.view.action.clinicaltest;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Columns;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblClinicalTestData;
import com.lanen.model.clinicaltest.TblClinicalTestData_json;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.studyplan.DictBioChem;
import com.lanen.model.studyplan.DictBloodCoag;
import com.lanen.model.studyplan.DictHemat;
import com.lanen.model.studyplan.DictUrine;
import com.lanen.model.studyplan.TblClinicalTestReq;
import com.lanen.model.studyplan.TblClinicalTestReqIndex2;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.clinicaltest.TblClinicalTestDataService;
import com.lanen.service.studyplan.DictBioChemService;
import com.lanen.service.studyplan.DictBloodCoagService;
import com.lanen.service.studyplan.DictHematService;
import com.lanen.service.studyplan.DictUrineService;
import com.lanen.service.studyplan.TblClinicalTestReqIndex2Service;
import com.lanen.service.studyplan.TblClinicalTestReqService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.util.DateUtil;
import com.lanen.util.POIExcelUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class TblClinicalTestDataAction extends BaseAction<TblClinicalTestData>{
	
	private static final long serialVersionUID = 6576496431290186706L;
	//检验数据service
	@Resource
	private TblClinicalTestDataService tblClinicalTestDataService;
	//临检申请service
	@Resource
	private TblClinicalTestReqService tblClinicalTestReqService;
	//临检申请单-动物编号
	@Resource
	private TblClinicalTestReqIndex2Service tblClinicalTestReqIndex2Service;
	/** 试验计划Service*/
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	@Resource
	private DictBioChemService dictBioChemService;
	@Resource
	private DictHematService dictHematService;
	@Resource
	private DictBloodCoagService dictBloodCoagService;
	@Resource
	private DictUrineService dictUrineService;
	
	/**
	 * 用于导出excel时使用
	 */
	private InputStream fileInput;
	private String fileName;
	
	/*** 试验计划*/
	private TblStudyPlan tblStudyPlan;
	//是否显示有效数据   0不显示      1  显示
	private String isValidationPara;
	private String isValidationPara1;
	private String setTab;
	private Date createDate;
	
	//临检检测数据     List
	private List<TblClinicalTestData>  tblClinicalTestDataList = new ArrayList<TblClinicalTestData>();
	
	private List<String> selecteds;
	
	private List<String> selecteds1;
	/*转到list页面*/
	public String list(){
		String studyNoPara =request.getParameter("studyNoPara");
		String reqNoPara =request.getParameter("reqNoPara");
		String isValidationPara =request.getParameter("isValidationPara");
		String isValidationPara1 =request.getParameter("isValidationPara1");
		String AdditionalApplications =request.getParameter("AdditionalApplications");
		String combinedWithAnimal =request.getParameter("combinedWithAnimal");
		if(setTab!= null){
			String setTab=request.getParameter("setTab");
			ActionContext.getContext().put("setTab", setTab);
		}
		ActionContext.getContext().put("studyNoPara", studyNoPara);
		ActionContext.getContext().put("reqNoPara", reqNoPara);
		ActionContext.getContext().put("AdditionalApplications", AdditionalApplications);
		ActionContext.getContext().put("isValidationPara", isValidationPara);
		ActionContext.getContext().put("isValidationPara1", isValidationPara1);
		ActionContext.getContext().put("combinedWithAnimal", combinedWithAnimal);
		return "list";
	}

	/*加载数据和表头*/
	public void loadRowsAndColumns(){
		String studyNoPara =request.getParameter("studyNoPara");
		//studyNoPara="LS2013-0001";
		int reqNoPara =Integer.parseInt(request.getParameter("reqNoPara"));
		//reqNoPara=1;
		int testItemPara = Integer.parseInt(request.getParameter("testItemPara"));
		//标志位是否显示附加申请
		String AdditionalApplications =request.getParameter("AdditionalApplications");
		
		//是否合并动物
		String combinedWithAnimal =request.getParameter("combinedWithAnimal");
		
		if( AdditionalApplications.equals("all")){
			//检测结果数据（签过字的）
			List<TblClinicalTestData> tblClinicalTestDataListAll = new ArrayList<TblClinicalTestData>();
			List<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2ListAll = new ArrayList<TblClinicalTestReqIndex2>();
			//获取试验计划
			tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
			//获取临检申请数据
			List<TblClinicalTestData> tblClinicalTestDataList=tblClinicalTestDataService.findByStudyNoReqNoTestItem(studyNoPara, reqNoPara, testItemPara);
			List<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2List =tblClinicalTestReqIndex2Service.findByStudyNoAndReqNO(studyNoPara, reqNoPara);
			List<TblClinicalTestReq> suntblClinicalTestReq = tblClinicalTestReqService.findByStudyNoAndparentReqNo(studyNoPara, reqNoPara);
			tblClinicalTestDataListAll.addAll(tblClinicalTestDataList);
			tblClinicalTestReqIndex2ListAll.addAll(tblClinicalTestReqIndex2List);
			for(TblClinicalTestReq obj:suntblClinicalTestReq){
				int reqNo = obj.getReqNo();
				List<TblClinicalTestData> tblClinicalTestDataList2=tblClinicalTestDataService.findByStudyNoReqNoTestItem(studyNoPara, reqNo, testItemPara);
				List<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2List2 =tblClinicalTestReqIndex2Service.findByStudyNoAndReqNO(studyNoPara, reqNo);
				if(tblClinicalTestDataList2 != null && tblClinicalTestReqIndex2List2 != null){
				tblClinicalTestDataListAll.addAll(tblClinicalTestDataList2);
				tblClinicalTestReqIndex2ListAll.addAll(tblClinicalTestReqIndex2List2);
				}
			}
			String jsonStr = null;
			if(combinedWithAnimal.equals("0")){
				jsonStr  = JsonPluginsUtil.beanToJson( tblClinicalTestDataList2RowsAndColumnsJson(tblClinicalTestDataListAll,tblClinicalTestReqIndex2ListAll,testItemPara,isValidationPara,isValidationPara1));
			}else{
				jsonStr = JsonPluginsUtil.beanToJson(tblClinicalTestDataList2RowsAndColumnsJsonCombined(tblClinicalTestDataListAll,tblClinicalTestReqIndex2ListAll,testItemPara,isValidationPara,isValidationPara1));
			}
			
			
			writeJson(jsonStr);
		}else{
			
			//检测结果数据（签过字的）
			List<TblClinicalTestData> tblClinicalTestDataList=tblClinicalTestDataService.findByStudyNoReqNoTestItem(studyNoPara, reqNoPara, testItemPara);
			List<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2List =tblClinicalTestReqIndex2Service.findByStudyNoAndReqNO(studyNoPara, reqNoPara);
			String jsonStr= JsonPluginsUtil.beanToJson(tblClinicalTestDataList2RowsAndColumnsJson(tblClinicalTestDataList,tblClinicalTestReqIndex2List,testItemPara,isValidationPara,isValidationPara1));
			writeJson(jsonStr);
			
		}

	}
	//
	/*加载数据和表头*/
	@SuppressWarnings("unchecked")
	public String outExcel() throws IOException{
		String studyNoPara =request.getParameter("studyNoPara");
		//studyNoPara="LS2013-0001";
		int reqNoPara =Integer.parseInt(request.getParameter("reqNoPara"));
		//reqNoPara=1;
		int testItemPara = Integer.parseInt(request.getParameter("testItemPara"));
		//标志位是否显示附加申请
		String AdditionalApplications =request.getParameter("AdditionalApplications");
		
		//获取临检申请
		tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		TblClinicalTestReq tblClinicalTestReq = tblClinicalTestReqService.findByStudyNoAndReqNO(studyNoPara, reqNoPara);
		
		//是否合并动物
		String combinedWithAnimal =request.getParameter("combinedWithAnimal");
		Map<String, Object> map = null;
		if( AdditionalApplications.equals("all")){
			//检测结果数据（签过字的）
			List<TblClinicalTestData> tblClinicalTestDataListAll = new ArrayList<TblClinicalTestData>();
			List<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2ListAll = new ArrayList<TblClinicalTestReqIndex2>();
			//获取试验计划
			tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
			//获取临检申请数据
			
			List<TblClinicalTestData> tblClinicalTestDataList=tblClinicalTestDataService.findByStudyNoReqNoTestItem(studyNoPara, reqNoPara, testItemPara);
			List<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2List =tblClinicalTestReqIndex2Service.findByStudyNoAndReqNO(studyNoPara, reqNoPara);
			List<TblClinicalTestReq> suntblClinicalTestReq = tblClinicalTestReqService.findByStudyNoAndparentReqNo(studyNoPara, reqNoPara);
			tblClinicalTestDataListAll.addAll(tblClinicalTestDataList);
			tblClinicalTestReqIndex2ListAll.addAll(tblClinicalTestReqIndex2List);
			for(TblClinicalTestReq obj:suntblClinicalTestReq){
				int reqNo = obj.getReqNo();
				List<TblClinicalTestData> tblClinicalTestDataList2=tblClinicalTestDataService.findByStudyNoReqNoTestItem(studyNoPara, reqNo, testItemPara);
				List<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2List2 =tblClinicalTestReqIndex2Service.findByStudyNoAndReqNO(studyNoPara, reqNo);
				if(tblClinicalTestDataList2 != null && tblClinicalTestReqIndex2List2 != null){
				tblClinicalTestDataListAll.addAll(tblClinicalTestDataList2);
				tblClinicalTestReqIndex2ListAll.addAll(tblClinicalTestReqIndex2List2);
				}
			}
			
			if(combinedWithAnimal.equals("0")){
				map  = tblClinicalTestDataList2RowsAndColumnsJson(tblClinicalTestDataListAll,tblClinicalTestReqIndex2ListAll,testItemPara,isValidationPara,isValidationPara1);
			
			}else{
				map = tblClinicalTestDataList2RowsAndColumnsJsonCombined(tblClinicalTestDataListAll,tblClinicalTestReqIndex2ListAll,testItemPara,isValidationPara,isValidationPara1);
			}
			//writeJson(jsonStr);
		}else{
			
			//检测结果数据（签过字的）
			List<TblClinicalTestData> tblClinicalTestDataList=tblClinicalTestDataService.findByStudyNoReqNoTestItem(studyNoPara, reqNoPara, testItemPara);
			List<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2List =tblClinicalTestReqIndex2Service.findByStudyNoAndReqNO(studyNoPara, reqNoPara);
			map = tblClinicalTestDataList2RowsAndColumnsJson(tblClinicalTestDataList,tblClinicalTestReqIndex2List,testItemPara,isValidationPara,isValidationPara1);
			//writeJson(jsonStr);
			
		}
		if(map!=null&&map.size()>0)
		{
			//列list（列 columns）
			List<List<Columns>> columnsList = (List<List<Columns>>)map.get("columns");
			//数值列表
			List<Map<String,String>> rowsList = (List<Map<String,String>>)map.get("rows") ;
		
			POIExcelUtil excelUtil = new POIExcelUtil();
			String name ="";
			switch(testItemPara)
			{
				case 1:name="生化";break;
				case 2:name="血液";break;
				case 3:name="血凝";break;
				case 4:name="尿常规";break;
			}
			HSSFSheet sheet = excelUtil.newSheet(name);
			
			HSSFRow row0 = sheet.createRow(0);
			if(columnsList!=null&&!"".equals(columnsList)&&columnsList.size()>0)
			{
					List<Columns> columns = columnsList.get(1);//三列申请编号，id号和编号
					for(int i=0;i<columns.size();i++)//最后的一个不用显示
					{
						Columns column = columns.get(i);
						if(column.getTitle().equals("编号"))
						{
							row0.createCell(i).setCellValue("动物编号");
						}else
						{
						 row0.createCell(i).setCellValue(column.getTitle());
						}
						//row0.getCell(i).setCellStyle();
					}
					List<Columns> columnsAbbr = columnsList.get(0);//指标
					for(int i=1;i<columnsAbbr.size();i++)
					{
						Columns column = columnsAbbr.get(i);
						row0.createCell(i+2).setCellValue(column.getTitle());
					}
				
			}
	        for(int i=1;i<=rowsList.size();i++)
	        {
	          Map<String, String> rowData = rowsList.get(i-1);
	   	      HSSFRow row = sheet.createRow(i);
	          for(int columnNum=0;columnNum<columnsList.get(0).size()+columnsList.get(1).size()-1;columnNum++)
	          {
	        	 String key = row0.getCell(columnNum).getStringCellValue();   
	        	if(key!=null&&key.contains("("))
	        		key = key.substring(0,key.indexOf("(")).trim();
	        	  key = key.replaceAll("#", "0").replaceAll("%", "_").replaceAll("\\+", "9").replaceAll("\\-", "8").replaceAll("/", "7");
	        	if(key.equals("申请编号")) 
	        		 key="ReqNo";
	        	 if(key.equals("ID号"))
	        		 key="animalId";
	        	 if(key.equals("动物编号"))
	        		 key="animalCode";
	        		 
	        	 String value = rowData.get(key);
	        	if(value!=null&&value.contains("<"))//弃掉格式
	        	 {
	        		 value = value.substring(value.indexOf(">")+1);
	        		 value = value.substring(0,value.lastIndexOf("<"));
	        	 }
	        	//System.out.println("key="+key+" value2="+value); 
	        	if(value!=null&&value.equals("-"))
	        		 value.replaceAll("-", "\\-");
	        	row.createCell(columnNum).setCellValue(value);
	          }
	        }
	      
	 	 	//excelUtil.save(name);
	 	 	HSSFWorkbook wb = excelUtil.getWb();
	 	 	ByteArrayOutputStream fos = new ByteArrayOutputStream();
	 	 	wb.write(fos);
	 		fileInput =  new ByteArrayInputStream(fos.toByteArray());
	 		fos.close();
	 		
	 		fileName = studyNoPara;
	 		fileName = fileName.concat(" "+DateUtil.dateToString(tblClinicalTestReq.getBeginDate(),"yyyy-MM-dd"));
	 		fileName = fileName.concat(" "+name);
	 		fileName = fileName.concat(" .xls");
	 		
	 		System.out.println("===="+fileName);
	 		//fileName= java.net.URLEncoder.encode(fileName,request.getCharacterEncoding());
	 		fileName = new String(fileName.getBytes(request.getCharacterEncoding()), "ISO_8859_1"); 
	 		return "saveExcel";
	       
	      /* Map<String,Object> map2 = new HashMap<String,Object>();
		     map2.put("success", true);
		     String jsonString = JsonPluginsUtil.beanToJson(map2);
		     writeJson(jsonString);*/
		}else {
		     return "list";
		}
	       
	    
		
	}
	
	
	/** 临检检测数据转成  crosstab 合并动物表格形式*/
	public  Map<String, Object> tblClinicalTestDataList2RowsAndColumnsJsonCombined(List<TblClinicalTestData> list,List<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2List,int testItem,String isValidationPara,String isValidationPara1){
		if(null!=list && list.size()>0){
			//动物Id号set
			Set<String> animalIdSet = new HashSet<String>();
			//列set
			Set<Columns> columnsSet = new HashSet<Columns>();
			
			//检验指标缩写set
			List<String> testIndexAbbrSet = new ArrayList<String>();
			Columns columns=null;

			for(TblClinicalTestData testData:list){
				testIndexAbbrSet.add(testData.getTestIndexAbbr());
				animalIdSet.add(testData.getAnimalId());
			
			}
			//检验指标缩写list
			List<String> testIndexAbbrList = new ArrayList<String>(testIndexAbbrSet);
			//动物Id号list(行)
			List<String> animalIdList=new ArrayList<String>();   
			//为什么要去掉重复行？？？？
			Set<String> animalIdSetForList = new HashSet<String>();///01.23后添加,为animalIdList去掉重复行
			//动物id号 动物编号  键值对map
			Map<String,String> animalIdCodeMap = new HashMap<String,String>();//TODO
			Map<String,String> ReqNoMap = new HashMap<String,String>();//TODO
			
			if(null!=list&& list.size()>0){
				List<String> reqNoList = new ArrayList<String>();
				for(TblClinicalTestData obj:list){
					if(animalIdSet.contains(obj.getAnimalId())){
						animalIdSetForList.add(obj.getAnimalId()); //01.23 后添加
						animalIdCodeMap.put(obj.getAnimalId(), obj.getAnimalCode());
						String reqNoString = ReqNoMap.get(obj.getAnimalId());
						if(reqNoList.contains(obj.getReqNo())){
							continue;
						}else{
							reqNoList.add(obj.getReqNo());
						}
						if(reqNoString == null){
							reqNoString = obj.getReqNo()+"";
							ReqNoMap.put(obj.getAnimalId()+"", reqNoString);
						}else{
							reqNoString = reqNoString+" , "+obj.getReqNo();
							ReqNoMap.put(obj.getAnimalId()+"", reqNoString);
						}
					}
					
				}
			}
			animalIdList = new ArrayList<String>(animalIdSetForList);      //01.23 后添加
			
			//列list（列 columns）
			List<List<Columns>> columnsList= testIndexAbbrList2Columns(testIndexAbbrList,testItem);
			
			//数值列表
			List<Map<String,String>> rowsList = new ArrayList<Map<String,String>>();
			Map<String,String> rowMap = null;
			int index1=0;
			for(String animalId:animalIdList){
				index1++;
				rowMap = new HashMap<String,String>();
				rowMap.put("animalId", animalId);
				rowMap.put("animalCode", animalIdCodeMap.get(animalId));
				rowMap.put("ReqNo", ReqNoMap.get(animalId));
				boolean falge = false;
				for(TblClinicalTestData testData:list){
							if( testData.getAnimalId().equals(animalId)){
//                                    String TestIndexAbbr =testData.getTestIndexAbbr().replaceAll("#", "0").replaceAll("%", "_");
                                    String TestIndexAbbr =testData.getTestIndexAbbr()
                                    								.replaceAll("#", "0")
                                    								.replaceAll("%", "_")
                                    								.replaceAll("\\+", "9")
                                    								.replaceAll("\\-", "8")
                                    								.replaceAll("/", "7");
                                    String oldTestData="";
									if( rowMap.containsKey(TestIndexAbbr)){
									   oldTestData= rowMap.get(TestIndexAbbr);
									}
									int flag = testData.getSelected();//1有效 0无效
									int es = testData.getEs();//0签过字，1没签过
									String newTestData = "";
								    //判断是否选择了只显示已确认数据
									if( isValidationPara1.equals("1")){
										//已签字
										if(es == 1){
											if(flag == 1){
												newTestData = "<a style='color:red;'>"+testData.getTestData()+"</a>";
												if(oldTestData != ""){
													newTestData = oldTestData +","+newTestData;
												}
											}else{
												//是否选择了只显示已选中数据
												if( isValidationPara.equals("1")){
													newTestData = oldTestData;
												}else{
													newTestData = "<a style=''>"+testData.getTestData()+"</a>";
													if(oldTestData != ""){
														newTestData = oldTestData +","+newTestData;
													}
												}
											}
										}else{
											newTestData = oldTestData;
										}
									}else{
										if(es == 1){
											if(flag == 1){
												//选中
												newTestData = "<a style='color:red;'>"+testData.getTestData()+"</a>";
												if(oldTestData != ""){
													newTestData = oldTestData +","+newTestData;
												}
											}else{
												if( isValidationPara.equals("1")){
													newTestData = oldTestData;
												}else{
													newTestData = "<a style=''>"+testData.getTestData()+"</a>";
													if(oldTestData != ""){
														newTestData = 	oldTestData+","+newTestData;
													}
												}
											}
										}else{
											newTestData = "<a style='color:#00EE00;'>"+testData.getTestData()+"</a>";
											if(oldTestData != ""){
												newTestData = newTestData+","+oldTestData;
											}				
										}
										
									}
						
								    rowMap.put(TestIndexAbbr, newTestData);
								    if(null != newTestData && newTestData.length()> 0 ){
								    	falge = true;
								    }
							}
				}		
				if(falge){
					rowsList.add(rowMap);
				}
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("columns", columnsList);
			map.put("rows", rowsList);
			//String jsonStr = JsonPluginsUtil.beanToJson(map);
			//System.out.println(jsonStr);
			return map;
		}
		return null;
	}
	
	
	/** 临检检测数据转成  crosstab 表格形式*/
	public  Map<String,Object> tblClinicalTestDataList2RowsAndColumnsJson(List<TblClinicalTestData> list,List<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2List,int testItem,String isValidationPara,String isValidationPara1){
		if(null!=list && list.size()>0){
			//动物Id号set
			//Set<String> animalIdSet = new HashSet<String>();
			List<String> animalIdSet = new ArrayList<String>();
			//列set
			//Set<Columns> columnsSet = new HashSet<Columns>();
			List<Columns> columnsSet = new ArrayList<Columns>();
			
			//检验指标缩写set
			List<String> testIndexAbbrSet = new ArrayList<String>();
			Columns columns=null;

			for(TblClinicalTestData testData:list){
				testIndexAbbrSet.add(testData.getTestIndexAbbr());
				animalIdSet.add(testData.getAnimalId());
			
			}
			//检验指标缩写list
			List<String> testIndexAbbrList = new ArrayList<String>(testIndexAbbrSet);
			//动物Id号list(行)
			List<String> animalIdList=new ArrayList<String>();   
			//为什么要去掉重复行？？？？
			//Set<String> animalIdSetForList = new HashSet<String>();///01.23后添加,为animalIdList去掉重复行
			List<String> animalIdSetForList = new ArrayList<String>();
			//动物id号 动物编号  键值对map
			Map<String,String> animalIdCodeMap = new HashMap<String,String>();//TODO
			Map<String,String> ReqNoMap = new HashMap<String,String>();//TODO
			if(null!=tblClinicalTestReqIndex2List&& tblClinicalTestReqIndex2List.size()>0){
				int index = 0;
				for(TblClinicalTestReqIndex2 obj:tblClinicalTestReqIndex2List){
					
					if(animalIdSet.contains(obj.getAnimalId())){
						index++;
						animalIdSetForList.add(obj.getAnimalId()); //01.23 后添加
//						animalIdList.add(obj.getAnimalId()); //?    //01.23 后注释掉的
						animalIdCodeMap.put(obj.getAnimalId(), obj.getAnimalCode());
						ReqNoMap.put(obj.getAnimalId()+" "+index, obj.getReqNo()+"");
						
					}
				}
			}
			animalIdList = new ArrayList<String>(animalIdSetForList);      //01.23 后添加
			//动物id号列表排序
			//Collections.sort(animalIdList, new Comparator<String>(){
				
			//	public int compare(String o1, String o2) {
			//		return o1.compareTo(o2);
			//	}
				
			//});
			
			
			//列list（列 columns）
			List<List<Columns>> columnsList= testIndexAbbrList2Columns(testIndexAbbrList,testItem);
			
			//数值列表
			List<Map<String,String>> rowsList = new ArrayList<Map<String,String>>();
			Map<String,String> rowMap = null;
			int index1=0;
			for(String animalId:animalIdList){
				index1++;
				rowMap = new HashMap<String,String>();
				rowMap.put("animalId", animalId);
				rowMap.put("animalCode", animalIdCodeMap.get(animalId));
				rowMap.put("animalCode", animalIdCodeMap.get(animalId));
				rowMap.put("ReqNo", ReqNoMap.get(animalId+" "+index1));
				String reqMap1 = ReqNoMap.get(animalId+" "+index1);
				boolean falge = false;
				for(TblClinicalTestData testData:list){
					String reqMap2 = testData.getReqNo();
							if(reqMap2.equals(reqMap1) && testData.getAnimalId().equals(animalId)){
//                                    String TestIndexAbbr =testData.getTestIndexAbbr().replaceAll("#", "0").replaceAll("%", "_");
                                    String TestIndexAbbr =testData.getTestIndexAbbr()
                                    								.replaceAll("#", "0")
                                    								.replaceAll("%", "_")
                                    								.replaceAll("\\+", "9")
                                    								.replaceAll("\\-", "8")
                                    								.replaceAll("/", "7");
                                    String oldTestData="";
									if( rowMap.containsKey(TestIndexAbbr)){
									   oldTestData= rowMap.get(TestIndexAbbr);
									}
									int flag = testData.getSelected();//1有效 0无效
									int es = testData.getEs();//0签过字，1没签过
									String newTestData = "";
								    //判断是否选择了只显示已确认数据
									if( isValidationPara1.equals("1")){
										//已签字
										if(es == 1){
											if(flag == 1){
												newTestData = "<a style='color:red;'>"+testData.getTestData()+"</a>";
												if(oldTestData != ""){
													newTestData = oldTestData +","+newTestData;
												}
//												if(oldTestData != ""){
//												newTestData = "<a style='color:#00EE00;'>"+testData.getTestData()+"</a>"+","+oldTestData;
//												}else{
//													newTestData = "<a style='color:#00EE00;'>"+testData.getTestData()+"</a>";
//												}
												
											}else{
												//是否选择了只显示已选中数据
												if( isValidationPara.equals("1")){
													newTestData = oldTestData;
												}else{
													newTestData = "<a style=''>"+testData.getTestData()+"</a>";
													if(oldTestData != ""){
														newTestData = oldTestData +","+newTestData;
													}
//													if(oldTestData != ""){
//													newTestData = "<a style=''>"+testData.getTestData()+"</a>"+","+oldTestData;
//													}else{
//														newTestData = "<a style=''>"+testData.getTestData()+"</a>";
//													}
												}
											}
										}else{
											newTestData = oldTestData;
										}
									}else{
										if(es == 1){
											if(flag == 1){
												//选中
												newTestData = "<a style='color:red;'>"+testData.getTestData()+"</a>";
												if(oldTestData != ""){
													newTestData = oldTestData +","+newTestData;
												}
//												if(oldTestData != ""){
//													newTestData = "<a style='color:#00EE00;'>"+testData.getTestData()+"</a>"+","+oldTestData;
//												}else{
//													newTestData = "<a style='color:#00EE00;'>"+testData.getTestData()+"</a>";
//												}
											}else{
												if( isValidationPara.equals("1")){
													newTestData = oldTestData;
												}else{
													newTestData = "<a style=''>"+testData.getTestData()+"</a>";
													if(oldTestData != ""){
														newTestData = 	oldTestData+","+newTestData;
													}
//													if(oldTestData != ""){
//														newTestData = "<a style=''>"+testData.getTestData()+"</a>"+","+oldTestData;
//													}else{
//														newTestData = "<a style=''>"+testData.getTestData()+"</a>";
//													}
													
												}
											}
										}else{
											newTestData = "<a style='color:#00EE00;'>"+testData.getTestData()+"</a>";
											if(oldTestData != ""){
												newTestData = newTestData+","+oldTestData;
											}
//											if(oldTestData != ""){
//												newTestData = "<a style='color:red;'>"+testData.getTestData()+"</a>"+","+oldTestData;
//											}else{
//												newTestData = "<a style='color:red;'>"+testData.getTestData()+"</a>";
//											}
											
										}
										
									}
						
								    rowMap.put(TestIndexAbbr, newTestData);
								    if(null != newTestData && newTestData.length()> 0 ){
								    	falge = true;
								    }
							
							}
				}		
				if(falge){
					rowsList.add(rowMap);
				}
				
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("columns", columnsList);
			map.put("rows", rowsList);
			//String jsonStr = JsonPluginsUtil.beanToJson(map);
			//return jsonStr;
			return map;
		}
		return null;
	}
	/**检验指标缩写转成表头所要格式 (columns)*/
    private List<List<Columns>> testIndexAbbrList2Columns(List<String> testIndexAbbrList, int testItem) {
//    	private DictBioChemService dictBioChemService;
//    	private DictHematService dictHematService;
//    	private DictBloodCoagService dictBloodCoagService;
//    	private DictUrineService dictUrineService;
    	List<Columns> list = new ArrayList<Columns>();
    	Columns columns = null;
		columns =new Columns();
		columns.setTitle("动物");
		columns.setColspan(3);
		columns.setWidth(160);
		list.add(columns);
		switch (testItem) {
		case 1:
			List<DictBioChem> dictBioChemList=dictBioChemService.findAllOrderByOrderNo();
			if(null!=dictBioChemList && dictBioChemList.size()>0){
				for(DictBioChem obj:dictBioChemList){
					if(testIndexAbbrList.contains(obj.getAbbr())){
						columns =new Columns();
//						columns.setField(obj.getAbbr().replaceAll("#", "0").replaceAll("%", "_"));
						columns.setField(obj.getAbbr().replaceAll("#", "0").replaceAll("%", "_")
																	.replaceAll("\\+", "9")
																	.replaceAll("\\-", "8")
																	.replaceAll("/", "7"));
						//单位不为空
						if(null!=obj.getUnit()&&!"".equals(obj.getUnit())){
							columns.setTitle(obj.getAbbr()+"\n("+obj.getUnit()+")");
						}else{
							columns.setTitle(obj.getAbbr());
						}
						columns.setRowspan(2);
						columns.setWidth(90);
						list.add(columns);
					}
				}
			}
			break;
		case 2:
			List<DictHemat> dictHematList=dictHematService.findAllOrderByOrderNo();
			if(null!=dictHematList && dictHematList.size()>0){
				for(DictHemat obj:dictHematList){
					if(testIndexAbbrList.contains(obj.getAbbr())){
						columns =new Columns();
						columns.setField(obj.getAbbr().replaceAll("#", "0").replaceAll("%", "_")
															.replaceAll("\\+", "9")
															.replaceAll("\\-", "8")
															.replaceAll("/", "7"));
//						columns.setTitle(obj.getAbbr()+"\n("+obj.getUnit()+")");
						//单位不为空
						if(null!=obj.getUnit()&&!"".equals(obj.getUnit())){
							columns.setTitle(obj.getAbbr()+"\n("+obj.getUnit()+")");
						}else{
							columns.setTitle(obj.getAbbr());
						}
						columns.setRowspan(2);
						columns.setWidth(90);
						list.add(columns);
					}
				}
			}
			break;
		case 3:
			List<DictBloodCoag> dictBloodCoagList=dictBloodCoagService.findAllOrderByOrderNo();
			if(null!=dictBloodCoagList && dictBloodCoagList.size()>0){
				for(DictBloodCoag obj:dictBloodCoagList){
					if(testIndexAbbrList.contains(obj.getAbbr())){
						columns =new Columns();
//						columns.setField(obj.getAbbr().replaceAll("#", "0").replaceAll("%", "_"));
						columns.setField(obj.getAbbr().replaceAll("#", "0").replaceAll("%", "_")
														.replaceAll("\\+", "9")
														.replaceAll("\\-", "8")
														.replaceAll("/", "7"));
//						columns.setTitle(obj.getAbbr()+"\n("+obj.getUnit()+")");
						//单位不为空
						if(null!=obj.getUnit()&&!"".equals(obj.getUnit())){
							columns.setTitle(obj.getAbbr()+"\n("+obj.getUnit()+")");
						}else{
							columns.setTitle(obj.getAbbr());
						}
						columns.setRowspan(2);
						columns.setWidth(90);
						list.add(columns);
					}
				}
			}
			break;
		case 4:
			List<DictUrine> dicUrineList=dictUrineService.findAllOrderByOrderNo();
			if(null!=dicUrineList && dicUrineList.size()>0){
				for(DictUrine obj:dicUrineList){
					if(testIndexAbbrList.contains(obj.getAbbr())){
						columns =new Columns();
//						columns.setField(obj.getAbbr().replaceAll("#", "0").replaceAll("%", "_"));
						columns.setField(obj.getAbbr().replaceAll("#", "0").replaceAll("%", "_")
															.replaceAll("\\+", "9")
															.replaceAll("\\-", "8")
															.replaceAll("/", "7"));
//						columns.setTitle(obj.getAbbr()+"\n("+obj.getUnit()+")");
						//单位不为空
						if(null!=obj.getUnit()&&!"".equals(obj.getUnit())){
							columns.setTitle(obj.getAbbr()+"\n("+obj.getUnit()+")");
						}else{
							columns.setTitle(obj.getAbbr());
						}
						columns.setRowspan(2);
						columns.setWidth(90);
						list.add(columns);
					}
				}
			}
			break;
		}
		List<Columns> list2 = new ArrayList<Columns>();
		columns = new Columns();
		columns =new Columns();
		columns.setField("ReqNo");
		columns.setTitle("申请编号");
		columns.setWidth(80);
		columns.setColspan(1);
		list2.add(columns);
		columns = new Columns();
		columns =new Columns();
		columns.setField("animalId");
		columns.setTitle("ID号");
		columns.setWidth(80);
		columns.setColspan(1);
		list2.add(columns);
		columns = new Columns();
		columns =new Columns();
		columns.setField("animalCode");
		columns.setTitle("编号");
		columns.setWidth(80);
		list2.add(columns);
		List<List<Columns>> list12=new ArrayList<List<Columns>>();
		list12.add(list);
		list12.add(list2);
		return list12;
	}

    
    public void DataScreeningloadRowsAndColumns(){
    	String studyNoPara =request.getParameter("studyNoPara");
    	String animalId =request.getParameter("animalId");
    	int testItemPara = Integer.parseInt(request.getParameter("testItemPara"));
    	List<TblClinicalTestData> tblClinicalTestDataList;
    	String  reqNoParaString = request.getParameter("reqNoPara");
    	if( reqNoParaString.contains(",")){
    		tblClinicalTestDataList=tblClinicalTestDataService.findByStudyNoTestItemAnimalCode(studyNoPara, testItemPara,animalId);
    	}else{
    		int reqNoPara =Integer.parseInt(request.getParameter("reqNoPara"));
    	    tblClinicalTestDataList=tblClinicalTestDataService.findByStudyNoReqNoTestItemAnimalCode(studyNoPara, reqNoPara, testItemPara,animalId);
    	}
    	
    	List<TblClinicalTestData_json> jsonList = new ArrayList<TblClinicalTestData_json>();
    	for(TblClinicalTestData testData:tblClinicalTestDataList){
    		TblClinicalTestData_json json = new TblClinicalTestData_json();
			String testDatanumber = testData.getTestData();
			String tesTestIndexAbbr = testData.getTestIndexAbbr();
			int flag = testData.getSelected();//1有效 0无效
			int es = testData.getEs();//0签过字，1没签过
			if(es == 1){
				if(flag==0){
					testDatanumber = "<a style=''>"+testDatanumber+"</a>";
					 json.setCkid(0);
				}else{
					testDatanumber ="<a style='color:red;' >"+ testDatanumber+"</a>";
					 json.setCkid(1);
				}
		    json.setTestData(testDatanumber);
			json.setDataId(testData.getDataId());
			json.setTestIndexAbbr(tesTestIndexAbbr);
			jsonList.add(json);
			}else{
				continue;
			}
		}	
    	Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows",jsonList);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
    }
    
    
    public void TestDataSelecteds(){
    	List<TblClinicalTestData> list = new ArrayList<TblClinicalTestData>();
    	Map<String,String> TestData = new HashMap<String,String>();
    	System.out.println(selecteds1);
    	System.out.println(selecteds);
    	if(null != selecteds && selecteds.size() > 0 ){
    		for(String sid:selecteds1){
        		TblClinicalTestData tblClinicalTestData= tblClinicalTestDataService.getById(sid);
        		tblClinicalTestData.setSelected(0);
        		for(String sid1:selecteds){
        			if(sid != sid1){
        				list.add(tblClinicalTestData);
        			}
        		}
        	}
        	for(String sid:selecteds){
        		TblClinicalTestData tblClinicalTestData= tblClinicalTestDataService.getById(sid);
        		tblClinicalTestData.setSelected(1);
        		String  Test=  tblClinicalTestData.getTestIndexAbbr();
        		TestData.put("指标", "动物"+sid+"/"+Test);
        		TestData.put("数值",tblClinicalTestData.getTestData() );
        		list.add(tblClinicalTestData);
        	}
    	}else{
    		for(String sid:selecteds1){
    			TblClinicalTestData tblClinicalTestData= tblClinicalTestDataService.getById(sid);
        		tblClinicalTestData.setSelected(0);
        		list.add(tblClinicalTestData);
    		}
    	}
    	
    	
    	
    	tblClinicalTestDataService.updateAllTblClinicalTestData(list);
    	String operatContent = "数据筛选"+TestData+"动物" ;
    	writeLog("临检数据筛选",operatContent);
    	Map<String,Object> map = new HashMap<String,Object>();
		map.put("success",true);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
    }
    
    /**
	 * 写日志
	 * @return
	 */
	private void writeLog(String operatType,String operatContent){
		//记录设备登记日志
		  TblLog tblLog = new TblLog();
		  tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
		  tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
		  tblLog.setOperatType(operatType);
		  tblLog.setOperatOject("临检申请");
		  User user = (User) ActionContext.getContext().getSession().get("user");
		  if(null!=user){
			  tblLog.setOperator(user.getRealName());
		  }
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}
    
	//---------------------------------------------------------------------	
	public String print(){
		tblClinicalTestDataList=tblClinicalTestDataService.findAll();
		return "print";
	}

	//-----------------------------------------------------------------------------
	public List<TblClinicalTestData> getTblClinicalTestDataList() {
		return tblClinicalTestDataList;
	}

	public void setTblClinicalTestDataList(
			List<TblClinicalTestData> tblClinicalTestDataList) {
		this.tblClinicalTestDataList = tblClinicalTestDataList;
	}

	public TblStudyPlan getTblStudyPlan() {
		return tblStudyPlan;
	}

	public void setTblStudyPlan(TblStudyPlan tblStudyPlan) {
		this.tblStudyPlan = tblStudyPlan;
	}

	public TblStudyPlanService getTblStudyPlanService() {
		return tblStudyPlanService;
	}

	public void setTblStudyPlanService(TblStudyPlanService tblStudyPlanService) {
		this.tblStudyPlanService = tblStudyPlanService;
	}

	public String getIsValidationPara() {
		return isValidationPara;
	}

	public void setIsValidationPara(String isValidationPara) {
		this.isValidationPara = isValidationPara;
	}

	public String getIsValidationPara1() {
		return isValidationPara1;
	}

	public void setIsValidationPara1(String isValidationPara1) {
		this.isValidationPara1 = isValidationPara1;
	}

	public String getSetTab() {
		return setTab;
	}

	public void setSetTab(String setTab) {
		this.setTab = setTab;
	}

	public List<String> getSelecteds() {
		return selecteds;
	}

	public void setSelecteds(List<String> selecteds) {
		this.selecteds = selecteds;
	}

	public List<String> getSelecteds1() {
		return selecteds1;
	}

	public void setSelecteds1(List<String> selecteds1) {
		this.selecteds1 = selecteds1;
	}

	public InputStream getFileInput() {
		return fileInput;
	}

	public void setFileInput(InputStream fileInput) {
		this.fileInput = fileInput;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
	

	
	
}
