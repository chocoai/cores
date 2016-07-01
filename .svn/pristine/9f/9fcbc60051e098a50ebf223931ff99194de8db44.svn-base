package com.lanen.service.path;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.User;
import com.lanen.model.path.TblPathPlanCheck;
import com.lanen.model.path.TblPathPlanCheckHis;
import com.lanen.model.studyplan.TblApplyRevise;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.studyplan.TblApplyReviseService;
import com.opensymphony.xwork2.ActionContext;
/**
 * 病理计划-脏器/组织学检查     serviceImpl 
 * @author 曾锋
 */
@Service
public class TblPathPlanCheckServiceImpl extends BaseDaoImpl<TblPathPlanCheck> implements
		TblPathPlanCheckService {

	@SuppressWarnings("unchecked")
	public List<TblPathPlanCheck> getListByStudyNo(String studyNoPara) {
		String hql="from TblPathPlanCheck where studyNo=:studyNoPara";
		List<TblPathPlanCheck> list=getSession().createQuery(hql)
												.setParameter("studyNoPara", studyNoPara)
												.list();
		return list;
	}

	public int getSn(String studyNo) {
		int sn=0;
		String sql = "select Max(ppvw.sn)"+
					" from tblPathPlanCheck as ppvw where ppvw.studyNo=:studyNo";
		Integer maxSn = (Integer) getSession().createSQLQuery(sql).setParameter("studyNo", studyNo).uniqueResult();
		if(maxSn!=null&&maxSn!=0){
			sn=maxSn+1;
		}else{
			sn=1;
		}
		return sn;
	}

	@SuppressWarnings("unchecked")
	public TblPathPlanCheck getByVisceraCode(String visceraCode,String studyNo) {
		String hql="from TblPathPlanCheck where visceraCode=:visceraCode and studyNo=:studyNo";
		List<TblPathPlanCheck> list=getSession().createQuery(hql)
		                                        .setParameter("visceraCode", visceraCode)
		                                        .setParameter("studyNo", studyNo)
		                                        .list();
		TblPathPlanCheck tblPathPlanCheck=null;
		if(list!=null&&list.size()>0){
			tblPathPlanCheck=list.get(0);
		}
		return tblPathPlanCheck;
	}

	@Resource
	private TblPathPlanCheckHisService  tblPathPlanCheckHisService;
	
	@Resource
	private TblApplyReviseService tblApplyReviseService;
	
	public void addSavePathPlanCheck(TblStudyPlan tblStudyPlan,
			List<TblPathPlanCheck> list) {
		String studyNoPara=tblStudyPlan.getStudyNo();
    	TblApplyRevise tblApplyRevise = tblApplyReviseService.getByStudyNo(studyNoPara);
    	
		//保存病理计划-脏器/组织学检查前，先查找已设置的计划并删除
		List<TblPathPlanCheck> originalList=getListByStudyNo(studyNoPara);
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		if(null!=originalList && originalList.size()>0){
			for(TblPathPlanCheck tblPathPlanCheck :originalList){
				if(tblStudyPlan.getStudyState().equals("3")){
					TblPathPlanCheckHis his = new TblPathPlanCheckHis();
					his.setId(tblPathPlanCheckHisService.getKey());
					his.setAtanomyCheckFlag(tblPathPlanCheck.getAtanomyCheckFlag());
					his.setGender(tblPathPlanCheck.getGender());
					his.setHistopathCheckFlag(tblPathPlanCheck.getHistopathCheckFlag());
					his.setOldID(tblPathPlanCheck.getId());
					his.setOperate("修改-删除");
					his.setOperateDate(new Date());
					his.setSn(tblPathPlanCheck.getSn());
					his.setStudyNo(tblPathPlanCheck.getStudyNo());
					his.setTblApplyReviseID(tblApplyRevise.getId());
					his.setVisceraCode(tblPathPlanCheck.getVisceraCode());
					his.setVisceraFixedFlag(tblPathPlanCheck.getVisceraFixedFlag());
					his.setVisceraName(tblPathPlanCheck.getVisceraName());
					his.setVisceraType(tblPathPlanCheck.getVisceraType());
					his.setOperater(tempUser.getUserName());
					tblPathPlanCheckHisService.save(his);
				}
				
				getSession().delete(tblPathPlanCheck);
			}
		}
		for(TblPathPlanCheck t:list){
			if(tblStudyPlan.getStudyState().equals("3")){
				TblPathPlanCheckHis his = new TblPathPlanCheckHis();
				his.setId(tblPathPlanCheckHisService.getKey());
				his.setAtanomyCheckFlag(t.getAtanomyCheckFlag());
				his.setGender(t.getGender());
				his.setHistopathCheckFlag(t.getHistopathCheckFlag());
				his.setOldID(t.getId());
				his.setOperate("修改-增加");
				his.setOperateDate(new Date());
				his.setSn(t.getSn());
				his.setStudyNo(t.getStudyNo());
				his.setTblApplyReviseID(tblApplyRevise.getId());
				his.setVisceraCode(t.getVisceraCode());
				his.setVisceraFixedFlag(t.getVisceraFixedFlag());
				his.setVisceraName(t.getVisceraName());
				his.setVisceraType(t.getVisceraType());
				his.setOperater(tempUser.getUserName());
				tblPathPlanCheckHisService.save(his);
			}
			getSession().save(t);
		}
		getSession().update(tblStudyPlan);
		
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraListByAnimalTypeAndStudyNo(
			String animalTypeId, String studyNoPara) {
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
					" ( select visceraCode from CoresStudy.dbo.tblPathPlanCheck where studyNo=:studyNoPara) "+
					" order by dv.sn";
		list=getSession().createSQLQuery(sql).setParameter("animalTypeId", animalTypeId)
		                                     .setParameter("studyNoPara", studyNoPara)
		                                     .setResultTransformer(new MapResultTransformer())
		                                     .list();
		return list;
	}


}
