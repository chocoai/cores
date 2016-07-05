package com.lanen.service.arp;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Leavebreast;
import com.lanen.model.LeavebreastErport;

/**
 * 离乳登记  service
 * @author Administrator
 */
public interface LeavebreastService extends BaseLongDao<Leavebreast> {

	/**根据条件加载离乳登记记录，并分页显示
	 * @param page
	 * @param rows
	 * @param monkeyid(幼崽编号)
	 * @param motherid(母猴编号)
	 * @param leavebreastdate(离乳日期)
	 * @return
	 */
	Map<String, Object> loadListByCondition(String page, String rows,
			String monkeyid, String motherid, String leavebreastdate);
	
	List<LeavebreastErport> getLeavebreastErport(String monkeyid,String leavedate);
	
	Map<String,Object> getLeavebreast(String page, String rows,String monkeyid,String leavebreastdate);
	
	List<Leavebreast> getLeavebreastById(String monkeyid);
}
