package com.lanen.service.arp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.RoomDisinfectRecord;

public interface RoomDisinfectRecordService extends BaseLongDao<RoomDisinfectRecord> {

	/**加载已有的消毒方式
	 * @return
	 */
	List<Map<String, Object>> loadAllDisinfectType();
	 /**根据消毒液ID到房间消毒记录表中检查消毒液是否已使用过0:未使用 1：已使用
     * @param id
     * @return
     */
    int isUsed(Long id);
	/**根据房间Id和消毒日期查找消毒记录
	 * @param areaId
	 * @param disDate
	 * @return
	 */
    Map<String, Object> getlistByRoomIdDisDate(String page, String rows,Long areaId, Date disDate);
}
