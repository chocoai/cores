package com.lanen.view.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;

import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.qa.DictChkItemChkTblReg;
import com.lanen.model.qa.DictChkItemQAFileReg;
import com.lanen.model.qa.DictQACheckContentTable;
import com.lanen.model.qa.DictQACheckItem;
import com.lanen.model.qa.DictQACheckTable;
import com.lanen.model.qa.QAChkIndex;
import com.lanen.model.qa.QAChkPlan;
import com.lanen.model.qa.QAChkRecord;
import com.lanen.model.qa.QAChkReport;
import com.lanen.model.qa.QAChkSop;
import com.lanen.model.qa.QAChkTblReg;
import com.lanen.model.qa.QAFileReg;

import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.qa.DictChkItemChkTblRegService;
import com.lanen.service.qa.DictChkItemQAFileRegService;
import com.lanen.service.qa.DictQACheckContentTableService;
import com.lanen.service.qa.DictQACheckItemService;
import com.lanen.service.qa.DictQACheckTableService;
import com.lanen.service.qa.QAChkIndexService;
import com.lanen.service.qa.QAChkPlanService;
import com.lanen.service.qa.QAChkRecordService;
import com.lanen.service.qa.QAChkReportService;
import com.lanen.service.qa.QAChkSopService;
import com.lanen.service.qa.QAChkTblRegService;
import com.lanen.service.qa.QAFileRegService;

import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;


@Controller
@Scope("prototype")
public class QAChkIndexAction extends BaseAction<QAChkIndex>{
	
	private static final long serialVersionUID = 4028571108726674700L;
	
	@Resource
	private QAChkIndexService qAChkIndexService;
	@Resource
	private DictChkItemQAFileRegService dictChkItemQAFileRegService;
	@Resource
	private QAFileRegService qAFileRegService;
	
	@Resource
	private QAChkSopService qAChkSopService;
	@Resource
	private QAChkPlanService qAChkPlanService;
	@Resource
	private DictQACheckTableService dictQACheckTableService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private QAChkReportService qAChkReportService;
	@Resource
	private DictChkItemChkTblRegService dictChkItemChkTblRegService;
	@Resource
	private DictQACheckItemService dictQACheckItemService;
	@Resource
	private QAChkRecordService qAChkRecordService;
	@Resource
	private QAChkTblRegService qAChkTblRegService;
	@Resource
	private DictQACheckContentTableService dictQACheckContentTableService;
	
	private String chkTypeName;
	//查询条件
	private Date startChkDate;
	private Date endChkDate;
	private String chkReportCode;
	
	private String chkIndexs;
	//private String chkPlanId;

	public QAChkPlanService getqAChkPlanService() {
		return qAChkPlanService;
	}

	public void setqAChkPlanService(QAChkPlanService qAChkPlanService) {
		this.qAChkPlanService = qAChkPlanService;
	}

	public String list()
	{
		return "list";
	}
	
