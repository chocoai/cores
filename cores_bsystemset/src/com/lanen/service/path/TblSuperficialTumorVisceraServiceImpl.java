package com.lanen.service.path;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.path.TblSuperficialTumorViscera;

/**浅表肿瘤脏器登记表 service
 * @author Administrator
 *
 */
@Service
public class TblSuperficialTumorVisceraServiceImpl extends BaseDaoImpl<TblSuperficialTumorViscera> implements TblSuperficialTumorVisceraService{

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSuperficialTumorVisceraList() {
		String sql = "select su.visceraCode,su.visceraName"+
					" from CoresSystemSet.dbo.tblSuperficialTumorViscera as su "+
					" 	inner join CoresSystemSet.dbo.dictViscera as v on v.level = 1 and su.visceraCode = v.visceraCode"+
					" order by v.sn";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getNoSuperficialTumorVisceraList() {
		String sql = "select v.visceraCode,v.visceraName"+
		" from CoresSystemSet.dbo.dictViscera  as v"+
		" 	left join  CoresSystemSet.dbo.tblSuperficialTumorViscera as su on v.level = 1 and su.visceraCode = v.visceraCode"+
		" where v.level = 1 and su.visceraCode is null"+
		" order by v.sn";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}
	
	public boolean isExistByVisceraCode(String visceraCode) {
		String sql = "select count(*)"+
					" from CoresSystemSet.dbo.tblSuperficialTumorViscera as su "+
					" where su.visceraCode = ? ";
		Integer count = (Integer) getSession().createSQLQuery(sql).setParameter(0, visceraCode).uniqueResult();
		return count > 0;
	}

	
}
