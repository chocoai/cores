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
import com.lanen.model.TblFileRecord;
import com.lanen.model.TblFileRecordSmplReserve;
import com.lanen.model.TblLog;
import com.lanen.model.User;
import com.lanen.model.archive.DictArchiveType;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.contract.TblContract;
import com.lanen.model.contract.TblTestItem;
import com.lanen.service.archive.DictArchiveTypeService;
import com.lanen.service.archive.DictDataTableService;
import com.lanen.service.archive.TblFileIndexService;
import com.lanen.service.archive.TblFileRecordService;
import com.lanen.service.archive.TblFileRecordSmplReserveService;
import com.lanen.service.archive.TblLog2Service;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.TblContractService;
import com.lanen.service.contract.TblTestItemService;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class TblFileRecordSmplReserveAction extends BaseAction<TblFileRecordSmplReserve> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8599820054286284484L;

	@Resource
	private TblFileIndexService tblFileIndexService;
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
	private TblFileRecordSmplReserveService tblFileRecordSmplReserveService;
	@Resource
	private TblTestItemService tblTestItemService;
	@Resource
	private TblContractService tblContractService;
	
	
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
	
	private Integer destroyType;
	
	private Integer isSmplKeepEndDate;
	private Date smplKeepEndDate;
	private Integer isDestroySmpl;
	
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
			fileIndex.setArchiveTypeFlag(9);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
			fileIndex.setOperateTime(operateTime);
			fileIndex.setOperator(getCurrentUser().getRealName());
			fileIndex.setStorePosition(model.getTblFileIndex().getStorePosition());
			fileIndex.setValidationFlag(validationFlag);
			
			tblFileIndexService.save(fileIndex);
			
			
			TblFileRecordSmplReserve fileContentSmpl = new TblFileRecordSmplReserve();
			fileContentSmpl.setTblFileIndex(fileIndex);
			fileContentSmpl.setFileRecordId(tblFileRecordSmplReserveService.getKey("TblFileRecordSmplReserve"));
			
			fileContentSmpl.setBatchCode(model.getBatchCode());
			fileContentSmpl.setContainer(model.getContainer());
			fileContentSmpl.setFileDate(model.getFileDate());
			fileContentSmpl.setFileOperator(model.getFileOperator());
			fileContentSmpl.setFileRecordSn(1);//增加的肯定是1
			fileContentSmpl.setKeepDate(model.getKeepDate());
			fileContentSmpl.setKeyWord(model.getKeyWord());
			fileContentSmpl.setOperateTime(operateTime);
			fileContentSmpl.setOperator(getCurrentUser().getRealName());
			fileContentSmpl.setRemark(model.getRemark());
			fileContentSmpl.setReportUnitName(model.getReportUnitName());
			fileContentSmpl.setReserveDate(model.getReserveDate());
			fileContentSmpl.setReserveMan(model.getReserveMan());
			fileContentSmpl.setReserveNum(model.getReserveNum());
			fileContentSmpl.setReserveNumUnit(model.getReserveNumUnit());
			fileContentSmpl.setReserveRecDate(model.getReserveRecDate());
			fileContentSmpl.setReserveRecMan(model.getReserveRecMan());
			fileContentSmpl.setSmplCode(model.getSmplCode());
			fileContentSmpl.setSmplName(model.getSmplName());
			fileContentSmpl.setSmplProvUnitName(model.getSmplProvUnitName());
			fileContentSmpl.setSmplType(model.getSmplType());
			fileContentSmpl.setSponsorName(model.getSponsorName());
			fileContentSmpl.setValidDate(model.getValidDate());
			fileContentSmpl.setStorageCondition(model.getStorageCondition());
			fileContentSmpl.setReserveBalance(model.getReserveBalance());
			fileContentSmpl.setGross(model.getGross());
			fileContentSmpl.setGrossUnit(model.getGrossUnit());
			fileContentSmpl.setGrossBalance(model.getGrossBalance());
			
			
			tblFileRecordSmplReserveService.save(fileContentSmpl);
			
			Map<String, Object> map0 = new HashMap<String, Object>();
			map0.put("archiveCode", fileContentSmpl.getTblFileIndex().getArchiveCode());
			map0.put("fileRecordId", fileContentSmpl.getFileRecordId());
			
			map0.put("batchCode",fileContentSmpl.getBatchCode());
			map0.put("container",fileContentSmpl.getContainer());
			map0.put("fileDate",DateUtil.dateToString(fileContentSmpl.getFileDate(),"yyyy-MM-dd"));
			
			map0.put("fileOperator",fileContentSmpl.getFileOperator());
			map0.put("keepDate",DateUtil.dateToString(fileContentSmpl.getKeepDate(),"yyyy-MM-dd"));
			if(fileContentSmpl.getDestoryDate()!=null)
			{
				map0.put("destoryDate", DateUtil.dateToString(fileContentSmpl.getDestoryDate(),"yyyy-MM-dd"));
			}
			if(fileContentSmpl.getSmplDestoryDate()!=null)
			{
				map0.put("smplDestoryDate", DateUtil.dateToString(fileContentSmpl.getSmplDestoryDate(),"yyyy-MM-dd"));
			}
			map0.put("keyWord",fileContentSmpl.getKeyWord());
			map0.put("remark",fileContentSmpl.getRemark());
			map0.put("reportUnitName",fileContentSmpl.getReportUnitName());
			map0.put("reserveDate",DateUtil.dateToString(fileContentSmpl.getReserveDate(),"yyyy-MM-dd"));
			map0.put("reserveNum",fileContentSmpl.getReserveNum());
			map0.put("reserveMan", fileContentSmpl.getReserveMan());
			map0.put("reserveNumUnit",fileContentSmpl.getReserveNumUnit());
			map0.put("reserveRecDate",DateUtil.dateToString(fileContentSmpl.getReserveRecDate(),"yyyy-MM-dd"));
			map0.put("reserveRecMan",fileContentSmpl.getReserveRecMan());
			map0.put("smplCode",fileContentSmpl.getSmplCode());
			map0.put("smplName",fileContentSmpl.getSmplName());
			map0.put("smplProvUnitName",fileContentSmpl.getSmplProvUnitName());
			map0.put("smplType",fileContentSmpl.getSmplType());
			map0.put("sponsorName",fileContentSmpl.getSponsorName());
			map0.put("validDate", DateUtil.dateToString(fileContentSmpl.getValidDate(),"yyyy-MM-dd"));
			
			map0.put("storageCondition", fileContentSmpl.getStorageCondition());
			map0.put("reserveBalance", fileContentSmpl.getReserveBalance());
			map0.put("gross", fileContentSmpl.getGross());
			map0.put("grossUnit", fileContentSmpl.getGrossUnit());
			map0.put("grossBalance", fileContentSmpl.getGrossBalance());
			
			map0.put("fileRecordSn", fileContentSmpl.getFileRecordSn());
			map0.put("fileOperator", fileContentSmpl.getFileOperator());
			
			map0.put("remark", fileContentSmpl.getRemark());
			
			map0.put("archiveTypeCode", fileContentSmpl.getTblFileIndex().getArchiveTypeCode());
			
			DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(fileContentSmpl.getTblFileIndex().getArchiveTypeCode());
			if(dictArchiveType!=null)
			{
				map0.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
			}
			
			map0.put("archiveTitle", fileContentSmpl.getTblFileIndex().getArchiveTitle());
			map0.put("storePosition", fileContentSmpl.getTblFileIndex().getStorePosition());
			map0.put("validationFlag", fileContentSmpl.getTblFileIndex().getValidationFlag());//0：否；1：验证数据
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
			
			
			TblFileRecordSmplReserve fileContentSmpl = new TblFileRecordSmplReserve();
			fileContentSmpl.setTblFileIndex(fileIndex);
			fileContentSmpl.setFileRecordId(tblFileRecordSmplReserveService.getKey("TblFileRecordSmplReserve"));
			
			fileContentSmpl.setBatchCode(model.getBatchCode());
			fileContentSmpl.setContainer(model.getContainer());
			fileContentSmpl.setFileDate(model.getFileDate());
			fileContentSmpl.setFileOperator(model.getFileOperator());
			
			Integer maxSn = tblFileRecordSmplReserveService.getMaxSnByArchiveCode(model.getTblFileIndex().getArchiveCode());
			fileContentSmpl.setFileRecordSn(maxSn+1);//追加是现在最大的+1

			fileContentSmpl.setKeepDate(model.getKeepDate());
			fileContentSmpl.setKeyWord(model.getKeyWord());
			fileContentSmpl.setOperateTime(operateTime);
			fileContentSmpl.setOperator(getCurrentUser().getRealName());
			fileContentSmpl.setRemark(model.getRemark());
			fileContentSmpl.setReportUnitName(model.getReportUnitName());
			fileContentSmpl.setReserveDate(model.getReserveDate());
			fileContentSmpl.setReserveMan(model.getReserveMan());
			fileContentSmpl.setReserveNum(model.getReserveNum());
			fileContentSmpl.setReserveNumUnit(model.getReserveNumUnit());
			fileContentSmpl.setReserveRecDate(model.getReserveRecDate());
			fileContentSmpl.setReserveRecMan(model.getReserveRecMan());
			fileContentSmpl.setSmplCode(model.getSmplCode());
			fileContentSmpl.setSmplName(model.getSmplName());
			fileContentSmpl.setSmplProvUnitName(model.getSmplProvUnitName());
			fileContentSmpl.setSmplType(model.getSmplType());
			fileContentSmpl.setSponsorName(model.getSponsorName());
			fileContentSmpl.setValidDate(model.getValidDate());
			fileContentSmpl.setStorageCondition(model.getStorageCondition());
			fileContentSmpl.setReserveBalance(model.getReserveBalance());
			fileContentSmpl.setGross(model.getGross());
			fileContentSmpl.setGrossUnit(model.getGrossUnit());
			fileContentSmpl.setGrossBalance(model.getGrossBalance());
			
			tblFileRecordSmplReserveService.save(fileContentSmpl);
			
			Map<String, Object> map0 = new HashMap<String, Object>();
			map0.put("archiveCode", fileContentSmpl.getTblFileIndex().getArchiveCode());
			map0.put("fileRecordId", fileContentSmpl.getFileRecordId());
			
			map0.put("batchCode",fileContentSmpl.getBatchCode());
			map0.put("container",fileContentSmpl.getContainer());
			map0.put("fileDate",DateUtil.dateToString(fileContentSmpl.getFileDate(),"yyyy-MM-dd"));
			
			map0.put("fileOperator",fileContentSmpl.getFileOperator());
			map0.put("keepDate",DateUtil.dateToString(fileContentSmpl.getKeepDate(),"yyyy-MM-dd"));
			if(fileContentSmpl.getDestoryDate()!=null)
			{
				map0.put("destoryDate", DateUtil.dateToString(fileContentSmpl.getDestoryDate(),"yyyy-MM-dd"));
			}
			if(fileContentSmpl.getSmplDestoryDate()!=null)
			{
				map0.put("smplDestoryDate", DateUtil.dateToString(fileContentSmpl.getSmplDestoryDate(),"yyyy-MM-dd"));
			}
			map0.put("keyWord",fileContentSmpl.getKeyWord());
			map0.put("remark",fileContentSmpl.getRemark());
			map0.put("reportUnitName",fileContentSmpl.getReportUnitName());
			map0.put("reserveDate",DateUtil.dateToString(fileContentSmpl.getReserveDate(),"yyyy-MM-dd"));
			map0.put("reserveNum",fileContentSmpl.getReserveNum());
			map0.put("reserveMan", fileContentSmpl.getReserveMan());
			map0.put("reserveNumUnit",fileContentSmpl.getReserveNumUnit());
			map0.put("reserveRecDate",DateUtil.dateToString(fileContentSmpl.getReserveRecDate(),"yyyy-MM-dd"));
			map0.put("reserveRecMan",fileContentSmpl.getReserveRecMan());
			map0.put("smplCode",fileContentSmpl.getSmplCode());
			map0.put("smplName",fileContentSmpl.getSmplName());
			map0.put("smplProvUnitName",fileContentSmpl.getSmplProvUnitName());
			map0.put("smplType",fileContentSmpl.getSmplType());
			map0.put("sponsorName",fileContentSmpl.getSponsorName());
			map0.put("validDate", DateUtil.dateToString(fileContentSmpl.getValidDate(),"yyyy-MM-dd"));
			
			map0.put("storageCondition", fileContentSmpl.getStorageCondition());
			
			map0.put("fileRecordSn", fileContentSmpl.getFileRecordSn());
			map0.put("fileOperator", fileContentSmpl.getFileOperator());
			
			map0.put("remark", fileContentSmpl.getRemark());
			
			map0.put("archiveTypeCode", fileContentSmpl.getTblFileIndex().getArchiveTypeCode());
			
			DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(fileContentSmpl.getTblFileIndex().getArchiveTypeCode());
			if(dictArchiveType!=null)
			{
				map0.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
			}
			
			map0.put("archiveTitle", fileContentSmpl.getTblFileIndex().getArchiveTitle());
			map0.put("storePosition", fileContentSmpl.getTblFileIndex().getStorePosition());
			map0.put("reserveBalance", fileContentSmpl.getReserveBalance());
			map0.put("gross", fileContentSmpl.getGross());
			map0.put("grossUnit", fileContentSmpl.getGrossUnit());
			map0.put("grossBalance", fileContentSmpl.getGrossBalance());
			
			map0.put("validationFlag", fileContentSmpl.getTblFileIndex().getValidationFlag());//0：否；1：验证数据
			map.put("record", map0);
			
			map.put("success", true);
		//}else {
		//	map.put("success", false);
		//	map.put("msg", "档案编号已经存在");
			
		//}`
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public void getReserveUnitList()
	{
		List<String> list = tblTestItemService.getReserveUnitList();
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		for (String str:list) {
			if(str!=null&&!"".equals(str))
			{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("unit", str);
			
				mapList.add(map);
			}
		}
		
		writeJson(JsonPluginsUtil.beanListToJson(mapList));
		
	}
	/*public void getGrossUnitList(){
		 SELECT distinct [GrossUnit]  FROM [TIMSDB].[dbo].[TBSMPLRESERVE]
		                                                   where GrossUnit is not null
	}*/

	public void getSmplReserveByCode()
	{
		//List<TblTestItem> tblTestItems = tblTestItemService.getConfirmedByTiNo(model.getSmplCode());
		List<Map<String, Object>> mapList = tblFileRecordSmplReserveService.getSmplListByCode(model.getSmplCode());
		Map<String, Object> map = new HashMap<String, Object>();
		//厂商即是报告出具单位
		//tiType,batchCode,smplName,contractCode,sponsorName,validDate,smplCode,reserveNum,reserveUnit,reportUnitName,
		//smplProvUnitName,reserveBalance,gross,grossUnit,grossBalance,storageCondition,reserveMan,reserveDate
		
		if(mapList!=null&&mapList.size()==1)
		{
			map.put("success", true);
			map.put("more", false);
			Map<String, Object> oneSmplR = mapList.get(0);
			if(oneSmplR.get("validDate")!=null&&!"".equals(oneSmplR.get("validDate")))
			{
				oneSmplR.put("validDate", DateUtil.dateToString((Date)oneSmplR.get("validDate"),"yyyy-MM-dd"));
			}
			if(oneSmplR.get("reserveDate")!=null&&!"".equals(oneSmplR.get("reserveDate")))
			{
				oneSmplR.put("reserveDate", DateUtil.dateToString((Date)oneSmplR.get("reserveDate"),"yyyy-MM-dd"));
			}
			map.put("record", mapList.get(0));
			
		}else if(mapList!=null&&mapList.size()>1){
			//多与一条的则显示出来多条
			map.put("success", true);
			map.put("more", true);
			
			for(Map<String, Object> oneSmplR:mapList)
			{
				if(oneSmplR.get("validDate")!=null&&!"".equals(oneSmplR.get("validDate")))
				{
					oneSmplR.put("validDate", DateUtil.dateToString((Date)oneSmplR.get("validDate"),"yyyy-MM-dd"));
				}
				if(oneSmplR.get("reserveDate")!=null&&!"".equals(oneSmplR.get("reserveDate")))
				{
					oneSmplR.put("reserveDate", DateUtil.dateToString((Date)oneSmplR.get("reserveDate"),"yyyy-MM-dd"));
				}
			}
			
			map.put("smplList", mapList);
			
		}else{
			map.put("success", false);
		}
		
		writeJson(JsonPluginsUtil.beanToJson(map));
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
		
		TblFileRecordSmplReserve fileContentSmpl = tblFileRecordSmplReserveService.getById(model.getFileRecordId());
		
		if((fileContentSmpl.getBatchCode()==null&&model.getBatchCode()!=null)||
				fileContentSmpl.getBatchCode()!=null&&!fileContentSmpl.getBatchCode().equals(model.getBatchCode()))
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","batchCode",fileContentSmpl.getFileRecordSn()
					,String.valueOf(model.getBatchCode()),model.getFileRecordId(),String.valueOf(fileContentSmpl.getBatchCode())
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setBatchCode(model.getBatchCode());
		
		if((fileContentSmpl.getContainer()==null&&model.getContainer()!=null)||
				fileContentSmpl.getContainer()!=null&&!fileContentSmpl.getContainer().equals(model.getContainer()))
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","container",fileContentSmpl.getFileRecordSn()
					,String.valueOf(model.getContainer()),model.getFileRecordId(),String.valueOf(fileContentSmpl.getContainer())
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setContainer(model.getContainer());
		
		if((fileContentSmpl.getFileDate()==null&&model.getFileDate()!=null)||
				fileContentSmpl.getFileDate()!=null&&fileContentSmpl.getFileDate().compareTo(model.getFileDate())!=0)
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","fileDate",fileContentSmpl.getFileRecordSn()
					,DateUtil.dateToString(model.getFileDate(),"yyyy-MM-dd"),model.getFileRecordId(),String.valueOf(fileContentSmpl.getFileDate())
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setFileDate(model.getFileDate());
		if((fileContentSmpl.getFileOperator()==null&&model.getFileOperator()!=null)||
				fileContentSmpl.getFileOperator()!=null&&!fileContentSmpl.getFileOperator().equals(model.getFileOperator()))
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","fileOperator",fileContentSmpl.getFileRecordSn()
					,String.valueOf(model.getFileOperator()),model.getFileRecordId(),String.valueOf(fileContentSmpl.getFileOperator())
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setFileOperator(model.getFileOperator());
		if((fileContentSmpl.getKeepDate()==null&&model.getKeepDate()!=null)||
				fileContentSmpl.getKeepDate()!=null&&fileContentSmpl.getKeepDate().compareTo(model.getKeepDate())!=0)
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","keepDate",fileContentSmpl.getFileRecordSn()
					,String.valueOf(model.getKeepDate()),model.getFileRecordId(),String.valueOf(fileContentSmpl.getKeepDate())
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setKeepDate(model.getKeepDate());
		if((fileContentSmpl.getKeyWord()==null&&model.getKeyWord()!=null)||
				fileContentSmpl.getKeyWord()!=null&&!fileContentSmpl.getKeyWord().equals(model.getKeyWord()))
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","keyWord",fileContentSmpl.getFileRecordSn()
					,String.valueOf(model.getKeyWord()),model.getFileRecordId(),String.valueOf(fileContentSmpl.getKeyWord())
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setKeyWord(model.getKeyWord());
		fileContentSmpl.setOperateTime(operateTime);
		fileContentSmpl.setOperator(getCurrentUser().getRealName());
		if((fileContentSmpl.getRemark()==null&&model.getRemark()!=null)||
				fileContentSmpl.getRemark()!=null&&!fileContentSmpl.getRemark().equals(model.getRemark()))
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","remark",fileContentSmpl.getFileRecordSn()
					,String.valueOf(model.getRemark()),model.getFileRecordId(),String.valueOf(fileContentSmpl.getRemark())
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setRemark(model.getRemark());
		if((fileContentSmpl.getReportUnitName()==null&&model.getReportUnitName()!=null)||
				fileContentSmpl.getReportUnitName()!=null&&!fileContentSmpl.getReportUnitName().equals(model.getReportUnitName()))
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","reportUnitName",fileContentSmpl.getFileRecordSn()
					,String.valueOf(model.getReportUnitName()),model.getFileRecordId(),String.valueOf(fileContentSmpl.getReportUnitName())
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setReportUnitName(model.getReportUnitName());
		if((fileContentSmpl.getReserveDate()==null&&model.getReserveDate()!=null)||
				fileContentSmpl.getReserveDate()!=null&&fileContentSmpl.getReserveDate().compareTo(model.getReserveDate())!=0)
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","reserveDate",fileContentSmpl.getFileRecordSn()
					,DateUtil.dateToString(model.getReserveDate(),"yyyy-MM-dd"),model.getFileRecordId(),String.valueOf(fileContentSmpl.getReserveDate())
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setReserveDate(model.getReserveDate());
		if((fileContentSmpl.getReserveMan()==null&&model.getReserveMan()!=null)||
				fileContentSmpl.getReserveMan()!=null&&!fileContentSmpl.getReserveMan().equals(model.getReserveMan()))
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","reserveMan",fileContentSmpl.getFileRecordSn()
					,String.valueOf(model.getReserveMan()),model.getFileRecordId(),String.valueOf(fileContentSmpl.getReserveMan())
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setReserveMan(model.getReserveMan());
		if((fileContentSmpl.getReserveNum()==null&&model.getReserveNum()!=null)||
				fileContentSmpl.getReserveNum()!=null&&!fileContentSmpl.getReserveNum().equals(model.getReserveNum()))
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","reserveNum",fileContentSmpl.getFileRecordSn()
					,String.valueOf(model.getReserveNum()),model.getFileRecordId(),String.valueOf(fileContentSmpl.getReserveNum())
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setReserveNum(model.getReserveNum());
		if((fileContentSmpl.getReserveRecDate()==null&&model.getReserveRecDate()!=null)||
				fileContentSmpl.getReserveRecDate()!=null&&fileContentSmpl.getReserveRecDate().compareTo(model.getReserveRecDate())!=0)
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","reserveRecDate",fileContentSmpl.getFileRecordSn()
					,DateUtil.dateToString(model.getReserveRecDate(),"yyyy-MM-dd"),model.getFileRecordId(),String.valueOf(fileContentSmpl.getReserveRecDate())
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setReserveRecDate(model.getReserveRecDate());
		if((fileContentSmpl.getReserveRecMan()==null&&model.getReserveRecMan()!=null)||
				fileContentSmpl.getReserveRecMan()!=null&&!fileContentSmpl.getReserveRecMan().equals(model.getReserveRecMan()))
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","reserveRecMan",fileContentSmpl.getFileRecordSn()
					,String.valueOf(model.getReserveRecMan()),model.getFileRecordId(),String.valueOf(fileContentSmpl.getReserveRecMan())
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setReserveRecMan(model.getReserveRecMan());
		
		if((fileContentSmpl.getReserveBalance()==null&&model.getReserveBalance()!=null)||
				fileContentSmpl.getReserveBalance()!=null&&!fileContentSmpl.getReserveBalance().equals(model.getReserveBalance()))
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","reserveBalance",fileContentSmpl.getFileRecordSn()
					,model.getReserveBalance(),model.getFileRecordId(),fileContentSmpl.getReserveBalance()
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setReserveBalance(model.getReserveBalance());
		if((fileContentSmpl.getGross()==null&&model.getGross()!=null)||
				fileContentSmpl.getGross()!=null&&!fileContentSmpl.getGross().equals(model.getGross()))
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","gross",fileContentSmpl.getFileRecordSn()
					,model.getGross(),model.getFileRecordId(),fileContentSmpl.getGross()
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setGross(model.getGross());
		if((fileContentSmpl.getGrossUnit()==null&&model.getGrossUnit()!=null)||
				fileContentSmpl.getGrossUnit()!=null&&!fileContentSmpl.getGrossUnit().equals(model.getGrossUnit()))
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","grossUnit",fileContentSmpl.getFileRecordSn()
					,model.getGrossUnit(),model.getFileRecordId(),fileContentSmpl.getGrossUnit()
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setGrossUnit(model.getGrossUnit());
		if((fileContentSmpl.getGrossBalance()==null&&model.getGrossBalance()!=null)||
				fileContentSmpl.getGrossBalance()!=null&&!fileContentSmpl.getGrossBalance().equals(model.getGrossBalance()))
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","grossBalance",fileContentSmpl.getFileRecordSn()
					,model.getGrossBalance(),model.getFileRecordId(),fileContentSmpl.getGrossBalance()
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setGrossBalance(model.getGrossBalance());
		
		if((fileContentSmpl.getSmplCode()==null&&model.getSmplCode()!=null)||
				fileContentSmpl.getSmplCode()!=null&&!fileContentSmpl.getSmplCode().equals(model.getSmplCode()))
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","smplCode",fileContentSmpl.getFileRecordSn()
					,String.valueOf(model.getSmplCode()),model.getFileRecordId(),String.valueOf(fileContentSmpl.getSmplCode())
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setSmplCode(model.getSmplCode());
		if((fileContentSmpl.getSmplName()==null&&model.getSmplName()!=null)||
				fileContentSmpl.getSmplName()!=null&&!fileContentSmpl.getSmplName().equals(model.getSmplName()))
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","smplName",fileContentSmpl.getFileRecordSn()
					,String.valueOf(model.getSmplName()),model.getFileRecordId(),String.valueOf(fileContentSmpl.getSmplName())
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setSmplName(model.getSmplName());
		if((fileContentSmpl.getSmplProvUnitName()==null&&model.getSmplProvUnitName()!=null)||
				fileContentSmpl.getSmplProvUnitName()!=null&&!fileContentSmpl.getSmplProvUnitName().equals(model.getSmplProvUnitName()))
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","smplProvUnitName",fileContentSmpl.getFileRecordSn()
					,String.valueOf(model.getSmplProvUnitName()),model.getFileRecordId(),String.valueOf(fileContentSmpl.getSmplProvUnitName())
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setSmplProvUnitName(model.getSmplProvUnitName());
		if((fileContentSmpl.getSmplType()==null&&model.getSmplType()!=null)||
				fileContentSmpl.getSmplType()!=null&&!fileContentSmpl.getSmplType().equals(model.getSmplType()))
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","smplType",fileContentSmpl.getFileRecordSn()
					,String.valueOf(model.getSmplType()),model.getFileRecordId(),String.valueOf(fileContentSmpl.getSmplType())
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setSmplType(model.getSmplType());
		if((fileContentSmpl.getSponsorName()==null&&model.getSponsorName()!=null)||
				fileContentSmpl.getSponsorName()!=null&&!fileContentSmpl.getSponsorName().equals(model.getSponsorName()))
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","sponsorName",fileContentSmpl.getFileRecordSn()
					,String.valueOf(model.getSponsorName()),model.getFileRecordId(),String.valueOf(fileContentSmpl.getSponsorName())
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setSponsorName(model.getSponsorName());
		
		if((fileContentSmpl.getValidDate()==null&&model.getValidDate()!=null)||
				fileContentSmpl.getValidDate()!=null&&fileContentSmpl.getValidDate().compareTo(model.getValidDate())!=0)
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","validDate",fileContentSmpl.getFileRecordSn()
					,DateUtil.dateToString(model.getValidDate(),"yyyy-MM-dd"),model.getFileRecordId(),String.valueOf(fileContentSmpl.getValidDate())
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setValidDate(model.getValidDate());
		if((fileContentSmpl.getStorageCondition()==null&&model.getStorageCondition()!=null)||
				fileContentSmpl.getStorageCondition()!=null&&!fileContentSmpl.getStorageCondition().equals(model.getStorageCondition()))
		{
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileContentSmpl.getTblFileIndex().getArchiveTitle()
					,"TblFileRecordSmplReserve","storageCondition",fileContentSmpl.getFileRecordSn()
					,model.getStorageCondition(),model.getFileRecordId(),fileContentSmpl.getStorageCondition()
					,operateRsn,operateTime,1);
		}
		fileContentSmpl.setStorageCondition(model.getStorageCondition());
		
		tblFileRecordSmplReserveService.update(fileContentSmpl);
		
		//更新的时候档案编号不可以更新
		TblFileIndex fileIndex = tblFileIndexService.getById(model.getTblFileIndex().getArchiveCode());
		if(!fileIndex.getArchiveTypeCode().equals(model.getTblFileIndex().getArchiveTypeCode())){
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileIndex.getArchiveTitle(),
					"TblFileIndex","archiveTypeCode",fileContentSmpl.getFileRecordSn()
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
					"TblFileIndex","storePosition",fileContentSmpl.getFileRecordSn()
					,model.getTblFileIndex().getStorePosition(),model.getFileRecordId(),fileIndex.getStorePosition(),
					operateRsn,operateTime,1);
		}
		fileIndex.setStorePosition(model.getTblFileIndex().getStorePosition());
		if((fileIndex.getValidationFlag()==null&&model.getTblFileIndex().getValidationFlag()!=null)
			||(	fileIndex.getValidationFlag()!=null&&!fileIndex.getValidationFlag().equals(validationFlag))){
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileIndex.getArchiveTitle(),
					"TblFileIndex","validationFlag",fileContentSmpl.getFileRecordSn()
					,String.valueOf(validationFlag),model.getFileRecordId(),String.valueOf(fileIndex.getValidationFlag()),
					operateRsn,operateTime,1);
		}
		fileIndex.setValidationFlag(validationFlag);
		if((fileIndex.getArchiveTitle()==null&&model.getTblFileIndex().getArchiveTitle()!=null)
			||(fileIndex.getArchiveTitle()!=null&&!fileIndex.getArchiveTitle().equals(model.getTblFileIndex().getArchiveTitle()))){
			writeTblLog(model.getTblFileIndex().getArchiveCode(),fileIndex.getArchiveTitle(),
					"TblFileIndex","archiveTitle",fileContentSmpl.getFileRecordSn()
					,model.getTblFileIndex().getArchiveTitle(),model.getFileRecordId(),fileIndex.getArchiveTitle(),
					operateRsn,operateTime,1);
		}
		fileIndex.setArchiveTitle(model.getTblFileIndex().getArchiveTitle());
		
		tblFileIndexService.update(fileIndex);
		
		writeES("更新供试品留样档案", 927, "TblFileRecordSmplReserve", model.getFileRecordId());
		
		Map<String, Object> map0 = new HashMap<String, Object>();
		map0.put("archiveCode", fileContentSmpl.getTblFileIndex().getArchiveCode());
		map0.put("fileRecordId", fileContentSmpl.getFileRecordId());
		
		map0.put("batchCode",fileContentSmpl.getBatchCode());
		map0.put("container",fileContentSmpl.getContainer());
		map0.put("fileDate",DateUtil.dateToString(fileContentSmpl.getFileDate(),"yyyy-MM-dd"));
		
		map0.put("fileOperator",fileContentSmpl.getFileOperator());
		map0.put("keepDate",DateUtil.dateToString(fileContentSmpl.getKeepDate(),"yyyy-MM-dd"));
		if(fileContentSmpl.getDestoryDate()!=null)
		{
			map0.put("destoryDate", DateUtil.dateToString(fileContentSmpl.getDestoryDate(),"yyyy-MM-dd"));
		}
		if(fileContentSmpl.getSmplDestoryDate()!=null)
		{
			map0.put("smplDestoryDate", DateUtil.dateToString(fileContentSmpl.getSmplDestoryDate(),"yyyy-MM-dd"));
		}
		map0.put("keyWord",fileContentSmpl.getKeyWord());
		map0.put("remark",fileContentSmpl.getRemark());
		map0.put("reportUnitName",fileContentSmpl.getReportUnitName());
		map0.put("reserveDate",DateUtil.dateToString(fileContentSmpl.getReserveDate(),"yyyy-MM-dd"));
		map0.put("reserveNum",fileContentSmpl.getReserveNum());
		map0.put("reserveMan", fileContentSmpl.getReserveMan());
		map0.put("reserveNumUnit",fileContentSmpl.getReserveNumUnit());
		map0.put("reserveRecDate",DateUtil.dateToString(fileContentSmpl.getReserveRecDate(),"yyyy-MM-dd"));
		map0.put("reserveRecMan",fileContentSmpl.getReserveRecMan());
		map0.put("smplCode",fileContentSmpl.getSmplCode());
		map0.put("smplName",fileContentSmpl.getSmplName());
		map0.put("smplProvUnitName",fileContentSmpl.getSmplProvUnitName());
		map0.put("smplType",fileContentSmpl.getSmplType());
		map0.put("sponsorName",fileContentSmpl.getSponsorName());
		map0.put("validDate", DateUtil.dateToString(fileContentSmpl.getValidDate(),"yyyy-MM-dd"));
		
		map0.put("storageCondition", fileContentSmpl.getStorageCondition());
		
		map0.put("fileRecordSn", fileContentSmpl.getFileRecordSn());
		map0.put("fileOperator", fileContentSmpl.getFileOperator());
		
		map0.put("remark", fileContentSmpl.getRemark());
		
		map0.put("archiveTypeCode", fileContentSmpl.getTblFileIndex().getArchiveTypeCode());
		
		DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(fileContentSmpl.getTblFileIndex().getArchiveTypeCode());
		if(dictArchiveType!=null)
		{
			map0.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
		}
		
		map0.put("archiveTitle", fileContentSmpl.getTblFileIndex().getArchiveTitle());
		map0.put("storePosition", fileContentSmpl.getTblFileIndex().getStorePosition());
		map0.put("validationFlag", fileContentSmpl.getTblFileIndex().getValidationFlag());//0：否；1：验证数据
		map.put("record", map0);
		
		map.put("success", true);

		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void delete()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		writeES("删除供试品留样档案", 928, "TblFileRecordSmplReserve", model.getFileRecordId());
		TblFileRecordSmplReserve record = tblFileRecordSmplReserveService.getById(model.getFileRecordId());
		record.setDelFlag(1);
		Date operateTime = new Date();
		record.setDelTime(operateTime);
		
		tblFileRecordSmplReserveService.update(record);
		
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
		TblFileRecordSmplReserve record0 = tblFileRecordSmplReserveService.getById(model.getFileRecordId());
		Date operateTime = new Date();
		if(destroyType!=null&&destroyType==2){
			record0.setSmplDestoryDate(model.getDestoryDate());
			String esLinkId = writeES("销毁供试品的留样", 935, "TblFileRecordSmplReserve", record0.getFileRecordId());
			record0.setSmplDestoryRegSign(esLinkId);
			
			tblFileRecordSmplReserveService.update(record0);
			writeTblLog(record0.getTblFileIndex().getArchiveCode(),record0.getTblFileIndex().getArchiveTitle(),
					"TblFileRecordSmplReserve","smplDestoryDate",record0.getFileRecordSn()
					,DateUtil.dateToString(record0.getSmplDestoryDate(),"yyyy-MM-dd"),record0.getFileRecordId(),"",
					operateRsn,operateTime,6);//1：修改；2：删除；3：销毁；4：SOP作废；5：合同终止 6销毁供试品留样 7销毁标本
			
			map.put("success", true);
		}else{
			List<TblFileRecordSmplReserve> recordList = tblFileRecordSmplReserveService.getByArchiveCode(record0.getTblFileIndex().getArchiveCode());
			for(TblFileRecordSmplReserve record:recordList)
			{
				record.setDestoryDate(model.getDestoryDate());
				String esLinkId = writeES("销毁供试品留样档案", 929, "TblFileRecordSmplReserve", record.getFileRecordId());
				record.setDestoryRegSign(esLinkId);
				
				tblFileRecordSmplReserveService.update(record);
				
				writeTblLog(record.getTblFileIndex().getArchiveCode(),record.getTblFileIndex().getArchiveTitle(),
						"TblFileRecordSmplReserve","destoryDate",record.getFileRecordSn()
						,DateUtil.dateToString(record.getDestoryDate(),"yyyy-MM-dd"),record.getFileRecordId(),"",
						operateRsn,operateTime,3);//1：修改；2：删除；3：销毁；4：SOP作废；5：合同终止
			}
			map.put("success", true);
		}

		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void loadList()
	{
		putSearchConIntoSession(searchString, 8);
		
		//Integer isNowValid,Integer isInvalid,Date changeEndDate,Integer yearNum,
		Map<String, Object> resultMap = tblFileRecordSmplReserveService.getByCondition(isSmplKeepEndDate,smplKeepEndDate,isDestroySmpl,model.getSmplType(),fileStartDate,fileEndDate,keepEndDate,isDestory,isValid,searchString,page,rows);
		List<Map<String, Object>> fileStudys = (List<Map<String, Object>>)resultMap.get("rows");
		 
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		for(Map<String, Object> fileStudy:fileStudys)
		{
		/*	Map<String, Object> map = new HashMap<String, Object>();
			map.put("archiveCode", fileStudy.getTblFileIndex().getArchiveCode());
			map.put("fileRecordId", fileStudy.getFileRecordId());
			
			map.put("batchCode",fileStudy.getBatchCode());
			map.put("container",fileStudy.getContainer());
			map.put("fileDate",DateUtil.dateToString(fileStudy.getFileDate(),"yyyy-MM-dd"));
			
			map.put("fileOperator",fileStudy.getFileOperator());
			map.put("keepDate",DateUtil.dateToString(fileStudy.getKeepDate(),"yyyy-MM-dd"));
			if(fileStudy.getDestoryDate()!=null)
			{
				map.put("destoryDate", DateUtil.dateToString(fileStudy.getDestoryDate(),"yyyy-MM-dd"));
			}
			if(fileStudy.getSmplDestoryDate()!=null)
			{
				map.put("smplDestoryDate", DateUtil.dateToString(fileStudy.getSmplDestoryDate(),"yyyy-MM-dd"));
			}
			map.put("keyWord",fileStudy.getKeyWord());
			map.put("remark",fileStudy.getRemark());
			map.put("reportUnitName",fileStudy.getReportUnitName());
			map.put("reserveDate",DateUtil.dateToString(fileStudy.getReserveDate(),"yyyy-MM-dd"));
			map.put("reserveNum",fileStudy.getReserveNum());
			map.put("reserveMan", fileStudy.getReserveMan());
			map.put("reserveNumUnit",fileStudy.getReserveNumUnit());
			map.put("reserveBalance",fileStudy.getReserveBalance());
			map.put("gross",fileStudy.getGross());
			map.put("grossUnit",fileStudy.getGrossUnit());
			map.put("grossBalance",fileStudy.getGrossBalance());
			
			map.put("reserveRecDate",DateUtil.dateToString(fileStudy.getReserveRecDate(),"yyyy-MM-dd"));
			map.put("reserveRecMan",fileStudy.getReserveRecMan());
			map.put("smplCode",fileStudy.getSmplCode());
			map.put("smplName",fileStudy.getSmplName());
			map.put("smplProvUnitName",fileStudy.getSmplProvUnitName());
			map.put("smplType",fileStudy.getSmplType());
			map.put("sponsorName",fileStudy.getSponsorName());
			map.put("validDate", DateUtil.dateToString(fileStudy.getValidDate(),"yyyy-MM-dd"));
			
			map.put("storageCondition", fileStudy.getStorageCondition());
			
			map.put("fileRecordSn", fileStudy.getFileRecordSn());
			map.put("fileOperator", fileStudy.getFileOperator());
			
			map.put("remark", fileStudy.getRemark());
			map.put("archiveTypeCode", fileStudy.getTblFileIndex().getArchiveTypeCode());
		 */
			//fileDate,reserveRecDate,validDate,reserveDate,smplDestoryDate,destoryDate,keepDate
			if(fileStudy.get("fileDate")!=null)
			{
				fileStudy.put("fileDate", DateUtil.dateToString((Date)fileStudy.get("fileDate"),"yyyy-MM-dd"));
			}
			if(fileStudy.get("reserveRecDate")!=null)
			{
				fileStudy.put("reserveRecDate", DateUtil.dateToString((Date)fileStudy.get("reserveRecDate"),"yyyy-MM-dd"));
			}
			if(fileStudy.get("validDate")!=null)
			{
				fileStudy.put("validDate", DateUtil.dateToString((Date)fileStudy.get("validDate"),"yyyy-MM-dd"));
			}
			if(fileStudy.get("reserveDate")!=null)
			{
				fileStudy.put("reserveDate", DateUtil.dateToString((Date)fileStudy.get("reserveDate"),"yyyy-MM-dd"));
			}
			if(fileStudy.get("smplDestoryDate")!=null)
			{
				fileStudy.put("smplDestoryDate", DateUtil.dateToString((Date)fileStudy.get("smplDestoryDate"),"yyyy-MM-dd"));
			}
			if(fileStudy.get("destoryDate")!=null)
			{
				fileStudy.put("destoryDate", DateUtil.dateToString((Date)fileStudy.get("destoryDate"),"yyyy-MM-dd"));
			}
			if(fileStudy.get("keepDate")!=null)
			{
				fileStudy.put("keepDate", DateUtil.dateToString((Date)fileStudy.get("keepDate"),"yyyy-MM-dd"));
			}
			
			String archiveTypeCode = (String)fileStudy.get("archiveTypeCode");
			
			DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(archiveTypeCode);
			if(dictArchiveType!=null)
			{
				fileStudy.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
			}
			
			/*map.put("archiveTitle", fileStudy.getTblFileIndex().getArchiveTitle());
			map.put("storePosition", fileStudy.getTblFileIndex().getStorePosition());
			map.put("validationFlag", fileStudy.getTblFileIndex().getValidationFlag());//0：否；1：验证数据
			*/
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
		tblLog.setArchiveTypeFlag(9);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
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

	public Integer getIsSmplKeepEndDate() {
		return isSmplKeepEndDate;
	}

	public void setIsSmplKeepEndDate(Integer isSmplKeepEndDate) {
		this.isSmplKeepEndDate = isSmplKeepEndDate;
	}

	public Date getSmplKeepEndDate() {
		return smplKeepEndDate;
	}

	public void setSmplKeepEndDate(Date smplKeepEndDate) {
		this.smplKeepEndDate = smplKeepEndDate;
	}

	public Integer getIsDestroySmpl() {
		return isDestroySmpl;
	}

	public void setIsDestroySmpl(Integer isDestroySmpl) {
		this.isDestroySmpl = isDestroySmpl;
	}
	
}
