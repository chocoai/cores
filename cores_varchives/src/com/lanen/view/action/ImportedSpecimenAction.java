package com.lanen.view.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.ImportedSpecimen;
import com.lanen.model.TblFileContentSop;
import com.lanen.model.TblFileIndex;
import com.lanen.model.TblFileRecord;
import com.lanen.service.archive.ImportedSpecimenService;
import com.lanen.service.archive.TblFileContentInstrumentService;
import com.lanen.service.archive.TblFileContentSopService;
import com.lanen.service.archive.TblFileContentStudyService;
import com.lanen.service.archive.TblFileIndexService;
import com.lanen.service.archive.TblFileRecordService;
import com.lanen.util.DateUtil;
import com.lanen.util.POIExcelUtil;

@Controller
@Scope("prototype")
public class ImportedSpecimenAction extends BaseAction<ImportedSpecimen> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -461125973251919449L;
	@Resource
	private ImportedSpecimenService importedSpecimenService;
	
	
	//页面上传的文件
	private File excelCodeFile;//File对象，目的是获取页面上传的文件
	private String fileName;    
	private String contentType;//文件类型
	
	private String searchCon;
	
	private Json json;
	
	
	
	
	/**标本批量导入
	  */
	public void importExcel() {  
		json = new Json();
		
		List<String> typeList = new ArrayList<String>();
		typeList.add("application/kset");
		typeList.add("application/excel");
		typeList.add("application/vnd.ms-excel");
		typeList.add("application/msexcel");
		typeList.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		
		List<String> titleList = new ArrayList<String>();
		titleList.add("专题编号");
		titleList.add("标本编号");
		titleList.add("脏器名称");
		
		List<List<String>> rowColsList = null ;
		if(typeList.contains(contentType)){//判断下文件类型
			List<Integer> sheetList = new ArrayList<Integer>();
			sheetList.add(0);
			
			if(excelCodeFile!=null){
				POIExcelUtil excelUtil = new POIExcelUtil();
				try {
					rowColsList = excelUtil.excel2007Export(excelCodeFile,titleList,sheetList);//excel文件，文件头列表，第几个sheet
				} catch (Exception e) {
					rowColsList = excelUtil.excel2003Export(excelCodeFile,titleList,sheetList);
				}
				json = excelUtil.getJson();
				
			}else{
				json.setMsg("文件读取失败");
			}
			String  msg = json.getMsg();
			if(null==msg || "".equals(msg)){//消息为空，表示读取顺利，
				if(rowColsList!=null)
				{
					for(List<String> row:rowColsList)
					{
						List<ImportedSpecimen> existStudySpecimenList = importedSpecimenService.getByStudyNoAndSpecimenCode(row.get(0),row.get(1));
						if(existStudySpecimenList!=null&&existStudySpecimenList.size()>0)
						{
							for(ImportedSpecimen exist:existStudySpecimenList)
							{
								importedSpecimenService.delete(exist.getId());
							}
							
						}
						if(row.get(0)!=null&&row.get(1)!=null&&row.get(2)!=null)
						{
							String[] viscreas = row.get(2).split("[,，;； ]");
							for(String str:viscreas)
							{
								ImportedSpecimen specimen = new ImportedSpecimen();
								String key = importedSpecimenService.getKey("ImportedSpecimen");
								specimen.setId(key);
								specimen.setSpecimenCode(row.get(1));
								specimen.setStudyNo(row.get(0));
								specimen.setVisceraName(str);
							
								importedSpecimenService.save(specimen);
							}
						}else{
							json.setMsg("专题编号，标本编号和脏器名称都不可以为空！");
							break;
						}
						
					}
				
					
				}else {
					json.setMsg("文件中没有数据！");
				}
			}
		}else{//文件类型不对
			if("application/octet-stream".equals(contentType)){
				json.setMsg("文件被其他程序占用");
			}else{
				json.setMsg("请导入excel类型文件");
			}
		}
		
		if(null==json.getMsg() || "".equals(json.getMsg())){//消息为空，表示读取顺利，
			json.setSuccess(true);
			json.setMsg("标本导入成功！");	
		}
		
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}

	public void getSliceSpecimenByStudyNoAndCon()
	{
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		
		List<ImportedSpecimen> list = importedSpecimenService.getListByCondition(model.getStudyNo(), searchCon);
		
		Map<String, Object> map = new HashMap<String, Object>();
		for(int i=0;i<list.size();i++)
		{
			ImportedSpecimen s = list.get(i);
			
			if(map.get(s.getStudyNo()+"~"+s.getSpecimenCode())!=null)
			{
				map.put(s.getStudyNo()+"~"+s.getSpecimenCode(), map.get(s.getStudyNo()+"~"+s.getSpecimenCode())+","+s.getVisceraName());
			}else {
				map.put(s.getStudyNo()+"~"+s.getSpecimenCode(), s.getVisceraName());
			}
			
		}
		for(Entry<String, Object> entry :map.entrySet())
		{
			String[] key = entry.getKey().split("~");
			Object value = entry.getValue();
			
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("studyNo",key[0] );
			map2.put("sliceCode",key[1] );
			map2.put("visceraOrTissueName", value);
			//studyNo,'sliceCode','visceraOrTissueName
			mapList.add(map2);
		}
		
		Collections.sort(mapList,new Comparator<Map<String, Object>>() {

			public int compare(Map<String, Object> arg0,
					Map<String, Object> arg1) {
				// TODO Auto-generated method stub
				return ((String)arg0.get("sliceCode")).compareTo((String)arg1.get("sliceCode"));
			}
		});
		writeJson(JsonPluginsUtil.beanListToJson(mapList));
		
	}
	
	
	
	
	public File getExcelCodeFile() {
		return excelCodeFile;
	}

	public void setExcelCodeFile(File excelCodeFile) {
		this.excelCodeFile = excelCodeFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setExcelCodeFileContentType(String contentType) {
		this.contentType = contentType;
	}
	

	public String getSearchCon() {
		return searchCon;
	}

	public void setSearchCon(String searchCon) {
		this.searchCon = searchCon;
	}
	
	

}