	public void save()
	{
		Map<String,Object> map = new HashMap<String, Object>();
	
		QAChkIndex oneChkIndex = new QAChkIndex();
		String key=qAChkIndexService.getKey("QAChkIndex");
		oneChkIndex.setChkIndexId(key);
		oneChkIndex.setChkItemName(model.getChkItemName().substring(model.getChkItemName().lastIndexOf(":")+1));//chkItemName是由taskName：itemName组成的
		QAChkPlan plan = null;
		if(model.getChkPlanId().contains("s:"))
		{
			//方案或者报告是没有检查计划的
		}else {
			plan= qAChkPlanService.getById(model.getChkPlanId().substring(0,model.getChkPlanId().indexOf(":")));
			plan.setChkOperator(model.getOperator());
			plan.setChkTime(DateUtil.getTodayDate());
			qAChkPlanService.update(plan);//更新检查记录中的实际操作者和检查时间
			
			oneChkIndex.setChkPlanId(model.getChkPlanId().substring(0,model.getChkPlanId().indexOf(":")));
			//oneChkIndex.setChkPlan(plan);	
		}
		
		oneChkIndex.setChkState(1);//1检查中
		
		if(chkTypeName.equals("研究"))
		{
			oneChkIndex.setChkType(1);//1:研究；2：过程；3：设施；4方案；5：报告
		}
		if(chkTypeName.equals("方案"))
		{
			oneChkIndex.setChkType(4);
		}
		if(chkTypeName.equals("报告"))
		{
			oneChkIndex.setChkType(5);
		}
		oneChkIndex.setCreateTime(DateUtil.getTodayDate());
		oneChkIndex.setOperator(model.getOperator());
		oneChkIndex.setStudyNo(model.getStudyNo());
		qAChkIndexService.save(oneChkIndex);
		
		if(plan!=null)
		{
			plan.setChkIndex(oneChkIndex);
			qAChkPlanService.update(plan);//更新chkplan
		}
		//建立检查索引要根据字典新建检查SOP	
		String chkItemId = model.getChkPlanId().substring(model.getChkPlanId().lastIndexOf(":")+1);
		
		List<DictChkItemQAFileReg> regs=dictChkItemQAFileRegService.getByChkItemId(chkItemId);
		String[] fileRegIds = new String[regs.size()];
		for(int i=0;i<regs.size();i++)
		{
			DictChkItemQAFileReg reg =regs.get(i);
			fileRegIds[i]= reg.getFileRegId();
			
		}
		List<QAFileReg> files = qAFileRegService.getByIds(fileRegIds);
		if(files!=null)
		{
			for(QAFileReg reg:files)
			{
				QAChkSop sop = new QAChkSop();
				String keySop = qAChkSopService.getKey("QAChkSop");
				sop.setFileRecordId(reg.getFileRegId());
				sop.setQachkIndex(oneChkIndex);
				sop.setSopCode(reg.getFileCode());
				sop.setSopName(reg.getFileName());
				sop.setSopPublishDepartment(reg.getFilePublishDepartment());
				sop.setSopPublishTime(reg.getFilePublishTime());
				sop.setSopRecordId(keySop);
				sop.setSopVersion(reg.getFileVersion());
				qAChkSopService.save(sop);
			}
		}
		
		
		map.put("chkIndexId", key);
		map.put("status", 1);
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}

	public void getByChkTime()
	{
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();

		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(endChkDate);
		calendar1.add(Calendar.DATE, 1);
		endChkDate = calendar1.getTime();
		
		//加入报告中项
		//QAChkReport report = qAChkReportService.getById(chkReportCode);
		User user = (User)ActionContext.getContext().getSession().get("user");
		if(chkReportCode!=null&&!"".equals(chkReportCode))
		{
			List<String> reportIndexs = qAChkIndexService.getByReport(chkReportCode,startChkDate,endChkDate,user);
			if(reportIndexs!=null)
			{
				for(String indexId:reportIndexs)
				{
					QAChkIndex index = qAChkIndexService.getById(indexId);
					
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("chkIndexId", index.getChkIndexId());
					map.put("timeAndItem", DateUtil.dateToString(index.getChkFinishTime(),"yyyy-MM-dd")+":"+index.getChkItemName());
					
					mapList.add(map);
				}
			}
		}
		QAChkReport report = qAChkReportService.getById(chkReportCode);
		if(report==null||(report!=null&&report.getRptState()!=-2))
		{
		
			List<QAChkIndex> indexs = qAChkIndexService.getByStudyNoAndTime(model.getStudyNo(),startChkDate,endChkDate,user);
			/*private String chkIndexId;
		     private QAChkReport qachkReport;
		     private String chkPlanId;
		     private Integer chkType;
		     private String studyNo;
		     private String chkItemName;
		     private String operator;
		     private java.util.Date createTime;
		     private Integer chkState;//0:：草稿；1：检查中（启动）2：完成
		     private java.util.Date chkFinishTime;*/
			if(indexs!=null)
			{
				for(QAChkIndex index:indexs)
				{
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("chkIndexId", index.getChkIndexId());
					map.put("timeAndItem", DateUtil.dateToString(index.getChkFinishTime(),"yyyy-MM-dd")+":"+index.getChkItemName());
					
					mapList.add(map);
				}
			}
			
		}
		String json = JsonPluginsUtil.beanListToJson(mapList, "yyyy-MM-dd");
		writeJson(json);
	}
	
