package com.lanen.service.arp;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Title;

public interface TitleService extends BaseLongDao<Title> {
	/**获取所有的职称名和ID
	 * @return
	 */
	List<Map<String, Object>> getAllTitIdName();

	/**根据职称id获取职称名
	 * @param zhic
	 * @return
	 */
	String getNameById(Integer zhic);

	/**查找所有未删除的职称信息
	 * @return
	 */
	List<Title> getAllTits();

	/**检查职称名是否已存在
	 * @param name
	 * @return
	 */
	boolean isExistName(String name);
}
