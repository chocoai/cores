package com.lanen.service.studyplan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.clinicaltest.TestItem;
import com.lanen.model.studyplan.TblAnimal;
import com.lanen.model.studyplan.TblClinicalTestReq;
import com.lanen.model.studyplan.TblClinicalTestReqIndex;
import com.lanen.model.studyplan.TblClinicalTestReqIndex2;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.util.DateUtil;

@Service
public class TblClinicalTestReqServiceImpl extends BaseDaoImpl<TblClinicalTestReq>  implements TblClinicalTestReqService{
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	@Resource
	private TblAnimalService tblAnimalService;
	@Resource
	private TblClinicalTestReqIndex2Service tblClinicalTestReqIndex2Service;
	@Resource
	private DictBioChemService dictBioChemService;
	@Resource
	private DictHematService dictHematService;
	@Resource
	private DictBloodCoagService dictBloodCoagService;
	@Resource
	private DictUrineService dictUrineService;
	@SuppressWarnings("unchecked")
	public List<TblClinicalTestReq> findByDate(String beginDate, String endDate) {
		String beginDateString=beginDate+" 00:00:00";
		String endDateString=endDate+" 23:59:59";
		List<TblClinicalTestReq> list=getSession().createQuery("FROM TblClinicalTestReq t WHERE t.beginDate between ? and ? ORDER BY t.beginDate desc ")
		.setParameter(0, DateUtil.stringToDate(beginDateString, "yyyy-MM-dd HH:mm:ss"))
		.setParameter(1, DateUtil.stringToDate(endDateString, "yyyy-MM-dd HH:mm:ss"))
		.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public TblClinicalTestReq findByStudyNoAndReqNO(String studyNo, int reqNo) {
		TblStudyPlan tblStudyPlan =new TblStudyPlan();
		tblStudyPlan.setStudyNo(studyNo);
		List<TblClinicalTestReq> list=getSession().createQuery("FROM TblClinicalTestReq t WHERE t.tblStudyPlan  = ? AND t.reqNo =? and t.id != '' " )//
		.setParameter(0, tblStudyPlan)//试验计划
		.setParameter(1, reqNo)//申请编号
		.list();
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<TblClinicalTestReq> findByStudyNoAndparentReqNo(String studyNo,
			int parentReqNo) {
		TblStudyPlan tblStudyPlan =new TblStudyPlan();
		tblStudyPlan.setStudyNo(studyNo);
		List<TblClinicalTestReq> list=getSession().createQuery("FROM TblClinicalTestReq t WHERE t.tblStudyPlan  = ? AND t.parentReqNo =? ")//
		.setParameter(0, tblStudyPlan)//试验计划
		.setParameter(1, parentReqNo)//父申请编号
		.list();
		if(null!=list&&list.size()>0){
			return list;
		}else{
			return null;
		}
		
	}
	@SuppressWarnings("unchecked")
	public List<TblStudyPlan> findAllTblStudyPlan() {
		List<TblStudyPlan> list=getSession().createQuery("select distinct t.tblStudyPlan FROM TblClinicalTestReq t  ").list();
		
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<TblClinicalTestReqIndex> getReqIndexByReqNo(TblStudyPlan studyPlan, int reqNo) {
		List<TblClinicalTestReqIndex> retList;
		if(studyPlan!=null&&reqNo!= 0){
			retList = getSession().createQuery("FROM TblClinicalTestReqIndex WHERE tblStudyPlan= ? AND reqNo = ?")
					  .setParameter(0, studyPlan).setParameter(1, reqNo).list();
		}else {
			retList = new ArrayList<TblClinicalTestReqIndex>();
		}
		if(null!=retList && retList.size()>0){
			retList=sortIndexList(retList);
		}
		return retList;
	}

	/**
	 * 保存多条动物信息
	 * @param tblClinicalTestReqIndex2List
	 */
	private void saveClinicalTestReqIndex2s(List<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2List) {
		for(TblClinicalTestReqIndex2 obj : tblClinicalTestReqIndex2List){
			obj.setId(getKey("TblClinicalTestReqIndex2"));
			getSession().save(obj);
//			getSession().merge(obj);
		}
	}

	/**
	 * 保存多条检验指标信息
	 * @param tblClinicalTestReqIndexList
	 */
	private void saveClinicalTestReqIndexs(List<TblClinicalTestReqIndex> tblClinicalTestReqIndexList) {
		for(TblClinicalTestReqIndex obj : tblClinicalTestReqIndexList){
			obj.setId(getKey("TblClinicalTestReqIndex"));
			getSession().save(obj);
//			getSession().merge(obj);
		}
	}

	public void saveOrUpdateClinicalTestReq(TblClinicalTestReq tblClinicalTestReq,List<TblClinicalTestReqIndex> tblClinicalTestReqIndexList,
			List<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2List) {
		if(tblClinicalTestReq != null && tblClinicalTestReq.getReqNo() != 0){
			//编辑操作
			//设置临检申请
			for(int i=0;i<tblClinicalTestReqIndexList.size();i++){
				tblClinicalTestReqIndexList.get(i).setTblClinicalTestReq(tblClinicalTestReq);
				tblClinicalTestReqIndexList.get(i).setReqNo(tblClinicalTestReq.getReqNo());
			}
			for (int j = 0; j < tblClinicalTestReqIndex2List.size(); j++) {
				tblClinicalTestReqIndex2List.get(j).setTblClinicalTestReq(tblClinicalTestReq);
				tblClinicalTestReqIndex2List.get(j).setReqNo(tblClinicalTestReq.getReqNo());
			}
			//保存临检申请基本信息
			getSession().merge(tblClinicalTestReq);
			//保存临检申请单检验指标信息
		
//			getSession().createQuery("DELETE FROM TblClinicalTestReqIndex WHERE tblClinicalTestReq = ?").setParameter(0, tblClinicalTestReq).executeUpdate();
			getSession().createQuery("DELETE FROM TblClinicalTestReqIndex WHERE reqNo = ? and tblStudyPlan =?")
			.setParameter(0, tblClinicalTestReq.getReqNo())
			.setParameter(1, tblClinicalTestReq.getTblStudyPlan())
			.executeUpdate();
			saveClinicalTestReqIndexs(tblClinicalTestReqIndexList);
			//保存临检申请动物编号信息
			
//			getSession().createQuery("DELETE FROM TblClinicalTestReqIndex2 WHERE tblClinicalTestReq = ?").setParameter(0, tblClinicalTestReq).executeUpdate();
			getSession().createQuery("DELETE FROM TblClinicalTestReqIndex2 WHERE reqNo = ? and tblStudyPlan =?")
			.setParameter(0, tblClinicalTestReq.getReqNo())
			.setParameter(1, tblClinicalTestReq.getTblStudyPlan())
			.executeUpdate();
			saveClinicalTestReqIndex2s(tblClinicalTestReqIndex2List);
		}else {//新增操作
			//获取最大申请单号
			Integer reqNo = (Integer) getSession().createQuery("SELECT MAX(reqNo) FROM TblClinicalTestReq WHERE tblStudyPlan = ?").setParameter(0, tblClinicalTestReq.getTblStudyPlan()).uniqueResult();
			//设置下一个申请单号
			if(reqNo!=null && reqNo!=0){
				tblClinicalTestReq.setReqNo(reqNo+1);
			}else {
				tblClinicalTestReq.setReqNo(1);
			}
			//设置主键
			tblClinicalTestReq.setId(getKey("TblClinicalTestReq"));
			//设置临检申请
			for(int i=0;i<tblClinicalTestReqIndexList.size();i++){
				tblClinicalTestReqIndexList.get(i).setTblClinicalTestReq(tblClinicalTestReq);
				tblClinicalTestReqIndexList.get(i).setReqNo(tblClinicalTestReq.getReqNo());
			}
			for (int j = 0; j < tblClinicalTestReqIndex2List.size(); j++) {
				tblClinicalTestReqIndex2List.get(j).setTblClinicalTestReq(tblClinicalTestReq);
				tblClinicalTestReqIndex2List.get(j).setReqNo(tblClinicalTestReq.getReqNo());
			}
			//保存临检申请基本信息
			save(tblClinicalTestReq);
			//保存临检申请单检验指标信息
			saveClinicalTestReqIndexs(tblClinicalTestReqIndexList);
			//保存临检申请动物编号信息
			saveClinicalTestReqIndex2s(tblClinicalTestReqIndex2List);
		}
	}
	public TblClinicalTestReq saveOrUpdateClinicalTestReq2(TblClinicalTestReq tblClinicalTestReq,List<TblClinicalTestReqIndex> tblClinicalTestReqIndexList,
			List<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2List) {
		if(tblClinicalTestReq != null && tblClinicalTestReq.getReqNo() != 0 
				&& !"".equals(tblClinicalTestReq.getId()) && null != tblClinicalTestReq.getId()){
			//编辑操作
			//设置临检申请
			for(int i=0;i<tblClinicalTestReqIndexList.size();i++){
				tblClinicalTestReqIndexList.get(i).setTblClinicalTestReq(tblClinicalTestReq);
				tblClinicalTestReqIndexList.get(i).setReqNo(tblClinicalTestReq.getReqNo());
			}
			for (int j = 0; j < tblClinicalTestReqIndex2List.size(); j++) {
				tblClinicalTestReqIndex2List.get(j).setTblClinicalTestReq(tblClinicalTestReq);
				tblClinicalTestReqIndex2List.get(j).setReqNo(tblClinicalTestReq.getReqNo());
			}
			String studyNo = tblClinicalTestReq.getStudyNo();
			int reqNo  = tblClinicalTestReq.getReqNo();
			
			String sql = "delete CoresStudy.dbo.tblClinicalTestReqIndex"+
						" from CoresStudy.dbo.tblClinicalTestReqIndex as c"+
						" where c.studyNo = :studyNo and c.reqNo = :reqNo ";
			String sql2 = "delete CoresStudy.dbo.tblClinicalTestReqIndex2"+
						" from CoresStudy.dbo.tblClinicalTestReqIndex2 as c"+
						" where c.studyNo = :studyNo and c.reqNo = :reqNo ";
			//删除原先申请的动物和指标
			getSession().createSQLQuery(sql).setParameter("studyNo", studyNo).setParameter("reqNo", reqNo).executeUpdate();
			getSession().createSQLQuery(sql2).setParameter("studyNo", studyNo).setParameter("reqNo", reqNo).executeUpdate();
			
			
			TblClinicalTestReq tblClinicalTestReq_ = getById(tblClinicalTestReq.getId());
			tblClinicalTestReq_.setTestPhase(tblClinicalTestReq.getTestPhase());
			tblClinicalTestReq_.setBeginDate(tblClinicalTestReq.getBeginDate());
			tblClinicalTestReq_.setEndDate(tblClinicalTestReq.getEndDate());
			tblClinicalTestReq_.setTestOther(tblClinicalTestReq.getTestOther());
			tblClinicalTestReq_.setTemp(tblClinicalTestReq.getTemp());
			tblClinicalTestReq_.setParentReqNo(tblClinicalTestReq.getParentReqNo());
			tblClinicalTestReq_.setRemark(tblClinicalTestReq.getRemark());
			tblClinicalTestReq_.setEs(tblClinicalTestReq.getEs());
//			tblClinicalTestReq_.setCreateDate(tblClinicalTestReq.getCreateDate());
			
			//保存临检申请基本信息
			getSession().update(tblClinicalTestReq_);
			
			saveClinicalTestReqIndexs(tblClinicalTestReqIndexList);
			saveClinicalTestReqIndex2s(tblClinicalTestReqIndex2List);
			
			return tblClinicalTestReq_;
		}else if(tblClinicalTestReq != null && tblClinicalTestReq.getReqNo() == 0 
				&& ("".equals(tblClinicalTestReq.getId()) || null == tblClinicalTestReq.getId())) {//新增操作
			//获取最大申请单号
			Integer reqNo = (Integer) getSession().createQuery("SELECT MAX(reqNo) FROM TblClinicalTestReq WHERE tblStudyPlan = ?").setParameter(0, tblClinicalTestReq.getTblStudyPlan()).uniqueResult();
			//设置下一个申请单号
			if(reqNo!=null && reqNo!=0){
				tblClinicalTestReq.setReqNo(reqNo+1);
			}else {
				tblClinicalTestReq.setReqNo(1);
			}
			//设置主键
			tblClinicalTestReq.setId(getKey("TblClinicalTestReq"));
			//设置临检申请
			for(int i=0;i<tblClinicalTestReqIndexList.size();i++){
				tblClinicalTestReqIndexList.get(i).setTblClinicalTestReq(tblClinicalTestReq);
				tblClinicalTestReqIndexList.get(i).setReqNo(tblClinicalTestReq.getReqNo());
			}
			for (int j = 0; j < tblClinicalTestReqIndex2List.size(); j++) {
				tblClinicalTestReqIndex2List.get(j).setTblClinicalTestReq(tblClinicalTestReq);
				tblClinicalTestReqIndex2List.get(j).setReqNo(tblClinicalTestReq.getReqNo());
			}
			//保存临检申请基本信息
			getSession().save(tblClinicalTestReq);
			//保存临检申请单检验指标信息
			saveClinicalTestReqIndexs(tblClinicalTestReqIndexList);
			//保存临检申请动物编号信息
			saveClinicalTestReqIndex2s(tblClinicalTestReqIndex2List);
			return tblClinicalTestReq;
		}else{
			return null;
		}
	}

	
	//先按父关系再按子关系排序FROM TblClinicalTestReq t WHERE t.tblStudyPlan = ? and t.es = 1 order by " +
	//"( case when t.parentReqNo = 0  then t.reqNo else t.parentReqNo  end ) , t.es  "
	@SuppressWarnings("unchecked")
	public List<TblClinicalTestReq> getByStudyPlan(TblStudyPlan tblStudyPlan, String beginDate, String endDate) {
		List<TblClinicalTestReq> list = new ArrayList<TblClinicalTestReq>();
		if(beginDate == null && endDate == null){//temp 0正式的1外部2内部临时的3临时转正
			list=getSession().createQuery("FROM TblClinicalTestReq t WHERE t.tblStudyPlan = ? AND t.temp != 1 order by " +
					" (case when t.parentReqNo = 0 then t.reqNo else t.parentReqNo end ) ,t.reqNo ").setParameter(0, tblStudyPlan).list();
		}else if (beginDate != null && endDate == null) {
			//TO_DO
		}else if (beginDate == null && endDate != null) {
			//TO_DO
		}else {
			//TO_DO
		}
		return list;
	}
	public void saveStudyPlanAndClinicalTestReq(String taskNo,
			String animalType, String clinicalTestDirector, String client,
			List<String> animalIdList, List<TestItem> indexList,int isValidation) {
		if("".equals(taskNo)||"".equals(animalType)||"".equals(clinicalTestDirector)||"".equals(client)){
			return;//信息不全，返回
		}
		if(animalIdList.size()<1||indexList.size()<1){
			return;//信息不全，返回
		}
		
		TblStudyPlan tblStudyPlan = new TblStudyPlan();
		tblStudyPlan.setStudyNo(taskNo);//课题编号
		tblStudyPlan.setAnimalType(animalType);//动物种类
		tblStudyPlan.setAnimalStrain(animalType);
		tblStudyPlan.setClinicalTestDirector(clinicalTestDirector);//临检负责人
		tblStudyPlan.setStudyStartDate(new Date());//试验启动日期
		tblStudyPlan.setTemp(1);//是临时
		tblStudyPlan.setClient(client);
		tblStudyPlan.setIsValidation(isValidation==1 ? 1 : 0);
		getSession().save(tblStudyPlan);//保存试验计划
		
		TblClinicalTestReq tblClinicalTestReq= new TblClinicalTestReq();
		tblClinicalTestReq.setStudyNo(taskNo);
		tblClinicalTestReq.setEs(1);
		tblClinicalTestReq.setReqNo(1);//申请编号
		tblClinicalTestReq.setCreateDate(new Date());
		tblClinicalTestReq.setBeginDate(new Date());
		tblClinicalTestReq.setTemp(1);
		tblClinicalTestReq.setTblStudyPlan(tblStudyPlan);
		this.save(tblClinicalTestReq);//保存 临检申请
		
		List<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2List = new ArrayList<TblClinicalTestReqIndex2>();
		int aniSerialNum=1;//动物序号
		for(String animalId:animalIdList){
			TblClinicalTestReqIndex2 entity = new TblClinicalTestReqIndex2();
			entity.setAnimalId(animalId);
			entity.setAniSerialNum(aniSerialNum);
			entity.setReqNo(1);
			entity.setTblClinicalTestReq(tblClinicalTestReq);
			entity.setTblStudyPlan(tblStudyPlan);
			tblClinicalTestReqIndex2List.add(entity);
			aniSerialNum++;
		}
		saveClinicalTestReqIndex2s(tblClinicalTestReqIndex2List);//保存动物列表
		
		List<TblClinicalTestReqIndex> tblClinicalTestReqIndexList = new ArrayList<TblClinicalTestReqIndex>();
		for(TestItem obj:indexList){
			TblClinicalTestReqIndex entity = new TblClinicalTestReqIndex();
			entity.setTestIndex(obj.getIndex2());
			entity.setTestIndexAbbr(obj.getAbbr());
			entity.setTestitem(obj.getTestItem());
			entity.setReqNo(1);
			entity.setTblClinicalTestReq(tblClinicalTestReq);
			entity.setTblStudyPlan(tblStudyPlan);
			tblClinicalTestReqIndexList.add(entity);
		}
		saveClinicalTestReqIndexs(tblClinicalTestReqIndexList);
	}

	@Override
	public void save(TblClinicalTestReq entity) {
		entity.setId(getKey("TblClinicalTestReq"));
		super.save(entity);
	}

	@SuppressWarnings("unchecked")
	public List<TblClinicalTestReq> findTempReqWithyear(String year) {
		int index =year.indexOf("之前");
		if(index<0){
			String beginDateString=year+"-01-01 00:00:00";
			String endDateString=year+"-12-31 23:59:59";
			
			Date beginDate=DateUtil.stringToDate(beginDateString, "yyyy-MM-dd HH:mm:ss");
			Date endDate=DateUtil.stringToDate(endDateString, "yyyy-MM-dd HH:mm:ss");
			
			List<TblClinicalTestReq> list= getSession().createQuery("FROM TblClinicalTestReq t WHERE t.temp > 0 and t.beginDate between ? and ?   ORDER BY t.studyNo ,t.reqNo ")
			.setParameter(0, beginDate)//开始时间
			.setParameter(1, endDate)//结束时间
			.list();
			return list;
		}else{
			String maxDateString =year.substring(0, index)+"-01-01 00:00:00";
			Date maxDate=DateUtil.stringToDate(maxDateString, "yyyy-MM-dd HH:mm:ss");
			List<TblClinicalTestReq> list= getSession().createQuery("FROM TblClinicalTestReq t WHERE t.temp > 0 and  t.beginDate <?  ORDER BY t.studyNo ,t.reqNo  ")
			.setParameter(0, maxDate)//开始时间
			.list();
			return list;
		}
	}

	public void updateStudyPlanAndClinicalTestReq(String taskNo,
			String animalType, String clinicalTestDirector, String client,
			List<String> animalIdList, List<TestItem> indexList,int isValidation) {
		if("".equals(taskNo)||"".equals(animalType)||"".equals(clinicalTestDirector)||"".equals(client)){
			return;//信息不全，返回
		}
		if(animalIdList.size()<1||indexList.size()<1){
			return;//信息不全，返回
		}
		//查询试验计划
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(taskNo);
		tblStudyPlan.setAnimalType(animalType);//动物种类
		tblStudyPlan.setClinicalTestDirector(clinicalTestDirector);//临检负责人
		tblStudyPlan.setClient(client);
		tblStudyPlan.setIsValidation(isValidation == 1 ? 1 : 0 );
		//更新试验计划
		tblStudyPlanService.update(tblStudyPlan);
//		
		//得到临检申请
		TblClinicalTestReq tblClinicalTestReq= findByStudyNoAndReqNO(taskNo, 1);
		tblClinicalTestReq.setStudyNo(taskNo);
		//
		tblClinicalTestReq.setEs(1);
		tblClinicalTestReq.setTblStudyPlan(tblStudyPlan);
		update(tblClinicalTestReq);

		//先删除原      临检申请单检验指标信息
		getSession().createQuery("DELETE FROM TblClinicalTestReqIndex WHERE tblClinicalTestReq = ?").setParameter(0, tblClinicalTestReq).executeUpdate();
		List<TblClinicalTestReqIndex> tblClinicalTestReqIndexList = new ArrayList<TblClinicalTestReqIndex>();
		for(TestItem obj:indexList){
			TblClinicalTestReqIndex entity = new TblClinicalTestReqIndex();
			entity.setTestIndex(obj.getIndex2());
			entity.setTestIndexAbbr(obj.getAbbr());
			entity.setTestitem(obj.getTestItem());
			entity.setReqNo(1);
			entity.setTblClinicalTestReq(tblClinicalTestReq);
			entity.setTblStudyPlan(tblStudyPlan);
			tblClinicalTestReqIndexList.add(entity);
		}
		//保存检验指标列表
		saveClinicalTestReqIndexs(tblClinicalTestReqIndexList);
		
		
//		//先删除原     临检申请动物编号信息
//		getSession().createQuery("DELETE FROM TblClinicalTestReqIndex2 WHERE tblClinicalTestReq = ?").setParameter(0, tblClinicalTestReq).executeUpdate();

		//		animalIdList   新动物列表
		List<String> animalIdList1=new ArrayList<String>();  //原动物列表
		List<String> animalIdList2=new ArrayList<String>();  //   新动物列表中的 新动物(待添加的)
		List<String> animalIdList3=new ArrayList<String>();  //   原动物列表中的有，而新动物列表中没有的动物（待删除的动物）
		
		Set<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2Set =tblClinicalTestReq.getTblClinicalTestReqIndex2s();
		List<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2List = new ArrayList<TblClinicalTestReqIndex2>(tblClinicalTestReqIndex2Set);
		for(TblClinicalTestReqIndex2 tblClinicalTestReqIndex2:tblClinicalTestReqIndex2List){
			animalIdList1.add(tblClinicalTestReqIndex2.getAnimalId());//原动物列表
		}
		
		for(String newAnimalId:animalIdList){
			if(!animalIdList1.contains(newAnimalId)){
				animalIdList2.add(newAnimalId);//新动物列表中的 新动物
			}
		}
		for(String oldAnimalId:animalIdList1){
			if(!animalIdList.contains(oldAnimalId)){
				animalIdList3.add(oldAnimalId);//原动物列表中的有，而新动物列表中没有的动物（待删除的动物）
			}
		}
		// 删除     原动物列表中的有，而新动物列表中没有的动物（待删除的动物）
		if(null!=animalIdList3 && animalIdList3.size()>0){
			for(String animalId:animalIdList3){
				getSession().createQuery("DELETE FROM TblClinicalTestReqIndex2 t WHERE t.tblClinicalTestReq = ? and t.animalId = ? ")//
				.setParameter(0, tblClinicalTestReq)//
				.setParameter(1, animalId)
				.executeUpdate();
			}
		}
		
		//保存     新动物列表中的 新动物(待添加的)
		if(null!=animalIdList2 && animalIdList2.size()>0){
			List<TblClinicalTestReqIndex2> newTblClinicalTestReqIndex2List = new ArrayList<TblClinicalTestReqIndex2>();
			int aniSerialNum = getMaxAniSerialNum(tblClinicalTestReq);
			for(String animalId:animalIdList2){
				aniSerialNum++;
				TblClinicalTestReqIndex2 entity = new TblClinicalTestReqIndex2();
				entity.setAnimalId(animalId);
				entity.setAniSerialNum(aniSerialNum);
				entity.setReqNo(1);
				entity.setTblClinicalTestReq(tblClinicalTestReq);
				entity.setTblStudyPlan(tblStudyPlan);
				newTblClinicalTestReqIndex2List.add(entity);
			}
			//保存动物列表
			saveClinicalTestReqIndex2s(newTblClinicalTestReqIndex2List);
		}
		
		
		
		
		
		
	}

	public int getMaxAniSerialNum(TblClinicalTestReq tblClinicalTestReq) {
		Integer aniSerialNum=0;
		if(null!=tblClinicalTestReq){
			aniSerialNum= (Integer) getSession().createQuery("select max(t.aniSerialNum)  FROM TblClinicalTestReqIndex2 t WHERE t.tblClinicalTestReq = ? )")//
			.setParameter(0, tblClinicalTestReq)//
			.uniqueResult();
		}
		return aniSerialNum.intValue();
	}

	public int getMaxReqNoByStudyPlan(TblStudyPlan tblStudyPlan) {
		Integer reqNo = (Integer) getSession().createQuery("SELECT MAX(reqNo) FROM TblClinicalTestReq WHERE tblStudyPlan = ?")//
		.setParameter(0, tblStudyPlan)//
		.uniqueResult();
		return reqNo != null ? reqNo:0;
	}

	@SuppressWarnings("unchecked")
	public TblClinicalTestReq getByStudyPlanReqNo(TblStudyPlan tblStudyPlan,
			int recurrentReqNo) {
		List<TblClinicalTestReq> list= getSession().createQuery("FROM TblClinicalTestReq t WHERE t.temp = 1 and t.tblStudyPlan = ? and t.reqNo = ? ")
		.setParameter(0, tblStudyPlan)//
		.setParameter(1, recurrentReqNo)//
		.list();
		if(null!=list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<TblClinicalTestReq> getSetByStudyPlan(TblStudyPlan studyPlan) {
		List<TblClinicalTestReq> list= getSession().createQuery("FROM TblClinicalTestReq t WHERE t.tblStudyPlan = ? and t.es = 1 order by " +
				"( case when t.parentReqNo = 0  then t.reqNo else t.parentReqNo  end ) , t.reqNo  ")
		.setParameter(0, studyPlan)//
		.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblClinicalTestReqIndex2> getTblClinicalTestReqIndex2ListByTblClinicalTestReq(
			TblClinicalTestReq tblClinicalTestReq) {
		List<TblClinicalTestReqIndex2> list= getSession().createQuery("FROM TblClinicalTestReqIndex2 t WHERE t.tblClinicalTestReq = ? order by " +
				" case when t.animalCode is null or t.animalCode ='' then '0000' else t.animalCode end , t.aniSerialNum ")
		.setParameter(0, tblClinicalTestReq)//
		.list();
		return list;
	}
	/**
	 * 排序：检测项目    指标
	 * @param list
	 * @return
	 */
	private List<TblClinicalTestReqIndex> sortIndexList(
			List<TblClinicalTestReqIndex> list) {
		final Map<String,Integer> bioChemMap = dictBioChemService.getMap();
		final Map<String,Integer> bloodCoagMap = dictBloodCoagService.getMap();
		final Map<String,Integer> hematMap = dictHematService.getMap();
		final Map<String,Integer> urineMap = dictUrineService.getMap();
		Collections.sort(list,new Comparator<TblClinicalTestReqIndex>(){
			//检测项目     检验编号    检验指标
			public int compare(TblClinicalTestReqIndex obj1,
					TblClinicalTestReqIndex obj2) {
				if(obj1.getTestitem() != obj2.getTestitem()){
					return obj1.getTestitem() - obj2.getTestitem();
				}else{
						int testItem=obj1.getTestitem();
						if(!obj1.getTestIndexAbbr().equals(obj2.getTestIndexAbbr())){
							if(null == bioChemMap.get(obj1.getTestIndexAbbr())){
							     return 1;
								}
							  if(null == bioChemMap.get(obj2.getTestIndexAbbr())){
								  return -1;
							  }
							switch (testItem) {   //TODO
							case 1:
								return bioChemMap.get(obj1.getTestIndexAbbr())-bioChemMap.get(obj2.getTestIndexAbbr());
							case 2:
								return hematMap.get(obj1.getTestIndexAbbr())-hematMap.get(obj2.getTestIndexAbbr());
							case 3:
								return bloodCoagMap.get(obj1.getTestIndexAbbr())-bloodCoagMap.get(obj2.getTestIndexAbbr());
							case 4:
								return urineMap.get(obj1.getTestIndexAbbr())-urineMap.get(obj2.getTestIndexAbbr());
							default:
								break;
							}
						}else{
							return 0;
						}
				}
				return 0;
			}
			
		});
		return list;
	}

	public int saveTempClinicalTestReq(TblStudyPlan tblStudyPlan,
			List<String> animalIdList, List<TestItem> indexList) {
		if(null == tblStudyPlan || null == animalIdList || null ==indexList ){
			return 0;//信息不全，返回
		}
		if(animalIdList.size()<1||indexList.size()<1){
			return 0;//信息不全，返回
		}
		int reqNo = getMaxReqNoByStudyPlan(tblStudyPlan);
		TblClinicalTestReq tblClinicalTestReq= new TblClinicalTestReq();
		tblClinicalTestReq.setStudyNo(tblStudyPlan.getStudyNo());
		tblClinicalTestReq.setEs(1);
		tblClinicalTestReq.setReqNo(reqNo+1);//申请编号
		tblClinicalTestReq.setCreateDate(new Date());
		tblClinicalTestReq.setBeginDate(new Date());
		tblClinicalTestReq.setTemp(2);//内部临时
		tblClinicalTestReq.setTblStudyPlan(tblStudyPlan);
		tblClinicalTestReq.setId(getKey("TblClinicalTestReq"));
		getSession().save(tblClinicalTestReq);//保存 临检申请
		
		List<TblAnimal> tblAnimalList = tblAnimalService.getAllByStudyNo(tblStudyPlan);
		//动物Id号：动物编号
		Map<String,String> animalIdCodeMap = new HashMap<String,String>();
		//动物Id号：性别
		Map<String,Integer> animalIdGenderMap = new HashMap<String,Integer>();
		if(null != tblAnimalList){
			for(TblAnimal obj:tblAnimalList){
				animalIdCodeMap.put(obj.getAnimalId(), obj.getAnimalCode());
				animalIdGenderMap.put(obj.getAnimalId(), obj.getGender());
			}
		}
		List<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2List = new ArrayList<TblClinicalTestReqIndex2>();
		int aniSerialNum=1;//动物序号
		for(String animalId:animalIdList){
			TblClinicalTestReqIndex2 entity = new TblClinicalTestReqIndex2();
			entity.setAnimalId(animalId);
			entity.setAnimalCode(animalIdCodeMap.get(animalId));
			entity.setGender(animalIdGenderMap.get(animalId) ==null ? 0:animalIdGenderMap.get(animalId) ) ;
			entity.setAniSerialNum(aniSerialNum);
			entity.setReqNo(reqNo+1);
			entity.setTblClinicalTestReq(tblClinicalTestReq);
			entity.setTblStudyPlan(tblStudyPlan);
			tblClinicalTestReqIndex2List.add(entity);
			aniSerialNum++;
		}
		saveClinicalTestReqIndex2s(tblClinicalTestReqIndex2List);//保存动物列表
		
		List<TblClinicalTestReqIndex> tblClinicalTestReqIndexList = new ArrayList<TblClinicalTestReqIndex>();
		for(TestItem obj:indexList){
			TblClinicalTestReqIndex entity = new TblClinicalTestReqIndex();
			entity.setTestIndex(obj.getIndex2());
			entity.setTestIndexAbbr(obj.getAbbr());
			entity.setTestitem(obj.getTestItem());
			entity.setReqNo(reqNo+1);
			entity.setTblClinicalTestReq(tblClinicalTestReq);
			entity.setTblStudyPlan(tblStudyPlan);
			tblClinicalTestReqIndexList.add(entity);
		}
		saveClinicalTestReqIndexs(tblClinicalTestReqIndexList);
		return reqNo+1;
	}

	public void updateTempClinicalTestReq(
			TblClinicalTestReq tblClinicalTestReq, List<String> animalIdList,
			List<TestItem> indexList) {

		if(tblClinicalTestReq == null || animalIdList == null || indexList==null ){
			return;//信息不全，返回
		}
		if(animalIdList.size()<1||indexList.size()<1){
			return;//信息不全，返回
		}
		//查询试验计划
		String studyNo = tblClinicalTestReq.getStudyNo();
		int reqNo = tblClinicalTestReq.getReqNo();
		tblClinicalTestReq = findByStudyNoAndReqNO(studyNo, reqNo);
		TblStudyPlan tblStudyPlan = tblClinicalTestReq.getTblStudyPlan();
		
//		

		//先删除原      临检申请单检验指标信息
		getSession().createQuery("DELETE FROM TblClinicalTestReqIndex as t WHERE t.reqNo = :reqNo and t.tblStudyPlan = :tblStudyPlan ")
		.setParameter("reqNo", reqNo)
		.setParameter("tblStudyPlan", tblStudyPlan)
		.executeUpdate();
		List<TblClinicalTestReqIndex> tblClinicalTestReqIndexList = new ArrayList<TblClinicalTestReqIndex>();
		for(TestItem obj:indexList){
			TblClinicalTestReqIndex entity = new TblClinicalTestReqIndex();
			entity.setTestIndex(obj.getIndex2());
			entity.setTestIndexAbbr(obj.getAbbr());
			entity.setTestitem(obj.getTestItem());
			entity.setReqNo(tblClinicalTestReq.getReqNo());
			entity.setTblClinicalTestReq(tblClinicalTestReq);
			entity.setTblStudyPlan(tblStudyPlan);
			tblClinicalTestReqIndexList.add(entity);
		}
		//保存检验指标列表
		saveClinicalTestReqIndexs(tblClinicalTestReqIndexList);
		
		List<TblAnimal> tblAnimalList = tblAnimalService.getNoDieByStudyNo(tblStudyPlan);
		//动物Id号：动物编号
		Map<String,String> animalIdCodeMap = new HashMap<String,String>();
		//动物Id号：性别
		Map<String,Integer> animalIdGenderMap = new HashMap<String,Integer>();
		if(null != tblAnimalList){
			for(TblAnimal obj:tblAnimalList){
				animalIdCodeMap.put(obj.getAnimalId(), obj.getAnimalCode());
				animalIdGenderMap.put(obj.getAnimalId(), obj.getGender());
			}
		}
		
//		//先删除原     临检申请动物编号信息
//		getSession().createQuery("DELETE FROM TblClinicalTestReqIndex2 WHERE tblClinicalTestReq = ?").setParameter(0, tblClinicalTestReq).executeUpdate();

		//		animalIdList   新动物列表
		List<String> animalIdList1=new ArrayList<String>();  //原动物列表
		List<String> animalIdList2=new ArrayList<String>();  //   新动物列表中的 新动物(待添加的)
		List<String> animalIdList3=new ArrayList<String>();  //   原动物列表中的有，而新动物列表中没有的动物（待删除的动物）
		
		//懒加载异常
//		Set<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2Set =tblClinicalTestReq.getTblClinicalTestReqIndex2s();
//		List<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2List = new ArrayList<TblClinicalTestReqIndex2>(tblClinicalTestReqIndex2Set);
		List<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2List = tblClinicalTestReqIndex2Service.findByStudyNoAndReqNO(tblClinicalTestReq.getStudyNo(), tblClinicalTestReq.getReqNo());
		for(TblClinicalTestReqIndex2 tblClinicalTestReqIndex2:tblClinicalTestReqIndex2List){
			animalIdList1.add(tblClinicalTestReqIndex2.getAnimalId());//原动物列表
		}
		
		for(String newAnimalId:animalIdList){
			if(!animalIdList1.contains(newAnimalId)){
				animalIdList2.add(newAnimalId);//新动物列表中的 新动物
			}
		}
		for(String oldAnimalId:animalIdList1){
			if(!animalIdList.contains(oldAnimalId)){
				animalIdList3.add(oldAnimalId);//原动物列表中的有，而新动物列表中没有的动物（待删除的动物）
			}
		}
		// 删除     原动物列表中的有，而新动物列表中没有的动物（待删除的动物）
		if(null!=animalIdList3 && animalIdList3.size()>0){
			for(String animalId:animalIdList3){
				getSession().createQuery("DELETE FROM TblClinicalTestReqIndex2 t WHERE t.tblClinicalTestReq = ? and t.animalId = ? ")//
				.setParameter(0, tblClinicalTestReq)//
				.setParameter(1, animalId)
				.executeUpdate();
			}
		}
		
		//保存     新动物列表中的 新动物(待添加的)
		if(null!=animalIdList2 && animalIdList2.size()>0){
			List<TblClinicalTestReqIndex2> newTblClinicalTestReqIndex2List = new ArrayList<TblClinicalTestReqIndex2>();
			int aniSerialNum = getMaxAniSerialNum(tblClinicalTestReq);
			for(String animalId:animalIdList2){
				aniSerialNum++;
				TblClinicalTestReqIndex2 entity = new TblClinicalTestReqIndex2();
				entity.setAnimalId(animalId);
				entity.setAnimalCode(animalIdCodeMap.get(animalId));
				entity.setGender(animalIdGenderMap.get(animalId));
				entity.setAniSerialNum(aniSerialNum);
				entity.setReqNo(tblClinicalTestReq.getReqNo());
				entity.setTblClinicalTestReq(tblClinicalTestReq);
				entity.setTblStudyPlan(tblStudyPlan);
				newTblClinicalTestReqIndex2List.add(entity);
			}
			//保存动物列表
			saveClinicalTestReqIndex2s(newTblClinicalTestReqIndex2List);
		}
		
		
		
		
		
		
	
		
	}

	
	
	
}
