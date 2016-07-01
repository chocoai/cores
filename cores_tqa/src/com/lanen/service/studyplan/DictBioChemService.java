package com.lanen.service.studyplan;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.studyplan.DictBioChem;

public interface DictBioChemService extends BaseDao<DictBioChem>{

	/**
	 * 查询所有列表
	 * @return
	 */
	List<DictBioChem> getAll();

	/**
	 * 判断名称是否存在
	 * @param name
	 * @return
	 */
	boolean isExistByName(String name);
	/**
	 * 获得      指标缩写，排序   对应map
	 * @return
	 */
	Map<String, Integer> getMap();

	/**
	 * 获得      指标缩写，单位   对应map
	 * @return
	 */
	Map<String, String> geUnittMap();

	/**
	 * 更具检验指标缩写得到名称
	 * @param indexAbbr
	 * @return
	 */
	String getIndex2ByAbbr(String indexAbbr);

	/**
	 * 设置顺序
	 * @param orderNoPara
	 * @param orderNoNext
	 */
	void moveOeder(int orderNoPara, int orderNoNext);
	
	/**
	 * 获取下一个排序号
	 * @return
	 */
	int getNextOrderNo();
	/**
	 * 查询所有并排序（升序）
	 * @return
	 */
	List<DictBioChem> findAllOrderByOrderNo();

	/**
	 * 判断缩写是否存在
	 * @param name
	 * @return
	 */
	boolean isExistByAbbr(String abbr);

	/**
	 * 判断缩写是否存在(自己除外)
	 * @param name
	 * @return
	 */
	boolean isExistByNameAbbr(String name, String abbr);
	/**
	 * 通过唯一的排序号查找实体
	 * @param orderNo
	 * @return
	 */
	DictBioChem getByOrderNo(int orderNo); 
	
	/**根据 指标缩写  得到指标单位
	 * @param indexAbbr
	 * @return
	 */
	String getIndexUnit(String indexAbbr);
}
