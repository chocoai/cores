package com.lanen.service.qa.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.DictQACheckContentTable;
import com.lanen.model.qa.DictQACheckTable;
import com.lanen.model.schedule.TblAnimalHouse;
import com.lanen.service.qa.DictQACheckContentTableService;
import com.lanen.service.qa.DictQACheckTableService;
@Service
public class DictQACheckContentTableServiceImpl extends BaseDaoImpl<DictQACheckContentTable> implements	DictQACheckContentTableService {

	@Resource
	private DictQACheckTableService dictQACheckTableService;
	
	@SuppressWarnings("unchecked")
	public List<DictQACheckContentTable> getByChkTblId(String chkTblId) {
		String hql = "FROM DictQACheckContentTable  d WHERE  d.dictQacheckTable.chkTblId = ?  ";
		List<DictQACheckContentTable> list=getSession().createQuery(hql)
											.setString(0, chkTblId)
											.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public Integer getMaxSnByChkTblId(String chkTblId) {
		String hql = "FROM DictQACheckContentTable  d WHERE  d.dictQacheckTable.chkTblId = ?  order by d.sn desc";
		List<DictQACheckContentTable> list=getSession().createQuery(hql)
									.setString(0, chkTblId)
									.list();
		Integer maxSn = 0;
		if(list!=null&&list.size()>0)
		{
			maxSn = list.get(0).getSn();
		}
		return maxSn;
	}
	@SuppressWarnings("unchecked")
	public boolean isExistChkTblAndContent(String chkTblId, String chkContent) {
		String hql = "FROM DictQACheckContentTable  d WHERE  d.dictQacheckTable.chkTblId = ? and d.chkContent=?";
		List<DictQACheckContentTable> list=getSession().createQuery(hql)
									.setString(0, chkTblId)
									.setString(1,chkContent)
									.list();
	
		if(list!=null&&list.size()>0)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * 获取表里面有多少条内容
	 * @param chkTblId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer getSizeByTable(String chkTblId)
	{
		String sql="SELECT count([chkTblContentID]) FROM [CoresSystemSet].[dbo].[DictQACheckContentTable]" +
					"  where chkTblID=:chkTblID";
		List<Integer> count = getSession().createSQLQuery(sql)
								.setParameter("chkTblID", chkTblId)
								.list();
		if(count!=null&&count.size()>0)
			return count.get(0);
		else
			return 0;
	}
	
public DictQACheckTableService getDictQACheckTableService() {
	return dictQACheckTableService;
}
public void setDictQACheckTableService(
		DictQACheckTableService dictQACheckTableService) {
	this.dictQACheckTableService = dictQACheckTableService;
}

	

}
