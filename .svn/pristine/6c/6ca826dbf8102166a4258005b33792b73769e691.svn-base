package com.lanen.service.schdeule;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.schedule.TblAnimalHouse;

public interface TblAnimalHouseService extends BaseDao<TblAnimalHouse>{

	/**
	 * 查询所有列表
	 * @return
	 */
	List<TblAnimalHouse> getAll();
	
	/**
	 * 判断名称是否存在
	 * @param name
	 * @return
	 */
	boolean isExistByResName(String name);
	
	/**
	 * 判断统一父级下名称是否重复
	 * @param name
	 * @param pidString
	 * @return
	 */
	boolean  isExistByResNameAndPid(String name,String pid);
	/**
	 * 根据类型查询
	 * @return
	 */
	List<TblAnimalHouse> getresKind(int resKind);
	//获得相同父类的list集合
	List<TblAnimalHouse>  getParentId(String id);
	
	/**
	 * 更新
	 * @param list
	 */
	void updateAll(List<TblAnimalHouse> list);
	
	/**
	 * 获取下一个顺序号
	 */
	int getNextOrderNo();
	
   /**
    * 判断是否给引用
    * @param id
    * @return
    */
	boolean referencedRid(String id);
	
}
