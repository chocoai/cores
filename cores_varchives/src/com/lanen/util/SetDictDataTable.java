package com.lanen.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lanen.model.DictDataTable;
import com.lanen.model.TblFileIndex;
import com.lanen.model.TblFileRecord;
import com.lanen.service.archive.DictDataTableService;

public class SetDictDataTable {

	
	public List<DictDataTable> getTableDesc(DictDataTableService dictDataTableService)
	{
		String dbName = "CoresArchives";
		
		List<Map<String, String>> filelds = new ArrayList<Map<String,String>>();
		
		Map<String, String> map0 = new HashMap<String, String>();
		map0.put("tableName", "TblFileIndex");
		
		map0.put("archiveCode", "档案编号");
		map0.put("archiveTypeCode", "分类代号");
		map0.put("archiveTypeFlag", "类别标志");//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
		map0.put("storePosition", "储存位置");//是专题时记录SD
		map0.put("archiveTitle", "题名");
		map0.put("validationFlag", "验证标志");//0：否；1：验证数据
		map0.put("operateTime", "操作时间");
		map0.put("operator", "操作者");
		filelds.add(map0);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("tableName", "TblFileRecord");
		
		map.put("fileRecordId", "归档记录ID");
		map.put("archiveCode", "档案编号");
		map.put("fileRecordSn", "归档记录序号");
		map.put("archiveMaker", "立卷人");
		map.put("fileOperator", "归档人");
		map.put("fileDate", "归档日期");
		map.put("keepDate", "保管期限");
		map.put("destoryDate", "销毁日期");
		map.put("destoryRegSign", "销毁登记人签名");
		map.put("archiveMediaFlag", "归档介质");//1：纸质；2：电子；3：其他
		map.put("archiveMedia", "归档介质说明");
		map.put("operateTime", "操作时间");
		map.put("operator", "操作者");
		map.put("remark", "归档备注");
		map.put("keyWord", "关键字");
		map.put("delFlag", "删除标记");//0：未删除；1：删除
		map.put("delTime", "删除时间");
		map.put("archiveMediaEleCode", "电子档编号");
		filelds.add(map);
		
		
		
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("tableName", "TblFileContentAdministration");
		
		map1.put("fileRecordId", "归档记录ID");
		map1.put("archiveCode", "档案编号");
		map1.put("docTypeFlag", "类别代码");//1：GLP相关资料；2：外来文件；3：内部发文；4：人字头文件
		map1.put("docTypeName", "列别名称");
		map1.put("docName", "资料名称");
		map1.put("docCode", "资料编号");
		map1.put("dispatchUnit", "发文单位");
		map1.put("dispatchDate", "发文时间");
		map1.put("receiptMan", "收件人");
		map1.put("receiptDate", "收件时间");
		filelds.add(map1);
		
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("tableName", "TblFileContentContract");
		
		map2.put("fileRecordId", "归档记录ID");
		map2.put("archiveCode", "档案编号");
		map2.put("contractCode", "合同编号");
		map2.put("contractName", "合同名称");
		map2.put("contractTypeFlag", "合同类型");
		map2.put("sponsorName", "委托方");
		map2.put("num", "份数");
		map2.put("beginDate", "开始日期");
		map2.put("endDate", "结束日期");
		map2.put("terminalDate", "终止日期");
		filelds.add(map2);
		
		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("tableName", "TblFileContentEmployee");
		
		map3.put("fileRecordId", "归档记录ID");
		map3.put("archiveCode", "档案编号");
		map3.put("staffName", "姓名");
		map3.put("staffCode", "工号");
		map3.put("staffDept", "部门");
		map3.put("staffState", "员工状态");
		filelds.add(map3);
		
		Map<String, String> map4 = new HashMap<String, String>();
		map4.put("tableName", "TblFileContentGlpSynthesis");
		
		map4.put("fileRecordId", "归档记录ID");
		map4.put("archiveCode", "档案编号");
		map4.put("department", "部门");
		map4.put("docName", "资料名称");
		filelds.add(map4);
		
		Map<String, String> map5 = new HashMap<String, String>();
		map5.put("tableName", "TblFileContentInstrument");
		
		map5.put("fileRecordId", "归档记录ID");
		map5.put("archiveCode", "档案编号");
		map5.put("instrumentId", "仪器编号");
		map5.put("instrumentName", "仪器名称");
		map5.put("instrumentModel", "仪器型号");
		map5.put("instrumentManufacturer", "仪器厂商");
		map5.put("instrumentPurchaseDate", "购买日期");
		filelds.add(map5);
		
		Map<String, String> map6 = new HashMap<String, String>();
		map6.put("tableName", "TblFileContentQacheck");
		
		map6.put("fileRecordId", "归档记录ID");
		map6.put("archiveCode", "档案编号");
		map6.put("checkItemType", "检查项类型标志");//1：专题；2：合同；3：设施检查；4：基于过程
		map6.put("studyNo", "合同/专题编号");
		map6.put("checkItemName", "合同/专题/检查项名称");//是专题时，记录专题名称
		map6.put("sdname", "SD名称");//是专题时记录SD
		map6.put("inspector", "QA检查员");
		map6.put("operateTime", "操作时间");
		map6.put("operator", "操作者");
		filelds.add(map6);
		
		Map<String, String> map7 = new HashMap<String, String>();
		map7.put("tableName", "TblFileContentSop");

		map7.put("fileRecordId", "归档记录ID");
		map7.put("archiveCode", "档案编号");
		map7.put("soptypeCode", "SOP类别编号");
		map7.put("soptypeName", "SOP类别名称");
		map7.put("sopcode", "SOP编号");//1：专题；2：合同；3：设施检查；4：基于过程
		map7.put("sopname", "SOP名称");
		map7.put("sopver", "SOP版本");//是专题时，记录专题名称
		map7.put("sopeffectiveDate", "SOP生效日期");//是专题时记录SD
		map7.put("sopinvalidDate", "SOP作废日期");
		filelds.add(map7);
		
		Map<String, String> map8 = new HashMap<String, String>();
		map8.put("tableName", "TblFileContentStudy");
		
		map8.put("fileRecordId", "归档记录ID");
		map8.put("archiveCode", "档案编号");
		map8.put("studyNo", "合同/专题编号");
		map8.put("studyNoType", "编号类型");//1专题时，2合同
		map8.put("studyName", "合同/专题名称");//是专题时记录SD
		map8.put("studySponerName", "委托单位");//是专题时记录SD
		map8.put("contractCode", "合同编号");
		map8.put("sdname", "SD名称");
		map8.put("operateTime", "操作时间");
		map8.put("operator", "操作者");
		filelds.add(map8);

		Map<String, String> map9 = new HashMap<String, String>();
		map9.put("tableName", "TblFileRecordSmplReserve");
		
		map9.put("fileRecordId", "归档记录ID");
		map9.put("archiveCode", "档案编号");
		map9.put("fileRecordSn", "归档记录编号");
		map9.put("smplType", "供试品类型");//医药，农药，化学品
		map9.put("smplCode", "供试品编号");
		map9.put("smplName", "供试品名称");
		map9.put("sponsorName", "委托单位");
		map9.put("container", "供试品容器");
		map9.put("reserveNum", "供试品留样量");
		map9.put("reserveNumUnit","供试品留样单位");
		map9.put("reserveBalance","供试品留样天平");
		map9.put("gross","供试品毛重量");
		map9.put("grossUnit","供试品毛重单位");
		map9.put("grossBalance","供试品毛重天平");
		map9.put("reportUnitName", "报告出具单位");
		map9.put("smplProvUnitName", "供试品提供单位");
		map9.put("batchCode", "供试品批号");
		map9.put("validDate", "供试品有效期");
		map9.put("reserveDate", "供试品留样日期");
		map9.put("reserveMan", "留样人");
		map9.put("reserveRecMan", "接收人");
		map9.put("reserveRecDate", "接收日起");
		map9.put("fileOperator", "归档人");
		map9.put("fileDate", "归档日期");
		map9.put("keepDate", "保管期限");
		map9.put("destoryDate", "销毁日期");
		map9.put("destoryRegSign", "销毁登记人签名");
		map9.put("smplDestoryDate", "留样销毁日期");
		map9.put("smplDestoryRegSign", "留样销毁登记人签名");
		map9.put("operateTime", "操作时间");
		map9.put("storageCondition", "存储条件");
		map9.put("operator", "操作者");
		map9.put("remark", "归档备注");
		map9.put("keyWord", "关键字");
		map9.put("delFlag", "删除标记");
		map9.put("delTime", "删除时间");
		filelds.add(map9);
		
		Map<String, String> map10 = new HashMap<String, String>();
		map10.put("tableName", "TblFileRecordSpecimen");
		
		map10.put("fileRecordId", "归档记录ID");
		map10.put("archiveCode", "档案编号");
		map10.put("fileRecordSn", "归档记录编号");
		map10.put("specimenTypeFlag", "标本类型");//1：湿标本；2：蜡块；3：切片
		map10.put("studyNo", "合同/专题编号");
		map10.put("studyNoType", "编号类型");
		map10.put("studyName", "合同/专题名称");
		map10.put("sd", "SD");
		map10.put("fileNum", "归档数量");
		map10.put("fileNumUnit", "数量单位");
		map10.put("fileOperator", "归档人");
		map10.put("fileDate", "归档日期");
		map10.put("keepDate", "保管期限");
		map10.put("destoryDate", "销毁日期");
		map10.put("destoryRegSign", "销毁登记人签名");
		map10.put("specimenDestoryDate", "标本销毁日期");
		map10.put("specimenDestoryRegSign", "标本销毁登记人签名");
		map10.put("operateTime", "操作时间");
		map10.put("operator", "操作者");
		map10.put("remark", "归档备注");
		map10.put("keyWord", "关键字");
		map10.put("delFlag", "删除标记");
		map10.put("delTime", "删除时间");
		filelds.add(map10);
		
		
		List<DictDataTable> list = new ArrayList<DictDataTable>();
		for(Map<String, String> table:filelds)
		{
			for(Entry<String, String> field:table.entrySet())
			{
				String columnName = field.getKey();
				String columnValue = field.getValue();
				if(!"tableName".equals(columnName))
				{
					DictDataTable dataTable = new DictDataTable();
					dataTable.setId(dictDataTableService.getKey("DictDataTable"));
					dataTable.setDbname(dbName);
					dataTable.setTableName(table.get("tableName"));
					dataTable.setFieldName(columnName);
					dataTable.setFieldDesc(columnValue);
					
					list.add(dataTable);
				}
				
			}
			
		}
		
		
			
		return list;
	}
	public static void main(String[] args) {
		
		ApplicationContext ac = new FileSystemXmlApplicationContext("E:/apache-tomcat-6.0.32archive/webapps/cores_varchives/WEB-INF/classes/applicationContext.xml");
		/*String names[] = ac.getBeanDefinitionNames();
		for(String name:names)
		{
			System.out.println("\n"+name);
		}*/
		DictDataTableService dictDataTableService = (DictDataTableService)ac.getBean("dictDataTableServiceImpl");
		
		List<DictDataTable> list = new SetDictDataTable().getTableDesc(dictDataTableService);
		for(DictDataTable table:list)
		{
			dictDataTableService.save(table);	
			
		}
		/*List<DictDataTable> list = dictDataTableService.findAll();
		System.out.println("list size="+list.size());
		System.out.println("结束");*/
		
	}
	
}
