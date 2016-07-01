package com.lanen.service.qa.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.DictQACheckTable;
import com.lanen.service.qa.DictQACheckTableService;
@Service
public class DictQACheckTableServiceImpl extends BaseDaoImpl<DictQACheckTable> implements DictQACheckTableService {

	private static final long serialVersionUID = 5680675692828253023L;
	
	@SuppressWarnings("unchecked")
	public List<DictQACheckTable> getByChkTblCodeAndName(String chkTblCode,
			String chkTblName) {
		String hql = "from DictQACheckTable where chkTblCode like ? and chkTblName like ? order by chkTblCode";
		List<DictQACheckTable> list = getSession().createQuery(hql)
									.setString(0,"%"+chkTblCode+"%")
									.setString(1,"%"+chkTblName+"%")
									.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public boolean isExistByCodeAndName(String chkTblCode, String chkTblName) {
		String hql = "from DictQACheckTable where chkTblCode=? or chkTblName= ?";
		List<DictQACheckTable> list = getSession().createQuery(hql)
									.setString(0,chkTblCode)
									.setString(1,chkTblName)
									.list();
		if(list!=null&&list.size()>0)
		{
			return true;
		}else {
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	public boolean isExistByCodeAndNameExceptOne(String chkTblCode,
			String chkTblName, String chkTblId) {
		String hql = "from DictQACheckTable where (chkTblCode=? or chkTblName= ?) and chkTblId!=?";
		List<DictQACheckTable> list = getSession().createQuery(hql)
									.setString(0,chkTblCode)
									.setString(1,chkTblName)
									.setString(2, chkTblId)
									.list();
		if(list!=null&&list.size()>0)
		{
			return true;
		}else {
			return false;
		}
	}

	
	
	
}
