package com.lanen.service.contract;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.contract.TblStudyScheduleNode;

/**
 * 项目进度节点设置 service
 * @author 黄国刚
 *
 */
public interface TblStudyScheduleNodeService extends BaseDao<TblStudyScheduleNode>{

	/**根据专题类别编号查询  项目进度节点设置列表
	 * @param studyTypeCode
	 * @return
	 */
	List<TblStudyScheduleNode> getListByStudyTypeCode(String studyTypeCode);

	/**判断nodeName 是否存在
	 * @param studyTypeCode   当前专题类别或@@@@@@ 下
	 * @param nodeName
	 * @return
	 */
	boolean isExistNodeName(String studyTypeCode, String nodeName);

	/**加载非默认节点名称的所有名称（distinct）
	 * @return
	 */
	List<String> getNoDefaultNodeNameList();

	/**判断该专题类别编号是否有节点
	 * @param studyTypeCode
	 * @return
	 */
	boolean isHasInit(String studyTypeCode);

	/**初始哈默认节点
	 * @param studyTypeCode
	 */
	void initStudyNode(String studyTypeCode);

	/**获得下一个节点序号
	 * @param nodeSn
	 * @return
	 */
	int getNextNodeSn(int nodeSn,String studyTypeCode);

	/**根据专题类别编号和节点名称查询实体
	 * @param nodeName
	 * @param studyTypeCode
	 * @return
	 */
	TblStudyScheduleNode getByNodeNameStudyTypeCode(String nodeName,
			String studyTypeCode);

	/**上移
	 * @param id
	 */
	boolean upNode(String id);

	/**下移
	 * @param id
	 * @return
	 */
	boolean downNode(String id);

}
