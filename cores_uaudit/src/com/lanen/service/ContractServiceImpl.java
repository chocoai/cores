package com.lanen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanen.base.MapResultTransformer;

@Service
@Transactional
public class ContractServiceImpl implements ContractService{

	@Resource
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getContractMapListByYear(String year) {
		String sql = "select c.contractCode"+
					" from CoresContract.dbo.tblContract as c"+
					" where convert(varchar(4),c.submitDate,120) = :year "+
					" order by c.contractCode";
		List<Map<String,Object>> mapList = sessionFactory.getCurrentSession()
				.createSQLQuery(sql)
				.setParameter("year", year)
				.setResultTransformer(new MapResultTransformer())
				.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<String> getYearList() {
		String sql = "select cc.year_"+
					" from (	"+
					" 	select distinct convert(varchar(4),c.submitDate,120) as year_"+
					" 	from CoresContract.dbo.tblContract as c"+
					" 	where c.submitDate is not null ) as cc"+
					" order by cc.year_ desc";
		List<String> yearList = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return yearList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getContractList(String contractCode) {
		String sql = "select *"+
					" from ("+
					" 	select h.id,h.contractCode,h.contractName,h.sponsorName, convert(varchar(10),h.effectiveDate,120) as effectiveDate,"+
					" 		case when isnull(h.contractPrice,'') = '' then '' else "+
					" 			h.contractPrice+' '+ (case h.priceUnit when 1 then '元' "+
					" 				when 4 then '万元' when 2 then '美元' when 3 then '欧元' "+
					" 				else '' end) end as contractPrice, convert(varchar(10),h.finishDate,120) as finishDate,"+
					" 		h.contractState, 1 as operate ,convert(varchar(19),h.operateTime,120) as operateTime,h.operateRsn," +
					" h.sponsorAddress,h.sponsorLinkman,h.sponsorTel,h.sponsorEmail,h.sponsorFax,h.venderName,h.venderLinkman,"+
					" h.venderAddress,h.venderTel,h.venderEmail,h.venderFax"+
					" 	from CoresContract.dbo.tblContractHis as h"+
					" 	where h.contractCode = :contractCode "+
					" 		union"+
					" 	select h.id,h.contractCode,h.contractName,h.sponsorName, convert(varchar(10),h.effectiveDate,120) as effectiveDate,"+
					" 		case when isnull(h.contractPrice,'') = '' then '' else "+
					" 		h.contractPrice+' '+ (case h.priceUnit when 1 then '元' "+
					" 				when 4 then '万元' when 2 then '美元' when 3 then '欧元' "+
					" 				else '' end) end as contractPrice, convert(varchar(10),h.finishDate,120) as finishDate,"+
					" 		h.contractState, 0 as operate ,'' as operateTime,'' as operateRsn," +
					" h.sponsorAddress,h.sponsorLinkman,h.sponsorTel,h.sponsorEmail,h.sponsorFax,h.venderName,h.venderLinkman,"+
					" h.venderAddress,h.venderTel,h.venderEmail,h.venderFax"+
					" 	from CoresContract.dbo.tblContract as h"+
					" 	where h.contractCode = :contractCode ) as r"+
					" order by r.operate desc,r.id";
		List<Map<String,Object>> mapList = sessionFactory.getCurrentSession()
				.createSQLQuery(sql)
				.setParameter("contractCode", contractCode)
				.setResultTransformer(new MapResultTransformer())
				.list();
		if(null != mapList && mapList.size() == 1){
			return new ArrayList<Map<String,Object>>();
		}
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTestitemList(String contractCode) {
		String sql = "select r.contractCode,r.tiNo,r.tiName,r.content,r.physical,r.storageCondition," +
				" 		case when r.validityPeriod is null then '' else "+
				" 		("+
				" 			case when r.isFailureDateFlag = 0 then '有效期：' else '失效期：' end+"+
				" 			case r.failureDatePrecision when 3 then convert(varchar(10),r.validityPeriod,120) "+
				" 									when 2 then convert(varchar(7),r.validityPeriod,120)"+
				" 									when 1 then convert(varchar(4),r.validityPeriod,120) end"+
				" 		)  "+
				" 	end  as validityPeriod,"+
		" 		r.reserveNum ,r.batchNo,r.sealNo,r.fileNo,r.meltPoint,r.boilPoint,"+
		" 		r.photolysis,r.volatility,r.composition,r.density,r.waterSolubility,r.waterStability,r.solventSolubility,"+
		" 		r.solventStability,r.ph,r.securityMeasures,r.analysis,r.postTreatment,r.cas,r.venderName,r.venderLinkman,"+
		" 		r.venderAddress,r.venderTel,r.venderEmail,r.venderFax, r.operate,r.operateTime,r.operateRsn"+
					" from ("+
					" 	select t.contractCode,t.tiNo,t.tiName,t.content,t.physical,t.storageCondition,t.validityPeriod,"+
					" 		t.reserveNum +''+ t.reserveUnit as reserveNum,t.batchNo,t.sealNo,t.fileNo,t.meltPoint,t.boilPoint,"+
					" 		t.photolysis,t.volatility,t.composition,t.density,t.waterSolubility,t.waterStability,t.solventSolubility,"+
					" 		t.solventStability,t.ph,t.securityMeasures,t.analysis,t.postTreatment,t.cas,t.venderName,t.venderLinkman,"+
					" 		t.venderAddress,t.venderTel,t.venderEmail,t.venderFax, 1 as operate,convert(varchar(19),t.operateTime,120)"+
					" 	 as operateTime,t.operateRsn,t.isFailureDateFlag,t.failureDatePrecision "+
					" 	from CoresContract.dbo.tblTestItemHis as t"+
					" 	where t.contractCode = :contractCode"+
					" 	union"+
					" 	select distinct t.contractCode,t.tiNo,t.tiName,t.content,t.physical,t.storageCondition,t.validityPeriod,"+
					" 		t.reserveNum +''+ t.reserveUnit as reserveNum,t.batchNo,t.sealNo,t.fileNo,t.meltPoint,t.boilPoint,"+
					" 		t.photolysis,t.volatility,t.composition,t.density,t.waterSolubility,t.waterStability,t.solventSolubility,"+
					" 	t.solventStability,t.ph,t.securityMeasures,t.analysis,t.postTreatment,t.cas,t.venderName,t.venderLinkman,"+
					" 	t.venderAddress,t.venderTel,t.venderEmail,t.venderFax, 0 as operate,'' as operateTime,'' as operateRsn," +
					" t.isFailureDateFlag,t.failureDatePrecision"+
					" from CoresContract.dbo.tblTestItem as t join CoresContract.dbo.tblTestItemHis as h"+
					" 	on t.id = h.oldId"+
					" where t.contractCode = :contractCode) as r"+
					" order by r.tiNo,r.operate desc,r.operateTime";
		List<Map<String,Object>> mapList = sessionFactory.getCurrentSession()
				.createSQLQuery(sql)
				.setParameter("contractCode", contractCode)
				.setResultTransformer(new MapResultTransformer())
				.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>>  getStudyitemList(String contractCode) {
		String sql = "select *"+
					" from ("+
					" 	select s.contractCode,s.tiNo,s.studyNo,s.studyName,s.glpFlag,s.animalType,s.animalStrain,"+
					" 		s.animalAge,s.numMale,s.numFemale,convert(varchar(10),s.finishDate,120) as finishDateStr,"+
					" 		s.remark,1 as operate,convert(varchar(19),s.operateTime,120) as operateTime,s.operateRsn"+
					" 	from CoresContract.dbo.tblStudyItemHis as s"+
					" 	where s.contractCode = :contractCode"+
					" 		union"+
					" 	select distinct s.contractCode,s.tiNo,s.studyNo,s.studyName,s.glpFlag,s.animalType,s.animalStrain,"+
					" 		s.animalAge,s.numMale,s.numFemale,convert(varchar(10),s.finishDate,120) as finishDateStr,"+
					" 		s.remark,0 as operate,'' as operateTime,'' as operateRsn"+
					" 	from CoresContract.dbo.tblStudyItem as s join CoresContract.dbo.tblStudyItemHis as h"+
					" 		on s.id = h.oldId"+
					" 	where s.contractCode = :contractCode) as r"+
					" order by r.tiNo,r.studyNo,r.operate desc,r.operateTime";
		List<Map<String,Object>> mapList = sessionFactory.getCurrentSession()
				.createSQLQuery(sql)
				.setParameter("contractCode", contractCode)
				.setResultTransformer(new MapResultTransformer())
				.list();
		return mapList;
	}

	
	
}
