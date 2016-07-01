package com.lanen.view.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Department;
import com.lanen.model.DictDataTable;
import com.lanen.model.TblFileContentGlpSynthesis;
import com.lanen.model.TblFileIndex;
import com.lanen.model.TblFileRecord;
import com.lanen.model.TblLog;
import com.lanen.model.User;
import com.lanen.model.archive.DictArchiveType;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.service.DepartmentService;
import com.lanen.service.archive.DictArchiveTypeService;
import com.lanen.service.archive.DictDataTableService;
import com.lanen.service.archive.TblFileContentGlpSynthesisService;
import com.lanen.service.archive.TblFileIndexService;
import com.lanen.service.archive.TblFileRecordService;
import com.lanen.service.archive.TblLog2Service;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class TblFileContentGlpSynthesisAction extends BaseAction<TblFileContentGlpSynthesis> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4608222706300159709L;
	
	@Resource
	private TblFileContentGlpSynthesisService tblFileContentGlpSynthesisService;
	@Resource
	private TblFileIndexService tblFileIndexService;
	@Resource
	private TblFileRecordService tblFileRecordService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private DictArchiveTypeService dictArchiveTypeService;
	@Resource
	private DictDataTableService dictDataTableService;
	@Resource
	private TblLog2Service tblLog2Service;
	@Resource
	private DepartmentService departmentService;
	
	private String archiveTitle;
	
	private String archiveTypeCode;
	private String storePosition;
	private Integer validationFlag;
	private String operateRsn;
	
	private Date fileStartDate;
	private Date fileEndDate;
	private Date keepEndDate;
	private Integer isDestory;
	private Integer isValid;
	private String searchString;
	
	private Integer rows;// 每页显示的记录数 
	private Integer page;// 当前第几页 
	
	private Date destoryDate;
	
	private Integer isCond;
	
	public String list()
	{
		
		return "list";
	}
	
	public void save()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		//档案编号编号不能相同
		Date operateTime = new Date();
		boolean isExistArchiveCode = tblFileIndexService.isExistArchiveCode(model.getArchiveCode());
		if(!isExistArchiveCode)
		{
			TblFileIndex fileIndex = new TblFileIndex();
			fileIndex.setArchiveCode(model.getArchiveCode());
			fileIndex.setArchiveTitle(archiveTitle);
			fileIndex.setArchiveTypeCode(archiveTypeCode);
			fileIndex.setArchiveTypeFlag(4);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
			fileIndex.setOperateTime(operateTime);
			fileIndex.setOperator(getCurrentUser().getRealName());
			fileIndex.setStorePosition(storePosition);
			fileIndex.setValidationFlag(validationFlag);
			
			tblFileIndexService.save(fileIndex);
			
			String key = tblFileRecordService.getKey("TblFileRecord");
			TblFileRecord record = new TblFileRecord();			
			record.setArchiveMaker(model.getTblFileRecord().getArchiveMaker());
			record.setArchiveMedia(model.getTblFileRecord().getArchiveMedia());
			record.setArchiveMediaFlag(model.getTblFileRecord().getArchiveMediaFlag());
			record.setDestoryDate(model.getTblFileRecord().getDestoryDate());
			record.setFileDate(model.getTblFileRecord().getFileDate());
			record.setFileDateType(3);//年月日
			record.setFileOperator(model.getTblFileRecord().getFileOperator());
			record.setFileRecordId(key);
			record.setFileRecordSn(1);//新建肯定是1，追加再往后加
			record.setKeepDate(model.getTblFileRecord().getKeepDate());
			record.setKeyWord(model.getTblFileRecord().getKeyWord());
			record.setOperateTime(operateTime);
			record.setOperator(getCurrentUser().getRealName());
			record.setRemark(model.getTblFileRecord().getRemark());
			record.setTblFileIndex(fileIndex);
			
			tblFileRecordService.save(record);
			
			TblFileContentGlpSynthesis fileContentGlp = new TblFileContentGlpSynthesis();
			fileContentGlp.setArchiveCode(model.getArchiveCode());
			fileContentGlp.setFileRecordId(key);
			
			Department department =departmentService.getById(model.getDepartment());
			fileContentGlp.setDepartment(department.getName());
			fileContentGlp.setDocName(model.getDocName());
			
			fileContentGlp.setBaseGlpFlag(1);
			
			fileContentGlp.setTblFileRecord(record);
			
			tblFileContentGlpSynthesisService.save(fileContentGlp);
			
			Map<String, Object> map0 = new HashMap<String, Object>();
			map0.put("archiveCode", fileContentGlp.getArchiveCode());
			map0.put("fileRecordId", fileContentGlp.getFileRecordId());
			
			Department department0 = departmentService.getByName(fileContentGlp.getDepartment());
			if(department0!=null)
			{
				map0.put("departmentId", department0.getId());
			}
			map0.put("department", fileContentGlp.getDepartment());
			map0.put("docName", fileContentGlp.getDocName());
			map0.put("archiveTypeCode", fileContentGlp.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
			
			DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(fileContentGlp.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
			if(dictArchiveType!=null)
			{
				map0.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
			}
			map0.put("archiveTitle", fileContentGlp.getTblFileRecord().getTblFileIndex().getArchiveTitle());
			map0.put("storePosition", fileContentGlp.getTblFileRecord().getTblFileIndex().getStorePosition());
			map0.put("fileDate", DateUtil.dateToString(fileContentGlp.getTblFileRecord().getFileDate(),"yyyy-MM-dd"));
			map0.put("fileRecordSn", fileContentGlp.getTblFileRecord().getFileRecordSn());
			map0.put("archiveMaker", fileContentGlp.getTblFileRecord().getArchiveMaker());
			map0.put("fileOperator", fileContentGlp.getTblFileRecord().getFileOperator());
			map0.put("keepDate", DateUtil.dateToString(fileContentGlp.getTblFileRecord().getKeepDate(),"yyyy-MM-dd"));
			map0.put("remark", fileContentGlp.getTblFileRecord().getRemark());
			if(fileContentGlp.getTblFileRecord().getDestoryDate()!=null)
			{
				map0.put("destoryDate", DateUtil.dateToString(fileContentGlp.getTblFileRecord().getDestoryDate(),"yyyy-MM-dd"));
			}
			map0.put("validationFlag", fileContentGlp.getTblFileRecord().getTblFileIndex().getValidationFlag());//0：否；1：验证数据
			map0.put("archiveMediaFlag", fileContentGlp.getTblFileRecord().getArchiveMediaFlag());
			map0.put("archiveMedia", fileContentGlp.getTblFileRecord().getArchiveMedia());
			map.put("record", map0);
			
			map.put("success", true);
		}else {
			map.put("success", false);
			map.put("msg", "档案编号已经存在");
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	public void appendSave()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		//档案编号编号不能相同
		Date operateTime = new Date();
		//boolean isExistArchiveCode = tblFileIndexService.isExistArchiveCode(model.getArchiveCode());
		//if(!isExistArchiveCode)
		//{
			//追加归档不用修改fileIndex
			TblFileIndex fileIndex = tblFileIndexService.getById(model.getArchiveCode());
			
			
			String key = tblFileRecordService.getKey("TblFileRecord");
			TblFileRecord record = new TblFileRecord();			
			record.setArchiveMaker(model.getTblFileRecord().getArchiveMaker());
			record.setArchiveMedia(model.getTblFileRecord().getArchiveMedia());
			record.setArchiveMediaFlag(model.getTblFileRecord().getArchiveMediaFlag());
			record.setDestoryDate(model.getTblFileRecord().getDestoryDate());
			record.setFileDate(model.getTblFileRecord().getFileDate());
			record.setFileDateType(3);//年月日
			record.setFileOperator(model.getTblFileRecord().getFileOperator());
			record.setFileRecordId(key);
			
			Integer maxSn = tblFileRecordService.getMaxSnByArchiveCode(model.getArchiveCode());
			record.setFileRecordSn(maxSn+1);//追加是现在最大的+1
			
			record.setKeepDate(model.getTblFileRecord().getKeepDate());
			record.setKeyWord(model.getTblFileRecord().getKeyWord());
			record.setOperateTime(operateTime);
			record.setOperator(getCurrentUser().getRealName());
			record.setRemark(model.getTblFileRecord().getRemark());
			record.setTblFileIndex(fileIndex);
			
			tblFileRecordService.save(record);
			
			TblFileContentGlpSynthesis fileContentGlp = new TblFileContentGlpSynthesis();
			fileContentGlp.setArchiveCode(model.getArchiveCode());
			fileContentGlp.setFileRecordId(key);
			Department department =departmentService.getById(model.getDepartment());
			fileContentGlp.setDepartment(department.getName());
			fileContentGlp.setDocName(model.getDocName());
			fileContentGlp.setBaseGlpFlag(1);
			
			fileContentGlp.setTblFileRecord(record);
		
			tblFileContentGlpSynthesisService.save(fileContentGlp);
			
			Map<String, Object> map0 = new HashMap<String, Object>();
			map0.put("archiveCode", fileContentGlp.getArchiveCode());
			map0.put("fileRecordId", fileContentGlp.getFileRecordId());
			
			Department department0 = departmentService.getByName(fileContentGlp.getDepartment());
			if(department0!=null)
			{
				map0.put("departmentId", department0.getId());
			}
			map0.put("department", fileContentGlp.getDepartment());
			map0.put("docName", fileContentGlp.getDocName());
			map0.put("archiveTypeCode", fileContentGlp.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
			
			DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(fileContentGlp.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
			if(dictArchiveType!=null)
			{
				map0.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
			}
			map0.put("archiveTitle", fileContentGlp.getTblFileRecord().getTblFileIndex().getArchiveTitle());
			map0.put("storePosition", fileContentGlp.getTblFileRecord().getTblFileIndex().getStorePosition());
			map0.put("fileDate", DateUtil.dateToString(fileContentGlp.getTblFileRecord().getFileDate(),"yyyy-MM-dd"));
			map0.put("fileRecordSn", fileContentGlp.getTblFileRecord().getFileRecordSn());
			map0.put("archiveMaker", fileContentGlp.getTblFileRecord().getArchiveMaker());
			map0.put("fileOperator", fileContentGlp.getTblFileRecord().getFileOperator());
			map0.put("keepDate", DateUtil.dateToString(fileContentGlp.getTblFileRecord().getKeepDate(),"yyyy-MM-dd"));
			map0.put("remark", fileContentGlp.getTblFileRecord().getRemark());
			if(fileContentGlp.getTblFileRecord().getDestoryDate()!=null)
			{
				map0.put("destoryDate", DateUtil.dateToString(fileContentGlp.getTblFileRecord().getDestoryDate(),"yyyy-MM-dd"));
			}
			map0.put("validationFlag", fileContentGlp.getTblFileRecord().getTblFileIndex().getValidationFlag());//0：否；1：验证数据
			map0.put("archiveMediaFlag", fileContentGlp.getTblFileRecord().getArchiveMediaFlag());
			map0.put("archiveMedia", fileContentGlp.getTblFileRecord().getArchiveMedia());
			map.put("record", map0);
			
			map.put("success", true);
		//}else {
		//	map.put("success", false);
		//	map.put("msg", "档案编号已经存在");
			
		//}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public void getDepartmentList()
	{
		//id name remark
		List<Department> departments = departmentService.findAll();
		if(isCond!=null&&isCond==1){
			Department d = new Department();
			d.setId("");
			d.setName("全部");
			
			departments.add(0, d);
		}
		String[] _nory_format = {"id","name","remark"};
		String json = JsonPluginsUtil.beanListToJson(departments, "yyyy-MM-dd", _nory_format, true);
		writeJson(json);
	}
	
	
	
	public void getMaxArchiveCode() {
		Map<String, Object> map = new HashMap<String, Object>();
		String archiveCode = tblFileIndexService.getMaxCodeByTypeCode(archiveTypeCode);
		
		if(archiveCode==null)
		{
			map.put("archiveCode", 1);
		}else {
			try {
				Integer max = Integer.parseInt(archiveCode);
				map.put("archiveCode", max+1);
			} catch (Exception e) {
				map.put("archiveCode", "");
			}
			
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	
	public void update()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		Date operateTime = new Date();
		
		TblFileContentGlpSynthesis fileContentglp = tblFileContentGlpSynthesisService.getById(model.getFileRecordId());
		
		Department department = departmentService.getById(model.getDepartment());
		if((fileContentglp.getDepartment()==null&&department!=null)
				||(fileContentglp.getDepartment()!=null&&department!=null&&!fileContentglp.getDepartment().equals(department.getName())))
		{
			writeTblLog(model.getArchiveCode(),fileContentglp.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileContentGlpSynthesis","department",fileContentglp.getTblFileRecord().getFileRecordSn()
					,department.getName(),model.getFileRecordId(),fileContentglp.getDepartment()
					,operateRsn,operateTime,1);
		}
		if(department!=null)
			fileContentglp.setDepartment(department.getName());
		
		if((fileContentglp.getDocName()==null&&model.getDocName()!=null)
				||(fileContentglp.getDocName()!=null&&!fileContentglp.getDocName().equals(model.getDocName())))
		{
			writeTblLog(model.getArchiveCode(),fileContentglp.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileContentGlpSynthesis","docName",fileContentglp.getTblFileRecord().getFileRecordSn()
					,model.getDocName(),model.getFileRecordId(),fileContentglp.getDocName()
					,operateRsn,operateTime,1);
		}
	
		fileContentglp.setDocName(model.getDocName());
		
		
		tblFileContentGlpSynthesisService.update(fileContentglp);
		
		
		TblFileRecord record = tblFileRecordService.getById(model.getFileRecordId());//编辑的时候根据这个判断		
		if((record.getArchiveMaker()==null&&model.getTblFileRecord().getArchiveMaker()!=null)
				||(record.getArchiveMaker()!=null&&!record.getArchiveMaker().equals(model.getTblFileRecord().getArchiveMaker())))
		{
			writeTblLog(model.getArchiveCode(),fileContentglp.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","archiveMaker",record.getFileRecordSn()
					,record.getArchiveMaker(),model.getFileRecordId(),model.getTblFileRecord().getArchiveMaker()
					,operateRsn,operateTime,1);
		}
		record.setArchiveMaker(model.getTblFileRecord().getArchiveMaker());
		if(!record.getArchiveMediaFlag().equals(model.getTblFileRecord().getArchiveMediaFlag()))
		{
			writeTblLog(model.getArchiveCode(),fileContentglp.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","archiveMediaFlag",record.getFileRecordSn()
					,String.valueOf(record.getArchiveMediaFlag()),model.getFileRecordId(),String.valueOf(model.getTblFileRecord().getArchiveMediaFlag())
					,operateRsn,operateTime,1);
		}
		if(record.getArchiveMedia()!=null&&!record.getArchiveMedia().equals(model.getTblFileRecord().getArchiveMedia()))
		{
			writeTblLog(model.getArchiveCode(),fileContentglp.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","archiveMedia",record.getFileRecordSn()
					,record.getArchiveMedia(),model.getFileRecordId(),model.getTblFileRecord().getArchiveMedia()
					,operateRsn,operateTime,1);
		}
		record.setArchiveMedia(model.getTblFileRecord().getArchiveMedia());
		record.setArchiveMediaFlag(model.getTblFileRecord().getArchiveMediaFlag());
		
		if((record.getDestoryDate()==null&&model.getTblFileRecord().getDestoryDate()!=null)||
				(record.getDestoryDate()!=null&&record.getDestoryDate().compareTo(model.getTblFileRecord().getDestoryDate())!=0))
		{
			writeTblLog(model.getArchiveCode(),fileContentglp.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","destoryDate",record.getFileRecordSn()
					,DateUtil.dateToString(record.getDestoryDate(),"yyyy-MM-dd"),model.getFileRecordId(),DateUtil.dateToString(model.getTblFileRecord().getDestoryDate(),"yyyy-MM-dd")
					,operateRsn,operateTime,1);
		}
		record.setDestoryDate(model.getTblFileRecord().getDestoryDate());
		if((record.getFileDate()==null&&model.getTblFileRecord().getFileDate()!=null)
				||(record.getFileDate()!=null&&record.getFileDate().compareTo(model.getTblFileRecord().getFileDate())!=0))
		{
			writeTblLog(model.getArchiveCode(),fileContentglp.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","fileDate",record.getFileRecordSn()
					,DateUtil.dateToString(record.getFileDate(),"yyyy-MM-dd"),model.getFileRecordId(),DateUtil.dateToString(model.getTblFileRecord().getFileDate(),"yyyy-MM-dd")
					,operateRsn,operateTime,1);
		}
		record.setFileDate(model.getTblFileRecord().getFileDate());
		
		if((record.getFileOperator()==null&&model.getTblFileRecord().getFileOperator()!=null)
				||(record.getFileOperator()!=null&&!record.getFileOperator().equals(model.getTblFileRecord().getFileOperator())))
		{
			writeTblLog(model.getArchiveCode(),fileContentglp.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","fileOperator",record.getFileRecordSn()
					,record.getFileOperator(),model.getFileRecordId(),model.getTblFileRecord().getFileOperator()
					,operateRsn,operateTime,1);
		}
		
		record.setFileOperator(model.getTblFileRecord().getFileOperator());
		//record.setFileRecordSn(1);//sn是后台维护的。更新的时候用不到这个
		if((record.getKeepDate()==null&&model.getTblFileRecord().getKeepDate()!=null)
				||(record.getKeepDate()!=null&&record.getKeepDate().compareTo(model.getTblFileRecord().getKeepDate())!=0))
		{
			writeTblLog(model.getArchiveCode(),fileContentglp.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","keepDate",record.getFileRecordSn()
					,DateUtil.dateToString(record.getKeepDate(),"yyyy-MM-dd"),model.getFileRecordId(),DateUtil.dateToString(model.getTblFileRecord().getKeepDate(),"yyyy-MM-dd")
					,operateRsn,operateTime,1);
		}
		record.setKeepDate(model.getTblFileRecord().getKeepDate());

		if((record.getKeyWord()==null&&model.getTblFileRecord().getKeyWord()!=null)
				||(record.getKeyWord()!=null&&!record.getKeyWord().equals(model.getTblFileRecord().getKeyWord())))
		{
			writeTblLog(model.getArchiveCode(),fileContentglp.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","keyWord",record.getFileRecordSn()
					,record.getKeyWord(),model.getFileRecordId(),model.getTblFileRecord().getKeyWord()
					,operateRsn,operateTime,1);
		}
		record.setKeyWord(model.getTblFileRecord().getKeyWord());
		record.setOperateTime(operateTime);
		//operator在专题那边记录过一次了
		record.setOperator(getCurrentUser().getRealName());
		if((record.getRemark()==null&&model.getTblFileRecord().getRemark()!=null)
				||(record.getRemark()!=null&&!record.getRemark().equals(model.getTblFileRecord().getRemark())))
		{
			writeTblLog(model.getArchiveCode(),fileContentglp.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","remark",record.getFileRecordSn()
					,record.getRemark(),model.getFileRecordId(),model.getTblFileRecord().getRemark()
					,operateRsn,operateTime,1);
		}
		record.setRemark(model.getTblFileRecord().getRemark());
		
		tblFileRecordService.update(record);
		
		//更新的时候档案编号不可以更新
		TblFileIndex fileIndex = tblFileIndexService.getById(model.getArchiveCode());
		if(!fileIndex.getArchiveTypeCode().equals(archiveTypeCode)){
			writeTblLog(model.getArchiveCode(),fileIndex.getArchiveTitle(),
					"TblFileIndex","archiveTypeCode",record.getFileRecordSn()
					,archiveTypeCode,model.getFileRecordId(),fileIndex.getArchiveTypeCode(),
					operateRsn,operateTime,1);
		}
		fileIndex.setArchiveTypeCode(archiveTypeCode);
		fileIndex.setOperateTime(operateTime);
		//在专题记录中记录过了
		fileIndex.setOperator(getCurrentUser().getRealName());
		
		if((fileIndex.getStorePosition()==null&&storePosition!=null)
				||(fileIndex.getStorePosition()!=null&&!fileIndex.getStorePosition().equals(storePosition))){
			writeTblLog(model.getArchiveCode(),fileIndex.getArchiveTitle(),
					"TblFileIndex","storePosition",record.getFileRecordSn()
					,storePosition,model.getFileRecordId(),fileIndex.getStorePosition(),
					operateRsn,operateTime,1);
		}
		fileIndex.setStorePosition(storePosition);
		if(!fileIndex.getValidationFlag().equals(validationFlag)){
			writeTblLog(model.getArchiveCode(),fileIndex.getArchiveTitle(),
					"TblFileIndex","validationFlag",record.getFileRecordSn()
					,String.valueOf(validationFlag),model.getFileRecordId(),String.valueOf(fileIndex.getValidationFlag()),
					operateRsn,operateTime,1);
		}
		fileIndex.setValidationFlag(validationFlag);
		if((fileIndex.getArchiveTitle()==null&&archiveTitle!=null)
				||(fileIndex.getArchiveTitle()!=null&&!fileIndex.getArchiveTitle().equals(archiveTitle))){
			writeTblLog(model.getArchiveCode(),fileIndex.getArchiveTitle(),
					"TblFileIndex","archiveTitle",record.getFileRecordSn()
					,archiveTitle,model.getFileRecordId(),fileIndex.getArchiveTitle(),
					operateRsn,operateTime,1);
		}
		fileIndex.setArchiveTitle(archiveTitle);
		
		tblFileIndexService.update(fileIndex);
		
		writeES("更新综合档案", 909, "TblFileContentGlpSynthesis", model.getFileRecordId());
		
		Map<String, Object> map0 = new HashMap<String, Object>();
		map0.put("archiveCode", fileContentglp.getArchiveCode());
		map0.put("fileRecordId", fileContentglp.getFileRecordId());
		
		Department department0 = departmentService.getByName(fileContentglp.getDepartment());
		if(department0!=null)
		{
			map0.put("departmentId", department0.getId());
		}
		map0.put("department", fileContentglp.getDepartment());
		map0.put("docName", fileContentglp.getDocName());
		map0.put("archiveTypeCode", fileContentglp.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
		
		DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(fileContentglp.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
		if(dictArchiveType!=null)
		{
			map0.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
		}
		map0.put("archiveTitle", fileContentglp.getTblFileRecord().getTblFileIndex().getArchiveTitle());
		map0.put("storePosition", fileContentglp.getTblFileRecord().getTblFileIndex().getStorePosition());
		map0.put("fileDate", DateUtil.dateToString(fileContentglp.getTblFileRecord().getFileDate(),"yyyy-MM-dd"));
		map0.put("fileRecordSn", fileContentglp.getTblFileRecord().getFileRecordSn());
		map0.put("archiveMaker", fileContentglp.getTblFileRecord().getArchiveMaker());
		map0.put("fileOperator", fileContentglp.getTblFileRecord().getFileOperator());
		map0.put("keepDate", DateUtil.dateToString(fileContentglp.getTblFileRecord().getKeepDate(),"yyyy-MM-dd"));
		map0.put("remark", fileContentglp.getTblFileRecord().getRemark());
		if(fileContentglp.getTblFileRecord().getDestoryDate()!=null)
		{
			map0.put("destoryDate", DateUtil.dateToString(fileContentglp.getTblFileRecord().getDestoryDate(),"yyyy-MM-dd"));
		}
		map0.put("validationFlag", fileContentglp.getTblFileRecord().getTblFileIndex().getValidationFlag());//0：否；1：验证数据
		map0.put("archiveMediaFlag", fileContentglp.getTblFileRecord().getArchiveMediaFlag());
		map0.put("archiveMedia", fileContentglp.getTblFileRecord().getArchiveMedia());
		map.put("record", map0);
		
		map.put("success", true);

		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void delete()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		writeES("删除综合档案", 910, "TblFileContentGlpSynthesis", model.getFileRecordId());
		TblFileRecord record = tblFileRecordService.getById(model.getFileRecordId());
		record.setDelFlag(1);
		Date operateTime = new Date();
		record.setDelTime(operateTime);
		
		tblFileRecordService.update(record);
		
		writeTblLog(record.getTblFileIndex().getArchiveCode(),record.getTblFileIndex().getArchiveTitle(),
				"TblFileRecord","delFlag",record.getFileRecordSn()
				,""+0,model.getFileRecordId(),""+1,
				operateRsn,operateTime,2);
		
		map.put("success", true);

		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void destroy()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		TblFileRecord record0 = tblFileRecordService.getById(model.getFileRecordId());
		Date operateTime = new Date();
		List<TblFileRecord> recordList = tblFileRecordService.getByArchiveCode(record0.getTblFileIndex().getArchiveCode());
		for(TblFileRecord record:recordList)
		{
			record.setDestoryDate(destoryDate);
			String esLinkId = writeES("销毁综合档案", 922, "TblFileContentGlpSynthesis", record.getFileRecordId());
			record.setDestoryRegSign(esLinkId);
			
			tblFileRecordService.update(record);
			
			writeTblLog(record.getTblFileIndex().getArchiveCode(),record.getTblFileIndex().getArchiveTitle(),
					"TblFileRecord","destoryDate",record.getFileRecordSn()
					,DateUtil.dateToString(record.getDestoryDate(),"yyyy-MM-dd"),record.getFileRecordId(),"",
					operateRsn,operateTime,3);//1：修改；2：删除；3：销毁；4：SOP作废；5：合同终止
		}
		map.put("success", true);

		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void loadList()
	{
		putSearchConIntoSession(searchString, 3);
		
		String departName = "";
		if(model.getDepartment()!=null&&!"".equals(model.getDepartment()))
		{
			Department department =departmentService.getById(model.getDepartment());
			departName = department.getName();
		}
		//Integer isNowValid,Integer isInvalid,Date changeEndDate,Integer yearNum,baseGlp 1综合 2基建
		Map<String, Object> resultMap =tblFileContentGlpSynthesisService.getByCondition(departName,fileStartDate,fileEndDate,keepEndDate,isDestory,isValid,searchString,page,rows,1);
		
		 List<Map<String, Object>> fileStudys = (List<Map<String, Object>>) resultMap.get("rows");
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		for(Map<String, Object> fileStudy:fileStudys)
		{
			/*Map<String, Object> map = new HashMap<String, Object>();
			map.put("archiveCode", fileStudy.getArchiveCode());
			map.put("fileRecordId", fileStudy.getFileRecordId());
			*/
			
			Department department = departmentService.getByName((String)fileStudy.get("department"));
			if(department!=null)
			{
				fileStudy.put("departmentId", department.getId());
			}
			/*map.put("department", fileStudy.getDepartment());
			map.put("docName", fileStudy.getDocName());
			map.put("archiveTypeCode", fileStudy.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
			*/
			String archiveTypeCode = (String)fileStudy.get("archiveTypeCode");
			DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(archiveTypeCode);
			if(dictArchiveType!=null)
			{
				fileStudy.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
			}
			/*map.put("archiveTitle", fileStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle());
			map.put("storePosition", fileStudy.getTblFileRecord().getTblFileIndex().getStorePosition());
			map.put("fileDate", DateUtil.dateToString(fileStudy.getTblFileRecord().getFileDate(),"yyyy-MM-dd"));
			map.put("fileRecordSn", fileStudy.getTblFileRecord().getFileRecordSn());
			map.put("archiveMaker", fileStudy.getTblFileRecord().getArchiveMaker());
			map.put("fileOperator", fileStudy.getTblFileRecord().getFileOperator());
			map.put("remark", fileStudy.getTblFileRecord().getRemark());*/
			if(fileStudy.get("fileDate")!=null)
			{
				fileStudy.put("fileDate", DateUtil.dateToString((Date)fileStudy.get("fileDate"),"yyyy-MM-dd"));
			}
			if(fileStudy.get("keepDate")!=null)
			{
				fileStudy.put("keepDate", DateUtil.dateToString((Date)fileStudy.get("keepDate"),"yyyy-MM-dd"));
			}
			if(fileStudy.get("destoryDate")!=null)
			{
				fileStudy.put("destoryDate", DateUtil.dateToString((Date)fileStudy.get("destoryDate"),"yyyy-MM-dd"));
			}
			
			/*map.put("validationFlag", fileStudy.getTblFileRecord().getTblFileIndex().getValidationFlag());//0：否；1：验证数据
			map.put("archiveMediaFlag", fileStudy.getTblFileRecord().getArchiveMediaFlag());
			map.put("archiveMedia", fileStudy.getTblFileRecord().getArchiveMedia());*/
			
			mapList.add(fileStudy);
		}
	
		resultMap.put("rows", mapList);
		writeJson(JsonPluginsUtil.beanToJson(resultMap));
	}
	
	
	public void writeTblLog(String archiveCode,String archiveTitle,String tableName,String fieldName,Integer fileRecordSn
			,String newValue,String oldFileRecordId,String oldValue,String operateRsn,Date operateTime,Integer operateTypeFlag)//操作日志
	{
		TblLog tblLog = new TblLog();
		tblLog.setArchiveCode(archiveCode);
		tblLog.setArchiveTitle(archiveTitle);
		tblLog.setArchiveTypeFlag(4);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
		DictDataTable dictDataTable = dictDataTableService.getByTableNameAndField(tableName,fieldName);
		if(dictDataTable!=null)
		{
			tblLog.setFieldDesc(dictDataTable.getFieldDesc());
		}else {
			
		}
		tblLog.setFieldName(fieldName);
		tblLog.setFileRecordSn(fileRecordSn);
		tblLog.setId(tblLog2Service.getKey("TblLog"));
		tblLog.setNewValue(newValue);
		tblLog.setOldFileRecordId(oldFileRecordId);
		tblLog.setOldValue(oldValue);
		tblLog.setOperateRsn(operateRsn);
		tblLog.setOperateTime(operateTime);
		tblLog.setOperateTypeFlag(operateTypeFlag);
		String operateType = "";
		switch (operateTypeFlag) {
			case 1:
				operateType="修改";
				break;
			case 2:
				operateType="删除";
				break;
			case 3:
				operateType="销毁";
				break;
			case 4:
				operateType="SOP作废";
				break;
			case 5:
				operateType="合同终止";
				break;
	
			default:
				break;
		}
		tblLog.setOperateType(operateType);//1：修改；2：删除；3：销毁；4：SOP作废；5：合同终止
		tblLog.setOperator(getCurrentUser().getRealName());
		tblLog.setTableName(tableName);
		tblLog2Service.save(tblLog);
		
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
	
	
	public String getArchiveTitle() {
		return archiveTitle;
	}

	public void setArchiveTitle(String archiveTitle) {
		this.archiveTitle = archiveTitle;
	}

	public String getArchiveTypeCode() {
		return archiveTypeCode;
	}

	public void setArchiveTypeCode(String archiveTypeCode) {
		this.archiveTypeCode = archiveTypeCode;
	}

	public String getStorePosition() {
		return storePosition;
	}

	public void setStorePosition(String storePosition) {
		this.storePosition = storePosition;
	}

	public Integer getValidationFlag() {
		return validationFlag;
	}

	public void setValidationFlag(Integer validationFlag) {
		this.validationFlag = validationFlag;
	}

	public String getOperateRsn() {
		return operateRsn;
	}

	public void setOperateRsn(String operateRsn) {
		this.operateRsn = operateRsn;
	}

	public Date getFileStartDate() {
		return fileStartDate;
	}

	public void setFileStartDate(Date fileStartDate) {
		this.fileStartDate = fileStartDate;
	}

	public Date getFileEndDate() {
		return fileEndDate;
	}

	public void setFileEndDate(Date fileEndDate) {
		this.fileEndDate = fileEndDate;
	}

	public Date getKeepEndDate() {
		return keepEndDate;
	}

	public void setKeepEndDate(Date keepEndDate) {
		this.keepEndDate = keepEndDate;
	}

	public Integer getIsDestory() {
		return isDestory;
	}

	public void setIsDestory(Integer isDestory) {
		this.isDestory = isDestory;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public Integer getIsCond() {
		return isCond;
	}

	public void setIsCond(Integer isCond) {
		this.isCond = isCond;
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

	public Date getDestoryDate() {
		return destoryDate;
	}

	public void setDestoryDate(Date destoryDate) {
		this.destoryDate = destoryDate;
	}
	
}
