package com.lanen.service.path;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.path.TblPathPlanAttachedViscera;
import com.lanen.model.path.TblPathPlanVisceraWeigh;

/**
 * 病理计划-脏器称重-附加脏器     service 
 * @author 曾锋
 */
public interface TblPathPlanAttachedVisceraService extends BaseDao<TblPathPlanAttachedViscera> {

	/**根据称重计划表Id查询附加称重记录
	 * @param visceraWeighPlanID(病理计划-脏器称重表Id)
	 * @return
	 */
	List<TblPathPlanAttachedViscera> getListByPid(String visceraWeighPlanID);

	/**添加附加脏器，并更新脏器称重列表中对应的实体项有无附加脏器为1(0,无;1,有)
	 * @param tblPathPlanAttachedViscera
	 * @param tblPathPlanVisceraWeigh
	 */
	void addSave(TblPathPlanAttachedViscera tblPathPlanAttachedViscera,
			TblPathPlanVisceraWeigh tblPathPlanVisceraWeigh);
    /**根据课题编号查找病理计划-附加脏器列表ID
     * @param studyNo
     * @return
     */
    List<String> getIdListByStudyNo(String studyNo);
    
    /**根据课题编号查找病理计划-附加脏器列表
     * @param studyNo
     * @return
     */
    List<TblPathPlanAttachedViscera> getListByStudyNo(String studyNo);
    
}
