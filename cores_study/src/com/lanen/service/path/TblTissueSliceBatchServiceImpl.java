package com.lanen.service.path;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.path.DictViscera;
import com.lanen.model.path.TblTissueSliceBatchAnimal;
import com.lanen.model.path.TblTissueSliceBatchExcluded;
import com.lanen.model.path.TblTissueSliceBatchIndex;
import com.lanen.model.path.TblTissueSliceBatchViscera;
import com.lanen.model.path.TblTissueSliceViscera;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;

@Service
@Transactional
public class TblTissueSliceBatchServiceImpl extends BaseDaoImpl<TblTissueSliceBatchIndex> implements TblTissueSliceBatchService{

	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private DictVisceraService dictVisceraService;
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnatomyTaskListByStudyNo(String studyNo) {
		String sql = "select task.taskId,convert(varchar(10),req.beginDate,120) as planAnatomyDate,COUNT(animal.taskId) as animalNum," +
					" task.anatomyRsn as reason"+
					" from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyReq as req"+
					" 	on task.studyNo = req.studyNo and task.anatomyReqNo = req.reqNo left join "+
					" 	CoresStudy.dbo.tblAnatomyAnimal as animal on task.taskId = animal.taskId"+
					" where task.studyNo = :studyNo and task.tempFlag != 2"+
					" group by task.taskId,req.beginDate,task.anatomyRsn";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter("studyNo", studyNo)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTaskAnimalMapListByStudyNo(
			String studyNo) {
		String sql = "select aa.taskId,aa.animalCode,aa.gender,ds.dosageDesc,"+
					" 	convert(varchar(10),aa.anatomyBeginTime,120) as anatomyDate"+
					" from CoresStudy.dbo.tblAnatomyAnimal as aa left join CoresStudy.dbo.tblDoseSetting as ds"+
					" 	on aa.studyNo = ds.studyNo and aa.groupId = ds.dosageNum"+
					" where aa.studyNo = :studyNo and aa.autolyzeFlag = 0"+
					" order by animalCode";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter("studyNo", studyNo)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDosageAnimalMapListByStudyNo(
			String studyNo) {
		String sql = "select ad.animalCode,ad.gender,ad.groupId as dosageNum ,ds.dosageDesc,"+
		" 	convert(varchar(10),aa.anatomyBeginTime,120) as anatomyDate"+
		" from CoresStudy.dbo.tblAnimalDetailDissectPlan as ad left join CoresStudy.dbo.tblDoseSetting as ds"+
		" 	on ad.studyNo = ds.studyNo and ad.groupId = ds.dosageNum	left join "+
		" 	CoresStudy.dbo.tblAnatomyAnimal as aa on ad.studyNo = aa.studyNo and ad.animalCode = aa.animalCode"+
		" where ad.studyNo = :studyNo " +
		" order by ad.groupId ,ad.animalCode ";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
													.setParameter("studyNo", studyNo)
													.setResultTransformer(new MapResultTransformer())
													.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDosageMapListByStudyNo(String studyNo) {
		String sql = "select ds.dosageNum,ds.maleNum,ds.femaleNum"+
					" from CoresStudy.dbo.tblDoseSetting as ds"+
					" where ds.studyNo = :studyNo"+
					" order by ds.dosageNum";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
													.setParameter("studyNo", studyNo)
													.setResultTransformer(new MapResultTransformer())
													.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCGSliceCodeVisceraMapListByStudyNo(
			String studyNo) {
		String sql = "select scode.tissueSliceVisceraId,scode.sliceCode, "+
					" 	case when isnull(scode.subVisceraCode,'') = '' then scode.visceraName"+
					" 		else scode.subVisceraName end  as visceraOrTissueName, " +
					" 	scode.visceraCode,scode.visceraName,scode.subVisceraCode,scode.subVisceraName"+
					" from CoresStudy.dbo.tissueSliceCode_IndexSnViscera(:studyNo) as scode"+
					" where scode.sliceCodeType = 0" +
					" order by cast(scode.sliceCode as int )";
			List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
													.setParameter("studyNo", studyNo)
													.setResultTransformer(new MapResultTransformer())
													.list();
			return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getFCGSliceCodeVisceraMapList(
			String studyNo, List<String> animalCodeList) {
		String sql = "select scode.tissueSliceVisceraId,scode.sliceCode, "+
					" 	(case when isnull(scode.visceraCode,'')= '' then ''  "+
					" 			when isnull(scode.subVisceraCode,'') = '' then scode.visceraName+' '"+
					" 			when isnull(scode.subVisceraCode,'') != '' then scode.subVisceraName+' ' "+
					" 		else '' end "+
					" 			+case when isnull(scode.bodySurfacePos,'') = '' then '' else (scode.bodySurfacePos+' ') end"+
					" 		+case when isnull(scode.anatomyPos,'') = '' then '' else (scode.anatomyPos+' ') end"+
					" 		+case when isnull(scode.anatomyFingding,'') = '' then '' else (scode.anatomyFingding+' ') end"+
					" 	)   as visceraOrTissueName,"+
					" 		scode.visceraCode,scode.visceraName,scode.subVisceraCode,scode.subVisceraName"+
					" from CoresStudy.dbo.tissueSliceCode_IndexSnViscera(:studyNo) as scode"+
					" where scode.sliceCodeType = 1 and isnull(scode.operatorSign,'') != '' and scode.animalCode in (:animalCodeList)";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
												.setParameter("studyNo", studyNo)
												.setParameterList("animalCodeList", animalCodeList)
												.setResultTransformer(new MapResultTransformer())
												.list();
		return mapList;
	}

	public TblTissueSliceBatchIndex saveOne(
			TblTissueSliceBatchIndex tblTissueSliceBatchIndex,
			List<String> animalCodeList, List<String> tissueSliceVisceraIdList,
			List<TblTissueSliceBatchExcluded> batchExcludedList) {
		String batchId = getKey();
		String studyNo = tblTissueSliceBatchIndex.getStudyNo();
		int batchSn = getBatchSnByStudyNo(studyNo);
		Date createTime = new Date();
		//1.索引
		tblTissueSliceBatchIndex.setId(batchId);
		tblTissueSliceBatchIndex.setBatchSn(batchSn);
		tblTissueSliceBatchIndex.setCreateTime(createTime);
		
		//2.动物
		List<TblTissueSliceBatchAnimal> tblTissueSliceBacthAnimalList = new ArrayList<TblTissueSliceBatchAnimal>();
		TblTissueSliceBatchAnimal tblTissueSliceBatchAnimal = null;
		for(String animalCode :animalCodeList){
			tblTissueSliceBatchAnimal = new TblTissueSliceBatchAnimal();
			tblTissueSliceBatchAnimal.setId(getKey("TblTissueSliceBatchAnimal"));
			tblTissueSliceBatchAnimal.setBatchId(batchId);
			tblTissueSliceBatchAnimal.setBatchSn(batchSn);
			tblTissueSliceBatchAnimal.setAnimalCode(animalCode);
			
			tblTissueSliceBacthAnimalList.add(tblTissueSliceBatchAnimal);
		}
		//3.脏器或组织
		List<TblTissueSliceBatchViscera> tblTissueSliceBatchVisceraList = new ArrayList<TblTissueSliceBatchViscera>();
		TblTissueSliceBatchViscera tblTissueSliceBatchViscera = null;
		for(String tissueSliceVisceraId:tissueSliceVisceraIdList){
			tblTissueSliceBatchViscera = new TblTissueSliceBatchViscera();
			tblTissueSliceBatchViscera.setId(getKey("TblTissueSliceBatchViscera"));
			tblTissueSliceBatchViscera.setBatchId(batchId);
			tblTissueSliceBatchViscera.setBatchSn(batchSn);
			tblTissueSliceBatchViscera.setTissueSliceVisceraId(tissueSliceVisceraId);
			
			tblTissueSliceBatchVisceraList.add(tblTissueSliceBatchViscera);
		}
		
		//4.排除的动物脏器
//		List<TblTissueSliceBatchExcluded> batchExcludedList
		if(batchExcludedList.size() > 0){
			for(TblTissueSliceBatchExcluded obj:batchExcludedList){
				obj.setId(getKey("TblTissueSliceBatchExcluded"));
				obj.setBatchId(batchId);
				obj.setBatchSn(batchSn);
				obj.setStudyNo(studyNo);
			}
		}
		
		//5.保存
		//5.1索引
		getSession().save(tblTissueSliceBatchIndex);
		//5.2动物
		for(TblTissueSliceBatchAnimal obj:tblTissueSliceBacthAnimalList){
			getSession().save(obj);
		}
		//5.3脏器或组织
		for(TblTissueSliceBatchViscera obj:tblTissueSliceBatchVisceraList){
			getSession().save(obj);
		}
		//5.4排除的动物脏器
		if(batchExcludedList.size() > 0){
			for(TblTissueSliceBatchExcluded obj:batchExcludedList){
				getSession().save(obj);
			}
		}
		
		
		return tblTissueSliceBatchIndex;
	}

	private int getBatchSnByStudyNo(String studyNo) {
		String sql = "select max(batchSn)"+
					" from CoresStudy.dbo.tblTissueSliceBatchIndex "+
					" where studyNo = ? ";
		Integer maxBatchSn = (Integer) getSession().createSQLQuery(sql)
													.setParameter(0, studyNo)
													.uniqueResult();
		if(null != maxBatchSn){
			return maxBatchSn+1;
		}else{
			return 1;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getZJSliceCodeVisceraMapListByStudyNo(
			String studyNo) {
		String sql = "select scode.tissueSliceVisceraId,scode.sliceCode, "+
					" 	case when isnull(scode.subVisceraCode,'') = '' then scode.visceraName"+
					" 		else scode.subVisceraName end  as visceraOrTissueName, " +
					" 	scode.visceraCode,scode.visceraName,scode.subVisceraCode,scode.subVisceraName"+
					" from CoresStudy.dbo.tissueSliceCode_IndexSnViscera(:studyNo) as scode"+
					" where scode.sliceCodeType = 2 and isnull(scode.operatorSign,'') != '' ";
			List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
													.setParameter("studyNo", studyNo)
													.setResultTransformer(new MapResultTransformer())
													.list();
			return mapList;
	}


	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getBatchIndexMapListByStudyNo(
			String studyNo) {
		String sql = "select sb.id as batchId,sb.batchSn,sb.sliceType,es.signer as operator,"+
					" 	convert(varchar(10),sb.createTime,120) as createTime"+
					" from CoresStudy.dbo.tblTissueSliceBatchIndex as sb left join CoresUserPrivilege.dbo.tblES as es"+
					" 	on sb.operatorSign = es.esId "+
					" where sb.studyNo = :studyNo" +
					" order by sb.id";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
												.setParameter("studyNo", studyNo)
												.setResultTransformer(new MapResultTransformer())
												.list();
		return mapList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getBatchAnimalMapListByBatchId(
			String batchId) {
		String sql = "select ba.animalCode,bi.studyNo,ad.gender,ad.groupId as dosageNum ,ds.dosageDesc,"+
		" 		convert(varchar(10),aa.anatomyBeginTime,120) as anatomyDate"+
		" from CoresStudy.dbo.tblTissueSliceBatchAnimal as ba left join CoresStudy.dbo.tblTissueSliceBatchIndex as bi"+
		" 	on ba.batchId = bi.id left join CoresStudy.dbo.tblAnimalDetailDissectPlan as ad on bi.studyNo = ad.studyNo"+
		" 	and ba.animalCode = ad.animalCode left join CoresStudy.dbo.tblDoseSetting as ds"+
		" 	on ad.studyNo = ds.studyNo and ad.groupId = ds.dosageNum	left join "+
		" 	CoresStudy.dbo.tblAnatomyAnimal as aa on ad.studyNo = aa.studyNo and ad.animalCode = aa.animalCode"+
		" where ba.batchId = :batchId ";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
												.setParameter("batchId", batchId)
												.setResultTransformer(new MapResultTransformer())
												.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getBatchVisceraMapListByBatchId(
			String batchId) {
		String sql = "select (case when isnull(sv.visceraCode,'')= '' then ''  "+
					"  			when isnull(sv.subVisceraCode,'') = '' then sv.visceraName+' '"+
					" 			when isnull(sv.subVisceraCode,'') != '' then sv.subVisceraName+' ' "+
					" 			else '' end "+
					" 			+case when isnull(sv.bodySurfacePos,'') = '' then '' else (sv.bodySurfacePos+' ') end"+
					" 			+case when isnull(sv.anatomyPos,'') = '' then '' else (sv.anatomyPos+' ') end"+
					" 			+case when isnull(sv.anatomyFingding,'') = '' then '' else (sv.anatomyFingding+' ') end"+
					" 	)   as visceraOrTissueName,"+
					" ss.sliceCode,bv.tissueSliceVisceraId"+
					" from CoresStudy.dbo.tblTissueSliceBatchViscera as bv left join CoresStudy.dbo.tblTissueSliceViscera as sv"+
					" 	on bv.tissueSliceVisceraId = sv.id left join CoresStudy.dbo.tblTissueSliceSn as  ss"+
					" on sv.tissueSliceSnId = ss.id"+
					" where bv.batchId = :batchId ";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
												.setParameter("batchId", batchId)
												.setResultTransformer(new MapResultTransformer())
												.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getBatchExcludedMapListByBatchId(
			String batchId) {
		String sql = "select be.animalCode,be.reason as reason,be.tissueSliceVisceraId,"+
					" 	be.visceraType,be.visceraCode,be.visceraName,be.subVisceraCode,be.subVisceraName "+
					" 	"+
					" from CoresStudy.dbo.tblTissueSliceBatchExcluded as be left join CoresStudy.dbo.tblTissueSliceViscera "+
					" 	as sv on be.tissueSliceVisceraId = sv.id"+
					" where be.batchId = :batchId";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
												.setParameter("batchId", batchId)
												.setResultTransformer(new MapResultTransformer())
												.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMissViscera(String studyNo,
			List<String> animalCodeList) {
		String sql = "select ac.animalCode,ac.visceraType,ac.visceraCode,ac.visceraName,ac.subVisceraCode,ac.subVisceraName,"+
					" 	case ac.autolyzaFlag when 1 then '自溶' when 2 then '缺如' else ''end as reason"+
					" from CoresStudy.dbo.TblAnatomyCheck as ac"+
					" where ac.studyNo = :studyNo and ac.animalCode in (:animalCodeList)"+
					" 	and ac.autolyzaFlag > 0"+
					" union"+
					" select vm.animalCode,vm.visceraType,vm.visceraCode,vm.visceraName,vm.subVisceraCode,vm.subVisceraName,"+
					" 	'缺失' as reason"+
					" from CoresStudy.dbo.tblVisceraMissing as vm"+
					" where vm.studyNo = :studyNo and vm.animalCode in (:animalCodeList)";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
											.setParameter("studyNo", studyNo)
											.setParameterList("animalCodeList", animalCodeList)
											.setResultTransformer(new MapResultTransformer())
											.list();
		return mapList;
	}

	public TblTissueSliceBatchIndex updateOne(
			TblTissueSliceBatchIndex tblTissueSliceBatchIndex,
			List<String> animalCodeList, List<String> tissueSliceVisceraIdList,
			List<TblTissueSliceBatchExcluded> batchExcludedList) {
		String batchId = tblTissueSliceBatchIndex.getId();
		String studyNo = tblTissueSliceBatchIndex.getStudyNo();
		int batchSn = tblTissueSliceBatchIndex.getBatchSn();
		
		//1.动物
		List<TblTissueSliceBatchAnimal> tblTissueSliceBacthAnimalList = new ArrayList<TblTissueSliceBatchAnimal>();
		TblTissueSliceBatchAnimal tblTissueSliceBatchAnimal = null;
		for(String animalCode :animalCodeList){
			tblTissueSliceBatchAnimal = new TblTissueSliceBatchAnimal();
			tblTissueSliceBatchAnimal.setId(getKey("TblTissueSliceBatchAnimal"));
			tblTissueSliceBatchAnimal.setBatchId(batchId);
			tblTissueSliceBatchAnimal.setBatchSn(batchSn);
			tblTissueSliceBatchAnimal.setAnimalCode(animalCode);
			
			tblTissueSliceBacthAnimalList.add(tblTissueSliceBatchAnimal);
		}
		//2.脏器或组织
		List<TblTissueSliceBatchViscera> tblTissueSliceBatchVisceraList = new ArrayList<TblTissueSliceBatchViscera>();
		TblTissueSliceBatchViscera tblTissueSliceBatchViscera = null;
		for(String tissueSliceVisceraId:tissueSliceVisceraIdList){
			tblTissueSliceBatchViscera = new TblTissueSliceBatchViscera();
			tblTissueSliceBatchViscera.setId(getKey("TblTissueSliceBatchViscera"));
			tblTissueSliceBatchViscera.setBatchId(batchId);
			tblTissueSliceBatchViscera.setBatchSn(batchSn);
			tblTissueSliceBatchViscera.setTissueSliceVisceraId(tissueSliceVisceraId);
			
			tblTissueSliceBatchVisceraList.add(tblTissueSliceBatchViscera);
		}
		
		//3.排除的动物脏器
		if(batchExcludedList.size() > 0){
			for(TblTissueSliceBatchExcluded obj:batchExcludedList){
				obj.setId(getKey("TblTissueSliceBatchExcluded"));
				obj.setBatchId(batchId);
				obj.setBatchSn(batchSn);
				obj.setStudyNo(studyNo);
			}
		}
		//4.先删除原批次动物、脏器、排除
		String delSQL_animal = "delete"+
								" from CoresStudy.dbo.tblTissueSliceBatchAnimal "+
								" where batchId = :batchId";
		String delSQL_viscera = "delete"+
								" from CoresStudy.dbo.tblTissueSliceBatchViscera "+
								" where batchId = :batchId";
		String delSQL_excluded = "delete"+
								" from CoresStudy.dbo.tblTissueSliceBatchExcluded "+
								" where batchId = :batchId";
		getSession().createSQLQuery(delSQL_animal).setParameter("batchId", batchId).executeUpdate();
		getSession().createSQLQuery(delSQL_viscera).setParameter("batchId", batchId).executeUpdate();
		getSession().createSQLQuery(delSQL_excluded).setParameter("batchId", batchId).executeUpdate();
		
		//5.保存
		//5.1索引
//		getSession().save(tblTissueSliceBatchIndex);
		//5.2动物
		for(TblTissueSliceBatchAnimal obj:tblTissueSliceBacthAnimalList){
			getSession().save(obj);
		}
		//5.3脏器或组织
		for(TblTissueSliceBatchViscera obj:tblTissueSliceBatchVisceraList){
			getSession().save(obj);
		}
		//5.4排除的动物脏器
		if(batchExcludedList.size() > 0){
			for(TblTissueSliceBatchExcluded obj:batchExcludedList){
				getSession().save(obj);
			}
		}
		
		
		return tblTissueSliceBatchIndex;
	}

	public TblTissueSliceBatchIndex signTissueSliceBatch(
			TblTissueSliceBatchIndex tblTissueSliceBatchIndex, String realName) {
		List<String> dataIdList = new ArrayList<String>();
		dataIdList.add(tblTissueSliceBatchIndex.getId());
		String operatorSign = writeES(realName,264,"组织取材签字确认",
				"TblTissueSliceBatchIndex",dataIdList);
		tblTissueSliceBatchIndex.setOperatorSign(operatorSign);
		getSession().update(tblTissueSliceBatchIndex);
		return tblTissueSliceBatchIndex;
	}
	/**签字
	 * @param siger
	 * @param esType
	 * @param esTypeDesc
	 * @param tableName
	 * @param dataIdList
	 * @return
	 */
	private String writeES(String siger,int esType,String esTypeDesc,String tableName,List<String> dataIdList){
		String esId = tblESService.getKey("TblES");
		Date date = new Date();
		
		TblES tblES=new TblES();
		tblES.setEsId(esId);
		tblES.setSigner(siger);
		tblES.setEsType(esType);
		tblES.setEsTypeDesc(esTypeDesc);
		tblES.setDateTime(date);
		tblESService.save(tblES);//保存  电子签名
		//签名链接表
		TblESLink tblESLink = null;
		for(String dataId :dataIdList){
			//签名链接表
			tblESLink =new TblESLink();
			tblESLink.setLinkId(tblESLinkService.getKey("TblESLink"));
			tblESLink.setDataId(dataId);
			tblESLink.setTableName(tableName);
			tblESLink.setEsType(esType);
			tblESLink.setEsTypeDesc(esTypeDesc);
			tblESLink.setRecordTime(date);
			tblESLink.setTblES(tblES);
			tblESLinkService.save(tblESLink);
		}
		
		return esId;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPrintMapList(String batchId,
			String studyNo) {
		String sql = "select distinct '00'+ba.animalCode as animalCode ,scode.sliceCode, ' ' as confirmFlag"+
					" from CoresStudy.dbo.tblTissueSliceBatchAnimal as ba left join CoresStudy.dbo.tblTissueSliceBatchViscera"+
					" 	as bv on ba.batchId = bv.batchId left join CoresStudy.dbo.tissueSliceCode_IndexSnViscera(:studyNo)"+
					" 	as scode on scode.tissueSliceVisceraId = bv.tissueSliceVisceraId and "+
					" 		(scode.sliceCodeType != 1 or scode.sliceCodeType = 1 and scode.animalCode = ba.animalCode)"+
					" 	left join CoresStudy.dbo.tblAnimalDetailDissectPlan as animal on animal.studyNo = :studyNo and "+
					" 	ba.animalCode = animal.animalCode left join CoresSystemSet.dbo.dictViscera as dv on scode.sliceCodeType != 1"+
					" 	and scode.visceraCode = dv.visceraCode and (animal.gender = dv.gender or dv.gender = 0) " +
					" left join CoresStudy.dbo.tblTissueSliceBatchExcluded as be on be.batchId = :batchId and be.tissueSliceVisceraId" +
					" 	= bv.tissueSliceVisceraId  and ba.animalCode = be.animalCode "+
					" where ba.batchId = :batchId and scode.sliceCode is not null and ( scode.sliceCodeType = 1 or dv.gender is not null)" +
					" and be.id is null ";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
										.setParameter("batchId", batchId)
										.setParameter("studyNo", studyNo)
										.setResultTransformer(new MapResultTransformer())
										.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<String> getPrintRemark(String batchId, String studyNo) {
		String sql = "select '['+be.animalCode+' '+scode.sliceCode+' '+"+
					" 	case when isnull(be.subVisceraCode,'') != '' then be.subVisceraName"+
					" 			else be.visceraName end"+
					" +']'"+
					" from CoresStudy.dbo.tblTissueSliceBatchExcluded as be left join " +
					" CoresStudy.dbo.tissueSliceCode_IndexSnViscera(:studyNo)"+
					" 	as scode on be.tissueSliceVisceraId = scode.tissueSliceVisceraId"+
					" where be.batchId =  :batchId ";
		List<String> strList = getSession().createSQLQuery(sql)
													.setParameter("batchId", batchId)
													.setParameter("studyNo", studyNo)
													.list();
		return strList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListByTissueSliceVisceraId(
			String tissueSliceVisceraId) {
		List<Map<String,Object>> mapList = null;
		String hql = "from TblTissueSliceViscera where id = ? ";
		List<TblTissueSliceViscera> list = getSession().createQuery(hql)
														.setParameter(0, tissueSliceVisceraId)
														.list();
		TblTissueSliceViscera tblTissueSliceViscera = null;
		if(null != list && list.size() > 0){
			tblTissueSliceViscera = list.get(0);
			mapList = new ArrayList<Map<String,Object>>();
			//第一个，
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("visceraType", tblTissueSliceViscera.getVisceraType());
			map.put("visceraCode", tblTissueSliceViscera.getVisceraCode());
			map.put("visceraName", tblTissueSliceViscera.getVisceraName());
			map.put("subVisceraCode", tblTissueSliceViscera.getSubVisceraCode());
			map.put("subVisceraName", tblTissueSliceViscera.getSubVisceraName());
			mapList.add(map);
			
			// 2.若是父节点，看是否有子节点，有的话增加
			
			if(null == tblTissueSliceViscera.getSubVisceraCode() || 
					"".equals(tblTissueSliceViscera.getSubVisceraCode().trim())){
				List<DictViscera> dictVisceraList = dictVisceraService.getListByParentVisceraCode(tblTissueSliceViscera.getVisceraCode());
				if(null != dictVisceraList && dictVisceraList.size() > 0){
					for(DictViscera obj:dictVisceraList){
						map = new HashMap<String,Object>();
						map.put("visceraType", tblTissueSliceViscera.getVisceraType());
						map.put("visceraCode", tblTissueSliceViscera.getVisceraCode());
						map.put("visceraName", tblTissueSliceViscera.getVisceraName());
						map.put("subVisceraCode", obj.getVisceraCode());
						map.put("subVisceraName", obj.getVisceraName());
						mapList.add(map);
					}
				}
			}
			
		}
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListByStudyNoAnimalCodeSliceVisceraId(
			String studyNo, String animalCode, String sliceVisceraId2) {
		List<Map<String,Object>> mapList = null;
		String hql = "from TblTissueSliceViscera where id = ? ";
		List<TblTissueSliceViscera> list = getSession().createQuery(hql)
														.setParameter(0, sliceVisceraId2)
														.list();	 
		TblTissueSliceViscera tblTissueSliceViscera = null;
		if(null != list && list.size() > 0){
			tblTissueSliceViscera = list.get(0);
			mapList = new ArrayList<Map<String,Object>>();
			//第一个，
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("visceraType", tblTissueSliceViscera.getVisceraType());
			map.put("visceraCode", tblTissueSliceViscera.getVisceraCode());
			map.put("visceraName", tblTissueSliceViscera.getVisceraName());
			map.put("subVisceraCode", tblTissueSliceViscera.getSubVisceraCode());
			map.put("subVisceraName", tblTissueSliceViscera.getSubVisceraName());
			boolean isexist = isExistByStudyNoAnimalCode(studyNo,animalCode,sliceVisceraId2,
					tblTissueSliceViscera.getVisceraCode(),tblTissueSliceViscera.getSubVisceraCode());
			if(!isexist){
				mapList.add(map);
			}
			
			// 2.若是父节点，看是否有子节点，有的话增加
			
			if(null == tblTissueSliceViscera.getSubVisceraCode() || 
					"".equals(tblTissueSliceViscera.getSubVisceraCode().trim())){
				List<DictViscera> dictVisceraList = dictVisceraService.getListByParentVisceraCode(tblTissueSliceViscera.getVisceraCode());
				if(null != dictVisceraList && dictVisceraList.size() > 0){
					for(DictViscera obj:dictVisceraList){
						map = new HashMap<String,Object>();
						map.put("visceraType", tblTissueSliceViscera.getVisceraType());
						map.put("visceraCode", tblTissueSliceViscera.getVisceraCode());
						map.put("visceraName", tblTissueSliceViscera.getVisceraName());
						map.put("subVisceraCode", obj.getVisceraCode());
						map.put("subVisceraName", obj.getVisceraName());
						
						isexist = isExistByStudyNoAnimalCode(studyNo,animalCode,sliceVisceraId2,
								tblTissueSliceViscera.getVisceraCode(),obj.getVisceraCode());
						if(!isexist){
							mapList.add(map);
						}
//						mapList.add(map);
					}
				}
			}
			
		}
		return mapList;
	}
	
	/**判断排除表中是否存在
	 * @return
	 */
	boolean isExistByStudyNoAnimalCode(String studyNo,String animalCode
			,String tissueSliceVisceraId,String visceraCode,String subVisceraCode){
		String sql = " select count(id)"+
					" from CoresStudy.dbo.tblTissueSliceBatchExcluded as be"+
					" where be.studyNo = :studyNo and be.animalCode = :animalCode and be.tissueSliceVisceraId = :tissueSliceVisceraId "+
					" 	and ISNULL(be.visceraCode,'') = :visceraCode and ISNULL(be.subVisceraCode,'') = :subVisceraCode ";
		Integer count = (Integer) getSession().createSQLQuery(sql)
									.setParameter("studyNo", studyNo)
									.setParameter("animalCode", animalCode)
									.setParameter("tissueSliceVisceraId", tissueSliceVisceraId)
									.setParameter("visceraCode", visceraCode != null ? visceraCode.trim() :"")
									.setParameter("subVisceraCode", subVisceraCode != null ? subVisceraCode.trim() :"")
									.uniqueResult();
		return null != count && count > 0 ;
	}
}

