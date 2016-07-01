package com.lanen.service.schdeule;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.schedule.TblAttachment;
import com.lanen.model.schedule.TblAttachmentIndex;

/**
 * 附件索引表service
 * @author Administrator
 *
 */
public interface TblAttachmentIndexService extends BaseDao<TblAttachmentIndex>{

	/**加载所有创建者 存放于mapList<Map<id,text>>
	 * @return
	 */
	List<Map<String, Object>> getAllCreaterMapList();

	/**条件查询列表
	 * @param startDate	开始日期
	 * @param endDate	结束日期
	 * @param creater	创建者（""：全部）
	 * @param state		状态（-1：全部  ， 0,1）
	 * @param condition	模糊条件（studyNo，describe）
	 * @return
	 */
	List<Map<String, Object>> getMapListByDateCreaterStateStudyNoDescribe(
			Date startDate, Date endDate, String creater, int state,
			String condition);

	/**查询一个索引下的多个文件
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> getMapListById(String id);

	/** 根据id 获得附件实体
	 * @param id
	 * @return
	 */
	TblAttachment getTblAttachmentById(String id);

	

	/**保存附件索引及附件列表
	 * @param tblAttachmentIndex
	 * @param tblAttachmentList
	 */
	void save(TblAttachmentIndex tblAttachmentIndex,
			List<TblAttachment> tblAttachmentList);

	/**是否是未提交  （附件索引及附件列表  的  state 全为  0  => true  ,否则为    false）
	 * @param id
	 * @return
	 */
	boolean isUncommittedById(String id);

	/**撤销
	 * @param id
	 */
	void cancelById(String id);

	/**标记索引及附件已打印（已标记的除外）
	 * @param id
	 * @param userName
	 */
	void signHasPrintById(String id, String userName);

	/**是否存在未提交的？
	 * @param id
	 * @return
	 */
	boolean isHasUncommittedById(String id);

	/**标记附件已打印，若（索引对应都打印则 索引标记为已打印）
	 * @param id  附件id
	 * @param userName
	 * @param indexId 
	 */
	void signHasPrintOneById(String id, String userName, String indexId);

	/**判断文件是否是未撤销
	 * @param id
	 * @return
	 */
	boolean isNoCancelById(String id);

	/**
	 * 从 DictAttachmentRootDirectory 获取根目录
	 * @return
	 */
	String getRootDirectory();
	
	/**从 DictAttachmentRootDirectory 获取根目录(完整的smb路径，包括用户名和密码)
	 * @return
	 */
	String getSmbRootDirectory();

}
