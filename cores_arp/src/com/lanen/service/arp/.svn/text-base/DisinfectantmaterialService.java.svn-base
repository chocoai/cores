package com.lanen.service.arp;

import java.util.List;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Disinfectantmaterial;

public interface DisinfectantmaterialService extends
		BaseLongDao<Disinfectantmaterial> {

	/**根据所属消毒液ID获得消毒液配方记录
	 * @param disinfectantId 
	 * @return
	 */
	List<Disinfectantmaterial> getListByDisinfectantId(Long disinfectantId);

	/**按照消毒液ID删除配方表数据
	 * @param id
	 */
	void deleteByDisinfectantId(Long id);

}
