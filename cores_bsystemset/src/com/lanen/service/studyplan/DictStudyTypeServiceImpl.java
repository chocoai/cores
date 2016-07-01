package com.lanen.service.studyplan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.model.studyplan.DictTestItemType;

@Service
public class DictStudyTypeServiceImpl extends BaseDaoImpl<DictStudyType> implements
		DictStudyTypeService {

	@SuppressWarnings("unchecked")
	public List<DictStudyType> getAll() {
		List<DictStudyType> list=getSession().createQuery("FROM DictStudyType  d ORDER BY d.studyTypeCode ASC").list();
		return list;
	}

	public boolean isExistByCode(String code) {
		Object obj =getSession().get(DictStudyType.class, code);
		return obj!=null ? true :false;
	}

	@SuppressWarnings("unchecked")
	public boolean isExistByStudyCode(String studyCode) {
		List<DictStudyType> list=getSession().createQuery("FROM DictStudyType  d WHERE d.studyCode= ?")//
		.setParameter(0, studyCode)
		.list();
		if(null!=list && list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean isExistByTiCode(String tiCode) {
		List<DictStudyType> list=getSession().createQuery("FROM DictStudyType  d WHERE d.tiCode = ?")//
		.setParameter(0, tiCode)
		.list();
		if(null!=list && list.size()>0){
			return true;
		}else{
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DictStudyType> getByTiCode(String tiCode) {
		List<DictStudyType> list=getSession().createQuery("FROM DictStudyType  d WHERE d.tiCode = ?  ORDER BY d.studyTypeCode ASC")//
		.setParameter(0, tiCode)
		.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public boolean isExistByStudyName(String studyName) {
		List<DictStudyType> list=getSession().createQuery("FROM DictStudyType  d WHERE d.studyName = ? ")//
		.setParameter(0, studyName)
		.list();
		if(null!=list && list.size()>0){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean isExistByNameCode(String studyTypeCode, String studyName) {
		List<DictStudyType> list=getSession().createQuery("FROM DictStudyType  d WHERE d.studyName = ? and d.studyTypeCode != ? ")//
		.setParameter(0, studyName)
		.setParameter(1, studyTypeCode)
		.list();
		if(null!=list && list.size()>0){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean isExistByStudyCode(String studyTypeCode, String studyCode) {
		List<DictStudyType> list=getSession().createQuery("FROM DictStudyType  d WHERE d.studyCode = ? and d.studyTypeCode != ? ")//
		.setParameter(0, studyCode)
		.setParameter(1, studyTypeCode)
		.list();
		if(null!=list && list.size()>0){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public DictStudyType getByStudyNametiNo(String studyName, String tiNo) {
		String tiCode = null;
		String sql = "select testitem.tiCode"+
					" from CoresContract.dbo.tblTestItem as testitem"+
					" where testitem.tiNo = :tiNo";
		List<String> list = getSession().createSQLQuery(sql).setParameter("tiNo", tiNo).list();
		if(null != list && list.size() > 0){
			tiCode = list.get(0);
		}
		DictStudyType dictStudyType = null;
		if(null != tiCode){
			List<DictStudyType> dictStudyTypeList=getSession().createQuery("FROM DictStudyType  d WHERE d.studyName = ? and d.tiCode = ? ")
																.setParameter(0, studyName)
																.setParameter(1, tiCode)
																.list();
			if(null != dictStudyTypeList && dictStudyTypeList.size() > 0){
				dictStudyType = dictStudyTypeList.get(0);
			}
		}
		return dictStudyType;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getByStudyGroupId(String studyGroupId) {
		//"SELECT [studyTypeCode],[studyName],[studyPeriod],[studyPeriodUnit],studytype.[tiCode],[tiType],[studyCode],[animalHave],[studyGroupID]  " +
		String sql = "FROM DictStudyType studytype " +
				", DictTestItemType itemtype  " +
				"where studytype.tiCode=itemtype.tiCode and studytype.studyGroupID=:studyGroupId ";
		List<Object[]> objList= getSession().createQuery(sql).setString("studyGroupId", studyGroupId)			
															.list();
		List<Map<String, Object>> groupAndTypeList = new ArrayList<Map<String, Object>>();
		for(Object[] objs:objList)
		{
			boolean flag = false;
			Map<String, Object> map = new HashMap<String, Object>();
			if(objs[0] instanceof DictStudyType)
			{
				map.put("studyCode", ((DictStudyType)objs[0]).getStudyCode());
				map.put("studyGroupID", ((DictStudyType)objs[0]).getStudyGroupID());
				map.put("studyName", ((DictStudyType)objs[0]).getStudyName());
				map.put("studyPeriodUnit", ((DictStudyType)objs[0]).getStudyPeriodUnit());
				map.put("studyTypeCode", ((DictStudyType)objs[0]).getStudyTypeCode());
				map.put("tiCode", ((DictStudyType)objs[0]).getTiCode());
				map.put("animalHave", ((DictStudyType)objs[0]).getAnimalHave());
				map.put("studyPeriod", ((DictStudyType)objs[0]).getStudyPeriod());
				flag=true;
			}
			if(objs[1] instanceof DictTestItemType)
			{
				map.put("tiType", ((DictTestItemType)objs[1]).getTiType());
				flag=true;
			}
			if(flag)
			{
				groupAndTypeList.add(map);
			}
		}

		return groupAndTypeList;
	}
	@SuppressWarnings("unchecked")
	public List<DictStudyType> getListByStudyGroupId(String studyGroupId) {
		//"SELECT [studyTypeCode],[studyName],[studyPeriod],[studyPeriodUnit],studytype.[tiCode],[tiType],[studyCode],[animalHave],[studyGroupID]  " +
		String sql = "FROM DictStudyType studytype " +
				"where studytype.studyGroupID=:studyGroupId ";
		List<DictStudyType> objList= getSession().createQuery(sql).setString("studyGroupId", studyGroupId)			
															.list();
		
		return objList;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getExceptStudyGroupId(String studyGroupId,String tiCode,String studyName) {
		//"SELECT [studyTypeCode],[studyName],[studyPeriod],[studyPeriodUnit],studytype.[tiCode],[tiType],[studyCode],[animalHave],[studyGroupID]  " +
		String sql = "FROM DictStudyType as studytype " +
				", DictTestItemType as itemtype  " +
				"where studytype.tiCode=itemtype.tiCode and studytype.studyGroupID is null";
		//and studytype.studyGroupID !=:studyGroupId 
		if(tiCode!=null&&!"".equals(tiCode))
		{
			sql+=" and studytype.tiCode=:tiCode ";
		}
		if(studyName!=null&&!"".equals(studyName))
		{
			sql+=" and studyName like :studyName";
		}
		Query query = getSession().createQuery(sql);
		if(tiCode!=null&&!"".equals(tiCode))
		{
			query.setString("tiCode",tiCode);
		}
		if(studyName!=null&&!"".equals(studyName))
		{
			query.setString("studyName","%"+studyName+"%");
		}
		List<Object[]> objList=query
									//.setString("studyGroupId", studyGroupId)			
									.list();
		List<Map<String, Object>> groupAndTypeList = new ArrayList<Map<String, Object>>();
		for(Object[] objs:objList)
		{
			boolean flag = false;
			Map<String, Object> map = new HashMap<String, Object>();
			if(objs[0] instanceof DictStudyType)
			{
				map.put("studyCode", ((DictStudyType)objs[0]).getStudyCode());
				map.put("studyGroupID", ((DictStudyType)objs[0]).getStudyGroupID());
				map.put("studyName", ((DictStudyType)objs[0]).getStudyName());
				map.put("studyPeriodUnit", ((DictStudyType)objs[0]).getStudyPeriodUnit());
				map.put("studyTypeCode", ((DictStudyType)objs[0]).getStudyTypeCode());
				map.put("tiCode", ((DictStudyType)objs[0]).getTiCode());
				map.put("animalHave", ((DictStudyType)objs[0]).getAnimalHave());
				map.put("studyPeriod", ((DictStudyType)objs[0]).getStudyPeriod());
				flag=true;
			}
			if(objs[1] instanceof DictTestItemType)
			{
				map.put("tiType", ((DictTestItemType)objs[1]).getTiType());
				flag=true;
			}
			if(flag)
			{
				groupAndTypeList.add(map);
			}
		}
		return groupAndTypeList;

	}
	@SuppressWarnings("unchecked")
	public DictStudyType getByStudyTypeCode(String studyTypeCode) {
		String sql = "FROM DictStudyType " +
				"where studyTypeCode=:studyTypeCode";
		
	
		Query query = getSession().createQuery(sql)
								  //.setString("studyName", studyName)
								  .setString("studyTypeCode", studyTypeCode);
		
		
		List<DictStudyType> list = query.list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	

}
