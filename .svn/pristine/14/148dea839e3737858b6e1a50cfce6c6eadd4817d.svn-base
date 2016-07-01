package com.lanen.service.studyplan;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.User;
import com.lanen.model.qa.QAStudyChkIndex;
import com.lanen.model.studyplan.TblApplyRevise;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.model.studyplan.TblStudyPlanHis;
import com.lanen.service.UserService;
import com.lanen.util.DateUtil;

@Service
public class TblStudyPlanServiceImpl extends BaseDaoImpl<TblStudyPlan> implements TblStudyPlanService {

	@Resource
	private UserService userService;
	
	@SuppressWarnings("unchecked")
	public List<TblStudyPlan> getAll() {
		List<TblStudyPlan> tblStudyPlanList = getSession().createQuery("FROM TblStudyPlan ORDER BY studyStartDate").list();
		return tblStudyPlanList;
	}

	@SuppressWarnings("unchecked")
	public List<TblStudyPlan> findWithYear(String year) {
		int index =year.indexOf("之前");
		if(index<0){
			String beginDateString=year+"-01-01 00:00:00";
			String endDateString=year+"-12-31 23:59:59";
			
			Date beginDate=DateUtil.stringToDate(beginDateString, "yyyy-MM-dd HH:mm:ss");
			Date endDate=DateUtil.stringToDate(endDateString, "yyyy-MM-dd HH:mm:ss");
			
			List<TblStudyPlan> list= getSession().createQuery("FROM TblStudyPlan t WHERE t.studyStartDate between ? and ?  ORDER BY t.studyNo")
			.setParameter(0, beginDate)//开始时间
			.setParameter(1, endDate)//结束时间
			.list();
			return list;
		}else{
			String maxDateString =year.substring(0, index)+"-01-01 00:00:00";
			Date maxDate=DateUtil.stringToDate(maxDateString, "yyyy-MM-dd HH:mm:ss");
			List<TblStudyPlan> list= getSession().createQuery("FROM TblStudyPlan t WHERE t.studyStartDate <?  ORDER BY t.studyNo")
			.setParameter(0, maxDate)//开始时间
			.list();
			return list;
		}
	}