	public void noSignChkIndexStatus()
	{
		String[] chkIndexIdList = chkIndexs.split(",");
		List<QAChkIndex> qAChkIndexList = qAChkIndexService.getNoSignByIds(chkIndexIdList);
		
		
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		if(qAChkIndexList!=null&&qAChkIndexList.size()>0)
		{
			for(QAChkIndex qAChkIndex:qAChkIndexList){
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("chkIndexId", qAChkIndex.getChkIndexId());
				//num isValid
				Map<String,Object> recordSizeAndValid = qAChkRecordService.getSizeAndValidByChkIndexId(qAChkIndex.getChkIndexId());
				//Integer QAChkTblReg = qAChkTblRegService.getSizeByChkIndexId(qAChkIndex.getChkIndexId());
				map.put("chkType", qAChkIndex.getChkType());
				//获取检查项目的所有检查表
				//1:研究；2：过程；3：设施；4方案；5：报告chkIndex
				//1：方案；2：报告；3：变更；4：基于研究的检查项 dictChkItem
				int itemType=4;
				if(qAChkIndex.getChkType()==1)
					itemType=4;
				else if(qAChkIndex.getChkType()==4)
					itemType=1;
				else if(qAChkIndex.getChkType()==5)
					itemType=2;
				
				DictQACheckItem item = dictQACheckItemService.getByItemName(itemType, qAChkIndex.getChkItemName());
				map.put("chkItemName", item.getChkItemName());
				if(item!=null)
				{
					Integer dictChkContentSize = dictQACheckItemService.getTblConSizeByItem(item.getChkItemId());
					//code name没有完成检查的表code和name,如果个数不相等就是没有检查完毕
					//List<Map<String, Object>> noFinTable = qAChkIndexService.getNoFinTableByItem(item.getChkItemId());
					
					//num isValid
					if(recordSizeAndValid!=null&&(Integer)recordSizeAndValid.get("num")>0)
					{
						//记录不会重复，可以根据个数来判断是否检查完毕
						//检查表存在就证明有检查记录存在
						if(dictChkContentSize!=null&&dictChkContentSize.equals((Integer)recordSizeAndValid.get("num")))
						{
							boolean isValid = (Boolean)recordSizeAndValid.get("isValid");
							
							if(!isValid){
								map.put("success", false);
								map.put("msg", "该检查项无有效检查内容");
								
							}else{
								//检查完毕
								map.put("success", true);
								map.put("finish", true);
								map.put("msg", "已完成，可以签字");
								/*
								if(noFinTable!=null&&noFinTable.size()>0)
								{
									String noFinishTbl = "";
									for(Map<String, Object> tempMap:noFinTable)
									{
										if(!"".equals(noFinishTbl))
											noFinishTbl+=",";
										noFinishTbl+=tempMap.get("name");
									}
								}
								
								if(!"".equals(otherTables))
								{
									map.put("otherTables", otherTables);
								}
								 */
							}
							
						}else {
							//没有检查完毕
							map.put("success", false);
							map.put("msg", "该检查项的检查表内容还没有检查完毕");
						}
						
					}else {
						map.put("success", false);
						map.put("msg", "该检查项还没开始检查");
					}
				}else{
					map.put("success", false);
					map.put("msg", "该检查项已经被删除");
				}
				mapList.add(map);
			}
			
		}
		
		
		writeJson(JsonPluginsUtil.beanListToJson(mapList));
		
	}
	
	public void isFinish()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("finish", false);
		map.put("success", true);
		
