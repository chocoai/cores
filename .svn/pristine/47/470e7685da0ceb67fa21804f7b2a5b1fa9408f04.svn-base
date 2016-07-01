package com.lanen.service.clinicaltest;

import java.util.List;
import java.util.Map;



/**临检审计信息查询
 * @author Administrator
 *
 */
public interface StudyService {

	/**查询所有课题的年份列表（降序）
	 * @return
	 */
	List<String> getYearList();

	
	/**对应年份，对应供试品类型的，专题列表
	 * @param year
	 * @param testItemType
	 * @return
	 */
	List<Map<String, Object>> getStudyNoMapListByYear(String year,String testItemType);

	
}
