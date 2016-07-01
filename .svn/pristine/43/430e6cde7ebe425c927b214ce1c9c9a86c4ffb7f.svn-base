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
import com.lanen.model.path.TblAnatomyCheck;
import com.lanen.model.path.TblAnatomyCheckEdit;
import com.lanen.model.path.TblVisceraFixedEdit;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;

/**
 * @author Administrator
 *
 */
@Service
public class TblAnatomyCheckEditServiceImpl extends BaseDaoImpl<TblAnatomyCheckEdit> implements TblAnatomyCheckEditService{
	
	@Resource
	private TblAnatomyCheckService tblAnatomyCheckService;
	@Resource
	private TblESLinkService tblESLinkService;
	
	@Resource
	private TblESService tblESService;
	@Resource
	private UserService userService;

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListByTaskId(String taskId) {
		String sql = "select result.oldId,result.studyNo,result.animalCode,result.visceraName,result.subVisceraName,result.operateTime,"+
				" 	result.anatomyOperator,result.anatomyFinding,result.autolyzaFlag,result.editType,result.editTime,                                "+
				" 	result.editRsn,result.delFlag,result.delTime,result.delTime,result.delRsn,result.id                                              "+
				" from (                                                                                                                               "+
				" 		select distinct tblc.id as oldId,tblc.studyNo,tblc.animalCode , tblc.visceraName , tblc.subVisceraName ,                     "+
				" 			 convert(varchar(16),tblc.operateTime,120) as operateTime , u.realName as anatomyOperator,                               "+
				" 			case when tblc.autolyzaFlag = 1 then '自溶'                                                                                "+
				" 				 when tblc.autolyzaFlag = 2 and isnull(qr.missingRsn,'') !='' then (tblc.anatomyFingding+':'+qr.missingRsn)          "+
				" 				 when tblc.autolyzaFlag = 2 and isnull(qr.missingRsn,'') ='' then tblc.anatomyFingding                               "+
				" 				 else                                                                                                                "+
				" 					( case when isnull(tblc.bodySurfacePos,'') = '' then '' else tblc.bodySurfacePos+' ' end                         "+
				" 						+case when isnull(tblc.anatomyPos,'') = '' then '' else tblc.anatomyPos+' ' end                              "+
				" 				 			+case when isnull(tblc.pos,'') = '' then '' else tblc.pos+' ' end                                        "+
				" 						+case when isnull(tblc.number,'') = '' then '' else tblc.number+' ' end                                      "+
				" 						+case when isnull(tblc.range,'') = '' then '' else tblc.range+' ' end                                        "+
				" 						+case when isnull(tblc.size,'') = '' then '' else tblc.size+' ' end                                          "+
				" 						+case when isnull(tblc.color,'') = '' then '' else tblc.color+' ' end                                        "+
				" 						+case when isnull(tblc.texture,'') = '' then '' else tblc.texture+' ' end                                    "+
				" 				 			+case when isnull(tblc.shape,'') = '' then '' else tblc.shape+' ' end                                    "+
				" 						+case when isnull(tblc.anatomyFingding,'') = '' then '' else tblc.anatomyFingding+' ' end                    "+
				" 						+case when isnull(tblc.lesionDegree,'') = '' then '' else tblc.lesionDegree+' ' end                          "+
				" 					) end  as anatomyFinding		,tblc.autolyzaFlag,                                                              "+
				" 					0 as editType,'' as editTime,'' as  editRsn,0 as delFlag,'' as delTime,'' as delRsn,'' as id                     "+
				" 		from CoresStudy.dbo.tblPathSession as se left join  CoresStudy.dbo.TblAnatomyCheck  as tblc on se.sessionId = tblc.sessionId "+
				" 						                                                                                                     "+
				" 			left join CoresStudy.dbo.tblVisceraQueRu as qr on tblc.autolyzaFlag = 2 and tblc.id = qr.anatomyCheckId                  "+
				" 			left join CoresStudy.dbo.tblAnatomyAnimal as taskanimal on tblc.sessionId = taskanimal.anatomySessionId                  "+
				" 						 and tblc.animalCode = taskanimal.animalCode and tblc.studyNo = taskanimal.studyNo                           "+
				" 			left join CoresUserPrivilege.dbo.tbluser as u on taskanimal.anatomyOperator = u.userName                                 "+
				" 			left join CoresStudy.dbo.tblAnatomyCheckEdit as edit on tblc.id = edit.oldId and edit.delFlag = 0                        "+
				" 		where se.taskId =:taskId and tblc.sessionId is not null and edit.id is null                                            "+
				"                                                                                                                                      "+
				" 		  union                                                                                                                      "+
				"                                                                                                                                      "+
				" 		 select edit.oldId ,edit.studyNo,edit.animalCode , edit.visceraName , edit.subVisceraName ,                                  "+
				" 			 convert(varchar(16),edit.operateTime,120) as operateTime , u.realName as anatomyOperator,                               "+
				" 			case when edit.autolyzaFlag = 1 then '自溶'                                                                                "+
				" 				 else                                                                                                                "+
				" 					( case when isnull(edit.bodySurfacePos,'') = '' then '' else edit.bodySurfacePos+' ' end                         "+
				" 						+case when isnull(edit.anatomyPos,'') = '' then '' else edit.anatomyPos+' ' end                              "+
				" 				 			+case when isnull(edit.pos,'') = '' then '' else edit.pos+' ' end                                        "+
				" 						+case when isnull(edit.number,'') = '' then '' else edit.number+' ' end                                      "+
				" 						+case when isnull(edit.range,'') = '' then '' else edit.range+' ' end                                        "+
				" 						+case when isnull(edit.size,'') = '' then '' else edit.size+' ' end                                          "+
				" 						+case when isnull(edit.color,'') = '' then '' else edit.color+' ' end                                        "+
				" 						+case when isnull(edit.texture,'') = '' then '' else edit.texture+' ' end                                    "+
				" 				 			+case when isnull(edit.shape,'') = '' then '' else edit.shape+' ' end                                    "+
				" 						+case when isnull(edit.anatomyFingding,'') = '' then '' else edit.anatomyFingding+' ' end                    "+
				" 						+case when isnull(edit.lesionDegree,'') = '' then '' else edit.lesionDegree+' ' end                          "+
				" 					) end  as anatomyFinding		,edit.autolyzaFlag,                                                              "+
				" 					edit.editType,convert(varchar(16),edit.editTime,120) as editTime,edit.editRsn,                                   "+
				" 					edit.delFlag,convert(varchar(16),edit.delTime,120) as delTime,edit.delRsn,edit.id                                "+
				" 		from CoresStudy.dbo.tblAnatomyCheckEdit	as edit 	                                                             "+
				" 			left join CoresStudy.dbo.tblAnatomyAnimal as taskanimal on edit.animalCode = taskanimal.animalCode                       "+
				" 				and edit.studyNo = taskanimal.studyNo                                                                                "+
				" 			left join CoresUserPrivilege.dbo.tbluser as u on taskanimal.anatomyOperator = u.userName                                 "+
				" 		where edit.taskId = :taskId and edit.delFlag = 0                                                                       "+
				" 			and (edit.editType = 1 or edit.editType = 2)                                                                             "+
				" 	) as result                                                                                                                      "+
				" order by result.animalCode";                                                                                                         
		List<Map<String,Object>> mapList =  getSession().createSQLQuery(sql)
			.setParameter("taskId", taskId)
			.setResultTransformer(new MapResultTransformer())
			.list();
	return mapList;
	}

