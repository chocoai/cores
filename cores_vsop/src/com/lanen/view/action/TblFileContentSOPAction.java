package com.lanen.view.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.lanen.model.TblFileContentSop;
import com.lanen.model.TblFileRecord;
import com.lanen.model.TblLog;
import com.lanen.model.TblSopTableLinkInfo;
import com.lanen.model.User;
import com.lanen.model.archive.DictArchiveType;
import com.lanen.model.archive.DictSoptype;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.service.archive.DictArchiveTypeService;
import com.lanen.service.archive.DictDataTableService;
import com.lanen.service.archive.DictSOPTypeService;
import com.lanen.service.archive.TblFileContentSopService;
import com.lanen.service.archive.TblFileIndexService;
import com.lanen.service.archive.TblFileRecordService;
import com.lanen.service.archive.TblLog2Service;
import com.lanen.service.archive.TblSopTableLinkInfoService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.util.DateUtil;
import com.lanen.util.FileOperateUtil;
import com.lanen.util.MathUtils;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class TblFileContentSOPAction extends BaseAction<TblFileContentSop> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3800837680043568435L;
	
	@Resource
	private TblFileContentSopService tblFileContentSopService;
	@Resource
	private TblFileIndexService tblFileIndexService;
	@Resource
	private TblFileRecordService tblFileRecordService;
	@Resource
	private DictSOPTypeService dictSOPTypeService;
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
	private TblSopTableLinkInfoService tblSopTableLinkInfoService;
	
	
	private String archiveTitle;
	private String archiveTypeCode;
	private String storePosition;
	private Integer validationFlag;
	private String operateRsn;
	
	private Integer isAll;
	private Integer isNowValid;
	private Integer isInvalid;
	private Integer needChange;
	private Date changeEndDate;
	private Integer yearNum;
	private Integer yearNumUnit;//1年 2月 3日
	private Date fileStartDate;
	private Date fileEndDate;
	private Date keepEndDate;
	private Integer isDestory;
	private Integer isValid;
	private String searchString;
	
	private String codes;
	
	private Integer rows;// 每页显示的记录数 
	private Integer page;// 当前第几页 
	
	private Date destoryDate;
	private String id;
	
	private InputStream fileInput;  
    private String sopfile2FileName;  
    private String downFileName;
	
	private File sopfile2;
	
	private Integer fileFlag;
	
	public String list()
	{
		
		return "list";
	}
	public void save()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		//档案编号编号不能相同
		Date operateTime = new Date();
		//boolean isExistArchiveCode = tblFileIndexService.isExistArchiveCode(model.getArchiveCode());
		boolean isExistCodeAndVer = isExistSopAndVer(model.getSopcode(), model.getSopver());
		if(!isExistCodeAndVer)
		{
			/*TblFileIndex fileIndex = new TblFileIndex();
			fileIndex.setArchiveCode(model.getArchiveCode());
			fileIndex.setArchiveTitle(archiveTitle);
			fileIndex.setArchiveTypeCode(archiveTypeCode);
			fileIndex.setArchiveTypeFlag(3);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
			fileIndex.setOperateTime(operateTime);
			fileIndex.setOperator(getCurrentUser().getRealName());
			fileIndex.setStorePosition(storePosition);
			fileIndex.setValidationFlag(validationFlag);
			
			tblFileIndexService.save(fileIndex);*/
			
			String key = tblFileRecordService.getKey("TblFileRecord");
			TblFileRecord record = new TblFileRecord();			
			//record.setArchiveMaker(model.getTblFileRecord().getArchiveMaker());
			record.setArchiveMedia(model.getTblFileRecord().getArchiveMedia());
			record.setArchiveMediaFlag(model.getTblFileRecord().getArchiveMediaFlag());
			//record.setDestoryDate(model.getTblFileRecord().getDestoryDate());
			//record.setFileDate(model.getTblFileRecord().getFileDate());
			//record.setFileDateType(3);//年月日
			//record.setFileOperator(model.getTblFileRecord().getFileOperator());
			record.setFileRecordId(key);
			record.setFileRecordSn(1);//新建肯定是1，追加再往后加
			//record.setKeepDate(model.getTblFileRecord().getKeepDate());
			//record.setKeyWord(model.getTblFileRecord().getKeyWord());
			record.setOperateTime(operateTime);
			record.setOperator(getCurrentUser().getRealName());
			record.setFileFlag(-1);//0归档，-1没归档
			//record.setRemark(model.getTblFileRecord().getRemark());
			//record.setTblFileIndex(fileIndex);
			
			tblFileRecordService.save(record);
			
			TblFileContentSop fileContentSop = new TblFileContentSop();
			//fileContentSop.setArchiveCode(model.getArchiveCode());
			fileContentSop.setFileRecordId(key);
			fileContentSop.setSopcode(model.getSopcode());
			fileContentSop.setSopeffectiveDate(model.getSopeffectiveDate());
			fileContentSop.setSopinvalidDate(model.getSopinvalidDate());
			fileContentSop.setSopname(model.getSopname());
			DictSoptype soptype = dictSOPTypeService.getById(model.getSoptypeCode());
			fileContentSop.setSoptypeCode(soptype.getSoptypeCode());
			fileContentSop.setSoptypeName(soptype.getSopname());
			fileContentSop.setSopver(model.getSopver());
			
			fileContentSop.setSopflag(model.getSopflag());
			fileContentSop.setTblFileRecord(record);
			
			if(sopfile2!=null)
			{
				byte[] sopfile = FileOperateUtil.getInstance().getBytes(sopfile2);
				fileContentSop.setSopfile(sopfile);
				fileContentSop.setSopfileName(sopfile2FileName);
			}
			
			tblFileContentSopService.save(fileContentSop);
			
			addLastLinkToNew(model.getSopcode(),model.getSopver(),model.getSopflag());
			
			Map<String, Object> map0 = new HashMap<String, Object>();
			map0.put("archiveCode", fileContentSop.getArchiveCode());
			map0.put("fileRecordId", fileContentSop.getFileRecordId());
			map0.put("sopcode", fileContentSop.getSopcode());
			map0.put("sopeffectiveDate", DateUtil.dateToString(fileContentSop.getSopeffectiveDate(),"yyyy-MM-dd"));
			map0.put("sopinvalidDate", DateUtil.dateToString(fileContentSop.getSopinvalidDate(),"yyyy-MM-dd"));
			if(fileContentSop.getSopinvalidDate()!=null&&fileContentSop.getSopinvalidDate().after(new Date()))
			{
				map0.put("isInvalid", true);
			}else {
				map0.put("isInvalid", false);
			}
			map0.put("sopname", fileContentSop.getSopname());
			map0.put("soptypeCode", fileContentSop.getSoptypeCode());
			map0.put("soptypeName", fileContentSop.getSoptypeName());
			map0.put("sopver", fileContentSop.getSopver());
			map0.put("sopflag", fileContentSop.getSopflag());
			
		
			//map0.put("fileDate", DateUtil.dateToString(fileContentSop.getTblFileRecord().getFileDate(),"yyyy-MM-dd"));
			map0.put("fileRecordSn", fileContentSop.getTblFileRecord().getFileRecordSn());
			//map0.put("archiveMaker", fileContentSop.getTblFileRecord().getArchiveMaker());
			//map0.put("fileOperator", fileContentSop.getTblFileRecord().getFileOperator());
			//map0.put("keepDate", DateUtil.dateToString(fileContentSop.getTblFileRecord().getKeepDate(),"yyyy-MM-dd"));	
			//map0.put("remark", fileContentSop.getTblFileRecord().getRemark());
			if(fileContentSop.getTblFileRecord().getDestoryDate()!=null)
			{
				map0.put("destoryDate", DateUtil.dateToString(fileContentSop.getTblFileRecord().getDestoryDate(),"yyyy-MM-dd"));
			}
		//	map0.put("validationFlag", fileContentSop.getTblFileRecord().getTblFileIndex().getValidationFlag());//0：否；1：验证数据
			map0.put("archiveMediaFlag", fileContentSop.getTblFileRecord().getArchiveMediaFlag());
			map0.put("archiveMedia", fileContentSop.getTblFileRecord().getArchiveMedia());
			
			map0.put("delFlag", fileContentSop.getTblFileRecord().getDelFlag());
			if(fileContentSop.getSopfile()!=null&&!"".equals(fileContentSop.getSopfile()))
			{
				map0.put("sopfile", fileContentSop.getSopfileName());//
			}else {
				map0.put("sopfile", "");
			}
			
			map.put("record", map0);
			map.put("typeId", "typeId"+fileContentSop.getSoptypeCode());
			
			map.put("success", true);
		}else {
			
			map.put("success", false);
			map.put("msg", "该SOP编号的该版本已经存在");
			
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}

	
	
	public void addLastLinkToNew(String sopcode,String sopver,Integer sopflag)
	{
		//如果是sop把以前版本相关的有效表格关联加入，如果是表格把以前的有效sop加入
		if(sopflag==null||sopflag==0){
			List<Map<String, Object>> list = tblSopTableLinkInfoService.getEffectLinkBySOP(sopcode);//上一个版本的有效的链接
			for(Map<String, Object> link:list)
			{
				TblSopTableLinkInfo info = new TblSopTableLinkInfo();
				String key = tblSopTableLinkInfoService.getKey("TblSopTableLinkInfo");
				info.setId(key);
				info.setSopcode(sopcode);
				info.setSopver(sopver);
				info.setTblCode((String)link.get("TblCode"));
				info.setTblVer((String)link.get("TblVer"));
				
				tblSopTableLinkInfoService.save(info);
			}
			
		}else if(sopflag==1){//表格
			List<Map<String, Object>> list = tblSopTableLinkInfoService.getEffectLinkByTbl(sopcode);//上一个版本的有效的链接
			for(Map<String, Object> link:list)
			{
				TblSopTableLinkInfo info = new TblSopTableLinkInfo();
				String key = tblSopTableLinkInfoService.getKey("TblSopTableLinkInfo");
				info.setId(key);
				info.setSopcode((String)link.get("SOPCode"));
				info.setSopver((String)link.get("SOPVer"));
				info.setTblCode(sopcode);
				info.setTblVer(sopver);
				
				tblSopTableLinkInfoService.save(info);
				
			}
		}
		
	}
		
	public void loadTree()
	{
		List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>();
		//id;text; state ; children; iconCls ;
		
		List<DictSoptype> list = dictSOPTypeService.findAll();
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
	public void getTree(List<DictSoptype> list,List<Map<String, Object>> tree)
	{
		List<DictSoptype> noDealList = new ArrayList<DictSoptype>();
		Map<String, Object> ctm = null;
		
		for(int i=0;i<list.size();i++)
		{
			DictSoptype type=list.get(i);
			if(type.getPid()==null||"".equals(type.getPid()))
			{//没有父类的直接加进去
				ctm = new HashMap<String, Object>();
				ctm.put("id",type.getId());
				ctm.put("sn", type.getSn());
				ctm.put("pid",type.getPid());
				ctm.put("text", type.getSopname());
				ctm.put("sopTypeCode", type.getSoptypeCode());
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
					ctmChile.put("text",type.getSopname());
					ctmChile.put("sopTypeCode", type.getSoptypeCode());
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
	public Map<String, Object> getParent(DictSoptype type,List<Map<String, Object>> tree)
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

	
	public void appendSave()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		//档案编号编号不能相同
		Date operateTime = new Date();
		boolean isExistArchiveCode = tblFileIndexService.isExistArchiveCode(model.getArchiveCode());
		boolean isExistCodeAndVer = isExistSopAndVer(model.getSopcode(), model.getSopver());
		if(!isExistCodeAndVer)
		{
			//追加归档不用修改fileIndex
			//TblFileIndex fileIndex = tblFileIndexService.getById(model.getArchiveCode());
			
			String key = tblFileRecordService.getKey("TblFileRecord");
			TblFileRecord record = new TblFileRecord();			
			//record.setArchiveMaker(model.getTblFileRecord().getArchiveMaker());
			record.setArchiveMedia(model.getTblFileRecord().getArchiveMedia());
			record.setArchiveMediaFlag(model.getTblFileRecord().getArchiveMediaFlag());
			//record.setDestoryDate(model.getTblFileRecord().getDestoryDate());
			//record.setFileDate(model.getTblFileRecord().getFileDate());
			//record.setFileDateType(3);//年月日
			//record.setFileOperator(model.getTblFileRecord().getFileOperator());
			record.setFileRecordId(key);
			
			//Integer maxSn = tblFileRecordService.getMaxSnByArchiveCode(model.getArchiveCode());
		//	record.setFileRecordSn(maxSn+1);//追加是现在最大的+1
			
			//record.setKeepDate(model.getTblFileRecord().getKeepDate());
			//record.setKeyWord(model.getTblFileRecord().getKeyWord());
			record.setOperateTime(operateTime);
			record.setOperator(getCurrentUser().getRealName());
			record.setRemark(model.getTblFileRecord().getRemark());
			//record.setTblFileIndex(fileIndex);
			record.setFileFlag(-1);//0归档，-1没归档
			
			tblFileRecordService.save(record);
			
			TblFileContentSop fileContentSop = new TblFileContentSop();
			//fileContentSop.setArchiveCode(model.getArchiveCode());
			fileContentSop.setFileRecordId(key);
			fileContentSop.setSopcode(model.getSopcode());
			fileContentSop.setSopeffectiveDate(model.getSopeffectiveDate());
			fileContentSop.setSopinvalidDate(model.getSopinvalidDate());
			fileContentSop.setSopname(model.getSopname());
			DictSoptype soptype = dictSOPTypeService.getById(model.getSoptypeCode());
			fileContentSop.setSoptypeCode(soptype.getSoptypeCode());
			fileContentSop.setSoptypeName(soptype.getSopname());
			fileContentSop.setSopflag(model.getSopflag());

			fileContentSop.setSopver(model.getSopver());
			fileContentSop.setTblFileRecord(record);
			if(sopfile2!=null)
			{
				byte[] sopfile = FileOperateUtil.getInstance().getBytes(sopfile2);
				fileContentSop.setSopfile(sopfile);
				fileContentSop.setSopfileName(sopfile2FileName);
			}
			
			
			tblFileContentSopService.save(fileContentSop);
			
			addLastLinkToNew(model.getSopcode(),model.getSopver(),model.getSopflag());
			
			Map<String, Object> map0 = new HashMap<String, Object>();
			map0.put("archiveCode", fileContentSop.getArchiveCode());
			map0.put("fileRecordId", fileContentSop.getFileRecordId());
			map0.put("sopcode", fileContentSop.getSopcode());
			map0.put("sopeffectiveDate", DateUtil.dateToString(fileContentSop.getSopeffectiveDate(),"yyyy-MM-dd"));
			map0.put("sopinvalidDate", DateUtil.dateToString(fileContentSop.getSopinvalidDate(),"yyyy-MM-dd"));
			if(fileContentSop.getSopinvalidDate()!=null&&fileContentSop.getSopinvalidDate().after(new Date()))
			{
				map0.put("isInvalid", true);
			}else {
				map0.put("isInvalid", false);
			}
			map0.put("sopname", fileContentSop.getSopname());
			map0.put("soptypeCode", fileContentSop.getSoptypeCode());
			map0.put("soptypeName", fileContentSop.getSoptypeName());
			map0.put("sopver", fileContentSop.getSopver());
			map0.put("sopflag", fileContentSop.getSopflag());
			
			//map0.put("archiveTypeCode", fileContentSop.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
			//String archiveTypeCode = fileStudy.getTblFileRecord().getTblFileIndex().getArchiveTypeCode();
			//DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(fileContentSop.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
			//if(dictArchiveType!=null)
			//{
			//	map0.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
			//}
			
			//map0.put("archiveTitle", fileContentSop.getTblFileRecord().getTblFileIndex().getArchiveTitle());
			//map0.put("storePosition", fileContentSop.getTblFileRecord().getTblFileIndex().getStorePosition());
			//map0.put("fileDate", DateUtil.dateToString(fileContentSop.getTblFileRecord().getFileDate(),"yyyy-MM-dd"));
			map0.put("fileRecordSn", fileContentSop.getTblFileRecord().getFileRecordSn());
			//map0.put("archiveMaker", fileContentSop.getTblFileRecord().getArchiveMaker());
		//	map0.put("fileOperator", fileContentSop.getTblFileRecord().getFileOperator());
		//	map0.put("keepDate", DateUtil.dateToString(fileContentSop.getTblFileRecord().getKeepDate(),"yyyy-MM-dd"));	
			//map0.put("remark", fileContentSop.getTblFileRecord().getRemark());
			if(fileContentSop.getTblFileRecord().getDestoryDate()!=null)
			{
				map0.put("destoryDate", DateUtil.dateToString(fileContentSop.getTblFileRecord().getDestoryDate(),"yyyy-MM-dd"));
			}
			//map0.put("validationFlag", fileContentSop.getTblFileRecord().getTblFileIndex().getValidationFlag());//0：否；1：验证数据
			map0.put("archiveMediaFlag", fileContentSop.getTblFileRecord().getArchiveMediaFlag());
			map0.put("archiveMedia", fileContentSop.getTblFileRecord().getArchiveMedia());
			
			map0.put("delFlag", fileContentSop.getTblFileRecord().getDelFlag());
			if(fileContentSop.getSopfile()!=null&&!"".equals(fileContentSop.getSopfile()))
			{
				map0.put("sopfile", fileContentSop.getSopfileName());//
			}else {
				map0.put("sopfile", "");
			}
			map.put("record", map0);
			map.put("typeId", "typeId"+fileContentSop.getSoptypeCode());
			
			map.put("success", true);
		}else {
			map.put("success", false);
			map.put("msg", "该SOP编号的该版本已经存在");
			
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public boolean isExistSopAndVer(String sopcode,String sopver)
	{
		boolean isExist = tblFileContentSopService.isExistCodeAndVer(sopcode,sopver);
		
		return isExist;
	}
	public boolean isExistSopAndVerExceptOne(String sopcode,String sopver,String fileRecordId)
	{
		boolean isExist = tblFileContentSopService.isExistCodeAndVerExceptOne(sopcode,sopver,fileRecordId);
		
		return isExist;
	}
	
	public boolean isExistCode(String sopcode,Integer sopflag)
	{
		//增加SOP的时候判断是否存在该编号的表格，增加SOP表格的时候判断是否存在该SOP编号
		boolean isExist = tblFileContentSopService.isExistCodeInSop(sopcode,sopflag);
		
		return isExist;
	}
	public void getVerByCode()
	{
		boolean isExist = true;
		String str = "";
		//增加SOP的时候判断是否存在该编号的表格，增加SOP表格的时候判断是否存在该SOP编号
		if(model.getSopflag()==null||model.getSopflag()==0)
		{
			str = "SOP表格中已经存在该SOP编号";
			isExist = isExistCode(model.getSopcode(),1);
		}else if(model.getSopflag()==1){
			str = "SOP中已经存在该SOP编号";
			isExist = isExistCode(model.getSopcode(),0);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if(!isExist)
		{
			TblFileContentSop sop = tblFileContentSopService.getMaxVerByCode(model.getSopcode());
			Integer maxVer=0 ;
			if(sop!=null)
			{
				try {
					maxVer = Integer.valueOf(sop.getSopver());
				} catch (Exception e) {
					maxVer = 0;
				}
				//map.put("archiveCode",MathUtils.add1ToStringInt(sop.getArchiveCode()));
				map.put("sopflag", sop.getSopflag());//0sop 1sop表格
				map.put("sopname", sop.getSopname());
				DictSoptype dict = dictSOPTypeService.getByCode( sop.getSoptypeCode());
				if(dict!=null)
				{
					map.put("soptypeCode", dict.getId());//前台的是combotree是id
					map.put("soptypeName", dict.getSopname());//sop type name
				}
				//TblFileIndex fileIndex = sop.getTblFileRecord().getTblFileIndex();
			//	String typeCode = fileIndex.getArchiveTypeCode();
			//	map.put("archiveTypeCode",fileIndex.getArchiveTypeCode());
				//DictArchiveType type = dictArchiveTypeService.getByArchiveTypeCode(typeCode);
				//if(type!=null)
				//{
				//	map.put("archiveTypeName", type.getArchiveTypeName());
				//}
				//map.put("storePosition", fileIndex.getStorePosition());
			//	map.put("fileOperator", sop.getTblFileRecord().getFileOperator());
			//	map.put("archiveMaker", sop.getTblFileRecord().getArchiveMaker());
			//	map.put("keyWord", sop.getTblFileRecord().getKeyWord());
			//	map.put("remark", sop.getTblFileRecord().getRemark());
			}
		
			map.put("success", true);
			map.put("sopver", maxVer+1);
		}else{
			map.put("success", false);
			map.put("msg", str);
		}
		
		writeJson(JsonPluginsUtil.beanToJson(map));
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
		
		TblFileContentSop fileContentsop = tblFileContentSopService.getById(model.getFileRecordId());
		boolean isExistCodeAndVer = isExistSopAndVerExceptOne(model.getSopcode(), model.getSopver(),model.getFileRecordId());
		if(!isExistCodeAndVer)
		{
			//Sopcode,SopeffectiveDate,SopinvalidDate,
			if((fileContentsop.getSopcode()==null&&model.getSopcode()!=null)
					||(fileContentsop.getSopcode()!=null&&!fileContentsop.getSopcode().equals(model.getSopcode())))
			{
				writeTblLog("TblFileContentSop","sopcode",fileContentsop.getTblFileRecord().getFileRecordSn()
						,model.getSopcode(),model.getFileRecordId(),fileContentsop.getSopcode()
						,operateRsn,operateTime,1);
			}
			fileContentsop.setSopcode(model.getSopcode());
			if((fileContentsop.getSopeffectiveDate()==null&&model.getSopeffectiveDate()!=null)
					||(fileContentsop.getSopeffectiveDate()!=null&&fileContentsop.getSopeffectiveDate().compareTo(model.getSopeffectiveDate())!=0))
			{
				String str = "";
				if(fileContentsop.getSopeffectiveDate()!=null&&!"".equals(fileContentsop.getSopeffectiveDate()))
				{
					str = DateUtil.dateToString(fileContentsop.getSopeffectiveDate(),"yyyy-MM-dd");
				}
				writeTblLog("TblFileContentSop","sopeffectiveDate",fileContentsop.getTblFileRecord().getFileRecordSn()
						,DateUtil.dateToString(model.getSopeffectiveDate(),"yyyy-MM-dd"),model.getFileRecordId(),str
						,operateRsn,operateTime,1);
			}
			fileContentsop.setSopeffectiveDate(model.getSopeffectiveDate());
			
			if((fileContentsop.getSopinvalidDate()==null&&model.getSopinvalidDate()!=null)||
					fileContentsop.getSopinvalidDate()!=null&&fileContentsop.getSopinvalidDate().compareTo(model.getSopinvalidDate())!=0)
			{
				String str = "";
				if(fileContentsop.getSopinvalidDate()!=null&&!"".equals(fileContentsop.getSopinvalidDate()))
				{
					str = DateUtil.dateToString(fileContentsop.getSopinvalidDate(),"yyyy-MM-dd");
				}
				writeTblLog("TblFileContentSop","sopinvalidDate",fileContentsop.getTblFileRecord().getFileRecordSn()
						,DateUtil.dateToString(model.getSopinvalidDate(),"yyyy-MM-dd"),model.getFileRecordId(),str
						,operateRsn,operateTime,1);
			}
			fileContentsop.setSopinvalidDate(model.getSopinvalidDate());
			//Sopname,SoptypeCode,SoptypeName,Sopver
			if((fileContentsop.getSopname()==null&&model.getSopname()!=null)
					||(fileContentsop.getSopname()!=null&&!fileContentsop.getSopname().equals(model.getSopname())))
			{
				writeTblLog("TblFileContentSop","sopname",fileContentsop.getTblFileRecord().getFileRecordSn()
						,model.getSopname(),model.getFileRecordId(),fileContentsop.getSopname()
						,operateRsn,operateTime,1);
			}
			fileContentsop.setSopname(model.getSopname());
			DictSoptype dictSOPType = dictSOPTypeService.getById(model.getSoptypeCode());//前台传过来的是id，数据库中存的是code
			if(dictSOPType!=null&&
					((fileContentsop.getSoptypeCode()==null&&dictSOPType.getSoptypeCode()!=null)
					||(fileContentsop.getSoptypeCode()!=null&&!fileContentsop.getSoptypeCode().equals(dictSOPType.getSoptypeCode()))))
			{
				writeTblLog("TblFileContentSop","soptypeCode",fileContentsop.getTblFileRecord().getFileRecordSn()
						,dictSOPType.getSoptypeCode(),model.getFileRecordId(),fileContentsop.getSoptypeCode()
						,operateRsn,operateTime,1);
				
				map.put("typeId", "typeId"+dictSOPType.getSoptypeCode());
				
				fileContentsop.setSoptypeCode(dictSOPType.getSoptypeCode());
			}
			
			if(dictSOPType!=null&&
					(fileContentsop.getSoptypeName()!=null&&!fileContentsop.getSoptypeName().equals(dictSOPType.getSopname())))
			{
				writeTblLog("TblFileContentSop","soptypeName",fileContentsop.getTblFileRecord().getFileRecordSn()
						,dictSOPType.getSopname(),model.getFileRecordId(),fileContentsop.getSoptypeName()
						,operateRsn,operateTime,1);
				
				fileContentsop.setSoptypeName(dictSOPType.getSopname());
			}
			
			if((fileContentsop.getSopver()==null&&model.getSopver()!=null)
					||(fileContentsop.getSopver()!=null&&!fileContentsop.getSopver().equals(model.getSopver())))
			{
				writeTblLog("TblFileContentSop","sopver",fileContentsop.getTblFileRecord().getFileRecordSn()
						,model.getSopver(),model.getFileRecordId(),fileContentsop.getSopver()
						,operateRsn,operateTime,1);
			}
			fileContentsop.setSopver(model.getSopver());
			
			if((fileContentsop.getSopflag()==null&&model.getSopflag()!=null)
					||(fileContentsop.getSopflag()!=null&&!fileContentsop.getSopflag().equals(model.getSopflag())))
			{
				writeTblLog("TblFileContentSop","sopflag",fileContentsop.getTblFileRecord().getFileRecordSn()
						,""+model.getSopflag(),model.getFileRecordId(),""+fileContentsop.getSopflag()
						,operateRsn,operateTime,1);
			}
			fileContentsop.setSopflag(model.getSopflag());
			
			tblFileContentSopService.update(fileContentsop);
			
			
			TblFileRecord record = tblFileRecordService.getById(model.getFileRecordId());//编辑的时候根据这个判断		
			
			if((record.getArchiveMediaFlag()==null&&model.getTblFileRecord().getArchiveMediaFlag()!=null)||
					(record.getArchiveMediaFlag()!=null&&!record.getArchiveMediaFlag().equals(model.getTblFileRecord().getArchiveMediaFlag())))
			{
				writeTblLog("TblFileRecord","archiveMediaFlag",record.getFileRecordSn()
						,String.valueOf(record.getArchiveMediaFlag()),model.getFileRecordId(),String.valueOf(model.getTblFileRecord().getArchiveMediaFlag())
						,operateRsn,operateTime,1);
			}
			if(record.getArchiveMedia()!=null&&!record.getArchiveMedia().equals(model.getTblFileRecord().getArchiveMedia()))
			{
				writeTblLog("TblFileRecord","archiveMedia",record.getFileRecordSn()
						,record.getArchiveMedia(),model.getFileRecordId(),model.getTblFileRecord().getArchiveMedia()
						,operateRsn,operateTime,1);
			}
			record.setArchiveMedia(model.getTblFileRecord().getArchiveMedia());
			record.setArchiveMediaFlag(model.getTblFileRecord().getArchiveMediaFlag());
			
			
			
			
			
			tblFileRecordService.update(record);
			
			
			writeES("更新SOP", 942, "TblFileContentSop", model.getFileRecordId());
		
			Map<String, Object> map0 = new HashMap<String, Object>();
			map0.put("archiveCode", fileContentsop.getArchiveCode());
			map0.put("fileRecordId", fileContentsop.getFileRecordId());
			map0.put("sopcode", fileContentsop.getSopcode());
			map0.put("sopeffectiveDate", DateUtil.dateToString(fileContentsop.getSopeffectiveDate(),"yyyy-MM-dd"));
			map0.put("sopinvalidDate", DateUtil.dateToString(fileContentsop.getSopinvalidDate(),"yyyy-MM-dd"));
			if(fileContentsop.getSopinvalidDate()!=null&&fileContentsop.getSopinvalidDate().after(new Date()))
			{
				map0.put("isInvalid", true);
			}else {
				map0.put("isInvalid", false);
			}
			map0.put("sopname", fileContentsop.getSopname());
			map0.put("soptypeCode", fileContentsop.getSoptypeCode());
			map0.put("soptypeName", fileContentsop.getSoptypeName());
			map0.put("sopver", fileContentsop.getSopver());
			map0.put("sopflag", fileContentsop.getSopflag());
			
			//map0.put("archiveTypeCode", fileContentsop.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
			//String archiveTypeCode = fileStudy.getTblFileRecord().getTblFileIndex().getArchiveTypeCode();
			//DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(fileContentsop.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
			//if(dictArchiveType!=null)
			//{
			//	map0.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
			//}
			
			//map0.put("archiveTitle", fileContentsop.getTblFileRecord().getTblFileIndex().getArchiveTitle());
			//map0.put("storePosition", fileContentsop.getTblFileRecord().getTblFileIndex().getStorePosition());
			//map0.put("fileDate", DateUtil.dateToString(fileContentsop.getTblFileRecord().getFileDate(),"yyyy-MM-dd"));
			map0.put("fileRecordSn", fileContentsop.getTblFileRecord().getFileRecordSn());
			//map0.put("archiveMaker", fileContentsop.getTblFileRecord().getArchiveMaker());
		//	map0.put("fileOperator", fileContentsop.getTblFileRecord().getFileOperator());
		//	map0.put("keepDate", DateUtil.dateToString(fileContentsop.getTblFileRecord().getKeepDate(),"yyyy-MM-dd"));	
		//	map0.put("remark", fileContentsop.getTblFileRecord().getRemark());
			if(fileContentsop.getTblFileRecord().getDestoryDate()!=null)
			{
				map0.put("destoryDate", DateUtil.dateToString(fileContentsop.getTblFileRecord().getDestoryDate(),"yyyy-MM-dd"));
			}
			//map0.put("validationFlag", fileContentsop.getTblFileRecord().getTblFileIndex().getValidationFlag());//0：否；1：验证数据
			map0.put("archiveMediaFlag", fileContentsop.getTblFileRecord().getArchiveMediaFlag());
			map0.put("archiveMedia", fileContentsop.getTblFileRecord().getArchiveMedia());
			
			map0.put("delFlag", fileContentsop.getTblFileRecord().getDelFlag());
			if(fileContentsop.getSopfile()!=null&&!"".equals(fileContentsop.getSopfile()))
			{
				map0.put("sopfile", fileContentsop.getSopfileName());//
			}else {
				map0.put("sopfile", "");
			}
			
			map.put("record", map0);
			
			
			map.put("success", true);
		}else{
			map.put("success", true);
			map.put("msg", "该SOP编号的该版本已经存在！");
		}

		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void delete()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		writeES("删除SOP", 943, "TblFileContentSop", model.getFileRecordId());
		TblFileRecord record = tblFileRecordService.getById(model.getFileRecordId());
		record.setDelFlag(1);
		Date operateTime = new Date();
		record.setDelTime(operateTime);
		
		tblFileRecordService.update(record);
		
		writeTblLog("TblFileRecord","delFlag",record.getFileRecordSn()
				,""+0,model.getFileRecordId(),""+1,
				operateRsn,operateTime,2);
		
		map.put("fileRecordId", model.getFileRecordId());
		map.put("success", true);

		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void destroy()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		TblFileRecord record0 = tblFileRecordService.getById(model.getFileRecordId());
		Date operateTime = new Date();
		if(record0.getTblFileIndex()!=null)
		{
			List<TblFileRecord> recordList = tblFileRecordService.getByArchiveCode(record0.getTblFileIndex().getArchiveCode());
			for(TblFileRecord record:recordList)
			{
				record.setDestoryDate(destoryDate);
				String esLinkId = writeES("销毁SOP", 944, "TblFileContentSop", record.getFileRecordId());
				record.setDestoryRegSign(esLinkId);
				
				tblFileRecordService.update(record);
				
				writeTblLog("TblFileRecord","destoryDate",record.getFileRecordSn()
						,DateUtil.dateToString(record.getDestoryDate(),"yyyy-MM-dd"),record.getFileRecordId(),"",
						operateRsn,operateTime,3);//1：修改；2：删除；3：销毁；4：SOP作废；5：合同终止
			}
			
			map.put("success", true);
		}else {
			map.put("success", false);
			map.put("msg", "销毁是对档案的");
		}

		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void invalidSOP()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		Date operateTime = new Date();
		
		TblFileContentSop contentSop0 = tblFileContentSopService.getById(model.getFileRecordId());
		List<TblFileContentSop> sopList = tblFileContentSopService.getBySopCode(contentSop0.getSopcode());
		for(TblFileContentSop contentSop:sopList)
		{
			contentSop.setSopinvalidDate(new Date());
			tblFileContentSopService.update(contentSop);
			
			String esLinkId = writeES("作废SOP", 945, "TblFileContentSop", contentSop.getFileRecordId());
			writeTblLog("TblFileContentSop","sopinvalidDate",contentSop.getTblFileRecord().getFileRecordSn()
					,""+0,model.getFileRecordId(),""+1,
					operateRsn,operateTime,4);//1：修改；2：删除；3：销毁；4：SOP作废；5：合同终止
		}
			
		map.put("success", true);

		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public void loadList()
	{
		putSearchConIntoSession(searchString, 2);
		
		//Integer isNowValid,Integer isInvalid,Date changeEndDate,Integer yearNum,
		Map<String, Object> resultMap = tblFileContentSopService.getByCondition(fileFlag,isAll,model.getSopflag(),isNowValid,isInvalid,needChange,changeEndDate,yearNum,yearNumUnit,fileStartDate,fileEndDate,keepEndDate,isDestory,isValid,searchString,page,rows);
		List<TblFileContentSop> fileStudys = (List<TblFileContentSop>)resultMap.get("rows");
		 
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		//获取parentId
		for(TblFileContentSop fileStudy:fileStudys)
		{
			boolean isExistP = false;
			for(Map<String, Object> parMap : mapList)
			{
				if(parMap.get("fileRecordId")!=null&&("typeId"+fileStudy.getSoptypeCode()).equals((String)parMap.get("fileRecordId")))
				{
					isExistP = true;
				}
			}
			if(!isExistP)
			{
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("fileRecordId", "typeId"+fileStudy.getSoptypeCode());
				map.put("soptypeCode", fileStudy.getSoptypeCode());
				map.put("soptypeName", fileStudy.getSoptypeName());
				map.put("sopcode", "");
				map.put("sopver", "");
				
				mapList.add(map);
			}
			
		}
		
		mapList =  getDictSOPList(mapList);
		for(TblFileContentSop fileStudy:fileStudys)
		{
			//Sopcode,SopeffectiveDate,SopinvalidDate,
			//Sopname,SoptypeCode,SoptypeName,Sopver
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("_parentId","typeId"+fileStudy.getSoptypeCode());
			
			map.put("archiveCode", fileStudy.getArchiveCode());
			map.put("fileRecordId", fileStudy.getFileRecordId());
			map.put("sopcode", fileStudy.getSopcode());
			map.put("sopeffectiveDate", DateUtil.dateToString(fileStudy.getSopeffectiveDate(),"yyyy-MM-dd"));
			map.put("sopinvalidDate", DateUtil.dateToString(fileStudy.getSopinvalidDate(),"yyyy-MM-dd"));
			if(fileStudy.getSopinvalidDate()!=null&&fileStudy.getSopinvalidDate().after(new Date()))
			{
				map.put("isInvalid", true);
			}else {
				map.put("isInvalid", false);
			}
			map.put("sopname", fileStudy.getSopname());
			map.put("soptypeCode", fileStudy.getSoptypeCode());
			map.put("soptypeName", fileStudy.getSoptypeName());
			map.put("sopver", fileStudy.getSopver());
			map.put("sopflag", fileStudy.getSopflag());
			if(fileStudy.getSopfile()!=null&&!"".equals(fileStudy.getSopfile()))
			{
				map.put("sopfile", fileStudy.getSopfileName());//
			}else {
				map.put("sopfile", "");
			}
			
			//map.put("archiveTypeCode", fileStudy.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
			//String archiveTypeCode = fileStudy.getTblFileRecord().getTblFileIndex().getArchiveTypeCode();
			//DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(fileStudy.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
			//if(dictArchiveType!=null)
			//{
			//	map.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
			//}
			
		//	map.put("archiveTitle", fileStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle());
		//	map.put("storePosition", fileStudy.getTblFileRecord().getTblFileIndex().getStorePosition());
		//	map.put("fileDate", DateUtil.dateToString(fileStudy.getTblFileRecord().getFileDate(),"yyyy-MM-dd"));
			map.put("fileRecordSn", fileStudy.getTblFileRecord().getFileRecordSn());
		//	map.put("archiveMaker", fileStudy.getTblFileRecord().getArchiveMaker());
		//	map.put("fileOperator", fileStudy.getTblFileRecord().getFileOperator());
		//	map.put("keepDate", DateUtil.dateToString(fileStudy.getTblFileRecord().getKeepDate(),"yyyy-MM-dd"));	
		//	map.put("remark", fileStudy.getTblFileRecord().getRemark());
			if(fileStudy.getTblFileRecord().getDestoryDate()!=null)
			{
				map.put("destoryDate", DateUtil.dateToString(fileStudy.getTblFileRecord().getDestoryDate(),"yyyy-MM-dd"));
			}
			if(fileStudy.getTblFileRecord().getTblFileIndex()!=null)
			{
				map.put("validationFlag", fileStudy.getTblFileRecord().getTblFileIndex().getValidationFlag());//0：否；1：验证数据
			}else {
				map.put("validationFlag", 0);
			}
			
			map.put("archiveMediaFlag", fileStudy.getTblFileRecord().getArchiveMediaFlag());
			map.put("archiveMedia", fileStudy.getTblFileRecord().getArchiveMedia());
			
			map.put("delFlag", fileStudy.getTblFileRecord().getDelFlag());
			
			mapList.add(map);
		}
		resultMap.put("rows", mapList);
		writeJson(JsonPluginsUtil.beanToJson(resultMap));
	}
	
	public String downloadSOPFile() throws Exception{  
		if(null != model.getFileRecordId() && !"".equals(model.getFileRecordId())){
			TblFileContentSop sop = tblFileContentSopService.getById(model.getFileRecordId());
			
			if(sop!=null&&null != sop.getSopfile()&&!"".equals(sop.getSopfile())){
				fileInput = new ByteArrayInputStream(sop.getSopfile());
				downFileName = sop.getSopfileName();
				downFileName= java.net.URLEncoder.encode(downFileName,request.getCharacterEncoding());
				downFileName = new String(downFileName.getBytes(request.getCharacterEncoding()), "ISO_8859_1"); 
			}
		}

	    return "download2";  
	} 
	
	public void getAllvalidSopOrTbl()
	{
		List<TblFileContentSop>  rels = tblFileContentSopService.getAllvalidListByFlag(model.getSopflag());
		List<TblSopTableLinkInfo> existRel = null;
		Map<String, Object> existLinkMap = new HashMap<String, Object>();
		if(model.getSopflag()==null||model.getSopflag()==0)//获取的是有效的sop，所以是由表格查找的
		{
			 existRel = tblSopTableLinkInfoService.getByTblCodeAndTblVer(model.getSopcode(),model.getSopver());
			 for(TblSopTableLinkInfo info:existRel)
			 {
				 existLinkMap.put(info.getSopcode()+"~"+info.getSopver(), true);
			 }
		}else if(model.getSopflag()==1)//获取的是有效的表格，所以是由sop查找的
		{
			 existRel = tblSopTableLinkInfoService.getBySOPCodeAndSOPVer(model.getSopcode(),model.getSopver());
			 for(TblSopTableLinkInfo info:existRel)
			 {
				 existLinkMap.put(info.getTblCode()+"~"+info.getTblVer(), true);
			 }
		}
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();//结构
		List<Map<String, Object>> recordList = new ArrayList<Map<String,Object>>();//数据
		if(rels!=null)
		{
			for(TblFileContentSop fileStudy:rels)
			{
				if(existLinkMap.get(fileStudy.getSopcode()+"~"+fileStudy.getSopver())==null)//关系不存在
				{	
					// children格式
					//List<Map<String, Object>> childList = null;
					Map<String, Object> existTypeMap = null;
					for(Map<String, Object> typeMap0:mapList)
					{
						if(fileStudy.getSoptypeCode().equals(typeMap0.get("soptypeCode")))
						{
							existTypeMap = typeMap0;
						}
					}
					
					if(existTypeMap!=null){
						//childList = (List<Map<String, Object>> )existTypeMap.get("children");
					}else {
					//	childList = new ArrayList<Map<String,Object>>();
						Map<String, Object> typeMap=new HashMap<String, Object>();
						//typeMap.put("children",childList);
						typeMap.put("fileRecordId", "typeId"+fileStudy.getSoptypeCode());
						typeMap.put("soptypeCode", fileStudy.getSoptypeCode());
						typeMap.put("soptypeName", fileStudy.getSoptypeName());
						typeMap.put("sopcode", "");
						typeMap.put("sopver", "");
						
						mapList.add(typeMap);
					}
						
					
					//Sopcode,SopeffectiveDate,SopinvalidDate,
					//Sopname,SoptypeCode,SoptypeName,Sopver
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("_parentId","typeId"+fileStudy.getSoptypeCode());
					
					map.put("archiveCode", fileStudy.getArchiveCode());
					map.put("fileRecordId", fileStudy.getFileRecordId());
					map.put("sopcode", fileStudy.getSopcode());
					map.put("sopeffectiveDate", DateUtil.dateToString(fileStudy.getSopeffectiveDate(),"yyyy-MM-dd"));
					map.put("sopinvalidDate", DateUtil.dateToString(fileStudy.getSopinvalidDate(),"yyyy-MM-dd"));
					if(fileStudy.getSopinvalidDate()!=null&&fileStudy.getSopinvalidDate().after(new Date()))
					{
						map.put("isInvalid", true);
					}else {
						map.put("isInvalid", false);
					}
					map.put("sopname", fileStudy.getSopname());
					map.put("soptypeCode", fileStudy.getSoptypeCode());
					map.put("soptypeName", fileStudy.getSoptypeName());
					//map.put("soptypeCode", "");
					//map.put("soptypeName", "");
					map.put("sopver", fileStudy.getSopver());
					map.put("sopflag", fileStudy.getSopflag());
					
					//map.put("archiveTypeCode", fileStudy.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
					
				//	DictArchiveType dictArchiveType = dictArchiveTypeService.getByArchiveTypeCode(fileStudy.getTblFileRecord().getTblFileIndex().getArchiveTypeCode());
				//	map.put("archiveTypeName", dictArchiveType.getArchiveTypeName());
					
				//	map.put("archiveTitle", fileStudy.getTblFileRecord().getTblFileIndex().getArchiveTitle());
				//	map.put("storePosition", fileStudy.getTblFileRecord().getTblFileIndex().getStorePosition());
				//	map.put("fileDate", DateUtil.dateToString(fileStudy.getTblFileRecord().getFileDate(),"yyyy-MM-dd"));
					map.put("fileRecordSn", fileStudy.getTblFileRecord().getFileRecordSn());
				//	map.put("archiveMaker", fileStudy.getTblFileRecord().getArchiveMaker());
				//	map.put("fileOperator", fileStudy.getTblFileRecord().getFileOperator());
				//	map.put("keepDate", DateUtil.dateToString(fileStudy.getTblFileRecord().getKeepDate(),"yyyy-MM-dd"));	
				//	map.put("remark", fileStudy.getTblFileRecord().getRemark());
					if(fileStudy.getTblFileRecord().getDestoryDate()!=null)
					{
						map.put("destoryDate", DateUtil.dateToString(fileStudy.getTblFileRecord().getDestoryDate(),"yyyy-MM-dd"));
					}
					if(fileStudy.getTblFileRecord().getTblFileIndex()!=null)
					{
						map.put("validationFlag", fileStudy.getTblFileRecord().getTblFileIndex().getValidationFlag());//0：否；1：验证数据
					}else{
						map.put("validationFlag", 0);//0：否；1：验证数据
					}
					
					map.put("archiveMediaFlag", fileStudy.getTblFileRecord().getArchiveMediaFlag());
					map.put("archiveMedia", fileStudy.getTblFileRecord().getArchiveMedia());
					
					map.put("delFlag", fileStudy.getTblFileRecord().getDelFlag());
					
					recordList.add(map);
				}
			}
			
			mapList = getDictSOPList(mapList);
			mapList.addAll(recordList);
			
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("rows", mapList);
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	//-------------以下是关联-----------
	public void saveTblSopTableLinkList()
	{
		String[] codeListStrings = codes.split(",");
		if(model.getSopflag()==null||model.getSopflag()==0)
		{
			for(String str:codeListStrings)
			{
				if(str.contains("~"))
				{
					TblSopTableLinkInfo info = new TblSopTableLinkInfo();
					String key = tblSopTableLinkInfoService.getKey("TblSopTableLinkInfo");
					info.setId(key);
					info.setSopcode(model.getSopcode());
					info.setSopver(model.getSopver());
					info.setTblCode(str.split("~")[0]);
					info.setTblVer(str.split("~")[1]);
					
					tblSopTableLinkInfoService.save(info);
				}
			}
			
		}else if(model.getSopflag()==1){
			
			for(String str:codeListStrings)
			{
				if(str.contains("~"))
				{
					TblSopTableLinkInfo info = new TblSopTableLinkInfo();
					String key = tblSopTableLinkInfoService.getKey("TblSopTableLinkInfo");
					info.setId(key);
					info.setTblCode(model.getSopcode());
					info.setTblVer(model.getSopver());
					info.setSopcode(str.split("~")[0]);
					info.setSopver(str.split("~")[1]);
					
					tblSopTableLinkInfoService.save(info);
				}
			}
		}
		
		
	}
	
	public void delOneSopTableLink()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		TblSopTableLinkInfo info = tblSopTableLinkInfoService.getById(id);
		tblSopTableLinkInfoService.delete(id);
		
		String desc = "删除了一个SOP与SOP表格 的关联";
		writeES(desc, 937, "TblSopTableLinkInfo", id);
		
		map.put("success", true);
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public List<Map<String, Object>> getDictSOPList(List<Map<String, Object>> mapList)
	{
		Map<String, DictSoptype> dictTypeMap = new HashMap<String, DictSoptype>();
		Map<String, DictSoptype> dictTypeMap2 = new HashMap<String, DictSoptype>();
		
		List<DictSoptype> list = dictSOPTypeService.findAll();
		for(DictSoptype type:list)
		{
			dictTypeMap.put(type.getSoptypeCode(), type);
			dictTypeMap2.put(type.getId(), type);
		}
		
		List<Map<String, Object>> needAddSopTypeList = new ArrayList<Map<String,Object>>();
		
			for(Map<String, Object> temp2:mapList)
			{
				DictSoptype dictSoptype = dictTypeMap.get(temp2.get("soptypeCode"));
				if(dictSoptype!=null)
				{
					while(dictSoptype.getPid()!=null&&!"".equals(dictSoptype.getPid()))
					{
						DictSoptype dictSoptypeParent = dictTypeMap2.get(dictSoptype.getPid());
						//查看pid是否存在
						boolean isExistP = false;
						for(Map<String, Object> temp3:mapList)
						{
							if(dictSoptypeParent.getSoptypeCode().equals(temp3.get("soptypeCode")))
							{
								isExistP = true;
								temp2.put("_parentId", "typeId"+dictSoptypeParent.getSoptypeCode());
								
								temp2 = temp3;
								
								break;
							}
						}
						if(!isExistP)
						{
							for(Map<String, Object> temp3:needAddSopTypeList)
							{
								if(dictSoptypeParent.getSoptypeCode().equals(temp3.get("soptypeCode")))
								{
									isExistP = true;
									temp2.put("_parentId", "typeId"+dictSoptypeParent.getSoptypeCode());
									
									temp2 = temp3;
									
									break;
									
								}
							}
							
						}
						if(!isExistP){
							Map<String, Object> mapP = new HashMap<String, Object>();
							
							mapP.put("fileRecordId", "typeId"+dictSoptypeParent.getSoptypeCode());
							mapP.put("soptypeCode", dictSoptypeParent.getSoptypeCode());
							mapP.put("soptypeName", dictSoptypeParent.getSopname());
							
							mapP.put("sopcode", "");
							mapP.put("sopver", "");
							
							temp2.put("_parentId", "typeId"+dictSoptypeParent.getSoptypeCode());
							
							needAddSopTypeList.add(0,mapP);

							//寻找父类的父类
							temp2 = mapP;
						}
						//寻找父类的父类
						dictSoptype = dictSoptypeParent;
						
					}
				}
			}
			
		
		mapList.addAll(0,needAddSopTypeList);
		//排序mapList
		List<Map<String, Object>> sortMapList = new ArrayList<Map<String,Object>>();
		while(sortMapList.size()!=mapList.size())
		{
			for(Map<String, Object> one:mapList)
			{
				boolean isExist = false;
				if(one.get("_parentId")==null||"".equals(one.get("_parentId")))//父类不存在
				{
					boolean isExistInList = false;
					for(Map<String, Object> existOne:sortMapList)
					{
						if(one.get("fileRecordId").equals(existOne.get("fileRecordId")))
						{
							isExistInList = true;
							break;
						}
						
					}
					if(!isExistInList)
					{
						sortMapList.add(one);	
					}
						
				}else{//父类存在
					for(Map<String, Object> sortOne:sortMapList)
					{
						if(one.get("_parentId").equals(sortOne.get("fileRecordId")))
						{
							isExist = true;
							break;
						}
						
					}
					if(isExist)//父类存在直接加，如果不存在就下一轮
					{
						boolean isExistInList = false;
						for(Map<String, Object> existOne:sortMapList)
						{
							if(one.get("fileRecordId").equals(existOne.get("fileRecordId")))
							{
								isExistInList = true;
								break;
							}
							
						}
						if(!isExistInList)
						{
							sortMapList.add(one);	
						}
					}
				}
			}
		}
		
		return sortMapList;
	}
	
	
	public void loadRelationSOPList()
	{
		List<Map<String, Object>> rels = new ArrayList<Map<String, Object>>();
		
		if(model.getSopflag()==null||model.getSopflag()==0)
		{
			rels = tblSopTableLinkInfoService.getListBySOPCodeAndSOPVer(model.getSopcode(), model.getSopver());
		}else if(model.getSopflag()==1){
			rels = tblSopTableLinkInfoService.getListByTblCodeAndTblVer(model.getSopcode(), model.getSopver());
		}
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		for(Map<String, Object> map :rels)
		{
			if(map.get("sopeffectiveDate")!=null&&!"".equals(map.get("sopeffectiveDate")))
				map.put("sopeffectiveDate", DateUtil.dateToString((Date)map.get("sopeffectiveDate"), "yyyy-MM-dd"));
			if(map.get("sopinvalidDate")!=null&&!"".equals(map.get("sopinvalidDate")))
				map.put("sopinvalidDate", DateUtil.dateToString((Date)map.get("sopinvalidDate"), "yyyy-MM-dd"));
			if(map.get("fileDate")!=null&&!"".equals(map.get("fileDate")))
				map.put("fileDate", DateUtil.dateToString((Date)map.get("fileDate"), "yyyy-MM-dd"));
			if(map.get("keepDate")!=null&&!"".equals(map.get("keepDate")))
				map.put("keepDate", DateUtil.dateToString((Date)map.get("keepDate"), "yyyy-MM-dd"));
			if(map.get("destoryDate")!=null&&!"".equals(map.get("destoryDate")))
				map.put("destoryDate", DateUtil.dateToString((Date)map.get("destoryDate"), "yyyy-MM-dd"));
			
			//获取parentId
			
				boolean isExistP = false;
				for(Map<String, Object> parMap : mapList)
				{
					if(parMap.get("fileRecordId")!=null&&("typeId"+map.get("soptypeCode")).equals((String)parMap.get("fileRecordId")))
					{
						isExistP = true;
					}
				}
				if(!isExistP)
				{
					Map<String, Object> mapP = new HashMap<String, Object>();
					
					mapP.put("fileRecordId", "typeId"+map.get("soptypeCode"));
					mapP.put("soptypeCode", map.get("soptypeCode"));
					mapP.put("soptypeName", map.get("soptypeName"));
					mapP.put("sopcode", "");
					mapP.put("sopver", "");
					
					mapList.add(mapP);
				}
				
		}
		
		mapList = getDictSOPList(mapList);
		for(Map<String, Object> map :rels)
		{
			map.put("_parentId","typeId"+map.get("soptypeCode"));
		}
		//父类的内容加在前面
		rels.addAll(0,mapList);
		
		
		/*String[] noryFormat = {"sopver","sopcode","archiveCode","fileRecordId","sopeffectiveDate","sopinvalidDate",
				"isInvalid","sopname","soptypeCode","soptypeName","sopflag","archiveTypeCode","archiveTypeName",
				"archiveTitle","storePosition","fileDate","fileRecordSn","archiveMaker","fileOperator","keepDate"
				,"remark","destoryDate","validationFlag","archiveMediaFlag","archiveMedia","delFlag"};*/
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("rows",rels);
		
		
		writeJson(JsonPluginsUtil.beanToJson(resultMap));
		
	}
	
	//
	public void writeTblLog(String tableName,String fieldName,Integer fileRecordSn
			,String newValue,String oldFileRecordId,String oldValue,String operateRsn,Date operateTime,Integer operateTypeFlag)//操作日志
	{
		TblLog tblLog = new TblLog();
		//tblLog.setArchiveCode(archiveCode);
		//tblLog.setArchiveTitle(archiveTitle);
		tblLog.setArchiveTypeFlag(3);//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
		DictDataTable dictDataTable = dictDataTableService.getByTableNameAndField(tableName,fieldName);
		if(dictDataTable!=null)
		{
			tblLog.setFieldDesc(dictDataTable.getFieldDesc());
		}else {
			
		}
		tblLog.setFieldName(fieldName);
		//tblLog.setFileRecordSn(fileRecordSn);
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
	public String getOperateRsn() {
		return operateRsn;
	}
	public void setOperateRsn(String operateRsn) {
		this.operateRsn = operateRsn;
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
	public Integer getIsNowValid() {
		return isNowValid;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public void setIsNowValid(Integer isNowValid) {
		this.isNowValid = isNowValid;
	}
	public Integer getIsInvalid() {
		return isInvalid;
	}
	public void setIsInvalid(Integer isInvalid) {
		this.isInvalid = isInvalid;
	}
	public Integer getNeedChange() {
		return needChange;
	}
	public void setNeedChange(Integer needChange) {
		this.needChange = needChange;
	}
	public Date getChangeEndDate() {
		return changeEndDate;
	}
	public void setChangeEndDate(Date changeEndDate) {
		this.changeEndDate = changeEndDate;
	}
	public Integer getYearNum() {
		return yearNum;
	}
	public void setYearNum(Integer yearNum) {
		this.yearNum = yearNum;
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
	public Integer getIsAll() {
		return isAll;
	}
	public void setIsAll(Integer isAll) {
		this.isAll = isAll;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getYearNumUnit() {
		return yearNumUnit;
	}
	public void setYearNumUnit(Integer yearNumUnit) {
		this.yearNumUnit = yearNumUnit;
	}
	public Date getDestoryDate() {
		return destoryDate;
	}
	public void setDestoryDate(Date destoryDate) {
		this.destoryDate = destoryDate;
	}
	public String getCodes() {
		return codes;
	}
	public void setCodes(String codes) {
		this.codes = codes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public File getSopfile2() {
		return sopfile2;
	}
	public void setSopfile2(File file) {
		this.sopfile2 = file;
	}
	public InputStream getFileInput() {
		return fileInput;
	}
	public void setFileInput(InputStream fileInput) {
		this.fileInput = fileInput;
	}
	public String getSopfile2FileName() {
		return sopfile2FileName;
	}
	public void setSopfile2FileName(String fileName) {
		this.sopfile2FileName = fileName;
	}
	public String getDownFileName() {
		return downFileName;
	}
	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}
	public Integer getFileFlag() {
		return fileFlag;
	}
	public void setFileFlag(Integer fileFlag) {
		this.fileFlag = fileFlag;
	}

}
