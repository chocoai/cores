package com.lanen.service.contract;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.contract.TblStudySchedule;

public interface TblStudyScheduleService extends BaseDao<TblStudySchedule> {
	 /**
	  * 根据合同编号查询对应的专题列表
	  * @param contractCode
	  * @return
	  */
	List<?> getTblStudyScheduleByContractCode(String contractCode);

	/** 查询节点列表，若为空(仅有SD任命视为空)，则加载专题对应  专题列别  的列表，若还没有，则加载默认列表
	 * @param studyNo
	 * @return
	 */
	List<TblStudySchedule> getListByStudyNo(String studyNo);
	/**
	 * 通过专题编号获得百分比
	 * @param studyNo
	 * @return
	 */
	String getPercentageByStudyNo(String studyNo);
	
	/**
	 * 根据课题编号获取当前进度节点
	 * @param studyNo
	 * @return
	 */
	TblStudySchedule getByStudyNoMaxStudySchedule(String studyNo);
	
	/**判断当前课题是否有节点计划(仅有SD任命视为没有)
	 * @param studyNo
	 * @return
	 */
	boolean isHasStudyNo(String studyNo);
	
	
	/**根据   studyNo,nodeName 查询实体
	 * @param studyNo
	 * @param nodeName
	 * @return
	 */
	TblStudySchedule getByStudyNoNodeName(String studyNo, String nodeName);
	
	/**查询节点设置表，获得     nodeName，planDays(试验分组除外)
	 * @param studyNo
	 * @return
	 */
	Map<String, String> getMapByStudyNo(String studyNo);
	/**根据节点编号查询此节点之前的节点 ，获得节点编号和实际完成日期
	 * @param studyNo
	 * @param NodeSn
	 * @return
	 */
	List<Map<Integer, Object>>  getMapBeforeNodeSn(String studyNo,int NodeSn);
}
