package com.lanen.service.contract;


import java.util.Date;
import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.contract.TblCustomer;
import com.lanen.model.contract.TblCustomer_Json;
import com.lanen.model.studyplan.DictTestItemType;

/**
 * 客户信息表service
 * @author 黄国刚
 *
 */
public interface TblCustomerService extends BaseDao<TblCustomer> {
	
	/**
	 * DictTestItemType系统表里面供试品类型
	 * @return
	 */
	List<DictTestItemType> getAllDictTestItemTypes();
	   
	/**
	 * 通过地区Id获取客户信息集合
	 * @param regionId地区Id
	 * @return
	 */
	List<TblCustomer_Json> getByRegionIdCustomerList(String regionId,boolean readAll,String reader);
	
	/**
	 * 通过地区Id获取客户信息集合
	 * @param regionId地区Id
	 * @return
	 */
	List<TblCustomer_Json> getByCustomerNameCustomerList(String name);
	
	/**
	 * 判断名称是否重复
	 * @param name
	 * @param pidString
	 * @return
	 */
	boolean  isExistByCustomerName(String name);
	/**
	 * 判断除自身外名称是否重复
	 * @param name
	 * @return
	 */
	boolean  isExistOtherCustomerName(String name,String id);
	
    /**综合查询中根据查询条件查询委托单位信息
     * @param type
     * @param start
     * @param end
     * @param name
     * @param readAll
     * @param reader
     * @return
     */
    List<TblCustomer_Json> getCustomerByCondition(String type,Date start,Date end,String name,boolean readAll,String reader);
    /**
     * 在地区表中查询地区id是否有父ID(pid)
     * */
    String getPid(String regionId);
    /**
     * 根据客户名联系人，电话或者手机查询
     * @param content
     * @return
     */
    List<TblCustomer_Json> getCustomerByNameOrIinkmanOrTelOrmobile(String content,boolean readAll,String reader);
}
