package com.lanen.service.path;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.path.TblAnatomyReqPathPlanCheck;
import com.lanen.model.path.TblAnatomyReqPathPlanCheckHis;
/**
 * 解剖申请-脏器/组织学检查   serviceImpl
 *@author 
 */
@Service
public class TblAnatomyReqPathPlanCheckServiceImpl extends
		BaseDaoImpl<TblAnatomyReqPathPlanCheck> implements
		TblAnatomyReqPathPlanCheckService {

	public int getSn(String studyNoPara,Integer reqNo) {
		int sn=0;
		String sql = "select Max(arpp.sn)"+
					" from tblAnatomyReqPathPlanCheck as arpp where arpp.studyNo=:studyNo and arpp.reqNo=:reqNo ";
		Integer maxSn = (Integer) getSession().createSQLQuery(sql)
		                                      .setParameter("studyNo", studyNoPara)
		                                      .setParameter("reqNo", reqNo)
		                                      .uniqueResult();
		if(maxSn!=null&&maxSn!=0){
			sn=maxSn+1;
		}else{
			sn=1;
		}
		return sn;
	}

	@SuppressWarnings("unchecked")
	public List<TblAnatomyReqPathPlanCheck> getListByStudyNoAndReqNo(
			String studyNoPara, int reqNo) {
		String hql="from TblAnatomyReqPathPlanCheck where studyNo=:studyNoPara and  reqNo=:reqNo";
		List<TblAnatomyReqPathPlanCheck> list=getSession().createQuery(hql).setParameter("studyNoPara", studyNoPara)
                                                          .setParameter("reqNo", reqNo).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraListByAnimalTypeAndStudyNo(
			String animalTypeId, String studyNoPara,Integer reqNo) {
		List<Map<String, Object>> list=null;
		String sql="select visceraCode ,visceraName "+
				    "  from CoresSystemSet.dbo.dictViscera as dv "+
					"  where  dv.level=1 and " +
					"  (dv.animalFlag=1 or (dv.animalFlag=0 and dv.visceraCode in  " +
					"       (select va.visceraCode  " +
					" 	     from CoresSystemSet.dbo.dictVisceraAnimal as va " +
					" 	     where va.animalTypeName=:animalTypeId ) " +
					" 		)) and "+
					"  dv.visceraCode not in  "+
					" ( select visceraCode from CoresStudy.dbo.tblAnatomyReqPathPlanCheck where studyNo=:studyNoPara and reqNo=:reqNo) "+
					" order by dv.sn";
		list=getSession().createSQLQuery(sql).setParameter("animalTypeId", animalTypeId)
		                                     .setParameter("studyNoPara", studyNoPara)
		                                     .setParameter("reqNo", reqNo)
		                                     .setResultTransformer(new MapResultTransformer())
		                                     .list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblAnatomyReqPathPlanCheckHis> getHisListByStudyNoAndReqNo(
			String studyNo, Integer anatomyReqNo) {
		String hql="from TblAnatomyReqPathPlanCheckHis where studyNo=:studyNoPara and  reqNo=:reqNo";
		List<TblAnatomyReqPathPlanCheckHis> list=getSession().createQuery(hql).setParameter("studyNoPara", studyNo)
                                                          .setParameter("reqNo", anatomyReqNo).list();
		return list;
	}

}
