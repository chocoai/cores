package com.lanen.service.clinicaltest;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanen.base.MapResultTransformer;

@Service
@Transactional
public class ClinicalTestServiceImpl implements ClinicalTestService {
	@Resource
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<String> getYearList() {
		String sql = "select distinct convert(varchar(4),study.studyStartDate,120)"+
					" from("+
					" 	select distinct d.studyNo"+
					" 	from CoresStudy.dbo.tblClinicalTestData as d "+
					" 	) as r"+
					" 	left join CoresStudy.dbo.tblStudyPlan as study"+
					" 	on r.studyNo = study.studyNo"+
					" order by convert(varchar(4),study.studyStartDate,120) desc";
		
		List<String> yearList = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return yearList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getStudyNoMapListByYear(String year) {
		String sql = "select distinct d.studyNo"+
					" from CoresStudy.dbo.tblClinicalTestData as d "+
					" 	left join CoresStudy.dbo.tblStudyPlan as study"+
					" 	on d.studyNo = study.studyNo"+
					" where convert(varchar(4),study.studyStartDate,120) = :year "+
					" order by d.studyNo";
		List<Map<String,Object>> mapList = sessionFactory.getCurrentSession()
															.createSQLQuery(sql)
															.setParameter("year", year)
															.setResultTransformer(new MapResultTransformer())
															.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getClinicalTestData(String studyNo) {
		String sql = "select CoresStudy.dbo.studyNoRemoveFN(cd.studyNo) as studyNo,cd.reqNo,cd.specimenCode,cd.animalId,cd.testItem,cd.testIndexAbbr,cd.animalCode,"+
					" 	cd.testIndex,cd.testIndexUnit,cd.testData,convert(varchar(19),"+
					" 	cd.collectionTime,120) as collectionTime,cd.confirmFlag,cd.es ,CoresStudy.dbo.studyNoToFN(cd.studyNo) as fn"+
					" from CoresStudy.dbo.tblClinicalTestData as cd join CoresStudy.dbo.tblClinicalTestData as cd2"+
					" 	on cd.studyNo = cd2.studyNo and cd.reqNo = cd2.reqNo and cd.specimenCode = cd2.specimenCode and cd.animalId = cd2.animalId "+
					" 	and cd.testItem = cd2.testItem and cd.testIndexAbbr = cd2.testIndexAbbr and cd.dataId != cd2.dataId"+
					" where  cd.studyNo like :studyNo+'%'  and   CoresStudy.dbo.studyNoRemoveFN(cd.studyNo) = :studyNo "+
					" order by cd.studyNo,cd.reqNo,cd.testItem,cd.specimenCode,cd.animalId,cd.testIndexAbbr";
		List<Map<String,Object>> mapList = sessionFactory.getCurrentSession()
														.createSQLQuery(sql)
														.setParameter("studyNo", studyNo)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDelTrace(String studyNo) {
		String sql = "select t.oldValue,t.newValue as newValue,'' as newValue2,t.operator,t.modifyReason,t.modifyReason," +
					"	 convert(varchar(19),t.modifyTime,120) as  modifyTime,t.operateMode ," +
					" CoresStudy.dbo.studyNoToFN( substring(t.newValue,0,charindex(',',t.newValue))) as fn"+
					" from CoresUserPrivilege.dbo.tblTrace as t"+
					" where t.tableName ='TblClinicalTestData' and " +
					"  CoresStudy.dbo.studyNoRemoveFN( substring(t.newValue,0,charindex(',',t.newValue))) = :studyNo ";
		List<Map<String,Object>> mapList = sessionFactory.getCurrentSession()
														.createSQLQuery(sql)
														.setParameter("studyNo", studyNo)
														.setResultTransformer(new MapResultTransformer())
														.list();
		if(null !=mapList && mapList.size() > 0 ){
			for(Map<String,Object> map:mapList){
				String newValue = (String) map.get("newValue");
				String[] newValues = newValue.split(",");
				if(newValues.length >5 ){
//					map.put("studyNo", newValues[0]);
					map.put("studyNo", studyNo);
					map.put("reqNo", newValues[1]);
					map.put("testItem", newValues[2]);
					map.put("specimenCode", newValues[3]);
					map.put("testIndexAbbr", newValues[4]);
					String collectionTime = newValues[5];
					collectionTime = collectionTime.substring(0, 4)+"-"+
						collectionTime.substring(4, 6)+"-"+collectionTime.substring(6);
					map.put("collectionTime", collectionTime);
				}
			}
		}
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getEditTrace(String studyNo) {
		String sql = "select :studyNo as studyNo,d.reqNo,d.testItem,d.specimenCode ,d.testIndexAbbr,"+
					" 	convert(varchar(19),d.collectionTime,120) as collectionTime,t.oldValue,t.newValue as newValue2,"+
					" 	t.operateMode,t.operator,t.modifyReason, convert(varchar(19),t.modifyTime,120) as  modifyTime,"+
					" 	 CoresStudy.dbo.studyNoToFN(d.studyNo) as fn"+
					" from CoresUserPrivilege.dbo.tblTrace as t join CoresStudy.dbo.tblClinicalTestData as d"+
					" 	on t.dataId  = d.dataId "+
					" where t.tableName = 'TblClinicalTestData' and t.operateMode = 1 "+
					" 	and CoresStudy.dbo.studyNoRemoveFN(d.studyNo) = :studyNo ";
		List<Map<String,Object>> mapList = sessionFactory.getCurrentSession()
		.createSQLQuery(sql)
		.setParameter("studyNo", studyNo)
		.setResultTransformer(new MapResultTransformer())
		.list();
		return mapList;
	}
}
