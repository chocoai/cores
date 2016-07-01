package com.lanen.service.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.jsonAndModel.HighGradeTreeGrid;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.contract.TblContract;
import com.lanen.model.contract.TblContractHis;
import com.lanen.model.contract.TblContract_Json;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.util.DateUtil;

/**
 * 合同信息 service实现
 * @author 黄国刚
 *
 */
@Service
public class TblContractServiceImpl extends BaseDaoImpl<TblContract> implements TblContractService{
	
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblESService tblESService;

	public boolean isExistByContractCode(String contractCode) {
		if(null != contractCode){
			List<?> list = getSession().createQuery("From TblContract where contractCode = ? ")
						.setParameter(0, contractCode)
						.list();
			if(null != list && list.size()>0){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}

	public boolean isExistByContractCode(String contractCode, String currentId) {
		if(null != contractCode && null!= currentId){
			List<?> list = getSession().createQuery("From TblContract where contractCode = ? and id != ?")
						.setParameter(0, contractCode)
						.setParameter(1, currentId)
						.list();
			if(null != list && list.size()>0){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}

	public List<Map<String, String>> getMapListByName(String customerName,String reader,boolean readAll) {
		
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		if(null != customerName && !"".equals(customerName) ){
			String sql = "SELECT distinct tblContract.sponsorId, tblContract.sponsorName"
				+ " FROM     CoresContract.dbo.tblContract as tblContract"
				+ " where tblContract.sponsorName like :sponsorName " ;
				
			if(readAll){
				sql = sql + " and ( tblContract.operator = :operator or tblContract.contractState )";
			}else{
				sql = sql + " and ( tblContract.operator = :operator  )";
			}
			sql = sql + " order by tblContract.sponsorId";
			
			List<?> list = getSession().createSQLQuery(sql)
			.setParameter("sponsorName", "%"+customerName+"%")
			.setParameter("operator", reader)
			.list();
			if (null != list) {
				for (Object obj : list) {
					Object[] objs = (Object[]) obj;
					map = new HashMap<String, String>();
					map.put("id", (String) objs[0]);
					map.put("text", (String) objs[1]);
					mapList.add(map);
				}
			}
		}
		return mapList;
	}

	public Map<String, Object> getMinMaxDateMapBySponsorId(String sponsorId) {
		Map<String, Object> map = new HashMap<String,Object>();
		if(null != sponsorId && !"".equals(sponsorId)){
			String sql = "SELECT max(tblContract.signingDate) as maxDate ,min(tblContract.signingDate) as minDate "
				+ " FROM     CoresContract.dbo.tblContract as tblContract"
				+ " where tblContract.sponsorId = :sponsorId ";
			
			Object obj = getSession().createSQLQuery(sql)
									.setParameter("sponsorId", sponsorId)
									.uniqueResult();
			Object[] objs = (Object[]) obj;
			Date maxDate = (Date) objs[0];
			Date minDate = (Date) objs[1];
			map.put("maxDate", DateUtil.dateToString(maxDate, "yyyy-MM-dd"));
			map.put("minDate", DateUtil.dateToString(minDate, "yyyy-MM-dd"));
		}
		return map;
	}

	public Map<String, Object> getDateRowsMapByContractCode(
			String contractCode) {
		Map<String, Object> map = new HashMap<String,Object>();
		if(null != contractCode && !"".equals(contractCode)){
			String sql = "SELECT tblContract.signingDate ,tblContract.sponsorId,tblContract.sponsorName  "
				+ " FROM     CoresContract.dbo.tblContract as tblContract"
				+ " where tblContract.contractCode = :contractCode ";
			
			List<?> list = getSession().createSQLQuery(sql)
									.setParameter("contractCode", contractCode)
									.list();
			if(null != list && list.size()>0){
				Object[] objs = (Object[]) list.get(0);
				Date maxDate = (Date) objs[0];
				Map<String,String> dataMap = new HashMap<String, String>();
				dataMap.put("id", (String)objs[1]);
				dataMap.put("text", (String)objs[2]);
				
				map.put("maxDate", DateUtil.dateToString(maxDate, "yyyy-MM-dd"));
				map.put("minDate", DateUtil.dateToString(maxDate, "yyyy-MM-dd"));
				map.put("data", dataMap);
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getMapListByContractCode(
			String contractCode,String reader,boolean readAll) {
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		if(null != contractCode && !"".equals(contractCode) ){
			
			String sql = "select distinct tblContract.contractCode "
				+ " from CoresContract.dbo.tblContract as tblContract"
				+ " where tblContract.contractCode like :contractCode " ;
			
			if(readAll){
				sql = sql +  " and ( tblContract.operator = :operator or tblContract.contractState != 0 )";
			}else{
				sql = sql +  " and ( tblContract.operator = :operator ) ";
			}
			sql = sql+ " order by tblContract.contractCode ";
			
			List<String> list = getSession().createSQLQuery(sql)
										.setParameter("contractCode", "%"+contractCode+"%")
										.setParameter("operator", reader)
										.list();
			Map<String, String> map = null;
			if (null != list) {
				for (String str : list) {
					map = new HashMap<String, String>();
					map.put("id", "@"+str);
					map.put("text",str);
					mapList.add(map);
				}
			}
		}
		return mapList;
	}

	public List<Map<String, String>> getIdCodeMapListByDateContractCodeSponsorId(
			Date minDate, Date maxDate, String contractCode, String sponsorId,String reader,boolean readAll) {
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		if(null != minDate && null != maxDate ){
			String sql = "SELECT  tblContract.id,tblContract.contractCode "
				+ " FROM CoresContract.dbo.tblContract as tblContract"
				+ " where tblContract.signingDate between :minDate and :maxDate "
				+ "  ";
			
			if(null !=contractCode && !"".equals(contractCode) ){
				sql = sql + " and tblContract.contractCode = :contractCode ";
			}
			if(null !=sponsorId && !"".equals(sponsorId) ){
				sql = sql + " and tblContract.sponsorId = :sponsorId ";
			}
			if(readAll){
				sql = sql + " and (tblContract.operator = :operator or tblContract.contractState != 0)";
			}else{
				sql = sql + " and (tblContract.operator = :operator )";
			}
			sql = sql +" order by tblContract.contractCode ";
			Query query = getSession().createSQLQuery(sql);
			query.setParameter("minDate", minDate);
			query.setParameter("maxDate", maxDate);
			
			if(null !=contractCode && !"".equals(contractCode) ){
				query.setParameter("contractCode", contractCode);
			}
			if(null !=sponsorId && !"".equals(sponsorId) ){
				query.setParameter("sponsorId", sponsorId);
			}
			
			query.setParameter("operator", reader);
			
			List<?> list = query.list();
			
			if (null != list) {
				for (Object obj : list) {
					Object[] objs= (Object[]) obj;
					map = new HashMap<String, String>();
					map.put("id", (String)objs[0]);
					map.put("contractCode", (String)objs[1]);
					mapList.add(map);
				}
			}
		}
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public String getIdByContractCode(String contractCode) {

		List<TblContract> tblContractList = getSession().createQuery("From TblContract t where t.contractCode = ?  ")
												.setParameter(0, contractCode).list();
		if(null != tblContractList && tblContractList.size()>0){
			return tblContractList.get(0).getId();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public TblContract getByContractCode(String contractCode) {
		List<TblContract> tblContractList = getSession().createQuery("From TblContract t where t.contractCode = ?  ")
		.setParameter(0, contractCode).list();
		if(null != tblContractList && tblContractList.size()>0){
			return tblContractList.get(0);
		}
		return null;
	}
	
	public List<?> getbyContractCodetestItemAndStudyItem(String contractCode) {
		String sql = " SELECT   tblt.id, tblt.contractCode, tblt.tiNo, tblt.tiName, tblt.tiType, tblt.[content], tblt.sealNo, " +
				"  tblt.fileNo, tblt.physical, "+
		"  tblt.meltPoint, tblt.boilPoint, tblt.photolysis, tblt.volatility, tblt.density, tblt.waterSolubility, tblt.waterStability, " +
		"  tblt.solventSolubility, tblt.solventStability, tblt.ph, tblt.securityMeasures, tblt.analysis, tblt.postTreatment, " +
		"  tblt.composition, tblt.cas, tblt.storageCondition, tblt.validityPeriod, tblt.sponsorIsVender, tblt.venderId, tblt.venderName, " +
		"  tblt.venderAddress, tblt.venderLinkman, tblt.venderTel, tblt.venderMobile, tblt.venderEmail, tblt.venderFax, " +
		"  tbls.id AS Expr1, tbls.contractCode AS Expr2, tbls.tiNo AS Expr3, tbls.studyTypeCode, tbls.studyName, tbls.studyNo, " +
		"  tbls.glpFlag, tbls.remark, tbls.sdCode, tbls.sd, tbls.qaCode, tbls.qa, tbls.sdManager, tbls.sdState, tbls.qaState, " +
		"  tbls.sdAppointDate, tbls.qaAppointDate, tbls.fmCode, tbls.fm, tbls.studyState, tbls.animalType, tbls.animalStrain, " +
		"  tbls.animalAge, tbls.numMale, tbls.numFemale ,tblt.state,tbls.state as state2,tblt.confirmSign,tbls.finishDate,tblt.contentLabel " +
		"  FROM  tblTestItem AS tblt LEFT OUTER JOIN " +
		"  tblStudyItem AS tbls ON tblt.contractCode = tbls.contractCode AND tblt.tiNo = tbls.tiNo " +
		"  WHERE  (tblt.contractCode = :contractCode ) ";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("contractCode", contractCode);
		List<?> list = query.list();
		return list;
	}

	public String getContractCodeById(String currentId) {
		TblContract tblContract = getById(currentId);
		if(null != tblContract){
			return tblContract.getContractCode();
		}
		return null;
	}

	public boolean isExistBySponsorId(String sponsorId) {
		String sql = "select count(contract.id) "+
					" from CoresContract.dbo.tblContract contract"+
					" where contract.sponsorId = ? ";
		Integer count = (Integer) getSession().createSQLQuery(sql)
									.setParameter(0, sponsorId)
									.uniqueResult();
		return count > 0;
	}

	public List<TblContract_Json> getByStartimeAndEndtimeAndTiCode(
			Date startime,Date endtime,String tiCode,String contractCode,boolean readAll,String reader) {
		String sql = "  SELECT cou1.*,tbls.c FROM ( SELECT distinct  tbl1.*,tblt1.cout FROM  "+
        "  (SELECT tblc.[id],tblc.[contractCode],tblc.[contractName],tblc.[sponsorId],tblc.[sponsorName]  "+
        "  ,tblc.[sponsorAddress],tblc.[sponsorLinkman],tblc.[sponsorTel],tblc.[sponsorMobile]  "+
        "  ,tblc.[sponsorEmail],tblc.[sponsorFax],tblc.[sponsorIsVender],tblc.[venderId]  "+
        "  ,tblc.[venderName],tblc.[venderAddress],tblc.[venderLinkman],tblc.[venderTel]  "+
        "  ,tblc.[venderMobile],tblc.[venderEmail],tblc.[venderFax],tblc.[contractPrice] "+
        "  ,tblc.[signingDate],tblc.[effectiveDate],tblc.[finishDate]  "+
        "  ,tblc.[contractState],tblc.[operator],tblc.[remark],tblc.[priceUnit],tbl.[tiCode]  "+
        "  FROM [CoresContract].[dbo].[tblContract] as tblc left join [CoresContract].[dbo].[tblTestItem] as tbl  "+
        "  ON  tblc.[contractCode] = tbl.[contractCode]  "+
        "  WHERE  tblc.contractState !=0 and  tblc.[signingDate]  between  :startime and :endtime " ;
        if(null != tiCode && !tiCode.equals("")){
  		  sql = sql+" and tbl.[tiCode] = :tiCode ";
  		}
        if(null != contractCode && !contractCode.equals("")){
        	sql = sql+"  and (tblc.[contractCode] like :contractCode or tblc.[contractName] like :contractCode or tblc.[sponsorName] like :contractCode or tblc.sponsorLinkman like :contractCode or tblc.sponsorTel like :contractCode or tblc.sponsorMobile like :contractCode )" ;
        }
        if(!readAll){
        	sql = sql+" and tblc.[operator] = :reader ";
        }
        sql = sql +
        "  ) as tbl1 left join ( "+
        "  SELECT count(tblt.id) as cout,tblt.contractCode "+
        "   FROM [CoresContract].[dbo].[tblTestItem] as tblt GROUP BY tblt.contractCode ) as  tblt1 "+
        "   ON  tbl1.[contractCode] = tblt1.[contractCode] ) as cou1  "+
        "  left join  (  "+
        "   SELECT  count(tiNo) as c ,contractCode   FROM [CoresContract].[dbo].[tblStudyItem] "+
        "   GROUP BY contractCode  "+
        "  ) as tbls  ON cou1.[contractCode] = tbls.contractCode  ";
		
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("startime", startime);
		query.setParameter("endtime", endtime);
		if(null != tiCode && !tiCode.equals("")){
			query.setParameter("tiCode", tiCode);
		}
		if(null != contractCode && !contractCode.equals("")){
        	query.setParameter("contractCode","%"+ contractCode+"%");
        }
		if(!readAll){
			query.setParameter("reader", reader);
		}
		List<?> list = query.list();
		List<TblContract_Json> listjson = new ArrayList<TblContract_Json>();
		String ContractCodestr ="";
		if (null != list) {
			for (Object obj : list) {
				Object[] objs= (Object[]) obj;
				TblContract_Json contract = new TblContract_Json();
				if(!ContractCodestr.contains("@"+(String)objs[1]+"@") ||ContractCodestr.equals("") ){
					contract.setId((String)objs[0]);
					contract.setContractCode((String)objs[1]);
					ContractCodestr = ContractCodestr+"@"+(String)objs[1]+"@";
					contract.setContractName((String)objs[2]);
					contract.setSponsorId((String)objs[3]);
					contract.setSponsorName((String)objs[4]);
					contract.setSponsorAddress((String)objs[5]);
					contract.setSponsorLinkman((String)objs[6]);
					contract.setSponsorTel((String)objs[7]);
					contract.setSponsorMobile((String)objs[8]);
					contract.setSponsorEmail((String)objs[9]);
					contract.setSponsorFax((String)objs[10]);
					if(null != objs[11]){
						contract.setSponsorIsVender((Integer)objs[11]);
					}
					contract.setVenderId((String)objs[12]);
					
					contract.setVenderName((String)objs[13]);
					
					contract.setVenderAddress((String)objs[14]);
					contract.setVenderLinkman((String)objs[15]);
					contract.setVenderTel((String)objs[16]);
					
					contract.setVenderMobile((String)objs[17]);
					contract.setVenderEmail((String)objs[18]);
					contract.setVenderFax((String)objs[19]);
					if(null != objs[20]){
						if((Integer)objs[27]==1){
							contract.setContractPrice((String)objs[20]+"元");
						}else if((Integer)objs[27]==2){
							contract.setContractPrice((String)objs[20]+"美元");
						}else if((Integer)objs[27]==3){
							contract.setContractPrice((String)objs[20]+"欧元");
						}else if((Integer)objs[27]==4){
							contract.setContractPrice((String)objs[20]+"万元");
						}
					}else{
						contract.setContractPrice("");
					}
//					contract.setContractPrice((String)objs[20]);
					if(null != objs[21]){
						contract.setSigningDate(DateUtil.dateToString((Date)objs[21], "yyyy-MM-dd"));
					}
					if(null != objs[22]){
						contract.setEffectiveDate(DateUtil.dateToString((Date)objs[22], "yyyy-MM-dd"));
					}
					if(null != objs[23]){
						contract.setFinishDate(DateUtil.dateToString((Date)objs[23], "yyyy-MM-dd"));
					}
					if(null != objs[24]){
						contract.setContractState((Integer)objs[24]);
					}
					contract.setOperator((String)objs[25]);
					contract.setRemark((String)objs[26]);
					if(null != objs[27]){
						contract.setPriceUnit((Integer)objs[27]);
					}
					contract.setTiCode((String)objs[28]);
					if(null != objs[29] && !objs[29].equals("")){
						contract.setCountTestItem((Integer)objs[29]+"");
					}else{
						contract.setCountTestItem("0");
					}
					if(null != objs[30] && !objs[30].equals("")){
						contract.setCountstudyItem((Integer)objs[30]+"");
					}else{
						contract.setCountstudyItem("0");
					}
					listjson.add(contract);
					
				}
			}
		}
		return listjson;
	}

	public void updateContractStateAndSavetblES(TblES tblES,
			TblESLink tblESLink, String contractCode) {
		String sql  = " update CoresContract.dbo.tblContract  set contractState = 1  where contractCode = ? ";
		String sql0 = " update CoresContract.dbo.tblContract  set submitDate = getdate()  where contractCode = ? ";
		String sql1 = " update CoresContract.dbo.tblContractAttachment  set state = 1  where contractCode= ? ";
		String sql2 = " update CoresContract.dbo.tblTestItem  set state = 1  where contractCode= ? ";
		String sql3 = " update CoresContract.dbo.tblStudyItem  set state = 1  where contractCode= ? ";
		String sql4 = " insert into CoresUserPrivilege.dbo.tblES (esId,signer,esType,esTypeDesc,dateTime,dataSource) " +
				" values (:esId,:signer,:esType,:esTypeDesc,:dateTime,:dataSource) ";
		String sql5 = " insert into CoresUserPrivilege.dbo.tblESLink " +
				" (linkId,tableName,dataId,esType,esTypeDesc,recordTime,tblESId,esId) values " +
				" (:linkId,:tableName,:dataId,:esType,:esTypeDesc,:recordTime,:tblESId,:esId) ";
		getSession().createSQLQuery(sql ).setParameter(0, contractCode).executeUpdate();
		getSession().createSQLQuery(sql0).setParameter(0, contractCode).executeUpdate();
		getSession().createSQLQuery(sql1).setParameter(0, contractCode).executeUpdate();
		getSession().createSQLQuery(sql2).setParameter(0, contractCode).executeUpdate();
		getSession().createSQLQuery(sql3).setParameter(0, contractCode).executeUpdate();
		getSession().createSQLQuery(sql4).setParameter("esId", tblES.getEsId()).setParameter("signer", tblES.getSigner())
		.setParameter("esType", tblES.getEsType()).setParameter("esTypeDesc", tblES.getEsTypeDesc())
		.setParameter("dateTime", tblES.getDateTime()).setParameter("dataSource", tblES.getDataSource()).executeUpdate();
		getSession().createSQLQuery(sql5).setParameter("linkId", tblESLink.getLinkId()).setParameter("tableName", tblESLink.getTableName())
		.setParameter("dataId", tblESLink.getDataId()).setParameter("esType", tblESLink.getEsType())
		.setParameter("esTypeDesc", tblESLink.getEsTypeDesc()).setParameter("recordTime", tblESLink.getRecordTime())
		.setParameter("tblESId", tblESLink.getTblES().getEsId()).setParameter("esId", tblESLink.getTblES().getEsId()).executeUpdate();
	}
	
	
	public void updateContractState(String contractCode) {
		
		String sql  = " update CoresContract.dbo.tblContract "+
					  " set contractState = 1 where contractCode = ? ";
		String sql0 = " update CoresContract.dbo.tblContract "+
		              " set submitDate = getdate() where contractCode = ? ";
		String sql1 = " update CoresContract.dbo.tblContractAttachment "+
					  " set state = 1 where contractCode= ? ";
		String sql2 = " update  CoresContract.dbo.tblTestItem "+
					  " set state = 1 where contractCode= ? ";
		String sql3 = " update  CoresContract.dbo.tblStudyItem "+
					  " set state = 1 where contractCode= ? ";
	    
		getSession().createSQLQuery(sql ).setParameter(0, contractCode).executeUpdate();
		getSession().createSQLQuery(sql0).setParameter(0, contractCode).executeUpdate();
		getSession().createSQLQuery(sql1).setParameter(0, contractCode).executeUpdate();
		getSession().createSQLQuery(sql2).setParameter(0, contractCode).executeUpdate();
		getSession().createSQLQuery(sql3).setParameter(0, contractCode).executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	public TblContract getByCode(String contractCode) {
		if(null != contractCode ){
			List<TblContract> list = getSession().createQuery("From TblContract where contractCode = ?")
						.setParameter(0, contractCode)
						.list();
			if(null != list && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}

	public void update(TblContract tblContract, String oldContractCode) {
		getSession().update(tblContract);
		String sql4 = "update  "+
		" CoresContract.dbo.tblTestItem "+
		" set venderId = :venderId , venderName = :venderName , venderAddress = :venderAddress ,venderLinkman = :venderLinkman " +
		" ,venderTel = :venderTel ,venderMobile = :venderMobile, " +
		" venderEmail = :venderEmail ,venderFax = :venderFax "+
		" where contractCode= :contractCode  and sponsorIsVender = 1 ";
		getSession().createSQLQuery(sql4)
		.setParameter("contractCode", oldContractCode)
		.setParameter("venderId", tblContract.getVenderId())
		.setParameter("venderName", tblContract.getVenderName())
		.setParameter("venderAddress", tblContract.getVenderAddress())
		.setParameter("venderLinkman", tblContract.getVenderLinkman())
		.setParameter("venderTel", tblContract.getVenderTel())
		.setParameter("venderMobile", tblContract.getVenderMobile())
		.setParameter("venderEmail", tblContract.getVenderEmail())
		.setParameter("venderFax", tblContract.getVenderFax())
		.executeUpdate();
	
		if(!tblContract.getContractCode().equals(oldContractCode)){
			String sql1 = "update  "+
						" CoresContract.dbo.tblContractAttachment "+
						" set contractCode = ? "+
						" where contractCode= ? ";
			String sql2 = "update  "+
						" CoresContract.dbo.tblTestItem "+
						" set contractCode = ? "+
						" where contractCode= ? ";
			String sql3 = "update  "+
						" CoresContract.dbo.tblStudyItem "+
						" set contractCode = ? "+
						" where contractCode= ? ";
			
			getSession().createSQLQuery(sql1)
						.setParameter(0, tblContract.getContractCode())
						.setParameter(1, oldContractCode)
						.executeUpdate();
			getSession().createSQLQuery(sql2)
						.setParameter(0, tblContract.getContractCode())
						.setParameter(1, oldContractCode)
						.executeUpdate();
			getSession().createSQLQuery(sql3)
						.setParameter(0, tblContract.getContractCode())
						.setParameter(1, oldContractCode)
						.executeUpdate();
		}
		
	}

	public boolean isViewContractByUser(String customerId, boolean readAll,
			String reader) {
		boolean flag=false;
		String sql="select operator,contractState from CoresContract.dbo.tblContract where sponsorId=:customerId ";
		Query query = getSession().createSQLQuery(sql).setParameter("customerId", customerId);
		List<?> list=query.list();
		if(list!=null){
			for(Object obj:list){
				Object[] objs = (Object[]) obj;
				String r  =(String)objs[0];
				Integer contractState =(Integer)objs[1];
				if((readAll && contractState != 0) || r.equals(reader)){
					flag=true;
					return flag;
				}
			}
			
		}
		return flag;
	}
   //根据合同编号查询当前合同的金额单位
	public Integer getPriceUnitByContractCode(String contractCode) {
		String sql="SELECT [priceUnit] FROM [CoresContract].[dbo].[tblContract] where contractCode=:contractCode";
		List<?> list= getSession().createSQLQuery(sql).setParameter("contractCode", contractCode).list();
		Integer priceUnit=null;
		if(list!=null){
			for(Object obj:list){
				priceUnit=(Integer)obj;
			}
			
		}
		return priceUnit;
	}

	@SuppressWarnings("unchecked")
	public List<HighGradeTreeGrid> getHighGradeTreeGrid(String operator, int readAllInt, 
			String contractCode, String contractName, String sponsorName, String sponsorLinkman, String sponsorTel,
			String tiCode, String tiNo, String tiName, 
			String studyNo, String studyName, String sd, int glpFlag) {
		String sql ="select contract.id as cid,contract.contractCode,contract.contractName,contract.sponsorName," +
					" contract.sponsorLinkman,contract.sponsorTel,contract.sponsorMobile," +
					" ti.id as tiId,ti.tiNo,tiName,ti.tiCode,ti.physical,ti.content," +
					" si.id as siId,si.studyNo,si.studyName,si.glpFlag,si.sd,si.finishDate "+
					" from CoresContract.dbo.tblContract as contract "+
					" left join CoresContract.dbo.tblTestItem as ti on contract.contractCode = ti.contractCode "+
					" left join CoresContract.dbo.tblStudyItem as si on ti.contractCode = si.contractCode and ti.tiNo = si.tiNo" +
					" where contract.contractState != 0";
		
		sql = sql +" and (contract.operator = :operator or 1 = :readAllInt ) ";
		if(null != contractCode && !"".equals(contractCode)){
			sql = sql+" and contract.contractCode like :contractCode";
		}
		if(null != contractName && !"".equals(contractName)){
			sql = sql+" and contract.contractName like :contractName";
		}
		if(null != sponsorName && !"".equals(sponsorName)){
			sql = sql+" and contract.sponsorName like :sponsorName";
		}
		if(null != sponsorLinkman && !"".equals(sponsorLinkman)){
			sql = sql+" and contract.sponsorLinkman like :sponsorLinkman";
		}
		if(null != sponsorTel && !"".equals(sponsorTel)){
			sql = sql+" and (contract.sponsorTel like :sponsorTel or contract.sponsorMobile like :sponsorTel)";
		}
		if(null != tiCode && !"".equals(tiCode)){
			sql = sql+" and (ti.tiCode = :tiCode or '00' = :tiCode )";
		}
		if(null != tiNo && !"".equals(tiNo)){
			sql = sql+" and ti.tiNo like :tiNo";
		}
		if(null != tiName && !"".equals(tiName)){
			sql = sql+" and ti.tiName like :tiName";
		}
		if(null != studyNo && !"".equals(studyNo)){
			sql = sql+" and si.studyNo like :studyNo";
		}
		if(null != studyName && !"".equals(studyName)){
			sql = sql+" and si.studyName like :studyName";
		}
		if(null != sd && !"".equals(sd)){
			sql = sql+" and si.sd like :sd";
		}

		if(glpFlag != -1){
			sql = sql+" and (si.glpFlag = :glpFlag or -1 = :glpFlag)";
		}
		
		Query query = getSession().createSQLQuery(sql);
		
		query.setParameter("operator", operator).setParameter("readAllInt", readAllInt);
		if(null != contractCode && !"".equals(contractCode)){
			query.setParameter("contractCode", "%"+contractCode+"%");
		}
		if(null != contractName && !"".equals(contractName)){
			query.setParameter("contractName", "%"+contractName+"%");
		}
		if(null != sponsorName && !"".equals(sponsorName)){
			query.setParameter("sponsorName", "%"+sponsorName+"%");
		}
		if(null != sponsorLinkman && !"".equals(sponsorLinkman)){
			query.setParameter("sponsorLinkman", "%"+sponsorLinkman+"%");
		}
		if(null != sponsorTel && !"".equals(sponsorTel)){
			query.setParameter("sponsorTel", "%"+sponsorTel+"%");
		}
		if(null != tiCode && !"".equals(tiCode)){
			query.setParameter("tiCode", tiCode);
		}
		if(null != tiNo && !"".equals(tiNo)){
			query.setParameter("tiNo", "%"+tiNo+"%");
		}
		if(null != tiName && !"".equals(tiName)){
			query.setParameter("tiName", "%"+tiName+"%");
		}
		if(null != studyNo && !"".equals(studyNo)){
			query.setParameter("studyNo", "%"+studyNo+"%");
		}
		if(null != studyName && !"".equals(studyName)){
			query.setParameter("studyName", "%"+studyName+"%");
		}
		if(null != sd && !"".equals(sd)){
			query.setParameter("sd", "%"+sd+"%");
		}

		if(glpFlag != -1){
			query.setParameter("glpFlag", glpFlag);
		}
		
		List<Map<String,Object>> list = query
							.setResultTransformer(new MapResultTransformer())
							.list();
		//存放最终结果
		List<HighGradeTreeGrid> highGradeTreeGridList = null;
		if(null != list && list.size()>0){
			highGradeTreeGridList = new ArrayList<HighGradeTreeGrid>();
			
			HighGradeTreeGrid highGradeTreeGrid =null;
			//存放合同Id
			List<Object> contractIdList = new ArrayList<Object>();
			//存放供试品Id
			List<Object> testItemIdList = new ArrayList<Object>();
			for(Map<String,Object> map :list){
				//委托项目
				if( null != map.get("siId")){
					highGradeTreeGrid = new HighGradeTreeGrid();
					highGradeTreeGrid.setId("s"+(String)map.get("siId"));
					highGradeTreeGrid.setName((String)map.get("studyName"));
					highGradeTreeGrid.setCode((String)map.get("studyNo"));
					highGradeTreeGrid.set_parentId("t"+(String)map.get("tiId"));
					highGradeTreeGrid.setLevel(3);
					Integer currentGlpFlag = (Integer) map.get("glpFlag");
					highGradeTreeGrid.setType(currentGlpFlag == 1 ?"GLP":"");
					String currentSD = (String) map.get("sd");
				
					String text = "";
					if(null !=  map.get("finishDate")){
						String finishDate = DateUtil.dateToString((Date)map.get("finishDate"),"yyyy-MM-dd" );
						text = "要求完成日期："+ finishDate+";";
					}
					if(null != currentSD){
						text = text +"   SD:"+currentSD;
					}
					highGradeTreeGrid.setText(text);
					//
					highGradeTreeGridList.add(highGradeTreeGrid);
				}
				//供试品
				if( null != map.get("tiId") && !testItemIdList.contains(map.get("tiId"))){
					testItemIdList.add(map.get("tiId"));
					highGradeTreeGrid = new HighGradeTreeGrid();
					highGradeTreeGrid.setId("t"+(String)map.get("tiId"));
					highGradeTreeGrid.setName((String)map.get("tiName"));
					highGradeTreeGrid.setCode((String)map.get("tiNo"));
					highGradeTreeGrid.set_parentId((String)map.get("cid"));
					highGradeTreeGrid.setLevel(2);
					
					String currentTICode = (String) map.get("tiCode");
					if(null != currentTICode && currentTICode.equals("01")){
						currentTICode ="医药";
					}else if(null != currentTICode && currentTICode.equals("02")){
						currentTICode ="农药";
					}else if(null != currentTICode && currentTICode.equals("03")){
						currentTICode ="化学品";
					}
					highGradeTreeGrid.setType(currentTICode);
					
					String text ="";
					String content = (String) map.get("content");
					String physical = (String) map.get("physical");
					if(null != content && !"".equals(content)){
						text = text+" 含量："+content;
					}
					if(null != physical && !"".equals(physical)){
						text = text+", 外观："+physical;
					}
					
					highGradeTreeGrid.setText(text);
					//
					highGradeTreeGridList.add(highGradeTreeGrid);
				}
				//合同
				if( null != map.get("cid") && !contractIdList.contains(map.get("cid"))){
					contractIdList.add(map.get("cid"));
					highGradeTreeGrid = new HighGradeTreeGrid();
					highGradeTreeGrid.setId((String)map.get("cid"));
					highGradeTreeGrid.setName((String)map.get("contractName"));
					String currentContractCode = (String)map.get("contractCode");
//					if(null != contractCode && !"".equals(contractCode)){
//						currentContractCode = currentContractCode.replaceAll(contractCode, "<a style='color:red;text:bold;'>"+contractCode+"</a>");
//					}
					highGradeTreeGrid.setCode(currentContractCode);
					highGradeTreeGrid.setLevel(1);
					
					
					String text ="";
					String currentSponsorName = (String) map.get("sponsorName");
					String currentSponsorLinkman = (String) map.get("sponsorLinkman");
					String currentsponsorTel = (String) map.get("sponsorTel");
					String sponsorMobile = (String) map.get("sponsorMobile");
					if(null != currentSponsorName && !"".equals(currentSponsorName)){
						text = text+" "+currentSponsorName;
					}
					if(null != currentSponsorLinkman && !"".equals(currentSponsorLinkman)){
						text = text+", 联系人："+currentSponsorLinkman;
					}
					if(null != currentsponsorTel && !"".equals(currentsponsorTel)){
						text = text+", 电话："+currentsponsorTel;
					}
					if(null != sponsorMobile && !"".equals(sponsorMobile)){
						text = text+", 手机："+sponsorMobile;
					}
					
					highGradeTreeGrid.setText(text);
					//
					highGradeTreeGridList.add(highGradeTreeGrid);
				}
			}
		}
		return highGradeTreeGridList;
	}

	public void update(TblContract tblContract, TblContractHis tblContractHis,
			String realName) {
		
		String sql4 = "update  "+
		" CoresContract.dbo.tblTestItem "+
		" set venderId = :venderId , venderName = :venderName , venderAddress = :venderAddress ,venderLinkman = :venderLinkman " +
		" ,venderTel = :venderTel ,venderMobile = :venderMobile, " +
		" venderEmail = :venderEmail ,venderFax = :venderFax "+
		" where contractCode= :contractCode  and sponsorIsVender = 1 ";
		getSession().createSQLQuery(sql4)
		.setParameter("contractCode", tblContract.getContractCode())
		.setParameter("venderId", tblContract.getVenderId())
		.setParameter("venderName", tblContract.getVenderName())
		.setParameter("venderAddress", tblContract.getVenderAddress())
		.setParameter("venderLinkman", tblContract.getVenderLinkman())
		.setParameter("venderTel", tblContract.getVenderTel())
		.setParameter("venderMobile", tblContract.getVenderMobile())
		.setParameter("venderEmail", tblContract.getVenderEmail())
		.setParameter("venderFax", tblContract.getVenderFax())
		.executeUpdate();
		
		String hisId = getKey("TblContractHis");
		tblContractHis.setId(hisId);
		String signId = writeES(hisId,"TblContractHis",624,"合同编辑",realName);
		tblContractHis.setOperateSign(signId);
		
		getSession().update(tblContract);
		getSession().save(tblContractHis);
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

}
