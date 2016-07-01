package com.lanen.service.path;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.path.TblHistopathCheckContentAnimal;
import com.lanen.model.path.TblHistopathCheckContentIndex;
import com.lanen.model.path.TblHistopathCheckContentSlice;

@Service
@Transactional
public class TblHistopathCheckContentServiceImpl extends BaseDaoImpl<TblHistopathCheckContentIndex> 
	implements TblHistopathCheckContentService{

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDissectPlanAnimalMapListByStudyNo(
			String studyNo) {
		String sql = "select ad.animalCode,ad.gender,ad.groupId as dosageNum ,ds.dosageDesc,ad.dissectNum,"+
		" 	convert(varchar(10),aa.anatomyBeginTime,120) as anatomyDate"+
		" from CoresStudy.dbo.tblAnimalDetailDissectPlan as ad left join CoresStudy.dbo.tblDoseSetting as ds"+
		" 	on ad.studyNo = ds.studyNo and ad.groupId = ds.dosageNum	left join "+
		" 	CoresStudy.dbo.tblAnatomyAnimal as aa on ad.studyNo = aa.studyNo and ad.animalCode = aa.animalCode"+
		" where ad.studyNo = :studyNo " +
		" order by ad.animalCode ";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
													.setParameter("studyNo", studyNo)
													.setResultTransformer(new MapResultTransformer())
													.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDissectPlanListByStudyNo(String studyNo) {
		String sql = "select dp.dissectNum,convert(varchar(10),dp.beginDate,120) as beginDate,"+
					" 	convert(varchar(10),dp.endDate,120) as endDate,a.dissectNum,a.animalNum"+
					" from CoresStudy.dbo.tblDissectPlan as dp left join "+
					" 	("+
					" 	select studyNo,dissectNum,count(dissectNum) as animalNum"+
					" 	from CoresStudy.dbo.tblAnimalDetailDissectPlan "+
					" 	where studyNo = :studyNo and dissectNum > 0"+
					" 	group by studyNo,dissectNum"+
					" 	) as a on a.studyNo = dp.studyNo and a.dissectNum = dp.dissectNum"+
					" where dp.studyNo = :studyNo";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
				.setParameter("studyNo", studyNo)
				.setResultTransformer(new MapResultTransformer())
				.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getBatchAnimalMapListByStudyNo(
			String studyNo) {
		String sql = "select bi.id as batchId,ba.animalCode,ad.gender,ad.groupId as doseageNum,ds.dosageDesc,"+
					" 	convert(varchar(10),aa.anatomyBeginTime,120) as anatomyDate"+
					" from CoresStudy.dbo.tblTissueSliceBatchIndex as bi left join CoresStudy"+
					" 	.dbo.tblTissueSliceBatchAnimal as ba on bi.id = ba.batchId left join "+
					" 	CoresStudy.dbo.tblAnimalDetailDissectPlan as ad on bi.studyNo = ad.studyNo and "+
					" 	ba.animalCode = ad.animalCode	left join CoresStudy.dbo.tblDoseSetting as ds"+
					" 	on ad.studyNo = ds.studyNo and ad.groupId = ds.dosageNum	left join 	CoresStudy"+
					" 	.dbo.tblAnatomyAnimal as aa on ad.studyNo = aa.studyNo and ad.animalCode = aa.animalCode"+
					" where bi.studyNo = :studyNo" +
					" order by ba.animalCode ";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
			.setParameter("studyNo", studyNo)
			.setResultTransformer(new MapResultTransformer())
			.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getBatchIndexListByStudyNo(String studyNo) {
		String sql = "select bi.id as batchId,bi.batchSn,bi.sliceType,a.animalNum,"+
					" 	convert(varchar(10),bi.createTime,120) as createTime"+
					" from CoresStudy.dbo.tblTissueSliceBatchIndex as bi left join "+
					" 	("+
					" 	select batchId,count(batchId) as animalNum"+
					" 	from CoresStudy.dbo.tblTissueSliceBatchAnimal"+
					" 	group by batchId"+
					" 	) as a on a.batchId = bi.id"+
					" where bi.studyNo = :studyNo and isnull(bi.operatorSign,'') != ''";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
			.setParameter("studyNo", studyNo)
			.setResultTransformer(new MapResultTransformer())
			.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSliceCodeByStudyNo(String studyNo) {
		String sql = "select ssindex.id as indexId, sn.sliceCode,ssindex.sliceCodeType,sn.id as tissueSliceSnId," +
				"convert(varchar(10),ssindex.createTime,120) as codeDate ,isnull(sn.animalCode,'') as animalCode,"+
		" 					  "+
		" 					 	(case when isnull(viscera.visceraCode,'')= '' then ''  "+
		" 					 			when isnull(viscera.subVisceraCode,'') = '' then viscera.visceraName+' '"+
		" 					 			when isnull(viscera.subVisceraCode,'') != '' then viscera.subVisceraName+' ' "+
		" 					 			else '' end "+
		" 					 		+ case when isnull(viscera.bodySurfacePos,'') = '' then '' else (viscera.bodySurfacePos+' ') end"+
		" 					 		  		+case when isnull(viscera.anatomyPos,'') = '' then '' else (viscera.anatomyPos+' ') end"+
		" 				 		  		+case when isnull(viscera.anatomyFingding,'') = '' then '' else (viscera.anatomyFingding+' ') end"+
		" 					 		  		)    as visceraOrTissueName"+
			
							" from CoresStudy.dbo.tblTissueSliceIndex as ssindex left join  CoresStudy.dbo.tblTissueSliceSn as sn  "+
							" 	on ssindex.id = sn.tissueSliceIndexId"+
							" 	left join CoresStudy.dbo.tblTissueSliceViscera as viscera"+
							" 	on sn.id = viscera.tissueSliceSnId " +
							" where ssindex.studyNo = :studyNo "+
							" order by ssindex.sliceCodeType,ssindex.createTime,sn.animalCode,"+
							" 	(case when ssindex.sliceCodeType = 0 then cast(sn.sliceCode as int )"+
							" 		else 9999 end )";
				List<Map<String,Object>> maplist = getSession().createSQLQuery(sql)
																.setParameter("studyNo", studyNo)
																.setResultTransformer(new MapResultTransformer())
																.list();
				return maplist;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getContentIndexMapList(String studyNo) {
		String sql = "select id as contentIndexId,contentName"+
					" from CoresStudy.dbo.tblHistopathCheckContentIndex "+
					" where studyNo = :studyNo";
		List<Map<String,Object>> maplist = getSession().createSQLQuery(sql)
				.setParameter("studyNo", studyNo)
				.setResultTransformer(new MapResultTransformer())
				.list();
		return maplist;
	}

	public void deleteOne(String contentIndexId) {
		String sql_index = "delete"+
							" from CoresStudy.dbo.tblHistopathCheckContentIndex "+
							" where id = ? ";
		String sql_animal = "delete"+
						" from CoresStudy.dbo.tblHistopathCheckContentAnimal "+
						" where contentIndexId = ?";
		String sql_slice = "delete"+
							" from CoresStudy.dbo.tblHistopathCheckContentSlice "+
							" where contentIndexId = ?";
		getSession().createSQLQuery(sql_index).setParameter(0, contentIndexId).executeUpdate();
		getSession().createSQLQuery(sql_animal).setParameter(0, contentIndexId).executeUpdate();
		getSession().createSQLQuery(sql_slice).setParameter(0, contentIndexId).executeUpdate();
	}

	public TblHistopathCheckContentIndex saveOne(
			TblHistopathCheckContentIndex contentIndex,
			List<String> animalCodeList, List<String> tissueSliceSnIdList,
			List<String> sliceCodeList) {
		//1.索引
		String contentIndexId = getKey();
		Date createDate = new Date();
		contentIndex.setId(contentIndexId);
		contentIndex.setCreateDate(createDate);
		String studyNo = contentIndex.getStudyNo();
		getSession().save(contentIndex);
		//2.动物
		TblHistopathCheckContentAnimal tblHistopathCheckContentAnimal = null;
		
		for(String animalCode :animalCodeList){
			tblHistopathCheckContentAnimal = new TblHistopathCheckContentAnimal();
			tblHistopathCheckContentAnimal.setId(getKey("TblHistopathCheckContentAnimal"));
			tblHistopathCheckContentAnimal.setStudyNo(studyNo);
			tblHistopathCheckContentAnimal.setContentIndexId(contentIndexId);
			tblHistopathCheckContentAnimal.setAnimalCode(animalCode);
			
			getSession().save(tblHistopathCheckContentAnimal);
		}
		//3.切片
		TblHistopathCheckContentSlice tblHistopathCheckContentSlice = null;
		int index  = 0;
		for(String tissueSliceSnId :tissueSliceSnIdList){
			tblHistopathCheckContentSlice = new TblHistopathCheckContentSlice();
			tblHistopathCheckContentSlice.setId(getKey("TblHistopathCheckContentSlice"));
			tblHistopathCheckContentSlice.setStudyNo(studyNo);
			tblHistopathCheckContentSlice.setContentIndexId(contentIndexId);
			tblHistopathCheckContentSlice.setTissueSliceSnId(tissueSliceSnId);
			tblHistopathCheckContentSlice.setSliceCode(sliceCodeList.get(index));
			
			getSession().save(tblHistopathCheckContentSlice);
			index++;
		}
		
		return contentIndex;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnimalMapList(String contentIndexId) {
		String sql = "select ca.animalCode,ad.gender,ad.groupId as doseageNum,ds.dosageDesc," +
		" 	convert(varchar(10),aa.anatomyBeginTime,120) as anatomyDate" +
		" from CoresStudy.dbo.tblHistopathCheckContentAnimal as ca left join " +
		" 	CoresStudy.dbo.tblAnimalDetailDissectPlan as ad on ca.studyNo = ad.studyNo and " +
		" 	ca.animalCode = ad.animalCode	left join CoresStudy.dbo.tblDoseSetting as ds" +
		" 	on ad.studyNo = ds.studyNo and ad.groupId = ds.dosageNum	left join 	CoresStudy" +
		" 	.dbo.tblAnatomyAnimal as aa on ad.studyNo = aa.studyNo and ad.animalCode = aa.animalCode" +
		" where ca.contentIndexId = :contentIndexId ";
		List<Map<String,Object>> list = getSession().createSQLQuery(sql).
													setParameter("contentIndexId", contentIndexId)
													.setResultTransformer(new MapResultTransformer())
													.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<String> getSliceCodeList(String contentIndexId) {
		String sql = "select sliceCode " +
				" from CoresStudy.dbo.tblHistopathCheckContentSlice " +
				" where contentIndexId = ?";
		List<String> list = getSession().createSQLQuery(sql).setParameter(0, contentIndexId).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<String> getSliceCodeListByBatchIdSet(Set<String> batchIdSet) {
		String sql = "select distinct sn.sliceCode" +
		" from CoresStudy.dbo.tblTissueSliceBatchIndex as bi left join CoresStudy.dbo.tblTissueSliceBatchViscera as bv" +
		" 	on bi.id = bv.batchId left join CoresStudy.dbo.tblTissueSliceViscera as sv on sv.id = bv.tissueSliceVisceraId" +
		" 	left join CoresStudy.dbo.tblTissueSliceSn as sn on sn.id = sv.tissueSliceSnId" +
		" where bi.id in (:batchIdSet) ";
		List<String> list = null;
		if(null != batchIdSet && batchIdSet.size() > 0){
			list = getSession().createSQLQuery(sql).setParameterList("batchIdSet", new ArrayList<String>(batchIdSet)).list();
		}
		return list;
	}

}
