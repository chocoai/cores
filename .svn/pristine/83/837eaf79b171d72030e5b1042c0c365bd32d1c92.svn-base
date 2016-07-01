package com.lanen.service.path;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.path.TblTissueSliceIndex;
import com.lanen.model.path.TblTissueSliceSn;
import com.lanen.model.path.TblTissueSliceViscera;

/**组织切片编号索引 Service
 * 
 * @author Administrator
 *
 */
public interface TblTissueSliceIndexService extends BaseDao<TblTissueSliceIndex>{

	/** 查询该任务下已固定脏器列表及其子脏器，Map
	 * @param taskId
	 * @return
	 */
	List<Map<String, Object>> getFiexdVisceraMapList(String taskId);
	
	/** taskId,sliceCodeType,operatorSign,gender,groupId ，根据任务Id号及切片编号类型
	 * @param taskId
	 * @param sliceCodeType
	 * @return
	 */
	List<Map<String, Object>> getMapListByTaskIdSliceCodeType(String taskId, int sliceCodeType);
	
	/**根据任务Id号及切片编号类型 查询实体(类型仅限 0,1)
	 * @param taskId
	 * @param sliceCodeType
	 * @return
	 */
	TblTissueSliceIndex getByTaskIdSliceCodeType(String taskId, int sliceCodeType);
	
	/** 查询 sliceCode,visceraType,visceraCode,visceraName,subVisceraCode,subVisceraName,sn,subSn    ， 根据任务Id号,类型仅限0（常规组织取材编号），
	 * @param taskId
	 * @return
	 */
	List<Map<String, Object>> getSliceSnVisceraMapListByTaskId(String taskId);
	
	/**查询任务对应申请的脏器列表（固定或镜检）， visceraType,visceraCode,visceraName,subVisceraCode,subVisceraName,sn,subSn(类型仅限 0)
	 * @param taskId
	 * @param showHistopathViscera   仅查询需镜检脏器
	 * @return
	 */
	List<Map<String, Object>> getVisceraMapListByTaskId(String taskId, boolean showHistopathViscera);
	
	/**保存切片 索引，范围，编号，脏器
	 * @param tblTissueSliceIndex（无主键及时间）
	 * @param dosageNumList
	 * @param sliceCodeList
	 * @param visceraMapList
	 */
	TblTissueSliceIndex addOne(TblTissueSliceIndex tblTissueSliceIndex, List<Integer> dosageNumList,
			List<String> sliceCodeList, List<Map<String, Object>> visceraMapList);

	/**更新切片 索引，范围，编号，脏器
	 * @param tblTissueSliceIndex
	 * @param dosageNumList
	 * @param sliceCodeList
	 * @param visceraMapList
	 */
	TblTissueSliceIndex updateOne(TblTissueSliceIndex tblTissueSliceIndex, List<Integer> dosageNumList,
			List<String> sliceCodeList, List<Map<String, Object>> visceraMapList);
	
	/**查询需镜检但未切片编号脏器列表（子脏器编号也算编号了），visceraCode,visceraName
	 * @param taskId
	 * @return
	 */
	List<Map<String, Object>> getRemainVisceraMapListByTaskId(String taskId);
	
	/**根据任务id加载列表（已签字的）id,sliceCodeType,createTime,creator,gender
	 * @param taskId
	 */
	List<Map<String,Object>> getListByTaskId(String taskId);
	
	/**根据索引id，查询使用组及组名dosageNum,dosageDesc
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> getGroupMapListByIndexId(String id);
	
	/**加载  1.有异常且固定的脏器-去重（ 未 被常规组织切片编号的） 2.固定的非常规病变的组织，
	 * Map : animalCode,visceraOrTissueName,visceraType,visceraCode,visceraFixedRecordId
	 * @param taskId
	 */
	List<Map<String,Object>> getAnatomyCheckTissueCodeByTaskId(String taskId);
	/**加载  1.有异常且固定的脏器-去重（ 已 被常规组织切片编号的）
	 * Map : animalCode,visceraOrTissueName,visceraType,visceraCode,visceraFixedRecordId
	 * @param taskId
	 */
	List<Map<String,Object>> getAnatomyCheckTissueCodeByTaskId_2(String taskId);
	
	/**保存切片 索引（切片类型为1），编号，脏器
	 * @param tblTissueSliceIndex（无主键及时间专题编号）
	 * @param tblTissueSliceSnList
	 * @param tblTissueSliceVisceraList
	 */
	void addOne(TblTissueSliceIndex tblTissueSliceIndex,
			List<TblTissueSliceSn> tblTissueSliceSnList,
			List<TblTissueSliceViscera> tblTissueSliceVisceraList);

	/**更新切片 索引（切片类型为1），编号，脏器
	 * @param tblTissueSliceIndex
	 * @param tblTissueSliceSnList
	 * @param tblTissueSliceVisceraList
	 */
	void updateOne(TblTissueSliceIndex tblTissueSliceIndex,
			List<TblTissueSliceSn> tblTissueSliceSnList,
			List<TblTissueSliceViscera> tblTissueSliceVisceraList);
	
	/** 根据切片编号索引表id，查询切片编号及脏器、组织（异常组织取材编号）
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> getSliceCodeByIndexId(String id);
	
	/**根据切片编号索引表id，查询 未 切片编号及脏器、组织（异常组织取材编号）
	 * @param snId
	 * @param taskId
	 * @return
	 */
	List<Map<String, Object>> getSliceCodeListByIndexId_2(String snId, String taskId);
	
	/**查询追加组织取材编号索引表List（sliceTypeCode == 2）
	 * @param taskId
	 * @return
	 */
	List<TblTissueSliceIndex> getAddToListByTaskId(String taskId);
	
	/**根据切片编号索引表id,查询适用组别范围
	 * @param id
	 * @return
	 */
	List<Integer> getGroupIdListByIndexId(String id);
	
	/**查询 sliceCode,visceraType,visceraCode,visceraName,subVisceraCode,subVisceraName,sn,subSn    ， 根据索引Id号
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> getSliceSnVisceraMapListByIndexId(String id);

	/**常规或追加 使用过的切片编号个数（已签字）
	 * @param taskId
	 * @return
	 */
	Integer getSliceCodeNum(String taskId);
}
