package com.lanen.service.path;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.User;
import com.lanen.model.path.TblPathPlanAttachedViscera;
import com.lanen.model.path.TblPathPlanAttachedVisceraHis;
import com.lanen.model.path.TblPathPlanVisceraWeigh;
import com.lanen.model.path.TblPathPlanVisceraWeighHis;
import com.lanen.model.studyplan.TblApplyRevise;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.studyplan.TblApplyReviseService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.opensymphony.xwork2.ActionContext;
/**
 * 病理计划-脏器称重     serviceImpl 
 * @author 曾锋
 */
@Service
public class TblPathPlanVisceraWeighServiceImpl extends BaseDaoImpl<TblPathPlanVisceraWeigh>
		implements TblPathPlanVisceraWeighService {
    @Resource
    private TblPathPlanAttachedVisceraService tblPathPlanAttachedVisceraService;
    
    
	@SuppressWarnings("unchecked")
	public List<TblPathPlanVisceraWeigh> getListByStudyNo(String studyNoPara) {
		String hql="from TblPathPlanVisceraWeigh where studyNo=:studyNoPara";
		List<TblPathPlanVisceraWeigh> list=getSession().createQuery(hql)
														.setParameter("studyNoPara", studyNoPara)
														.list();
		return list;
	}

	public int getSn(String studyNo) {
		int sn=0;
		String sql = "select Max(ppc.sn)"+
					" from TblPathPlanVisceraWeigh as ppc where ppc.studyNo=:studyNo";
		Integer maxSn = (Integer) getSession().createSQLQuery(sql).setParameter("studyNo", studyNo).uniqueResult();
		if(maxSn!=null&&maxSn!=0){
			sn=maxSn+1;
		}else{
			sn=1;
		}
		return sn;
	}

	@SuppressWarnings("unchecked")
	public TblPathPlanVisceraWeigh getByVisceraCode(String visceraCode,String studyNo) {
		String hql="from TblPathPlanVisceraWeigh where visceraCode=:visceraCode and studyNo=:studyNo";
		List<TblPathPlanVisceraWeigh> list=getSession().createQuery(hql)
		                                               .setParameter("visceraCode", visceraCode)
		                                               .setParameter("studyNo", studyNo)
		                                               .list();
		TblPathPlanVisceraWeigh tblPathPlanVisceraWeigh=null;
		if(list!=null&&list.size()>0){
			tblPathPlanVisceraWeigh=list.get(0);
		}
		return tblPathPlanVisceraWeigh;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraListByAnimalTypeAndStudyNo(
			String animalTypeId, String studyNoPara) {
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
					"    ( select visceraCode from CoresStudy.dbo.tblPathPlanVisceraWeigh where studyNo=:studyNoPara) "+
					"  and dv.visceraCode not in  "+
					"    ( select pav.visceraCode  "+
					"      from CoresStudy.dbo.TblPathPlanAttachedViscera as pav "+
					"	   join CoresStudy.dbo.tblPathPlanVisceraWeigh as pvw  "+
					"	   on pvw.id=pav.VisceraWeighPlanID "+
					"	   where pvw.studyNo=:studyNoPara" +
					"     ) "+
					"  order by dv.sn";
		list=getSession().createSQLQuery(sql).setParameter("animalTypeId", animalTypeId)
									        .setParameter("studyNoPara", studyNoPara)
									        .setResultTransformer(new MapResultTransformer())
									        .list();
		return list;
	}

	@Resource
	private TblPathPlanVisceraWeighHisService  tblPathPlanVisceraWeighHisService;
	

	@Resource
	private TblPathPlanAttachedVisceraHisService tblPathPlanAttachedVisceraHisService;
	
	
	
	@Resource
	private TblApplyReviseService tblApplyReviseService;
	
	/**
	 * 试验计划Service
	 */
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	
	public void addSavePathPlanVisceraWeigh(List<TblPathPlanVisceraWeigh> list,
			List<TblPathPlanAttachedViscera> attachedList,String studyNo) {
		List<TblPathPlanVisceraWeigh> originalWeighList=getListByStudyNo(studyNo);
    	TblApplyRevise tblApplyRevise = tblApplyReviseService.getByStudyNo(studyNo);
		List<String> attachedIdList=tblPathPlanAttachedVisceraService.getIdListByStudyNo(studyNo);
		TblStudyPlan studyPlan = tblStudyPlanService.getById(studyNo);
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		if(null!=originalWeighList && originalWeighList.size()>0){
			for(TblPathPlanVisceraWeigh tpvw:originalWeighList){
				if(studyPlan.getStudyState().equals("3")){
					TblPathPlanVisceraWeighHis his = new TblPathPlanVisceraWeighHis();
					his.setId(tblPathPlanVisceraWeighHisService.getKey());
					his.setAttachedVisceraFlag(tpvw.getAttachedVisceraFlag());
					his.setFixedWeighFlag(tpvw.getFixedWeighFlag());
					his.setOldID(tpvw.getId());
					his.setOperate("修改-删除");
					his.setOperateDate(new Date());
					his.setPartVisceraSeparateWeigh(tpvw.getPartVisceraSeparateWeigh());
					his.setSn(tpvw.getSn());
					his.setStudyNo(studyNo);
					his.setTblApplyReviseID(tblApplyRevise.getId());
					his.setVisceraCode(tpvw.getVisceraCode());
					his.setVisceraName(tpvw.getVisceraName());
					his.setVisceraType(tpvw.getVisceraType());
					his.setOperater(tempUser.getUserName());
					tblPathPlanVisceraWeighHisService.save(his);
				}
				getSession().delete(tpvw);
			}
		}
		if(null!=attachedIdList && attachedIdList.size()>0){
			for(String attachedId:attachedIdList){
				TblPathPlanAttachedViscera pav=tblPathPlanAttachedVisceraService.getById(attachedId);
				if(studyPlan.getStudyState().equals("3")){
					TblPathPlanAttachedVisceraHis his = new TblPathPlanAttachedVisceraHis();
					his.setId(tblPathPlanAttachedVisceraHisService.getKey());
					his.setOldID(pav.getId());
					his.setOperate("修改-删除");
					his.setOperateDate(new Date());
					his.setTblApplyReviseID(tblApplyRevise.getId());
					his.setVisceraCode(pav.getVisceraCode());
					his.setVisceraName(pav.getVisceraName());
					his.setVisceraType(pav.getVisceraType());
					his.setVisceraWeighPlanID(pav.getVisceraWeighPlanID());
					his.setOperater(tempUser.getUserName());
					tblPathPlanAttachedVisceraHisService.save(his);
				}
				getSession().delete(pav);
			}
		}
		if(list.size()>0){
			for(TblPathPlanVisceraWeigh t:list){
				if(studyPlan.getStudyState().equals("3")){
					TblPathPlanVisceraWeighHis his = new TblPathPlanVisceraWeighHis();
					his.setId(tblPathPlanVisceraWeighHisService.getKey());
					his.setAttachedVisceraFlag(t.getAttachedVisceraFlag());
					his.setFixedWeighFlag(t.getFixedWeighFlag());
					his.setOldID(t.getId());
					his.setOperate("修改-增加");
					his.setOperateDate(new Date());
					his.setPartVisceraSeparateWeigh(t.getPartVisceraSeparateWeigh());
					his.setSn(t.getSn());
					his.setStudyNo(studyNo);
					his.setTblApplyReviseID(tblApplyRevise.getId());
					his.setVisceraCode(t.getVisceraCode());
					his.setVisceraName(t.getVisceraName());
					his.setVisceraType(t.getVisceraType());
					his.setOperater(tempUser.getUserName());
					tblPathPlanVisceraWeighHisService.save(his);
				}
				getSession().save(t);
			}
		}
		if(attachedList.size()>0){
			for(TblPathPlanAttachedViscera pav:attachedList){
				if(studyPlan.getStudyState().equals("3")){
					TblPathPlanAttachedVisceraHis his = new TblPathPlanAttachedVisceraHis();
					his.setId(tblPathPlanAttachedVisceraHisService.getKey());
					his.setOldID(pav.getId());
					his.setOperate("修改-增加");
					his.setOperateDate(new Date());
					his.setTblApplyReviseID(tblApplyRevise.getId());
					his.setVisceraCode(pav.getVisceraCode());
					his.setVisceraName(pav.getVisceraName());
					his.setVisceraType(pav.getVisceraType());
					his.setVisceraWeighPlanID(pav.getVisceraWeighPlanID());
					his.setOperater(tempUser.getUserName());
					tblPathPlanAttachedVisceraHisService.save(his);
				}
				getSession().save(pav);
			}
		}
		
	}


}
