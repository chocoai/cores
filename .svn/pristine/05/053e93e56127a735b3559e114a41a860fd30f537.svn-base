package com.lanen.view.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Department;
import com.lanen.model.TblFileContentSop;
import com.lanen.model.TblFileIndex;
import com.lanen.model.TblFileRecord;
import com.lanen.model.TblLog;
import com.lanen.model.archive.DictArchiveType;
import com.lanen.service.archive.DictArchiveTypeService;
import com.lanen.service.archive.TblFileContentSopService;
import com.lanen.service.archive.TblLog2Service;
import com.lanen.util.DateUtil;
@Controller
@Scope("prototype")
public class TblLogAction extends BaseAction<TblLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6782653371953421575L;

	@Resource
	private TblLog2Service tblLog2Service;
	
	@Resource
	private TblFileContentSopService  tblFileContentSopService;
	@Resource
	private DictArchiveTypeService dictArchiveTypeService;
	
	//private Integer validationFlag;
	
	//private Date fileStartDate;
	//private Date fileEndDate;
	//private Date keepEndDate;
	//private Integer isDestory;
	//private Integer isValid;
	private String searchString;
	private Date logStartDate;
	private Date logEndDate;
	
	private Integer rows;// 每页显示的记录数 
	private Integer page;// 当前第几页 
	
	public String list()
	{
		
		return "list";
	}
	public void loadList()
	{
		if(logEndDate!=null&&!"".equals(logEndDate)){
			Calendar c = Calendar.getInstance();
			c.setTime(logEndDate);
			c.add(Calendar.DATE, 1);
			logEndDate = c.getTime();
		}
		
		Map<String, Object> resultMap = tblLog2Service.getByCondition(model.getArchiveTypeFlag(),logStartDate,logEndDate,searchString,page,rows);
		
		String json = JsonPluginsUtil.beanToJson(resultMap,"yyyy-MM-dd");
		writeJson(json);
		
	}
	
	public void getArchiveRecordDetailById()
	{
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		Map<String, Object> mapState = new HashMap<String, Object>();
		TblLog archiveLog = tblLog2Service.getById(model.getId());
		if(archiveLog!=null)
		{
			mapState.put("success", true);
			
			TblFileIndex fileIndex = null;
			TblFileRecord fileRecord = null;
			
			//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
			Integer typeFlag = archiveLog.getArchiveTypeFlag();
			if(typeFlag!=null)
			{
				switch (typeFlag) {
					
				
					case 3:
						TblFileContentSop sop = tblFileContentSopService.getById(archiveLog.getOldFileRecordId());			
						if(sop!=null){
							fileRecord = sop.getTblFileRecord();
							fileIndex = fileRecord.getTblFileIndex();
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("name", "档案编号");
							map.put("value", sop.getArchiveCode());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "SOP类别编号");
							map.put("value", sop.getSoptypeCode());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "SOP类别名称");
							map.put("value", sop.getSoptypeName());
							mapList.add(map);
							/*map = new HashMap<String, Object>();
							map.put("name", "fileRecordId");
							map.put("value", sop.getFileRecordId());
							mapList.add(map);*/
							map = new HashMap<String, Object>();
							map.put("name", "SOP编号");
							map.put("value", sop.getSopcode());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "SOP名称");
							map.put("value", sop.getSopname());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "SOP版本");
							map.put("value", sop.getSopver());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "SOP生效日期");
							map.put("value", DateUtil.dateToString(sop.getSopeffectiveDate(),"yyyy-MM-dd"));
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "SOP作废日期");
							map.put("value", DateUtil.dateToString(sop.getSopinvalidDate(),"yyyy-MM-dd"));
							mapList.add(map);
							
							
						}else{
							mapState.put("success", false);
							mapState.put("msg", "该SOP记录不存在！");
						}
						break;
					
						
					
					default:
						break;
				}
				if(fileIndex!=null&&!"".equals(fileIndex))
				{
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name", "档案分类代号");
					map.put("value", fileIndex.getArchiveTypeCode());
					mapList.add(0,map);
					map = new HashMap<String, Object>();
					map.put("name", "档案类别名称");
					DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(fileIndex.getArchiveTypeCode());
					map.put("value", dictArchiveType.getArchiveTypeName());
					mapList.add(1,map);
					map = new HashMap<String, Object>();
					map.put("name", "题名");
					map.put("value", fileIndex.getArchiveTitle());
					mapList.add(2,map);
					map = new HashMap<String, Object>();
					map.put("name", "存储位置");
					map.put("value", fileIndex.getStorePosition());
					mapList.add(map);
					map = new HashMap<String, Object>();
					map.put("name", "验证数据");
					String str = "否";
					if(fileIndex.getValidationFlag()!=null&&fileIndex.getValidationFlag()==1)
						str="是";
					map.put("value", str);//0：否；1：验证数据
					mapList.add(map);
					
				}
				if(fileRecord!=null&&!"".equals(fileRecord))
				{
					Map<String, Object> map = new  HashMap<String, Object>();
					map.put("name", "归档时间");
					map.put("value", DateUtil.dateToString(fileRecord.getFileDate(),"yyyy-MM-dd"));
					mapList.add(map);
					map = new HashMap<String, Object>();
					map.put("name", "序号");
					map.put("value", fileRecord.getFileRecordSn());
					mapList.add(map);
					map = new HashMap<String, Object>();
					map.put("name", "立卷人");
					map.put("value", fileRecord.getArchiveMaker());
					mapList.add(map);
					map = new HashMap<String, Object>();
					map.put("name", "归档人");
					map.put("value", fileRecord.getFileOperator());
					mapList.add(map);
					map = new HashMap<String, Object>();
					map.put("name", "保管期限");
					map.put("value", DateUtil.dateToString(fileRecord.getKeepDate(),"yyyy-MM-dd"));
					mapList.add(map);
					if(fileRecord.getKeepDate()!=null)
					{
						map = new HashMap<String, Object>();
						map.put("name", "销毁日期");
						map.put("value", DateUtil.dateToString(fileRecord.getDestoryDate(),"yyyy-MM-dd"));
						mapList.add(map);
					}
					map = new HashMap<String, Object>();
					map.put("name", "备注");
					map.put("value", fileRecord.getRemark());
					mapList.add(map);
					map = new HashMap<String, Object>();
					map.put("name", "归档介质");
					//1：纸质；2：电子；3：其他
					String str = "";
					if(fileRecord.getArchiveMediaFlag()!=null){
						if(fileRecord.getArchiveMediaFlag()==1)
							str="纸质";
						else if(fileRecord.getArchiveMediaFlag()==2)
							str="电子";
						else str="其他";
					}
					map.put("value", str);
					mapList.add(map);
					map = new HashMap<String, Object>();
					map.put("name", "归档介质说明");
					map.put("value", fileRecord.getArchiveMedia());
					mapList.add(map);
				}
				
			}else{
				mapState.put("success", false);
				mapState.put("success", "日志中类型不存在！");
			}
			
		}else {
			mapState.put("success", false);
			mapState.put("success", "日志不存在！");
		}
		
		
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("total",mapList.size());
		map2.put("rows", mapList);
		
		writeJson(JsonPluginsUtil.beanToJson(map2));
	}
	
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public Date getLogStartDate() {
		return logStartDate;
	}
	public void setLogStartDate(Date logStartDate) {
		this.logStartDate = logStartDate;
	}
	public Date getLogEndDate() {
		return logEndDate;
	}
	public void setLogEndDate(Date logEndDate) {
		this.logEndDate = logEndDate;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
}
