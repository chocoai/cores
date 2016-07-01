package com.lanen.service.clinicaltest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.clinicaltest.TblClinicalTestData;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblSpecimen;
import com.lanen.model.studyplan.TblClinicalTestReq;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.studyplan.TblClinicalTestReqService;
import com.lanen.util.DateUtil;

@Service
public class TblSpecimenServiceImpl extends BaseDaoImpl<TblSpecimen> implements TblSpecimenService{

	@Resource
	private GetIdService getIdService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblClinicalTestReqService tblClinicalTestReqService;
	public void saveList(List<TblSpecimen> list, String realName) {
		Date date =new Date();
		TblES tblES=new TblES();
		String esId=getIdService.getKey("TblES");
		tblES.setEsId(esId);
		tblES.setSigner(realName);
		tblES.setEsType(1);
		tblES.setEsTypeDesc("标本接收");
		tblES.setDateTime(date);
		tblESService.save(tblES);//保存  电子签名
		
//		List<TblESLink> tblESLinkList=new ArrayList<TblESLink>();
		for(TblSpecimen obj:list){
			//标本
			obj.setRecDate(date);
			obj.setRecTime(date);
			String specimenId=getIdService.getKey("TblSpecimen");
			obj.setSpecimenId(specimenId);
			save(obj);
			//签名链接表
			TblESLink tblESLink =new TblESLink();
			tblESLink.setLinkId(getIdService.getKey("TblESLink"));
			tblESLink.setDataId(specimenId);
			tblESLink.setTableName("TblSpecimen");
			tblESLink.setEsType(1);
			tblESLink.setEsTypeDesc("标本接收");
			tblESLink.setRecordTime(date);
			tblESLink.setTblES(tblES);
			tblESLinkService.save(tblESLink);
		}
	}
	@SuppressWarnings("unchecked")
	public String getToDayMostCode() {
		
		String dateString=DateUtil.dateToString(new Date(), "yyyy-MM-dd");
		Date minDate= DateUtil.stringToDate(dateString+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
		Date maxDate= DateUtil.stringToDate(dateString+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		List<TblSpecimen> list=getSession().//
		createQuery("FROM TblSpecimen t WHERE t.testItem = 4  AND t.recDate BETWEEN  ? AND ? ORDER BY t.specimenId DESC ")//
		.setParameter(0, minDate)
		.setParameter(1, maxDate)
		.list();
		if(null!=list&&list.size()>0){
			return list.get(0).getSpecimenCode();
		}else{
			return "0000";
		}
	}
	@SuppressWarnings("unchecked")
	public List<TblSpecimen> findAllOrderByRecdateDese() {
		List<TblSpecimen> list=getSession().createQuery("FROM TblSpecimen t  ORDER BY t.specimenId DESC ")//
		.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<TblStudyPlan> findAllTblStudyPlan() {
		List<TblStudyPlan> list=null;
		list=getSession().createQuery("select distinct t.tblStudyPlan From TblSpecimen t  ").list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<TblSpecimen> findByCondition(String studyNo, String reqNo,
			String animalId, String testItemString, String specimenCode,
			String recDate) {
		List<TblSpecimen> list=null;
		String hql="FROM TblSpecimen t WHERE 1=1 ";
		String orderby=" ORDER BY t.tblStudyPlan ,t.reqNo,t.testItem ,t.aniSerialNum";
		if(!"".equals(recDate)){//接收日期
			hql=hql+" AND  t.recDate BETWEEN  '"+recDate+" 00:00:00' AND  '"+recDate+" 23:59:59' ";
		}
		if(!"".equals(specimenCode)){//检验编号
			hql=hql+" AND t.specimenCode like '"+specimenCode+"%' ";
		}
			
		if(null!=testItemString&&!"".equals(testItemString)){//检查项目
			int testItem=0;
			if("血液生化检查".equals(testItemString)){
				testItem=1;
			}else if("血液常规检查".equals(testItemString)){
				testItem=2;
			}else if("凝血功能检查".equals(testItemString)){
				testItem=3;
			}else {
				testItem=4;
			}
			hql=hql+" AND t.testItem ="+testItem+" ";
		}
		
		if(null!=animalId&&!"".equals(animalId)){
			hql=hql+" AND t.animalId = '"+animalId+"' ";
		}
		
		if(null!=studyNo&&!"".equals(studyNo)){//课题编号
			if(null!=reqNo&&!"".equals(reqNo)){//申请编号
				TblClinicalTestReq tblClinicalTestReq = tblClinicalTestReqService.findByStudyNoAndReqNO(studyNo, Integer.parseInt(reqNo)) ;
				hql=hql+" AND t.tblClinicalTestReq = ?  ";
				list=getSession().createQuery(hql+orderby).setParameter(0, tblClinicalTestReq).list();
			}else{//无申请编号  ，仅课题bianhao
				TblStudyPlan tblStudyPlan=new TblStudyPlan();
				tblStudyPlan.setStudyNo(studyNo);
				hql=hql+" AND t.tblStudyPlan = ?  ";
				list=getSession().createQuery(hql+orderby).setParameter(0, tblStudyPlan).list();
			}
		}else{
			list=getSession().createQuery(hql+orderby).list();
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	public TblSpecimen getBySpecimenCodeTestItemTime(String specimenCode,
			int testItem, String collectionTime) {
		Date date= DateUtil.stringToDate(collectionTime, "yyyyMMddHHmmss");
		List<TblSpecimen> list=getSession().createQuery("FROM TblSpecimen t WHERE t.specimenCode= ? AND t.testItem=?   AND t.recTime <?  ORDER BY t.recTime DESC ")//
		.setParameter(0, specimenCode)
		.setParameter(1, testItem)
		.setParameter(2, date)
		.list();
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<TblSpecimen> findByTblClinicalTestReq(
			TblClinicalTestReq tblClinicalTestReq2) {
		List<TblSpecimen> list=getSession().createQuery("FROM TblSpecimen t WHERE t.tblClinicalTestReq = ?   ")//
		.setParameter(0, tblClinicalTestReq2)
		.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public boolean isExistBySpecimen(String studyNo, int reqNo,
			String animalId, String recDate, String specimenCode) {
		int ss=Integer.parseInt(1+recDate.substring(17,19));
		int endss=101+ss;
		String endssStr=endss+"";
		
		Date beginDate=DateUtil.stringToDate(recDate, "yyyy-MM-dd HH:mm:ss"); 
		Date endDate=DateUtil.stringToDate(recDate.substring(0,17)+endssStr.substring(1), "yyyy-MM-dd HH:mm:ss"); 
		TblClinicalTestReq tblClinicalTestReq=tblClinicalTestReqService.findByStudyNoAndReqNO(studyNo, reqNo);
		List<TblSpecimen> list=getSession().//
		createQuery("FROM TblSpecimen t WHERE  t.tblClinicalTestReq = ? AND t.recDate BETWEEN ? AND ? AND t.animalId =? AND t.specimenCode=? ")//
		.setParameter(0, tblClinicalTestReq)
		.setParameter(1, beginDate)
		.setParameter(2, endDate)
		.setParameter(3, animalId)
		.setParameter(4, specimenCode)
		.list();
		if(null!=list&&list.size()>0){
			TblSpecimen tblSpecimen=list.get(0);
			List<TblClinicalTestData> tblClinicalTestDataList=getSession().createQuery("FROM TblClinicalTestData t WHERE t.tblSpecimen = ?")
			.setParameter(0, tblSpecimen)
			.list();
			if(null!=tblClinicalTestDataList && tblClinicalTestDataList.size()>0){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public String getIdBySpecimen(String studyNo, int reqNo, String animalId,
			String recDate, String specimenCode) {
		int ss=Integer.parseInt(1+recDate.substring(17,19));
		int endss=101+ss;
		String endssStr=endss+"";
		
		Date beginDate=DateUtil.stringToDate(recDate, "yyyy-MM-dd HH:mm:ss"); 
		Date endDate=DateUtil.stringToDate(recDate.substring(0,17)+endssStr.substring(1), "yyyy-MM-dd HH:mm:ss"); 
		TblClinicalTestReq tblClinicalTestReq=tblClinicalTestReqService.findByStudyNoAndReqNO(studyNo, reqNo);
		List<String> list=getSession().//
		createQuery("select t.specimenId  FROM TblSpecimen t WHERE  t.tblClinicalTestReq = ? AND t.recDate BETWEEN ? AND ? AND t.animalId =? AND t.specimenCode=? ")//
		.setParameter(0, tblClinicalTestReq)
		.setParameter(1, beginDate)
		.setParameter(2, endDate)
		.setParameter(3, animalId)
		.setParameter(4, specimenCode)
		.list();
		if(null!=list && list.size()>0){
			return list.get(0);
		}
		return null;
	}
//	@SuppressWarnings("unchecked")
//	public List<TblSpecimen> findByTblClinicalTestReqAndTestItems(
//			TblClinicalTestReq tblClinicalTestReq, Integer[] testItems) {
//		//如果检测项目为空
//		if(null==testItems || testItems.length<1){
//			return findByTblClinicalTestReq(tblClinicalTestReq);
//		}
//		//检测项目不为空
//		String str="";
//		for(int i=0;i<testItems.length;i++){
//			str=str+" "+testItems[i]+" ";
//			if(i!=(testItems.length-1)){
//				str=str+",";
//			}
//		}
//		System.out.println(str);
	@SuppressWarnings("unchecked")
	public boolean isExistByClinicalTestReq(
			TblClinicalTestReq tblClinicalTestReq) {
		List<TblSpecimen> list=getSession().createQuery("FROM TblSpecimen t WHERE t.tblClinicalTestReq = ? ")//
		.setParameter(0, tblClinicalTestReq)//
		.list();
		if(null!=list && list.size()>0){
			return true;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public boolean isExistByClinicalTestReqAndAnimalId(
			TblClinicalTestReq tblClinicalTestReq, String selected) {
		List<TblSpecimen> list=getSession().createQuery("FROM TblSpecimen t WHERE t.tblClinicalTestReq = ? and t.animalId = ?  ")//
		.setParameter(0, tblClinicalTestReq)//
		.setParameter(1, selected)
		.list();
		if(null!=list && list.size()>0){
			return true;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public List<String> getStudyNoListByRecDate(String recDate) {
		if(null != recDate && !recDate.isEmpty()){
			Date beginDate=DateUtil.stringToDate(recDate+" 00:00:00", "yyyy-MM-dd HH:mm:ss"); 
			Date endDate=DateUtil.stringToDate(recDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss"); 
			String sql = "select distinct studyNo from tblSpecimen where recDate between ? and ? ";
			List<String> list = getSession().createSQLQuery(sql)
				.setParameter(0, beginDate)
				.setParameter(1, endDate)
				.list();
			return list;
		}
		return null;
	}
	public List<?> findList(String studyNo, String reqNo, String animalId,
			String testItemString, String specimenCode, String recDate) {
		String sql ="SELECT   s.studyNo, s.animalId, s.animalCode, s.recTime, s.testItem, s.specimenCode, es.signer,es2.signer as sender "+
			" from      tblSpecimen AS s INNER JOIN"+
			" CoresUserPrivilege.dbo.tblESLink AS el ON s.specimenId = el.dataId and el.esType = 1 INNER JOIN"+
			" CoresUserPrivilege.dbo.tblES AS es ON el.esId = es.esId and es.esType = 1" +
			" inner join CoresUserPrivilege.dbo.tblESLink AS el2 on s.specimenId = el2.dataId and el2.esType = 20 INNER JOIN"+
			" CoresUserPrivilege.dbo.tblES AS es2 ON el2.esId = es2.esId and es2.esType =20 " +
			" WHERE   (el.tableName = 'TblSpecimen') and (el2.tableName = 'TblSpecimen') ";
		
		String orderby=" ORDER BY s.recTime ,s.testItem,s.specimenCode ";
		if(null !=recDate &&!"".equals(recDate)){//接收日期
			sql=sql+" AND  s.recDate BETWEEN  '"+recDate+" 00:00:00' AND  '"+recDate+" 23:59:59' ";
		}
		if(null !=specimenCode &&!"".equals(specimenCode)){//检验编号
			sql=sql+" AND s.specimenCode like '"+specimenCode+"%' ";
		}
			
		if(null!=testItemString && !"".equals(testItemString)){//检查项目
			int testItem=0;
			if("生化检验".equals(testItemString)){
				testItem=1;
			}else if("血液检验".equals(testItemString)){
				testItem=2;
			}else if("血凝检验".equals(testItemString)){
				testItem=3;
			}else {
				testItem=4;
			}
			sql=sql+" AND s.testItem ="+testItem+" ";
		}
		
		if(null!=animalId && !"".equals(animalId)){
			sql=sql+" AND s.animalId = '"+animalId+"' ";
		}
		
		if(null!=studyNo && !"".equals(studyNo)){//课题编号
			sql=sql+" AND s.studyNo = '"+studyNo+"' ";
			
			if(null!=reqNo&&!"".equals(reqNo)){//申请编号
				sql=sql+" AND s.reqNo = "+reqNo;
			}
		}
		sql = sql + orderby;
		List<?> list = getSession().createSQLQuery(sql).list();
		return list;
		
	}

	@SuppressWarnings("unchecked")
	public List<String> getRecDateStrList(String studyNo, String reqNo,
			int testItem) {
		String sql = "select  s.recDate from "
				+ "  tblSpecimen AS s  where "
				+ " s.studyNo = :studyNo and s.reqNo = :reqNo and  s.testItem = :testItem"
				+ "    ";
		List<Date> dateList = getSession().createSQLQuery(sql).setParameter(
				"studyNo", studyNo).setParameter("reqNo", reqNo).setParameter(
				"testItem", testItem).list();
		List<String> dateStrList = new ArrayList<String>();
		if (null != dateList && dateList.size() > 0) {
			String dateStr = "";
			for (Date date : dateList) {
				dateStr = DateUtil.dateToString(date, "yyyy-MM-dd");
				if (!dateStrList.contains(dateStr)) {
					dateStrList.add(dateStr);
				}
			}
		}
		return dateStrList;
	}
	public void saveList(List<TblSpecimen> list, String realName,
			String realName2) {
		Date date =new Date();
		TblES tblES=new TblES();
		String esId=getIdService.getKey("TblES");
		tblES.setEsId(esId);
		tblES.setSigner(realName);
		tblES.setEsType(1);
		tblES.setEsTypeDesc("标本接收");
		tblES.setDateTime(date);
		tblESService.save(tblES);//保存  电子签名
		
		TblES tblES2=new TblES();
		String esId2=getIdService.getKey("TblES");
		tblES2.setEsId(esId2);
		tblES2.setSigner(realName2);
		tblES2.setEsType(20);
		tblES2.setEsTypeDesc("临检，标本送检");
		tblES2.setDateTime(date);
		tblESService.save(tblES2);//保存  送检人 电子签名
		
//		List<TblESLink> tblESLinkList=new ArrayList<TblESLink>();
		for(TblSpecimen obj:list){
			//标本
			obj.setRecDate(date);
			obj.setRecTime(date);
			String specimenId=getIdService.getKey("TblSpecimen");
			obj.setSpecimenId(specimenId);
			getSession().save(obj);
			//签名链接表
			TblESLink tblESLink =new TblESLink();
			tblESLink.setLinkId(getIdService.getKey("TblESLink"));
			tblESLink.setDataId(specimenId);
			tblESLink.setTableName("TblSpecimen");
			tblESLink.setEsType(1);
			tblESLink.setEsTypeDesc("标本接收");
			tblESLink.setRecordTime(date);
			tblESLink.setTblES(tblES);
			tblESLinkService.save(tblESLink);
			//签名链接表  送检人
			TblESLink tblESLink2 =new TblESLink();
			tblESLink2.setLinkId(getIdService.getKey("TblESLink"));
			tblESLink2.setDataId(specimenId);
			tblESLink2.setTableName("TblSpecimen");
			tblESLink2.setEsType(20);
			tblESLink2.setEsTypeDesc("临检，标本送检");
			tblESLink2.setRecordTime(date);
			tblESLink2.setTblES(tblES2);
			tblESLinkService.save(tblESLink2);
		}
		
	}

	public List<?> findListByTblClinicalTestReq(
			TblClinicalTestReq tblClinicalTestReq) {
		String sql = "SELECT   s.studyNo,s.reqNo, s.animalId, s.animalCode,s.recDate, s.recTime, s.testItem, s.specimenCode,s.specimenKind,es2.signer as sender, es.signer "
				+ " from      tblSpecimen AS s INNER JOIN"
				+ " CoresUserPrivilege.dbo.tblESLink AS el ON s.specimenId = el.dataId and el.esType = 1 INNER JOIN"
				+ " CoresUserPrivilege.dbo.tblES AS es ON el.esId = es.esId and es.esType = 1"
				+ " inner join CoresUserPrivilege.dbo.tblESLink AS el2 on s.specimenId = el2.dataId and el2.esType = 20 INNER JOIN"
				+ " CoresUserPrivilege.dbo.tblES AS es2 ON el2.esId = es2.esId and es2.esType =20 "
				+ " WHERE   (el.tableName = 'TblSpecimen') and (el2.tableName = 'TblSpecimen') ";

		String orderby = " ORDER BY s.recTime ,s.testItem,s.specimenCode ";
		String studyNo = "";
		int  reqNo = 0;
		if(null != tblClinicalTestReq){
			studyNo = tblClinicalTestReq.getStudyNo();
			reqNo = tblClinicalTestReq.getReqNo();
		}else{
			return null;
		}

		if (null != studyNo && !"".equals(studyNo)) {// 课题编号
			sql = sql + " AND s.studyNo = '" + studyNo + "' ";

			if ( reqNo!=0 ) {// 申请编号
				sql = sql + " AND s.reqNo = " + reqNo;
			}
		}
		sql = sql + orderby;
		List<?> list = getSession().createSQLQuery(sql).list();
		return list;

	}

	public List<?> findListByCondition(String studyNo, String reqNo,
			String animalId, String testItemString, String specimenCode,
			String recDate) {
		String sql = "SELECT   s.studyNo,s.reqNo, s.animalId, s.animalCode,s.recDate, s.recTime, s.testItem, s.specimenCode,s.specimenKind,es2.signer as sender, es.signer "
			+ " from      tblSpecimen AS s INNER JOIN"
			+ " CoresUserPrivilege.dbo.tblESLink AS el ON s.specimenId = el.dataId and el.esType = 1 INNER JOIN"
			+ " CoresUserPrivilege.dbo.tblES AS es ON el.esId = es.esId and es.esType = 1"
			+ " inner join CoresUserPrivilege.dbo.tblESLink AS el2 on s.specimenId = el2.dataId and el2.esType = 20 INNER JOIN"
			+ " CoresUserPrivilege.dbo.tblES AS es2 ON el2.esId = es2.esId and es2.esType =20 "
			+ " WHERE   (el.tableName = 'TblSpecimen') and (el2.tableName = 'TblSpecimen') ";

		String orderby = " ORDER BY s.recTime ,s.testItem,s.specimenCode ";
		if (null != recDate && !"".equals(recDate)) {// 接收日期
			sql = sql + " AND  s.recDate BETWEEN  '" + recDate
					+ " 00:00:00' AND  '" + recDate + " 23:59:59' ";
		}
		if (null != specimenCode && !"".equals(specimenCode)) {// 检验编号
			sql = sql + " AND s.specimenCode like '" + specimenCode + "%' ";
		}

		if (null != testItemString && !"".equals(testItemString)) {// 检查项目
			int testItem = 0;
			if ("生化检验".equals(testItemString)) {
				testItem = 1;
			} else if ("血液检验".equals(testItemString)) {
				testItem = 2;
			} else if ("血凝检验".equals(testItemString)) {
				testItem = 3;
			} else {
				testItem = 4;
			}
			sql = sql + " AND s.testItem =" + testItem + " ";
		}

		if (null != animalId && !"".equals(animalId)) {
			sql = sql + " AND s.animalId = '" + animalId + "' ";
		}

		if (null != studyNo && !"".equals(studyNo)) {// 课题编号
			sql = sql + " AND s.studyNo = '" + studyNo + "' ";

			if (null != reqNo && !"".equals(reqNo)) {// 申请编号
				sql = sql + " AND s.reqNo = " + reqNo;
			}
		}
		sql = sql + orderby;
		List<?> list = getSession().createSQLQuery(sql).list();
		return list;
	}

}
