package com.lanen.service.path;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.path.DictViscera;

/**
 * 
 * 脏器字典     service
 * @author 黄国刚
 *
 */
public interface DictVisceraService extends BaseDao<DictViscera>{

	/**
	 * 查询脏器列表,以treeData需要展示(包括_parentId,state:'','open','closed')
	 * @return
	 */
	List<Map<String, Object>> queryDataMapList();

	/**查询脏器类别列表(from dictVisceraType)
	 * @return
	 */
	List<Map<String, Object>> queryVisceraTypeDataMapList();

	/**脏器名称是否存在
	 * @param visceraName
	 * @return
	 */
	boolean isExistByVisceraName(String visceraName);

	/**脏器名称是否存在(出visceraCode脏器外)
	 * @param visceraName
	 * @param visceraCode
	 * @return
	 */
	boolean isExistByVisceraName(String visceraName, String visceraCode);
	/**
	 * 查询 动物种类列表
	 * @return
	 */
	List<Map<String, Object>> queryAnimalTypeDataMapList();

	/**获取下一个SN
	 * @return
	 */
	Integer getNextSn();

	/**添加一个对象
	 * @param dictViscera
	 */
	void addOne(DictViscera dictViscera);

	/**更新一级脏器,同时对于二级的动物类别与脏器类别进行更新
	 * @param dictViscera
	 */
	void updateOne(DictViscera dictViscera);

	/**更新二级脏器
	 * @param dictViscera
	 */
	void updateSon(DictViscera dictViscera);

	/**删除脏器及其子脏器
	 * @param visceraCode
	 */
	void delOne(String visceraCode);

	/**上移(一级脏器)
	 * @param sn  序号
	 */
	boolean upOneBySn(Integer sn);
	
	/**下移(一级脏器)
	 * @param sn  序号
	 */
	boolean downOneBySn(Integer sn);


	/**
	 * 根据序号,获得下一个序号对应  对象(一级脏器)
	 * @param sn
	 * @return
	 */
	DictViscera getNextOneBySn(Integer sn);
	/**
	 * 根据序号,获得序号对应  对象(一/二级脏器)
	 * @param sn
	 * @return
	 */
	DictViscera getOneBySn(Integer sn);
	
	/**
	 * 根据序号,获得上一个序号对应  对象(一级脏器)
	 * @param sn
	 * @return
	 */
	DictViscera getPrevOneBySn(Integer sn);
	
	/**上移(二级脏器)
	 * @param sn  序号
	 * * @param pvisceraCode
	 */
	boolean upOneBySnPvisceraCode(Integer sn,String pvisceraCode);
	
	/**下移(二级脏器)
	 * @param sn  序号
	 * * @param pvisceraCode
	 */
	boolean downOneBySnPvisceraCode(Integer sn,String pvisceraCode);
	
	
	/**
	 * 根据序号\父节点,获得下一个序号对应  对象(二级脏器)
	 * @param sn
	 * * @param pvisceraCode
	 * @return
	 */
	DictViscera getNextOneBySnPvisceraCode(Integer sn,String pvisceraCode);
	
	/**
	 * 根据序号\父节点,获得上一个序号对应  对象(二级脏器)
	 * @param sn
	 * @param pvisceraCode
	 * @return
	 */
	DictViscera getPrevOneBySnPvisceraCode(Integer sn,String pvisceraCode);

	/**查询所有一级脏器,code,name
	 * @return
	 */
	List<Map<String,Object>> query1LCodeNameList();
	/**根据动物种类查询所有该种类特有以及动物都有的脏器
	 * @param AnimalType
	 * @return
	 */
	List<DictViscera> get1LListByAnimalType(String animalType);

	/**根据脏器名查询实体
	 * @param visceraName 
	 * @return
	 */
	DictViscera getByVisceraName(String visceraName);
	
	List<DictViscera> getAllOrderBySnFixed();
}
