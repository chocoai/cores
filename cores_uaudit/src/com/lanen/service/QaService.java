package com.lanen.service;

import java.util.List;
import java.util.Map;

/**Qa管理Service
 * @author Administrator
 *
 */
public interface QaService  {

	/**
	 * @param studyNo
	 * @param operateType
	 * @return
	 */
	List<Map<String, Object>> getList(String studyNo, int operateType);

}
