package com.lanen.service.schdeule;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.schedule.TblAttachment;
import com.lanen.model.schedule.TblAttachmentIndex;

@Service
public class TblAttachmentIndexServiceImpl extends BaseDaoImpl<TblAttachmentIndex> implements TblAttachmentIndexService {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAllCreaterMapList() {
		String sql = "SELECT distinct  att.creater as id,u.realName as text "+
					" FROM     CoresSchedule.dbo.tblAttachmentIndex as att left join"+
					" CoresUserPrivilege.dbo.tbluser as u on att.creater = u.userName";
		List<Map<String,Object>> list = getSession().createSQLQuery(sql)
													.setResultTransformer(new MapResultTransformer())
													.list();
		
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListByDateCreaterStateStudyNoDescribe(
			Date startDate, Date endDate, String creater, int state,
			String condition) {
		if(null != startDate && null != endDate){
			
			String sql = "SELECT att.id, att.studyNo, att.describe, convert(varchar(10)" +
			",att.createTime,120) as createTime, att.state, att.remark,u.realName as creater"+
			" FROM     CoresSchedule.dbo.tblAttachmentIndex as att left join"+
			" CoresUserPrivilege.dbo.tbluser as u on att.creater = u.userName"+
			" where (convert(date,att.createTime,120) between :startDate and :endDate "+
			" or convert(date,att.createTime,120) between :endDate and :startDate )"+
			" and (:state = -1 or att.state = :state)"+
			" and (:creater = '' or att.creater = :creater)"+
			" and(( (case when att.studyNo is null then '' else att.studyNo end)  like :condition or :condition is null or :condition = '' ) "+
			" or ( (case when att.describe is null then '' else att.describe end) like :condition or :condition is null or :condition = '' ) )" +
			" order by att.createTime desc";
			
			if(null != condition ){
				condition = condition.trim();
			}else{
				condition = "";
			}
			
			List<Map<String,Object>> list = getSession().createSQLQuery(sql)
														.setParameter("startDate", startDate)
														.setParameter("endDate", endDate)
														.setParameter("creater", creater)
														.setParameter("state", state)
														.setParameter("condition", "%"+condition+"%")
														.setResultTransformer(new MapResultTransformer())
														.list();
			return list;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListById(String id) {
		String sql = "select att.state, att.id,convert(varchar(16),attIndex.createTime,120) as createTime ,u.realName as creater ,att.fileName,"+
					" att.printNum,u2.realName as printer,convert(varchar(16),att.finishTime,120) as finishTime,att.fileUrl,att.realFileName"+
					" from CoresSchedule.dbo.tblAttachmentIndex as attIndex "+
					" left join CoresUserPrivilege.dbo.tbluser as u on attIndex.creater = u.userName"+
					" left join CoresSchedule.dbo.tblAttachment as att on attIndex.id = att.indexId " +
					" left join CoresUserPrivilege.dbo.tbluser as u2 on att.printer = u2.userName"+
					" where attIndex.id = :id ";
		List<Map<String,Object>> list = getSession().createSQLQuery(sql)
													.setParameter("id", id)
													.setResultTransformer(new MapResultTransformer())
													.list();
		return list;
	}

	public TblAttachment getTblAttachmentById(String id) {
		if(id==null||id.length()<1){
			return null;
		}
		return (TblAttachment) getSession().get(TblAttachment.class, id);
	}

	

	public void save(TblAttachmentIndex tblAttachmentIndex,
			List<TblAttachment> tblAttachmentList) {
		if(null != tblAttachmentIndex){
			getSession().save(tblAttachmentIndex);
			for(TblAttachment obj:tblAttachmentList){
				getSession().save(obj);
			}
		}
	}

	public boolean isUncommittedById(String id) {
		String sql = "select count(atti.id)"+
					" from CoresSchedule.dbo.tblAttachmentIndex as atti"+
					" left join CoresSchedule.dbo.tblAttachment as att "+
					" on atti.id = att.indexId "+
					" where atti.id = :id and (atti.state >0 or att.state >0)";
		Integer count = (Integer) getSession().createSQLQuery(sql)
									.setParameter("id", id)
									.uniqueResult();
		return count < 1;
	}

	public void cancelById(String id) {
		String sql ="update CoresSchedule.dbo.tblAttachmentIndex"+
					" set state = 2"+
					" where id = :id ";
		getSession().createSQLQuery(sql)
					.setParameter("id", id)
					.executeUpdate();
		String sql2 ="update CoresSchedule.dbo.tblAttachment"+
					" set state = 2"+
					" where indexId = :id ";
		getSession().createSQLQuery(sql2)
					.setParameter("id", id)
					.executeUpdate();
		
	}

	public void signHasPrintById(String id, String userName) {
		String sql ="update CoresSchedule.dbo.tblAttachmentIndex"+
					" set state = 1"+
					" where id = :id ";
		getSession().createSQLQuery(sql)
					.setParameter("id", id)
					.executeUpdate();
		
		String sql2 ="update CoresSchedule.dbo.tblAttachment"+
					" set state = 1 ,printer = :printer,finishTime = :finishTime"+
					" where indexId = :id and state = 0";
			getSession().createSQLQuery(sql2)
					.setParameter("id", id)
					.setParameter("printer", userName)
					.setParameter("finishTime", new Date())
					.executeUpdate();
		
	}

	public boolean isHasUncommittedById(String id) {
		String sql = "select count(atti.id)"+
					" from CoresSchedule.dbo.tblAttachmentIndex as atti"+
					" left join CoresSchedule.dbo.tblAttachment as att "+
					" on atti.id = att.indexId "+
					" where atti.id = :id and atti.state =0 and att.state = 0";
			Integer count = (Integer) getSession().createSQLQuery(sql)
									.setParameter("id", id)
									.uniqueResult();
			return count > 0;
	}

	public void signHasPrintOneById(String id, String userName,String indexId) {
		//1.当前附件状态改为   1，及printer,finishTime
		String sql ="update CoresSchedule.dbo.tblAttachment"+
					" set state = 1 ,printer = :printer,finishTime = :finishTime"+
					" where id = :id and state = 0";
		getSession().createSQLQuery(sql)
					.setParameter("id", id)
					.setParameter("printer", userName)
					.setParameter("finishTime", new Date())
					.executeUpdate();
		//2.判断 该索引下的其他附件状态  不为1 的有没有
		String sql2 ="SELECT   COUNT(id) AS Expr1"+
					" FROM      tblAttachment"+
					" WHERE   (state <> 1) AND (id <> :id) AND (indexId = :indexId)";
		Integer count = (Integer) getSession().createSQLQuery(sql2)
					.setParameter("id", id)
					.setParameter("indexId", indexId)
					.uniqueResult();
		if(count < 1){
			//3.都已打印，则索引标记为已打印
			String sql3 ="update CoresSchedule.dbo.tblAttachmentIndex"+
						" set state = 1"+
						" where id = :id ";
			getSession().createSQLQuery(sql3)
						.setParameter("id", indexId)
						.executeUpdate();
		}
		
	}

	public boolean isNoCancelById(String id) {
		TblAttachment tblAttachment = getTblAttachmentById(id);
		if(null != tblAttachment){
			return tblAttachment.getState() < 2;
		}
		return false;
	}
	
	public String getRootDirectory() {
		String sql = "select rd.rootDirectory "+
					" from CoresSystemSet.dbo.dictAttachmentRootDirectory as rd "+
					" where rd.id = '1'";
		String rootDirectory = (String) getSession().createSQLQuery(sql).uniqueResult();
		return rootDirectory;
	}

	public String getSmbRootDirectory() {
		String sql = "select rd.rootDirectory "+
					" from CoresSystemSet.dbo.dictAttachmentRootDirectory as rd "+
					" where rd.id = ? ";
		String rootDirectory = (String) getSession().createSQLQuery(sql)
													.setParameter(0, 1)
													.uniqueResult();
		String userName = (String) getSession().createSQLQuery(sql)
												.setParameter(0, 2)
												.uniqueResult();
		String password = (String) getSession().createSQLQuery(sql)
												.setParameter(0, 3)
												.uniqueResult();
		if(null != rootDirectory && !"".equals(rootDirectory) &&
			null != userName && !"".equals(userName) &&
			null != password && !"".equals(password) ){
			//smb://xxx:xxx@192.168.2.188/testIndex
			String smbRootDirectory = "smb://"+userName+":"+password+"@"+rootDirectory;
			return smbRootDirectory;
		}
			
		return null;
	}

}
