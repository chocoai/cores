package com.lanen.service.path;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.path.TblBalReg;
/**
 * 天平登记信息
 * @author Administrator
 *
 */
@Service
public class TblBalRegServiceImpl extends BaseDaoImpl<TblBalReg>  implements  TblBalRegService{

	@SuppressWarnings("unchecked")
	public boolean isExistByBalCode(String balCode) {
		List<TblBalReg> list=getSession().createQuery("FROM TblBalReg  d WHERE  d.balCode = ? ")
		.setParameter(0, balCode)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean isExistByBalCode(String balCode, String oldbalCode) {
		List<TblBalReg> list=getSession().createQuery("FROM TblBalReg  d WHERE  d.balCode = ?  ")
		.setParameter(0, balCode)
		.list();
		if(null!=list&&list.size()>1){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean isExistBybalName(String balName) {
		List<TblBalReg> list=getSession().createQuery("FROM TblBalReg  d WHERE  d.balName = ? ")
		.setParameter(0, balName)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean isExistBybalName(String balName, String oldbalCode) {
		List<TblBalReg> list=getSession().createQuery("FROM TblBalReg  d WHERE  d.balName = ? and d.balCode != ? ")
		.setParameter(0, balName).setParameter(1, oldbalCode)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

	public List<String> getAllBalCode(String hostName) {
		String sql="select balCode from tblBalConnect where type=1 and  hostname=:hostname and enabled = 1";
		List<?> list=getSession().createSQLQuery(sql).setParameter("hostname", hostName).list();
		List<String> list2=null;
		if(list!=null&&list.size()>0){
			list2=new ArrayList<String>();
			for(Object obj:list){
				list2.add((String)obj);
			}
		}
		return list2;
	}

	public List<String> getEnableBalCode(String hostName) {
		String sql="select balCode from tblBalConnect where type=1 and  hostname=:hostname and enabled = 1 ";
		List<?> list=getSession().createSQLQuery(sql).setParameter("hostname", hostName).list();
		List<String> list2=null;
		if(list!=null&&list.size()>0){
			list2=new ArrayList<String>();
			for(Object obj:list){
				list2.add((String)obj);
			}
		}
		return list2;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> findBalMapByBalCodeHostName(String balCode,
			String hostName) {
		Map<String,Object> map = null;
		String sql = " select chip.balCode,conn.commName,chip.baud,chip.dataBit,chip.stopBit,chip.parit "+
					" from CoresStudy.dbo.tblBalReg as chip left join CoresStudy.dbo.tblBalConnect as conn"+
					" 	on chip.balCode = conn.balCode and conn.type = 1"+
					" where chip.balCode = :balCode and conn.hostName = :hostName and conn.enabled = 1";
		map = (Map<String, Object>) getSession().createSQLQuery(sql)
							.setParameter("balCode", balCode)
							.setParameter("hostName", hostName)
							.setResultTransformer(new MapResultTransformer())
							.uniqueResult();
		return map;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getNoHaveTblBalRegByHostName(String hostName) {
		
		List<Map<String,Object>> list=getSession().createSQLQuery(" select tlb.balCode from  CoresStudy.dbo.tblBalReg as tlb  "+
				" 	where balCode not in (  select tblc.balCode from  CoresStudy.dbo.tblBalConnect as tblc where tblc.hostName = ? ) ")
		.setParameter(0, hostName)
		.setResultTransformer(new MapResultTransformer())
		.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<String> getAllBalCode() {
		String sql="select balCode from tblBalReg ";
		List<String> list=getSession().createSQLQuery(sql).list();
		
		return list;
	}

	

}
