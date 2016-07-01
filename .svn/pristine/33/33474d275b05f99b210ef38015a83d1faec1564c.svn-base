package com.lanen.service;

import java.util.List;
import java.util.Map;


/**委托合同service
 * @author Administrator
 *
 */
public interface ContractService {

	/**查询所有合同提交日期年份列表（去重）
	 * @return
	 */
	List<String> getYearList();

	/**查询对应年份合同编号列表
	 * @param year
	 * @return
	 */
	List<Map<String, Object>> getContractMapListByYear(String year);

	/**查询合同编辑记录列表
	 * @param contractCode
	 * @return
	 */
	List<Map<String, Object>> getContractList(String contractCode);

	/**查询供试品编辑记录列表
	 * @param contractCode
	 * @return
	 */
	List<Map<String, Object>> getTestitemList(String contractCode);

	/**查询委托项目编辑记录列表
	 * @param contractCode
	 * @return
	 */
	List<Map<String, Object>> getStudyitemList(String contractCode);
	
	

}
