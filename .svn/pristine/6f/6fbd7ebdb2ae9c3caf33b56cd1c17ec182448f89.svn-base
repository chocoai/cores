package com.lanen.service.path;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.path.TblPathPlanCheck;
import com.lanen.model.studyplan.TblStudyPlan;
/**
 * 病理计划-脏器/组织学检查     service 
 * @author 曾锋
 */
public interface TblPathPlanCheckService extends BaseDao<TblPathPlanCheck> {

	/**根据课题编号查找实体
	 * @param studyNoPara
	 * @return
	 */
	List<TblPathPlanCheck> getListByStudyNo(String studyNoPara);

	/**数据库获得Sn(当前课题下最大Sn+1)
	 * @param studyNo (课题编号) 
	 * @return
	 */
	int getSn(String studyNo);

	/**根据脏器编号及课题编号查询实体
	 * @param visceraCode
	 * @param studyNo
	 * @return
	 */
	TblPathPlanCheck getByVisceraCode(String visceraCode,String studyNo);

	/**保存病理计划-脏器/组织学检查  
	 * @param tblStudyPlan
	 * @param list
	 */
	void addSavePathPlanCheck(TblStudyPlan tblStudyPlan,
			List<TblPathPlanCheck> list);

	/**加载脏器列表，根据动物类型和课题编号
	 * @param animalTypeId
	 * @param studyNoPara
	 * @return
	 */
	List<Map<String, Object>> getVisceraListByAnimalTypeAndStudyNo(
			String animalTypeId, String studyNoPara);

}