		QAChkIndex qAChkIndex =qAChkIndexService.getById(model.getChkIndexId());
		//Set<QAChkRecord> records = qAChkIndex.getQachkRecords();//检查记录
		//Set<QAChkTblReg> tblRegs = qAChkIndex.getQachkTblRegs();//检查表记录
		List<QAChkRecord> records = qAChkRecordService.getByChkIndexId(qAChkIndex.getChkIndexId());
		List<QAChkTblReg> tblRegs = qAChkTblRegService.getByChkIndexId(qAChkIndex.getChkIndexId());
		//获取检查项目的所有检查表
		//1:研究；2：过程；3：设施；4方案；5：报告
		//1：方案；2：报告；3：变更；4：基于研究的检查项
		int itemType=4;
		if(qAChkIndex.getChkType()==1)
			itemType=4;
		else if(qAChkIndex.getChkType()==4)
			itemType=1;
		else if(qAChkIndex.getChkType()==5)
			itemType=2;
		
		DictQACheckItem item = dictQACheckItemService.getByItemName(itemType, qAChkIndex.getChkItemName());
		if(item!=null)
		{
			List<DictChkItemChkTblReg> dictChkItemChkTblRegs = dictChkItemChkTblRegService.getByChkItemId(item.getChkItemId());
			
			if(tblRegs!=null&&tblRegs.size()>0)
			{
				Integer contentsSize = 0;
				for (QAChkTblReg tblReg:tblRegs) {
					//一个检查项或许有多个检查表
					List<DictQACheckTable> tables =dictQACheckTableService.getByChkTblCodeAndName(tblReg.getChkTblCode(), tblReg.getChkTblName());
					if(tables!=null&&tables.size()>0)
					{
						Integer contentSize = dictQACheckContentTableService.getSizeByTable(tables.get(0).getChkTblId());
						//Set<DictQACheckContentTable> contents = tables.get(0).getDictQacheckContentTables();
						//contentsSize+=contents.size();
						contentsSize+=contentSize;
						DictChkItemChkTblReg temp=null;
						for(DictChkItemChkTblReg itemReg: dictChkItemChkTblRegs)
						{
							if(tblReg.getChkTblCode().equals(itemReg.getDictQacheckTable().getChkTblCode())&&
									tblReg.getChkTblName().equals(itemReg.getDictQacheckTable().getChkTblName()))
							{
								temp=itemReg;
								break;
							}
						}
						dictChkItemChkTblRegs.remove(temp);
					}
				}
				//记录不会重复，可以根据个数来判断是否检查完毕
				//检查表存在就证明有检查记录存在
				if(contentsSize==records.size())
				{
					boolean isValid = false;
					for(QAChkRecord record:records)
					{
						if(record.getChkResultFlag()!=0)
						{
							isValid=true;
							break;
						}
					}
					if(!isValid){
						map.put("success", false);
						map.put("msg", "该检查项无有效检查内容");
						
					}else{
						//检查完毕
						map.put("success", true);
						map.put("finish", true);
						String otherTables="";
						if(dictChkItemChkTblRegs.size()>0)
						{
							for(DictChkItemChkTblReg itemReg: dictChkItemChkTblRegs)
							{
								if(!"".equals(otherTables))
									otherTables+=",";
								otherTables+=itemReg.getDictQacheckTable().getChkTblName();
							}
						}
						if(!"".equals(otherTables))
						{
							map.put("otherTables", otherTables);
						}
					}
					
				}else {
					//没有检查完毕
					map.put("success", false);
					map.put("msg", "该检查项的检查表内容还没有检查完毕");
				}
			
			}else {
				map.put("success", false);
				map.put("msg", "该检查项还没开始检查");
			}
		}else{
			map.put("success", false);
			map.put("msg", "该检查项已经被删除");
		}
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	public void sign()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		

