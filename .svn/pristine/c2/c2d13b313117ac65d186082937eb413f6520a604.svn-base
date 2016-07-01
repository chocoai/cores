package com.lanen.service.archive.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.listener.initServletContextListener;
import com.lanen.model.TblFileRecord;
import com.lanen.service.archive.TblFileRecordService;
@Service
public class TblFileRecordServiceImpl extends BaseDaoImpl<TblFileRecord> implements
		TblFileRecordService {

	public Integer getMaxSnByArchiveCode(String archiveCode) {
		String sql = "select max(fileRecordSn) from [CoresArchives].[dbo].[TblFileRecord] " +
				" where archiveCode=:archiveCode";
		Object maxSn = getSession().createSQLQuery(sql)
									.setParameter("archiveCode", archiveCode)
									.uniqueResult();
		if(maxSn!=null)
		{
			return (Integer)maxSn;
		}
		return 0;
	}
	@SuppressWarnings("unchecked")
	public List<TblFileRecord> getByArchiveCode(String archiveCode)
	{
		String hql = " from TblFileRecord where tblFileIndex.archiveCode=:archiveCode";
		List<TblFileRecord> list = getSession().createQuery(hql)
										.setString("archiveCode", archiveCode)
										.list();
		
		return list;
	}
	
	public String getLastFileOperateByType(Integer archiveTypeFlag)
	{
		String sql = "  select top 1 [fileOperator]  FROM [CoresArchives].[dbo].[TblFileRecord] record" +
					 "  left join  [CoresArchives].[dbo].[TblFileIndex] fileIndex on record.archiveCode =fileIndex.archiveCode" +
					 "  where archiveTypeFlag=:archiveTypeFlag " +
					 "  order by record.[operateTime] desc";
		Object str = getSession().createSQLQuery(sql)
								.setParameter("archiveTypeFlag", archiveTypeFlag)
								.uniqueResult();
		if(str!=null)
			return (String)str;
		return "";
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getLastFileOperateListByType(Integer archiveTypeFlag)
	{
		String sql = "select distinct [fileOperator] from ( "+
						"  select  top 5 [fileOperator] FROM [CoresArchives].[dbo].[TblFileRecord_SmplReserve] smpl" +
						"  left join  [CoresArchives].[dbo].[TblFileIndex] fileIndex on smpl.archiveCode =fileIndex.archiveCode" +
						"  where archiveTypeFlag=:archiveTypeFlag " +
						"  order by smpl.[operateTime] desc "+
					"  ) as a";
		List<Map<String, Object>> list = getSession().createSQLQuery(sql)
										.setParameter("archiveTypeFlag", archiveTypeFlag)
										.setResultTransformer(new MapResultTransformer())
										.list();
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getExistByCodeAndSnList(List<String> codeAndSnList) {
		Set<String> strList = new HashSet<String>();
		
		if(codeAndSnList!=null&&codeAndSnList.size()>0)
		{
			int oneTimeNum = 350;
			for(int i=0;i<=codeAndSnList.size()/oneTimeNum;i++)
			{
				String sql = "SELECT [archiveCode],[fileRecordSn] FROM [CoresArchives].[dbo].[TblFileRecord] ";
				sql += " where (";
				
				for(int j=i*oneTimeNum;j<codeAndSnList.size()&&j<(i+1)*oneTimeNum;j++)
				{
					String str = codeAndSnList.get(j);
					if(str.contains("-")&&!str.contains("(")&&!str.contains("（"))
					{
						if(str.split("-").length==2)
							str+="-001";
						if(str.split("-").length==3)
							sql+=" (archiveCode='"+str.substring(0, str.lastIndexOf("-"))+"' and fileRecordSn="+Integer.valueOf(str.substring(str.lastIndexOf("-")+1))+") or";
					}
				}
				//sql = sql.substring(0,sql.length()-3 );//去掉最后一个or
				sql += " (1=0) )";
				List<Object> list = getSession().createSQLQuery(sql).list();
				
				for(Object obj:list)
				{
					Object[] objs = (Object[])obj;
					if(objs[1]!=null&&!"".equals(objs[1]))
					{
						if((Integer)objs[1]>0&&((Integer)objs[1])<10)
						{
							objs[1]="00"+objs[1];
						}else if((Integer)objs[1]<100){
							objs[1]="0"+objs[1];
						}
					}
					strList.add(objs[0]+"-"+objs[1]);
				}
			}
		}
		
		
		
		return new ArrayList<String>(strList);
	}
}
