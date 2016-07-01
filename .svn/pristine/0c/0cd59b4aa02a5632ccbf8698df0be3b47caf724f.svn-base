package com.lanen.service.qa.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.User;
import com.lanen.model.qa.TblStudyFileIndex;
import com.lanen.service.qa.TblStudyFileIndexService;

@Service
public class TblStudyFileIndexServiceImpl extends BaseDaoImpl<TblStudyFileIndex> implements TblStudyFileIndexService{

	@SuppressWarnings("unchecked")
	public List<TblStudyFileIndex> getByStudyNo(String studyNo) {
		
		String hql = "from TblStudyFileIndex where studyNo=:studyNo";
		List<TblStudyFileIndex> list = getSession().createQuery(hql)
													.setString("studyNo", studyNo)
													.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public TblStudyFileIndex getByStudyNoAndFileType(String studyNo,
			Integer fileType) {
		String hql = "from TblStudyFileIndex where studyNo=:studyNo and fileType=:fileType";
		List<TblStudyFileIndex> list = getSession().createQuery(hql)
													.setString("studyNo", studyNo)
													.setInteger("fileType", fileType)
													.list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public boolean getByStudyNoAndTypeAndUser(String studyNo, Integer fileType,
			User user) {
		
		String sql = "select [disID],dis.[studyFileIndexID],dis.[studyNo] " +
				" from (" +
				"	SELECT [disID],[studyFileIndexID],[studyNo],[disTime],[reader],[readFlag],[revokeFlag],[revokeTime],[remark]" +
				"    FROM [CoresQA].[dbo].[TblStudyFileDis]" +
				"    where studyNo=:studyNo and reader=:realName" +
				"  )as dis" +
				"  left join [CoresQA].[dbo].[TblStudyFileIndex] as fileIndex" +
				"   on fileIndex.studyFileIndexID = dis.studyFileIndexID" +
				"   where fileType=:fileType and readFlag=0 ";
		
		List<TblStudyFileIndex> list = getSession().createSQLQuery(sql)
												.setParameter("studyNo", studyNo)
												.setParameter("fileType", fileType)
												.setParameter("realName", user.getRealName())
												.list();
		if(list!=null&&list.size()>0)
		{
			return true;
		}
		return false;
	}
	public int finishFileDis(String studyNo, Integer fileType, User user) {
		String sql = "update  [CoresQA].[dbo].[TblStudyFileDis] set readFlag=1" +
				" where  studyNo=:studyNo and reader=:realName and " +
				" [studyFileIndexID] =(" +
				"	SELECT [studyFileIndexID]" +
				"    FROM [CoresQA].[dbo].[TblStudyFileIndex] as fileIndex" +
				"   where studyNo=:studyNo and fileType=:fileType and readFlag=0) ";

		int i = getSession().createSQLQuery(sql)
							.setParameter("studyNo", studyNo)
							.setParameter("fileType", fileType)
							.setParameter("realName", user.getRealName())
							.executeUpdate();
		return i;
		
	}
	

}