		QAChkIndex qAChkIndex =qAChkIndexService.getById(model.getChkIndexId());
		//日志录入
		QAChkPlan plan = qAChkPlanService.getByChkIndex(model.getChkIndexId());
		if(plan==null)
		{
			String typeName = "";
			if(qAChkIndex.getChkType()==4)//1:研究；2：过程；3：设施；4方案；5：报告
				typeName="方案";
			if(qAChkIndex.getChkType()==5)//1:研究；2：过程；3：设施；4方案；5：报告
				typeName="报告";
			
			//es.setEsTypeDesc(typeName+"的一个检查项检查完毕");
			//esLink.setEsTypeDesc(typeName+"的一个检查项检查完毕签字确认");
			writeES(typeName+"的一个检查项检查完毕",802,"QAChkIndex",model.getChkIndexId());
			
			writeLog("提交检查记录","一个检查项的检查记录提交","提交"+typeName+"的一个检查项的检查记录,专题编号："+qAChkIndex.getStudyNo()
				+" 检查类型："+typeName+"   检查项："+qAChkIndex.getChkItemName());
		}else {
			plan.setChkFinishedFlag(1);
			qAChkPlanService.update(plan);
			//es.setEsTypeDesc("基于研究的一个检查项检查完毕");
			//esLink.setEsTypeDesc("基于研究的一个检查项检查完毕签字确认");
			writeES("基于研究的一个检查项检查完毕",802,"QAChkIndex",model.getChkIndexId());
			
			writeLog("提交检查记录","一个检查项的检查记录提交","提交基于研究的一个检查项的检查记录,专题编号："+qAChkIndex.getStudyNo()
					+" 检查类型：基于研究     检查项："+qAChkIndex.getChkItemName());
		}
		
		//检查索引
		qAChkIndex.setChkState(2);//0:草稿；1：检查中（启动）2：完成
		qAChkIndex.setChkFinishTime(new Date());

