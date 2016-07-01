package com.lanen.view.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Department;
import com.lanen.model.ImportedSpecimen;
import com.lanen.model.TblFileContentEmployee;
import com.lanen.model.TblFileContentGlpSynthesis;
import com.lanen.model.TblFileContentInstrument;
import com.lanen.model.TblFileContentSop;
import com.lanen.model.TblFileContentStudy;
import com.lanen.model.TblFileIndex;
import com.lanen.model.TblFileRecord;
import com.lanen.model.TblFileRecordSmplReserve;
import com.lanen.model.archive.DictArchiveType;
import com.lanen.model.archive.DictSoptype;
import com.lanen.service.archive.DictArchiveTypeService;
import com.lanen.service.archive.DictSOPTypeService;
import com.lanen.service.archive.TblFileContentEmployeeService;
import com.lanen.service.archive.TblFileContentGlpSynthesisService;
import com.lanen.service.archive.TblFileContentInstrumentService;
import com.lanen.service.archive.TblFileContentSopService;
import com.lanen.service.archive.TblFileContentStudyService;
import com.lanen.service.archive.TblFileIndexService;
import com.lanen.service.archive.TblFileRecordService;
import com.lanen.service.archive.TblFileRecordSmplReserveService;
import com.lanen.util.DateUtil;
import com.lanen.util.POIExcelUtil;

