package com.lanen.service.arp;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Department;

public interface DepartmentService extends BaseLongDao<Department> {
	/**根据部门id得到部门名
	 * @param id
	 * @return
	 */
	String getNameById(Integer id);
	/**查找所有的部门id和name
	 * @return
	 */
	List<Map<String, Object>> getAllDepIdName();
	/**得到所有的未被删除的部门
	 * @return
	 */
	List<Department> getAllDeps();
	/**检查部门名是否已存在
	 * @param name
	 * @return
	 */
	boolean isExistName(String name);
}
