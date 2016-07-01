package com.lanen.service.path;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.path.TblChipReader;

@Service
public class TblChipReaderServiceImpl extends BaseDaoImpl<TblChipReader>  implements   TblChipReaderService{

	@SuppressWarnings("unchecked")
	public Map<String, Object> findChipReaderMapByHostNameEnable(
			String hostName, int enabled) {
		Map<String,Object> map = null;
		String sql = " select chip.chipCode,conn.commName,chip.baud,chip.dataBit,chip.stopBit,chip.parit "+
					" from CoresStudy.dbo.tblChipReader as chip left join CoresStudy.dbo.tblBalConnect as conn"+
					" 	on chip.chipCode = conn.balCode and conn.type = 2"+
					" where chip.chipCode = :chipCode and conn.hostName = :hostName and conn.enabled = :enabled";
		map = (Map<String, Object>) getSession().createSQLQuery(sql)
							.setParameter("chipCode", "0001")
							.setParameter("hostName", hostName)
							.setParameter("enabled", enabled)
							.setResultTransformer(new MapResultTransformer())
							.uniqueResult();
		return map;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getStudyNoAnimalCodeMapByICardCode(
			String iCardCode) {
		Map<String,Object> map = null;
		String sql = "select card.Testcode as studyNo,card.AnimalCode as animalCode"+
					" from YYDB.dbo.TBICard as card"+
					" where card.ICardCode = :iCardCode ";
		map = (Map<String, Object>) getSession().createSQLQuery(sql)
							.setParameter("iCardCode", iCardCode)
							.setResultTransformer(new MapResultTransformer())
							.uniqueResult();
		return map;
	}

}
