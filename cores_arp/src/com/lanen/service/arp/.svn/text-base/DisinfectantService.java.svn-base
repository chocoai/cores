package com.lanen.service.arp;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Disinfectant;

public interface DisinfectantService extends BaseLongDao<Disinfectant> {

	/**查找所有的消毒液编号和ID
	 * @return
	 */
	List<Map<String, Object>> getAllRoomIdName();
   
    /**获取所有的消毒液列表并根据配制时间倒序排列（最新配制的在前）
     * @return
     */
    List<Disinfectant> getALLbyCreateDate();

	/**检查消毒液编号是否已存在
	 * @param disinfectantCode
	 * @return
	 */
	boolean isExistName(String disinfectantCode);
}
