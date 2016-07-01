package com.lanen.service.path;

import java.util.List;

import org.springframework.stereotype.Service;


import com.lanen.base.BaseDaoImpl;
import com.lanen.model.path.TblBalConnect;
/**
 * 天平接入信息
 * @author 黄国刚
 *
 */
@Service
public class TblBalConnectServiceImpl  extends BaseDaoImpl<TblBalConnect> implements TblBalConnectService{

	@SuppressWarnings("unchecked")
	public boolean isExistByHostnameAndBalCode(String hostname,String balCode) {
		List<TblBalConnect> list=getSession().createQuery("FROM TblBalConnect  d WHERE  d.hostName = ? and d.balCode = ?")
		.setParameter(0, hostname).setParameter(1, balCode)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean isEnabledByBalCode(String balCode) {
		List<TblBalConnect> list=getSession().createQuery("FROM TblBalConnect  d WHERE d.balCode = ? and d.enabled = 1 ")
         .setParameter(0, balCode)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<TblBalConnect> findByBalCodeList(String balCode) {
		List<TblBalConnect> list=getSession().createQuery("FROM TblBalConnect  d WHERE d.balCode = ?  ")
        .setParameter(0, balCode)
		.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public boolean isEnabledByBalCode2(String balCode) {
		List<TblBalConnect> list=getSession().createQuery("FROM TblBalConnect  d WHERE d.balCode = ? and d.enabled = 1 ")
        .setParameter(0, balCode)
		.list();
		if(null!=list&&list.size()>1){
			return true;
		}
		return false;
	}

	public String getComPort(String balCode, String hostName) {
		String sql="select commName from tblBalConnect where balCode=:balCode and hostName=:hostName";
		String comPort=(String) getSession().createSQLQuery(sql)
		                           .setParameter("balCode", balCode)
		                           .setParameter("hostName", hostName)
		                           .uniqueResult();
		return comPort;
	}

	public void closedEnabledByBalCode(String balCode) {
		String sql = "  UPDATE CoresStudy.dbo.tblBalConnect SET  enabled = 0 WHERE balCode = :balCode ";
		getSession().createSQLQuery(sql)
        .setParameter("balCode", balCode)
        .executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<TblBalConnect> findByHostNameList(String hostName) {
		List<TblBalConnect> list=getSession().createQuery("FROM TblBalConnect  d WHERE d.hostName = ? and d.type = 1 ")
        .setParameter(0, hostName)
		.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblBalConnect> isHaveChipReaderByHostname(String hostname) {
		List<TblBalConnect> list= getSession().createQuery("FROM TblBalConnect  d WHERE d.hostName = ?  and d.type = 2 ")
        .setParameter(0, hostname)
		.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblBalConnect> findByHostNameList(String hostName, int isEnable) {
		List<TblBalConnect> list=getSession()
			.createQuery("FROM TblBalConnect  d WHERE d.hostName = ? and d.type = 1 and d.enabled = ?")
			.setParameter(0, hostName)
			.setParameter(1, isEnable)
			.list();
		return list;
	}


	

}
