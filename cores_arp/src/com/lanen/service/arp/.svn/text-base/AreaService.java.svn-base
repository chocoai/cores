package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Area;
import com.lanen.model.AreaJson;

public interface AreaService extends BaseLongDao<Area>{
	
	/**加载所有未删除的区域和房间数据
	 * @return
	 */
	List<Area> getAllArea();

	/**得到所以的父区域名字和ID
	 * @return
	 */
	List<Map<String, Object>> getAllPareaIdName();

	/**数据库中检查区域是否已存在
	 * @param areaname
	 * @return
	 */
	boolean isExistName(String areaname);

	/**根据父区域ID拿到所有房间
	 * @param blongArea
	 * @return
	 */
	List<Map<String, Object>> getAllRoomIdName(Long blongArea);
	/**根据房间名得到房间信息
	 * @param areaname
	 * @return
	 */
	Area getAreaByName(String areaname);

	/**得到所有房间的饲养员（不重复）
	 * @return
	 */
	List<Long> getAllKeeper();

	/**根据饲养员查询房间信息
	 * @param keeper
	 * @return
	 */
	List<Area> getIdsByKeeper(Long keeper);

	/**根据房间ID得到房间内各种猴子的数量
	 * @param id
	 * @return
	 */
	AreaJson getAreanameAndMonkeyCount(Long id);

	/**得到表头
	 * @return
	 */
	ArrayList<Object> getExcelFiledNameList();

	/**获取表数据
	 * @param keeper
	 * @return
	 */
	ArrayList<Object> getExcelFiledDataList(Long keeper);

	/**根据父区域Id查找下面所有房间信息
	 * @param blongId 
	 * @return
	 */
	List<Area> getListByBlongId(Long blongarea);

	/**加载所有未删除的区域和房间数据
	 * @return
	 */
	List<?> getAllAreaList();
	
	/**加载所有治疗房
	 * @return
	 */
	List<Map<String, String>> getTreatRoom(String name);

	Map<String,Object> getRoomByKeeper(String rows,String page,Long keeper,Long area);
	List<AreaJson> getRoomByKeeperAndArea(Long keeper,Long area );
	List<?> getAnimal(Long id,Long keeper);
	List<?> getAnimal(Long id);
	List<Map<String, String>> getRoom();
	//获取所有房间信息且blongarea!=null
	Map<String,Object> getAllRoomName(String rows,String page);
	List<AreaJson> getAllRoomName();
	
	Map<String,Object> getAnimalByArea(String rows,String page,String areaid);
	
	List<?> getAnimalRoomByArea(String roomid);
	/**
	 * 获取房舍
	 * @return
	 */
	List<Area> getAreas();
	/**
	 * 根据饲养员获取动物.
	 */
	List<?> getAnimalByKeeper(Long keeper);
	/**
	 * 在场 deleted=0,status=1
	 * @param id
	 * @return
	 */
	List<?> getInAnimal(Long id);
	/**
	 * 待销售 deleted=0,status=2
	 * @param id
	 * @return
	 */
	List<?> getOutAnimal(Long id);
	/**
	 * 根据饲养员获取在场动物.
	 */
	List<?> getInAnimalByKeeper(Long keeper);
	/**
	 * 根据饲养员获取待销售动物.
	 */
	List<?> getOutAnimalByKeeper(Long keeper);
	List<?> getAnimalByArea(Long id);
}
