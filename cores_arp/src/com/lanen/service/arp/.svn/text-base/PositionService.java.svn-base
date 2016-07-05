package com.lanen.service.arp;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Position;

public interface PositionService extends BaseLongDao<Position> {
	/**根据职位id得到职位名
	 * @param id
	 * @return
	 */
	String getNameById(Integer id);
	/**根据部门id查找所有的职位id和name
	 * @return
	 */
	List<Map<String, Object>> getAllPosIdName(Integer depId);
	/**获取所有职位信息根本部门id排序
	 * @return
	 */
	List<Position> getALLPosOrderByDid();
	/**检查表中职位名是否已存在
	 * @param name
	 * @return
	 */
	boolean isExistName(String name);
}
