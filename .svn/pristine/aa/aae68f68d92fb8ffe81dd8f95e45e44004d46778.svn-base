package com.lanen.service.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.contract.TblContract;
import com.lanen.model.contract.TblTestItem;
import com.lanen.model.contract.TblTestItemHis;
import com.lanen.model.contract.TblTestItem_Json;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.util.DateUtil;
@Service 
public class TblTestItemServiceImpl extends BaseDaoImpl<TblTestItem> implements TblTestItemService{
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	@SuppressWarnings("unchecked")
	public boolean isExistByTestItemtiNo(String tiNo) {
		List<TblTestItem> list=getSession().createQuery("FROM TblTestItem t WHERE t.tiNo = :tiNo ")//
		.setParameter("tiNo", tiNo)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public List<TblTestItem> getConfirmedByTiNo(String tiNo) {
		List<TblTestItem> list=getSession().createQuery("FROM TblTestItem t WHERE t.tiNo = :tiNo and confirmSign!=null ")//
											.setParameter("tiNo", tiNo)
											.list();
		if(null!=list&&list.size()>0){
			return list;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public boolean checkTestItemConfirmSign(String tiNo) {
		// TODO 判断供试品是否已签字确认
		List<TblTestItem> list=getSession().createQuery("FROM TblTestItem t WHERE t.tiNo = :tiNo and ( t.confirmSign = null or t.confirmSign = '')")//
		.setParameter("tiNo", tiNo)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}
//	public void delTestItemAndStudyItemById(String id) {
//		   delete(id);
//			String sql = "   delete from  [CoresContract].[dbo].[tblStudyItem] where id  in  ( "+
//           " select tbls.id FROM  [CoresContract].[dbo].[tblTestItem] AS tblt LEFT OUTER JOIN "+
//		   " [CoresContract].[dbo].[tblStudyItem] AS tbls ON tblt.contractCode = tbls.contractCode AND tblt.tiNo = tbls.tiNo "+
//		   " WHERE  tblt.id = :id ) ";
//			Query query = getSession().createSQLQuery(sql);
//			query.setParameter("id", id).executeUpdate();
//		 
//	}

	public List<TblTestItem_Json> getByStartimeAndEndtimeAndTiCode(
			Date startime, Date endtime, String tiCode, String tiName,boolean readAll,String reader) {
		String sql = "  SELECT tblt1.*,tbls2.cout1 FROM  "+
		"  (SELECT tblt.[id],tblt.[contractCode],tblt.[tiNo],tblt.[tiCode],tblt.[tiName],tblt.[tiType],tblt.[content],tblt.[sealNo],  "+
		"   tblt.[fileNo],tblt.[physical],tblt.[meltPoint]  "+
		"   ,tblt.[boilPoint],tblt.[photolysis],tblt.[volatility],tblt.[density],tblt.[waterSolubility],tblt.[waterStability],tblt.[solventSolubility]  "+
		"   ,tblt.[solventStability],tblt.[ph] ,tblt.[securityMeasures]  "+
		"   ,tblt.[analysis],tblt.[postTreatment],tblt.[composition],tblt.[cas],tblt.[storageCondition],tblt.[validityPeriod]  "+
		"   ,tblt.[sponsorIsVender],tblt.[venderId],tblt.[venderName],tblt.[venderAddress],tblt.[venderLinkman]  "+
		"   ,tblt.[venderTel],tblt.[venderMobile],tblt.[venderEmail],tblt.[venderFax],tblt.[state]  ,tblt.[confirmSign]  "+
		
		"   FROM [CoresContract].[dbo].[tblTestItem] as tblt left join [CoresContract].[dbo].[tblContract] as tblc  "+
		"   ON tblt.contractCode = tblc.contractCode  "+
		"   WHERE tblt.state!=0 and tblc.contractState !=0   and (tblc.signingDate between :startime and :endtime  )";
		if(null != tiCode && !tiCode.equals("")){
			sql = sql +"  and tblt.[tiCode] = :tiCode "; 
		}
		if(null != tiName && !tiName.equals("")){
			sql = sql +"  and  (tblt.[tiName] like :tiName or tblt.tiNo like :tiName or tblt.contractCode like :tiName )";
		}
		if(!readAll){
        	sql = sql+" and tblc.[operator] = :reader ";
        }
		sql = sql +") as tblt1 left join (  "+
		"   SELECT count(tbls.id) as cout1, tbls.tiNo FROM [CoresContract].[dbo].[tblStudyItem] as tbls group by tbls.tiNo "+
		"  ) as tbls2 ON tbls2.tiNo = tblt1.tiNo ";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("startime", startime);
		query.setParameter("endtime", endtime);
		if(null != tiCode && !tiCode.equals("")){
			query.setParameter("tiCode", tiCode);
		}
		if(null != tiName && !tiName.equals("")){
        	query.setParameter("tiName","%"+ tiName+"%");
        }
		if(!readAll){
			query.setParameter("reader", reader);
		}
		List<?> list = query.list();
		List<TblTestItem_Json> listjson = new ArrayList<TblTestItem_Json>();
		if (null != list) {
			for (Object obj : list) {
				Object[] objs= (Object[]) obj;
				TblTestItem_Json testItem = new TblTestItem_Json();
				testItem.setId((String)objs[0]);
				testItem.setContractCode((String)objs[1]);
				testItem.setTiNo((String)objs[2]);
				testItem.setTiCode((String)objs[3]);
				testItem.setTiName((String)objs[4]);
				testItem.setTiType((String)objs[5]);
				testItem.setContent((String)objs[6]);
				testItem.setSealNo((String)objs[7]);
				testItem.setFileNo((String)objs[8]);
				testItem.setPhysical((String)objs[9]);
				testItem.setMeltPoint((String)objs[10]);
				testItem.setBoilPoint((String)objs[11]);
				testItem.setPhotolysis((String)objs[12]);
				testItem.setVolatility((String)objs[13]);
				testItem.setDensity((String)objs[14]);
				testItem.setWaterSolubility((String)objs[15]);
				testItem.setWaterStability((String)objs[16]);
				testItem.setSolventSolubility((String)objs[17]);
				testItem.setSolventStability((String)objs[18]);
				testItem.setPh((String)objs[19]);
				testItem.setSecurityMeasures((String)objs[20]);
				testItem.setAnalysis((String)objs[21]);
				testItem.setPostTreatment((String)objs[22]);
				testItem.setComposition((String)objs[23]);
				testItem.setCas((String)objs[24]);
				testItem.setStorageCondition((String)objs[25]);
				if(null != objs[26] && !objs[26].equals("")){
					testItem.setValidityPeriod(DateUtil.dateToString((Date)objs[26], "yyyy-MM-dd"));
				}
				
				if(null != objs[27]  && !objs[27].equals("")){
				testItem.setSponsorIsVender((Integer)objs[27]);
				}
				testItem.setVenderId((String)objs[28]);
				testItem.setVenderName((String)objs[29]);
				testItem.setVenderAddress((String)objs[30]);
				testItem.setVenderLinkman((String)objs[31]);
				testItem.setVenderTel((String)objs[32]);
				testItem.setVenderMobile((String)objs[33]);
				testItem.setVenderEmail((String)objs[34]);
				testItem.setVenderFax((String)objs[35]);
				if(null != objs[36]  && !objs[36].equals("")){
					testItem.setTestItemState((Integer)objs[36]);
				}
				if(null != objs[37] && !objs[37].equals("") ){
					TblES tblES =tblESService.getById((String)objs[37]);
					if(null != tblES){
						String signer = tblES.getSigner();
						String time = DateUtil.dateToString(tblES.getDateTime(), "yyyy-MM-dd");
						testItem.setConfirmSign(time+" "+signer);	
					}else{
						
					}
						
				}else{
					testItem.setConfirmSign("未确认");					
				}
				if(null != objs[38]){
					testItem.setStudyItemCount((Integer)objs[38]+"");
				}else{
					testItem.setStudyItemCount("0");
				}
			
				listjson.add(testItem);
			}
		}
		return listjson;
	}

	@SuppressWarnings("unchecked")
	public List<TblTestItem> getListByContract(String contractCode) {
		List<TblTestItem> list=getSession().createQuery("FROM TblTestItem t WHERE t.contractCode = :contractCode ")//
											.setParameter("contractCode", contractCode)
											.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public String getTiNameByContractAndTiNo(String contractCode, String tiNo) {
		List<TblTestItem> list=getSession().createQuery("FROM TblTestItem t WHERE t.tiNo = :tiNo and t.contractCode = :contractCode   ")//
		.setParameter("tiNo", tiNo).setParameter("contractCode", contractCode)
		.list();
		if(null!=list&&list.size()>0){
			return list.get(0).getTiName();
		}
		return null ;
	}

	public String getTiCodeByTiNo(String tiNo) {
		String sql = "SELECT   tiCode"+
					" FROM      tblTestItem"+
					" WHERE   (tiNo = ? )";
		
		String tiCode  = (String) getSession().createSQLQuery(sql)
											  .setParameter(0, tiNo)
											  .uniqueResult();
		
		return tiCode;
	}

	@SuppressWarnings("unchecked")
	public List<String> getContentList() {
		String sql = "SELECT DISTINCT [content]"+
					" FROM      tblTestItem";
		List<String> list = getSession().createSQLQuery(sql).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<String> getContentLabelList() {
		String sql = "SELECT DISTINCT [contentLabel]"+
					" FROM      tblTestItem where contentLabel is not null";
		List<String> list = getSession().createSQLQuery(sql).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<String> getPhysicalList() {
		String sql = "SELECT DISTINCT  physical"+
				" FROM      tblTestItem";
		List<String> list = getSession().createSQLQuery(sql).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<String> getStorageConditionList() {
		String sql = "SELECT DISTINCT  storageCondition"+
				" FROM      tblTestItem";
		List<String> list = getSession().createSQLQuery(sql).list();
		return list;
	}

	public List<TblTestItem_Json> getByStartimeAndEndtimeAndTiCodeConfirmFlag(
			Date startDate, Date endDate, String tiCode, String tiName,
			String isConfirm) {
		
		String sql = "  SELECT tblt.[id],tblt.[contractCode],tblt.[tiNo],tblt.[tiCode],tblt.[tiName],tblt.[tiType],tblt.[content],tblt.[sealNo],  "+
		"   tblt.[fileNo],tblt.[physical],tblt.[meltPoint]  "+
		"   ,tblt.[boilPoint],tblt.[photolysis],tblt.[volatility],tblt.[density],tblt.[waterSolubility],tblt.[waterStability],tblt.[solventSolubility]  "+
		"   ,tblt.[solventStability],tblt.[ph] ,tblt.[securityMeasures]  "+
		"   ,tblt.[analysis],tblt.[postTreatment],tblt.[composition],tblt.[cas],tblt.[storageCondition],tblt.[validityPeriod]  "+
		"   ,tblt.[sponsorIsVender],tblt.[venderId],tblt.[venderName],tblt.[venderAddress],tblt.[venderLinkman]  "+
		"   ,tblt.[venderTel],tblt.[venderMobile],tblt.[venderEmail],tblt.[venderFax],tblt.[state]  ,tblt.[confirmSign] ,tblt.reserveNum+' '+tblt.reserveUnit as reserveNum "+
		
		"   FROM [CoresContract].[dbo].[tblTestItem] as tblt left join [CoresContract].[dbo].[tblContract] as tblc  "+
		"   ON tblt.contractCode = tblc.contractCode  "+
		"   WHERE tblt.state > 0 and  tblc.contractState !=0 and (convert(varchar(10),tblc.submitDate,120) between :startime and  :endtime  ) ";
		//( convert(varchar(10),tblc.submitDate,120)
		if(null != tiCode && !tiCode.equals("")){
			sql = sql +"  and tblt.[tiCode] = :tiCode "; 
		}
		if(null != tiName && !tiName.equals("")){
			sql = sql +"  and  (tblt.[tiName] like :tiName or tblt.tiNo like :tiName or tblt.contractCode like :tiName )";
		}
		//  ""或null 全部  ,"0":未确认     ,"1":已确认
		if(null == isConfirm || isConfirm.equals("")){
			
		}else if(isConfirm .equals("0")){
			sql = sql +"  and  (tblt.[confirmSign] is null or tblt.[confirmSign] = '' )";
		}else if(isConfirm .equals("1")){
			sql = sql +"  and  (tblt.[confirmSign] is not null or tblt.[confirmSign] = '' )";
		}
		 sql = sql + " order by tblc.submitDate desc ";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("startime", startDate);
		query.setParameter("endtime", endDate);
		if(null != tiCode && !tiCode.equals("")){
			query.setParameter("tiCode", tiCode);
		}
		if(null != tiName && !tiName.equals("")){
        	query.setParameter("tiName","%"+ tiName+"%");
        }
		List<?> list = query.list();
		List<TblTestItem_Json> listjson = new ArrayList<TblTestItem_Json>();
		if (null != list) {
			for (Object obj : list) {
				Object[] objs= (Object[]) obj;
				TblTestItem_Json testItem = new TblTestItem_Json();
				testItem.setId((String)objs[0]);
				testItem.setContractCode((String)objs[1]);
				testItem.setTiNo((String)objs[2]);
				testItem.setTiCode((String)objs[3]);
				testItem.setTiName((String)objs[4]);
				testItem.setTiType((String)objs[5]);
				testItem.setContent((String)objs[6]);
				testItem.setSealNo((String)objs[7]);
				testItem.setFileNo((String)objs[8]);
				testItem.setPhysical((String)objs[9]);
				testItem.setMeltPoint((String)objs[10]);
				testItem.setBoilPoint((String)objs[11]);
				testItem.setPhotolysis((String)objs[12]);
				testItem.setVolatility((String)objs[13]);
				testItem.setDensity((String)objs[14]);
				testItem.setWaterSolubility((String)objs[15]);
				testItem.setWaterStability((String)objs[16]);
				testItem.setSolventSolubility((String)objs[17]);
				testItem.setSolventStability((String)objs[18]);
				testItem.setPh((String)objs[19]);
				testItem.setSecurityMeasures((String)objs[20]);
				testItem.setAnalysis((String)objs[21]);
				testItem.setPostTreatment((String)objs[22]);
				testItem.setComposition((String)objs[23]);
				testItem.setCas((String)objs[24]);
				testItem.setStorageCondition((String)objs[25]);
				testItem.setValidityPeriod(DateUtil.dateToString((Date)objs[26], "yyyy-MM-dd"));
				if(null != objs[27]){
				testItem.setSponsorIsVender((Integer)objs[27]);
				}
				testItem.setVenderId((String)objs[28]);
				testItem.setVenderName((String)objs[29]);
				testItem.setVenderAddress((String)objs[30]);
				testItem.setVenderLinkman((String)objs[31]);
				testItem.setVenderTel((String)objs[32]);
				testItem.setVenderMobile((String)objs[33]);
				testItem.setVenderEmail((String)objs[34]);
				testItem.setVenderFax((String)objs[35]);
				if(null != objs[36]){
					testItem.setTestItemState((Integer)objs[36]);
				}
				if(null != objs[37] && (!objs[37].equals(""))){
					TblES tblES =tblESService.getById((String)objs[37]);
					String signer = tblES.getSigner();
					String time = DateUtil.dateToString(tblES.getDateTime(), "yyyy-MM-dd");
					testItem.setConfirmSign(time+" "+signer);		
				}else{
					testItem.setConfirmSign("未确认");					
				}
				
				testItem.setReserveNum((String)objs[38]);
			
				listjson.add(testItem);
			}
		}
		return listjson;
	}

	public void confirm(String[] testItemIds,String esId) {
		String sql = "update CoresContract.dbo.tblTestItem set confirmSign = :esId" +
				"	where id in (:testItemIds)";
		
		getSession().createSQLQuery(sql)
					.setParameter("esId", esId)
					.setParameterList("testItemIds", testItemIds)
					.executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	public String getTiCodeByContractCode(String contractCode) {
		List<TblTestItem> list=getSession().createQuery("FROM TblTestItem t WHERE t.contractCode = :contractCode order by id desc ")//
								.setParameter("contractCode", contractCode)
								.list();
		if( null != list && list.size() > 0){
			return list.get(0).getTiCode();
		}else{
			String sql = "select tcu.tiCode"+
						" from CoresContract.dbo.tblContract as tc left join "+
						" CoresContract.dbo.tblCustomer as tcu "+
						" on tc.sponsorId = tcu.id"+
						" where tc.contractCode = :contractCode";
			String tiCode = (String) getSession().createSQLQuery(sql)
										.setParameter("contractCode", contractCode)
										.uniqueResult();
			return tiCode;
		}
	}

	@SuppressWarnings("unchecked")
	public List<String> getAnalysisList() {
		String sql = "SELECT DISTINCT  analysis"+
				" FROM      tblTestItem";
		List<String> list = getSession().createSQLQuery(sql).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<String> getPostTreatmentList() {
		String sql = "SELECT DISTINCT  postTreatment"+
				" FROM      tblTestItem";
		List<String> list = getSession().createSQLQuery(sql).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<String> getReserveUnitList() {
		String sql = "SELECT DISTINCT  reserveUnit"+
				" FROM      tblTestItem " +
				" where isnull(reserveUnit,'') != ''";
		List<String> list = getSession().createSQLQuery(sql).list();
		return list;
	}

	public void update(TblTestItem testItem, TblContract contract) {
		getSession().update(testItem);
		
//		String sql1 = " update   CoresContract.dbo.tblContract "+
//		" set sponsorIsVender = :sponsorIsVender,venderId=:venderId,venderName=:venderName," +
//		" venderAddress =:venderAddress,venderLinkman =:venderLinkman,venderTel = :venderTel, "+
//		" venderMobile = :venderMobile,venderEmail =:venderEmail,venderFax = :venderFax "+
//		" where contractCode= :contractCode";
//		
//		getSession().createSQLQuery(sql1)
//		 .setParameter("contractCode", contract.getContractCode())
//		 .setParameter("sponsorIsVender", contract.getSponsorIsVender())
//		 .setParameter("venderId", contract.getVenderId())
//		 .setParameter("venderName", contract.getVenderName())
//		 .setParameter("venderAddress", contract.getVenderAddress())
//		 .setParameter("venderLinkman", contract.getVenderLinkman())
//		 .setParameter("venderTel", contract.getVenderTel())
//		 .setParameter("venderMobile", contract.getVenderMobile())
//		 .setParameter("venderEmail", contract.getVenderEmail())
//		 .setParameter("venderFax", contract.getVenderFax())
//		 .executeUpdate();
//		//更新相同的
//		String sql2 = "update   CoresContract.dbo.tblTestItem "+
//	                  " set sponsorIsVender = 1 "+
//	                  " where contractCode= :contractCode and venderId = :venderId";
//		 getSession().createSQLQuery(sql2)
//		 .setParameter("contractCode", contract.getContractCode())
//		 .setParameter("venderId", contract.getVenderId())
//		 .executeUpdate();
//		
//		//更新不相同的
//		String sql3 = "update   CoresContract.dbo.tblTestItem "+
//	                  " set sponsorIsVender = 0 "+
//	                  " where contractCode= :contractCode and venderId != :venderId";
//		  getSession().createSQLQuery(sql3)
//		 .setParameter("contractCode", contract.getContractCode())
//		 .setParameter("venderId", contract.getVenderId())
//		 .executeUpdate();
		
		getSession().update(testItem);
		
	}

	@SuppressWarnings("unchecked")
	public List<TblTestItem_Json> getByStartimeAndEndtimeAndTiCodeConfirmFlagAndSD(
			Date startDate, Date endDate, String tiCode, String tiName,
			String isConfirm, String sd) {
		String sql = "  select distinct tblcc.id,tblcc.contractCode,tblcc.tiNo,tblcc.tiCode,tblcc.tiName,tblcc.tiType,tblcc.content,tblcc.sealNo,tblcc.fileNo,tblcc.physical, "+
                     "  tblcc.meltPoint,tblcc.boilPoint,tblcc.photolysis,tblcc.volatility,tblcc.density,tblcc.waterSolubility,tblcc.waterStability,tblcc.solventSolubility   "+
                     "  ,tblcc.solventStability,tblcc.ph ,tblcc.securityMeasures,tblcc.analysis,tblcc.postTreatment,tblcc.composition,tblcc.cas,tblcc.storageCondition, "+
                     "  tblcc.validityPeriod ,tblcc.sponsorIsVender,tblcc.venderId,tblcc.venderName,tblcc.venderAddress,tblcc.venderLinkman ,tblcc.venderTel, "+
                     "  tblcc.venderMobile,tblcc.venderEmail,tblcc.venderFax,tblcc.state  ,tblcc.confirmSign ,tblcc.reserveNum,tblcc.submitDate" ;
					 if(null != sd && (!sd.equals(""))){
							sql = sql + " ,tbls.sd ";
					 }
                     sql = sql + " from "+
                     "         ( select tblt.id,tblt.contractCode,tblt.tiNo,tblt.tiCode,tblt.tiName,tblt.tiType,tblt.content,tblt.sealNo,tblt.fileNo,tblt.physical,"+
                     "            tblt.meltPoint,tblt.boilPoint,tblt.photolysis,tblt.volatility,tblt.density,tblt.waterSolubility,tblt.waterStability,tblt.solventSolubility  "+
                     "            ,tblt.solventStability,tblt.ph ,tblt.securityMeasures,tblt.analysis,tblt.postTreatment,tblt.composition,tblt.cas,tblt.storageCondition,"+
                     "            tblt.validityPeriod ,tblt.sponsorIsVender,tblt.venderId,tblt.venderName,tblt.venderAddress,tblt.venderLinkman ,tblt.venderTel,"+
                     "            tblt.venderMobile,tblt.venderEmail,tblt.venderFax,tblt.state  ,tblt.confirmSign ," +
//                     " tblt.reserveNum+' '+tblt.reserveUnit as reserveNum," +
                     //留样量
                     " (case when isnull(tblt.reserveNum,'') = '' then PLANDB.dbo.GETSMPLRESERVENUM(tblt.tiNo) else tblt.reserveNum+' '+tblt.reserveUnit end) as reserveNum," +
                     "tblc.submitDate"+
                     "            from CoresContract.dbo.tblTestItem as tblt left join CoresContract.dbo.tblContract as tblc  "+
                     "            on tblt.contractCode = tblc.contractCode  "+
                     "            where tblt.state > 0 and  tblc.contractState !=0 " ;
                     if(null != startDate && null != endDate ){
                    	 sql = sql +  "and (convert(varchar(10),tblc.submitDate,120) between :startime and  :endtime  )";
                     }
                   
		if(null != tiCode && !tiCode.equals("")){
			sql = sql +"  and tblt.tiCode = :tiCode "; 
		}
		if(null != tiName && !tiName.equals("")){
			sql = sql +"  and  (tblt.tiName like :tiName or tblt.tiNo like :tiName or tblt.contractCode like :tiName )";
		}
		//  ""或null 全部  ,"0":未确认     ,"1":已确认
		if(null == isConfirm || isConfirm.equals("")){
			
		}else if(isConfirm .equals("0")){
			sql = sql +"  and  (tblt.confirmSign is null or tblt.confirmSign = '' )";
		}else if(isConfirm .equals("1")){
			sql = sql +"  and  (tblt.confirmSign is not null or tblt.confirmSign = '' )";
		}
		 sql = sql  +"  ) as tblcc left join CoresContract.dbo.tblStudyItem as tbls ";
		 sql = sql  +" on tblcc.tiNo = tbls.tiNo ";
		 if(null != sd && (!sd.equals(""))){
//			sql = sql + " where tbls.sd = '"+sd+"' and tblcc.confirmSign is not null and tblcc.confirmSign != '' ";
			sql = sql + " where tbls.sd = '"+sd+"' ";
		 }
            sql = sql + " order by tblcc.submitDate desc";
		Query query = getSession().createSQLQuery(sql);
		if(null != startDate && null != endDate ){
		   query.setParameter("startime", startDate);
		   query.setParameter("endtime", endDate);
		}
		if(null != tiCode && !tiCode.equals("")){
			query.setParameter("tiCode", tiCode);
		}
		if(null != tiName && !tiName.equals("")){
        	query.setParameter("tiName","%"+ tiName+"%");
        }
		List<Map<String,Object>> list = query.setResultTransformer(new MapResultTransformer()).list();
		List<TblTestItem_Json> listjson = new ArrayList<TblTestItem_Json>();
		if (null != list) {
			for (Map<String,Object> map : list) {
				TblTestItem_Json testItem = new TblTestItem_Json();
				testItem.setId((String)map.get("id"));
				testItem.setContractCode((String)map.get("contractCode"));
				testItem.setTiNo((String)map.get("tiNo"));
				testItem.setTiCode((String)map.get("tiCode"));
				testItem.setTiName((String)map.get("tiName"));
				testItem.setTiType((String)map.get("tiType"));
				testItem.setContent((String)map.get("content"));
				testItem.setSealNo((String)map.get("sealNo"));
				testItem.setFileNo((String)map.get("fileNo"));
				testItem.setPhysical((String)map.get("physical"));
				testItem.setMeltPoint((String)map.get("meltPoint"));
				testItem.setBoilPoint((String)map.get("boilPoint"));
				testItem.setPhotolysis((String)map.get("photolysis"));
				testItem.setVolatility((String)map.get("volatility"));
				testItem.setDensity((String)map.get("density"));
				testItem.setWaterSolubility((String)map.get("waterSolubility"));
				testItem.setWaterStability((String)map.get("waterStability"));
				testItem.setSolventSolubility((String)map.get("solventSolubility"));
				testItem.setSolventStability((String)map.get("solventStability"));
				testItem.setPh((String)map.get("ph"));
				testItem.setSecurityMeasures((String)map.get("securityMeasures"));
				testItem.setAnalysis((String)map.get("analysis"));
				testItem.setPostTreatment((String)map.get("postTreatment"));
				testItem.setComposition((String)map.get("composition"));
				testItem.setCas((String)map.get("cas"));
				testItem.setStorageCondition((String)map.get("storageCondition"));
				testItem.setValidityPeriod(DateUtil.dateToString((Date)map.get("validityPeriod"), "yyyy-MM-dd"));
				if(null != map.get("sponsorIsVender")){
				testItem.setSponsorIsVender((Integer)map.get("sponsorIsVender"));
				}
				testItem.setVenderId((String)map.get("venderId"));
				testItem.setVenderName((String)map.get("venderName"));
				testItem.setVenderAddress((String)map.get("venderAddress"));
				testItem.setVenderLinkman((String)map.get("venderLinkman"));
				testItem.setVenderTel((String)map.get("venderTel"));
				testItem.setVenderMobile((String)map.get("venderMobile"));
				testItem.setVenderEmail((String)map.get("venderEmail"));
				testItem.setVenderFax((String)map.get("venderFax"));
				if(null != map.get("state")){
					testItem.setTestItemState((Integer)map.get("state"));
				}
				if(null != map.get("confirmSign") && (!map.get("confirmSign").equals(""))){
					TblES tblES =tblESService.getById((String)map.get("confirmSign"));
					String signer = tblES.getSigner();
					String time = DateUtil.dateToString(tblES.getDateTime(), "yyyy-MM-dd");
					testItem.setConfirmSign(time+" "+signer);		
				}else{
					testItem.setConfirmSign("未确认");					
				}
				
				testItem.setReserveNum((String)map.get("reserveNum"));
				testItem.setSd((String)map.get("sd"));
//				if(null != map.get("sd") && (!map.get("sd").equals(""))){
//			    	testItem.setSd((String)map.get("sd"));
//				}
				listjson.add(testItem);
			}
		}
		return listjson;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getByStartimeAndEndtimeAndTiCodeConfirmFlagAndSD2(
			String startDate, String endDate, String tiCode, String tiName,
			String isConfirm, String sd) {
		String sql = "  select distinct tblcc.id,tblcc.contractCode,tblcc.tiNo,tblcc.tiCode,tblcc.tiName,tblcc.tiType,tblcc.content,tblcc.contentLabel,tblcc.sealNo,tblcc.fileNo,tblcc.physical, "+
		"  tblcc.meltPoint,tblcc.boilPoint,tblcc.photolysis,tblcc.volatility,tblcc.density,tblcc.waterSolubility,tblcc.waterStability,tblcc.solventSolubility   "+
		"  ,tblcc.solventStability,tblcc.ph ,tblcc.securityMeasures,tblcc.analysis,tblcc.postTreatment,tblcc.composition,tblcc.cas,tblcc.storageCondition, "+
		"  tblcc.validityPeriod ,tblcc.testItem_venderName,tblcc.venderName,tblcc.venderAddress,tblcc.venderLinkman ,tblcc.venderTel, "+
		"  tblcc.venderEmail,tblcc.venderFax,tblcc.state  ,tblcc.confirmSign ,tblcc.reserveNum,tblcc.submitDate," +
		"  tblcc.sponsorName,tblcc.sponsorAddress,tblcc.sponsorLinkman,tblcc.sponsorTel,tblcc.sponsorEmail,tblcc.sponsorFax,tblcc.failureDatePrecision,tblcc.isFailureDateFlag" ;
		if(null != sd && (!sd.equals(""))){
			sql = sql + " ,tbls.sd ";
		}
		sql = sql + " from "+
		"         ( select tblt.id,tblt.contractCode,tblt.tiNo,tblt.tiCode,tblt.tiName,tblt.tiType,tblt.content,tblt.contentLabel,tblt.sealNo,tblt.fileNo,tblt.physical,"+
			"            tblt.meltPoint,tblt.boilPoint,tblt.photolysis,tblt.volatility,tblt.density,tblt.waterSolubility,tblt.waterStability,tblt.solventSolubility  "+
			"            ,tblt.solventStability,tblt.ph ,tblt.securityMeasures,tblt.analysis,tblt.postTreatment,tblt.composition,tblt.cas,tblt.storageCondition,"+
			"            convert(varchar(10),tblt.validityPeriod,120) as validityPeriod ,tblt.state  ,tblt.venderName as testItem_venderName," +
			" 			case when isnull(tblt.confirmSign,'') = '' then '未确认' else  convert(varchar(10),es.dateTime,120)+' '+es.signer end as confirmSign," +
	//                     " tblt.reserveNum+' '+tblt.reserveUnit as reserveNum," +
			//留样量
			" 			(case when isnull(tblt.reserveNum,'') = '' then PLANDB.dbo.GETSMPLRESERVENUM(tblt.tiNo) else tblt.reserveNum+' '+tblt.reserveUnit end) as reserveNum," +
			" 			convert(varchar(10),tblc.submitDate,120) as submitDate,tblc.sponsorName,tblc.sponsorAddress,tblc.sponsorLinkman,tblc.sponsorTel,tblc.sponsorEmail,tblc.sponsorFax,tblc.venderName," +
			" 			tblc.venderAddress,tblc.venderLinkman,tblc.venderTel,tblc.venderEmail,tblc.venderFax,tblt.failureDatePrecision,tblt.isFailureDateFlag "+
		"            from CoresContract.dbo.tblTestItem as tblt left join CoresContract.dbo.tblContract as tblc  "+
			"            on tblt.contractCode = tblc.contractCode  left join CoresUserPrivilege.dbo.tblES as es"+
			"			on tblt.confirmSign = es.esId"+
		"            where tblt.state > 0 and  tblc.contractState !=0 " ;
		if(null != startDate && null != endDate ){
			sql = sql +  "and (convert(varchar(10),tblc.submitDate,120) between :startime and  :endtime  )";
		}
		
		if(null != tiCode && !tiCode.equals("")){
			sql = sql +"  and tblt.tiCode = :tiCode "; 
		}
		if(null != tiName && !tiName.equals("")){
			sql = sql +"  and  (tblt.tiName like :tiName or tblt.tiNo like :tiName or tblt.contractCode like :tiName )";
		}
		//  ""或null 全部  ,"0":未确认     ,"1":已确认
		if(null == isConfirm || isConfirm.equals("")){
			
		}else if(isConfirm .equals("0")){
			sql = sql +"  and  (tblt.confirmSign is null or tblt.confirmSign = '' )";
		}else if(isConfirm .equals("1")){
			sql = sql +"  and  (tblt.confirmSign is not null or tblt.confirmSign = '' )";
		}
		sql = sql  +"  ) as tblcc left join CoresContract.dbo.tblStudyItem as tbls ";
		sql = sql  +" on tblcc.tiNo = tbls.tiNo ";
		if(null != sd && (!sd.equals(""))){
//			sql = sql + " where tbls.sd = '"+sd+"' and tblcc.confirmSign is not null and tblcc.confirmSign != '' ";
			sql = sql + " where tbls.sd = '"+sd+"' ";
		}
		sql = sql + " order by tblcc.submitDate desc";
		Query query = getSession().createSQLQuery(sql);
		if(null != startDate && null != endDate ){
			query.setParameter("startime", startDate);
			query.setParameter("endtime", endDate);
		}
		if(null != tiCode && !tiCode.equals("")){
			query.setParameter("tiCode", tiCode);
		}
		if(null != tiName && !tiName.equals("")){
			query.setParameter("tiName","%"+ tiName+"%");
		}
		List<Map<String,Object>> list = query.setResultTransformer(new MapResultTransformer()).list();
//		List<TblTestItem_Json> listjson = new ArrayList<TblTestItem_Json>();
//		if (null != list) {
//			for (Map<String,Object> map : list) {
//				TblTestItem_Json testItem = new TblTestItem_Json();
//				testItem.setId((String)map.get("id"));
//				testItem.setContractCode((String)map.get("contractCode"));
//				testItem.setTiNo((String)map.get("tiNo"));
//				testItem.setTiCode((String)map.get("tiCode"));
//				testItem.setTiName((String)map.get("tiName"));
//				testItem.setTiType((String)map.get("tiType"));
//				testItem.setContent((String)map.get("content"));
//				testItem.setSealNo((String)map.get("sealNo"));
//				testItem.setFileNo((String)map.get("fileNo"));
//				testItem.setPhysical((String)map.get("physical"));
//				testItem.setMeltPoint((String)map.get("meltPoint"));
//				testItem.setBoilPoint((String)map.get("boilPoint"));
//				testItem.setPhotolysis((String)map.get("photolysis"));
//				testItem.setVolatility((String)map.get("volatility"));
//				testItem.setDensity((String)map.get("density"));
//				testItem.setWaterSolubility((String)map.get("waterSolubility"));
//				testItem.setWaterStability((String)map.get("waterStability"));
//				testItem.setSolventSolubility((String)map.get("solventSolubility"));
//				testItem.setSolventStability((String)map.get("solventStability"));
//				testItem.setPh((String)map.get("ph"));
//				testItem.setSecurityMeasures((String)map.get("securityMeasures"));
//				testItem.setAnalysis((String)map.get("analysis"));
//				testItem.setPostTreatment((String)map.get("postTreatment"));
//				testItem.setComposition((String)map.get("composition"));
//				testItem.setCas((String)map.get("cas"));
//				testItem.setStorageCondition((String)map.get("storageCondition"));
//				testItem.setValidityPeriod(DateUtil.dateToString((Date)map.get("validityPeriod"), "yyyy-MM-dd"));
//				if(null != map.get("sponsorIsVender")){
//					testItem.setSponsorIsVender((Integer)map.get("sponsorIsVender"));
//				}
//				testItem.setVenderId((String)map.get("venderId"));
//				testItem.setVenderName((String)map.get("venderName"));
//				testItem.setVenderAddress((String)map.get("venderAddress"));
//				testItem.setVenderLinkman((String)map.get("venderLinkman"));
//				testItem.setVenderTel((String)map.get("venderTel"));
//				testItem.setVenderMobile((String)map.get("venderMobile"));
//				testItem.setVenderEmail((String)map.get("venderEmail"));
//				testItem.setVenderFax((String)map.get("venderFax"));
//				if(null != map.get("state")){
//					testItem.setTestItemState((Integer)map.get("state"));
//				}
//				if(null != map.get("confirmSign") && (!map.get("confirmSign").equals(""))){
//					TblES tblES =tblESService.getById((String)map.get("confirmSign"));
//					String signer = tblES.getSigner();
//					String time = DateUtil.dateToString(tblES.getDateTime(), "yyyy-MM-dd");
//					testItem.setConfirmSign(time+" "+signer);		
//				}else{
//					testItem.setConfirmSign("未确认");					
//				}
//				
//				testItem.setReserveNum((String)map.get("reserveNum"));
//				testItem.setSd((String)map.get("sd"));
////				if(null != map.get("sd") && (!map.get("sd").equals(""))){
////			    	testItem.setSd((String)map.get("sd"));
////				}
//				listjson.add(testItem);
//			}
//		}
		return list;
	}

	public String getReserveNum(String tiNo) {
		String sql = "select (case when isnull(tblt.reserveNum,'') = '' then PLANDB.dbo.GETSMPLRESERVENUM(tblt.tiNo) else tblt.reserveNum+' '+tblt.reserveUnit end) as reserveNum" +
				" from CoresContract.dbo.tblTestItem as tblt " +
				" where  tblt.tiNo = :tiNo ";
		String reserveNum = (String) getSession().createSQLQuery(sql).setParameter("tiNo", tiNo).uniqueResult();
		
		return reserveNum != null ? reserveNum : "";
	}
	public void update(TblTestItem testItem, TblTestItemHis tblTestItemHis,
			String realName) {
		String hisId = getKey("TblTestItemHis");
		tblTestItemHis.setId(hisId);
		String signId = writeES(hisId,"TblTestItemHis",625,"供试品编辑",realName);
		tblTestItemHis.setOperateSign(signId);
		
		getSession().update(testItem);
		getSession().save(tblTestItemHis);
		
	}

	/**
	 * 签字
	 */
	private String writeES(String dataId,String tableName,int estype,String typeDesc,String operator){
		
		//签名链接
		TblESLink esLink = new TblESLink();
		//电子签名
		TblES es = new TblES();
		es.setSigner(operator);
        es.setEsType(estype);
        es.setEsTypeDesc(typeDesc);
        es.setDateTime(new Date());
        String esId = tblESService.getKey("TblES");
		es.setEsId(esId);
        esLink.setTableName(tableName);
		esLink.setDataId(dataId);
		esLink.setTblES(es);
		esLink.setEsType(estype);
        esLink.setEsTypeDesc(typeDesc);
        esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
			tblESService.save(es);
			tblESLinkService.save(esLink);
		return esId;
	}
	public void update(TblTestItem testItem, TblTestItemHis tblTestItemHis,
			String realName, TblContract contract) {
		//1.报告出具放问题
//		String sql1 = " update   CoresContract.dbo.tblContract "+
//		" set sponsorIsVender = :sponsorIsVender,venderId=:venderId,venderName=:venderName," +
//		" venderAddress =:venderAddress,venderLinkman =:venderLinkman,venderTel = :venderTel, "+
//		" venderMobile = :venderMobile,venderEmail =:venderEmail,venderFax = :venderFax "+
//		" where contractCode= :contractCode";
//		
//		getSession().createSQLQuery(sql1)
//		 .setParameter("contractCode", contract.getContractCode())
//		 .setParameter("sponsorIsVender", contract.getSponsorIsVender())
//		 .setParameter("venderId", contract.getVenderId())
//		 .setParameter("venderName", contract.getVenderName())
//		 .setParameter("venderAddress", contract.getVenderAddress())
//		 .setParameter("venderLinkman", contract.getVenderLinkman())
//		 .setParameter("venderTel", contract.getVenderTel())
//		 .setParameter("venderMobile", contract.getVenderMobile())
//		 .setParameter("venderEmail", contract.getVenderEmail())
//		 .setParameter("venderFax", contract.getVenderFax())
//		 .executeUpdate();
//		//更新相同的
//		String sql2 = "update   CoresContract.dbo.tblTestItem "+
//	                  " set sponsorIsVender = 1 "+
//	                  " where contractCode= :contractCode and venderId = :venderId";
//		 getSession().createSQLQuery(sql2)
//		 .setParameter("contractCode", contract.getContractCode())
//		 .setParameter("venderId", contract.getVenderId())
//		 .executeUpdate();
//		
//		//更新不相同的
//		String sql3 = "update   CoresContract.dbo.tblTestItem "+
//	                  " set sponsorIsVender = 0 "+
//	                  " where contractCode= :contractCode and venderId != :venderId";
//		  getSession().createSQLQuery(sql3)
//		 .setParameter("contractCode", contract.getContractCode())
//		 .setParameter("venderId", contract.getVenderId())
//		 .executeUpdate();
		//2.历史记录
		String hisId = getKey("TblTestItemHis");
		tblTestItemHis.setId(hisId);
		String signId = writeES(hisId,"TblTestItemHis",625,"供试品编辑",realName);
		tblTestItemHis.setOperateSign(signId);
		
		getSession().update(testItem);
		getSession().save(tblTestItemHis);
		
	}


}
