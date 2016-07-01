package com.lanen.service.studyplan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.studyplan.TblAnimalDetailDissectPlan;
import com.lanen.model.studyplan.TblDoseSetting;
import com.lanen.model.studyplan.TblStudyPlan;
@Service
public class TblAnimalDetailDissectPlanServiceImpl extends BaseDaoImpl<TblAnimalDetailDissectPlan> implements TblAnimalDetailDissectPlanService {

	@Resource
	private TblDoseSettingService tblDoseSettingService;
	public void save(TblAnimalDetailDissectPlan entity) {
		if(null == entity.getId()){
			entity.setId(getKey("TblAnimalDetailDissectPlan"));
		}
		getSession().save(entity);
	}

	@SuppressWarnings("unchecked")
	public List<TblAnimalDetailDissectPlan> getByStudyPlan(TblStudyPlan studyPlan, int number) {
		return getSession().createQuery("FROM TblAnimalDetailDissectPlan WHERE tblStudyPlan=? AND dissectNum =? order by animalCode ")
		.setParameter(0, studyPlan).setParameter(1, number).list();
	}

	@SuppressWarnings("unchecked")
	public List<TblAnimalDetailDissectPlan> getByStudyPlan(
			TblStudyPlan tblStudyPlan) {
		return getSession().createQuery("FROM TblAnimalDetailDissectPlan WHERE tblStudyPlan=? order by animalCode ")
		.setParameter(0, tblStudyPlan).list();
	}

