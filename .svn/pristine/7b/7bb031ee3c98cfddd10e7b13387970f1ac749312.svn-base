package com.lanen.service.contract;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.jsonAndModel.HighGradeTreeGrid;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.contract.TblContract;
import com.lanen.model.contract.TblContract_Json;

/**
 * 合同信息service
 * @author 黄国刚
 *
 */
public interface TblContractService extends BaseDao<TblContract>{

	/**检查合同编号的唯一性
	 * @param contractCode
	 * @return
	 */
	boolean isExistByContractCode(String contractCode);

	/**检查合同编号的唯一性(当前id除外)
	 * @param contractCode
	 * @return
	 */
	boolean isExistByContractCode(String contractCode, String currentId);

	/**根据客户名称  模糊  查询  id,sponsorName
	 * @param customerName
	 * @param reader 读者
	 * @param readAll 是否阅读所有
	 * @return
	 */
	List<Map<String, String>> getMapListByName(String customerName,String reader,boolean readAll);

	/** 根据委托方id 查询日期区间   map 存放        {minDate: ""  ,     maxDate:""}
	 * @param sponsorId
	 * @return
	 */
	Map<String, Object> getMinMaxDateMapBySponsorId(String sponsorId);

	/**根据合同编号  查询日期区间   map 存放        {minDate: ""  ,     maxDate:""} 以及nameCombobox 的值
	 * @param contractCode
	 * @return
	 */
	Map<String, Object> getDateRowsMapByContractCode(String contractCode);

	/**根据合同编号  模糊  查询  @+contractCode,contractCode
	 * @param contractCode
	 * @param author 读者
	 * @param readAll 是否阅读所有
	 * @return
	 */
	List<Map<String, String>> getMapListByContractCode(String contractCode,String author,boolean readAll);

	/**查询   map id,contractCode  列表
	 * @param minDate
	 * @param maxDate
	 * @param contractCode
	 * @param sponsorId
	 * @param reader 读者
	 * @param readAll 是否阅读所有
	 * @return
	 */
	List<Map<String, String>> getIdCodeMapListByDateContractCodeSponsorId(
			Date minDate, Date maxDate, String contractCode, String sponsorId,String reader,boolean readAll);

	/**根据合同编号查询id
	 * @param contractCode
	 */
	String getIdByContractCode(String contractCode);
	
	/**
	 * 根据合同编号获取
	 * @param contractCode
	 * @return
	 */
	TblContract getByContractCode(String contractCode);
	
	/**加载供试品和委托项目的一些信息
	 * @param contractCode
	 * @return
	 */
	List<?> getbyContractCodetestItemAndStudyItem(String contractCode);

	/**根据id查询合同编号
	 * @param currentId
	 * @return
	 */
	String getContractCodeById(String currentId);

	/**查看该客户是否有合同
	 * @param sponsorId
	 * @return
	 */
	boolean isExistBySponsorId(String sponsorId);
	/**
	 * 按开始结束时间以及供试品类型和合同编号进行查询
	 * @param startime
	 * @param endtime
	 * @param tiCode
	 * @param contractCode
	 * @param readAll
	 * @param reader
	 * @return
	 */
	List<TblContract_Json> getByStartimeAndEndtimeAndTiCode(Date startime,Date endtime,String tiCode,String contractCode,boolean readAll,String reader);

	/**把合同，供试品，附件，委托项目状态  改为     1
	 * @param contractCode
	 */
	void updateContractState(String contractCode);
	
	void updateContractStateAndSavetblES(TblES tblES,TblESLink tblESLink,String contractCode);

	/**根据合同编号查询实体
	 * @param contractCode
	 * @return
	 */
	TblContract getByCode(String contractCode);

	/**更新合同，同时更新其对应附件，供试品，委托项目的合同编号
	 * @param tblContract
	 * @param oldContractCode
	 */
	void update(TblContract tblContract, String oldContractCode);
	/**如果客户有合同，判断当前用户是否有阅读合同的权限
	 * @param customerId
	 * @param readAll
	 * @param reader
	 * @return
	 */
	boolean isViewContractByUser(String customerId,boolean readAll,String reader);
	/**根据合同编号查询当前合同的金额单位
	 * @param contractCode
	 * @return
	 */
	Integer getPriceUnitByContractCode(String contractCode);

	/**
	 * 获取合同、供试品、委托项目 treegrid列表
	 * @param operator			//操作者
	 * @param readAllInt		//1:查看所有     0：仅自己
	 * @param contractCode		
	 * @param contractName
	 * @param sponsorName
	 * @param sponsorLinkman
	 * @param sponsorTel		//委托方  电话或手机
	 * @param tiCode			// 00:所有
	 * @param tiNo
	 * @param tiName
	 * @param studyNo
	 * @param studyName
	 * @param sd
	 * @param glpFlag			//  -1:所有     1:是   0 :否
	 * @return
	 */
	List<HighGradeTreeGrid> getHighGradeTreeGrid(String operator, int readAllInt, String contractCode, String contractName, String sponsorName, String sponsorLinkman, String sponsorTel, String tiCode, String tiNo, String tiName, String studyNo, String studyName, String sd, int glpFlag);

	void b();
	
	void c();
	
	void r();
}
