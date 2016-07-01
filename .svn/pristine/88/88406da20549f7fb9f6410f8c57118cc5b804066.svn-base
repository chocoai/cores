package com.lanen.view.action;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.junit.experimental.results.ResultMatchers;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;

import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.DictDataTable;
import com.lanen.model.TblFileContentStudy;
import com.lanen.model.TblFileIndex;
import com.lanen.model.TblFileRecord;
import com.lanen.model.User;
import com.lanen.model.archive.DictArchivePosition;
import com.lanen.model.archive.DictArchiveType;
import com.lanen.model.TblLog;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.contract.TblContract;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.studyplan.DictStudyType;

import com.lanen.service.UserService;
import com.lanen.service.archive.DictArchivePositionService;
import com.lanen.service.archive.DictArchiveTypeService;
import com.lanen.service.archive.DictDataTableService;
import com.lanen.service.archive.TblFileContentStudyService;
import com.lanen.service.archive.TblFileIndexService;
import com.lanen.service.archive.TblFileRecordService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.archive.TblLog2Service;
import com.lanen.service.contract.TblContractService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.contract.TblTestItemService;
import com.lanen.service.studyplan.DictStudyTypeService;
import com.lanen.util.DateUtil;
import com.lanen.util.MathUtils;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TblFileContentStudyAction extends BaseAction<TblFileContentStudy> {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -603022677491684058L;
	@Resource
	private TblLog2Service tblLog2Service;
	@Resource
	private UserService userService;
	@Resource
	private TblFileContentStudyService tblFileContentStudyService;
	@Resource
	private TblFileIndexService tblFileIndexService;
	@Resource
	private TblFileRecordService tblFileRecordService;
	
	@Resource
	private TblStudyItemService tblStudyItemService;
	@Resource
	private TblTestItemService tblTestItemService;
	@Resource
	private DictStudyTypeService dictStudyTypeService;
	@Resource
	private DictArchiveTypeService dictArchiveTypeService;
	@Resource
	private DictDataTableService dictDataTableService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private DictArchivePositionService dictArchivePositionService;
	@Resource
	private TblContractService tblContractService;
	
	
	private String archiveTitle;
	private String archiveTypeCode;
	private Integer validationFlag;
	private String storePosition;
	
	private Date fileStartDate;
	private Date fileEndDate;
	private Date keepEndDate;
	private String searchString;
	private Integer isDestory;
	private Integer isValid;
	
	private String operateRsn;
	
	private Integer rows;// 每页显示的记录数 
	private Integer page;// 当前第几页 
	
	private Date destoryDate;
	
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
			fileIndex.setArchiveTypeFlag(1);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
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
			record.setArchiveMediaEleCode(model.getTblFileRecord().getArchiveMediaEleCode());
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
			
			TblFileContentStudy fileContentStudy = new TblFileContentStudy();
			fileContentStudy.setArchiveCode(model.getArchiveCode());
			fileContentStudy.setFileRecordId(key);
			fileContentStudy.setOperateTime(operateTime);
			fileContentStudy.setOperator(getCurrentUser().getRealName());
			fileContentStudy.setSdname(model.getSdname());
			fileContentStudy.setStudyName(model.getStudyName());
			fileContentStudy.setStudySponerName(model.getStudySponerName());
			fileContentStudy.setStudyNo(model.getStudyNo());
			fileContentStudy.setStudyNoType(model.getStudyNoType());
			fileContentStudy.setContractCode(model.getContractCode());
			
			fileContentStudy.setTblFileRecord(record);
			
			tblFileContentStudyService.save(fileContentStudy);
			
			Map<String, Object> map0 = new HashMap<String, Object>();
			map0.put("archiveCode", fileContentStudy.getArchiveCode());
			map0.put("fileRecordId", fileContentStudy.getFileRecordId());
			map0.put("studyNo", fileContentStudy.getStudyNo());
			map0.put("studyName", fileContentStudy.getStudyName());
			map0.put("contractCode", fileContentStudy.getContractCode());
			map0.put("studySponerName", fileContentStudy.getStudySponerName());
			map0.put("sdname", fileContentStudy.getSdname());
			map0.put("studyNoType", fileContentStudy.getStudyNoType());
			
			map0.put("archiveTypeCode", fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
			
			DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
			map0.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
			
			map0.put("archiveTitle", fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle());
			map0.put("storePosition", fileContentStudy.getTblFileRecord().getTblFileIndex().getStorePosition());
			map0.put("fileDate", DateUtil.dateToString(fileContentStudy.getTblFileRecord().getFileDate(),"yyyy-MM-dd"));
			map0.put("fileRecordSn", fileContentStudy.getTblFileRecord().getFileRecordSn());
			map0.put("archiveMaker", fileContentStudy.getTblFileRecord().getArchiveMaker());
			map0.put("fileOperator", fileContentStudy.getTblFileRecord().getFileOperator());
			map0.put("keepDate", DateUtil.dateToString(fileContentStudy.getTblFileRecord().getKeepDate(),"yyyy-MM-dd"));
			map.put("archiveMaker", fileContentStudy.getTblFileRecord().getArchiveMaker());
			map0.put("remark", fileContentStudy.getTblFileRecord().getRemark());
			if(fileContentStudy.getTblFileRecord().getDestoryDate()!=null)
			{
				map.put("destoryDate", DateUtil.dateToString(fileContentStudy.getTblFileRecord().getDestoryDate(),"yyyy-MM-dd"));
			}
			map0.put("validationFlag", fileContentStudy.getTblFileRecord().getTblFileIndex().getValidationFlag());//0：否；1：验证数据
			map0.put("archiveMediaFlag", fileContentStudy.getTblFileRecord().getArchiveMediaFlag());
			map0.put("archiveMedia", fileContentStudy.getTblFileRecord().getArchiveMedia());
			map0.put("archiveMediaEleCode", fileContentStudy.getTblFileRecord().getArchiveMediaEleCode());
			
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
		boolean isExistArchiveCode = tblFileIndexService.isExistArchiveCode(model.getArchiveCode());
		//if(!isExistArchiveCode)
		//{
			//追加归档不用修改fileIndex
			TblFileIndex fileIndex = tblFileIndexService.getById(model.getArchiveCode());
			
			
			String key = tblFileRecordService.getKey("TblFileRecord");
			TblFileRecord record = new TblFileRecord();			
			record.setArchiveMaker(model.getTblFileRecord().getArchiveMaker());
			record.setArchiveMedia(model.getTblFileRecord().getArchiveMedia());
			record.setArchiveMediaFlag(model.getTblFileRecord().getArchiveMediaFlag());
			record.setArchiveMediaEleCode(model.getTblFileRecord().getArchiveMediaEleCode());
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
			
			TblFileContentStudy fileContentStudy = new TblFileContentStudy();
			fileContentStudy.setArchiveCode(model.getArchiveCode());
			fileContentStudy.setFileRecordId(key);
			fileContentStudy.setOperateTime(operateTime);
			fileContentStudy.setOperator(getCurrentUser().getRealName());
			fileContentStudy.setSdname(model.getSdname());
			fileContentStudy.setStudyName(model.getStudyName());
			fileContentStudy.setStudySponerName(model.getStudySponerName());
			fileContentStudy.setStudyNo(model.getStudyNo());
			fileContentStudy.setStudyNoType(model.getStudyNoType());
			fileContentStudy.setContractCode(model.getContractCode());
			fileContentStudy.setTblFileRecord(record);
			
			tblFileContentStudyService.save(fileContentStudy);
			
			Map<String, Object> map0 = new HashMap<String, Object>();
			map0.put("archiveCode", fileContentStudy.getArchiveCode());
			map0.put("fileRecordId", fileContentStudy.getFileRecordId());
			map0.put("studyNo", fileContentStudy.getStudyNo());
			map0.put("studyName", fileContentStudy.getStudyName());
			map0.put("contractCode", fileContentStudy.getContractCode());
			map0.put("studySponerName", fileContentStudy.getStudySponerName());
			map0.put("sdname", fileContentStudy.getSdname());
			map0.put("studyNoType", fileContentStudy.getStudyNoType());
			
			map0.put("archiveTypeCode", fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
			
			DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
			map0.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
			
			map0.put("archiveTitle", fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle());
			map0.put("storePosition", fileContentStudy.getTblFileRecord().getTblFileIndex().getStorePosition());
			map0.put("fileDate", DateUtil.dateToString(fileContentStudy.getTblFileRecord().getFileDate(),"yyyy-MM-dd"));
			map0.put("fileRecordSn", fileContentStudy.getTblFileRecord().getFileRecordSn());
			map0.put("archiveMaker", fileContentStudy.getTblFileRecord().getArchiveMaker());
			map0.put("fileOperator", fileContentStudy.getTblFileRecord().getFileOperator());
			map0.put("keepDate", DateUtil.dateToString(fileContentStudy.getTblFileRecord().getKeepDate(),"yyyy-MM-dd"));
			map.put("archiveMaker", fileContentStudy.getTblFileRecord().getArchiveMaker());
			map0.put("remark", fileContentStudy.getTblFileRecord().getRemark());
			if(fileContentStudy.getTblFileRecord().getDestoryDate()!=null)
			{
				map.put("destoryDate", DateUtil.dateToString(fileContentStudy.getTblFileRecord().getDestoryDate(),"yyyy-MM-dd"));
			}
			map0.put("validationFlag", fileContentStudy.getTblFileRecord().getTblFileIndex().getValidationFlag());//0：否；1：验证数据
			map0.put("archiveMediaFlag", fileContentStudy.getTblFileRecord().getArchiveMediaFlag());
			map0.put("archiveMedia", fileContentStudy.getTblFileRecord().getArchiveMedia());
			map0.put("archiveMediaEleCode", fileContentStudy.getTblFileRecord().getArchiveMediaEleCode());
			
			map.put("record", map0);
			
			map.put("success", true);
		//}else {
		//	map.put("success", false);
		//	map.put("msg", "档案编号已经存在");
			
		//}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	
	public void getStudyNameByStudyNo()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		Integer studyType =model.getStudyNoType();
		if(studyType!=null&&studyType==2)
		{
			TblContract contract = tblContractService.getByContractCode(model.getStudyNo());
			if(contract!=null){
				String studySponerName = "";
				if(contract!=null)
				{
					studySponerName = contract.getSponsorName();
					if(studySponerName!=null)
					{
						if(!studySponerName.equals(contract.getVenderName()))
							studySponerName+=","+contract.getVenderName();
					}else if(contract.getVenderName()!=null){
						studySponerName = contract.getVenderName();
					}
					
				}
				List<TblStudyItem> studyItems = tblStudyItemService.getByContractCode(contract.getContractCode());
				String sd = "";
				String qa = "";
				if(studyItems!=null&&studyItems.size()>0)
				{
					TblStudyItem studyItem = studyItems.get(0);
					sd = studyItem.getSd();
					qa = studyItem.getQa();
				}
				map.put("studySponerName", studySponerName);
				map.put("studyNoName", 	contract.getContractName());
				map.put("SDName", sd);
				map.put("qa", qa);
				map.put("contractCode", contract.getContractCode());
				map.put("success", true);
			}else {
				map.put("success", false);
				map.put("msg", "合同不存在！");
			}
		}else if(studyType!=null&&studyType==1){
			
			TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(model.getStudyNo());
			String studyNoName = "";
			if(studyItem!=null)
			{
				String tiNo = studyItem.getTiNo();
				String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
				DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
				if(null != dictStudyType &&  dictStudyType.getAnimalHave() == 1){
					studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
				}else{
					studyNoName = testItemName+studyItem.getStudyName();
				}
				TblContract contract = tblContractService.getByContractCode(studyItem.getContractCode());
				String studySponerName = "";
				if(contract!=null)
				{
					studySponerName = contract.getSponsorName();
					if(studySponerName!=null)
					{
						if(!studySponerName.equals(contract.getVenderName()))
							studySponerName+=","+contract.getVenderName();
					}else if(contract.getVenderName()!=null){
						studySponerName = contract.getVenderName();
					}
					map.put("contractCode", contract.getContractCode());
				}
				
				map.put("studySponerName", studySponerName);
				map.put("studyNoName", studyNoName);
				map.put("SDName", studyItem.getSd());
				map.put("qa", studyItem.getQa());
				map.put("success", true);
			}else {
				map.put("success", false);
				map.put("msg","专题不存在！");
			}
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	
	
	public void getMaxArchiveCode() {
		Map<String, Object> map = new HashMap<String, Object>();
		String archiveCode = tblFileIndexService.getMaxCodeByTypeCode(archiveTypeCode);
		
		if(archiveCode==null)
		{
			map.put("archiveCode", "XSZK-"+archiveTypeCode);
		}else {
			map.put("archiveCode", MathUtils.add1ToStringInt(archiveCode));
			
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	
	public void update()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		Date operateTime = new Date();
		
		TblFileContentStudy fileContentStudy = tblFileContentStudyService.getById(model.getFileRecordId());
		
		fileContentStudy.setOperateTime(operateTime);
		if((fileContentStudy.getOperator()!=null&&!fileContentStudy.getOperator().equals(getCurrentUser().getRealName())))
		{
			writeTblLog(model.getArchiveCode(),fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileContentStudy","operator",fileContentStudy.getTblFileRecord().getFileRecordSn()
					,getCurrentUser().getRealName(),model.getFileRecordId(),fileContentStudy.getOperator()
					,operateRsn,operateTime,1);
		}
		fileContentStudy.setOperator(getCurrentUser().getRealName());
		if((fileContentStudy.getSdname()==null&&model.getSdname()!=null)
				||(fileContentStudy.getSdname()!=null&&!fileContentStudy.getSdname().equals(model.getSdname())))
		{
			writeTblLog(model.getArchiveCode(),fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileContentStudy","studyNo",fileContentStudy.getTblFileRecord().getFileRecordSn()
					,model.getSdname(),model.getFileRecordId(),fileContentStudy.getSdname()
					,operateRsn,operateTime,1);
		}
		fileContentStudy.setSdname(model.getSdname());
		if((fileContentStudy.getStudyName()==null&&model.getStudyName()!=null)
				||(fileContentStudy.getStudyName()!=null&&!fileContentStudy.getStudyName().equals(model.getStudyName())))
		{
			writeTblLog(model.getArchiveCode(),fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileContentStudy","studyNo",fileContentStudy.getTblFileRecord().getFileRecordSn()
					,model.getStudyName(),model.getFileRecordId(),fileContentStudy.getStudyName()
					,operateRsn,operateTime,1);
		}
		fileContentStudy.setStudyName(model.getStudyName());
		if((fileContentStudy.getContractCode()==null&&model.getContractCode()!=null)
				||(fileContentStudy.getContractCode()!=null&&!fileContentStudy.getContractCode().equals(model.getContractCode())))
		{
			writeTblLog(model.getArchiveCode(),fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileContentStudy","contractCode",fileContentStudy.getTblFileRecord().getFileRecordSn()
					,model.getContractCode(),model.getFileRecordId(),fileContentStudy.getContractCode()
					,operateRsn,operateTime,1);
		}
		fileContentStudy.setContractCode(model.getContractCode());
		if((fileContentStudy.getStudySponerName()==null&&model.getStudySponerName()!=null)
				||(fileContentStudy.getStudySponerName()!=null&&!fileContentStudy.getStudySponerName().equals(model.getStudySponerName())))
		{
			writeTblLog(model.getArchiveCode(),fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileContentStudy","studySponerName",fileContentStudy.getTblFileRecord().getFileRecordSn()
					,model.getStudySponerName(),model.getFileRecordId(),fileContentStudy.getStudySponerName()
					,operateRsn,operateTime,1);
		}
		fileContentStudy.setStudySponerName(model.getStudySponerName());
		if((fileContentStudy.getStudyNo()==null&&model.getStudyNo()!=null)
				||(fileContentStudy.getStudyNo()!=null&&!fileContentStudy.getStudyNo().equals(model.getStudyNo())))//只记录一个studyNo的改变就可以了，studyName和SD是跟着的
		{
			writeTblLog(model.getArchiveCode(),fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileContentStudy","studyNo",fileContentStudy.getTblFileRecord().getFileRecordSn()
					,model.getStudyNo(),model.getFileRecordId(),fileContentStudy.getStudyNo()
					,operateRsn,operateTime,1);
		}
		fileContentStudy.setStudyNo(model.getStudyNo());
		if((fileContentStudy.getStudyNoType()==null&&model.getStudyNoType()!=null)
				||(fileContentStudy.getStudyNoType()!=null&&!fileContentStudy.getStudyNoType().equals(model.getStudyNoType())))//只记录一个studyNo的改变就可以了，studyName和SD是跟着的
		{
			writeTblLog(model.getArchiveCode(),fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileContentStudy","studyNoType",fileContentStudy.getTblFileRecord().getFileRecordSn()
					,String.valueOf(model.getStudyNoType()),model.getFileRecordId(),String.valueOf(fileContentStudy.getStudyNoType())
					,operateRsn,operateTime,1);
		}
		fileContentStudy.setStudyNoType(model.getStudyNoType());
		
		tblFileContentStudyService.update(fileContentStudy);
		
		TblFileRecord record = tblFileRecordService.getById(model.getFileRecordId());//编辑的时候根据这个判断		
		if((record.getArchiveMaker()==null&&model.getTblFileRecord().getArchiveMaker()!=null)
				||(record.getArchiveMaker()!=null&&!record.getArchiveMaker().equals(model.getTblFileRecord().getArchiveMaker())))
		{
			writeTblLog(model.getArchiveCode(),fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","archiveMaker",record.getFileRecordSn()
					,record.getArchiveMaker(),model.getFileRecordId(),model.getTblFileRecord().getArchiveMaker()
					,operateRsn,operateTime,1);
		}
		record.setArchiveMaker(model.getTblFileRecord().getArchiveMaker());
		if(!record.getArchiveMediaFlag().equals(model.getTblFileRecord().getArchiveMediaFlag()))
		{
			writeTblLog(model.getArchiveCode(),fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","archiveMediaFlag",record.getFileRecordSn()
					,String.valueOf(record.getArchiveMediaFlag()),model.getFileRecordId(),String.valueOf(model.getTblFileRecord().getArchiveMediaFlag())
					,operateRsn,operateTime,1);
		}
		if(record.getArchiveMedia()!=null&&!record.getArchiveMedia().equals(model.getTblFileRecord().getArchiveMedia()))
		{
			writeTblLog(model.getArchiveCode(),fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","archiveMedia",record.getFileRecordSn()
					,record.getArchiveMedia(),model.getFileRecordId(),model.getTblFileRecord().getArchiveMedia()
					,operateRsn,operateTime,1);
		}
		record.setArchiveMedia(model.getTblFileRecord().getArchiveMedia());
		record.setArchiveMediaFlag(model.getTblFileRecord().getArchiveMediaFlag());
		if((record.getArchiveMediaEleCode()==null&&model.getTblFileRecord().getArchiveMediaEleCode()!=null)
				||(record.getArchiveMediaEleCode()!=null&&!record.getArchiveMediaEleCode().equals(model.getTblFileRecord().getArchiveMediaEleCode())))
		{
			writeTblLog(model.getArchiveCode(),fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","archiveMediaEleCode",record.getFileRecordSn()
					,record.getArchiveMediaEleCode(),model.getFileRecordId(),model.getTblFileRecord().getArchiveMediaEleCode()
					,operateRsn,operateTime,1);
		}
		record.setArchiveMediaEleCode(model.getTblFileRecord().getArchiveMediaEleCode());
		if((record.getDestoryDate()==null&&model.getTblFileRecord().getDestoryDate()!=null)||
				(record.getDestoryDate()!=null&&record.getDestoryDate().compareTo(model.getTblFileRecord().getDestoryDate())!=0))
		{
			writeTblLog(model.getArchiveCode(),fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","destoryDate",record.getFileRecordSn()
					,DateUtil.dateToString(record.getDestoryDate(),"yyyy-MM-dd"),model.getFileRecordId(),DateUtil.dateToString(model.getTblFileRecord().getDestoryDate(),"yyyy-MM-dd")
					,operateRsn,operateTime,1);
		}
		record.setDestoryDate(model.getTblFileRecord().getDestoryDate());
		if((record.getFileDate()==null&&model.getTblFileRecord().getFileDate()!=null)
				||(record.getFileDate()!=null&&record.getFileDate().compareTo(model.getTblFileRecord().getFileDate())!=0))
		{
			writeTblLog(model.getArchiveCode(),fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","fileDate",record.getFileRecordSn()
					,DateUtil.dateToString(record.getFileDate(),"yyyy-MM-dd"),model.getFileRecordId(),DateUtil.dateToString(model.getTblFileRecord().getFileDate(),"yyyy-MM-dd")
					,operateRsn,operateTime,1);
		}
		record.setFileDate(model.getTblFileRecord().getFileDate());
		
		if((record.getFileOperator()==null&&model.getTblFileRecord().getFileOperator()!=null)
				||(record.getFileOperator()!=null&&!record.getFileOperator().equals(model.getTblFileRecord().getFileOperator())))
		{
			writeTblLog(model.getArchiveCode(),fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","fileOperator",record.getFileRecordSn()
					,record.getFileOperator(),model.getFileRecordId(),model.getTblFileRecord().getFileOperator()
					,operateRsn,operateTime,1);
		}
		
		record.setFileOperator(model.getTblFileRecord().getFileOperator());
		//record.setFileRecordSn(1);//sn是后台维护的。更新的时候用不到这个
		if((record.getKeepDate()==null&&model.getTblFileRecord().getKeepDate()!=null)
				||(record.getKeepDate()!=null&&record.getKeepDate().compareTo(model.getTblFileRecord().getKeepDate())!=0))
		{
			writeTblLog(model.getArchiveCode(),fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","keepDate",record.getFileRecordSn()
					,DateUtil.dateToString(record.getKeepDate(),"yyyy-MM-dd"),model.getFileRecordId(),DateUtil.dateToString(model.getTblFileRecord().getKeepDate(),"yyyy-MM-dd")
					,operateRsn,operateTime,1);
		}
		record.setKeepDate(model.getTblFileRecord().getKeepDate());

		
		if((record.getKeyWord()==null&&model.getTblFileRecord().getKeyWord()!=null)
				||(record.getKeyWord()!=null&&!record.getKeyWord().equals(model.getTblFileRecord().getKeyWord())))
		{
			writeTblLog(model.getArchiveCode(),fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle()
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
			writeTblLog(model.getArchiveCode(),fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","remark",record.getFileRecordSn()
					,record.getRemark(),model.getFileRecordId(),model.getTblFileRecord().getRemark()
					,operateRsn,operateTime,1);
		}
		record.setRemark(model.getTblFileRecord().getRemark());
		
		tblFileRecordService.update(record);
		
		//更新的时候档案编号不可以更新
		TblFileIndex fileIndex = tblFileIndexService.getById(model.getArchiveCode());
		if((fileIndex.getArchiveTypeCode()==null&&archiveTypeCode!=null)
				||(fileIndex.getArchiveTypeCode()!=null&&!fileIndex.getArchiveTypeCode().equals(archiveTypeCode))){
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
		
		writeES("更新专题档案", 903, "TblFileContentStudy", model.getFileRecordId());
		
		Map<String, Object> map0 = new HashMap<String, Object>();
		map0.put("archiveCode", fileContentStudy.getArchiveCode());
		map0.put("fileRecordId", fileContentStudy.getFileRecordId());
		map0.put("studyNo", fileContentStudy.getStudyNo());
		map0.put("studyName", fileContentStudy.getStudyName());
		map0.put("contractCode", fileContentStudy.getContractCode());
		map0.put("studySponerName", fileContentStudy.getStudySponerName());
		map0.put("sdname", fileContentStudy.getSdname());
		map0.put("studyNoType", fileContentStudy.getStudyNoType());
		
		map0.put("archiveTypeCode", fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
		
		DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
		map0.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
		
		map0.put("archiveTitle", fileContentStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle());
		map0.put("storePosition", fileContentStudy.getTblFileRecord().getTblFileIndex().getStorePosition());
		map0.put("fileDate", DateUtil.dateToString(fileContentStudy.getTblFileRecord().getFileDate(),"yyyy-MM-dd"));
		map0.put("fileRecordSn", fileContentStudy.getTblFileRecord().getFileRecordSn());
		map0.put("archiveMaker", fileContentStudy.getTblFileRecord().getArchiveMaker());
		map0.put("fileOperator", fileContentStudy.getTblFileRecord().getFileOperator());
		map0.put("keepDate", DateUtil.dateToString(fileContentStudy.getTblFileRecord().getKeepDate(),"yyyy-MM-dd"));
		map.put("archiveMaker", fileContentStudy.getTblFileRecord().getArchiveMaker());
		map0.put("remark", fileContentStudy.getTblFileRecord().getRemark());
		if(fileContentStudy.getTblFileRecord().getDestoryDate()!=null)
		{
			map.put("destoryDate", DateUtil.dateToString(fileContentStudy.getTblFileRecord().getDestoryDate(),"yyyy-MM-dd"));
		}
		map0.put("validationFlag", fileContentStudy.getTblFileRecord().getTblFileIndex().getValidationFlag());//0：否；1：验证数据
		map0.put("archiveMediaFlag", fileContentStudy.getTblFileRecord().getArchiveMediaFlag());
		map0.put("archiveMedia", fileContentStudy.getTblFileRecord().getArchiveMedia());
		map0.put("archiveMediaEleCode", fileContentStudy.getTblFileRecord().getArchiveMediaEleCode());
		
		map.put("record", map0);
		map.put("success", true);

		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void delete()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		writeES("删除专题档案", 904, "TblFileContentStudy", model.getFileRecordId());
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
			String esLinkId = writeES("销毁专题档案", 919, "TblFileContentStudy", record.getFileRecordId());
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
		putSearchConIntoSession(searchString,0);
		
		Map<String, Object> resultMap = tblFileContentStudyService.getByCondition(model.getStudyNoType(),fileStartDate,fileEndDate,keepEndDate,isDestory,isValid,searchString,page,rows);
		
		List<Map<String, Object>> fileStudys = (List<Map<String, Object>>)resultMap.get("rows");
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		for(Map<String, Object> fileStudy:fileStudys)
		{
			//Map<String, Object> map = new HashMap<String, Object>();
			/*map.put("archiveCode", fileStudy.getArchiveCode());
			map.put("fileRecordId", fileStudy.getFileRecordId());
			map.put("studyNo", fileStudy.getStudyNo());
			map.put("studyName", fileStudy.getStudyName());
			map.put("contractCode", fileStudy.getContractCode());
			map.put("studySponerName", fileStudy.getStudySponerName());
			map.put("sdname", fileStudy.getSdname());
			map.put("studyNoType", fileStudy.getStudyNoType());
			
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
			map.put("keepDate", DateUtil.dateToString(fileStudy.getTblFileRecord().getKeepDate(),"yyyy-MM-dd"));map.put("archiveMaker", fileStudy.getTblFileRecord().getArchiveMaker());
			map.put("remark", fileStudy.getTblFileRecord().getRemark());
			if(fileStudy.getTblFileRecord().getDestoryDate()!=null)
			{
				map.put("destoryDate", DateUtil.dateToString(fileStudy.getTblFileRecord().getDestoryDate(),"yyyy-MM-dd"));
			}
			map.put("validationFlag", fileStudy.getTblFileRecord().getTblFileIndex().getValidationFlag());//0：否；1：验证数据
			map.put("archiveMediaFlag", fileStudy.getTblFileRecord().getArchiveMediaFlag());
			map.put("archiveMedia", fileStudy.getTblFileRecord().getArchiveMedia());
			map.put("archiveMediaEleCode", fileStudy.getTblFileRecord().getArchiveMediaEleCode());*/
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
			
			mapList.add(fileStudy);
		}
	
		resultMap.put("rows", mapList);
		writeJson(JsonPluginsUtil.beanToJson(resultMap));
	}
	public void getUserList()
	{
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		
		/*List<User> users = userService.findByPrivilegeName2("档案管理_查看");
		List<User> users2 = userService.findByPrivilegeName2("档案管理_编辑");
		for (User user:users2) {
			if(!users.contains(user))
			{
				users.add(user);
			}
		}*/
		List<User> users = userService.getUserListByDepartmentId("档案部");//档案部的id和名称都是：档案部
		for(User user:users)
		{
			Map<String,Object> map = new HashMap<String, Object>();
			//map.put("userName", user.getUserName());
			map.put("realName", user.getRealName());
			mapList.add(map);
		}
		writeJson(JsonPluginsUtil.beanListToJson(mapList));
		
	}
	
	
	public void loadArchivePositionTree()
	{
		List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>();
		//id;text; state ; children; iconCls ;
		
		List<DictArchivePosition> list = dictArchivePositionService.findAll();
		//生成树形结构
		getTree(list,tree);
		//按sn排序
		sortBySn(tree);
		
		String json = JsonPluginsUtil.beanListToJson(tree);
		writeJson(json);
		
	}
	public void sortBySn(List<Map<String, Object>> tree)
	{
		Collections.sort(tree, new Comparator<Map<String, Object>>() {

			public int compare(Map<String, Object> arg0,
					Map<String, Object> arg1) {
				if(arg0!=null&&arg1!=null)
				{
					if(arg0.get("sn")==null&&arg1.get("sn")!=null)
					{
						return -1;
					}else if(arg0.get("sn")!=null&&arg1.get("sn")!=null)
					{
						return (Integer)arg0.get("sn")-(Integer)arg1.get("sn");
					}else if(arg0.get("sn")!=null&&arg1.get("sn")==null){
						return 1;
					}else return 0;
				}else if(arg0==null&&arg1!=null){
					return -1;
				}else if(arg0!=null&&arg1==null)
				{
					return 1;
				}else return 0;
			}
		});
		for(Map<String, Object> map:tree)
		{
			if(map.get("children")!=null)
			{
				List<Map<String, Object>> childList = (List<Map<String, Object>>)map.get("children");
				sortBySn(childList);
			}
				
		}
		
	}
	public void getTree(List<DictArchivePosition> list,List<Map<String, Object>> tree)
	{
		List<DictArchivePosition> noDealList = new ArrayList<DictArchivePosition>();
		Map<String, Object> ctm = null;
		
		for(int i=0;i<list.size();i++)
		{
			DictArchivePosition type=list.get(i);
			if(type.getPid()==null||"".equals(type.getPid()))
			{//没有父类的直接加进去
				ctm = new HashMap<String, Object>();
				ctm.put("id",type.getId());
				ctm.put("sn", type.getSn());
				ctm.put("pid",type.getPid());
				ctm.put("text", type.getPositionName());
				ctm.put("children",new ArrayList<Map<String, Object>>());
				
				tree.add(ctm);
				
			}else {//有父类的处理
				
				Map<String, Object> parent = getParent(type,tree);
				
				if(parent!=null)//父类不为空，并且父类在tree中存在
				{
					Map<String, Object> ctmChile = new HashMap<String, Object>();
					ctmChile.put("id",type.getId());
					ctmChile.put("pid",type.getPid());
					ctmChile.put("sn", type.getSn());
					ctmChile.put("text",type.getPositionName());
					if(parent.get("children")==null&&!"".equals(parent.get("children")))
						parent.put("children",new ArrayList<Map<String, Object>>());
					parent.put("state", "closed");
					((List<Map<String, Object>>)parent.get("children")).add(ctmChile);
				}else {//父类不为空，并且tree中不存在,先处理list中的其他的
					noDealList.add(type);
				}
			}
			
		}
		if(noDealList.size()>0)
		{
			getTree(noDealList, tree);
		}
	}
	public Map<String, Object> getParent(DictArchivePosition type,List<Map<String, Object>> tree)
	{
		Map<String, Object> parent = null;
		for(Map<String, Object> model:tree)
		{
			if(model.get("id").equals(type.getPid()))
			{
				parent=model;
				break;
			}
			if(model.get("children")!=null)
			{
				parent=getParent(type,(List<Map<String,Object>>)model.get("children"));
				if(parent!=null)
				{
					break;
				}
			}
		}
		
		return parent;
		
	}
	
	public void writeTblLog(String archiveCode,String archiveTitle,String tableName,String fieldName,Integer fileRecordSn
			,String newValue,String oldFileRecordId,String oldValue,String operateRsn,Date operateTime,Integer operateTypeFlag)//操作日志
	{
		TblLog tblLog = new TblLog();
		tblLog.setArchiveCode(archiveCode);
		tblLog.setArchiveTitle(archiveTitle);
		tblLog.setArchiveTypeFlag(1);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
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
	public Integer getValidationFlag() {
		return validationFlag;
	}
	public void setValidationFlag(Integer validationFlag) {
		this.validationFlag = validationFlag;
	}
	public String getStorePosition() {
		return storePosition;
	}
	public void setStorePosition(String storePosition) {
		this.storePosition = storePosition;
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
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
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
	public String getOperateRsn() {
		return operateRsn;
	}
	public void setOperateRsn(String operateRsn) {
		this.operateRsn = operateRsn;
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
