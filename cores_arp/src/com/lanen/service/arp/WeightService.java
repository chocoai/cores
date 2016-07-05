package com.lanen.service.arp;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Weight;

public interface WeightService  extends BaseLongDao<Weight>{
	/**
	 * 加载所有称重信息
	 * @param page
	 * @param rows
	 * @return
	 */
	Map<String, Object>  getAllWeight(String page,String rows,int weighttype,String startDate ,String endDate);
	
	/**
	 * 
	 */
	List<Weight> getOneWeight(String monkeyid);
	/**
	 * 保存
	 * @param weight
	 */
	void saveWeight(Weight weight);
	/**
	 * 更新
	 * @param weight
	 */
	void updateWeight(Weight weight);
	
	List<String> getMonkeyid();
	Integer getWeightType(String monkeyid);
	Float getWeight(String monkeyid,Integer type);
	String getSex(String monkeyid);
	List<?> getMore(String monkeyid);
	
	/**
	 * 体格检查记录
	 * @param weight
	 */
	Map<String,Object> getWeight(String page,String rows,String monkeyid,String weightdate);
	Map<String,Object> getWeight(String monkeyid,String weightdate);
	/**
	 * 称重类型
	 * @param mark
	 * @return
	 */
	List<Map<String,String>> getWeightTypeMap(String mark);
	/**
	 * 最近4次称重记录.
	 */
	List<Weight> getLast4WeightRecord(String monkeyid);
	/**
	 * 该动物的称重记录--weighttype=14.
	 */
	List<?> getAllWeightById(String monkeyid);
	/**
	 * 该动物的称重记录--weighttype=14.
	 */
	List<?> getAllWeightById(String monkeyid,String weightdate);
}
