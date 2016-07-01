package com.lanen.service.qa.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.User;
import com.lanen.model.qa.QAChkRecord;
import com.lanen.model.qa.TblStudyFileDis;
import com.lanen.service.qa.TblStudyFileDisService;
@Service
public class TblStudyFileDisServiceImpl extends BaseDaoImpl<TblStudyFileDis> implements TblStudyFileDisService{

	@SuppressWarnings("unchecked")
	public boolean isExistNoFinishByUser(User user) {
		boolean flag = false;
		String sql="SELECT [disID],[studyFileIndexID],[studyNo],[disTime],[reader]  ,[readFlag],[revokeFlag],[revokeTime],[remark]" +
				"  FROM [CoresQA].[dbo].[TblStudyFileDis]" +
				"  where reader= :realName and readFlag=0 ";
		
		List<QAChkRecord> list = getSession().createSQLQuery(sql)
											.setParameter("realName", user.getRealName())
											.list();
		
		if(list!=null&&list.size()>0)
		{
			flag = true;
		}
		return flag;
	
	}
	@SuppressWarnings("unchecked")
	public TblStudyFileDis getNoFinishByStudyNoAndUser(String studyNo,User user)
	{
		String sql="  FROM TblStudyFileDis" +
					"  where studyNo=:studyNo and reader= :realName and readFlag=0 ";
		
		List<TblStudyFileDis> list = getSession().createQuery(sql)
											.setString("studyNo", studyNo)
											.setString("realName", user.getRealName())
											.list();
		
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
		
	}
	@SuppressWarnings("unchecked")
	public List<User> getByStudyNoAndUser(String studyNo,List<User> users)
	{
		List<String> userRealNameList = new ArrayList<String>();
		for(User user:users)
		{
			userRealNameList.add(user.getRealName());
		}
		
		String sql="  SELECT [reader]	FROM [CoresQA].[dbo].[TblStudyFileDis]" +
				"  where studyNo=:studyNo and reader in (:realNameList)  ";

		List<String> existRealName = getSession().createSQLQuery(sql)
										.setString("studyNo", studyNo)
										.setParameterList("realNameList",userRealNameList)
										.list();
		
		List<User> existUser = new ArrayList<User>();
		for(User user:users)
		{
			if(existRealName.contains(user.getRealName()))
			{
				existUser.add(user);
			}
		}
		return existUser;
		
	}

}
