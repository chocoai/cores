package com.lanen.service.path;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.path.TblAnatomyReqVisceraWeigh;
import com.lanen.model.path.TblAnatomyReqVisceraWeighHis;
/**
 * 解剖申请-脏器称重   serviceImpl
 *@author 
 */
@Service
public class TblAnatomyReqVisceraWeighServiceImpl extends BaseDaoImpl<TblAnatomyReqVisceraWeigh>
		implements TblAnatomyReqVisceraWeighService {

	public int getSn(String studyNoPara,Integer reqNo) {
		int sn=0;
		String sql = "select Max(arvw.sn)"+
					" from tblAnatomyReqVisceraWeigh as arvw where arvw.studyNo=:studyNo and arvw.reqNo=:reqNo";
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
	public List<TblAnatomyReqVisceraWeigh> getListByStudyAndReqNo(
			String studyNoPara, int reqNo) {
		String hql="from TblAnatomyReqVisceraWeigh where studyNo=:studyNoPara and  reqNo=:reqNo";
		List<TblAnatomyReqVisceraWeigh> list=getSession().createQuery(hql).setParameter("studyNoPara", studyNoPara)
                                                         .setParameter("reqNo", reqNo)
                                                         .list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraListByAnimalTypeAndStudyNo(
			String animalTypeId, String studyNoPara,Integer reqNo) {
		List<Map<String, Object>> list=null;
		String sql=" select visceraCode ,visceraName,isPart "+
		   "   from CoresSystemSet.dbo.dictViscera as dv "+
			"  where  dv.level=1 and " +
			"  (dv.animalFlag=1 or (dv.animalFlag=0 and dv.visceraCode in  " +
			"       (select va.visceraCode  " +
			" 	     from CoresSystemSet.dbo.dictVisceraAnimal as va " +
			" 	     where va.animalTypeName=:animalTypeId ) " +
			" 		)) and "+
			"  dv.visceraCode not in "+ 
			"    ( select visceraCode from CoresStudy.dbo.tblAnatomyReqVisceraWeigh where studyNo=:studyNoPara and reqNo=:reqNo) "+
			"  and dv.visceraCode not in  "+
			"    ( select pav.visceraCode  "+
			"      from CoresStudy.dbo.tblAnatomyReqAttachedViscera as pav "+
			"	   join CoresStudy.dbo.tblAnatomyReqVisceraWeigh as pvw  "+
			"	   on pvw.id=pav.anatomyReqVisceraWeighID  "+
			"	   where pvw.studyNo=:studyNoPara and pvw.reqNo=:reqNo" +
			"     ) "+
			"  order by dv.sn";
		list=getSession().createSQLQuery(sql).setParameter("animalTypeId", animalTypeId)
		                                     .setParameter("studyNoPara", studyNoPara)
		                                     .setParameter("reqNo", reqNo)
		                                     .setResultTransformer(new MapResultTransformer())
		                                     .list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblAnatomyReqVisceraWeighHis> getHisListByStudyAndReqNo(
			String studyNo, Integer anatomyReqNo) {
		String hql="from TblAnatomyReqVisceraWeighHis where studyNo=:studyNoPara and  reqNo=:reqNo";
		List<TblAnatomyReqVisceraWeighHis> list=getSession().createQuery(hql).setParameter("studyNoPara", studyNo)
                                                         .setParameter("reqNo", anatomyReqNo)
                                                         .list();
		return list;
	}

	

}
