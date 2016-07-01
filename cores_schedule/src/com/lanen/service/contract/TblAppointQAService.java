package com.lanen.service.contract;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.contract.TblAppointQA;
import com.lanen.model.contract.TblStudyItem;

public interface TblAppointQAService extends BaseDao<TblAppointQA>{
	/**
	 * 批量保存
	 * @param list
	 */
	void saveAll(List<TblAppointQA> list);

	/**
	 * 更新
	 * @param list
	 * @param list2
	 */
	void updateAll(List<TblAppointQA> list, List<TblStudyItem> list2);
	/**
	 * 重新任命
	 * @param list
	 * @param list2
	 * @param list3
	 */
	void updateAgainAll(List<TblAppointQA> list,List<TblAppointQA> list2,List<TblStudyItem> list3);
	
	/**
	 * 根据专题编号获得实体
	 * @param studyNo
	 * @return
	 */
	TblAppointQA getByStudyNo(String studyNo);

}