@Controller
@Scope("prototype")
public class ImportedExistArchiveDataAction extends BaseAction<ImportedSpecimen> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -461125973251919449L;
	@Resource
	private TblFileContentStudyService tblFileContentStudyService;
	@Resource
	private TblFileContentInstrumentService tblFileContentInstrumentService;
	@Resource
	private TblFileContentSopService tblFileContentSopService;
	@Resource
	private TblFileIndexService tblFileIndexService;
	@Resource
	private TblFileRecordService tblFileRecordService;
	@Resource
	private DictArchiveTypeService dictArchiveTypeService;
	@Resource
	private DictSOPTypeService dictSOPTypeService;
	@Resource
	private TblFileContentGlpSynthesisService tblFileContentGlpSynthesisService;
	@Resource
	private TblFileContentEmployeeService tblFileContentEmployeeService;
	@Resource
	private  TblFileRecordSmplReserveService tblFileRecordSmplReserveService;
	
	
	
	//页面上传的文件
	private File excelExistArchive;//File对象，目的是获取页面上传的文件
	private String contentType;//文件类型
	
	
	private Json json;
	private Map<String, Object> exceptMap;
	
	
	/**批量导入
	  */
	public void importExistDataExcel() {
		String returnMsg = "" ; 
		json = new Json();
		
		List<String> typeList = new ArrayList<String>();
		typeList.add("application/kset");
		typeList.add("application/excel");
		typeList.add("application/vnd.ms-excel");
		typeList.add("application/msexcel");
		typeList.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		
		
		
		List<List<String>> rowColsList = null ;
		if(typeList.contains(contentType)){//判断下文件类型
			
			if(excelExistArchive!=null){
				POIExcelUtil excelUtil = new POIExcelUtil();
				
				//对excel的处理
				//0档案目录 1仪器 2sop 3专题（非GLP），4化学品，5农药，6分析，7专题（药品），8专题（急性试验），9电子版，10基建，11综合资料，12人事，
				//0档案目录 1仪器 2sop 3专题（非GLP），4化学品，5农药，6分析，7专题（药品），8专题（急性试验），9电子版，10基建，11综合资料，12人事，13Sheet1
				List<String> titleList = new ArrayList<String>();
				String  msg ;
				
				List<Integer> sheets = new ArrayList<Integer>();
				sheets.add(0);
				
				String titleStr = "类别，分类号，全宗号，档号，归档日期，题名，供货的信息，储存地（资料室  L3036），份数，文字张数 ，编制单位，中标单位，招标代理， ，备注 ，竣工日期，进库日期，顺序号，编制日期起，编制日期止，备考表，" +
				"立卷人，  ，审核人，审核日期，保管期限，密级，互见号，图纸，其他，路径，列号，区间号，方向，层号，节号，原文地址，附件张数，凭证号起，凭证号讫";
				
				for(String str:titleStr.split("，"))
					titleList.add(str);
				try {
					rowColsList = excelUtil.excel2007Export(excelExistArchive,titleList,sheets);//excel文件，文件头列表，第几个sheet
				} catch (Exception e) {
					rowColsList = excelUtil.excel2003Export(excelExistArchive,titleList,sheets);
				}
				json = excelUtil.getJson();
				msg = json.getMsg();
				if(null==msg || "".equals(msg)){//消息为空，表示读取顺利，
					//对于获取数据的处理
					dealInstrumentArchive(rowColsList);
				}else {
					returnMsg+=msg+"\n\n";
				}
				
				//dealSOP好了
				//0档案目录 1仪器 2sop 3专题（非GLP），4化学品，5农药，6分析，7专题（药品），8专题（急性试验），9电子版，10基建，11综合资料，12人事，13Sheet1
				//每个excel都是一种。
				titleList = new ArrayList<String>();
				titleStr = "类别，分类号，全宗号，档号，归档日期，题名，表格，归档日期，最新版本，作废版本，储存地（资料室  L3036），份数，文字张数，编制单位，中标单位，招标代理， ，备注，竣工日期，进库日期，顺序号，编制日期起，编制日期止，备考表，" +
						"立卷人，  ，审核人，审核日期，保管期限，密级，互见号，图纸，其他，路径，列号，区间号，方向，层号，节号，原文地址，附件张数，凭证号起，凭证号讫";
				for(String str:titleStr.split("，"))
					titleList.add(str);
				sheets = new ArrayList<Integer>();
				sheets.add(1);
				
				try {
					rowColsList = excelUtil.excel2007Export(excelExistArchive,titleList,sheets);//excel文件，文件头列表，第几个sheet
				} catch (Exception e) {
					rowColsList = excelUtil.excel2003Export(excelExistArchive,titleList,sheets);
				}
				json = excelUtil.getJson();
				msg = json.getMsg();
				if(null==msg || "".equals(msg)){//消息为空，表示读取顺利，
					//对于获取数据的处理
					dealSOPArchive(rowColsList);
				}else {
					returnMsg+=msg+"\n\n";
				}
				
				
				//处理study的
				//0档案目录 1仪器 2sop 3专题（非GLP），4化学品，5农药，6分析，7专题（药品），8专题（急性试验），9电子版，10基建，11综合资料，12人事，
				titleList = new ArrayList<String>();
				//sheet1
				//	titleStr = "类别，分类号，全宗号，档号，归档日期，题名， ， ，编制单位， ， ，  ， ， ， ， ， ， ， ，立卷人， ， ， ， 保管期限，" ;
				//专题急性试验也不一样
				//titleStr = "类别，分类号，全宗号，档号，归档日期，专题编号，专题名称 ，专题负责人，份数，文字张数 ，编制单位，  ，中标单位，招标代理， ，备注 ，竣工日期，进库日期，顺序号，编制日期起，编制日期止，备考表，" +
				//"立卷人，  ，审核人，审核日期，保管期限，密级，互见号，图纸，其他，路径，列号，区间号，方向，层号，节号，原文地址，附件张数，凭证号起，凭证号讫";
				//专题（非GLP） title都一样，sheet1先不管
				titleStr = "类别，分类号，全宗号，档号，归档日期，题名，委托方信息，储存地（资料室  L3036），份数，文字张数，编制单位，中标单位，招标代理， ，备注，竣工日期，进库日期，顺序号，编制日期起，编制日期止，备考表，" +
				"立卷人，  ，审核人，审核日期，保管期限，密级，互见号，图纸，其他，路径，列号，区间号，方向，层号，节号，原文地址，附件张数，凭证号起，凭证号讫";
				
				sheets = new ArrayList<Integer>();
				sheets.add(2);
				sheets.add(3);
				sheets.add(4);
				sheets.add(5);
				sheets.add(6);
				
				//六个sheet,最后一个sheet1不管
				for(String str:titleStr.split("，"))
					titleList.add(str);
				try {
					rowColsList = excelUtil.excel2007Export(excelExistArchive,titleList,sheets);//excel文件，文件头列表，第几个sheet
				} catch (Exception e) {
					rowColsList = excelUtil.excel2003Export(excelExistArchive,titleList,sheets);
				}
				json = excelUtil.getJson();
				msg = json.getMsg();
				if(null==msg || "".equals(msg)){//消息为空，表示读取顺利，
					//对于获取数据的处理
					dealStudyArchive(rowColsList);
				}else {
					returnMsg+=msg+"\n\n";
				}
				
				//0档案目录 1仪器 2sop 3专题（非GLP），4化学品，5农药，6分析，7专题（药品），8专题（急性试验），9电子版，10基建，11综合资料，12人事，
				
				//处理study的急性试验的，不同的是题名分为专题编号和专题名称
				/*titleList = new ArrayList<String>();
				//sheet1
				//	titleStr = "类别，分类号，全宗号，档号，归档日期，题名， ， ，编制单位， ， ，  ， ， ， ， ， ， ， ，立卷人， ， ， ， 保管期限，" ;
				//专题急性试验也不一样,先不处理
				titleStr = "类别，分类号，全宗号，档号，归档日期，专题编号，专题名称，专题负责人，份数，文字张数 ，编制单位，  ，中标单位，招标代理， ，备注 ，竣工日期，进库日期，顺序号，编制日期起，编制日期止，备考表，" +
				"立卷人，  ，审核人，审核日期，保管期限，密级，互见号，图纸，其他，路径，列号，区间号，方向，层号，节号，原文地址，附件张数，凭证号起，凭证号讫";
				
				sheets = new ArrayList<Integer>();
				sheets.add(0);
				
			
				for(String str:titleStr.split("，"))
					titleList.add(str);
				try {
					rowColsList = excelUtil.excel2007Export(excelExistArchive,titleList,sheets);//excel文件，文件头列表，第几个sheet
				} catch (Exception e) {
					rowColsList = excelUtil.excel2003Export(excelExistArchive,titleList,sheets);
				}
				json = excelUtil.getJson();
				msg = json.getMsg();
				if(null==msg || "".equals(msg)){//消息为空，表示读取顺利，
					//对于获取数据的处理
					dealStudyArchive2(rowColsList);
				}*/
				
				
				
				
				//0档案目录 1仪器 2sop 3专题（非GLP），4化学品，5农药，6分析，7专题（药品），8专题（急性试验），9电子版，10基建，11综合资料，12人事，
				
				//处理综合
				titleList = new ArrayList<String>();
				
				titleStr = "类别，分类号，全宗号，档号，归档日期，题名，部门，份数，文字张数 ，编制单位，中标单位，招标代理， ，备注 ，竣工日期，进库日期，顺序号，编制日期起，编制日期止，备考表，" +
				"立卷人，  ，审核人，审核日期，保管期限，密级，互见号，图纸，其他，路径，列号，区间号，方向，层号，节号，原文地址，附件张数，凭证号起，凭证号讫";
				
				sheets = new ArrayList<Integer>();
				sheets.add(10);
				
				for(String str:titleStr.split("，"))
					titleList.add(str);
				try {
					rowColsList = excelUtil.excel2007Export(excelExistArchive,titleList,sheets);//excel文件，文件头列表，第几个sheet
				} catch (Exception e) {
					rowColsList = excelUtil.excel2003Export(excelExistArchive,titleList,sheets);
				}
				json = excelUtil.getJson();
				msg = json.getMsg();
				if(null==msg || "".equals(msg)){//消息为空，表示读取顺利，
					//对于获取数据的处理
					dealGlpArchive(rowColsList);
				}else {
					returnMsg+=msg+"\n\n";
				}
				
				//0档案目录 1仪器 2sop 3专题（非GLP），4化学品，5农药，6分析，7专题（药品），8专题（急性试验），9电子版，10基建，11综合资料，12人事，
				
				//处理基建
				titleList = new ArrayList<String>();
				titleStr = "类别，分类号，全宗号，档号，归档日期，题名，份数，文字张数 ，编制单位，中标单位，招标代理， ，备注 ，竣工日期，进库日期，顺序号，编制日期起，编制日期止，备考表，" +
				"立卷人，  ，审核人，审核日期，保管期限，密级，互见号，图纸，其他，路径，列号，区间号，方向，层号，节号，原文地址，附件张数，凭证号起，凭证号讫";
				
				sheets = new ArrayList<Integer>();
				sheets.add(9);
				
				for(String str:titleStr.split("，"))
					titleList.add(str);
				try {
					rowColsList = excelUtil.excel2007Export(excelExistArchive,titleList,sheets);//excel文件，文件头列表，第几个sheet
				} catch (Exception e) {
					rowColsList = excelUtil.excel2003Export(excelExistArchive,titleList,sheets);
				}
				json = excelUtil.getJson();
				msg = json.getMsg();
				if(null==msg || "".equals(msg)){//消息为空，表示读取顺利，
					//对于获取数据的处理
					dealGlpArchive2(rowColsList);//基建
				}else {
					returnMsg+=msg+"\n\n";
				}
				//0档案目录 1仪器 2sop 3专题（非GLP），4化学品，5农药，6分析，7专题（药品），8专题（急性试验），9电子版，10基建，11综合资料，12人事，
				//处理人员
				titleList = new ArrayList<String>();
				
				titleStr = "存放位置，姓名，人员编号";
				
				sheets = new ArrayList<Integer>();
				sheets.add(11);
				
				for(String str:titleStr.split("，"))
					titleList.add(str);
				try {
					rowColsList = excelUtil.excel2007Export(excelExistArchive,titleList,sheets);//excel文件，文件头列表，第几个sheet
				} catch (Exception e) {
					rowColsList = excelUtil.excel2003Export(excelExistArchive,titleList,sheets);
				}
				json = excelUtil.getJson();
				msg = json.getMsg();
				if(null==msg || "".equals(msg)){//消息为空，表示读取顺利，
					//对于获取数据的处理
					dealEmployeeArchive(rowColsList);//人员
				}else {
					returnMsg+=msg+"\n\n";
				}
				
				
			}else{
				returnMsg = "文件读取失败";
				json.setMsg("文件读取失败");
			}
			
			
		}else{//文件类型不对
			if("application/octet-stream".equals(contentType)){
				returnMsg = "文件被其他程序占用";
				json.setMsg("文件被其他程序占用");
			}else{
				returnMsg = "请导入excel类型文件";
				json.setMsg("请导入excel类型文件");
			}
		}
		
		/*if(null==json.getMsg() || "".equals(json.getMsg())){//消息为空，表示读取顺利，
			returnMsg = "导入成功！";
		}*/
		json.setSuccess(true);
		//json.setMsg(returnMsg);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String str = "";
		for (String key:exceptMap.keySet()) {
			str+=key+"  "+exceptMap.get(key);
		}
		resultMap.put("exceptList",str);
		resultMap.put("success", true);
		resultMap.put("msg",json.getMsg());
		
		String jsonStr = JsonPluginsUtil.beanToJson(resultMap);
		writeJson(jsonStr);
	}
	
	public List<String> getStrInfoFromStr(String str)
	{
		//String str="aaa(bbb(ccc)df)(ddd )(ffff)";
		//System.out.println(str2.replaceAll("(", "~").split("~").length);
		Pattern pattern = Pattern.compile("\\(");//获取小括号里的内容
		Matcher matcher = pattern.matcher(str);
		String str2 = str;
		 List<String> resultList = new ArrayList<String>();
		 boolean flag = false;
		 while(matcher.find())
		 { 
			
			String result = str2;
			if(result.contains("("))
			{
				result = str2.substring(0,str2.indexOf("("));
			}
			if(result.contains(")"))
			{
				result = result.substring(0,result.indexOf(")"));
			}
			resultList.add(result);
			
			
			str2 = str2.substring(str2.indexOf(matcher.group())+1);
		 	
			
		 	if(str2.indexOf("(")>0&&str2.indexOf(")")>0&&str2.indexOf(")")>str2.indexOf("("))
		 	{
		 		str2=str2.substring(0,str2.indexOf("("))+str2.substring(str2.indexOf(")")+1);
		 		
		 	}
		 	
		 }
		 
		/* for(String result:resultList)
		 {
			 System.out.println("111r=="+result);
		 }*/
		 
		 return resultList;
		
	}
	
	public void dealInstrumentArchive(List<List<String>> rowColsList)
	{
		if(exceptMap==null||"".equals(exceptMap))
		{
			exceptMap = new HashMap<String, Object>();
		}
		String exceptStr = "仪器不规范的数据行有：";
		
		String fileOperator = "曹红梅";
		List<List<String>> removeRecords = new ArrayList<List<String>>();
		List<String> archiveCodeAndSnList = new ArrayList<String>();
		
		//移除不规范的记录
		for(List<String> strL:rowColsList)
		{
			if(strL.get(5)==null||!strL.get(5).contains("(")||
				strL.get(3)==null||!strL.get(3).contains("-")
					||strL.get(3).contains("(")||strL.get(3).contains("（")
					||strL.get(3).split("-").length>3)
			{
				//类别行是不用导入的。
				removeRecords.add(strL);
				exceptStr += strL.get(3)+",";
			}
			try {
				String archiveCodeAndSn = strL.get(3);
				String archiveCode = archiveCodeAndSn;
				Integer sn = 1;
				if(archiveCodeAndSn!=null&&archiveCodeAndSn.contains("-")
						&&!archiveCodeAndSn.contains("(")&&!archiveCodeAndSn.contains("（")
						&&strL.get(3).split("-").length<=3)
				{
					if(archiveCodeAndSn.split("-").length==2)
						strL.set(3,strL.get(3)+"-001");
					else if(archiveCodeAndSn.split("-").length==3)
					{
						archiveCode = archiveCodeAndSn.substring(0,archiveCodeAndSn.lastIndexOf("-"));
						sn = Integer.valueOf(archiveCodeAndSn.substring(archiveCodeAndSn.lastIndexOf("-")+1));
						if(sn>0&&sn<10)
						{
							strL.set(3,archiveCode+"-00"+sn);
						}else if(sn<100){
							strL.set(3,archiveCode+"-0"+sn);
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			archiveCodeAndSnList.add(strL.get(3));
			
		}
		rowColsList.removeAll(removeRecords);
		removeRecords = new ArrayList<List<String>>();
		exceptStr = "\n仪器中档案编号和盒子号已经存在的数据行有：";
		if(rowColsList!=null)
		{
			Map<String,String> studyArchiveTypeList = new HashMap<String,String>();
			
			List<String> existArchiveCodeSnList = tblFileRecordService.getExistByCodeAndSnList(archiveCodeAndSnList);
			//移除已经存在档案编号和序列号 的记录
			for(List<String> record:rowColsList)
			{
				for(String existCodeAndSn:existArchiveCodeSnList)
				{
					if(record.get(3)!=null&&
							record.get(3).equals(existCodeAndSn))
					{
						exceptStr += record.get(3)+",";
						removeRecords.add(record);
						continue;
					}
				}
				
				studyArchiveTypeList.put(record.get(1),record.get(0));//档案类别4.3.1
				
			}
			rowColsList.removeAll(removeRecords);
			
			exceptMap.put("仪器",exceptStr);
			
			//查看不存在的类别编号，如果类别不存在则添加类别
			List<String> existTypeCode = dictArchiveTypeService.getExistTypeCodeByList(new ArrayList<String>(studyArchiveTypeList.keySet()),5);
			studyArchiveTypeList.keySet().removeAll(existTypeCode);
			for(String archiveTypeCode:studyArchiveTypeList.keySet())
			{
				DictArchiveType archiveType = new DictArchiveType();
				archiveType.setArchiveTypeCode(archiveTypeCode);
				archiveType.setArchiveTypeFlag(5);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				archiveType.setArchiveTypeName(studyArchiveTypeList.get(archiveTypeCode));
				
				dictArchiveTypeService.save(archiveType);
			}
			
			Date operateTime = new Date();
			for(List<String> rowRecord:rowColsList)
			{
				
					String archiveCodeAndSn = rowRecord.get(3);
					String archiveCode = archiveCodeAndSn;
					Integer sn = 1;
					if(archiveCodeAndSn.contains("-")&&archiveCodeAndSn.split("-").length==3)
					{
						archiveCode = archiveCodeAndSn.substring(0,archiveCodeAndSn.lastIndexOf("-"));
						sn = Integer.valueOf(archiveCodeAndSn.substring(archiveCodeAndSn.lastIndexOf("-")+1));
					}
					
					String archiveTypeCode = rowRecord.get(1);//4.3.1即是类型名又是类型编号
					String archivetypeName = rowRecord.get(0);
					String archiveMarker = rowRecord.get(22);
					String archiveTitle = archivetypeName;
					
					String info = rowRecord.get(5);
					List<String> infoList = getStrInfoFromStr(info);
					if(infoList!=null&&infoList.size()>=3)
					{
						//前id 后型号
						String instrumentId=infoList.get(1);
						String instrumentName=infoList.get(0);
						String instrumentModel=infoList.get(2);
						String remark = "";
						if(infoList.size()>3)
						{
							remark = infoList.get(3);
						}
					
						//String sponerName = rowRecord.get(6);
						Integer fileDateType = 1; 
						Date fileDate = null;
						if(rowRecord.get(4)!=null)
						{
							String dateStr = rowRecord.get(4);
							if(dateStr.length()==4)
							{
								dateStr+="0101";
								fileDateType=1;
								fileDate = DateUtil.stringToDate(dateStr, "yyyyMMdd");
							}else if(dateStr.length()==8){
								fileDateType = 3; 
								fileDate = DateUtil.stringToDate(dateStr, "yyyyMMdd");
							}else if(dateStr.length()==10){
								fileDateType = 3; 
								fileDate = DateUtil.stringToDate(dateStr, "yyyy/MM/dd");
							}
							
						}
						
						TblFileIndex fileIndex  = tblFileIndexService.getById(archiveCode);
						if(fileIndex==null)
						{
							fileIndex = new TblFileIndex();
							fileIndex.setArchiveCode(archiveCode);
							fileIndex.setArchiveTitle(archiveTitle);
							fileIndex.setArchiveTypeCode(archiveTypeCode);
							fileIndex.setArchiveTypeFlag(5);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
							fileIndex.setOperateTime(operateTime);
							fileIndex.setOperator(fileOperator);
							fileIndex.setStorePosition("L3036");
							fileIndex.setValidationFlag(0);
								
							tblFileIndexService.save(fileIndex);
							
						}
						
						String key = tblFileRecordService.getKey("TblFileRecord");
						TblFileRecord record = new TblFileRecord();			
						record.setArchiveMaker(archiveMarker);
						//record.setArchiveMedia(model.getTblFileRecord().getArchiveMedia());
						record.setArchiveMediaFlag(1);
					//	record.setArchiveMediaEleCode(model.getTblFileRecord().getArchiveMediaEleCode());
					//	record.setDestoryDate(model.getTblFileRecord().getDestoryDate());
						record.setFileDate(fileDate);
						record.setFileDateType(fileDateType);//年月日
						record.setFileOperator(fileOperator);
						record.setFileRecordId(key);
						record.setFileRecordSn(sn);
						//record.setKeepDate(model.getTblFileRecord().getKeepDate());
						//record.setKeyWord(model.getTblFileRecord().getKeyWord());
						record.setOperateTime(operateTime);
						record.setOperator(fileOperator);
						record.setRemark(remark);
						record.setTblFileIndex(fileIndex);
						
						tblFileRecordService.save(record);
						
						TblFileContentInstrument fileContentIns = new TblFileContentInstrument();
						fileContentIns.setArchiveCode(archiveCode);
						fileContentIns.setFileRecordId(key);
						
						fileContentIns.setInstrumentId(instrumentId);
						//fileContentIns.setInstrumentManufacturer(instrumentManufacturer);
						fileContentIns.setInstrumentModel(instrumentModel);
						fileContentIns.setInstrumentName(instrumentName);
						//fileContentIns.setInstrumentPurchaseDate(model.getInstrumentPurchaseDate());
						
						fileContentIns.setTblFileRecord(record);
						
						try {
							tblFileContentInstrumentService.save(fileContentIns);
						} catch (Exception e) {
							//该条数据不符合要求，请规范
							System.out.println("数据不符合要求："+archiveCodeAndSn);
						}
						
					}else {
						System.out.println("仪器必须包含仪器ID，仪器名称以及仪器型号："+archiveCodeAndSn);
					}
					
					
			}//for结束
		}
		
		
	}
	public void dealSOPArchive(List<List<String>> rowColsList)
	{
		if(exceptMap==null||"".equals(exceptMap))
		{
			exceptMap = new HashMap<String, Object>();
		}
		String exceptStr = "SOP不规范的数据行有：";
		
		String fileOperator = "曹红梅";
		List<List<String>> removeRecords = new ArrayList<List<String>>();
		List<String> archiveCodeAndSnList = new ArrayList<String>();
		
		for(List<String> strL:rowColsList)
		{
			if(strL.get(5).split(" ").length<2
					||strL.get(5).split("-").length<2)
			{
				//类别行是不用导入的。
				removeRecords.add(strL);

				exceptStr += strL.get(3)+",";
			}
			archiveCodeAndSnList.add(strL.get(3));
			String archiveCodeAndSn = strL.get(3);
			String archiveCode = archiveCodeAndSn;
			Integer sn = 1;
			if(archiveCodeAndSn.contains("-"))
			{
				if(archiveCodeAndSn.split("-").length==2)
					strL.set(3,strL.get(3)+"-001");
				else if(archiveCodeAndSn.split("-").length==3)
				{
					archiveCode = archiveCodeAndSn.substring(0,archiveCodeAndSn.lastIndexOf("-"));
					sn = Integer.valueOf(archiveCodeAndSn.substring(archiveCodeAndSn.lastIndexOf("-")+1));
					if(sn>0&&sn<10)
					{
						strL.set(3,archiveCode+"-00"+sn);
					}else if(sn<100){
						strL.set(3,archiveCode+"-0"+sn);
					}
				}
			}
			
		}
		exceptStr = "\nSOP中档案编号和盒子号已经存在的数据行有：";
		
		List<String> sopCodeAndSopVerList = new ArrayList<String>();
		for(List<String> strL:rowColsList)
		{
			if(strL.get(5)!=null&&!"".equals(strL.get(5))
					&&strL.get(5).split(" ").length>=2)
			{
				sopCodeAndSopVerList.add(strL.get(5).split(" ")[0]);
			}
		}
		if(rowColsList!=null)
		{
			Map<String,String> sopTypeList = new HashMap<String,String>();
			Map<String,String> sopArchiveTypeList = new HashMap<String,String>();
			
			List<String> existArchiveCodeSnList = tblFileRecordService.getExistByCodeAndSnList(archiveCodeAndSnList);
			List<String> existSopCodeVerSnList = tblFileContentSopService.getExistBySopCodeAndVerList(sopCodeAndSopVerList);
			//移除已经存在档案编号和序列号 的记录
			for(List<String> record:rowColsList)
			{
				for(String existCodeAndSn:existArchiveCodeSnList)
				{
					if(record.get(3)!=null&&
							record.get(3).equals(existCodeAndSn))
					{
						removeRecords.add(record);
						exceptStr += record.get(3)+",";
						continue;
					}
				}
				for(String existSopCodeAndVer:existSopCodeVerSnList)
				{
					if(record.get(5)!=null&&
							record.get(5).split(" ")[0].equals(existSopCodeAndVer))
					{
						removeRecords.add(record);
					}
				}
				
				if(record.get(5)!=null&&record.get(5).split("-").length>2)
				{
					String sopTypeCode = record.get(5).split("-")[1];//sop-GM-....
					String sopTypeName = record.get(0);
					sopTypeList.put(sopTypeCode,sopTypeName);
				}
				sopArchiveTypeList.put(record.get(1),record.get(0));//档案类别4.3.1
				
			}
			rowColsList.removeAll(removeRecords);
			
			exceptMap.put("SOP",exceptStr);
			
			//查看不存在的类别编号，如果类别不存在则添加类别
			List<String> existTypeCode = dictArchiveTypeService.getExistTypeCodeByList(new ArrayList<String>(sopArchiveTypeList.keySet()),3);
			sopArchiveTypeList.keySet().removeAll(existTypeCode);
			for(String archiveTypeCode:sopArchiveTypeList.keySet())
			{
				DictArchiveType archiveType = new DictArchiveType();
				archiveType.setArchiveTypeCode(archiveTypeCode);
				archiveType.setArchiveTypeFlag(3);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				archiveType.setArchiveTypeName(sopArchiveTypeList.get(archiveTypeCode));
				
				dictArchiveTypeService.save(archiveType);
			}
			
			List<String> existSOPTypeCode = dictSOPTypeService.getExistSOPTypeCodeByList(new ArrayList<String>(sopTypeList.keySet()));
			sopTypeList.keySet().removeAll(existSOPTypeCode);
			for(String sopTypeCode:sopTypeList.keySet())
			{
				DictSoptype archiveType = new DictSoptype();
				archiveType.setId(dictSOPTypeService.getKey("DictSoptype"));
				archiveType.setSopname(sopTypeList.get(sopTypeCode));
				archiveType.setSoptypeCode(sopTypeCode);
				
				dictSOPTypeService.save(archiveType);
			}
			
			
			// "类别，分类号，全宗号，档号，归档日期，题名，表格，归档日期，最新版本，作废版本，储存地（资料室  L3036），份数，文字张数，编制单位，中标单位，招标代理， ，备注，竣工日期，进库日期，顺序号，编制日期起，编制日期止，备考表，" +
			//"立卷人， ，审核人，审核日期，保管期限，密级，互见号，图纸，其他，路径，列号，区间号，方向，层号，节号，原文地址，附件张数，凭证号起，凭证号讫";
			Date operateTime = new Date();
			for(List<String> rowRecord:rowColsList)
			{
				String titleTblCodeAndVer = rowRecord.get(6);
				String archiveTblTitle= null,sopTblCodeAndVer= null,sopTblCode= null,sopTblName= null,sopTblVer= null,archiveTblTypeCode = null;
				if(titleTblCodeAndVer!=null&&!"".equals(titleTblCodeAndVer)&&titleTblCodeAndVer.contains(" "))
				{
					 archiveTblTitle = titleTblCodeAndVer.substring(titleTblCodeAndVer.indexOf(" ")).trim();
					 sopTblCodeAndVer = titleTblCodeAndVer.substring(0,titleTblCodeAndVer.indexOf(" "));
					 sopTblCode = sopTblCodeAndVer.substring(0,sopTblCodeAndVer.lastIndexOf("-"));
					 sopTblName = archiveTblTitle;
				}
					
					String archiveCodeAndSn = rowRecord.get(3);
					String archiveCode = archiveCodeAndSn.substring(0,archiveCodeAndSn.lastIndexOf("-"));
					Integer sn = Integer.valueOf(archiveCodeAndSn.substring(archiveCodeAndSn.length()-3));
					String archiveTypeCode = rowRecord.get(1);//4.3.1即是类型名又是类型编号
					String archivetypeName = rowRecord.get(0);
					String archiveMarker = rowRecord.get(24);
					String titleCodeAndVer = rowRecord.get(5);
					String archiveTitle = archivetypeName;
					String sopCodeAndVer = titleCodeAndVer.substring(0,titleCodeAndVer.indexOf(" ")).trim();
					String sopCode = sopCodeAndVer.substring(0,sopCodeAndVer.lastIndexOf("-"));
					
					String sopName = titleCodeAndVer.substring(titleCodeAndVer.indexOf(" ")).trim();
					String sopVer = sopCodeAndVer.substring(sopCodeAndVer.lastIndexOf("-")+1);
					
					String sopTypeCode = sopCode.split("-")[1];//GM类似的
					
					Integer fileDateType = 1; 
					Date fileDate = null;
					if(rowRecord.get(4)!=null)
					{
						String dateStr = rowRecord.get(4);
						if(dateStr.length()==4)
						{
							dateStr+="0101";
							fileDateType=1;
							fileDate = DateUtil.stringToDate(dateStr, "yyyyMMdd");
						}else if(dateStr.length()==8){
							fileDateType = 3; 
							fileDate = DateUtil.stringToDate(dateStr, "yyyyMMdd");
						}else if(dateStr.length()==10){
							fileDateType = 3; 
							fileDate = DateUtil.stringToDate(dateStr, "yyyy/MM/dd");
						}
						
					}
					
					TblFileIndex fileIndex = null;
					if(sn==1)
					{
						fileIndex = new TblFileIndex();
						fileIndex.setArchiveCode(archiveCode);
						fileIndex.setArchiveTitle(archiveTitle);
						
						fileIndex.setArchiveTypeCode(archiveTypeCode);//
						
						fileIndex.setArchiveTypeFlag(3);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
						fileIndex.setOperateTime(operateTime);
						fileIndex.setOperator(getCurrentUser().getRealName());
						fileIndex.setStorePosition("L3036");
						fileIndex.setValidationFlag(0);
						
						tblFileIndexService.save(fileIndex);
					}else{
						fileIndex = tblFileIndexService.getById(archiveCode);
					}
					
					String key = tblFileRecordService.getKey("TblFileRecord");
					TblFileRecord record = new TblFileRecord();			
					record.setArchiveMaker(archiveMarker);
					//record.setArchiveMedia();
					record.setArchiveMediaFlag(1);//1：纸质；2：电子；3：其他
					//record.setDestoryDate(model.getTblFileRecord().getDestoryDate());
					record.setFileDate(fileDate);
					record.setFileDateType(fileDateType);
					
					record.setFileOperator(fileOperator);
					record.setFileRecordId(key);
					record.setFileRecordSn(sn);
					
					//record.setKeepDate(); //都是长期的
				//	record.setKeyWord();
					record.setOperateTime(operateTime);
					record.setOperator(getCurrentUser().getRealName());
				//	record.setRemark();
					record.setTblFileIndex(fileIndex);
					
					tblFileRecordService.save(record);
					
					TblFileContentSop fileContentSop = new TblFileContentSop();
					fileContentSop.setArchiveCode(archiveCode);
					fileContentSop.setFileRecordId(key);
					fileContentSop.setSopcode(sopCode);
					//fileContentSop.setSopeffectiveDate();
					//fileContentSop.setSopinvalidDate();
					fileContentSop.setSopname(sopName);
					
					fileContentSop.setSoptypeCode(sopTypeCode);
					fileContentSop.setSoptypeName(archivetypeName);
					fileContentSop.setSopver(sopVer);
					
					fileContentSop.setSopflag(0);//0:sop,1:table
					fileContentSop.setTblFileRecord(record);
					
					
					tblFileContentSopService.save(fileContentSop);
					
					if(titleTblCodeAndVer!=null&&!"".equals(titleTblCodeAndVer)&&titleTblCodeAndVer.contains(" "))
					{
						//archiveTblTitle,,sopTblCode,sopTblName,sopTblVer,archiveTblTypeCode
						//如果有表格，表格增加一条数据
						/*fileIndex = new TblFileIndex();
						fileIndex.setArchiveCode(archiveCode);
						fileIndex.setArchiveTitle(archiveTblTitle);
						
						fileIndex.setArchiveTypeCode(archiveTblTypeCode);//GM...
						
						fileIndex.setArchiveTypeFlag(3);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
						fileIndex.setOperateTime(operateTime);
						fileIndex.setOperator(getCurrentUser().getRealName());
						fileIndex.setStorePosition("L3036");
						fileIndex.setValidationFlag(0);
						
						tblFileIndexService.save(fileIndex);*/
						
						/*key = tblFileRecordService.getKey("TblFileRecord");
						 
						record = new TblFileRecord();			
						record.setArchiveMaker(archiveMarker);
						//record.setArchiveMedia();
						record.setArchiveMediaFlag(1);//1：纸质；2：电子；3：其他
						//record.setDestoryDate(model.getTblFileRecord().getDestoryDate());
						record.setFileDate(fileDate);
						record.setFileDateType(fileDateType);
						
						record.setFileOperator(fileOperator);
						record.setFileRecordId(key);
						record.setFileRecordSn(sn);
						
						//record.setKeepDate(); //都是长期的
					//	record.setKeyWord();
						record.setOperateTime(operateTime);
						record.setOperator(getCurrentUser().getRealName());
					//	record.setRemark();
						record.setTblFileIndex(fileIndex);
						
						tblFileRecordService.save(record);
						
						fileContentSop = new TblFileContentSop();
						fileContentSop.setArchiveCode(archiveCode);
						fileContentSop.setFileRecordId(key);
						fileContentSop.setSopcode(sopTblCode);
						//fileContentSop.setSopeffectiveDate();
						//fileContentSop.setSopinvalidDate();
						fileContentSop.setSopname(sopTblName);
						
						fileContentSop.setSoptypeCode(sopTypeCode);
						fileContentSop.setSoptypeName(archivetypeName);
						fileContentSop.setSopver(sopTblVer);
						
						fileContentSop.setSopflag(1);//0:sop,1:table
						fileContentSop.setTblFileRecord(record);
						
						
						tblFileContentSopService.save(fileContentSop);*/
					}
					
					
				
			}//for结束
		}
		
	}
	public void dealStudyArchive(List<List<String>> rowColsList)//专题(非GLP)，化学品
	{
		if(exceptMap==null||"".equals(exceptMap))
		{
			exceptMap = new HashMap<String, Object>();
		}
		String exceptStr = "专题不规范的数据行有：";
		
		String fileOperator = "曹红梅";
		List<List<String>> removeRecords = new ArrayList<List<String>>();
		List<String> archiveCodeAndSnList = new ArrayList<String>();
		
		//移除不规范的记录
		for(List<String> strL:rowColsList)
		{
			if(strL.get(5).split(" ").length<2
					||strL.get(3)==null||!strL.get(3).contains("-")
					||strL.get(3).contains("(")||strL.get(3).contains("（"))
			{
				//类别行是不用导入的。
				removeRecords.add(strL);
				exceptStr += strL.get(3)+",";
			}
			try {
				String archiveCodeAndSn = strL.get(3);
				String archiveCode = archiveCodeAndSn;
				Integer sn = 1;
				if(archiveCodeAndSn!=null&&archiveCodeAndSn.contains("-")
						&&!archiveCodeAndSn.contains("(")&&!archiveCodeAndSn.contains("（"))
				{
					if(archiveCodeAndSn.split("-").length==2)
						strL.set(3,strL.get(3)+"-001");
					else if(archiveCodeAndSn.split("-").length==3)
					{
						archiveCode = archiveCodeAndSn.substring(0,archiveCodeAndSn.lastIndexOf("-"));
						sn = Integer.valueOf(archiveCodeAndSn.substring(archiveCodeAndSn.lastIndexOf("-")+1));
						if(sn>0&&sn<10)
						{
							strL.set(3,archiveCode+"-00"+sn);
						}else if(sn<100){
							strL.set(3,archiveCode+"-0"+sn);
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			archiveCodeAndSnList.add(strL.get(3));
		}
		rowColsList.removeAll(removeRecords);
		removeRecords = new ArrayList<List<String>>();
		exceptStr = "\n专题中档案编号和盒子号已经存在的数据行有：";
		
		if(rowColsList!=null)
		{
			Map<String,String> studyArchiveTypeList = new HashMap<String,String>();
			
			List<String> existArchiveCodeSnList = tblFileRecordService.getExistByCodeAndSnList(archiveCodeAndSnList);
			//移除已经存在档案编号和序列号 的记录
			for(List<String> record:rowColsList)
			{
				for(String existCodeAndSn:existArchiveCodeSnList)
				{
					if(record.get(3)!=null&&
							record.get(3).equals(existCodeAndSn))
					{
						removeRecords.add(record);
						exceptStr += record.get(3)+",";
						continue;
					}
				}
				
				studyArchiveTypeList.put(record.get(1),record.get(0));//档案类别4.3.1
				
			}
			rowColsList.removeAll(removeRecords);
			exceptMap.put("专题", exceptStr);
			
			//查看不存在的类别编号，如果类别不存在则添加类别
			List<String> existTypeCode = dictArchiveTypeService.getExistTypeCodeByList(new ArrayList<String>(studyArchiveTypeList.keySet()),1);
			studyArchiveTypeList.keySet().removeAll(existTypeCode);
			for(String archiveTypeCode:studyArchiveTypeList.keySet())
			{
				DictArchiveType archiveType = new DictArchiveType();
				archiveType.setArchiveTypeCode(archiveTypeCode);
				archiveType.setArchiveTypeFlag(1);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				archiveType.setArchiveTypeName(studyArchiveTypeList.get(archiveTypeCode));
				
				dictArchiveTypeService.save(archiveType);
			}
			
			// "类别，分类号，全宗号，档号，归档日期，题名，表格，归档日期，最新版本，作废版本，储存地（资料室  L3036），份数，文字张数，编制单位，中标单位，招标代理， ，备注，竣工日期，进库日期，顺序号，编制日期起，编制日期止，备考表，" +
			//"立卷人， ，审核人，审核日期，保管期限，密级，互见号，图纸，其他，路径，列号，区间号，方向，层号，节号，原文地址，附件张数，凭证号起，凭证号讫";
			Date operateTime = new Date();
			for(List<String> rowRecord:rowColsList)
			{
				
					String archiveCodeAndSn = rowRecord.get(3);
					String archiveCode = archiveCodeAndSn;
					Integer sn = 1;
					if(archiveCodeAndSn.contains("-")&&archiveCodeAndSn.split("-").length==3)
					{
						archiveCode = archiveCodeAndSn.substring(0,archiveCodeAndSn.lastIndexOf("-"));
						sn = Integer.valueOf(archiveCodeAndSn.substring(archiveCodeAndSn.lastIndexOf("-")+1));
					}
					
					String archiveTypeCode = rowRecord.get(1);//4.3.1即是类型名又是类型编号
					String archivetypeName = rowRecord.get(0);
					String archiveMarker = rowRecord.get(21);
					String archiveTitle = archivetypeName;
					String titleStudyNoAndName = rowRecord.get(5);
					String studyNo = titleStudyNoAndName.substring(0,titleStudyNoAndName.indexOf(" ")).trim();
					String studyName = titleStudyNoAndName.substring(titleStudyNoAndName.indexOf(" "));
					
					String sponerName = rowRecord.get(6);
					Integer fileDateType = 1; 
					Date fileDate = null;
					if(rowRecord.get(4)!=null)
					{
						String dateStr = rowRecord.get(4);
						if(dateStr.length()==4)
						{
							dateStr+="0101";
							fileDateType=1;
							fileDate = DateUtil.stringToDate(dateStr, "yyyyMMdd");
						}else if(dateStr.length()==8){
							fileDateType = 3; 
							fileDate = DateUtil.stringToDate(dateStr, "yyyyMMdd");
						}else if(dateStr.length()==10){
							fileDateType = 3; 
							fileDate = DateUtil.stringToDate(dateStr, "yyyy/MM/dd");
							if(fileDate==null)
							{
								fileDate = DateUtil.stringToDate(dateStr, "yyyy-MM-dd");
							}
						}
						
					}
					
					TblFileIndex fileIndex  = tblFileIndexService.getById(archiveCode);
					if(fileIndex==null)
					{
						fileIndex = new TblFileIndex();
						fileIndex.setArchiveCode(archiveCode);
						fileIndex.setArchiveTitle(archiveTitle);
						fileIndex.setArchiveTypeCode(archiveTypeCode);
						fileIndex.setArchiveTypeFlag(1);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
						fileIndex.setOperateTime(operateTime);
						fileIndex.setOperator(fileOperator);
						fileIndex.setStorePosition("L3036");
						fileIndex.setValidationFlag(0);
							
						tblFileIndexService.save(fileIndex);
						
					}
					
					String key = tblFileRecordService.getKey("TblFileRecord");
					TblFileRecord record = new TblFileRecord();			
					record.setArchiveMaker(archiveMarker);
					//record.setArchiveMedia(model.getTblFileRecord().getArchiveMedia());
					record.setArchiveMediaFlag(1);
				//	record.setArchiveMediaEleCode(model.getTblFileRecord().getArchiveMediaEleCode());
				//	record.setDestoryDate(model.getTblFileRecord().getDestoryDate());
					record.setFileDate(fileDate);
					record.setFileDateType(fileDateType);//年月日
					record.setFileOperator(fileOperator);
					record.setFileRecordId(key);
					record.setFileRecordSn(sn);
					//record.setKeepDate(model.getTblFileRecord().getKeepDate());
					//record.setKeyWord(model.getTblFileRecord().getKeyWord());
					record.setOperateTime(operateTime);
					record.setOperator(fileOperator);
					//record.setRemark(model.getTblFileRecord().getRemark());
					record.setTblFileIndex(fileIndex);
					
					tblFileRecordService.save(record);
					
					TblFileContentStudy fileContentStudy = new TblFileContentStudy();
					fileContentStudy.setArchiveCode(archiveCode);
					fileContentStudy.setFileRecordId(key);
					fileContentStudy.setOperateTime(operateTime);
					fileContentStudy.setOperator(fileOperator);
					//fileContentStudy.setSdname(model.getSdname());
					fileContentStudy.setStudyName(studyName);
					fileContentStudy.setStudySponerName(sponerName);
					fileContentStudy.setStudyNo(studyNo);
					fileContentStudy.setStudyNoType(1);//1专题2合同
					//fileContentStudy.setContractCode();
					
					fileContentStudy.setTblFileRecord(record);
					try {
						tblFileContentStudyService.save(fileContentStudy);
					} catch (Exception e) {
						//该条数据不符合要求，请规范
						System.out.println("数据不符合要求："+archiveCodeAndSn);
					}
					
					
			}//for结束
		}
	}
	
	public void dealStudyArchive2(List<List<String>> rowColsList)
	{
		if(exceptMap==null||"".equals(exceptMap))
		{
			exceptMap = new HashMap<String, Object>();
		}
		String exceptStr = "专题（试验）不规范的数据行有：";
		
		String fileOperator = "曹红梅";
		List<List<String>> removeRecords = new ArrayList<List<String>>();
		List<String> archiveCodeAndSnList = new ArrayList<String>();
		
		//移除不规范的记录
		for(List<String> strL:rowColsList)
		{
			//专题编号和专题名称是分开的，所以不用对5限制
			if(strL.get(3)==null||!strL.get(3).contains("-")
					||strL.get(3).contains("(")||strL.get(3).contains("（")
					||strL.get(3).split("-").length>3)
			{
				//类别行是不用导入的。
				removeRecords.add(strL);

				exceptStr += strL.get(3)+",";
			}
			try {
				String archiveCodeAndSn = strL.get(3);
				String archiveCode = archiveCodeAndSn;
				Integer sn = 1;
				if(archiveCodeAndSn!=null&&archiveCodeAndSn.contains("-")
						&&!archiveCodeAndSn.contains("(")&&!archiveCodeAndSn.contains("（")
						&&strL.get(3).split("-").length<=3)
				{
					if(archiveCodeAndSn.split("-").length==2)
						strL.set(3,strL.get(3)+"-001");
					else if(archiveCodeAndSn.split("-").length==3)
					{
						archiveCode = archiveCodeAndSn.substring(0,archiveCodeAndSn.lastIndexOf("-"));
						sn = Integer.valueOf(archiveCodeAndSn.substring(archiveCodeAndSn.lastIndexOf("-")+1));
						if(sn>0&&sn<10)
						{
							strL.set(3,archiveCode+"-00"+sn);
						}else if(sn<100){
							strL.set(3,archiveCode+"-0"+sn);
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			archiveCodeAndSnList.add(strL.get(3));
			
		}
		rowColsList.removeAll(removeRecords);
		removeRecords = new ArrayList<List<String>>();
		exceptStr = "\n专题（试验）中档案编号和盒子号已经存在的数据行有：";
		
		if(rowColsList!=null)
		{
			Map<String,String> studyArchiveTypeList = new HashMap<String,String>();
			
			List<String> existArchiveCodeSnList = tblFileRecordService.getExistByCodeAndSnList(archiveCodeAndSnList);
			//移除已经存在档案编号和序列号 的记录
			for(List<String> record:rowColsList)
			{
				for(String existCodeAndSn:existArchiveCodeSnList)
				{
					if(record.get(3)!=null&&
							record.get(3).equals(existCodeAndSn))
					{
						removeRecords.add(record);
						exceptStr += record.get(3)+",";
						continue;
					}
				}
				
				studyArchiveTypeList.put(record.get(1),record.get(0));//档案类别4.3.1
				
			}
			rowColsList.removeAll(removeRecords);
			exceptMap.put("专题(试验)", exceptStr);
			
			//查看不存在的类别编号，如果类别不存在则添加类别
			List<String> existTypeCode = dictArchiveTypeService.getExistTypeCodeByList(new ArrayList<String>(studyArchiveTypeList.keySet()),1);
			studyArchiveTypeList.keySet().removeAll(existTypeCode);
			for(String archiveTypeCode:studyArchiveTypeList.keySet())
			{
				DictArchiveType archiveType = new DictArchiveType();
				archiveType.setArchiveTypeCode(archiveTypeCode);
				archiveType.setArchiveTypeFlag(1);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				archiveType.setArchiveTypeName(studyArchiveTypeList.get(archiveTypeCode));
				
				dictArchiveTypeService.save(archiveType);
			}
			
			// "类别，分类号，全宗号，档号，归档日期，题名，表格，归档日期，最新版本，作废版本，储存地（资料室  L3036），份数，文字张数，编制单位，中标单位，招标代理， ，备注，竣工日期，进库日期，顺序号，编制日期起，编制日期止，备考表，" +
			//"立卷人， ，审核人，审核日期，保管期限，密级，互见号，图纸，其他，路径，列号，区间号，方向，层号，节号，原文地址，附件张数，凭证号起，凭证号讫";
			Date operateTime = new Date();
			for(List<String> rowRecord:rowColsList)
			{
				
					String archiveCodeAndSn = rowRecord.get(3);
					String archiveCode = archiveCodeAndSn;
					Integer sn = 1;
					if(archiveCodeAndSn.contains("-")&&archiveCodeAndSn.split("-").length==3)
					{
						archiveCode = archiveCodeAndSn.substring(0,archiveCodeAndSn.lastIndexOf("-"));
						sn = Integer.valueOf(archiveCodeAndSn.substring(archiveCodeAndSn.lastIndexOf("-")+1));
					}
					
					String archiveTypeCode = rowRecord.get(1);//4.3.1即是类型名又是类型编号
					String archivetypeName = rowRecord.get(0);
					String archiveMarker = rowRecord.get(22);
					String archiveTitle = archivetypeName;
					
					String studyNo = rowRecord.get(5);
					String studyName = rowRecord.get(6);
					
					//String sponerName = rowRecord.get(6);
					Integer fileDateType = 1; 
					Date fileDate = null;
					if(rowRecord.get(4)!=null)
					{
						String dateStr = rowRecord.get(4);
						if(dateStr.length()==4)
						{
							dateStr+="0101";
							fileDateType=1;
							fileDate = DateUtil.stringToDate(dateStr, "yyyyMMdd");
						}else if(dateStr.length()==8){
							fileDateType = 3; 
							fileDate = DateUtil.stringToDate(dateStr, "yyyyMMdd");
						}else if(dateStr.length()==10){
							fileDateType = 3; 
							fileDate = DateUtil.stringToDate(dateStr, "yyyy/MM/dd");
						}
						
					}
					
					TblFileIndex fileIndex  = tblFileIndexService.getById(archiveCode);
					if(fileIndex==null)
					{
						fileIndex = new TblFileIndex();
						fileIndex.setArchiveCode(archiveCode);
						fileIndex.setArchiveTitle(archiveTitle);
						fileIndex.setArchiveTypeCode(archiveTypeCode);
						fileIndex.setArchiveTypeFlag(1);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
						fileIndex.setOperateTime(operateTime);
						fileIndex.setOperator(fileOperator);
						fileIndex.setStorePosition("L3036");
						fileIndex.setValidationFlag(0);
							
						tblFileIndexService.save(fileIndex);
						
					}
					
					String key = tblFileRecordService.getKey("TblFileRecord");
					TblFileRecord record = new TblFileRecord();			
					record.setArchiveMaker(archiveMarker);
					//record.setArchiveMedia(model.getTblFileRecord().getArchiveMedia());
					record.setArchiveMediaFlag(1);
				//	record.setArchiveMediaEleCode(model.getTblFileRecord().getArchiveMediaEleCode());
				//	record.setDestoryDate(model.getTblFileRecord().getDestoryDate());
					record.setFileDate(fileDate);
					record.setFileDateType(fileDateType);//年月日
					record.setFileOperator(fileOperator);
					record.setFileRecordId(key);
					record.setFileRecordSn(sn);
					//record.setKeepDate(model.getTblFileRecord().getKeepDate());
					//record.setKeyWord(model.getTblFileRecord().getKeyWord());
					record.setOperateTime(operateTime);
					record.setOperator(fileOperator);
					//record.setRemark(model.getTblFileRecord().getRemark());
					record.setTblFileIndex(fileIndex);
					
					tblFileRecordService.save(record);
					
					TblFileContentStudy fileContentStudy = new TblFileContentStudy();
					fileContentStudy.setArchiveCode(archiveCode);
					fileContentStudy.setFileRecordId(key);
					fileContentStudy.setOperateTime(operateTime);
					fileContentStudy.setOperator(fileOperator);
					//fileContentStudy.setSdname(model.getSdname());
					fileContentStudy.setStudyName(studyName);
					//fileContentStudy.setStudySponerName(sponerName);
					fileContentStudy.setStudyNo(studyNo);
					fileContentStudy.setStudyNoType(1);//1专题2合同
					//fileContentStudy.setContractCode();
					
					fileContentStudy.setTblFileRecord(record);
					try {
						tblFileContentStudyService.save(fileContentStudy);
					} catch (Exception e) {
						//该条数据不符合要求，请规范
						System.out.println("数据不符合要求："+archiveCodeAndSn);
					}
					
					
			}//for结束
		}
	}

	public void dealGlpArchive(List<List<String>> rowColsList)
	{
		if(exceptMap==null||"".equals(exceptMap))
		{
			exceptMap = new HashMap<String, Object>();
		}
		String exceptStr = "综合资料中不规范的数据行有：";
		
		String fileOperator = "曹红梅";
		List<List<String>> removeRecords = new ArrayList<List<String>>();
		List<String> archiveCodeAndSnList = new ArrayList<String>();
		
		//移除不规范的记录
		for(List<String> strL:rowColsList)
		{
			if(
				strL.get(3)==null||!strL.get(3).contains("-")
					||strL.get(3).contains("(")||strL.get(3).contains("（")
					||strL.get(3).split("-").length>3)
			{
				//类别行是不用导入的。
				removeRecords.add(strL);

				exceptStr += strL.get(3)+",";
			}
			try {
				String archiveCodeAndSn = strL.get(3);
				String archiveCode = archiveCodeAndSn;
				Integer sn = 1;
				if(archiveCodeAndSn!=null&&archiveCodeAndSn.contains("-")
						&&!archiveCodeAndSn.contains("(")&&!archiveCodeAndSn.contains("（")
						&&strL.get(3).split("-").length<=3)
				{
					if(archiveCodeAndSn.split("-").length==2)
						strL.set(3,strL.get(3)+"-001");
					else if(archiveCodeAndSn.split("-").length==3)
					{
						archiveCode = archiveCodeAndSn.substring(0,archiveCodeAndSn.lastIndexOf("-"));
						sn = Integer.valueOf(archiveCodeAndSn.substring(archiveCodeAndSn.lastIndexOf("-")+1));
						if(sn>0&&sn<10)
						{
							strL.set(3,archiveCode+"-00"+sn);
						}else if(sn<100){
							strL.set(3,archiveCode+"-0"+sn);
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			archiveCodeAndSnList.add(strL.get(3));
			
		}
		rowColsList.removeAll(removeRecords);
		removeRecords = new ArrayList<List<String>>();
		exceptStr = "\n综合资料中档案编号和盒子号已经存在的数据行有：";
		
		if(rowColsList!=null)
		{
			Map<String,String> studyArchiveTypeList = new HashMap<String,String>();
			
			List<String> existArchiveCodeSnList = tblFileRecordService.getExistByCodeAndSnList(archiveCodeAndSnList);
			//移除已经存在档案编号和序列号 的记录
			for(List<String> record:rowColsList)
			{
				for(String existCodeAndSn:existArchiveCodeSnList)
				{
					if(record.get(3)!=null&&
							record.get(3).equals(existCodeAndSn))
					{
						removeRecords.add(record);
						exceptStr += record.get(3)+",";
						continue;
					}
				}
				
				studyArchiveTypeList.put(record.get(1),record.get(0));//档案类别4.3.1
				
			}
			rowColsList.removeAll(removeRecords);
			exceptMap.put("综合资料", exceptStr);
			
			//查看不存在的类别编号，如果类别不存在则添加类别
			List<String> existTypeCode = dictArchiveTypeService.getExistTypeCodeByList(new ArrayList<String>(studyArchiveTypeList.keySet()),4);
			studyArchiveTypeList.keySet().removeAll(existTypeCode);
			for(String archiveTypeCode:studyArchiveTypeList.keySet())
			{
				DictArchiveType archiveType = new DictArchiveType();
				archiveType.setArchiveTypeCode(archiveTypeCode);
				archiveType.setArchiveTypeFlag(4);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				archiveType.setArchiveTypeName(studyArchiveTypeList.get(archiveTypeCode));
				
				dictArchiveTypeService.save(archiveType);
			}
			
			Date operateTime = new Date();
			for(List<String> rowRecord:rowColsList)
			{
				
					String archiveCodeAndSn = rowRecord.get(3);
					String archiveCode = archiveCodeAndSn;
					Integer sn = 1;
					if(archiveCodeAndSn.contains("-")&&archiveCodeAndSn.split("-").length==3)
					{
						archiveCode = archiveCodeAndSn.substring(0,archiveCodeAndSn.lastIndexOf("-"));
						sn = Integer.valueOf(archiveCodeAndSn.substring(archiveCodeAndSn.lastIndexOf("-")+1));
					}
					
					String archiveTypeCode = rowRecord.get(1);//4.3.1即是类型名又是类型编号
					String archivetypeName = rowRecord.get(0);
					String archiveMarker = rowRecord.get(22);
					String archiveTitle = archivetypeName;
					
					String docName = rowRecord.get(5);
					String department = rowRecord.get(6);
				
					
					//String sponerName = rowRecord.get(6);
					Integer fileDateType = 1; 
					Date fileDate = null;
					if(rowRecord.get(4)!=null)
					{
						String dateStr = rowRecord.get(4);
						if(dateStr.length()==4)
						{
							dateStr+="0101";
							fileDateType=1;
							fileDate = DateUtil.stringToDate(dateStr, "yyyyMMdd");
						}else if(dateStr.length()==8){
							fileDateType = 3; 
							fileDate = DateUtil.stringToDate(dateStr, "yyyyMMdd");
						}else if(dateStr.length()==10){
							fileDateType = 3; 
							fileDate = DateUtil.stringToDate(dateStr, "yyyy/MM/dd");
						}
						
					}
					
					TblFileIndex fileIndex  = tblFileIndexService.getById(archiveCode);
					if(fileIndex==null)
					{
						fileIndex = new TblFileIndex();
						fileIndex.setArchiveCode(archiveCode);
						fileIndex.setArchiveTitle(archiveTitle);
						fileIndex.setArchiveTypeCode(archiveTypeCode);
						fileIndex.setArchiveTypeFlag(4);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
						fileIndex.setOperateTime(operateTime);
						fileIndex.setOperator(fileOperator);
						fileIndex.setStorePosition("L3036");
						fileIndex.setValidationFlag(0);
							
						tblFileIndexService.save(fileIndex);
						
					}
					
					String key = tblFileRecordService.getKey("TblFileRecord");
					TblFileRecord record = new TblFileRecord();			
					record.setArchiveMaker(archiveMarker);
					//record.setArchiveMedia(model.getTblFileRecord().getArchiveMedia());
					record.setArchiveMediaFlag(1);
				//	record.setArchiveMediaEleCode(model.getTblFileRecord().getArchiveMediaEleCode());
				//	record.setDestoryDate(model.getTblFileRecord().getDestoryDate());
					record.setFileDate(fileDate);
					record.setFileDateType(fileDateType);//年月日
					record.setFileOperator(fileOperator);
					record.setFileRecordId(key);
					record.setFileRecordSn(sn);
					//record.setKeepDate(model.getTblFileRecord().getKeepDate());
					//record.setKeyWord(model.getTblFileRecord().getKeyWord());
					record.setOperateTime(operateTime);
					record.setOperator(fileOperator);
					//record.setRemark(model.getTblFileRecord().getRemark());
					record.setTblFileIndex(fileIndex);
					
					tblFileRecordService.save(record);
					
					TblFileContentGlpSynthesis fileContentGlp = new TblFileContentGlpSynthesis();
					fileContentGlp.setArchiveCode(archiveCode);
					fileContentGlp.setFileRecordId(key);
					
					
					fileContentGlp.setDepartment(department);
					fileContentGlp.setDocName(docName);
					
					fileContentGlp.setBaseGlpFlag(1);//1综合，2基建
					
					fileContentGlp.setTblFileRecord(record);
					
				
					
					try {
						tblFileContentGlpSynthesisService.save(fileContentGlp);
					} catch (Exception e) {
						//该条数据不符合要求，请规范
						System.out.println("数据不符合要求："+archiveCodeAndSn);
					}
					
					
			}//for结束
		}
		
		
	}
	
	public void dealGlpArchive2(List<List<String>> rowColsList)//基建的处理
	{
		if(exceptMap==null||"".equals(exceptMap))
		{
			exceptMap = new HashMap<String, Object>();
		}
		String exceptStr = "基建中不规范的数据行有：";
		
		String fileOperator = "曹红梅";
		List<List<String>> removeRecords = new ArrayList<List<String>>();
		List<String> archiveCodeAndSnList = new ArrayList<String>();
		
		//移除不规范的记录
		for(List<String> strL:rowColsList)
		{
			if(
				strL.get(3)==null||!strL.get(3).contains("-")
					||strL.get(3).contains("(")||strL.get(3).contains("（")
					||strL.get(3).split("-").length>3)
			{
				//类别行是不用导入的。
				removeRecords.add(strL);

				exceptStr += strL.get(3)+",";
			}
			try {
				String archiveCodeAndSn = strL.get(3);
				String archiveCode = archiveCodeAndSn;
				Integer sn = 1;
				if(archiveCodeAndSn!=null&&archiveCodeAndSn.contains("-")
						&&!archiveCodeAndSn.contains("(")&&!archiveCodeAndSn.contains("（")
						&&strL.get(3).split("-").length<=3)
				{
					if(archiveCodeAndSn.split("-").length==2)
						strL.set(3,strL.get(3)+"-001");
					else if(archiveCodeAndSn.split("-").length==3)
					{
						archiveCode = archiveCodeAndSn.substring(0,archiveCodeAndSn.lastIndexOf("-"));
						sn = Integer.valueOf(archiveCodeAndSn.substring(archiveCodeAndSn.lastIndexOf("-")+1));
						if(sn>0&&sn<10)
						{
							strL.set(3,archiveCode+"-00"+sn);
						}else if(sn<100){
							strL.set(3,archiveCode+"-0"+sn);
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			archiveCodeAndSnList.add(strL.get(3));
			
		}
		rowColsList.removeAll(removeRecords);
		removeRecords = new ArrayList<List<String>>();
		exceptStr = "\n基建中档案编号和盒子号已经存在的数据行有：";
		
		if(rowColsList!=null)
		{
			Map<String,String> studyArchiveTypeList = new HashMap<String,String>();
			
			List<String> existArchiveCodeSnList = tblFileRecordService.getExistByCodeAndSnList(archiveCodeAndSnList);
			//移除已经存在档案编号和序列号 的记录
			for(List<String> record:rowColsList)
			{
				for(String existCodeAndSn:existArchiveCodeSnList)
				{
					if(record.get(3)!=null&&
							record.get(3).equals(existCodeAndSn))
					{
						removeRecords.add(record);
						exceptStr += record.get(3)+",";
						continue;
					}
				}
				
				studyArchiveTypeList.put(record.get(1),record.get(0));//档案类别4.3.1
				
			}
			rowColsList.removeAll(removeRecords);
			
			exceptMap.put("基建", exceptStr);
			
			//查看不存在的类别编号，如果类别不存在则添加类别
			List<String> existTypeCode = dictArchiveTypeService.getExistTypeCodeByList(new ArrayList<String>(studyArchiveTypeList.keySet()),4);
			studyArchiveTypeList.keySet().removeAll(existTypeCode);
			for(String archiveTypeCode:studyArchiveTypeList.keySet())
			{
				DictArchiveType archiveType = new DictArchiveType();
				archiveType.setArchiveTypeCode(archiveTypeCode);
				archiveType.setArchiveTypeFlag(4);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				archiveType.setArchiveTypeName(studyArchiveTypeList.get(archiveTypeCode));
				
				dictArchiveTypeService.save(archiveType);
			}
			
			Date operateTime = new Date();
			for(List<String> rowRecord:rowColsList)
			{
				
					String archiveCodeAndSn = rowRecord.get(3);
					String archiveCode = archiveCodeAndSn;
					Integer sn = 1;
					if(archiveCodeAndSn.contains("-")&&archiveCodeAndSn.split("-").length==3)
					{
						archiveCode = archiveCodeAndSn.substring(0,archiveCodeAndSn.lastIndexOf("-"));
						sn = Integer.valueOf(archiveCodeAndSn.substring(archiveCodeAndSn.lastIndexOf("-")+1));
					}
					
					String archiveTypeCode = rowRecord.get(1);//4.3.1即是类型名又是类型编号
					String archivetypeName = rowRecord.get(0);
					String archiveMarker = rowRecord.get(22);
					String archiveTitle = archivetypeName;
					
					String docName = rowRecord.get(5);
					
					//String sponerName = rowRecord.get(6);
					Integer fileDateType = 1; 
					Date fileDate = null;
					if(rowRecord.get(4)!=null)
					{
						String dateStr = rowRecord.get(4);
						if(dateStr.length()==4)
						{
							dateStr+="0101";
							fileDateType=1;
							fileDate = DateUtil.stringToDate(dateStr, "yyyyMMdd");
						}else if(dateStr.length()==8){
							fileDateType = 3; 
							fileDate = DateUtil.stringToDate(dateStr, "yyyyMMdd");
						}else if(dateStr.length()==10){
							fileDateType = 3; 
							fileDate = DateUtil.stringToDate(dateStr, "yyyy/MM/dd");
						}
						
					}
					
					TblFileIndex fileIndex  = tblFileIndexService.getById(archiveCode);
					if(fileIndex==null)
					{
						fileIndex = new TblFileIndex();
						fileIndex.setArchiveCode(archiveCode);
						fileIndex.setArchiveTitle(archiveTitle);
						fileIndex.setArchiveTypeCode(archiveTypeCode);
						fileIndex.setArchiveTypeFlag(4);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
						fileIndex.setOperateTime(operateTime);
						fileIndex.setOperator(fileOperator);
						fileIndex.setStorePosition("L3036");
						fileIndex.setValidationFlag(0);
							
						tblFileIndexService.save(fileIndex);
						
					}
					
					String key = tblFileRecordService.getKey("TblFileRecord");
					TblFileRecord record = new TblFileRecord();			
					record.setArchiveMaker(archiveMarker);
					//record.setArchiveMedia(model.getTblFileRecord().getArchiveMedia());
					record.setArchiveMediaFlag(1);
				//	record.setArchiveMediaEleCode(model.getTblFileRecord().getArchiveMediaEleCode());
				//	record.setDestoryDate(model.getTblFileRecord().getDestoryDate());
					record.setFileDate(fileDate);
					record.setFileDateType(fileDateType);//年月日
					record.setFileOperator(fileOperator);
					record.setFileRecordId(key);
					record.setFileRecordSn(sn);
					//record.setKeepDate(model.getTblFileRecord().getKeepDate());
					//record.setKeyWord(model.getTblFileRecord().getKeyWord());
					record.setOperateTime(operateTime);
					record.setOperator(fileOperator);
					//record.setRemark(model.getTblFileRecord().getRemark());
					record.setTblFileIndex(fileIndex);
					
					tblFileRecordService.save(record);
					
					TblFileContentGlpSynthesis fileContentGlp = new TblFileContentGlpSynthesis();
					fileContentGlp.setArchiveCode(archiveCode);
					fileContentGlp.setFileRecordId(key);
					
					
					fileContentGlp.setDepartment("后勤保障部");
					fileContentGlp.setDocName(docName);
					
					fileContentGlp.setBaseGlpFlag(2);//1综合，2基建
					
					fileContentGlp.setTblFileRecord(record);
					
				
					
					try {
						tblFileContentGlpSynthesisService.save(fileContentGlp);
					} catch (Exception e) {
						//该条数据不符合要求，请规范
						System.out.println("数据不符合要求："+archiveCodeAndSn);
					}
					
					
			}//for结束
		}
		
		
	}
	
	public void dealEmployeeArchive(List<List<String>> rowColsList)//员工的处理
	{
		if(exceptMap==null||"".equals(exceptMap))
		{
			exceptMap = new HashMap<String, Object>();
		}
		//人事没有不符合项目
		String exceptStr = "人事中档案编号和盒子号已经存在的数据行有：";
		
		String fileOperator = "曹红梅";
		List<List<String>> removeRecords = new ArrayList<List<String>>();
		List<String> empCodeList = new ArrayList<String>();
		
		//移除不规范的记录
		for(List<String> strL:rowColsList)
		{
			empCodeList.add(strL.get(2));
			
		}
		
		if(rowColsList!=null)
		{
			Map<String,String> studyArchiveTypeList = new HashMap<String,String>();
			
			List<String> existArchiveCodeSnList = tblFileContentEmployeeService.getExistByCodeList(empCodeList);
			//移除已经存在档案编号和序列号 的记录
			for(List<String> record:rowColsList)
			{
				for(String existCodeAndSn:existArchiveCodeSnList)
				{
					if(record.get(2)!=null&&
							record.get(2).equalsIgnoreCase(existCodeAndSn))
					{
						removeRecords.add(record);

						exceptStr += record.get(2)+",";
						continue;
					}
				}
				
				
			}
			rowColsList.removeAll(removeRecords);
			
			
			//查看不存在的类别编号，如果类别不存在则添加类别
			List<String> empTypeList= new ArrayList<String>();
			empTypeList.add("2");
			List<String> existTypeCode = dictArchiveTypeService.getExistTypeCodeByList(empTypeList,6);
			//studyArchiveTypeList.keySet().removeAll(existTypeCode);
			if(existTypeCode==null||existTypeCode.size()==0)
			{
				DictArchiveType archiveType = new DictArchiveType();
				archiveType.setArchiveTypeCode("2");
				archiveType.setArchiveTypeFlag(6);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				archiveType.setArchiveTypeName("人员档案");
				
				dictArchiveTypeService.save(archiveType);
			}
			
			Date operateTime = new Date();
			for(List<String> rowRecord:rowColsList)
			{
				//人员档案的默认
					String archiveCode = "XSZK-2";
					Integer sn = 1;
					
					String archiveTypeCode = "2";//4.3.1即是类型名又是类型编号
					String archivetypeName = "人员档案";
					String archiveMarker = "曹红梅";
					String archiveTitle = "人员档案";
					
					String staffName = rowRecord.get(1);
					String staffCode = rowRecord.get(2);
					//String staffDept = rowRecord.get(5);
					String storePosition = rowRecord.get(0);
					
					//String sponerName = rowRecord.get(6);
					
					TblFileIndex fileIndex  = tblFileIndexService.getById(archiveCode);
					if(fileIndex==null)
					{
						fileIndex = new TblFileIndex();
						fileIndex.setArchiveCode(archiveCode);
						fileIndex.setArchiveTitle(archiveTitle);
						fileIndex.setArchiveTypeCode(archiveTypeCode);
						fileIndex.setArchiveTypeFlag(6);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
						fileIndex.setOperateTime(operateTime);
						fileIndex.setOperator(fileOperator);
						fileIndex.setStorePosition(storePosition);
						fileIndex.setValidationFlag(0);
							
						tblFileIndexService.save(fileIndex);
						
					}
					
					String key = tblFileRecordService.getKey("TblFileRecord");
					TblFileRecord record = new TblFileRecord();			
					record.setArchiveMaker(archiveMarker);
					//record.setArchiveMedia(model.getTblFileRecord().getArchiveMedia());
					record.setArchiveMediaFlag(1);
				//	record.setArchiveMediaEleCode(model.getTblFileRecord().getArchiveMediaEleCode());
				//	record.setDestoryDate(model.getTblFileRecord().getDestoryDate());
					//record.setFileDate(fileDate);
					//record.setFileDateType(fileDateType);//年月日
					
					record.setFileOperator(fileOperator);
					record.setFileRecordId(key);
					record.setFileRecordSn(sn);
					//record.setKeepDate(model.getTblFileRecord().getKeepDate());
					//record.setKeyWord(model.getTblFileRecord().getKeyWord());
					record.setOperateTime(operateTime);
					record.setOperator(fileOperator);
					//record.setRemark(model.getTblFileRecord().getRemark());
					record.setTblFileIndex(fileIndex);
					
					tblFileRecordService.save(record);
					
					TblFileContentEmployee fileContentEmp = new TblFileContentEmployee();
					fileContentEmp.setArchiveCode(archiveCode);
					fileContentEmp.setFileRecordId(key);
					
					fileContentEmp.setStaffName(staffName);
					fileContentEmp.setStaffCode(staffCode);
					//fileContentEmp.setStaffDept(staffDept);
					fileContentEmp.setStaffState(1);//1在职 2离职
					
					fileContentEmp.setTblFileRecord(record);
					
					try {
						tblFileContentEmployeeService.save(fileContentEmp);
					} catch (Exception e) {
						//该条数据不符合要求，请规范
						System.out.println("数据不符合要求："+staffName+"=="+staffCode);
					}
					
					
			}//for结束
		}
		
		
	}
	
	public void dealSmplReserveArchive(List<List<String>> rowColsList)//留样
	{
		if(exceptMap==null||"".equals(exceptMap))
		{
			exceptMap = new HashMap<String, Object>();
		}
		String exceptStr = "留样不规范的数据行有：";
		
		String fileOperator = "曹红梅";
		List<List<String>> removeRecords = new ArrayList<List<String>>();
		List<String> archiveCodeAndSnList = new ArrayList<String>();
		
		//移除不规范的记录
		for(List<String> strL:rowColsList)
		{
			if(strL.get(5).split(" ").length<2
					||strL.get(3)==null||!strL.get(3).contains("-")
					||strL.get(3).contains("(")||strL.get(3).contains("（"))
			{
				//类别行是不用导入的。
				removeRecords.add(strL);
				exceptStr += strL.get(3)+",";
			}
			try {
				String archiveCodeAndSn = strL.get(3);
				String archiveCode = archiveCodeAndSn;
				Integer sn = 1;
				if(archiveCodeAndSn!=null&&archiveCodeAndSn.contains("-")
						&&!archiveCodeAndSn.contains("(")&&!archiveCodeAndSn.contains("（"))
				{
					if(archiveCodeAndSn.split("-").length==2)
						strL.set(3,strL.get(3)+"-001");
					else if(archiveCodeAndSn.split("-").length==3)
					{
						archiveCode = archiveCodeAndSn.substring(0,archiveCodeAndSn.lastIndexOf("-"));
						sn = Integer.valueOf(archiveCodeAndSn.substring(archiveCodeAndSn.lastIndexOf("-")+1));
						if(sn>0&&sn<10)
						{
							strL.set(3,archiveCode+"-00"+sn);
						}else if(sn<100){
							strL.set(3,archiveCode+"-0"+sn);
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			archiveCodeAndSnList.add(strL.get(3));
		}
		rowColsList.removeAll(removeRecords);
		removeRecords = new ArrayList<List<String>>();
		exceptStr = "\n留样中档案编号和盒子号已经存在的数据行有：";
		
		if(rowColsList!=null)
		{
			Map<String,String> studyArchiveTypeList = new HashMap<String,String>();
			
			List<String> existArchiveCodeSnList = tblFileRecordService.getExistByCodeAndSnList(archiveCodeAndSnList);
			//移除已经存在档案编号和序列号 的记录
			for(List<String> record:rowColsList)
			{
				for(String existCodeAndSn:existArchiveCodeSnList)
				{
					if(record.get(3)!=null&&
							record.get(3).equals(existCodeAndSn))
					{
						removeRecords.add(record);
						exceptStr += record.get(3)+",";
						continue;
					}
				}
				
				studyArchiveTypeList.put(record.get(1),record.get(0));//档案类别4.3.1
				
			}
			rowColsList.removeAll(removeRecords);
			exceptMap.put("留样", exceptStr);
			
			//查看不存在的类别编号，如果类别不存在则添加类别
			List<String> existTypeCode = dictArchiveTypeService.getExistTypeCodeByList(new ArrayList<String>(studyArchiveTypeList.keySet()),9);
			studyArchiveTypeList.keySet().removeAll(existTypeCode);
			for(String archiveTypeCode:studyArchiveTypeList.keySet())
			{
				DictArchiveType archiveType = new DictArchiveType();
				archiveType.setArchiveTypeCode(archiveTypeCode);
				archiveType.setArchiveTypeFlag(1);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				archiveType.setArchiveTypeName(studyArchiveTypeList.get(archiveTypeCode));
				
				dictArchiveTypeService.save(archiveType);
			}
			
			// "类别，分类号，全宗号，档号，归档日期，题名，表格，归档日期，最新版本，作废版本，储存地（资料室  L3036），份数，文字张数，编制单位，中标单位，招标代理， ，备注，竣工日期，进库日期，顺序号，编制日期起，编制日期止，备考表，" +
			//"立卷人， ，审核人，审核日期，保管期限，密级，互见号，图纸，其他，路径，列号，区间号，方向，层号，节号，原文地址，附件张数，凭证号起，凭证号讫";
			Date operateTime = new Date();
			for(List<String> rowRecord:rowColsList)
			{
				
					String archiveCodeAndSn = rowRecord.get(3);
					String archiveCode = archiveCodeAndSn;
					Integer sn = 1;
					if(archiveCodeAndSn.contains("-")&&archiveCodeAndSn.split("-").length==3)
					{
						archiveCode = archiveCodeAndSn.substring(0,archiveCodeAndSn.lastIndexOf("-"));
						sn = Integer.valueOf(archiveCodeAndSn.substring(archiveCodeAndSn.lastIndexOf("-")+1));
					}
					
					String archiveTypeCode = rowRecord.get(1);//4.3.1即是类型名又是类型编号
					String archivetypeName = rowRecord.get(0);
					String archiveMarker = rowRecord.get(21);
					String archiveTitle = archivetypeName;

					String batchCode=null,container=null,remark=null,reportUnitName=null,reserveMan=null,
					reserveNumUnit=null,reserveRecMan=null,smplCode=null,smplName=null,smplProvUnitName=null,
					sponsorName=null,storageCondition=null,reserveBalance=null,gross=null,grossUnit=null,
					grossBalance=null,reserveNum=null,smplType=null;
					Date reserveDate=null,reserveRecDate=null,validDate=null;
					
					
					Integer fileDateType = 1; 
					Date fileDate = null;
					if(rowRecord.get(4)!=null)
					{
						String dateStr = rowRecord.get(4);
						if(dateStr.length()==4)
						{
							dateStr+="0101";
							fileDateType=1;
							fileDate = DateUtil.stringToDate(dateStr, "yyyyMMdd");
						}else if(dateStr.length()==8){
							fileDateType = 3; 
							fileDate = DateUtil.stringToDate(dateStr, "yyyyMMdd");
						}else if(dateStr.length()==10){
							fileDateType = 3; 
							fileDate = DateUtil.stringToDate(dateStr, "yyyy/MM/dd");
							if(fileDate==null)
							{
								fileDate = DateUtil.stringToDate(dateStr, "yyyy-MM-dd");
							}
						}
						
					}
					
					TblFileIndex fileIndex  = tblFileIndexService.getById(archiveCode);
					if(fileIndex==null)
					{
						fileIndex = new TblFileIndex();
						fileIndex.setArchiveCode(archiveCode);
						fileIndex.setArchiveTitle(archiveTitle);
						fileIndex.setArchiveTypeCode(archiveTypeCode);
						fileIndex.setArchiveTypeFlag(9);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
						fileIndex.setOperateTime(operateTime);
						fileIndex.setOperator(fileOperator);
						fileIndex.setStorePosition("L3036");
						fileIndex.setValidationFlag(0);
							
						tblFileIndexService.save(fileIndex);
						
					}
					
					
					TblFileRecordSmplReserve fileContentSmpl = new TblFileRecordSmplReserve();
					fileContentSmpl.setTblFileIndex(fileIndex);
					fileContentSmpl.setFileRecordId(tblFileRecordSmplReserveService.getKey("TblFileRecordSmplReserve"));
					
					fileContentSmpl.setBatchCode(batchCode);
					fileContentSmpl.setContainer(container);
					fileContentSmpl.setFileDate(fileDate);//没有fileDataType
					//record.setFileDateType(fileDateType);//年月日
					fileContentSmpl.setFileOperator(fileOperator);
					fileContentSmpl.setFileRecordSn(1);//增加的肯定是1
				//	fileContentSmpl.setKeepDate(model.getKeepDate());
				//	fileContentSmpl.setKeyWord(model.getKeyWord());
					fileContentSmpl.setOperateTime(operateTime);
					fileContentSmpl.setOperator(getCurrentUser().getRealName());
					fileContentSmpl.setRemark(remark);
					fileContentSmpl.setReportUnitName(reportUnitName);
					fileContentSmpl.setReserveDate(reserveDate);
					fileContentSmpl.setReserveMan(reserveMan);
					fileContentSmpl.setReserveNum(reserveNum);
					fileContentSmpl.setReserveNumUnit(reserveNumUnit);
					fileContentSmpl.setReserveRecDate(reserveRecDate);
					fileContentSmpl.setReserveRecMan(reserveRecMan);
					fileContentSmpl.setSmplCode(smplCode);
					fileContentSmpl.setSmplName(smplName);
					fileContentSmpl.setSmplProvUnitName(smplProvUnitName);
					fileContentSmpl.setSmplType(smplType);
					fileContentSmpl.setSponsorName(sponsorName);
					fileContentSmpl.setValidDate(validDate);
					fileContentSmpl.setStorageCondition(storageCondition);
					fileContentSmpl.setReserveBalance(reserveBalance);
					fileContentSmpl.setGross(gross);
					fileContentSmpl.setGrossUnit(grossUnit);
					fileContentSmpl.setGrossBalance(grossBalance);
					
					try {
						tblFileRecordSmplReserveService.save(fileContentSmpl);
					} catch (Exception e) {
						//该条数据不符合要求，请规范
						System.out.println("留样数据不符合要求："+archiveCodeAndSn);
					}
					
					
			}//for结束
		}
	}
	
	public File getExcelExistArchive() {
		return excelExistArchive;
	}

	public void setExcelExistArchive(File excelCodeFile) {
		this.excelExistArchive = excelCodeFile;
	}

	/*public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}*/

	//contentType的set方法是file名加ContentType
	public void setExcelExistArchiveContentType(String contentType) {
		this.contentType = contentType;
	}

	
	

}
