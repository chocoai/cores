package com.lanen.service.path;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.jsonAndModel.Json;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.path.TblAnimalTargetOrgan;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;

@Service
public class TblAnimalTargetOrganServiceImpl extends BaseDaoImpl<TblAnimalTargetOrgan> implements TblAnimalTargetOrganService{

	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblESService tblESService;
	public String getTestPhaseByTaskId(String taskId) {
		String sql = "select req.testPhase"+
					" from CoresStudy.dbo.tblAnatomyTask as task left join "+
					" 	CoresStudy.dbo.tblAnatomyReq as req on task.studyNo = req.studyNo "+
					" 	and task.anatomyReqNo = req.reqNo "+
					" where task.taskId = :taskId ";
		String taskPhase = (String) getSession().createSQLQuery(sql)
												.setParameter("taskId", taskId)
												.uniqueResult();
		return taskPhase;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getfsMapListByStudyNo(String studyNo) {
		String sql = "select distinct tsv.visceraCode,tsv.visceraName,tsv.visceraType,dv.sn"+
					" from CoresStudy.dbo.tblHistopathCheck as hcheck left join CoresStudy.dbo.tblTissueSliceViscera as tsv"+
					" 		on hcheck.tissueSliceVisceraRecordId = tsv.id and tsv.visceraCode is not null "+
					" 	left join CoresSystemSet.dbo.dictViscera as dv on tsv.visceraCode = dv.visceraCode " +
					" left join " +
					" (" +
						"select r1.visceraCode,r1.visceraName,r1.visceraType,r1.num"+
						" from"+
						" 	(select targetOrgan.visceraCode,targetOrgan.visceraName,targetOrgan.visceraType ,"+
						" 		 count(targetOrgan.visceraCode) as num"+
						" 	from CoresStudy.dbo.tblAnimalTargetOrgan as targetOrgan "+
						" 	"+
						" 	where targetOrgan.studyNo = :studyNo and targetOrgan.targetOrganFlag = 1"+
						" 		and targetOrgan.delFlag = 0"+
						" 	group by targetOrgan.visceraCode,targetOrgan.visceraName,targetOrgan.visceraType) as r1"+
						" 	 left join "+
						" 	(select targetOrgan.visceraCode,targetOrgan.visceraName,targetOrgan.visceraType ,"+
						" 		 count(targetOrgan.visceraCode) as num"+
						" 	from CoresStudy.dbo.tblAnimalTargetOrgan as targetOrgan "+
						" 	"+
						" 	where targetOrgan.studyNo = :studyNo and targetOrgan.targetOrganFlag = 2"+
						" 		and targetOrgan.delFlag = 0"+
						" 	group by targetOrgan.visceraCode,targetOrgan.visceraName,targetOrgan.visceraType) as r2"+
						" 	on r1.visceraCode = r2.visceraCode " +
						"	where r2.visceraCode is null or r1.num > r2.num" +
					" ) as result on tsv.visceraCode = result.visceraCode "+
					" where hcheck.studyNo = :studyNo and result.visceraCode is null "+
					" order by dv.sn";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
//														.setParameter("taskId", taskId)
														.setParameter("studyNo", studyNo)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListByStudyNo(String studyNo) {
		String sql = "select r1.visceraCode,r1.visceraName,r1.visceraType,r1.num"+
					" from"+
					" 	(select targetOrgan.visceraCode,targetOrgan.visceraName,targetOrgan.visceraType ,"+
					" 		 count(targetOrgan.visceraCode) as num"+
					" 	from CoresStudy.dbo.tblAnimalTargetOrgan as targetOrgan "+
					" 	"+
					" 	where targetOrgan.studyNo = :studyNo and targetOrgan.targetOrganFlag = 1"+
					" 		and targetOrgan.delFlag = 0"+
					" 	group by targetOrgan.visceraCode,targetOrgan.visceraName,targetOrgan.visceraType) as r1"+
					" 	left join"+
					" 	(select targetOrgan.visceraCode,targetOrgan.visceraName,targetOrgan.visceraType ,"+
					" 		 count(targetOrgan.visceraCode) as num"+
					" 	from CoresStudy.dbo.tblAnimalTargetOrgan as targetOrgan "+
					" 	"+
					" 	where targetOrgan.studyNo = :studyNo and targetOrgan.targetOrganFlag = 2"+
					" 		and targetOrgan.delFlag = 0"+
					" 	group by targetOrgan.visceraCode,targetOrgan.visceraName,targetOrgan.visceraType) as r2"+
					" 	on r1.visceraCode = r2.visceraCode " +
					" where r2.visceraCode is null or r1.num > r2.num";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
													.setParameter("studyNo", studyNo)
													.setResultTransformer(new MapResultTransformer())
													.list();
		return mapList;
	}

	public void addOne(TblAnimalTargetOrgan tblAnimalTargetOrgan,
			String operator) {
		TblAnimalTargetOrgan tblAnimalTargetOrgan2 = getLastTargetOrgan(tblAnimalTargetOrgan.getStudyNo(),tblAnimalTargetOrgan.getVisceraName());
		if(null != tblAnimalTargetOrgan2){
			if(tblAnimalTargetOrgan.getTargetOrganFlag() == tblAnimalTargetOrgan2.getTargetOrganFlag()){
//				showErrorMessage("逻辑错误！");
				return;
			}
		}else{
			if(tblAnimalTargetOrgan.getTargetOrganFlag() == 2){
//				showErrorMessage("逻辑错误！");
				return;
			}
		}
		String id = getKey();
		Date regDate = new Date();
		String signId = writeES(id,"TblAnimalTargetOrgan",261,"靶器官登记",operator);
		
		tblAnimalTargetOrgan.setId(id);
		tblAnimalTargetOrgan.setSignId(signId);
		tblAnimalTargetOrgan.setRegDate(regDate);
		
		getSession().save(tblAnimalTargetOrgan);
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

	public int getGenderByStudyNoVisceraName(String studyNo, String newValue) {
		String sql = "select top(1) ato.gender"+
					" from CoresStudy.dbo.tblAnimalTargetOrgan ato"+
					" where ato.studyNo = :studyNo and ato.visceraName = :visceraName " +
					"  and ato.delFlag = 0"+
					" order by ato.regDate desc";
		Integer count = (Integer) getSession().createSQLQuery(sql)
									.setParameter("studyNo", studyNo)
									.setParameter("visceraName", newValue)
									.uniqueResult();
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getListByStudyNo(String studyNo) {
		String sql = "select ato.id,ato.visceraName,ato.occurPhase,ato.remark,ato.regDate,ato.gender,"+
					" 	ato.targetOrganFlag,ato.delFlag,ato.delRsn,ato.delTime ,es.signer  as register "+
					" from CoresStudy.dbo.tblAnimalTargetOrgan as ato left join CoresUserPrivilege.dbo.tblES	as es"+
					" 	on ato.signId = es.esId"+
					" where ato.studyNo = ? ";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
													.setParameter(0, studyNo)
													.setResultTransformer(new MapResultTransformer())
													.list();
		
		return mapList;
	}

	public Json delOne(String id, String reason, String realName) {
//		String signId = writeES(id,"TblAnimalTargetOrgan",262,"靶器官登记信息_删除",operator);
		Json json = new Json();
		TblAnimalTargetOrgan obj = getById(id);
		if(null != obj){
			boolean isexist = isHasNewRecord(id);
			if(!isexist){
				int delFlag = 1;
				String delSignId = writeES(id,"TblAnimalTargetOrgan",262,"靶器官登记信息_删除",realName);
				obj.setDelFlag(delFlag);
				obj.setDelRsn(reason);
				obj.setDelSignId(delSignId);
				obj.setDelTime(new Date());
				getSession().update(obj);
				json.setSuccess(true);
			}else{
				json.setMsg("同一靶器官请按时间由后到前顺序删除！");
			}
		}else{
			json.setMsg("交互错误！");
		}
		return json;
	}

	public TblAnimalTargetOrgan getLastTargetOrgan(String studyNo,
			String visceraName) {
		String hql = "from TblAnimalTargetOrgan where studyNo = ? and visceraName = ? " +
					"	and delFlag = 0" +
					" order by regDate desc";
		TblAnimalTargetOrgan obj = (TblAnimalTargetOrgan) getSession().createQuery(hql)
												.setParameter(0, studyNo)
												.setParameter(1, visceraName)
												.setFirstResult(0)
												.setMaxResults(1)
												.uniqueResult();
		return obj;
	}

	public boolean isHasNewRecord(String id) {
		String sql = "select count(*)"+
					" from CoresStudy.dbo.tblAnimalTargetOrgan as ato join "+
					" 	CoresStudy.dbo.tblAnimalTargetOrgan as ato2"+
					" 	on ato.visceraCode = ato2.visceraCode and ato.studyNo = ato2.studyNo"+
					" 	and ato.regDate < ato2.regDate and ato2.delFlag = 0"+
					" where ato.id = ? ";
		Integer count = (Integer) getSession().createSQLQuery(sql).setParameter(0, id).uniqueResult();
		return count > 0;
	}

	
}
