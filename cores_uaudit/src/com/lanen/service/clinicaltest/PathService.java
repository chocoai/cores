package com.lanen.service.clinicaltest;

import java.util.List;
import java.util.Map;

/**病理service
 * @author Administrator
 *
 */
public interface PathService {

	/**获取年份列表（含解剖申请的专题）,降序排列
	 * @return
	 */
	List<String> getYearList();

	/**专题编号列表（含解剖申请的专题）
	 * @param year
	 * @return
	 */
	List<Map<String, Object>> getStudyNoMapListByYear(String year);

	/**查询数据确认数据（添加 ，编辑，删除）
	 * @param studyNo
	 * @param operateType 1 添加 ，2 编辑，3 删除
	 * @return
	 */
	List<Map<String, Object>> getDataConfirmMapList(String studyNo,
			int operateType);

	/**查询数据修改数据
	 * @param studyNo
	 * @return
	 */
	List<Map<String, Object>> getDataEditMapList(String studyNo);

	/**撤销的解剖申请列表
	 * @param studyNo
	 * @return
	 */
	List<Map<String, Object>> getAnatomyReqCancelMapList(String studyNo);

	/**变更的解剖申请列表
	 * @param studyNo
	 * @return
	 */
	List<Map<String, Object>> getAnatomyReqChangeMapList(String studyNo);

	/**查询解剖申请表数据（animalType，anatomyRsn，testPhase，anatomyDate，animalList,anatomyVisceraList,visceraWeighList）
	 * @param studyNo
	 * @param reqNo
	 * @param change  1：查询历史表
	 * @return
	 */
	Map<String, Object> getReqView(String studyNo, int reqNo, int change);

	/**查询镜检数据
	 * @param studyNo
	 * @param operateType 9：全部   10：镜检数据-添加  11：镜检数据-删除
	 * @return
	 */
	List<Map<String, Object>> getHistopathCheckMapList(String studyNo,
			int operateType);

	/**查询称重数据修改记录
	 * @param studyNo
	 * @param operateType 12：全    13：重新称量  14:删除  15：编辑
	 * @return
	 */
	List<Map<String, Object>> getVisceraWeightDataMapList(String studyNo,
			int operateType);

	/**
	 * @param studyNo
	 * @param operateType   16:全部      17：数据确认_添加   18：数据修改_添加
	 * @return
	 */
	List<Map<String, Object>> getVisceraFixedMapList(String studyNo,
			int operateType);

	/**动物死亡日期修改痕迹
	 * @param studyNo
	 * @return
	 */
	List<Map<String, Object>> getAnimalDeadDateTraceMapList(String studyNo);

}
