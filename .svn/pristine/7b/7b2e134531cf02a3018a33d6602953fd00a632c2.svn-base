package com.lanen.view.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.DictDataTable;
import com.lanen.model.TblFileContentAdministration;
import com.lanen.model.TblFileIndex;
import com.lanen.model.TblFileRecord;
import com.lanen.model.TblLog;
import com.lanen.model.User;
import com.lanen.model.archive.DictAdministrationType;
import com.lanen.model.archive.DictArchiveType;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.service.archive.DictAdministrationTypeService;
import com.lanen.service.archive.DictArchiveTypeService;
import com.lanen.service.archive.DictDataTableService;
import com.lanen.service.archive.TblFileContentAdministrationService;
import com.lanen.service.archive.TblFileIndexService;
import com.lanen.service.archive.TblFileRecordService;
import com.lanen.service.archive.TblLog2Service;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class TblFileContentAdministrationAction extends BaseAction<TblFileContentAdministration> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3801603727519349150L;
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
	private TblFileContentAdministrationService tblFileContentAdministrationService;
	@Resource
	private DictAdministrationTypeService dictAdministrationTypeService;
	
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
	
	private Integer hasAll;
	
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
			fileIndex.setArchiveTypeFlag(7);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
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
			
			TblFileContentAdministration fileContentAdm = new TblFileContentAdministration();
			fileContentAdm.setArchiveCode(model.getArchiveCode());
			fileContentAdm.setFileRecordId(key);
			
			fileContentAdm.setDispatchDate(model.getDispatchDate());
			fileContentAdm.setDispatchUnit(model.getDispatchUnit());
			fileContentAdm.setDocCode(model.getDocCode());
			fileContentAdm.setDocName(model.getDocName());
			fileContentAdm.setDocTypeFlag(model.getDocTypeFlag());
			fileContentAdm.setDocTypeName(model.getDocTypeName());
			fileContentAdm.setReceiptDate(model.getReceiptDate());
			fileContentAdm.setReceiptMan(model.getReceiptMan());
			
			fileContentAdm.setTblFileRecord(record);
			
			tblFileContentAdministrationService.save(fileContentAdm);
			
			Map<String, Object> map0 = new HashMap<String, Object>();
			map0.put("archiveCode", fileContentAdm.getArchiveCode());
			map0.put("fileRecordId", fileContentAdm.getFileRecordId());
			map0.put("dispatchDate", DateUtil.dateToString(fileContentAdm.getDispatchDate(),"yyyy-MM-dd"));
			map0.put("dispatchUnit", fileContentAdm.getDispatchUnit());
			map0.put("docCode", fileContentAdm.getDocCode());
			map0.put("docName", fileContentAdm.getDocName());
			map0.put("docTypeFlag", fileContentAdm.getDocTypeFlag());
			map0.put("docTypeName", fileContentAdm.getDocTypeName());
			map0.put("receiptDate", DateUtil.dateToString(fileContentAdm.getReceiptDate(),"yyyy-MM-dd"));
			map0.put("receiptMan", fileContentAdm.getReceiptMan());
			
			map0.put("archiveTypeCode", fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
			
			DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
			if(dictArchiveType!=null)
			{
				map0.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
			}
			
			map0.put("archiveTitle", fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTitle());
			map0.put("storePosition", fileContentAdm.getTblFileRecord().getTblFileIndex().getStorePosition());
			map0.put("fileDate", DateUtil.dateToString(fileContentAdm.getTblFileRecord().getFileDate(),"yyyy-MM-dd"));
			map0.put("fileRecordSn", fileContentAdm.getTblFileRecord().getFileRecordSn());
			map0.put("archiveMaker", fileContentAdm.getTblFileRecord().getArchiveMaker());
			map0.put("archiveMedia", fileContentAdm.getTblFileRecord().getArchiveMedia());
			map0.put("fileOperator", fileContentAdm.getTblFileRecord().getFileOperator());
			map0.put("keepDate", DateUtil.dateToString(fileContentAdm.getTblFileRecord().getKeepDate(),"yyyy-MM-dd"));
			map0.put("remark", fileContentAdm.getTblFileRecord().getRemark());
			if(fileContentAdm.getTblFileRecord().getDestoryDate()!=null)
			{
				map0.put("destoryDate", DateUtil.dateToString(fileContentAdm.getTblFileRecord().getDestoryDate(),"yyyy-MM-dd"));
			}
			map0.put("validationFlag", fileContentAdm.getTblFileRecord().getTblFileIndex().getValidationFlag());//0：否；1：验证数据
			map0.put("archiveMediaFlag", fileContentAdm.getTblFileRecord().getArchiveMediaFlag());
			map0.put("archiveMedia", fileContentAdm.getTblFileRecord().getArchiveMedia());
			
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
			
			TblFileContentAdministration fileContentAdm = new TblFileContentAdministration();
			fileContentAdm.setArchiveCode(model.getArchiveCode());
			fileContentAdm.setFileRecordId(key);
			
			fileContentAdm.setDispatchDate(model.getDispatchDate());
			fileContentAdm.setDispatchUnit(model.getDispatchUnit());
			fileContentAdm.setDocCode(model.getDocCode());
			fileContentAdm.setDocName(model.getDocName());
			fileContentAdm.setDocTypeFlag(model.getDocTypeFlag());
			fileContentAdm.setDocTypeName(model.getDocTypeName());
			fileContentAdm.setReceiptDate(model.getReceiptDate());
			fileContentAdm.setReceiptMan(model.getReceiptMan());
			
			fileContentAdm.setTblFileRecord(record);
		
			tblFileContentAdministrationService.save(fileContentAdm);
			
			Map<String, Object> map0 = new HashMap<String, Object>();
			map0.put("archiveCode", fileContentAdm.getArchiveCode());
			map0.put("fileRecordId", fileContentAdm.getFileRecordId());
			
			map0.put("dispatchDate", DateUtil.dateToString(fileContentAdm.getDispatchDate(),"yyyy-MM-dd"));
			map0.put("dispatchUnit", fileContentAdm.getDispatchUnit());
			map0.put("docCode", fileContentAdm.getDocCode());
			map0.put("docName", fileContentAdm.getDocName());
			map0.put("docTypeFlag", fileContentAdm.getDocTypeFlag());
			map0.put("docTypeName", fileContentAdm.getDocTypeName());
			map0.put("receiptDate", DateUtil.dateToString(fileContentAdm.getReceiptDate(),"yyyy-MM-dd"));
			map0.put("receiptMan", fileContentAdm.getReceiptMan());
			
			map0.put("archiveTypeCode", fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
			
			DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
			if(dictArchiveType!=null)
			{
				map0.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
			}
			
			map0.put("archiveTitle", fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTitle());
			map0.put("storePosition", fileContentAdm.getTblFileRecord().getTblFileIndex().getStorePosition());
			map0.put("fileDate", DateUtil.dateToString(fileContentAdm.getTblFileRecord().getFileDate(),"yyyy-MM-dd"));
			map0.put("fileRecordSn", fileContentAdm.getTblFileRecord().getFileRecordSn());
			map0.put("archiveMaker", fileContentAdm.getTblFileRecord().getArchiveMaker());
			map0.put("archiveMedia", fileContentAdm.getTblFileRecord().getArchiveMedia());
			map0.put("fileOperator", fileContentAdm.getTblFileRecord().getFileOperator());
			map0.put("keepDate", DateUtil.dateToString(fileContentAdm.getTblFileRecord().getKeepDate(),"yyyy-MM-dd"));
			map0.put("remark", fileContentAdm.getTblFileRecord().getRemark());
			if(fileContentAdm.getTblFileRecord().getDestoryDate()!=null)
			{
				map0.put("destoryDate", DateUtil.dateToString(fileContentAdm.getTblFileRecord().getDestoryDate(),"yyyy-MM-dd"));
			}
			map0.put("validationFlag", fileContentAdm.getTblFileRecord().getTblFileIndex().getValidationFlag());//0：否；1：验证数据
			map0.put("archiveMediaFlag", fileContentAdm.getTblFileRecord().getArchiveMediaFlag());
			map0.put("archiveMedia", fileContentAdm.getTblFileRecord().getArchiveMedia());
			
			map.put("record", map0);
			
			map.put("success", true);
		//}else {
		//	map.put("success", false);
		//	map.put("msg", "档案编号已经存在");
			
		//}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	

	public void getUserList()
	{
		Map<String, String> realNames = userService.getAllRealName();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for (Entry<String, String> entry:realNames.entrySet()) {
			String id = entry.getKey();
			String realName = entry.getValue();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("name", realName);
			maps.add(map);
		}
		
		writeJson(JsonPluginsUtil.beanListToJson(maps));
	}
	
	public void getDocTypeList(){
		List<DictAdministrationType> list = dictAdministrationTypeService.getAll();
		if(hasAll!=null&&hasAll==1)
		{
			DictAdministrationType type = new DictAdministrationType();
			type.setDocTypeFlag("全部");
			type.setDocTypeName("全部");
			list.add(0,type);
		}
		writeJson(JsonPluginsUtil.beanListToJson(list));
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
		
		TblFileContentAdministration fileContentAdm = tblFileContentAdministrationService.getById(model.getFileRecordId());
		
		//map.put("dispatchDate", fileStudy.getDispatchDate());
		//map.put("dispatchUnit", fileStudy.getDispatchUnit());
		if((fileContentAdm.getDispatchDate()==null&&model.getDispatchDate()!=null)||
				fileContentAdm.getDispatchDate()!=null&&fileContentAdm.getDispatchDate().compareTo(model.getDispatchDate())!=0)
		{
			writeTblLog(model.getArchiveCode(),fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileContentAdministration","dispatchDate",fileContentAdm.getTblFileRecord().getFileRecordSn()
					,String.valueOf(DateUtil.dateToString(model.getDispatchDate(),"yyyy-MM-dd")),model.getFileRecordId(),String.valueOf(DateUtil.dateToString(fileContentAdm.getDispatchDate(),"yyyy-MM-dd"))
					,operateRsn,operateTime,1);
		}
		fileContentAdm.setDispatchDate(model.getDispatchDate());
		
		if((fileContentAdm.getDispatchUnit()==null&&model.getDispatchUnit()!=null)||
				fileContentAdm.getDispatchUnit()!=null&&!fileContentAdm.getDispatchUnit().equals(model.getDispatchUnit()))
		{
			writeTblLog(model.getArchiveCode(),fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileContentAdministration","dispatchUnit",fileContentAdm.getTblFileRecord().getFileRecordSn()
					,String.valueOf(model.getDispatchUnit()),model.getFileRecordId(),String.valueOf(fileContentAdm.getDispatchUnit())
					,operateRsn,operateTime,1);
		}
		fileContentAdm.setDispatchUnit(model.getDispatchUnit());
		
		//map.put("docCode", fileStudy.getDocCode());
		//map.put("docName", fileStudy.getDocName());
		if((fileContentAdm.getDocCode()==null&&model.getDocCode()!=null)||
				fileContentAdm.getDocCode()!=null&&!fileContentAdm.getDocCode().equals(model.getDocCode()))
		{
			writeTblLog(model.getArchiveCode(),fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileContentAdministration","docCode",fileContentAdm.getTblFileRecord().getFileRecordSn()
					,String.valueOf(model.getDocCode()),model.getFileRecordId(),String.valueOf(fileContentAdm.getDocCode())
					,operateRsn,operateTime,1);
		}
		fileContentAdm.setDocCode(model.getDocCode());
		if((fileContentAdm.getDocName()==null&&model.getDocName()!=null)||
				fileContentAdm.getDocName()!=null&&!fileContentAdm.getDocName().equals(model.getDocName()))
		{
			writeTblLog(model.getArchiveCode(),fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileContentAdministration","docName",fileContentAdm.getTblFileRecord().getFileRecordSn()
					,String.valueOf(model.getDocName()),model.getFileRecordId(),String.valueOf(fileContentAdm.getDocName())
					,operateRsn,operateTime,1);
		}
		fileContentAdm.setDocName(model.getDocName());
		//map.put("docTypeFlag", fileStudy.getDocTypeFlag());
		//map.put("docTypeName", fileStudy.getDocTypeName());
		if((fileContentAdm.getDocTypeFlag()==null&&model.getDocTypeFlag()!=null)||
				fileContentAdm.getDocTypeFlag()!=null&&!fileContentAdm.getDocTypeFlag().equals(model.getDocTypeFlag()))
		{
			writeTblLog(model.getArchiveCode(),fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileContentAdministration","docTypeFlag",fileContentAdm.getTblFileRecord().getFileRecordSn()
					,String.valueOf(model.getDocTypeFlag()),model.getFileRecordId(),String.valueOf(fileContentAdm.getDocTypeFlag())
					,operateRsn,operateTime,1);
		}
		fileContentAdm.setDocTypeFlag(model.getDocTypeFlag());
		if((fileContentAdm.getDocTypeName()==null&&model.getDocTypeName()!=null)||
				fileContentAdm.getDocTypeName()!=null&&!fileContentAdm.getDocTypeName().equals(model.getDocTypeName()))
		{
			writeTblLog(model.getArchiveCode(),fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileContentAdministration","docTypeName",fileContentAdm.getTblFileRecord().getFileRecordSn()
					,String.valueOf(model.getDocTypeName()),model.getFileRecordId(),String.valueOf(fileContentAdm.getDocTypeName())
					,operateRsn,operateTime,1);
		}
		fileContentAdm.setDocTypeName(model.getDocTypeName());
		//map.put("receiptDate", fileStudy.getReceiptDate());
		//map.put("receiptMan", fileStudy.getReceiptMan());
		if((fileContentAdm.getReceiptDate()==null&&model.getReceiptDate()!=null)||
				fileContentAdm.getReceiptDate()!=null&&fileContentAdm.getReceiptDate().compareTo(model.getReceiptDate())!=0)
		{
			writeTblLog(model.getArchiveCode(),fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileContentAdministration","receiptDate",fileContentAdm.getTblFileRecord().getFileRecordSn()
					,DateUtil.dateToString(model.getReceiptDate(),"yyyy-MM-dd"),model.getFileRecordId(),DateUtil.dateToString(fileContentAdm.getReceiptDate(),"yyyy-MM-dd")
					,operateRsn,operateTime,1);
		}
		fileContentAdm.setReceiptDate(model.getReceiptDate());
		if((fileContentAdm.getReceiptMan()==null&&model.getReceiptMan()!=null)||
				fileContentAdm.getReceiptMan()!=null&&!fileContentAdm.getReceiptMan().equals(model.getReceiptMan()))
		{
			writeTblLog(model.getArchiveCode(),fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileContentAdministration","receiptMan",fileContentAdm.getTblFileRecord().getFileRecordSn()
					,String.valueOf(model.getReceiptMan()),model.getFileRecordId(),String.valueOf(fileContentAdm.getReceiptMan())
					,operateRsn,operateTime,1);
		}
		fileContentAdm.setReceiptMan(model.getReceiptMan());
		
		tblFileContentAdministrationService.update(fileContentAdm);
		
		
		TblFileRecord record = tblFileRecordService.getById(model.getFileRecordId());//编辑的时候根据这个判断		
		if((record.getArchiveMaker()==null&&model.getTblFileRecord().getArchiveMaker()!=null)
				||(record.getArchiveMaker()!=null&&!record.getArchiveMaker().equals(model.getTblFileRecord().getArchiveMaker())))
		{
			writeTblLog(model.getArchiveCode(),fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","archiveMaker",record.getFileRecordSn()
					,record.getArchiveMaker(),model.getFileRecordId(),model.getTblFileRecord().getArchiveMaker()
					,operateRsn,operateTime,1);
		}
		record.setArchiveMaker(model.getTblFileRecord().getArchiveMaker());
		if(!record.getArchiveMediaFlag().equals(model.getTblFileRecord().getArchiveMediaFlag()))
		{
			writeTblLog(model.getArchiveCode(),fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","archiveMediaFlag",record.getFileRecordSn()
					,String.valueOf(record.getArchiveMediaFlag()),model.getFileRecordId(),String.valueOf(model.getTblFileRecord().getArchiveMediaFlag())
					,operateRsn,operateTime,1);
		}
		if(record.getArchiveMedia()!=null&&!record.getArchiveMedia().equals(model.getTblFileRecord().getArchiveMedia()))
		{
			writeTblLog(model.getArchiveCode(),fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","archiveMedia",record.getFileRecordSn()
					,record.getArchiveMedia(),model.getFileRecordId(),model.getTblFileRecord().getArchiveMedia()
					,operateRsn,operateTime,1);
		}
		record.setArchiveMedia(model.getTblFileRecord().getArchiveMedia());
		record.setArchiveMediaFlag(model.getTblFileRecord().getArchiveMediaFlag());
		
		if((record.getDestoryDate()==null&&model.getTblFileRecord().getDestoryDate()!=null)||
				(record.getDestoryDate()!=null&&record.getDestoryDate().compareTo(model.getTblFileRecord().getDestoryDate())!=0))
		{
			writeTblLog(model.getArchiveCode(),fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","destoryDate",record.getFileRecordSn()
					,DateUtil.dateToString(record.getDestoryDate(),"yyyy-MM-dd"),model.getFileRecordId(),DateUtil.dateToString(model.getTblFileRecord().getDestoryDate(),"yyyy-MM-dd")
					,operateRsn,operateTime,1);
		}
		record.setDestoryDate(model.getTblFileRecord().getDestoryDate());
		if((record.getFileDate()==null&&model.getTblFileRecord().getFileDate()!=null)
				||(record.getFileDate()!=null&&record.getFileDate().compareTo(model.getTblFileRecord().getFileDate())!=0))
		{
			writeTblLog(model.getArchiveCode(),fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","fileDate",record.getFileRecordSn()
					,DateUtil.dateToString(record.getFileDate(),"yyyy-MM-dd"),model.getFileRecordId(),DateUtil.dateToString(model.getTblFileRecord().getFileDate(),"yyyy-MM-dd")
					,operateRsn,operateTime,1);
		}
		record.setFileDate(model.getTblFileRecord().getFileDate());
		
		if((record.getFileOperator()==null&&model.getTblFileRecord().getFileOperator()!=null)
				||(record.getFileOperator()!=null&&!record.getFileOperator().equals(model.getTblFileRecord().getFileOperator())))
		{
			writeTblLog(model.getArchiveCode(),fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","fileOperator",record.getFileRecordSn()
					,record.getFileOperator(),model.getFileRecordId(),model.getTblFileRecord().getFileOperator()
					,operateRsn,operateTime,1);
		}
		
		record.setFileOperator(model.getTblFileRecord().getFileOperator());
		//record.setFileRecordSn(1);//sn是后台维护的。更新的时候用不到这个
		if((record.getKeepDate()==null&&model.getTblFileRecord().getKeepDate()!=null)
				||(record.getKeepDate()!=null&&record.getKeepDate().compareTo(model.getTblFileRecord().getKeepDate())!=0))
		{
			writeTblLog(model.getArchiveCode(),fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTitle()
					,"TblFileRecord","keepDate",record.getFileRecordSn()
					,DateUtil.dateToString(record.getKeepDate(),"yyyy-MM-dd"),model.getFileRecordId(),DateUtil.dateToString(model.getTblFileRecord().getKeepDate(),"yyyy-MM-dd")
					,operateRsn,operateTime,1);
		}
		record.setKeepDate(model.getTblFileRecord().getKeepDate());
		if((record.getKeyWord()==null&&model.getTblFileRecord().getKeyWord()!=null)
				||(record.getKeyWord()!=null&&!record.getKeyWord().equals(model.getTblFileRecord().getKeyWord())))
		{
			writeTblLog(model.getArchiveCode(),fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTitle()
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
			writeTblLog(model.getArchiveCode(),fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTitle()
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
		if(!fileIndex.getArchiveTitle().equals(archiveTitle)){
			writeTblLog(model.getArchiveCode(),fileIndex.getArchiveTitle(),
					"TblFileIndex","archiveTitle",record.getFileRecordSn()
					,archiveTitle,model.getFileRecordId(),fileIndex.getArchiveTitle(),
					operateRsn,operateTime,1);
		}
		fileIndex.setArchiveTitle(archiveTitle);
		
		tblFileIndexService.update(fileIndex);
		
		writeES("更新行政综合档案", 915, "TblFileContentAdministration", model.getFileRecordId());
		
		Map<String, Object> map0 = new HashMap<String, Object>();
		map0.put("archiveCode", fileContentAdm.getArchiveCode());
		map0.put("fileRecordId", fileContentAdm.getFileRecordId());
		
		map0.put("dispatchDate", DateUtil.dateToString(fileContentAdm.getDispatchDate(),"yyyy-MM-dd"));
		map0.put("dispatchUnit", fileContentAdm.getDispatchUnit());
		map0.put("docCode", fileContentAdm.getDocCode());
		map0.put("docName", fileContentAdm.getDocName());
		map0.put("docTypeFlag", fileContentAdm.getDocTypeFlag());
		map0.put("docTypeName", fileContentAdm.getDocTypeName());
		map0.put("receiptDate", DateUtil.dateToString(fileContentAdm.getReceiptDate(),"yyyy-MM-dd"));
		map0.put("receiptMan", fileContentAdm.getReceiptMan());
		
		map0.put("archiveTypeCode", fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
		
		DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
		if(dictArchiveType!=null)
		{
			map0.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
		}
		
		map0.put("archiveTitle", fileContentAdm.getTblFileRecord().getTblFileIndex().getArchiveTitle());
		map0.put("storePosition", fileContentAdm.getTblFileRecord().getTblFileIndex().getStorePosition());
		map0.put("fileDate", DateUtil.dateToString(fileContentAdm.getTblFileRecord().getFileDate(),"yyyy-MM-dd"));
		map0.put("fileRecordSn", fileContentAdm.getTblFileRecord().getFileRecordSn());
		map0.put("archiveMaker", fileContentAdm.getTblFileRecord().getArchiveMaker());
		map0.put("archiveMedia", fileContentAdm.getTblFileRecord().getArchiveMedia());
		map0.put("fileOperator", fileContentAdm.getTblFileRecord().getFileOperator());
		map0.put("keepDate", DateUtil.dateToString(fileContentAdm.getTblFileRecord().getKeepDate(),"yyyy-MM-dd"));
		map0.put("remark", fileContentAdm.getTblFileRecord().getRemark());
		if(fileContentAdm.getTblFileRecord().getDestoryDate()!=null)
		{
			map0.put("destoryDate", DateUtil.dateToString(fileContentAdm.getTblFileRecord().getDestoryDate(),"yyyy-MM-dd"));
		}
		map0.put("validationFlag", fileContentAdm.getTblFileRecord().getTblFileIndex().getValidationFlag());//0：否；1：验证数据
		map0.put("archiveMediaFlag", fileContentAdm.getTblFileRecord().getArchiveMediaFlag());
		map0.put("archiveMedia", fileContentAdm.getTblFileRecord().getArchiveMedia());
		
		map.put("record", map0);
		
		
		map.put("success", true);

		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void delete()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		writeES("删除行政综合档案", 916, "TblFileContentAdministration", model.getFileRecordId());
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
			String esLinkId = writeES("销毁行政综合档案", 925, "TblFileContentAdministration", record.getFileRecordId());
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
		putSearchConIntoSession(searchString,6);
		//,rows,page
		
		//Integer isNowValid,Integer isInvalid,Date changeEndDate,Integer yearNum,
		
		 Map<String, Object> resultMap = tblFileContentAdministrationService.getByCondition(model.getDocTypeFlag(),fileStartDate,fileEndDate,keepEndDate,isDestory,isValid,searchString,page,rows);
		 //Integer total = (Integer)resultMap.get("total");
		 List< Map<String, Object>> fileStudys = ( List<Map<String, Object>>)resultMap.get("rows");
		 
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		for( Map<String, Object> fileStudy:fileStudys)
		{
			/*Map<String, Object> map = new HashMap<String, Object>();
			map.put("archiveCode", fileStudy.getArchiveCode());
			map.put("fileRecordId", fileStudy.getFileRecordId());
			
			map.put("dispatchDate", DateUtil.dateToString(fileStudy.getDispatchDate(),"yyyy-MM-dd"));
			map.put("dispatchUnit", fileStudy.getDispatchUnit());
			map.put("docCode", fileStudy.getDocCode());
			map.put("docName", fileStudy.getDocName());
			map.put("docTypeFlag", fileStudy.getDocTypeFlag());
			map.put("docTypeName", fileStudy.getDocTypeName());
			map.put("receiptDate", DateUtil.dateToString(fileStudy.getReceiptDate(),"yyyy-MM-dd"));
			map.put("receiptMan", fileStudy.getReceiptMan());
			map.put("archiveTypeCode", fileStudy.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
			 */
			if(fileStudy.get("dispatchDate")!=null)
			{
				fileStudy.put("dispatchDate", DateUtil.dateToString((Date)fileStudy.get("dispatchDate"),"yyyy-MM-dd"));
			}
			if(fileStudy.get("receiptDate")!=null)
			{
				fileStudy.put("receiptDate", DateUtil.dateToString((Date)fileStudy.get("receiptDate"),"yyyy-MM-dd"));
			}
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
			map.put("archiveMedia", fileStudy.getTblFileRecord().getArchiveMedia());
			map.put("fileOperator", fileStudy.getTblFileRecord().getFileOperator());
			map.put("keepDate", DateUtil.dateToString(fileStudy.getTblFileRecord().getKeepDate(),"yyyy-MM-dd"));
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
			map.put("archiveMedia", fileStudy.getTblFileRecord().getArchiveMedia());
			*/
			mapList.add(fileStudy);
		}
		resultMap.put("rows",mapList);
		
		writeJson(JsonPluginsUtil.beanToJson(resultMap));
	}
	
	
	public void writeTblLog(String archiveCode,String archiveTitle,String tableName,String fieldName,Integer fileRecordSn
			,String newValue,String oldFileRecordId,String oldValue,String operateRsn,Date operateTime,Integer operateTypeFlag)//操作日志
	{
		TblLog tblLog = new TblLog();
		tblLog.setArchiveCode(archiveCode);
		tblLog.setArchiveTitle(archiveTitle);
		tblLog.setArchiveTypeFlag(7);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
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

	public Integer getHasAll() {
		return hasAll;
	}

	public void setHasAll(Integer hasAll) {
		this.hasAll = hasAll;
	}

	public Date getDestoryDate() {
		return destoryDate;
	}

	public void setDestoryDate(Date destoryDate) {
		this.destoryDate = destoryDate;
	}
}