		qAChkIndexService.update(qAChkIndex);
		map.put("success", true);
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void signMany()
	{
		String[] manyChkIndex = chkIndexs.split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<QAChkIndex> qAChkIndexList =qAChkIndexService.getByIds(manyChkIndex);
		for(QAChkIndex qAChkIndex:qAChkIndexList)
		{
			//日志录入
			QAChkPlan plan = qAChkPlanService.getByChkIndex(qAChkIndex.getChkIndexId());
			if(plan==null)
			{
				String typeName = "";
				if(qAChkIndex.getChkType()==4)//1:研究；2：过程；3：设施；4方案；5：报告
					typeName="方案";
				if(qAChkIndex.getChkType()==5)//1:研究；2：过程；3：设施；4方案；5：报告
					typeName="报告";
				
				//es.setEsTypeDesc(typeName+"的一个检查项检查完毕");
				//esLink.setEsTypeDesc(typeName+"的一个检查项检查完毕签字确认");
				writeES(typeName+"的一个检查项检查完毕",802,"QAChkIndex",qAChkIndex.getChkIndexId());
				
				writeLog("提交检查记录","一个检查项的检查记录提交","提交"+typeName+"的一个检查项的检查记录,专题编号："+qAChkIndex.getStudyNo()
					+" 检查类型："+typeName+"   检查项："+qAChkIndex.getChkItemName());
			}else {
				plan.setChkFinishedFlag(1);
				qAChkPlanService.update(plan);
				//es.setEsTypeDesc("基于研究的一个检查项检查完毕");
				//esLink.setEsTypeDesc("基于研究的一个检查项检查完毕签字确认");
				writeES("基于研究的一个检查项检查完毕",802,"QAChkIndex",qAChkIndex.getChkIndexId());
				
				writeLog("提交检查记录","一个检查项的检查记录提交","提交基于研究的一个检查项的检查记录,专题编号："+qAChkIndex.getStudyNo()
						+" 检查类型：基于研究     检查项："+qAChkIndex.getChkItemName());
			}
			
			//检查索引
			qAChkIndex.setChkState(2);//0:草稿；1：检查中（启动）2：完成
			qAChkIndex.setChkFinishTime(new Date());

			qAChkIndexService.update(qAChkIndex);
		}
		map.put("success", true);
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	private String writeES(String EsTypeDesc,int EsType,String tableName,String dataId)
	{
		//签名链接
		TblESLink esLink = new TblESLink();
		//电子签名
		TblES es = new TblES();
		//验证通过则进行一下操作
		
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		es.setSigner(tempUser.getRealName());
		es.setEsTypeDesc(EsTypeDesc);
		es.setEsType(EsType);
		es.setDateTime(new Date());
		String eid = tblESService.getKey("TblES");
		es.setEsId(eid);
	
		tblESService.save(es);
		
		esLink.setTableName(tableName);
		esLink.setDataId(dataId);
		esLink.setTblES(es);
		esLink.setEsType(EsType);
		esLink.setEsTypeDesc(EsTypeDesc+"签字确认");
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		tblESLinkService.save(esLink);
		
		return esLink.getLinkId();
		
	}
	/**
	 * 写日志
	 * @return
	 */
	private void writeLog(String operatType,String operatOjbect,String operatContent){
		
		  TblLog tblLog = new TblLog();
		  tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
		  tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
		  tblLog.setOperatType(operatType);
		  tblLog.setOperatOject(operatOjbect);
		  User user = (User) ActionContext.getContext().getSession().get("user");
		  if(null!=user){
			  tblLog.setOperator(user.getRealName());
		  }
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}
	public QAChkIndexService getqAChkIndexService() {
		return qAChkIndexService;
	}

	public void setqAChkIndexService(QAChkIndexService qAChkIndexService) {
		this.qAChkIndexService = qAChkIndexService;
	}

	public QAChkSopService getqAChkSopService() {
		return qAChkSopService;
	}

	public void setqAChkSopService(QAChkSopService qAChkSopService) {
		this.qAChkSopService = qAChkSopService;
	}

	public DictChkItemQAFileRegService getDictChkItemQAFileRegService() {
		return dictChkItemQAFileRegService;
	}

	public void setDictChkItemQAFileRegService(
			DictChkItemQAFileRegService dictChkItemQAFileRegService) {
		this.dictChkItemQAFileRegService = dictChkItemQAFileRegService;
	}

	public QAFileRegService getqAFileRegService() {
		return qAFileRegService;
	}

	public void setqAFileRegService(QAFileRegService qAFileRegService) {
		this.qAFileRegService = qAFileRegService;
	}

	public Date getStartChkDate() {
		return startChkDate;
	}

	public void setStartChkDate(Date startChkDate) {
		this.startChkDate = startChkDate;
	}

	public Date getEndChkDate() {
		return endChkDate;
	}

	public void setEndChkDate(Date endChkDate) {
		this.endChkDate = endChkDate;
	}



	public DictQACheckTableService getDictQACheckTableService() {
		return dictQACheckTableService;
	}

	public void setDictQACheckTableService(
			DictQACheckTableService dictQACheckTableService) {
		this.dictQACheckTableService = dictQACheckTableService;
	}

	public TblESService getTblESService() {
		return tblESService;
	}

	public void setTblESService(TblESService tblESService) {
		this.tblESService = tblESService;
	}

	public TblESLinkService getTblESLinkService() {
		return tblESLinkService;
	}

	public void setTblESLinkService(TblESLinkService tblESLinkService) {
		this.tblESLinkService = tblESLinkService;
	}

	public String getChkTypeName() {
		return chkTypeName;
	}

	public void setChkTypeName(String chkTypeName) {
		this.chkTypeName = chkTypeName;
	}

	public QAChkReportService getqAChkReportService() {
		return qAChkReportService;
	}

	public void setqAChkReportService(QAChkReportService qAChkReportService) {
		this.qAChkReportService = qAChkReportService;
	}

	public String getChkReportCode() {
		return chkReportCode;
	}

	public void setChkReportCode(String chkReportCode) {
		this.chkReportCode = chkReportCode;
	}

	public DictChkItemChkTblRegService getDictChkItemChkTblRegService() {
		return dictChkItemChkTblRegService;
	}

	public void setDictChkItemChkTblRegService(
			DictChkItemChkTblRegService dictChkItemChkTblRegService) {
		this.dictChkItemChkTblRegService = dictChkItemChkTblRegService;
	}

	public String getChkIndexs() {
		return chkIndexs;
	}

	public void setChkIndexs(String chkIndexs) {
		this.chkIndexs = chkIndexs;
	}

	
	
}
