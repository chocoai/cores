package com.lanen.service.clinicaltest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanen.base.MapResultTransformer;
import com.lanen.util.NumberValidationUtils;

@Service
@Transactional
public class PathServiceImpl implements PathService {

	@Resource
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<String> getYearList() {
		String sql = "select distinct convert(varchar(4),study.studyStartDate,120)"+
				" from("+
				" 	select distinct d.studyNo"+
				" 	from CoresStudy.dbo.tblAnatomyReq as d "+
				" 	) as r"+
				" 	left join CoresStudy.dbo.tblStudyPlan as study"+
				" 	on r.studyNo = study.studyNo"+
				" order by convert(varchar(4),study.studyStartDate,120) desc";
		
		List<String> yearList = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return yearList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getStudyNoMapListByYear(String year) {
		String sql = "select distinct d.studyNo"+
					" from CoresStudy.dbo.tblAnatomyReq  as d "+
					" 	left join CoresStudy.dbo.tblStudyPlan as study"+
					" 	on d.studyNo = study.studyNo"+
					" where convert(varchar(4),study.studyStartDate,120) = :year "+
					" order by d.studyNo";
		List<Map<String,Object>> mapList = sessionFactory.getCurrentSession()
														.createSQLQuery(sql)
														.setParameter("year", year)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDataConfirmMapList(String studyNo,
			int operateType) {
		String operate = "全部";
//		switch (operateType) {
//			case 1:operate = "添加";break;
//			case 2:operate = "编辑";break;
//			case 3:operate = "删除";break;
//			case 8:operate = "全部";break;
//			default:break;
//		}
		
//		String sql = "select CoresStudy.dbo.studyNoRemoveFN(his.studyNo) as studyNo,his.animalCode,"+
//					" 	his.anatomyFingding,"+
////	--原解剖所见：编辑时从his中取，非编辑时为空
//					" 	case when his.operate = '添加' then ''else"+
//					" 				 case when his.autolyzaFlag = 1 then '自溶' "+
//					" 				 else"+
//					" 				  	( "+
//					" 					case when isnull(his.visceraName,'') = '' then '' "+
//					" 					when isnull(his.subVisceraName,'') = '' then his.visceraName +' ' "+
//					" 						else his.subVisceraName+' ' end"+
//					" 					+case when isnull(his.bodySurfacePos,'') = '' then '' else his.bodySurfacePos+' ' end"+
//					" 				  	+case when isnull(his.anatomyPos,'') = '' then '' else his.anatomyPos+' ' end"+
//					" 				 	+case when isnull(his.pos,'') = '' then '' else his.pos+' ' end"+
//					" 				  	+case when isnull(his.number,'') = '' then '' else his.number+' ' end"+
//					" 				  	+case when isnull(his.range,'') = '' then '' else his.range+' ' end"+
//					" 				 	+case when isnull(his.size,'') = '' then '' else his.size+' ' end"+
//					" 				 	+case when isnull(his.color,'') = '' then '' else his.color+' ' end"+
//					" 				  	+case when isnull(his.texture,'') = '' then '' else his.texture+' ' end"+
//					" 				 	+case when isnull(his.shape,'') = '' then '' else his.shape+' ' end"+
//					" 				  	+case when isnull(his.anatomyFingding,'') = '' then '' else his.anatomyFingding+' ' end"+
//					" 				  	+case when isnull(his.lesionDegree,'') = '' then '' else his.lesionDegree+' ' end"+
//					" 				  	) end  "+
//					" 				 end as oldAnatomyFinding,"+
////	--解剖所见：编辑时从tblAnatomyCheck中取，非编辑时从his取
//					" 	case when his.operate = '编辑' or his.operate = '删除' then "+
//					" 				case when ac.autolyzaFlag = 1 then '自溶' "+
//					" 				 else"+
//					" 				  	( "+
//					" 					case when isnull(ac.visceraName,'') = '' then '' "+
//					" 					when isnull(ac.subVisceraName,'') = '' then ac.visceraName +' ' "+
//					" 						else ac.subVisceraName+' ' end"+
//					" 					+case when isnull(ac.bodySurfacePos,'') = '' then '' else ac.bodySurfacePos+' ' end"+
//					" 				  	+case when isnull(ac.anatomyPos,'') = '' then '' else ac.anatomyPos+' ' end"+
//					" 			 	+case when isnull(ac.pos,'') = '' then '' else ac.pos+' ' end"+
//					" 			  	+case when isnull(ac.number,'') = '' then '' else ac.number+' ' end"+
//					" 				  	+case when isnull(ac.range,'') = '' then '' else ac.range+' ' end"+
//					" 				 	+case when isnull(ac.size,'') = '' then '' else ac.size+' ' end"+
//					" 				 	+case when isnull(ac.color,'') = '' then '' else ac.color+' ' end"+
//					" 				  	+case when isnull(ac.texture,'') = '' then '' else ac.texture+' ' end"+
//					" 				 	+case when isnull(ac.shape,'') = '' then '' else ac.shape+' ' end"+
//					" 				  	+case when isnull(ac.anatomyFingding,'') = '' then '' else ac.anatomyFingding+' ' end"+
//					" 				  	+case when isnull(ac.lesionDegree,'') = '' then '' else ac.lesionDegree+' ' end"+
//					" 				  	) end"+
//					" 	else"+
//					" 				 case when his.autolyzaFlag = 1 then '自溶' "+
//					" 				 else"+
//					" 				  	( "+
//					" 					case when isnull(his.visceraName,'') = '' then '' "+
//					" 					when isnull(his.subVisceraName,'') = '' then his.visceraName +' ' "+
//					" 						else his.subVisceraName+' ' end"+
//					" 					+case when isnull(his.bodySurfacePos,'') = '' then '' else his.bodySurfacePos+' ' end"+
//					" 				  	+case when isnull(his.anatomyPos,'') = '' then '' else his.anatomyPos+' ' end"+
//					" 				 	+case when isnull(his.pos,'') = '' then '' else his.pos+' ' end"+
//					" 				  	+case when isnull(his.number,'') = '' then '' else his.number+' ' end"+
//					" 				  	+case when isnull(his.range,'') = '' then '' else his.range+' ' end"+
//					" 				 	+case when isnull(his.size,'') = '' then '' else his.size+' ' end"+
//					" 				 	+case when isnull(his.color,'') = '' then '' else his.color+' ' end"+
//					" 				  	+case when isnull(his.texture,'') = '' then '' else his.texture+' ' end"+
//					" 				 	+case when isnull(his.shape,'') = '' then '' else his.shape+' ' end"+
//					" 				  	+case when isnull(his.anatomyFingding,'') = '' then '' else his.anatomyFingding+' ' end"+
//					" 				  	+case when isnull(his.lesionDegree,'') = '' then '' else his.lesionDegree+' ' end"+
//					" 				  	) end  "+
//					" 				 end as anatomyFinding,"+
//					" 	u1.realName as operator ,convert(varchar(16),his.operateTime,120) as operateTime,"+
//					" 	his.operate,his.operateRsn,u2.realName as operater,"+
//					" 	convert(varchar(16),his.operateDate,120) as operateDate ,CoresStudy.dbo.studyNoToFN(his.studyNo) as fn"+
//					" from CoresStudy.dbo.TblAnatomyCheckHis as his left join CoresStudy.dbo.TblAnatomyCheck as ac"+
//					" 	on his.operate = '编辑' and his.oldId = ac.id left join CoresUserPrivilege.dbo.tbluser as u1"+
//					" 	on his.operator = u1.userName left join CoresUserPrivilege.dbo.tbluser as u2"+
//					" 	on his.operater = u2.userName"+
//					" where CoresStudy.dbo.studyNoRemoveFN(his.studyNo) = :studyNo and his.operate = :operate "+
//					" order by his.animalCode ,his.id";
		String sql = "select  CoresStudy.dbo.studyNoRemoveFN(his1.studyNo) as studyNo,his1.animalCode,"+
					 	
//	--原解剖所见：
					" 					 	case when his1.operate = '添加' then ''else"+
					" 				 				 case when his1.autolyzaFlag = 1 then '自溶' "+
					" 				 else"+
					" 				  	( "+
					" 					case when isnull(his1.visceraName,'') = '' then '' "+
					" 					when isnull(his1.subVisceraName,'') = '' then his1.visceraName +' ' "+
					" 						else his1.subVisceraName+' ' end"+
					" 					+case when isnull(his1.bodySurfacePos,'') = '' then '' else his1.bodySurfacePos+' ' end"+
					" 			  	+case when isnull(his1.anatomyPos,'') = '' then '' else his1.anatomyPos+' ' end"+
					" 				 	+case when isnull(his1.pos,'') = '' then '' else his1.pos+' ' end"+
					" 				  	+case when isnull(his1.number,'') = '' then '' else his1.number+' ' end"+
					" 			  	+case when isnull(his1.range,'') = '' then '' else his1.range+' ' end"+
					" 				 	+case when isnull(his1.size,'') = '' then '' else his1.size+' ' end"+
					" 				 	+case when isnull(his1.color,'') = '' then '' else his1.color+' ' end"+
					" 				  	+case when isnull(his1.texture,'') = '' then '' else his1.texture+' ' end"+
					" 				 	+case when isnull(his1.shape,'') = '' then '' else his1.shape+' ' end"+
					" 				  	+case when isnull(his1.anatomyFingding,'') = '' then '' else his1.anatomyFingding+' ' end"+
					" 				  	+case when isnull(his1.lesionDegree,'') = '' then '' else his1.lesionDegree+' ' end"+
					" 				  	) end  "+
					" 				 end as oldAnatomyFinding,"+
//	--解剖所见：
					" 	case when his1.operate = '删除'  then '' when his1.operate = '编辑'  then "+
					" 				case when ac.autolyzaFlag = 1 then '自溶' "+
					" 			when his2.id is not null then "+
					" 			( "+
					" 					case when isnull(his2.visceraName,'') = '' then '' "+
					" 					when isnull(his2.subVisceraName,'') = '' then his2.visceraName +' ' "+
					" 						else his2.subVisceraName+' ' end"+
					" 					+case when isnull(his2.bodySurfacePos,'') = '' then '' else his2.bodySurfacePos+' ' end"+
					" 				  	+case when isnull(his2.anatomyPos,'') = '' then '' else his2.anatomyPos+' ' end"+
					" 			 	+case when isnull(his2.pos,'') = '' then '' else his2.pos+' ' end"+
					" 			  	+case when isnull(his2.number,'') = '' then '' else his2.number+' ' end"+
					" 				  	+case when isnull(his2.range,'') = '' then '' else his2.range+' ' end"+
					" 				 	+case when isnull(his2.size,'') = '' then '' else his2.size+' ' end"+
					" 				 	+case when isnull(his2.color,'') = '' then '' else his2.color+' ' end"+
					" 				  	+case when isnull(his2.texture,'') = '' then '' else his2.texture+' ' end"+
					" 				 	+case when isnull(his2.shape,'') = '' then '' else his2.shape+' ' end"+
					" 				  	+case when isnull(his2.anatomyFingding,'') = '' then '' else his2.anatomyFingding+' ' end"+
					" 				  	+case when isnull(his2.lesionDegree,'') = '' then '' else his2.lesionDegree+' ' end"+
					" 			  	)"+
					" 				 else"+
					" 			  	( "+
					" 					case when isnull(ac.visceraName,'') = '' then '' "+
					" 					when isnull(ac.subVisceraName,'') = '' then ac.visceraName +' ' "+
					" 						else ac.subVisceraName+' ' end"+
					" 					+case when isnull(ac.bodySurfacePos,'') = '' then '' else ac.bodySurfacePos+' ' end"+
					" 				  	+case when isnull(ac.anatomyPos,'') = '' then '' else ac.anatomyPos+' ' end"+
					" 			 	+case when isnull(ac.pos,'') = '' then '' else ac.pos+' ' end"+
					" 			  	+case when isnull(ac.number,'') = '' then '' else ac.number+' ' end"+
					" 				  	+case when isnull(ac.range,'') = '' then '' else ac.range+' ' end"+
					" 				 	+case when isnull(ac.size,'') = '' then '' else ac.size+' ' end"+
					" 				 	+case when isnull(ac.color,'') = '' then '' else ac.color+' ' end"+
					" 				  	+case when isnull(ac.texture,'') = '' then '' else ac.texture+' ' end"+
					" 				 	+case when isnull(ac.shape,'') = '' then '' else ac.shape+' ' end"+
					" 				  	+case when isnull(ac.anatomyFingding,'') = '' then '' else ac.anatomyFingding+' ' end"+
					" 				  	+case when isnull(ac.lesionDegree,'') = '' then '' else ac.lesionDegree+' ' end"+
					" 			  	) end"+
					" 	else"+
					" 				 case when his1.autolyzaFlag = 1 then '自溶' "+
					" 				 else"+
					" 				  	( "+
					" 					case when isnull(his1.visceraName,'') = '' then '' "+
					" 					when isnull(his1.subVisceraName,'') = '' then his1.visceraName +' ' "+
					" 						else his1.subVisceraName+' ' end"+
					" 					+case when isnull(his1.bodySurfacePos,'') = '' then '' else his1.bodySurfacePos+' ' end"+
					" 			  	+case when isnull(his1.anatomyPos,'') = '' then '' else his1.anatomyPos+' ' end"+
					" 				 	+case when isnull(his1.pos,'') = '' then '' else his1.pos+' ' end"+
					" 				  	+case when isnull(his1.number,'') = '' then '' else his1.number+' ' end"+
					" 				  	+case when isnull(his1.range,'') = '' then '' else his1.range+' ' end"+
					" 				 	+case when isnull(his1.size,'') = '' then '' else his1.size+' ' end"+
					" 				 	+case when isnull(his1.color,'') = '' then '' else his1.color+' ' end"+
					" 				  	+case when isnull(his1.texture,'') = '' then '' else his1.texture+' ' end"+
					" 				 	+case when isnull(his1.shape,'') = '' then '' else his1.shape+' ' end"+
					" 				  	+case when isnull(his1.anatomyFingding,'') = '' then '' else his1.anatomyFingding+' ' end"+
					" 				  	+case when isnull(his1.lesionDegree,'') = '' then '' else his1.lesionDegree+' ' end"+
					" 				  	) end  "+
					" 				 end as anatomyFinding,"+
					" 	u1.realName as operator ,"+
					" case when his1.operate != '添加' then convert(varchar(16),his1.operateTime,120) else "+
					" 		convert(varchar(16),his1.operateDate,120) end as operateTime,"+
					" 	his1.operate,his1.operateRsn,u2.realName as operater,"+
					" 	convert(varchar(16),his1.operateDate,120) as operateDate ,CoresStudy.dbo.studyNoToFN(his1.studyNo) as fn"+
					" from (select *,ROW_NUMBER() over(PARTITION BY his.oldId ORDER BY his.id) as rowNum"+
					" 		from CoresStudy.dbo.TblAnatomyCheckHis as his "+
					" 		where CoresStudy.dbo.studyNoRemoveFN(his.studyNo) = :studyNo and ('全部' = :operate or his.operate =:operate))"+
					" 	as his1 left join "+
					" 	(select *,ROW_NUMBER() over(PARTITION BY his.oldId ORDER BY his.id) as rowNum"+
					" 		from CoresStudy.dbo.TblAnatomyCheckHis as his "+
					" 		where CoresStudy.dbo.studyNoRemoveFN(his.studyNo) = :studyNo and ('全部' = :operate or his.operate =:operate))"+
					" 	as his2"+
					" 	on his1.oldId = his2.oldId and his1.rowNum +1 = his2.rowNum "+
					" 	left join CoresStudy.dbo.TblAnatomyCheck as ac"+
					" 	on his2.id is null and his1.operate = '编辑' and his1.oldId = ac.id left join CoresUserPrivilege.dbo.tbluser as u1"+
					" 	on his1.operator = u1.userName left join CoresUserPrivilege.dbo.tbluser as u2"+
					" 	on his1.operater = u2.userName"+

					" order by his1.animalCode,his1.oldId,his1.id";
		
		List<Map<String,Object>> mapList = sessionFactory.getCurrentSession()
														.createSQLQuery(sql)
														.setParameter("studyNo", studyNo)
														.setParameter("operate", operate)
														.setResultTransformer(new MapResultTransformer())
														.list();
		
		if(null != mapList && operateType != 8){
			List<Map<String,Object>> mapList2 = new ArrayList<Map<String,Object>>();
			switch (operateType) {
				case 1:operate = "添加";break;
				case 2:operate = "编辑";break;
				case 3:operate = "删除";break;
				case 8:operate = "全部";break;
				default:break;
			}
			for(Map<String,Object> obj:mapList){
				String currentOperate = (String) obj.get("operate");
				if(operate.equals(currentOperate)){
					mapList2.add(obj);
				}
			}
			return mapList2;
		}else{
			return mapList;
		}
		
		
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDataEditMapList(String studyNo) {
		String sql = "select r.id,CoresStudy.dbo.studyNoRemoveFN(r.studyNo) as studyNo,r.animalCode,r.oldAnatomyFinding,r.anatomyFinding,"+
					" 	u.realName as operator , convert(varchar(16),r.operateTime,120) as operateTime,"+
					" 		r.operate,r.operateRsn,r.operater,convert(varchar(16),r.operateDate,120) as operateDate " +
					"    ,CoresStudy.dbo.studyNoToFN(r.studyNo) as fn "+
					" from ("+
					" 		select edit.id, edit.studyNo,edit.animalCode,"+
//					" 				--原解剖所见"+
					" 				case edit.editType when 1 then ''"+
					" 				 else "+
					" 					 case when ac.autolyzaFlag = 1 then '自溶' "+
					" 						 else"+
					" 				  			( "+
					" 							case when isnull(ac.visceraName,'') = '' then '' "+
					" 							when isnull(ac.subVisceraName,'') = '' then ac.visceraName +' ' "+
					" 								else ac.subVisceraName+' ' end"+
					" 							+case when isnull(ac.bodySurfacePos,'') = '' then '' else ac.bodySurfacePos+' ' end"+
					" 				  			+case when isnull(ac.anatomyPos,'') = '' then '' else ac.anatomyPos+' ' end"+
					" 				 			+case when isnull(ac.pos,'') = '' then '' else ac.pos+' ' end"+
							" 				  			+case when isnull(ac.number,'') = '' then '' else ac.number+' ' end"+
							" 				  			+case when isnull(ac.range,'') = '' then '' else ac.range+' ' end"+
							" 				 			+case when isnull(ac.size,'') = '' then '' else ac.size+' ' end"+
							" 				 			+case when isnull(ac.color,'') = '' then '' else ac.color+' ' end"+
							" 				  			+case when isnull(ac.texture,'') = '' then '' else ac.texture+' ' end"+
							" 				 			+case when isnull(ac.shape,'') = '' then '' else ac.shape+' ' end"+
							" 				  			+case when isnull(ac.anatomyFingding,'') = '' then '' else ac.anatomyFingding+' ' end"+
							" 				  			+case when isnull(ac.lesionDegree,'') = '' then '' else ac.lesionDegree+' ' end"+
							" 				  			) end"+
							" 				end  as oldAnatomyFinding,"+
//				--解剖所见
							" 				case edit.editType when 3 then ''"+
							" 				 else "+
							" 					 case when ac.autolyzaFlag = 1 then '自溶' "+
							" 						 else"+
							" 				  			( "+
							" 							case when isnull(edit.visceraName,'') = '' then '' "+
							" 							when isnull(edit.subVisceraName,'') = '' then edit.visceraName +' ' "+
							" 								else edit.subVisceraName+' ' end"+
							" 						+case when isnull(edit.bodySurfacePos,'') = '' then '' else edit.bodySurfacePos+' ' end"+
							" 				  			+case when isnull(edit.anatomyPos,'') = '' then '' else edit.anatomyPos+' ' end"+
							" 				 			+case when isnull(edit.pos,'') = '' then '' else edit.pos+' ' end"+
							" 				  			+case when isnull(edit.number,'') = '' then '' else edit.number+' ' end"+
							" 				  			+case when isnull(edit.range,'') = '' then '' else edit.range+' ' end"+
							" 				 			+case when isnull(edit.size,'') = '' then '' else edit.size+' ' end"+
							" 				 			+case when isnull(edit.color,'') = '' then '' else edit.color+' ' end"+
							" 				  			+case when isnull(edit.texture,'') = '' then '' else edit.texture+' ' end"+
							" 				 			+case when isnull(edit.shape,'') = '' then '' else edit.shape+' ' end"+
							" 				  			+case when isnull(edit.anatomyFingding,'') = '' then '' else edit.anatomyFingding+' ' end"+
							" 			  			+case when isnull(edit.lesionDegree,'') = '' then '' else edit.lesionDegree+' ' end"+
							" 				  			) end"+
							" 				end  as anatomyFinding,"+
							" 			case edit.editType when 1 then edit.operator else ac.operator end as operator,"+
							" 			case edit.editType when 1 then edit.operateTime else ac.operateTime end as operateTime,"+
							" 			case edit.editType when 1 then '添加' when 2 then'编辑' when 3 then '删除' else '' end as operate,"+
							" 			edit.editRsn as operateRsn,edit.editTime as operateDate,es.signer as operater"+
							" 	from CoresStudy.dbo.tblAnatomyCheckEdit as edit left join CoresStudy.dbo.TblAnatomyCheck as ac"+
							" 			on (edit.editType = 2 or edit.editType = 3) and edit.oldId = ac.id left join CoresUserPrivilege.dbo.tblESLink "+
							" 			as eslink on eslink.tableName ='TblAnatomyCheckEdit' and edit.id = eslink.dataId and "+
							" 			( edit.editType =1 and eslink.esType = 256 or edit.editType =2 and eslink.esType = 257 or "+
							" 			edit.editType =3 and eslink.esType = 258 ) left join CoresUserPrivilege.dbo.tblES as es "+
							" 			on eslink.esId = es.esId "+
							" 		where CoresStudy.dbo.studyNoRemoveFN(edit.studyNo) = :studyNo "+

							" 		union"+

							" 	select edit.id, edit.studyNo,edit.animalCode,"+
//				--原解剖所见
							" 			case edit.editType when 1 then ''"+
							" 			 else "+
							" 				 case when ac.autolyzaFlag = 1 then '自溶' "+
							" 					 else"+
								" 			  			( "+
								" 						case when isnull(ac.visceraName,'') = '' then '' "+
								" 						when isnull(ac.subVisceraName,'') = '' then ac.visceraName +' ' "+
								" 							else ac.subVisceraName+' ' end"+
								" 						+case when isnull(ac.bodySurfacePos,'') = '' then '' else ac.bodySurfacePos+' ' end"+
								" 			  			+case when isnull(ac.anatomyPos,'') = '' then '' else ac.anatomyPos+' ' end"+
							" 			 			+case when isnull(ac.pos,'') = '' then '' else ac.pos+' ' end"+
							" 			  			+case when isnull(ac.number,'') = '' then '' else ac.number+' ' end"+
							" 			  			+case when isnull(ac.range,'') = '' then '' else ac.range+' ' end"+
							" 			 			+case when isnull(ac.size,'') = '' then '' else ac.size+' ' end"+
							" 			 			+case when isnull(ac.color,'') = '' then '' else ac.color+' ' end"+
							" 			  			+case when isnull(ac.texture,'') = '' then '' else ac.texture+' ' end"+
							" 			 			+case when isnull(ac.shape,'') = '' then '' else ac.shape+' ' end"+
							" 			  			+case when isnull(ac.anatomyFingding,'') = '' then '' else ac.anatomyFingding+' ' end"+
							" 			  			+case when isnull(ac.lesionDegree,'') = '' then '' else ac.lesionDegree+' ' end"+
							" 		  			) end"+
							" 			end  as oldAnatomyFinding,"+
//				--解剖所见
							" 			case edit.editType when 3 then ''"+
							" 			 else "+
							" 				 case when ac.autolyzaFlag = 1 then '自溶' "+
							" 					 else"+
								" 			  			( "+
								" 						case when isnull(edit.visceraName,'') = '' then '' "+
								" 						when isnull(edit.subVisceraName,'') = '' then edit.visceraName +' ' "+
								" 							else edit.subVisceraName+' ' end"+
								" 						+case when isnull(edit.bodySurfacePos,'') = '' then '' else edit.bodySurfacePos+' ' end"+
								" 			  			+case when isnull(edit.anatomyPos,'') = '' then '' else edit.anatomyPos+' ' end"+
								" 			 			+case when isnull(edit.pos,'') = '' then '' else edit.pos+' ' end"+
								" 			  			+case when isnull(edit.number,'') = '' then '' else edit.number+' ' end"+
							" 			  			+case when isnull(edit.range,'') = '' then '' else edit.range+' ' end"+
							" 			 			+case when isnull(edit.size,'') = '' then '' else edit.size+' ' end"+
							" 			 			+case when isnull(edit.color,'') = '' then '' else edit.color+' ' end"+
							" 		  			+case when isnull(edit.texture,'') = '' then '' else edit.texture+' ' end"+
							" 			 			+case when isnull(edit.shape,'') = '' then '' else edit.shape+' ' end"+
							" 		  			+case when isnull(edit.anatomyFingding,'') = '' then '' else edit.anatomyFingding+' ' end"+
							" 		  			+case when isnull(edit.lesionDegree,'') = '' then '' else edit.lesionDegree+' ' end"+
							" 		  			) end"+
							" 			end  as anatomyFinding,"+
							" 			case edit.editType when 1 then edit.operator else ac.operator end as operator,"+
							" 			case edit.editType when 1 then edit.operateTime else ac.operateTime end as operateTime,"+
							" 			 '删除'  as operate,"+
							" 			edit.delRsn as operateRsn,edit.delTime as operateDate,es.signer as operater"+
							" 	from CoresStudy.dbo.tblAnatomyCheckEdit as edit left join CoresStudy.dbo.TblAnatomyCheck as ac"+
							" 		on (edit.editType = 2 or edit.editType = 3) and edit.oldId = ac.id left join CoresUserPrivilege.dbo.tblESLink "+
							" 		as eslink on eslink.tableName ='TblAnatomyCheckEdit' and edit.id = eslink.dataId and "+
							" 			( edit.editType =1 and eslink.esType = 258 or edit.editType =2 and eslink.esType = 258  ) "+
							" 			left join CoresUserPrivilege.dbo.tblES as es on eslink.esId = es.esId "+
							" 	where CoresStudy.dbo.studyNoRemoveFN(edit.studyNo) = :studyNo and edit.delFlag = 1 ) as r "+
							" 	left join CoresUserPrivilege.dbo.tbluser as u on r.operator = u.userName "+
							" order by r.id,r.operateDate";
		List<Map<String,Object>> mapList = sessionFactory.getCurrentSession()
														.createSQLQuery(sql)
														.setParameter("studyNo", studyNo)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnatomyReqCancelMapList(String studyNo) {
		String sql = "select req.id,CoresStudy.dbo.studyNoRemoveFN(req.studyNo) as studyNo1,req.studyNo,req.reqNo,convert(varchar(10),req.beginDate,120) as beginDate,"+
					" 	convert(varchar(10),req.endDate,120) as endDate,req.anatomyRsn,"+
					" 	 u.realName as author,convert(varchar(10),req.createTime,120) as createTime,'已撤销' as submitFlag ," +
					" req.id,CoresStudy.dbo.studyNoToFN(req.studyNo) as fn"+
					" from CoresStudy.dbo.tblAnatomyReq as req left join CoresUserPrivilege.dbo.tbluser as u	"+
					" 	on req.author = u.userName"+
					" where CoresStudy.dbo.studyNoRemoveFN(req.studyNo) = :studyNo and req.submitFlag = -1";
		List<Map<String,Object>> mapList = sessionFactory.getCurrentSession()
				.createSQLQuery(sql)
				.setParameter("studyNo", studyNo)
				.setResultTransformer(new MapResultTransformer())
				.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnatomyReqChangeMapList(String studyNo) {
		String sql = "select r.id,CoresStudy.dbo.studyNoRemoveFN(r.studyNo) as studyNo1,r.studyNo,r.reqNo,r.beginDate,r.endDate,r.anatomyRsn,r.author,r.createTime,r.submitFlag,r.change" +
				"	,CoresStudy.dbo.studyNoToFN(r.studyNo) as fn"+
					" from (select his.id,his.studyNo,his.reqNo,convert(varchar(10),his.beginDate,120) as beginDate,"+
					" 	convert(varchar(10),his.endDate,120) as endDate,his.anatomyRsn,"+
					" 	 u.realName as author,convert(varchar(10),his.createTime,120) as createTime,'变更前' as submitFlag,"+
					" 	 1 as change"+
					" from CoresStudy.dbo.tblAnatomyReq as req left join CoresStudy.dbo.tblAnatomyReqHis as his on "+
					" 	req.id = his.id left join CoresUserPrivilege.dbo.tbluser as u	"+
					" 	on req.author = u.userName"+
					" where his.id is not null and CoresStudy.dbo.studyNoRemoveFN(his.studyNo) = :studyNo "+
					" 	union"+
					" select req.id,req.studyNo,req.reqNo,convert(varchar(10),req.beginDate,120) as beginDate,"+
					" 	convert(varchar(10),req.endDate,120) as endDate,req.anatomyRsn,"+
					" 	 u.realName as author,convert(varchar(10),req.createTime,120) as createTime,'变更后' as submitFlag,"+
					" 	 2 as change"+
					" from CoresStudy.dbo.tblAnatomyReq as req left join CoresStudy.dbo.tblAnatomyReqHis as his on "+
					" 	req.id = his.id left join CoresUserPrivilege.dbo.tbluser as u	"+
					" 	on req.author = u.userName"+
					" where his.id is not null and CoresStudy.dbo.studyNoRemoveFN(his.studyNo) = :studyNo ) as r"+
					" order by r.id,r.change";
		List<Map<String,Object>> mapList = sessionFactory.getCurrentSession()
														.createSQLQuery(sql)
														.setParameter("studyNo", studyNo)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getReqView(String studyNo, int reqNo, int change) {
		Map<String,Object> map = null;
		// animalType，anatomyRsn，testPhase，anatomyDate，animalList,anatomyVisceraList,visceraWeighList
		if(change != 1){
			//1.查询  animalType，anatomyRsn，testPhase，anatomyDate
			String sql = "select case req.anatomyRsn  when 1 then '计划解剖' when 2 then '濒死解剖'"+
						" 	when 3 then '死亡解剖' when 4 then '安乐死解剖' else '' end as anatomyRsn,"+
						" 	req.testPhase,convert(varchar(10),req.beginDate,120) as beginDate,"+
						" 	convert(varchar(10),req.endDate,120) as endDate,st.animalType"+
						" from CoresStudy.dbo.tblAnatomyReq as req left join CoresStudy.dbo.tblStudyPlan as st"+
						" 	on req.studyNo = st.studyNo "+
						" where req.studyNo = :studyNo and req.reqNo = :reqNo ";
			map = (Map<String, Object>) sessionFactory.getCurrentSession()
																		.createSQLQuery(sql)
																		.setParameter("studyNo", studyNo)
																		.setParameter("reqNo", reqNo)
																		.setResultTransformer(new MapResultTransformer())
																		.uniqueResult();
			String beginDate = (String) map.get("beginDate");
			String endDate = (String) map.get("endDate");
			if(beginDate.equals(endDate)){
				map.put("anatomyDate", beginDate);
			}else{
				map.put("anatomyDate", beginDate+"～"+endDate);
			}
			//2.查询动物列表 //animalList
			String sql1 = "select a.animalCode ,a.gender"+
						" from CoresStudy.dbo.tblAnatomyReqAnimalList as a"+
						" where a.studyNo = :studyNo and a.anatomyReqNo = :reqNo " ;
//						" order by a.animalCode";
			List<Map<String,Object>> animalList = sessionFactory.getCurrentSession()
																.createSQLQuery(sql1)
																.setParameter("studyNo", studyNo)
																.setParameter("reqNo", reqNo)
																.setResultTransformer(new MapResultTransformer())
																.list();
			map.put("animalList", animalList);
			
			//3.查询解剖脏器列表anatomyVisceraList
			
			String sql2 = "select a.id,a.visceraName,a.atanomyCheckFlag,a.visceraFixedFlag,a.histopathCheckFlag,a.sn"+
						" from CoresStudy.dbo.tblAnatomyReqPathPlanCheck as a"+
						" where a.studyNo = :studyNo and a.reqNo = :reqNo";
			List<Map<String,Object>> anatomyVisceraList = sessionFactory.getCurrentSession()
						.createSQLQuery(sql2)
						.setParameter("studyNo", studyNo)
						.setParameter("reqNo", reqNo)
						.setResultTransformer(new MapResultTransformer())
						.list();
			map.put("anatomyVisceraList", anatomyVisceraList);
			
			//4.查询称重脏器列表,visceraWeighList
			String sql3 = "select a.id,a.visceraName,a.partVisceraSeparateWeigh,a.fixedWeighFlag,a.attachedVisceraFlag"+
						" from CoresStudy.dbo.tblAnatomyReqVisceraWeigh as a"+
						" where a.studyNo = :studyNo and a.reqNo = :reqNo";
			List<Map<String,Object>> visceraWeighList = sessionFactory.getCurrentSession()
			.createSQLQuery(sql3)
			.setParameter("studyNo", studyNo)
			.setParameter("reqNo", reqNo)
			.setResultTransformer(new MapResultTransformer())
			.list();
			if(null != visceraWeighList && visceraWeighList.size() > 0){
				for(Map<String,Object> obj:visceraWeighList){
					int attachedVisceraFlag = (Integer) obj.get("attachedVisceraFlag");
					if(attachedVisceraFlag == 1){
						String id = (String) obj.get("id");
						String attachedViscera = "";
						String sql4 = "select a.visceraName"+
						" from CoresStudy.dbo.tblAnatomyReqAttachedViscera as a"+
						" where a.anatomyReqVisceraWeighID = :id  and a.visceraName is not null";
						List<String> visceraNameList = sessionFactory.getCurrentSession()
																	.createSQLQuery(sql4)
																	.setParameter("id", id)
																	.list();
						for(int i = 0;i<visceraNameList.size();i++){
							String visceraName = visceraNameList.get(i);
							if(i == 0){
								attachedViscera = visceraName;
							}else{
								attachedViscera =attachedViscera +"、"+ visceraName;
							}
						}
						obj.put("attachedViscera", attachedViscera);
					}
				}
			}
			map.put("visceraWeighList", visceraWeighList);
		}else{
			//1.查询  animalType，anatomyRsn，testPhase，anatomyDate
			String sql = "select case req.anatomyRsn  when 1 then '计划解剖' when 2 then '濒死解剖'"+
			" 	when 3 then '死亡解剖' when 4 then '安乐死解剖' else '' end as anatomyRsn,"+
			" 	req.testPhase,convert(varchar(10),req.beginDate,120) as beginDate,"+
			" 	convert(varchar(10),req.endDate,120) as endDate,st.animalType"+
			" from CoresStudy.dbo.tblAnatomyReqHis as req left join CoresStudy.dbo.tblStudyPlan as st"+
			" 	on req.studyNo = st.studyNo "+
			" where req.studyNo = :studyNo and req.reqNo = :reqNo ";
			map = (Map<String, Object>) sessionFactory.getCurrentSession()
			.createSQLQuery(sql)
			.setParameter("studyNo", studyNo)
			.setParameter("reqNo", reqNo)
			.setResultTransformer(new MapResultTransformer())
			.uniqueResult();
			String beginDate = (String) map.get("beginDate");
			String endDate = (String) map.get("endDate");
			if(beginDate.equals(endDate)){
				map.put("anatomyDate", beginDate);
			}else{
				map.put("anatomyDate", beginDate+"～"+endDate);
			}
			//2.查询动物列表
			String sql1 = "select a.animalCode ,a.gender"+
							" from CoresStudy.dbo.tblAnatomyReqAnimalListHis as a"+
							" where a.studyNo = :studyNo and a.anatomyReqNo = :reqNo ";
//							" order by a.animalCode";
			List<Map<String,Object>> animalList = sessionFactory.getCurrentSession()
													.createSQLQuery(sql1)
													.setParameter("studyNo", studyNo)
													.setParameter("reqNo", reqNo)
													.setResultTransformer(new MapResultTransformer())
													.list();
			map.put("animalList", animalList);
			//3.查询解剖脏器列表anatomyVisceraList
			
			String sql2 = "select a.id,a.visceraName,a.atanomyCheckFlag,a.visceraFixedFlag,a.histopathCheckFlag,a.sn"+
						" from CoresStudy.dbo.tblAnatomyReqPathPlanCheckHis as a"+
						" where a.studyNo = :studyNo and a.reqNo = :reqNo";
			List<Map<String,Object>> anatomyVisceraList = sessionFactory.getCurrentSession()
						.createSQLQuery(sql2)
						.setParameter("studyNo", studyNo)
						.setParameter("reqNo", reqNo)
						.setResultTransformer(new MapResultTransformer())
						.list();
			map.put("anatomyVisceraList", anatomyVisceraList);
			
			//4.查询称重脏器列表,visceraWeighList
			String sql3 = "select a.id,a.visceraName,a.partVisceraSeparateWeigh,a.fixedWeighFlag,a.attachedVisceraFlag"+
						" from CoresStudy.dbo.tblAnatomyReqVisceraWeighHis as a"+
						" where a.studyNo = :studyNo and a.reqNo = :reqNo";
			List<Map<String,Object>> visceraWeighList = sessionFactory.getCurrentSession()
			.createSQLQuery(sql3)
			.setParameter("studyNo", studyNo)
			.setParameter("reqNo", reqNo)
			.setResultTransformer(new MapResultTransformer())
			.list();
			if(null != visceraWeighList && visceraWeighList.size() > 0){
				for(Map<String,Object> obj:visceraWeighList){
					int attachedVisceraFlag = (Integer) obj.get("attachedVisceraFlag");
					if(attachedVisceraFlag == 1){
						String id = (String) obj.get("id");
						String attachedViscera = "";
						String sql4 = "select a.visceraName"+
						" from CoresStudy.dbo.tblAnatomyReqAttachedVisceraHis as a"+
						" where a.anatomyReqVisceraWeighID = :id  and a.visceraName is not null";
						List<String> visceraNameList = sessionFactory.getCurrentSession()
																	.createSQLQuery(sql4)
																	.setParameter("id", id)
																	.list();
						for(int i = 0;i<visceraNameList.size();i++){
							String visceraName = visceraNameList.get(i);
							if(i == 0){
								attachedViscera = visceraName;
							}else{
								attachedViscera =attachedViscera +"、"+ visceraName;
							}
						}
						obj.put("attachedViscera", attachedViscera);
					}
				}
			}
			map.put("visceraWeighList", visceraWeighList);
		}
		
		return map;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getHistopathCheckMapList(String studyNo,
			int operateType) {
		String sql3 = "select hc.id,CoresStudy.dbo.studyNoRemoveFN(hc.studyNo) as studyNo,hc.animalCode,"+
					"   case when isnull(hc.visceraCode,'') != '' then "+
					" 		("+
					" 		case when isnull(hc.subVisceraCode,'') = '' then hc.visceraName"+
					" 	 		else hc.subVisceraName end"+
					" 	) "+
					" 	else "+
					" 	(case when isnull(sliceViscera.visceraCode,'')= '' then ''  "+
					" 					when isnull(sliceViscera.subVisceraCode,'') = '' then sliceViscera.visceraName+' '"+
					" 					when isnull(sliceViscera.subVisceraCode,'') != '' then sliceViscera.subVisceraName+' ' "+
					" 					else '' end "+
					" 		+ case when isnull(sliceViscera.bodySurfacePos,'') = '' then '' else (sliceViscera.bodySurfacePos+' ') end"+
					" 		+case when isnull(sliceViscera.anatomyPos,'') = '' then '' else (sliceViscera.anatomyPos+' ') end"+
					" 		+case when isnull(sliceViscera.anatomyFingding,'') = '' then '' else (sliceViscera.anatomyFingding+' ') end"+
					" 			) end   as visceraOrTissueName,"+
//	--性质
					" case hc.isNoAbnormal when 1 then '' else case hc.tumorFlag when 0 then '非肿瘤' when 1 then '良性肿瘤' when 2 then '恶性肿瘤' end end as tumorFlag,"+
//	--转移
					" case hc.isNoAbnormal when 1 then '' else case hc.metastasisFlag when 0 then '源发' when 1 then '转移' when 2 then '侵袭' end end as metastasisFlag,"+
//	--检查结果
					" case hc.isNoAbnormal when 1 then '未见异常' else case when isnull(hc.histoPos,'') =''then hc.lesionFinding else isnull(hc.histoPos,'')+hc.lesionFinding end end as checkResult,"+
//	--原发脏器肿瘤
					" case hc.metastasisFlag when 1 then isnull(hc.primaryViscera,'')+isnull(hc.primaryTumor,'') else '' end as primaryVisceraTumor,"+
//	--检查日期
					" convert(varchar(10),hc.operateTime,120) as checkTime,"+
//	--复查意见
					" isnull(hc.histopathReviewOpinion,'') as opinion,"+
//	--操作
					" case when hc.historyFlag = 2 then '删除' else '添加' end as state,"+
//	--病变程度，数量，位置
					" isnull(hc.level,'') as level,hc.tumorNum,case hc.tumorPos when 1 then '浅表' when 2 then '深部' else '' end as tumorPos,"+
//	--肿瘤发生日期
					" case hc.tumorPos when 1 then convert(varchar(10),hc.tumorOccurDate,120) else '' end as tumorOccurDate,"+
//	--肿瘤特性
					" case hc.tumorPos when 2 then case hc.tumorCharacter when 1 then '偶发' when 2 then '致死' when 3 then '不明确' end else '' end as tumorCharacter"+

					" ,CoresStudy.dbo.studyNoToFN(hc.studyNo)+' '+isnull(hc.remark,'') as remark"+
					" from CoresStudy.dbo.tblHistopathCheck as hc join CoresStudy.dbo.tblPathStudyIndex as study on "+
					" 	hc.studyNo = study.studyNo join CoresStudy.dbo.tblTissueSliceViscera as sliceViscera"+
					" 	on hc.tissueSliceVisceraRecordId = sliceViscera.id"+
					" where CoresStudy.dbo.studyNoRemoveFN(hc.studyNo) = :studyNo  and isnull(study.histopathCheckFinishSign,'') != '' ";
		if(operateType == 9){
			sql3 = sql3+" and (hc.historyFlag = 2 or hc.histopathReviewFlag = 0)";
		}else if(operateType == 10){
			sql3 = sql3+" and ( hc.histopathReviewFlag = 0)";
		}else{
			sql3 = sql3+" and (hc.historyFlag = 2 )";
		}
		
		sql3 = sql3+ " order by hc.animalCode,hc.visceraCode,hc.id";
		
		List<Map<String,Object>> list = sessionFactory.getCurrentSession()
		.createSQLQuery(sql3)
		.setParameter("studyNo", studyNo)
		.setResultTransformer(new MapResultTransformer())
		.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraWeightDataMapList(
			String studyNo, int operateType) {
		String operateTypeStr = "全部";
//		switch (operateType) {
//		case 12:
//			operateTypeStr = "全部";
//			break;
//		case 13:
//			operateTypeStr = "重新称量";
//			break;
//		case 14:
//			operateTypeStr = "删除";
//			break;
//		case 15:
//			operateTypeStr = "编辑";
//			break;
//
//		default:
//			break;
//		}
		String sql3 = "select CoresStudy.dbo.studyNoRemoveFN(his1.studyNo) as studyNo,his1.animalCode,"+
					" 	case when isnull(his1.subVisceraName,'') = '' then his1.visceraName else his1.subVisceraName end as visceraName,"+
					" 	his1.weight as oldWeight,"+
					" 	case when his2.weight is not null then isnull(his2.weight,'') else isnull(w.weight,'') end as weight  ,"+
					" 	his1.attachedVisceraNames,his1.fixedWeightFlag,u1.realName as operator,"+
					" 	convert(varchar(16),his1.operateTime,120) as operateTime,his1.operate,his1.operateRsn,u2.realName as operater,"+
					" 	convert(varchar(16),his1.operateDate,120) as operateDate ,CoresStudy.dbo.studyNoToFN(his1.studyNo) as fn"+
					" from (select *,ROW_NUMBER() over(PARTITION BY his.oldId ORDER BY his.id) as rowNum"+
					" 		from CoresStudy.dbo.tblVisceraWeightHis as his"+
					" 		where CoresStudy.dbo.studyNoRemoveFN(his.studyNo) = :studyNo and ('全部' = :operateType or his.operate =:operateType)"+
					" 			) as his1 "+
					" 	left join "+
					" 		(select *,ROW_NUMBER() over(PARTITION BY his.oldId ORDER BY his.id) as rowNum"+
					" 		from CoresStudy.dbo.tblVisceraWeightHis as his"+
					" 		where CoresStudy.dbo.studyNoRemoveFN(his.studyNo) = :studyNo and ('全部' = :operateType or his.operate =:operateType)"+
					" 			) as his2 on"+
					" 			his1.oldId = his2.oldId and his1.rowNum +1 = his2.rowNum "+
					" 	left join CoresStudy.dbo.tblVisceraWeight as w"+
					" 	on his2.id is null and his1.operate != '删除' and his1.oldId = w.id left join CoresUserPrivilege.dbo.tbluser as u1"+
					" 	on his1.operator = u1.userName left join CoresUserPrivilege.dbo.tbluser as u2"+
					" 	on his1.operater = u2.userName"+
					" order by his1.animalCode,his1.oldId,his1.id";
		List<Map<String,Object>> mapList = sessionFactory.getCurrentSession()
														.createSQLQuery(sql3 )
														.setParameter("studyNo", studyNo)
														.setParameter("operateType", operateTypeStr)
														.setResultTransformer(new MapResultTransformer())
														.list();
		
		if(null != mapList && operateType != 12){
			List<Map<String,Object>> mapList2 = new ArrayList<Map<String,Object>>();
			switch (operateType) {
			case 12:
				operateTypeStr = "全部";
				break;
			case 13:
				operateTypeStr = "重新称量";
				break;
			case 14:
				operateTypeStr = "删除";
				break;
			case 15:
				operateTypeStr = "编辑";
				break;
	
			default:
				break;
			}
			for(Map<String,Object> obj:mapList){
				String currentOperate = (String) obj.get("operate");
				if(operateTypeStr.equals(currentOperate)){
					mapList2.add(obj);
				}
			}
			return mapList2;
		}else{
			return mapList;
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraFixedMapList(String studyNo,
			int operateType) {
		//数据确认
		String sql1 = "select CoresStudy.dbo.studyNoRemoveFN(f.studyNo) as studyNo,f.animalCode,"+
					" 	case when isnull(c.subVisceraName,'') = '' then isnull(c.visceraName,'') else"+
					" 		isnull(c.subVisceraName,'') end  "+
					" 	 +isnull(c.bodySurfacePos,'')+' '+isnull(c.anatomyFingding,'') as visceraName,"+
					"  u1.realName as operator,convert(varchar(16),f.operateTime,120) as operateTime,"+
					"  f.operate,f.operateRsn,u2.realName as operater,"+
					" 	convert(varchar(16),f.operateDate,120) as operateDate ,CoresStudy.dbo.studyNoToFN(f.studyNo) as fn"+
					" from CoresStudy.dbo.tblVisceraFixedHis as f left join CoresStudy.dbo.TblAnatomyCheck as c"+
					" 	on f.anatomyCheckRecordId = c.id left join CoresUserPrivilege.dbo.tbluser as u1"+
					" 	on f.operator = u1.userName  left join CoresUserPrivilege.dbo.tbluser as u2"+
					" 	on f.operator = u2.userName"+
					" where CoresStudy.dbo.studyNoRemoveFN(f.studyNo) = :studyNo "+
					" order by f.animalCode";
		
		String sql2 = "select CoresStudy.dbo.studyNoRemoveFN(f.studyNo) as studyNo,f.animalCode,"+
					" 	case when isnull(c.subVisceraName,'') = '' then isnull(c.visceraName,'') else"+
					" 		isnull(c.subVisceraName,'') end  "+
					" 	 +isnull(c.bodySurfacePos,'')+' '+isnull(c.anatomyFingding,'') as visceraName,"+
					" 	 u1.realName as operator,convert(varchar(16),f.operateTime,120) as operateTime,"+
					" 	  '添加' as  operate,f.editRsn as operateRsn,u2.realName as operater,"+
					" 	convert(varchar(16),f.editTime,120) as operateDate ,CoresStudy.dbo.studyNoToFN(f.studyNo) as fn"+
					" from CoresStudy.dbo.tblVisceraFixedEdit as f left join CoresStudy.dbo.TblAnatomyCheckEdit as c"+
					" 	on f.anatomyCheckEditId = c.id left join CoresUserPrivilege.dbo.tbluser as u1"+
					" 	on f.operator = u1.userName  left join CoresUserPrivilege.dbo.tbluser as u2"+
					" 	on f.operator = u2.userName"+
					" where CoresStudy.dbo.studyNoRemoveFN(f.studyNo) = :studyNo "+
					" order by f.animalCode";
		
		if(operateType == 16){
			List<Map<String,Object>> mapList1 = sessionFactory.getCurrentSession()
			.createSQLQuery(sql1 )
			.setParameter("studyNo", studyNo)
			.setResultTransformer(new MapResultTransformer())
			.list();
			List<Map<String,Object>> mapList2 = sessionFactory.getCurrentSession()
			.createSQLQuery(sql2 )
			.setParameter("studyNo", studyNo)
			.setResultTransformer(new MapResultTransformer())
			.list();
			if(null == mapList1 || mapList1.size() < 0){
				return mapList2;
			}else{
				mapList1.addAll(mapList2);
				Collections.sort(mapList1, new Comparator<Map<String,Object>>(){

					public int compare(Map<String, Object> obj1,
							Map<String, Object> obj2) {
						String animalCode1 = (String) obj1.get("animalCode");
						String animalCode2 = (String) obj2.get("animalCode");
						if(null != animalCode1 && null != animalCode2 && !animalCode1.equals(animalCode2)){
							if(NumberValidationUtils.isPositiveInteger(animalCode1) && NumberValidationUtils.isPositiveInteger(animalCode2)){
								return Integer.parseInt(animalCode1) - Integer.parseInt(animalCode2);
							}
						}
						return 0;
					}});
				return mapList1;
			}
			
		}else if(operateType == 17){
			List<Map<String,Object>> mapList1 = sessionFactory.getCurrentSession()
			.createSQLQuery(sql1 )
			.setParameter("studyNo", studyNo)
			.setResultTransformer(new MapResultTransformer())
			.list();
			return mapList1;
		}else if(operateType == 18){
			List<Map<String,Object>> mapList2 = sessionFactory.getCurrentSession()
			.createSQLQuery(sql2 )
			.setParameter("studyNo", studyNo)
			.setResultTransformer(new MapResultTransformer())
			.list();
			return mapList2;
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnimalDeadDateTraceMapList(
			String studyNo) {
		
		String sql2 = "select CoresStudy.dbo.studyNoRemoveFN(a.studyNo) as studyNo,a.animalCode,Substring(t.oldValue,0,11) as oldDeadDate,"+
					" 	Substring(t.newValue,0,11) as deadDate,'编辑' as operate,t.operator as operater,t.modifyReason as operateRsn"+
					" 	,  convert(varchar(16),t.modifyTime,120) as operateDate ,CoresStudy.dbo.studyNoToFN(a.studyNo) as fn"+
					" from CoresUserPrivilege.dbo.tblTrace as t join CoresStudy.dbo.tblAnatomyAnimal as a"+
					" 	on t.dataId = a.id"+
					" where t.tableName = 'TblAnatomyAnimal' and CoresStudy.dbo.studyNoRemoveFN(a.studyNo) = :studyNo "+
					" order by a.animalCode";
		List<Map<String,Object>> mapList2 = sessionFactory.getCurrentSession()
		.createSQLQuery(sql2  )
		.setParameter("studyNo", studyNo)
		.setResultTransformer(new MapResultTransformer())
		.list();
		return mapList2;
	}
}
