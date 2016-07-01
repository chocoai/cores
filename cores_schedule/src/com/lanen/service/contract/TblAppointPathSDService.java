package com.lanen.service.contract;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.contract.TblAppointPathSD;
import com.lanen.model.contract.TblStudyItem;

public interface TblAppointPathSDService extends BaseDao<TblAppointPathSD>{
	
	/**
	 * 批量保存
	 * @param list
	 */
	void saveAll(List<TblAppointPathSD> list);
	
	/**
	 * 更新
	 * @param list
	 * @param list2
	 */
	void updateAll(List<TblAppointPathSD> list, List<TblStudyItem> list2);
	
	/**
	 * 重新任命
	 * @param list
	 * @param list2
	 * @param list3
	 */
	void updateAgainAll(List<TblAppointPathSD> list,List<TblAppointPathSD> list2,List<TblStudyItem> list3);
	
	/**
	 * 根据专题编号获得实体
	 * @param studyNo
	 * @return
	 */
	TblAppointPathSD getByStudyNo(String studyNo);

}
