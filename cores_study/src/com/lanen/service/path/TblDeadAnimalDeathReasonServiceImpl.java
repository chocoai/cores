package com.lanen.service.path;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.jsonAndModel.Json;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.path.TblAnatomyAnimal;
import com.lanen.model.path.TblDeadAnimalDeathReason;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;

@Service
public class TblDeadAnimalDeathReasonServiceImpl extends BaseDaoImpl<TblDeadAnimalDeathReason> implements TblDeadAnimalDeathReasonService {

	@Resource
	private TblAnatomyAnimalService tblAnatomyAnimalService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblESService tblESService;
	public boolean isHasRecord(String studyNo, String animalCode) {
		String sql = "select count(id)"+
					" from CoresStudy.dbo.tblDeadAnimalDeathReason "+
					" where studyNo = :studyNo and animalCode = :animalCode ";
		Integer count = (Integer) getSession().createSQLQuery(sql)
									.setParameter("studyNo", studyNo)
									.setParameter("animalCode", animalCode)
									.uniqueResult();
		return count > 0;
	}

	public void addOne(TblDeadAnimalDeathReason tblDeadAnimalDeathReason,
			String operator) {
		
		String id = getKey();
		tblDeadAnimalDeathReason.setId(id);
		
		String anatomyAnimalId = "";
		TblAnatomyAnimal anatomyAnimal = tblAnatomyAnimalService.getByStudyNoAnimalCode(tblDeadAnimalDeathReason.getStudyNo(),
				tblDeadAnimalDeathReason.getAnimalCode());
		if(null != anatomyAnimal){
			anatomyAnimalId = anatomyAnimal.getId();
		}
		tblDeadAnimalDeathReason.setAnatomyAnimalId(anatomyAnimalId);
		
		String esId = writeES(id,"TblDeadAnimalDeathReason",259,"死亡动物致死原因_登记",operator);
		tblDeadAnimalDeathReason.setSignId(esId);
		
		getSession().save(tblDeadAnimalDeathReason);
		
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

	@SuppressWarnings("unchecked")
	public List<TblDeadAnimalDeathReason> getListByStudyNoAnimalCode(
			String studyNo, String animalCode) {
		String hql = "from TblDeadAnimalDeathReason where studyNo = ? and animalCode = ? ";
		List<TblDeadAnimalDeathReason> list = getSession().createQuery(hql)
														.setParameter(0, studyNo)
														.setParameter(1, animalCode)
														.list();
		return list;
	}

	public Json delOne(String id, String reason, String realName) {
		Json json = new Json();
		TblDeadAnimalDeathReason obj = getById(id);
		if(null != obj && obj.getDelFlag() == 0){
			obj.setDelFlag(1);
			obj.setDelRsn(reason);
			obj.setDelTime(new Date());
			String esId = writeES(id,"TblDeadAnimalDeathReason",260,"死亡动物致死原因_删除",realName);
			obj.setDelSignId(esId);
			getSession().update(obj);
			json.setSuccess(true);
		}else{
			json.setMsg("不可以重复删除！");
		}
		return json;
	}

	public String getDeathReason(String studyNo, String animalCode) {
		String sql = "select top(1) deathReason"+
              		" from CoresStudy.dbo.tblDeadAnimalDeathReason "+
              		" where delFlag = 0 and studyNo = ? and animalCode = ? "+
              		" order by id desc";
		String reason = (String) getSession().createSQLQuery(sql)
									.setParameter(0, studyNo)
									.setParameter(1, animalCode)
									.uniqueResult();
		return reason;
	}
}