	public Json saveOne(TblAnatomyCheckEdit tblAnatomyCheckEdit) {
		Json json = new Json();
		String id = getKey();
		tblAnatomyCheckEdit.setId(id);
		tblAnatomyCheckEdit.setOperateTime(new Date());
		tblAnatomyCheckEdit.setEditTime(new Date());
		
		String realName = userService.getRealNameByUserName(tblAnatomyCheckEdit.getOperator());
		writeES(id,"解剖所见数据修改_新增",realName);
		getSession().save(tblAnatomyCheckEdit);
		
		json.setMsg(id);
		json.setSuccess(true);
		return json;
	}
	
	private void writeES(String dataId, String itemName,String signer) {
		if(null != dataId && null != itemName && null != signer){
			//签名链接
			TblESLink esLink = new TblESLink();
			//电子签名
			TblES es = new TblES();
			String esId = tblESService.getKey("TblES");
			es.setDateTime(new Date());
			es.setEsId(esId);
			es.setSigner(signer);
			esLink.setTableName("TblAnatomyCheckEdit");
			esLink.setDataId(dataId);
			esLink.setTblES(es);
			esLink.setRecordTime(new Date());
			esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
			if(itemName.equals("解剖所见数据修改_新增")){
				es.setEsType(256);
				esLink.setEsType(256);
				es.setEsTypeDesc(itemName);
				esLink.setEsTypeDesc(itemName);
			}else if(itemName.equals("解剖所见数据修改_编辑")){
				es.setEsType(257);
				esLink.setEsType(257);
				es.setEsTypeDesc(itemName);
				esLink.setEsTypeDesc(itemName);
			}else if(itemName.equals("解剖所见数据修改_删除")){
				es.setEsType(258);
				esLink.setEsType(258);
				es.setEsTypeDesc(itemName);
				esLink.setEsTypeDesc(itemName);
			}
			tblESService.save(es);
			tblESLinkService.save(esLink);
		}
	}

