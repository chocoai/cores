package com.lanen.service.archive.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.TblFileIndex;
import com.lanen.service.archive.TblFileIndexService;
@Service
public class TblFileIndexServiceImpl extends BaseDaoImpl<TblFileIndex>
		implements TblFileIndexService {

	
	@SuppressWarnings("unchecked")
	public boolean isExistArchiveCode(String archiveCode) {
		String hql = "from TblFileIndex where archiveCode=:archiveCode ";
		List<TblFileIndex> list = getSession().createQuery(hql)
												.setString("archiveCode", archiveCode)
												.list();
		if(list!=null&&list.size()>0)
		{
			return true;
		}
		
		return false;
	}
	@SuppressWarnings("unchecked")
	public String getMaxCodeByTypeCode(String archiveTypeCode) {
		String hql = "SELECT [archiveCode]FROM [CoresArchives].[dbo].[TblFileIndex]" +
				" where archiveTypeCode=:archiveTypeCode" ;
		List<String> list = getSession().createSQLQuery(hql)
										.setParameter("archiveTypeCode", archiveTypeCode)
										.list();
		if(list!=null&&list.size()>0)
		{
			//获取最大的code
			Collections.sort(list, new Comparator<String>() {

				public int compare(String arg0, String arg1) {
					if(arg0.length()>1&&arg1.length()>1)
					{
						if(Character.isDigit(arg0.charAt(arg0.length()-1))&&Character.isDigit(arg1.charAt(arg1.length()-1)))
						{
							String[] array1 = arg0.split("[\\D]+");
							String[] array2 = arg1.split("[\\D]+");
							
							return Integer.parseInt(array2[array2.length-1])-Integer.parseInt(array1[array1.length-1]);
							
						}else if(!Character.isDigit(arg0.charAt(arg0.length()-1))&&Character.isDigit(arg1.charAt(arg1.length()-1))){
							return -1;
						}else if(Character.isDigit(arg0.charAt(arg0.length()-1))&&!Character.isDigit(arg1.charAt(arg1.length()-1))){
							return 1;
						}
					}
					
					return 0;
				}
			});
			
			return list.get(0);
		}else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getStudyRecordByPosition(String storePosition,String archiveCode) {
		String hql = "select archiveCode from [CoresArchives].[dbo].[TblFileIndex] where storePosition=:storePosition " ;
		if(archiveCode!=null&&!"".equals(archiveCode))
		{
			hql += " and (archiveCode is null or archiveCode!=:archiveCode) ";
		}
		Query query = getSession().createSQLQuery(hql)
								.setParameter("storePosition", storePosition);
		if(archiveCode!=null&&!"".equals(archiveCode))
		{
			query.setParameter("archiveCode", archiveCode);
		}
		List<String> list = query.list();
		if(list!=null&&list.size()>0)
		{
			return list;
		}else {
			return null;
		}
	}
}
