package com.lanen.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanen.base.MapResultTransformer;

@Transactional
@Service
public class QaServiceImpl implements QaService {

	@Resource
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getList(String studyNo, int operateType) {
		List<Map<String,Object>> mapList = null;
		String sql = "";
		if( operateType == 0){
			sql = "select t.studyNo,t.operateTime,t.operator,t.operateType"+
			" from "+
			" ("+
			" select esl.esType,esl.esTypeDesc,esl.dataId,sfi.studyNo,convert(varchar(16),es.dateTime,120) as operateTime,es.signer as operator,"+
			" 	case esl.esType when 808 then '专题方案提交' when 809 then '专题报告提交' "+
			" 	when 842 then 'QA接收专题报告' when 822 then 'QA接收专题方案' "+
			" 	else '' end as operateType"+
			" from CoresQA.dbo.TblStudyFileIndex as sfi join CoresUserPrivilege.dbo.tblESLink as esl"+
			" 	on sfi.studyFileIndexID = esl.dataId join CoresUserPrivilege.dbo.tblES as es"+
			" 	on esl.esId = es.esId"+
			" where sfi.studyNo = :studyNo and (esl.esType = 808 or  esl.esType = 809 or  esl.esType = 822 or  esl.esType = 842)"+
			" union"+
			" select esl.esType,esl.esTypeDesc,esl.dataId,sfi.studyNo,convert(varchar(16),es.dateTime,120) as operateTime,es.signer as operator,"+
			" 	 '方案阅读完成' as operateType"+
			" from CoresQA.dbo.TblStudyFileIndex as sfi join CoresQA.dbo.[TblStudyPlanReadRecord] as sprr "+
			" 		on sfi.studyFileIndexID = sprr.studyNo  join CoresUserPrivilege.dbo.tblESLink as esl"+
			" 	on sprr.ID = esl.dataId join CoresUserPrivilege.dbo.tblES as es"+
			" 	on esl.esId = es.esId"+
			" where sfi.studyNo = :studyNo and esl.esType = 834"+
			" union"+
			" select esl.esType,esl.esTypeDesc,esl.dataId,esl.dataId as studyNo,convert(varchar(16),es.dateTime,120) as operateTime,es.signer as operator,"+
			" 	case esl.esType when 801 then '检查计划提交' when 810 then '检查计划审批通过' when 811 then '检查计划审批未通过' else '' end as operateType"+
			" from CoresUserPrivilege.dbo.tblESLink as esl"+
			" 	join CoresUserPrivilege.dbo.tblES as es"+
			" 	on esl.esId = es.esId"+
			" where esl.dataId = :studyNo and esl.tableName='QAStudyChkIndex' and (esl.esType = 801 or esl.esType = 810 or esl.esType = 811)"+
			" union"+
			" select esl.esType,esl.esTypeDesc,esl.dataId,cpc.studyNo,convert(varchar(16),es.dateTime,120) as operateTime,es.signer as operator,"+
			" 	case esl.esType when 817 then '检查计划变更申请' when 830 then '检查计划变更申请审批通过' "+
			" 	when 831 then '检查计划变更申请审批未通过' when 844 then '检查计划变更申请撤销'  else '' end as operateType"+
			" from CoresQA.dbo.QAChkPlanChangeIndex as cpc join CoresUserPrivilege.dbo.tblESLink as esl"+
			" 	on cpc.chkPlanChangeIndexID = esl.dataId join CoresUserPrivilege.dbo.tblES as es"+
			" 	on esl.esId = es.esId"+
			" where cpc.studyNo = :studyNo and (esl.esType = 817 or  esl.esType = 830 or  esl.esType = 831 or  esl.esType = 844)"+
			" union"+
			" select esl.esType,esl.esTypeDesc,esl.dataId,cp.studyNo,convert(varchar(16),es.dateTime,120) as operateTime,es.signer as operator,"+
			" 	case esl.esType when 832 then 'QA申请临时检查人' when 833 then 'QA申请临时检查人审批通过' "+
			" 	when 841 then 'QA申请临时检查人审批未通过' else '' end as operateType"+
			" from CoresQA.dbo.QAChkPlan as cp join CoresUserPrivilege.dbo.tblESLink as esl"+
			" 	on cp.chkPlanID = esl.dataId join CoresUserPrivilege.dbo.tblES as es"+
			" 	on esl.esId = es.esId"+
			" where cp.studyNo = :studyNo and (esl.esType = 832 or  esl.esType = 833 or  esl.esType = 841)"+
			" union"+
			" select esl.esType,esl.esTypeDesc,esl.dataId,cr.chkReportCode as studyNo,convert(varchar(16),es.dateTime,120) as operateTime,es.signer as operator,"+
			" 	case esl.esType when 803 then '检查报告提交' when 818 then '检查报告审批通过'"+ 
			" 	when 840 then '检查报告审批未通过' when 820 then '检查报告（SD回复）' "+
			" 	when 821 then '检查报告（延迟整改）' when 804 then 'FM批复检查报告（SD回复）' "+
			" 	when 805 then 'FM批复检检查报告（延迟整改）' when 819 then 'QA接收检查报告（SD回复或延迟整改）' "+
			" 	else '' end as operateType"+
			" from CoresQA.dbo.QAChkIndex as ci join  CoresQA.dbo.QAChkReport as cr on ci.chkReportCode = cr.chkReportCode"+
			" 	join CoresUserPrivilege.dbo.tblESLink as esl"+
			" 	on cr.chkReportCode = esl.dataId join CoresUserPrivilege.dbo.tblES as es"+
			" 	on esl.esId = es.esId"+
			" where ci.studyNo = :studyNo and (esl.esType = 803 or  esl.esType = 818 or  esl.esType = 840 "+
			" 	or  esl.esType = 820 or  esl.esType = 821 or  esl.esType = 804 or  esl.esType = 805 "+
			" 	 or  esl.esType = 819 )"+
			" ) as t"+
			" order by  t.operateTime";
		}else if(operateType == 808){
			sql = "select esl.esType,esl.esTypeDesc,esl.dataId,sfi.studyNo,convert(varchar(16),es.dateTime,120) as operateTime,es.signer as operator,"+
			" 	case esl.esType when 808 then '专题方案提交' when 809 then '专题报告提交' else '' end as operateType"+
			" from CoresQA.dbo.TblStudyFileIndex as sfi join CoresUserPrivilege.dbo.tblESLink as esl"+
			" 	on sfi.studyFileIndexID = esl.dataId join CoresUserPrivilege.dbo.tblES as es"+
			" 	on esl.esId = es.esId"+
			" where sfi.studyNo = :studyNo and (esl.esType = 808 or  esl.esType = 809)"+
			" order by  es.dateTime";
		}else if(operateType == 809){
			sql = "select esl.esType,esl.esTypeDesc,esl.dataId,sfi.studyNo,convert(varchar(16),es.dateTime,120) as operateTime,es.signer as operator,"+
			" 	case esl.esType when 808 then '专题方案提交' when 809 then '专题报告提交' else '' end as operateType"+
			" from CoresQA.dbo.TblStudyFileIndex as sfi join CoresUserPrivilege.dbo.tblESLink as esl"+
			" 	on sfi.studyFileIndexID = esl.dataId join CoresUserPrivilege.dbo.tblES as es"+
			" 	on esl.esId = es.esId"+
			" where sfi.studyNo = :studyNo and esl.esType = 809"+
			" order by  es.dateTime";
		}else if(operateType == 842){
			sql = "select esl.esType,esl.esTypeDesc,esl.dataId,sfi.studyNo,convert(varchar(16),es.dateTime,120) as operateTime,es.signer as operator,"+
			" 	case esl.esType when 842 then 'QA接收专题报告' when 809 then '专题报告提交' else '' end as operateType"+
			" from CoresQA.dbo.TblStudyFileIndex as sfi join CoresUserPrivilege.dbo.tblESLink as esl"+
			" 	on sfi.studyFileIndexID = esl.dataId join CoresUserPrivilege.dbo.tblES as es"+
			" 	on esl.esId = es.esId"+
			" where sfi.studyNo = :studyNo and esl.esType = 842"+
			" order by  es.dateTime";
		}else if(operateType == 822){
			sql = "select esl.esType,esl.esTypeDesc,esl.dataId,sfi.studyNo,convert(varchar(16),es.dateTime,120) as operateTime,es.signer as operator,"+
			" 	case esl.esType when 822 then 'QA接收专题方案' when 809 then '专题报告提交' else '' end as operateType"+
			" from CoresQA.dbo.TblStudyFileIndex as sfi join CoresUserPrivilege.dbo.tblESLink as esl"+
			" 	on sfi.studyFileIndexID = esl.dataId join CoresUserPrivilege.dbo.tblES as es"+
			" 	on esl.esId = es.esId"+
			" where sfi.studyNo = :studyNo and esl.esType = 822"+
			" order by  es.dateTime";
		}else if(operateType == 834){
			sql = "select esl.esType,esl.esTypeDesc,esl.dataId,sfi.studyNo,convert(varchar(16),es.dateTime,120) as operateTime,es.signer as operator,"+
				" 		 '方案阅读完成' as operateType"+
				" 	from CoresQA.dbo.TblStudyFileIndex as sfi join CoresQA.dbo.[TblStudyPlanReadRecord] as sprr "+
				" 			on sfi.studyFileIndexID = sprr.studyNo  join CoresUserPrivilege.dbo.tblESLink as esl"+
				" 		on sprr.ID = esl.dataId join CoresUserPrivilege.dbo.tblES as es"+
				" 		on esl.esId = es.esId"+
				" 	where sfi.studyNo = :studyNo and esl.esType = 834"+
				" 	order by  es.dateTime";
		}else if(operateType == 801){
			sql = "select esl.esType,esl.esTypeDesc,esl.dataId,esl.dataId as studyNo,convert(varchar(16),es.dateTime,120) as operateTime,es.signer as operator,"+
				" case esl.esType when 801 then '检查计划提交' when 810 then '检查计划审批通过' when 811 then '检查计划审批未通过' else '' end as operateType"+
				" from CoresUserPrivilege.dbo.tblESLink as esl"+
				" 	join CoresUserPrivilege.dbo.tblES as es"+
				" 	on esl.esId = es.esId"+
				" where esl.dataId = :studyNo and esl.tableName='QAStudyChkIndex' and (esl.esType = 801)"+
				" order by  es.dateTime";
		}else if(operateType == 810){//810,811
			sql = "select esl.esType,esl.esTypeDesc,esl.dataId,esl.dataId as studyNo,convert(varchar(16),es.dateTime,120) as operateTime,es.signer as operator,"+
			" case esl.esType when 801 then '检查计划提交' when 810 then '检查计划审批通过' when 811 then '检查计划审批未通过' else '' end as operateType"+
			" from CoresUserPrivilege.dbo.tblESLink as esl"+
			" 	join CoresUserPrivilege.dbo.tblES as es"+
			" 	on esl.esId = es.esId"+
			" where esl.dataId = :studyNo and esl.tableName='QAStudyChkIndex' and ( esl.esType = 810 or esl.esType = 811)"+
			" order by  es.dateTime";
		}else if(operateType == 817){
			sql = "select esl.esType,esl.esTypeDesc,esl.dataId,cpc.studyNo,convert(varchar(16),es.dateTime,120) as operateTime,es.signer as operator,"+
				" 	case esl.esType when 817 then '检查计划变更申请' when 830 then '检查计划变更申请审批通过' "+
				" 	when 831 then '检查计划变更申请审批未通过' else '' end as operateType"+
				" from CoresQA.dbo.QAChkPlanChangeIndex as cpc join CoresUserPrivilege.dbo.tblESLink as esl"+
				" 	on cpc.chkPlanChangeIndexID = esl.dataId join CoresUserPrivilege.dbo.tblES as es"+
				" 	on esl.esId = es.esId"+
				" where cpc.studyNo = :studyNo and (esl.esType = 817)"+
				" order by  es.dateTime";
		}else if(operateType == 844){
			sql = "select esl.esType,esl.esTypeDesc,esl.dataId,cpc.studyNo,convert(varchar(16),es.dateTime,120) as operateTime,es.signer as operator,"+
			" 	case esl.esType when 844 then '检查计划变更申请撤销' when 830 then '检查计划变更申请审批通过' "+
			" 	when 831 then '检查计划变更申请审批未通过' else '' end as operateType"+
			" from CoresQA.dbo.QAChkPlanChangeIndex as cpc join CoresUserPrivilege.dbo.tblESLink as esl"+
			" 	on cpc.chkPlanChangeIndexID = esl.dataId join CoresUserPrivilege.dbo.tblES as es"+
			" 	on esl.esId = es.esId"+
			" where cpc.studyNo = :studyNo and (esl.esType = 844)"+
			" order by  es.dateTime";
	}else if(operateType == 830){//830,831
			sql = "select esl.esType,esl.esTypeDesc,esl.dataId,cpc.studyNo,convert(varchar(16),es.dateTime,120) as operateTime,es.signer as operator,"+
			" 	case esl.esType when 817 then '检查计划变更申请' when 830 then '检查计划变更申请审批通过' "+
			" 	when 831 then '检查计划变更申请审批未通过' else '' end as operateType"+
			" from CoresQA.dbo.QAChkPlanChangeIndex as cpc join CoresUserPrivilege.dbo.tblESLink as esl"+
			" 	on cpc.chkPlanChangeIndexID = esl.dataId join CoresUserPrivilege.dbo.tblES as es"+
			" 	on esl.esId = es.esId"+
			" where cpc.studyNo = :studyNo and (esl.esType = 830 or  esl.esType = 831)"+
			" order by  es.dateTime";
		}else if(operateType == 832){
			sql = "select esl.esType,esl.esTypeDesc,esl.dataId,cp.studyNo,convert(varchar(16),es.dateTime,120) as operateTime,es.signer as operator,"+
			" 	case esl.esType when 832 then 'QA申请临时检查人' when 833 then 'QA申请临时检查人审批通过' "+
			" 	when 841 then 'QA申请临时检查人审批未通过' else '' end as operateType"+
			" from CoresQA.dbo.QAChkPlan as cp join CoresUserPrivilege.dbo.tblESLink as esl"+
			" 	on cp.chkPlanID = esl.dataId join CoresUserPrivilege.dbo.tblES as es"+
			" 	on esl.esId = es.esId"+
			" where cp.studyNo = :studyNo and (esl.esType = 832)"+
			" order by  es.dateTime";
		}else if(operateType == 833){//833,841
			sql = "select esl.esType,esl.esTypeDesc,esl.dataId,cp.studyNo,convert(varchar(16),es.dateTime,120) as operateTime,es.signer as operator,"+
			" 	case esl.esType when 832 then 'QA申请临时检查人' when 833 then 'QA申请临时检查人审批通过' "+
			" 	when 841 then 'QA申请临时检查人审批未通过' else '' end as operateType"+
			" from CoresQA.dbo.QAChkPlan as cp join CoresUserPrivilege.dbo.tblESLink as esl"+
			" 	on cp.chkPlanID = esl.dataId join CoresUserPrivilege.dbo.tblES as es"+
			" 	on esl.esId = es.esId"+
			" where cp.studyNo = :studyNo and (esl.esType = 833 or  esl.esType = 841)"+
			" order by  es.dateTime";
		}else if(operateType == 803 || operateType ==818 || operateType ==840 || operateType ==820 || operateType ==821 
				|| operateType ==804 || operateType ==805 || operateType ==819){
			if(operateType != 818){
				sql = "select esl.esType,esl.esTypeDesc,esl.dataId,cr.chkReportCode as studyNo,convert(varchar(16),es.dateTime,120) as operateTime,es.signer as operator,"+
				" 	case esl.esType when 803 then '检查报告提交' when 818 then '检查报告审批通过' "+
				" 	when 840 then '检查报告审批未通过' when 820 then '检查报告（SD回复）' "+
				" 	when 821 then '检查报告（延迟整改）' when 804 then 'FM批复检查报告（SD回复）' "+
				" 	when 805 then 'FM批复检检查报告（延迟整改）' when 819 then 'QA接收检查报告（SD回复或延迟整改）' "+
				" 	else '' end as operateType"+
				" from CoresQA.dbo.QAChkIndex as ci join  CoresQA.dbo.QAChkReport as cr on ci.chkReportCode = cr.chkReportCode"+
				" 	join CoresUserPrivilege.dbo.tblESLink as esl"+
				" 	on cr.chkReportCode = esl.dataId join CoresUserPrivilege.dbo.tblES as es"+
				" 	on esl.esId = es.esId"+
				" where ci.studyNo = :studyNo and (esl.esType = "+operateType+" )"+
				" order by  es.dateTime";
			}else{
				sql = "select esl.esType,esl.esTypeDesc,esl.dataId,cr.chkReportCode as studyNo,convert(varchar(16),es.dateTime,120) as operateTime,es.signer as operator,"+
				" 	case esl.esType when 803 then '检查报告提交' when 818 then '检查报告审批通过' "+
				" 	when 840 then '检查报告审批未通过' when 820 then '检查报告（SD回复）' "+
				" 	when 821 then '检查报告（延迟整改）' when 804 then 'FM批复检查报告（SD回复）' "+
				" 	when 805 then 'FM批复检检查报告（延迟整改）' when 819 then 'QA接收检查报告（SD回复或延迟整改）' "+
				" 	else '' end as operateType"+
				" from CoresQA.dbo.QAChkIndex as ci join  CoresQA.dbo.QAChkReport as cr on ci.chkReportCode = cr.chkReportCode"+
				" 	join CoresUserPrivilege.dbo.tblESLink as esl"+
				" 	on cr.chkReportCode = esl.dataId join CoresUserPrivilege.dbo.tblES as es"+
				" 	on esl.esId = es.esId"+
				" where ci.studyNo = :studyNo and (esl.esType = 818 or  esl.esType = 840 "+
				" 	)"+
				" order by  es.dateTime";
				
			}
		}else if(operateType == 835){//833,841
			sql = "";
		}
		mapList = sessionFactory.getCurrentSession().createSQLQuery(sql)
													.setParameter("studyNo", studyNo)
													.setResultTransformer(new MapResultTransformer())
													.list();
		return mapList;
	}

}