	public Json saveOne(TblAnatomyCheckEdit tblAnatomyCheckEdit,
			String currentUserName) {
		Json json = new Json();
		String id = getKey();
		tblAnatomyCheckEdit.setId(id);
		tblAnatomyCheckEdit.setEditTime(new Date());
		writeES(id,"解剖所见数据修改_编辑",currentUserName);
		
		getSession().save(tblAnatomyCheckEdit);
		
		json.setMsg(id);
		json.setSuccess(true);
		return json;
	}

	public void deleteOne(String id, String checkId, String currentUserName,
			String reason,String taskId) {
		TblAnatomyCheckEdit tblAnatomyCheckEdit = null;
		if(null == id || "".equals(id.trim())){
			//解剖所见删除
			tblAnatomyCheckEdit = new TblAnatomyCheckEdit();
			id = getKey();
			
			tblAnatomyCheckEdit.setId(id);
			//taskId,animalCode,studyNo
			String studyNo = "";
			String animalCode = "";
			TblAnatomyCheck tblAnatomyCheck = tblAnatomyCheckService.getById(checkId);
			if(null != tblAnatomyCheck ){
				studyNo = tblAnatomyCheck.getStudyNo();
				animalCode = tblAnatomyCheck.getAnimalCode();
			}
			tblAnatomyCheckEdit.setTaskId(taskId);
			tblAnatomyCheckEdit.setStudyNo(studyNo);
			tblAnatomyCheckEdit.setAnimalCode(animalCode);
			
			tblAnatomyCheckEdit.setEditType(3);
			tblAnatomyCheckEdit.setOldId(checkId);
			tblAnatomyCheckEdit.setEditTime(new Date());
			tblAnatomyCheckEdit.setEditRsn(reason);
		}else{
			tblAnatomyCheckEdit = getById(id);
			
			tblAnatomyCheckEdit.setDelFlag(1);
			tblAnatomyCheckEdit.setDelRsn(reason);
			tblAnatomyCheckEdit.setDelTime(new Date());
		}
		writeES(id,"解剖所见数据修改_删除",currentUserName);
		
		getSession().saveOrUpdate(tblAnatomyCheckEdit);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnatomyCheckEditPrint(String taskId) {
		String sql = "select case e.edittype when 1 then '新增' when 2 then '编辑' when 3 then '删除' end  as editType, " +
" 	e.animalCode,                                                                                                 " +
" 	case e.edittype when 3 then (case when isnull(d.subVisceraName,'') = '' then d.visceraName else d.subVisceraName end)  " +
" 		else (case when isnull(e.subVisceraName,'') = '' then e.visceraName else e.subVisceraName end)  end as visceraName," +
" 		case when d.autolyzaFlag = 1 then '自溶'                                                                    " +
" 			when d.autolyzaFlag = 2 and isnull(qr.missingRsn,'') !='' then (d.anatomyFingding+':'+qr.missingRsn)  " +
" 			when d.autolyzaFlag = 2 and isnull(qr.missingRsn,'') ='' then d.anatomyFingding                       " +
" 			else                                                                                                  " +
" 			( case when isnull(d.bodySurfacePos,'') = '' then '' else d.bodySurfacePos+' ' end                    " +
" 				+case when isnull(d.anatomyPos,'') = '' then '' else d.anatomyPos+' ' end                         " +
" 				 	+case when isnull(d.pos,'') = '' then '' else d.pos+' ' end                                   " +
" 				+case when isnull(d.number,'') = '' then '' else d.number+' ' end                                 " +
" 				+case when isnull(d.range,'') = '' then '' else d.range+' ' end                                   " +
" 				+case when isnull(d.size,'') = '' then '' else d.size+' ' end                                     " +
" 				+case when isnull(d.color,'') = '' then '' else d.color+' ' end                                   " +
" 				+case when isnull(d.texture,'') = '' then '' else d.texture+' ' end                               " +
" 				 	+case when isnull(d.shape,'') = '' then '' else d.shape+' ' end                               " +
" 				+case when isnull(d.anatomyFingding,'') = '' then '' else d.anatomyFingding+' ' end               " +
" 				+case when isnull(d.lesionDegree,'') = '' then '' else d.lesionDegree+' ' end                     " +
" 			) end  as anatomyCheckResult,                                                                         " +
" 			case when e.autolyzaFlag = 1 then '自溶'                                                                " +
" 			when e.autolyzaFlag = 2  then e.anatomyFingding                                                       " +
" 			else                                                                                                  " +
" 			( case when isnull(e.bodySurfacePos,'') = '' then '' else e.bodySurfacePos+' ' end                    " +
" 				+case when isnull(e.anatomyPos,'') = '' then '' else e.anatomyPos+' ' end                         " +
" 				 	+case when isnull(e.pos,'') = '' then '' else e.pos+' ' end                                   " +
" 				+case when isnull(e.number,'') = '' then '' else e.number+' ' end                                 " +
" 				+case when isnull(e.range,'') = '' then '' else e.range+' ' end                                   " +
" 				+case when isnull(e.size,'') = '' then '' else e.size+' ' end                                     " +
" 				+case when isnull(e.color,'') = '' then '' else e.color+' ' end                                   " +
" 				+case when isnull(e.texture,'') = '' then '' else e.texture+' ' end                               " +
" 				 	+case when isnull(e.shape,'') = '' then '' else e.shape+' ' end                               " +
" 				+case when isnull(e.anatomyFingding,'') = '' then '' else e.anatomyFingding+' ' end               " +
" 				+case when isnull(e.lesionDegree,'') = '' then '' else e.lesionDegree+' ' end                     " +
" 			) end  as anatomyCheckEditResult                                                                      " +
" from CoresStudy.dbo.tblAnatomyCheckEdit as e left join CoresStudy.dbo.TblAnatomyCheck as d                        " +
" 	on e.oldId = d.id left join CoresStudy.dbo.tblVisceraQueRu as qr                                              " +
" 	on d.autolyzaFlag = 2 and d.id = qr.anatomyCheckId   			                                              " +
" where e.taskId = :taskId and e.delFlag = 0 " +
" order by e.animalCode,e.editType";
		
		List<Map<String, Object>> list=getSession().createSQLQuery(sql)
		        .setParameter("taskId", taskId)
		        .setResultTransformer(new MapResultTransformer())
		        .list();
		return list;
	}

	public Json saveOne_1(TblAnatomyCheckEdit tblAnatomyCheckEdit) {
		
		Date date = new Date();
		
		Json json = new Json();
		String id = getKey();
		tblAnatomyCheckEdit.setId(id);
		tblAnatomyCheckEdit.setOperateTime(date);
		tblAnatomyCheckEdit.setEditTime(date);
		
		String realName = userService.getRealNameByUserName(tblAnatomyCheckEdit.getOperator());
		writeES(id,"解剖所见数据修改_新增",realName);
		getSession().save(tblAnatomyCheckEdit);
		
		
		//固定历史记录
		TblVisceraFixedEdit tblVisceraFixedEdit = new TblVisceraFixedEdit();
		tblVisceraFixedEdit.setId(getKey("TblVisceraFixedEdit"));
		tblVisceraFixedEdit.setTaskId(tblAnatomyCheckEdit.getTaskId());
		tblVisceraFixedEdit.setStudyNo(tblAnatomyCheckEdit.getStudyNo());
		tblVisceraFixedEdit.setOperateTime(tblAnatomyCheckEdit.getOperateTime());
		tblVisceraFixedEdit.setOperator(tblAnatomyCheckEdit.getOperator());
		tblVisceraFixedEdit.setAnimalCode(tblAnatomyCheckEdit.getAnimalCode());
		tblVisceraFixedEdit.setFixedType(1);
		tblVisceraFixedEdit.setAnatomyCheckEditId(id);
		
		tblVisceraFixedEdit.setEditType(1);
		tblVisceraFixedEdit.setEditRsn(tblAnatomyCheckEdit.getEditRsn());
		tblVisceraFixedEdit.setEditTime(tblAnatomyCheckEdit.getEditTime());
		tblVisceraFixedEdit.setOldId(null);
		getSession().save(tblVisceraFixedEdit);
		
		json.setMsg(id);
		json.setSuccess(true);
		return json;
	}

}
