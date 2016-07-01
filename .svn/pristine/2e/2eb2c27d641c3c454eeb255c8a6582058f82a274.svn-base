package com.lanen.service.clinicaltest;

import java.util.List;
import java.util.Map;



/**临检审计信息查询
 * @author Administrator
 *
 */
public interface ClinicalTestService {

	/**获取有临检数据的专题对应的年份列表,降序排列
	 * @return
	 */
	List<String> getYearList();

	/**查询有临检数据的专题编号列表（对应年份）
	 * @param year
	 * @return
	 */
	List<Map<String, Object>> getStudyNoMapListByYear(String year);

	/**查询临检重测数据
	 * @param studyNo
	 * @return
	 */
	List<Map<String, Object>> getClinicalTestData(String studyNo);

	/**查询临检数据的删除痕迹
	 * @param studyNo
	 * @return
	 */
	List<Map<String, Object>> getDelTrace(String studyNo);

	/**查询临检数据的修改痕迹
	 * @param studyNo
	 * @return
	 */
	List<Map<String, Object>> getEditTrace(String studyNo);
	
}
