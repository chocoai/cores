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
import com.lanen.model.DictDataTable;
import com.lanen.model.TblFileIndex;
import com.lanen.model.TblFileRecordSmplReserve;
import com.lanen.model.TblFileRecordSpecimen;
import com.lanen.model.TblLog;
import com.lanen.model.User;
import com.lanen.model.archive.DictArchiveType;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.contract.TblContract;
import com.lanen.model.contract.TblTestItem;
import com.lanen.model.path.TblTissueSliceIndex;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.archive.DictArchiveTypeService;
import com.lanen.service.archive.DictDataTableService;
import com.lanen.service.archive.TblFileIndexService;
import com.lanen.service.archive.TblFileRecordSpecimenService;
import com.lanen.service.archive.TblLog2Service;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.TblContractService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.path.TblTissueSliceIndexService;
import com.lanen.service.studyplan.TblAnimalDetailDissectPlanService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class TblFileRecordSpecimenAction extends BaseAction<TblFileRecordSpecimen> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4354617920457434771L;
	
	@Resource
	private TblFileIndexService tblFileIndexService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private DictDataTableService dictDataTableService;
	@Resource
	private TblLog2Service tblLog2Service;
	@Resource
	private TblFileRecordSpecimenService tblFileRecordSpecimenService;
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	@Resource
	private TblContractService tblContractService;
	@Resource
	private TblTissueSliceIndexService tblTissueSliceIndexService;
	@Resource
	private TblAnimalDetailDissectPlanService tblAnimalDetailDissectPlanService;
	@Resource
	private DictArchiveTypeService dictArchiveTypeService;
	
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
	
	private Integer studyNoType3;
	
	private Integer destroyType;
	
	/*private Integer isSpecimenKeepEndDate;
	private Date specimenKeepEndDate;*/
	private Integer isDestroySpecimen;
	
	public String list()
	{
		
		return "list";
	}

	public void save()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		//档案编号编号不能相同
		Date operateTime = new Date();
		boolean isExistArchiveCode = tblFileIndexService.isExistArchiveCode(model.getTblFileIndex().getArchiveCode());
		if(!isExistArchiveCode)
		{
			TblFileIndex fileIndex = new TblFileIndex();
			fileIndex.setArchiveCode(model.getTblFileIndex().getArchiveCode());
			fileIndex.setArchiveTitle(model.getTblFileIndex().getArchiveTitle());
			fileIndex.setArchiveTypeCode(model.getTblFileIndex().getArchiveTypeCode());
			fileIndex.setArchiveTypeFlag(10);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
			fileIndex.setOperateTime(operateTime);
			fileIndex.setOperator(getCurrentUser().getRealName());
			fileIndex.setStorePosition(model.getTblFileIndex().getStorePosition());
			fileIndex.setValidationFlag(validationFlag);
			
			tblFileIndexService.save(fileIndex);
			
			
			TblFileRecordSpecimen fileContentSpecimen = new TblFileRecordSpecimen();
			fileContentSpecimen.setTblFileIndex(fileIndex);
			fileContentSpecimen.setFileRecordId(tblFileRecordSpecimenService.getKey("TblFileRecordSpecimen"));
			
			fileContentSpecimen.setFileDate(model.getFileDate());
			fileContentSpecimen.setFileOperator(model.getFileOperator());
			fileContentSpecimen.setFileRecordSn(1);//增加的肯定是1
			fileContentSpecimen.setKeepDate(model.getKeepDate());
			fileContentSpecimen.setKeyWord(model.getKeyWord());
			fileContentSpecimen.setOperateTime(operateTime);
			fileContentSpecimen.setOperator(getCurrentUser().getRealName());
			fileContentSpecimen.setRemark(model.getRemark());
			//1：湿标本；2：蜡块；3：切片
			fileContentSpecimen.setSpecimenTypeFlag(model.getSpecimenTypeFlag());
			fileContentSpecimen.setStudyNo(model.getStudyNo());
			fileContentSpecimen.setStudyNoType(studyNoType3);
			fileContentSpecimen.setStudyName(model.getStudyName());
			fileContentSpecimen.setSd(model.getSd());
			fileContentSpecimen.setFileNum(model.getFileNum());
			fileContentSpecimen.setFileNumUnit(model.getFileNumUnit());
			
			
			tblFileRecordSpecimenService.save(fileContentSpecimen);
			
			Map<String, Object> map0 = new HashMap<String, Object>();
			map0.put("archiveCode", fileContentSpecimen.getTblFileIndex().getArchiveCode());
			map0.put("fileRecordId", fileContentSpecimen.getFileRecordId());
			map0.put("fileDate", DateUtil.dateToString(fileContentSpecimen.getFileDate(),"yyyy-MM-dd"));
			map0.put("fileNum", fileContentSpecimen.getFileNum());
			map0.put("fileNumUnit", fileContentSpecimen.getFileNumUnit());
			map0.put("fileOperator", fileContentSpecimen.getFileOperator());
			map0.put("fileRecordSn", fileContentSpecimen.getFileRecordSn());
			map0.put("keepDate", DateUtil.dateToString(fileContentSpecimen.getKeepDate(),"yyyy-MM-dd"));
			if(fileContentSpecimen.getDestoryDate()!=null)
			{
				map0.put("destoryDate", DateUtil.dateToString(fileContentSpecimen.getDestoryDate(),"yyyy-MM-dd"));
			}
			if(fileContentSpecimen.getSpecimenDestoryDate()!=null)
			{
				map0.put("specimenDestoryDate", DateUtil.dateToString(fileContentSpecimen.getSpecimenDestoryDate(),"yyyy-MM-dd"));
			}
			map0.put("keyWord", fileContentSpecimen.getKeyWord());
			map0.put("operateTime", fileContentSpecimen.getOperateTime());
			map0.put("operator", fileContentSpecimen.getOperator());
			map0.put("remark", fileContentSpecimen.getRemark());
			map0.put("sd", fileContentSpecimen.getSd());
			map0.put("specimenTypeFlag", fileContentSpecimen.getSpecimenTypeFlag());
			map0.put("studyName", fileContentSpecimen.getStudyName());
			map0.put("studyNo", fileContentSpecimen.getStudyNo());
			map0.put("studyNoType", fileContentSpecimen.getStudyNoType());
			map0.put("archiveCode", fileContentSpecimen.getTblFileIndex().getArchiveCode());
			map0.put("archiveTitle", fileContentSpecimen.getTblFileIndex().getArchiveTitle());
			map0.put("archiveTypeCode", fileContentSpecimen.getTblFileIndex().getArchiveTypeCode());
			
			DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(fileContentSpecimen.getTblFileIndex().getArchiveTypeCode());
			if(dictArchiveType!=null)
			{
				map0.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
			}
			map0.put("storePosition", fileContentSpecimen.getTblFileIndex().getStorePosition());
			map0.put("validationFlag", fileContentSpecimen.getTblFileIndex().getValidationFlag());
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
			TblFileIndex fileIndex = tblFileIndexService.getById(model.getTblFileIndex().getArchiveCode());
			
			Integer maxSn = tblFileRecordSpecimenService.getMaxSnByArchiveCode(model.getTblFileIndex().getArchiveCode());
			
			TblFileRecordSpecimen fileContentSpecimen = new TblFileRecordSpecimen();
			fileContentSpecimen.setTblFileIndex(fileIndex);
			fileContentSpecimen.setFileRecordId(tblFileRecordSpecimenService.getKey("TblFileRecordSpecimen"));
			
			fileContentSpecimen.setFileDate(model.getFileDate());
			fileContentSpecimen.setFileOperator(model.getFileOperator());
			fileContentSpecimen.setFileRecordSn(maxSn+1);//+1
			fileContentSpecimen.setKeepDate(model.getKeepDate());
			fileContentSpecimen.setKeyWord(model.getKeyWord());
			fileContentSpecimen.setOperateTime(operateTime);
			fileContentSpecimen.setOperator(getCurrentUser().getRealName());
			fileContentSpecimen.setRemark(model.getRemark());
			//1：湿标本；2：蜡块；3：切片
			fileContentSpecimen.setSpecimenTypeFlag(model.getSpecimenTypeFlag());
			fileContentSpecimen.setStudyNo(model.getStudyNo());
			fileContentSpecimen.setStudyNoType(studyNoType3);
			fileContentSpecimen.setStudyName(model.getStudyName());
			fileContentSpecimen.setSd(model.getSd());
			fileContentSpecimen.setFileNum(model.getFileNum());
			fileContentSpecimen.setFileNumUnit(model.getFileNumUnit());
			
			
			tblFileRecordSpecimenService.save(fileContentSpecimen);
			
			Map<String, Object> map0 = new HashMap<String, Object>();
			map0.put("archiveCode", fileContentSpecimen.getTblFileIndex().getArchiveCode());
			map0.put("fileRecordId", fileContentSpecimen.getFileRecordId());
			map0.put("fileDate", DateUtil.dateToString(fileContentSpecimen.getFileDate(),"yyyy-MM-dd"));
			map0.put("fileNum", fileContentSpecimen.getFileNum());
			map0.put("fileNumUnit", fileContentSpecimen.getFileNumUnit());
			map0.put("fileOperator", fileContentSpecimen.getFileOperator());
			map0.put("fileRecordSn", fileContentSpecimen.getFileRecordSn());
			map0.put("keepDate", DateUtil.dateToString(fileContentSpecimen.getKeepDate(),"yyyy-MM-dd"));
			if(fileContentSpecimen.getDestoryDate()!=null)
			{
				map0.put("destoryDate", DateUtil.dateToString(fileContentSpecimen.getDestoryDate(),"yyyy-MM-dd"));
			}
			if(fileContentSpecimen.getSpecimenDestoryDate()!=null)
			{
				map0.put("specimenDestoryDate", DateUtil.dateToString(fileContentSpecimen.getSpecimenDestoryDate(),"yyyy-MM-dd"));
			}
			map0.put("keyWord", fileContentSpecimen.getKeyWord());
			map0.put("operateTime", fileContentSpecimen.getOperateTime());
			map0.put("operator", fileContentSpecimen.getOperator());
			map0.put("remark", fileContentSpecimen.getRemark());
			map0.put("sd", fileContentSpecimen.getSd());
			map0.put("specimenTypeFlag", fileContentSpecimen.getSpecimenTypeFlag());
			map0.put("studyName", fileContentSpecimen.getStudyName());
			map0.put("studyNo", fileContentSpecimen.getStudyNo());
			map0.put("studyNoType", fileContentSpecimen.getStudyNoType());
			map0.put("archiveCode", fileContentSpecimen.getTblFileIndex().getArchiveCode());
			map0.put("archiveTitle", fileContentSpecimen.getTblFileIndex().getArchiveTitle());
			map0.put("archiveTypeCode", fileContentSpecimen.getTblFileIndex().getArchiveTypeCode());
			
			DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(fileContentSpecimen.getTblFileIndex().getArchiveTypeCode());
			if(dictArchiveType!=null)
			{
				map0.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
			}
			map0.put("storePosition", fileContentSpecimen.getTblFileIndex().getStorePosition());
			map0.put("validationFlag", fileContentSpecimen.getTblFileIndex().getValidationFlag());
			map.put("record", map0);
			
			map.put("success", true);
		//}else {
		//	map.put("success", false);
		//	map.put("msg", "档案编号已经存在");
			
		//}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	

	public void getSpecimenByCode()
	{
		//studyNoType, studyNo
		List<TblTestItem> tblTestItems =new ArrayList<TblTestItem>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success",false);
		if(model.getStudyNoType()!=null){
			if(model.getStudyNoType()==1)//1专题 2合同
			{
				TblStudyPlan studyPlan = tblStudyPlanService.getByStudyNo(model.getStudyNo());
				if(studyPlan!=null)
				{
					map.put("success",true);
					map.put("studyName",studyPlan.getStudyName());
					map.put("sd",studyPlan.getStudydirector());
				}
				
			}else if(model.getStudyNoType()==2){//选择合同的时候是合同编号
				TblContract tblContract = tblContractService.getByContractCode(model.getStudyNo());
				if(tblContract!=null)//
				{
					map.put("success",true);
					map.put("studyName",tblContract.getContractName());
					//map.put("sd",);
				}
			
			}
		}
		
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void getSDList()
	{
		List<User> sdList = userService.findByPrivilegeName2("SD");
		String[] _nory_changes = {"userCode","realName"};
		writeJson(JsonPluginsUtil.beanListToJson(sdList,"yyyy-MM-dd",  _nory_changes,true));
	}
	public void getSliceAnimalByStudyNo()
	{
		//model.getStudyNo()
		//List<Map<String, Object>> sliceAnimalList =  tblTissueSliceIndexService.getSliceAnimalByStudyNo(model.getStudyNo());
		//sliceSn.[id],[studyNo] ,[sliceCodeType] ,[operatorSign],[createTime],[taskId],[gender],[animalCode]" +
		//getMapListByStudyNo(String studyNoPara);
		List<Map<String, Object>> animalList = tblAnimalDetailDissectPlanService.getMapListByStudyNo(model.getStudyNo());
		// animal.id ,animal.gender,animal.animalCode,dose.dosageNum,dose.dosageDesc 
		//dosageNum=goupId，"dosageDesc"
		//String[] _nory_format = {"id","gender","animalCode","dosageNum","dosageDesc"};
		writeJson(JsonPluginsUtil.beanListToJson(animalList));
	}
	public void getSliceSpecimenByStudyNo(){
		//model.getStudyNo()
		//List<Map<String, Object>> sliceList =  tblTissueSliceIndexService.getSliceVisceraByStudyNo(model.getStudyNo());
		//sliceSn.[id],[studyNo] ,[sliceCodeType] ,[operatorSign],[createTime],[taskId],[gender],[animalCode]" +
		List<Map<String, Object>> sliceMapList = new ArrayList<Map<String,Object>>();
		
		List<Map<String,Object>> mapList = tblTissueSliceIndexService.getSliceCodeByStudyNo(model.getStudyNo());
		if(null != mapList){
			String preIndexId = "";
			String preSliceCode="";
			String preVisceraOrTissueName = "";
			for(Map<String,Object> map:mapList){
				String indexId = (String) map.get("indexId");
				String sliceCode = (String) map.get("sliceCode");
				String visceraOrTissueName = (String) map.get("visceraOrTissueName");
				if("".equals(preSliceCode)){
					preIndexId = indexId;
					preSliceCode = sliceCode;
					preVisceraOrTissueName = visceraOrTissueName;
				}else if(preSliceCode.equals(sliceCode)){
					preVisceraOrTissueName += "、"+visceraOrTissueName;
				}else{
					Map<String, Object> tempMap = new HashMap<String, Object>();
					tempMap.put("indexId", preIndexId);
					tempMap.put("sliceCode", preSliceCode);
					tempMap.put("visceraOrTissueName", preVisceraOrTissueName);
					sliceMapList.add(tempMap);
					
					preIndexId = indexId;
					preSliceCode = sliceCode;
					preVisceraOrTissueName = visceraOrTissueName;
				}
				
			}
			if(!"".equals(preSliceCode)){
				Map<String, Object> tempMap = new HashMap<String, Object>();
				tempMap.put("indexId", preIndexId);
				tempMap.put("sliceCode", preSliceCode);
				tempMap.put("visceraOrTissueName", preVisceraOrTissueName);
				sliceMapList.add(tempMap);
			}
		}
		writeJson(JsonPluginsUtil.beanListToJson(sliceMapList));
		
	}
	
	public void getMaxArchiveCode() {
		Map<String, Object> map = new HashMap<String, Object>();
		String archiveCode = tblFileIndexService.getMaxCodeByTypeCode(model.getTblFileIndex().getArchiveTypeCode());
		
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
		
		TblFileRecordSpecimen fileContentSpecimen = tblFileRecordSpecimenService.getById(model.getFileRecordId());
		
		if((fileContentSpecimen.getFileDate()==null&&model.getFileDate()!=null)
				||(fileContentSpecimen.getFileDate()!=null&&fileContentSpecimen.getFileDate().compareTo(model.getFileDate())!=0)){
				writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSpecimen.getTblFileIndex().getArchiveTitle(),
						"TblFileRecordSpecimen","fileDate",fileContentSpecimen.getFileRecordSn()
						,DateUtil.dateToString(model.getFileDate(),"yyyy-MM-dd"),model.getFileRecordId(),DateUtil.dateToString(fileContentSpecimen.getFileDate(),"yyyy-MM-dd"),
						operateRsn,operateTime,1);
		}
		fileContentSpecimen.setFileDate(model.getFileDate());
		if((fileContentSpecimen.getFileOperator()==null&&model.getFileOperator()!=null)
				||(fileContentSpecimen.getFileOperator()!=null&&!fileContentSpecimen.getFileOperator().equals(model.getFileOperator()))){
				writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSpecimen.getTblFileIndex().getArchiveTitle(),
						"TblFileRecordSpecimen","fileOperator",fileContentSpecimen.getFileRecordSn()
						,model.getFileOperator(),model.getFileRecordId(),fileContentSpecimen.getFileOperator(),
						operateRsn,operateTime,1);
		}
		fileContentSpecimen.setFileOperator(model.getFileOperator());
		fileContentSpecimen.setFileRecordSn(1);//增加的肯定是1
		if((fileContentSpecimen.getKeepDate()==null&&model.getKeepDate()!=null)
				||(fileContentSpecimen.getKeepDate()!=null&&fileContentSpecimen.getKeepDate().compareTo(model.getKeepDate())!=0)){
				writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSpecimen.getTblFileIndex().getArchiveTitle(),
						"TblFileRecordSpecimen","keepDate",fileContentSpecimen.getFileRecordSn()
						,DateUtil.dateToString(model.getKeepDate(),"yyyy-MM-dd"),model.getFileRecordId(),DateUtil.dateToString(fileContentSpecimen.getKeepDate(),"yyyy-MM-dd"),
						operateRsn,operateTime,1);
		}
		fileContentSpecimen.setKeepDate(model.getKeepDate());
		if((fileContentSpecimen.getKeyWord()==null&&model.getKeyWord()!=null)
				||(fileContentSpecimen.getKeyWord()!=null&&!fileContentSpecimen.getKeyWord().equals(model.getKeyWord()))){
				writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSpecimen.getTblFileIndex().getArchiveTitle(),
						"TblFileRecordSpecimen","keyWord",fileContentSpecimen.getFileRecordSn()
						,model.getKeyWord(),model.getFileRecordId(),fileContentSpecimen.getKeyWord(),
						operateRsn,operateTime,1);
		}
		fileContentSpecimen.setKeyWord(model.getKeyWord());
		fileContentSpecimen.setOperateTime(operateTime);
		fileContentSpecimen.setOperator(getCurrentUser().getRealName());
		if((fileContentSpecimen.getRemark()==null&&model.getRemark()!=null)
				||(fileContentSpecimen.getRemark()!=null&&!fileContentSpecimen.getRemark().equals(model.getRemark()))){
				writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSpecimen.getTblFileIndex().getArchiveTitle(),
						"TblFileRecordSpecimen","remark",fileContentSpecimen.getFileRecordSn()
						,model.getRemark(),model.getFileRecordId(),fileContentSpecimen.getRemark(),
						operateRsn,operateTime,1);
		}
		fileContentSpecimen.setRemark(model.getRemark());
		//1：湿标本；2：蜡块；3：切片
		if((fileContentSpecimen.getSpecimenTypeFlag()==null&&model.getSpecimenTypeFlag()!=null)
				||(fileContentSpecimen.getSpecimenTypeFlag()!=null&&!fileContentSpecimen.getSpecimenTypeFlag().equals(model.getSpecimenTypeFlag()))){
				writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSpecimen.getTblFileIndex().getArchiveTitle(),
						"TblFileRecordSpecimen","specimenTypeFlag",fileContentSpecimen.getFileRecordSn()
						,String.valueOf(model.getSpecimenTypeFlag()),model.getFileRecordId(),String.valueOf(fileContentSpecimen.getSpecimenTypeFlag()),
						operateRsn,operateTime,1);
		}
		fileContentSpecimen.setSpecimenTypeFlag(model.getSpecimenTypeFlag());
		if((fileContentSpecimen.getStudyNo()==null&&model.getStudyNo()!=null)
				||(fileContentSpecimen.getStudyNo()!=null&&!fileContentSpecimen.getStudyNo().equals(model.getStudyNo()))){
				writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSpecimen.getTblFileIndex().getArchiveTitle(),
						"TblFileRecordSpecimen","studyNo",fileContentSpecimen.getFileRecordSn()
						,String.valueOf(model.getStudyNo()),model.getFileRecordId(),String.valueOf(fileContentSpecimen.getStudyNo()),
						operateRsn,operateTime,1);
		}
		fileContentSpecimen.setStudyNo(model.getStudyNo());
		if((fileContentSpecimen.getStudyNoType()==null&&studyNoType3!=null)
				||(fileContentSpecimen.getStudyNoType()!=null&&!fileContentSpecimen.getStudyNoType().equals(studyNoType3))){
				writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSpecimen.getTblFileIndex().getArchiveTitle(),
						"TblFileRecordSpecimen","studyNoType",fileContentSpecimen.getFileRecordSn()
						,String.valueOf(studyNoType3),model.getFileRecordId(),String.valueOf(fileContentSpecimen.getStudyNoType()),
						operateRsn,operateTime,1);
		}
		fileContentSpecimen.setStudyNoType(studyNoType3);
		if((fileContentSpecimen.getStudyName()==null&&model.getStudyName()!=null)
				||(fileContentSpecimen.getStudyName()!=null&&!fileContentSpecimen.getStudyName().equals(model.getStudyName()))){
				writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSpecimen.getTblFileIndex().getArchiveTitle(),
						"TblFileRecordSpecimen","studyName",fileContentSpecimen.getFileRecordSn()
						,String.valueOf(model.getStudyName()),model.getFileRecordId(),String.valueOf(fileContentSpecimen.getStudyName()),
						operateRsn,operateTime,1);
		}
		fileContentSpecimen.setStudyName(model.getStudyName());
		if((fileContentSpecimen.getSd()==null&&model.getSd()!=null)
				||(fileContentSpecimen.getSd()!=null&&!fileContentSpecimen.getSd().equals(model.getSd()))){
				writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSpecimen.getTblFileIndex().getArchiveTitle(),
						"TblFileRecordSpecimen","sd",fileContentSpecimen.getFileRecordSn()
						,String.valueOf(model.getSd()),model.getFileRecordId(),String.valueOf(fileContentSpecimen.getSd()),
						operateRsn,operateTime,1);
		}
		fileContentSpecimen.setSd(model.getSd());
		if((fileContentSpecimen.getFileNum()==null&&model.getFileNum()!=null)
				||(fileContentSpecimen.getFileNum()!=null&&!fileContentSpecimen.getFileNum().equals(model.getFileNum()))){
				writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSpecimen.getTblFileIndex().getArchiveTitle(),
						"TblFileRecordSpecimen","fileNum",fileContentSpecimen.getFileRecordSn()
						,String.valueOf(model.getFileNum()),model.getFileRecordId(),String.valueOf(fileContentSpecimen.getFileNum()),
						operateRsn,operateTime,1);
		}
		fileContentSpecimen.setFileNum(model.getFileNum());
		if((fileContentSpecimen.getFileNumUnit()==null&&model.getFileNumUnit()!=null)
				||(fileContentSpecimen.getFileNumUnit()!=null&&!fileContentSpecimen.getFileNumUnit().equals(model.getFileNumUnit()))){
				writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSpecimen.getTblFileIndex().getArchiveTitle(),
						"TblFileRecordSpecimen","fileNumUnit",fileContentSpecimen.getFileRecordSn()
						,String.valueOf(model.getFileNumUnit()),model.getFileRecordId(),String.valueOf(fileContentSpecimen.getFileNumUnit()),
						operateRsn,operateTime,1);
		}
		fileContentSpecimen.setFileNumUnit(model.getFileNumUnit());
		
		
		tblFileRecordSpecimenService.update(fileContentSpecimen);
		
		//更新的时候档案编号不可以更新
		TblFileIndex fileIndex = tblFileIndexService.getById(model.getTblFileIndex().getArchiveCode());
		if(!fileIndex.getArchiveTypeCode().equals(model.getTblFileIndex().getArchiveTypeCode())){
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileIndex.getArchiveTitle(),
					"TblFileIndex","archiveTypeCode",fileContentSpecimen.getFileRecordSn()
					,model.getTblFileIndex().getArchiveTypeCode(),model.getFileRecordId(),fileIndex.getArchiveTypeCode(),
					operateRsn,operateTime,1);
		}
		fileIndex.setArchiveTypeCode(model.getTblFileIndex().getArchiveTypeCode());
		fileIndex.setOperateTime(operateTime);
		//在专题记录中记录过了
		fileIndex.setOperator(getCurrentUser().getRealName());
		
		if((fileIndex.getStorePosition()==null&&model.getTblFileIndex().getStorePosition()!=null)
			||(fileIndex.getStorePosition()!=null&&!fileIndex.getStorePosition().equals(model.getTblFileIndex().getStorePosition()))){
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileIndex.getArchiveTitle(),
					"TblFileIndex","storePosition",fileContentSpecimen.getFileRecordSn()
					,model.getTblFileIndex().getStorePosition(),model.getFileRecordId(),fileIndex.getStorePosition(),
					operateRsn,operateTime,1);
		}
		fileIndex.setStorePosition(model.getTblFileIndex().getStorePosition());
		if((fileIndex.getValidationFlag()==null&&model.getTblFileIndex().getValidationFlag()!=null)
			||(	fileIndex.getValidationFlag()!=null&&!fileIndex.getValidationFlag().equals(validationFlag))){
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileIndex.getArchiveTitle(),
					"TblFileIndex","validationFlag",fileContentSpecimen.getFileRecordSn()
					,String.valueOf(validationFlag),model.getFileRecordId(),String.valueOf(fileIndex.getValidationFlag()),
					operateRsn,operateTime,1);
		}
		fileIndex.setValidationFlag(validationFlag);
		if((fileIndex.getArchiveTitle()==null&&model.getTblFileIndex().getArchiveTitle()!=null)
			||(fileIndex.getArchiveTitle()!=null&&!fileIndex.getArchiveTitle().equals(model.getTblFileIndex().getArchiveTitle()))){
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileIndex.getArchiveTitle(),
					"TblFileIndex","archiveTitle",fileContentSpecimen.getFileRecordSn()
					,model.getTblFileIndex().getArchiveTitle(),model.getFileRecordId(),fileIndex.getArchiveTitle(),
					operateRsn,operateTime,1);
		}
		fileIndex.setArchiveTitle(model.getTblFileIndex().getArchiveTitle());
		
		tblFileIndexService.update(fileIndex);
		
		writeES("更新标本档案", 930, "TblFileRecordSmplReserve", model.getFileRecordId());
		
		Map<String, Object> map0 = new HashMap<String, Object>();
		map0.put("archiveCode", fileContentSpecimen.getTblFileIndex().getArchiveCode());
		map0.put("fileRecordId", fileContentSpecimen.getFileRecordId());
		map0.put("fileDate", DateUtil.dateToString(fileContentSpecimen.getFileDate(),"yyyy-MM-dd"));
		map0.put("fileNum", fileContentSpecimen.getFileNum());
		map0.put("fileNumUnit", fileContentSpecimen.getFileNumUnit());
		map0.put("fileOperator", fileContentSpecimen.getFileOperator());
		map0.put("fileRecordSn", fileContentSpecimen.getFileRecordSn());
		map0.put("keepDate", DateUtil.dateToString(fileContentSpecimen.getKeepDate(),"yyyy-MM-dd"));
		if(fileContentSpecimen.getDestoryDate()!=null)
		{
			map0.put("destoryDate", DateUtil.dateToString(fileContentSpecimen.getDestoryDate(),"yyyy-MM-dd"));
		}
		if(fileContentSpecimen.getSpecimenDestoryDate()!=null)
		{
			map0.put("specimenDestoryDate", DateUtil.dateToString(fileContentSpecimen.getSpecimenDestoryDate(),"yyyy-MM-dd"));
		}
		map0.put("keyWord", fileContentSpecimen.getKeyWord());
		map0.put("operateTime", fileContentSpecimen.getOperateTime());
		map0.put("operator", fileContentSpecimen.getOperator());
		map0.put("remark", fileContentSpecimen.getRemark());
		map0.put("sd", fileContentSpecimen.getSd());
		map0.put("specimenTypeFlag", fileContentSpecimen.getSpecimenTypeFlag());
		map0.put("studyName", fileContentSpecimen.getStudyName());
		map0.put("studyNo", fileContentSpecimen.getStudyNo());
		map0.put("studyNoType", fileContentSpecimen.getStudyNoType());
		map0.put("archiveCode", fileContentSpecimen.getTblFileIndex().getArchiveCode());
		map0.put("archiveTitle", fileContentSpecimen.getTblFileIndex().getArchiveTitle());
		map0.put("archiveTypeCode", fileContentSpecimen.getTblFileIndex().getArchiveTypeCode());
		
		DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(fileContentSpecimen.getTblFileIndex().getArchiveTypeCode());
		if(dictArchiveType!=null)
		{
			map0.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
		}
		map0.put("storePosition", fileContentSpecimen.getTblFileIndex().getStorePosition());
		map0.put("validationFlag", fileContentSpecimen.getTblFileIndex().getValidationFlag());
		map.put("record", map0);
		
		map.put("success", true);

		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void delete()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		writeES("删除标本档案", 931, "TblFileRecordSpecimen", model.getFileRecordId());
		TblFileRecordSpecimen record = tblFileRecordSpecimenService.getById(model.getFileRecordId());
		record.setDelFlag(1);
		Date operateTime = new Date();
		record.setDelTime(operateTime);
		
		tblFileRecordSpecimenService.update(record);
		
		writeTblLog(record.getTblFileIndex().getArchiveCode(),record.getTblFileIndex().getArchiveTitle(),
				"TblFileRecordSmplReserve","delFlag",record.getFileRecordSn()
				,""+0,model.getFileRecordId(),""+1,
				operateRsn,operateTime,2);
		
		map.put("success", true);

		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void destroy()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		TblFileRecordSpecimen record0 = tblFileRecordSpecimenService.getById(model.getFileRecordId());
		Date operateTime = new Date();
		if(destroyType!=null&&destroyType==2){
			record0.setSpecimenDestoryDate(model.getDestoryDate());
			String esLinkId = writeES("销毁标本", 936, "TblFileRecordSpecimen", record0.getFileRecordId());
			record0.setSpecimenDestoryRegSign(esLinkId);
			
			tblFileRecordSpecimenService.update(record0);
			writeTblLog(record0.getTblFileIndex().getArchiveCode(),record0.getTblFileIndex().getArchiveTitle(),
					"TblFileRecordSpecimen","specimenDestoryDate",record0.getFileRecordSn()
					,DateUtil.dateToString(record0.getSpecimenDestoryDate(),"yyyy-MM-dd"),record0.getFileRecordId(),"",
					operateRsn,operateTime,7);//1：修改；2：删除；3：销毁；4：SOP作废；5：合同终止 6销毁供试品留样 7销毁标本
			
			map.put("success", true);
		}else{
			
			List<TblFileRecordSpecimen> recordList = tblFileRecordSpecimenService.getByArchiveCode(record0.getTblFileIndex().getArchiveCode());
			for(TblFileRecordSpecimen record:recordList)
			{
				record.setDestoryDate(model.getDestoryDate());
				String esLinkId = writeES("销毁标本档案", 932, "TblFileRecordSpecimen", record.getFileRecordId());
				record.setDestoryRegSign(esLinkId);
				
				tblFileRecordSpecimenService.update(record);
				
				writeTblLog(record.getTblFileIndex().getArchiveCode(),record.getTblFileIndex().getArchiveTitle(),
						"TblFileRecordSpecimen","destoryDate",record.getFileRecordSn()
						,DateUtil.dateToString(record.getDestoryDate(),"yyyy-MM-dd"),record.getFileRecordId(),"",
						operateRsn,operateTime,3);//1：修改；2：删除；3：销毁；4：SOP作废；5：合同终止
			}
			map.put("success", true);
		}
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void loadList()
	{
		putSearchConIntoSession(searchString, 9);
		
		//Integer isNowValid,Integer isInvalid,Date changeEndDate,Integer yearNum,
		Map<String, Object> resultMap =tblFileRecordSpecimenService.getByCondition(isDestroySpecimen,model.getSpecimenTypeFlag(),fileStartDate,fileEndDate,keepEndDate,isDestory,isValid,searchString,page,rows);
		
		 List<Map<String, Object>> fileStudys = (List<Map<String, Object>>)resultMap.get("rows"); 
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		for(Map<String, Object> fileStudy:fileStudys)
		{
			/*Map<String, Object> map = new HashMap<String, Object>();
			map.put("archiveCode", fileStudy.getTblFileIndex().getArchiveCode());
			map.put("fileRecordId", fileStudy.getFileRecordId());
			map.put("fileDate", DateUtil.dateToString(fileStudy.getFileDate(),"yyyy-MM-dd"));
			map.put("fileNum", fileStudy.getFileNum());
			map.put("fileNumUnit", fileStudy.getFileNumUnit());
			map.put("fileOperator", fileStudy.getFileOperator());
			map.put("fileRecordSn", fileStudy.getFileRecordSn());
			map.put("keepDate", DateUtil.dateToString(fileStudy.getKeepDate(),"yyyy-MM-dd"));
			if(fileStudy.getDestoryDate()!=null)
			{
				map.put("destoryDate", DateUtil.dateToString(fileStudy.getDestoryDate(),"yyyy-MM-dd"));
			}
			if(fileStudy.getSpecimenDestoryDate()!=null)
			{
				map.put("specimenDestoryDate", DateUtil.dateToString(fileStudy.getSpecimenDestoryDate(),"yyyy-MM-dd"));
			}
			map.put("keyWord", fileStudy.getKeyWord());
			map.put("operateTime", fileStudy.getOperateTime());
			map.put("operator", fileStudy.getOperator());
			map.put("remark", fileStudy.getRemark());
			map.put("sd", fileStudy.getSd());
			map.put("specimenTypeFlag", fileStudy.getSpecimenTypeFlag());
			map.put("studyName", fileStudy.getStudyName());
			map.put("studyNo", fileStudy.getStudyNo());
			map.put("studyNoType", fileStudy.getStudyNoType());
			map.put("archiveCode", fileStudy.getTblFileIndex().getArchiveCode());
			map.put("archiveTitle", fileStudy.getTblFileIndex().getArchiveTitle());*/
			
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
			if(fileStudy.get("specimenDestoryDate")!=null)
			{
				fileStudy.put("specimenDestoryDate", DateUtil.dateToString((Date)fileStudy.get("specimenDestoryDate"),"yyyy-MM-dd"));
			}
			String archiveTypeCode = (String)fileStudy.get("archiveTypeCode");
			
			DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(archiveTypeCode);
			if(dictArchiveType!=null)
			{
				fileStudy.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
			}
			/*map.put("storePosition", fileStudy.getTblFileIndex().getStorePosition());
			map.put("validationFlag", fileStudy.getTblFileIndex().getValidationFlag());*/
			
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
		tblLog.setArchiveTypeFlag(10);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
		DictDataTable dictDataTable = dictDataTableService.getByTableNameAndField(tableName,fieldName);
		if(dictDataTable!=null)
		{
			tblLog.setFieldDesc(dictDataTable.getFieldDesc());
		}else {
			//数据字典数据库中没有存储
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
			case 6:
				operateType="销毁留样";
				break;
			case 7:
				operateType="销毁标本";
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

	public Integer getStudyNoType3() {
		return studyNoType3;
	}

	public void setStudyNoType3(Integer studyNoType3) {
		this.studyNoType3 = studyNoType3;
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

	public Integer getDestroyType() {
		return destroyType;
	}

	public void setDestroyType(Integer destroyType) {
		this.destroyType = destroyType;
	}

	
	public Integer getIsDestroySpecimen() {
		return isDestroySpecimen;
	}

	public void setIsDestroySpecimen(Integer isDestroySpecimen) {
		this.isDestroySpecimen = isDestroySpecimen;
	}
}
