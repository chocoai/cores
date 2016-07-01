package com.lanen.service.contract;

import java.util.Date;
import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.contract.TblContract;

public interface TblIntegratedInformService extends BaseDao<TblContract>{
	
	/**
	 * 根据开始结束时间汇总查询
	 * @param startime
	 * @param endtime
	 * @param tiCode
	 * @return
	 */
	List<?> getByStartimeAndEndtimeAndTiCodeCollectList(Date startime, Date endtime, String tiCode,String operator);

}
