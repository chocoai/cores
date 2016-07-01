package com.lanen.service.path;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.path.TblPathPlanAttachedViscera;
import com.lanen.model.path.TblPathPlanVisceraWeigh;
/**
 * 病理计划-脏器称重-附加脏器     serviceImpl 
 * @author 曾锋
 */
@Service
public class TblPathPlanAttachedVisceraServiceImpl extends BaseDaoImpl<TblPathPlanAttachedViscera>
		implements TblPathPlanAttachedVisceraService {

	@SuppressWarnings("unchecked")
	public List<TblPathPlanAttachedViscera> getListByPid(
			String visceraWeighPlanID) {
		String hql="from TblPathPlanAttachedViscera where visceraWeighPlanID=:visceraWeighPlanID";
		List<TblPathPlanAttachedViscera> list=getSession().createQuery(hql)
		                                                  .setParameter("visceraWeighPlanID", visceraWeighPlanID)
		                                                  .list();
		return list;
	}

	public void addSave(TblPathPlanAttachedViscera tblPathPlanAttachedViscera,
			TblPathPlanVisceraWeigh tblPathPlanVisceraWeigh) {
		getSession().save(tblPathPlanAttachedViscera);
		getSession().update(tblPathPlanVisceraWeigh);
	}

	@SuppressWarnings("unchecked")
	public List<String> getIdListByStudyNo(String studyNo) {
		String sql="select pav.id " +
				   " from CoresStudy.dbo.TblPathPlanAttachedViscera as pav" +
				   " join CoresStudy.dbo.tblPathPlanVisceraWeigh as pvw" +
				   " on pvw.id=pav.VisceraWeighPlanID" +
				   " where pvw.studyNo=:studyNo";
		List<String> list=null;
		list=getSession().createSQLQuery(sql).setParameter("studyNo", studyNo).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblPathPlanAttachedViscera> getListByStudyNo(String studyNo) {
		  String sql="select pav.id,pav.visceraWeighPlanID,pav.visceraType,pav.visceraCode,pav.visceraName " +
		   " from CoresStudy.dbo.TblPathPlanAttachedViscera as pav" +
		   " join CoresStudy.dbo.tblPathPlanVisceraWeigh as pvw" +
		   " on pvw.id=pav.VisceraWeighPlanID" +
		   " where pvw.studyNo=:studyNo";
			List<Map<String, Object>> list= getSession().createSQLQuery(sql).
			          setParameter("studyNo", studyNo).setResultTransformer(new MapResultTransformer()).list();
			List<TblPathPlanAttachedViscera> plist= new ArrayList<TblPathPlanAttachedViscera>();
			for(Map<String, Object> map:list){
				TblPathPlanAttachedViscera tblPathPlanAttachedViscera = new TblPathPlanAttachedViscera();
				String id = (String) map.get("id");
				String visceraWeighPlanID = (String) map.get("visceraWeighPlanID");
				Integer visceraType = (Integer) map.get("visceraType");
				String visceraCode = (String) map.get("visceraCode");
				String visceraName = (String) map.get("visceraName");
				tblPathPlanAttachedViscera.setId(id);
				tblPathPlanAttachedViscera.setVisceraCode(visceraCode);
				tblPathPlanAttachedViscera.setVisceraName(visceraName);
				tblPathPlanAttachedViscera.setVisceraType(visceraType);
				tblPathPlanAttachedViscera.setVisceraWeighPlanID(visceraWeighPlanID);
				plist.add(tblPathPlanAttachedViscera);
			}
			return plist;
	}


}
