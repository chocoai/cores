package com.lanen.service.contract;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.contract.TblContract;
import com.lanen.model.contract.TblTestItem;
import com.lanen.model.contract.TblTestItemHis;
import com.lanen.model.contract.TblTestItem_Json;
/**
 * 供试品
 * @author 小万
 *
 */
/**
 * @author Administrator
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
	/**
	 * 判断供试品是否已签字确认
	 * @param tiNo
	 * @return
	 */
	boolean checkTestItemConfirmSign(String tiNo);
//    /**
//     * 根据Id删除供试品及以下的委托试验
//     * @param id
//     */
//	void delTestItemAndStudyItemById(String id);
	
	/**
	 * 根据开始时间结束时间，供试品类型，供试品名称查询
	 * @param startime
	 * @param endtime
	 * @param tiCode
	 * @param tiName
	 * @param readAll
	 * @param reader
	 * @return
	 */
	List<TblTestItem_Json> getByStartimeAndEndtimeAndTiCode(Date startime,Date endtime,String tiCode,String tiName,boolean readAll,String reader);
	
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

	/**根据tiNo 获得 tiCode
	 * @param tiNo
	 * @return
	 */
	String getTiCodeByTiNo(String tiNo);

	/**加载含量/浓度/纯度列表（去重）
	 * @return
	 */
	List<String> getContentList();
	/**加载含量/浓度/纯度列表（去重）标签
	 * @return
	 */
	List<String> getContentLabelList();

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
	
	
	/**
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param tiCode	供试品编码
	 * @param tiName	模糊查询名称
	 * @param isConfirm	   ""或null 全部  ,"0":未确认     ,"1":已确认
	 * @return
	 */
	List<TblTestItem_Json> getByStartimeAndEndtimeAndTiCodeConfirmFlagAndSD(
			Date startDate, Date endDate, String tiCode, String tiName,
			String isConfirm,String sd);
	/**加委托方地址，委托方联系人，电话 ，传真，报告出具放名称名称，电话等 TODO
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param tiCode	供试品编码
	 * @param tiName	模糊查询名称
	 * @param isConfirm	   ""或null 全部  ,"0":未确认     ,"1":已确认
	 * @return
	 */
	List<Map<String,Object>> getByStartimeAndEndtimeAndTiCodeConfirmFlagAndSD2(
			String startDate, String endDate, String tiCode, String tiName,
			String isConfirm,String sd);
	

	/**确认签字
	 * @param testItemIds    供试品表主 键      数据
	 * @param esId   签字
	 */
	void confirm(String[] testItemIds,String esId);
	
	/**根据合同编号,查询供试品类型(tiCode),
	 * 先看该合同下最后添加的供试品类型,若无,则选择合同客户的主要产品类型
	 * @param contractCode
	 * @return
	 */
	String getTiCodeByContractCode(String contractCode);
	
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
	
	
	/**获取留样量
	 * @param tiNo
	 * @return
	 */
	String getReserveNum(String tiNo);
	
	/**合同再编辑状态下  供试品编辑保存
	 * @param testItem
	 * @param tblTestItemHis
	 * @param realName
	 */
	void update(TblTestItem testItem, TblTestItemHis tblTestItemHis,
			String realName);
	
	/**综合里，供试品确认签字后再编辑保存
	 * @param testItem
	 * @param tblTestItemHis
	 * @param realName
	 * @param contract主要是报告出具方
	 */
	void update(TblTestItem testItem, TblTestItemHis tblTestItemHis,
			String realName,TblContract contract);
}