	@SuppressWarnings("unchecked")
	public TblAnimalDetailDissectPlan getByStudyPlanAndAnimalCode(
			TblStudyPlan tblStudyPlan, String animalCode) {
		List<TblAnimalDetailDissectPlan> list= getSession().createQuery("FROM TblAnimalDetailDissectPlan WHERE tblStudyPlan=? AND animalCode =?  ")
		.setParameter(0, tblStudyPlan)//
		.setParameter(1, animalCode)//
		.list();
		if(null!=list &&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<TblAnimalDetailDissectPlan> getByStudyPlanAndNo0(
			TblStudyPlan tblStudyPlan) {
		return getSession().createQuery("FROM TblAnimalDetailDissectPlan WHERE tblStudyPlan=? AND dissectNum !=0 order by animalCode ")
		.setParameter(0, tblStudyPlan).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getByStudyNoAndDissectNum2(
			String studyNo, int dissectNum) {
		List<Map<String, Object>> list=null;
		String sql=" select ddp.animalCode,ddp.gender "+
		           "  from CoresStudy.dbo.TblAnimalDetailDissectPlan as ddp "+
		           "  where ddp.studyNo=:studyNo and ddp.dissectNum=:dissectNum "+
		           " and ddp.animalCode not in "+
		           " ( select tara.animalCode from "+
		           "    CoresStudy.dbo.tblAnatomyReqAnimalList as tara "+
		           "          join CoresStudy.dbo.tblAnatomyReq as tar "+
		           "         on tara.studyNo=tar.studyNo and tara.anatomyReqNo=tar.reqNo "+
		           "         where tara.studyNo=:studyNo and tar.submitFlag>0 "+
		           "   )";
//		String hql="from TblAnimalDetailDissectPlan where studyNo=:studyNo and dissectNum=:dissectNum";
		list=getSession().createSQLQuery(sql).setParameter("studyNo", studyNo)
		                                      .setParameter("dissectNum", dissectNum)
		                                      .setResultTransformer(new MapResultTransformer())
		                                      .list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<TblAnimalDetailDissectPlan> getByStudyNoAndDissectNum(
			String studyNo, int dissectNum) {
		String hql="from TblAnimalDetailDissectPlan where studyNo=:studyNo and dissectNum=:dissectNum";
		List<TblAnimalDetailDissectPlan> list=null;
		list=getSession().createQuery(hql).setParameter("studyNo", studyNo)
					        .setParameter("dissectNum", dissectNum)
					        .list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<TblAnimalDetailDissectPlan> getListByStudyNoAndAnimalCodeList(
			String studyNo, List<String> animalCodeList) {
		TblStudyPlan tblStudyPlan = new TblStudyPlan();
		tblStudyPlan.setStudyNo(studyNo);
		String hql = "FROM TblAnimalDetailDissectPlan WHERE tblStudyPlan = :tblStudyPlan AND animalCode in (:animalCodeList) ";
		List<TblAnimalDetailDissectPlan> list= getSession().createQuery(hql)
															.setParameter("tblStudyPlan", tblStudyPlan)//
															.setParameterList("animalCodeList", animalCodeList)//
															.list();
		return list;
	}

	public Integer getGroupIdByStudyNoAndAnimalCode(String studyNo,
			String animalCode) {
		String sql="select groupId from CoresStudy.dbo.TblAnimalDetailDissectPlan where studyNo=:studyNo and animalCode=:animalCode";
		Integer groupId=(Integer) getSession().createSQLQuery(sql).setParameter("studyNo", studyNo)
		                            .setParameter("animalCode", animalCode)
		                            .uniqueResult();
		return groupId;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListByStudyNo(String studyNoPara) {
		String sql = "select animal.id ,animal.gender,animal.animalCode,dose.dosageNum,dose.dosageDesc  "+
					" from CoresStudy.dbo.tblAnimalDetailDissectPlan as animal"+
					" 	left join CoresStudy.dbo.tblDoseSetting as dose"+
					" 	on animal.studyNo = dose.studyNo and animal.groupId = dose.dosageNum"+
					" where animal.studyNo = ? "+
					" order by animal.groupId";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter(0, studyNoPara)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	public boolean isExistByStudyNoAnimalcode(String studyNo, String animalCode) {
		String sql = "select count(id)"+
					" from CoresStudy.dbo.tblAnimalDetailDissectPlan"+
					" where studyNo = ? and animalCode = ?";
		Integer count = (Integer) getSession().createSQLQuery(sql)
									.setParameter(0, studyNo)
									.setParameter(1, animalCode)
									.uniqueResult();
		return count > 0;
	}

	public boolean isExistByIdStudyNoAnimalcode(String studyNo,
			String animalCode, String codeId) {
		String sql = "select count(id)"+
				" from CoresStudy.dbo.tblAnimalDetailDissectPlan"+
				" where studyNo = ? and animalCode = ? and id != ?";
		Integer count = (Integer) getSession().createSQLQuery(sql)
								.setParameter(0, studyNo)
								.setParameter(1, animalCode)
								.setParameter(2, codeId)
								.uniqueResult();
return count > 0;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getNumberMapListByStudyNo(
			String studyNoPara) {
		String sql = "select groupId,gender,count(animalCode) as number"+
					" from CoresStudy.dbo.tblAnimalDetailDissectPlan "+
					" where studyNo =  ? "+
					" group by groupId,gender"+
					" order by groupId";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter(0, studyNoPara)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	public Map<String, Object> getNextByStudyNoGroupIdGender(String studyNo,
			int groupId, int gender) {
		Map<String,Object>	map = null;
		if(null != studyNo && groupId > 0 && gender > 0){
			TblStudyPlan tblStudyPlan = new TblStudyPlan();
			tblStudyPlan.setStudyNo(studyNo);
			int nextGroupId = groupId;
			int nextGender = gender;
			//找到
			boolean success = false;
			TblDoseSetting tblDoseSetting = null;
			//先向后找
			do{
				tblDoseSetting = tblDoseSettingService.getByStudyNoGroup(tblStudyPlan , nextGroupId);
				if(null != tblDoseSetting){
					Integer count = this.getCountByStudyNoGroupIdGender(studyNo,nextGroupId,nextGender);
					if(nextGender == 1){
						if(count < tblDoseSetting.getMaleNum()){
							success = true;
							break;
						}
					}else{
						if(count < tblDoseSetting.getFemaleNum()){
							success = true;
							break;
						}
					}
					
					if(nextGroupId == groupId && nextGender == gender){
						nextGender = gender%2 +1;
					}else{
						if(nextGroupId == groupId){
							nextGroupId = nextGroupId+1;
							nextGender = 1;
						}else{
							if(nextGender == 1){
								nextGender = 2;
							}else{
								nextGroupId = nextGroupId+1;
								nextGender = 1;
							}
						}
					}
				}else{
					break;
				}
			}while(true);
			//向后找，找到了
			if(success){
				map = new HashMap<String,Object>();
				map.put("dosageNum", nextGroupId);
				map.put("gender", nextGender);
			}
			
		}
 		return map;
	}

	private Integer getCountByStudyNoGroupIdGender(String studyNo, int groupId,
			int gender) {
		String sql = "select count(animalCode) "+
		" from CoresStudy.dbo.tblAnimalDetailDissectPlan "+
		" where studyNo =  ? and  groupId = ? and  gender = ?"+
		" ";
		Integer count = (Integer) getSession().createSQLQuery(sql)
											.setParameter(0, studyNo)
											.setParameter(1, groupId)
											.setParameter(2, gender)
											.uniqueResult();
		return count;
	}

	
}
