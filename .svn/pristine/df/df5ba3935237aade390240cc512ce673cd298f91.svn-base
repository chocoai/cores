package com.lanen.service.studyplan;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.studyplan.DictDoseUnit;

public interface DictDoseUnitService extends BaseDao<DictDoseUnit>{
	/**
	 * 查询所有列表
	 * @return
	 */
	List<DictDoseUnit> getAll();
	
	/**
	 * 查询所有列表
	 * @return
	 */
	List<DictDoseUnit> getAllorderNo();
	
	/**
	 * 判断名称是否存在
	 * @param name
	 * @return
	 */
	boolean isExistByName(String name);

	/**
	 * 判断缩写是否存在
	 * @param name
	 * @return
	 */
	boolean isExistByAbbr(String abbr);
	/**
	 * 获取下一个排序号
	 * @return
	 */
	int getNextOrderNo();

	/**
	 * 判断缩写是否存在(自己除外)
	 * @param name
	 * @return
	 */
	boolean isExistByNameAbbr(String name, String abbr);
	/**
	 * 设置顺序
	 * @param orderNoPara
	 * @param orderNoNext
	 */
	void moveOeder(int orderNoPara, int orderNoNext);
	/**
	 * 通过唯一的排序号查找实体
	 * @param orderNo
	 * @return
	 */
	DictDoseUnit getByOrderNo(int orderNoPara);

}
