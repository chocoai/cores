package com.lanen.service;

import java.util.List;
import java.util.Map;

/**一毒理系统service
 * @author Administrator
 *
 */
public interface TblYYDBService {
	
	/**查询一般毒理系统日期
	 * @param operatType
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	List<Map<String, Object>> getYYDBLogList(String operatType,
			String beginDate, String endDate);

	/**查询专题操作日志
	 * @param studyNo
	 * @param operateType
	 * @return
	 */
	List<Map<String, Object>> getStudyLogList(String studyNo, String operateType);

	/**加载数据修改跟踪日志
	 * @param studyNo
	 * @return
	 */
	List<Map<String, Object>> loadDataTrace(String studyNo);

}