	@SuppressWarnings("unchecked")
	public List<String> findAllTempClient() {
		List<String> list=getSession().createQuery("select distinct t.client  FROM TblStudyPlan t WHERE t.temp = 1  ").list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblStudyPlan> getAllNoTempTask() {
		List<TblStudyPlan> list= getSession().createQuery("FROM TblStudyPlan t WHERE t.temp = 0 and t.studyState != 0 ORDER BY t.studyStartDate desc, t.studyNo").list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblStudyPlan> getListNoTempTask(String year) {
		String beginDateString=year+"-01-01 00:00:00";
		String endDateString=year+"-12-31 23:59:59";
		
		Date beginDate=DateUtil.stringToDate(beginDateString, "yyyy-MM-dd HH:mm:ss");
		Date endDate=DateUtil.stringToDate(endDateString, "yyyy-MM-dd HH:mm:ss");
		
		List<TblStudyPlan> list= getSession().createQuery("FROM TblStudyPlan t WHERE t.temp = 0 and t.studyStartDate between ? and ?  ORDER BY t.studyNo")
		.setParameter(0, beginDate)//开始时间
		.setParameter(1, endDate)//结束时间
		.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<String> getAllStudyNo() {
		List<String> list= getSession().createQuery("select studyNo FROM TblStudyPlan ")
		.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblStudyPlan> getMyListNoTempTask(String year, String realName) {
		String beginDateString=year+"-01-01 00:00:00";
		String endDateString=year+"-12-31 23:59:59";
		
		Date beginDate=DateUtil.stringToDate(beginDateString, "yyyy-MM-dd HH:mm:ss");
		Date endDate=DateUtil.stringToDate(endDateString, "yyyy-MM-dd HH:mm:ss");
		
		List<TblStudyPlan> list= getSession().createQuery("FROM TblStudyPlan t WHERE t.temp = 0 and t.studydirector = ?  and t.studyStartDate between ? and ?  ORDER BY t.studyNo")
		.setParameter(0, realName)
		.setParameter(1, beginDate)//开始时间
		.setParameter(2, endDate)//结束时间
		.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<TblStudyPlan> getDepartmentListNoTempTask(String year,
			List<String> list, String name) {
		String beginDateString=year+"-01-01 00:00:00";
		String endDateString=year+"-12-31 23:59:59";
		
		Date beginDate=DateUtil.stringToDate(beginDateString, "yyyy-MM-dd HH:mm:ss");
		Date endDate=DateUtil.stringToDate(endDateString, "yyyy-MM-dd HH:mm:ss");
		
		List<TblStudyPlan> slist= getSession().createQuery("FROM TblStudyPlan t WHERE t.temp = 0  and t.studyState != 0 and t.studydirector in (:list) and t.studydirector != :name" +
				"  and t.studyStartDate between :beginDate and :endDate  ORDER BY t.studyNo")
		.setParameterList("list", list)
		.setParameter("name", name)//开始时间
		.setParameter("beginDate", beginDate)//开始时间
		.setParameter("endDate", endDate)//结束时间
		.list();
		return slist;
	}
	
	@SuppressWarnings("unchecked")
	public List<TblStudyPlan> getAllListNoTempTask(String year) {
		String beginDateString=year+"-01-01 00:00:00";
		String endDateString=year+"-12-31 23:59:59";
		
		Date beginDate=DateUtil.stringToDate(beginDateString, "yyyy-MM-dd HH:mm:ss");
		Date endDate=DateUtil.stringToDate(endDateString, "yyyy-MM-dd HH:mm:ss");
		
		List<TblStudyPlan> list= getSession().createQuery("FROM TblStudyPlan t WHERE t.temp = 0  and studyState != 0 and t.studyStartDate between ? and ?  ORDER BY t.studyNo")
		.setParameter(0, beginDate)//开始时间
		.setParameter(1, endDate)//结束时间
		.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblStudyPlan> getMyListNoValidationNoTempTask(String year,
			String realName) {
		String beginDateString=year+"-01-01 00:00:00";
		String endDateString=year+"-12-31 23:59:59";
		
		Date beginDate=DateUtil.stringToDate(beginDateString, "yyyy-MM-dd HH:mm:ss");
		Date endDate=DateUtil.stringToDate(endDateString, "yyyy-MM-dd HH:mm:ss");
		
		List<TblStudyPlan> list= getSession().createQuery("FROM TblStudyPlan t WHERE t.temp = 0 and t.isValidation = 0 and t.studydirector = ?  and t.studyStartDate between ? and ?  ORDER BY t.studyNo")
		.setParameter(0, realName)
		.setParameter(1, beginDate)//开始时间
		.setParameter(2, endDate)//结束时间
		.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<TblStudyPlan> getDepartmentListNoValidationNoTempTask(
			String year, List<String> list, String name) {
		String beginDateString=year+"-01-01 00:00:00";
		String endDateString=year+"-12-31 23:59:59";
		
		Date beginDate=DateUtil.stringToDate(beginDateString, "yyyy-MM-dd HH:mm:ss");
		Date endDate=DateUtil.stringToDate(endDateString, "yyyy-MM-dd HH:mm:ss");
		
		List<TblStudyPlan> slist= getSession().createQuery("FROM TblStudyPlan t WHERE t.temp = 0 and t.isValidation = 0 and t.studydirector in ( :list )  " +
				" and t.studydirector != :name and t.studyStartDate between :beginDate and :endDate and t.studyState != 0 ORDER BY t.studyNo")
		.setParameterList("list", list)
		.setParameter("name", name)
		.setParameter("beginDate", beginDate)//开始时间
		.setParameter("endDate", endDate)//结束时间
		.list();
		return slist;
	}

	@SuppressWarnings("unchecked")
	public List<TblStudyPlan> getAllListNoValidationNoTempTask(String year) {
		String beginDateString=year+"-01-01 00:00:00";
		String endDateString=year+"-12-31 23:59:59";
		
		Date beginDate=DateUtil.stringToDate(beginDateString, "yyyy-MM-dd HH:mm:ss");
		Date endDate=DateUtil.stringToDate(endDateString, "yyyy-MM-dd HH:mm:ss");
		
		List<TblStudyPlan> list= getSession().createQuery("FROM TblStudyPlan t WHERE t.temp = 0 and studyState != 0 and t.isValidation = 0  and t.studyStartDate between ? and ?  ORDER BY t.studyNo")
		.setParameter(0, beginDate)//开始时间
		.setParameter(1, endDate)//结束时间
		.list();
		return list;
	}
	
	public void update(String studyNoPara, int animalCodeMode) {
		TblStudyPlan studyPlan = this.getById(studyNoPara);
		studyPlan.setAnimalCodeMode(animalCodeMode);
		update(studyPlan);
	}

	@SuppressWarnings("unchecked")
	public List<Object> getYearStrList() {
		String sql = "SELECT DISTINCT CONVERT(nvarchar(4), studyStartDate, 120) AS date " +
				" from      tblStudyPlan ORDER BY date DESC";
		List<Object> list = getSession().createSQLQuery(sql).list();
		return list;
	}

	@Resource
	private TblStudyPlanHisService tblStudyPlanHisService;
	public void updateAndSaveTblStudyPlanHis(TblStudyPlan studyPlan,
			TblApplyRevise applyRevise) {
		update(studyPlan);
		TblStudyPlanHis studyPlanHis = new TblStudyPlanHis();
		studyPlanHis.setId(tblStudyPlanHisService.getKey());
		studyPlanHis.setAnimalCodeMode(studyPlan.getAnimalCodeMode());
		studyPlanHis.setAnimalImportDate(studyPlan.getAnimalImportDate());
		studyPlanHis.setAnimalStrain(studyPlan.getAnimalStrain());
		studyPlanHis.setAnimalType(studyPlan.getAnimalType());
		studyPlanHis.setClient(studyPlan.getClient());
		studyPlanHis.setClinicalTestDirector(studyPlan.getClinicalTestDirector());
		studyPlanHis.setDosageUnit(studyPlan.getDosageUnit());
		studyPlanHis.setIsGLP(studyPlan.getIsGLP());
		studyPlanHis.setIsValidation(studyPlan.getIsValidation());
		studyPlanHis.setOperate("编辑");
		studyPlanHis.setOperateDate(new Date());
		studyPlanHis.setPathDirector(studyPlan.getPathDirector());
		studyPlanHis.setPreStudyDate(studyPlan.getPreStudyDate());
		studyPlanHis.setQa(studyPlan.getQa());
		studyPlanHis.setSmplCode(studyPlan.getSmplCode());
		studyPlanHis.setStudyBeginDate(studyPlan.getStudyBeginDate());
		studyPlanHis.setStudydirector(studyPlan.getStudydirector());
		studyPlanHis.setStudyName(studyPlan.getStudyName());
		studyPlanHis.setStudyNo(studyPlan.getStudyNo());
		studyPlanHis.setStudyStartDate(studyPlan.getStudyStartDate());
		studyPlanHis.setStudyState(studyPlan.getStudyState());
		studyPlanHis.setStudyTypeCode(studyPlan.getStudyTypeCode());
		studyPlanHis.setTblApplyReviseID(applyRevise.getId());
		studyPlanHis.setTemp(studyPlan.getTemp());
		tblStudyPlanHisService.save(studyPlanHis);
	}


	@SuppressWarnings("unchecked")
	public TblStudyPlan getByStudyNo(String studyNo) {
		TblStudyPlan tblStudyPlan=null;
		String hql="from TblStudyPlan where studyNo=:studyNo";
		List<TblStudyPlan> list=getSession().createQuery(hql).setParameter("studyNo", studyNo).list();
		if(list!=null&&list.size()>0){
			tblStudyPlan=list.get(0);
		}
		return tblStudyPlan;
	}


	@SuppressWarnings("unchecked")
	public TblStudyPlan getByStudyNo2(String currentStudyNo) {
		TblStudyPlan tblStudyPlan=null;
		String sql="select sp.studyNo, "+
//				   "  (select count(a.id) from CoresStudy.dbo.tblAnimal as a where a.studyNo=sp.studyNo ) as animalIds,"+
				   "  (select count(a.id) from CoresStudy.dbo.tblAnimalDetailDissectPlan as a where a.studyNo=sp.studyNo ) as animalIds,"+
				   "  (select count(ppc.id)  from CoresStudy.dbo.tblPathPlanCheck as ppc where ppc.studyNo=sp.studyNo) as pathCheckId,"+
				   "  (select count(ppvw.id)  from CoresStudy.dbo.tblPathPlanVisceraWeigh as ppvw where ppvw.studyNo=sp.studyNo) as pathWeighId "+
				   "  from CoresStudy.dbo.tblStudyPlan as sp where studyNo=:studyNo";
		List<?> sqlList=getSession().createSQLQuery(sql)
		                   .setParameter("studyNo", currentStudyNo).list();
		if(sqlList!=null&&sqlList.size()>0){
			Object[] objs=(Object[])sqlList.get(0);
			//课题下动物数量
			int animalNumber=(Integer)objs[1];
			//课题下脏器/组织学检查实体ID数
			int checkIds=(Integer)objs[2];
			//课题下脏器称重实体ID数
			int weighIds=(Integer)objs[3];
			if(animalNumber!=0&&(checkIds!=0||weighIds!=0)){
				String hql="from TblStudyPlan where studyNo=:studyNo";
				List<TblStudyPlan> list=getSession().createQuery(hql).setParameter("studyNo", currentStudyNo).list();
				if(list!=null&&list.size()>0){
					tblStudyPlan=list.get(0);
				}
			}
		}
		return tblStudyPlan;
	}

	@SuppressWarnings("unchecked")
	public List<String> getYearList(String realName) {
		List<String> yearList = null;
		String sql = "select distinct CONVERT(varchar(4),studyStartDate,120) "+
					" from CoresStudy.dbo.tblStudyPlan "+
					" where studydirector = ? "+
					" order by CONVERT(varchar(4),studyStartDate,120)  desc ";
		yearList = getSession().createSQLQuery(sql)
								.setParameter(0, realName)
								.list();
		return yearList;
	}

	@SuppressWarnings("unchecked")
	public List<String> getYearList() {
		List<String> yearList = null;
		String sql = "select distinct CONVERT(varchar(4),studyStartDate,120) "+
					" from CoresStudy.dbo.tblStudyPlan "+
					" order by CONVERT(varchar(4),studyStartDate,120)  desc ";
		yearList = getSession().createSQLQuery(sql)
								.list();
		return yearList;
	}

	public String getPathSDByStudyNo(String studyNo) {
		String sql = "select pathSD"+
		" from CoresContract.dbo.tblStudyItem"+
				" where studyNo = :studyNo";
		String pathSd = (String) getSession().createSQLQuery(sql)
									.setParameter("studyNo", studyNo)
									.uniqueResult();
		if(null == pathSd || "".equals(pathSd)){
			List<User> list= userService.findByPrivilegeName2("病理负责人");
			if(null != list && list.size() > 0){
				pathSd = list.get(0).getRealName();
			}
		}
		return pathSd;
		
	}

	public String getSDByStudyNo(String studyNo) {
		String sql = "select sd"+
		" from CoresStudy.dbo.view_studyNoSD"+
		" where studyNo = :studyNo";
		String sd = (String) getSession().createSQLQuery(sql)
							.setParameter("studyNo", studyNo)
							.uniqueResult();
return sd;
	}

	public Date getCurrentDate() {
		
		return new Date();
	}
	@SuppressWarnings("unchecked")
	public List<TblStudyPlan> getByStudyType(String studyNo,User user) {
		
		String hql="from TblStudyPlan where studyTypeCode=(select studyTypeCode from TblStudyPlan where studyNo=:studyNo)";
		List<TblStudyPlan> list=getSession().createQuery(hql).setParameter("studyNo", studyNo).list();
		
		return list;
	}
	public boolean copyStudyPlan(String sourceStudyPlanNo, String studyNoPara)  {
		String sql = "{call dbo.copyStudyPlan (?,?)}";
		//@sourceStudyPlanNo varchar,@destStudyPlanNo varchar
		boolean flag = false;
		CallableStatement stmt;
		try {
			stmt = getSession().connection().prepareCall(sql);
			stmt.setString(1, sourceStudyPlanNo);//参数设置从1开始
			stmt.setString(2, studyNoPara);
			flag = stmt.execute();
		//	System.out.println("flag="+flag);
			flag = true;
		//	System.out.println("flag2="+flag);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public Date getGroupingTimeByStudyNoAndNodeName(String sourceStudyPlanNo,
			String nodeName) {
		String sql = "select planDate from [CoresContract].[dbo].[tblStudySchedule] where studyNo=:studyNo and nodeName=:nodeName";
		Date planDate = (Date) getSession().createSQLQuery(sql)
							.setParameter("studyNo", sourceStudyPlanNo)
							.setParameter("nodeName",nodeName)
							.uniqueResult();
		return planDate;
	}
	@SuppressWarnings("unchecked")
	public List<String> getByStartDateAndCondition(Date start,Date end,String studyNo)
	{
		
		//"from TblStudyPlan where studyStartDate between :start and :end and studyNo like :studyNo";
		String hql=	" select studyNo from [CoresStudy].[dbo].[tblStudyPlan]" +
					" where studyStartDate between :start and :end and studyNo like :studyNo " +
					"  union" +
					"  select studyNo from [CoresQA].[dbo].[QAStudyChkIndex]" +
					" where (studyPlanState is null or studyPlanState=0) and studyNo like :studyNo";
		List<String> list=getSession().createSQLQuery(hql).setParameter("start", start)
															.setParameter("end", end)
															.setParameter("studyNo", "%"+studyNo+"%")
															.list();
		
		return list;
		
	}

	public boolean isExistByStudyNo_YYDB(String studyNoPara) {
		String sql = "select count(A01)"+
					" from YYDB.dbo.TBPLAN "+
					" where A01 = ? ";
		Integer count = (Integer) getSession().createSQLQuery(sql)
											.setParameter(0, studyNoPara)
											.uniqueResult();
		return count > 0;
	}


	


}
