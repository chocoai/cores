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
import com.lanen.model.TblFileContentAdministration;
import com.lanen.model.TblFileContentContract;
import com.lanen.model.TblFileContentEmployee;
import com.lanen.model.TblFileContentGlpSynthesis;
import com.lanen.model.TblFileContentInstrument;
import com.lanen.model.TblFileContentQacheck;
import com.lanen.model.TblFileContentSop;
import com.lanen.model.TblFileContentStudy;
import com.lanen.model.TblFileIndex;
import com.lanen.model.TblFileRecord;
import com.lanen.model.TblFileRecordSmplReserve;
import com.lanen.model.TblFileRecordSpecimen;
import com.lanen.model.TblLog;
import com.lanen.model.archive.DictArchiveType;
import com.lanen.service.archive.DictArchiveTypeService;
import com.lanen.service.archive.TblFileContentAdministrationService;
import com.lanen.service.archive.TblFileContentContractService;
import com.lanen.service.archive.TblFileContentEmployeeService;
import com.lanen.service.archive.TblFileContentGlpSynthesisService;
import com.lanen.service.archive.TblFileContentInstrumentService;
import com.lanen.service.archive.TblFileContentQACheckService;
import com.lanen.service.archive.TblFileContentSopService;
import com.lanen.service.archive.TblFileContentStudyService;
import com.lanen.service.archive.TblFileRecordSmplReserveService;
import com.lanen.service.archive.TblFileRecordSpecimenService;
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
	//行政综合资料
	@Resource
	private TblFileContentAdministrationService tblFileContentAdministrationService;
	@Resource
	private TblFileContentContractService tblFileContentContractService;
	@Resource
	private TblFileContentEmployeeService tblFileContentEmployeeService;
	//综合资料
	@Resource
	private TblFileContentGlpSynthesisService tblFileContentGlpSynthesisService;
	@Resource
	private TblFileContentInstrumentService tblFileContentInstrumentService;
	@Resource
	private TblFileContentQACheckService tblFileContentQACheckService;
	@Resource
	private TblFileContentSopService  tblFileContentSopService;
	@Resource
	private TblFileContentStudyService tblFileContentStudyService;
	@Resource
	private TblFileRecordSmplReserveService tblFileRecordSmplReserveService;
	@Resource
	private TblFileRecordSpecimenService tblFileRecordSpecimenService;
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
					case 1:
						TblFileContentStudy study = tblFileContentStudyService.getById(archiveLog.getOldFileRecordId());
						if(study!=null){
							fileRecord = study.getTblFileRecord();
							fileIndex = fileRecord.getTblFileIndex();
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("name", "档案编号");
							map.put("value", study.getArchiveCode());
							mapList.add(map);
							map = new HashMap<String, Object>();
							/*map.put("name", "fileRecordId");
							map.put("value", study.getFileRecordId());
							mapList.add(map);*/
							map = new HashMap<String, Object>();
							map.put("name", "合同/专题编号");
							map.put("value", study.getStudyNo());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "合同/专题名称");
							map.put("value", study.getStudyName());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "委托单位");
							map.put("value", study.getStudySponerName());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "SD");
							map.put("value", study.getSdname());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "studyNoType");
							String str = "专题";
							if(study.getStudyNoType()!=null&&study.getStudyNoType()==2)
								str = "合同";
							map.put("value", str);
							mapList.add(map);
							
							
						}else{
							mapState.put("success", false);
							mapState.put("msg", "该专题记录不存在！");
						}
						
						break;
					case 2:
						TblFileContentQacheck qacheck = tblFileContentQACheckService.getById(archiveLog.getOldFileRecordId());
						if(qacheck!=null){
							fileRecord = qacheck.getTblFileRecord();
							fileIndex = fileRecord.getTblFileIndex();
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("name", "档案编号");
							map.put("value", qacheck.getArchiveCode());
							mapList.add(map);
							/*map = new HashMap<String, Object>();
							map.put("name", "fileRecordId");
							map.put("value", qacheck.getFileRecordId());
							mapList.add(map);*/
							map = new HashMap<String, Object>();
							map.put("name", "合同/专题编号");
							map.put("value", qacheck.getStudyNo());
							mapList.add(map);
							map.put("name", "合同/专题/检查项名称");
							map.put("value", qacheck.getCheckItemName());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "SD");
							map.put("value", qacheck.getSdname());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "QA");
							map.put("value", qacheck.getInspector());
							mapList.add(map);
							map = new HashMap<String, Object>();
							
							map = new HashMap<String, Object>();
							map.put("name", "编号类型");
							String str = "";
							if(qacheck.getCheckItemType()!=null){
								if(qacheck.getCheckItemType()==1)
									str = "专题";
								else if(qacheck.getCheckItemType()==2)
									str = "合同";
								else if(qacheck.getCheckItemType()==3)
									str = "设施检查";
								else if(qacheck.getCheckItemType()==4)
									str = "过程检查";
							}
							map.put("value", str);
							mapList.add(map);
							
						}else{
							mapState.put("success", false);
							mapState.put("msg", "该QA检查记录不存在！");
						}
						break;
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
					case 4:
						TblFileContentGlpSynthesis glpSynthesis= tblFileContentGlpSynthesisService.getById(archiveLog.getOldFileRecordId());
						if(glpSynthesis!=null){
							fileRecord = glpSynthesis.getTblFileRecord();
							fileIndex = fileRecord.getTblFileIndex();
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("name", "档案编号");
							map.put("value", glpSynthesis.getArchiveCode());
							mapList.add(map);
							/*map = new HashMap<String, Object>();
							map.put("name", "fileRecordId");
							map.put("value", glpSynthesis.getFileRecordId());
							mapList.add(map);*/
							map = new HashMap<String, Object>();
							map.put("name", "部门ID");
							Department department = departmentService.getByName(glpSynthesis.getDepartment());
							map.put("value", department.getId());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "部门");
							map.put("value", glpSynthesis.getDepartment());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "资料名称");
							map.put("value", glpSynthesis.getDocName());
							mapList.add(map);
							
						}else{
							mapState.put("success", false);
							mapState.put("msg", "该综合资料不存在！");
						}
						
						break;
					case 5:
						TblFileContentInstrument instrument = tblFileContentInstrumentService.getById(archiveLog.getOldFileRecordId());
						if(instrument!=null){
							fileRecord = instrument.getTblFileRecord();
							fileIndex = fileRecord.getTblFileIndex();
							
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("name", "档案编号");
							map.put("value", instrument.getArchiveCode());
							mapList.add(map);
							/*map = new HashMap<String, Object>();
							map.put("name", "fileRecordId");
							map.put("value", instrument.getFileRecordId());
							mapList.add(map);*/
							map = new HashMap<String, Object>();
							map.put("name", "仪器编号");
							
							map.put("value", instrument.getInstrumentId());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "仪器名称");
							map.put("value", instrument.getInstrumentName());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "仪器型号");
							map.put("value", instrument.getInstrumentModel());
							mapList.add(map);
							
							map = new HashMap<String, Object>();
							map.put("name", "仪器厂商");
							map.put("value", instrument.getInstrumentManufacturer());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "购买日期");
							map.put("value", DateUtil.dateToString(instrument.getInstrumentPurchaseDate(),"yyyy-MM-dd"));
							mapList.add(map);
							
						}else{
							mapState.put("success", false);
							mapState.put("msg", "该仪器资料不存在！");
						}
						
						break;
					case 6:
						TblFileContentEmployee employee = tblFileContentEmployeeService.getById(archiveLog.getOldFileRecordId());
						if(employee!=null){
							fileRecord = employee.getTblFileRecord();
							fileIndex = fileRecord.getTblFileIndex();
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("name", "档案编号");
							map.put("value", employee.getArchiveCode());
							mapList.add(map);
							/*map = new HashMap<String, Object>();
							map.put("name", "fileRecordId");
							map.put("value", employee.getFileRecordId());
							mapList.add(map);*/
							map = new HashMap<String, Object>();
							map.put("name", "姓名");
							
							map.put("value", employee.getStaffName());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "工号");
							map.put("value", employee.getStaffCode());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "部门");
							map.put("value", employee.getStaffDept());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "员工状态");
							String str = "在职";
							if(employee.getStaffState()!=null&&employee.getStaffState()==2)
								str = "离职";
							map.put("value", str);
							mapList.add(map);
							
						}else{
							mapState.put("success", false);
							mapState.put("msg", "该人员档案不存在！");
						}
						
						break;
					case 7:
						TblFileContentAdministration administration = tblFileContentAdministrationService.getById(archiveLog.getOldFileRecordId());
						if(administration!=null){
							fileRecord = administration.getTblFileRecord();
							fileIndex = fileRecord.getTblFileIndex();
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("name", "档案编号");
							map.put("value", administration.getArchiveCode());
							mapList.add(map);
							/*map = new HashMap<String, Object>();
							map.put("name", "fileRecordId");
							map.put("value", administration.getFileRecordId());
							mapList.add(map);*/
							
							map = new HashMap<String, Object>();
							map.put("name", "类别代码");
							map.put("value", administration.getDocTypeFlag());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "类别名称");
							map.put("value", administration.getDocTypeName());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "资料编号");
							map.put("value", administration.getDocCode());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "资料名称");
							map.put("value", administration.getDocName());
							mapList.add(map);
							
							map = new HashMap<String, Object>();
							map.put("name", "发文单位");
							map.put("value", administration.getDispatchUnit());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "发文时间");
							map.put("value", DateUtil.dateToString(administration.getDispatchDate(),"yyyy-MM-dd"));
							mapList.add(map);
		
							map = new HashMap<String, Object>();
							map.put("name", "收件人");
							map.put("value", administration.getReceiptMan());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "收件时间");
							map.put("value", DateUtil.dateToString(administration.getReceiptDate(),"yyyy-MM-dd"));
							mapList.add(map);
							
							
						}else{
							mapState.put("success", false);
							mapState.put("msg", "该行政综合资料不存在！");
						}
						
						break;
					case 8:
						TblFileContentContract contract = tblFileContentContractService.getById(archiveLog.getOldFileRecordId());
						if(contract!=null){
							fileRecord = contract.getTblFileRecord();
							fileIndex = fileRecord.getTblFileIndex();
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("name", "档案编号");
							map.put("value", contract.getArchiveCode());
							mapList.add(map);
							/*map = new HashMap<String, Object>();
							map.put("name", "fileRecordId");
							map.put("value", contract.getFileRecordId());
							mapList.add(map);*/
							map = new HashMap<String, Object>();
							map.put("name", "合同类型");
							String str = "专题合同";
							if(contract.getContractTypeFlag()!=null)
							{
								if(contract.getContractTypeFlag()==1)
									str="专题合同";
								else if(contract.getContractTypeFlag()==2)
									str="公司内部合同";
								else if(contract.getContractTypeFlag()==3)
									str="其他合同";
								
							}
							map.put("value",str);
							
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "合同编号");
							map.put("value",contract.getContractCode());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "合同名称");
							map.put("value",contract.getContractName());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "委托方");
							map.put("value",contract.getSponsorName());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "份数");
							map.put("value",contract.getNum());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "开始日期");
							map.put("value",DateUtil.dateToString(contract.getBeginDate(),"yyyy-MM-dd"));
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "结束日期");
							map.put("value",DateUtil.dateToString(contract.getEndDate(),"yyyy-MM-dd"));
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "终止日期");
							map.put("value",DateUtil.dateToString(contract.getTerminalDate(),"yyyy-MM-dd"));
							mapList.add(map);
							
							
						}else{
							mapState.put("success", false);
							mapState.put("msg", "该合同资料不存在！");
						}
						
						break;
					case 9:
						TblFileRecordSmplReserve smplRes = tblFileRecordSmplReserveService.getById(archiveLog.getOldFileRecordId());
						if(smplRes!=null){
							fileIndex = smplRes.getTblFileIndex();
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("name", "档案编号");
							map.put("value", smplRes.getTblFileIndex().getArchiveCode());
							mapList.add(map);
							/*map = new HashMap<String, Object>();
							map.put("name", "fileRecordId");
							map.put("value", smplRes.getFileRecordId());
							mapList.add(map);*/
							map = new HashMap<String, Object>();
							map.put("name", "供试品编号");
							map.put("value",smplRes.getSmplCode());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "供试品类型");
							map.put("value",smplRes.getSmplType());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "供试品名称");
							map.put("value",smplRes.getSmplName());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "委托单位");
							map.put("value",smplRes.getSponsorName());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "留样量");
							map.put("value",smplRes.getReserveNum());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "留样单位");
							map.put("value",smplRes.getReserveNumUnit());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "供试品提供单位");
							map.put("value",smplRes.getSmplProvUnitName());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "出具单位");
							map.put("value",smplRes.getReportUnitName());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "供试品批号");
							map.put("value",smplRes.getBatchCode());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "留样人");
							map.put("value", smplRes.getReserveMan());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "留样日期");
							map.put("value",DateUtil.dateToString(smplRes.getReserveDate(),"yyyy-MM-dd"));
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "有效日期");
							map.put("value",DateUtil.dateToString(smplRes.getValidDate(),"yyyy-MM-dd"));
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "留样容器");
							map.put("value",smplRes.getContainer());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "留样接收日期");
							map.put("value",DateUtil.dateToString(smplRes.getReserveRecDate(),"yyyy-MM-dd"));
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "留样接收人");
							map.put("value",smplRes.getReserveRecMan());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "归档日期");
							map.put("value",DateUtil.dateToString(smplRes.getFileDate(),"yyyy-MM-dd"));
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "归档人");
							map.put("value",smplRes.getFileOperator());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "保管期限");
							map.put("value",DateUtil.dateToString(smplRes.getKeepDate(),"yyyy-MM-dd"));
							mapList.add(map);
							if(smplRes.getDestoryDate()!=null)
							{
								map = new HashMap<String, Object>();
								map.put("name", "销毁日期");
								map.put("value",DateUtil.dateToString(smplRes.getDestoryDate(),"yyyy-MM-dd"));
								mapList.add(map);
							}
							if(smplRes.getSmplDestoryDate()!=null)
							{
								map = new HashMap<String, Object>();
								map.put("name", "留样销毁日期");
								map.put("value",DateUtil.dateToString(smplRes.getSmplDestoryDate(),"yyyy-MM-dd"));
								mapList.add(map);
							}
							map = new HashMap<String, Object>();
							map.put("name", "关键字");
							map.put("value",smplRes.getKeyWord());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "备注");
							map.put("value",smplRes.getRemark());
							mapList.add(map);
							
							
							
							
						}else{
							mapState.put("success", false);
							mapState.put("msg", "该留样信息不存在！");
						}
						
						break;
					case 10:
						TblFileRecordSpecimen specimen = tblFileRecordSpecimenService.getById(archiveLog.getOldFileRecordId());
						if(specimen!=null){
							fileIndex = specimen.getTblFileIndex();
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("name", "档案编号");
							map.put("value", specimen.getTblFileIndex().getArchiveCode());
							mapList.add(map);
							/*map = new HashMap<String, Object>();
							map.put("name", "fileRecordId");
							map.put("value", specimen.getFileRecordId());
							mapList.add(map);*/
							map = new HashMap<String, Object>();
							map.put("name", "合同/专题编号");
							map.put("value", specimen.getStudyNo());
							mapList.add(map);
							/*map = new HashMap<String, Object>();
							map.put("name", "studyName");
							map.put("value", specimen.getStudyName());
							mapList.add(map);*/
							map = new HashMap<String, Object>();
							map.put("name", "编号类型");
							String str = "专题";
							if(specimen.getStudyNoType()!=null&&specimen.getStudyNoType()==2)
								str = "合同";
							map.put("value",str );
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "标本类型");
							String str2 = "湿标本";
							if(specimen.getSpecimenTypeFlag()!=null)
							{
								if(specimen.getSpecimenTypeFlag()==2)
									str2 = "蜡块";
								else if(specimen.getSpecimenTypeFlag()==3)
									str2 = "切片";
							}
							map.put("value", str2);
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "归档数量");
							map.put("value", specimen.getFileNum());
							mapList.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "数量单位");
							map.put("value", specimen.getFileNumUnit());
							mapList.add(map);
							
							
							map = new HashMap<String, Object>();
							map.put("name", "归档日期");
							map.put("value", DateUtil.dateToString(specimen.getFileDate(),"yyyy-MM-dd"));
							mapList.add(map);
							
							map = new HashMap<String, Object>();
							map.put("name", "归档人");
							map.put("value", specimen.getFileOperator());
							mapList.add(map);
							/*map = new HashMap<String, Object>();
							map.put("name", "fileRecordSn");
							map.put("value", specimen.getFileRecordSn());
							mapList.add(map);*/
							map = new HashMap<String, Object>();
							map.put("name", "保管期限");
							map.put("value", DateUtil.dateToString(specimen.getKeepDate(),"yyyy-MM-dd"));
							mapList.add(map);
							if(specimen.getDestoryDate()!=null)
							{
								map = new HashMap<String, Object>();
								map.put("name", "销毁日期");
								map.put("value",DateUtil.dateToString(specimen.getDestoryDate(),"yyyy-MM-dd"));
								mapList.add(map);
							}
							if(specimen.getSpecimenDestoryDate()!=null)
							{
								map = new HashMap<String, Object>();
								map.put("name", "标本销毁日期");
								map.put("value",DateUtil.dateToString(specimen.getSpecimenDestoryDate(),"yyyy-MM-dd"));
								mapList.add(map);
							}
							map = new HashMap<String, Object>();
							map.put("name", "关键子");
							map.put("value", specimen.getKeyWord());
							mapList.add(map);
							/*map = new HashMap<String, Object>();
							map.put("name", "operateTime");
							map.put("value", DateUtil.dateToString(specimen.getOperateTime(),"yyyy-MM-dd"));
							mapList.add(map);*/
							/*map = new HashMap<String, Object>();
							map.put("name", "operator");
							map.put("value", specimen.getOperator());
							mapList.add(map);*/
							map = new HashMap<String, Object>();
							map.put("name", "备注");
							map.put("value", specimen.getRemark());
							mapList.add(map);
							/*map = new HashMap<String, Object>();
							map.put("name", "sd");
							map.put("value", specimen.getSd());
							mapList.add(map);*/
							
							
							
							
						}else{
							mapState.put("success", false);
							mapState.put("msg", "该标本资料不存在！");
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
					if(dictArchiveType!=null)
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
