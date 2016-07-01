package com.lanen.service.contract;

import java.util.Date;
import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.contract.TblContract;
import com.lanen.model.contract.TblTestItem;
import com.lanen.model.contract.TblTestItem_Json;
/**
 * 供试品
 * @author 小万
 *
 */
public interface TblTestItemService extends BaseDao<TblTestItem>{
	/**
	 * 判断供试品编号是否重复
	 * @return
	 */
	boolean isExistByTestItemtiNo(String tiNo);
	/**
	 * 
	 * @param tiNo
	 * @return
	 */
	List<TblTestItem> getConfirmedByTiNo(String tiNo);
//    /**
//     * 根据Id删除供试品及以下的委托试验
//     * @param id
//     */
//	void delTestItemAndStudyItemById(String id);
	
	
	/**根据合同编号，查询供试品列表
	 * @param contractCode
	 * @return
	 */
	List<TblTestItem> getListByContract(String contractCode);
	
	/**
	 * 根据合同编号和供试品编号查询
	 * @param contractCode
	 * @param tiNo
	 * @return
	 */
	String getTiNameByContractAndTiNo(String contractCode,String tiNo);
	
	/**加载含量/浓度/纯度列表（去重）
	 * @return
	 */
	List<String> getContentList();
	
	/**加载外观列表（去重）
	 * @return
	 */
	List<String> getPhysicalList();

	/**加载存储条件列表（去重）
	 * @return
	 */
	List<String> getStorageConditionList();


	/**
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param tiCode	供试品编码
	 * @param tiName	模糊查询名称
	 * @param isConfirm	   ""或null 全部  ,"0":未确认     ,"1":已确认
	 * @return
	 */
	List<TblTestItem_Json> getByStartimeAndEndtimeAndTiCodeConfirmFlag(
			Date startDate, Date endDate, String tiCode, String tiName,
			String isConfirm);


	/**确认签字
	 * @param testItemIds    供试品表主 键      数据
	 * @param esId   签字
	 */
	void confirm(String[] testItemIds,String esId);
	
	/**根据tiNo 获得 tiCode
	 * @param tiNo
	 * @return
	 */
	String getTiCodeByTiNo(String tiNo);
	
	
	/**加载    稳定性与均一性分析    列表（去重）
	 * @return
	 */
	List<String> getAnalysisList();
	/**加载   样品检测后处理（去重）
	 * @return
	 */
	List<String> getPostTreatmentList();


	/**加载   留样量（去重）
	 * @return
	 */
	List<String> getReserveUnitList();
	
	/**
	 * 更新供试品，和合同的报告出具方
	 * @param testItem
	 * @param contract
	 */
	void update(TblTestItem testItem,TblContract contract); 
}
